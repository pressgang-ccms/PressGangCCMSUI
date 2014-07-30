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

import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.IntegerCell;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RowCountChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.ListDataProviderEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.WholeNumbersValidator;

public class OtherSettingsEditor extends FlexTable implements IsEditor<ListDataProviderEditor<Integer>> {
    private final CellList<Integer> otherSettingTable = new CellList<Integer>(new IntegerCell());
    private final ListDataProvider<Integer> dataProvider = new ListDataProvider<Integer>();
    private final ListDataProviderEditor<Integer> editor = ListDataProviderEditor.of(dataProvider);
    private final Button remove = new Button(PressGangCCMSUI.INSTANCE.Remove());
    private final Button add = new Button(PressGangCCMSUI.INSTANCE.Add());
    private final IntegerBox newValue = new IntegerBox();

    public OtherSettingsEditor(final String title) {
        dataProvider.addDataDisplay(otherSettingTable);
        otherSettingTable.setSelectionModel(new SingleSelectionModel<Integer>());
        new WholeNumbersValidator(newValue);

        final ScrollPanel localesTablePanel = new ScrollPanel();
        localesTablePanel.getElement().getStyle().setOverflowY(Style.Overflow.SCROLL);
        localesTablePanel.add(otherSettingTable);
        localesTablePanel.addStyleName(CSSConstants.SettingsView.OTHER_CELL_SCROLL);
        otherSettingTable.addStyleName(CSSConstants.SettingsView.OTHER_CELL_LIST);

        final VerticalPanel buttonPanel = new VerticalPanel();
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        buttonPanel.add(newValue);
        buttonPanel.add(add);
        buttonPanel.add(remove);

        setWidget(0, 0, new Label(title));
        setWidget(1, 0, localesTablePanel);
        setWidget(1, 1, buttonPanel);

        newValue.setWidth("50px");
        getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        getFlexCellFormatter().addStyleName(1, 0, CSSConstants.SettingsView.OTHER_CELL);

        getFlexCellFormatter().setColSpan(0, 0, 2);

        // Configure the cell lists to display all data
        otherSettingTable.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {
            @Override
            public void onRowCountChange(final RowCountChangeEvent event) {
                otherSettingTable.setVisibleRange(0, event.getNewRowCount());
            }
        });
    }

    @Override
    public ListDataProviderEditor<Integer> asEditor() {
        return editor;
    }

    @Editor.Ignore
    public CellList<Integer> getOtherSettingTable() {
        return otherSettingTable;
    }

    @Editor.Ignore
    public ListDataProvider<Integer> getDataProvider() {
        return dataProvider;
    }

    @Editor.Ignore
    public Button getAddButton() {
        return add;
    }

    @Editor.Ignore
    public Button getRemoveButton() {
        return remove;
    }

    @Editor.Ignore
    public IntegerBox getNewValueBox() {
        return newValue;
    }
}
