package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.inject.Inject;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
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

/**
 * Created by mcasperson on 5/13/14.
 */
public class TopicDuplicatesPresenter extends BaseRenderedDiffPresenter {
    public interface Display extends BaseRenderedDiffPresenter.Display, BaseCustomViewInterface<RESTTopicV1> {

        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(@NotNull final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        @NotNull
        CellTable<RESTTopicCollectionItemV1> getResults();

        @NotNull
        SimplePager getPager();

        Column<RESTTopicCollectionItemV1, String> getViewButton();

        Column<RESTTopicCollectionItemV1, String> getDiffButton();

        Column<RESTTopicCollectionItemV1, String> getHTMLDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        @Nullable
        RESTTopicV1 getDuplicateTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setDuplicateTopic(@Nullable final RESTTopicV1 revisionTopic);

        @NotNull
        PushButton getDone();

        @NotNull PushButton getCancel();

        @NotNull PushButton getHTMLDone();

        @NotNull PushButton getHtmlOpenDiff();

        Mergely getMergely();

        void displayDuplicates();

        void displayDiff(@NotNull final String lhs, final boolean rhsReadOnly, @NotNull final String rhs);

        /**
         *
         * @return true if the view is displaying the list of revisions, and false if
         * it is in any other state (i.e. showing or in the process of showing a diff).
         */
        boolean isDisplayingDuplicates();

        boolean isButtonsEnabled();

        void setButtonsEnabled(boolean buttonsEnabled);

        @NotNull
        VerticalPanel getSearchResultsPanel();

        @NotNull
        SimpleLayoutPanel getDiffParent();
    }

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TopicDuplicatesPresenter.class.getName());

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicDuplicatesView";

    @Inject
    private Display display;

    private Integer topicID = null;
    private Integer secondTopicId = null;

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
            loadTopics(topicID, null, secondTopicId, null, new RenderedDiffCallback() {
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
        if (!display.isDisplayingDuplicates()) {
            super.displayRenderedHTML();
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);
            final String[] topicDetails = fixedToken.split(";");
            if (topicDetails.length == 2) {
                topicID = Integer.parseInt(topicDetails[0]);
                secondTopicId = Integer.parseInt(topicDetails[1]);
            }
        } catch (@NotNull final NumberFormatException ex) {
            topicID = null;
            secondTopicId = null;
        }
    }

    public void reset() {
        providerData = new ProviderUpdateData<RESTTopicCollectionItemV1>();
        if (getDisplay().getProvider() != null) {
            getDisplay().getProvider().resetProvider(true);
            getDisplay().setProvider(null);
        }
        getDisplay().setDuplicateTopic(null);
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
                    final RESTCallBack<RESTTopicCollectionV1> callback = new RESTCallBack<RESTTopicCollectionV1>() {
                        @Override
                        public void success(@NotNull final RESTTopicCollectionV1 retValue) {
                            checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                            providerData.setItems(retValue.getItems());
                            providerData.setSize(retValue.getSize());
                            displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                        }
                    };

                    final int start = list.getVisibleRange().getStart();
                    providerData.setStartRow(start);
                    final int length = list.getVisibleRange().getLength();
                    final int end = start + length;

                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getSimilarTopics(topic.getId(), start, end), callback, display);
                } else {
                    providerData.resetToEmpty();
                    displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                }
            }
        };
        return provider;
    }



}
