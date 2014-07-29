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
