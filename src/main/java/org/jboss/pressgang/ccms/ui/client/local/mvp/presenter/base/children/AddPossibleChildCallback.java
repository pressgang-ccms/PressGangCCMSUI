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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jetbrains.annotations.NotNull;

/**
 * When the BaseChildrenPresenter needs to add a new child to a collection, it will call the method defined in this interface.
 *
 * @param <Y> The collection wrapper for the possible child type.
 * @author Matthew Casperson
 */
public interface AddPossibleChildCallback<Y extends RESTBaseEntityCollectionItemV1<?, ?, ?>> {

    /**
     * Create a new child relationship and add it to the parent collection.
     *
     * @param copy The existing child to associated with the new parent
     */
    void createAndAddChild(@NotNull final Y copy);
}
