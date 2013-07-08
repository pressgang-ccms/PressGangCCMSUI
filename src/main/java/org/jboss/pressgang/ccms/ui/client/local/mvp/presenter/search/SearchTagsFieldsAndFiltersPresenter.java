package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.*;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentTagV1;
import org.jboss.pressgang.ccms.rest.v1.components.GWTComponentTopicV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TranslatedSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
@Dependent
public class SearchTagsFieldsAndFiltersPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {
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
    private static final Logger LOGGER = Logger.getLogger(SearchTagsFieldsAndFiltersPresenter.class.getName());
    /**
     * true if we are showing bulk tag buttons, and false otherwise.
     */
    private boolean showBulkTags;
    @Inject
    private Display display;
    @Inject
    private SearchPresenter tagsComponent;
    @Inject
    private SearchFieldPresenter fieldsComponent;
    @Inject
    private SearchLocalePresenter localePresenter;
    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private SearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;
    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject
    private SaveFilterDialogInterface saveFilterDialog;
    @Inject
    private HandlerManager eventBus;
    private HasWidgets container;
    /**
     * This is set to true if the history token indicates that we are trying to do a search of translated topics.
     */
    private boolean doingTranslatedSearch = false;

    @Override
    public void go(@NotNull final HasWidgets container) {

        this.container = container;
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        bindExtended(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN);

        tagsComponent.bindExtended(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN);
        fieldsComponent.bindExtended(ServiceConstants.SEARCH_FIELDS_HELP_TOPIC, HISTORY_TOKEN);
        localePresenter.bindExtended(ServiceConstants.SEARCH_LOCALES_HELP_TOPIC, HISTORY_TOKEN);
        searchFilterResultsAndFilterPresenter.bindSearchAndEditExtended(ServiceConstants.FILTERS_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);

        fieldsComponent.getDisplay().display(null, false);

        bindSearchButtons();
        loadSearchTags();
        loadSearchLocales();

        /*
            Show the bulk tagging button instead of the search button if the history token
            indicates that we are displaying a bulk tagging screen.
         */
        if (!showBulkTags) {
            display.removeTopActionButton(display.getApplyBulkTags());
        }

        displayTags();
    }

    @Override
    public void close() {

    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        bind(helpTopicId, pageId, display);
    }

