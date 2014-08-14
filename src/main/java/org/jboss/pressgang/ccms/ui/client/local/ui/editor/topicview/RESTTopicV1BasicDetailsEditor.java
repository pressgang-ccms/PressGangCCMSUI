/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RESTTopicV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTopicV1> {

    private static final int ROWS = 11;
    private static final int COLS = 2;

    private RESTTopicV1 value;
    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    private final SimpleIntegerLabel revision = new SimpleIntegerLabel();
    private final ValueListBox<RESTLocaleV1> locale = new ValueListBox<RESTLocaleV1>(new Renderer<RESTLocaleV1>() {

        @Override
        public String render(final RESTLocaleV1 object) {
            if (object == null) {
                return "";
            } else {
                return object.getValue();
            }
        }

        @Override
        public void render(final RESTLocaleV1 object, final Appendable appendable) throws IOException {
        }
    });
    private final TextArea title = new TextArea();
    private final TextArea description = new TextArea();
    private final DateLabel created = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));
    private final DateLabel lastModified = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));

    private final Anchor restTopicDetails = new Anchor();
    private final Anchor restTopicXML = new Anchor();
    private final Label restTopicWebDav = new Label();

    private final ListBox xmlFormat = new ListBox();

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
    public ValueListBox<RESTLocaleV1> localeEditor() {
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

    public RESTTopicV1BasicDetailsEditor() {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_PANEL);

        id.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_ID_FIELD);
        revision.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_REVISION_NUMBER_FIELD);
        title.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_TITLE_FIELD);
        xmlFormat.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_XMLDOCTYPE_FIELD);
        restTopicDetails.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DETAILS_ENDPOINT_FIELD);
        restTopicXML.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_XML_ENDPOINT_FIELD);
        restTopicWebDav.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_WEBDAV_ENDPOINT_FIELD);
        locale.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_LOCALE_FIELD);
        description.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DESCRIPTION_FIELD);

        title.getElement().setId(Constants.ElementIDs.TOPIC_TITLE_PROPERTIES_VIEW_FIELD_ID.getId());
        description.getElement().setId(Constants.ElementIDs.TOPIC_DESCRIPTION_PROPERTIES_VIEW_FIELD_ID.getId());
        locale.getElement().setId(Constants.ElementIDs.TOPIC_LOCALE_PROPERTIES_VIEW_FIELD_ID.getId());

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
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicFormat()));
        this.setWidget(row, 1, xmlFormat);

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

    public void initialize(final boolean readOnly, @Nullable final List<RESTLocaleV1> locales) {
        title.setReadOnly(readOnly);
        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);

        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue(locales == null || locales.isEmpty() ? null : locales.get(0));
        locale.setAcceptableValues(locales == null ? new ArrayList<RESTLocaleV1>() : locales);

        xmlFormat.setEnabled(!readOnly);
        xmlFormat.clear();
        for (final RESTXMLFormat docType : RESTXMLFormat.values()) {
            xmlFormat.addItem(docType.getCommonName(), docType.name());
        }

        description.setReadOnly(readOnly);
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

        if (value.getXmlFormat() != null) {
            for (int i = 0; i < xmlFormat.getItemCount(); ++i) {
                if (xmlFormat.getValue(i).equals(value.getXmlFormat().name()))  {
                    xmlFormat.setSelectedIndex(i);
                }
            }
        } else {
            // default to 4.5
            for (int i = 0; i < xmlFormat.getItemCount(); ++i) {
                if (xmlFormat.getValue(i).equals(RESTXMLFormat.DOCBOOK_45.name()))  {
                    xmlFormat.setSelectedIndex(i);
                }
            }
        }

        /* the id will be null for new topics */
        if (value.getId() != null) {
            ServerDetails.getSavedServer(new ServerDetailsCallback() {
                @Override
                public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                    final String detailsURL = serverDetails.getRestEndpoint() + "/1/topic/get/json/" + value.getId() + "/r/" + value.getRevision();
                    final String xmlURL = serverDetails.getRestEndpoint() + "/1/topic/get/xml/" + value.getId() + "/r/" + value.getRevision() + "/xml";

                    final String idString = value.getId().toString();
                    String webDAV = serverDetails.getRestUrl() + "/webdav/TOPICS/";
                    for (int i = 0; i < idString.length(); ++i) {
                        webDAV += idString.charAt(i) + "/";
                    }
                    webDAV += "TOPIC" + idString + "/" + idString + ".xml";
                    restTopicWebDav.setText(webDAV);

                    restTopicDetails.setHref(detailsURL);
                    restTopicDetails.setText(detailsURL);
                    restTopicXML.setHref(xmlURL);
                    restTopicXML.setText(xmlURL);
                }
            });
        }
    }

    @Override
    public RESTTopicV1 getValue() {
        value.setTitle(title.getValue());
        value.setLocale(locale.getValue());
        value.setDescription(description.getValue());
        if (xmlFormat.getSelectedIndex() != -1)
        {
            for (final RESTXMLFormat docType : RESTXMLFormat.values())
            {
                if (docType.name().equals(xmlFormat.getValue(xmlFormat.getSelectedIndex())))
                {
                    value.setXmlFormat(docType);
                    break;
                }
            }
        }
        return value;
    }

    @NotNull
    public Anchor getRestTopicDetails() {
        return restTopicDetails;
    }

    @NotNull
    public Anchor getRestTopicXML() {
        return restTopicXML;
    }

    @NotNull
    public Label getRestTopicWebDav() {
        return restTopicWebDav;
    }

    @NotNull
    public ListBox getXmlDoctypeEditor() {
        return xmlFormat;
    }
}
