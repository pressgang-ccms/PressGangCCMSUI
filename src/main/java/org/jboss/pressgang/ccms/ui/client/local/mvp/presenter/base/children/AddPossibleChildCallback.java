package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jetbrains.annotations.NotNull;

/**
 * When the BaseChildrenPresenter needs to add a new child to a collection, it will call the method defined in this interface.
 *
 * @param <Y> The collection wrapper for the possible child type.
 * @author Matthew Casperson
 */
public interface AddPossibleChildCallback<Y extends RESTBaseCollectionItemV1<?, ?, ?>> {

    /**
     * Create a new child relationship and add it to the parent collection.
     *
     * @param copy The existing child to associated with the new parent
     */
    void createAndAddChild(@NotNull final Y copy);
}
