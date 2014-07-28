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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutPanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutView extends ShortcutPanel {
    /**
     * The panel that holds the shortcut buttons visible when the advanced submenu is open.
     */
    private final ShortcutAdvancedPanel advancedShortcutPanel = new ShortcutAdvancedPanel(PressGangCCMSUI.INSTANCE.Administration());

    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutEntitiesPanel entitiesShortcutPanel = new ShortcutEntitiesPanel(PressGangCCMSUI.INSTANCE.Entities());
    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutSearchPanel searchShortcutPanel = new ShortcutSearchPanel(PressGangCCMSUI.INSTANCE.Search());

    private final ToggleButton home = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Home(), Constants.ElementIDs.HOME_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton docbuilder = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.DocBuilder(), Constants.ElementIDs.DOCBUILDER_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton createTopic = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateTopic(), Constants.ElementIDs.CREATE_TOPIC_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton createContentSpec = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateContentSpec(), Constants.ElementIDs.CREATE_SPEC_NAVIGATION_BUTTON_ID.getId());
    private final PushButton bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ReportBug(), false, true, false, Constants.ElementIDs.CREATE_BUG_NAVIGATION_BUTTON_ID.getId());
    private final PushButton reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true, false, Constants.ElementIDs.REPORTS_NAVIGATION_BUTTON_ID.getId());

    @NotNull
    public ShortcutAdvancedPanel getAdvancedSubMenu() {
        return advancedShortcutPanel;
    }

    @NotNull
    public ShortcutEntitiesPanel getEntitiesSubMenu() {
        return entitiesShortcutPanel;
    }

    @NotNull
    public ShortcutSearchPanel getSearchSubMenu() {
        return searchShortcutPanel;
    }

    @NotNull
    public ToggleButton getHomeButton() {
        return home;
    }

    @NotNull
    public ToggleButton getDocBuilderButton() {
        return docbuilder;
    }

    @NotNull
    public ToggleButton getCreateTopicButton() {
        return createTopic;
    }

    @NotNull
    public ToggleButton getCreateContentSpecButton() {
        return createContentSpec;
    }

    @NotNull
    public PushButton getReportsButton() {
        return reports;
    }

    @NotNull
    public PushButton getBugButton() {
        return bug;
    }

    public void initialise() {
        advancedShortcutPanel.initialise();
        entitiesShortcutPanel.initialise();
        searchShortcutPanel.initialise();

        add(home);
        add(docbuilder);
        add(createTopic);
        add(createContentSpec);
        add(bug);
        add(searchShortcutPanel);
        add(entitiesShortcutPanel);
        add(advancedShortcutPanel);

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                // Only add the reports button if the URL is not null
                if (serverDetails.getReportUrl() != null) {
                    ShortcutView.this.insert(reports, 4);
                }
            }
        });
    }
}
