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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFileCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageFileCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFileCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageFileCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLanguageFileV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.FilesFilteredResultsAndFileViewEvent;
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
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.file.RESTFileV1Editor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.file.RESTLanguageFileV1Editor;
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
public class FilesFilteredResultsAndDetailsPresenter extends BaseSearchAndEditPresenter<RESTFileV1, RESTFileCollectionV1,
        RESTFileCollectionItemV1, RESTFileV1Editor> implements BaseTemplatePresenterInterface {


    public interface Display extends BaseSearchAndEditViewInterface<RESTFileV1, RESTFileCollectionV1, RESTFileCollectionItemV1> {
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
             * @return The description to be added to each new file
             */
            @NotNull
            TextArea getDescription();

            /**
             * @return The file path of the new files
             */
            @NotNull
            TextBox getFilePath();
        }

        BulkUploadDisplay getBulkUploadDialog();
    }


    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "FilesFilteredResultsAndFileView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FilesFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * A reference to the StringConstants that holds the locales.
     */
    private List<String> locales;

    @Inject
    private Display display;

    @Inject
    private FileFilteredResultsPresenter fileFilteredResultsComponent;

    @Inject
    private FilePresenter fileComponent;

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

        fileComponent.bindExtended();
        fileFilteredResultsComponent.bindExtendedFilteredResults(queryString);

        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTFileV1> getNewEntityCallback = new GetNewEntityCallback<RESTFileV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTFileV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTFileV1> displayCallback) {

                final RESTCallBack<RESTFileV1> callback = new RESTCallBack<RESTFileV1>() {
                    @Override
                    public void success(@NotNull final RESTFileV1 retValue) {
                        checkArgument(retValue.getLanguageFiles_OTM() != null,
                                "The initially retrieved entity should have a language files collection");
                        displayCallback.displayNewEntity(retValue);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getFileWithoutData(selectedEntity.getId()), callback,
                        display);
            }
        };

        super.bindSearchAndEdit(Preferences.FILE_VIEW_MAIN_SPLIT_WIDTH, fileComponent.getDisplay(),
                fileComponent.getDisplay(), fileFilteredResultsComponent.getDisplay(), fileFilteredResultsComponent, display, display,
                getNewEntityCallback);

        populateLocales();

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                fileComponent.getDisplay().getSave().setEnabled(!readOnly);
                fileFilteredResultsComponent.getDisplay().getCreate().setEnabled(!readOnly);
                fileFilteredResultsComponent.getDisplay().getBulkUpload().setEnabled(!readOnly);
            }
        });
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

        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        // If the displayed item isn't a new file then load the additional data
        if (!fileFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {

            final RESTCallBack<RESTFileV1> callback = new RESTCallBack<RESTFileV1>() {
                @Override
                public void success(@NotNull final RESTFileV1 retValue) {
                    checkArgument(retValue.getLanguageFiles_OTM() != null, "The file should have the language file children populated.");

                    /*
                     * Do a shallow copy here, because Chrome has issues with System.arraycopy - see
                     * http://code.google.com/p/chromium/issues/detail?id=56588
                     */
                    retValue.cloneInto(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);

                    finishLoading();
                }
            };

            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getFileWithoutData(
                    fileFilteredResultsComponent.getProviderData().getSelectedItem().getItem().getId()), callback, display);
        }
    }

    @NotNull
    private RESTCallBack<RESTFileV1> getDefaultFileRestCallback(final boolean newEntity) {
        return new RESTCallBack<RESTFileV1>() {
            @Override
            public void success(@NotNull final RESTFileV1 retValue) {
                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");
                if (!newEntity) {
                    checkState(fileFilteredResultsComponent.getProviderData().getSelectedItem() != null,
                            "There should be a selected collection item.");
                    checkState(fileFilteredResultsComponent.getProviderData().getSelectedItem().getItem() != null,
                            "The selected collection item to reference a valid entity.");

                    cloneIntoFile(retValue, fileFilteredResultsComponent.getProviderData().getSelectedItem().getItem());
                    cloneIntoFile(retValue, fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                } else {
                    // create the file, and add to the wrapper
                    final RESTFileCollectionItemV1 fileCollectionItem = new RESTFileCollectionItemV1();
                    fileCollectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
                    fileCollectionItem.setItem(retValue);

                    // Clone the new item, as we alter content in the displayed item, so this shouldn't be reflected in the selected item
                    final RESTFileCollectionItemV1 displayedFileCollectionItem = fileCollectionItem.clone(false);
                    cloneIntoFile(retValue, displayedFileCollectionItem.getItem());

                    // Update the displayed file
                    fileFilteredResultsComponent.getProviderData().setDisplayedItem(displayedFileCollectionItem);
                    fileFilteredResultsComponent.setSelectedItem(fileCollectionItem);
                }

                updateDisplayWithNewEntityData(newEntity);

                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.FileUploadedSuccessfully());
            }

            @Override
            public void failed() {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.FileUploadFailure());
            }
        };
    }

    /**
     * Clones a file and it's language file making sure not to clone the file data as if it is too large you will get an error as per
     * https://bugzilla.redhat.com/show_bug.cgi?id=1026589. Additionally since the file data cannot change via the editor we really aren't
     * concerned with it being cloned and only care about the additional attributes
     *
     * @param source
     * @param output
     */
    private void cloneIntoFile(final RESTFileV1 source, final RESTFileV1 output) {
        source.cloneInto(output, false);
        if (source.getLanguageFiles_OTM() != null) {
            final RESTLanguageFileCollectionV1 languageImages = new RESTLanguageFileCollectionV1();
            output.setLanguageFiles_OTM(languageImages);

            for (final RESTLanguageFileCollectionItemV1 languageImage : source.getLanguageFiles_OTM().getItems()) {
                final RESTLanguageFileCollectionItemV1 outputLanguageImage = languageImage.clone(false);
                if (languageImage.getItem() != null) {
                    outputLanguageImage.setItem(languageImage.getItem().clone(false));
                }
                languageImages.getItems().add(outputLanguageImage);
            }
        }
    }

    @NotNull
    private List<String> getUnassignedLocales() {

        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");
        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM() != null,
                "The displayed collection item to reference a valid entity and have a valid collection of language files.");

        final List<String> newLocales = new ArrayList<String>(locales);

        /* Make it so you can't add a locale if it already exists */
        if (fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM() != null) {
            for (@NotNull final RESTLanguageFileCollectionItemV1 langFile : fileFilteredResultsComponent.getProviderData()
                    .getDisplayedItem().getItem().getLanguageFiles_OTM().returnExistingAndAddedCollectionItems()) {
                newLocales.remove(langFile.getItem().getLocale());
            }
        }

        return newLocales;
    }

    private void populateLocales() {
        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                locales = serverSettings.getLocales();
                Collections.sort(locales);
                finishLoading();
            }
        });
    }

    /**
     * Each Language File has an upload button that needs to be bound to some behaviour.
     */
    private void bindFileUploadButtons() {

        checkState(fileComponent.getDisplay().getEditor() != null, "display.getEditor() cannot be null");

        for (@NotNull final RESTLanguageFileV1Editor editor : fileComponent.getDisplay().getEditor().languageFiles_OTMEditor()
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
                                    checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                                            "There should be a displayed collection item.");
                                    checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                                            "The displayed collection item to reference a valid entity.");

                                    final String result = reader.getStringResult();
                                    final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                                    // Flush any changes
                                    fileComponent.getDisplay().getDriver().flush();

                                    final RESTFileV1 sourceFile = fileFilteredResultsComponent.getProviderData().getDisplayedItem()
                                            .getItem();

                                    /*
                                     * Create the file to be modified. This is so we don't send off unnecessary data.
                                     */
                                    final RESTFileV1 updateFile = new RESTFileV1();
                                    updateFile.setId(sourceFile.getId());
                                    updateFile.explicitSetDescription(sourceFile.getDescription());
                                    if (sourceFile.getExplodeArchive() != null) {
                                        updateFile.explicitSetExplodeArchive(sourceFile.getExplodeArchive());
                                    }
                                    if (sourceFile.getFileName() == null || sourceFile.getFileName().trim().isEmpty()) {
                                        updateFile.explicitSetFileName(file.getName());
                                    } else {
                                        updateFile.explicitSetFileName(sourceFile.getFileName());
                                    }
                                    updateFile.explicitSetFilePath(sourceFile.getFilePath());

                                    // Create the language file item
                                    final RESTLanguageFileCollectionItemV1 updatedLanguageFileItem = editor.self.clone(false);

                                    // Create the language file
                                    final RESTLanguageFileV1 updatedLanguageFile = new RESTLanguageFileV1();
                                    updatedLanguageFile.setId(editor.self.getItem().getId());
                                    updatedLanguageFile.explicitSetLocale(editor.self.getItem().getLocale());
                                    updatedLanguageFile.explicitSetFileData(buffer);
                                    updatedLanguageFile.explicitSetFilename(file.getName());
                                    // If the state is unchanged then it means it already exists, so we just want to update the data
                                    if (updatedLanguageFileItem.getState().equals(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE)) {
                                        updatedLanguageFileItem.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                                    }
                                    updatedLanguageFileItem.setItem(updatedLanguageFile);

                                    // Add the language file
                                    updateFile.explicitSetLanguageFiles_OTM(new RESTLanguageFileCollectionV1());
                                    updateFile.getLanguageFiles_OTM().getItems().add(updatedLanguageFileItem);

                                    // Add any other language files that have been added or removed
                                    for (final RESTLanguageFileCollectionItemV1 languageFile : sourceFile.getLanguageFiles_OTM()
                                            .returnDeletedAndAddedCollectionItems()) {
                                        // Make sure we don't add the current selection
                                        if (!languageFile.getItem().getLocale().equalsIgnoreCase(editor.self.getItem().getLocale())) {
                                            updateFile.getLanguageFiles_OTM().getItems().add(languageFile);
                                        }
                                    }

                                    if (fileFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
                                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createFile(updateFile),
                                                getDefaultFileRestCallback(true), display);
                                    } else {
                                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateFile(updateFile),
                                                getDefaultFileRestCallback(false), display);
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
        if (fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null) {

            checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");

            fileComponent.getDisplay().getDriver().flush();

            final RESTFileV1 displayedFile = fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem();

            if (fileFilteredResultsComponent.getProviderData().getSelectedItem() == null) {
                if (displayedFile.getExplodeArchive() != null) {
                    return true;
                }

                if (!isStringNullOrEmpty(displayedFile.getDescription())) {
                    return true;
                }

                if (!isStringNullOrEmpty(displayedFile.getFileName())) {
                    return true;
                }

                if (!isStringNullOrEmpty(displayedFile.getFilePath())) {
                    return true;
                }
            } else {
                checkState(fileFilteredResultsComponent.getProviderData().getSelectedItem() != null,
                        "There should be a selected collection item.");
                checkState(fileFilteredResultsComponent.getProviderData().getSelectedItem().getItem() != null,
                        "The selected collection item to reference a valid entity.");

                final RESTFileV1 selectedFile = fileFilteredResultsComponent.getProviderData().getSelectedItem().getItem();

                if (!GWTUtilities.booleanEquals(selectedFile.getExplodeArchive(), displayedFile.getExplodeArchive())) {
                    return true;
                }

                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedFile.getDescription(), displayedFile.getDescription())) {
                    return true;
                }

                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedFile.getFileName(), displayedFile.getFileName())) {
                    return true;
                }

                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedFile.getFilePath(), displayedFile.getFilePath())) {
                    return true;
                }
            }

            // If there are any modified language files in the file, we have unsaved changes
            if (displayedFile.getLanguageFiles_OTM() != null && !displayedFile.getLanguageFiles_OTM()
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
        if (locales != null && fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            initializeViews();
        }
    }

    @Override
    protected void bindActionButtons() {
        fileComponent.getDisplay().getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (hasUnsavedChanges()) {

                    checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                            "There should be a displayed collection item.");
                    checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                            "The displayed collection item to reference a valid entity.");

                    fileComponent.getDisplay().getDriver().flush();

                    final RESTFileV1 sourceFile = fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem();

                    /*
                     * Create the file to be modified. This is so we don't send off unnessessary data.
                     */
                    final RESTFileV1 updateFile = new RESTFileV1();
                    updateFile.setId(sourceFile.getId());
                    updateFile.explicitSetDescription(sourceFile.getDescription());
                    if (sourceFile.getExplodeArchive() != null) {
                        updateFile.explicitSetExplodeArchive(sourceFile.getExplodeArchive());
                    }
                    updateFile.explicitSetFileName(sourceFile.getFileName());
                    updateFile.explicitSetFilePath(sourceFile.getFilePath());

                    if (sourceFile.getLanguageFiles_OTM() != null && sourceFile.getLanguageFiles_OTM().getItems() != null) {
                        updateFile.explicitSetLanguageFiles_OTM(new RESTLanguageFileCollectionV1());
                        updateFile.getLanguageFiles_OTM().setItems(
                                sourceFile.getLanguageFiles_OTM().returnDeletedAddedAndUpdatedCollectionItems());
                    }

                    if (fileFilteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createFile(updateFile),
                                getDefaultFileRestCallback(true), display);
                    } else {
                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateFile(updateFile),
                                getDefaultFileRestCallback(false), display);
                    }
                } else {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }

            }
        });

        fileComponent.getDisplay().getDownloadFile().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                final int selectedTab = fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().getSelectedIndex();
                if (selectedTab != -1) {
                    final RESTLanguageFileCollectionItemV1 selectedFile = fileComponent.getDisplay().getEditor().languageFiles_OTMEditor
                            ().itemsEditor().getList().get(
                            selectedTab);

                    ServerDetails.getSavedServer(new ServerDetailsCallback() {
                        @Override
                        public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                            Window.open(serverDetails.getRestEndpoint() + "/1/file/get/raw/" +
                                    fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() + "?lang=" + selectedFile
                                    .getItem().getLocale(),
                                    null, null);
                        }
                    });

                }
            }
        });

        fileComponent.getDisplay().getAddLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                fileComponent.getDisplay().getAddLocaleDialog().getDialogBox().center();
                fileComponent.getDisplay().getAddLocaleDialog().getDialogBox().show();
            }
        });

        fileComponent.getDisplay().getRemoveLocale().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (Window.confirm(PressGangCCMSUI.INSTANCE.ConfirmDelete())) {

                    final int selectedTab = fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().getSelectedIndex();
                    if (selectedTab != -1) {

                        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                                "There should be a displayed collection item.");
                        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                                "The displayed collection item to reference a valid entity.");

                        final RESTLanguageFileCollectionItemV1 selectedFile = fileComponent.getDisplay().getEditor()
                                .languageFiles_OTMEditor().itemsEditor().getList().get(
                                selectedTab);

                        // Change the state to removed and remove the tab from the view
                        if (selectedFile.returnIsAddItem()) {
                            fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM().getItems()
                                    .remove(selectedFile);
                        } else {
                            selectedFile.setState(RESTBaseEntityCollectionItemV1.REMOVE_STATE);
                        }

                        // Remove the language file from the display and remove add the locale back to the locale dialog list
                        fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().itemsEditor().setValue(
                                fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM()
                                        .returnExistingAddedAndUpdatedCollectionItems());
                        fileComponent.getDisplay().getAddLocaleDialog().getLocales().addItem(selectedFile.getItem().getLocale());

                        // Rebind the file upload buttons
                        bindFileUploadButtons();
                    }
                }
            }
        });

        fileComponent.getDisplay().getAddLocaleDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                fileComponent.getDisplay().getAddLocaleDialog().getDialogBox().hide();

                final String selectedLocale = fileComponent.getDisplay().getAddLocaleDialog().getLocales().getItemText(
                        fileComponent.getDisplay().getAddLocaleDialog().getLocales().getSelectedIndex());

                // Don't add locales twice
                RESTLanguageFileCollectionItemV1 existingLanguageFileItem = null;
                if (fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM() != null) {
                    for (@NotNull final RESTLanguageFileCollectionItemV1 langFile : fileFilteredResultsComponent.getProviderData()
                            .getDisplayedItem().getItem().getLanguageFiles_OTM().getItems()) {
                        if (langFile.getItem().getLocale().equals(selectedLocale)) {
                            if (langFile.returnIsRemoveItem()) {
                                // The language file exists, but has been removed from the display
                                existingLanguageFileItem = langFile;
                                break;
                            } else {
                                // The language file already exists so just return
                                return;
                            }
                        }
                    }
                }

                final RESTLanguageFileCollectionItemV1 languageFileItem;
                if (existingLanguageFileItem == null) {
                    // Add the new language file to the displayed file
                    languageFileItem = new RESTLanguageFileCollectionItemV1();
                    languageFileItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);

                    final RESTLanguageFileV1 languageFile = new RESTLanguageFileV1();
                    languageFile.explicitSetLocale(selectedLocale);
                    languageFileItem.setItem(languageFile);
                    fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem().getLanguageFiles_OTM().addNewItem(languageFile);
                } else {
                    // Change the state from removed to updated or added
                    languageFileItem = existingLanguageFileItem;
                    if (languageFileItem.getItem().getId() == null) {
                        languageFileItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);
                    } else {
                        languageFileItem.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                    }
                }

                // Add the language file to the display and remove the locale from the locale dialog list
                fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().itemsEditor().getList().add(languageFileItem);
                fileComponent.getDisplay().getAddLocaleDialog().getLocales().removeItem(fileComponent.getDisplay().getAddLocaleDialog().getLocales().getSelectedIndex());

                // Rebind the file upload buttons
                bindFileUploadButtons();
            }
        });

        fileComponent.getDisplay().getAddLocaleDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                fileComponent.getDisplay().getAddLocaleDialog().getDialogBox().hide();
            }
        });
    }

    private void bindLanguageFileTabPanelEvents() {
        fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(final BeforeSelectionEvent<Integer> event) {
                final int curSelectedIndex = fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().getSelectedIndex();
                if (curSelectedIndex >= 0) {
                    final RESTLanguageFileV1Editor editor = fileComponent.getDisplay().getEditor().languageFiles_OTMEditor().itemsEditor()
                            .getEditors().get(curSelectedIndex);

                    if (editor.getUpload().getFiles() != null && editor.getUpload().getFiles().getLength() > 0) {
                        if (!Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedFileUploadPrompt())) {
                            event.cancel();
                        }
                    }
                }
            }
        });
    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            getEventBus().fireEvent(new FilesFilteredResultsAndFileViewEvent(fileFilteredResultsComponent.getQuery(), newWindow));
        }
    }

    @Override
    protected void bindFilteredResultsButtons() {
        fileFilteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
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

        fileFilteredResultsComponent.getDisplay().getFileDescriptionFilter().addKeyPressHandler(searchKeyPressHandler);
        fileFilteredResultsComponent.getDisplay().getFileIdFilter().addKeyPressHandler(searchKeyPressHandler);
        fileFilteredResultsComponent.getDisplay().getFileNameFilter().addKeyPressHandler(searchKeyPressHandler);

        fileFilteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    getServerSettings(new ServerSettingsCallback() {
                        @Override
                        public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                            final String defaultLocale = serverSettings.getDefaultLocale();

                            // Create the file wrapper
                            final RESTFileCollectionItemV1 fileCollectionItem = new RESTFileCollectionItemV1();
                            fileCollectionItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);

                            // When we have the default locale, create a new file
                            final RESTLanguageFileV1 langFile = new RESTLanguageFileV1();
                            langFile.explicitSetLocale(defaultLocale);

                            final RESTFileV1 newFile = new RESTFileV1();
                            newFile.explicitSetLanguageFiles_OTM(new RESTLanguageFileCollectionV1());
                            newFile.getLanguageFiles_OTM().addNewItem(langFile);
                            fileCollectionItem.setItem(newFile);

                            // the file won't show up in the list of files until it is saved, so the
                            // selected item is null
                            fileFilteredResultsComponent.setSelectedItem(null);

                            // the new file is being displayed though, so we set the displayed item
                            fileFilteredResultsComponent.getProviderData().setDisplayedItem(fileCollectionItem);

                            updateViewsAfterNewEntityLoaded();
                        }
                    });
                }
            }
        });

        fileFilteredResultsComponent.getDisplay().getBulkUpload().addClickHandler(new ClickHandler() {
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
                            final String defaultLocale = serverSettings.getDefaultLocale();
                            createNewFile(display.getBulkUploadDialog().getDescription().getText(), defaultLocale,
                                    display.getBulkUploadDialog().getFilePath().getText(), 0,
                                    display.getBulkUploadDialog().getFiles().getFiles(), new ArrayList<Integer>(),
                                    new ArrayList<String>());
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
    private void createNewFile(@NotNull final String description, @NotNull final String locale, @NotNull final String filePath,
            final int index, @NotNull final FileList files, @NotNull final List<Integer> ids, @NotNull final List<String> failedFiles) {
        if (index >= files.getLength()) {

            /*
                Get a comma separated list of the IDs of the files that were uploaded.
                This will be displayed to the user, and also used to list the files
                as a search result.
             */
            final StringBuilder idsQuery = new StringBuilder();
            final StringBuilder idsDisplay = new StringBuilder();
            for (final Integer id : ids) {
                if (!idsQuery.toString().isEmpty()) {
                    idsQuery.append(",");
                    idsDisplay.append(", ");
                }
                idsQuery.append(id);
                idsDisplay.append(id);
            }

            if (failedFiles.size() == 0) {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.FilesUploadedSuccessfully() + " " + idsDisplay.toString());
            } else {
                final StringBuilder failedNames = new StringBuilder();
                for (final String name : failedFiles) {
                    if (!failedNames.toString().isEmpty()) {
                        failedNames.append(",");
                    }
                    failedNames.append(name);
                }

                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.FilesNotUploadedSuccessfully() + ": " + failedNames.toString());
            }

            getEventBus().fireEvent(new FilesFilteredResultsAndFileViewEvent(
                    Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILE_IDS_FILTER_VAR + "=" + idsQuery.toString(), false));
        } else {
            display.addWaitOperation();

            final FileReader reader = new FileReader();

            reader.addErrorHandler(new ErrorHandler() {
                @Override
                public void onError(@NotNull final org.vectomatic.file.events.ErrorEvent event) {
                    display.removeWaitOperation();
                    failedFiles.add(files.getItem(index).getName());
                    createNewFile(description, locale, filePath, index + 1, files, ids, failedFiles);
                }
            });

            reader.addLoadEndHandler(new LoadEndHandler() {
                @Override
                public void onLoadEnd(@NotNull final LoadEndEvent event) {
                    try {
                        final String result = reader.getStringResult();
                        @NotNull final byte[] buffer = GWTUtilities.getByteArray(result, 1);

                         /* When we have the default locale, create a new file */
                        final RESTLanguageFileV1 langFile = new RESTLanguageFileV1();
                        langFile.explicitSetLocale(locale);
                        langFile.explicitSetFileData(buffer);
                        langFile.explicitSetFilename(files.getItem(index).getName());

                        final RESTFileV1 newFile = new RESTFileV1();
                        newFile.explicitSetDescription(description);
                        newFile.explicitSetFileName(files.getItem(index).getName());
                        newFile.explicitSetFilePath(filePath);
                        newFile.explicitSetLanguageFiles_OTM(new RESTLanguageFileCollectionV1());
                        newFile.getLanguageFiles_OTM().addNewItem(langFile);

                        final RESTCallBack<RESTFileV1> callback = new RESTCallBack<RESTFileV1>() {
                            @Override
                            public void success(@NotNull final RESTFileV1 retValue) {
                                ids.add(retValue.getId());
                                createNewFile(description, locale, filePath, index + 1, files, ids, failedFiles);
                            }

                            @Override
                            public void failed() {
                                createNewFile(description, locale, filePath, index + 1, files, ids, failedFiles);
                            }
                        };

                        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createFile(newFile), callback, display);
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

        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem() != null,
                "There should be a displayed collection item.");
        checkState(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        if (viewIsInFilter(filter, fileComponent.getDisplay())) {
            fileComponent.getDisplay().displayExtended(fileFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false,
                    getUnassignedLocales().toArray(new String[0]));
        }

        bindFileUploadButtons();
        bindLanguageFileTabPanelEvents();
        bindFileNameAndFilePathKeyEvents();
    }

    /**
     * Binds the File Name and File Path TextBox's to capture key up events and update the full file path.
     */
    protected void bindFileNameAndFilePathKeyEvents() {
        final KeyUpHandler handler = new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                fileComponent.getDisplay().getEditor().flush();
                fileComponent.getDisplay().getEditor().updateFullFilePath();
            }
        };
        fileComponent.getDisplay().getEditor().fileNameEditor().addKeyUpHandler(handler);
        fileComponent.getDisplay().getEditor().filePathEditor().addKeyUpHandler(handler);
    }
}
