package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import java.util.ArrayList;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.TopicListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.TopicListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

public class SearchResultsPresenter
        extends
        BaseFilteredResultsComponent<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1>
        implements TemplatePresenter {

    public interface Display extends
            BaseFilteredResultsViewInterface<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

    }

    public static final String HISTORY_TOKEN = "SearchResultsView";

    /**
     * Manages event registration and notification.
     */
    final private HandlerManager handlerManager = new HandlerManager(this);

    @Inject
    private Display display;

    private String queryString;

    @Inject
    private HandlerManager eventBus;

    public void addTopicListReceivedHandler(TopicListReceivedHandler handler) {
        handlerManager.addHandler(TopicListReceived.getType(), handler);
    }

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    public void process(final int topicId, final String pageId, final String queryString) {
        super.bind(topicId, pageId, queryString, display);
        this.queryString = queryString;
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    public String getQuery() {
        return queryString;
    }

    @Override
    protected void displayQueryElements(final String queryString) {
        // TODO Auto-generated method stub

    }

    @Override
    protected EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(final String queryString, final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicCollectionItemV1> list) {

                final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
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
                    public void success(final RESTTopicCollectionV1 retValue) {
                        try {
                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        } finally {
                            display.removeWaitOperation();
                            handlerManager.fireEvent(new TopicListReceived(retValue));
                        }
                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        display.removeWaitOperation();
                        getProviderData().setItems(new ArrayList<RESTTopicCollectionItemV1>());
                        getProviderData().setSize(0);
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(),
                                getProviderData().getStartRow());
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
