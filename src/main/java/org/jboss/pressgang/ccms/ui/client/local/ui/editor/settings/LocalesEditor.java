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

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RowCountChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;

public class LocalesEditor extends FlexTable implements IsEditor<ListDataProviderEditor<String>> {
    private final CellList<String> localesTable = new CellList<String>(new TextCell());
    private final CellList<String> availableLocalesTable = new CellList<String>(new TextCell());
    private final ListDataProvider<String> localesDataProvider = new ListDataProvider<String>();
    private final ListDataProvider<String> availableLocalesDataProvider = new ListDataProvider<String>();
    private final ListDataProviderEditor<String> editor = ListDataProviderEditor.of(localesDataProvider);
    private final Button add = new Button("<< " + PressGangCCMSUI.INSTANCE.Add());
    private final Button remove = new Button(">> " + PressGangCCMSUI.INSTANCE.Remove());

    public LocalesEditor() {
        localesDataProvider.addDataDisplay(localesTable);
        availableLocalesDataProvider.addDataDisplay(availableLocalesTable);

        localesTable.setSelectionModel(new SingleSelectionModel<String>());
        availableLocalesTable.setSelectionModel(new SingleSelectionModel<String>());

        final VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(add);
        panel.add(remove);

        final ScrollPanel localesTablePanel = new ScrollPanel();
        localesTablePanel.getElement().getStyle().setOverflowY(Style.Overflow.SCROLL);
        localesTablePanel.add(localesTable);
        localesTablePanel.addStyleName(CSSConstants.SettingsView.LOCALES_CELL_SCROLL);
        localesTable.addStyleName(CSSConstants.SettingsView.LOCALES_CELL_LIST);

        final ScrollPanel availableLocalesTablePanel = new ScrollPanel();
        availableLocalesTablePanel.getElement().getStyle().setOverflowY(Style.Overflow.SCROLL);
        availableLocalesTablePanel.add(availableLocalesTable);
        availableLocalesTablePanel.addStyleName(CSSConstants.SettingsView.LOCALES_CELL_SCROLL);
        availableLocalesTable.addStyleName(CSSConstants.SettingsView.LOCALES_CELL_LIST);

        setWidget(0, 0, localesTablePanel);
        setWidget(0, 1, panel);
        setWidget(0, 2, availableLocalesTablePanel);

        getFlexCellFormatter().addStyleName(0, 0, CSSConstants.SettingsView.LOCALES_CELL);
        getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
        getFlexCellFormatter().addStyleName(0, 2, CSSConstants.SettingsView.LOCALES_CELL);
        addStyleName(CSSConstants.SettingsView.LOCALES_TABLE);

        // Configure the cell lists to display all data
        localesTable.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {
            @Override
            public void onRowCountChange(final RowCountChangeEvent event) {
                localesTable.setVisibleRange(0, event.getNewRowCount());
            }
        });
        availableLocalesTable.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {
            @Override
            public void onRowCountChange(final RowCountChangeEvent event) {
                availableLocalesTable.setVisibleRange(0, event.getNewRowCount());
            }
        });
    }

    @Override
    public ListDataProviderEditor<String> asEditor() {
        return editor;
    }

    @Editor.Ignore
    public CellList<String> getLocalesTable() {
        return localesTable;
    }

    @Editor.Ignore
    public ListDataProvider<String> getLocalesDataProvider() {
        return localesDataProvider;
    }

    @Editor.Ignore
    public CellList<String> getAvailableLocalesTable() {
        return availableLocalesTable;
    }

    @Editor.Ignore
    public ListDataProvider<String> getAvailableLocalesDataProvider() {
        return availableLocalesDataProvider;
    }

    @Editor.Ignore
    public Button getAdd() {
        return add;
    }

    @Editor.Ignore
    public Button getRemove() {
        return remove;
    }
}
