package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * Called to get the current collection of existing children
 * @author Matthew Casperson
 *
 */
public interface GetExistingCollectionCallback<W extends RESTBaseEntityV1<W, X, Y>, X extends RESTBaseCollectionV1<W, X, Y>, Y extends RESTBaseCollectionItemV1<W, X, Y>> {
        X getExistingCollection();
}
