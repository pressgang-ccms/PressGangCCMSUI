package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Base for events that open a new view.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class ViewOpenEvent<T extends ViewOpenEventHandler> extends GwtEvent<T> {
    @Override
    protected void dispatch(T handler) {
        handler.onViewOpen(this);
    }
}


