/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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
