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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Defines an event to be triggered when the UI fails over to a new server.
 */
public class FailoverEvent extends GwtEvent<FailoverEventHandler> {
    private static final Type TYPE = new Type<FailoverEventHandler>();

    private final int id;

    @NotNull
    public static Type getType() {
        return TYPE;
    }

    public FailoverEvent(final int id) {
        this.id = id;
    }

    @Override
    public Type<FailoverEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(@NotNull final FailoverEventHandler handler) {
        handler.onFailOverEvent();
    }

    public int getId() {
        return id;
    }
}
