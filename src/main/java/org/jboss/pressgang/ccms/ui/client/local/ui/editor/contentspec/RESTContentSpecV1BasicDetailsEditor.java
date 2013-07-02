package org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RESTContentSpecV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTContentSpecV1> {

    private static final int ROWS = 6;
    private static final int COLS = 2;

    private RESTContentSpecV1 value;
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
    private final DateBox lastModified = new DateBox();

    private final Anchor restTopicDetailsLabel = new Anchor(PressGangCCMSUI.INSTANCE.ContentSpecDetailsRESTEndpoint());
    private final Anchor restTopicXMLLabel = new Anchor(PressGangCCMSUI.INSTANCE.ContentSpecTextRESTEndpoint());
    private final TextBox restTopicDetails = new TextBox();
    private final TextBox restTopicXML = new TextBox();

    @NotNull
    public DateBox lastModifiedEditor() {
        return lastModified;
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

    public RESTContentSpecV1BasicDetailsEditor(final boolean readOnly, @Nullable final List<String> locales) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_PANEL);

        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue("");
        locale.setAcceptableValues(locales == null ? new ArrayList<String>() : locales);

        id.setReadOnly(true);
        revision.setReadOnly(true);
        lastModified.setEnabled(false);
        restTopicDetails.setReadOnly(true);
        restTopicXML.setReadOnly(true);
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", true);

        id.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_ID_FIELD);
        revision.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_REVISION_NUMBER_FIELD);
        restTopicDetails.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_DETAILS_ENDPOINT_FIELD);
        restTopicXML.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_XML_ENDPOINT_FIELD);
        locale.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_LOCALE_FIELD);

        int row = 0;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.ContentSpecID()));
        this.setWidget(row, 1, id);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.ContentSpecRevision()));
        this.setWidget(row, 1, revision);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.ContentSpecLastModified()));
        this.setWidget(row, 1, lastModified);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.ContentSpecLocale()));
        this.setWidget(row, 1, locale);
        ++row;
        this.setWidget(row, 0, restTopicDetailsLabel);
        this.setWidget(row, 1, restTopicDetails);

        ++row;
        this.setWidget(row, 0, restTopicXMLLabel);
        this.setWidget(row, 1, restTopicXML);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL);
        }
    }

    @Override
    public void setValue(@NotNull final RESTContentSpecV1 value) {
        this.value = value;

        id.setValue(value.getId());
        revision.setValue(value.getRevision());
        locale.setValue(value.getLocale());
        lastModified.setValue(value.getLastModified());

        final String detailsURL = Constants.REST_SERVER + "/1/contentspec/get/json/" + value.getId() + "/r/" + value.getRevision();
        final String textURL = Constants.REST_SERVER + "/1/contentspec/get/text/" + value.getId() + "/r/" + value.getRevision();

        restTopicDetailsLabel.setHref(detailsURL);
        restTopicXMLLabel.setHref(textURL);

        restTopicDetails.setValue(detailsURL);
        restTopicXML.setValue(textURL);
    }

    @Override
    public RESTContentSpecV1 getValue() {
        value.setLocale(locale.getValue());
        return value;
    }
}
