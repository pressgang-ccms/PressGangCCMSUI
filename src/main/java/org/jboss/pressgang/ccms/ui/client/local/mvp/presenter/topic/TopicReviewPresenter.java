package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.topic.RESTTopicCollectionItemV1RevisionSort;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * The presenter used to add logic to the review view.
 */
public class TopicReviewPresenter extends BaseRenderedDiffPresenter {

    public interface Display extends BaseRenderedDiffPresenter.Display {
        @NotNull PushButton getStartReview();
        @NotNull PushButton getEndAndAcceptReview();
        @NotNull PushButton getEndAndRejectReview();
    }

    @Inject
    private Display display;

    @Inject private FailOverRESTCall failOverRESTCall;

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        super.bindRenderedDiff(display);
    }

    /**
     * Called to display the diff between when the revision period was started and
     * now.
     * @param topic The topic whose reviews are to be displayed.
     */
    public void displayTopicReview(@NotNull final RESTTopicV1 topic) {

        final boolean hasReviewTag = ComponentTopicV1.hasTag(topic, ServiceConstants.REVIEW_PROPERTY_TAG);

        if (hasReviewTag) {
            failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopicWithRevisions(topic.getId()),
                new RESTCallBack<Object>(){
                    public void success(@NotNull final RESTTopicV1 value) {

                        /*
                            Make sure the list of revisions is smallest to largest
                        */
                        Collections.sort(value.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());
                        /*
                            And then reverse the list to go from largest to smallest
                        */
                        Collections.reverse(value.getRevisions().getItems());

                        final List<Integer> ids = new ArrayList<Integer>();
                        boolean foundLatest = false;
                        for (@NotNull final RESTTopicCollectionItemV1 revision : value.getRevisions().getItems()) {
                            /*
                                On the off chance that a new revision was created between when this topic was
                                selected and when the review diff is viewed, we ignore any revisions greater than
                                the one assigned to the topic.
                             */
                            if (!foundLatest && revision.getItem().getRevision() == topic.getRevision()) {
                                foundLatest = true;
                            }

                            if (foundLatest) {
                                ids.add(revision.getItem().getId());
                            }
                        }

                        processRevision(topic.getId(), topic.getRevision(), null, ids);
                    }
                },
                display
            );
        }
    }

    /**
     * A recursively called method that works through each revision looking for one that does not have the review tag.
     * When this is found, or when the list of revisions is exhausted, the rendered diff between these revisions
     * is displayed.
     * @param topicId The topic id we are working with
     * @param latestRevision The current topic revision, which is one of the revisions that is compared
     * @param lastRevisionWithTag The last topic to have the tag assigned to it. The revision that matches latestRevision will have the review tag.
     * @param revisionIds
     */
    private void processRevision(final int topicId, final int latestRevision, @NotNull final RESTTopicCollectionItemV1 lastRevisionWithTag, @NotNull final List<Integer> revisionIds) {

        if (!revisionIds.isEmpty()) {
            failOverRESTCall.performRESTCall(
                    FailOverRESTCallDatabase.getTopicRevisionWithTags(topicId, revisionIds.get(0)),
                    new RESTCallBack<Object>(){
                        public void success(@NotNull final RESTTopicV1 value) {

                            /*
                                Make sure the list of revisions is smallest to largest
                            */
                            Collections.sort(value.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());
                            /*
                                And then reverse the list to go from largest to smallest
                            */
                            Collections.reverse(value.getRevisions().getItems());

                            /*
                                The largest revision is the current revision, which we already know has the property tag. So
                                the first loop through will assign the current revision to the lastRevisionWithTag variable.
                                When we find a revision without the tag, lastRevisionWithTag will hold the last time a review
                                was started.
                            */
                            RESTTopicCollectionItemV1 lastRevisionWithTag = null;
                            for (@NotNull final RESTTopicCollectionItemV1 revision : value.getRevisions().getItems()) {
                                final boolean hasReviewTag = ComponentTopicV1.hasTag(revision.getItem(), ServiceConstants.REVIEW_PROPERTY_TAG);

                                checkState(lastRevisionWithTag != null || hasReviewTag, "The first revision should have the review tag. The database revisions may be in an inconsistent state.");

                                if (hasReviewTag) {
                                    revisionIds.remove(0);
                                    processRevision(topicId, latestRevision, revision, revisionIds);
                                } else {
                                    if (lastRevisionWithTag != null) {
                                        loadTopics(topicId, latestRevision, lastRevisionWithTag.getItem().getRevision());
                                    }
                                }
                            }
                        }
                    },
                    display
            );
        } else {
            // If we got here then the first revision of the topic was set for review.
            checkState(lastRevisionWithTag != null, "lastRevisionWithTag should not be null. The database revisions may be in an inconsistent state.");
            loadTopics(topicId, latestRevision, lastRevisionWithTag.getItem().getRevision());
        }
    }
}
