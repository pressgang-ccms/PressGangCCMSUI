package org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class SearchResultsPresenter extends TemplatePresenter {
    public interface Display extends BaseTemplateViewInterface {
        AsyncDataProvider<RESTTopicV1> getProvider();

        void setProvider(final AsyncDataProvider<RESTTopicV1> provider);

        CellTable<RESTTopicV1> getResults();

        SimplePager getPager();
    }

    @Inject
    private Display display;

    @Inject
    private TopicPresenter.Display topicViewDisplay;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = searchToken.replace(SearchResultsView.HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        bind();
    }

    private void bind() {
        super.bind(display);

        final AsyncDataProvider<RESTTopicV1> provider = new AsyncDataProvider<RESTTopicV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicV1> display) {
                final int start = display.getVisibleRange().getStart();
                final int length = display.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
                    @Override
                    public void begin() {
                        startProcessing();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        stopProcessing();
                    }

                    @Override
                    public void success(final RESTTopicCollectionV1 retValue) {
                        try {
                            updateRowData(start, retValue.getItems());
                            updateRowCount(retValue.getSize(), true);
                        } finally {
                            stopProcessing();
                        }
                    }

                    @Override
                    public void failed() {
                        stopProcessing();
                    }
                };

                RESTCalls.getTopicsFromQuery(callback, queryString, start, end);
            }
        };

        /* Respone to row clicks */
        display.getResults().addCellPreviewHandler(new Handler<RESTTopicV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTTopicV1> event) {
                final Integer id = event.getValue().getId();

                final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>() {
                    @Override
                    public void begin() {
                        startProcessing();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        stopProcessing();
                    }

                    @Override
                    public void success(final RESTTopicV1 retValue) {
                        try {
                            topicViewDisplay.initialize(retValue, false, SplitType.NONE);
                        } finally {
                            stopProcessing();
                        }
                    }

                    @Override
                    public void failed() {
                        stopProcessing();
                    }
                };

                RESTCalls.getTopic(callback, id);
            }
        });

        display.setProvider(provider);
    }

    private void stopProcessing() {
        display.setSpinnerVisible(false);
    }

    private void startProcessing() {
        display.setSpinnerVisible(true);
    }
}
