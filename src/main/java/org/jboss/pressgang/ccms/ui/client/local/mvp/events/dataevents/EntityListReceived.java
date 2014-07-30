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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is raised when the topics from a query have been returned from the
 * REST server.
 */
public class EntityListReceived<T extends RESTBaseEntityCollectionV1<?, ?, ?>> extends GwtEvent<EntityListReceivedHandler<T>> {

    private static final Type TYPE = new Type<EntityListReceivedHandler>();
    /**
     * The collection of entities that this event relates to.
     */
    @NotNull
    private final T entities;

    @NotNull
    public static Type getType() {
        return TYPE;
    }

    /**
     * The constructor initializes the final variables.
     *
     * @param entities The collection of entities that this event relates to.
     */
    public EntityListReceived(@NotNull final T entities) {
        this.entities = entities;
    }


    @NotNull
    @Override
    public Type<EntityListReceivedHandler<T>> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(@NotNull final EntityListReceivedHandler<T> collectionReceivedHandler) {
        collectionReceivedHandler.onCollectionReceived(entities);
    }
}
