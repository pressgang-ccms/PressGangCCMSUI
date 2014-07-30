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
import org.jetbrains.annotations.NotNull;

public interface SetNewChildSortCallback<D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseEntityCollectionV1<D, E, F>, F extends RESTBaseEntityCollectionItemV1<D, E, F>> {
    /**
     * The sort property is not exposed via a consistent interface, so this method is used to allow overriding components to set
     * the sort order on the children being modified.
     *
     * @param child The child entity whose sort order is to be modified
     * @param index The new sort index
     * @return true if a change was made, false otherwise
     */
    boolean setSort(@NotNull final F child, final int index);
}
