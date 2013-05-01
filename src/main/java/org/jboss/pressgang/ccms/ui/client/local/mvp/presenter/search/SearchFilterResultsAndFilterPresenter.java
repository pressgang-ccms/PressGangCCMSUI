package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to display the list of filter's and their details.
 */
@Dependent
public class SearchFilterResultsAndFilterPresenter extends BaseSearchAndEditPresenter<
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1,
        RESTFilterV1BasicDetailsEditor> {

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterResultsAndFilterView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchFilterResultsAndFilterPresenter.class.getName());
    @Inject
    private SearchFilterPresenter searchFilterPresenter;
    @Inject
    private SearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter;
    /**
     * The display.
     */
    @Inject
    private Display display;

    /**
     * @return The display
     */
    @NotNull
    public final Display getDisplay() {
        return display;
    }

    /**
     * @return The display
     */
    @NotNull
    public final SearchFilterFilteredResultsPresenter.Display getFilteredResultsDisplay() {
        return searchFilterFilteredResultsPresenter.getDisplay();
    }

    @Override
    public final void parseToken(@NotNull final String historyToken) {
        LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.parseToken()");
    }

    @Override
    public final void go(@NotNull final HasWidgets container) {

        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, display);
            bindSearchAndEditExtended(ServiceConstants.FILTERS_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);

        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go()");
        }
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTFilterV1> getNewEntityCallback = new GetNewEntityCallback<RESTFilterV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTFilterV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTFilterV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");

                    @NotNull final RESTCalls.RESTCallback<RESTFilterV1> callback = new BaseRestCallback<RESTFilterV1, BaseTemplateViewInterface>(
                            display, new BaseRestCallback.SuccessAction<RESTFilterV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(@NotNull final RESTFilterV1 retValue, final BaseTemplateViewInterface display) {
                            try {
                                LOGGER.log(Level.INFO, "ENTERSearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getFilterCategories_OTM() != null, "The initially retrieved entity should have an expanded filter categories collection");
                                checkArgument(retValue.getFilterFields_OTM() != null, "The initially retrieved entity should have an expanded filter fields collection");
                                checkArgument(retValue.getFilterTags_OTM() != null, "The initially retrieved entity should have an expanded filter tags collection");
                                checkArgument(retValue.getFilterLocales_OTM() != null, "The initially retrieved entity should have an expanded filter locales collection");

                                checkArgument(retValue.getFilterTags_OTM().getItems().size() == 0 || retValue.getFilterTags_OTM().getItems().get(0).getItem().getTag() != null,
                                        "The initially retrieved entity should have an expanded filter tags collection, and the filter tags should have an expanded tag reference");

                                checkArgument(retValue.getFilterCategories_OTM().getItems().size() == 0 || retValue.getFilterCategories_OTM().getItems().get(0).getItem().getCategory() != null,
                                        "The initially retrieved entity should have an expanded filter categories collection, and the filter tags should have an expanded category reference");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");
                            }
                        }
                    });
                    RESTCalls.getFilter(callback, selectedEntity.getId());
                } finally {
                    LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        searchFilterPresenter.bindExtended(topicId, pageId);
        searchFilterFilteredResultsPresenter.bindExtendedFilteredResults(topicId, pageId, Constants.QUERY_PATH_SEGMENT_PREFIX);
        super.bindSearchAndEdit(
                topicId,
                pageId,
                Preferences.FILTER_VIEW_MAIN_SPLIT_WIDTH,
                searchFilterPresenter.getDisplay(),
                searchFilterPresenter.getDisplay(),
                searchFilterFilteredResultsPresenter.getDisplay(),
                searchFilterFilteredResultsPresenter,
                display,
                display,
                getNewEntityCallback);
    }

    @Override
    protected final void bindActionButtons() {

    }

    @Override
    protected final void bindFilteredResultsButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected final void loadAdditionalDisplayedItemData() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.loadAdditionalDisplayedItemData()");

            /*
                When a filter is selected, the load and overwrite buttons are displayed.
            */
            display.getLoad().setEnabled(true);
            display.getLoadAndSearch().setEnabled(true);
            display.getOverwrite().setEnabled(true);

        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.loadAdditionalDisplayedItemData()");
        }
    }

    @Override
    protected final void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.initializeViews()");

            checkState(searchFilterFilteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
            checkState(searchFilterFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

            if (this.viewIsInFilter(filter, this.searchFilterPresenter.getDisplay())) {
                this.searchFilterPresenter.getDisplay().display(this.searchFilterFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.initializeViews()");
        }
    }

    /**
     * The presenter used to display the filter's details.
     */
    public SearchFilterPresenter getSearchFilterPresenter() {
        return searchFilterPresenter;
    }

    public void setSearchFilterPresenter(@NotNull final SearchFilterPresenter searchFilterPresenter) {
        this.searchFilterPresenter = searchFilterPresenter;
    }

    /**
     * The presenter used to display the list of filters.
     */
    public SearchFilterFilteredResultsPresenter getSearchFilterFilteredResultsPresenter() {
        return searchFilterFilteredResultsPresenter;
    }

    public void setSearchFilterFilteredResultsPresenter(@NotNull final SearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter) {
        this.searchFilterFilteredResultsPresenter = searchFilterFilteredResultsPresenter;
    }


    /**
     * The interface that defines the view that this presenter displays.
     */
    public interface Display extends BaseSearchAndEditViewInterface<
            RESTFilterV1,
            RESTFilterCollectionV1,
            RESTFilterCollectionItemV1> {
        PushButton getCreate();
        PushButton getOverwrite();
        PushButton getLoad();
        PushButton getLoadAndSearch();
    }
}
