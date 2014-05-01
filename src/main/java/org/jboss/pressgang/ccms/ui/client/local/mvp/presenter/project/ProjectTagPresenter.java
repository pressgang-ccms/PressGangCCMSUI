package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

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
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.tag.RESTTagCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.tag.RESTTagCollectionItemNameSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.taginproject.RESTTagCollectionItemParentSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class ProjectTagPresenter extends BaseChildrenPresenter<
        RESTProjectV1,                                                          // The main REST types
        RESTTagCollectionItemV1,                                                // The possible children types
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>                // The existing children types
        implements BaseTemplatePresenterInterface {

    /**
     * The interface that defines the child tag view
     */
    public interface Display extends BaseChildrenViewInterface<RESTProjectV1, RESTTagCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
        /**
         * @return The column that holds the child tag id
         */
        @NotNull
        TextColumn<RESTTagCollectionItemV1> getTagsIdColumn();

        /**
         * @return The column that holds the child tag name
         */
        @NotNull
        TextColumn<RESTTagCollectionItemV1> getTagsNameColumn();
    }

    public static final String HISTORY_TOKEN = "ProjectTagView";

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
    public void displayChildrenExtended(@NotNull final RESTProjectV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        display.display(parent, readOnly);
    }

    @Override
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTProjectV1 parent) {

        final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
            @Override
            public void success(@NotNull final RESTTagCollectionV1 retValue) {
                checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                getPossibleChildrenProviderData().setStartRow(0);
                getPossibleChildrenProviderData().setItems(retValue.getItems());
                getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                redisplayPossibleChildList(parent);
            }
        };
        getPossibleChildrenProviderData().reset();
        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTags(), callback, display);
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTProjectV1 parent) {

        @NotNull final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagCollectionItemV1> data) {

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
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTTagCollectionItemParentSort(parent, ascending));
                        } else if (column == display.getTagsIdColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTTagCollectionItemIDSort(ascending));
                        } else if (column == display.getTagsNameColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTTagCollectionItemNameSort(ascending));
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
}
