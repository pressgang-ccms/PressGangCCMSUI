package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.cell.client.FieldUpdater;

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

    /** An instance of the provider data */
    protected ProviderUpdateData<V> providerData = new ProviderUpdateData<V>();

    @Override
    public ProviderUpdateData<V> getPossibleChildrenProviderData() {
        return providerData;
    }

    @Override
    public void setPossibleChildrenProviderData(final ProviderUpdateData<V> providerData) {
        this.providerData = providerData;
    }
   
    /**
     * Used to bind logic to mouse clicks on the list of possible children. This is not mandatory,
     * as sometimes selecting an item in this list has no effect in the UI.
     */
    protected void bindPossibleChildrenRowClick() {};
}
