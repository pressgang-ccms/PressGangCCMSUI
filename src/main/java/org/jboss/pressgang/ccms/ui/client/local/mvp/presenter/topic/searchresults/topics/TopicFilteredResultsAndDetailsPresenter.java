package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.view.client.HasData;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.GetCurrentTopic;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringMapLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.*;
import org.zanata.rest.ElemSet;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * Extends the BaseTopicFilteredResultsAndDetailsPresenter class to provide the functionality required to
 * display, edit and create topics.
 */
@Dependent
public class TopicFilteredResultsAndDetailsPresenter extends BaseTopicFilteredResultsAndDetailsPresenter<
        RESTTopicV1,
        RESTTopicCollectionV1,
        RESTTopicCollectionItemV1,
        RESTTopicV1BasicDetailsEditor> {


    @Inject
    private TopicFilteredResultsPresenter searchResultsComponent;
    @Inject
    private TopicPresenter topicViewComponent;
    @Inject
    private TopicRevisionsPresenter topicRevisionsComponent;
    @Inject
    private Display display;

    /**
     * The global event bus.
     */
    @Inject
    private HandlerManager eventBus;


    /**
     * This entity is used to hold the tags that will be applied to
     * any bulk uploaded topics.
     */
    private final RESTTopicV1 bulkImportTemplate = new RESTTopicV1();


    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicFilteredResultsAndDetailsPresenter.class.getName());
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

    /**
     * true after the default locale has been loaded
     */
    @org.jetbrains.annotations.Nullable
    private String defaultLocale = null;

    /**
     * The last xml that was rendered
     */
    @org.jetbrains.annotations.Nullable
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

    /**
     * true after the locales have been loaded
     */
    private boolean localesLoaded = false;
    /**
     * true after the topics have been loaded
     */
    private boolean topicListLoaded = false;

    @NotNull
    @Override
    protected Display getDisplay() {
        return display;
    }

    @NotNull
    @Override
    protected BaseFilteredResultsPresenter<RESTTopicCollectionItemV1> getSearchResultsComponent() {
        return searchResultsComponent;
    }

    public TopicFilteredResultsAndDetailsPresenter() {
        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter()");
    }

    @Override
    protected void postBindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @Nullable final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTopicV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTopicV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    @NotNull final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                            getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final BaseTemplateViewInterface display) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getSourceUrls_OTM() != null, "The initially retrieved entity should have an expanded source urls collection");
                                checkArgument(retValue.getProperties() != null, "The initially retrieved entity should have an expanded properties collection");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    });
                    RESTCalls.getTopic(callback, selectedEntity.getId());
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        super.bindSearchAndEdit(topicId, pageId, getMainResizePreferencesKey(), getTopicXMLComponent().getDisplay(), topicViewComponent.getDisplay(),
                getSearchResultsComponent().getDisplay(), getSearchResultsComponent(), getDisplay(), getDisplay(), getNewEntityCallback);

        /* Bind the add button in the tags view */
        bindNewTagListBoxes(new AddTagClickHandler(
                new ReturnCurrentTopic() {
                    @NotNull
                    @Override
                    public RESTTopicV1 getTopic() {
                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTags() != null, "The displayed collection item to reference a valid entity and have a valid tags collection.");
                        return getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();
                    }
                }, new ReturnReadOnlyMode() {
                    @Override
                    public boolean getReadOnlyMode() {
                        return isReadOnlyMode();
                    }
                }, new BindRemoveButtons() {
                    @Override
                    public void bindRemoveButtons() {
                        bindTagEditingButtons();
                    }
                },
                getTopicTagsPresenter().getDisplay()
            ), getTopicTagsPresenter().getDisplay()
        );

        /* The template is used to hold tags, so we need to populate the tags collection */
        bulkImportTemplate.setTags(new RESTTagCollectionV1());

        /* Bind the add button in the bulk topic import dialog */
        bindNewTagListBoxes(new AddTagClickHandler(
                new ReturnCurrentTopic() {
                    @NotNull
                    @Override
                    public RESTTopicV1 getTopic() {
                        return bulkImportTemplate;
                    }
                }, new ReturnReadOnlyMode() {
                    @Override
                    public boolean getReadOnlyMode() {
                        return false;
                    }
                }, new BindRemoveButtons() {
                    @Override
                    public void bindRemoveButtons() {
                        bindBulkImportTagEditingButtons();
                    }
                },
                display.getBulkImport().getTagsView()
            ), display.getBulkImport().getTagsView());

        bindViewTopicRevisionButton();

        /* When the topics have been loaded, display the first one */
        getSearchResultsComponent().addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTopicCollectionV1>() {
            @Override
            public void onCollectionReceived(@NotNull final RESTTopicCollectionV1 topics) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");

                    topicListLoaded = true;
                    displayInitialTopic(getNewEntityCallback);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");
                }
            }
        });

        RESTCalls.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> locales) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bind() StringListLoaded.stringListLoaded()");

                    TopicFilteredResultsAndDetailsPresenter.this.locales = locales;
                    localesLoaded = true;
                    displayNewTopic();
                    displayInitialTopic(getNewEntityCallback);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bind() StringListLoaded.stringListLoaded()");
                }

            }
        }, display);

        loadDefaultLocale(new StringLoaded() {
            @Override
            public void stringLoaded(@NotNull final String string) {
                defaultLocale = string;
                displayNewTopic();
            }
        });

        addKeyboardShortcutEventHandler(this.getTopicXMLComponent().getDisplay(), this.getDisplay(), new GetCurrentTopic() {

            @Override
            public RESTTopicV1 getCurrentlyEditedTopic() {
                checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                return getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();
            }
        });

        getTags();
    }

    /**
     * Adds event handlers to the new tag combo boxes and add button. Similar to TopicTagsPresenter.bindNewTagListBoxes(),
     * but this version takes the tags display, so we can apply it to the bulk import dialog too.
     * @param clickHandler The Add button click handler
     * @param tagsDisplay The tags view
     */
    private void bindNewTagListBoxes(@NotNull final ClickHandler clickHandler, @NotNull final TopicTagsPresenter.Display tagsDisplay) {
        tagsDisplay.getProjectsList().addValueChangeHandler(new ValueChangeHandler<SearchUIProject>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<SearchUIProject> event) {
                tagsDisplay.updateNewTagCategoriesDisplay();
            }
        });

        tagsDisplay.getCategoriesList().addValueChangeHandler(new ValueChangeHandler<SearchUICategory>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<SearchUICategory> event) {
                tagsDisplay.updateNewTagTagDisplay();
            }
        });

        tagsDisplay.getAdd().addClickHandler(clickHandler);
    }

    /**
     * Gets the tags, so they can be displayed and added to topics. Unlike the TopicTagsPresenter.getTags() method,
     * here we populate both the tags view, and the tags view in the bulk topic import dialog.
     */
    private void getTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.getTags()");

            @NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, Display>(
                    display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, Display>() {
                @Override
                public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final Display display) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");

                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        getTopicTagsPresenter().getDisplay().initializeNewTags(retValue);
                        display.getBulkImport().getTagsView().initializeNewTags(retValue);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");
                    }
                }
            });
            RESTCalls.getTags(callback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.getTags()");
        }
    }

    @NotNull
    @Override
    protected  String getMainResizePreferencesKey() {
        return Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH;
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

    /**
     * The tags and bugs for a topic are loaded as separate operations to minimize the amount of data initially sent when a
     * topic is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    private void loadTagsAndBugs() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs()");

            /* Initiate the REST calls */
            final Integer id = getDisplayedTopic().getId();
            final Integer revision = getDisplayedTopic().getRevision();

            /* If this is a new topic, the id will be null, and there will not be any tags to get */
            if (id != null) {

                /* A callback to respond to a request for a topic with the tags expanded */
                @NotNull final RESTCalls.RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                        getTopicTagsPresenter().getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");

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
                            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");
                        }
                    }
                });

                RESTCalls.getTopicWithTags(topicWithTagsCallback, id);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs()");
        }
    }

    @Override
    protected void postLoadAdditionalDisplayedItemData() {
        loadTagsAndBugs();

        /* set the revisions to show the loading widget */
        if (topicRevisionsComponent.getDisplay().getProvider() != null) {
            topicRevisionsComponent.getDisplay().getProvider().resetProvider();
        }

        /* if getSearchResultsComponent().getProviderData().getSelectedItem() == null, then we are displaying a new topic */
        if (this.getSearchResultsComponent().getProviderData().getSelectedItem() != null) {

            checkState(getSearchResultsComponent().getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
            checkState(getSearchResultsComponent().getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

            this.topicRevisionsComponent.getDisplay().setProvider(this.topicRevisionsComponent.generateListProvider(getSearchResultsComponent().getProviderData().getSelectedItem().getItem().getId(), display));
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected RESTTopicV1 getDisplayedTopic() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.getDisplayedTopic()");

            final RESTTopicCollectionItemV1 sourceTopic = topicRevisionsComponent.getDisplay().getRevisionTopic() == null ? this.getSearchResultsComponent()
                    .getProviderData().getDisplayedItem() : topicRevisionsComponent.getDisplay().getRevisionTopic();

            return sourceTopic == null ? null : sourceTopic.getItem();
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.getDisplayedTopic()");
        }
    }

    @Override
    protected void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postEnableAndDisableActionButtons()");

            this.getDisplay().replaceTopActionButton(this.getDisplay().getHistoryDown(), this.getDisplay().getHistory());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), this.getDisplay().getFields());

            if (displayedView == this.topicViewComponent.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDown());
            } else if (displayedView == this.topicRevisionsComponent.getDisplay()) {
                this.getDisplay().replaceTopActionButton(this.getDisplay().getHistory(), this.getDisplay().getHistoryDown());
            }

            if (isReadOnlyMode()) {
                this.getDisplay().getHistory().addStyleName(CSSConstants.Common.ALERT_BUTTON);
            } else {
                this.getDisplay().getHistory().removeStyleName(CSSConstants.Common.ALERT_BUTTON);
            }



            this.getDisplay().getSave().setEnabled(!isReadOnlyMode());

        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.postEnableAndDisableActionButtons()");
        }
    }

    protected void postAfterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postAfterSwitchView()");

            /* Set the projects combo box as the focused element */
            if (displayedView == this.getTopicTagsPresenter().getDisplay() && getTopicTagsPresenter().getDisplay().getProjectsList().isAttached()) {
                getTopicTagsPresenter().getDisplay().getProjectsList().getElement().focus();
            }

                /* While editing the XML, we need to setup a refresh of the rendered view */
            if (displayedView == this.getTopicXMLComponent().getDisplay() && this.getDisplay().getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
                timer.scheduleRepeating(Constants.REFRESH_RATE);
            } else {
                timer.cancel();
                refreshRenderedView(true);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.postAfterSwitchView()");
        }
    }

    @Override
    protected void postBindActionButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postAfterSwitchView()");

            /* Build up a click handler to save the topic */
            final ClickHandler saveClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() saveClickHandler.onClick()");

                        if (hasUnsavedChanges()) {
                            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                            /*
                                Default to using the major change for new topics
                             */
                            if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null && getSearchResultsComponent().getProviderData().getDisplayedItem().returnIsAddItem()) {
                                display.getMessageLogDialog().getMajorChange().setValue(true);
                                display.getMessageLogDialog().getMessage().setValue(PressGangCCMSUI.INSTANCE.InitialTopicCreation());
                            }

                            display.getMessageLogDialog().getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));

                            display.getMessageLogDialog().getDialogBox().center();
                            display.getMessageLogDialog().getDialogBox().show();
                        } else {
                            Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                        }
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() saveClickHandler.onClick()");
                    }
                }
            };

            final ClickHandler messageLogDialogOK = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                        if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {

                            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                            final String user = display.getMessageLogDialog().getUsername().getText().trim();
                            Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                            @NotNull final StringBuilder message = new StringBuilder();
                            if (!user.isEmpty()) {
                                message.append(user).append(": ");
                            }
                            message.append(display.getMessageLogDialog().getMessage().getText());
                            @NotNull final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE : ServiceConstants.MAJOR_CHANGE);

                            /* Sync any changes back to the underlying object */
                            flushChanges();

                             /*
                             * Create a new instance of the topic, and copy out any updated, added or deleted fields. We don't
                             * do a clone or send the original object here because a full object will send back a whole lot of
                             * data that was never modified, wasting bandwidth, and chewing up CPU cycles as Errai serializes
                             * the data into JSON.
                             */
                            final RESTTopicV1 sourceTopic = getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();

                            @NotNull final RESTTopicV1 newTopic = new RESTTopicV1();
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
                                @NotNull final BaseRestCallback<RESTTopicV1, Display> addCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                        display,
                                        new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                            @Override
                                            public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                                                try {
                                                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");

                                                    // Create the topic wrapper
                                                    @NotNull final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
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
                                                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");
                                                }
                                            }
                                        }, new BaseRestCallback.FailureAction<Display>() {
                                    @Override
                                    public void doFailureAction(final Display display) {
                                        getTopicXMLComponent().getDisplay().getEditor().redisplay();
                                    }
                                }
                                );

                                RESTCalls.createTopic(addCallback, newTopic, message.toString(), flag, ServiceConstants.NULL_USER_ID.toString());
                            } else {
                                @NotNull final BaseRestCallback<RESTTopicV1, Display> updateCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                        display,
                                        new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                            @Override
                                            public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                                                try {
                                                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

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
                                                            Otherwise we need to make sure that the second last revision matches the revision of the topic we were editing.
                                                         */
                                                        if (overwroteChanges && retValue.getRevisions().getItems().size() >= 2) {
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
                                                    retValue.cloneInto(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem(),true);
                                                    /* Update the selected topic */
                                                    retValue.cloneInto(getSearchResultsComponent().getProviderData().getSelectedItem().getItem(), true);

                                                    lastXML = null;

                                                    updateDisplayAfterSave(false);

                                                    if (overwroteChanges) {
                                                        /* Take the user to the revisions view so they can review any overwritten changes */
                                                        switchView(topicRevisionsComponent.getDisplay());
                                                        Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                                    } else {
                                                        Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                                    }
                                                } finally {
                                                    LOGGER.log(Level.INFO,
                                                            "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

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


                                RESTCalls.saveTopic(updateCallback, newTopic, message.toString(), flag, ServiceConstants.NULL_USER_ID.toString());
                            }
                        }
                    } finally {
                        display.getMessageLogDialog().reset();
                        display.getMessageLogDialog().getDialogBox().hide();

                        LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
                    }
                }
            };

            final ClickHandler messageLogDialogCancel = new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();
                }
            };

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

            final ClickHandler bulkImport = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (!hasUnsavedChanges()) {
                        display.getBulkImport().getTagsView().display(bulkImportTemplate, false);
                        bindBulkImportTagEditingButtons();
                        display.getBulkImport().getDialog().center();
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.PleaseSaveChangesBeforeUploading());
                    }

                }
            };

            final ClickHandler bulkImportOK = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    try
                    {
                        display.getBulkImport().getTagsView().getDriver().flush();

                        if (display.getBulkImport().getFiles().getFiles().getLength() != 0) {
                            createNewTopic(false, 0, display.getBulkImport().getFiles().getFiles(), new ArrayList<Integer>(), new ArrayList<String>(), display.getBulkImport().getCommitMessage().getText());
                        }

                    } finally {
                        display.getBulkImport().getDialog().hide();
                    }
                }
            };

            final ClickHandler bulkImportCancel = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    display.getBulkImport().getDialog().hide();
                }
            };

            final ClickHandler bulkOverwrite = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (!hasUnsavedChanges()) {
                        display.getBulkOverwrite().getDialog().center();
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.PleaseSaveChangesBeforeUploading());
                    }

                }
            };

            final ClickHandler bulkOverwriteOK = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    try
                    {
                        if (display.getBulkOverwrite().getFiles().getFiles().getLength() != 0) {
                            createNewTopic(true, 0, display.getBulkOverwrite().getFiles().getFiles(), new ArrayList<Integer>(), new ArrayList<String>(), display.getBulkOverwrite().getCommitMessage().getText());
                        }

                    } finally {
                        display.getBulkOverwrite().getDialog().hide();
                    }
                }
            };

            final ClickHandler bulkOverwriteCancel = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    display.getBulkOverwrite().getDialog().hide();
                }
            };

            display.getMessageLogDialog().getOk().addClickHandler(messageLogDialogOK);
            display.getMessageLogDialog().getCancel().addClickHandler(messageLogDialogCancel);

            display.getBulkImport().getOK().addClickHandler(bulkImportOK);
            display.getBulkImport().getCancel().addClickHandler(bulkImportCancel);

            display.getBulkOverwrite().getOK().addClickHandler(bulkOverwriteOK);
            display.getBulkOverwrite().getCancel().addClickHandler(bulkOverwriteCancel);

            searchResultsComponent.getDisplay().getBulkImport().addClickHandler(bulkImport);
            searchResultsComponent.getDisplay().getBulkOverwrite().addClickHandler(bulkOverwrite);
            getDisplay().getSave().addClickHandler(saveClickHandler);
            getDisplay().getHistory().addClickHandler(topicRevisionsClickHanlder);
            getDisplay().getFields().addClickHandler(topicViewClickHandler);

            addKeyboardShortcutEvents(getTopicXMLComponent().getDisplay(), display);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.postAfterSwitchView()");
        }
    }

    /**
     *
     * @param overwrite true if files named 123.xml (where 123 is the topic id) should overwrite existing topics
     * @param index the current file being processed
     * @param files the list of files to process
     * @param ids the ids of the topics that have been modified
     * @param failedFiled the file names of files that were not processed
     * @param logMessage The message to use when overwriting topics
     */
    private void createNewTopic(final boolean overwrite, final int index, @NotNull final FileList files, @NotNull final List<Integer> ids, @NotNull final List<String> failedFiled, @NotNull final String logMessage) {
        if (index >= files.getLength()) {

            final StringBuilder message = new StringBuilder();

            if (failedFiled.size() == 0) {
                message.append(PressGangCCMSUI.INSTANCE.TopicsUplodedSuccessfully());
            } else {
                final StringBuilder failedNames = new StringBuilder();
                for (final String name : failedFiled) {
                    if (!failedNames.toString().isEmpty()) {
                        failedNames.append(",");
                    }
                    failedNames.append(name);
                }

                message.append(PressGangCCMSUI.INSTANCE.TopicsNotUplodedSuccessfully() + ": " + failedNames.toString());

                if (overwrite) {
                    message.append("\n" + PressGangCCMSUI.INSTANCE.OverwriteFilenameErrorMessage());
                }
            }

            if (ids.isEmpty()) {
                Window.alert(message.toString());
            } else if (Window.confirm(message.toString() + "\n" + PressGangCCMSUI.INSTANCE.OpenImportedTopics())) {
                /*
                    This gives the user the option to open a new search with just the imported topics.
                 */

                final StringBuilder idsQuery = new StringBuilder();
                for (final Integer id : ids) {
                    if (!idsQuery.toString().isEmpty()) {
                        idsQuery.append(",");
                    }
                    idsQuery.append(id);
                }

                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + idsQuery.toString(), false));
            } else if (startWithNewTopic) {
                /*
                    If this view was started by the Create Topic link in the menu (as opposed to a search),
                    then the new topics will just show up.
                 */
                updateDisplayAfterSave(false);
            } else {
                updateDisplayAfterSave(true);
            }

        } else {
            display.addWaitOperation();

            @NotNull final FileReader reader = new FileReader();

            reader.addErrorHandler(new org.vectomatic.file.events.ErrorHandler() {
                @Override
                public void onError(@NotNull final org.vectomatic.file.events.ErrorEvent event) {
                    display.removeWaitOperation();
                    failedFiled.add(files.getItem(index).getName());
                    createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                }
            });

            reader.addLoadEndHandler(new LoadEndHandler() {
                @Override
                public void onLoadEnd(@NotNull final LoadEndEvent event) {
                    try {
                        final String result = reader.getStringResult();

                         /* Populate the new topics details */
                        final RESTTopicV1 newTopic = new RESTTopicV1();
                        newTopic.explicitSetXml(result);
                        if (overwrite) {
                            newTopic.setId(overwriteFilenameAsInt(files.getItem(index).getName()));
                        } else {
                            newTopic.explicitSetTags(bulkImportTemplate.getTags());
                            newTopic.explicitSetLocale(defaultLocale);
                            newTopic.explicitSetTitle(PressGangCCMSUI.INSTANCE.ImportedTopic());
                        }

                        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, Display>(
                                display,
                                new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                    @Override
                                    public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                                        ids.add(retValue.getId());

                                        /*
                                            If we are working with a collection of new topics, add anything uploaded to
                                            that list.
                                         */
                                        if (startWithNewTopic) {
                                            final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
                                            topicCollectionItem.setItem(retValue);
                                            topicCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                            getSearchResultsComponent().getProviderData().getItems().add(topicCollectionItem);
                                            getSearchResultsComponent().getProviderData().setSize(getSearchResultsComponent().getProviderData().getItems().size());
                                        }

                                        createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                                    }
                                }, new BaseRestCallback.FailureAction<Display>() {
                            @Override
                            public void doFailureAction(@NotNull final Display display) {
                                createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                            }
                        });

                        if (overwrite) {
                            RESTCalls.saveTopic(callback, newTopic, logMessage, (int)ServiceConstants.MAJOR_CHANGE, ServiceConstants.NULL_USER_ID.toString());
                        } else {
                            RESTCalls.createTopic(callback, newTopic, logMessage, (int)ServiceConstants.MAJOR_CHANGE, ServiceConstants.NULL_USER_ID.toString());
                        }
                    } finally {
                        display.removeWaitOperation();
                    }
                }
            });

            if (overwrite) {
                if (overwriteFilenameAsInt(files.getItem(index).getName()) == null) {
                    display.removeWaitOperation();
                    failedFiled.add(files.getItem(index).getName());
                    createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                } else {
                    reader.readAsBinaryString(files.getItem(index));
                }
            } else {
                reader.readAsBinaryString(files.getItem(index));
            }
        }
    }

    @Nullable
    private Integer overwriteFilenameAsInt(@NotNull final String filename) {
        if (!filename.endsWith(".xml")) {
            return null;
        }

        final String fixedFilename = filename.replaceFirst(".xml", "");

        try {
            return Integer.parseInt(fixedFilename);

        } catch (@NotNull final NumberFormatException ex) {
            return null;
        }
    }


    @Override
    protected void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postInitializeViews()");

            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

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
            if (viewIsInFilter(filter, getTopicTagsPresenter().getDisplay())) {
                LOGGER.log(Level.INFO, "\tInitializing topic tags view");
                bindTagEditingButtons();
            }
        } finally {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postInitializeViews()");
        }
    }

    @Override
    protected boolean isReadOnlyMode() {
        return this.topicRevisionsComponent.getDisplay().getRevisionTopic() != null;
    }

    @Override
    protected void bindFilteredResultsButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");

            @NotNull final ClickHandler createClickHanlder = new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    createNewTopic();
                }
            };

            getSearchResultsComponent().getDisplay().getCreate().addClickHandler(createClickHanlder);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.parseToken()");

            setQueryString(removeHistoryToken(historyToken, TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));

            if (getQueryString().startsWith(Constants.CREATE_PATH_SEGMENT_PREFIX)) {
                startWithNewTopic = true;
                setQueryString(null);
            } else if (!getQueryString().startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                /* Make sure that the query string has at least the prefix */
                setQueryString(Constants.QUERY_PATH_SEGMENT_PREFIX);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.parseToken()");
        }
    }

    /**
     * When the default locale and the topic list have been loaded we can display the fisrt topic if only
     * one was returned.
     */
    private void displayNewTopic() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.displayNewTopic()");

            if (defaultLocale != null && locales != null && startWithNewTopic) {
                createNewTopic();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.displayNewTopic()");
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
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.refreshRenderedView()");

            try {
                getTopicXMLComponent().getDisplay().getDriver().flush();
            } catch (@NotNull final IllegalStateException ex) {
                LOGGER.log(Level.WARNING, "getTopicXMLComponent().getDisplay().getDriver().flush() threw an IllegalStateException. This probably happened because the rendered view was refreshed before the XML editor was bound.");
            }

            if (this.getDisplayedTopic() != null) {
                final boolean xmlHasChanges = lastXML == null || !lastXML.equals(this.getDisplayedTopic().getXml());

                if (xmlHasChanges) {
                    lastXMLChange = System.currentTimeMillis();
                }

                @NotNull final Boolean timeToDisplayImage = forceExternalImages || System.currentTimeMillis() - lastXMLChange >= Constants.REFRESH_RATE_WTH_IMAGES;

                if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
                    isDisplayingImage = timeToDisplayImage;
                    getTopicSplitPanelRenderedDisplay().displayTopicRendered(this.getDisplayedTopic(), isReadOnlyMode(), isDisplayingImage);
                }

                lastXML = this.getDisplayedTopic().getXml();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.refreshRenderedView()");
        }
    }

    protected void beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        flushChanges();
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.flushChanges()");

            if (lastDisplayedView == null || !(lastDisplayedView instanceof BasePopulatedEditorViewInterface)) {
                return;
            }

            /* These are read only views */
            if (lastDisplayedView == getTopicXMLErrorsPresenter().getDisplay() || lastDisplayedView == getTopicTagsPresenter().getDisplay()) {
                return;
            }

            ((BasePopulatedEditorViewInterface) lastDisplayedView).getDriver().flush();
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.flushChanges()");
        }
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (getTopicTagsPresenter().getDisplay().getEditor() == null) {
                return;
            }

            if (getTopicTagsPresenter().getDisplay().getEditor().projects == null) {
                return;
            }

            for (@NotNull final TopicTagViewProjectEditor topicTagViewProjectEditor : getTopicTagsPresenter().getDisplay().getEditor().projects.getEditors()) {

                checkState(topicTagViewProjectEditor.categories != null && topicTagViewProjectEditor.categories.getEditors() != null, "The project's categories editor collection should be valid");

                for (@NotNull final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories.getEditors()) {

                    checkState(topicTagViewCategoryEditor.myTags != null && topicTagViewCategoryEditor.myTags.getEditors() != null, "The category's tag editor collection should be valid");

                    for (@NotNull final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {

                        checkState(topicTagViewTagEditor.getTag() != null, "The tag editor should point to a valid tag ui data POJO.");
                        checkState(topicTagViewTagEditor.getTag().getTag() != null, "The tag editor should point to a valid tag ui data POJO, which should reference a valid tag entity.");

                        topicTagViewTagEditor.getDelete().addClickHandler(new DeleteTagClickHandler(
                            topicTagViewTagEditor.getTag().getTag(),
                            new ReturnCurrentTopic() {
                                @NotNull
                                @Override
                                public RESTTopicV1 getTopic() {

                                    checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                                    checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                                    checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTags() != null, "The displayed collection item to reference a valid entity and have a valid tags collection.");

                                    return getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();
                                }
                            }, new ReturnReadOnlyMode() {
                                @Override
                                public boolean getReadOnlyMode() {
                                    return isReadOnlyMode();
                                }
                            }, new BindRemoveButtons() {
                                @Override
                                public void bindRemoveButtons() {
                                    bindTagEditingButtons();
                                }
                            },
                            getTopicTagsPresenter().getDisplay()));
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindTagEditingButtons()");
        }
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindBulkImportTagEditingButtons() {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindBulkImportTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (display.getBulkImport().getTagsView().getEditor() == null) {
                return;
            }

            if (display.getBulkImport().getTagsView().getEditor().projects == null) {
                return;
            }

            for (@NotNull final TopicTagViewProjectEditor topicTagViewProjectEditor : display.getBulkImport().getTagsView().getEditor().projects.getEditors()) {

                checkState(topicTagViewProjectEditor.categories != null && topicTagViewProjectEditor.categories.getEditors() != null, "The project's categories editor collection should be valid");

                for (@NotNull final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories.getEditors()) {

                    checkState(topicTagViewCategoryEditor.myTags != null && topicTagViewCategoryEditor.myTags.getEditors() != null, "The category's tag editor collection should be valid");

                    for (@NotNull final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                        checkState(topicTagViewTagEditor.getTag() != null, "The tag editor should point to a valid tag ui data POJO.");
                        checkState(topicTagViewTagEditor.getTag().getTag() != null, "The tag editor should point to a valid tag ui data POJO, which should reference a valid tag entity.");

                        topicTagViewTagEditor.getDelete().addClickHandler(new DeleteTagClickHandler(
                                topicTagViewTagEditor.getTag().getTag(),
                                new ReturnCurrentTopic() {
                                    @NotNull
                                    @Override
                                    public RESTTopicV1 getTopic() {
                                        return bulkImportTemplate;
                                    }
                                },
                                new ReturnReadOnlyMode() {
                                    @Override
                                    public boolean getReadOnlyMode() {
                                        return false;
                                    }
                                },new BindRemoveButtons() {
                                    @Override
                                    public void bindRemoveButtons() {
                                        bindBulkImportTagEditingButtons();
                                    }
                                } ,
                                display.getBulkImport().getTagsView()));
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindBulkImportTagEditingButtons()");
        }
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewTopicRevisionButton() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton()");

            topicRevisionsComponent.getDisplay().getDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicCollectionItemV1 revisionTopic, final String value) {
                    @NotNull final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                            topicRevisionsComponent.getDisplay(),
                            new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                                @Override
                                public void doSuccessAction(@NotNull final RESTTopicV1 retValue, final TopicRevisionsPresenter.Display display) {
                                    checkState(getDisplayedTopic() != null, "There should be a displayed item.");

                                    if (getDisplayedTopic() != null) {
                                        @NotNull final String retValueLabel = PressGangCCMSUI.INSTANCE.TopicID()
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

                                        @NotNull final String sourceTopicLabel = PressGangCCMSUI.INSTANCE.TopicID()
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
                                        } catch (@NotNull final DOMParseException ex) {
                                            isXML = false;
                                        }

                                        topicRevisionsComponent.displayDiff(retValue.getXml(), retValueLabel, getDisplayedTopic().getXml(), sourceTopicLabel, isXML);
                                    }
                                }
                            });
                    RESTCalls.getTopicRevision(callback, revisionTopic.getItem().getId(), revisionTopic.getItem().getRevision());
                }
            });

            topicRevisionsComponent.getDisplay().getViewButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");

                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                        checkState(getDisplayedTopic() != null, "There should be a displayed item.");

                        /* Reset the reference to the revision topic */
                        viewLatestTopicRevision();

                        if (!revisionTopic.getItem().getRevision().equals(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevision())) {
                            /* Reset the reference to the revision topic */
                            topicRevisionsComponent.getDisplay().setRevisionTopic(revisionTopic);
                        }

                        initializeViews();

                        /* Load the tags and bugs */
                        loadTagsAndBugs();

                        getTopicPropertyTagPresenter().getDisplay().setExistingChildrenProvider(getTopicPropertyTagPresenter().generateExistingProvider(getDisplayedTopic()));

                        switchView(topicRevisionsComponent.getDisplay());
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton()");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");

            /* No topic selected, so no changes need to be saved */
            if (this.getSearchResultsComponent().getProviderData().getDisplayedItem() == null) {
                return false;
            }

            /* Save any pending changes */
            flushChanges();

            final RESTTopicV1 displayedTopic = this.getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();

             /*
                If there are any modified tags in newTopic, we have unsaved changes.
                If getTags() is null, the tags have not been loaded yet (and can't have been modified).
            */
            if (displayedTopic.getTags() != null &&
                    !displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }

            /* If there are any modified property tags in newTopic, we have unsaved changes */
            if (!displayedTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }

            /* If there are any modified source urls in newTopic, we have unsaved changes */
            if (!displayedTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }


            if (this.getSearchResultsComponent().getProviderData().getSelectedItem() == null) {

                /* if there is no selected item, we are trying to save a new topic */

                if (!displayedTopic.getXml().trim().isEmpty()) {
                    return true;
                }

                if (!displayedTopic.getTitle().trim().isEmpty()) {
                    return true;
                }

                if (!displayedTopic.getLocale().equals(defaultLocale)) {
                    return true;
                }

            } else {

                /* compare the displayed topics to the selected topic */

                final RESTTopicV1 selectedTopic = this.getSearchResultsComponent().getProviderData().getSelectedItem().getItem();

                /*
                 * If any values in selectedTopic don't match displayedTopic, we have unsaved changes
                 */
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getTitle(), displayedTopic.getTitle())) {
                    return true;
                }
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getLocale(), displayedTopic.getLocale())) {
                    return true;
                }
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getDescription(),
                        displayedTopic.getDescription())) {
                    return true;
                }
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getXml(), displayedTopic.getXml())) {
                    return true;
                }
            }

            return false;
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");
        }
    }

    /**
     * topicRevisionsComponent.getDisplay().getRevisionTopic() is used to indicate which revision
     * of a topic we are looking at. Setting it to null means that we are not looking at a revision.
     */
    private void viewLatestTopicRevision() {
        topicRevisionsComponent.getDisplay().setRevisionTopic(null);
    }

    /**
     * Called to create a new topic
     */
    private void createNewTopic() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.createNewTopic()");

            /* make sure there are no unsaved changes, or that the user is happy to continue without saving */
            if (!isOKToProceed()) {
                return;
            }

            // Create the topic wrapper
            @NotNull final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
            topicCollectionItem.setState(RESTBaseCollectionItemV1.ADD_STATE);

            // create the topic, and add to the wrapper
            @NotNull final RESTTopicV1 restTopic = new RESTTopicV1();
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
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.createNewTopic()");
        }
    }



    private void loadDefaultLocale(@NotNull final StringLoaded loadedCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadDefaultLocale()");

            @NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(@NotNull final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                    loadedCallback.stringLoaded(retValue.getValue());
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.DEFAULT_LOCALE_ID);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadDefaultLocale()");
        }
    }

    /**
     * Retrieve a list of xml elements from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    private void populateXMLElements(@NotNull final BaseTemplateViewInterface waitDisplay, @NotNull final StringListLoaded loadedCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.populateXMLElements()");

            @NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                    waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(@NotNull final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.populateXMLElements() callback.doSuccessAction()");

                        /* Get the list of locales from the StringConstant */
                        @NotNull final List<String> xmlElements = new LinkedList<String>(Arrays.asList(retValue.getValue()
                                .replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK, Constants.LINE_BREAK).replaceAll(" ", "").split(Constants.LINE_BREAK)));

                        /* Clean the list */
                        while (xmlElements.contains("")) {
                            xmlElements.remove("");
                        }

                        Collections.sort(xmlElements);

                        loadedCallback.stringListLoaded(xmlElements);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.populateXMLElements() callback.doSuccessAction()");
                    }
                }
            });

            RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.populateXMLElements()");
        }
    }

    /**
     * Retrieve a list of xml elements from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    private void populateXMLTemplates(@NotNull final BaseTemplateViewInterface waitDisplay, @NotNull final StringMapLoaded loadedCallback) {
        @NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                /* Get the list of template string constant ids from the StringConstant */
                @NotNull final Set<String> xmlElements = new HashSet<String>(Arrays.asList(GWTUtilities.fixUpIdSearchString(
                        retValue.getValue()).split(Constants.COMMA)));
                @NotNull final Map<String, String> data = new TreeMap<String, String>();

                /* work around the inability to modify an int from an anonymous class */
                @NotNull final int[] counter = new int[]{0};

                for (final String id : xmlElements) {
                    try {
                        @NotNull final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                                waitDisplay,
                                new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                                    @Override
                                    public void doSuccessAction(@NotNull final RESTStringConstantV1 retValue,
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
                    } catch (@NotNull final NumberFormatException ex) {
                        // this should not happen if GWTUtilities.fixUpIdSearchString() does its job properly
                    }
                }

            }
        });

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_TEMPLATES_STRING_CONSTANT_ID);
    }

    private boolean isAnyDialogBoxesOpen(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
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
    private void addKeyboardShortcutEventHandler(@NotNull final TopicXMLPresenter.Display topicXMLDisplay,
                                                 @NotNull final BaseTemplateViewInterface display, @NotNull final GetCurrentTopic currentTopicCallback) {
        @NotNull final Event.NativePreviewHandler keyboardShortcutPreviewhandler = new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(@NotNull final Event.NativePreviewEvent event) {
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
    private void addKeyboardShortcutEvents(@NotNull final TopicXMLPresenter.Display topicXMLDisplay,
                                           @NotNull final BaseTemplateViewInterface display) {
        topicXMLDisplay.getXmlTagsDialog().getOK().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                insertElement(topicXMLDisplay);
            }
        });

        topicXMLDisplay.getXmlTagsDialog().getOptions().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {
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
            public void onKeyPress(@NotNull final KeyPressEvent event) {
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
            public void onKeyPress(@NotNull final KeyPressEvent event) {
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

    private void hidePlainTextXMLDialog(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void copyTextToTopic(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getEditor().setText(topicXMLDisplay.getPlainTextXMLDialog().getText());
        hidePlainTextXMLDialog(topicXMLDisplay);
    }

    private void hideCspDetailsDialogBox(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void hideElementDialogBox(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTagsDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void hideTemplateDialogBox(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().hide();
        topicXMLDisplay.getEditor().focus();
    }

    private void insertElement(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTagsDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTagsDialog().getOptions().getItemText(selectedItem);
            topicXMLDisplay.getEditor().wrapSelection("<" + value + ">", "</" + value + ">");
        }
        hideElementDialogBox(topicXMLDisplay);
    }

    private void insertTemplate(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        final int selectedItem = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getSelectedIndex();
        if (selectedItem != -1) {
            final String value = topicXMLDisplay.getXmlTemplatesDialog().getOptions().getValue(selectedItem);
            topicXMLDisplay.getEditor().insertAtCursor(value);
        }
        hideTemplateDialogBox(topicXMLDisplay);
    }

    private void insertCspDetails(@NotNull final TopicXMLPresenter.Display topicXMLDisplay,
                                  @NotNull final BaseTemplateViewInterface display) {
        @NotNull final String ids = GWTUtilities.fixUpIdSearchString(topicXMLDisplay.getCSPTopicDetailsDialog().getIds().getValue());
        if (!ids.isEmpty()) {
            @NotNull final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>() {
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
                public void success(@NotNull final RESTTopicCollectionV1 retValue) {
                    try {
                        @NotNull final StringBuilder details = new StringBuilder();
                        for (@NotNull final RESTTopicCollectionItemV1 topicCollectionItem : retValue.getItems()) {
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

    private interface ReturnCurrentTopic {
        @NotNull RESTTopicV1 getTopic();
    }

    private interface ReturnReadOnlyMode {
        boolean getReadOnlyMode();
    }

    private interface BindRemoveButtons {
        void bindRemoveButtons();
    }

    /**
     * A click handler to add a tag to a topic
     *
     * @author Matthew Casperson
     */
    private static class AddTagClickHandler implements ClickHandler {

        private final ReturnCurrentTopic returnCurrentTopic;
        private final TopicTagsPresenter.Display tagDisplay;
        private final ReturnReadOnlyMode returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;

        /**
         * @param returnCurrentTopic A callback used to get the topic that the click handler is modifying
         * @param tagDisplay The display that the callback is modifying
         */
        public AddTagClickHandler(
                @NotNull final ReturnCurrentTopic returnCurrentTopic,
                @NotNull final ReturnReadOnlyMode returnReadOnlyMode,
                @NotNull final BindRemoveButtons bindRemoveButtons,
                @NotNull final TopicTagsPresenter.Display tagDisplay) {
            this.returnCurrentTopic = returnCurrentTopic;
            this.tagDisplay = tagDisplay;
            this.returnReadOnlyMode = returnReadOnlyMode;
            this.bindRemoveButtons = bindRemoveButtons;
        }

        @Override
        public void onClick(@NotNull final ClickEvent event) {

            final RESTTagV1 selectedTag = tagDisplay.getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            @Nullable RESTTagCollectionItemV1 deletedTag = null;
            for (@NotNull final RESTTagCollectionItemV1 tag : returnCurrentTopic.getTopic().getTags().getItems()) {
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
            final Collection<RESTCategoryInTagCollectionItemV1> mutuallyExclusiveCategories = Collections2.filter(selectedTag
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
            final Collection<RESTTagCollectionItemV1> conflictingTags = Collections2.filter(returnCurrentTopic.getTopic().getTags().getItems(),
                    new Predicate<RESTTagCollectionItemV1>() {

                        @Override
                        public boolean apply(final @Nullable RESTTagCollectionItemV1 existingTag) {

                            /* there is no match if the tag has already been removed */
                            if (existingTag == null || existingTag.getItem() == null || RESTBaseCollectionItemV1.REMOVE_STATE.equals(existingTag.getState())) {
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
                                            return Iterables.any(mutuallyExclusiveCategories,
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
                @NotNull final StringBuilder tags = new StringBuilder("\n");
                for (@NotNull final RESTTagCollectionItemV1 tag : conflictingTags) {
                    tags.append("\n* " + tag.getItem().getName());
                }

                /* make sure the user is happy to remove the conflicting tags */
                if (!Window.confirm(PressGangCCMSUI.INSTANCE.RemoveConflictingTags() + tags.toString())) {
                    return;
                }

                for (@NotNull final RESTTagCollectionItemV1 tag : conflictingTags) {
                    tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                }
            }

            if (deletedTag == null) {
                /* Get the selected tag, and clone it */
                final RESTTagV1 selectedTagClone = selectedTag.clone(true);
                /* Add the tag to the topic */
                returnCurrentTopic.getTopic().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            tagDisplay.display(returnCurrentTopic.getTopic(), returnReadOnlyMode.getReadOnlyMode());
            bindRemoveButtons.bindRemoveButtons();
        }
    }

    /**
     * A click handler to remove a tag from a topic
     *
     * @author Matthew Casperson
     */
    private static class DeleteTagClickHandler implements ClickHandler {
        private final RESTTagCollectionItemV1 tag;
        private final ReturnCurrentTopic returnCurrentTopic;
        private final TopicTagsPresenter.Display tagDisplay;
        private final ReturnReadOnlyMode returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;


        /**
         * @param returnCurrentTopic A callback used to get the topic that the click handler is modifying
         * @param tagDisplay The display that the callback is modifying
         */
        public DeleteTagClickHandler(
                @NotNull final RESTTagCollectionItemV1 tag,
                @NotNull final ReturnCurrentTopic returnCurrentTopic,
                @NotNull final ReturnReadOnlyMode returnReadOnlyMode,
                @NotNull final BindRemoveButtons bindRemoveButtons,
                @NotNull final TopicTagsPresenter.Display tagDisplay) {
            this.returnCurrentTopic = returnCurrentTopic;
            this.tagDisplay = tagDisplay;
            this.tag = tag;
            this.returnReadOnlyMode = returnReadOnlyMode;
            this.bindRemoveButtons = bindRemoveButtons;
        }

        @Override
        public void onClick(@NotNull final ClickEvent event) {

            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                returnCurrentTopic.getTopic().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            tagDisplay.display(returnCurrentTopic.getTopic(), returnReadOnlyMode.getReadOnlyMode());
            bindRemoveButtons.bindRemoveButtons();
        }
    }

    public interface Display extends BaseTopicFilteredResultsAndDetailsPresenter.Display<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {
        @NotNull PushButton getSave();
        @NotNull PushButton getHistory();
        @NotNull Label getHistoryDown();
        @NotNull BulkImport getBulkImport();
        @NotNull BulkOverwrite getBulkOverwrite();

    }

    /**
     * Defines the dialog box used to bulk overwrite topics.
     */
    public interface BulkOverwrite {
        @NotNull DialogBox getDialog();
        @NotNull FileUploadExt getFiles();
        @NotNull PushButton getOK();
        @NotNull PushButton getCancel();
        @NotNull TextArea getCommitMessage();
    }

    /**
     * Defines the dialog box used to bulk upload topics.
     */
    public interface BulkImport extends BulkOverwrite {
        @NotNull TopicTagsPresenter.Display getTagsView();

    }


}
