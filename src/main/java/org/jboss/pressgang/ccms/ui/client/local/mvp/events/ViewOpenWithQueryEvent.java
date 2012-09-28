package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

/**
 * Base for events that open a new view and run a query.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenWithQueryEvent extends ViewOpenEvent<ViewOpenWithQueryEventHandler> {
    protected final String query;

    public ViewOpenWithQueryEvent(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
