package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class SearchResultsPresenter extends TemplatePresenter implements EditableView {

    public static final String HISTORY_TOKEN = "SearchResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        CellTable<RESTTopicCollectionItemV1> getResults();

        SimplePager getPager();
    }

    @Inject
    private Display display;

    @Inject
    private TopicPresenter.Display topicViewDisplay;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bind();
    }

    private void bind() {

        super.bind(display, this);

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicCollectionItemV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new BaseRestCallback<RESTTopicCollectionV1, Display>(
                        display, new BaseRestCallback.SuccessAction<RESTTopicCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicCollectionV1 retValue, Display display) {
                        updateRowData(start, retValue.getItems());
                        updateRowCount(retValue.getSize(), true);
                    }
                }) {
                };
                RESTCalls.getTopicsFromQuery(callback, queryString, start, end);
            }
        };

        /* Respond to row clicks */
        display.getResults().addCellPreviewHandler(new Handler<RESTTopicCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTTopicCollectionItemV1> event) {
                final Integer id = event.getValue().getItem().getId();

                final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, Display>(
                        display, new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, Display display) {
                        topicViewDisplay.initialize(retValue, false, SplitType.NONE);
                    }
                }) {
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