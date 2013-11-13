package org.jboss.pressgang.ccms.ui.client.local.server;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

/**
 * A class to hold a list of server details that can be used to fall back on.
 */
public class ServerGroup {
    private final ServerType serverType;
    private final List<ServerDetails> serverDetails = new ArrayList<ServerDetails>();

    public ServerGroup(@NotNull final ServerType serverType) {
        this.serverType = serverType;
    }

    public ServerType getType() {
        return serverType;
    }

    public String getName() {
        return serverType.name();
    }

    public List<ServerDetails> getServerDetails() {
        return serverDetails;
    }

    public void addServer(@NotNull final ServerDetails serverDetails) {
        this.serverDetails.add(serverDetails);
    }

    public void removeServer(@NotNull final ServerDetails serverDetails) {
        this.serverDetails.remove(serverDetails);
    }
}
