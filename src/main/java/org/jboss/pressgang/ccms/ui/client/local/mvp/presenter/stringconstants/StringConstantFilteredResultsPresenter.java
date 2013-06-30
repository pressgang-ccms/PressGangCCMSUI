package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to display the list of string constants.
 */
@Dependent
public class StringConstantFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTStringConstantCollectionItemV1> {

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "StringConstantFilteredResultsView";

    /**
     * The view that corresponds to this presenter.
     */
    @Inject
    private Display display;
    /**
     * The query string, which is the full history token minus the HISTORY_TOKEN.
     */
    private String queryString;

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (@NotNull final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals(CommonFilterConstants.STRING_CONSTANT_IDS_FILTER_VAR)) {
                    this.display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.STRING_CONSTANT_NAME_FILTER_VAR)) {
                    this.display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.STRING_CONSTANT_VALUE_FILTER_VAR)) {
                    this.display.getValueFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }
    }

    @NotNull
    @Override
    protected EnhancedAsyncDataProvider<RESTStringConstantCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        return new EnhancedAsyncDataProvider<RESTStringConstantCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTStringConstantCollectionItemV1> list) {

                @NotNull final BaseRestCallback<RESTStringConstantCollectionV1, Display> callback = new BaseRestCallback<RESTStringConstantCollectionV1, Display>(display, new BaseRestCallback.SuccessAction<RESTStringConstantCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTStringConstantCollectionV1 retValue, @NotNull final Display display) {
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

                RESTCalls.getStringConstantsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
    }

    @NotNull
    @Override
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.STRING_CONSTANT_IDS_FILTER_VAR).append("=").append(Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(display.getIdFilter().getText()) : display.getIdFilter().getText());
        }
        if (!display.getValueFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.STRING_CONSTANT_VALUE_FILTER_VAR).append("=").append(Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(display.getValueFilter().getText()) : display.getValueFilter().getText());
        }
        if (!display.getNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.STRING_CONSTANT_NAME_FILTER_VAR).append("=").append(Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(display.getNameFilter().getText()) : display.getNameFilter().getText());
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    /**
     * @return The view that corresponds to this presenter.
     */
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

    @Override
    public void close() {

    }

    /**
     * The definition of the view that corresponds to this presenter.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTStringConstantCollectionItemV1> {

        /**
         * @return The text box used to display the filter ids
         */
        TextBox getIdFilter();

        /**
         * @return The text box used to display the filter name
         */
        TextBox getNameFilter();

        /**
         * @return The text box used to display the filter value
         */
        TextBox getValueFilter();
    }
}
