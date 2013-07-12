package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.shortcut.ShortcutDisclosurePanel;
import org.jetbrains.annotations.NotNull;

public class ShortcutAdvancedPanel extends ShortcutDisclosurePanel {
    private final ToggleButton bulkTagging = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.BulkTagging());
    private final ToggleButton stringConstants = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.StringConstants());
    private final ToggleButton blobConstants = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.BlobConstants());
    private final ToggleButton integerConstants = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.IntegerConstants());
    private final ToggleButton propertyTags = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.PropertyTags());
    private final ToggleButton propertyTagCategories = UIUtilities.createLeftSideTabToggleButton(PressGangCCMSUI.INSTANCE.PropertyTagCategories());
    private final PushButton monitoring = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Monitoring(), false, true);

    public ShortcutAdvancedPanel(final String header) {
        super(header);
    }

    @NotNull
    public ToggleButton getBulkTaggingButton() {
        return bulkTagging;
    }

    @NotNull
    public ToggleButton getStringConstantsButton() {
        return stringConstants;
    }

    @NotNull
    public ToggleButton getPropertyTagCategoriesButton() {
        return propertyTagCategories;
    }

    @NotNull
    public ToggleButton getPropertyTagsButton() {
        return propertyTags;
    }

    @NotNull
    public ToggleButton getIntegerConstantsButton() {
        return integerConstants;
    }

    @NotNull
    public ToggleButton getBlobConstantsButton() {
        return blobConstants;
    }

    @NotNull
    public PushButton getMonitoringButton() {
        return monitoring;
    }

    public void initialise() {
        // Advanced Sub Menu
        add(bulkTagging);
        add(stringConstants);
        add(blobConstants);
        add(integerConstants);
        add(propertyTags);
        add(propertyTagCategories);
        add(monitoring);
    }
}
