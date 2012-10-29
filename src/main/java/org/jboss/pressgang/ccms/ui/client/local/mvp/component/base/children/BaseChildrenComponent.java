package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This is the base class that is used for components adding logic to views that list the children of an entity
 * @author Matthew Casperson
 *
 * @param <T> The display that holds the child view
 * @param <U> The parent entity type
 * @param <V> The potential children type
 */
abstract public class BaseChildrenComponent<T extends BaseChildrenViewInterface<U, V>, U extends RESTBasePrimaryEntityV1, V extends RESTBaseCollectionItemV1>
        extends ComponentBase<T> implements BaseChildrenComponentInterface<T, U, V> {

    protected ProviderUpdateData<V> providerData = new ProviderUpdateData<V>();

    @Override
    public ProviderUpdateData<V> getProviderData() {
        return providerData;
    }

    @Override
    public void setProviderData(final ProviderUpdateData<V> providerData) {
        this.providerData = providerData;
    }

    
    
    abstract protected void bindPossibleChildrenRowClick();
}
