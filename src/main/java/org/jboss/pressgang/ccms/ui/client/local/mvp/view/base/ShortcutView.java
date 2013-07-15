package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutPanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutView extends ShortcutPanel {
    /**
     * The panel that holds the shortcut buttons visible when the advanced submenu is open.
     */
    private final ShortcutAdvancedPanel advancedShortcutPanel = new ShortcutAdvancedPanel(PressGangCCMSUI.INSTANCE.Advanced());

    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutEntitiesPanel entitiesShortcutPanel = new ShortcutEntitiesPanel(PressGangCCMSUI.INSTANCE.Entities());
    /**
     * The panel that holds the shortcut buttons visible when the entities submenu is open.
     */
    private final ShortcutSearchPanel searchShortcutPanel = new ShortcutSearchPanel(PressGangCCMSUI.INSTANCE.Search());

    private final ToggleButton home = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.Home());
    private final ToggleButton docbuilder = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.DocBuilder());
    private final ToggleButton createTopic = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateTopic());
    private final ToggleButton createContentSpec = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.CreateContentSpec());
    private final PushButton bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true);
    private final PushButton reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true);

    private boolean spacerEnabled = false;
    private SimplePanel spacer = new SimplePanel();

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

        // Only add the reports button if the URL is not null
        if (ServerDetails.getSavedServer().getReportUrl() != null) {
            add(reports);
        }
        add(bug);
        add(searchShortcutPanel);
        add(entitiesShortcutPanel);
        add(advancedShortcutPanel);
    }

    public void setSpacerEnabled(boolean enabled) {
        if (enabled && !spacerEnabled) {
            insert(spacer, 0);
            setCellHeight(spacer, Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px");
            spacerEnabled = true;
        } else if (!enabled && spacerEnabled) {
            remove(spacer);
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
        final FlowPanel panel = new FlowPanel();
        panel.add(button);
        spacer.setWidget(panel);
    }
}
