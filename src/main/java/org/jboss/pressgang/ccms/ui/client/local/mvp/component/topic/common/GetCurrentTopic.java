package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;

/** A callback used to get the currently edited topic */
public interface GetCurrentTopic {
    RESTTopicV1 getCurrentlyEditedTopic();
}
