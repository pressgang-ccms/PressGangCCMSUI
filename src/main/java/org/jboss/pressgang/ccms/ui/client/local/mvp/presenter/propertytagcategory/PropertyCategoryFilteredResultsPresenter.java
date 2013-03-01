package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
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

@Dependent
public class PropertyCategoryFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTPropertyCategoryCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "PropertyCategoryFilteredResultsView";
    @Inject
    private Display display;
    private String queryString;

    @Override
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {

        super.bindFilteredResults(topicId, pageId, queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    /**
     * @param waitDisplay The view used to notify the user that an ongoing operation is in progress
     * @return A provider to be used for the category display list
     */
    @Override
    @NotNull
    protected EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        @NotNull final EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTPropertyCategoryCollectionItemV1> list) {

                @NotNull final BaseRestCallback<RESTPropertyCategoryCollectionV1, Display> callback = new BaseRestCallback<RESTPropertyCategoryCollectionV1, Display>(display, new BaseRestCallback.SuccessAction<RESTPropertyCategoryCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTPropertyCategoryCollectionV1 retValue, @NotNull final Display display) {
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

                RESTCalls.getPropertyCategoriesFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROP_CATEGORY_IDS_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getIdFilter().getText()) : display.getIdFilter().getText()));
        }
        if (!display.getNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROP_CATEGORY_NAME_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getNameFilter().getText()) : display.getNameFilter().getText()));
        }
        if (!display.getDescriptionFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROP_CATEGORY_DESCRIPTION_FILTER_VAR).append("=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getDescriptionFilter().getText()) : display.getDescriptionFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (@NotNull final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals(CommonFilterConstants.PROP_CATEGORY_IDS_FILTER_VAR)) {
                    this.display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.PROP_CATEGORY_NAME_FILTER_VAR)) {
                    this.display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.PROP_CATEGORY_DESCRIPTION_FILTER_VAR)) {
                    this.display.getDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }

    }

    public interface Display extends BaseFilteredResultsViewInterface<RESTPropertyCategoryCollectionItemV1> {

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();
    }
}