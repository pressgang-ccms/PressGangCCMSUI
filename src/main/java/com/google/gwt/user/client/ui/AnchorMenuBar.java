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
