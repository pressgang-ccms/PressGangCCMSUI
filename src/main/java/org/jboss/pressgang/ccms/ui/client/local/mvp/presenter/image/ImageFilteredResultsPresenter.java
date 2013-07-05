package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;

@Dependent
public class ImageFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTImageCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTImageCollectionItemV1> {

        /**
         * @return The image id search filter field
         */
        TextBox getImageIdFilter();

        /**
         * @return The image description search filter field
         */
        TextBox getImageDescriptionFilter();

        /**
         * @return The image original file name search filter field
         */
        TextBox getImageOriginalFileNameFilter();

        /**
         * @return The bulk image upload action button.
         */
        PushButton getBulkUpload();
    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

    @Inject
    private Display display;

    private String queryString;

    @Inject
    private HandlerManager eventBus;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        this.queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public void close() {

    }

    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getImageIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.IMAGE_IDS_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getImageIdFilter().getText()));
        }
        if (!display.getImageDescriptionFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.IMAGE_DESCRIPTION_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getImageDescriptionFilter().getText()));
        }
        if (!display.getImageOriginalFileNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.IMAGE_ORIGINAL_FILENAME_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getImageOriginalFileNameFilter().getText()));
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
    protected EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider(@NotNull final String queryString,
            @NotNull final BaseTemplateViewInterface waitDisplay) {
        @NotNull final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = new
                EnhancedAsyncDataProvider<RESTImageCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTImageCollectionItemV1> item) {
                getProviderData().setStartRow(item.getVisibleRange().getStart());
                final int length = item.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                @NotNull final BaseRestCallback<RESTImageCollectionV1, Display> callback = new BaseRestCallback<RESTImageCollectionV1,
                        Display>(
                        display, new BaseRestCallback.SuccessAction<RESTImageCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTImageCollectionV1 retValue, @NotNull final Display display) {
                        checkState(retValue.getItems() != null, "The returned collection should have a valid items collection");
                        checkState(retValue.getSize() != null, "The returned collection should have a valid size");

                        getProviderData().setItems(retValue.getItems());
                        getProviderData().setSize(retValue.getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                });

                RESTCalls.getImagesFromQuery(callback, queryString, getProviderData().getStartRow(), end);
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
                if (queryElements[0].equals(CommonFilterConstants.IMAGE_IDS_FILTER_VAR)) {
                    this.display.getImageIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.IMAGE_DESCRIPTION_FILTER_VAR)) {
                    this.display.getImageDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.IMAGE_ORIGINAL_FILENAME_FILTER_VAR)) {
                    this.display.getImageOriginalFileNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }

    }
}
