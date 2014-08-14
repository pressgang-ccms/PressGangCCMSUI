/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static com.google.common.base.Preconditions.checkState;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.RenderedDiffCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.ReviewTopicStartRevisionFound;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.topic.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EntityUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;

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

    private RESTTopicV1 topic;

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    protected void go() {
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
                loadTopics(topic.getId(), topic.getId(), revision.getRevision(), topic.getRevision(), hiddenAttach, new RenderedDiffCallback() {
                    @Override
                    public void success() {
                    }

                    @Override
                    public void failed() {
                        display.showRenderedDiffError();
                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotDisplayRenderedDiff());
                    }
                });
            }

            @Override
            public void revisionNotFound() {
                getFailOverRESTCall().performRESTCall(
                        FailOverRESTCallDatabase.getTopic(ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId()),
                        new RESTCallBack<RESTTopicV1>() {
                            public void success(@NotNull final RESTTopicV1 value) {
                                final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + DocBookDTD.getDtdDoctype() + "\n" +
                                        XMLUtilities.removeAllPreamble(
                                        value.getXml());
                                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.holdXML(xml),
                                        new RESTCallBack<IntegerWrapper>() {
                                            public void success(@NotNull final IntegerWrapper value) {
                                                display.showHelpTopic(value.value);
                                            }
                                        }, display, true);
                            }
                        }, display);
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
        getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopicWithRevisionsWithTags(topic.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 topicWithTags) {
                        getServerSettings(new ServerSettingsCallback() {
                            @Override
                            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings,
                                    RESTLocaleCollectionV1 locales) {
                                final boolean hasReviewTag = EntityUtilities.topicHasTag(topicWithTags,
                                        serverSettings.getEntities().getReviewTagId());

                                if (hasReviewTag) {
                                    // Make sure the list of revisions is smallest to largest
                                    Collections.sort(topicWithTags.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());
                                    // And then reverse the list to go from largest to smallest
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
                                            final boolean revisionHasReviewTag = EntityUtilities.topicHasTag(revision.getItem(),
                                                    serverSettings.getEntities().getReviewTagId());

                                            checkState(lastRevisionWithTag != null || revisionHasReviewTag,
                                                    "The first revision should have the review tag. The database revisions may be in an " +
                                                            "inconsistent state.");

                                            if (revisionHasReviewTag) {
                                                lastRevisionWithTag = revision.getItem();
                                            } else {
                                                checkState(lastRevisionWithTag != null,
                                                        "A revision should have been found with the revision tag. The database revisions may be " +
                                                                "in an inconsistent state.");

                                                callback.revisionFound(lastRevisionWithTag);
                                                foundEarliest = true;
                                                break;
                                            }
                                        }
                                    }

                                    if (!foundEarliest) {
                                        checkState(lastRevisionWithTag != null,
                                                "A revision should have been found with the revision tag. The database revisions may be in an " +
                                                        "inconsistent state.");

                                        // If we got here then the first revision of the topic was set for review.
                                        callback.revisionFound(lastRevisionWithTag);
                                    }
                                } else {
                                    callback.revisionNotFound();
                                }
                            }
                        });
                    }
                }, waitDisplay);
    }
}
