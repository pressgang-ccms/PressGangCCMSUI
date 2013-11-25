package org.jboss.pressgang.ccms.ui.client.local.callbacks;

import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;

/**
 * The callback that is triggered when the server details have been found
 */
public interface ServerDetailsCallback {
    void serverDetailsFound(@NotNull final ServerDetails serverDetails);
}
