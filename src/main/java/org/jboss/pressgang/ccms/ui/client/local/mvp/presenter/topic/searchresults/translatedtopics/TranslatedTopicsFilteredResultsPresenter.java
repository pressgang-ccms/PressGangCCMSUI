package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.inject.Inject;
import java.util.ArrayList;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

public class TranslatedTopicsFilteredResultsPresenter extends BaseFilteredResultsComponent<RESTTranslatedTopicCollectionItemV1> implements BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTTranslatedTopicCollectionItemV1> {

    }

    public static final String HISTORY_TOKEN = "TranslatedTopicsFilteredResultsView";

    @Inject
    private Display display;

    private String queryString;

    @Inject
    private HandlerManager eventBus;

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
        bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    public void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        this.queryString = queryString;

        if (queryString == null) {
            display.setProvider(generateListProvider());
        } else {
            display.setProvider(generateListProvider(queryString, display));
        }
    }

    @Override
    public String getQuery() {
        return queryString;
    }

    @Override
    protected void displayQueryElements(final String queryString) {
        // TODO Auto-generated method stub
    }

    protected EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> generateListProvider() {
        getProviderData().resetToEmpty();

        final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTranslatedTopicCollectionItemV1> list) {
                displayNewFixedList(getProviderData().getItems());
            }
        };
        return provider;
    }

    @Override
    protected EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> generateListProvider(final String queryString, final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTranslatedTopicCollectionItemV1> list) {

                final BaseRestCallback<RESTTranslatedTopicCollectionV1, Display> callback = new BaseRestCallback<RESTTranslatedTopicCollectionV1, Display>(
                        display,
                        new BaseRestCallback.SuccessAction<RESTTranslatedTopicCollectionV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTTranslatedTopicCollectionV1 retValue, final Display display) {
                                try {
                                    getProviderData().setItems(retValue.getItems());
                                    getProviderData().setSize(retValue.getSize());
                                    relinkSelectedItem();
                                    displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                                } finally {
                                    getHandlerManager().fireEvent(new EntityListReceived<RESTTranslatedTopicCollectionV1>(retValue));
                                }
                            }
                    }, new BaseRestCallback.FailureAction<Display>() {
                        @Override
                        public void doFailureAction(Display display) {
                            getProviderData().setItems(new ArrayList<RESTTranslatedTopicCollectionItemV1>());
                            getProviderData().setSize(0);
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        }
                    });

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getTranslatedTopicsFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

}
