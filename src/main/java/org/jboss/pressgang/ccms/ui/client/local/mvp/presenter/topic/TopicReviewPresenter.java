package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.ReviewTopicStartRevisionFound;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.topic.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EntityUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
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
@Dependent
public class TopicReviewPresenter extends BaseRenderedDiffPresenter {

    public Display getDisplay() {
        return display;
    }

    public interface Display extends BaseRenderedDiffPresenter.Display {
        @NotNull PushButton getStartReview();
        @NotNull PushButton getEndAndAcceptReview();
        @NotNull PushButton getEndAndRejectReview();
        void showHelpTopic(@NotNull final Integer helpTopic);
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
        findReviewRevision(topic, new ReviewTopicStartRevisionFound() {
            @Override
            public void revisionFound(int revision) {
                loadTopics(topic.getId(), topic.getRevision(), revision);
            }

            @Override
            public void revisionNotFound() {
                failOverRESTCall.performRESTCall(
                        FailOverRESTCallDatabase.getTopic(ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId()),
                        new RESTCallBack<RESTTopicV1>() {
                            public void success(@NotNull final RESTTopicV1 value) {
                                final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + DocbookDTD.getDtdDoctype() + "\n" + GWTUtilities.removeAllPreabmle(value.getXml());
                                failOverRESTCall.performRESTCall(
                                        FailOverRESTCallDatabase.holdXML(xml),
                                        new RESTCallBack<IntegerWrapper>() {
                                            public void success(@NotNull final IntegerWrapper value) {
                                                display.showHelpTopic(value.value);
                                            }
                                        },
                                        display,
                                        true
                                );
                            }
                        },
                        display
                );
            }
        });
    }

    /**
     * Scans through the revisions looking for the highest revision where the review tag was added.
     * @param topic The topic to scan
     * @param callback The callback to call when the revision is found
     */
    public void findReviewRevision(@NotNull final RESTTopicV1 topic, @NotNull final ReviewTopicStartRevisionFound callback) {
        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopicWithTags(
                topic.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 topicWithTags) {

                        final boolean hasReviewTag = EntityUtilities.topicHasTag(topicWithTags, ServiceConstants.REVIEW_PROPERTY_TAG);

                        if (hasReviewTag) {
                            failOverRESTCall.performRESTCall(
                                    FailOverRESTCallDatabase.getTopicWithRevisions(topic.getId()),
                                    new RESTCallBack<RESTTopicV1>(){
                                        public void success(@NotNull final RESTTopicV1 topicWithRevisions) {

                                            /*
                                                Make sure the list of revisions is smallest to largest
                                            */
                                            Collections.sort(topicWithRevisions.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());
                                            /*
                                                And then reverse the list to go from largest to smallest
                                            */
                                            Collections.reverse(topicWithRevisions.getRevisions().getItems());

                                            final List<Integer> revisions = new ArrayList<Integer>();
                                            boolean foundLatest = false;
                                            for (@NotNull final RESTTopicCollectionItemV1 revision : topicWithRevisions.getRevisions().getItems()) {
                                                /*
                                                    On the off chance that a new revision was created between when this topic was
                                                    selected and when the review diff is viewed, we ignore any revisions greater than
                                                    the one assigned to the topic.
                                                 */
                                                if (!foundLatest && revision.getItem().getRevision().equals(topic.getRevision())) {
                                                    foundLatest = true;
                                                }

                                                if (foundLatest) {
                                                    revisions.add(revision.getItem().getRevision());
                                                }
                                            }

                                            processRevision(topic.getId(), null, revisions, callback);
                                        }
                                    },
                                    display
                            );
                        } else {
                            callback.revisionNotFound();
                        }

                    }
                },
                display
        );
    }

    /**
     * A recursively called method that works through each revision looking for one that does not have the review tag.
     * When this is found, or when the list of revisions is exhausted, the rendered diff between these revisions
     * is displayed.
     * @param topicId The topic id we are working with
     * @param lastRevisionWithTag The last topic to have the tag assigned to it. The revision that matches latestRevision will have the review tag.
     * @param revisionIds The list of ids left to process
     */
    private void processRevision(final int topicId, @NotNull final RESTTopicV1 lastRevisionWithTag, @NotNull final List<Integer> revisionIds, @NotNull final ReviewTopicStartRevisionFound callback) {

        if (!revisionIds.isEmpty()) {
            failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopicRevisionWithTags(topicId, revisionIds.get(0)),
                new RESTCallBack<RESTTopicV1>(){
                    public void success(@NotNull final RESTTopicV1 value) {

                        final boolean hasReviewTag = EntityUtilities.topicHasTag(value, ServiceConstants.REVIEW_PROPERTY_TAG);

                        checkState(lastRevisionWithTag != null || hasReviewTag, "The first revision should have the review tag. The database revisions may be in an inconsistent state.");

                        if (hasReviewTag) {
                            revisionIds.remove(0);
                            processRevision(topicId, value, revisionIds, callback);
                        } else {
                            callback.revisionFound(lastRevisionWithTag.getRevision());
                        }
                    }

                },
                display
            );
        } else {
            // If we got here then the first revision of the topic was set for review.
            checkState(lastRevisionWithTag != null, "lastRevisionWithTag should not be null. The database revisions may be in an inconsistent state.");
            callback.revisionFound(lastRevisionWithTag.getRevision());
        }
    }


}
