package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RESTTopicV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTopicV1> {

    private static final int ROWS = 10;
    private static final int COLS = 2;

    private RESTTopicV1 value;
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
    private final TextArea title = new TextArea();
    private final TextArea description = new TextArea();
    private final DateBox created = new DateBox();
    private final DateBox lastModified = new DateBox();

    private final Anchor restTopicDetailsLabel = new Anchor(PressGangCCMSUI.INSTANCE.TopicDetailsRESTEndpoint());
    private final Anchor restTopicXMLLabel = new Anchor(PressGangCCMSUI.INSTANCE.TopicXMLRESTEndpoint());
    private final TextBox restTopicDetails = new TextBox();
    private final TextBox restTopicXML = new TextBox();
    private final TextBox restTopicWebDav = new TextBox();

    @NotNull
    public DateBox lastModifiedEditor() {
        return lastModified;
    }

    @NotNull
    public DateBox createdEditor() {
        return created;
    }

    @NotNull
    public TextArea descriptionEditor() {
        return description;
    }

    @NotNull
    public TextArea titleEditor() {
        return title;
    }

    @NotNull
    public ValueListBox<String> localeEditor() {
        return locale;
    }

    @NotNull
    public SimpleIntegerBox revisionEditor() {
        return revision;
    }

    @NotNull
    public SimpleIntegerBox idEditor() {
        return id;
    }

    public RESTTopicV1BasicDetailsEditor(final boolean readOnly, @Nullable final List<String> locales) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_PANEL);

        title.setReadOnly(readOnly);
        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue("");
        locale.setAcceptableValues(locales == null ? new ArrayList<String>() : locales);
        description.setReadOnly(readOnly);

        id.setReadOnly(true);
        revision.setReadOnly(true);
        created.setEnabled(false);
        lastModified.setEnabled(false);
        restTopicDetails.setReadOnly(true);
        restTopicXML.setReadOnly(true);
        restTopicWebDav.setReadOnly(true);

        id.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_ID_FIELD);
        revision.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_REVISION_NUMBER_FIELD);
        title.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_TITLE_FIELD);
        restTopicDetails.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DETAILS_ENDPOINT_FIELD);
        restTopicXML.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_XML_ENDPOINT_FIELD);
        restTopicWebDav.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_WEBDAV_ENDPOINT_FIELD);
        locale.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_LOCALE_FIELD);
        description.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_FIELD);

        int row = 0;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
        this.setWidget(row, 1, id);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
        this.setWidget(row, 1, revision);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicCreated()));
        this.setWidget(row, 1, created);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLastModified()));
        this.setWidget(row, 1, lastModified);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
        this.setWidget(row, 1, locale);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicTitle()));
        this.setWidget(row, 1, title);

        ++row;
        this.setWidget(row, 0, restTopicDetailsLabel);
        this.setWidget(row, 1, restTopicDetails);

        ++row;
        this.setWidget(row, 0, restTopicXMLLabel);
        this.setWidget(row, 1, restTopicXML);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicWebDAV()));
        this.setWidget(row, 1, restTopicWebDav);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
        this.setWidget(row, 1, description);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_DETAIL);
    }

    @Override
    public void setValue(@NotNull final RESTTopicV1 value) {
        this.value = value;

        id.setValue(value.getId());
        revision.setValue(value.getRevision());
        created.setValue(value.getCreated());
        title.setValue(value.getTitle());
        description.setValue(value.getDescription());
        locale.setValue(value.getLocale());
        lastModified.setValue(value.getLastModified());

        /* the id will be null for new topics */
        if (value.getId() != null)  {
            final String detailsURL = Constants.REST_SERVER + "/1/topic/get/json/" + value.getId() + "/r/" + value.getRevision();
            final String xmlURL = Constants.REST_SERVER + "/1/topic/get/xml/" + value.getId() + "/r/" + value.getRevision() + "/xml";

            final String idString = value.getId().toString();
            String webDAV = Constants.BASE_URL + "/webdav/TOPICS/";
            for (int i = 0; i < idString.length(); ++i) {
                webDAV += idString.charAt(i) + "/";
            }
            webDAV += "TOPIC" + idString + "/" + idString + ".xml";

            restTopicDetailsLabel.setHref(detailsURL);
            restTopicXMLLabel.setHref(xmlURL);

            restTopicDetails.setValue(detailsURL);
            restTopicXML.setValue(xmlURL);

            restTopicWebDav.setValue(webDAV);
        }
    }

    @Override
    public RESTTopicV1 getValue() {
        value.setTitle(title.getValue());
        value.setLocale(locale.getValue());
        value.setDescription(description.getValue());
        return value;
    }
}
