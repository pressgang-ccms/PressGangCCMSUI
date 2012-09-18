package org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.topicsearch.SearchResultsView;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class SearchResultsPresenter extends TemplatePresenter implements EditableView {
    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTTopicV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicV1> provider);

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
        
        super.bind(display, this);

        final EnhancedAsyncDataProvider<RESTTopicV1> provider = new EnhancedAsyncDataProvider<RESTTopicV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
                    @Override
                    public void begin() {
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTTopicCollectionV1 retValue) {
                        try {
                            updateRowData(start, retValue.getItems());
                            updateRowCount(retValue.getSize(), true);
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.removeWaitOperation();
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
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTTopicV1 retValue) {
                        try {
                            topicViewDisplay.initialize(retValue, false, SplitType.NONE);
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.removeWaitOperation();
                    }
                };

                RESTCalls.getTopic(callback, id);
            }
        });

        display.setProvider(provider);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
