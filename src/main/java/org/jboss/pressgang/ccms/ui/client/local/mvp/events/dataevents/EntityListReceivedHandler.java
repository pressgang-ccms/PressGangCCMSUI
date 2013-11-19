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
