package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
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
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
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
        @NotNull Label getInfo();
        void showHelpTopic(@NotNull final Integer helpTopic);
        void reDisplayHtmlDiff();
    }

    @Inject
    private Display display;

    @Inject
    private FailOverRESTCall failOverRESTCall;

    private RESTTopicV1 topic;

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        super.bindRenderedDiff(display);
    }

    /**
     * @param topic The topic whose reviews are to be displayed.
     */
    public void setTopic(@NotNull final RESTTopicV1 topic) {
       this.topic = topic;
    }

    /**
     * Called to display the diff between when the revision period was started and
     * now.
     */
    public void displayTopicReview(@NotNull final Panel hiddenAttach) {
        findReviewRevision(topic, display, new ReviewTopicStartRevisionFound() {
            @Override
            public void revisionFound(@NotNull final RESTTopicV1 revision) {
                /*
                    Add some details about which revisions are being compared to what
                 */
                display.getInfo().setText(PressGangCCMSUI.INSTANCE.RevisionStartedAt()
                        + " " + revision.getRevision()
                        + " (" + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(revision.getLastModified()) + ") " +
                        PressGangCCMSUI.INSTANCE.CurrentRevision()
                        + " " + topic.getRevision()
                        + " (" + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM).format(topic.getLastModified()) + ")");
                /*
                    Load the revisions and create a diff
                 */
                loadTopics(topic.getId(), revision.getRevision(), topic.getRevision(), hiddenAttach);
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
     * @param waitDisplay The view to display the waiting widget
     * @param callback The callback to call when the revision is found
     */
    public void findReviewRevision(@NotNull final RESTTopicV1 topic, @NotNull final BaseTemplateViewInterface waitDisplay, @NotNull final ReviewTopicStartRevisionFound callback) {
        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopicWithRevisionsWithTags(
                        topic.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 topicWithTags) {

                        final boolean hasReviewTag = EntityUtilities.topicHasTag(topicWithTags, ServiceConstants.REVIEW_PROPERTY_TAG);

                        if (hasReviewTag) {
                            /*
                                Make sure the list of revisions is smallest to largest
                            */
                            Collections.sort(topicWithTags.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());
                            /*
                                And then reverse the list to go from largest to smallest
                            */
                            Collections.reverse(topicWithTags.getRevisions().getItems());

                            boolean foundLatest = false;
                            boolean foundEarliest = false;
                            RESTTopicV1 lastRevisionWithTag = null;
                            for (@NotNull final RESTTopicCollectionItemV1 revision : topicWithTags.getRevisions().getItems()) {
                                /*
                                    On the off chance that a new revision was created between when this topic was
                                    selected and when the review diff is viewed, we ignore any revisions greater than
                                    the one assigned to the topic.
                                 */
                                if (!foundLatest && revision.getItem().getRevision().equals(topic.getRevision())) {
                                    foundLatest = true;
                                }

                                if (foundLatest) {
                                    final boolean revisionHasReviewTag = EntityUtilities.topicHasTag(revision.getItem(), ServiceConstants.REVIEW_PROPERTY_TAG);

                                    checkState(lastRevisionWithTag != null || revisionHasReviewTag, "The first revision should have the review tag. The database revisions may be in an inconsistent state.");

                                    if (revisionHasReviewTag) {
                                        lastRevisionWithTag = revision.getItem();
                                    } else {
                                        checkState(lastRevisionWithTag != null, "A revision should have been found with the revision tag. The database revisions may be in an inconsistent state.");

                                        callback.revisionFound(lastRevisionWithTag);
                                        foundEarliest = true;
                                        break;
                                    }
                                }
                            }

                            if (!foundEarliest) {
                                checkState(lastRevisionWithTag != null, "A revision should have been found with the revision tag. The database revisions may be in an inconsistent state.");

                                // If we got here then the first revision of the topic was set for review.
                                callback.revisionFound(lastRevisionWithTag);
                            }
                        } else {
                            callback.revisionNotFound();
                        }
                    }
                },
                waitDisplay
        );
    }
}
