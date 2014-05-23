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
import com.google.gwt.user.client.ui.DisableEditTextCell;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.TextInputHeader;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTZanataServerSettingsCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTZanataServerSettingsCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTZanataServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public final class RESTZanataServerSettingsCollectionV1Editor extends Composite implements Editor<RESTZanataServerSettingsCollectionV1> {
    private final CellTable<RESTZanataServerSettingsCollectionItemV1> table = UIUtilities
            .<RESTZanataServerSettingsCollectionItemV1>createCellTable();
    private final ListDataProvider<RESTZanataServerSettingsCollectionItemV1> dataProvider = new
            ListDataProvider<RESTZanataServerSettingsCollectionItemV1>();
    private final ListDataProviderEditor<RESTZanataServerSettingsCollectionItemV1> editor = ListDataProviderEditor.of(dataProvider);
    private final SimplePager pager = UIUtilities.createSimplePager();
    private boolean readOnly;

    private TextColumn<RESTZanataServerSettingsCollectionItemV1> idColumn = new TextColumn<RESTZanataServerSettingsCollectionItemV1>() {
        @Override
        public String getValue(final RESTZanataServerSettingsCollectionItemV1 object) {
            return object.getItem().getId();
        }
    };

    private DisableEditTextCell nameCell = new DisableEditTextCell();
    private Column<RESTZanataServerSettingsCollectionItemV1, String> nameColumn = new Column<RESTZanataServerSettingsCollectionItemV1,
            String>(nameCell) {
        @Override
        public String getValue(final RESTZanataServerSettingsCollectionItemV1 object) {
            nameCell.setEnabled(!readOnly);

            return object.getItem().getName() == null ? "" : object.getItem().getName();
        }
    };

    private DisableEditTextCell urlCell = new DisableEditTextCell();
    private Column<RESTZanataServerSettingsCollectionItemV1, String> urlColumn = new Column<RESTZanataServerSettingsCollectionItemV1,
            String>(urlCell) {
        @Override
        public String getValue(final RESTZanataServerSettingsCollectionItemV1 object) {
            urlCell.setEnabled(!readOnly);

            return object.getItem().getUrl() == null ? "" : object.getItem().getUrl();
        }
    };

    private DisableEditTextCell projectCell = new DisableEditTextCell();
    private Column<RESTZanataServerSettingsCollectionItemV1, String> projectColumn = new Column<RESTZanataServerSettingsCollectionItemV1,
            String>(projectCell) {
        @Override
        public String getValue(final RESTZanataServerSettingsCollectionItemV1 object) {
            projectCell.setEnabled(!readOnly);

            return object.getItem().getProject() == null ? "" : object.getItem().getProject();
        }
    };

    private DisableEditTextCell versionCell = new DisableEditTextCell();
    private Column<RESTZanataServerSettingsCollectionItemV1, String> versionColumn = new Column<RESTZanataServerSettingsCollectionItemV1,
            String>(versionCell) {
        @Override
        public String getValue(final RESTZanataServerSettingsCollectionItemV1 object) {
            versionCell.setEnabled(!readOnly);

            return object.getItem().getProjectVersion() == null ? "" : object.getItem().getProjectVersion();
        }
    };

    final DisableableButtonCell removeButton = new DisableableButtonCell();
    private Column<RESTZanataServerSettingsCollectionItemV1, String> removeColumn = new Column<RESTZanataServerSettingsCollectionItemV1,
            String>(removeButton) {
        @Override
        public String getValue(RESTZanataServerSettingsCollectionItemV1 object) {
            removeButton.setEnabled(!readOnly);

            return PressGangCCMSUI.INSTANCE.Remove();
        }
    };

    private TextInputHeader idFooter = new TextInputHeader();
    private TextInputHeader nameFooter = new TextInputHeader();
    private TextInputHeader urlFooter = new TextInputHeader();
    private TextInputHeader projectFooter = new TextInputHeader();
    private TextInputHeader projectVersionFooter = new TextInputHeader();
    private Header<String> addButtonFooter = new Header<String>(new ButtonCell()) {
        @Override
        public String getValue() {
            return PressGangCCMSUI.INSTANCE.Add();
        }
    };

    public RESTZanataServerSettingsCollectionV1Editor(final boolean readOnly) {
        this.readOnly = readOnly;

        table.addColumn(idColumn, new TextHeader(PressGangCCMSUI.INSTANCE.ZanataServerID()), readOnly ? null : idFooter);
        table.addColumn(nameColumn, new TextHeader(PressGangCCMSUI.INSTANCE.ZanataServerName()), readOnly ? null : nameFooter);
        table.addColumn(urlColumn, new TextHeader(PressGangCCMSUI.INSTANCE.URL()), readOnly ? null : urlFooter);
        table.addColumn(projectColumn, new TextHeader(PressGangCCMSUI.INSTANCE.ZanataServerProject()), readOnly ? null : projectFooter);
        table.addColumn(versionColumn, new TextHeader(PressGangCCMSUI.INSTANCE.ZanataServerProjectVersion()), readOnly ? null : projectVersionFooter);
        table.addColumn(removeColumn, null, readOnly ? null : addButtonFooter);

        idFooter.setHeaderStyleNames(CSSConstants.SettingsView.ZANATA_ID_FOOTER);
        nameFooter.setHeaderStyleNames(CSSConstants.SettingsView.ZANATA_NAME_FOOTER);
        urlFooter.setHeaderStyleNames(CSSConstants.SettingsView.ZANATA_URL_FOOTER);
        projectFooter.setHeaderStyleNames(CSSConstants.SettingsView.ZANATA_PROJECT_FOOTER);
        projectVersionFooter.setHeaderStyleNames(CSSConstants.SettingsView.ZANATA_PROJECT_VERSION_FOOTER);

        table.addStyleName(CSSConstants.SettingsView.ZANATA_TABLE);
        table.setWidth("100%");

        dataProvider.addDataDisplay(table);
        pager.setDisplay(table);

        final VerticalPanel zanataServersPanel = new VerticalPanel();
        zanataServersPanel.add(table);
        zanataServersPanel.add(pager);
        zanataServersPanel.setWidth("100%");

        final SelectionModel<RESTZanataServerSettingsCollectionItemV1> selectionModel = new SingleSelectionModel<RESTZanataServerSettingsCollectionItemV1>();
        table.setSelectionModel(selectionModel);

        initWidget(zanataServersPanel);

        setupFieldUpdaters();
    }

    public ListDataProviderEditor<RESTZanataServerSettingsCollectionItemV1> itemsEditor() {
        return editor;
    }

    public Column<RESTZanataServerSettingsCollectionItemV1, String> getRemoveColumn() {
        return removeColumn;
    }

    public ListDataProvider<RESTZanataServerSettingsCollectionItemV1> getDataProvider() {
        return dataProvider;
    }

    public Header<String> getAddButtonFooter() {
        return addButtonFooter;
    }

    public RESTZanataServerSettingsV1 getNewItemFromFooter() {
        final RESTZanataServerSettingsV1 setting = new RESTZanataServerSettingsV1();
        setting.setId(idFooter.getValue());
        setting.explicitSetName(nameFooter.getValue());
        setting.explicitSetUrl(urlFooter.getValue());
        setting.explicitSetProject(projectFooter.getValue());
        setting.explicitSetProjectVersion(projectVersionFooter.getValue());
        return setting;
    }

    public void resetFooter() {
        idFooter.setValue(null);
        nameFooter.setValue(null);
        urlFooter.setValue(null);
        projectFooter.setValue(null);
        projectVersionFooter.setValue(null);
    }

    protected void setupFieldUpdaters() {
        nameColumn.setFieldUpdater(new FieldUpdater<RESTZanataServerSettingsCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTZanataServerSettingsCollectionItemV1 object, String value) {
                if (object.getState() != RESTZanataServerSettingsCollectionItemV1.ADD_STATE) {
                    object.setState(RESTZanataServerSettingsCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetName(value);
            }
        });
        urlColumn.setFieldUpdater(new FieldUpdater<RESTZanataServerSettingsCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTZanataServerSettingsCollectionItemV1 object, String value) {
                if (object.getState() != RESTZanataServerSettingsCollectionItemV1.ADD_STATE) {
                    object.setState(RESTZanataServerSettingsCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetUrl(value);
            }
        });
        projectColumn.setFieldUpdater(new FieldUpdater<RESTZanataServerSettingsCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTZanataServerSettingsCollectionItemV1 object, String value) {
                if (object.getState() != RESTZanataServerSettingsCollectionItemV1.ADD_STATE) {
                    object.setState(RESTZanataServerSettingsCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetProject(value);
            }
        });
        versionColumn.setFieldUpdater(new FieldUpdater<RESTZanataServerSettingsCollectionItemV1, String>() {
            @Override
            public void update(int index, final RESTZanataServerSettingsCollectionItemV1 object, String value) {
                if (object.getState() != RESTZanataServerSettingsCollectionItemV1.ADD_STATE) {
                    object.setState(RESTZanataServerSettingsCollectionItemV1.UPDATE_STATE);
                }
                object.getItem().explicitSetProjectVersion(value);
            }
        });
    }
}