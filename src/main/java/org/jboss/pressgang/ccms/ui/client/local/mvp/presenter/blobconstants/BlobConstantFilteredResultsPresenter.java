package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
    The presenter used to display the list of integer constants.
 */
@Dependent
public class BlobConstantFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTBlobConstantCollectionItemV1> {

    public static final String HISTORY_TOKEN = "BlobConstantFilteredResultsView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BlobConstantFilteredResultsPresenter.class.getName());
    /**
     * The view this presenter is associated with.
     */
    @Inject private Display display;
    /**
     * The query string extracted from the history token
     */
    private String queryString;

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (@NotNull final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals(CommonFilterConstants.BLOB_CONSTANT_IDS_FILTER_VAR)) {
                    this.display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.BLOB_CONSTANT_NAME_FILTER_VAR)) {
                    this.display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }
    }

    @NotNull
    @Override
    protected EnhancedAsyncDataProvider<RESTBlobConstantCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        @NotNull final EnhancedAsyncDataProvider<RESTBlobConstantCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTBlobConstantCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTBlobConstantCollectionItemV1> list) {
                try {
                    LOGGER.log(Level.INFO, "ENTER BlobConstantFilteredResultsPresenter.generateListProvider() EnhancedAsyncDataProvider.onRangeChanged()");

                    @NotNull final BaseRestCallback<RESTBlobConstantCollectionV1, Display> callback = new BaseRestCallback<RESTBlobConstantCollectionV1, Display>(display, new BaseRestCallback.SuccessAction<RESTBlobConstantCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTBlobConstantCollectionV1 retValue, @NotNull final Display display) {
                            checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        }
                    });

                    getProviderData().setStartRow(list.getVisibleRange().getStart());
                    final int length = list.getVisibleRange().getLength();
                    final int end = getProviderData().getStartRow() + length;

                    RESTCalls.getBlobConstantsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT BlobConstantFilteredResultsPresenter.generateListProvider() EnhancedAsyncDataProvider.onRangeChanged()");
                }
            }
        };
        return provider;
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.INTEGER_CONSTANT_IDS_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getIdFilter().getText()) : display.getIdFilter().getText()));
        }
        if (!display.getValueFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.INTEGER_CONSTANT_VALUE_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getValueFilter().getText()) : display.getValueFilter().getText()));
        }
        if (!display.getNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.INTEGER_CONSTANT_NAME_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getNameFilter().getText()) : display.getNameFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    @Override
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    /**
     * The display that this presenter is associated with.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTBlobConstantCollectionItemV1> {

        /**
         *
         * @return The text box that holds the ids to search on
         */
        TextBox getIdFilter();

        /**
         *
         * @return The text box that holds the name to search on
         */
        TextBox getNameFilter();

        /**
         *
         * @return The text box that holds the description to search on
         */
        TextBox getValueFilter();
    }
}
