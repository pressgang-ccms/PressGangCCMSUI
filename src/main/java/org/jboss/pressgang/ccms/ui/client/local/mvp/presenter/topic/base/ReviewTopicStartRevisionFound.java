package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import org.jetbrains.annotations.NotNull;

/**
 * A callback used when the start of a revision period is found.
 */
public interface ReviewTopicStartRevisionFound {
    void revisionFound(@NotNull final org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1 revision);
    void revisionNotFound();
}
