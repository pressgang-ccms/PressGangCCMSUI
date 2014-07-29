/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor that binds the data in a RESTFilterV1 to UI elements.
 */
public final class RESTFilterV1BasicDetailsEditor extends Grid implements Editor<RESTFilterV1> {
    /**
     * How many rows will be displayed by this editor.
     */
    private static final int ROWS = 2;
    /**
     * How many columns will be displayed by this editor.
     */
    private static final int COLS = 2;

    /**
     * Displays the filter's name.
     */
    private final TextBox name = new TextBox();
    /**
     * Displays the filter's description.
     */
    private final TextArea description = new TextArea();

    /**
     * The method used by the editor framework to manipulate the description field.
     *
     * @return The ui element displaying the filter's description.
     */
    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    /**
     * The method used by the editor framework to manipulate the name field.
     *
     * @return The ui element displaying the filter's name.
     */
    @NotNull
    public TextBox nameEditor() {
        return name;
    }

    /**
     * @param readOnly true if this editor should be readonly
     */
    public RESTFilterV1BasicDetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.FilterEditor.FILTER_VIEW_PANEL);

        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        name.addStyleName(CSSConstants.FilterEditor.FILTER_VIEW_NAME_FIELD);
        description.addStyleName(CSSConstants.FilterEditor.FILTER_VIEW_DESCRIPTION_FIELD);

        int row = 0;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.FilterName()));
        this.setWidget(row, 1, name);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.FilterDescription()));
        this.setWidget(row, 1, description);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.FilterEditor.FILTER_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.FilterEditor.FILTER_VIEW_DETAIL_CELL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.FilterEditor.FILTER_VIEW_DESCRIPTION_CELL);
    }
}
