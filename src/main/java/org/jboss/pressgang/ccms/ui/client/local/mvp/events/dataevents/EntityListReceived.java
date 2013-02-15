package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;

/**
 * Represents an event that is raised when the topics from a query have been returned from the
 * REST server.
 */
public class EntityListReceived<T extends RESTBaseCollectionV1<?, ?, ?>> extends GwtEvent<EntityListReceivedHandler<T>> {

    private static final Type TYPE = new Type<EntityListReceivedHandler>();
    private final T topics;

    public static Type getType() {
        return TYPE;
    }

    public EntityListReceived(final T topics)
    {
        this.topics = topics;
    }


    @Override
    public Type<EntityListReceivedHandler<T>> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final EntityListReceivedHandler<T> collectionRecievedHandler) {
        collectionRecievedHandler.onCollectionRecieved(topics);
    }
}
