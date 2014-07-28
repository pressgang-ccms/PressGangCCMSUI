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

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutEntitiesPanel extends ShortcutDisclosurePanel {

    private final ToggleButton images = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Images(), Constants.ElementIDs.IMAGES_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton files = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Files(), Constants.ElementIDs.FILES_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton tags = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Tags(), Constants.ElementIDs.TAGS_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton categories = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Categories(), Constants.ElementIDs.CATEGORIES_NAVIGATION_BUTTON_ID.getId());
    private final ToggleButton projects = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Projects(), Constants.ElementIDs.PROJECTS_NAVIGATION_BUTTON_ID.getId());

    public ShortcutEntitiesPanel(@NotNull final String header) {
        super(header);
        setOpen(Preferences.INSTANCE.getBoolean(Preferences.SHORTCUT_ENTITIES_MENU_OPEN, false));
    }

    @NotNull
    public ToggleButton getProjectsButton() {
        return projects;
    }

    @NotNull
    public ToggleButton getCategoriesButton() {
        return categories;
    }

    @NotNull
    public ToggleButton getTagsButton() {
        return tags;
    }

    @NotNull
    public ToggleButton getImagesButton() {
        return images;
    }

    @NotNull
    public ToggleButton getFilesButton() {
        return files;
    }

    public void initialise() {
        add(images);
        add(files);
        add(tags);
        add(categories);
        add(projects);
    }
}
