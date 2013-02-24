package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/**
 * @param <T> The type of the entity being edited by the view
 * @param <U> The collection type of T
 * @param <V> The collection Item type of T
 * @param <W> The type of the parent of A and D
 * @param <A> The type of the potential children
 * @param <B> The collection type of A
 * @param <C> The collection item type of A
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 * @author Matthew Casperson
 */
abstract public class BaseOrderedChildrenComponent<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseDetailedChildrenPresenter<T, W, C, D, E, F>
        implements BaseOrderedChildrenComponentInterface<T, W, C, D, E, F> {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BaseOrderedChildrenComponent.class.getName());



    @Override
    public final void setSortOrderOfChildren(@NotNull final SetNewChildSortCallback<D, E, F> sortCallback) {
        if (sortCallback == null) {
            throw new NullPointerException("sortCallback cannot be null");
        }

        final int size = getExistingProviderData().getItems().size();

        for (int i = Constants.CHILDREN_SORT_ORDER_START; i < size + Constants.CHILDREN_SORT_ORDER_START; ++i) {
            final F child = getExistingProviderData().getItems().get(i - Constants.CHILDREN_SORT_ORDER_START);
            sortCallback.setSort(child, i);
        }
    }

    @Override
    public final boolean moveTagsUpAndDown(@NotNull final T editingParent, @NotNull final W parent, @NotNull final F object, final boolean down,
                                           @NotNull final SetNewChildSortCallback<D, E, F> sortCallback) {

        if (parent == null) {
            throw new NullPointerException("parent cannot be null");
        }
        if (object == null) {
            throw new NullPointerException("object cannot be null");
        }
        if (sortCallback == null) {
            throw new NullPointerException("sortCallback cannot be null");
        }

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
                    if (RESTBaseCollectionItemV1.UNCHANGED_STATE.equals(existingChild.getState())) {
                        existingChild.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
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
