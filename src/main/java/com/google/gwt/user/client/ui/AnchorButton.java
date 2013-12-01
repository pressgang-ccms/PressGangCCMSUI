package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

public class AnchorButton extends CustomButton {
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
