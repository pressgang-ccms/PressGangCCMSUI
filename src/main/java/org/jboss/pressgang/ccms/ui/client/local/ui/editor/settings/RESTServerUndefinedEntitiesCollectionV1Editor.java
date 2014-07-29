/*
  Copyright 2011-2014 Red Hat

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

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisableTextInputCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.IntegerTextInputHeader;
import com.google.gwt.user.client.ui.TextInputHeader;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTServerUndefinedEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTServerUndefinedEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerUndefinedEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public final class RESTServerUndefinedEntitiesCollectionV1Editor extends Composite implements
        Editor<RESTServerUndefinedEntityCollectionV1> {
    private final CellTable<RESTServerUndefinedEntityCollectionItemV1> table = UIUtilities
            .<RESTServerUndefinedEntityCollectionItemV1>createCellTable();
    private final ListDataProvider<RESTServerUndefinedEntityCollectionItemV1> dataProvider = new
            ListDataProvider<RESTServerUndefinedEntityCollectionItemV1>();
    private final ListDataProviderEditor<RESTServerUndefinedEntityCollectionItemV1> editor = ListDataProviderEditor.of(dataProvider);
    private final SimplePager pager = UIUtilities.createSimplePager();
    private boolean readOnly;


    private DisableTextInputCell keyCell = new DisableTextInputCell();
    private Column<RESTServerUndefinedEntityCollectionItemV1, String> keyColumn = new Column<RESTServerUndefinedEntityCollectionItemV1,
            String>(keyCell) {
        @Override
        public String getValue(final RESTServerUndefinedEntityCollectionItemV1 object) {
            keyCell.setEnabled(!readOnly);

            return object.getItem().getKey();
        }
    };

    private DisableTextInputCell valueCell = new DisableTextInputCell();
    private Column<RESTServerUndefinedEntityCollectionItemV1, String> valueColumn = new Column<RESTServerUndefinedEntityCollectionItemV1,
            String>(valueCell) {
        @Override
        public String getValue(final RESTServerUndefinedEntityCollectionItemV1 object) {
            valueCell.setEnabled(!readOnly);

            return object.getItem().getValue() + "";
        }
    };

    final DisableableButtonCell removeButton = new DisableableButtonCell();
    private Column<RESTServerUndefinedEntityCollectionItemV1, String> removeColumn = new Column<RESTServerUndefinedEntityCollectionItemV1,
            String>(removeButton) {
        @Override
        public String getValue(RESTServerUndefinedEntityCollectionItemV1 object) {
            removeButton.setEnabled(!readOnly);

            return PressGangCCMSUI.INSTANCE.Remove();
        }
    };

    private TextInputHeader keyFooter = new TextInputHeader();
    private TextInputCell valueFooterCell = new TextInputCell();
    private IntegerTextInputHeader valueFooter = new IntegerTextInputHeader(valueFooterCell);
    private Header<String> addButtonFooter = new Header<String>(new ButtonCell()) {
        @Override
        public String getValue() {
            return PressGangCCMSUI.INSTANCE.Add();
        }
    };

    public RESTServerUndefinedEntitiesCollectionV1Editor(final boolean readOnly) {
        this.readOnly = readOnly;

        final FieldUpdater<RESTServerUndefinedEntityCollectionItemV1, String> fieldUpdater = new FieldUpdater<RESTServerUndefinedEntityCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTServerUndefinedEntityCollectionItemV1 object, String value) {
                if (object.getState() != RESTServerUndefinedEntityCollectionItemV1.ADD_STATE) {
                    object.setState(RESTServerUndefinedEntityCollectionItemV1.UPDATE_STATE);
                }
                final String fixedValue = value.replaceAll("[^\\d]", "");
                if (!isStringNullOrEmpty(fixedValue)) {
                    object.getItem().explicitSetValue(Integer.parseInt(fixedValue));
                } else {
                    object.getItem().explicitSetValue(null);
                }
            }
        };
        keyColumn.setFieldUpdater(fieldUpdater);
        valueColumn.setFieldUpdater(fieldUpdater);

        table.addColumn(keyColumn, new TextHeader(PressGangCCMSUI.INSTANCE.SettingsKey()), readOnly ? null : keyFooter);
        table.addColumn(valueColumn, new TextHeader(PressGangCCMSUI.INSTANCE.SettingsValue()), readOnly ? null : valueFooter);
        table.addColumn(removeColumn, null, readOnly ? null : addButtonFooter);

        keyFooter.setHeaderStyleNames(CSSConstants.SettingsView.KEY_FOOTER);
        valueFooter.setHeaderStyleNames(CSSConstants.SettingsView.VALUE_FOOTER);

        table.addStyleName(CSSConstants.SettingsView.UNDEFINED_TABLE);
        table.setWidth("100%");

        dataProvider.addDataDisplay(table);
        pager.setDisplay(table);

        final VerticalPanel undefinedEntitiesPanel = new VerticalPanel();
        undefinedEntitiesPanel.add(table);
        undefinedEntitiesPanel.add(pager);
        undefinedEntitiesPanel.setWidth("100%");

        final SelectionModel<RESTServerUndefinedEntityCollectionItemV1> selectionModel = new SingleSelectionModel<RESTServerUndefinedEntityCollectionItemV1>();
        table.setSelectionModel(selectionModel);

        initWidget(undefinedEntitiesPanel);
    }

    public ListDataProviderEditor<RESTServerUndefinedEntityCollectionItemV1> itemsEditor() {
        return editor;
    }

    public ListDataProvider<RESTServerUndefinedEntityCollectionItemV1> getDataProvider() {
        return dataProvider;
    }

    public Column<RESTServerUndefinedEntityCollectionItemV1, String> getRemoveColumn() {
        return removeColumn;
    }

    public Header<String> getAddButtonFooter() {
        return addButtonFooter;
    }

    public RESTServerUndefinedEntityV1 getNewItemFromFooter() {
        final RESTServerUndefinedEntityV1 setting = new RESTServerUndefinedEntityV1();
        setting.setKey(keyFooter.getValue());
        setting.explicitSetValue(Integer.parseInt(valueFooter.getValue()));
        return setting;
    }

    public void resetFooter() {
        keyFooter.setValue(null);
        valueFooter.setValue(null);
    }
}
