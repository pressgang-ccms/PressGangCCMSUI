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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * The interface that defines the filter create/overwrite dialog
 */
public interface SaveFilterDialogInterface {

    /**
     * @return The text box representing the name of the filter.
     */
    TextBox getName();

    /**
     * @return The textarea representing the description of the filter.
     */
    TextArea getDescription();

    /**
     * @return The OK button.
     */
    PushButton getOk();

    /**
     * @return The cancel button.
     */
    PushButton getCancel();

    /**
     * @return The dialog box itself.
     */
    DialogBox getDialogBox();

    /**
     * Clear the dialog box fields.
     */
    void reset();
}
