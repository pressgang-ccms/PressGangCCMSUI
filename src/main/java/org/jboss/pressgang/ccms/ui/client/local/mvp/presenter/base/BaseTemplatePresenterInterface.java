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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;


import org.jetbrains.annotations.NotNull;

/**
 * The base interface for all presenters.
 */
public interface BaseTemplatePresenterInterface extends PresenterInterface {

    /**
     * Parse the history token (i.e. what is after the # in the URL).
     *
     * @param historyToken The URL history token
     */
    void parseToken(@NotNull final String historyToken);

    /**
     * Bind behaviour to the UI elements in the display.
     *
     * When a view (and its associated presenter) is added to the screen directly,
     * the go() method will be called. The go() method should then call bindExtended().
     *
     * When a view (and its associated presenter) is part of a composite parent
     * view, the bindExtended() will be called by the parent presenters go() method.
     *
     * Other presenter base types may need additional parameters, in which case
     * they will provide an empty implementation of this method, and then create
     * and overloaded method.
     */
    void bindExtended();

    /**
     * @return false if the view has unsaved changes that the user wishes to save (i.e. don't continue with a navigation event),
     *         true otherwise
     */
    boolean isOKToProceed();

    /**
     * Is called by isOKToProceed to determine if it is ok to move from the page. Can also be called by other methods to see if there are
     * pending changes.
     *
     * @return true if there are unsaved changes, and false otherwise
     */
    boolean hasUnsavedChanges();

    /**
     * Called after the close method when another presenter is to be displayed.
     */
    void destroy();
}
