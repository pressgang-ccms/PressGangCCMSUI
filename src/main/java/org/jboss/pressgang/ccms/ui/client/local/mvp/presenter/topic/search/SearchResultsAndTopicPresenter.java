package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.view.client.HasData;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common.CommonTopicComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common.GetCurrentTopic;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class SearchResultsAndTopicPresenter
        extends
        BaseSearchAndEditComponent<SearchResultsPresenter.Display, SearchResultsAndTopicPresenter.Display, RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1, TopicViewInterface, TopicPresenter.Display, RESTTopicV1BasicDetailsEditor>
        implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
    /**
     * false to indicate that the topic views should display action buttons applicabale to established topics (as opposed to new
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
            if (lastDisplayedView == topicXMLDisplay) {
                refreshRenderedView(false);
            }
        }
    };
    @Inject
    private HandlerManager eventBus;
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
    private TopicPresenter.Display topicViewDisplay;
    @Inject
    private TopicPresenter.LogicComponent topicViewComponent;
    @Inject
    private TopicXMLPresenter.Display topicXMLDisplay;
    @Inject
    private TopicXMLPresenter.LogicComponent topicXMLComponent;
    /**
     * The rendered topic view display
     */
    @Inject
    private TopicRenderedPresenter.Display topicRenderedDisplay;
    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;
    @Inject
    private SearchResultsPresenter.Display searchResultsDisplay;
    @Inject
    private SearchResultsPresenter.LogicComponent searchResultsComponent;
    @Inject
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;
    @Inject
    private TopicTagsPresenter.Display topicTagsDisplay;
    @Inject
    private TopicTagsPresenter.LogicComponent topicTagsComponent;
    @Inject
    private TopicBugsPresenter.Display topicBugsDisplay;
    @Inject
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;
    @Inject
    private TopicRevisionsPresenter.LogicComponent topicrevisionsComponent;
    /**
     * How the rendering panel is displayed
     */
    private SplitType split = SplitType.NONE;

    @Override
    public void go(final HasWidgets container) {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.go()");


            /* A call back used to get a fresh copy of the entity that was selected */
            final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTopicV1>() {

                @Override
                public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTTopicV1> displayCallback) {

                    try {
                        logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.bind() GetNewEntityCallback.getNewEntity()");

                        final RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                                waitDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                            @Override
                            public void doSuccessAction(final RESTTopicV1 retValue, final BaseTemplateViewInterface display) {
                                try {
                                    logger.log(Level.INFO,
                                            "ENTER SearchResultsAndTopicComponent.bind() RESTCallback.doSuccessAction()");

                                    displayCallback.displayNewEntity(retValue);
                                } finally {
                                    logger.log(Level.INFO,
                                            "EXIT SearchResultsAndTopicComponent.bind() RESTCallback.doSuccessAction()");
                                }
                            }
                        });
                        RESTCalls.getTopic(callback, id);
                    } finally {
                        logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.bind() GetNewEntityCallback.getNewEntity()");
                    }
                }
            };

            /* Display the view */
            searchResultsDisplay.setViewShown(true);
            display.setViewShown(true);

            clearContainerAndAddTopLevelPanel(container, display);

            display.displaySearchResultsView(searchResultsDisplay);

            /* Initialize the other presenters we have pulled in */
            searchResultsComponent.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString, searchResultsDisplay, display);
            topicTagsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, topicTagsDisplay, display);

            super.bind(ServiceConstants.TOPIC_EDIT_VIEW_CONTENT_TOPIC, HISTORY_TOKEN, Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, topicXMLDisplay, topicViewDisplay,
                    searchResultsDisplay, searchResultsComponent, display, display, getNewEntityCallback);

            /* Display the split panes */
            initializeDisplay();

            this.topicBugsDisplay.setProvider(generateTopicBugListProvider());
            this.topicRevisionsDisplay.setProvider(generateTopicRevisionsListProvider());

            bindViewTopicRevisionButton();
            bindSplitPanelResize();
            bindTagEditingButtons();
            loadSplitPanelSize();

            this.topicTagsComponent.bindNewTagListBoxes(new AddTagClickhandler());

            CommonTopicComponent.populateLocales(waitDisplay, new StringListLoaded() {
                @Override
                public void stringListLoaded(final List<String> locales) {
                    SearchResultsAndTopicPresenter.this.locales = locales;
                }
            });

            CommonTopicComponent.addKeyboardShortcutEventHandler(this.topicXMLDisplay, this.display, new GetCurrentTopic() {

                @Override
                public RESTTopicV1 getCurrentlyEditedTopic() {
                    return filteredResultsComponent.getProviderData().getDisplayedItem().getItem();
                }
            });
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.go()");
        }
    }

    @Override
    public void parseToken(final String historyToken) {

        queryString = removeHistoryToken(historyToken, SearchResultsAndTopicPresenter.HISTORY_TOKEN);

        /* Make sure that the query string has at least the prefix */
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
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
        topicXMLDisplay.getDriver().flush();

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
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.setXMLEditorButtonsToEditorState()");

            topicXMLDisplay.getLineWrap().setDown(topicXMLDisplay.getEditor().getUserWrapMode());
            topicXMLDisplay.getShowInvisibles().setDown(topicXMLDisplay.getEditor().getShowInvisibles());
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.setXMLEditorButtonsToEditorState()");
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
        display.initialize(split, topicSplitPanelRenderedDisplay.getPanel());

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
        if (lastDisplayedView == topicXMLErrorsDisplay || lastDisplayedView == topicTagsDisplay) {
            return;
        }

        lastDisplayedView.getDriver().flush();
    }

    /**
     * The currently displayed topic is topicRevisionsDisplay.getRevisionTopic() if it is not null, or
     * searchResultsComponent.getProviderData().getDisplayedItem() otherwise.
     *
     * @return The currently displayed topic
     */
    private RESTTopicCollectionItemV1 getTopicOrRevisionTopic() {
        final RESTTopicCollectionItemV1 sourceTopic = topicRevisionsDisplay.getRevisionTopic() == null ? filteredResultsComponent
                .getProviderData().getDisplayedItem() : topicRevisionsDisplay.getRevisionTopic();

        if (sourceTopic == null)
            throw new NullPointerException("sourceTopic cannot be null");

        return sourceTopic;
    }

    /**
     * The UI is in a readonly mode if viewing a topic revision
     *
     * @return true if the UI is in readonly mode, and false otherwise
     */
    private boolean isReadOnlyMode() {
        return topicRevisionsDisplay.getRevisionTopic() != null;
    }

    private void showRegularMenu() {
        display.getViewActionButtonsPanel().clear();
        display.getViewActionButtonsPanel().add(lastDisplayedView.getTopActionPanel());
    }

    private void showRenderedSplitPanelMenu() {
        display.getViewActionButtonsPanel().clear();
        display.getViewActionButtonsPanel().add(lastDisplayedView.getRenderedSplitViewMenu());
    }

    /**
     * Load the split panel sizes
     */
    private void loadSplitPanelSize() {

        if (split == SplitType.HORIZONTAL) {
            display.getSplitPanel().setSplitPosition(
                    topicSplitPanelRenderedDisplay.getPanel().getParent(),
                    Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH,
                            Constants.SPLIT_PANEL_SIZE), false);
        } else if (split == SplitType.VERTICAL) {
            display.getSplitPanel().setSplitPosition(
                    topicSplitPanelRenderedDisplay.getPanel().getParent(),
                    Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH,
                            Constants.SPLIT_PANEL_SIZE), false);
        }
    }

    /**
     * @return A provider to be used for the topic revisions display list
     */
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> generateTopicRevisionsListProvider() {
        final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicCollectionItemV1> display) {
                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null
                        && filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions() != null
                        && filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions().getItems() != null) {
                    displayNewFixedList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getRevisions()
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
                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null
                        && filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getBugzillaBugs_OTM() != null) {
                    displayNewFixedList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
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
        display.getSplitPanel().addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(final ResizeEvent event) {
                if (topicXMLDisplay.getEditor() != null) {
                    topicXMLDisplay.getEditor().redisplay();
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
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.bindTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (topicTagsDisplay.getEditor() != null) {
                for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTagsDisplay.getEditor().projects
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
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.bindTagEditingButtons()");
        }
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewTopicRevisionButton() {
        topicRevisionsDisplay.getDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {
                final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                        topicRevisionsDisplay,
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

                                topicrevisionsComponent.displayDiff(retValue.getXml(), retValueLabel, sourceTopic.getItem()
                                        .getXml(), sourceTopicLabel, isXML);
                            }
                        }) {

                };
                RESTCalls.getTopicRevision(callback, revisionTopic.getItem().getId(), revisionTopic.getItem().getRevision());
            }
        });

        topicRevisionsDisplay.getViewButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                /* Reset the reference to the revision topic */
                topicRevisionsDisplay.setRevisionTopic(null);

                if (!revisionTopic.getItem().getRevision()
                        .equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getRevision())) {
                    /* Reset the reference to the revision topic */
                    topicRevisionsDisplay.setRevisionTopic(revisionTopic);
                }

                /* Load the tags and bugs */
                loadTagsandBugs();

                initializeViews();
                topicRevisionsDisplay.setProvider(generateTopicRevisionsListProvider());
                switchView(topicRevisionsDisplay);
            }
        });
    }

    @Override
    public boolean hasUnsavedChanges() {

        /* No topic selected, so no changes need to be saved */
        if (this.filteredResultsComponent.getProviderData().getDisplayedItem() == null)
            return false;

        /* Save any pending changes */
        flushChanges();

        final RESTTopicV1 displayedTopic = this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem();
        final RESTTopicV1 selectedTopic = this.filteredResultsComponent.getProviderData().getSelectedItem().getItem();

        boolean unsavedChanges = false;

        /* If there are any modified tags in newTopic, we have unsaved changes */
        if (!displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
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
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.loadAdditionalDisplayedItemData()");

            topicRevisionsDisplay.setRevisionTopic(null);

            /* set the revisions to show the loading widget */
            if (topicRevisionsDisplay.getProvider() != null) {
                topicRevisionsDisplay.getProvider().resetProvider();
            }

            /* A callback to respond to a request for a topic with the revisions expanded */
            final RESTCalls.RESTCallback<RESTTopicV1> topicWithRevisionsCallback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                    topicRevisionsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                @Override
                public void doSuccessAction(final RESTTopicV1 retValue, final TopicRevisionsPresenter.Display display) {
                    filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                            .setRevisions(retValue.getRevisions());

                            /* refresh the list */
                    topicRevisionsDisplay.getProvider().displayNewFixedList(retValue.getRevisions().getItems());
                }
            });

            RESTCalls.getTopicWithRevisions(topicWithRevisionsCallback, filteredResultsComponent.getProviderData()
                    .getSelectedItem().getItem().getId());

            /* got on to load the tags and bugs */
            loadTagsandBugs();

            /* fix the rendered view buttons */
            initializeSplitViewButtons();
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.loadAdditionalDisplayedItemData()");
        }

    }

    /**
     * The tags and bugs for a topic are loaded as seperate operations to minimize the amount of data initially sent when a
     * topic is displayed.
     */
    private void loadTagsandBugs() {
        /* set the bugs to show the loading widget */
        if (topicBugsDisplay.getProvider() != null) {
            topicBugsDisplay.getProvider().resetProvider();
        }

        /* clear the tags display */
        initializeViews(Arrays.asList(new TopicViewInterface[]{topicTagsDisplay}));

        /* A callback to respond to a request for a topic with the tags expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                topicTagsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {

                        /* copy the revisions into the displayed Topic */
                getTopicOrRevisionTopic().getItem().setTags(retValue.getTags());

                        /* update the view */
                initializeViews(Arrays.asList(new TopicViewInterface[]{topicTagsDisplay}));
            }
        });

        /* A callback to respond to a request for a topic with the bugzilla bugs expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithBugsCallback = new BaseRestCallback<RESTTopicV1, TopicBugsPresenter.Display>(
                topicBugsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicBugsPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTTopicV1 retValue, final TopicBugsPresenter.Display display) {
                final RESTBugzillaBugCollectionV1 collection = retValue.getBugzillaBugs_OTM();
                        /* copy the revisions into the displayed Topic */
                getTopicOrRevisionTopic().getItem().setBugzillaBugs_OTM(collection);
                        /* refresh the celltable */
                topicBugsDisplay.getProvider().displayNewFixedList(collection.getItems());
            }
        }) {

        };

        /* Initiate the REST calls */
        final Integer id = getTopicOrRevisionTopic().getItem().getId();
        final Integer revision = getTopicOrRevisionTopic().getItem().getRevision();

        RESTCalls.getTopicRevisionWithBugs(topicWithBugsCallback, id, revision);
        RESTCalls.getTopicRevisionWithTags(topicWithTagsCallback, id, revision);
    }

    @Override
    protected void switchView(final TopicViewInterface displayedView) {
        try {
            logger.log(Level.INFO, "ENTER SearchResultsAndTopicComponent.switchView(final TopicViewInterface displayedView)");

            super.switchView(displayedView);

            /* Save any changes to the xml editor */
            if (lastDisplayedView == this.topicXMLDisplay) {
                this.topicXMLDisplay.getDriver().flush();
            }

            /* Refresh the rendered view (when there is no page splitting) */
            if (displayedView == this.topicRenderedDisplay) {
                topicRenderedDisplay.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC,
                        display.getSplitType(), locales, false);
            }
            /* Set the projects combo box as the focsed element */
            else if (displayedView == this.topicTagsDisplay) {
                if (topicTagsDisplay.getProjectsList().isAttached()) {
                    topicTagsDisplay.getProjectsList().getElement().focus();
                }
            }

            /* Update the page name */
            final StringBuilder title = new StringBuilder(displayedView.getPageName());
            if (this.filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                title.append(": [" + filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() + "] "
                        + filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTitle());
            }
            display.getPageTitle().setText(title.toString());

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (displayedView == topicXMLDisplay && display.getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
                timer.scheduleRepeating(Constants.REFRESH_RATE);
            } else {
                timer.cancel();
                refreshRenderedView(true);
            }

            CommonTopicComponent.setHelpTopicForView(this, displayedView);

            lastDisplayedView = displayedView;
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.switchView(final TopicViewInterface displayedView)");
        }
    }

    @Override
    protected void bindActionButtons() {
        /* Build up a click handler to save the topic */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                try {
                    logger.log(Level.INFO,
                            "ENTER SearchResultsAndTopicComponent.bindActionButtons() saveClickHandler.onClick()");

                    if (hasUnsavedChanges()) {
                        display.getMessageLogDialog().getDialogBox().center();
                        display.getMessageLogDialog().getDialogBox().show();
                    } else {
                        Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                } finally {
                    logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.bindActionButtons() saveClickHandler.onClick()");
                }
            }
        };

        /* Hook up the dialog box buttons */
        display.getMessageLogDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>() {
                        @Override
                        public void begin() {
                            display.addWaitOperation();
                        }

                        @Override
                        public void generalException(final Exception e) {
                            logger.log(Level.SEVERE, e.toString());
                            Window.alert(PressGangCCMSUI.INSTANCE.ErrorSavingTopic());
                            display.removeWaitOperation();
                            topicXMLDisplay.getEditor().redisplay();
                        }

                        @Override
                        public void success(final RESTTopicV1 retValue) {
                            try {

                                boolean overwroteChanges = false;

                                if (retValue.getRevisions() != null && retValue.getRevisions().getItems() != null
                                        && retValue.getRevisions().getItems().size() >= 2) {
                                    Collections.sort(retValue.getRevisions().getItems(),
                                            new RESTTopicCollectionItemV1RevisionSort());
                                    /* Get the second last revision (the last one is the current one) */
                                    final Integer overwriteRevision = retValue.getRevisions().getItems()
                                            .get(retValue.getRevisions().getItems().size() - 2).getItem().getRevision();
                                    /*
                                     * if the second last revision doesn't match the revision of the topic when editing was
                                     * started, then we have overwritten someone elses changes
                                     */
                                    overwroteChanges = !(filteredResultsComponent.getProviderData().getSelectedItem().getItem()
                                            .getRevision().equals(overwriteRevision));
                                }

                                /* Update the displayed topic */
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        true);
                                /* Update the selected topic */
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);

                                lastXML = null;

                                initializeViews();

                                updateDisplayAfterSave(false);

                                if (overwroteChanges) {
                                    Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                } else {
                                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                }
                            } finally {
                                display.removeWaitOperation();
                                if (topicXMLDisplay.getEditor() != null) {
                                    topicXMLDisplay.getEditor().redisplay();
                                }
                            }
                        }

                        @Override
                        public void failed(final Message message, final Throwable throwable) {

                            if (message != null)
                                logger.log(Level.SEVERE, message.toString());
                            if (throwable != null)
                                logger.log(Level.SEVERE, throwable.getMessage());

                            Window.alert(PressGangCCMSUI.INSTANCE.ErrorSavingTopic());
                            display.removeWaitOperation();
                            topicXMLDisplay.getEditor().redisplay();
                        }
                    };

                    /* Sync any changes back to the underlying object */
                    flushChanges();

                    /*
                     * Create a new instance of the topic, with all the properties set to explicitly update
                     */
                    final RESTTopicV1 updateTopic = filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                            .clone(true);

                    updateTopic.explicitSetBugzillaBugs_OTM(updateTopic.getBugzillaBugs_OTM());
                    updateTopic.explicitSetProperties(updateTopic.getProperties());
                    updateTopic.explicitSetSourceUrls_OTM(updateTopic.getSourceUrls_OTM());
                    updateTopic.explicitSetTags(updateTopic.getTags());
                    updateTopic.explicitSetDescription(updateTopic.getDescription());
                    updateTopic.explicitSetLocale(updateTopic.getLocale());
                    updateTopic.explicitSetTitle(updateTopic.getTitle());
                    updateTopic.explicitSetXml(updateTopic.getXml());

                    final String message = display.getMessageLogDialog().getMessage().getText();
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE
                            : ServiceConstants.MAJOR_CHANGE);
                    RESTCalls.saveTopic(callback, updateTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
                }

                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        });

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

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(entityPropertiesView);
                }
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicXMLDisplay);

                }
            }
        };

        final ClickHandler topicRenderedClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicRenderedDisplay);
                }
            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicXMLErrorsDisplay);
                }
            }
        };

        final ClickHandler topicTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicTagsDisplay);
                }
            }
        };

        final ClickHandler topicBugsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicBugsDisplay);
                }
            }
        };

        final ClickHandler topicRevisionsClickHanlder = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                    switchView(topicRevisionsDisplay);
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
            }
        };

        final ClickHandler cspsHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null && isOKToProceed()) {

                    final RESTTopicV1 topic = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                            + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_FILTER_VAR + "="
                            + topic.getTitle() + " [" + topic.getId() + "];tag" + ServiceConstants.CSP_TAG_ID + "=1;logic=AND",
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }

            }
        };

        /* Hook up the click listeners */
        for (final TopicViewInterface view : new TopicViewInterface[]{entityPropertiesView, topicXMLDisplay,
                topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay, topicRevisionsDisplay}) {
            view.getRenderedSplit().addClickHandler(splitMenuHandler);
            view.getFields().addClickHandler(topicViewClickHandler);
            view.getXml().addClickHandler(topicXMLClickHandler);
            view.getRendered().addClickHandler(topicRenderedClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
            view.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
            view.getTopicTags().addClickHandler(topicTagsClickHandler);
            view.getBugs().addClickHandler(topicBugsClickHandler);
            view.getHistory().addClickHandler(topicRevisionsClickHanlder);
            view.getCsps().addClickHandler(cspsHandler);

            view.getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            view.getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            view.getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            view.getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            view.getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);
        }

        /* Hook up the xml editor buttons */
        topicXMLDisplay.getLineWrap().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                topicXMLDisplay.getEditor().setUseWrapMode(topicXMLDisplay.getLineWrap().isDown());
            }
        });

        topicXMLDisplay.getShowInvisibles().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                topicXMLDisplay.getEditor().setShowInvisibles(topicXMLDisplay.getShowInvisibles().isDown());
            }
        });

        CommonTopicComponent.addKeyboardShortcutEvents(topicXMLDisplay, display);
    }

    @Override
    protected void bindFilteredResultsButtons() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initializeViews(final List<TopicViewInterface> filter) {

        try {
            logger.log(Level.INFO,
                    "ENTER SearchResultsAndTopicComponent.initializeViews(final List<TopicViewInterface> filter)");

            logger.log(Level.INFO, "\tInitializing topic views");

            for (final TopicViewInterface view : new TopicViewInterface[]{entityPropertiesView, topicXMLDisplay,
                    topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay,
                    topicSplitPanelRenderedDisplay}) {
                if (viewIsInFilter(filter, view)) {

                    final RESTTopicCollectionItemV1 topicToDisplay = getTopicOrRevisionTopic();

                    view.initialize(topicToDisplay.getItem(), isReadOnlyMode(), NEW_TOPIC, this.split, locales, false);
                }
            }

            if (viewIsInFilter(filter, topicRevisionsDisplay)) {
                logger.log(Level.INFO, "\tInitializing topic revisions view");
                topicRevisionsDisplay.initialize(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                        isReadOnlyMode(), NEW_TOPIC, display.getSplitType(), locales, false);
            }

            if (viewIsInFilter(filter, topicTagsDisplay)) {
                bindTagEditingButtons();
            }

            /* Redisplay the editor. topicXMLDisplay.getEditor() will be not null after the initialize method was called above */
            if (viewIsInFilter(filter, topicXMLDisplay)) {
                logger.log(Level.INFO, "\tSetting topic XML edit button state and redisplaying ACE editor");
                setXMLEditorButtonsToEditorState();
                topicXMLDisplay.getEditor().redisplay();
            }

        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.initializeViews(final List<TopicViewInterface> filter)");
        }

    }

    private void initializeSplitViewButtons() {
        /* fix the rendered view button */
        for (final TopicViewInterface view : new TopicViewInterface[]{entityPropertiesView, topicXMLDisplay,
                topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay, topicRevisionsDisplay}) {
            view.buildSplitViewButtons(split);
        }
    }

    /**
     * The interface that defines the top level topic list and edit view
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

        LogMessageInterface getMessageLogDialog();

        SplitType getSplitType();

        void initialize(final SplitType splitType, final Panel panel);
    }

    /**
     * A click handler to add a tag to a topic
     *
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = topicTagsDisplay.getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            RESTTagCollectionItemV1 deletedTag = null;
            for (final RESTTagCollectionItemV1 tag : filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
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
            final Collection<RESTTagCollectionItemV1> conflictingTags = Collections2.filter(filteredResultsComponent
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
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            initializeViews(Arrays.asList(new TopicViewInterface[]{topicTagsDisplay}));
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
            if (filteredResultsComponent.getProviderData().getDisplayedItem() == null) {
                throw new IllegalStateException("searchResultsComponent.getProviderData().getDisplayedItem() cannot be null");
            }

            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            initializeViews(Arrays.asList(new TopicViewInterface[]{topicTagsDisplay}));
        }
    }
}
