package org.jboss.pressgang.ccms.ui.client.local.callbacks;

import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * The callback that is triggered when the server details have been found
 */
public interface AllServerDetailsCallback {
    void serverDetailsFound(@NotNull final Map<Integer, ServerDetails> serverDetails);
}
