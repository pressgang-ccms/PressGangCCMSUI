package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

/**
 * This is the base class that is used for components adding logic to views that list the children of an entity
 * @author Matthew Casperson
 *
 * @param <S> The type of the view that display the children
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 */
abstract public class BaseChildrenComponent<S extends BaseChildrenViewInterface<T, U, V, W>, T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends ComponentBase<S> implements BaseChildrenComponentInterface<S, T, U, V, W> {

    /** An instance of the provider data */
    protected ProviderUpdateData<W> providerData = new ProviderUpdateData<W>();

    @Override
    public ProviderUpdateData<W> getPossibleChildrenProviderData() {
        return providerData;
    }

    @Override
    public void setPossibleChildrenProviderData(final ProviderUpdateData<W> providerData) {
        this.providerData = providerData;
    }
   
    /**
     * Used to bind logic to mouse clicks on the list of possible children. This is not mandatory,
     * as sometimes selecting an item in this list has no effect in the UI.
     */
    protected void bindPossibleChildrenRowClick() {};
}
