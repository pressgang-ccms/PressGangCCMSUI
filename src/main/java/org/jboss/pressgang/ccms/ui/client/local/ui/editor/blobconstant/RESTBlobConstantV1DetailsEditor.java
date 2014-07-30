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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.blobconstant;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

/**
 * An editor used to bind the blob constant's details to ui elements.
 */
public final class RESTBlobConstantV1DetailsEditor extends Grid implements ValueAwareEditor<RESTBlobConstantV1> {
    private static final int ROWS = 3;
    private static final int COLS = 2;

    /**
     * Keep a reference to the object this editor gets its values from.
     */
    public RESTBlobConstantV1 self;

    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    private final TextBox name = new TextBox();
    private final FileUploadExt upload = new FileUploadExt(false);
    private final PushButton uploadButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Upload());

    @NotNull
    public SimpleIntegerLabel idEditor() {
        return id;
    }

    @NotNull
    public TextBox nameEditor() {
        return name;
    }

    @NotNull
    public PushButton getUploadButton() {
        return uploadButton;
    }

    @NotNull
    public FileUploadExt getUpload() {
        return upload;
    }

    public RESTBlobConstantV1DetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_PANEL);
        id.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_ID_FIELD);
        name.addStyleName(CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_NAME_FIELD);

        name.setReadOnly(readOnly);
        upload.setEnabled(!readOnly);
        uploadButton.setEnabled(!readOnly);

        final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.BlobConstantId());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.BlobConstantName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        final Label uploadLabel = new Label(PressGangCCMSUI.INSTANCE.Upload());
        this.setWidget(2, 0, uploadLabel);
        final HorizontalPanel uploadPanel = new HorizontalPanel();
        this.setWidget(2, 1, uploadPanel);
        uploadPanel.add(upload);
        uploadPanel.add(uploadButton);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.BlobConstantView.BLOB_CONSTANT_VIEW_DETAIL_CELL);
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void onPropertyChange(final String... paths) {
    }

    @Override
    public void setValue(@NotNull final RESTBlobConstantV1 value) {
        this.self = value;
        this.id.setValue(value.getId());
        this.name.setText(value.getName());
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTBlobConstantV1> delegate) {
    }
}
