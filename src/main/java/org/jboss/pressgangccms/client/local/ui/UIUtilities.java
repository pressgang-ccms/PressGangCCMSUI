package org.jboss.pressgangccms.client.local.ui;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * This class contains helper methods for creating and managing UI elements
 * 
 * @author Matthew Casperson
 * 
 */
final public class UIUtilities {
    private UIUtilities() {

    }

    public static PushButton createPushButton(final String text) {
        return createPushButton(text, false);
    }

    public static PushButton createPushButton(final String text, boolean subMenu) {
        final PushButton retvalue = new PushButton(text);
        retvalue.addStyleName(CSSConstants.TEXTBUTTON);

        if (subMenu)
            retvalue.addStyleName(CSSConstants.SUBMENU);

        return retvalue;
    }

    public static ToggleButton createToggleButton(final String text, final boolean subMenu) {
        final ToggleButton retvalue = new ToggleButton(text);
        retvalue.addStyleName(CSSConstants.TEXTBUTTON);

        if (subMenu)
            retvalue.addStyleName(CSSConstants.SUBMENU);

        return retvalue;
    }

    public static ToggleButton createToggleButton(final String text) {
        return createToggleButton(text, false);
    }

    public static Label createDownLabel(final String text) {
        final Label retvalue = new Label(text);
        retvalue.addStyleName(CSSConstants.DOWNLABEL);
        return retvalue;
    }
}
