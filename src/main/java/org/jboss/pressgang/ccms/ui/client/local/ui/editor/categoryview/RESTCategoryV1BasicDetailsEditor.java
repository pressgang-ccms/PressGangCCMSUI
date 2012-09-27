package org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class RESTCategoryV1BasicDetailsEditor extends FlexTable implements Editor<RESTCategoryV1> {
    private final IntegerBox id = new IntegerBox();
    private final TextBox name = new TextBox();
    private final TextArea description = new TextArea();

    private final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryID());
    private final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryName());
    private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.CategoryDescription());

    public IntegerBox idEditor() {
        return id;
    }

    public TextBox nameEditor() {
        return name;
    }

    public TextArea descriptionEditor() {
        return description;
    }

    public RESTCategoryV1BasicDetailsEditor(final boolean readOnly) {
        id.setReadOnly(true);
        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);
        this.setWidget(2, 0, descriptionLabel);
        this.setWidget(2, 1, description);

        this.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_PANEL);

        idLabel.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_ID_LABEL);
        id.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_ID_TEXT);
        nameLabel.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_NAME_LABEL);
        name.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_NAME_TEXT);
        descriptionLabel.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_DESCRIPTION_LABEL);
        description.addStyleName(CSSConstants.CategoryView.CATEGORY_VIEW_DESCRIPTION_TEXT);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.CategoryView.CATEGORY_VIEW_ID_LABEL_CELL);
        this.getCellFormatter().addStyleName(0, 1, CSSConstants.CategoryView.CATEGORY_VIEW_ID_TEXT_CELL);
        this.getCellFormatter().addStyleName(1, 0, CSSConstants.CategoryView.CATEGORY_VIEW_NAME_LABEL_CELL);
        this.getCellFormatter().addStyleName(1, 1, CSSConstants.CategoryView.CATEGORY_VIEW_NAME_TEXT_CELL);
        this.getCellFormatter().addStyleName(2, 0, CSSConstants.CategoryView.CATEGORY_VIEW_DESCRIPTION_LABEL_CELL);
        this.getCellFormatter().addStyleName(2, 1, CSSConstants.CategoryView.CATEGORY_VIEW_DESCRIPTION_TEXT_CELL);
    }
}
