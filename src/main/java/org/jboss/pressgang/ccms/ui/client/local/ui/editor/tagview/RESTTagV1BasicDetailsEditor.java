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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public final class RESTTagV1BasicDetailsEditor extends FlexTable implements Editor<RESTTagV1> {
    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    private final TextBox name = new TextBox();
    private final TextArea description = new TextArea();

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.TagID());
    private final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.TagName());
    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.TagDescription());

    @NotNull
    public SimpleIntegerLabel idEditor() {
        return id;
    }

    @NotNull
    public TextBox nameEditor() {
        return name;
    }

    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    public RESTTagV1BasicDetailsEditor(final boolean readOnly) {
        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);
        this.setWidget(2, 0, descriptionLabel);
        this.setWidget(2, 1, description);

        this.addStyleName(CSSConstants.TagView.TAG_VIEW_PANEL);

        idLabel.addStyleName(CSSConstants.TagView.TAG_VIEW_ID_LABEL);
        id.addStyleName(CSSConstants.TagView.TAG_VIEW_ID_TEXT);
        nameLabel.addStyleName(CSSConstants.TagView.TAG_VIEW_NAME_LABEL);
        name.addStyleName(CSSConstants.TagView.TAG_VIEW_NAME_TEXT);
        descriptionLabel.addStyleName(CSSConstants.TagView.TAG_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.TagView.TAG_VIEW_DESCRIPTION_TEXT);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TagView.TAG_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(0, 1, CSSConstants.TagView.TAG_VIEW_DETAIL_CELL);
        this.getCellFormatter().addStyleName(1, 0, CSSConstants.TagView.TAG_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(1, 1, CSSConstants.TagView.TAG_VIEW_DETAIL_CELL);
        this.getCellFormatter().addStyleName(2, 0, CSSConstants.TagView.TAG_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(2, 1, CSSConstants.TagView.TAG_VIEW_DESCRIPTION_CELL);
    }
}
