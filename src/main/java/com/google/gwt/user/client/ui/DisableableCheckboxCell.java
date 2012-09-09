package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * A version of CheckboxCell that can display a disabled checkbox
 * 
 * @author Matthew Casperson
 * 
 */
public class DisableableCheckboxCell extends CheckboxCell {
    /**
     * A HTML string representation of a checked input box.
     */
    private static final SafeHtml INPUT_CHECKED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/>");

    /**
     * A HTML string representation of an unchecked input box.
     */
    private static final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/>");

    /**
     * A HTML string representation of a disabled checked input box.
     */
    private static final SafeHtml INPUT_CHECKED_DISABLED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked disabled=\"disabled\"/>");

    /**
     * A HTML string representation of an disabled unchecked input box.
     */
    private static final SafeHtml INPUT_UNCHECKED_DISABLED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" disabled=\"disabled\"/>");

    private final boolean enabled;

    public DisableableCheckboxCell(final boolean enabled, final boolean dependsOnSelection, final boolean handlesSelection) {
        super(dependsOnSelection, handlesSelection);
        this.enabled = enabled;
    }

    @Override
    public void render(final Context context, final Boolean value, final SafeHtmlBuilder sb) {
        // Get the view data.
        final Object key = context.getKey();
        Boolean viewData = getViewData(key);
        if (viewData != null && viewData.equals(value)) {
            clearViewData(key);
            viewData = null;
        }

        final Boolean checked = value && (viewData != null ? viewData : value);

        if (checked && !enabled) {
            sb.append(INPUT_CHECKED_DISABLED);
        } else if (!checked && !enabled) {
            sb.append(INPUT_UNCHECKED_DISABLED);
        } else if (checked && enabled) {
            sb.append(INPUT_CHECKED);
        } else if (!checked && enabled) {
            sb.append(INPUT_UNCHECKED);
        }
    }
}