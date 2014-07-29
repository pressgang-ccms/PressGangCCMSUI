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

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;

public class IntegerTextInputHeader extends TextInputHeader {
    private static final int ZERO_KEY_CODE = 48;
    private static final int NINE_KEY_CODE = 57;
    private static final int INSERT_KEY_CODE = 45;
    private static final int MINUS_KEY_CODE = 189;
    private static final int NUMPAD_MINUS_KEY_CODE = 109;
    private static final int NUMPAD_0 = 96;
    private static final int NUMPAD_9 = 105;

    public IntegerTextInputHeader() {
        this(new TextInputCell());
    }

    public IntegerTextInputHeader(final TextInputCell inputCell) {
        super(inputCell);
    }

    /**
     * Handle a browser event that took place within the header.
     *
     * @param context the context of the header
     * @param elem the parent Element
     * @param event the native browser event
     */
    @Override
    public void onBrowserEvent(Cell.Context context, Element elem, NativeEvent event) {
        if (event instanceof Event) {
            final int keyCode = event.getKeyCode();
            final int type = ((Event) event).getTypeInt();

            if ((type & Event.KEYEVENTS) == 0 || isKeyCodeValid(keyCode, event)) {
                super.onBrowserEvent(context, elem, event);
            } else {
                event.preventDefault();
            }
        } else {
            super.onBrowserEvent(context, elem, event);
        }
    }

    private boolean isKeyCodeValid(final int keyCode, final NativeEvent event) {
        /* Allow navigation keys */
        if (keyCode == KeyCodes.KEY_DELETE ||
                keyCode == KeyCodes.KEY_BACKSPACE ||
                keyCode == KeyCodes.KEY_LEFT ||
                keyCode == KeyCodes.KEY_RIGHT ||
                keyCode == KeyCodes.KEY_UP ||
                keyCode == KeyCodes.KEY_DOWN ||
                keyCode == KeyCodes.KEY_HOME ||
                keyCode == KeyCodes.KEY_END ||
                keyCode == KeyCodes.KEY_ENTER ||

                (event.getCtrlKey() && keyCode == 'V') ||                  // paste
                (event.getCtrlKey() && keyCode == 'C') ||                 // copy
                (event.getCtrlKey() && keyCode == 'X') ||                 // cut
                (event.getCtrlKey() && keyCode == INSERT_KEY_CODE) ||     // copy
                (event.getShiftKey() && keyCode == INSERT_KEY_CODE)) {      // paste
            return true;
        }

        /* Allow numeric keys */
        if (keyCode >= ZERO_KEY_CODE && keyCode <= NINE_KEY_CODE) {
            return true;
        }

        /* Allow keypad keys */
        if (keyCode >= NUMPAD_0 && keyCode <= NUMPAD_9) {
            return true;
        }

        /* Allow minus keys */
        if (keyCode == MINUS_KEY_CODE || keyCode == NUMPAD_MINUS_KEY_CODE) {
            return true;
        }

        return false;
    }
}
