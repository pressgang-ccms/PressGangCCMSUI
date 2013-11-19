package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;

public interface SetNewChildSortCallback<D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseEntityCollectionV1<D, E, F>, F extends RESTBaseEntityCollectionItemV1<D, E, F>> {
    /**
     * The sort property is not exposed via a consistent interface, so this method is used to allow overriding components to set
     * the sort order on the children being modified.
     *
     * @param child The child entity whose sort order is to be modified
     * @param index The new sort index
     * @return true if a change was made, false otherwise
     */
    boolean setSort(@NotNull final F child, final int index);
}
