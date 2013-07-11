package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutEntitiesView extends ShortcutDisclosurePanel {

    private final ToggleButton images = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Images());
    private final ToggleButton files = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Files());
    private final ToggleButton tags = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Tags());
    private final ToggleButton categories = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Categories());
    private final ToggleButton projects = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Projects());

    public ShortcutEntitiesView(@NotNull final String header) {
        super(header);
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
