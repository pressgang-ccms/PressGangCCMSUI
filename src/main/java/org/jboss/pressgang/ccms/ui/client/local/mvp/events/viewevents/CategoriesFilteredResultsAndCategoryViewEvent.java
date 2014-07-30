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

import org.jetbrains.annotations.NotNull;

/**
 * Event used to display the Categories view.
 */
public final class CategoriesFilteredResultsAndCategoryViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    /**
     * Initialize the event data.
     *
     * @param query     The query to be passed in the URL history token, and then ultimately onto the REST service
     * @param newWindow true if the view should be opened in a new window
     */
    public CategoriesFilteredResultsAndCategoryViewEvent(@NotNull final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @NotNull
    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
