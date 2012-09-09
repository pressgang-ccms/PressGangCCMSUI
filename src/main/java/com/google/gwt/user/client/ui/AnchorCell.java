package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * 
 * {@link Cell} that wraps a {@link Hyperlink}
 * 
 * WARNING: make sure the contents of your Hyperlink really are safe from XSS!
 * https://groups.google.com/forum/?fromgroups=#!topic/google-web-toolkit/C18YWWkVbHw
 */

public class AnchorCell extends AbstractCell<Anchor>
{
	@Override
	public void render(final com.google.gwt.cell.client.Cell.Context context, final Anchor link, final SafeHtmlBuilder sb)
	{
		sb.append(SafeHtmlUtils.fromTrustedString(link.toString()));
	}
}
