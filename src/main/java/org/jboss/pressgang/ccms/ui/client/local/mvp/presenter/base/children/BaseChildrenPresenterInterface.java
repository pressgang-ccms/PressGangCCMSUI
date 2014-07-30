/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * The base class for any presenter displaying the children of an entity. Presenters that extend this class directly
 * usually display a table of possible children, with add or remove buttons allowing the collection of children
 * held by the parent to be modified.
 * <p/>
 * Even though the child of a parent is the same database entity as the child in the list of potential children,
 * the REST interface wraps up the relationship in a specific type. This is done so the list of children will
 * include any relationship information, like sorting e.g. Parent -> Child Collection -> Child and Relationship Info.
 * This is opposed to having a list of relationship types that then hold a reference to the child itself
 * e.g. Parent -> Child Collection -> Relationship Info -> Child.
 * <p/>
 * This manifests itself in the generic types defined by this class: A, B and C are the types that represent the potential children (i.e. the "raw" list
 * of entities from the table that holds the entities that can be attached as children); D, E and F are the types
 * that represent the children of the parent entity.
 *
 * @param <T> The entity type
 * @param <C> The collection item type for potential children
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseChildrenPresenterInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends PresenterInterface {

    /**
     * Classes extending BaseChildrenPresenter need to implement this method to initialize the object, making sure to
     * call bindChildren().
     */
    void bindChildrenExtended();

    /**
     * Display the data held by parent. This methods must call displayChildren().
     *
     * @param parent   The object that holds the data we want to display
     * @param readOnly true if the view is read only , false otherwise
     */
    void displayChildrenExtended(@NotNull final T parent, final boolean readOnly);

    /**
     * @return the data that is used to back the list of potential children.
     */
    @NotNull
    ProviderUpdateData<C> getPossibleChildrenProviderData();

    /**
     * @param providerData the data that is used to back the list of potential children
     */
    //void setPossibleChildrenProviderData(final ProviderUpdateData<C> providerData);

    /**
     * @param parent The entity that will hold the children. This will be used when populating a table of Ont-To-Many
     *               children, as the list of "potential children" is actually just the list of existing children. For
     *               Many-To-Many collections, the list of potential children will usually ignore this parameter and
     *               just get a collection from the REST interface.
     * @return the provider that displays the entities found in getPossibleChildrenProviderData().
     */
    @NotNull
    EnhancedAsyncDataProvider<C> generatePossibleChildrenProvider(@NotNull final T parent);

    /**
     * Binds behaviour to the tag list buttons.
     * <p/>
     * This method provides the logic for matching the potential child that was clicked, and either adding or removing
     * it from the parent collection. The various callbacks delegate the tasks of getting the collection of existing
     * children, creating a new child and updating any modified children.
     *
     * @param getExistingCollectionCallback A callback that is used to get the collection of existing children.
     * @param addChildCallback              A callback that is used to create and add a new child.
     * @param updateAfterChildModified      A callback that is used to perform any post-processing required after the
     *                                      child collection has been modified.
     */
    void bindPossibleChildrenListButtonClicks(@NotNull final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback,
                                              @NotNull final AddPossibleChildCallback<C> addChildCallback,
                                              @NotNull final UpdateAfterChildModifiedCallback updateAfterChildModified);

    /**
     * Get a list of potential children from the REST service and refresh the list of potential children. Used when we
     * want to get a fresh list of potential children from the REST interface.
     */
    void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final T parent);

    /**
     * Called to refresh the list of potential children. Used when the update needs to be displayed (because a potential
     * child has been added or removed), but we don't actually want to make another call to the REST interface to get a
     * fresh list of potential children.
     *
     * @param parent The entity that will hold the children. This will be used when populating a table of Ont-To-Many
     *               children, as the list of "potential children" is actually just the list of existing children. For
     *               Many-To-Many collections, the list of potential children will usually ignore this parameter and
     *               just get a collection from the REST interface.
     */
    void redisplayPossibleChildList(@NotNull final T parent);
}
