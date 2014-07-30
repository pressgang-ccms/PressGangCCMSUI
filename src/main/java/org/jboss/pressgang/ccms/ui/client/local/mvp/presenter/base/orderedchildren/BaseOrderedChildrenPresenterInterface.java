/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren.BaseDetailedChildrenPresenterInterface;
import org.jetbrains.annotations.NotNull;

/**
 * This presenter is used to present the ordered children of an entity.
 *
 * @param <T> The type of the entity being edited by the view
 * @param <W> The type of the parent of X
 * @param <C> The collection item type of possible child entities
 * @param <D> The type of the existing children
 * @param <E> The collection type of D
 * @param <F> The collection item type of D
 * @author Matthew Casperson
 */
public interface BaseOrderedChildrenPresenterInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseDetailedChildrenPresenterInterface<T, W, C, D, E, F> {

    /**
     * The sort order of child collections is determined by an integer field. This field has no restrictions, and may be set
     * with duplicate, non-consecutive or null values. This function will take the current sort order (based on the Integer
     * field and the name) and set the sort field to consecutive, predicable values.
     */
    void setSortOrderOfChildren(@NotNull final SetNewChildSortCallback<D, E, F> sortCallback);

    /**
     * @param editingParent The entity that is being edited.
     * @param parent        The parent of the children being modified. This is not necessarily the same of the editingParent. For example, tags are ordered children of categories.
     *                      So while none of the child collections of a tag is ordered, it is still convenient to be able to order a tag within the child tags held bya parent
     *                      category within the tags editing view. In this case the editingParent would be the tag, while the parent would be the category.
     * @param object        The child being edited.
     * @param down          true if it is being moved down, and false if it is being moved up
     * @param sortCallback  a callback used to set the sort property of the child (as this property is not defined in it's own interface or subclass)
     * @return true if the sort order of any child was modified, false otherwise
     */
    boolean moveTagsUpAndDown(@NotNull final T editingParent, @NotNull final W parent, @NotNull final F object, final boolean down, @NotNull final SetNewChildSortCallback<D, E, F> sortCallback);
}
