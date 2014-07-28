/*
  Copyright 2011-2014 Red Hat

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

package com.google.gwt.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.impl.HyperlinkImpl;

public class AnchorMenuBar extends MenuBar {
    private static final HyperlinkImpl hyperlinkImpl = GWT.create(HyperlinkImpl.class);

    /**
     * Creates an empty horizontal menu bar.
     */
    public AnchorMenuBar() {
        this(false);
    }

    /**
     * Creates an empty menu bar.
     *
     * @param vertical <code>true</code> to orient the menu bar vertically
     */
    public AnchorMenuBar(boolean vertical) {
        this(vertical, GWT.<Resources> create(Resources.class));
    }


    /**
     * Creates an empty menu bar that uses the specified ClientBundle for menu
     * images.
     *
     * @param vertical <code>true</code> to orient the menu bar vertically
     * @param resources a bundle that provides images for this menu
     */
    public AnchorMenuBar(boolean vertical, Resources resources) {
        super(vertical, resources);
    }


    /**
     * Creates an empty horizontal menu bar that uses the specified ClientBundle
     * for menu images.
     *
     * @param resources a bundle that provides images for this menu
     */
    public AnchorMenuBar(Resources resources) {
        this(false, resources);
    }

    @Override
    public void onBrowserEvent(Event event) {
        // Override click events so that only normal clicks are handled.
        if (DOM.eventGetType(event) == Event.ONCLICK) {
            if (hyperlinkImpl.handleAsClick(event)) {
                DOM.eventCancelBubble(event, true);
                DOM.eventPreventDefault(event);
                super.onBrowserEvent(event);
            }
        } else {
            super.onBrowserEvent(event);
        }
    }
}
