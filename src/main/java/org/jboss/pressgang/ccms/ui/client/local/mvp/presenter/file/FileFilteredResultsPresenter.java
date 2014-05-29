package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFileCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFileCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;

@Dependent
public class FileFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTFileCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTFileCollectionItemV1> {

        /**
         * @return The file id search filter field
         */
        TextBox getFileIdFilter();

        /**
         * @return The file description search filter field
         */
        TextBox getFileDescriptionFilter();

        /**
         * @return The file name search filter field
         */
        TextBox getFileNameFilter();

        /**
         * @return The bulk file upload action button.
         */
        PushButton getBulkUpload();
    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "FileFilteredResultsView";

    @Inject
    private Display display;

    private String queryString;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        this.queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(queryString);
    }

    @Override
    public void close() {

    }

    public void bindExtendedFilteredResults(@NotNull final String queryString) {
        super.bindFilteredResults(queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getFileIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.FILE_IDS_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getFileIdFilter().getText()));
        }
        if (!display.getFileDescriptionFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.FILE_DESCRIPTION_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getFileDescriptionFilter().getText()));
        }
        if (!display.getFileNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.FILE_NAME_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getFileNameFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON +
                retValue.toString();
    }

    /**
     * @param waitDisplay The view used to notify the user that an ongoing operation is in progress
     * @return A provider to be used for the image display list.
     */
    @Override
    @NotNull
    protected EnhancedAsyncDataProvider<RESTFileCollectionItemV1> generateListProvider(@NotNull final String queryString,
            @NotNull final BaseTemplateViewInterface waitDisplay) {
        @NotNull final EnhancedAsyncDataProvider<RESTFileCollectionItemV1> provider = new
                EnhancedAsyncDataProvider<RESTFileCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTFileCollectionItemV1> item) {
                getProviderData().setStartRow(item.getVisibleRange().getStart());
                final int length = item.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                final RESTCallBack<RESTFileCollectionV1> callback = new RESTCallBack<RESTFileCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTFileCollectionV1 retValue) {
                        checkState(retValue.getItems() != null, "The returned collection should have a valid items collection");
                        checkState(retValue.getSize() != null, "The returned collection should have a valid size");

                        getProviderData().setItems(retValue.getItems());
                        getProviderData().setSize(retValue.getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getFilesFromQuery(queryString, getProviderData().getStartRow(), end),
                        callback, display);
            }
        };
        return provider;
    }

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (@NotNull final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals(CommonFilterConstants.FILE_IDS_FILTER_VAR)) {
                    display.getFileIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.FILE_DESCRIPTION_FILTER_VAR)) {
                    display.getFileDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.FILE_NAME_FILTER_VAR)) {
                    display.getFileNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }

    }
}
