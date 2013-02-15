package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.TopicListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.GetCurrentTopic;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringMapLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseSearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import javax.annotation.Nullable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
    Extends the BaseSearchResultsAndTopicPresenter class to provide the functionality required to
    display, edit and create topics.
 */
@Dependent
public class SearchResultsAndTopicPresenter extends BaseSearchResultsAndTopicPresenter {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
    private static final String LINE_BREAK_ESCAPED = "\\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED = "\\r\\n";
    private static final String LINE_BREAK = "\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK = "\r\n";
    private static final String COMMA = ",";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchResultsAndTopicPresenter.class.getName());
    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastDisplayedView == getTopicXMLComponent().getDisplay()) {
                refreshRenderedView(false);
            }
        }
    };

    /**
     * true if this presenter should be opened with a fresh topic, and false otherwise
     */
    private boolean startWithNewTopic = false;

    /** true after the default locale has been loaded */
    private String defaultLocale = null;

    /**
     * The last xml that was rendered
     */
    private String lastXML;
    /**
     * How long it has been since the xml changes
     */
    private long lastXMLChange;
    /**
     * False if we are not displaying external images in the current rendered view, and true otherwise
     */
    private boolean isDisplayingImage;

    /**
     * A list of locales retrieved from the server
     */
    private List<String> locales;

    /** true after the locales have been loaded */
    private boolean localesLoaded = false;
    /** true after the topics have been loaded */
    private boolean topicListLoaded = false;

    @Inject
    private TopicPresenter topicViewComponent;
    @Inject
    private TopicRevisionsPresenter topicRevisionsComponent;
    @Inject private Display display;

    @Override
    protected final Display getDisplay() {
        return display;
    }

    @Override
    protected void postBindSearchAndEditExtended(final int topicId, final String pageId, final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTopicV1>() {

            @Override
            public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                            getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(final RESTTopicV1 retValue, final BaseTemplateViewInterface display) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bind() RESTCallback.doSuccessAction()");

                                LOGGER.log(Level.INFO, "retValue.getProperties().getItems().size(): " + retValue.getProperties().getItems().size());

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    });
                    RESTCalls.getTopic(callback, id);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        super.bindSearchAndEdit(topicId, pageId, Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, getTopicXMLComponent().getDisplay(), topicViewComponent.getDisplay(),
                getSearchResultsComponent().getDisplay(), getSearchResultsComponent(), getDisplay(), getDisplay(), getNewEntityCallback);

        this.topicRevisionsComponent.getDisplay().setProvider(generateTopicRevisionsListProvider());
        this.getTopicTagsComponent().bindNewTagListBoxes(new AddTagClickhandler());

        bindViewTopicRevisionButton();

        /* When the topics have been loaded, display the first one */
        getSearchResultsComponent().addTopicListReceivedHandler(new TopicListReceivedHandler() {
            @Override
            public void onTopicsRecieved(final RESTTopicCollectionV1 topics) {
                topicListLoaded = true;
                displayInitialTopic(getNewEntityCallback);
            }
        });

        populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(final List<String> locales) {
                SearchResultsAndTopicPresenter.this.locales = locales;
                localesLoaded = true;
                displayNewTopic();
                displayInitialTopic(getNewEntityCallback);
            }
        });

        loadDefaultLocale(new StringLoaded() {
            @Override
            public void stringLoaded(final String string) {
                defaultLocale = string;
                displayNewTopic();
            }
        });

        addKeyboardShortcutEventHandler(this.getTopicXMLComponent().getDisplay(), this.getDisplay(), new GetCurrentTopic() {

            @Override
            public RESTTopicV1 getCurrentlyEditedTopic() {
                return getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();
            }
        });
    }

    @Override
    protected boolean isInitialTopicReadyToBeLoaded() {
        /* only proceed loading the initial topic when the locales and the topic list have been loaded */
        return localesLoaded && topicListLoaded;
    }

    @Override
    protected void preLoadAdditionalDisplayedItemData() {
       /* Disable the topic revision view */
        viewLatestTopicRevision();
    }

    @Override
    protected void postLoadAdditionalDisplayedItemData() {
        /* set the revisions to show the loading widget */
        if (topicRevisionsComponent.getDisplay().getProvider() != null) {
            topicRevisionsComponent.getDisplay().getProvider().resetProvider();
        }

        /* if getSearchResultsComponent().getProviderData().getSelectedItem() == null, then we are displaying a new topic */
        if (this.getSearchResultsComponent().getProviderData().getSelectedItem() != null) {
                /* A callback to respond to a request for a topic with the revisions expanded */
            final RESTCalls.RESTCallback<RESTTopicV1> topicWithRevisionsCallback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                    topicRevisionsComponent.getDisplay(),
                    new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                        @Override
                        public void doSuccessAction(final RESTTopicV1 retValue, final TopicRevisionsPresenter.Display display) {
                            getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().setRevisions(retValue.getRevisions());

                                /* refresh the list */
                            topicRevisionsComponent.getDisplay().getProvider().displayNewFixedList(retValue.getRevisions().getItems());
                        }
                    });

            RESTCalls.getTopicWithRevisions(topicWithRevisionsCallback, getSearchResultsComponent().getProviderData().getSelectedItem().getItem().getId());
        }
    }

    @Override
    protected RESTTopicV1 getDisplayedTopic() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.getDisplayedTopic()");

            final RESTTopicCollectionItemV1 sourceTopic = topicRevisionsComponent.getDisplay().getRevisionTopic() == null ? this.getSearchResultsComponent()
                    .getProviderData().getDisplayedItem() : topicRevisionsComponent.getDisplay().getRevisionTopic();

            return sourceTopic == null ? null : sourceTopic.getItem();
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.getDisplayedTopic()");
        }
    }

    @Override
    protected void postEnableAndDisableActionButtons(BaseTemplateViewInterface displayedView) {
        this.getDisplay().replaceTopActionButton(this.getDisplay().getHistoryDown(), this.getDisplay().getHistory());

        if (displayedView == this.topicRevisionsComponent.getDisplay()) {
            this.getDisplay().replaceTopActionButton(this.getDisplay().getHistory(), this.getDisplay().getHistoryDown());
        }

        this.getDisplay().getSave().setEnabled(!isReadOnlyMode());
    }

    protected void postAfterSwitchView(final BaseTemplateViewInterface displayedView) {
        /* Set the projects combo box as the focused element */
        if (displayedView == this.getTopicTagsComponent().getDisplay() && getTopicTagsComponent().getDisplay().getProjectsList().isAttached()) {
            getTopicTagsComponent().getDisplay().getProjectsList().getElement().focus();
        }

            /* While editing the XML, we need to setup a refresh of the rendered view */
        if (displayedView == this.getTopicXMLComponent().getDisplay() && this.getDisplay().getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
            timer.scheduleRepeating(Constants.REFRESH_RATE);
        } else {
            timer.cancel();
            refreshRenderedView(true);
        }
    }

    @Override
    protected void postBindActionButtons() {
        /* Build up a click handler to save the topic */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                try {
                    LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindActionButtons() saveClickHandler.onClick()");

                    if (hasUnsavedChanges()) {
                            /*
                                Default to using the major change for new topics
                             */
                        if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null &&
                                getSearchResultsComponent().getProviderData().getDisplayedItem().returnIsAddItem())
                        {
                            display.getMessageLogDialog().getMajorChange().setValue(true);
                            display.getMessageLogDialog().getMessage().setValue(PressGangCCMSUI.INSTANCE.InitialTopicCreation());
                        }

                        display.getMessageLogDialog().getDialogBox().center();
                        display.getMessageLogDialog().getDialogBox().show();
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                } finally {
                    LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindActionButtons() saveClickHandler.onClick()");
                }
            }
        };

        final ClickHandler messageLogDialogOK = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO,
                            "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {

                        final String message = display.getMessageLogDialog().getMessage().getText();
                        final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE
                                : ServiceConstants.MAJOR_CHANGE);

                            /* Sync any changes back to the underlying object */
                        flushChanges();

                             /*
                             * Create a new instance of the topic, and copy out any updated, added or deleted fields. We don't
                             * do a clone or send the original object here because a full object will send back a whole lot of
                             * data that was never modified, wasting bandwidth, and chewing up CPU cycles as Errai serializes
                             * the data into JSON.
                             */
                        final RESTTopicV1 sourceTopic = getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();

                        final RESTTopicV1 newTopic = new RESTTopicV1();
                        newTopic.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                        newTopic.explicitSetSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
                        newTopic.explicitSetTags(new RESTTagCollectionV1());

                            /*
                                Only assign those modified children to the topic that is to be added/updated
                             */
                        LOGGER.log(Level.INFO, "Copying modified collections");
                        newTopic.getProperties().setItems(sourceTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems());
                        if (sourceTopic.getSourceUrls_OTM() != null && sourceTopic.getSourceUrls_OTM().getItems() != null) {
                            newTopic.getSourceUrls_OTM().setItems(sourceTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems());
                        }
                        newTopic.getTags().setItems(sourceTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems());

                            /*
                                Assume all the text fields have been updated
                             */
                        LOGGER.log(Level.INFO, "Copying modified fields");
                        newTopic.setId(sourceTopic.getId());
                        newTopic.explicitSetDescription(sourceTopic.getDescription());
                        newTopic.explicitSetLocale(sourceTopic.getLocale());
                        newTopic.explicitSetTitle(sourceTopic.getTitle());
                        newTopic.explicitSetXml(sourceTopic.getXml());

                        if (getSearchResultsComponent().getProviderData().getDisplayedItem().returnIsAddItem()) {
                            final BaseRestCallback<RESTTopicV1, Display> addCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                    display,
                                    new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                        @Override
                                        public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                                            try {
                                                LOGGER.log(Level.INFO,
                                                        "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");

                                                // Create the topic wrapper
                                                final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
                                                topicCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                                // create the topic, and add to the wrapper
                                                topicCollectionItem.setItem(retValue);

                                                    /* Update the displayed topic */
                                                getSearchResultsComponent().getProviderData().setDisplayedItem(topicCollectionItem.clone(true));

                                                    /*
                                                        Two things can happen to the selected item at this point. Either we are in the
                                                        "create topic" mode, in which we simply add the new topics to the data provider, and
                                                        never refresh from the database. In this case, the selected item and the item
                                                        in the data provider are the same, and always linked.

                                                        The second mode is where we have created a topic when already displaying a query.
                                                        In this case the selected item will be relinked in the relinkSelectedItem() method,
                                                        or it will remain referencing the returned value here if the query doesn't actually
                                                        return the topic that was saved.
                                                     */
                                                getSearchResultsComponent().getProviderData().setSelectedItem(topicCollectionItem);

                                                lastXML = null;

                                                if (startWithNewTopic) {
                                                    LOGGER.log(Level.INFO, "Adding new topic to static list");
                                                    getSearchResultsComponent().getProviderData().getItems().add(topicCollectionItem);
                                                    getSearchResultsComponent().getProviderData().setSize(getSearchResultsComponent().getProviderData().getItems().size());
                                                    updateDisplayAfterSave(false);
                                                } else {
                                                        /* Update the selected topic */
                                                    LOGGER.log(Level.INFO, "Redisplaying query");
                                                    updateDisplayAfterSave(true);
                                                }

                                                LOGGER.log(Level.INFO, "Refreshing editor");
                                                if (getTopicXMLComponent().getDisplay().getEditor() != null) {
                                                    getTopicXMLComponent().getDisplay().getEditor().redisplay();
                                                }

                                                Window.alert(PressGangCCMSUI.INSTANCE.TopicSaveSuccessWithID() + " " + retValue.getId());
                                            } finally {
                                                LOGGER.log(Level.INFO,
                                                        "EXIT SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");
                                            }
                                        }
                                    }, new BaseRestCallback.FailureAction<Display>() {
                                @Override
                                public void doFailureAction(final Display display) {
                                    getTopicXMLComponent().getDisplay().getEditor().redisplay();
                                }
                            }
                            );

                            RESTCalls.createTopic(addCallback, newTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
                        } else {
                            final BaseRestCallback<RESTTopicV1, Display> updateCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                    display,
                                    new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                        @Override
                                        public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                                            try {
                                                LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                                boolean overwroteChanges = false;
                                                final Integer originalRevision = getSearchResultsComponent().getProviderData().getSelectedItem().getItem().getRevision();

                                                if (retValue.getRevisions() != null && retValue.getRevisions().getItems() != null) {
                                                    Collections.sort(retValue.getRevisions().getItems(), new RESTTopicCollectionItemV1RevisionSort());

                                                        /*
                                                            If no changes were made to the topic itself (i.e. we just update some children),
                                                            then the revision number will not change. So if what is sent back has the same
                                                            revision number as the topic we were editing, then we have not overwritten background
                                                            changes.

                                                            Note that this should not happen because we don't actually just update the property tags;
                                                            any change to the property tag value results in the mapping being deleted and recreated.

                                                            The code is left here as a reminder that some additional checking might be required with
                                                            new children that are exposed through the UI.
                                                        */
                                                    if (retValue.getRevisions().getItems().size() >= 1) {
                                                        final Integer overwriteRevision = retValue.getRevisions().getItems()
                                                                .get(retValue.getRevisions().getItems().size() - 1).getItem().getRevision();

                                                        LOGGER.log(Level.INFO, "originalRevision: " + originalRevision + " new revision: " + overwriteRevision);

                                                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                                                    }

                                                        /*
                                                            Otherwise we need to make sure that the second last revision matches the revision
                                                             of the topic we were editing.
                                                         */
                                                    if (overwroteChanges && retValue.getRevisions().getItems().size() >= 2)
                                                    {
                                                            /* Get the second last revision (the last one is the current one) */
                                                        final Integer overwriteRevision = retValue.getRevisions().getItems()
                                                                .get(retValue.getRevisions().getItems().size() - 2).getItem().getRevision();

                                                        LOGGER.log(Level.INFO, "originalRevision: " + originalRevision + " last revision: " + overwriteRevision);

                                                            /*
                                                             * if the second last revision doesn't match the revision of the topic when editing was
                                                             * started, then we have overwritten someone elses changes
                                                             */
                                                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                                                    }
                                                }

                                                    /* Update the displayed topic */
                                                retValue.cloneInto(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem(),
                                                        true);
                                                    /* Update the selected topic */
                                                retValue.cloneInto(getSearchResultsComponent().getProviderData().getSelectedItem().getItem(), true);

                                                lastXML = null;

                                                updateDisplayAfterSave(false);

                                                if (overwroteChanges) {
                                                    Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                                } else {
                                                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                                }
                                            } finally {
                                                LOGGER.log(Level.INFO,
                                                        "EXIT SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                                if (getTopicXMLComponent().getDisplay().getEditor() != null) {
                                                    getTopicXMLComponent().getDisplay().getEditor().redisplay();
                                                }
                                            }
                                        }
                                    }, new BaseRestCallback.FailureAction<Display>() {
                                @Override
                                public void doFailureAction(final Display display) {
                                    getTopicXMLComponent().getDisplay().getEditor().redisplay();
                                }
                            }
                            );


                            RESTCalls.saveTopic(updateCallback, newTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
                        }
                    }
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();

                    LOGGER.log(Level.INFO,
                            "EXIT SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick()");
                }
            }
        };

        display.getMessageLogDialog().getOk().addClickHandler(messageLogDialogOK);

        display.getMessageLogDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        });

        final ClickHandler topicViewClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                    switchView(topicViewComponent.getDisplay());
                }
            }
        };

        final ClickHandler topicRevisionsClickHanlder = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                    switchView(topicRevisionsComponent.getDisplay());
                }
            }
        };

        getDisplay().getSave().addClickHandler(saveClickHandler);
        getDisplay().getHistory().addClickHandler(topicRevisionsClickHanlder);
        getDisplay().getFields().addClickHandler(topicViewClickHandler);

        addKeyboardShortcutEvents(getTopicXMLComponent().getDisplay(), display);
    }

    @Override
    protected void postInitializeViews(final List<BaseTemplateViewInterface> filter) {
        if (viewIsInFilter(filter, topicViewComponent.getDisplay())) {
            topicViewComponent.getDisplay().displayTopicDetails(this.getDisplayedTopic(), isReadOnlyMode(), locales);
        }

        /*
            The revision display always displays details from the main topic, and not the selected revision.
        */
        if (viewIsInFilter(filter, topicRevisionsComponent.getDisplay())) {
            LOGGER.log(Level.INFO, "\tInitializing topic revisions view");
            topicRevisionsComponent.getDisplay().display(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem(), isReadOnlyMode());
        }

        /*
            Bind logic to the tag buttons
         */
        if (viewIsInFilter(filter, getTopicTagsComponent().getDisplay())) {
            LOGGER.log(Level.INFO, "\tInitializing topic tags view");
            bindTagEditingButtons();
        }
    }

    @Override
    protected boolean isReadOnlyMode() {
        return this.topicRevisionsComponent.getDisplay().getRevisionTopic() != null;
    }

    @Override
    protected void bindFilteredResultsButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindFilteredResultsButtons()");

            final ClickHandler createClickHanlder = new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    createNewTopic();
                }
            };

            getSearchResultsComponent().getDisplay().getCreate().addClickHandler(createClickHanlder);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindFilteredResultsButtons()");
        }
    }

    @Override
    public void parseToken(final String historyToken) {

        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.parseToken()");

            setQueryString(removeHistoryToken(historyToken, SearchResultsAndTopicPresenter.HISTORY_TOKEN));

            if (getQueryString().startsWith(Constants.CREATE_PATH_SEGMENT_PREFIX)) {
                startWithNewTopic = true;
                setQueryString(null);
            } else if (!getQueryString().startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                /* Make sure that the query string has at least the prefix */
                setQueryString(Constants.QUERY_PATH_SEGMENT_PREFIX);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.parseToken()");
        }
    }

    /**
     * When the default locale and the topic list have been loaded we can display the fisrt topic if only
     * one was returned.
     */
    private void displayNewTopic()
    {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.displayNewTopic()");

            if (defaultLocale != null && locales != null && startWithNewTopic) {
                createNewTopic();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.displayNewTopic()");
        }
    }

    /**
     * Refresh the split panel rendered view
     *
     * @param forceExternalImages true if external images should be displayed, false if they should only be displayed
     *                            after the topics has not been edited after a period of time
     */
    private void refreshRenderedView(final boolean forceExternalImages) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.refreshRenderedView()");

            try {
                getTopicXMLComponent().getDisplay().getDriver().flush();
            } catch (final IllegalStateException ex) {
                LOGGER.log(Level.WARNING, "getTopicXMLComponent().getDisplay().getDriver().flush() threw an IllegalStateException. This probably happened because the rendered view was refreshed before the XML editor was bound.");
            }

            final boolean xmlHasChanges = lastXML == null || !lastXML.equals(this.getDisplayedTopic().getXml());

            if (xmlHasChanges) {
                lastXMLChange = System.currentTimeMillis();
            }

            final Boolean timeToDisplayImage = forceExternalImages || System.currentTimeMillis() - lastXMLChange >= Constants.REFRESH_RATE_WTH_IMAGES;

            if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
                isDisplayingImage = timeToDisplayImage;
                getTopicSplitPanelRenderedDisplay().displayTopicRendered(this.getDisplayedTopic(), isReadOnlyMode(), isDisplayingImage);
            }

            lastXML = this.getDisplayedTopic().getXml();
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.refreshRenderedView()");
        }
    }

    protected final void beforeSwitchView(final BaseTemplateViewInterface displayedView) {
        flushChanges();
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.flushChanges()");

            if (lastDisplayedView == null || !(lastDisplayedView instanceof BasePopulatedEditorViewInterface)) {
                return;
            }

            /* These are read only views */
            if (lastDisplayedView == getTopicXMLErrorsPresenter().getDisplay() || lastDisplayedView == getTopicTagsComponent().getDisplay()) {
                return;
            }

            ((BasePopulatedEditorViewInterface)lastDisplayedView).getDriver().flush();
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.flushChanges()");
        }
    }

    /**
     * @return A provider to be used for the topic revisions display list
     */
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateTopicRevisionsListProvider() {
        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicCollectionItemV1> display) {
                if (SearchResultsAndTopicPresenter.this.getSearchResultsComponent().getProviderData().getDisplayedItem() != null
                        && SearchResultsAndTopicPresenter.this.getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevisions() != null
                        && SearchResultsAndTopicPresenter.this.getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevisions().getItems() != null) {
                    displayNewFixedList(SearchResultsAndTopicPresenter.this.getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevisions()
                            .getItems());
                } else {
                    resetProvider();
                }
            }
        };
        return provider;
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (getTopicTagsComponent().getDisplay().getEditor() == null) {
                return;
            }

            if (getTopicTagsComponent().getDisplay().getEditor().projects == null) {
                return;
            }

            for (final TopicTagViewProjectEditor topicTagViewProjectEditor : getTopicTagsComponent().getDisplay().getEditor().projects.getEditors()) {

                if (topicTagViewProjectEditor.categories == null || topicTagViewProjectEditor.categories.getEditors() == null) {
                    LOGGER.log(Level.INFO, "categories is null");
                    break;
                }

                for (final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories.getEditors()) {

                    if (topicTagViewCategoryEditor.myTags == null || topicTagViewCategoryEditor.myTags.getEditors() == null) {
                        LOGGER.log(Level.INFO, "myTags is null");
                        break;
                    }

                    for (final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                        topicTagViewTagEditor.getDelete().addClickHandler(new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag()));
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindTagEditingButtons()");
        }
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewTopicRevisionButton() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindViewTopicRevisionButton()");

            topicRevisionsComponent.getDisplay().getDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {
                    final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                            topicRevisionsComponent.getDisplay(),
                            new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                                @Override
                                public void doSuccessAction(final RESTTopicV1 retValue, final TopicRevisionsPresenter.Display display) {
                                    final String retValueLabel = PressGangCCMSUI.INSTANCE.TopicID()
                                            + ": "
                                            + retValue.getId()
                                            + " "
                                            + PressGangCCMSUI.INSTANCE.TopicRevision()
                                            + ": "
                                            + retValue.getRevision().toString()
                                            + " "
                                            + PressGangCCMSUI.INSTANCE.RevisionDate()
                                            + ": "
                                            + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_FULL).format(
                                            retValue.getLastModified());

                                    final String sourceTopicLabel = PressGangCCMSUI.INSTANCE.TopicID()
                                            + ": "
                                            + getDisplayedTopic().getId()
                                            + " "
                                            + PressGangCCMSUI.INSTANCE.TopicRevision()
                                            + ": "
                                            + getDisplayedTopic().getRevision().toString()
                                            + " "
                                            + PressGangCCMSUI.INSTANCE.RevisionDate()
                                            + ": "
                                            + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_FULL).format(
                                            getDisplayedTopic().getLastModified());

                                    /* See if the topic contains valid XML or not */
                                    boolean isXML = true;
                                    try {
                                        XMLParser.parse(getDisplayedTopic().getXml());
                                    } catch (final DOMParseException ex) {
                                        isXML = false;
                                    }

                                    topicRevisionsComponent.displayDiff(retValue.getXml(), retValueLabel, getDisplayedTopic().getXml(), sourceTopicLabel, isXML);
                                }
                            });
                    RESTCalls.getTopicRevision(callback, revisionTopic.getItem().getId(), revisionTopic.getItem().getRevision());
                }
            });

            topicRevisionsComponent.getDisplay().getViewButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");

                        /* Reset the reference to the revision topic */
                        viewLatestTopicRevision();

                        if (!revisionTopic.getItem().getRevision().equals(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevision())) {
                            /* Reset the reference to the revision topic */
                            topicRevisionsComponent.getDisplay().setRevisionTopic(revisionTopic);
                        }

                        initializeViews();

                        topicRevisionsComponent.getDisplay().setProvider(generateTopicRevisionsListProvider());
                        getTopicPropertyTagPresenter().getDisplay().setExistingChildrenProvider(getTopicPropertyTagPresenter().generateExistingProvider(getDisplayedTopic()));

                        switchView(topicRevisionsComponent.getDisplay());
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindViewTopicRevisionButton()");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.hasUnsavedChanges()");

            /* No topic selected, so no changes need to be saved */
            if (this.getSearchResultsComponent().getProviderData().getDisplayedItem() == null) {
                return false;
            }

            /* if there is no selected item, we are trying to save a new topic */
            if (this.getSearchResultsComponent().getProviderData().getSelectedItem() == null) {
                return true;
            }

            /* Save any pending changes */
            flushChanges();

            final RESTTopicV1 displayedTopic = this.getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();
            final RESTTopicV1 selectedTopic = this.getSearchResultsComponent().getProviderData().getSelectedItem().getItem();

            boolean unsavedChanges = false;

            /*
                If there are any modified tags in newTopic, we have unsaved changes.
                If getTags() is null, the tags have not been loaded yet (and can't have been modified).
            */
            if (displayedTopic.getTags() != null &&
                    !displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                unsavedChanges = true;
            }

            /* If there are any modified property tags in newTopic, we have unsaved changes */
            if (!displayedTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                unsavedChanges = true;
            }

            /* If there are any modified source urls in newTopic, we have unsaved changes */
            if (!displayedTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                unsavedChanges = true;
            }

            /*
             * If any values in selectedTopic don't match displayedTopic, we have unsaved changes
             */
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getTitle(), displayedTopic.getTitle())) {
                unsavedChanges = true;
            }
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getLocale(), displayedTopic.getLocale())) {
                unsavedChanges = true;
            }
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getDescription(),
                    displayedTopic.getDescription()))                                                 {
                unsavedChanges = true;
            }
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getXml(), displayedTopic.getXml())) {
                unsavedChanges = true;
            }

            return unsavedChanges;
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.hasUnsavedChanges()");
        }
    }

    /**
     *   topicRevisionsComponent.getDisplay().getRevisionTopic() is used to indicate which revision
     *   of a topic we are looking at. Setting it to null means that we are not looking at a revision.
     */
    private void viewLatestTopicRevision() {
        topicRevisionsComponent.getDisplay().setRevisionTopic(null);
    }

    /**
     * Called to create a new topic
     */
    private void createNewTopic() {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.createNewTopic()");

            /* make sure there are no unsaved changes, or that the user is happy to continue without saving */
            if (!isOKToProceed()) {
                return;
            }

            // Create the topic wrapper
            final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
            topicCollectionItem.setState(RESTBaseCollectionItemV1.ADD_STATE);

            // create the topic, and add to the wrapper
            final RESTTopicV1 restTopic = new RESTTopicV1();
            restTopic.setProperties(new RESTAssignedPropertyTagCollectionV1());
            restTopic.setTags(new RESTTagCollectionV1());
            restTopic.setRevisions(new RESTTopicCollectionV1());
            restTopic.setSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
            restTopic.setLocale(defaultLocale);
            topicCollectionItem.setItem(restTopic);

            // the topic won't show up in the list of topics until it is saved, so the
            // selected item is null
            this.getSearchResultsComponent().getProviderData().setSelectedItem(null);

            // the new topic is being displayed though, so we set the displayed item
            this.getSearchResultsComponent().getProviderData().setDisplayedItem(topicCollectionItem);

            updateViewsAfterNewEntityLoaded();
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.createNewTopic()");
        }
    }

    /**
     * Retrieve a list of locales from the server.
     *
     * @param loadedCallback The callback to call when the locales are loaded
     */
    private void populateLocales(final StringListLoaded loadedCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.populateLocales()");

            final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                            /* Get the list of locales from the StringConstant */
                    final List<String> locales = new LinkedList<String>(Arrays.asList(retValue.getValue()
                            .replaceAll(CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED, "").replaceAll(LINE_BREAK_ESCAPED, "")
                            .replaceAll(" ", "").split(COMMA)));

                            /* Clean the list */
                    while (locales.contains("")) {
                        locales.remove("");
                    }

                    Collections.sort(locales);

                    loadedCallback.stringListLoaded(locales);
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.populateLocales()");
        }
    }

    private void loadDefaultLocale(final StringLoaded loadedCallback)
    {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.loadDefaultLocale()");

            final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                    loadedCallback.stringLoaded(retValue.getValue());
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.DEFAULT_LOCALE_ID);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.loadDefaultLocale()");
        }
    }

    /**
     * Retrieve a list of xml elements from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    private void populateXMLElements(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.populateXMLElements()");

            final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.populateXMLElements() callback.doSuccessAction()");

                        /* Get the list of locales from the StringConstant */
                        final List<String> xmlElements = new LinkedList<String>(Arrays.asList(retValue.getValue()
                                .replaceAll(CARRIAGE_RETURN_AND_LINE_BREAK, LINE_BREAK).replaceAll(" ", "").split(LINE_BREAK)));

                        /* Clean the list */
                        while (xmlElements.contains("")) {
                            xmlElements.remove("");
                        }

                        Collections.sort(xmlElements);

                        loadedCallback.stringListLoaded(xmlElements);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.populateXMLElements() callback.doSuccessAction()");
                    }
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.populateXMLElements()");
        }
    }

    /**
     * Retrieve a list of xml elements from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    private void populateXMLTemplates(final BaseTemplateViewInterface waitDisplay, final StringMapLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                /* Get the list of template string constant ids from the StringConstant */
                final Set<String> xmlElements = new HashSet<String>(Arrays.asList(GWTUtilities.fixUpIdSearchString(
                        retValue.getValue()).split(COMMA)));
                final Map<String, String> data = new TreeMap<String, String>();

                /* work around the inability to modify an int from an anonymous class */
                final int[] counter = new int[]{0};

                for (final String id : xmlElements) {
                    try {
                        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                                waitDisplay,
                                new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                                    @Override
                                    public void doSuccessAction(final RESTStringConstantV1 retValue,
                                                                final BaseTemplateViewInterface display) {

                                        ++counter[0];

                                        data.put(retValue.getName(), retValue.getValue());

                                                /*
                                                 * If this was the last call, return the data to the callee.
                                                 */
                                        if (counter[0] == xmlElements.size()) {
                                            loadedCallback.stringMapLoaded(data);
                                        }
                                    }
                                }, new BaseRestCallback.FailureAction<BaseTemplateViewInterface>() {

                            @Override
                            public void doFailureAction(final BaseTemplateViewInterface display) {
                                ++counter[0];
                                if (counter[0] == xmlElements.size()) {
                                    loadedCallback.stringMapLoaded(data);
                                }
                            }
                        }
                        );

                        RESTCalls.getStringConstant(callback, Integer.parseInt(id));
                    } catch (final NumberFormatException ex) {
                        // this should not happen if GWTUtilities.fixUpIdSearchString() does its job properly
                    }
                }

            }
        });

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_TEMPLATES_STRING_CONSTANT_ID);
    }

    private boolean isAnyDialogBoxesOpen(final TopicXMLPresenter.Display topicXMLDisplay) {
        if (topicXMLDisplay.getXmlTagsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().isShowing()) {
            return true;
        }

        return false;
    }

    /**
     * Add listeners for keyboard events
     *
     * @param topicXMLDisplay      The XML editing view
     * @param display              The main view
     * @param currentTopicCallback Called to get the currently displayed topic
     */
    private void addKeyboardShortcutEventHandler(final TopicXMLPresenter.Display topicXMLDisplay,
                                                 final BaseTemplateViewInterface display, final GetCurrentTopic currentTopicCallback) {
        final Event.NativePreviewHandler keyboardShortcutPreviewhandler = new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(final Event.NativePreviewEvent event) {
                final NativeEvent ne = event.getNativeEvent();

                if (ne.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()) {
                                if (topicXMLDisplay.getXmlTagsDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getXmlTagsDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().hide();
                                }

                                if (topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().isShowing()) {
                                    topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().hide();
                                }
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'Q') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getXmlTagsDialog().getDialogBox().center();
                                topicXMLDisplay.getXmlTagsDialog().getDialogBox().show();
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'W') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().center();
                                topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().show();
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'E') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().center();
                                topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().show();
                            }
                        }
                    });

                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'R') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                topicXMLDisplay.getPlainTextXMLDialog().setText(
                                        currentTopicCallback.getCurrentlyEditedTopic().getXml());
                                topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().center();
                                topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().show();
                            }
                        }
                    });
                }

            }
        };

        populateXMLElements(display, new StringListLoaded() {

            @Override
            public void stringListLoaded(final List<String> xmlElements) {
                topicXMLDisplay.getXmlTagsDialog().setSuggestions(xmlElements);
            }
        });

        populateXMLTemplates(display, new StringMapLoaded() {

            @Override
            public void stringMapLoaded(final Map<String, String> stringMap) {
                topicXMLDisplay.getXmlTemplatesDialog().setSuggestions(stringMap);

            }
        });

        Event.addNativePreviewHandler(keyboardShortcutPreviewhandler);
    }

    /**
     * Add event handlers to the buttons in the various dialog boxes.
     *
     * @param topicXMLDisplay The XML editing view
     * @param display         The main view
     */
    private void addKeyboardShortcutEvents(final TopicXMLPresenter.Display topicXMLDisplay,
                                           final BaseTemplateViewInterface display) {
        topicXMLDisplay.getXmlTagsDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertElement(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getOptions().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                final int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    final int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertElement(topicXMLDisplay);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertElement(topicXMLDisplay);
                }
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideElementDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getMoreInfo().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                final int selectedItem = topicXMLDisplay.getXmlTagsDialog().getOptions().getSelectedIndex();
                if (selectedItem != -1) {
                    final String value = topicXMLDisplay.getXmlTagsDialog().getOptions().getItemText(selectedItem);
                    Window.open(Constants.DOCBOOK_ELEMENT_URL_PREFIX + value + Constants.DOCBOOK_ELEMENT_URL_POSTFIX, "_blank",
                            "");
                }

            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getIds().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                final int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    final int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertCspDetails(topicXMLDisplay, display);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertCspDetails(topicXMLDisplay, display);
                }

            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertCspDetails(topicXMLDisplay, display);
            }
        });

        topicXMLDisplay.getCSPTopicDetailsDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideCspDetailsDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getOptions().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                final int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    final int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        insertTemplate(topicXMLDisplay);
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    insertTemplate(topicXMLDisplay);
                }

            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertTemplate(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTemplatesDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                hideTemplateDialogBox(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getPlainTextXMLDialog().getOK().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                copyTextToTopic(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getPlainTextXMLDialog().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                hidePlainTextXMLDialog(topicXMLDisplay);
            }
        });
    }

    private void hidePlainTextXMLDialog(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void copyTextToTopic(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getEditor().setText(topicXMLDisplay.getPlainTextXMLDialog().getText());
        hidePlainTextXMLDialog(topicXMLDisplay);
    }

    private void hideCspDetailsDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void hideElementDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTagsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void hideTemplateDialogBox(final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void insertElement(final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTagsDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTagsDialog().getOptions().getItemText(selectedItem);
            topicXMLDisplay.getEditor().wrapSelection("<" + value + ">", "</" + value + ">");
        }
        hideElementDialogBox(topicXMLDisplay);
    }

    private void insertTemplate(final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getValue(selectedItem);
            topicXMLDisplay.getEditor().insertAtCursor(value);
        }
        hideTemplateDialogBox(topicXMLDisplay);
    }

    private void insertCspDetails(final TopicXMLPresenter.Display topicXMLDisplay,
                                  final BaseTemplateViewInterface display) {
        final String ids = GWTUtilities.fixUpIdSearchString(topicXMLDisplay.getCSPTopicDetailsDialog().getIds().getValue());
        if (!ids.isEmpty()) {
            final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
                @Override
                public void begin() {
                    display.addWaitOperation();
                }

                @Override
                public void generalException(final Exception e) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    display.removeWaitOperation();
                }

                @Override
                public void success(final RESTTopicCollectionV1 retValue) {
                    try {
                        final StringBuilder details = new StringBuilder();
                        for (final RESTTopicCollectionItemV1 topicCollectionItem : retValue.getItems()) {
                            final RESTTopicV1 topic = topicCollectionItem.getItem();
                            if (!details.toString().isEmpty()) {
                                details.append("\n");
                            }
                            details.append(topic.getTitle() + " [" + topic.getId() + "]");
                        }

                        topicXMLDisplay.getEditor().insertText(details.toString());

                    } finally {
                        display.removeWaitOperation();
                    }
                }

                @Override
                public void failed(final Message message, final Throwable throwable) {
                    display.removeWaitOperation();
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                }
            };

            RESTCalls.getTopicsFromQuery(callback, Constants.QUERY_PATH_SEGMENT_PREFIX
                    + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ids);
            hideCspDetailsDialogBox(topicXMLDisplay);
        }

    }

    /**
     * A click handler to add a tag to a topic
     *
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = getTopicTagsComponent().getDisplay().getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            RESTTagCollectionItemV1 deletedTag = null;
            for (final RESTTagCollectionItemV1 tag : getSearchResultsComponent().getProviderData().getDisplayedItem().getItem()
                    .getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (RESTBaseCollectionItemV1.REMOVE_STATE.equals(tag.getState())) {
                        deletedTag = tag;
                        break;
                    } else {
                        /* Don't add tags twice */
                        Window.alert(PressGangCCMSUI.INSTANCE.TagAlreadyExists());
                        return;
                    }
                }
            }

            /*
             * If we get this far we are adding a tag to the topic. However, some categories are mutually exclusive, so we need
             * to remove any conflicting tags.
             */

            /* Find the mutually exclusive categories that the new tag belongs to */
            final Collection<RESTCategoryInTagCollectionItemV1> mutuiallyExclusiveCategories = Collections2.filter(selectedTag
                    .getCategories().getItems(), new Predicate<RESTCategoryInTagCollectionItemV1>() {

                @Override
                public boolean apply(final @Nullable RESTCategoryInTagCollectionItemV1 arg0) {
                    if (arg0 == null || arg0.getItem() == null) {
                        return false;
                    }
                    return arg0.getItem().getMutuallyExclusive();
                }
            });

            /* Find existing tags that belong to any of the mutually exclusive categories */
            final Collection<RESTTagCollectionItemV1> conflictingTags = Collections2.filter(getSearchResultsComponent()
                    .getProviderData().getDisplayedItem().getItem().getTags().getItems(),
                    new Predicate<RESTTagCollectionItemV1>() {

                        @Override
                        public boolean apply(final @Nullable RESTTagCollectionItemV1 existingTag) {

                            /* there is no match if the tag has already been removed */
                            if (existingTag == null || existingTag.getItem() == null
                                    || RESTBaseCollectionItemV1.REMOVE_STATE.equals(existingTag.getState())) {
                                return false;
                            }

                            /* loop over the categories that the tag belongs to */
                            return Iterables.any(existingTag.getItem().getCategories().getItems(),
                                    new Predicate<RESTCategoryInTagCollectionItemV1>() {

                                        @Override
                                        public boolean apply(
                                                final @Nullable RESTCategoryInTagCollectionItemV1 existingTagCategory) {
                                            if (existingTagCategory == null || existingTagCategory.getItem() == null) {
                                                return false;
                                            }

                                            /*
                                             * match any categories that the tag belongs to with any of the mutually exclusive
                                             * categories
                                             */
                                            return Iterables.any(mutuiallyExclusiveCategories,
                                                    new Predicate<RESTCategoryInTagCollectionItemV1>() {

                                                        @Override
                                                        public boolean apply(
                                                                final @Nullable RESTCategoryInTagCollectionItemV1 mutuallyExclusiveCategory) {
                                                            return mutuallyExclusiveCategory.getItem().getId()
                                                                    .equals(existingTagCategory.getItem().getId());
                                                        }
                                                    });

                                        }
                                    });
                        }
                    });

            if (!conflictingTags.isEmpty()) {
                final StringBuilder tags = new StringBuilder("\n");
                for (final RESTTagCollectionItemV1 tag : conflictingTags) {
                    tags.append("\n* " + tag.getItem().getName());
                }

                /* make sure the user is happy to remove the conflicting tags */
                if (!Window.confirm(PressGangCCMSUI.INSTANCE.RemoveConflictingTags() + tags.toString())) {
                    return;
                }

                for (final RESTTagCollectionItemV1 tag : conflictingTags) {
                    tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                }
            }

            if (deletedTag == null) {
                /* Get the selected tag, and clone it */
                final RESTTagV1 selectedTagClone = selectedTag.clone(true);
                /* Add the tag to the topic */
                getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{getTopicTagsComponent().getDisplay()}));
        }
    }

    /**
     * A click handler to remove a tag from a topic
     *
     * @author Matthew Casperson
     */
    private class DeleteTagClickHandler implements ClickHandler {
        private final RESTTagCollectionItemV1 tag;

        public DeleteTagClickHandler(final RESTTagCollectionItemV1 tag) {
            if (tag == null) {
                throw new IllegalArgumentException("tag cannot be null");
            }

            this.tag = tag;
        }

        @Override
        public void onClick(final ClickEvent event) {
            if (getSearchResultsComponent().getProviderData().getDisplayedItem() == null) {
                throw new IllegalStateException("getSearchResultsComponent().getProviderData().getDisplayedItem() cannot be null");
            }

            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{getTopicTagsComponent().getDisplay()}));
        }
    }

    public interface Display extends BaseSearchResultsAndTopicPresenter.Display {
        PushButton getSave();
        PushButton getHistory();
        Label getHistoryDown();
    }
}
