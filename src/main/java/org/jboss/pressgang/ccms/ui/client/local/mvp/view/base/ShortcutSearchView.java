package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutSearchView extends ShortcutDisclosurePanel {

    private final ToggleButton searchTopics = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTopics());
    private final ToggleButton searchContentSpecs = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchContentSpecs());
    private final ToggleButton searchTranslations = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTranslations());

    public ShortcutSearchView(final String header) {
        super(header);
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
