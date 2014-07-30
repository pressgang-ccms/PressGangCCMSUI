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

/**
 * This interface is used as a callback to check to see if there are any unsaved changes pending. If so, the user will usually
 * be notified before moving between screens.
 *
 * @author Matthew Casperson
 */
public interface EditableView {

    /**
     * @return true if there are no unsaved changes, or if the user is happy to lose any unsaved changes, and false otherwise.
     */
    boolean isOKToProceed();

    /**
     * @return true if there are unsaved changes, false otherwise
     */
    boolean hasUnsavedChanges();
}
