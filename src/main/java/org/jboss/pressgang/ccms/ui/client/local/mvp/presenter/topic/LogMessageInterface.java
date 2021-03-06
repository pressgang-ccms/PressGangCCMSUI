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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.client.ui.*;

/**
 * The interface that defines the save log message dialog box
 *
 * @author Matthew Casperson
 */
public interface LogMessageInterface {
    PushButton getCancel();

    PushButton getOk();

    TextArea getMessage();

    RadioButton getMinorChange();

    RadioButton getMajorChange();

    TextBox getUsername();

    DialogBox getDialogBox();

    /**
     * Reset all the ui elements to default states
     */
    void reset();
}
