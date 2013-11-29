package com.google.gwt.user.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;

public class AnchorPushButton extends PushButton {
    private static final String DEFAULT_ANCHOR_STYLE = "AnchorPushButton";
    private Anchor anchor = new Anchor();

    /**
     * Constructor for <code>AnchorPushButton</code>.
     */
    public AnchorPushButton() {
        init();
    }

    /**
     * Constructor for <code>AnchorPushButton</code>. The supplied text is used to
     * construct the default face of the button.
     *
     * @param upText the text for the default (up) face of the button.
     */
    public AnchorPushButton(String upText) {
        super(upText);
        init();
    }

    /**
     * Constructor for <code>AnchorPushButton</code>. The supplied text is used to
     * construct the default face of the button.
     *
     * @param upText the text for the default (up) face of the button
     * @param handler the click handler
     */
    public AnchorPushButton(String upText, ClickHandler handler) {
        super(upText, handler);
        init();
    }

    /**
     * Constructor for <code>AnchorPushButton</code>.
     *
     * @param upImage image for the default(up) face of the button
     */
    public AnchorPushButton(Image upImage) {
        super(upImage);
        init();
    }

    /**
     * Constructor for <code>AnchorPushButton</code>. The supplied image is used to
     * construct the default face of the button.
     *
     * @param upImage image for the default (up) face of the button
     * @param handler the click handler
     */
    public AnchorPushButton(Image upImage, ClickHandler handler) {
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
}
