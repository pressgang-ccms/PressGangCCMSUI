package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class SearchResultsComponent extends ComponentBase<SearchResultsPresenter.Display> implements
        SearchResultsPresenter.LogicComponent {

    /**
     * Holds the data required to populate and refresh the topic list
     */
    private ProviderUpdateData<RESTTopicCollectionItemV1> topicProviderData = new ProviderUpdateData<RESTTopicCollectionItemV1>();

    public ProviderUpdateData<RESTTopicCollectionItemV1> getTopicProviderData() {
        return topicProviderData;
    }

    public void setTopicProviderData(ProviderUpdateData<RESTTopicCollectionItemV1> topicProviderData) {
        this.topicProviderData = topicProviderData;
    }

    public void bind(final String queryString, final SearchResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        display.setProvider(generateTopicListProvider(queryString));
    }

    /**
     * @return A provider to be used for the topic display list
     */
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateTopicListProvider(final String queryString) {
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
                            topicProviderData.setItems(retValue.getItems());
                            displayAsynchronousList(topicProviderData.getItems(), retValue.getSize(), list.getVisibleRange()
                                    .getStart());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                topicProviderData.setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = topicProviderData.getStartRow() + length;

                RESTCalls.getTopicsFromQuery(callback, queryString, topicProviderData.getStartRow(), end);
            }
        };
        return provider;
    }
    


}
