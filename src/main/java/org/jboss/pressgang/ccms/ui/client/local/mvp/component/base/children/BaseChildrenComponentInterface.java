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
 * 
 * @param <W> The parent of the children
 * 
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 * 
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 */
public interface BaseChildrenComponentInterface<S extends BaseChildrenViewInterface<T, U, V, A, B, C, D, E, F>, 
    T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, 
    A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>,
    D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends EditableView, Component<S> {
    
    /**
     * @return the data that is used to back the list of potential children
     */
    ProviderUpdateData<C> getPossibleChildrenProviderData();

    /**
     * @param providerData the data that is used to back the list of potential children
     */
    void setPossibleChildrenProviderData(final ProviderUpdateData<C> providerData);
    
    /**
     * Get a list of potential children from the REST service and display it in the 
     * celltable held by the view of type <T>
     */
    void getEntityList();
    
    /**
     * @return the provider that displays the entities found in getPossibleChildrenProviderData()
     */
    EnhancedAsyncDataProvider<C> generatePossibleChildrenProvider();
    
    /**
     * Bind logic to add or remove children
     * @param getExistingCollectionCallback The callback used to get the current collection
     * @param addChildCallback The callback called to create and add a new child
     * @param updateAfterChildModfied The callback to perform additional work after the collection has been modified
     */
    void bindPossibleChildrenListButtonClicks(final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback, final AddPossibleChildCallback<A, B, C> addChildCallback,
            final UpdateAfterChildModfiedCallback updateAfterChildModfied);
}
