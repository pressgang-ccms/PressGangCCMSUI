package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.EventHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jetbrains.annotations.NotNull;

/**
 * An event handler for events raised when topics have been loaded from the REST server.
 */
public interface EntityListReceivedHandler<T extends RESTBaseCollectionV1<?, ?, ?>> extends EventHandler {
    void onCollectionRecieved(@NotNull final T topics);
}
