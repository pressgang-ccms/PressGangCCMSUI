package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

/**
 * 
 * @author Matttthew Casperson
 * 
 * @param <S> The view type
 * @param <T> The entity being modified
 * @param <W> The entity type modified as an ordered collection of children
 * @param <U> The potential children type
 * @param <V> The existing children type
 */
abstract public class BaseOrderedChildrenComponent<S extends BaseOrderedChildrenViewInterface<T, U, V>, T extends RESTBasePrimaryEntityV1, W extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseChildrenComponent<S, T, U> implements BaseOrderedChildrenComponentInterface<S, T, W, U, V> {

    protected ProviderUpdateData<V> existingProviderData = new ProviderUpdateData<V>();

    @Override
    public ProviderUpdateData<V> getExistingProviderData() {
        return existingProviderData;
    }

    @Override
    public void setExistingProviderData(final ProviderUpdateData<V> existingProviderData) {
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
    public boolean moveTagsUpAndDown(final V object, final boolean down) {

        final int size = getExistingProviderData().getItems().size();

        boolean modifiedSort = false;

        /* if moving down, start at the beginning, and end on the second last item. If moving up, start the second item */
        for (int i = (down ? 0 : 1); i < (down ? size - 1 : size); ++i) {

            /* Get the item from the collection for convenience */
            final V child = getExistingProviderData().getItems().get(i);

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
                    final V existingChild = getExistingProviderData().getItems().get(j);

                    /* set the sort value (the list was previously sorted on this value) */
                    setSort(existingChild, j);

                    /* we need to mark the joiner entity as updated */
                    if (!existingChild.returnIsAddItem())
                        existingChild.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                }

                /* The next item is either the item before (if moving up) of the item after (if moving down) */
                final int nextItemIndex = down ? i + 1 : i - 1;

                /* get the next item in the list */
                final V nextChild = getExistingProviderData().getItems().get(nextItemIndex);

                /* swap the sort field */
                setSort(child, nextItemIndex);
                setSort(nextChild, i);

                modifiedSort = true;
                break;
            }
        }
        
        return modifiedSort;
    }

    /**
     * The sort property is not exposed via a consistent interface, so this method is used to allow overriding components to set
     * the sort order on the children being modified.
     * 
     * @param child The child entity whose sort order is to be modified
     * @param index The new sort index
     */
    abstract protected void setSort(final V child, final int index);

}
