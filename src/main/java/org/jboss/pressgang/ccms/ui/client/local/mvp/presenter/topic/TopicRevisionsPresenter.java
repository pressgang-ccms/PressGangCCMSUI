package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.BaseOrderedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.mergelygwt.client.Mergely;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TopicRevisionsPresenter extends BaseTemplatePresenter {

    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTTopicV1> {

        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        CellTable<RESTTopicCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTTopicCollectionItemV1, String> getViewButton();

        Column<RESTTopicCollectionItemV1, String> getDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        RESTTopicV1 getRevisionTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setRevisionTopic(RESTTopicV1 revisionTopic);

        PushButton getDone();
        PushButton getCancel();
        Mergely getMergely();
        void displayRevisions();
        void displayDiff(String lhs, boolean lhsReadOnly, String rhs);
        boolean isDisplayingRevisions();
        boolean isButtonsEnabled();
        void setButtonsEnabled(boolean buttonsEnabled);
    }

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TopicRevisionsPresenter.class.getName());

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicHistoryView";

    private String topicId;

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
        bindExtended(ServiceConstants.TOPIC_REVISIONS_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateListProvider(@NotNull final Integer id, @NotNull final BaseTemplateViewInterface waitDisplay) {

        getProviderData().reset();

        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicCollectionItemV1> list) {

                final BaseRestCallback<RESTTopicV1, Display> callback = new BaseRestCallback<RESTTopicV1, Display>(display, new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
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
                });

                final int start = list.getVisibleRange().getStart();
                getProviderData().setStartRow(start);
                final int length = list.getVisibleRange().getLength();
                final int end = start + length;

                this.resetProvider();

                RESTCalls.getTopicWithRevisions(callback, id, start, end);
            }
        };
        return provider;
    }

}
