package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class RESTTopicV1BasicDetailsEditor extends Grid implements Editor<RESTTopicV1> {
    private final IntegerBox id = new IntegerBox();
    private final IntegerBox revision = new IntegerBox();
    private final TextBox locale = new TextBox();
    private final TextBox title = new TextBox();
    private final TextArea description = new TextArea();

    public TextArea descriptionEditor() {
        return description;
    }

    public TextBox titleEditor() {
        return title;
    }

    public TextBox localeEditor() {
        return locale;
    }

    public IntegerBox revisionEditor() {
        return revision;
    }

    public IntegerBox idEditor() {
        return id;
    }

    public RESTTopicV1BasicDetailsEditor(final boolean readOnly) {
        super(5, 2);

        this.addStyleName(CSSConstants.TOPIC_VIEW_PANEL);

        for (int i = 0; i < 5; ++i)
        {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < 4; ++i)
        {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TOPIC_VIEW_DETAIL);
        }

        title.setReadOnly(readOnly);
        locale.setReadOnly(readOnly);
        description.setReadOnly(readOnly);

        id.setReadOnly(true);
        revision.setReadOnly(true);

        id.addStyleName(CSSConstants.TOPIC_VIEW_ID_FIELD);
        revision.addStyleName(CSSConstants.TOPIC_VIEW_REVISION_NUMBER_FIELD);
        title.addStyleName(CSSConstants.TOPIC_VIEW_TITLE_FIELD);
        locale.addStyleName(CSSConstants.TOPIC_VIEW_LOCALE_FIELD);
        description.addStyleName(CSSConstants.TOPIC_VIEW_DESCRIPTION_FIELD);

        this.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
        this.setWidget(0, 1, id);

        this.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
        this.setWidget(1, 1, revision);

        this.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
        this.setWidget(2, 1, locale);

        this.setWidget(3, 0, new Label(PressGangCCMSUI.INSTANCE.TopicTitle()));
        this.setWidget(3, 1, title);

        this.setWidget(4, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
        this.getCellFormatter().addStyleName(4, 1, CSSConstants.TOPIC_VIEW_DESCRIPTION_DETAIL);
        this.setWidget(4, 1, description);
    }
}
