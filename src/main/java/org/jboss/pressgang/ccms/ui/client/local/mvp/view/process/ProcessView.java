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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.process;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process.ProcessPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

public class ProcessView extends BaseTemplateView implements ProcessPresenter.Display {

    private final TabLayoutPanel processListTable = new TabLayoutPanel(Constants.TAB_PANEL_HEIGHT, Constants.TAB_PANEL_HEIGHT_FORMAT);
    private final HeaderPanel panel = new HeaderPanel();
    private final SimpleLayoutPanel processPanel = new SimpleLayoutPanel();
    private final FlexTable filterPanel = new FlexTable();
    private final PushButton refreshButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Refresh());
    private final PushButton resetButton = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reset());
    private final DateBox submittedAfter = new DateBox();
    private final DateBox submittedBefore = new DateBox();
    private final SimpleLayoutPanel allProcesses = new SimpleLayoutPanel();
    private final SimpleLayoutPanel zanataSyncProcesses = new SimpleLayoutPanel();
    private final SimpleLayoutPanel zanataPushProcesses = new SimpleLayoutPanel();

    @Override
    public TabLayoutPanel getTabLayoutPanel() {
        return processListTable;
    }

    @Override
    public PushButton getRefreshButton() {
        return refreshButton;
    }

    @Override
    public PushButton getResetButton() {
        return resetButton;
    }

    @Override
    public DateBox getSubmittedAfter() {
        return submittedAfter;
    }

    @Override
    public DateBox getSubmittedBefore() {
        return submittedBefore;
    }

    @Override
    public SimpleLayoutPanel getAllProcessesPanel() {
        return allProcesses;
    }

    @Override
    public SimpleLayoutPanel getZanataSyncProcessesPanel() {
        return zanataSyncProcesses;
    }

    @Override
    public SimpleLayoutPanel getZanataPushProcessesPanel() {
        return zanataPushProcesses;
    }

    public ProcessView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Processes());

        panel.addStyleName(CSSConstants.ProcessView.PROCESS_PANEL);

        processListTable.add(allProcesses, PressGangCCMSUI.INSTANCE.AllProcesses());
        processListTable.add(zanataSyncProcesses, PressGangCCMSUI.INSTANCE.TranslationSyncProcesses());
        processListTable.add(zanataPushProcesses, PressGangCCMSUI.INSTANCE.TranslationPushProcesses());

        filterPanel.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.SubmittedAfter()));
        filterPanel.setWidget(1, 0, submittedAfter);
        filterPanel.setWidget(0, 1, new Label(PressGangCCMSUI.INSTANCE.SubmittedBefore()));
        filterPanel.setWidget(1, 1, submittedBefore);

        // Create a cell to split the content
        filterPanel.setWidget(0, filterPanel.getCellCount(0), new SimplePanel());
        filterPanel.getCellFormatter().setAlignment(0, filterPanel.getCellCount(0) - 1,
                HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
        filterPanel.getCellFormatter().setWidth(0, filterPanel.getCellCount(0) - 1, "100%");

        filterPanel.setWidget(0, filterPanel.getCellCount(0), refreshButton);
        filterPanel.getFlexCellFormatter().setRowSpan(0, filterPanel.getCellCount(0) - 1, 2);
        filterPanel.setWidget(0, filterPanel.getCellCount(0), resetButton);
        filterPanel.getFlexCellFormatter().setRowSpan(0, filterPanel.getCellCount(0) - 1, 2);

        // we need to sink the click handler to respond to clicks (http://stackoverflow.com/a/4303407/157605)
        processListTable.sinkEvents(Event.ONCLICK);
        processListTable.addStyleName(CSSConstants.ProcessView.TAB_PANEL);

        processPanel.setHeight("100%");
        processPanel.setWidget(processListTable);

        panel.setContentWidget(processPanel);
        panel.setFooterWidget(filterPanel);

        panel.setHeight("100%");
        panel.setWidth("100%");

        getPanel().setWidget(panel);

        // Remove the action bar since it's not needed
        getTopActionGrandParentPanel().removeFromParent();
    }
}
