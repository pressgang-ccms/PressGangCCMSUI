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

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

/**
 * A version of the EditTextCell element that can be disabled.
 */
public final class DisableEditTextCell extends EditTextCell {
    /**
     * true if the text bos should permit editing, and false otherwise
     */
    private boolean enabled;

    @Override
    public void onBrowserEvent(final Context context, final Element parent, final String value, final NativeEvent event, final ValueUpdater<String> valueUpdater) {
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
