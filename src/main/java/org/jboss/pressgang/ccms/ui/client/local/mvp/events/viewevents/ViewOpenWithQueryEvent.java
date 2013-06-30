package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Base for events that open a new view and run a query.
 *
 * @param <T> The event handler that corresponds to this event.
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenWithQueryEvent<T extends ViewOpenWithQueryEventHandler> extends GwtEvent<T> {
    /**
     * The query to be passed in the URL history token, and then ultimately onto the REST service.
     */
    @NotNull
    protected final String query;
    /**
     * true if the event should trigger a new window, false otherwise
     */
    private final boolean newWindow;

    /**
     * @return true if the event should trigger a new window, false otherwise
     */
    public boolean isNewWindow() {
        return newWindow;
    }

    /**
     * @param query     The query to be passed in the URL history token, and then ultimately onto the REST service.
     * @param newWindow true if the event should trigger a new window, false otherwise
     */
    public ViewOpenWithQueryEvent(@NotNull final String query, final boolean newWindow) {
        this.query = query;
        this.newWindow = newWindow;
    }

    /**
     * @return The query to be passed in the URL history token, and then ultimately onto the REST service
     */
    @NotNull
    public String getQuery() {
        return query;
    }

    @Override
    protected void dispatch(@NotNull final T handler) {
        handler.onViewOpen(this);
    }
}
