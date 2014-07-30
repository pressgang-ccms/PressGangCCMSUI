/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.server;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class to hold a list of server details that can be used to fall back on.
 */
public class ServerGroup {
    private final String serverType;
    private final List<ServerDetails> serverDetails = new ArrayList<ServerDetails>();

    public ServerGroup(@NotNull final String serverType) {
        this.serverType = serverType;
    }

    public String getType() {
        return serverType;
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

    @Override
    public boolean equals(@Nullable final Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof ServerGroup)) {
            return false;
        }

        final ServerGroup otherServerGroup = (ServerGroup)other;

        if (this.serverType == null && otherServerGroup.serverType == null) {
            return true;
        }

        if (this.serverType != null && otherServerGroup.serverType == null) {
            return false;
        }

        if (this.serverType == null && otherServerGroup.serverType != null) {
            return false;
        }

        return this.serverType.equals(otherServerGroup.serverType);
    }
}
