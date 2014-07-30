/*
  Copyright 2011-2014 Red Hat, Inc

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

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import org.jetbrains.annotations.NotNull;

/**
 * A version of CheckboxCell that can display a disabled checkbox.
 *
 * @author Matthew Casperson
 */
public final class DisableableCheckboxCell extends CheckboxCell {
    /**
     * A HTML string representation of a checked input box.
     */
    private static final SafeHtml INPUT_CHECKED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/>");

    /**
     * A HTML string representation of an unchecked input box.
     */
    private static final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/>");

    /**
     * A HTML string representation of a disabled checked input box.
     */
    private static final SafeHtml INPUT_CHECKED_DISABLED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked disabled=\"disabled\"/>");

    /**
     * A HTML string representation of an disabled unchecked input box.
     */
    private static final SafeHtml INPUT_UNCHECKED_DISABLED = SafeHtmlUtils
            .fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" disabled=\"disabled\"/>");

    private final boolean enabled;

    public DisableableCheckboxCell(final boolean enabled) {
        this.enabled = enabled;
    }

    public DisableableCheckboxCell(final boolean enabled, final boolean dependsOnSelection, final boolean handlesSelection) {
        super(dependsOnSelection, handlesSelection);
        this.enabled = enabled;
    }

    @Override
    public void render(final Context context, final Boolean value, @NotNull final SafeHtmlBuilder sb) {
        if (value && !enabled) {
            sb.append(INPUT_CHECKED_DISABLED);
        } else if (!value && !enabled) {
            sb.append(INPUT_UNCHECKED_DISABLED);
        } else if (value && enabled) {
            sb.append(INPUT_CHECKED);
        } else if (!value && enabled) {
            sb.append(INPUT_UNCHECKED);
        }
    }
}