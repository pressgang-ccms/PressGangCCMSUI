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
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLocaleCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public final class RESTLocaleCollectionV1Editor extends Composite implements Editor<RESTLocaleCollectionV1> {
    private final CellTable<RESTLocaleCollectionItemV1> table = UIUtilities
            .<RESTLocaleCollectionItemV1>createCellTable();
    private final ListDataProvider<RESTLocaleCollectionItemV1> dataProvider = new
            ListDataProvider<RESTLocaleCollectionItemV1>();
    private final ListDataProviderEditor<RESTLocaleCollectionItemV1> editor = ListDataProviderEditor.of(dataProvider);
    private final SimplePager pager = UIUtilities.createSimplePager();
    private boolean readOnly;

    private TextColumn<RESTLocaleCollectionItemV1> idColumn = new TextColumn<RESTLocaleCollectionItemV1>() {
        @Override
        public String getValue(final RESTLocaleCollectionItemV1 object) {
            if (object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            } else {
                return "";
            }
        }
    };

    private DisableTextInputCell valueCell = new DisableTextInputCell();
    private Column<RESTLocaleCollectionItemV1, String> valueColumn = new Column<RESTLocaleCollectionItemV1, String>(valueCell) {
        @Override
        public String getValue(final RESTLocaleCollectionItemV1 object) {
            valueCell.setEnabled(!readOnly);

            if (object.getItem() != null && object.getItem().getValue() != null) {
                return object.getItem().getValue();
            } else {
                return "";
            }
        }
    };

    private DisableTextInputCell translationValueCell = new DisableTextInputCell();
    private Column<RESTLocaleCollectionItemV1, String> translationValueColumn = new Column<RESTLocaleCollectionItemV1,
            String>(translationValueCell) {
        @Override
        public String getValue(final RESTLocaleCollectionItemV1 object) {
            translationValueCell.setEnabled(!readOnly);

            if (object.getItem() != null && object.getItem().getTranslationValue() != null) {
                return object.getItem().getTranslationValue();
            } else {
                return "";
            }
        }
    };

    private DisableTextInputCell buildValueCell = new DisableTextInputCell();
    private Column<RESTLocaleCollectionItemV1, String> buildValueColumn = new Column<RESTLocaleCollectionItemV1, String>(buildValueCell) {
        @Override
        public String getValue(final RESTLocaleCollectionItemV1 object) {
            buildValueCell.setEnabled(!readOnly);

            if (object.getItem() != null && object.getItem().getBuildValue() != null) {
                return object.getItem().getBuildValue();
            } else {
                return "";
            }
        }
    };

    final DisableableButtonCell removeButton = new DisableableButtonCell();
    private Column<RESTLocaleCollectionItemV1, String> removeColumn = new Column<RESTLocaleCollectionItemV1, String>(removeButton) {
        @Override
        public String getValue(RESTLocaleCollectionItemV1 object) {
            if (object.returnIsAddItem()) {
                removeButton.setEnabled(true);
            } else {
                // We currently have no way to remove locales, so just disable the button
                removeButton.setEnabled(false);
            }

            return PressGangCCMSUI.INSTANCE.Remove();
        }
    };

    private TextInputHeader valueFooter = new TextInputHeader();
    private TextInputHeader translationValueFooter = new TextInputHeader();
    private TextInputHeader buildValueFooter = new TextInputHeader();
    private Header<String> addButtonFooter = new Header<String>(new ButtonCell()) {
        @Override
        public String getValue() {
            return PressGangCCMSUI.INSTANCE.Add();
        }
    };

    public RESTLocaleCollectionV1Editor(final boolean readOnly) {
        this.readOnly = readOnly;

        table.addColumn(idColumn, new TextHeader(PressGangCCMSUI.INSTANCE.LocaleID()));
        table.addColumn(valueColumn, new TextHeader(PressGangCCMSUI.INSTANCE.LocaleValue()), readOnly ? null : valueFooter);
        table.addColumn(translationValueColumn, new TextHeader(PressGangCCMSUI.INSTANCE.LocaleTranslationValue()),
                readOnly ? null : translationValueFooter);
        table.addColumn(buildValueColumn, new TextHeader(PressGangCCMSUI.INSTANCE.LocaleBuildValue()), readOnly ? null : buildValueFooter);
        table.addColumn(removeColumn, null, readOnly ? null : addButtonFooter);

        valueFooter.setHeaderStyleNames(CSSConstants.SettingsView.LOCALE_VALUE_FOOTER);
        translationValueFooter.setHeaderStyleNames(CSSConstants.SettingsView.LOCALE_TRANSLATION_VALUE_FOOTER);
        buildValueFooter.setHeaderStyleNames(CSSConstants.SettingsView.LOCALE_BUILD_VALUE_FOOTER);

        table.addStyleName(CSSConstants.SettingsView.LOCALES_TABLE);
        table.setWidth("100%");

        dataProvider.addDataDisplay(table);
        pager.setDisplay(table);

        final VerticalPanel zanataServersPanel = new VerticalPanel();
        zanataServersPanel.add(table);
        zanataServersPanel.add(pager);
        zanataServersPanel.setWidth("100%");

        final SelectionModel<RESTLocaleCollectionItemV1> selectionModel = new SingleSelectionModel<RESTLocaleCollectionItemV1>();
        table.setSelectionModel(selectionModel);

        initWidget(zanataServersPanel);

        setupFieldUpdaters();
    }

    public ListDataProviderEditor<RESTLocaleCollectionItemV1> itemsEditor() {
        return editor;
    }

    public Column<RESTLocaleCollectionItemV1, String> getRemoveColumn() {
        return removeColumn;
    }

    public ListDataProvider<RESTLocaleCollectionItemV1> getDataProvider() {
        return dataProvider;
    }

    public Header<String> getAddButtonFooter() {
        return addButtonFooter;
    }

    public RESTLocaleV1 getNewItemFromFooter() {
        final RESTLocaleV1 setting = new RESTLocaleV1();
        setting.explicitSetValue(valueFooter.getValue());
        setting.explicitSetTranslationValue(translationValueFooter.getValue());
        setting.explicitSetBuildValue(buildValueFooter.getValue());
        return setting;
    }

    public void resetFooter() {
        valueFooter.setValue(null);
        translationValueFooter.setValue(null);
        buildValueFooter.setValue(null);
    }

    protected void setupFieldUpdaters() {
        valueColumn.setFieldUpdater(new FieldUpdater<RESTLocaleCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTLocaleCollectionItemV1 object, String value) {
                object.getItem().explicitSetValue(value);
            }
        });
        translationValueColumn.setFieldUpdater(new FieldUpdater<RESTLocaleCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTLocaleCollectionItemV1 object, String value) {
                object.getItem().explicitSetTranslationValue(value);
            }
        });
        buildValueColumn.setFieldUpdater(new FieldUpdater<RESTLocaleCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTLocaleCollectionItemV1 object, String value) {
                object.getItem().explicitSetBuildValue(value);
            }
        });
    }
}