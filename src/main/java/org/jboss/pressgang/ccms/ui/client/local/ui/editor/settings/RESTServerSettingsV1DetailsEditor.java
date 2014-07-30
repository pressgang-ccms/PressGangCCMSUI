/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings;

import java.io.IOException;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.WholeNumbersValidator;
import org.jetbrains.annotations.NotNull;

public final class RESTServerSettingsV1DetailsEditor extends FlexTable implements Editor<RESTServerSettingsV1> {
    private final DisclosurePanel zanataDetailsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.ZanataServers());
    private final DisclosurePanel localeDetailsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.Locales());
    private final FlexTable localeDetailsTable = new FlexTable();
    private final DisclosurePanel customSettingsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.CustomSettings());
    private final DisclosurePanel otherPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.Other());
    private final FlexTable otherTable = new FlexTable();

    private final Label uiUrlLabel = new Label(PressGangCCMSUI.INSTANCE.UIURL());
    private final TextBox uiUrl = new TextBox();
    private final Label docBuilderUrlLabel = new Label(PressGangCCMSUI.INSTANCE.DocBuilderURL());
    private final TextBox docBuilderUrl = new TextBox();
    private final CheckBox readOnly = new CheckBox(PressGangCCMSUI.INSTANCE.ReadOnly());
    private final Label jmsUpdateFrequencyLabel = new Label(PressGangCCMSUI.INSTANCE.JMSUpdateFrequency());
    private final SimpleIntegerBox jmsUpdateFrequency = new SimpleIntegerBox();
    private final Label defaultLocaleLabel = new Label(PressGangCCMSUI.INSTANCE.DefaultLocale() + ":");
    private final ValueListBox<String> defaultLocale = new ValueListBox<String>(new Renderer<String>() {
        @Override
        public String render(String object) {
            return object;
        }

        @Override
        public void render(String object, Appendable appendable) throws IOException {
        }
    });
    private final LocalesEditor localesEditor = new LocalesEditor();
    private final RESTZanataServerSettingsCollectionV1Editor zanataServersEditor;
    private final RESTServerUndefinedSettingsCollectionV1Editor customSettingsEditor;
    private final OtherSettingsEditor seoCategoryIds = new OtherSettingsEditor(PressGangCCMSUI.INSTANCE.SEOCategoryIDs());
    private final OtherSettingsEditor docBookTemplateIds = new OtherSettingsEditor(PressGangCCMSUI.INSTANCE.DocBookTemplateIDs());

    @NotNull
    public TextBox uiUrlEditor() {
        return uiUrl;
    }

    @NotNull
    public TextBox docBuilderUrlEditor() {
        return docBuilderUrl;
    }

    @NotNull
    public SimpleIntegerBox jmsUpdateFrequencyEditor() {
        return jmsUpdateFrequency;
    }

    @NotNull
    public CheckBox readOnlyEditor() {
        return readOnly;
    }

    @NotNull
    public LocalesEditor localesEditor() {
        return localesEditor;
    }

    @NotNull
    public ValueListBox<String> defaultLocaleEditor() {
        return defaultLocale;
    }

    @NotNull
    public RESTZanataServerSettingsCollectionV1Editor zanataSettingsEditor() {
        return zanataServersEditor;
    }

    @NotNull
    public RESTServerUndefinedSettingsCollectionV1Editor undefinedSettingsEditor() {
        return customSettingsEditor;
    }

    @NotNull
    public OtherSettingsEditor docBookTemplateIdsEditor() {
        return docBookTemplateIds;
    }

    @NotNull
    public OtherSettingsEditor seoCategoryIdsEditor() {
        return seoCategoryIds;
    }

    public RESTServerSettingsV1DetailsEditor(final boolean readOnly) {
        zanataServersEditor = new RESTZanataServerSettingsCollectionV1Editor(readOnly);
        customSettingsEditor = new RESTServerUndefinedSettingsCollectionV1Editor(readOnly);

        uiUrl.setReadOnly(readOnly);
        docBuilderUrl.setReadOnly(readOnly);
        this.readOnly.setEnabled(!readOnly);
        jmsUpdateFrequency.setEnabled(!readOnly);
        new WholeNumbersValidator(jmsUpdateFrequency);

        final HorizontalPanel defaultLocalePanel = new HorizontalPanel();
        defaultLocalePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        defaultLocalePanel.add(defaultLocaleLabel);
        defaultLocalePanel.add(defaultLocale);

        int row = 0;
        setWidget(row, 0, this.readOnly);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, uiUrlLabel);
        setWidget(row, 1, uiUrl);

        row++;
        setWidget(row, 0, docBuilderUrlLabel);
        setWidget(row, 1, docBuilderUrl);

        row++;
        setWidget(row, 0, jmsUpdateFrequencyLabel);
        setWidget(row, 1, jmsUpdateFrequency);

        // Add styles for text boxes
        for (int i = 1; i <= row; i++) {
            getCellFormatter().addStyleName(i, 0, CSSConstants.SettingsView.SETTINGS_LABEL);
            getCellFormatter().addStyleName(i, 1, CSSConstants.SettingsView.SETTINGS_FIELD);
        }

        row++;
        setWidget(row, 0, localeDetailsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, zanataDetailsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, customSettingsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, otherPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        // Setup the information in the disclosure panels
        localeDetailsPanel.setAnimationEnabled(true);
        localeDetailsPanel.setOpen(true);
        localeDetailsPanel.add(localeDetailsTable);
        localeDetailsTable.setWidget(0, 0, localesEditor);
        localeDetailsTable.setWidget(1, 0, defaultLocalePanel);

        zanataDetailsPanel.setAnimationEnabled(true);
        zanataDetailsPanel.setOpen(true);
        zanataDetailsPanel.add(zanataServersEditor);

        customSettingsPanel.setAnimationEnabled(true);
        customSettingsPanel.setOpen(false);
        customSettingsPanel.add(customSettingsEditor);

        setupOtherPanel();

        // Add the styling for the disclosure panels
        localeDetailsTable.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
        localeDetailsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        zanataDetailsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        customSettingsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);

        addStyleName(CSSConstants.SettingsView.SETTINGS_TABLE);
    }

    protected void setupOtherPanel() {
        otherPanel.setAnimationEnabled(true);
        otherPanel.setOpen(false);
        otherPanel.add(otherTable);

        otherPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        otherTable.setWidth("100%");

        otherTable.setWidget(0, 0, docBookTemplateIds);
        otherTable.setWidget(0, 1, seoCategoryIds);
    }
}
