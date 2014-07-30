/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static com.google.common.base.Preconditions.checkArgument;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.BaseOrderedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.tag.RESTTagCollectionItemIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.tag.RESTTagCollectionItemNameSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.tagincategory.RESTTagCollectionItemParentSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter used to add logic to the category tag view.
 */
@Dependent
public class CategoryTagPresenter
        extends BaseOrderedChildrenPresenter<
        RESTCategoryV1,
        RESTCategoryV1,
        RESTTagCollectionItemV1,
        RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "CategoryTagView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoryTagPresenter.class.getName());

    /**
     * The id of the category to display.
     */
    @Nullable
    private Integer entityId;
    /**
     * The category tag view.
     */
    @Inject
    private Display display;

    /**
     * The category tag view.
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public CategoryTagPresenter() {

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
        super.bindDetailedChildren(Preferences.CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH, display);
    }

    @Override
    public void displayDetailedChildrenExtended(@NotNull final RESTCategoryV1 parent, final boolean readOnly) {
        super.displayDetailedChildren(parent, readOnly);
        display.display(parent, readOnly);
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
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTCategoryV1 parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");

            final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
                @Override
                public void success(@NotNull final RESTTagCollectionV1 retValue) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() BaseRestCallback.doSuccessAction()");

                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        getPossibleChildrenProviderData().setStartRow(0);
                        getPossibleChildrenProviderData().setItems(retValue.getItems());
                        getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                        redisplayPossibleChildList(parent);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() BaseRestCallback.doSuccessAction()");
                    }
                }
            };
            getPossibleChildrenProviderData().reset();
            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTags(), callback, display);
        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");
        }
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTCategoryV1 parent) {

        return new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTagCollectionItemV1> data) {
                try {
                    LOGGER.log(Level.INFO, "ENTER CategoryTagPresenter.EnhancedAsyncDataProvider() EnhancedAsyncDataProvider.onRangeChanged()");

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
                } finally {
                    LOGGER.log(Level.INFO, "EXIT CategoryTagPresenter.EnhancedAsyncDataProvider() EnhancedAsyncDataProvider.onRangeChanged()");
                }
            }
        };
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1> generateExistingProvider(@Nullable final RESTCategoryV1 entity) {
        return new EnhancedAsyncDataProvider<RESTTagInCategoryCollectionItemV1>() {
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
    }

    /**
     * The interface that defines the category tag view.
     */
    public interface Display extends
            BaseOrderedChildrenViewInterface<
                    RESTCategoryV1,
                    RESTTagCollectionItemV1,
                    RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> {

        @NotNull
        TextColumn<RESTTagCollectionItemV1> getTagsIdColumn();

        @NotNull
        TextColumn<RESTTagCollectionItemV1> getTagsNameColumn();
    }

}
