package org.jboss.pressgang.ccms.ui.client.local.ui.editor.propertytag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor used to bind the property tag's details to ui elements.
 */
public final class RESTPropertyTagV1DetailsEditor extends Grid implements Editor<RESTPropertyTagV1> {
    /**
     * The number of rows that make up the grid that this editor extends.
     */
    private static final int ROWS = 6;
    /**
     * The number of columns that make up the grid that this editor extends.
     */
    private static final int COLS = 2;

    private final SimpleIntegerBox id = new SimpleIntegerBox();
    private final TextBox name = new TextBox();
    private final TextArea description = new TextArea();
    private final TextBox regex = new TextBox();
    private final CheckBox unique = new CheckBox();
    private final CheckBox canBeNull = new CheckBox();

    @NotNull
    public SimpleIntegerBox idEditor() {
        return id;
    }

    @NotNull
    public TextBox nameEditor() {
        return name;
    }

    @NotNull
    public TextBox regexEditor() {
        return regex;
    }

    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    @NotNull
    public CheckBox isUniqueEditor() {
        return unique;
    }

    @NotNull
    public CheckBox canBeNullEditor() {
        return canBeNull;
    }

    /**
     * Builds the UI.
     * @param readOnly true if the ui elements should be read only, and false otherwise
     */
    public RESTPropertyTagV1DetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_PANEL);
        id.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_ID_FIELD);
        name.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_NAME_FIELD);
        description.addStyleName(CSSConstants.StringConstantView.STRING_CONSTANT_VIEW_VALUE_FIELD);

        id.setReadOnly(true);
        name.setReadOnly(readOnly);
        description.setReadOnly(readOnly);
        regex.setReadOnly(readOnly);
        unique.setEnabled(!readOnly);
        canBeNull.setEnabled(!readOnly);

        final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyID());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        final Label regexLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyRegex());
        this.setWidget(2, 0, regexLabel);
        this.setWidget(2, 1, regex);

        final Label uniqueLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyUnique());
        this.setWidget(3, 0, uniqueLabel);
        this.setWidget(3, 1, unique);

        final Label nullLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyCanBeNull());
        this.setWidget(4, 0, nullLabel);
        this.setWidget(4, 1, canBeNull);

        final Label valueLabel = new Label(PressGangCCMSUI.INSTANCE.ExtendedPropertyDescription());
        this.setWidget(5, 0, valueLabel);
        this.setWidget(5, 1, description);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TOPIC_VIEW_DETAIL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.TOPIC_VIEW_DESCRIPTION_DETAIL);
    }

}
