package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import java.util.Arrays;
import java.util.List;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
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

public class ImageComponent extends ComponentBase<ImagePresenter.Display> implements ImagePresenter.LogicComponent {
    /**
     * The currently displayed image.
     */
    private ProviderUpdateData<RESTImageCollectionItemV1> imageData;

    /**
     * A reference to the StringConstants that holds the locales.
     */
    private String[] locales;

    @Override
    public ProviderUpdateData<RESTImageCollectionItemV1> getImageData() {
        return imageData;
    }

    @Override
    public void setImageData(ProviderUpdateData<RESTImageCollectionItemV1> imageData) {
        this.imageData = imageData;
    }

    public void setLocales(final String[] locales) {
        this.locales = locales;
    }

    public String[] getLocales() {
        return locales;
    }

    public ImageComponent() {
        final RESTImageCollectionItemV1 item = new RESTImageCollectionItemV1();
        item.setItem(new RESTImageV1());
        imageData = new ProviderUpdateData<RESTImageCollectionItemV1>(item);
    }

    @Override
    public List<String> getUnassignedLocales() {
        final List<String> newLocales = Arrays.asList(locales);

        /* Make it so you can't add a locale if it already exists */
        if (imageData.getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
            for (final RESTLanguageImageCollectionItemV1 langImage : imageData.getDisplayedItem().getItem()
                    .getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                newLocales.remove(langImage.getItem().getLocale());
            }
        }

        return newLocales;
    }

    private void populateLocales() {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                        /* Get the list of locales from the StringConstant */
                        locales = retValue.getValue().replaceAll("\\n", "").replaceAll(" ", "").split(",");
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }

    private void bindImageViewButtons() {

        display.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getEditor().flush();

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(imageData.getDisplayedItem().getItem().getId());
                updateImage.explicitSetDescription(imageData.getDisplayedItem().getItem().getDescription());

                final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                        waitDisplay, new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                                retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                                reInitialiseImageView();
                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                RESTCalls.updateImage(callback, updateImage);

            }
        });

        display.getAddLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getAddLocaleDialog().getDialogBox().center();
                display.getAddLocaleDialog().getDialogBox().show();
            }
        });

        display.getRemoveLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete())) {

                    final int selectedTab = display.getEditor().languageImages_OTMEditor().getSelectedIndex();
                    if (selectedTab != -1) {
                        final RESTLanguageImageCollectionItemV1 selectedImage = display.getEditor().languageImages_OTMEditor()
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

                        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                                waitDisplay, getDefaultImageRestCallback()) {
                        };

                        RESTCalls.updateImage(callback, updateImage);
                    }
                }
            }
        });

        display.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = display.getAddLocaleDialog().getLocales()
                        .getItemText(display.getAddLocaleDialog().getLocales().getSelectedIndex());

                /* Don't add locales twice */
                if (imageData.getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
                    for (final RESTLanguageImageCollectionItemV1 langImage : imageData.getDisplayedItem().getItem()
                            .getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
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

                final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                        waitDisplay, getDefaultImageRestCallback()) {
                };

                RESTCalls.updateImage(callback, updateImage);
            }
        });

        display.getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getAddLocaleDialog().getDialogBox().hide();
            }
        });
    }

    private BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface> getDefaultImageRestCallback() {
        return new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                reInitialiseImageView();
            }
        };
    }

    /**
     * Each Language Image has an upload button that needs to be bound to some behaviour.
     * 
     * @param imageDisplay The view that displays the image details.
     * @param waitDisplay The view that displays the waiting screen
     */
    @Override
    public void bindImageUploadButtons(final ImagePresenter.Display imageDisplay, final BaseTemplateViewInterface waitDisplay) {
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

                                    final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                                            waitDisplay,
                                            new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
                                                @Override
                                                public void doSuccessAction(RESTImageV1 retValue,
                                                        BaseTemplateViewInterface display) {
                                                    retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                                                    reInitialiseImageView();
                                                }
                                            }) {
                                        @Override
                                        public void failed(final Message message, final Throwable throwable) {
                                            waitDisplay.removeWaitOperation();
                                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                                        }
                                    };

                                    RESTCalls.updateImage(callback, updateImage);
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

    public void reInitialiseImageView() {
        display.initialize(imageData.getDisplayedItem().getItem(), getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons(display, display);
    }

    @Override
    public void bind(final ImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        bindImageViewButtons();
        populateLocales();
    }

    @Override
    public void getImage(final Integer imageId) {
        /*
         * Create a call back that resets the exception handler on any normal error or exception
         */
        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTImageV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTImageV1 retValue, Display display) {
                        retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                        finishLoading();
                    }
                }) {

            @Override
            public void failed(final Message message, final Throwable throwable) {
                display.removeWaitOperation();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        if (imageId != null) {
            RESTCalls.getImage(callback, imageId);
        }
    }

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (locales != null && imageData.getDisplayedItem() != null) {
            reInitialiseImageView();
        }
    }
}
