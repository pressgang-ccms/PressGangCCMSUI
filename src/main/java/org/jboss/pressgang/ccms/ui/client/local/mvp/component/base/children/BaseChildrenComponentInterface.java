package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This interface defines the methods used to add logic to a view that displays a list of potential children
 * 
 * @author Matthew Casperson
 *
 * @param <T> The type of the view
 * @param <U> The type of the possible children
 * @param <V> The type of the existing children
 */
public interface BaseChildrenComponentInterface<T extends BaseChildrenViewInterface<U, V>, U extends RESTBasePrimaryEntityV1<?, ?, ?>, V extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends EditableView, Component<T> {
    
    /**
     * @return the data that is used to back the list of potential children
     */
    ProviderUpdateData<V> getPossibleChildrenProviderData();

    /**
     * @param providerData the data that is used to back the list of potential children
     */
    void setPossibleChildrenProviderData(final ProviderUpdateData<V> providerData);
    
    /**
     * Get a list of potential children from the REST service and display it in the 
     * celltable held by the view of type <T>
     */
    void getEntityList();
    
    /**
     * @return the provider that displays the entities found in getPossibleChildrenProviderData()
     */
    EnhancedAsyncDataProvider<V> generatePossibleChildrenProvider();
}
