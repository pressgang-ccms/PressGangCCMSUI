package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
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
 * @param <S> The type of the view that display the children
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 */
public interface BaseChildrenComponentInterface<S extends BaseChildrenViewInterface<T, U, V, W>, T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends EditableView, Component<S> {
    
    /**
     * @return the data that is used to back the list of potential children
     */
    ProviderUpdateData<W> getPossibleChildrenProviderData();

    /**
     * @param providerData the data that is used to back the list of potential children
     */
    void setPossibleChildrenProviderData(final ProviderUpdateData<W> providerData);
    
    /**
     * Get a list of potential children from the REST service and display it in the 
     * celltable held by the view of type <T>
     */
    void getEntityList();
    
    /**
     * @return the provider that displays the entities found in getPossibleChildrenProviderData()
     */
    EnhancedAsyncDataProvider<W> generatePossibleChildrenProvider();
}
