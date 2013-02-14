package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * The base class for views that display a collection of ordered children and a second collection of possible children
 *
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 *
 * @param <W> The parent of the children
 *
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 *
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 */
abstract public class BaseOrderedChildrenView<
    T extends RESTBaseEntityV1<T, ?, ?>,
    W extends RESTBaseEntityV1<?, ?, ?>,
    A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>,
    D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseExtendedChildrenView<T, W, C, D, E, F> implements BaseOrderedChildrenViewInterface<T, W, C, D, E, F> {

    public BaseOrderedChildrenView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
    }
}
