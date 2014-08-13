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

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor to bind the details in a RESTTranslatedTopicV1 to UI elements. This is similar but not the same
 * as RESTTopicV1BasicDetailsEditor, which has some slightly different properties being displayed.
 */
public final class RESTTranslatedTopicV1BasicDetailsEditor extends Grid implements LeafValueEditor<RESTTranslatedTopicV1> {

    /**
     * The number of rows displayed by this editor.
     */
    private static final int ROWS = 5;
    /**
     * The number of columns displayed by this editor.
     */
    private static final int COLS = 2;

    /**
     * The integer label that displays the translated topic id
     */
    private final SimpleIntegerLabel id = new SimpleIntegerLabel();
    /**
     * The integer label that display the topic revision.
     */
    private final SimpleIntegerLabel topicRevision = new SimpleIntegerLabel();
    /**
     * The integer label that displays the topic id
     */
    private final SimpleIntegerLabel topicId = new SimpleIntegerLabel();
    /**
     * The label that displays the translation locale
     */
    private final Label locale = new Label();
    /**
     * The label that displays the doc type
     */
    private final Label xmlDoctype = new Label();

    /**
     * The property used by the editor framework to bind the locale to the ui element.
     *
     * @return The locale ui element
     */
    @NotNull
    public Label getLocale() {
        return locale;
    }

    /**
     * The property used by the editor framework to bind the topic id to the ui element.
     *
     * @return The topic id ui element
     */
    @NotNull
    public SimpleIntegerLabel getTopicId() {
        return topicId;
    }

    /**
     * The property used by the editor framework to bind the topic revision to the ui element.
     *
     * @return The revision ui element
     */
    @NotNull
    public SimpleIntegerLabel getTopicRevision() {
        return topicRevision;
    }

    /**
     * The property used by the editor framework to bind the translated topic id to the ui element.
     *
     * @return The translated topic id ui element
     */
    @NotNull
    public SimpleIntegerLabel getId() {
        return id;
    }

    @NotNull
    public Label getXmlDoctype() {
        return xmlDoctype;
    }

    /**
     * @param readOnly true if the ui elements presented by this editor should be readonly
     */
    public RESTTranslatedTopicV1BasicDetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.TranslatedTopicView.TRANSLATED_TOPIC_VIEW_PANEL);

        int row = 0;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TranslatedTopicID()));
        this.setWidget(row, 1, id);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
        this.setWidget(row, 1, topicId);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
        this.setWidget(row, 1, topicRevision);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
        this.setWidget(row, 1, locale);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicFormat()));
        this.setWidget(row, 1, xmlDoctype);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL_CELL);
        }
    }

    @Override
    public void setValue(final RESTTranslatedTopicV1 value) {
        id.setValue(value.getId());
        topicId.setValue(value.getTopicId());
        topicRevision.setValue(value.getTopicRevision());
        locale.setText(value.getLocale().getValue());
        xmlDoctype.setText(value.getXmlFormat().getCommonName());
    }

    @Override
    public RESTTranslatedTopicV1 getValue() {
        // this is a read only editor
        return null;
    }
}
