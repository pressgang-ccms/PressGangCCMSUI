package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * When the BaseChildrenComponent needs to add a new child to a collection, it will call the method defined in this interface.
 * 
 * @author Matthew Casperson
 */
public interface AddPossibleChildCallback<W extends RESTBaseEntityV1<W, X, Y>, X extends RESTBaseCollectionV1<W, X, Y>, Y extends RESTBaseCollectionItemV1<W, X, Y>> {
    void createAndAddChild(final Y copy);
}
