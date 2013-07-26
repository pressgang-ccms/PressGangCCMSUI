package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutEntitiesPanel extends ShortcutDisclosurePanel {

    private final ToggleButton images = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Images(), Constants.IMAGES_NAVIGATION_BUTTON_ID);
    private final ToggleButton files = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Files(), Constants.FILES_NAVIGATION_BUTTON_ID);
    private final ToggleButton tags = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Tags(), Constants.TAGS_NAVIGATION_BUTTON_ID);
    private final ToggleButton categories = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Categories(), Constants.CATEGORIES_NAVIGATION_BUTTON_ID);
    private final ToggleButton projects = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Projects(), Constants.PROJECTS_NAVIGATION_BUTTON_ID);

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
