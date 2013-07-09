package org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RESTTextContentSpecV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTextContentSpecV1> {

    private static final int ROWS = 6;
    private static final int COLS = 2;

    private RESTTextContentSpecV1 value;
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
    private final DateBox lastModified = new DateBox();

    private final Anchor restContentSpecDetailsLabel = new Anchor(PressGangCCMSUI.INSTANCE.ContentSpecDetailsRESTEndpoint());
    private final Anchor restContentSpecTextLabel = new Anchor(PressGangCCMSUI.INSTANCE.ContentSpecTextRESTEndpoint());
    private final TextBox restContentSpecDetails = new TextBox();
    private final TextBox restContentSpecText = new TextBox();

    @NotNull
    public DateBox lastModifiedEditor() {
        return lastModified;
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

    public RESTTextContentSpecV1BasicDetailsEditor(final boolean readOnly, @Nullable final List<String> locales) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_PANEL);

        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue("");
        locale.setAcceptableValues(locales == null ? new ArrayList<String>() : locales);

        lastModified.setEnabled(false);
        restContentSpecDetails.setReadOnly(true);
        restContentSpecText.setReadOnly(true);
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", true);

        id.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_ID_FIELD);
        revision.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_REVISION_NUMBER_FIELD);
        restContentSpecDetails.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_DETAILS_ENDPOINT_FIELD);
        restContentSpecText.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_TEXT_ENDPOINT_FIELD);
        locale.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_LOCALE_FIELD);

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
        this.setWidget(row, 0, restContentSpecDetailsLabel);
        this.setWidget(row, 1, restContentSpecDetails);

        ++row;
        this.setWidget(row, 0, restContentSpecTextLabel);
        this.setWidget(row, 1, restContentSpecText);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_DETAIL_CELL);
        }
    }

    @Override
    public void setValue(@NotNull final RESTTextContentSpecV1 value) {
        this.value = value;

        id.setValue(value.getId());
        revision.setValue(value.getRevision());
        locale.setValue(value.getLocale());
        lastModified.setValue(value.getLastModified());

        final String detailsURL = Constants.REST_SERVER + "/1/contentspec/get/json/" + value.getId() + "/r/" + value.getRevision();
        final String textURL = Constants.REST_SERVER + "/1/contentspec/get/text/" + value.getId() + "/r/" + value.getRevision();

        restContentSpecDetailsLabel.setHref(detailsURL);
        restContentSpecTextLabel.setHref(textURL);

        restContentSpecDetails.setValue(detailsURL);
        restContentSpecText.setValue(textURL);
    }

    @Override
    public RESTTextContentSpecV1 getValue() {
        value.setLocale(locale.getValue());
        return value;
    }
}
