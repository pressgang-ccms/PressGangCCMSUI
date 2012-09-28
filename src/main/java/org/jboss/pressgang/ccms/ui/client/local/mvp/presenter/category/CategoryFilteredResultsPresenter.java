package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class CategoryFilteredResultsPresenter extends TemplatePresenter implements EditableView {
    public static final String HISTORY_TOKEN = "CategoryFilteredResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider);

        CellTable<RESTCategoryCollectionItemV1> getResults();

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

        final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTCategoryCollectionV1> callback =
                        new BaseRestCallback<RESTCategoryCollectionV1, Display>(display,
                                new BaseRestCallback.SuccessAction<RESTCategoryCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTCategoryCollectionV1 retValue, Display display) {
                        displayAsynchronousList(retValue.getItems(), retValue.getSize(), start);
                    }
                }) {
                };

                RESTCalls.getUnexpandedCategoriesFromQuery(callback, queryString, start, end);
            }
        };
        display.setProvider(provider);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
