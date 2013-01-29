package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * When the BaseChildrenComponent needs to add a new child to a collection, it will call the method defined in this interface.
 * 
 * @author Matthew Casperson
 *
 * @param <W> The REST entity type
 * @param <X> The collection that holds type Y
 * @param <Y> The collection wrapper type for W in X
 */
public interface AddPossibleChildCallback<
        W extends RESTBaseEntityV1<W, X, Y>,
        X extends RESTBaseCollectionV1<W, X, Y>,
        Y extends RESTBaseCollectionItemV1<W, X, Y>> {

    /**
     * Create a new child relationship and add it to the parent collection.
     * @param copy The existing child to associated with the new parent
     */
    void createAndAddChild(final Y copy);
}
