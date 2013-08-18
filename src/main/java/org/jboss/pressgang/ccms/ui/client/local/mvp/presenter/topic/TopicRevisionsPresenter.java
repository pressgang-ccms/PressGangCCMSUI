package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedDiffPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

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
    private FailOverRESTCall failOverRESTCall;

    @Inject
    private Display display;

    /**
     * Holds the data required to populate and refresh the tags list
     */
    private final ProviderUpdateData<RESTTopicCollectionItemV1> providerData = new ProviderUpdateData<RESTTopicCollectionItemV1>();

    @NotNull
    public ProviderUpdateData<RESTTopicCollectionItemV1> getProviderData() {
        return providerData;
    }


    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindRenderedDiff(display);

        /*
            When this presenter is used a sa standalone presenter to display the rendered diff
            view of a topic, the done and new window buttons are not displayed.
         */
        display.getHTMLDone().setVisible(false);
        display.getHtmlOpenDiff().setVisible(false);
        display.showWaitingFromRenderedDiff();

    }

    @Override
    protected void displayRenderedHTML() {
        /*
            Check isDisplayingRevisions() here because the user may have
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
                final Integer topicID = Integer.parseInt(topicDetails[0]);
                final Integer firstRevision = Integer.parseInt(topicDetails[1]);
                final Integer secondRevision = topicDetails.length == 3 ? Integer.parseInt(topicDetails[2]) : null;

                loadTopics(topicID, firstRevision, secondRevision);
            }
        } catch (final NumberFormatException ex) {
            // invalid data supplied on the url. do nothing
        }
    }

    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@NotNull final Integer id, @NotNull final BaseTemplateViewInterface waitDisplay) {

        getProviderData().reset();

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        checkArgument(retValue.getRevisions().getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getRevisions().getSize() != null, "Returned collection should have a valid size.");

                        if (retValue.getRevisions().getItems().size() != 0) {
                            checkArgument(retValue.getRevisions().getItems().get(0).getItem().getProperties() != null, "Returned collection should include items with a valid properties collection.");
                            checkArgument(retValue.getRevisions().getItems().get(0).getItem().getSourceUrls_OTM() != null, "Returned collection should include items with a valid source urls collection.");
                        }

                        getProviderData().setItems(retValue.getRevisions().getItems());
                        getProviderData().setSize(retValue.getRevisions().getSize());
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                };

                final int start = list.getVisibleRange().getStart();
                getProviderData().setStartRow(start);
                final int length = list.getVisibleRange().getLength();
                final int end = start + length;

                this.resetProvider();

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicWithRevisions(id, start, end), callback, display);
            }
        };
        return provider;
    }



}
