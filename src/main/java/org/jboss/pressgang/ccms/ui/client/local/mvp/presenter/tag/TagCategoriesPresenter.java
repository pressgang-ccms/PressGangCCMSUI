package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;

/**
 * The presenter that provides the logic for the tag category relationships.
 *
 * @author matthew
 */
@Dependent
public class TagCategoriesPresenter extends TemplatePresenter implements EditableView {
    public static final String HISTORY_TOKEN = "TagCategoriesView";

    public interface Display extends TagViewInterface {
        EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider);

        CellTable<RESTCategoryCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTCategoryCollectionItemV1, String> getButtonColumn();

        Column<RESTTagInCategoryCollectionItemV1, String> getTagDownButtonColumn();

        Column<RESTTagInCategoryCollectionItemV1, String> getTagUpButtonColumn();

        EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> getTagsProvider();

        void setTagsProvider(EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> tagsProvider);

        VerticalPanel getTagsResultsPanel();

        HandlerSplitLayoutPanel getSplit();

        VerticalPanel getSearchResultsPanel();
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

                final RESTCalls.RESTCallback<RESTCategoryCollectionV1> callback = new BaseRestCallback<RESTCategoryCollectionV1,
                        Display>(display, new BaseRestCallback.SuccessAction<RESTCategoryCollectionV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTCategoryCollectionV1 retValue, Display display) {
                        displayNewFixedList(retValue.getItems());
                    }
                }) {
                };

                RESTCalls.getCategoriesFromQuery(callback, queryString, start, end);
            }
        };

        display.setProvider(provider);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
