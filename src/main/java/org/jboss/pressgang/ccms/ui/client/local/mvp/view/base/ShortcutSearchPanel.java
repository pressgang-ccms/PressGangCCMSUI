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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutSearchPanel extends ShortcutDisclosurePanel {

    private final ToggleButton searchTopics = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTopics(), Constants.ElementIDs.SEARCH_TOPICS_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton searchContentSpecs = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchContentSpecs(), Constants.ElementIDs.SEARCH_CONTENT_SPECS_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton searchTranslations = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTranslations(), Constants.ElementIDs.SEARCH_TRANSLATIONS_NAVIGATION_BUTTON_ID.getId());

    public ShortcutSearchPanel(final String header) {
        super(header);
        setOpen(Preferences.INSTANCE.getBoolean(Preferences.SHORTCUT_SEARCH_MENU_OPEN, false));
    }

    @NotNull
    public ToggleButton getSearchTranslationsButton() {
        return searchTranslations;
    }

    @NotNull
    public ToggleButton getSearchTopicsButton() {
        return searchTopics;
    }

    @NotNull
    public ToggleButton getSearchContentSpecsButton() {
        return searchContentSpecs;
    }

    public void initialise() {
        add(searchTopics);
        add(searchContentSpecs);
        add(searchTranslations);
    }
}
