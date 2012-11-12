package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;

public class RESTTopicV1BasicDetailsEditor extends Grid implements Editor<RESTTopicV1> {
    private final SimpleIntegerBox id = new SimpleIntegerBox();
    private final SimpleIntegerBox revision = new SimpleIntegerBox();
    private final ValueListBox<String> locale = new ValueListBox<String>(new Renderer<String>() {

        @Override
        public String render(final String object) {
            return object;
        }

        @Override
        public void render(final String object, final Appendable appendable) throws IOException {
        }
    });
    private final TextBox title = new TextBox();
    private final TextArea description = new TextArea();
    private final DateBox created = new DateBox();
    private final DateBox lastModified = new DateBox();

    public DateBox lastModifiedEditor() {
        return lastModified;
    }

    public DateBox createdEditor() {
        return created;
    }

    public TextArea descriptionEditor() {
        return description;
    }

    public TextBox titleEditor() {
        return title;
    }

    public ValueListBox<String> localeEditor() {
        return locale;
    }

    public SimpleIntegerBox revisionEditor() {
        return revision;
    }

    public SimpleIntegerBox idEditor() {
        return id;
    }

    public RESTTopicV1BasicDetailsEditor(final boolean readOnly, final List<String> locales) {
        super(7, 2);

        this.addStyleName(CSSConstants.TOPIC_VIEW_PANEL);

        for (int i = 0; i < 5; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < 4; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TOPIC_VIEW_DETAIL);
        }

        title.setReadOnly(readOnly);
        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue("");
        locale.setAcceptableValues(locales == null ? new ArrayList<String>() : locales);
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
        
        this.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.TopicCreated()));
        this.setWidget(1, 1, created);
        
        this.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLastModified()));
        this.setWidget(2, 1, lastModified);

        this.setWidget(3, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
        this.setWidget(3, 1, revision);

        this.setWidget(4, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
        this.setWidget(4, 1, locale);

        this.setWidget(5, 0, new Label(PressGangCCMSUI.INSTANCE.TopicTitle()));
        this.setWidget(5, 1, title);

        this.setWidget(6, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
        this.getCellFormatter().addStyleName(6, 1, CSSConstants.TOPIC_VIEW_DESCRIPTION_DETAIL);
        this.setWidget(6, 1, description);
    }
}
