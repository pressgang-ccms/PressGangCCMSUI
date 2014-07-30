/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package com.google.gwt.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.impl.HyperlinkImpl;

public class AnchorButton extends CustomButton {
    private static final HyperlinkImpl hyperlinkImpl = GWT.create(HyperlinkImpl.class);
    private static final String DEFAULT_ANCHOR_STYLE = "AnchorPushButton";
    private Anchor anchor = new Anchor();

    /**
     * Constructor for <code>AnchorButton</code>.
     */
    public AnchorButton() {
        init();
    }

    /**
     * Constructor for <code>AnchorButton</code>. The supplied text is used to
     * construct the default face of the button.
     *
     * @param upText the text for the default (up) face of the button.
     */
    public AnchorButton(String upText) {
        super(upText);
        init();
    }

    /**
     * Constructor for <code>AnchorButton</code>. The supplied text is used to
     * construct the default face of the button.
     *
     * @param upText the text for the default (up) face of the button
     * @param handler the click handler
     */
    public AnchorButton(String upText, ClickHandler handler) {
        super(upText, handler);
        init();
    }

    /**
     * Constructor for <code>AnchorButton</code>.
     *
     * @param upImage image for the default(up) face of the button
     */
    public AnchorButton(Image upImage) {
        super(upImage);
        init();
    }

    /**
     * Constructor for <code>AnchorButton</code>. The supplied image is used to
     * construct the default face of the button.
     *
     * @param upImage image for the default (up) face of the button
     * @param handler the click handler
     */
    public AnchorButton(Image upImage, ClickHandler handler) {
        super(upImage, handler);
        init();
    }

    private void init() {
        addStyleName(DEFAULT_ANCHOR_STYLE);
        anchor.addStyleName(DEFAULT_ANCHOR_STYLE);
        getElement().appendChild(anchor.getElement());
    }

    public void setHref(final String href) {
        anchor.setHref(href);
    }

    public void setTarget(final String target) {
        anchor.setTarget(target);
    }

    @Override
    public void onBrowserEvent(Event event) {
        switch (DOM.eventGetType(event)) {
            case Event.ONMOUSEOVER:
                // Only fire the mouse over event if it's coming from outside this
                // widget.
            case Event.ONMOUSEOUT:
                // Only fire the mouse out event if it's leaving this
                // widget.
                Element related = event.getRelatedEventTarget().cast();
                if (related != null && getElement().isOrHasChild(related)) {
                    return;
                }
                break;
        }
        DomEvent.fireNativeEvent(event, this, this.getElement());
    }
}
