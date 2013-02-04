package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.History;

/**
 * Event handler for standard events that open a new view.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenEventHandler implements EventHandler {

    protected String historyToken;

    public ViewOpenEventHandler(final String historyToken) {
        this.historyToken = historyToken;
    }

    protected void onViewOpen(final ViewOpenEvent<?> event) {
        History.newItem(historyToken);
    }
}
