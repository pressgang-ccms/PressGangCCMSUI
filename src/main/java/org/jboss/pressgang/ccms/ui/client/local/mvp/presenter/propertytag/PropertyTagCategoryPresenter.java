package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTPropertyCategoryInPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTPropertyCategoryInPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTPropertyCategoryInPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.propertycategory.RESTPropertyCategoryCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.propertycategory.RESTPropertyCategoryCollectionItemNameSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.propertycategory.RESTPropertyCategoryCollectionItemParentSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class PropertyTagCategoryPresenter extends BaseChildrenPresenter<
        RESTPropertyTagV1,                                                                                                                          // The main REST types
        RESTPropertyCategoryCollectionItemV1,                                                                                                       // The possible children types
        RESTPropertyCategoryInPropertyTagV1, RESTPropertyCategoryInPropertyTagCollectionV1, RESTPropertyCategoryInPropertyTagCollectionItemV1>      // The existing children types
        implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "PropertyTagCategoryView";

    @Nullable
    private Integer entityId;
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (@NotNull final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindChildrenExtended() {
        super.bindChildren(display);
    }

    @Override
    public void displayChildrenExtended(@NotNull final RESTPropertyTagV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        display.display(parent, readOnly);
    }

    @Override
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTPropertyTagV1 parent) {

        getPossibleChildrenProviderData().reset();


        final RESTCallBack<RESTPropertyCategoryCollectionV1> callback = new RESTCallBack<RESTPropertyCategoryCollectionV1>() {
            @Override
            public void success(@NotNull final RESTPropertyCategoryCollectionV1 retValue) {
                checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        /* Zero results can be a null list */
                getPossibleChildrenProviderData().setStartRow(0);
                getPossibleChildrenProviderData().setItems(retValue.getItems());
                getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                redisplayPossibleChildList(parent);
            }
        };

        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getPropertyTagCategories(), callback, display);
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTPropertyTagV1 parent) {

        @NotNull final EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTPropertyCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTPropertyCategoryCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
                    /*
                        Implement sorting
                    */
                    final ColumnSortList sortList = display.getPossibleChildrenResults().getColumnSortList();
                    if (sortList.size() != 0) {
                        final Column<?, ?> column = sortList.get(0).getColumn();
                        final boolean ascending = sortList.get(0).isAscending();

                        /*
                            Sort the collection
                        */
                        if (column == display.getPossibleChildrenButtonColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTPropertyCategoryCollectionItemParentSort(parent, ascending));
                        } else if (column == display.getTagsIdColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTPropertyCategoryCollectionItemIDSort(ascending));
                        } else if (column == display.getTagsNameColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTPropertyCategoryCollectionItemNameSort(ascending));
                        }
                    }

                    displayNewFixedList(getPossibleChildrenProviderData().getItems());
                } else {
                    resetProvider(false);
                }

            }
        };
        return provider;
    }

    public interface Display extends BaseChildrenViewInterface<
            RESTPropertyTagV1,                                                                                                                          // The main REST types
            RESTPropertyCategoryCollectionItemV1,                                                                                                       // The possible children types
            RESTPropertyCategoryInPropertyTagV1, RESTPropertyCategoryInPropertyTagCollectionV1, RESTPropertyCategoryInPropertyTagCollectionItemV1>      // The existing children types
    {
        /**
         * @return The column that holds the child id
         */
        @NotNull
        TextColumn<RESTPropertyCategoryCollectionItemV1> getTagsIdColumn();

        /**
         * @return The column that holds the child name
         */
        @NotNull
        TextColumn<RESTPropertyCategoryCollectionItemV1> getTagsNameColumn();
    }
}
