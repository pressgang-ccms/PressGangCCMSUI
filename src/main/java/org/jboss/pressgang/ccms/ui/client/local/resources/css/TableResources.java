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

package org.jboss.pressgang.ccms.ui.client.local.resources.css;

import com.google.gwt.user.cellview.client.CellTable;
import org.jetbrains.annotations.NotNull;

public interface TableResources extends CellTable.Resources {
    /**
     * The styles applied to the table.
     */
    interface TableStyle extends CellTable.Style {

    }

    @NotNull
    @Override
    @Source({CellTable.Style.DEFAULT_CSS, "CustomCellTable.css"})
    TableStyle cellTableStyle();
}