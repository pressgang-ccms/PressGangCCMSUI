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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren.BaseDetailedChildrenPresenter;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;

/**
 * @see BaseOrderedChildrenPresenterInterface
 */
abstract public class BaseOrderedChildrenPresenter<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseDetailedChildrenPresenter<T, W, C, D, E, F>
        implements BaseOrderedChildrenPresenterInterface<T, W, C, D, E, F> {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseOrderedChildrenPresenter.class.getName());

    @Override
    public void setSortOrderOfChildren(@NotNull final SetNewChildSortCallback<D, E, F> sortCallback) {

        checkState(getExistingProviderData() != null, "getExistingProviderData() should not return null");
        checkState(getExistingProviderData().isValid(), "The getExistingProviderData() collection needs to be valid");

        final int size = getExistingProviderData().getItems().size();

        for (int i = Constants.CHILDREN_SORT_ORDER_START; i < size + Constants.CHILDREN_SORT_ORDER_START; ++i) {
            final F child = getExistingProviderData().getItems().get(i - Constants.CHILDREN_SORT_ORDER_START);
            sortCallback.setSort(child, i);
        }
    }

    @Override
    public boolean moveTagsUpAndDown(@NotNull final T editingParent, @NotNull final W parent, @NotNull final F object, final boolean down,
                                     @NotNull final SetNewChildSortCallback<D, E, F> sortCallback) {

        checkState(getExistingProviderData() != null, "getExistingProviderData() should not return null");
        checkState(getExistingProviderData().isValid(), "The getExistingProviderData() collection needs to be valid");

        final int size = getExistingProviderData().getItems().size();

        boolean modifiedSort = false;

        /* if moving down, start at the beginning, and end on the second last item. If moving up, start the second item */
        for (int i = (down ? 0 : 1); i < (down ? size - 1 : size); ++i) {

            /* Get the item from the collection for convenience */
            final F child = getExistingProviderData().getItems().get(i);

            if (child.getItem().getId().equals(object.getItem().getId())) {

                /*
                 * The sort values are exposed directly in the old UI. This means that it is possible for two tags to have the
                 * same or no sort value assigned to them, or have sort values that increment in odd values.
                 * 
                 * If we are changing the sort order in the new UI, we need a consistent progression of the sort field. So, now
                 * that we have found a tag that needs changing, we start by reassigning sort values based on the location of
                 * the tag in the collection.
                 */

                for (int j = 0; j < size; ++j) {
                    /* get the item from the collection */
                    final F existingChild = getExistingProviderData().getItems().get(j);

                    /* set the sort value (the list was previously sorted on this value) */
                    sortCallback.setSort(existingChild, j);

                    /* we need to mark the joiner entity as updated */
                    if (RESTBaseEntityCollectionItemV1.UNCHANGED_STATE.equals(existingChild.getState())) {
                        existingChild.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                    }
                }

                /* The next item is either the item before (if moving up) of the item after (if moving down) */
                final int nextItemIndex = down ? i + 1 : i - 1;

                /* get the next item in the list */
                final F nextChild = getExistingProviderData().getItems().get(nextItemIndex);

                /* swap the sort field */
                sortCallback.setSort(child, nextItemIndex);
                sortCallback.setSort(nextChild, i);

                modifiedSort = true;
                break;
            }
        }

        if (modifiedSort) {
            refreshExistingChildList(parent);
            redisplayPossibleChildList(editingParent);
        }

        return modifiedSort;
    }

}
