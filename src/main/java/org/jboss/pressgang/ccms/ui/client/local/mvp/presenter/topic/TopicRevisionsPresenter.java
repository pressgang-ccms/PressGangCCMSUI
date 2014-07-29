/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.RenderedDiffCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class TopicRevisionsPresenter extends BaseRenderedDiffPresenter {

    public interface Display extends BaseRenderedDiffPresenter.Display, BaseCustomViewInterface<RESTTopicV1> {

        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(@NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        @NotNull CellTable<RESTTopicCollectionItemV1> getResults();

        @NotNull SimplePager getPager();

        Column<RESTTopicCollectionItemV1, String> getViewButton();

        Column<RESTTopicCollectionItemV1, String> getDiffButton();

        Column<RESTTopicCollectionItemV1, String> getHTMLDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        @Nullable RESTTopicV1 getRevisionTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setRevisionTopic(@Nullable final RESTTopicV1 revisionTopic);

        @NotNull PushButton getDone();

        @NotNull PushButton getCancel();

        @NotNull PushButton getHTMLDone();

        @NotNull PushButton getHtmlOpenDiff();

        Mergely getMergely();

        void displayRevisions();

        void displayDiff(@NotNull final String lhs, final boolean rhsReadOnly, @NotNull final String rhs);

        /**
         *
         * @return true if the view is displaying the list of revisions, and false if
         * it is in any other state (i.e. showing or in the process of showing a diff).
         */
        boolean isDisplayingRevisions();

        boolean isButtonsEnabled();

        void setButtonsEnabled(boolean buttonsEnabled);

        @NotNull VerticalPanel getSearchResultsPanel();

        @NotNull SimpleLayoutPanel getDiffParent();
    }

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TopicRevisionsPresenter.class.getName());

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicHistoryView";

    @Inject
    private Display display;

    private Integer topicID = null;
    private Integer firstRevision = null;
    private Integer secondRevision = null;

    /**
     * Holds the data required to populate and refresh the tags list
     */
    private ProviderUpdateData<RESTTopicCollectionItemV1> providerData = new ProviderUpdateData<RESTTopicCollectionItemV1>();

    @NotNull
    public ProviderUpdateData<RESTTopicCollectionItemV1> getProviderData() {
        return providerData;
    }


    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    protected void go() {
        bindRenderedDiff(display);

        /*
            When this presenter is used a sa standalone presenter to display the rendered diff
            view of a topic, the done and new window buttons are not displayed.
         */
        display.getHTMLDone().setVisible(false);
        display.getHtmlOpenDiff().setVisible(false);
        display.showWaitingFromRenderedDiff();
        display.getTopActionGrandParentPanel().removeFromParent();

        if (topicID != null) {
            loadTopics(topicID, firstRevision, secondRevision, new RenderedDiffCallback() {
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
    }

    @Override
    protected void displayRenderedHTML() {
        /*
            Check isDisplayingDuplicates() here because the user may have
            moved off the view.
        */
        if (!display.isDisplayingRevisions()) {
            super.displayRenderedHTML();
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);
            final String[] topicDetails = fixedToken.split(";");
            if (topicDetails.length == 2 || topicDetails.length == 3) {
                topicID = Integer.parseInt(topicDetails[0]);
                firstRevision = Integer.parseInt(topicDetails[1]);
                secondRevision = topicDetails.length == 3 ? Integer.parseInt(topicDetails[2]) : null;
            }
        } catch (@NotNull final NumberFormatException ex) {
            topicID = null;
            firstRevision = null;
            secondRevision = null;
        }
    }

    public void reset() {
        providerData = new ProviderUpdateData<RESTTopicCollectionItemV1>();
        if (getDisplay().getProvider() != null) {
            getDisplay().getProvider().resetProvider(true);
            getDisplay().setProvider(null);
        }
        getDisplay().setRevisionTopic(null);
    }

    public void refreshList() {
        if (getDisplay().getProvider() != null && getProviderData().isValid()) {
            getDisplay().getProvider().displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
        }
    }

    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@NotNull final RESTTopicV1 topic) {

        final ProviderUpdateData<RESTTopicCollectionItemV1> providerData = getProviderData();
        providerData.reset();

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {
                resetProvider(false);
                if (topic.getId() != null) {
                    final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                        @Override
                        public void success(@NotNull final RESTTopicV1 retValue) {
                            checkArgument(retValue.getRevisions().getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getRevisions().getSize() != null, "Returned collection should have a valid size.");

                            if (retValue.getRevisions().getItems().size() != 0) {
                                checkArgument(retValue.getRevisions().getItems().get(0).getItem().getProperties() != null, "Returned collection should include items with a valid properties collection.");
                                checkArgument(retValue.getRevisions().getItems().get(0).getItem().getSourceUrls_OTM() != null, "Returned collection should include items with a valid source urls collection.");
                            }

                            providerData.setItems(retValue.getRevisions().getItems());
                            providerData.setSize(retValue.getRevisions().getSize());
                            displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                        }
                    };

                    final int start = list.getVisibleRange().getStart();
                    providerData.setStartRow(start);
                    final int length = list.getVisibleRange().getLength();
                    final int end = start + length;

                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopicWithRevisions(topic.getId(), start, end), callback,
                            display);
                } else {
                    providerData.resetToEmpty();
                    displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                }
            }
        };
        return provider;
    }



}
