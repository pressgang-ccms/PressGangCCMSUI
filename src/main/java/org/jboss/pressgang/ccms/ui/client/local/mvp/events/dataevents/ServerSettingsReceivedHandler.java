package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.EventHandler;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTServerSettingsV1;

public interface ServerSettingsReceivedHandler extends EventHandler {
    void onSettingsReceived(final RESTServerSettingsV1 serverSettings);
}
