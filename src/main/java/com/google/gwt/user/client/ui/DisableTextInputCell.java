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

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * A version of the EditTextCell element that can be disabled.
 */
public final class DisableTextInputCell extends TextInputCell {

    /**
     * true if the text bos should permit editing, and false otherwise
     */
    private boolean enabled;

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (enabled) {
            super.render(context, value, sb);
        } else {
            sb.append(SafeHtmlUtils.fromString(value));
        }
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value,
            NativeEvent event, ValueUpdater<String> valueUpdater) {
        if (enabled) {
            super.onBrowserEvent(context, parent, value, event, valueUpdater);
        }
    }

    /**
     * @param enabled true if the text bos should permit editing, and false otherwise
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
