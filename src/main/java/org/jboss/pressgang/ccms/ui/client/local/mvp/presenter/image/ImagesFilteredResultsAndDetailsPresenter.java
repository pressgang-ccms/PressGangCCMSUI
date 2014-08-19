/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentImageV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTLocaleV1Sort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTLanguageImageV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

/**
 * The presenter used to add logic to the image search and edit view.
 * <p/>
 * Images are a little different to other entities in that one of the properties, imageBase64, is generated by the server from
 * the binary image data. This property is used by the GWT application to display the image, which means that when editing
 * uploading a new image we actually have to save to the server instead of applying all changes locally and then saving them all
 * in one hit.
 * <p/>
 * It also means that when we create a new image we actually create and save a new image, instead of creating an in memory image
 * that is edited and then saved.
 *
 * @author Matthew Casperson
 */
@Dependent
public class ImagesFilteredResultsAndDetailsPresenter extends BaseSearchAndEditPresenter<RESTImageV1, RESTImageCollectionV1,
        RESTImageCollectionItemV1, RESTImageV1Editor> implements BaseTemplatePresenterInterface {


    public interface Display extends BaseSearchAndEditViewInterface<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1> {
        /**
         * The interface that defines the bulk image upload dialog box.
         */
        public interface BulkUploadDisplay {
            /**
             * @return A reference to the dialog box.
             */
            @NotNull
            DialogBox getDialogBox();

            /**
             * @return The OK button
             */
            @NotNull
            PushButton getOK();

            /**
             * @return The Cancel button
             */
            @NotNull
            PushButton getCancel();

            /**
             * @return The file upload element
             */
            @NotNull
            FileUploadExt getFiles();

            /**
             * @return The description to be added to each new image
             */
            @NotNull
            TextArea getDescription();
        }

        BulkUploadDisplay getBulkUploadDialog();
    }


    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ImagesFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * A reference to the StringConstants that holds the locales.
     */
    private List<RESTLocaleV1> locales;

    @Inject
    private Display display;

    @Inject
    private ImageFilteredResultsPresenter imageFilteredResultsComponent;

    @Inject
    private ImagePresenter imageComponent;

    private String queryString;

    @Override
    protected void go() {
        bindSearchAndEditExtended(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {

        imageComponent.bindExtended();
        imageFilteredResultsComponent.bindExtendedFilteredResults(queryString);

        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTImageV1> getNewEntityCallback = new GetNewEntityCallback<RESTImageV1>() {


            @Override
            public void getNewEntity(@NotNull final RESTImageV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTImageV1> displayCallback) {

                final RESTCallBack<RESTImageV1> callback = new RESTCallBack<RESTImageV1>() {
                    @Override
                    public void success(@NotNull final RESTImageV1 retValue) {
                        checkArgument(retValue.getLanguageImages_OTM() != null,
                                "The initially retrieved entity should have a language images collection");
                        displayCallback.displayNewEntity(retValue);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getImageWithoutPreview(selectedEntity.getId()), callback,
                        display);
            }
        };

        LOGGER.info("super.bindSearchAndEdit()");
        super.bindSearchAndEdit(Preferences.IMAGE_VIEW_MAIN_SPLIT_WIDTH, imageComponent.getDisplay(), imageComponent.getDisplay(),
                imageFilteredResultsComponent.getDisplay(), imageFilteredResultsComponent, display, display, getNewEntityCallback);
        LOGGER.info("super.bindSearchAndEditCompleted()");

        populateLocales();
        buildHelpDatabase();
        bindTabSelections();

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                imageComponent.getDisplay().getSave().setEnabled(!readOnly);
                imageComponent.getDisplay().getAddLocale().setEnabled(!readOnly);
                imageComponent.getDisplay().getRemoveLocale().setEnabled(!readOnly);
                imageFilteredResultsComponent.getDisplay().getCreate().setEnabled(!readOnly);
                imageFilteredResultsComponent.getDisplay().getBulkUpload().setEnabled(!readOnly);
            }
        });
    }

    private void bindTabSelections()
    {
        imageComponent.getDisplay().getEditor().getImageTemplateTable().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(@NotNull final SelectionEvent<Integer> event) {
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        final TextArea textArea = (TextArea)imageComponent.getDisplay().getEditor().getImageTemplateTable().getWidget(event.getSelectedItem());
                        textArea.setFocus(true);
                        textArea.selectAll();
                    }
                });
            }
        });

        // select the text when the tab control is clicked (http://stackoverflow.com/a/4303407/157605)
        imageComponent.getDisplay().getEditor().getImageTemplateTable().addHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                final TextArea textArea = (TextArea) imageComponent.getDisplay().getEditor().getImageTemplateTable().getWidget(imageComponent.getDisplay().getEditor().getImageTemplateTable().getSelectedIndex());
                textArea.setFocus(true);
                textArea.selectAll();
            }
        }, ClickEvent.getType());

        // select the text when the text control is clicked
        final ClickHandler textAreaClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                final TextArea textArea = (TextArea) event.getSource();
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                    @Override
                    public void execute() {
                        textArea.setFocus(true);
                        textArea.selectAll();
                    }
                });
            }
        };

        imageComponent.getDisplay().getEditor().getBareXmlTemplate().addClickHandler(textAreaClickHandler);
        imageComponent.getDisplay().getEditor().getXmlTemplate().addClickHandler(textAreaClickHandler);
        imageComponent.getDisplay().getEditor().getInlineXmlTemplate().addClickHandler(textAreaClickHandler);
    }

    private void buildHelpDatabase() {
        setDataAttribute(imageComponent.getDisplay().getAddLocale(), ServiceConstants.HELP_TOPICS.IMAGE_ADD_LOCALE.getId());
        setDataAttribute(imageComponent.getDisplay().getRemoveLocale(), ServiceConstants.HELP_TOPICS.IMAGE_REMOVE_LOCALE.getId());
        setDataAttribute(imageComponent.getDisplay().getViewImage(), ServiceConstants.HELP_TOPICS.IMAGE_VIEW_IMAGE.getId());
        setDataAttribute(imageComponent.getDisplay().getFindTopics(), ServiceConstants.HELP_TOPICS.IMAGE_FIND_TOPICS.getId());
        setDataAttribute(imageComponent.getDisplay().getSave(), ServiceConstants.HELP_TOPICS.IMAGE_SAVE.getId());
        setDataAttribute(imageComponent.getDisplay().getEditor().descriptionEditor(),
                ServiceConstants.HELP_TOPICS.IMAGE_DESCRIPTION_FIELD.getId());
        setDataAttribute(imageComponent.getDisplay().getEditor().getId(), ServiceConstants.HELP_TOPICS.IMAGE_ID_FIELD.getId());
        setDataAttribute(imageComponent.getDisplay().getEditor().getImageTemplateTable(),
                ServiceConstants.HELP_TOPICS.IMAGE_DOCBOOK_IMAGE_TEMPLATES_TABLE.getId());
        setDataAttribute(imageComponent.getDisplay().getEditor().getLanguageImages_OTM(),
                ServiceConstants.HELP_TOPICS.IMAGE_DETAILS_TABLE.getId());

        setDataAttribute(imageFilteredResultsComponent.getDisplay().getEntitySearch(), ServiceConstants.HELP_TOPICS.IMAGE_SEARCH.getId());
        setDataAttribute(imageFilteredResultsComponent.getDisplay().getBulkUpload(),
                ServiceConstants.HELP_TOPICS.IMAGE_BULK_IMAGE_UPLOAD.getId());
        setDataAttribute(imageFilteredResultsComponent.getDisplay().getCreate(), ServiceConstants.HELP_TOPICS.IMAGE_CREATE_IMAGE.getId());

        setDataAttribute(imageFilteredResultsComponent.getDisplay().getImageDescriptionFilter(),
                ServiceConstants.HELP_TOPICS.IMAGE_DESCRIPTION_SEARCH_FIELD.getId());
        setDataAttribute(imageFilteredResultsComponent.getDisplay().getImageIdFilter(),
                ServiceConstants.HELP_TOPICS.IMAGE_ID_SEARCH_FIELD.getId());
        setDataAttribute(imageFilteredResultsComponent.getDisplay().getImageOriginalFileNameFilter(),
                ServiceConstants.HELP_TOPICS.IMAGE_ORIGINAL_FILE_NAME_SEARCH_FIELD.getId());
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    /**
     * Here we load the actual language images associated with the image
     */
    @Override
    protected void loadAdditionalDisplayedItemData() {

        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        // If the displayed item isn't a new image then load the additional data
        if (!imageFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
            checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() != null,
                    "The displayed collection item to reference a valid entity and have a valid id");

            final RESTCallBack<RESTImageV1> callback = new RESTCallBack<RESTImageV1>() {
                @Override
                public void success(@NotNull final RESTImageV1 retValue) {
                    checkArgument(retValue.getLanguageImages_OTM() != null, "The image should have the language image children populated.");

                    /*
                     * Do a shallow copy here, because Chrome has issues with System.arraycopy - see
                     * http://code.google.com/p/chromium/issues/detail?id=56588
                     */
                    retValue.cloneInto(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);

                    finishLoading();
                }
            };

            getFailOverRESTCall().performRESTCall(
                    FailOverRESTCallDatabase.getImage(imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem().getId()),
                    callback, display);
        }


    }

    @NotNull
    private RESTCallBack<RESTImageV1> getDefaultImageRestCallback(final boolean newEntity) {
        return new RESTCallBack<RESTImageV1>() {
            @Override
            public void success(@NotNull final RESTImageV1 retValue) {

                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                if (!newEntity) {
                    checkState(imageFilteredResultsComponent.getProviderData().getSelectedItem() != null,
                            "There should be a selected collection item.");
                    checkState(imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem() != null,
                            "The selected collection item to reference a valid entity.");

                    cloneIntoImage(retValue, imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem());
                    cloneIntoImage(retValue, imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                } else {
                    // create the image, and add to the wrapper
                    final RESTImageCollectionItemV1 imageCollectionItem = new RESTImageCollectionItemV1();
                    imageCollectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
                    imageCollectionItem.setItem(retValue);

                    // Clone the new item, as we alter content in the displayed item, so this shouldn't be reflected in the selected item
                    final RESTImageCollectionItemV1 displayedImageCollectionItem = imageCollectionItem.clone(false);
                    cloneIntoImage(retValue, displayedImageCollectionItem.getItem());

                    // Update the displayed image
                    imageFilteredResultsComponent.getProviderData().setDisplayedItem(displayedImageCollectionItem);
                    imageFilteredResultsComponent.setSelectedItem(imageCollectionItem);
                }

                updateDisplayWithNewEntityData(newEntity);

                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ImageUploadedSuccessfully());
            }

            @Override
            public void failed() {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ImageUploadFailure());
            }
        };
    }

    /**
     * Clones a image and it's language images making sure not to clone the image data as if it is too large you will get an error as per
     * https://bugzilla.redhat.com/show_bug.cgi?id=1026589. Additionally since the image data cannot change via the editor we really
     * aren't concerned with it being cloned and only care about the additional attributes
     *
     * @param source
     * @param output
     */
    private void cloneIntoImage(final RESTImageV1 source, final RESTImageV1 output) {
        source.cloneInto(output, false);
        if (source.getLanguageImages_OTM() != null) {
            final RESTLanguageImageCollectionV1 languageImages = new RESTLanguageImageCollectionV1();
            output.setLanguageImages_OTM(languageImages);

            for (final RESTLanguageImageCollectionItemV1 languageImage : source.getLanguageImages_OTM().getItems()) {
                final RESTLanguageImageCollectionItemV1 outputLanguageImage = languageImage.clone(false);
                if (languageImage.getItem() != null) {
                    outputLanguageImage.setItem(languageImage.getItem().clone(false));
                }
                languageImages.getItems().add(outputLanguageImage);
            }
        }
    }

    @NotNull
    private List<RESTLocaleV1> getUnassignedLocales() {

        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");
        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null,
                "The displayed collection item to reference a valid entity and have a valid collection of language images.");

        final List<RESTLocaleV1> newLocales = new ArrayList<RESTLocaleV1>(locales);

        /* Make it so you can't add a locale if it already exists */
        if (imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
            for (@NotNull final RESTLanguageImageCollectionItemV1 langImage : imageFilteredResultsComponent.getProviderData()
                    .getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                newLocales.remove(langImage.getItem().getLocale());
            }
        }

        return newLocales;
    }

    private void populateLocales() {
        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                ImagesFilteredResultsAndDetailsPresenter.this.locales = serverSettings.getLocales().returnItems();
                Collections.sort(ImagesFilteredResultsAndDetailsPresenter.this.locales, new RESTLocaleV1Sort());
                finishLoading();
            }
        });
    }

    /**
     * Each Language Image has an upload button that needs to be bound to some behaviour.
     */
    private void bindImageUploadButtons() {

        checkState(imageComponent.getDisplay().getEditor() != null, "display.getEditor() cannot be null");

        for (@NotNull final RESTLanguageImageV1Editor editor : imageComponent.getDisplay().getEditor().languageImages_OTMEditor()
                .itemsEditor().getEditors()) {
            editor.getUploadButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {

                    /*
                     * There should only be one file, but use a loop to accommodate any changes that might implement multiple
                     * files
                     */
                    for (@NotNull final File file : editor.getUpload().getFiles()) {
                        display.addWaitOperation();

                        final FileReader reader = new FileReader();

                        reader.addErrorHandler(new ErrorHandler() {
                            @Override
                            public void onError(@NotNull final org.vectomatic.file.events.ErrorEvent event) {
                                display.removeWaitOperation();
                            }
                        });

                        reader.addLoadEndHandler(new LoadEndHandler() {
                            @Override
                            public void onLoadEnd(@NotNull final LoadEndEvent event) {
                                try {
                                    checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                                            "There should be a displayed collection item.");
                                    checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                                            "The displayed collection item to reference a valid entity.");

                                    final String result = reader.getStringResult();
                                    final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                                    /* Flush any changes */
                                    imageComponent.getDisplay().getDriver().flush();

                                    final RESTImageV1 sourceImage = imageFilteredResultsComponent.getProviderData().getDisplayedItem()
                                            .getItem();
                                    
                                    /*
                                     * Create the image to be modified. This is so we don't send off unnecessary data.
                                     */
                                    final RESTImageV1 updateImage = new RESTImageV1();
                                    updateImage.setId(sourceImage.getId());
                                    updateImage.explicitSetDescription(sourceImage.getDescription());

                                    // Create the language image item
                                    final RESTLanguageImageCollectionItemV1 updatedLanguageImageItem = editor.self.clone(false);

                                    /* Create the language image */
                                    final RESTLanguageImageV1 updatedLanguageImage = new RESTLanguageImageV1();
                                    updatedLanguageImage.setId(editor.self.getItem().getId());
                                    updatedLanguageImage.explicitSetLocale(editor.self.getItem().getLocale());
                                    updatedLanguageImage.explicitSetImageData(buffer);
                                    updatedLanguageImage.explicitSetFilename(file.getName());
                                    // If the state is unchanged then it means it already exists, so we just want to update the data
                                    if (updatedLanguageImageItem.getState().equals(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE)) {
                                        updatedLanguageImageItem.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                                    }
                                    updatedLanguageImageItem.setItem(updatedLanguageImage);

                                    /* Add the language image */
                                    updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                                    updateImage.getLanguageImages_OTM().getItems().add(updatedLanguageImageItem);

                                    // Add any other language images that have been added or removed
                                    for (final RESTLanguageImageCollectionItemV1 languageImage : sourceImage.getLanguageImages_OTM()
                                            .returnDeletedAndAddedCollectionItems()) {
                                        // Make sure we don't add the current selection
                                        if (!languageImage.getItem().getLocale().equals(editor.self.getItem().getLocale())) {
                                            updateImage.getLanguageImages_OTM().getItems().add(languageImage);
                                        }
                                    }

                                    if (imageFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
                                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createImage(updateImage),
                                                getDefaultImageRestCallback(true), display);
                                    } else {
                                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateImage(updateImage),
                                                getDefaultImageRestCallback(false), display);
                                    }

                                } finally {
                                    display.removeWaitOperation();
                                }
                            }
                        });

                        reader.readAsBinaryString(file);

                        /* we only upload one file */
                        break;
                    }
                }
            });
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        if (imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null) {

            checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");

            imageComponent.getDisplay().getDriver().flush();

            final RESTImageV1 displayedImage = imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem();

            if (imageFilteredResultsComponent.getProviderData().getSelectedItem() == null) {
                if (!GWTUtilities.isStringNullOrEmpty(displayedImage.getDescription())) {
                    return true;
                }
            } else {
                checkState(imageFilteredResultsComponent.getProviderData().getSelectedItem() != null,
                        "There should be a selected collection item.");
                checkState(imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem() != null,
                        "The selected collection item to reference a valid entity.");

                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(
                        imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                        imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription())) {
                    return true;
                }
            }

            // If there are any modified language images in the image, we have unsaved changes
            if (displayedImage.getLanguageImages_OTM() != null && !displayedImage.getLanguageImages_OTM()
                    .returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Potentially two REST calls have to finish before we can display the page. This function will be called as each REST call
     * finishes, and when all the information has been gathered, the page will be displayed.
     */
    private void finishLoading() {
        if (locales != null && imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            initializeViews();
        }
    }

    @Override
    protected void bindActionButtons() {
        imageComponent.getDisplay().getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (hasUnsavedChanges()) {

                    checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                            "There should be a displayed collection item.");
                    checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                            "The displayed collection item to reference a valid entity.");

                    final RESTImageV1 sourceImage = imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem();

                    /*
                     * Create the image to be modified. This is so we don't send off unnessessary data.
                     */
                    final RESTImageV1 updateImage = new RESTImageV1();
                    updateImage.setId(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                    updateImage.explicitSetDescription(
                            imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription());

                    if (sourceImage.getLanguageImages_OTM() != null && sourceImage.getLanguageImages_OTM().getItems() != null) {
                        updateImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                        updateImage.getLanguageImages_OTM().setItems(
                                sourceImage.getLanguageImages_OTM().returnDeletedAddedAndUpdatedCollectionItems());
                    }

                    if (imageFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createImage(updateImage),
                                getDefaultImageRestCallback(true), display);
                    } else {
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateImage(updateImage),
                                getDefaultImageRestCallback(false), display);
                    }

                } else {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }

            }
        });

        imageComponent.getDisplay().getAddLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageComponent.getDisplay().getAddLocaleDialog().getDialogBox().center();
                imageComponent.getDisplay().getAddLocaleDialog().getDialogBox().show();
            }
        });

        imageComponent.getDisplay().getRemoveLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete())) {

                    final int selectedTab = imageComponent.getDisplay().getEditor().languageImages_OTMEditor().getSelectedIndex();
                    if (selectedTab != -1) {

                        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                                "There should be a displayed collection item.");
                        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                                "The displayed collection item to reference a valid entity.");

                        final RESTLanguageImageCollectionItemV1 selectedImage = imageComponent.getDisplay().getEditor()
                                .languageImages_OTMEditor().itemsEditor().getList().get(
                                selectedTab);

                        // Change the state to removed and remove the tab from the view
                        if (selectedImage.returnIsAddItem()) {
                            imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM().getItems
                                    ().remove(
                                    selectedImage);
                        } else {
                            selectedImage.setState(RESTBaseEntityCollectionItemV1.REMOVE_STATE);
                        }

                        // Remove the language image from the display and re-add the locale to the locale dialog list
                        imageComponent.getDisplay().getEditor().languageImages_OTMEditor().itemsEditor().setValue(
                                imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM()
                                        .returnExistingAddedAndUpdatedCollectionItems());
                        final RESTLocaleV1 locale = selectedImage.getItem().getLocale();
                        imageComponent.getDisplay().getAddLocaleDialog().getLocales().addItem(locale.getValue(), locale.getId().toString());

                        // Rebind the upload buttons
                        bindImageUploadButtons();
                    }
                }
            }
        });

        imageComponent.getDisplay().getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                imageComponent.getDisplay().getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = imageComponent.getDisplay().getAddLocaleDialog().getLocales().getItemText(
                        imageComponent.getDisplay().getAddLocaleDialog().getLocales().getSelectedIndex());
                final Integer selectedLocaleId = Integer.parseInt(imageComponent.getDisplay().getAddLocaleDialog().getLocales().getValue(
                        imageComponent.getDisplay().getAddLocaleDialog().getLocales().getSelectedIndex()));

                /* Don't add locales twice */
                RESTLanguageImageCollectionItemV1 existingLanguageImageItem = null;
                if (imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM() != null) {
                    for (@NotNull final RESTLanguageImageCollectionItemV1 langImage : imageFilteredResultsComponent.getProviderData()
                            .getDisplayedItem().getItem().getLanguageImages_OTM().returnExistingAndAddedCollectionItems()) {
                        if (langImage.getItem().getLocale().getId().equals(selectedLocaleId)) {
                            if (existingLanguageImageItem.returnIsRemoveItem()) {
                                // The language item exists but isn't displayed because it was set for removal
                                existingLanguageImageItem = langImage;
                                break;
                            } else {
                                // The language already exists so just return
                                return;
                            }
                        }
                    }
                }

                final RESTLanguageImageCollectionItemV1 languageImageItem;
                if (existingLanguageImageItem == null) {
                    // Add the new language image to the displayed image
                    languageImageItem = new RESTLanguageImageCollectionItemV1();
                    languageImageItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);

                    final RESTLocaleV1 tempLocale = new RESTLocaleV1();
                    tempLocale.setId(selectedLocaleId);
                    tempLocale.setValue(selectedLocale);

                    final RESTLanguageImageV1 languageImage = new RESTLanguageImageV1();
                    languageImage.explicitSetLocale(tempLocale);
                    languageImageItem.setItem(languageImage);
                    imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageImages_OTM().addNewItem(
                            languageImage);
                } else {
                    // Change the state from removed to added/updated
                    languageImageItem = existingLanguageImageItem;
                    if (languageImageItem.getItem().getId() == null) {
                        languageImageItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);
                    } else {
                        languageImageItem.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                    }
                }

                // Add the language image item to the display and remove the locale from the locale dialog list
                imageComponent.getDisplay().getEditor().languageImages_OTMEditor().itemsEditor().getList().add(languageImageItem);
                imageComponent.getDisplay().getAddLocaleDialog().getLocales().removeItem(
                        imageComponent.getDisplay().getAddLocaleDialog().getLocales().getSelectedIndex());

                // Rebind the upload buttons
                bindImageUploadButtons();
            }
        });

        imageComponent.getDisplay().getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                imageComponent.getDisplay().getAddLocaleDialog().getDialogBox().hide();
            }
        });

        imageComponent.getDisplay().getViewImage().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                final int selectedTab = imageComponent.getDisplay().getEditor().languageImages_OTMEditor().getSelectedIndex();
                if (selectedTab != -1) {
                    final RESTLanguageImageCollectionItemV1 selectedImage = imageComponent.getDisplay().getEditor()
                            .languageImages_OTMEditor().itemsEditor().getList().get(
                            selectedTab);

                    ServerDetails.getSavedServer(new ServerDetailsCallback() {
                        @Override
                        public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                            Window.open(serverDetails.getRestEndpoint() + "/1/image/get/raw/" +
                                    imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() + "?lang=" + selectedImage
                                    .getItem().getLocale(),
                                    null, null);
                        }
                    });
                }
            }
        });

        imageComponent.getDisplay().getFindTopics().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                final String docbookFileName = ComponentImageV1.getDocbookFileName(
                        imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                if (docbookFileName != null && !docbookFileName.isEmpty() && isOKToProceed()) {

                    final String searchQuery = "images/" + docbookFileName;

                    getEventBus().fireEvent(new TopicSearchResultsAndTopicViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants
                                    .TOPIC_XML_FILTER_VAR + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(
                                    searchQuery) : searchQuery), event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL));
                }

            }
        });
    }

    private void bindLanguageImageTabPanelEvents() {
        imageComponent.getDisplay().getEditor().languageImages_OTMEditor().addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(final BeforeSelectionEvent<Integer> event) {
                final int curSelectedIndex = imageComponent.getDisplay().getEditor().languageImages_OTMEditor().getSelectedIndex();
                if (curSelectedIndex >= 0) {
                    final RESTLanguageImageV1Editor editor = imageComponent.getDisplay().getEditor().languageImages_OTMEditor()
                            .itemsEditor().getEditors().get(
                            curSelectedIndex);

                    if (editor.getUpload().getFiles() != null && editor.getUpload().getFiles().getLength() > 0) {
                        if (!Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedImageUploadPrompt())) {
                            event.cancel();
                        }
                    }
                }
            }
        });
    }

    /**
     * Open a popup window that displays the image defined in the base64 parameter
     *
     * @param base64 The BASE64 representation of the image to be displayed
     */
    native private void displayImageInPopup(@NotNull final String base64) /*-{
        var win = $wnd.open("data:image/jpeg;base64," + base64, "_blank",
            "width=" + (screen.width - 200) + ", height="
                + (screen.height - 200) + ", left=100, top=100"); // a window object
    }-*/;

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            getEventBus().fireEvent(new ImagesFilteredResultsAndImageViewEvent(imageFilteredResultsComponent.getQuery(), newWindow));
        }
    }

    @Override
    protected void bindFilteredResultsButtons() {
        imageFilteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                doSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        final KeyPressHandler searchKeyPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {
                LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditViewInterface.bindFilteredResultsButtons() KeyPressHandler.onKeyPress()");

                final int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    final int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        doSearch(false);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    doSearch(false);
                }

            }
        };

        imageFilteredResultsComponent.getDisplay().getImageDescriptionFilter().addKeyPressHandler(searchKeyPressHandler);
        imageFilteredResultsComponent.getDisplay().getImageIdFilter().addKeyPressHandler(searchKeyPressHandler);
        imageFilteredResultsComponent.getDisplay().getImageOriginalFileNameFilter().addKeyPressHandler(searchKeyPressHandler);

        imageFilteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    getServerSettings(new ServerSettingsCallback() {
                        @Override
                        public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                            final RESTLocaleV1 defaultLocale = serverSettings.getDefaultLocale();

                            // Create the image wrapper
                            final RESTImageCollectionItemV1 imageCollectionItem = new RESTImageCollectionItemV1();
                            imageCollectionItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);

                            // When we have the default locale, create a new image
                            final RESTLanguageImageV1 langImage = new RESTLanguageImageV1();
                            langImage.explicitSetLocale(defaultLocale);

                            final RESTImageV1 newImage = new RESTImageV1();
                            newImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                            newImage.getLanguageImages_OTM().addNewItem(langImage);
                            imageCollectionItem.setItem(newImage);

                            // the image won't show up in the list of files until it is saved, so the
                            // selected item is null
                            imageFilteredResultsComponent.setSelectedItem(null);

                            // the new image is being displayed though, so we set the displayed item
                            imageFilteredResultsComponent.getProviderData().setDisplayedItem(imageCollectionItem);

                            updateViewsAfterNewEntityLoaded();
                        }
                    });

                }
            };
        });

        imageFilteredResultsComponent.getDisplay().getBulkUpload().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getBulkUploadDialog().getDialogBox().center();
            }
        });

        display.getBulkUploadDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getBulkUploadDialog().getDialogBox().hide();
            }
        });

        display.getBulkUploadDialog().getOK().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getBulkUploadDialog().getDialogBox().hide();

                if (display.getBulkUploadDialog().getFiles().getFiles().getLength() == 0) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoFilesSelected());
                } else {
                    getServerSettings(new ServerSettingsCallback() {
                        @Override
                        public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                            final RESTLocaleV1 defaultLocale = serverSettings.getDefaultLocale();
                            createNewImage(display.getBulkUploadDialog().getDescription().getText(), defaultLocale, 0,
                                    display.getBulkUploadDialog().getFiles().getFiles(), new ArrayList<Integer>(), new ArrayList<String>());
                        }
                    });

                }
            }
        });
    }

    /**
     * A methods to recursively load a file from the disk, add it to a new image, and upload the image.
     *
     * @param description The common description to be added to each image
     * @param locale      The image locale
     * @param index       The index of the file we are uploading
     * @param files       The collection of file selected on the disk
     * @param ids         A list of the newly created image ids
     */
    private void createNewImage(@NotNull final String description, @NotNull final RESTLocaleV1 locale, final int index,
            @NotNull final FileList files, @NotNull final List<Integer> ids, @NotNull final List<String> failedFiles) {
        if (index >= files.getLength()) {
            final StringBuilder idsQuery = new StringBuilder();
            final StringBuilder messageIds = new StringBuilder();
            int count = 1;
            for (final Integer id : ids) {
                if (!idsQuery.toString().isEmpty()) {
                    idsQuery.append(",");
                    messageIds.append(", ");
                }

                idsQuery.append(id);
                messageIds.append(id);
            }

            if (failedFiles.size() == 0) {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ImagesUploadedSuccessfully() + " " + messageIds.toString());
            } else {
                final StringBuilder failedNames = new StringBuilder();
                for (final String name : failedFiles) {
                    if (!failedNames.toString().isEmpty()) {
                        failedNames.append(",");
                    }
                    failedNames.append(name);
                }

                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ImagesNotUploadedSuccessfully() + ": " + failedNames.toString());
            }



            getEventBus().fireEvent(new ImagesFilteredResultsAndImageViewEvent(
                    Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.IMAGE_IDS_FILTER_VAR + "=" + idsQuery.toString(), false));
        } else {
            display.addWaitOperation();


            @NotNull final FileReader reader = new FileReader();

            reader.addErrorHandler(new ErrorHandler() {
                @Override
                public void onError(@NotNull final org.vectomatic.file.events.ErrorEvent event) {
                    display.removeWaitOperation();
                    failedFiles.add(files.getItem(index).getName());
                    createNewImage(description, locale, index + 1, files, ids, failedFiles);
                }
            });

            reader.addLoadEndHandler(new LoadEndHandler() {
                @Override
                public void onLoadEnd(@NotNull final LoadEndEvent event) {
                    try {
                        final String result = reader.getStringResult();
                        final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                         /* When we have the default locale, create a new image */
                        final RESTLanguageImageV1 langImage = new RESTLanguageImageV1();
                        langImage.explicitSetLocale(locale);
                        langImage.explicitSetImageData(buffer);
                        langImage.explicitSetFilename(files.getItem(index).getName());

                        final RESTImageV1 newImage = new RESTImageV1();
                        newImage.explicitSetDescription(description);
                        newImage.explicitSetLanguageImages_OTM(new RESTLanguageImageCollectionV1());
                        newImage.getLanguageImages_OTM().addNewItem(langImage);

                        final RESTCallBack<RESTImageV1> callback = new RESTCallBack<RESTImageV1>() {
                            @Override
                            public void success(@NotNull final RESTImageV1 retValue) {
                                ids.add(retValue.getId());
                                createNewImage(description, locale, index + 1, files, ids, failedFiles);
                            }

                            @Override
                            public void failed() {
                                createNewImage(description, locale, index + 1, files, ids, failedFiles);
                            }
                        };

                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createImage(newImage), callback, display);
                    } finally {
                        display.removeWaitOperation();
                    }
                }
            });

            reader.readAsBinaryString(files.getItem(index));
        }

    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        if (viewIsInFilter(filter, imageComponent.getDisplay())) {
            imageComponent.getDisplay().displayExtended(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false,
                    getUnassignedLocales().toArray(new RESTLocaleV1[0]));

            // select the docbook template text so the users can just CTRL-C to copy it out
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    final TextArea textArea = (TextArea)imageComponent.getDisplay().getEditor().getImageTemplateTable().getWidget(imageComponent.getDisplay().getEditor().getImageTemplateTable().getSelectedIndex());
                    textArea.setFocus(true);
                    textArea.selectAll();
                }
            });
        }

        bindImageUploadButtons();
        bindLanguageImageTabPanelEvents();
    }
}
