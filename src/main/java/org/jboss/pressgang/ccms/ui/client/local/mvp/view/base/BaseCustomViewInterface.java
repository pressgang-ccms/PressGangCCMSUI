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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

/**
 * The interface for a view that displays custom ui elements (i.e. not a table or editor)
 */
public interface BaseCustomViewInterface<T> extends BaseTemplateViewInterface {
    /**
     * Populate the UI with the details from the supplied entity. If additional data is required by the view,
     * it is usually defined in a method called initialize.
     *
     * Since this is a method on a view, no logic is done here other than to copy out the information in
     * the entity and display it in the view.
     *
     * @param entity   The entity that contains the data to be displayed
     * @param readonly true if the UI should be read only, and false otherwise
     */
    void display(final T entity, final boolean readonly);
}
