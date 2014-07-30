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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import com.google.gwt.user.cellview.client.Column;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseOrderedChildrenViewInterface<
        T extends RESTBaseEntityV1<T, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseEntityCollectionV1<D, E, F>, F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseExtendedChildrenViewInterface<T, C, D, E, F> {

    @NotNull
    Column<F, String> getExistingChildUpButtonColumn();

    @NotNull
    Column<F, String> getExistingChildDownButtonColumn();
}
