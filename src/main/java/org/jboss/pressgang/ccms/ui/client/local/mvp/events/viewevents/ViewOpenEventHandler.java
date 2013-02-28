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
     * @param historyToken The history token to be appended to the URL.
     */
    public ViewOpenEventHandler(@NotNull final String historyToken) {
        this.historyToken = historyToken;
    }

    /**
     * Changes the history token when the event is triggered.
     * @param event The event triggered to open a new view.
     */
    protected void onViewOpen(@NotNull final ViewOpenEvent<?> event) {
        History.newItem(historyToken);
    }
}
