package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This is the base class that is used for components adding logic to views that list the children of an entity.
 *
 * Even though the child of a parent is the same database entity as the child in the list of potential children,
 * the REST interface wraps up the relationship in a specific type. This is done so the list of children will
 * include any relationship information, like sorting e.g. Parent -> Child Collection -> Child and Relationship Info.
 * This is opposed to having a list of relationship types that then hold a reference to the child itself
 * e.g. Parent -> Child Collection -> Relationship Info -> Child.
 *
 * This manifests itself in the generic types defined by this class: A, B and C are the types that represent the potential children (i.e. the "raw" list
 * of entities from the table that holds the entities that can be attached as children); D, E and F are the types
 * that represent the children of the parent entity.
 * 
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * 
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 *
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 */
public interface BaseChildrenComponentInterface< T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>, D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends PresenterInterface {

    /**
     * @return the data that is used to back the list of potential children.
     */
    ProviderUpdateData<C> getPossibleChildrenProviderData();

    /**
     * @param providerData the data that is used to back the list of potential children
     */
    //void setPossibleChildrenProviderData(final ProviderUpdateData<C> providerData);

    /**
     * @return the provider that displays the entities found in getPossibleChildrenProviderData().
     */
    EnhancedAsyncDataProvider<C> generatePossibleChildrenProvider();

    /**
     * Binds behaviour to the tag list buttons.
     *
     * This method provides the logic for matching the potential child that was clicked, and either adding or removing
     * it from the parent collection. The various callbacks delegate the tasks of getting the collection of existing
     * children, creating a new child and updating any modified children.
     *
     * @param getExistingCollectionCallback A callback that is used to get the collection of existing children.
     * @param addChildCallback A callback that is used to create and add a new child.
     * @param updateAfterChildModfied A callback that is used to perform any post-processing required after the
     *                                child collection has been modified.
     */
    void bindPossibleChildrenListButtonClicks(final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback,
            final AddPossibleChildCallback<A, B, C> addChildCallback,
            final UpdateAfterChildModfiedCallback updateAfterChildModfied);

    /**
     * Get a list of potential children from the REST service and refresh the list of potential children.
     */
    void refreshPossibleChildrenDataAndList();

    /**
     * Called to refresh the list of potential children.
     */
    void refreshPossibleChildList();
}
