package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.*;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTranslatedTopicV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * Extends the BaseTopicFilteredResultsAndDetailsPresenter class to provide the functionality required to
 * display translated topics.
 */
@Dependent
public class TranslatedTopicFilteredResultsAndDetailsPresenter extends BaseTopicFilteredResultsAndDetailsPresenter<
        RESTTranslatedTopicV1,
        RESTTranslatedTopicCollectionV1,
        RESTTranslatedTopicCollectionItemV1,
        RESTTranslatedTopicV1BasicDetailsEditor> {
    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "TranslatedTopicResultsAndTranslatedTopicView";


    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TranslatedTopicFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * The main view.
     */
    @Inject
    private Display display;

    @Inject
    private TranslatedTopicPresenter translatedTopicPresenter;
    @Inject
    private TranslatedTopicsFilteredResultsPresenter translatedTopicsFilteredResultsPresenter;


    /**
     * A list of locales retrieved from the server
     */
    private List<String> locales;

    /**
     * @return The display.
     */
    @Override
    protected Display getDisplay() {
        return display;
    }

    @NotNull
    @Override
    protected BaseFilteredResultsPresenter<RESTTranslatedTopicCollectionItemV1> getSearchResultsComponent() {
        return translatedTopicsFilteredResultsPresenter;
    }

    @Override
    protected void postBindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @Nullable final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTTranslatedTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTranslatedTopicV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTranslatedTopicV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTTranslatedTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    @NotNull final RESTCalls.RESTCallback<RESTTranslatedTopicV1> callback = new BaseRestCallback<RESTTranslatedTopicV1, BaseTemplateViewInterface>(
                            getDisplay(), new BaseRestCallback.SuccessAction<RESTTranslatedTopicV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(@NotNull final RESTTranslatedTopicV1 retValue, final BaseTemplateViewInterface display) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getSourceUrls_OTM() != null, "The initially retrieved entity should have an expanded source urls collection");
                                checkArgument(retValue.getProperties() != null, "The initially retrieved entity should have an expanded properties collection");
                                checkArgument(retValue.getRevisions() != null, "The initially retrieved entity should have an expanded revisions collection");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    });
                    RESTCalls.getTranslatedTopic(callback, selectedEntity.getId());
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        bindSearchAndEdit(topicId, pageId, getMainResizePreferencesKey(), getTopicXMLComponent().getDisplay(), translatedTopicPresenter.getDisplay(),
                getSearchResultsComponent().getDisplay(), getSearchResultsComponent(), getDisplay(), getDisplay(), getNewEntityCallback);



        /* When the topics have been loaded, display the first one */
        getSearchResultsComponent().addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTranslatedTopicCollectionV1>() {
            @Override
            public void onCollectionReceived(@NotNull final RESTTranslatedTopicCollectionV1 topics) {
                displayInitialTopic(getNewEntityCallback);
            }
        });
    }

    @NotNull
    @Override
    protected String getMainResizePreferencesKey() {
        return Preferences.TRANSLATED_TOPIC_VIEW_MAIN_SPLIT_WIDTH;
    }

    @Override
    protected boolean isInitialTopicReadyToBeLoaded() {
        /* displayInitialContentSpec() is only called when all the data is available, so just return true */
        return true;
    }

    @Override
    protected void preLoadAdditionalDisplayedItemData() {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    protected void postLoadAdditionalDisplayedItemData() {

        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.postLoadAdditionalDisplayedItemData()");

            this.getTopicPropertyTagPresenter().refreshExistingChildList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of property tags */
            this.getTopicPropertyTagPresenter().refreshPossibleChildrenDataFromRESTAndRedisplayList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

            Collections.sort(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                    new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());

            /* Initiate the REST calls */
            final Integer id = getDisplayedTopic().getId();
            final Integer revision = getDisplayedTopic().getRevision();

            /* If this is a new topic, the id will be null, and there will not be any tags to get */
            if (id != null) {

            /* A callback to respond to a request for a topic with the tags expanded */
                final RESTCallBack<RESTTranslatedTopicV1> topicWithTagsCallback = new RESTCallBack<RESTTranslatedTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTranslatedTopicV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");

                            /*
                                There is a small chance that in between loading the topic's details and
                                loading its tags, a new revision was created.

                                So, what do we do? If changes are made to the topic, then
                                the user will be warned that they have overwritten a revision created
                                in the mean time. In fact seeing the latest tag relationships could
                                mean that the user doesn't try to add conflicting tags (like adding
                                a tag from a mutually exclusive category when one already exists).

                                This check is left in comments just to show that a conflict is possible.
                            */
                            /*if (!retValue.getRevision().equals(revision)) {
                                Window.alert("The topics details and tags are not in sync.");
                            }*/

                            /* copy the revisions into the displayed Topic */
                            getDisplayedTopic().setTags(retValue.getTags());

                            /* update the view */
                            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{getTopicTagsPresenter().getDisplay()}));
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.postLoadAdditionalDisplayedItemData() topicWithTagsCallback.doSuccessAction()");
                        }
                    }
                };

                FailOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTranslatedTopicWithTags(id), topicWithTagsCallback, getTopicTagsPresenter().getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.postLoadAdditionalDisplayedItemData()");
        }
    }

    @Nullable
    @Override
    protected RESTTranslatedTopicV1 getDisplayedTopic() {
        final RESTTranslatedTopicCollectionItemV1 displayedItem = this.getSearchResultsComponent().getProviderData().getDisplayedItem();
        return displayedItem == null ? null : displayedItem.getItem();
    }

    @Override
    protected void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), this.getDisplay().getFields());
        if (displayedView == this.translatedTopicPresenter.getDisplay()) {
            getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDown());
        }
    }

    @Override
    protected void postAfterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    protected void postBindActionButtons() {
        @NotNull final ClickHandler topicViewClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                    switchView(translatedTopicPresenter.getDisplay());
                }
            }
        };

        getDisplay().getFields().addClickHandler(topicViewClickHandler);
    }

    @Override
    protected void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        if (viewIsInFilter(filter, translatedTopicPresenter.getDisplay())) {
            translatedTopicPresenter.getDisplay().display(this.getDisplayedTopic(), isReadOnlyMode());
        }
    }

    @Override
    protected boolean isReadOnlyMode() {
        /* translated topics are always readonly */
        return true;
    }

    @Override
    protected void bindFilteredResultsButtons() {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.parseToken()");

            setQueryString(removeHistoryToken(historyToken, HISTORY_TOKEN));

            if (!getQueryString().startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                /* Make sure that the query string has at least the prefix */
                setQueryString(Constants.QUERY_PATH_SEGMENT_PREFIX);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.parseToken()");
        }
    }

    /**
     * This interface defines nothing over BaseTopicFilteredResultsAndDetailsPresenter.Display,
     * but exists for the benefit of the injection.
     */
    public interface Display extends BaseTopicFilteredResultsAndDetailsPresenter.Display<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

    }
}
