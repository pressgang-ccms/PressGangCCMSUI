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

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * A version of ButtonCell that can display a disabled button.
 *
 * @author Matthew Casperson
 */
public final class DisableableButtonCell extends ButtonCell {
    /**
     * A HTML string representation of an unchecked input box.
     */
    private static final String INPUT_ENABLED = "<button type=\"button\" tabindex=\"-1\">";

    /**
     * A HTML string representation of a disabled checked input box.
     */
    private static final String INPUT_DISABLED = "<button type=\"button\" tabindex=\"-1\" disabled=\"disabled\">";

    /**
     * The close tag
     */
    private static final String INPUT_CLOSE = "</button>";

    /**
     * whether or not the button is enabled.
     */
    private boolean enabled = true;

    /**
     * @return whether or not the button is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled whether or not the button is enabled
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Default constructor. Does nothing.
     */
    public DisableableButtonCell() {

    }

    @Override
    public void render(final Context context, @NotNull final SafeHtml value, @NotNull final SafeHtmlBuilder sb) {
        if (enabled) {
            sb.appendHtmlConstant(INPUT_ENABLED + value.asString() + INPUT_CLOSE);
        } else {
            sb.appendHtmlConstant(INPUT_DISABLED + value.asString() + INPUT_CLOSE);
        }
    }
}