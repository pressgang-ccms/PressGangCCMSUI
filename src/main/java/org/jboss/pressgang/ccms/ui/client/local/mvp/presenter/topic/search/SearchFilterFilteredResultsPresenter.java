package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The presenter used to display the list of search filters.
 */
@Dependent
public class SearchFilterFilteredResultsPresenter extends BaseFilteredResultsComponent<RESTFilterCollectionItemV1> {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchFilterFilteredResultsPresenter.class.getName());

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTFilterCollectionItemV1> {
        PushButton getSearchTopics();

        PushButton getTagsSearch();

        PushButton getFields();
    }

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterFilteredResultsView";

    /**
     * The display.
     */
    @Inject
    private Display display;

    /**
     * @return The display.
     */
    public final Display getDisplay() {
        return display;
    }

    @Override
    protected final EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.generateListProvider()");

            checkArgument(queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX), "queryString must begin with " + Constants.QUERY_PATH_SEGMENT_PREFIX);

            final EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTFilterCollectionItemV1>() {
                @Override
                protected void onRangeChanged(@NotNull final HasData<RESTFilterCollectionItemV1> list) {

                    final RESTCalls.RESTCallback<RESTFilterCollectionV1> callback = new BaseRestCallback<RESTFilterCollectionV1, Display>(
                            display,
                            new BaseRestCallback.SuccessAction<RESTFilterCollectionV1, Display>() {
                                @Override
                                public void doSuccessAction(@NotNull final RESTFilterCollectionV1 retValue, @NotNull final Display display) {
                                    try {
                                        LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.generateListProvider() SuccessAction.doSuccessAction()");

                                        getProviderData().setItems(retValue.getItems());
                                        getProviderData().setSize(retValue.getSize());
                                        relinkSelectedItem();
                                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                                    } finally {
                                        LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.generateListProvider() SuccessAction.doSuccessAction()");
                                    }
                                }
                            });

                    getProviderData().setStartRow(list.getVisibleRange().getStart());
                    final int length = list.getVisibleRange().getLength();
                    final int end = getProviderData().getStartRow() + length;

                    RESTCalls.getFiltersFromQuery(callback, queryString, getProviderData().getStartRow(), end);
                }
            };
            return provider;
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.generateListProvider()");
        }
    }

    @Override
    public final String getQuery() {
        return Constants.QUERY_PATH_SEGMENT_PREFIX;
    }

    @Override
    public final void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);

        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.bindExtendedFilteredResults()");

            display.setProvider(generateListProvider(queryString, display));
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.bindExtendedFilteredResults()");
        }
    }

    @Override
    public final void go(final HasWidgets container) {
        bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);
    }

    @Override
    public final void parseToken(final String historyToken) {

    }

    @Override
    protected final void displayQueryElements(@NotNull final String queryString) {

    }
}
