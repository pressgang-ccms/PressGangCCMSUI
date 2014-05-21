package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * A version of the EditTextCell element that can be disabled.
 */
public final class DisableTextInputCell extends TextInputCell {

    /**
     * true if the text bos should permit editing, and false otherwise
     */
    private boolean enabled;

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (enabled) {
            super.render(context, value, sb);
        } else {
            sb.append(SafeHtmlUtils.fromString(value));
        }
    }

    /**
     * @param enabled true if the text bos should permit editing, and false otherwise
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
