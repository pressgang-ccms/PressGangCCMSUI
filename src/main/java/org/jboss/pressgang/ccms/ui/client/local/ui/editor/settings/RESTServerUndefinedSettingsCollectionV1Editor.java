package org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisableTextInputCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.TextInputHeader;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTServerUndefinedSettingCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTServerUndefinedSettingCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerUndefinedSettingV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public final class RESTServerUndefinedSettingsCollectionV1Editor extends Composite implements Editor<RESTServerUndefinedSettingCollectionV1> {
    private final CellTable<RESTServerUndefinedSettingCollectionItemV1> table = UIUtilities
            .<RESTServerUndefinedSettingCollectionItemV1>createCellTable();
    private final ListDataProvider<RESTServerUndefinedSettingCollectionItemV1> dataProvider = new
            ListDataProvider<RESTServerUndefinedSettingCollectionItemV1>();
    private final ListDataProviderEditor<RESTServerUndefinedSettingCollectionItemV1> editor = ListDataProviderEditor.of(dataProvider);
    private final SimplePager pager = UIUtilities.createSimplePager();
    private boolean readOnly;


    private DisableTextInputCell keyCell = new DisableTextInputCell();
    private Column<RESTServerUndefinedSettingCollectionItemV1, String> keyColumn = new Column<RESTServerUndefinedSettingCollectionItemV1,
            String>(keyCell) {
        @Override
        public String getValue(final RESTServerUndefinedSettingCollectionItemV1 object) {
            keyCell.setEnabled(!readOnly);

            return object.getItem().getKey();
        }
    };

    private DisableTextInputCell valueCell = new DisableTextInputCell();
    private Column<RESTServerUndefinedSettingCollectionItemV1, String> valueColumn = new Column<RESTServerUndefinedSettingCollectionItemV1,
            String>(valueCell) {
        @Override
        public String getValue(final RESTServerUndefinedSettingCollectionItemV1 object) {
            valueCell.setEnabled(!readOnly);

            return object.getItem().getValue();
        }
    };


    final DisableableButtonCell removeButton = new DisableableButtonCell();
    private Column<RESTServerUndefinedSettingCollectionItemV1, String> removeColumn = new Column<RESTServerUndefinedSettingCollectionItemV1,
            String>(removeButton) {
        @Override
        public String getValue(RESTServerUndefinedSettingCollectionItemV1 object) {
            removeButton.setEnabled(!readOnly);

            return PressGangCCMSUI.INSTANCE.Remove();
        }
    };

    private TextInputHeader keyFooter = new TextInputHeader();
    private TextInputHeader valueFooter = new TextInputHeader();
    private Header<String> addButtonFooter = new Header<String>(new ButtonCell()) {
        @Override
        public String getValue() {
            return PressGangCCMSUI.INSTANCE.Add();
        }
    };

    public RESTServerUndefinedSettingsCollectionV1Editor(final boolean readOnly) {
        this.readOnly = readOnly;

        final FieldUpdater<RESTServerUndefinedSettingCollectionItemV1, String> fieldUpdater = new FieldUpdater<RESTServerUndefinedSettingCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTServerUndefinedSettingCollectionItemV1 object, String value) {
                if (object.getState() != RESTServerUndefinedSettingCollectionItemV1.ADD_STATE) {
                    object.setState(RESTServerUndefinedSettingCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetValue(value);
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

        final VerticalPanel undefinedSettingsPanel = new VerticalPanel();
        undefinedSettingsPanel.add(table);
        undefinedSettingsPanel.add(pager);
        undefinedSettingsPanel.setWidth("100%");

        final SelectionModel<RESTServerUndefinedSettingCollectionItemV1> selectionModel = new SingleSelectionModel<RESTServerUndefinedSettingCollectionItemV1>();
        table.setSelectionModel(selectionModel);

        initWidget(undefinedSettingsPanel);
    }

    public ListDataProviderEditor<RESTServerUndefinedSettingCollectionItemV1> itemsEditor() {
        return editor;
    }

    public ListDataProvider<RESTServerUndefinedSettingCollectionItemV1> getDataProvider() {
        return dataProvider;
    }

    public Column<RESTServerUndefinedSettingCollectionItemV1, String> getRemoveColumn() {
        return removeColumn;
    }

    public Header<String> getAddButtonFooter() {
        return addButtonFooter;
    }

    public RESTServerUndefinedSettingV1 getNewItemFromFooter() {
        final RESTServerUndefinedSettingV1 setting = new RESTServerUndefinedSettingV1();
        setting.setKey(keyFooter.getValue());
        setting.explicitSetValue(valueFooter.getValue());
        return setting;
    }

    public void resetFooter() {
        keyFooter.setValue(null);
        valueFooter.setValue(null);
    }
}
