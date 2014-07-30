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

package org.jboss.pressgang.ccms.ui.client.local.restcalls;

/**
 * A wrapper class around a timeout method. Used to implement request timeouts on the client side.
 */
public class CallbackWrapper {
    /**
     * true if the callback returned, either as a failure or as a success
     */
    private boolean returned = false;
    /**
     * true if the call that this callback is responding to has been marked as having timed out.
     */
    private boolean timedout = false;

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean isTimedout() {
        return timedout;
    }

    public void setTimedout(boolean timedout) {
        this.timedout = timedout;
    }
}
