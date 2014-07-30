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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.file;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageFileCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

/**
 * Provides a UI for viewing and editing a RESTLanguageFileV1 object.
 * <p/>
 * We can't bind to a byte array directly (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600), so we need to use
 * the ValueAwareEditor to do some manual binding.
 *
 * @author Matthew Casperson
 */
public final class RESTLanguageFileV1Editor extends FlexTable implements ValueAwareEditor<RESTLanguageFileCollectionItemV1> {
    /**
     * Keep a reference to the object this editor gets its values from.
     */
    public RESTLanguageFileCollectionItemV1 self;

    /**
     * To set the name of a tab based on the object that initializes this editor we need to keep a track of the tab layout and
     * the index that this editor is assigned to. See http://stackoverflow.com/questions/10463782/adding-list
     * -sub-editors-to-tab-panel for an explanation. This is the reference to the tab panel
     */
    private final TabLayoutPanel parentPanel;

    /**
     * This is a reference to the tab index
     */
    private final int parentIndex;

    private final Label filename = new Label();
    private final FileUploadExt upload = new FileUploadExt(false);
    private final PushButton uploadButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Upload());

    private final Label filenameLabel = new Label(PressGangCCMSUI.INSTANCE.FileOriginalFilename());
    private final Label newFileLabel = new Label(PressGangCCMSUI.INSTANCE.UploadFile());

    @NotNull
    @Ignore
    public Label getFilename() {
        return filename;
    }

    @NotNull
    @Ignore
    public FileUploadExt getUpload() {
        return upload;
    }

    @NotNull
    @Ignore
    public PushButton getUploadButton() {
        return uploadButton;
    }

    public RESTLanguageFileV1Editor(final TabLayoutPanel parentPanel, final int parentIndex) {
        addStyleName(CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_TAB);

        this.parentPanel = parentPanel;
        this.parentIndex = parentIndex;

        final HorizontalPanel uploadPanel = new HorizontalPanel();
        uploadPanel.add(this.upload);
        uploadPanel.add(this.uploadButton);

        int row = 0;
        this.setWidget(row, 0, this.newFileLabel);
        this.setWidget(row, 1, uploadPanel);

        ++row;
        this.setWidget(row, 0, this.filenameLabel);
        this.setWidget(row, 1, this.filename);

        this.filenameLabel.addStyleName(CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_FILENAME_LABEL);
        this.filename.addStyleName(CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_FILENAME_TEXT);

        row = 0;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_UPLOAD_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_UPLOAD_BUTTONS_CELL);

        ++row;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.FileView.FILE_VIEW_DETAIL_CELL);
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTLanguageFileCollectionItemV1> delegate) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void onPropertyChange(final String... paths) {

    }

    @Override
    public void setValue(@NotNull final RESTLanguageFileCollectionItemV1 value) {
        this.self = value;

        if (value.getItem().getLocale() != null) {
            this.parentPanel.setTabText(this.parentIndex, value.getItem().getLocale());
        }

        if (value.getItem().getFilename() != null) {
            this.filename.setText(value.getItem().getFilename());
        }
    }
}
