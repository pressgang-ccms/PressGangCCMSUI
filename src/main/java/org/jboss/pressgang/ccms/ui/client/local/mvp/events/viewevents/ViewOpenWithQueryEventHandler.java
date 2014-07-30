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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import org.jetbrains.annotations.NotNull;

/**
 * Event handler for events that open a new view and run a query.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenWithQueryEventHandler extends ViewOpenEventHandler {

    /**
     * Initialize the event handler.
     *
     * @param historyToken The history token to be appended to the URL.
     */
    public ViewOpenWithQueryEventHandler(@NotNull final String historyToken) {
        super(historyToken);
    }

    /**
     * Set the url, which in turn will trigger a presenter to be loaded and the view to be displayed.
     *
     * @param event The event that this handler is responding to
     */
    protected final void onViewOpen(@NotNull final ViewOpenWithQueryEvent<?> event) {
        if (event.isNewWindow()) {
            final String newWindowURL = Window.Location.getProtocol() + "//" + Window.Location.getHost() + "/"
                    + Window.Location.getPath() + "#" + historyToken + ";"
                    + ((event.getQuery() != null) ? event.getQuery() : "");

            Window.open(newWindowURL, "_blank", "");
        } else {
            final String token = historyToken + ";" + ((event.getQuery() != null) ? event.getQuery() : "");

            if (History.getToken().equals(token)) {
                History.fireCurrentHistoryState();
            } else {
                History.newItem(token);
            }
        }
    }
}
