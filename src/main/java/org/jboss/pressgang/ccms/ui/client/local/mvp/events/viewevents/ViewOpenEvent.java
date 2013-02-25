package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Base for events that open a new view.
 *
 * @param <T> The event handler that corresponds to this event.
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenEvent<T extends ViewOpenEventHandler> extends GwtEvent<T> {
    @Override
    protected final void dispatch(@NotNull final T handler) {
        handler.onViewOpen(this);
    }
}


