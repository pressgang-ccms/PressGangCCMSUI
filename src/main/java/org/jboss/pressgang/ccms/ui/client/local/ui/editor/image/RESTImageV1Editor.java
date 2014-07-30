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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public final class RESTImageV1Editor extends DockPanel implements ValueAwareEditor<RESTImageV1> {

    private boolean readOnly = false;

    private RESTImageV1 value;

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.ImageID());

    private final SimpleIntegerLabel id = new SimpleIntegerLabel();

    private final TextArea description = new TextArea();

    private final Label docbookFileName = new Label();

    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());

    private final Label docbookFileNameLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookFilename());

    private final FlexTable imageDetails = new FlexTable();

    private final Label templateLabel =  new Label(PressGangCCMSUI.INSTANCE.DocbookImageTemplates());

    private final Label copyToClipboard = new Label(PressGangCCMSUI.INSTANCE.CtrlCToCopy());

    private final TextArea xmlTemplate = new TextArea();

    private final TextArea inlineXmlTemplate = new TextArea();

    private final TextArea bareXmlTemplate = new TextArea();

    private final TabLayoutPanel imageTemplateTable = new TabLayoutPanel(Constants.TAB_PANEL_HEIGHT, Constants.TAB_PANEL_HEIGHT_FORMAT);

    private final RESTLanguageImageCollectionV1Editor languageImages_OTM = new RESTLanguageImageCollectionV1Editor();

    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    @NotNull
    public RESTLanguageImageCollectionV1Editor languageImages_OTMEditor() {
        return languageImages_OTM;
    }

    public RESTImageV1Editor() {
        this.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_PARENT_DOCK_PANEL);

        getXmlTemplate().setReadOnly(true);
        getInlineXmlTemplate().setReadOnly(true);
        getBareXmlTemplate().setReadOnly(true);

        imageTemplateTable.addStyleName(CSSConstants.ImageView.IMAGE_TEMPLATES_TAB_PANEL);
        imageDetails.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DETAILS_TABLE);
        descriptionLabel.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_TEXT);
        getXmlTemplate().addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);
        getInlineXmlTemplate().addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);
        getBareXmlTemplate().addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);
        id.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_ID_TEXT);

        imageDetails.setWidget(0, 0, idLabel);
        imageDetails.setWidget(0, 1, id);
        imageDetails.setWidget(1, 0, descriptionLabel);
        imageDetails.setWidget(1, 1, description);

        final VerticalPanel templateLabels = new VerticalPanel();
        templateLabels.add(templateLabel);
        templateLabels.add(copyToClipboard);

        imageDetails.setWidget(2, 0, templateLabels);
        imageDetails.setWidget(2, 1, imageTemplateTable);

        imageTemplateTable.add(getXmlTemplate(), PressGangCCMSUI.INSTANCE.DocbookImageTemplate());
        imageTemplateTable.add(getBareXmlTemplate(), PressGangCCMSUI.INSTANCE.DocbookBareImageTemplate());
        imageTemplateTable.add(getInlineXmlTemplate(), PressGangCCMSUI.INSTANCE.DocbookInlineImageTemplate());

        imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.ImageView.IMAGE_VIEW_DETAIL_CELL);
        imageDetails.getCellFormatter().addStyleName(1, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(1, 1, CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_CELL);
        imageDetails.getCellFormatter().addStyleName(2, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);


        this.add(imageDetails, DockPanel.NORTH);
        this.add(languageImages_OTM, DockPanel.CENTER);

        // we need to sink the click handler to respond to clicks (http://stackoverflow.com/a/4303407/157605)
        imageTemplateTable.sinkEvents(Event.ONCLICK);

    }

    @Override
    public void setDelegate(@NotNull final EditorDelegate<RESTImageV1> delegate) {

    }

    @Override
    public void flush() {
        value.setDescription(this.description.getText());
    }

    @Override
    public void onPropertyChange(@NotNull final String... paths) {

    }

    @Override
    public void setValue(@NotNull final RESTImageV1 value) {
        this.value = value;
        this.id.setValue(value.getId());
        this.docbookFileName.setText(ComponentImageV1.getDocbookFileName(value));
        this.getXmlTemplate().setText(ComponentImageV1.getXMLTemplate(value));

        this.getInlineXmlTemplate().setText(ComponentImageV1.getInlineXMLTemplate(value));
        this.getBareXmlTemplate().setText(ComponentImageV1.getBareXMLTemplate(value));
    }

    @Ignore
    public SimpleIntegerLabel getId() {
        return id;
    }

    /**
     * A text area to represent the description field.
     */
    @Ignore
    public TextArea getDescription() {
        return description;
    }

    @Ignore
    public Label getDocbookFileName() {
        return docbookFileName;
    }

    @Ignore
    public FlexTable getImageDetails() {
        return imageDetails;
    }

    @Ignore
    public TabLayoutPanel getImageTemplateTable() {
        return imageTemplateTable;
    }

    /**
     * The editor representing a collection of language image editors.
     */
    @Ignore
    public RESTLanguageImageCollectionV1Editor getLanguageImages_OTM() {
        return languageImages_OTM;
    }

    @Ignore
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
        languageImages_OTM.setReadOnly(readOnly);
    }

    @Ignore
    public TextArea getXmlTemplate() {
        return xmlTemplate;
    }

    @Ignore
    public TextArea getInlineXmlTemplate() {
        return inlineXmlTemplate;
    }

    @Ignore
    public TextArea getBareXmlTemplate() {
        return bareXmlTemplate;
    }
}
