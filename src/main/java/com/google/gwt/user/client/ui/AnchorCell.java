package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Cell that wraps a {@link Hyperlink}
 * WARNING: make sure the contents of your Hyperlink really are safe from XSS!
 * https://groups.google.com/forum/?fromgroups=#!topic/google-web-toolkit/C18YWWkVbHw
 */

public final class AnchorCell extends AbstractCell<Anchor> {

    /**
     * Default constructor. Does nothing.
     */
    public AnchorCell() {

    }

    @Override
    public void render(final com.google.gwt.cell.client.Cell.Context context, @NotNull final Anchor link, @NotNull final SafeHtmlBuilder sb) {
        sb.append(SafeHtmlUtils.fromTrustedString(link.toString()));
    }
}
