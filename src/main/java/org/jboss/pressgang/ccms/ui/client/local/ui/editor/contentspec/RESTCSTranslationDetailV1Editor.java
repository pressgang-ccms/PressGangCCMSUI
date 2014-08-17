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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLocaleCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSTranslationDetailV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTLocaleV1Sort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RESTCSTranslationDetailV1Editor extends FlexTable implements LeafValueEditor<RESTCSTranslationDetailV1> {
    private final LinkedList<RESTLocaleV1> locales;
    private final Map<Integer, RESTLocaleV1> localesMap = new HashMap<Integer, RESTLocaleV1>();

    private RESTCSTranslationDetailV1 value;
    private RESTLocaleCollectionV1 localesValue;
    private final CheckBox enabled = new CheckBox(PressGangCCMSUI.INSTANCE.EnableTranslations());
    private final ValueListBox<RESTTranslationServerV1> server = new ValueListBox<RESTTranslationServerV1>(new Renderer<RESTTranslationServerV1>() {

        @Override
        public String render(final RESTTranslationServerV1 object) {
            if (object == null) {
                return "";
            } else {
                return object.getName();
            }
        }

        @Override
        public void render(final RESTTranslationServerV1 object, final Appendable appendable) throws IOException {
        }
    });
    private final CheckBoxList localesList = new CheckBoxList();
    private final Label project = new Label();
    private final Label projectVersion = new Label();

    @NotNull
    public ValueListBox<RESTTranslationServerV1> translationServerEditor() {
        return server;
    }

    @NotNull
    public CheckBoxList localesEditor() {
        return localesList;
    }

    public RESTCSTranslationDetailV1Editor(final boolean readOnly, @Nullable final List<RESTLocaleV1> locales,
            @NotNull final List<RESTTranslationServerV1> translationServers) {
        addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_PANEL);

        /* http://code.google.com/p/google-web-toolkit/issues/detail?id=6112 */
        DOM.setElementPropertyBoolean(server.getElement(), "disabled", readOnly);
        localesList.setEnabled(!readOnly);
        enabled.setEnabled(!readOnly);

        final List<RESTTranslationServerV1> translationServersCopy = new ArrayList();
        translationServersCopy.add(null);
        translationServersCopy.addAll(translationServers);

        /* http://stackoverflow.com/a/11176707/157605 */
        server.setValue(translationServers == null || translationServers.isEmpty() ? null : translationServers.get(0));
        server.setAcceptableValues(translationServersCopy);

        this.locales = new LinkedList<RESTLocaleV1>(locales);
        Collections.sort(this.locales, new RESTLocaleV1Sort());

        for (final RESTLocaleV1 locale : this.locales) {
            localesMap.put(locale.getId(), locale);
        }

        int row = 0;
        setWidget(row, 0, enabled);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        ++row;
        setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TranslationServer()));
        setWidget(row, 1, server);

        ++row;
        setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TranslationProject()));
        setWidget(row, 1, project);

        ++row;
        setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TranslationProjectVersion()));
        setWidget(row, 1, projectVersion);

        ++row;
        setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Locales()));
        setWidget(row, 1, localesList);

        for (int i = 0; i < row; ++i) {
            getCellFormatter().addStyleName(i, 0, CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_LABEL_CELL);
        }

        for (int i = 1; i < row; ++i) {
            getCellFormatter().addStyleName(i, 1, CSSConstants.ContentSpecView.CONTENT_SPEC_VIEW_DETAIL_CELL);
        }

        localesList.addStyleName(CSSConstants.ContentSpecView.TRANSLATION_LOCALE_LIST_BOX);
    }

    @Override
    public void setValue(@NotNull final RESTCSTranslationDetailV1 value) {
        this.value = value;

        // Store the initial state
        localesValue = value.getLocales() == null ? new RESTLocaleCollectionV1() : value.getLocales();

        enabled.setValue(value.isEnabled());
        project.setText(value.getProject() == null ? Constants.TRANSLATION_SERVER_PROJECT : value.getProject());
        projectVersion.setText(value.getProjectVersion() == null ? Constants.TRANSLATION_SERVER_PROJECT_VERSION : value.getProjectVersion());
        server.setValue(value.getTranslationServer());

        localesList.clear();
        final List<RESTLocaleV1> translationLocales = value.getLocales() == null || value.getLocales().getItems() == null ?
                new ArrayList<RESTLocaleV1>() : value.getLocales().returnItems();
        for (final RESTLocaleV1 locale : locales) {
            localesList.addItem(locale.getValue(), locale.getId().toString(), translationLocales.contains(locale));
        }
    }


    @Override
    public RESTCSTranslationDetailV1 getValue() {
        value.setEnabled(enabled.getValue());
        value.setTranslationServer(server.getValue());
        value.setProject(project.getText());
        value.setProjectVersion(projectVersion.getText());

        final RESTLocaleCollectionV1 translationLocales = new RESTLocaleCollectionV1();
        for (final String localeId : localesList.getSelectedItemValues()) {
            translationLocales.addItem(localesMap.get(Integer.parseInt(localeId)));
        }
        if (!GWTUtilities.listsEqual(translationLocales.getItems(), localesValue.getItems())) {
            // Sync removed items and changed items
            for (final RESTLocaleCollectionItemV1 item : localesValue.getItems()) {
                boolean found = false;
                for (final RESTLocaleCollectionItemV1 item2 : translationLocales.getItems()) {
                    if (item.getItem().equals(item2.getItem())) {
                        item2.setState(item.getState());
                        found = true;
                        break;
                    }
                }

                // Remove the item if its not something that has been added recently
                if (!found && item.getState() != RESTLocaleCollectionItemV1.ADD_STATE) {
                    translationLocales.addRemoveItem(item.getItem());
                }
            }

            // Check for new items
            for (final RESTLocaleCollectionItemV1 item : translationLocales.getItems()) {
                boolean found = false;
                for (final RESTLocaleCollectionItemV1 item2 : localesValue.getItems()) {
                    if (item.getItem().equals(item2.getItem())) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    item.setState(RESTLocaleCollectionItemV1.ADD_STATE);
                }
            }
        }
        localesValue = translationLocales;

        value.setLocales(translationLocales);
        return value;
    }
}
