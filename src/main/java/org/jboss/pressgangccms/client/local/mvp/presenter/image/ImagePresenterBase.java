package org.jboss.pressgangccms.client.local.mvp.presenter.image;

import java.util.Arrays;
import java.util.List;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.constants.ServiceConstants;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.image.RESTLanguageImageV1Editor;
import org.jboss.pressgangccms.client.local.utilities.GWTUtilities;
import org.jboss.pressgangccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTStringConstantV1;
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
    protected RESTImageV1 displayedImage;

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
        if (displayedImage.getLanguageImages_OTM() != null && displayedImage.getLanguageImages_OTM().getItems() != null) {
            for (final RESTLanguageImageV1 langImage : displayedImage.getLanguageImages_OTM().getItems()) {
                newLocales.remove(langImage.getLocale());
            }
        }

        return newLocales;
    }

    protected void getLocales() {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new RESTCalls.RESTCallback<RESTStringConstantV1>() {
            @Override
            public void begin() {
                startProcessing();
            }

            @Override
            public void generalException(final Exception ex) {
                stopProcessing();
            }

            @Override
            public void success(final RESTStringConstantV1 retValue) {
                try {
                    /* Get the list of locales from the StringConstant */
                    locales = retValue.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
                } finally {
                    stopProcessing();
                }
            }

            @Override
            public void failed() {
                stopProcessing();
            }
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRINGCONSTANT);
    }

    protected void bindImageViewButtons(final ImagePresenter.Display imageDisplay) {
        imageDisplay.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getEditor().flush();

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(displayedImage.getId());
                updateImage.explicitSetDescription(displayedImage.getDescription());

                final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                    @Override
                    public void begin() {
                        startProcessing();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        stopProcessing();
                    }

                    @Override
                    public void success(final RESTImageV1 retValue) {
                        try {
                            displayedImage = retValue;
                            reInitialiseImageView();
                        } finally {
                            stopProcessing();
                        }
                    }

                    @Override
                    public void failed() {
                        stopProcessing();
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
                        final RESTLanguageImageV1 selectedImage = imageDisplay.getEditor().languageImages_OTMEditor()
                                .itemsEditor().getList().get(selectedTab);

                        /*
                         * Create the image to be modified. This is so we don't send off unnessessary data.
                         */
                        final RESTImageV1 updateImage = new RESTImageV1();
                        updateImage.setId(displayedImage.getId());

                        /* Create the language image */
                        final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                        languageImage.setRemoveItem(true);
                        languageImage.setId(selectedImage.getId());

                        /* Add the langauge image */
                        updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                        updateImage.getLanguageImages_OTM().addItem(languageImage);

                        final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                            @Override
                            public void begin() {
                                startProcessing();
                            }

                            @Override
                            public void generalException(final Exception ex) {
                                stopProcessing();
                            }

                            @Override
                            public void success(final RESTImageV1 retValue) {
                                try {
                                    displayedImage = retValue;
                                    reInitialiseImageView();
                                } finally {
                                    stopProcessing();
                                }
                            }

                            @Override
                            public void failed() {
                                stopProcessing();
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
                if (displayedImage.getLanguageImages_OTM() != null && displayedImage.getLanguageImages_OTM().getItems() != null) {
                    for (final RESTLanguageImageV1 langImage : displayedImage.getLanguageImages_OTM().getItems()) {
                        if (langImage.getLocale().equals(selectedLocale)) {
                            return;
                        }
                    }
                }

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(displayedImage.getId());

                /* Create the language image */
                final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                languageImage.setAddItem(true);
                languageImage.explicitSetLocale(selectedLocale);

                /* Add the langauge image */
                updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                updateImage.getLanguageImages_OTM().addItem(languageImage);

                final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                    @Override
                    public void begin() {
                        startProcessing();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        stopProcessing();
                    }

                    @Override
                    public void success(final RESTImageV1 retValue) {
                        try {
                            displayedImage = retValue;
                            reInitialiseImageView();
                        } finally {
                            stopProcessing();
                        }
                    }

                    @Override
                    public void failed() {
                        stopProcessing();
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
     */
    protected void bindImageUploadButtons(final ImagePresenter.Display imageDisplay) {
        if (imageDisplay.getEditor() == null) {
            throw new IllegalStateException("display.getEditor() cannot be null");
        }

        for (final RESTLanguageImageV1Editor editor : imageDisplay.getEditor().languageImages_OTMEditor().itemsEditor()
                .getEditors()) {
            editor.getUploadButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    for (final File file : editor.getUpload().getFiles()) {
                        startProcessing();

                        final FileReader reader = new FileReader();

                        reader.addErrorHandler(new ErrorHandler() {
                            @Override
                            public void onError(final org.vectomatic.file.events.ErrorEvent event) {
                                stopProcessing();
                            }
                        });

                        reader.addLoadEndHandler(new LoadEndHandler() {
                            @Override
                            public void onLoadEnd(final LoadEndEvent event) {
                                try {
                                    final String result = reader.getStringResult();
                                    final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                                    /*
                                     * Create the image to be modified. This is so we don't send off unnessessary data.
                                     */
                                    final RESTImageV1 updateImage = new RESTImageV1();
                                    updateImage.setId(displayedImage.getId());

                                    /* Create the language image */
                                    final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                                    languageImage.setAddItem(true);
                                    languageImage.explicitSetLocale(editor.self.getLocale());
                                    languageImage.explicitSetImageData(buffer);
                                    languageImage.explicitSetFilename(file.getName());

                                    /* Delete the old language image */
                                    final RESTLanguageImageV1 deleteLanguageImage = new RESTLanguageImageV1();
                                    deleteLanguageImage.setRemoveItem(true);
                                    deleteLanguageImage.setId(editor.self.getId());

                                    /* Add the langauge image */
                                    updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                                    updateImage.getLanguageImages_OTM().addItem(languageImage);
                                    updateImage.getLanguageImages_OTM().addItem(deleteLanguageImage);

                                    final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                                        @Override
                                        public void begin() {
                                            startProcessing();
                                        }

                                        @Override
                                        public void generalException(final Exception ex) {
                                            stopProcessing();
                                        }

                                        @Override
                                        public void success(final RESTImageV1 retValue) {
                                            try {
                                                displayedImage = retValue;
                                                reInitialiseImageView();
                                            } finally {
                                                stopProcessing();
                                            }
                                        }

                                        @Override
                                        public void failed() {
                                            stopProcessing();
                                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                                        }
                                    };

                                    RESTCalls.saveImage(callback, updateImage);
                                } finally {
                                    stopProcessing();
                                }
                            }
                        });

                        reader.readAsBinaryString(file);
                    }
                }
            });
        }

    }

    abstract protected void stopProcessing();

    abstract protected void startProcessing();

    abstract protected void reInitialiseImageView();
}
