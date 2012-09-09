package org.jboss.pressgangccms.client.local.ui.editor.tagview;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class RESTTagV1BasicDetailsEditor extends FlexTable implements Editor<RESTTagV1> {
    private final IntegerBox id = new IntegerBox();
    private final TextBox name = new TextBox();
    private final TextArea description = new TextArea();

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.TagID());
    private final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.TagName());
    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.TagDescription());

    public IntegerBox idEditor() {
        return id;
    }

    public TextBox nameEditor() {
        return name;
    }

    public TextArea descriptionEditor() {
        return description;
    }

    public RESTTagV1BasicDetailsEditor(final boolean readOnly) {
        id.setReadOnly(readOnly);
        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);
        this.setWidget(2, 0, descriptionLabel);
        this.setWidget(2, 1, description);

        this.addStyleName(CSSConstants.TagView.TAGVIEWPANEL);

        idLabel.addStyleName(CSSConstants.TagView.TAGVIEWIDLABEL);
        id.addStyleName(CSSConstants.TagView.TAGVIEWIDTEXT);
        nameLabel.addStyleName(CSSConstants.TagView.TAGVIEWNAMELABEL);
        name.addStyleName(CSSConstants.TagView.TAGVIEWNAMETEXT);
        descriptionLabel.addStyleName(CSSConstants.TagView.TAGVIEWDESCRIPTIONLABEL);
        description.addStyleName(CSSConstants.TagView.TAGVIEWDESCRIPTIONTEXT);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TagView.TAGVIEWIDLABELCELL);
        this.getCellFormatter().addStyleName(0, 1, CSSConstants.TagView.TAGVIEWIDTEXTCELL);
        this.getCellFormatter().addStyleName(1, 0, CSSConstants.TagView.TAGVIEWNAMELABELCELL);
        this.getCellFormatter().addStyleName(1, 1, CSSConstants.TagView.TAGVIEWNAMETEXTCELL);
        this.getCellFormatter().addStyleName(2, 0, CSSConstants.TagView.TAGVIEWDESCRIPTIONLABELCELL);
        this.getCellFormatter().addStyleName(2, 1, CSSConstants.TagView.TAGVIEWDESCRIPTIONTEXTCELL);
    }
}
