package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.user.client.History;

/**
 * Event handler for events that open a new view and run a query.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenWithQueryEventHandler extends ViewOpenEventHandler {

    public ViewOpenWithQueryEventHandler(String historyToken) {
        super(historyToken);
    }

    void onViewOpen(final ViewOpenWithQueryEvent event) {
        History.newItem(historyToken + ";" + ((event.getQuery() != null) ? event.getQuery() : ""));
    }
}
