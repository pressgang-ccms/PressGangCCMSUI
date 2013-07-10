package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutPanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutView extends ShortcutPanel {
    /**
     * The panel that holds the shortcut buttons visible when the advanced submenu is open.
     */
    private final ShortcutAdvancedView advancedShortcutPanel = new ShortcutAdvancedView();

    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutDisclosurePanel entitiesDisclosurePanel = new ShortcutDisclosurePanel(PressGangCCMSUI.INSTANCE.Entities());
    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutDisclosurePanel searchDisclosurePanel = new ShortcutDisclosurePanel(PressGangCCMSUI.INSTANCE.Search());

    private final ToggleButton home = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Home());
    private final ToggleButton docbuilder = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.DocBuilder());
    private final ToggleButton createTopic = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateTopic());
    private final ToggleButton createContentSpec = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateContentSpec());
    private final ToggleButton searchTopics = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTopics());
    private final ToggleButton searchContentSpecs = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchContentSpecs());
    private final ToggleButton searchTranslations = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTranslations());
    private final ToggleButton images = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Images());
    private final ToggleButton files = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Files());
    private final ToggleButton tags = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Tags());
    private final ToggleButton categories = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Categories());
    private final ToggleButton projects = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Projects());
    private final PushButton bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true);
    private final PushButton reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true);
    private final PushButton advanced = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Advanced(), true);

    private boolean spacerEnabled = false;
    private SimplePanel spacer = new SimplePanel();

    public ShortcutView() {
        spacer.setHeight(Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px");
    }

    @NotNull
    public ShortcutAdvancedView getAdvancedSubMenu() {
        return advancedShortcutPanel;
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
    public PushButton getAdvancedSubMenuButton() {
        return advanced;
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

    @NotNull
    public PushButton getReportsButton() {
        return reports;
    }

    @NotNull
    public ToggleButton getSearchTranslationsButton() {
        return searchTranslations;
    }

    @NotNull
    public PushButton getBugButton() {
        return bug;
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
        advancedShortcutPanel.initialise();

        add(home);
        add(docbuilder);
        add(createTopic);
        add(createContentSpec);
        add(searchDisclosurePanel);
        add(entitiesDisclosurePanel);

        // Only add the reports button if the URL is not null
        if (Constants.BIRT_URL != null) {
            add(reports);
        }
        add(bug);
        addSubMenu(advancedShortcutPanel, advanced);

        // Search
        searchDisclosurePanel.add(searchTopics);
        searchDisclosurePanel.add(searchContentSpecs);
        searchDisclosurePanel.add(searchTranslations);

        // Entities
        entitiesDisclosurePanel.add(images);
        entitiesDisclosurePanel.add(files);
        entitiesDisclosurePanel.add(tags);
        entitiesDisclosurePanel.add(categories);
        entitiesDisclosurePanel.add(projects);
    }

    public void showAdvancedSubMenu() {
        showSubMenu(advancedShortcutPanel);
    }

    public void hideAdvancedSubMenu() {
        showBaseMenu();
    }

    public void openSearchMenu() {
        searchDisclosurePanel.setOpen(true);
    }

    public void closeSearchMenu() {
        searchDisclosurePanel.setOpen(false);
    }

    public void openEntitiesMenu() {
        entitiesDisclosurePanel.setOpen(true);
    }

    public void closeEntitiesMenu() {
        entitiesDisclosurePanel.setOpen(false);
    }

    public void setSpacerEnabled(boolean enabled) {
        if (enabled && !spacerEnabled) {
            final SimplePanel advancedSpacer = new SimplePanel();
            advancedSpacer.setHeight(Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px");

            insert(spacer, 0);
            advancedShortcutPanel.insert(advancedSpacer, 0);
            spacerEnabled = true;
        } else if (!enabled && spacerEnabled) {
            remove(spacer);
            advancedShortcutPanel.remove(advancedShortcutPanel.getWidget(0));
            spacerEnabled = false;
        }
    }

    public boolean isSpacerEnabled() {
        return spacerEnabled;
    }

    /**
     * When combining views into a single merged view, the shortcut panels need to have a spacer placed above them to replace
     * the template action bar, which is removed.
     * <p/>
     * The button supplied to this method is placed in the spacer cell.
     */
    public void setSpacerButton(@NotNull final PushButton button) {
        // Ensure that the spacer is being shown
        if (!isSpacerEnabled()) {
            setSpacerEnabled(true);
        }
        spacer.setWidget(button);
    }
}
