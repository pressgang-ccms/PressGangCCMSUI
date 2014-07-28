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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.History;
import org.jetbrains.annotations.NotNull;

/**
 * Event handler for standard events that open a new view.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenEventHandler implements EventHandler {
    /**
     * The history token to be appended to the URL.
     */
    protected String historyToken;

    /**
     * Initialize the event.
     *
     * @param historyToken The history token to be appended to the URL.
     */
    public ViewOpenEventHandler(@NotNull final String historyToken) {
        this.historyToken = historyToken;
    }

    /**
     * Changes the history token when the event is triggered.
     *
     * @param event The event triggered to open a new view.
     */
    protected void onViewOpen(@NotNull final ViewOpenEvent<?> event) {
        if (History.getToken().equals(historyToken)) {
            History.fireCurrentHistoryState();
        } else {
            History.newItem(historyToken);
        }
    }
}
