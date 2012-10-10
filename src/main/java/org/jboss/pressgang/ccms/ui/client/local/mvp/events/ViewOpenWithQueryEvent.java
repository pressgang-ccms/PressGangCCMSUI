package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Base for events that open a new view and run a query.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenWithQueryEvent <T extends ViewOpenWithQueryEventHandler> extends GwtEvent<T> {
    protected final String query;

    public ViewOpenWithQueryEvent(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
    
    @Override
    protected void dispatch(final T handler) {
        handler.onViewOpen(this);
    }
}
