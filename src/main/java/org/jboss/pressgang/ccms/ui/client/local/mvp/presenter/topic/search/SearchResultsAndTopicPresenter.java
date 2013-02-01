package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.HasData;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.*;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.TopicListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.GetCurrentTopic;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringMapLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
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

import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1.REMOVE_STATE;
import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1.UPDATE_STATE;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class SearchResultsAndTopicPresenter
        extends BaseSearchAndEditComponent<
        SearchResultsPresenter.Display,
        RESTTopicV1,
        RESTTopicCollectionV1,
        RESTTopicCollectionItemV1,
        BaseTopicViewInterface,
        TopicPresenter.Display,
        RESTTopicV1BasicDetailsEditor>
        implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
    private static final String LINE_BREAK_ESCAPED = "\\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED = "\\r\\n";
    private static final String LINE_BREAK = "\n";
    private static final String CARRIAGE_RETURN_AND_LINE_BREAK = "\r\n";
    private static final String COMMA = ",";
    /**
     * false to indicate that the topic views should display action buttons applicable to established topics (as opposed to new
     * topics)
     */
    private static final boolean NEW_TOPIC = false;
    private static final Logger logger = Logger.getLogger(SearchResultsAndTopicPresenter.class.getName());
    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastDisplayedView == topicXMLComponent.getDisplay()) {
                refreshRenderedView(false);
            }
        }
    };
    @Inject
    private HandlerManager eventBus;
    /**
     * true if this presenter should be opened with a fresh topic, and false otherwise
     */
    private boolean startWithNewTopic = false;
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
    private String queryString;
    /**
     * A list of locales retrieved from the server
     */
    private List<String> locales;
    @Inject
    private Display display;
    @Inject
    private TopicPresenter topicViewComponent;
    @Inject
    private TopicXMLPresenter topicXMLComponent;
    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;
    @Inject
    private SearchResultsPresenter searchResultsComponent;
    @Inject
    private TopicXMLErrorsPresenter topicXMLErrorsPresenter;
    @Inject
    private TopicTagsPresenter topicTagsComponent;
    @Inject
    private TopicRevisionsPresenter topicRevisionsComponent;
    @Inject
    private TopicBIRTBugsPresenter topicBugsPresenter;
    @Inject
    private TopicRenderedPresenter topicRenderedPresenter;
    @Inject
    private TopicPropertyTagsPresenter topicPropertyTagPresenter;
    /**
     * How the rendering panel is displayed
     */
    private SplitType split = SplitType.NONE;

    @Override
    public void go(final HasWidgets container) {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.go()");


            /* A call back used to get a fresh copy of the entity that was selected */
            final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTopicV1>() {

                @Override
                public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTTopicV1> displayCallback) {

                    try {
                        logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bind() GetNewEntityCallback.getNewEntity()");

                        final RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                                display, new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(final RESTTopicV1 retValue, final BaseTemplateViewInterface display) {
                                try {
                                    logger.log(Level.INFO,
                                            "ENTER SearchResultsAndTopicPresenter.bind() RESTCallback.doSuccessAction()");

                                    logger.log(Level.INFO, "retValue.getProperties().getItems().size(): " + retValue.getProperties().getItems().size());

                                    displayCallback.displayNewEntity(retValue);
                                } finally {
                                    logger.log(Level.INFO,
                                            "EXIT SearchResultsAndTopicPresenter.bind() RESTCallback.doSuccessAction()");
                                }
                            }
                        });
                        RESTCalls.getTopic(callback, id);
                    } finally {
                        logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bind() GetNewEntityCallback.getNewEntity()");
                    }
                }
            };

            clearContainerAndAddTopLevelPanel(container, display);

            /* Initialize the other presenters we have pulled in */
            searchResultsComponent.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
            topicTagsComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
            topicPropertyTagPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);

            /* When the topics have been loaded, display the first one */
            searchResultsComponent.addTopicListReceivedHandler(new TopicListReceivedHandler() {
                @Override
                public void onTopicsRecieved(final RESTTopicCollectionV1 topics) {
                    if (topics.getItems() != null && topics.getItems().size() == 1) {
                        loadNewEntity(getNewEntityCallback, topics.getItems().get(0));
                    }
                }
            });

            super.bindSearchAndEdit(ServiceConstants.TOPIC_EDIT_VIEW_CONTENT_TOPIC, HISTORY_TOKEN, Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, topicXMLComponent.getDisplay(), topicViewComponent.getDisplay(),
                    searchResultsComponent.getDisplay(), searchResultsComponent, display, display, getNewEntityCallback);

            /* Display the split panes */
            initializeDisplay();

            // the birt bugs presenter is just an iframe, and doesn't need any providers
            // this.topicBugsPresenter.getDisplay().setProvider(generateTopicBugListProvider());
            this.topicRevisionsComponent.getDisplay().setProvider(generateTopicRevisionsListProvider());

            bindViewTopicRevisionButton();
            bindSplitPanelResize();
            bindTagEditingButtons();
            bindPropertyTagButtons();
            loadSplitPanelSize();

            this.topicTagsComponent.bindNewTagListBoxes(new AddTagClickhandler());

            populateLocales(display, new StringListLoaded() {
                @Override
                public void stringListLoaded(final List<String> locales) {
                    SearchResultsAndTopicPresenter.this.locales = locales;
                }
            });

            addKeyboardShortcutEventHandler(this.topicXMLComponent.getDisplay(), this.display, new GetCurrentTopic() {

                @Override
                public RESTTopicV1 getCurrentlyEditedTopic() {
                    return searchResultsComponent.getProviderData().getDisplayedItem().getItem();
                }
            });

            /* If a new topic was requested, then created it */
            if (startWithNewTopic) {
                createNewTopic();
            }
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.go()");
        }
    }

    @Override
    public void parseToken(final String historyToken) {

        queryString = removeHistoryToken(historyToken, SearchResultsAndTopicPresenter.HISTORY_TOKEN);

        if (queryString.startsWith(Constants.CREATE_PATH_SEGMENT_PREFIX)) {
            startWithNewTopic = true;
            queryString = null;
        } else if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            /* Make sure that the query string has at least the prefix */
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    /**
     * Refresh the split panel rendered view
     *
     * @param forceExternalImages true if external images should be displayed, false if they should only be displayed
     *                            after the topics has not been edited after a period of time
     */
    private void refreshRenderedView(final boolean forceExternalImages) {
        topicXMLComponent.getDisplay().getDriver().flush();

        final boolean xmlHasChanges = lastXML == null || !lastXML.equals(getTopicOrRevisionTopic().getItem().getXml());

        if (xmlHasChanges) {
            lastXMLChange = System.currentTimeMillis();
        }

        final Boolean timeToDisplayImage = forceExternalImages
                || System.currentTimeMillis() - lastXMLChange >= Constants.REFRESH_RATE_WTH_IMAGES;

        if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
            isDisplayingImage = timeToDisplayImage;
            topicSplitPanelRenderedDisplay.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC,
                    display.getSplitType(), locales, isDisplayingImage);
        }

        lastXML = getTopicOrRevisionTopic().getItem().getXml();
    }

    /**
     * Reflect the state of the editor with the XML editor toggle buttons
     */
    private void setXMLEditorButtonsToEditorState() {
        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.setXMLEditorButtonsToEditorState()");

            topicXMLComponent.getDisplay().getLineWrap().setDown(topicXMLComponent.getDisplay().getEditor().getUserWrapMode());
            topicXMLComponent.getDisplay().getShowInvisibles().setDown(topicXMLComponent.getDisplay().getEditor().getShowInvisibles());
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.setXMLEditorButtonsToEditorState()");
        }
    }

    /**
     * (Re)Initialize the main display with the rendered view split pane (if selected).
     */
    private void initializeDisplay() {
        final String savedSplit = Preferences.INSTANCE.getString(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE, "");
        if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE.equals(savedSplit)) {
            split = SplitType.NONE;
        } else if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL.equals(savedSplit)) {
            split = SplitType.VERTICAL;
        } else {
            split = SplitType.HORIZONTAL;
        }

        /* Have to do this after the parseToken method has been called */
        display.initialize(false, split, topicSplitPanelRenderedDisplay.getPanel());
        enableAndDisableActionButtons(lastDisplayedView);

        loadSplitPanelSize();
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        if (lastDisplayedView == null || lastDisplayedView.getDriver() == null) {
            return;
        }

        /* These are read only views */
        if (lastDisplayedView == topicXMLErrorsPresenter.getDisplay() || lastDisplayedView == topicTagsComponent.getDisplay()) {
            return;
        }

        lastDisplayedView.getDriver().flush();
    }

    /**
     * The currently displayed topic is topicRevisionsComponent.getDisplay().getRevisionTopic() if it is not null, or
     * searchResultsComponent.getProviderData().getDisplayedItem() otherwise.
     *
     * @return The currently displayed topic
     */
    private RESTTopicCollectionItemV1 getTopicOrRevisionTopic() {
        final RESTTopicCollectionItemV1 sourceTopic = topicRevisionsComponent.getDisplay().getRevisionTopic() == null ? searchResultsComponent
                .getProviderData().getDisplayedItem() : topicRevisionsComponent.getDisplay().getRevisionTopic();

        return sourceTopic;
    }

    /**
     * The UI is in a readonly mode if viewing a topic revision
     *
     * @return true if the UI is in readonly mode, and false otherwise
     */
    private boolean isReadOnlyMode() {
        return this.topicRevisionsComponent.getDisplay().getRevisionTopic() != null;
    }

    /**
     * Display the usual menus. This is called after the split rendering pane menu has been closed.
     */
    private void showRegularMenu() {
        this.display.displayChildView(this.lastDisplayedView);
    }

    /**
     * Display the split panel menu, which will remove all common and local action buttons
     */
    private void showRenderedSplitPanelMenu() {
        this.display.getViewActionButtonsParentPanel().clear();
        this.display.getViewActionButtonsParentPanel().setWidget(0, 0, this.display.getRenderedSplitViewMenu());
    }

    /**
     * Load the split panel sizes
     */
    private void loadSplitPanelSize() {
        try {
            this.logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.loadSplitPanelSize()");

            if (this.split == SplitType.HORIZONTAL) {
                this.display.getSplitPanel().setSplitPosition(
                        this.topicSplitPanelRenderedDisplay.getPanel().getParent(),
                        Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH,
                                Constants.SPLIT_PANEL_SIZE), false);
            } else if (this.split == SplitType.VERTICAL) {
                this.display.getSplitPanel().setSplitPosition(
                        this.topicSplitPanelRenderedDisplay.getPanel().getParent(),
                        Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH,
                                Constants.SPLIT_PANEL_SIZE), false);
            }
        } finally {
            this.logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.loadSplitPanelSize()");
        }
    }

    /**
     * @return A provider to be used for the topic revisions display list
     */
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateTopicRevisionsListProvider() {
        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicCollectionItemV1> display) {
                if (SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem() != null
                        && SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions() != null
                        && SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions().getItems() != null) {
                    displayNewFixedList(SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions()
                            .getItems());
                } else {
                    resetProvider();
                }
            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the topic display list
     */
    private EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> generateTopicBugListProvider() {
        final EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTBugzillaBugCollectionItemV1> display) {
                if (SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem() != null
                        && SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getBugzillaBugs_OTM() != null) {
                    displayNewFixedList(SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem()
                            .getBugzillaBugs_OTM().getItems());
                } else {
                    resetProvider();
                }
            }
        };
        return provider;
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindSplitPanelResize()");

            display.getSplitPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(final ResizeEvent event) {
                    if (topicXMLComponent.getDisplay().getEditor() != null) {
                        topicXMLComponent.getDisplay().getEditor().redisplay();
                    }

                    /*
                     * Saves the width of the split screen
                     */
                    if (split == SplitType.HORIZONTAL) {
                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH, display
                                .getSplitPanel().getSplitPosition(topicSplitPanelRenderedDisplay.getPanel().getParent()) + "");
                    } else if (split == SplitType.VERTICAL) {
                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH, display
                                .getSplitPanel().getSplitPosition(topicSplitPanelRenderedDisplay.getPanel().getParent()) + "");
                    }
                }
            });
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindSplitPanelResize()");
        }
    }

    /**
     * Add behaviour to the property tag add and remove buttons, and the value text edit field.
     */
    private void bindPropertyTagButtons()
    {
        this.topicPropertyTagPresenter.getDisplay().getPossibleChildrenButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTPropertyTagCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, final RESTPropertyTagCollectionItemV1 object, final String value) {

                        /* Create a new property tag child */
                        final RESTAssignedPropertyTagV1 restAssignedPropertyTagV1 = new RESTAssignedPropertyTagV1();
                        restAssignedPropertyTagV1.setId(object.getItem().getId());
                        restAssignedPropertyTagV1.setName(object.getItem().getName());
                        restAssignedPropertyTagV1.setDescription(object.getItem().getDescription());

                        SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().addNewItem(restAssignedPropertyTagV1);

                        /* Update the list of existing children */
                        Collections.sort(SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                                new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                        SearchResultsAndTopicPresenter.this.topicPropertyTagPresenter.refreshExistingChildList(
                                SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem());
                    }
                }
        );

        this.topicPropertyTagPresenter.getDisplay().getPropertyTagRemoveColumn().setFieldUpdater(
                new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

                        /*
                            Note that the relationship between topic and property tag is many to many.
                         */

                        if (object.returnIsAddItem()) {
                            /* Previously added items are just removed from the collection */
                            SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().getItems().remove(object);
                        } else {
                            /* Existing children are marked for removal */
                            object.setState(REMOVE_STATE);
                        }

                        /* Update the list of existing children */
                        SearchResultsAndTopicPresenter.this.topicPropertyTagPresenter.refreshExistingChildList(
                                SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem());
                    }
                }
        );

        this.topicPropertyTagPresenter.getDisplay().getPropertyTagValueColumn().setFieldUpdater(new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

                /*
                    Updating just the value (and no other topic fields or children) will not create a new Envers revision
                    for the topic. This makes it incredibly difficult to get the state of the topic, including the state
                    or the property tags, as the topic existed at a particular point in time because the state of the
                    assigned property tags may have been changed.

                    To force a new topic revision to be created, any time a value is updated the existing mapping is removed
                     and a new one created. This has the effect of creating a new topic revision to match the fact that
                     the value of a property tag has been changed.
                 */

                if (object.returnIsAddItem()) {
                    object.getItem().setValue(value);
                }
                else {
                    object.setState(REMOVE_STATE);

                    /* Create a new property tag child */
                    final RESTAssignedPropertyTagV1 restAssignedPropertyTagV1 = new RESTAssignedPropertyTagV1();
                    restAssignedPropertyTagV1.setId(object.getItem().getId());
                    restAssignedPropertyTagV1.setName(object.getItem().getName());
                    restAssignedPropertyTagV1.setDescription(object.getItem().getDescription());
                    restAssignedPropertyTagV1.setValue(value);

                    SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().addNewItem(restAssignedPropertyTagV1);

                    /* Update the list of existing children */
                    Collections.sort(SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                            new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                    SearchResultsAndTopicPresenter.this.topicPropertyTagPresenter.refreshExistingChildList(
                            SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem());
                }
            }
        });
    }


    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.bindTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (topicTagsComponent.getDisplay().getEditor() != null) {
                for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTagsComponent.getDisplay().getEditor().projects
                        .getEditors()) {
                    for (final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories
                            .getEditors()) {
                        for (final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                            topicTagViewTagEditor.getDelete().addClickHandler(
                                    new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag()));
                        }
                    }
                }
            }
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindTagEditingButtons()");
        }
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewTopicRevisionButton() {
        topicRevisionsComponent.getDisplay().getDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {
                final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                        topicRevisionsComponent.getDisplay(),
                        new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                            @Override
                            public void doSuccessAction(final RESTTopicV1 retValue,
                                                        final TopicRevisionsPresenter.Display display) {
                                final RESTTopicCollectionItemV1 sourceTopic = getTopicOrRevisionTopic();
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
                                        + DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL).format(
                                        retValue.getLastModified());

                                final String sourceTopicLabel = PressGangCCMSUI.INSTANCE.TopicID()
                                        + ": "
                                        + sourceTopic.getItem().getId()
                                        + " "
                                        + PressGangCCMSUI.INSTANCE.TopicRevision()
                                        + ": "
                                        + sourceTopic.getItem().getRevision().toString()
                                        + " "
                                        + PressGangCCMSUI.INSTANCE.RevisionDate()
                                        + ": "
                                        + DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL).format(
                                        sourceTopic.getItem().getLastModified());

                                /* See if the topic contains valid XML or not */
                                boolean isXML = true;
                                try {
                                    XMLParser.parse(sourceTopic.getItem().getXml());
                                } catch (final DOMParseException ex) {
                                    isXML = false;
                                }

                                topicRevisionsComponent.displayDiff(retValue.getXml(), retValueLabel, sourceTopic.getItem()
                                        .getXml(), sourceTopicLabel, isXML);
                            }
                        }) {

                };
                RESTCalls.getTopicRevision(callback, revisionTopic.getItem().getId(), revisionTopic.getItem().getRevision());
            }
        });

        topicRevisionsComponent.getDisplay().getViewButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                /* Reset the reference to the revision topic */
                topicRevisionsComponent.getDisplay().setRevisionTopic(null);

                if (!revisionTopic.getItem().getRevision()
                        .equals(searchResultsComponent.getProviderData().getDisplayedItem().getItem().getRevision())) {
                    /* Reset the reference to the revision topic */
                    topicRevisionsComponent.getDisplay().setRevisionTopic(revisionTopic);
                }

                /* Load the tags and bugs */
                loadTagsAndBugs();

                initializeViews();
                topicRevisionsComponent.getDisplay().setProvider(generateTopicRevisionsListProvider());
                switchView(topicRevisionsComponent.getDisplay());
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {

        /* No topic selected, so no changes need to be saved */
        if (this.searchResultsComponent.getProviderData().getDisplayedItem() == null) {
            return false;
        }

        /* if there is no selected item, we are trying to save a new topic */
        if (this.searchResultsComponent.getProviderData().getSelectedItem() == null) {
            return true;
        }

        /* Save any pending changes */
        flushChanges();

        final RESTTopicV1 displayedTopic = this.searchResultsComponent.getProviderData().getDisplayedItem().getItem();
        final RESTTopicV1 selectedTopic = this.searchResultsComponent.getProviderData().getSelectedItem().getItem();

        boolean unsavedChanges = false;

        /* If there are any modified tags in newTopic, we have unsaved changes */
        if (!displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
            unsavedChanges = true;
        }

        /* If there are any modified property tags in newTopic, we have unsaved changes */
        if (!displayedTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
            unsavedChanges = true;
        }

        /*
         * If any values in selectedTopic don't match displayedTopic, we have unsaved changes
         */
        if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getTitle(), displayedTopic.getTitle()))
            unsavedChanges = true;
        if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getLocale(), displayedTopic.getLocale()))
            unsavedChanges = true;
        if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getDescription(),
                displayedTopic.getDescription()))
            unsavedChanges = true;
        if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getXml(), displayedTopic.getXml()))
            unsavedChanges = true;

        return unsavedChanges;
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.loadAdditionalDisplayedItemData()");

            /* Display the tags that are added to the category */
            Collections.sort(SearchResultsAndTopicPresenter.this.searchResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                    new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
            topicPropertyTagPresenter.refreshExistingChildList(searchResultsComponent.getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of tags */
            topicPropertyTagPresenter.refreshPossibleChildrenDataAndList();

            /* reset the topic review view */
            topicRevisionsComponent.getDisplay().setRevisionTopic(null);

            /* set the revisions to show the loading widget */
            if (topicRevisionsComponent.getDisplay().getProvider() != null) {
                topicRevisionsComponent.getDisplay().getProvider().resetProvider();
            }

            /* if searchResultsComponent.getProviderData().getSelectedItem() == null, then we are displaying a new topic */
            if (searchResultsComponent.getProviderData().getSelectedItem() != null) {
                /* A callback to respond to a request for a topic with the revisions expanded */
                final RESTCalls.RESTCallback<RESTTopicV1> topicWithRevisionsCallback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                        topicRevisionsComponent.getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTTopicV1 retValue, final TopicRevisionsPresenter.Display display) {
                        searchResultsComponent.getProviderData().getDisplayedItem().getItem()
                                .setRevisions(retValue.getRevisions());

                                /* refresh the list */
                        topicRevisionsComponent.getDisplay().getProvider().displayNewFixedList(retValue.getRevisions().getItems());
                    }
                });

                RESTCalls.getTopicWithRevisions(topicWithRevisionsCallback, searchResultsComponent.getProviderData()
                        .getSelectedItem().getItem().getId());

                /* got on to load the tags and bugs */
                loadTagsAndBugs();
            }



            /* fix the rendered view buttons */
            initializeSplitViewButtons();
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.loadAdditionalDisplayedItemData()");
        }

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
        /* set the bugs to show the loading widget */
        /*if (topicBugsPresenter.getDisplay().getProvider() != null) {
            topicBugsPresenter.getDisplay().getProvider().resetProvider();
        }*/

        /* clear the tags display */
        initializeViews(Arrays.asList(new BaseTopicViewInterface[]{topicTagsComponent.getDisplay()}));

        /* A callback to respond to a request for a topic with the tags expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                topicTagsComponent.getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {

                /* copy the revisions into the displayed Topic */
                getTopicOrRevisionTopic().getItem().setTags(retValue.getTags());

                /* update the view */
                initializeViews(Arrays.asList(new BaseTopicViewInterface[]{topicTagsComponent.getDisplay()}));
            }
        });

        /* A callback to respond to a request for a topic with the bugzilla bugs expanded */
        /*final RESTCalls.RESTCallback<RESTTopicV1> topicWithBugsCallback = new BaseRestCallback<RESTTopicV1, TopicBugsPresenter.Display>(
                topicBugsPresenter.getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicBugsPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTTopicV1 retValue, final TopicBugsPresenter.Display display) {
                final RESTBugzillaBugCollectionV1 collection = retValue.getBugzillaBugs_OTM();
                // copy the revisions into the displayed Topic
                getTopicOrRevisionTopic().getItem().setBugzillaBugs_OTM(collection);
                // refresh the celltable
                topicBugsPresenter.getDisplay().getProvider().displayNewFixedList(collection.getItems());
            }
        }) {

        };*/

        /* Initiate the REST calls */
        final Integer id = getTopicOrRevisionTopic().getItem().getId();
        final Integer revision = getTopicOrRevisionTopic().getItem().getRevision();

        //RESTCalls.getTopicRevisionWithBugs(topicWithBugsCallback, id, revision);
        RESTCalls.getTopicRevisionWithTags(topicWithTagsCallback, id, revision);
    }

    /**
     * This method will replace the top action buttons with their disabled labels based on the
     * currently displayed view.
     */
    private void enableAndDisableActionButtons(final BaseTopicViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getXmlDown(), this.display.getXml());
        this.display.replaceTopActionButton(this.display.getBugsDown(), this.display.getBugs());
        this.display.replaceTopActionButton(this.display.getExtendedPropertiesDown(), this.display.getExtendedProperties());
        this.display.replaceTopActionButton(this.display.getFieldsDown(), this.display.getFields());
        this.display.replaceTopActionButton(this.display.getHistoryDown(), this.display.getHistory());
        this.display.replaceTopActionButton(this.display.getRenderedDown(), this.display.getRendered());
        this.display.replaceTopActionButton(this.display.getTopicTagsDown(), this.display.getTopicTags());
        this.display.replaceTopActionButton(this.display.getXmlErrorsDown(), this.display.getXmlErrors());

        if (displayedView == this.topicXMLComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getXml(), this.display.getXmlDown());
        } else if (displayedView == this.topicBugsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getBugs(), this.display.getBugsDown());
        } else if (displayedView == this.topicPropertyTagPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getExtendedProperties(), this.display.getExtendedPropertiesDown());
        } else if (displayedView == this.topicViewComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getFields(), this.display.getFieldsDown());
        } else if (displayedView == this.topicRevisionsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getHistory(), this.display.getHistoryDown());
        } else if (displayedView == this.topicRenderedPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getRendered(), this.display.getRenderedDown());
        } else if (displayedView == this.topicTagsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTopicTags(), this.display.getTopicTagsDown());
        } else if (displayedView == this.topicXMLErrorsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getXmlErrors(), this.display.getXmlErrorsDown());
        }

        if (getTopicOrRevisionTopic() != null &&
                getTopicOrRevisionTopic().getItem().getXmlErrors() != null &&
                !getTopicOrRevisionTopic().getItem().getXmlErrors().isEmpty()) {
            this.display.getXmlErrors().addStyleName(CSSConstants.ERROR);
            this.display.getXmlErrorsDown().addStyleName(CSSConstants.ERROR);
        } else {
            this.display.getXmlErrors().removeStyleName(CSSConstants.ERROR);
            this.display.getXmlErrorsDown().removeStyleName(CSSConstants.ERROR);
        }
    }

    /* Update the page name */
    private void updatePageTitle(final BaseTopicViewInterface displayedView) {

        final StringBuilder title = new StringBuilder(displayedView.getPageName());
        final String id = searchResultsComponent.getProviderData().getDisplayedItem().getItem().getId() == null ?
                PressGangCCMSUI.INSTANCE.New() : searchResultsComponent.getProviderData().getDisplayedItem().getItem().getId().toString();
        final String displayTitle = searchResultsComponent.getProviderData().getDisplayedItem().getItem().getTitle() == null ?
                "" : searchResultsComponent.getProviderData().getDisplayedItem().getItem().getTitle();
        if (this.searchResultsComponent.getProviderData().getDisplayedItem() != null) {
            title.append(": [" + id + "] " + displayTitle);
        }
        display.getPageTitle().setText(title.toString());
    }

    @Override
    protected void switchView(final BaseTopicViewInterface displayedView) {
        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicPresenter.switchView(final TopicViewInterface displayedView)");

            super.switchView(displayedView);

            enableAndDisableActionButtons(displayedView);

            updatePageTitle(displayedView);

            /* Save any changes to the xml editor */
            if (lastDisplayedView == this.topicXMLComponent.getDisplay()) {
                this.topicXMLComponent.getDisplay().getDriver().flush();
            }

            /* Refresh the rendered view (when there is no page splitting) */
            if (displayedView == this.topicRenderedPresenter.getDisplay()) {
                topicRenderedPresenter.getDisplay().initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC,
                        display.getSplitType(), locales, true);
            }
            /* Set the projects combo box as the focsed element */
            else if (displayedView == this.topicTagsComponent.getDisplay()) {
                if (topicTagsComponent.getDisplay().getProjectsList().isAttached()) {
                    topicTagsComponent.getDisplay().getProjectsList().getElement().focus();
                }
            }

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (displayedView == topicXMLComponent.getDisplay() && display.getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
                timer.scheduleRepeating(Constants.REFRESH_RATE);
            } else {
                timer.cancel();
                refreshRenderedView(true);
            }

            setHelpTopicForView(this, displayedView);

            lastDisplayedView = displayedView;
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.switchView(final TopicViewInterface displayedView)");
        }
    }

    /**
     * Called to create a new topic
     */
    private void createNewTopic() {
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
        topicCollectionItem.setItem(restTopic);

        // the topic won't show up in the list of topics until it is saved, so the
        // selected item is null
        searchResultsComponent.getProviderData().setSelectedItem(null);

        // the new topic is being displayed though, so we set the displayed item
        searchResultsComponent.getProviderData().setDisplayedItem(topicCollectionItem);

        updateViewsAfterNewEntityLoaded();
    }

    @Override
    protected void bindActionButtons() {


        /* Build up a click handler to save the topic */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                try {
                    logger.log(Level.INFO,
                            "ENTER SearchResultsAndTopicPresenter.bindActionButtons() saveClickHandler.onClick()");

                    if (hasUnsavedChanges()) {
                        display.getMessageLogDialog().getDialogBox().center();
                        display.getMessageLogDialog().getDialogBox().show();
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                } finally {
                    logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.bindActionButtons() saveClickHandler.onClick()");
                }
            }
        };

        final ClickHandler messageLogDialogOK = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                try {
                    logger.log(Level.INFO,
                            "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {

                        /* Sync any changes back to the underlying object */
                        flushChanges();

                         /*
                         * Create a new instance of the topic, and copy out any updated, added or deleted fields. We don't
                         * do a clone or send the original object here because a full object will send back a whole lot of
                         * data that was never modified, wasting bandwidth, and chewing up CPU cycles as Errai serializes
                         * the data into JSON.
                         */
                        final RESTTopicV1 sourceTopic = searchResultsComponent.getProviderData().getDisplayedItem().getItem();

                        final RESTTopicV1 newTopic = new RESTTopicV1();
                        newTopic.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                        newTopic.explicitSetSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
                        newTopic.explicitSetTags(new RESTTagCollectionV1());

                        /*
                            Only assign those modified children to the topic that is to be added/updated
                         */
                        logger.log(Level.INFO, "Copying modified collections");
                        newTopic.getProperties().setItems(sourceTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems());
                        if (sourceTopic.getSourceUrls_OTM() != null && sourceTopic.getSourceUrls_OTM().getItems() != null) {
                            newTopic.getSourceUrls_OTM().setItems(sourceTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems());
                        }
                        newTopic.getTags().setItems(sourceTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems());

                        /*
                            Assume all the text fields have been updated
                         */
                        logger.log(Level.INFO, "Copying modified fields");
                        newTopic.setId(sourceTopic.getId());
                        newTopic.explicitSetDescription(sourceTopic.getDescription());
                        newTopic.explicitSetLocale(sourceTopic.getLocale());
                        newTopic.explicitSetTitle(sourceTopic.getTitle());
                        newTopic.explicitSetXml(sourceTopic.getXml());

                        if (searchResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem()) {
                            final BaseRestCallback<RESTTopicV1, Display> addCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                    display,
                                    new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                        @Override
                                        public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                                            try {
                                                logger.log(Level.INFO,
                                                        "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");

                                                // Create the topic wrapper
                                                final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
                                                topicCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                                // create the topic, and add to the wrapper
                                                final RESTTopicV1 restTopic = new RESTTopicV1();
                                                topicCollectionItem.setItem(retValue);

                                                /* Update the displayed topic */
                                                searchResultsComponent.getProviderData().setDisplayedItem(topicCollectionItem.clone(true));

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
                                                searchResultsComponent.getProviderData().setSelectedItem(topicCollectionItem);

                                                lastXML = null;

                                                initializeViews();

                                                if (startWithNewTopic) {
                                                    logger.log(Level.INFO, "Adding new topic to static list");
                                                    searchResultsComponent.getProviderData().getItems().add(topicCollectionItem);
                                                    searchResultsComponent.getProviderData().setSize(searchResultsComponent.getProviderData().getItems().size());
                                                    updateDisplayAfterSave(false);
                                                } else {
                                                    /* Update the selected topic */
                                                    logger.log(Level.INFO, "Redisplaying query");
                                                    updateDisplayAfterSave(true);
                                                }

                                                logger.log(Level.INFO, "Refreshing editor");
                                                if (topicXMLComponent.getDisplay().getEditor() != null) {
                                                    topicXMLComponent.getDisplay().getEditor().redisplay();
                                                }

                                                Window.alert(PressGangCCMSUI.INSTANCE.TopicSaveSuccessWithID() + " " + retValue.getId());
                                            } finally {
                                                logger.log(Level.INFO,
                                                        "EXIT SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");
                                            }
                                        }
                                    }, new BaseRestCallback.FailureAction<Display>() {
                                @Override
                                public void doFailureAction(final Display display) {
                                    topicXMLComponent.getDisplay().getEditor().redisplay();
                                }
                            }
                            );

                            final String message = display.getMessageLogDialog().getMessage().getText();
                            RESTCalls.createTopic(addCallback, newTopic, message, (int) ServiceConstants.MAJOR_CHANGE, ServiceConstants.NULL_USER_ID.toString());
                        } else {
                            final BaseRestCallback<RESTTopicV1, Display> updateCallback = new BaseRestCallback<RESTTopicV1, Display>(
                                    display,
                                    new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                                        @Override
                                        public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                                            try {
                                                logger.log(Level.INFO,
                                                        "ENTER SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                                boolean overwroteChanges = false;
                                                final Integer originalRevision = searchResultsComponent.getProviderData().getSelectedItem().getItem().getRevision();

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

                                                        logger.log(Level.INFO, "originalRevision: " + originalRevision + " new revision: " + overwriteRevision);

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

                                                        logger.log(Level.INFO, "originalRevision: " + originalRevision + " last revision: " + overwriteRevision);

                                                        /*
                                                         * if the second last revision doesn't match the revision of the topic when editing was
                                                         * started, then we have overwritten someone elses changes
                                                         */
                                                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                                                    }
                                                }

                                                /* Update the displayed topic */
                                                retValue.cloneInto(searchResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                                        true);
                                                /* Update the selected topic */
                                                retValue.cloneInto(searchResultsComponent.getProviderData().getSelectedItem().getItem(), true);

                                                lastXML = null;

                                                initializeViews();

                                                updateDisplayAfterSave(false);

                                                if (overwroteChanges) {
                                                    Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                                } else {
                                                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                                }
                                            } finally {
                                                logger.log(Level.INFO,
                                                        "EXIT SearchResultsAndTopicPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                                if (topicXMLComponent.getDisplay().getEditor() != null) {
                                                    topicXMLComponent.getDisplay().getEditor().redisplay();
                                                }
                                            }
                                        }
                                    }, new BaseRestCallback.FailureAction<Display>() {
                                @Override
                                public void doFailureAction(final Display display) {
                                    topicXMLComponent.getDisplay().getEditor().redisplay();
                                }
                            }
                            );

                            final String message = display.getMessageLogDialog().getMessage().getText();
                            final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE
                                    : ServiceConstants.MAJOR_CHANGE);
                            RESTCalls.saveTopic(updateCallback, newTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
                        }
                    }
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();

                    logger.log(Level.INFO,
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
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicViewComponent.getDisplay());
                }
            }
        };

        final ClickHandler topicPropertyTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicPropertyTagPresenter.getDisplay());
                }
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicXMLComponent.getDisplay());

                }
            }
        };

        final ClickHandler topicRenderedClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicRenderedPresenter.getDisplay());
                }
            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicXMLErrorsPresenter.getDisplay());
                }
            }
        };

        final ClickHandler topicTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicTagsComponent.getDisplay());
                }
            }
        };

        final ClickHandler topicBugsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicBugsPresenter.getDisplay());
                }
            }
        };

        final ClickHandler topicRevisionsClickHanlder = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicRevisionsComponent.getDisplay());
                }
            }
        };

        final ClickHandler splitMenuHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                showRenderedSplitPanelMenu();
            }
        };

        final ClickHandler splitMenuCloseHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                showRegularMenu();
            }
        };

        final ClickHandler splitMenuNoSplitHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE);

                timer.cancel();

                initializeDisplay();
                initializeSplitViewButtons();
            }
        };

        final ClickHandler splitMenuVSplitHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL);

                timer.scheduleRepeating(Constants.REFRESH_RATE);

                initializeDisplay();
                initializeSplitViewButtons();

                if (lastDisplayedView == topicRenderedPresenter.getDisplay()) {
                    switchView(topicXMLComponent.getDisplay());
                    showRenderedSplitPanelMenu();
                }
            }
        };

        final ClickHandler splitMenuHSplitHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL);

                timer.scheduleRepeating(Constants.REFRESH_RATE);

                initializeDisplay();
                initializeSplitViewButtons();

                if (lastDisplayedView == topicRenderedPresenter.getDisplay()) {
                    switchView(topicXMLComponent.getDisplay());
                    showRenderedSplitPanelMenu();
                }
            }
        };

        final ClickHandler cspsHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {

                if (searchResultsComponent.getProviderData().getDisplayedItem() != null && isOKToProceed()) {

                    final RESTTopicV1 topic = searchResultsComponent.getProviderData().getDisplayedItem().getItem();

                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                            + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_FILTER_VAR + "="
                            + topic.getTitle() + " [" + topic.getId() + "];tag" + ServiceConstants.CSP_TAG_ID + "=1;logic=AND",
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }

            }
        };

        /* Hook up the click listeners */
        display.getRenderedSplit().addClickHandler(splitMenuHandler);
        display.getFields().addClickHandler(topicViewClickHandler);
        display.getExtendedProperties().addClickHandler(topicPropertyTagsClickHandler);
        display.getXml().addClickHandler(topicXMLClickHandler);
        display.getRendered().addClickHandler(topicRenderedClickHandler);
        display.getSave().addClickHandler(saveClickHandler);
        display.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
        display.getTopicTags().addClickHandler(topicTagsClickHandler);
        display.getBugs().addClickHandler(topicBugsClickHandler);
        display.getHistory().addClickHandler(topicRevisionsClickHanlder);
        display.getCsps().addClickHandler(cspsHandler);

        display.getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
        display.getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
        display.getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
        display.getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
        display.getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);

        /* Hook up the xml editor buttons */
        topicXMLComponent.getDisplay().getLineWrap().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                topicXMLComponent.getDisplay().getEditor().setUseWrapMode(topicXMLComponent.getDisplay().getLineWrap().isDown());
            }
        });

        topicXMLComponent.getDisplay().getShowInvisibles().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                topicXMLComponent.getDisplay().getEditor().setShowInvisibles(topicXMLComponent.getDisplay().getShowInvisibles().isDown());
            }
        });

        addKeyboardShortcutEvents(topicXMLComponent.getDisplay(), display);
    }

    protected void updateDisplayAfterSave(final boolean wasNewEntity) {
        super.updateDisplayAfterSave(wasNewEntity);

        /* Refresh the buttons (especially the xml errors button) after a save */
        enableAndDisableActionButtons(lastDisplayedView);
    }

    @Override
    protected void bindFilteredResultsButtons() {
        final ClickHandler createClickHanlder = new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                createNewTopic();
            }
        };

        searchResultsComponent.getDisplay().getCreate().addClickHandler(createClickHanlder);
    }

    @Override
    protected void initializeViews(final List<BaseTopicViewInterface> filter) {

        try {
            logger.log(Level.INFO,
                    "ENTER SearchResultsAndTopicPresenter.initializeViews(final List<TopicViewInterface> filter)");

            logger.log(Level.INFO, "\tInitializing topic views");

            for (final BaseTopicViewInterface view : new BaseTopicViewInterface[]{topicViewComponent.getDisplay(), topicXMLComponent.getDisplay(),
                    topicRenderedPresenter.getDisplay(), topicXMLErrorsPresenter.getDisplay(), topicTagsComponent.getDisplay(), topicBugsPresenter.getDisplay(),
                    topicSplitPanelRenderedDisplay, topicPropertyTagPresenter.getDisplay()}) {
                if (viewIsInFilter(filter, view)) {

                    final RESTTopicCollectionItemV1 topicToDisplay = getTopicOrRevisionTopic();

                    view.initialize(topicToDisplay.getItem(), isReadOnlyMode(), NEW_TOPIC, this.split, locales, false);
                }
            }

            if (viewIsInFilter(filter, topicRevisionsComponent.getDisplay())) {
                logger.log(Level.INFO, "\tInitializing topic revisions view");
                topicRevisionsComponent.getDisplay().initialize(searchResultsComponent.getProviderData().getDisplayedItem().getItem(),
                        isReadOnlyMode(), NEW_TOPIC, display.getSplitType(), locales, false);
            }

            if (viewIsInFilter(filter, topicTagsComponent.getDisplay())) {
                logger.log(Level.INFO, "\tInitializing topic tags view");
                bindTagEditingButtons();
            }

            /* Redisplay the editor. topicXMLComponent.getDisplay().getEditor() will be not null after the initialize method was called above */
            if (viewIsInFilter(filter, topicXMLComponent.getDisplay())) {
                logger.log(Level.INFO, "\tSetting topic XML edit button state and redisplaying ACE editor");
                setXMLEditorButtonsToEditorState();
                topicXMLComponent.getDisplay().getEditor().redisplay();
            }

        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicPresenter.initializeViews(final List<TopicViewInterface> filter)");
        }

    }

    private void initializeSplitViewButtons() {
        /* fix the rendered view button */
        display.buildSplitViewButtons(split);
    }

    /**
     * Retrieve a list of locales from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the locales are loaded
     */
    private void populateLocales(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
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
    }

    /**
     * Retrieve a list of xml elements from the server.
     *
     * @param waitDisplay    The view used to notify the user that an ongoing operation is in progress
     * @param loadedCallback The callback to call when the data is loaded
     */
    private void populateXMLElements(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                        /* Get the list of locales from the StringConstant */
                final List<String> xmlElements = new LinkedList<String>(Arrays.asList(retValue.getValue()
                        .replaceAll(CARRIAGE_RETURN_AND_LINE_BREAK, LINE_BREAK).replaceAll(" ", "").split(LINE_BREAK)));

                        /* Clean the list */
                while (xmlElements.contains("")) {
                    xmlElements.remove("");
                }

                Collections.sort(xmlElements);

                loadedCallback.stringListLoaded(xmlElements);

            }
        });

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID);
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
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
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
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
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
                int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    int keyCode = event.getNativeEvent().getKeyCode();
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
                            if (!details.toString().isEmpty())
                                details.append("\n");
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
     * Set the help link topic ids.
     */
    private void setHelpTopicForView(final BaseTemplatePresenterInterface component, final BaseTemplateViewInterface view) {

        if (view instanceof TopicXMLErrorsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_VALIDATION_ERRORS_TOPIC);
        } else if (view instanceof TopicPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_PROPERTIES_TOPIC);
        } else if (view instanceof TopicTagsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_TAGS_TOPIC);
        } else if (view instanceof TopicRevisionsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_REVISIONS_TOPIC);
        } else if (view instanceof TopicBugsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_BUGS_TOPIC);
        } else if (view instanceof TopicXMLView) {
            component.setHelpTopicId(ServiceConstants.TOPIC_XML_EDIT_TOPIC);
        } else {
            component.setHelpTopicId(ServiceConstants.DEFAULT_HELP_TOPIC);
        }
    }

    /**
     * The interface that defines the top level topic list and edit view
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

        FlexTable getRenderedSplitViewMenu();

        PushButton getRenderedSplitOpen();

        PushButton getRenderedHorizontalSplit();

        PushButton getRenderedSplitClose();

        PushButton getRenderedVerticalSplit();

        PushButton getRenderedNoSplit();

        PushButton getRenderedSplit();

        /**
         * @return The button that is used to switch to the history view
         */
        PushButton getHistory();

        /**
         * @return The button that is used to switch to the history view
         */
        Label getHistoryDown();

        /**
         * @return The button that is used to switch to the rendered view
         */
        PushButton getRendered();

        /**
         * @return The button that is used to switch to the rendered view
         */
        Label getRenderedDown();

        /**
         * @return The button that is used to switch to the XML view
         */
        PushButton getXml();

        /**
         * @return The label that is used to indicate that the XML view is selected
         */
        Label getXmlDown();

        /**
         * @return The button that is used to switch to the topic fields view
         */
        PushButton getFields();

        /**
         * @return The button that is used to switch to the topic fields view
         */
        Label getFieldsDown();

        /**
         * @return The button that is used to switch to the topic property tags view
         */
        PushButton getExtendedProperties();

        /**
         * @return The button that is used to switch to the topic property tags view
         */
        Label getExtendedPropertiesDown();

        /**
         * @return The button that is used to save the topic
         */
        PushButton getSave();

        /**
         * @return The button that is used to switch to the XML errors view
         */
        PushButton getXmlErrors();

        /**
         * @return The button that is used to switch to the XML errors view
         */
        Label getXmlErrorsDown();

        /**
         * @return The button that is used to switch to the tags view
         */
        PushButton getTopicTags();

        /**
         * @return The button that is used to switch to the tags view
         */
        Label getTopicTagsDown();

        /**
         * @return The button that is used to switch to the bugs view
         */
        PushButton getBugs();

        /**
         * @return The button that is used to switch to the bugs view
         */
        Label getBugsDown();

        /**
         * @return The button this is used to match topics to csps
         */
        PushButton getCsps();

        /**
         * Show the rendered split view menu
         */
        void showSplitViewButtons();

        /**
         * Rebuild the split view buttons
         *
         * @param splitType The screen split
         */
        void buildSplitViewButtons(final SplitType splitType);

        LogMessageInterface getMessageLogDialog();

        SplitType getSplitType();

        void initialize(final boolean readOnly, final SplitType splitType, final Panel panel);
    }

    /**
     * A click handler to add a tag to a topic
     *
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = topicTagsComponent.getDisplay().getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            RESTTagCollectionItemV1 deletedTag = null;
            for (final RESTTagCollectionItemV1 tag : searchResultsComponent.getProviderData().getDisplayedItem().getItem()
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
                    if (arg0 == null || arg0.getItem() == null)
                        return false;
                    return arg0.getItem().getMutuallyExclusive();
                }
            });

            /* Find existing tags that belong to any of the mutually exclusive categories */
            final Collection<RESTTagCollectionItemV1> conflictingTags = Collections2.filter(searchResultsComponent
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
                                            if (existingTagCategory == null || existingTagCategory.getItem() == null)
                                                return false;

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
                if (!Window.confirm(PressGangCCMSUI.INSTANCE.RemoveConflictingTags() + tags.toString()))
                    return;

                for (final RESTTagCollectionItemV1 tag : conflictingTags) {
                    tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                }
            }

            if (deletedTag == null) {
                /* Get the selected tag, and clone it */
                final RESTTagV1 selectedTagClone = selectedTag.clone(true);
                /* Add the tag to the topic */
                searchResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            initializeViews(Arrays.asList(new BaseTopicViewInterface[]{topicTagsComponent.getDisplay()}));
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
            if (searchResultsComponent.getProviderData().getDisplayedItem() == null) {
                throw new IllegalStateException("searchResultsComponent.getProviderData().getDisplayedItem() cannot be null");
            }

            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                searchResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            initializeViews(Arrays.asList(new BaseTopicViewInterface[]{topicTagsComponent.getDisplay()}));
        }
    }
}
