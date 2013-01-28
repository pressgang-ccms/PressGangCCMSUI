package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

public interface SetNewChildSortCallback<D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>> {
    /**
     * The sort property is not exposed via a consistent interface, so this method is used to allow overriding components to set
     * the sort order on the children being modified.
     * 
     * @param child The child entity whose sort order is to be modified
     * @param index The new sort index
     */
    void setSort(final F child, final int index);
}
