package com.google.gwt.user.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;

public class AnchorMenuItem extends MenuItem {
    private static final String DEFAULT_ANCHOR_STYLE = "AnchorMenuItem";
    private final Anchor anchor = new Anchor();
    private final SimplePanel div = new SimplePanel();
    private final Label text = new Label();

    public AnchorMenuItem(SafeHtml html) {
        this(html.asString(), true);
    }

    public AnchorMenuItem(SafeHtml html, Scheduler.ScheduledCommand cmd) {
        this(html.asString(), true, cmd);
    }

    public AnchorMenuItem(SafeHtml html, MenuBar subMenu) {
        this(html.asString(), true, subMenu);
    }

    public AnchorMenuItem(String text, boolean asHTML, Scheduler.ScheduledCommand cmd) {
        this(text, asHTML);
        setScheduledCommand(cmd);
    }

    public AnchorMenuItem(String text, boolean asHTML, MenuBar subMenu) {
        this(text, asHTML);
        setSubMenu(subMenu);
    }

    public AnchorMenuItem(String text, Scheduler.ScheduledCommand cmd) {
        this(text, false);
        setScheduledCommand(cmd);
    }

    public AnchorMenuItem(String text, MenuBar subMenu) {
        this(text, false);
        setSubMenu(subMenu);
    }

    AnchorMenuItem(String text, boolean asHTML) {
        super("", true);
        init();
        if (asHTML) {
            setHTML(text);
        } else {
            setText(text);
        }
    }

    private void init() {
        // Create the div to hold the new content
        div.addStyleName(DEFAULT_ANCHOR_STYLE);

        // Setup the anchor in the div
        div.setWidget(anchor);
        div.getElement().appendChild(text.getElement());
        anchor.addStyleName(DEFAULT_ANCHOR_STYLE);

        // Add the div to the menu item
        getElement().appendChild(div.getElement());
    }

    @Override
    public void setText(String text) {
        if (getElement().getFirstChildElement() != null) {
            DOM.setInnerText(this.text.getElement(), text);
        } else {
            super.setText(text);
        }
    }

    @Override
    public String getText() {
        if (getElement().getFirstChildElement() != null) {
            return DOM.getInnerText(text.getElement());
        } else {
            return super.getText();
        }
    }

    @Override
    public void setHTML(String html) {
        if (getElement().getFirstChildElement() != null) {
            DOM.setInnerHTML(div.getElement(), html);
        } else {
            super.setHTML(html);
        }
    }

    public void setHref(final String href) {
        anchor.setHref(href);
    }

    public void setTarget(final String target) {
        anchor.setTarget(target);
    }
}
