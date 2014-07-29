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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTSystemStatsV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.SysInfoPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * View to show the system information.
 */
public class SysInfoView extends BaseTemplateView implements SysInfoPresenter.Display {

    private FlexTable layout = new FlexTable();
    private Label revNumLabel = new Label(PressGangCCMSUI.INSTANCE.HighestRevisionNumber());
    private Label revNum = new Label();
    private Label revDateLabel = new Label(PressGangCCMSUI.INSTANCE.HighestRevisionDate());
    private DateLabel revDate = new DateLabel(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM));

    public SysInfoView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SystemInfo());

        layout.addStyleName(CSSConstants.SysInfo.SYS_INFO_PANEL);
        revNumLabel.addStyleName(CSSConstants.SysInfo.SYSINFO_LABEL);
        revDateLabel.addStyleName(CSSConstants.SysInfo.SYSINFO_LABEL);

        layout.setWidget(0, 0, revNumLabel);
        layout.setWidget(0, 1, revNum);
        layout.setWidget(1, 0, revDateLabel);
        layout.setWidget(1, 1, revDate);

        this.getPanel().setWidget(layout);


        // Remove the action bar since it's not needed
        getTopActionGrandParentPanel().removeFromParent();
    }

    public void display(@NotNull final RESTSystemStatsV1 stats) {
        revNum.setText(stats.getLastRevision() + "");
        revDate.setValue(stats.getLastRevisionDate());
    }
}