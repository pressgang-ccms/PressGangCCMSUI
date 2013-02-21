package org.jboss.pressgang.ccms.ui.client.local.ui.editor.stringconstant;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import java.util.List;

/**
 * An editor used to bind the string constant's details to ui elements
 */
public class RESTStringConstantV1DetailsEditor extends Grid implements Editor<RESTStringConstantV1> {
    private final SimpleIntegerBox id = new SimpleIntegerBox();
    private final TextBox name = new TextBox();
    private final TextArea value = new TextArea();

    public SimpleIntegerBox idEditor() {
        return id;
    }

    public TextBox nameEditor() {
        return name;
    }

    public TextArea valueEditor() {
        return value;
    }

    public RESTStringConstantV1DetailsEditor(final boolean readOnly) {
        super(2, 3);

        final Label idLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantId());
        this.setWidget(0, 0, idLabel);
        this.setWidget(0, 1, id);

        final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantName());
        this.setWidget(1, 0, nameLabel);
        this.setWidget(1, 1, name);

        final Label valueLabel = new Label(PressGangCCMSUI.INSTANCE.StringConstantValue());
        this.setWidget(2, 0, valueLabel);
        this.setWidget(2, 1, value);
    }

}
