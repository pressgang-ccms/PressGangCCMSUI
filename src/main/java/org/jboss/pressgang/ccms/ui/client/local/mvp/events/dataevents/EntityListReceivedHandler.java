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

package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.EventHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jetbrains.annotations.NotNull;

/**
 * An event handler for events raised when topics have been loaded from the REST server.
 */
public interface EntityListReceivedHandler<T extends RESTBaseEntityCollectionV1<?, ?, ?>> extends EventHandler {
    /**
     * Called when a collection of entities has been loaded by a filtered results view. This event is usually
     * used to display the first item in the collection.
     *
     * @param entities The collection that was loaded.
     */
    void onCollectionReceived(@NotNull final T entities);
}
