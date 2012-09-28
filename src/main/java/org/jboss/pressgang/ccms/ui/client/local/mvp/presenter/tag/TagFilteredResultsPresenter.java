package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;

@Dependent
public class TagFilteredResultsPresenter extends TemplatePresenter implements EditableView {
    public static final String HISTORY_TOKEN = "TagFilteredResultsView";
    
    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTTagCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider);

        CellTable<RESTTagCollectionItemV1> getResults();

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
        queryString = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        bind();
    }

    private void bind() {
        super.bind(display, this);

        final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagCollectionItemV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1,
                        Display>(display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTagCollectionV1 retValue, Display display) {
                        displayNewFixedList(retValue.getItems());
                    }
                }) {
                };

                RESTCalls.getTagsFromQuery(callback, queryString, start, end);
            }
        };

        display.setProvider(provider);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
