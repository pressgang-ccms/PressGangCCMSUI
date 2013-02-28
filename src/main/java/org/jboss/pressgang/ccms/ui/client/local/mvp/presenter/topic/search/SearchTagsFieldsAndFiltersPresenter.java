package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TranslatedSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

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
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchTagsFieldsAndFiltersPresenter.class.getName());

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject private Display display;
    @Inject private SearchPresenter tagsComponent;
    @Inject private SearchFieldPresenter fieldsComponent;
    @Inject private SearchLocalePresenter localePresenter;

    /**
     * The presenter used to display the list of filters
     */
    @Inject private SearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject private SaveFilterDialogInterface saveFilterDialog;

    @Inject private HandlerManager eventBus;

    private HasWidgets container;

    /**
     * This is set to true if the history token indicates that we are trying to do a search of translated topics.
     */
    private boolean doingTransleatedSearch = false;

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

        displayTags();
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
        }, display) ;
    }

    /**
     * Gets the tags from the REST server
     */
    private void loadSearchTags() {

        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final BaseTemplateViewInterface waitDisplay) {

                /* Bind the load, load and search and overwrite buttons */
                bindFilterActionButtons(retValue);

                /* Display the tags */
                tagsComponent.getDisplay().displayExtended(retValue, null, false);
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
            public void onClick(final ClickEvent event) {
                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();
                tagsComponent.getDisplay().displayExtended(tags, displayedFilter, false);
                fieldsComponent.getDisplay().display(displayedFilter, false);
            }
        };

        final ClickHandler loadAndSearchClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();
                final SearchUIProjects projects = new SearchUIProjects(tags, displayedFilter);
                final SearchUIFields fields = new SearchUIFields(displayedFilter);
                final String query = projects.getSearchQuery(true) + fields.getSearchQuery(false);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
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
                    for (final RESTFilterTagCollectionItemV1 tag : displayedFilter.getFilterTags_OTM().getItems()) {
                        final RESTFilterTagV1 removeTag = new RESTFilterTagV1();
                        removeTag.setId(tag.getItem().getId());
                        filter.getFilterTags_OTM().addRemoveItem(removeTag);
                    }
                }

                if (displayedFilter.getFilterFields_OTM() != null) {
                    for (final RESTFilterFieldCollectionItemV1 field : displayedFilter.getFilterFields_OTM().getItems()) {
                        final RESTFilterFieldV1 remove = new RESTFilterFieldV1();
                        remove.setId(field.getItem().getId());
                        filter.getFilterFields_OTM().addRemoveItem(remove);
                    }
                }

                if (displayedFilter.getFilterCategories_OTM() != null) {
                    for (final RESTFilterCategoryCollectionItemV1 category : displayedFilter.getFilterCategories_OTM().getItems()) {
                        final RESTFilterCategoryV1 remove = new RESTFilterCategoryV1();
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

                final BaseRestCallback<RESTFilterV1, Display> createFilter = new BaseRestCallback<RESTFilterV1, Display>(display, new BaseRestCallback.SuccessAction<RESTFilterV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTFilterV1 retValue, @NotNull final Display display) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER SearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");

                            // Create the topic wrapper
                            final RESTFilterCollectionItemV1 collectionItem = new RESTFilterCollectionItemV1();
                            collectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                            // create the topic, and add to the wrapper
                            collectionItem.setItem(retValue);

                            /* Update the displayed topic */
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().setDisplayedItem(collectionItem.clone(true));
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().setSelectedItem(collectionItem);

                            /* if filter.getId() == null, we created a new filter */
                            searchFilterResultsAndFilterPresenter.updateDisplayAfterSave(filter.getId() == null);


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

        final ClickHandler saveCancelHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                saveFilterDialog.getDialogBox().hide();
            }
        };

        searchFilterResultsAndFilterPresenter.getSearchFilterPresenter().getDisplay().getLoad().addClickHandler(loadClickHanlder);
        searchFilterResultsAndFilterPresenter.getSearchFilterPresenter().getDisplay().getLoadAndSearch().addClickHandler(loadAndSearchClickHandler);
        searchFilterResultsAndFilterPresenter.getSearchFilterPresenter().getDisplay().getOverwrite().addClickHandler(overwriteFilter);
        searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getDisplay().getCreate().addClickHandler(createFilter);
        saveFilterDialog.getOk().addClickHandler(saveOKHandler);
        saveFilterDialog.getCancel().addClickHandler(saveCancelHandler);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        if (historyToken.startsWith(TRANSLATED_HISTORY_TOKEN)) {
            doingTransleatedSearch = true;
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
            public void onClick(final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false)
                        + localePresenter.getDisplay().getSearchUILocales().buidlQueryString(false);

                if (doingTransleatedSearch) {
                    eventBus.fireEvent(new TranslatedSearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
                else {
                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        };

        tagsComponent.getDisplay().getSearchTopics().addClickHandler(searchHandler);
        fieldsComponent.getDisplay().getSearchTopics().addClickHandler(searchHandler);
        localePresenter.getDisplay().getSearchTopics().addClickHandler(searchHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getSearchTopics().addClickHandler(searchHandler);

        fieldsComponent.getDisplay().getTagsSearch().addClickHandler(tagsHandler);
        localePresenter.getDisplay().getTagsSearch().addClickHandler(tagsHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getTagsSearch().addClickHandler(tagsHandler);

        tagsComponent.getDisplay().getFields().addClickHandler(fieldsHandler);
        localePresenter.getDisplay().getFields().addClickHandler(fieldsHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getFields().addClickHandler(fieldsHandler);

        tagsComponent.getDisplay().getFilters().addClickHandler(filtersHandler);
        localePresenter.getDisplay().getFilters().addClickHandler(filtersHandler);
        fieldsComponent.getDisplay().getFilters().addClickHandler(filtersHandler);

        tagsComponent.getDisplay().getLocales().addClickHandler(localesHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getLocales().addClickHandler(localesHandler);
        fieldsComponent.getDisplay().getLocales().addClickHandler(localesHandler);
    }

    private void displayLocales() {
        clearContainerAndAddTopLevelPanel(container, display);
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(localePresenter.getDisplay().getTopActionParentPanel());
        this.setHelpTopicId(localePresenter.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(localePresenter.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(true);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayTags() {
        clearContainerAndAddTopLevelPanel(container, display);
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(tagsComponent.getDisplay().getTopActionParentPanel());
        this.setHelpTopicId(tagsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(true);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFields() {
        clearContainerAndAddTopLevelPanel(container, display);
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(fieldsComponent.getDisplay().getTopActionParentPanel());
        this.setHelpTopicId(fieldsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(true);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFilters() {
        clearContainerAndAddTopLevelPanel(container, searchFilterResultsAndFilterPresenter.getDisplay());
        this.setHelpTopicId(searchFilterResultsAndFilterPresenter.getHelpTopicId());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(true);
    }

}
