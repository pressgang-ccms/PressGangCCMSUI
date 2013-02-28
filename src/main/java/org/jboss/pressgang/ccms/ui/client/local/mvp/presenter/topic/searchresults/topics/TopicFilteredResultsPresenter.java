package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TopicFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTTopicCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTTopicCollectionItemV1> {

    }

    public static final String HISTORY_TOKEN = "SearchResultsView";

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

    public TopicFilteredResultsPresenter() {

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
    protected EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider() {
        getProviderData().resetToEmpty();

        @NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {
                displayNewFixedList(getProviderData().getItems());
            }
        };
        return provider;
    }

    @Nullable
    @Override
    protected EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@Nullable final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {

        if (queryString == null) {
            return null;
        }

        @NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {

                @NotNull final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(@NotNull final RESTTopicCollectionV1 retValue) {
                        try {
                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        } finally {
                            display.removeWaitOperation();
                            getHandlerManager().fireEvent(new EntityListReceived(retValue));
                        }
                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        display.removeWaitOperation();
                        getProviderData().setItems(new ArrayList<RESTTopicCollectionItemV1>());
                        getProviderData().setSize(0);
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getTopicsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

}
