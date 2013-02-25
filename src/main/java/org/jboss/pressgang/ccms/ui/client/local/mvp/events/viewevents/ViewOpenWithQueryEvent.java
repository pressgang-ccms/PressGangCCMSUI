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
    protected final String query;
    private final boolean newWindow;

    public final boolean isNewWindow() {
        return newWindow;
    }

    /**
     * @param query     The query that is associated with this event
     * @param newWindow true if the event should trigger a new window, false otherwise
     */
    public ViewOpenWithQueryEvent(@NotNull final String query, final boolean newWindow) {
        this.query = query;
        this.newWindow = newWindow;
    }

    public final String getQuery() {
        return query;
    }

    @Override
    protected final void dispatch(@NotNull final T handler) {
        handler.onViewOpen(this);
    }
}
