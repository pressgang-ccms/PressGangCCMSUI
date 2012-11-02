package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
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
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class ImagesFilteredResultsAndImageComponent
        extends
        ComponentBase<ImagesFilteredResultsAndImagePresenter.Display>
        implements ImagesFilteredResultsAndImagePresenter.LogicComponent {

    private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;
    private ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent;
    private ImagePresenter.Display imageDisplay;
    private ImagePresenter.LogicComponent imageComponent;
    
    @Override
    public void bind(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {
        
        this.imageFilteredResultsDisplay = imageFilteredResultsDisplay;
        this.imageFilteredResultsComponent = imageFilteredResultsComponent;
        this.imageDisplay = imageDisplay;
        this.imageComponent = imageComponent;
        
        super.bind(display, waitDisplay);

        bindListRowClicks();
        bindImageViewButtons();
    }

    /**
     * Bind the button click events for the topic editor screens.
     */
    private void bindListRowClicks() {

        imageFilteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTImageCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTImageCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * selectedSearchImage will be null until an image is selected for the first time
                     */
                    final boolean needToAddImageView = imageFilteredResultsComponent.getProviderData().getSelectedItem() == null;

                    imageFilteredResultsComponent.getProviderData().setDisplayedItem(event.getValue());
                    imageFilteredResultsComponent.getProviderData().setSelectedItem(event.getValue().clone(true));

                    final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                        @Override
                        public void begin() {
                            display.addWaitOperation();
                        }

                        @Override
                        public void generalException(final Exception e) {
                            display.removeWaitOperation();
                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        }

                        @Override
                        public void success(final RESTImageV1 retValue) {
                            try {
                                /*
                                 * Do a shallow copy here, because Chrome has issues with System.arraycopy - see
                                 * http://code.google.com/p/chromium/issues/detail?id=56588
                                 */
                                retValue.cloneInto(
                                        imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);

                                reInitialiseImageView();

                                /*
                                 * If this is the first image selected, display the image view
                                 */
                                if (needToAddImageView) {
                                    display.displayChildView(imageDisplay);
                                }
                            } finally {
                                display.removeWaitOperation();
                            }

                        }

                        @Override
                        public void failed(final Message message, final Throwable throwable) {
                            display.removeWaitOperation();
                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        }

                    };

                    RESTCalls.getImage(callback, imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem()
                            .getId());
                }
            }
        });
    }
    
    private void bindImageViewButtons() {

        imageDisplay.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getEditor().flush();

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                updateImage.explicitSetDescription(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription());

                final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                        waitDisplay, new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                                retValue.cloneInto(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                                reInitialiseImageView();
                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                RESTCalls.updateImage(callback, updateImage);

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
                        updateImage.setId(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());

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

        imageDisplay.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = imageDisplay.getAddLocaleDialog().getLocales()
                        .getItemText(imageDisplay.getAddLocaleDialog().getLocales().getSelectedIndex());

                /* Don't add locales twice */
                if (imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
                    for (final RESTLanguageImageCollectionItemV1 langImage : imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem()
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
                updateImage.setId(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());

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

        imageDisplay.getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageDisplay.getAddLocaleDialog().getDialogBox().hide();
            }
        });
    }
    
    private BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface> getDefaultImageRestCallback() {
        return new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                retValue.cloneInto(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                reInitialiseImageView();
            }
        };
    }

    private void reInitialiseImageView() {

        imageDisplay.initialize(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), imageComponent
                .getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons();
    }
    
    /**
     * Each Language Image has an upload button that needs to be bound to some behaviour.
     * 
     * @param imageDisplay The view that displays the image details.
     * @param waitDisplay The view that displays the waiting screen
     */
    private void bindImageUploadButtons() {
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
                                    updateImage.setId(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());

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
                                                    retValue.cloneInto(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
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

    public boolean checkForUnsavedChanges() {
        if (imageComponent.getImageData().getSelectedItem() != null) {
            if (!imageComponent.getImageData().getSelectedItem().getItem().getDescription()
                    .equals(imageComponent.getImageData().getDisplayedItem().getItem().getDescription())) {
                return true;
            }
        }
        return false;
    }
}
