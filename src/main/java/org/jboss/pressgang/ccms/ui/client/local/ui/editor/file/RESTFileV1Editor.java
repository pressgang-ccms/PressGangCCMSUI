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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.file;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentFileV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFileV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public final class RESTFileV1Editor extends DockPanel implements ValueAwareEditor<RESTFileV1> {
    private RESTFileV1 value;

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.FileID());

    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    /**
     * A text area to represent the description field.
     */
    private final TextArea description = new TextArea();

    private final TextBox fileName = new TextBox();

    private final TextBox filePath = new TextBox();

    private final Label fullFilePath = new Label();

    private final CheckBox explodeArchive = new CheckBox();

    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.FileDescription());

    private final Label fileNameLabel = new Label(PressGangCCMSUI.INSTANCE.FileName());

    private final Label filePathLabel = new Label(PressGangCCMSUI.INSTANCE.FilePath());

    private final Label fullFilePathLabel = new Label(PressGangCCMSUI.INSTANCE.FullFilePath());

    private final Label explodeArchiveLabel = new Label(PressGangCCMSUI.INSTANCE.ExplodeFileArchive());

    private final FlexTable fileDetails = new FlexTable();

    /**
     * The editor representing a collection of language image editors.
     */
    private final RESTLanguageFileCollectionV1Editor languageFiles_OTM = new RESTLanguageFileCollectionV1Editor();

    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    @NotNull
    public TextBox fileNameEditor() {
        return fileName;
    }

    @NotNull
    public TextBox filePathEditor() {
        return filePath;
    }

    @NotNull
    public RESTLanguageFileCollectionV1Editor languageFiles_OTMEditor() {
        return languageFiles_OTM;
    }

    public RESTFileV1Editor() {
        this.addStyleName(CSSConstants.FileView.FILE_VIEW_PARENT_DOCK_PANEL);

        // The panel used to hold the file path label and text box
        final HorizontalPanel filePathPanel = new HorizontalPanel();
        filePathPanel.setWidth("100%");
        filePathPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        filePathPanel.add(new Label("files/"));
        filePathPanel.add(filePath);
        filePathPanel.setCellWidth(filePath, "100%");

        explodeArchive.setEnabled(false);

        fileDetails.addStyleName(CSSConstants.FileView.FILE_VIEW_DETAILS_TABLE);
        descriptionLabel.addStyleName(CSSConstants.FileView.FILE_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.FileView.FILE_VIEW_DESCRIPTION_TEXT);
        fileNameLabel.addStyleName(CSSConstants.FileView.FILE_VIEW_FILENAME_LABEL);
        fileName.addStyleName(CSSConstants.FileView.FILE_VIEW_FILENAME_TEXT);
        filePathLabel.addStyleName(CSSConstants.FileView.FILE_VIEW_FILE_PATH_LABEL);
        filePath.addStyleName(CSSConstants.FileView.FILE_VIEW_FILE_PATH_TEXT);
        fullFilePath.addStyleName(CSSConstants.FileView.FILE_VIEW_FULL_FILE_PATH_LABEL);
        explodeArchiveLabel.setStyleName(CSSConstants.FileView.FILE_VIEW_EXPLODE_ARCHIVE_LABEL);

        fileDetails.setWidget(0, 0, idLabel);
        fileDetails.setWidget(0, 1, id);
        fileDetails.setWidget(1, 0, descriptionLabel);
        fileDetails.setWidget(1, 1, description);
        fileDetails.setWidget(2, 0, fileNameLabel);
        fileDetails.setWidget(2, 1, fileName);
        fileDetails.setWidget(3, 0, filePathLabel);
        fileDetails.setWidget(3, 1, filePathPanel);
        fileDetails.setWidget(4, 0, fullFilePathLabel);
        fileDetails.setWidget(4, 1, fullFilePath);
        fileDetails.setWidget(5, 0, explodeArchiveLabel);
        fileDetails.setWidget(5, 1, explodeArchive);

        fileDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        fileDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.FileView.FILE_VIEW_DETAIL_CELL);
        fileDetails.getCellFormatter().addStyleName(1, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        fileDetails.getCellFormatter().addStyleName(1, 1, CSSConstants.FileView.FILE_VIEW_DESCRIPTION_CELL);
        fileDetails.getCellFormatter().addStyleName(2, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        fileDetails.getCellFormatter().addStyleName(2, 1, CSSConstants.FileView.FILE_VIEW_DETAIL_CELL);
        fileDetails.getCellFormatter().addStyleName(3, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        fileDetails.getCellFormatter().addStyleName(3, 1, CSSConstants.FileView.FILE_VIEW_DETAIL_CELL);
        fileDetails.getCellFormatter().addStyleName(4, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);
        fileDetails.getCellFormatter().addStyleName(4, 1, CSSConstants.FileView.FILE_VIEW_DETAIL_CELL);
        fileDetails.getCellFormatter().addStyleName(5, 0, CSSConstants.FileView.FILE_VIEW_LABEL_CELL);

        add(fileDetails, DockPanel.NORTH);
        add(languageFiles_OTM, DockPanel.CENTER);
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTFileV1> delegate) {

    }

    @Override
    public void flush() {
        value.setDescription(description.getText());
        value.setFileName((fileName.getText()));
        value.setFilePath((filePath.getText()));
        if (explodeArchive.isEnabled()) {
            value.setExplodeArchive(explodeArchive.getValue());
        }
    }

    @Override
    public void onPropertyChange(final String... paths) {


    }

    @Override
    public void setValue(final RESTFileV1 value) {
        this.value = value;
        id.setValue(value.getId());
        fileName.setText(value.getFileName());
        filePath.setText(value.getFilePath());
        fullFilePath.setText(constructFullFilePath());
        if (ComponentFileV1.isArchive(value)) {
            explodeArchive.setEnabled(true);
            explodeArchive.setValue(value.getExplodeArchive());
        } else {
            explodeArchive.setEnabled(false);
        }
    }

    public void updateFullFilePath() {
        fullFilePath.setText(constructFullFilePath());
    }

    private String constructFullFilePath() {
        return "files/" + (value.getFilePath() == null ? "" : value.getFilePath()) + (value.getFileName() == null ? "" : value
                        .getFileName());
    }
}