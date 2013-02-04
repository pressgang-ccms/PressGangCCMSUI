package org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents;

import com.google.gwt.event.shared.GwtEvent;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;

/**
 * Represents an event that is raised when the topics from a query have been returned from the
 * REST server.
 */
public final class TopicListReceived extends GwtEvent<TopicListReceivedHandler> {

    private static final Type TYPE = new Type<TopicListReceivedHandler>();
    private final RESTTopicCollectionV1 topics;

    public static Type getType() {
        return TYPE;
    }

    public TopicListReceived(final RESTTopicCollectionV1 topics)
    {
        this.topics = topics;
    }


    @Override
    public Type<TopicListReceivedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final TopicListReceivedHandler topicListRecievedHandler) {
        final RESTTopicCollectionV1 copy = new RESTTopicCollectionV1();
        this.topics.cloneInto(copy, true);
        topicListRecievedHandler.onTopicsRecieved(copy);
    }
}
