package org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertycategory;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor used to bind the property category's details to ui elements.
 */
public final class RESTPropertyCategoryV1DetailsEditor extends Grid implements Editor<RESTPropertyCategoryV1> {
    /**
     * The number of rows that make up the grid that this editor extends.
     */
    private static final int ROWS = 3;
    /**
     * The number of columns that make up the grid that this editor extends.
     */
    private static final int COLS = 2;

    private final SimpleIntegerBox id = new SimpleIntegerBox();
    private final TextBox name = new TextBox();
    private final TextArea description = new TextArea();

    @NotNull
    public SimpleIntegerBox idEditor() {
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


    /**
     * Builds the UI.
     * @param readOnly true if the ui elements should be read only, and false otherwise
     */
    public RESTPropertyCategoryV1DetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.PropertyCategoryView.PROPERTY_CATEGORY_VIEW_PANEL);
        id.addStyleName(CSSConstants.PropertyCategoryView.PROPERTY_CATEGORY_VIEW_ID_FIELD);
        name.addStyleName(CSSConstants.PropertyCategoryView.PROPERTY_CATEGORY_VIEW_NAME_FIELD);
        description.addStyleName(CSSConstants.PropertyCategoryView.PROPERTY_CATEGORY_VIEW_DESCRIPTION_FIELD);

        id.setReadOnly(true);
        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        @NotNull final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyID());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        @NotNull final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        @NotNull final Label valueLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyDescription());
        this.setWidget(2, 0, valueLabel);
        this.setWidget(2, 1, description);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_DETAIL);
    }

}
