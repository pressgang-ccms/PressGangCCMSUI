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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentLanguageImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.vectomatic.file.FileUploadExt;

/**
 * Provides a UI for viewing and editing a RESTLanguageImageV1 object.
 * <p/>
 * We can't bind to a byte array directly (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600), so we need to use
 * the ValueAwareEditor to do some manual binding.
 *
 * @author Matthew Casperson
 */
public final class RESTLanguageImageV1Editor extends FlexTable implements ValueAwareEditor<RESTLanguageImageCollectionItemV1> {
    /**
     * Keep a reference to the object this editor gets its values from.
     */
    public RESTLanguageImageCollectionItemV1 self;

    /**
     * To set the name of a tab based on the object that initializes this editor we need to keep a track of the tab layout and
     * the index that this editor is assigned to. See http://stackoverflow.com/questions/10463782/adding-list
     * -sub-editors-to-tab-panel for an explanation. This is the reference to the tab panel
     */
    private final RESTLanguageImageCollectionV1Editor parentPanel;

    /**
     * This is a reference to the tab index
     */
    private final int parentIndex;

    private final Label filename = new Label();
    private final Label dimensions = new Label();
    private final Image imageDataBase64 = new Image();
    private final FileUploadExt upload = new FileUploadExt(false);
    private final PushButton uploadButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Upload());

    private final Label filenameLabel = new Label(PressGangCCMSUI.INSTANCE.ImageFilename());
    private final Label dimensionsLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDimensions());
    private final Label imageLabel = new Label(PressGangCCMSUI.INSTANCE.ImageSample());
    private final Label newFileLabel = new Label(PressGangCCMSUI.INSTANCE.UploadFile());

    @NotNull
    @Ignore
    public Label getFilename() {
        return filename;
    }

    @NotNull
    @Ignore
    public Image getImageDataBase64() {
        return imageDataBase64;
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

    public RESTLanguageImageV1Editor(final RESTLanguageImageCollectionV1Editor parentPanel, final int parentIndex) {
        this.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_TAB);

        this.parentPanel = parentPanel;
        this.parentIndex = parentIndex;

        upload.setEnabled(!parentPanel.isReadOnly());
        uploadButton.setEnabled(!parentPanel.isReadOnly());

        final HorizontalPanel uploadPanel = new HorizontalPanel();
        uploadPanel.add(this.upload);
        uploadPanel.add(this.uploadButton);

        int row = 0;
        this.setWidget(row, 0, this.newFileLabel);
        this.setWidget(row, 1, uploadPanel);

        ++row;
        this.setWidget(row, 0, this.filenameLabel);
        this.setWidget(row, 1, this.filename);

        ++row;
        this.setWidget(row, 0, this.dimensionsLabel);
        this.setWidget(row, 1, this.dimensions);

        ++row;
        this.setWidget(row, 0, this.imageLabel);
        this.setWidget(row, 1, this.imageDataBase64);

        this.filenameLabel.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL);
        this.filename.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT);
        this.imageLabel.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL);
        this.imageDataBase64.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE);

        row = 0;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_BUTTONS_CELL);

        ++row;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.ImageView.IMAGE_VIEW_DETAIL_CELL);

        ++row;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.ImageView.IMAGE_VIEW_DETAIL_CELL);

        ++row;
        this.getCellFormatter().addStyleName(row, 0, CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL_CELL);
        this.getCellFormatter().addStyleName(row, 1, CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE_CELL);
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTLanguageImageCollectionItemV1> delegate) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void onPropertyChange(final String... paths) {

    }

    @Override
    public void setValue(@NotNull final RESTLanguageImageCollectionItemV1 value) {
        this.self = value;

        if (value.getItem().getFilename() != null) {
            this.filename.setText(value.getItem().getFilename());
        }

        if (value.getItem().getImageDataBase64() != null) {
            @NotNull final String base64 = GWTUtilities.getStringUTF8(value.getItem().getImageDataBase64());
            imageDataBase64.addLoadHandler(new LoadHandler() {

                @Override
                public void onLoad(final LoadEvent event) {
                    dimensions.setText(imageDataBase64.getWidth() + "x" + imageDataBase64.getHeight());
                }
            });

            final String mimeType = ComponentLanguageImageV1.getMimeType(value.getItem());
            imageDataBase64.setUrl("data:" + mimeType + ";base64," + base64);
        }

        if (value.getItem().getLocale() != null) {
            this.parentPanel.setTabText(this.parentIndex, value.getItem().getLocale());
        }
    }
}
