package org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Defines an event to be triggered when the UI fails over to a new server.
 */
public class FailoverEvent extends GwtEvent<FailoverEventHandler> {
    private static final Type TYPE = new Type<FailoverEventHandler>();

    private final int id;

    @NotNull
    public static Type getType() {
        return TYPE;
    }

    public FailoverEvent(final int id) {
        this.id = id;
    }

    @Override
    public Type<FailoverEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(@NotNull final FailoverEventHandler handler) {
        handler.onFailOverEvent();
    }

    public int getId() {
        return id;
    }
}
