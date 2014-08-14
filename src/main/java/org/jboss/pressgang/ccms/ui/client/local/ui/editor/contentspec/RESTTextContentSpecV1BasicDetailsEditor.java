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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec;

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
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RESTTextContentSpecV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTextContentSpecV1> {

    private static final int ROWS = 6;
    private static final int COLS = 2;

    private RESTTextContentSpecV1 value;
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
    private final DateLabel lastModified = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));

    private final Label restContentSpecDetailsLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecDetailsRESTEndpoint());
    private final Label restContentSpecTextLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecTextRESTEndpoint());
    private final Anchor restContentSpecDetails = new Anchor();
    private final Anchor restContentSpecText = new Anchor();

    @NotNull
    public DateLabel lastModifiedEditor() {
        return lastModified;
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

    public RESTTextContentSpecV1BasicDetailsEditor(final boolean readOnly, @Nullable final List<RESTLocaleV1> locales) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_PANEL);

        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(locale.getElement(), "disabled", readOnly);
        /* http://stackoverflow.com/a/11176707/157605 */
        locale.setValue(locales == null || locales.isEmpty() ? null : locales.get(0));
        locale.setAcceptableValues(locales == null ? new ArrayList<RESTLocaleV1>() : locales);

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

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                final String detailsURL = serverDetails.getRestEndpoint() + "/1/contentspec/get/json/" + value.getId() + "/r/" +
                        value.getRevision();
                final String textURL = serverDetails.getRestEndpoint() + "/1/contentspec/get/text/" + value.getId() + "/r/" + value.getRevision();

                restContentSpecDetails.setHref(detailsURL);
                restContentSpecText.setHref(textURL);

                restContentSpecDetails.setText(detailsURL);
                restContentSpecText.setText(textURL);
            }
        });
    }


    @Override
    public RESTTextContentSpecV1 getValue() {
        value.setLocale(locale.getValue());
        return value;
    }
}
