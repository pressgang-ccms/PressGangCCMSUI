package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
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

public class ImagesFilteredResultsAndImageComponent
        extends
        BaseSearchAndEditComponent<ImageFilteredResultsPresenter.Display, ImagesFilteredResultsAndImagePresenter.Display, RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1, ImagePresenter.Display, ImagePresenter.Display>
        implements ImagesFilteredResultsAndImagePresenter.LogicComponent {

    /**
     * A reference to the StringConstants that holds the locales.
     */
    private String[] locales;

    @Override
    public void bind(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(Preferences.IMAGE_VIEW_MAIN_SPLIT_WIDTH, imageDisplay, imageDisplay, imageFilteredResultsDisplay,
                imageFilteredResultsComponent, display, waitDisplay);

        populateLocales();
    }

    @Override
    protected void newEntitySelected() {
        final RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, ImagesFilteredResultsAndImagePresenter.Display>(
                display, new BaseRestCallback.SuccessAction<RESTImageV1, ImagesFilteredResultsAndImagePresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTImageV1 retValue,
                            final ImagesFilteredResultsAndImagePresenter.Display display) {
                        /*
                         * Do a shallow copy here, because Chrome has issues with System.arraycopy - see
                         * http://code.google.com/p/chromium/issues/detail?id=56588
                         */
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);

                        finishLoading();
                    }
                }) {
        };

        RESTCalls.getImage(callback, filteredResultsComponent.getProviderData().getSelectedItem().getItem().getId());
    }

    private BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface> getDefaultImageRestCallback() {
        return new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                reInitialiseView(entityPropertiesView);
            }
        };
    }

    @Override
    protected void reInitialiseView(final ImagePresenter.Display displayedView) {

        super.reInitialiseView(displayedView);
        
        displayedView.initialize(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons();

        lastDisplayedView = displayedView;
    }

    private List<String> getUnassignedLocales() {
        final List<String> newLocales = new ArrayList<String>(Arrays.asList(locales));

        /* Make it so you can't add a locale if it already exists */
        if (filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
            for (final RESTLanguageImageCollectionItemV1 langImage : filteredResultsComponent.getProviderData()
                    .getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
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
                        locales = retValue.getValue().replaceAll("\\r\\n", "").replaceAll("\\n", "").replaceAll(" ", "")
                                .split(",");

                        finishLoading();
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }

    /**
     * Each Language Image has an upload button that needs to be bound to some behaviour.
     * 
     * @param entityPropertiesView The view that displays the image details.
     * @param waitDisplay The view that displays the waiting screen
     */
    private void bindImageUploadButtons() {
        if (entityPropertiesView.getEditor() == null) {
            throw new IllegalStateException("display.getEditor() cannot be null");
        }

        for (final RESTLanguageImageV1Editor editor : entityPropertiesView.getEditor().languageImages_OTMEditor().itemsEditor()
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
                                entityPropertiesView.removeWaitOperation();
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
                                    updateImage.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                            .getId());

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
                                                    retValue.cloneInto(filteredResultsComponent.getProviderData()
                                                            .getDisplayedItem().getItem(), true);
                                                    reInitialiseView(entityPropertiesView);
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

    @Override
    public boolean checkForUnsavedChanges() {
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

            if (GWTUtilities.stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getProviderData()
                    .getSelectedItem().getItem().getDescription(), filteredResultsComponent.getProviderData()
                    .getDisplayedItem().getItem().getDescription())) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (locales != null && filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            reInitialiseView(entityPropertiesView);
        }
    }

    /**
     * Called once an entity has been saved to refresh the various lists that may have been modified by the edited or created
     * entity.
     * 
     * 
     * @param wasNewEntity true if the entity that was saved was a new entity, and false otherwise
     */
    @Override
    protected void updateDisplayAfterSave(final boolean wasNewEntity) {
        /* refresh the list of tags from the existing list that was modified */
        if (!wasNewEntity) {
            filteredResultsDisplay.getProvider().displayAsynchronousList(filteredResultsComponent.getProviderData().getItems(),
                    filteredResultsComponent.getProviderData().getSize(),
                    filteredResultsComponent.getProviderData().getStartRow());
        }
        /* If we just created a new entity, refresh the list of entities from the database */
        else {
            filteredResultsComponent.bind(filteredResultsComponent.getQuery(), filteredResultsDisplay, waitDisplay);

            /*
             * reInitialiseView will flush the ui, which will flush the null ID back to the displayed object. To prevent that we
             * need to call edit on the newly saved entity
             */
            entityPropertiesView.getDriver().edit(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

        }

        /* refresh the display */
        reInitialiseView(entityPropertiesView);
    }

    @Override
    protected void bindActionButtons() {
        entityPropertiesView.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                entityPropertiesView.getEditor().flush();

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                updateImage.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                        .getDescription());

                final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, BaseTemplateViewInterface>(
                        waitDisplay, new BaseRestCallback.SuccessAction<RESTImageV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(RESTImageV1 retValue, BaseTemplateViewInterface display) {
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        true);
                                updateDisplayAfterSave(false);
                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                RESTCalls.updateImage(callback, updateImage);

            }
        });

        entityPropertiesView.getAddLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                entityPropertiesView.getAddLocaleDialog().getDialogBox().center();
                entityPropertiesView.getAddLocaleDialog().getDialogBox().show();
            }
        });

        entityPropertiesView.getRemoveLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete())) {

                    final int selectedTab = entityPropertiesView.getEditor().languageImages_OTMEditor().getSelectedIndex();
                    if (selectedTab != -1) {
                        final RESTLanguageImageCollectionItemV1 selectedImage = entityPropertiesView.getEditor()
                                .languageImages_OTMEditor().itemsEditor().getList().get(selectedTab);

                        /*
                         * Create the image to be modified. This is so we don't send off unnessessary data.
                         */
                        final RESTImageV1 updateImage = new RESTImageV1();
                        updateImage.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());

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

        entityPropertiesView.getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                entityPropertiesView.getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = entityPropertiesView.getAddLocaleDialog().getLocales()
                        .getItemText(entityPropertiesView.getAddLocaleDialog().getLocales().getSelectedIndex());

                /* Don't add locales twice */
                if (filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
                    for (final RESTLanguageImageCollectionItemV1 langImage : filteredResultsComponent.getProviderData()
                            .getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                        if (langImage.getItem().getLocale().equals(selectedLocale)) {
                            return;
                        }
                    }
                }

                /*
                 * Create the image to be modified. This is so we don't send off unnessessary data.
                 */
                final RESTImageV1 updateImage = new RESTImageV1();
                updateImage.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());

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

        entityPropertiesView.getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                entityPropertiesView.getAddLocaleDialog().getDialogBox().hide();
            }
        });
    }

    @Override
    protected void bindFilteredResultsButtons() {
        // TODO Auto-generated method stub

    }
}
