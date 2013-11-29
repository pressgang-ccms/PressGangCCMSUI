package com.google.gwt.user.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;

public class AnchorMenuItem extends MenuItem {
    private static final String DEFAULT_ANCHOR_STYLE = "AnchorMenuItem";
    private Anchor anchor = new Anchor();

    public AnchorMenuItem(SafeHtml html) {
        super(html);
        init();
    }

    public AnchorMenuItem(SafeHtml html, Scheduler.ScheduledCommand cmd) {
        super(html, cmd);
        init();
    }

    public AnchorMenuItem(SafeHtml html, MenuBar subMenu) {
        super(html, subMenu);
        init();
    }

    public AnchorMenuItem(String text, boolean asHTML, Scheduler.ScheduledCommand cmd) {
        super(text, asHTML, cmd);
        init();
    }

    public AnchorMenuItem(String text, boolean asHTML, MenuBar subMenu) {
        super(text, asHTML, subMenu);
        init();
    }

    public AnchorMenuItem(String text, Scheduler.ScheduledCommand cmd) {
        super(text, cmd);
        init();
    }

    public AnchorMenuItem(String text, MenuBar subMenu) {
        super(text, subMenu);
        init();
    }

    AnchorMenuItem(String text, boolean asHTML) {
        super(text, asHTML);
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
