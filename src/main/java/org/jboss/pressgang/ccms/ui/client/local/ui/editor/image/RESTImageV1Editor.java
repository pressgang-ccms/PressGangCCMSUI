package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentImageV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public final class RESTImageV1Editor extends DockPanel implements ValueAwareEditor<RESTImageV1> {
    private RESTImageV1 value;

    /**
     * A text area to represent the description field.
     */
    private final TextArea description = new TextArea();

    private final TextBox docbookFileName = new TextBox();

    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());

    private final Label docbookFileNameLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookFilename());

    private final FlexTable imageDetails = new FlexTable();

    private final TextArea xmlTemplate = new TextArea();

    private final Label xmlTemplateLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookImageTemplate());

    /**
     * The editor representing a collection of language image editors.
     */
    private final RESTLanguageImageCollectionV1Editor languageImages_OTM = new RESTLanguageImageCollectionV1Editor();

    public TextArea descriptionEditor() {
        return description;
    }

    public RESTLanguageImageCollectionV1Editor languageImages_OTMEditor() {
        return languageImages_OTM;
    }

    public RESTImageV1Editor() {
        this.addStyleName(CSSConstants.IMAGE_VIEW_PARENT_DOCK_PANEL);

        docbookFileName.setReadOnly(true);
        xmlTemplate.setReadOnly(true);

        imageDetails.addStyleName(CSSConstants.IMAGE_VIEW_DETAILS_TABLE);
        descriptionLabel.addStyleName(CSSConstants.IMAGE_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.IMAGE_VIEW_DESCRIPTION_TEXT);
        xmlTemplate.addStyleName(CSSConstants.IMAGE_VIEW_TEMPLATE_TEXT);

        imageDetails.setWidget(0, 0, descriptionLabel);
        imageDetails.setWidget(0, 1, description);
        imageDetails.setWidget(1, 0, xmlTemplateLabel);
        imageDetails.setWidget(1, 1, xmlTemplate);
        imageDetails.setWidget(2, 0, docbookFileNameLabel);
        imageDetails.setWidget(2, 1, docbookFileName);

        imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGE_VIEW_DESCRIPTION_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGE_VIEW_DESCRIPTION_TEXT_CELL);
        imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGE_VIEW_TEMPLATE_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGE_VIEW_TEMPLATE_TEXT_CELL);
        imageDetails.getCellFormatter().addStyleName(2, 0, CSSConstants.IMAGE_VIEW_DOCBOOK_FILENAME_LABEL_CELL);
        imageDetails.getCellFormatter().addStyleName(2, 1, CSSConstants.IMAGE_VIEW_DOCBOOK_FILENAME_TEXT_CELL);

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
        // TODO Auto-generated method stub

    }

    @Override
    public void setValue(final RESTImageV1 value) {
        this.value = value;
        this.docbookFileName.setText(ComponentImageV1.getDocbookFileName(value));
        this.xmlTemplate.setText(ComponentImageV1.getXMLTemplate(value));
    }

}
