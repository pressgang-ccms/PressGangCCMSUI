package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public final class RESTImageV1Editor extends DockPanel implements ValueAwareEditor<RESTImageV1> {
    private RESTImageV1 value;

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.ImageID());

    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    /**
     * A text area to represent the description field.
     */
    private final TextArea description = new TextArea();

    private final Label docbookFileName = new Label();

    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());

    private final Label docbookFileNameLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookFilename());

    private final FlexTable imageDetails = new FlexTable();

    private final Label templateLabel =  new Label(PressGangCCMSUI.INSTANCE.DocbookImageTemplates());

    private final TextArea xmlTemplate = new TextArea();

    private final TextArea inlineXmlTemplate = new TextArea();

    private final TextArea bareXmlTemplate = new TextArea();

    private final TabLayoutPanel tabPanel = new TabLayoutPanel(Constants.TAB_PANEL_HEIGHT, Style.Unit.EM);

    /**
     * The editor representing a collection of language image editors.
     */
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

        xmlTemplate.setReadOnly(true);
        inlineXmlTemplate.setReadOnly(true);
        bareXmlTemplate.setReadOnly(true);

        imageDetails.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DETAILS_TABLE);
        descriptionLabel.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_TEXT);
        xmlTemplate.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);
        inlineXmlTemplate.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);
        bareXmlTemplate.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_TEMPLATE_TEXT);

        imageDetails.setWidget(0, 0, idLabel);
        imageDetails.setWidget(0, 1, id);
        imageDetails.setWidget(1, 0, descriptionLabel);
        imageDetails.setWidget(1, 1, description);

        imageDetails.setWidget(2, 0, templateLabel);
        imageDetails.setWidget(2, 1, tabPanel);

        tabPanel.add(xmlTemplate, PressGangCCMSUI.INSTANCE.DocbookImageTemplate());
        tabPanel.add(bareXmlTemplate, PressGangCCMSUI.INSTANCE.DocbookBareImageTemplate());
        tabPanel.add(inlineXmlTemplate, PressGangCCMSUI.INSTANCE.DocbookInlineImageTemplate());

        imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.ImageView.IMAGE_VIEW_DETAIL_CELL);
        imageDetails.getCellFormatter().addStyleName(1, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(1, 1, CSSConstants.ImageView.IMAGE_VIEW_DESCRIPTION_CELL);
        imageDetails.getCellFormatter().addStyleName(2, 0, CSSConstants.ImageView.IMAGE_VIEW_LABEL_CELL);


        this.add(imageDetails, DockPanel.NORTH);
        this.add(languageImages_OTM, DockPanel.CENTER);
    }

    @Override
    public void setDelegate(final EditorDelegate<RESTImageV1> delegate) {

    }

    @Override
    public void flush() {
        value.setDescription(this.description.getText());
    }

    @Override
    public void onPropertyChange(final String... paths) {


    }

    @Override
    public void setValue(final RESTImageV1 value) {
        this.value = value;
        this.id.setValue(value.getId());
        this.docbookFileName.setText(ComponentImageV1.getDocbookFileName(value));
        this.xmlTemplate.setText(ComponentImageV1.getXMLTemplate(value));

        this.inlineXmlTemplate.setText(ComponentImageV1.getInlineXMLTemplate(value));
        this.bareXmlTemplate.setText(ComponentImageV1.getBareXMLTemplate(value));
    }

}
