package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A version of ButtonCell that can display a disabled button.
 * 
 * @author Matthew Casperson
 */
public class DisableableButtonCell extends ButtonCell {
    /**
     * A HTML string representation of an unchecked input box.
     */
    private static final String INPUT_ENABLED = "<button type=\"button\" tabindex=\"-1\">";

    /**
     * A HTML string representation of a disabled checked input box.
     */
    private static final String INPUT_DISABLED = "<button type=\"button\" tabindex=\"-1\" disabled=\"disabled\">";

    /** The close tag */
    private static final String INPUT_CLOSE = "</button>";

    /** whether or not the button is enabled */
    private boolean enabled = true;

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public DisableableButtonCell() {

    }

    @Override
    public void render(final Context context, final SafeHtml value, final SafeHtmlBuilder sb) {
        if (enabled) {
            sb.appendHtmlConstant(INPUT_ENABLED + value.asString() + INPUT_CLOSE);
        } else {
            sb.appendHtmlConstant(INPUT_DISABLED + value.asString() + INPUT_CLOSE);
        }
    }
}