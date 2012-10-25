package org.jboss.pressgang.ccms.ui.client.local.ui;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
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

    public static PushButton createPushButton(final String text, final boolean subMenu) {
        final PushButton retvalue = new PushButton(text);
        retvalue.addStyleName(CSSConstants.TEXT_BUTTON);

        if (subMenu) {
            retvalue.addStyleName(CSSConstants.SUB_MENU);
        }

        return retvalue;
    }

    public static ToggleButton createToggleButton(final String text, final boolean subMenu) {
        final ToggleButton retvalue = new ToggleButton(text);
        retvalue.addStyleName(CSSConstants.TEXT_BUTTON);

        if (subMenu) {
            retvalue.addStyleName(CSSConstants.SUB_MENU);
        }

        return retvalue;
    }

    public static ToggleButton createToggleButton(final String text) {
        return createToggleButton(text, false);
    }

    public static Label createDownLabel(final String text) {
        final Label retvalue = new Label(text);
        retvalue.addStyleName(CSSConstants.DOWN_LABEL);
        return retvalue;
    }
    
    /**
     * @return a new SimplePager with the default paging settings
     */
    public static SimplePager createSimplePager()
    {
        return new SimplePager(TextLocation.CENTER, true, Constants.FAST_FORWARD_ROWS, true);
    }
}
