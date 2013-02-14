package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchTagsFieldsAndFiltersPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface
{
    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;
    @Inject
    private SearchPresenter tagsComponent;
    @Inject
    private SearchFieldPresenter fieldsComponent;

    /**
     * The presenter used to display the list of filters
     */
    @Inject private SearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;

    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject private SaveFilterDialogInterface saveFilterDialog;

    @Inject
    private HandlerManager eventBus;

    private HasWidgets container;

    @Override
    public void go(final HasWidgets container) {

        this.container = container;
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        bindExtended(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN);

        tagsComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        fieldsComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        searchFilterResultsAndFilterPresenter.bindSearchAndEditExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);

        fieldsComponent.getDisplay().display(null, false);

        bindSearchButtons();
        loadSearchTags();

        displayTags();
    }

    public void bindExtended(final int helpTopicId, final String pageId)
    {
        bind(helpTopicId, pageId, display);
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
    private void  bindFilterActionButtons(@NotNull final RESTTagCollectionV1 tags) {
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
                final String query = projects.getSearchQuery(true);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        searchFilterResultsAndFilterPresenter.getSearchFilterPresenter().getDisplay().getLoad().addClickHandler(loadClickHanlder);
        searchFilterResultsAndFilterPresenter.getSearchFilterPresenter().getDisplay().getLoadAndSearch().addClickHandler(loadAndSearchClickHandler);
    }

    @Override
    public void parseToken(final String historyToken) {

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

        final ClickHandler searchHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        final ClickHandler filtersHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                displayFilters();
            }
        };

        tagsComponent.getDisplay().getSearchTopics().addClickHandler(searchHandler);
        fieldsComponent.getDisplay().getSearchTopics().addClickHandler(searchHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getSearchTopics().addClickHandler(searchHandler);

        fieldsComponent.getDisplay().getTagsSearch().addClickHandler(tagsHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getTagsSearch().addClickHandler(tagsHandler);

        tagsComponent.getDisplay().getFields().addClickHandler(fieldsHandler);
        searchFilterResultsAndFilterPresenter.getFilteredResulstsDisplay().getFields().addClickHandler(fieldsHandler);

        tagsComponent.getDisplay().getFilters().addClickHandler(filtersHandler);
        fieldsComponent.getDisplay().getFilters().addClickHandler(filtersHandler);
    }

    private void displayTags()
    {
        clearContainerAndAddTopLevelPanel(container, display);
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(tagsComponent.getDisplay().getTopActionParentPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(true);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFields()
    {
        clearContainerAndAddTopLevelPanel(container, display);
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(fieldsComponent.getDisplay().getTopActionParentPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(true);
        tagsComponent.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFilters()
    {
        clearContainerAndAddTopLevelPanel(container, searchFilterResultsAndFilterPresenter.getDisplay());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(true);
    }

}
