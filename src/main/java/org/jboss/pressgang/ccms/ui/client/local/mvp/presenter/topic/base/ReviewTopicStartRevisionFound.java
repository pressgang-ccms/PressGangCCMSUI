package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

/**
 * A callback used when the start of a revision period is found.
 */
public interface ReviewTopicStartRevisionFound {
    void revisionFound(final int revision);
    void revisionNotFound();
}
