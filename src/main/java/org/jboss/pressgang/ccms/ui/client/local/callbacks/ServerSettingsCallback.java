package org.jboss.pressgang.ccms.ui.client.local.callbacks;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTServerSettingsV1;
import org.jetbrains.annotations.NotNull;

public interface ServerSettingsCallback {
    void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings);
}
