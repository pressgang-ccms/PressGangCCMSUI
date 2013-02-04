package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Base for events that open a new view.
 *
 * @author kamiller@redhat.com (Katie Miller)
 * @param <T> The event handler that corresponds to this event.
 */
public abstract class ViewOpenEvent<T extends ViewOpenEventHandler> extends GwtEvent<T> {
    @Override
    protected final void dispatch(final T handler) {
        handler.onViewOpen(this);
    }
}


