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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor used to bind the string constant's details to ui elements
 */
public final class RESTStringConstantV1DetailsEditor extends Grid implements Editor<RESTStringConstantV1> {
    private static final int ROWS = 3;
    private static final int COLS = 2;

    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    private final TextBox name = new TextBox();
    private final TextArea value = new TextArea();

    @NotNull
    public SimpleIntegerLabel idEditor() {
        return id;
    }

    @NotNull
    public TextBox nameEditor() {
        return name;
    }

    @NotNull
    public TextArea valueEditor() {
        return value;
    }

    public RESTStringConstantV1DetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_PANEL);
        id.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_ID_FIELD);
        name.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_NAME_FIELD);
        value.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_VALUE_FIELD);

        name.setReadOnly(readOnly);
        value.setReadOnly(readOnly);

        @NotNull final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantId());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        @NotNull final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        @NotNull final Label valueLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantValue());
        this.setWidget(2, 0, valueLabel);
        this.setWidget(2, 1, value);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_DETAIL_CELL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_DESCRIPTION_CELL);
    }

}
