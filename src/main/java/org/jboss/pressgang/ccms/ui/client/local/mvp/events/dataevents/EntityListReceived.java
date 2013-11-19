package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is raised when the topics from a query have been returned from the
 * REST server.
 */
public class EntityListReceived<T extends RESTBaseEntityCollectionV1<?, ?, ?>> extends GwtEvent<EntityListReceivedHandler<T>> {

    private static final Type TYPE = new Type<EntityListReceivedHandler>();
    /**
     * The collection of entities that this event relates to.
     */
    @NotNull
    private final T entities;

    @NotNull
    public static Type getType() {
        return TYPE;
    }

    /**
     * The constructor initializes the final variables.
     *
     * @param entities The collection of entities that this event relates to.
     */
    public EntityListReceived(@NotNull final T entities) {
        this.entities = entities;
    }


    @NotNull
    @Override
    public Type<EntityListReceivedHandler<T>> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(@NotNull final EntityListReceivedHandler<T> collectionReceivedHandler) {
        collectionReceivedHandler.onCollectionReceived(entities);
    }
}
