package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;

/**
 * Called to get the current collection of existing children.
 *
 * @author Matthew Casperson
 */
public interface GetExistingCollectionCallback<
        W extends RESTBaseEntityV1<W, X, Y>,
        X extends RESTBaseEntityCollectionV1<W, X, Y>,
        Y extends RESTBaseEntityCollectionItemV1<W, X, Y>> {
    @NotNull
    X getExistingCollection();
}
