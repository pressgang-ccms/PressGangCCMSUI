/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.BaseOrderedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.SetNewChildSortCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.category.RESTCategoryCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.category.RESTCategoryCollectionItemNameSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.category.RESTCategoryCollectionItemParentSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class TagCategoriesPresenter
        extends
        BaseOrderedChildrenPresenter<
                RESTTagV1,
                RESTCategoryV1,
                RESTCategoryCollectionItemV1,
                RESTTagInCategoryV1,
                RESTTagInCategoryCollectionV1,
                RESTTagInCategoryCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    public interface Display extends BaseOrderedChildrenViewInterface<
            RESTTagV1,
            RESTCategoryCollectionItemV1,
            RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> {
        TextColumn<RESTCategoryCollectionItemV1> getIdColumn();

        TextColumn<RESTCategoryCollectionItemV1> getNameColumn();
    }

    /**
     * The history token
     */
    public static final String HISTORY_TOKEN = "TagCategoriesView";

    @Inject
    private Display display;

    private String queryString;

    @NotNull
    public Display getDisplay() {
        return display;
    }


    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    protected void go() {
        bindDetailedChildrenExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindDetailedChildrenExtended() {
        super.bindDetailedChildren(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display);
        // display.setExistingChildrenProvider(generateExistingProvider());
        initLifecycleBindPossibleChildrenRowClick();
    }

    @Override
    public void displayDetailedChildrenExtended(@NotNull final RESTTagV1 parent, final boolean readOnly) {
        super.displayDetailedChildren(parent, readOnly);

        display.setPossibleChildrenProvider(generatePossibleChildrenProvider(parent));
        initLifecycleBindExistingChildrenRowClick(parent);
        refreshPossibleChildrenDataFromRESTAndRedisplayList(parent);
    }

    /**
     * Used when moving children up and down
     */
    private final SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> sortCallback = new SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>() {

        @Override
        public boolean setSort(@NotNull final RESTTagInCategoryCollectionItemV1 child, final int index) {
            if (child.getItem().getRelationshipSort() != index) {
                child.getItem().explicitSetRelationshipSort(index);
                /* Set any unchanged items to updated */
                if (RESTBaseEntityCollectionItemV1.UNCHANGED_STATE.equals(child.getState())) {
                    child.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                }

                return true;
            }

            return false;
        }
    };

    /**
     * This provider pages over a collection of categories that was returned when the page was built. This is because changes to
     * the tag category relationships are done to the categories, not to the tag. This means we need to keep a list of the
     * categories instead of losing them when the table is paged through.
     *
     * @return A provider to be used for the category display list.
     */
    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTTagV1 parent) {
        @NotNull final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTCategoryCollectionItemV1> data) {

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
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTCategoryCollectionItemParentSort(parent, ascending));
                        } else if (column == display.getIdColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTCategoryCollectionItemIDSort(ascending));
                        } else if (column == display.getNameColumn()) {
                            Collections.sort(getPossibleChildrenProviderData().getItems(), new RESTCategoryCollectionItemNameSort(ascending));
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

    /**
     * @return A provider to be used for the tag display list
     */
    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateExistingProvider(@Nullable final RESTCategoryV1 entity) {
        @NotNull final EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagInCategoryCollectionItemV1> display) {
                getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                getExistingProviderData().setItems(new ArrayList<RESTTagInCategoryCollectionItemV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                if (entity != null && entity.getTags() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagInCategoryCollectionItemV1 tagInCategory : entity.getTags().returnExistingAddedAndUpdatedCollectionItems()) {
                        getExistingProviderData().getItems().add(tagInCategory);
                    }
                }

                getExistingProviderData().setSize(getExistingProviderData().getItems().size());
                Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                displayNewFixedList(getExistingProviderData().getItems());
            }
        };

        return provider;
    }

    /**
     * Bind behaviour to the buttons found in the cell table listing the categories
     */
    private void initLifecycleBindPossibleChildrenRowClick() {
        display.getPossibleChildrenResults().addCellPreviewHandler(new Handler<RESTCategoryCollectionItemV1>() {
            @Override
            public void onCellPreview(@NotNull final CellPreviewEvent<RESTCategoryCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * getProviderData().getSelectedItem() will be null until a category is selected for the first time
                     */
                    final boolean needToAddView = getPossibleChildrenProviderData().getSelectedItem() == null;

                    /*
                     * Normally a list is populated with an un-expanded collection of entities. However, in this case we have
                     * expanded the categories to include all the tags.
                     * 
                     * Because the categories that are displayed in the category list have all the expanded topics, we don't
                     * need to get an expanded category in response to a category being selected. This means the displayed and
                     * selected items are the same.
                     */
                    getPossibleChildrenProviderData().setSelectedItem(event.getValue());
                    getPossibleChildrenProviderData().setDisplayedItem(event.getValue());

                    /*
                     * If this is the first category selected, display the tags list
                     */
                    if (needToAddView) {
                        display.getSplit().add(display.getExistingChildrenResultsPanel());
                    }

                    /*
                     * reset the provider, which will refresh the list of tags
                     */
                    display.setExistingChildrenProvider(generateExistingProvider(getPossibleChildrenProviderData()
                            .getDisplayedItem().getItem()));
                }
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* It is possible that the list of categories has not loaded yet, in which case no changes could have been made */
        if (getPossibleChildrenProviderData().getItems() != null) {
            for (@NotNull final RESTCategoryCollectionItemV1 category : getPossibleChildrenProviderData().getItems()) {
                if (category.getItem().getTags().returnDeletedAddedAndUpdatedCollectionItems().size() != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void initLifecycleBindExistingChildrenRowClick(@NotNull final RESTTagV1 editingParent) {


        display.getExistingChildUpButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            @Override
            public void update(final int index, @NotNull final RESTTagInCategoryCollectionItemV1 object, @Nullable final String value) {
                checkState(getPossibleChildrenProviderData().getDisplayedItem() != null, "There should be a displayed possible children collection item.");
                checkState(getPossibleChildrenProviderData().getDisplayedItem().getItem() != null, "The displayed possible children collection item to reference a valid entity.");
                moveTagsUpAndDown(editingParent, getPossibleChildrenProviderData().getDisplayedItem().getItem(), object, false, sortCallback);
            }

        });

        display.getExistingChildDownButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    /**
                     * Swap the sort value for the tag that was selected with the tag below it.
                     */
                    @Override
                    public void update(final int index, @NotNull final RESTTagInCategoryCollectionItemV1 object, @Nullable final String value) {
                        checkState(getPossibleChildrenProviderData().getDisplayedItem() != null, "There should be a displayed possible children collection item.");
                        checkState(getPossibleChildrenProviderData().getDisplayedItem().getItem() != null, "The displayed possible children collection item to reference a valid entity.");
                        moveTagsUpAndDown(editingParent, getPossibleChildrenProviderData().getDisplayedItem().getItem(), object, true, sortCallback);
                    }
                });
    }

    /**
     * Get the collection of categories, to which we will add or remove the currently selected tag. Note that the changes made
     * to this collection will be synced in reverse to the tag when the save button is clicked i.e. where the displayed tag is
     * added to a project, that will actually be persisted through the REST interface as a category added to the displayed tag.
     */
    @Override
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTTagV1 parent) {

        getPossibleChildrenProviderData().reset();

        final RESTCallBack<RESTCategoryCollectionV1> callback = new RESTCallBack<RESTCategoryCollectionV1>() {
            @Override
            public void success(@NotNull final RESTCategoryCollectionV1 retValue) {
                checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                getPossibleChildrenProviderData().setStartRow(0);
                getPossibleChildrenProviderData().setSize(retValue.getSize());
                getPossibleChildrenProviderData().setItems(retValue.getItems());

                redisplayPossibleChildList(parent);
            }
        };

        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getCategories(), callback, display);
    }
}
