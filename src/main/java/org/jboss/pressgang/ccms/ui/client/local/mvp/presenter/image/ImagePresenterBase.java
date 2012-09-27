package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import java.util.Arrays;
import java.util.List;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTLanguageImageV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

abstract public class ImagePresenterBase extends TemplatePresenter {
    /** The currently displayed image. */
    protected ProviderUpdateData<RESTImageCollectionItemV1> imageData = new ProviderUpdateData<RESTImageCollectionItemV1>();

    /** A reference to the StringConstants that holds the locales. */
    protected String[] locales;

    protected String getQuery(final ImageFilteredResultsPresenter.Display imageSearchDisplay) {
        final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
        if (!imageSearchDisplay.getImageIdFilter().getText().isEmpty()) {
            retValue.append(";imageIds=" + imageSearchDisplay.getImageIdFilter().getText());
        }
        if (!imageSearchDisplay.getImageDescriptionFilter().getText().isEmpty()) {
            retValue.append(";imageDesc=" + imageSearchDisplay.getImageDescriptionFilter().getText());
        }
        if (!imageSearchDisplay.getImageOriginalFileNameFilter().getText().isEmpty()) {
            retValue.append(";imageOrigName=" + imageSearchDisplay.getImageOriginalFileNameFilter().getText());
        }

        return retValue.toString();
    }

    protected List<String> getUnassignedLocales() {
        final List<String> newLocales = Arrays.asList(locales);

        /* Make it so you can't add a locale if it already exists */
        if (imageData.getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
            for (final RESTLanguageImageCollectionItemV1 langImage : imageData.getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                newLocales.remove(langImage.getItem().getLocale());
            }
        }

        return newLocales;
    }

