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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisableTextInputCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.TextInputHeader;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslationServerExtendedCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslationServerExtendedCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerExtendedV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public final class RESTTranslationServerExtendedCollectionV1Editor extends Composite implements Editor<RESTTranslationServerExtendedCollectionV1> {
    private final CellTable<RESTTranslationServerExtendedCollectionItemV1> table = UIUtilities
            .<RESTTranslationServerExtendedCollectionItemV1>createCellTable();
    private final ListDataProvider<RESTTranslationServerExtendedCollectionItemV1> dataProvider = new
            ListDataProvider<RESTTranslationServerExtendedCollectionItemV1>();
    private final ListDataProviderEditor<RESTTranslationServerExtendedCollectionItemV1> editor = ListDataProviderEditor.of(dataProvider);
    private final SimplePager pager = UIUtilities.createSimplePager();
    private boolean readOnly;

    private TextColumn<RESTTranslationServerExtendedCollectionItemV1> idColumn = new TextColumn<RESTTranslationServerExtendedCollectionItemV1>() {
        @Override
        public String getValue(final RESTTranslationServerExtendedCollectionItemV1 object) {
            return object.getItem().getId() == null ? "" : object.getItem().getId().toString();
        }
    };

    private DisableTextInputCell nameCell = new DisableTextInputCell();
    private Column<RESTTranslationServerExtendedCollectionItemV1, String> nameColumn = new Column<RESTTranslationServerExtendedCollectionItemV1,
            String>(nameCell) {
        @Override
        public String getValue(final RESTTranslationServerExtendedCollectionItemV1 object) {
            nameCell.setEnabled(!readOnly);

            return object.getItem().getName() == null ? "" : object.getItem().getName();
        }
    };

    private DisableTextInputCell urlCell = new DisableTextInputCell();
    private Column<RESTTranslationServerExtendedCollectionItemV1, String> urlColumn = new Column<RESTTranslationServerExtendedCollectionItemV1,
            String>(urlCell) {
        @Override
        public String getValue(final RESTTranslationServerExtendedCollectionItemV1 object) {
            urlCell.setEnabled(!readOnly);

            return object.getItem().getUrl() == null ? "" : object.getItem().getUrl();
        }
    };

    private DisableTextInputCell usernameCell = new DisableTextInputCell();
    private Column<RESTTranslationServerExtendedCollectionItemV1, String> usernameColumn = new Column<RESTTranslationServerExtendedCollectionItemV1,
            String>(usernameCell) {
        @Override
        public String getValue(final RESTTranslationServerExtendedCollectionItemV1 object) {
            usernameCell.setEnabled(!readOnly);

            return object.getItem().getUsername() == null ? "" : object.getItem().getUsername();
        }
    };

    private DisableTextInputCell apiKeyCell = new DisableTextInputCell();
    private Column<RESTTranslationServerExtendedCollectionItemV1, String> apiKeyColumn = new Column<RESTTranslationServerExtendedCollectionItemV1,
            String>(apiKeyCell) {
        @Override
        public String getValue(final RESTTranslationServerExtendedCollectionItemV1 object) {
            apiKeyCell.setEnabled(!readOnly);

            return object.getItem().getKey() == null ? "" : object.getItem().getKey();
        }
    };

    final DisableableButtonCell removeButton = new DisableableButtonCell();
    private Column<RESTTranslationServerExtendedCollectionItemV1, String> removeColumn = new Column<RESTTranslationServerExtendedCollectionItemV1,
            String>(removeButton) {
        @Override
        public String getValue(RESTTranslationServerExtendedCollectionItemV1 object) {
            if (object.returnIsAddItem()) {
                removeButton.setEnabled(true);
            } else {
                // We currently have no way to remove translation servers, so just disable the button
                removeButton.setEnabled(false);
            }

            return PressGangCCMSUI.INSTANCE.Remove();
        }
    };

    private TextInputHeader nameFooter = new TextInputHeader();
    private TextInputHeader urlFooter = new TextInputHeader();
    private TextInputHeader usernameFooter = new TextInputHeader();
    private TextInputHeader apiKeyFooter = new TextInputHeader();
    private Header<String> addButtonFooter = new Header<String>(new ButtonCell()) {
        @Override
        public String getValue() {
            return PressGangCCMSUI.INSTANCE.Add();
        }
    };

    public RESTTranslationServerExtendedCollectionV1Editor(final boolean readOnly) {
        this.readOnly = readOnly;

        table.addColumn(idColumn, new TextHeader(PressGangCCMSUI.INSTANCE.TranslationServerID()));
        table.addColumn(nameColumn, new TextHeader(PressGangCCMSUI.INSTANCE.TranslationServerName()), readOnly ? null : nameFooter);
        table.addColumn(urlColumn, new TextHeader(PressGangCCMSUI.INSTANCE.URL()), readOnly ? null : urlFooter);
        table.addColumn(usernameColumn, new TextHeader(PressGangCCMSUI.INSTANCE.TranslationServerUsername()), readOnly ? null : usernameFooter);
        table.addColumn(apiKeyColumn, new TextHeader(PressGangCCMSUI.INSTANCE.TranslationServerApiKey()), readOnly ? null : apiKeyFooter);
        table.addColumn(removeColumn, null, readOnly ? null : addButtonFooter);

        nameFooter.setHeaderStyleNames(CSSConstants.SettingsView.TRANSLATION_SERVER_NAME_FOOTER);
        urlFooter.setHeaderStyleNames(CSSConstants.SettingsView.TRANSLATION_SERVER_URL_FOOTER);
        usernameFooter.setHeaderStyleNames(CSSConstants.SettingsView.TRANSLATION_SERVER_USERNAME_FOOTER);
        apiKeyFooter.setHeaderStyleNames(CSSConstants.SettingsView.TRANSLATION_SERVER_KEY_FOOTER);

        table.addStyleName(CSSConstants.SettingsView.TRANSLATION_SERVER_TABLE);
        table.setWidth("100%");

        dataProvider.addDataDisplay(table);
        pager.setDisplay(table);

        final VerticalPanel zanataServersPanel = new VerticalPanel();
        zanataServersPanel.add(table);
        zanataServersPanel.add(pager);
        zanataServersPanel.setWidth("100%");

        final SelectionModel<RESTTranslationServerExtendedCollectionItemV1> selectionModel = new SingleSelectionModel<RESTTranslationServerExtendedCollectionItemV1>();
        table.setSelectionModel(selectionModel);

        initWidget(zanataServersPanel);

        setupFieldUpdaters();
    }

    public ListDataProviderEditor<RESTTranslationServerExtendedCollectionItemV1> itemsEditor() {
        return editor;
    }

    public Column<RESTTranslationServerExtendedCollectionItemV1, String> getRemoveColumn() {
        return removeColumn;
    }

    public ListDataProvider<RESTTranslationServerExtendedCollectionItemV1> getDataProvider() {
        return dataProvider;
    }

    public Header<String> getAddButtonFooter() {
        return addButtonFooter;
    }

    public RESTTranslationServerExtendedV1 getNewItemFromFooter() {
        final RESTTranslationServerExtendedV1 setting = new RESTTranslationServerExtendedV1();
        setting.explicitSetName(nameFooter.getValue());
        setting.explicitSetUrl(urlFooter.getValue());
        setting.explicitSetUsername(usernameFooter.getValue());
        setting.explicitSetKey(apiKeyFooter.getValue());
        return setting;
    }

    public void resetFooter() {
        nameFooter.setValue(null);
        urlFooter.setValue(null);
        usernameFooter.setValue(null);
        apiKeyFooter.setValue(null);
    }

    protected void setupFieldUpdaters() {
        nameColumn.setFieldUpdater(new FieldUpdater<RESTTranslationServerExtendedCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTTranslationServerExtendedCollectionItemV1 object, String value) {
                if (object.getState() != RESTTranslationServerExtendedCollectionItemV1.ADD_STATE) {
                    object.setState(RESTTranslationServerExtendedCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetName(value);
            }
        });
        urlColumn.setFieldUpdater(new FieldUpdater<RESTTranslationServerExtendedCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTTranslationServerExtendedCollectionItemV1 object, String value) {
                if (object.getState() != RESTTranslationServerExtendedCollectionItemV1.ADD_STATE) {
                    object.setState(RESTTranslationServerExtendedCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetUrl(value);
            }
        });
        usernameColumn.setFieldUpdater(new FieldUpdater<RESTTranslationServerExtendedCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTTranslationServerExtendedCollectionItemV1 object, String value) {
                if (object.getState() != RESTTranslationServerExtendedCollectionItemV1.ADD_STATE) {
                    object.setState(RESTTranslationServerExtendedCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetUsername(value);
            }
        });
        apiKeyColumn.setFieldUpdater(new FieldUpdater<RESTTranslationServerExtendedCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTTranslationServerExtendedCollectionItemV1 object, String value) {
                if (object.getState() != RESTTranslationServerExtendedCollectionItemV1.ADD_STATE) {
                    object.setState(RESTTranslationServerExtendedCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetKey(value);
            }
        });
    }
}