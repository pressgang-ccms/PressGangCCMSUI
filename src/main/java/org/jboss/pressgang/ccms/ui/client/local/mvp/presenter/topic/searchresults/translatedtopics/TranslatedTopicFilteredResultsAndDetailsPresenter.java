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
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseSearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTranslatedTopicV1BasicDetailsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * Extends the BaseSearchResultsAndTopicPresenter class to provide the functionality required to
 * display translated topics.
 */
@Dependent
public class TranslatedTopicFilteredResultsAndDetailsPresenter extends BaseSearchResultsAndTopicPresenter<
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
    protected final Display getDisplay() {
        return display;
    }

    @Override
    protected BaseFilteredResultsComponent<RESTTranslatedTopicCollectionItemV1> getSearchResultsComponent() {
        return translatedTopicsFilteredResultsPresenter;
    }

    @Override
    protected void postBindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTTranslatedTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTranslatedTopicV1>() {

            @Override
            public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTTranslatedTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    final RESTCalls.RESTCallback<RESTTranslatedTopicV1> callback = new BaseRestCallback<RESTTranslatedTopicV1, BaseTemplateViewInterface>(
                            getDisplay(), new BaseRestCallback.SuccessAction<RESTTranslatedTopicV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(final RESTTranslatedTopicV1 retValue, final BaseTemplateViewInterface display) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");

                                LOGGER.log(Level.INFO, "retValue.getProperties().getItems().size(): " + retValue.getProperties().getItems().size());

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    });
                    RESTCalls.getTranslatedTopic(callback, id);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        bindSearchAndEdit(topicId, pageId, Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, getTopicXMLComponent().getDisplay(), translatedTopicPresenter.getDisplay(),
                getSearchResultsComponent().getDisplay(), getSearchResultsComponent(), getDisplay(), getDisplay(), getNewEntityCallback);

        /* When the topics have been loaded, display the first one */
        getSearchResultsComponent().addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTranslatedTopicCollectionV1>() {
            @Override
            public void onCollectionRecieved(final RESTTranslatedTopicCollectionV1 topics) {
                displayInitialTopic(getNewEntityCallback);
            }
        });
    }

    @Override
    protected boolean isInitialTopicReadyToBeLoaded() {
        /* displayInitialTopic() is only called when all the data is available, so just return true */
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
        /*
            Nothing needs to be done here
        */
    }

    @Override
    protected RESTTranslatedTopicV1 getDisplayedTopic() {
        final RESTTranslatedTopicCollectionItemV1 displayedItem = this.getSearchResultsComponent().getProviderData().getDisplayedItem();
        return displayedItem == null ? null : displayedItem.getItem();
    }

    @Override
    protected void postEnableAndDisableActionButtons(BaseTemplateViewInterface displayedView) {
        this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), this.getDisplay().getFields());
        if (displayedView == this.translatedTopicPresenter.getDisplay()) {
            getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDown());
        }
    }

    @Override
    protected void postAfterSwitchView(BaseTemplateViewInterface displayedView) {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    protected void postBindActionButtons() {
        final ClickHandler topicViewClickHandler = new ClickHandler() {
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
    protected void postInitializeViews(List<BaseTemplateViewInterface> filter) {
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
    public void parseToken(final String historyToken) {
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
     * This interface defines nothing over BaseSearchResultsAndTopicPresenter.Display,
     * but exists for the benefit of the injection.
     */
    public interface Display extends BaseSearchResultsAndTopicPresenter.Display<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

    }
}
