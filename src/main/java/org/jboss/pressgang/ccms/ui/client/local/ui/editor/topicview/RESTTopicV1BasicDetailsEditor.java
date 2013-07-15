package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RESTTopicV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTopicV1> {

    private static final int ROWS = 10;
    private static final int COLS = 2;

    private RESTTopicV1 value;
    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    private final SimpleIntegerLabel revision = new SimpleIntegerLabel();
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
    private final DateLabel created = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));
    private final DateLabel lastModified = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));

    private final Anchor restTopicDetails = new Anchor();
    private final Anchor restTopicXML = new Anchor();
    private final Label restTopicWebDav = new Label();

    @NotNull
    public DateLabel lastModifiedEditor() {
        return lastModified;
    }

    @NotNull
    public DateLabel createdEditor() {
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
    public SimpleIntegerLabel revisionEditor() {
        return revision;
    }

    @NotNull
    public SimpleIntegerLabel idEditor() {
        return id;
    }

    public RESTTopicV1BasicDetailsEditor(final boolean readOnly, @Nullable final List<String> locales) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_PANEL);

        title.setReadOnly(readOnly);
        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue(locales == null || locales.isEmpty() ? "" : locales.get(0));
        locale.setAcceptableValues(locales == null ? new ArrayList<String>() : locales);
        description.setReadOnly(readOnly);

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
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDetailsRESTEndpoint()));
        this.setWidget(row, 1, restTopicDetails);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicXMLRESTEndpoint()));
        this.setWidget(row, 1, restTopicXML);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicWebDAV()));
        this.setWidget(row, 1, restTopicWebDav);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
        this.setWidget(row, 1, description);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS - 1; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL_CELL);
        }
        this.getCellFormatter().addStyleName(ROWS - 1, 1, CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_CELL);
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
        if (value.getId() != null) {
            final String detailsURL = ServerDetails.getSavedServer().getRestEndpoint() + "/1/topic/get/json/" + value.getId() + "/r/" + value.getRevision();
            final String xmlURL = ServerDetails.getSavedServer().getRestEndpoint() + "/1/topic/get/xml/" + value.getId() + "/r/" + value.getRevision() + "/xml";

            final String idString = value.getId().toString();
            String webDAV = ServerDetails.getSavedServer().getRestUrl() + "/webdav/TOPICS/";
            for (int i = 0; i < idString.length(); ++i) {
                webDAV += idString.charAt(i) + "/";
            }
            webDAV += "TOPIC" + idString + "/" + idString + ".xml";

            restTopicDetails.setHref(detailsURL);
            restTopicDetails.setText(detailsURL);
            restTopicXML.setHref(xmlURL);
            restTopicXML.setText(xmlURL);

            restTopicWebDav.setText(webDAV);
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
