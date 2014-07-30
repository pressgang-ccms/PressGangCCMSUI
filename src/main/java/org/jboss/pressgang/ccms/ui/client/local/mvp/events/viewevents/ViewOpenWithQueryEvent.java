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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Base for events that open a new view and run a query.
 *
 * @param <T> The event handler that corresponds to this event.
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenWithQueryEvent<T extends ViewOpenWithQueryEventHandler> extends GwtEvent<T> {
    /**
     * The query to be passed in the URL history token, and then ultimately onto the REST service.
     */
    @NotNull
    protected final String query;
    /**
     * true if the event should trigger a new window, false otherwise
     */
    private final boolean newWindow;

    /**
     * @return true if the event should trigger a new window, false otherwise
     */
    public boolean isNewWindow() {
        return newWindow;
    }

    /**
     * @param query     The query to be passed in the URL history token, and then ultimately onto the REST service.
     * @param newWindow true if the event should trigger a new window, false otherwise
     */
    public ViewOpenWithQueryEvent(@NotNull final String query, final boolean newWindow) {
        this.query = query;
        this.newWindow = newWindow;
    }

    /**
     * @return The query to be passed in the URL history token, and then ultimately onto the REST service
     */
    @NotNull
    public String getQuery() {
        return query;
    }

    @Override
    protected void dispatch(@NotNull final T handler) {
        handler.onViewOpen(this);
    }
}
