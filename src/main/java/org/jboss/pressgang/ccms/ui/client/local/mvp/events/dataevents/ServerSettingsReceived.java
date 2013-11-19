package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTServerSettingsV1;
import org.jetbrains.annotations.NotNull;

public class ServerSettingsReceived extends GwtEvent<ServerSettingsReceivedHandler> {
    private static final Type TYPE = new Type<ServerSettingsReceivedHandler>();

    private final RESTServerSettingsV1 serverSettings;

    @NotNull
    public static Type getType() {
        return TYPE;
    }

    public ServerSettingsReceived(final RESTServerSettingsV1 serverSettings) {
        this.serverSettings = serverSettings;
    }

    @Override
    public Type<ServerSettingsReceivedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ServerSettingsReceivedHandler handler) {
        handler.onSettingsReceived(serverSettings);
    }
}
