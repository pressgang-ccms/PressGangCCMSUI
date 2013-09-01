package org.jboss.pressgang.ccms.ui.client.local.utilities;

import com.google.gwt.core.client.JavaScriptObject;
import org.jetbrains.annotations.NotNull;

/**
 * A java frontend to the script at http://www.lalit.org/lab/javascript-css-font-detect/
 */
public class FontDetector {
    private JavaScriptObject detector;

    public FontDetector() {
        initialise();
    }

    private native void initialise() /*-{
        if ($wnd.Detector == undefined) {
            $wnd.alert("window.Detector is undefined! Please make sure you have included the appropriate JavaScript files.");
            return;
        }

        this.@org.jboss.pressgang.ccms.ui.client.local.utilities.FontDetector::detector = new $wnd.Detector();
    }-*/;

    /**
     * Detects if a font is installed on the system.
     *
     * @param font The name of the font. (The font should be enclosed in quotes for multiple words)
     * @return True if the font is available, otherwise false.
     */
    public native boolean detect(@NotNull final String font) /*-{
        var detector = this.@org.jboss.pressgang.ccms.ui.client.local.utilities.FontDetector::detector;
        if (detector != null) {
            return detector.detect(font);
        }

        return true;
    }-*/;
}
