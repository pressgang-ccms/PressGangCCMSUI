package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The type of the entity being edited by the view
 * 
 * @param <W> The type of the parent of X
 *
 * @param <C> The collection item type of possible child entities
 * 
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 */
public interface BaseOrderedChildrenComponentInterface<
            T extends RESTBaseEntityV1<?, ?, ?>,
            W extends RESTBaseEntityV1<?, ?, ?>,
            C extends RESTBaseCollectionItemV1<?, ?, ?>,
            D extends RESTBaseEntityV1<D, E, F>,
            E extends RESTBaseCollectionV1<D, E, F>,
            F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseDetailedChildrenPresenterInterface<T, W, C, D, E, F> {

    void setSortOrderOfChildren(final SetNewChildSortCallback<D, E, F> sortCallback);
    
    boolean moveTagsUpAndDown(final T editingParent, final W parent, final F object, final boolean down, final SetNewChildSortCallback<D, E, F> sortCallback);
}
