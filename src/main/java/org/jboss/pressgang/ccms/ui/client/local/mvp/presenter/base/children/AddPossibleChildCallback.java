package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;

/**
 * When the BaseChildrenComponent needs to add a new child to a collection, it will call the method defined in this interface.
 *
 * @param <Y> The collection wrapper type for W in X
 * @author Matthew Casperson
 */
public interface AddPossibleChildCallback<
        Y extends RESTBaseCollectionItemV1<?, ?, ?>> {

    /**
     * Create a new child relationship and add it to the parent collection.
     *
     * @param copy The existing child to associated with the new parent
     */
    void createAndAddChild(final Y copy);
}
