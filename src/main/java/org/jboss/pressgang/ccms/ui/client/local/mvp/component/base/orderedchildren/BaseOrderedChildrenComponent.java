package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <S> The type of the parent view
 * 
 * @param <T> The type of the entity being edited by the view
 * @param <U> The collection type of T
 * @param <V> The collection Item type of T
 * 
 * @param <W> The type of the parent of A and D
 * 
 * @param <A> The type of the potential children
 * @param <B> The collection type of A
 * @param <C> The collection item type of A
 * 
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 */
abstract public class BaseOrderedChildrenComponent<S extends BaseOrderedChildrenViewInterface<T, U, V, W, A, B, C, D, E, F>, 
    T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>,
    W extends RESTBaseEntityV1<?, ?, ?>,
    A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>,
    D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenComponent<S, T, U, V, A, B, C, D, E, F> implements BaseOrderedChildrenComponentInterface<S, T, U, V, W, A, B, C, D, E, F> {

    protected ProviderUpdateData<F> existingProviderData = new ProviderUpdateData<F>();
    
    public void bind(final String preferencesKey, final S display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        display.setPossibleChildrenProvider(generatePossibleChildrenProvider());
        refreshPossibleChildrenDataAndList();
        loadChildSplitResize(preferencesKey);
        bindChildSplitResize(preferencesKey);
    }

    @Override
    public ProviderUpdateData<F> getExistingProviderData() {
        return existingProviderData;
    }

    @Override
    public void setExistingProviderData(final ProviderUpdateData<F> existingProviderData) {
        this.existingProviderData = existingProviderData;
    }

    /**
     * Save the size of the split ui component
     * 
     * @param preferencesKey The key against which the previous size was saved
     */
    protected void bindChildSplitResize(final String preferencesKey) {
        display.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(preferencesKey,
                        display.getSplit().getSplitPosition(display.getPossibleChildrenResultsPanel()) + "");
            }
        });
    }

    /**
     * Restores the size of the child split screen
     * 
     * @param preferencesKey The key against which the previous size was saved
     */
    protected void loadChildSplitResize(final String preferencesKey) {
        display.getSplit().setSplitPosition(display.getPossibleChildrenResultsPanel(),
                Preferences.INSTANCE.getInt(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
    }

    /**
     * Used to bind logic to the selection of an existing child. Optional, as most of the time this won't trigger any action.
     */
    protected void bindExistingChildrenRowClick() {
    }

    /**
     * Reorder a collection and move a child entity up or down
     * 
     * @param object The child to be moved
     * @param down true if the child is to be moved down, false if it is to be moved up
     * @return true if the sort order of any child was modified, false otherwise
     */
    @Override
    public boolean moveTagsUpAndDown(final W parent, final F object, final boolean down, final SetNewChildSortCallback<D, E, F> sortCallback) {

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
                    if (!existingChild.returnIsAddItem())
                        existingChild.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
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
        
        if (modifiedSort)
        {
            refreshExistingChildList(parent);
            refreshPossibleChildList();
        }
        
        return modifiedSort;
    }

    @Override
    public void refreshExistingChildList(final W parent)
    {
        display.setExistingChildrenProvider(generateExistingProvider(parent));
    }
    


}
