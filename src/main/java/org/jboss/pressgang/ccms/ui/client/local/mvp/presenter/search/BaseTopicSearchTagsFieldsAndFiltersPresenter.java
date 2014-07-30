/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentTagV1;
import org.jboss.pressgang.ccms.rest.v1.components.GWTComponentTopicV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTFilterTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TranslatedSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
public abstract class BaseTopicSearchTagsFieldsAndFiltersPresenter extends BaseSearchTagsFieldsAndFiltersPresenter {
    /**
     * The history token used to access this page
     */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";
    /**
     * The history token used when searching for translated topics
     */
    public static final String TRANSLATED_HISTORY_TOKEN = "TranslatedSearchTagsFieldsAndFiltersView";
    /**
     * The history token used when doing bulk tag operations
     */
    public static final String BULK_TAG_HISTORY_TOKEN = "BulkTagSearchTagsFieldsAndFiltersView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTopicSearchTagsFieldsAndFiltersPresenter.class.getName());

    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject
    private SaveFilterDialogInterface saveFilterDialog;
    /**
     * This is set to true if the history token indicates that we are trying to do a search of translated topics.
     */
    private boolean doingTranslatedSearch = false;

    @Override
    public abstract Display getDisplay();

    @Override
    protected void go() {
        final RESTFilterCollectionItemV1 filterItem = new RESTFilterCollectionItemV1();
        filterItem.setItem(new RESTFilterV1());

        getDisplay().setViewShown(true);

        bindExtended();

        getTagsPresenter().bindExtended();
        getFieldsPresenter().bindExtended();
        getLocalePresenter().bindExtended();
        getSearchFilterResultsAndFilterPresenter().bindSearchAndEditExtended(Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants.FILTER_TOPIC);

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                getFieldsPresenter().getDisplay().display(filterItem.getItem(), readOnly);
            }
        });

        bindSearchButtons();
        loadSearchLocales();

        /*
            Show the bulk tagging button instead of the search button if the history token
            indicates that we are displaying a bulk tagging screen.
         */
        if (!isShowBulkTags()) {
            getDisplay().removeTopActionButton(getDisplay().getApplyBulkTags());
        }

        displayFields();

        // Set a blank filter as the current displayed item
        getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter()
                .getProviderData().setDisplayedItem(filterItem);


    }

    @Override
    public void bindExtended() {
        super.bindExtended();

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                getDisplay().getApplyBulkTags().setEnabled(!readOnly);
                getSearchFilterResultsAndFilterPresenter().getDisplay().getOverwrite().setEnabled(!readOnly);
                getSearchFilterResultsAndFilterPresenter().getDisplay().getCreate().setEnabled(!readOnly);
            }
        });
    }

    @Override
    public void close() {

    }

    /**
     * Adds logic to the filter action buttons
     */
    @Override
    protected void bindFilterActionButtons() {
        final ClickHandler loadClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                checkState(getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData()
                        .getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                final RESTFilterV1 displayedFilter = getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();

                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        setTagsLoaded(false);
                        loadSearchTags(new ActionCompletedCallback<RESTTagCollectionV1>() {
                            @Override
                            public void success(final RESTTagCollectionV1 restTagCollectionV1) {
                                setTagsLoaded(true);
                                getFieldsPresenter().getDisplay().display(displayedFilter, readOnly);

                                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.FilterSuccessfullyLoaded());
                            }

                            @Override
                            public void failure() {}
                        });
                    }
                });

            }
        };

        final ClickHandler loadAndSearchClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final RESTFilterV1 displayedFilter = getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter()
                        .getProviderData().getDisplayedItem().getItem();
                final String query = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_ID + "=" + displayedFilter.getId();
                if (doingTranslatedSearch) {
                    getEventBus().fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                } else {
                    getEventBus().fireEvent(new TopicSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        /*
            A filter instance to be shared between the click handlers for create and overwrite, and the save
            dialog ok click handler.
        */
        final RESTFilterV1 filter = new RESTFilterV1();

        final ClickHandler saveFilter = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData()
                        .getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                /* Save any changes back to the underlying object */
                getFieldsPresenter().getDisplay().getDriver().flush();
                if (isTagsLoaded()) {
                    getTagsPresenter().getDisplay().getDriver().flush();
                }

                final RESTFilterV1 displayedFilter = getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter()
                        .getProviderData().getDisplayedItem().getItem();

                /* Setting the id to the value of the displayed filter is the indication that this is an updated filter */
                filter.setId(displayedFilter.getId());

                // If the displayed filter has no id, it's new so set the type
                if (displayedFilter.getId() == null) {
                    filter.explicitSetType(RESTFilterTypeV1.TOPIC);
                }

                /* An overwritten filter will delete any existing children before adding new ones */
                filter.explicitSetFilterTags_OTM(new RESTFilterTagCollectionV1());
                filter.explicitSetFilterFields_OTM(new RESTFilterFieldCollectionV1());
                filter.explicitSetFilterCategories_OTM(new RESTFilterCategoryCollectionV1());

                if (displayedFilter.getFilterTags_OTM() != null) {
                    for (@NotNull final RESTFilterTagCollectionItemV1 tag : displayedFilter.getFilterTags_OTM().getItems()) {
                        @NotNull final RESTFilterTagV1 removeTag = new RESTFilterTagV1();
                        removeTag.setId(tag.getItem().getId());
                        filter.getFilterTags_OTM().addRemoveItem(removeTag);
                    }
                }

                if (displayedFilter.getFilterFields_OTM() != null) {
                    for (@NotNull final RESTFilterFieldCollectionItemV1 field : displayedFilter.getFilterFields_OTM().getItems()) {
                        @NotNull final RESTFilterFieldV1 remove = new RESTFilterFieldV1();
                        remove.setId(field.getItem().getId());
                        filter.getFilterFields_OTM().addRemoveItem(remove);
                    }
                }

                if (displayedFilter.getFilterCategories_OTM() != null) {
                    for (@NotNull final RESTFilterCategoryCollectionItemV1 category : displayedFilter.getFilterCategories_OTM().getItems()) {
                        @NotNull final RESTFilterCategoryV1 remove = new RESTFilterCategoryV1();
                        remove.setId(category.getItem().getId());
                        filter.getFilterCategories_OTM().addRemoveItem(remove);
                    }
                }

                saveFilterDialog.getDialogBox().show();

                saveFilterDialog.getName().setValue(displayedFilter.getName());
                saveFilterDialog.getDescription().setValue(displayedFilter.getDescription());
            }
        };

        final ClickHandler saveOKHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                saveFilterDialog.getDialogBox().hide();

                getTagsPresenter().getDisplay().getSearchUIProjects().populateFilter(filter);
                getFieldsPresenter().getDisplay().getFields().populateFilter(filter);

                filter.explicitSetName(saveFilterDialog.getName().getValue());
                filter.explicitSetDescription(saveFilterDialog.getDescription().getValue());

                final RESTCallBack<RESTFilterV1> callback = new RESTCallBack<RESTFilterV1>() {
                    @Override
                    public void success(@NotNull final RESTFilterV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TopicSearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");

                            // Create the topic wrapper
                            @NotNull final RESTFilterCollectionItemV1 collectionItem = new RESTFilterCollectionItemV1();
                            collectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);

                            // create the topic, and add to the wrapper
                            collectionItem.setItem(retValue);

                            /* Update the displayed topic */
                            getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().getProviderData()
                                    .setDisplayedItem(collectionItem.clone(true));
                            getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter().setSelectedItem
                                    (collectionItem);

                            /* if filter.getId() == null, we created a new filter */
                            getSearchFilterResultsAndFilterPresenter().updateDisplayWithNewEntityData(filter.getId() == null);


                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TopicSearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");
                        }
                    }

                    @Override
                    public void failed() {
                        saveFilterDialog.getDialogBox().hide();
                    }
                };

                if (filter.getId() == null) {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createFilter(filter), callback, getDisplay());
                } else {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateFilter(filter), callback, getDisplay());
                }
            }
        };

        final ClickHandler saveCancelHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                saveFilterDialog.getDialogBox().hide();
            }
        };

        getSearchFilterResultsAndFilterPresenter().getDisplay().getLoad().addClickHandler(loadClickHandler);
        getSearchFilterResultsAndFilterPresenter().getDisplay().getLoadAndSearch().addClickHandler(loadAndSearchClickHandler);
        getSearchFilterResultsAndFilterPresenter().getDisplay().getOverwrite().addClickHandler(saveFilter);
        getSearchFilterResultsAndFilterPresenter().getDisplay().getCreate().addClickHandler(saveFilter);
        saveFilterDialog.getOk().addClickHandler(saveOKHandler);
        saveFilterDialog.getCancel().addClickHandler(saveCancelHandler);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        if (historyToken.startsWith(TRANSLATED_HISTORY_TOKEN)) {
            doingTranslatedSearch = true;
        } else if (historyToken.startsWith(BULK_TAG_HISTORY_TOKEN)) {
            setShowBulkTags(true);
        }
    }

    @Override
    protected void bindSearchButtons() {
        super.bindSearchButtons();

        final ClickHandler searchHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                if (isTagsLoaded()) {
                    getTagsPresenter().getDisplay().getDriver().flush();
                }
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + getFieldsPresenter().getDisplay().getFields().getSearchQuery(false)
                        + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                if (doingTranslatedSearch) {
                    getEventBus().fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                } else {
                    getEventBus().fireEvent(new TopicSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        final ClickHandler bulkTaggingHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                if (isTagsLoaded()) {
                    getTagsPresenter().getDisplay().getDriver().flush();
                }
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + getFieldsPresenter().getDisplay().getFields().getSearchQuery(false)
                        + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                /* Get a list of the tags to be added and removed */
                final List<Integer> removeTags = new ArrayList<Integer>();
                final Map<SearchUICategory, ArrayList<Integer>> addTags = new HashMap<SearchUICategory, ArrayList<Integer>>();

                for (@NotNull final SearchUIProject project : getTagsPresenter().getDisplay().getSearchUIProjects().getProjects()) {
                    for (@NotNull final SearchUICategory category : project.getCategories()) {
                        for (@NotNull final SearchUITag tag : category.getMyTags()) {
                            if (tag.getBulkTagState() == TriStateSelectionState.SELECTED) {
                                if (!addTags.containsKey(category)) {
                                    addTags.put(category, new ArrayList<Integer>());
                                }
                                addTags.get(category).add(tag.getId());
                            } else if (tag.getBulkTagState() == TriStateSelectionState.UNSELECTED) {
                                removeTags.add(tag.getId());
                            }
                        }
                    }
                }

                applyBulkTags(query, removeTags, addTags);
            }
        };

        final ClickHandler downloadZipHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                if (isTagsLoaded()) {
                    getTagsPresenter().getDisplay().getDriver().flush();
                }
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + getFieldsPresenter().getDisplay().getFields().getSearchQuery(false)
                        + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                        Window.open(serverDetails.getRestEndpoint() + "/1/topics/get/zip/" + query, "Zip Download", "");
                    }
                });
            }
        };

        final ClickHandler downloadCsvHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                if (isTagsLoaded()) {
                    getTagsPresenter().getDisplay().getDriver().flush();
                }
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + getFieldsPresenter().getDisplay().getFields().getSearchQuery(false)
                        + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                        Window.open(serverDetails.getRestEndpoint() + "/1/topics/get/csv/" + query, "Csv Download", "");
                    }
                });
            }
        };

        getDisplay().getSearchButton().addClickHandler(searchHandler);
        getDisplay().getDownloadZip().addClickHandler(downloadZipHandler);
        getDisplay().getDownloadCSV().addClickHandler(downloadCsvHandler);
        getDisplay().getApplyBulkTags().addClickHandler(bulkTaggingHandler);
    }

    private void applyBulkTags(@NotNull final String query, @NotNull final List<Integer> removeTags, @NotNull final Map<SearchUICategory, ArrayList<Integer>> addTags) {

        if (removeTags.size() == 0 && addTags.size() == 0) {
            AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoBulkTagsSelected());
            return;
        }

        final RESTCallBack<RESTTopicCollectionV1> callback = new RESTCallBack<RESTTopicCollectionV1>() {
            @Override
            public void success(@NotNull final RESTTopicCollectionV1 retValue) {
                checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                final List<RESTTopicV1> modifiedTopics = new ArrayList<RESTTopicV1>();

                for (@NotNull final RESTTopicCollectionItemV1 topic : retValue.getItems()) {

                    checkState(topic.getItem().getTags() != null, "Returned collection items should have an expanded tags collection.");
                    checkState(topic.getItem().getTags().getItems() != null, "Returned collection items should have an expanded tags collection.");

                    final RESTTopicV1 modifiedTopic = new RESTTopicV1();
                    modifiedTopic.setId(topic.getItem().getId());
                    modifiedTopic.explicitSetTags(new RESTTagCollectionV1());

                    for (@NotNull final Integer removeTagID : removeTags) {
                        if (GWTComponentTopicV1.hasTag(topic.getItem(), removeTagID)) {
                            final RESTTagV1 removeTag = new RESTTagV1();
                            removeTag.setId(removeTagID);
                            modifiedTopic.getTags().addRemoveItem(removeTag);
                        }
                    }

                    for (@NotNull final SearchUICategory addTagCategory : addTags.keySet()) {
                        if (addTagCategory.getMutuallyExclusiveCategory()) {

                            checkState(addTags.get(addTagCategory).size() == 1, "Only one tag should be added in a mutually exclusive category.");

                                    /* Remove any existing tag in the mutually exclusive category */
                            for (final RESTTagCollectionItemV1 existingTags : topic.getItem().getTags().getItems()) {

                                checkState(existingTags.getItem().getCategories() != null, "Tag should have an expanded categories collection.");
                                checkState(existingTags.getItem().getCategories().getItems() != null, "Tag should have an expanded categories collection.");

                                if (ComponentTagV1.containedInCategory(existingTags.getItem(), addTagCategory.getId())) {
                                    final RESTTagV1 removeTag = new RESTTagV1();
                                    removeTag.setId(existingTags.getItem().getId());
                                    modifiedTopic.getTags().addRemoveItem(removeTag);
                                }
                            }
                        }

                        for (final Integer addTagID : addTags.get(addTagCategory)) {
                            if (!GWTComponentTopicV1.hasTag(topic.getItem(), addTagID)) {
                                final RESTTagV1 addTag = new RESTTagV1();
                                addTag.setId(addTagID);
                                modifiedTopic.getTags().addNewItem(addTag);
                            }
                        }
                    }

                    modifiedTopics.add(modifiedTopic);
                }

                if (modifiedTopics.size() == 0) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoTopicsFound());
                } else if (Window.confirm(PressGangCCMSUI.INSTANCE.ThisOperationWillModify() + " " + modifiedTopics.size() + " " + PressGangCCMSUI.INSTANCE.Topics() + ".\n" + PressGangCCMSUI.INSTANCE.AreYouSureYouWishToContinue())) {
                    updateTopic(0, new ArrayList<Integer>(), modifiedTopics);
                }
            }
        };

        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopicsFromQueryWithExpandedTags(query), callback, getDisplay());
    }

    private void updateTopic(final int index, @NotNull final List<Integer> failedTopics, @NotNull final List<RESTTopicV1> modifiedTopics) {
        checkArgument(index >= 0, "The index must be positive");
        if (index < modifiedTopics.size()) {

            final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                @Override
                public void success(@NotNull final RESTTopicV1 retValue) {
                    updateTopic(index + 1, failedTopics, modifiedTopics);
                }

                @Override
                public void failed() {
                    failedTopics.add(modifiedTopics.get(index).getId());
                    updateTopic(index + 1, failedTopics, modifiedTopics);
                }
            };

            getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                    getFailOverRESTCall().performRESTCall(
                            FailOverRESTCallDatabase.saveTopic(modifiedTopics.get(index), PressGangCCMSUI.INSTANCE.BulkTagUpdateLogMessage(),
                                    new Integer(RESTLogDetailsV1.MINOR_CHANGE_FLAG_BIT),
                                    serverSettings.getEntities().getUnknownUserId().toString()), callback, getDisplay());
                }
            });
        } else {
            if (failedTopics.size() == 0) {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.AllTopicsUpdatedSuccessfully());
            } else {
                AlertBox.setMessageAndDisplay(failedTopics.size() + " " + PressGangCCMSUI.INSTANCE.TopicsWereNotUpdatedCorrectly() + modifiedTopics.toString());
            }
        }
    }

    /**
     * Assign help info to the UI elements exposed by this presenter.
     */
    @Override
    protected void buildHelpDatabase() {
        super.buildHelpDatabase();
        setDataAttribute(getDisplay().getDownloadCSV(), ServiceConstants.HELP_TOPICS.SEARCH_DOWNLOAD_CSV.getId());
    }

    public interface Display extends BaseSearchTagsFieldsAndFiltersPresenter.Display {
        PushButton getDownloadCSV();

        PushButton getApplyBulkTags();
    }
}