    protected void getLocales(final BaseTemplateViewInterface imageDisplay) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new RESTCalls.RESTCallback<RESTStringConstantV1>() {
            @Override
            public void begin() {
                imageDisplay.addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                imageDisplay.removeWaitOperation();
            }

            @Override
            public void success(final RESTStringConstantV1 retValue) {
                try {
                    /* Get the list of locales from the StringConstant */
                    locales = retValue.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
                } finally {
                    imageDisplay.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                imageDisplay.removeWaitOperation();
            }
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }

    protected void bindImageViewButtons(final ImagePresenter.Display imageDisplay, final BaseTemplateViewInterface waitDisplay) {
        
        imageDisplay.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getEditor().flush();

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(imageData.getDisplayedItem().getItem().getId());
                updateImage.explicitSetDescription(imageData.getDisplayedItem().getItem().getDescription());

                final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                    @Override
                    public void begin() {
                        waitDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        waitDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageV1 retValue) {
                        try {
                            retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                            reInitialiseImageView();
                            Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                        } finally {
                            waitDisplay.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        waitDisplay.removeWaitOperation();
                    }
                };

                RESTCalls.saveImage(callback, updateImage);

            }
        });

        imageDisplay.getAddLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getAddLocaleDialog().getDialogBox().center();
                imageDisplay.getAddLocaleDialog().getDialogBox().show();
            }
        });

        imageDisplay.getRemoveLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete())) {

                    final int selectedTab = imageDisplay.getEditor().languageImages_OTMEditor().getSelectedIndex();
                    if (selectedTab != -1) {
                        final RESTLanguageImageCollectionItemV1 selectedImage = imageDisplay.getEditor().languageImages_OTMEditor()
                                .itemsEditor().getList().get(selectedTab);

                        /*
                         * Create the image to be modified. This is so we don't send off unnessessary data.
                         */
                        final RESTImageV1 updateImage = new RESTImageV1();
                        updateImage.setId(imageData.getDisplayedItem().getItem().getId());

                        /* Create the language image */
                        final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                        languageImage.setId(selectedImage.getItem().getId());

                        /* Add the langauge image */
                        updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                        updateImage.getLanguageImages_OTM().addRemoveItem(languageImage);

                        final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                            @Override
                            public void begin() {
                                waitDisplay.addWaitOperation();
                            }

                            @Override
                            public void generalException(final Exception ex) {
                                waitDisplay.removeWaitOperation();
                            }

                            @Override
                            public void success(final RESTImageV1 retValue) {
                                try {
                                    retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                                    reInitialiseImageView();
                                } finally {
                                    waitDisplay.removeWaitOperation();
                                }
                            }

                            @Override
                            public void failed() {
                                waitDisplay.removeWaitOperation();
                            }
                        };

                        RESTCalls.saveImage(callback, updateImage);
                    }
                }
            }
        });

        imageDisplay.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = imageDisplay.getAddLocaleDialog().getLocales()
                        .getItemText(imageDisplay.getAddLocaleDialog().getLocales().getSelectedIndex());

                /* Don't add locales twice */
                if (imageData.getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
                    for (final RESTLanguageImageCollectionItemV1 langImage : imageData.getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                        if (langImage.getItem().getLocale().equals(selectedLocale)) {
                            return;
                        }
                    }
                }

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(imageData.getDisplayedItem().getItem().getId());

                /* Create the language image */
                final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                languageImage.explicitSetLocale(selectedLocale);

                /* Add the langauge image */
                updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                updateImage.getLanguageImages_OTM().addNewItem(languageImage);

                final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                    @Override
                    public void begin() {
                        waitDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        waitDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageV1 retValue) {
                        try {
                            retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                            reInitialiseImageView();
                        } finally {
                            waitDisplay.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        waitDisplay.removeWaitOperation();
                    }
                };

                RESTCalls.saveImage(callback, updateImage);
            }
        });

        imageDisplay.getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getAddLocaleDialog().getDialogBox().hide();
            }
        });
    }

    /**
     * Each Language Image has an upload button that needs to be bound to some behaviour.
     * 
     * @param imageDisplay The view that displays the image details.
     * @param waitDisplay The view that displays the waiting screen
     */
    protected void bindImageUploadButtons(final ImagePresenter.Display imageDisplay, final BaseTemplateViewInterface waitDisplay) {
        if (imageDisplay.getEditor() == null) {
            throw new IllegalStateException("display.getEditor() cannot be null");
        }

        for (final RESTLanguageImageV1Editor editor : imageDisplay.getEditor().languageImages_OTMEditor().itemsEditor()
                .getEditors()) {
            editor.getUploadButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    
                    /*
                     * There should only be one file, but use a loop to accommodate any changes that might implement multiple
                     * files
                     */
                    for (final File file : editor.getUpload().getFiles()) {
                        waitDisplay.addWaitOperation();

                        final FileReader reader = new FileReader();

                        reader.addErrorHandler(new ErrorHandler() {
                            @Override
                            public void onError(final org.vectomatic.file.events.ErrorEvent event) {
                                imageDisplay.removeWaitOperation();
                            }
                        });

                        reader.addLoadEndHandler(new LoadEndHandler() {
                            @Override
                            public void onLoadEnd(final LoadEndEvent event) {
                                try {
                                    final String result = reader.getStringResult();
                                    final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                                    /*
                                     * Create the image to be modified. This is so we don't send off unnecessary data.
                                     */
                                    final RESTImageV1 updateImage = new RESTImageV1();
                                    updateImage.setId(imageData.getDisplayedItem().getItem().getId());
                                    
                                    /* Create the language image */
                                    final RESTLanguageImageV1 updatedLanguageImage = new RESTLanguageImageV1();
                                    updatedLanguageImage.setId(editor.self.getItem().getId());
                                    updatedLanguageImage.explicitSetImageData(buffer);
                                    updatedLanguageImage.explicitSetFilename(file.getName());

                                    /* Add the language image */
                                    updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                                    updateImage.getLanguageImages_OTM().addUpdateItem(updatedLanguageImage);

                                    final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                                        @Override
                                        public void begin() {
                                            waitDisplay.addWaitOperation();
                                        }

                                        @Override
                                        public void generalException(final Exception ex) {
                                            waitDisplay.removeWaitOperation();
                                        }

                                        @Override
                                        public void success(final RESTImageV1 retValue) {
                                            try {
                                                retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                                                reInitialiseImageView();
                                            } finally {
                                                waitDisplay.removeWaitOperation();
                                            }
                                        }

                                        @Override
                                        public void failed() {
                                            waitDisplay.removeWaitOperation();
                                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                                        }
                                    };

                                    RESTCalls.saveImage(callback, updateImage);
                                } finally {
                                    waitDisplay.removeWaitOperation();
                                }
                            }
                        });

                        reader.readAsBinaryString(file);
                    }
                }
            });
        }
    }

    abstract protected void reInitialiseImageView();
}