    private void loadSearchLocales() {
        RESTCalls.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> stringList) {
                localePresenter.getDisplay().display(stringList, false);
            }
        }, display);
    }

    /**
     * Gets the tags from the REST server
     */
    private void loadSearchTags() {

        @NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final BaseTemplateViewInterface waitDisplay) {

                /* Bind the load, load and search and overwrite buttons */
                bindFilterActionButtons(retValue);

                /* Display the tags */
                tagsComponent.getDisplay().displayExtended(retValue, null, false, showBulkTags);
            }
        });
        RESTCalls.getTags(callback);
    }

    /**
     * Adds logic to the filter action buttons
     */
    private void bindFilterActionButtons(@NotNull final RESTTagCollectionV1 tags) {
        final ClickHandler loadClickHanlder = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();
                tagsComponent.getDisplay().displayExtended(tags, displayedFilter, false, showBulkTags);
                fieldsComponent.getDisplay().display(displayedFilter, false);
            }
        };

        final ClickHandler loadAndSearchClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();
                final String query = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_ID + "=" + displayedFilter.getId();
                if (doingTranslatedSearch) {
                    eventBus.fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                } else {
                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        /*
            A filter instance to be shared between the click handlers for create and overwrite, and the save
            dialog ok click handler.
        */
        final RESTFilterV1 filter = new RESTFilterV1();

        final ClickHandler createFilter = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO, "ENTER SearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.onClick()");

                    /* Setting the ID to null indicates that this is a new filter */
                    filter.setId(null);
                    /* The collections are new for a new filter */
                    filter.explicitSetFilterTags_OTM(new RESTFilterTagCollectionV1());
                    filter.explicitSetFilterFields_OTM(new RESTFilterFieldCollectionV1());
                    filter.explicitSetFilterCategories_OTM(new RESTFilterCategoryCollectionV1());

                    saveFilterDialog.getDialogBox().show();
                } finally {
                    LOGGER.log(Level.INFO, "EXIT SearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.onClick()");
                }
            }
        };

        final ClickHandler overwriteFilter = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                /* Save any changes back to the underlying object */
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();

                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();

                /* Setting the id to the value of the displayed filter is the indication that this is an updated filter */
                filter.setId(displayedFilter.getId());

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

                tagsComponent.getDisplay().getSearchUIProjects().populateFilter(filter);
                fieldsComponent.getDisplay().getSearchUIFields().populateFilter(filter);

                filter.explicitSetName(saveFilterDialog.getName().getValue());
                filter.explicitSetDescription(saveFilterDialog.getDescription().getValue());

                @NotNull final BaseRestCallback<RESTFilterV1, Display> createFilter = new BaseRestCallback<RESTFilterV1, Display>(display, new BaseRestCallback.SuccessAction<RESTFilterV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTFilterV1 retValue, @NotNull final Display display) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER SearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");

                            // Create the topic wrapper
                            @NotNull final RESTFilterCollectionItemV1 collectionItem = new RESTFilterCollectionItemV1();
                            collectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                            // create the topic, and add to the wrapper
                            collectionItem.setItem(retValue);

                            /* Update the displayed topic */
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().setDisplayedItem(collectionItem.clone(true));
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().setSelectedItem(collectionItem);

                            /* if filter.getId() == null, we created a new filter */
                            searchFilterResultsAndFilterPresenter.updateDisplayWithNewEntityData(filter.getId() == null);


                        } finally {
                            LOGGER.log(Level.INFO, "EXIT SearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");
                        }
                    }
                }, new BaseRestCallback.FailureAction<Display>() {
                    @Override
                    public void doFailureAction(final Display display) {
                        saveFilterDialog.getDialogBox().hide();
                    }
                }
                );

                if (filter.getId() == null) {
                    RESTCalls.createFilter(createFilter, filter);
                } else {
                    RESTCalls.updateFilter(createFilter, filter);
                }
            }
        };

        @NotNull final ClickHandler saveCancelHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                saveFilterDialog.getDialogBox().hide();
            }
        };

        searchFilterResultsAndFilterPresenter.getDisplay().getLoad().addClickHandler(loadClickHanlder);
        searchFilterResultsAndFilterPresenter.getDisplay().getLoadAndSearch().addClickHandler(loadAndSearchClickHandler);
        searchFilterResultsAndFilterPresenter.getDisplay().getOverwrite().addClickHandler(overwriteFilter);
        searchFilterResultsAndFilterPresenter.getDisplay().getCreate().addClickHandler(createFilter);
        saveFilterDialog.getOk().addClickHandler(saveOKHandler);
        saveFilterDialog.getCancel().addClickHandler(saveCancelHandler);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        if (historyToken.startsWith(TRANSLATED_HISTORY_TOKEN)) {
            doingTranslatedSearch = true;
        } else if (historyToken.startsWith(BULK_TAG_HISTORY_TOKEN)) {
            showBulkTags = true;
        }
    }

    private void bindSearchButtons() {

        final ClickHandler tagsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayTags();
            }
        };

        final ClickHandler fieldsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayFields();
            }
        };

        final ClickHandler filtersHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayFilters();
            }
        };

        final ClickHandler localesHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayLocales();
            }
        };

        final ClickHandler searchHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);

                if (doingTranslatedSearch) {
                    eventBus.fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                } else {
                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        final ClickHandler searchContentSpecsHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);

                if (doingTranslatedSearch) {
                    //eventBus.fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                } else {
                    eventBus.fireEvent(new ContentSpecSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        final ClickHandler bulkTaggingHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);

                /* Get a list of the tags to be added and removed */
                final List<Integer> removeTags = new ArrayList<Integer>();
                final Map<SearchUICategory, ArrayList<Integer>> addTags = new HashMap<SearchUICategory, ArrayList<Integer>>();

                for (@NotNull final SearchUIProject project : tagsComponent.getDisplay().getSearchUIProjects().getProjects()) {
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
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);
                Window.open(ServerDetails.getSavedServer().getRestEndpoint() + "/1/topics/get/zip/" + query, "Zip Download", "");
            }
        };

        final ClickHandler downloadCsvHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);
                Window.open(ServerDetails.getSavedServer().getRestEndpoint() + "/1/topics/get/csv/" + query, "Csv Download", "");
            }
        };

        display.getSearchTopics().addClickHandler(searchHandler);
        display.getSearchContentSpecs().addClickHandler(searchContentSpecsHandler);
        display.getDownloadZip().addClickHandler(downloadZipHandler);
        display.getDownloadCSV().addClickHandler(downloadCsvHandler);
        display.getTagsSearch().addClickHandler(tagsHandler);
        display.getFields().addClickHandler(fieldsHandler);
        display.getFilters().addClickHandler(filtersHandler);
        display.getLocales().addClickHandler(localesHandler);
        display.getApplyBulkTags().addClickHandler(bulkTaggingHandler);
    }

    private void resetTopActionButtons() {
        display.replaceTopActionButton(display.getLocalesDownLabel(), display.getLocales());
        display.replaceTopActionButton(display.getTagsSearchDownLabel(), display.getTagsSearch());
        display.replaceTopActionButton(display.getFieldsDownLabel(), display.getFields());
        display.replaceTopActionButton(display.getFiltersDownLabel(), display.getFilters());
        display.getTopViewSpecificLeftActionPanel().clear();
    }

    private void displayLocales() {
        this.setHelpTopicId(localePresenter.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(localePresenter.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getLocales(), display.getLocalesDownLabel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(true);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayTags() {
        this.setHelpTopicId(tagsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsComponent.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getTagsSearch(), display.getTagsSearchDownLabel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(true);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFields() {
        this.setHelpTopicId(fieldsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsComponent.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getFields(), display.getFieldsDownLabel());

        fieldsComponent.getDisplay().setViewShown(true);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFilters() {
        this.setHelpTopicId(searchFilterResultsAndFilterPresenter.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(searchFilterResultsAndFilterPresenter.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getFilters(), display.getFiltersDownLabel());

        searchFilterResultsAndFilterPresenter.getDisplay().getTopActionPanel().removeFromParent();
        display.getTopViewSpecificLeftActionPanel().setWidget(searchFilterResultsAndFilterPresenter.getDisplay().getTopActionPanel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(true);
    }

    private void applyBulkTags(@NotNull final String query, @NotNull final List<Integer> removeTags, @NotNull final Map<SearchUICategory, ArrayList<Integer>> addTags) {

        if (removeTags.size() == 0 && addTags.size() == 0) {
            Window.alert(PressGangCCMSUI.INSTANCE.NoBulkTagsSelected());
            return;
        }

        final BaseRestCallback<RESTTopicCollectionV1, Display> callback = new BaseRestCallback<RESTTopicCollectionV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTopicCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTopicCollectionV1 retValue, @NotNull final Display display) {
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
                            Window.alert(PressGangCCMSUI.INSTANCE.NoTopicsFound());
                        } else if (Window.confirm(PressGangCCMSUI.INSTANCE.ThisOperationWillModify() + " " + modifiedTopics.size() + " " + PressGangCCMSUI.INSTANCE.Topics() + ".\n" + PressGangCCMSUI.INSTANCE.AreYouSureYouWishToContinue())) {
                            updateTopic(0, new ArrayList<Integer>(), modifiedTopics);
                        }
                    }
                });
        RESTCalls.getTopicsFromQueryWithExpandedTags(callback, query);

    }

    private void updateTopic(final int index, @NotNull final List<Integer> failedTopics, @NotNull final List<RESTTopicV1> modifiedTopics) {
        checkArgument(index >= 0, "The index must be positive");
        if (index < modifiedTopics.size()) {
            final BaseRestCallback<RESTTopicV1, Display> updateTopicCallback = new BaseRestCallback<RESTTopicV1, Display>(display, new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                @Override
                public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                    updateTopic(index + 1, failedTopics, modifiedTopics);
                }
            }, new BaseRestCallback.FailureAction<Display>() {
                @Override
                public void doFailureAction(@NotNull final Display display) {
                    failedTopics.add(modifiedTopics.get(index).getId());
                    updateTopic(index + 1, failedTopics, modifiedTopics);
                }
            }
            );
            RESTCalls.saveTopic(updateTopicCallback, modifiedTopics.get(index), PressGangCCMSUI.INSTANCE.BulkTagUpdateLogMessage(), new Integer(ServiceConstants.MINOR_CHANGE), ServiceConstants.NULL_USER_ID.toString());
        } else {
            if (failedTopics.size() == 0) {
                Window.alert(PressGangCCMSUI.INSTANCE.AllTopicsUpdatedSuccessfully());
            } else {
                Window.alert(failedTopics.size() + " " + PressGangCCMSUI.INSTANCE.TopicsWereNotUpdatedCorrectly() + modifiedTopics.toString());
            }
        }
    }

    public interface Display extends BaseTemplateViewInterface {
        PushButton getSearchTopics();

        PushButton getSearchContentSpecs();

        PushButton getDownloadZip();

        PushButton getDownloadCSV();

        PushButton getApplyBulkTags();

        PushButton getTagsSearch();

        PushButton getFilters();

        PushButton getFields();

        PushButton getLocales();

        Label getTagsSearchDownLabel();

        Label getFiltersDownLabel();

        Label getFieldsDownLabel();

        Label getLocalesDownLabel();
    }


}
