package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTTextContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTTextContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ContentSpecFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTTextContentSpecCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTTextContentSpecCollectionItemV1> {
        //@NotNull PushButton getBulkImport();
        //@NotNull PushButton getBulkOverwrite();
    }

    public static final String HISTORY_TOKEN = "ContentSpecFilteredResultsView";

    @Inject
    private Display display;

    @Nullable
    private String queryString;

    @Inject
    private HandlerManager eventBus;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    public ContentSpecFilteredResultsPresenter() {

    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public void close() {
    }

    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @Nullable final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        this.queryString = queryString;

        if (queryString == null) {
            display.setProvider(generateListProvider());
        } else {
            display.setProvider(generateListProvider(queryString, display));
        }
    }

    @NotNull
    @Override
    public String getQuery() {
        return queryString;
    }

    @Override
    protected void displayQueryElements(@Nullable final String queryString) {
        // TODO Auto-generated method stub
    }

    @NotNull
    protected EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> generateListProvider() {
        getProviderData().resetToEmpty();

        @NotNull final EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTextContentSpecCollectionItemV1> list) {
                displayNewFixedList(getProviderData().getItems());
            }
        };
        return provider;
    }

    @Nullable
    @Override
    protected EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> generateListProvider(@Nullable final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {

        if (queryString == null) {
            return null;
        }

        final EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTextContentSpecCollectionItemV1> list) {

                final BaseRestCallback<RESTTextContentSpecCollectionV1, Display> callback = new BaseRestCallback<RESTTextContentSpecCollectionV1, Display>(
                        display, new BaseRestCallback.SuccessAction<RESTTextContentSpecCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTextContentSpecCollectionV1 retValue, @NotNull final Display display) {
                        try {
                            checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                            for (final RESTTextContentSpecCollectionItemV1 item : retValue.getItems()) {
                                ComponentContentSpecV1.fixDisplayedText(item.getItem());
                            }

                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        } finally {
                            getHandlerManager().fireEvent(new EntityListReceived<RESTTextContentSpecCollectionV1>(retValue));
                        }
                    }
                });

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getContentSpecsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

}
