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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseAuditedEntityV1;
import org.jetbrains.annotations.NotNull;


/**
 * A callback to be implemented by a Search and Edit component that gets a copy of an entity from the server.
 */
public interface GetNewEntityCallback<T extends RESTBaseAuditedEntityV1<?, ?, ?>> {
    /**
     * Load a new entity based on the one that was selected, and then call the displayCallback.
     *
     * @param selectedEntity  The entity that was selected from the list of filtered results.
     * @param displayCallback The callback to call once the new entity is loaded.
     */
    void getNewEntity(@NotNull final T selectedEntity, @NotNull final DisplayNewEntityCallback<T> displayCallback);
}
