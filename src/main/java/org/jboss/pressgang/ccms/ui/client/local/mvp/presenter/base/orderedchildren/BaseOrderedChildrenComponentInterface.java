package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The type of the entity being edited by the view
 * @param <U> The collection type of T
 * @param <V> The collection Item type of T
 * 
 * @param <W> The type of the parent of X
 * 
 * @param <A> The type of the potential children
 * @param <B> The collection type of A
 * @param <C> The collection item type of A
 * 
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 */
public interface BaseOrderedChildrenComponentInterface<
            T extends RESTBaseEntityV1<T, U, V>,
            U extends RESTBaseCollectionV1<T, U, V>,
            V extends RESTBaseCollectionItemV1<T, U, V>,
            W extends RESTBaseEntityV1<?, ?, ?>,
            A extends RESTBaseEntityV1<A, B, C>,
            B extends RESTBaseCollectionV1<A, B, C>,
            C extends RESTBaseCollectionItemV1<A, B, C>,
            D extends RESTBaseEntityV1<D, E, F>,
            E extends RESTBaseCollectionV1<D, E, F>,
            F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseExtendedChildrenPresenterInterface<T, U, V, W, A, B, C, D, E, F> {

    void setSortOrderOfChildren(final SetNewChildSortCallback<D, E, F> sortCallback);
    
    boolean moveTagsUpAndDown(final W parent, final F object, final boolean down, final SetNewChildSortCallback<D, E, F> sortCallback);
}
