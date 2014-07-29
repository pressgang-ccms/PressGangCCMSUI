/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This presenter is used to add logic to a display of potential children and the existing children of an entity.
 *
 * @param <T> The entity type
 * @param <W> The parent entity type
 * @param <C> The collection item type for potential children
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseDetailedChildrenPresenterInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseChildrenPresenterInterface<T, C, D, E, F>, EditableView {

    /**
     * @return The provider that exposes the existing children.
     */
    @NotNull
    ProviderUpdateData<F> getExistingProviderData();

    /**
     * @param entity Sometimes the entity being edited is one of the potential children e.g. when editing tags, we can add and
     *               remove the tag from a category that is selected as a potential child, and that selected category's tags are then
     *               presented for ordering. In that case, the entity being edited can be found through the
     *               getPossibleChildrenProviderData().getDisplayedItem() property.
     *               <p/>
     *               In other cases, the list of potential children is just used to add and remove children, and the selected potential
     *               child has no significance. This happens when editing a category and assigning tags to it. The list of potential
     *               children is all the tags, while the existing children are the children of the category being edited. In this case,
     *               the entity being edited can be referenced via the filtered results getProviderData().getDisplayedItem() property.
     * @return The provider used to display the list of existing children
     */
    @NotNull
    EnhancedAsyncDataProvider<F> generateExistingProvider(@NotNull final W entity);

    /**
     * Called to refresh the list of existing children.
     *
     * @param parent The entity that contains the existing children.
     */
    void refreshExistingChildList(@NotNull final W parent);

    /**
     * This method should be called to initialize any class that extends this interface.
     *
     * @param topicId The help topic id for this view.
     * @param pageId  The history token of the page
     */
    void bindDetailedChildrenExtended();

    /**
     * Display the data held in parent. Must call displayDetailedChildren();
     *
     * @param parent   The object holding the data to be displayed.
     * @param readOnly true if the view is read only , false otherwise
     */
    void displayDetailedChildrenExtended(@NotNull final T parent, final boolean readOnly);
}
