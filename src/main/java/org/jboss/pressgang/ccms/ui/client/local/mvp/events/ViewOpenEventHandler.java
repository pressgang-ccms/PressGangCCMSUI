package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.History;

/**
 * Event handler for standard events that open a new view.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenEventHandler implements EventHandler {

    protected String historyToken;

    public ViewOpenEventHandler(String historyToken) {
        this.historyToken = historyToken;
    }

    void onViewOpen(final ViewOpenEvent event) {
        History.newItem(historyToken);
    }
}
