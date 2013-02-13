package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to display the list of filter's and their details.
 */
@Dependent
public class SearchFilterResultsAndFilterPresenter extends BaseSearchAndEditComponent<
        SearchFilterFilteredResultsPresenter.Display,
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1,
        SearchFilterPresenter.Display,
        RESTFilterV1BasicDetailsEditor> {

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterResultsAndFilterView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchFilterResultsAndFilterPresenter.class.getName());
    /**
     * The presenter used to display the filter's details.
     */
    @Inject
    private SearchFilterPresenter searchFilterPresenter;
    /**
     * The presenter used to display the list of filters.
     */
    @Inject
    private SearchFilterFilteredResultsPresenter searchFilterFilteredResultsPresenter;
    /**
     * The display.
     */
    @Inject
    private Display display;

    @Override
    public final void parseToken(final String historyToken) {
        LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.parseToken()");
    }

    @Override
    public final void go(final HasWidgets container) {

        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, display);

            /* A call back used to get a fresh copy of the entity that was selected */
            final GetNewEntityCallback<RESTFilterV1> getNewEntityCallback = new GetNewEntityCallback<RESTFilterV1>() {

                @Override
                public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTFilterV1> displayCallback) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");

                        final RESTCalls.RESTCallback<RESTFilterV1> callback = new BaseRestCallback<RESTFilterV1, BaseTemplateViewInterface>(
                                display, new BaseRestCallback.SuccessAction<RESTFilterV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(final RESTFilterV1 retValue, final BaseTemplateViewInterface display) {
                                try {
                                    LOGGER.log(Level.INFO, "ENTERSearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");

                                    displayCallback.displayNewEntity(retValue);
                                } finally {
                                    LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");
                                }
                            }
                        });
                        RESTCalls.getFilter(callback, id);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");
                    }
                }
            };

            searchFilterPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
            searchFilterFilteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);
            super.bindSearchAndEdit(
                    ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN,
                    Preferences.FILTER_VIEW_MAIN_SPLIT_WIDTH,
                    searchFilterPresenter.getDisplay(),
                    searchFilterPresenter.getDisplay(),
                    searchFilterFilteredResultsPresenter.getDisplay(),
                    searchFilterFilteredResultsPresenter,
                    display,
                    display,
                    getNewEntityCallback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.go()");
        }
    }

    @Override
    protected final void bindActionButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected final void bindFilteredResultsButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected final void loadAdditionalDisplayedItemData() {
        /* There are no additional details that need to be loaded */
    }

    @Override
    protected final void initializeViews(final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterResultsAndFilterPresenter.initializeViews()");

            if (this.viewIsInFilter(filter, this.searchFilterPresenter.getDisplay())) {
                this.searchFilterPresenter.getDisplay().display(this.searchFilterFilteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterResultsAndFilterPresenter.initializeViews()") ;
        }
    }

    /**
     * The interface that defines the view that this presenter displays.
     */
    public interface Display extends BaseSearchAndEditViewInterface<
            RESTFilterV1,
            RESTFilterCollectionV1,
            RESTFilterCollectionItemV1> {

    }
}
