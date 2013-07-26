package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutSearchPanel extends ShortcutDisclosurePanel {

    private final ToggleButton searchTopics = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTopics(), Constants.SEARCH_TOPICS_NAVIGATION_BUTTON_ID);
    private final ToggleButton searchContentSpecs = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchContentSpecs(), Constants.SEARCH_CONTENT_SPECS_NAVIGATION_BUTTON_ID);
    private final ToggleButton searchTranslations = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.SearchTranslations(), Constants.SEARCH_TRANSLATIONS_NAVIGATION_BUTTON_ID);

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
