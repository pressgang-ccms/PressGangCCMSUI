package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import com.google.gwt.user.cellview.client.Column;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseOrderedChildrenViewInterface<
        T extends RESTBaseEntityV1<T, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseExtendedChildrenViewInterface<T, C, D, E, F> {

    @NotNull
    Column<F, String> getExistingChildUpButtonColumn();

    @NotNull
    Column<F, String> getExistingChildDownButtonColumn();
}
