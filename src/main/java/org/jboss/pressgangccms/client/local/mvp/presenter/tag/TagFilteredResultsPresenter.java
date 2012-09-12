package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagFilteredResultsView;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

@Dependent
public class TagFilteredResultsPresenter extends TemplatePresenter {
    public interface Display extends BaseTemplateViewInterface {
        AsyncDataProvider<RESTTagV1> getProvider();

        void setProvider(final AsyncDataProvider<RESTTagV1> provider);

        CellTable<RESTTagV1> getResults();

        SimplePager getPager();

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();

        @Override
        PushButton getSearch();
    }

    @Inject
    private Display display;

    private String queryString;

    /** Keeps a reference to the start row */
    private Integer tableStartRow;

    /** Keeps a reference to the list of topics being displayed */
    private List<RESTImageV1> currentList;

    @Override
    public void parseToken(final String searchToken) {
        queryString = searchToken.replace(TagFilteredResultsView.HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        bind();
    }

    private void bind() {
        super.bind(display);

        final AsyncDataProvider<RESTTagV1> provider = new AsyncDataProvider<RESTTagV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
                    @Override
                    public void begin() {
                        display.getWaiting().addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        display.getWaiting().removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTTagCollectionV1 retValue) {
                        try {
                            updateRowData(start, retValue.getItems());
                            updateRowCount(retValue.getSize(), true);
                        } finally {
                            display.getWaiting().removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.getWaiting().removeWaitOperation();
                    }
                };

                RESTCalls.getTagsFromQuery(callback, queryString, start, end);
            }
        };

        display.setProvider(provider);
    }
}
