package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
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
public class SearchFilterFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTFilterCollectionItemV1> {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchFilterFilteredResultsPresenter.class.getName());

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTFilterCollectionItemV1> {
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
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @NotNull
    @Override
    protected EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.generateListProvider()");

            checkArgument(queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX), "queryString must begin with " + Constants.QUERY_PATH_SEGMENT_PREFIX);

            @NotNull final EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTFilterCollectionItemV1>() {
                @Override
                protected void onRangeChanged(@NotNull final HasData<RESTFilterCollectionItemV1> list) {

                    getProviderData().setStartRow(list.getVisibleRange().getStart());
                    final int length = list.getVisibleRange().getLength();
                    final int end = getProviderData().getStartRow() + length;;

                    final RESTCallBack<RESTFilterCollectionV1> callback = new RESTCallBack<RESTFilterCollectionV1>() {
                        @Override
                        public void success(@NotNull final RESTFilterCollectionV1 retValue) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.generateListProvider() SuccessAction.doSuccessAction()");

                                checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                                checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                                getProviderData().setItems(retValue.getItems());
                                getProviderData().setSize(retValue.getSize());
                                relinkSelectedItem();
                                displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.generateListProvider() SuccessAction.doSuccessAction()");
                            }
                        }
                    };

                    FailOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getFiltersFromQuery(queryString, getProviderData().getStartRow(), end), callback, display);
                }
            };
            return provider;
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.generateListProvider()");
        }
    }

    @NotNull
    @Override
    public String getQuery() {
        return Constants.QUERY_PATH_SEGMENT_PREFIX;
    }

    @Override
    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);

        try {
            LOGGER.log(Level.INFO, "ENTER SearchFilterFilteredResultsPresenter.bindExtendedFilteredResults()");

            display.setProvider(generateListProvider(queryString, display));
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchFilterFilteredResultsPresenter.bindExtendedFilteredResults()");
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Constants.QUERY_PATH_SEGMENT_PREFIX);
    }

    @Override
    public void close() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {

    }
}
