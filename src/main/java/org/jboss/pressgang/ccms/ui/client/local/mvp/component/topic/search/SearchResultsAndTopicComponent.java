package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common.CommonTopicComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

@Dependent
public class SearchResultsAndTopicComponent
        extends
        BaseSearchAndEditComponent<SearchResultsPresenter.Display, SearchResultsAndTopicPresenter.Display, RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1, TopicViewInterface, TopicPresenter.Display>
        implements SearchResultsAndTopicPresenter.LogicComponent {

    /**
     * false to indicate that the topic views should display action buttons applicabale to established topics (as opposed to new
     * topics)
     */
    private static final boolean NEW_TOPIC = false;

    private static final Logger logger = Logger.getLogger(SearchResultsAndTopicComponent.class.getName());

    @Inject
    private HandlerManager eventBus;

    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastDisplayedView == topicXMLDisplay) {
                refreshRenderedView();
            }
        }
    };
    
    /** The last xml that was rendered */
    private String lastXML = null;
    /** How long it has been since the xml changes */
    private long lastXMLChange = 0;
    /** False if we are not displaying external images in the current rendered view, and true otherwise */
    private boolean isDisplayingImage = false;

    private String queryString;

    /** A list of locales retrieved from the server */
    private List<String> locales;

    private TopicPresenter.LogicComponent topicViewComponent;
    private TopicXMLPresenter.Display topicXMLDisplay;
    private TopicXMLPresenter.LogicComponent topicXMLComponent;
    private TopicRenderedPresenter.Display topicRenderedDisplay;
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;
    private TopicTagsPresenter.Display topicTagsDisplay;
    private TopicTagsPresenter.LogicComponent topicTagsComponent;
    private TopicBugsPresenter.Display topicBugsDisplay;
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;
    private TopicRevisionsPresenter.LogicComponent topicrevisionsComponent;

    /**
     * How the rendering panel is displayed
     */
    private SplitType split = SplitType.NONE;

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
            for (final RESTTagCollectionItemV1 tag : filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                    .getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (tag.getState() == RESTBaseCollectionItemV1.REMOVE_STATE) {
                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        initializeViews(Arrays.asList(new TopicViewInterface[] { topicTagsDisplay }));

                        return;
                    } else {
                        /* Don't add tags twice */
                        Window.alert(PressGangCCMSUI.INSTANCE.TagAlreadyExists());
                        return;
                    }
                }
            }

            /* Get the selected tag, and clone it */
            final RESTTagV1 selectedTagClone = selectedTag.clone(true);
            /* Add the tag to the topic */
            filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(selectedTagClone);
            /* Redisplay the view */
            initializeViews(Arrays.asList(new TopicViewInterface[] { topicTagsDisplay }));
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

            if (tag.getState() == RESTBaseCollectionItemV1.ADD_STATE) {
                /* Tag was added and then removed, so we just delete the tag */
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            initializeViews(Arrays.asList(new TopicViewInterface[] { topicTagsDisplay }));
        }
    }

    @Override
    public String getQueryString() {
        return queryString;
    }

    @Override
    public void setQueryString(final String queryString) {
        this.queryString = queryString;
    }

    @Override
    public SplitType getSplit() {
        return split;
    }

    @Override
    public void setSplit(SplitType split) {
        this.split = split;
    }

    @Override
    public void bind(final SearchResultsAndTopicPresenter.Display display, final BaseTemplateViewInterface waitDisplay,
            final TopicPresenter.Display topicViewDisplay, final TopicPresenter.LogicComponent topicViewComponent,
            final TopicXMLPresenter.Display topicXMLDisplay, final TopicXMLPresenter.LogicComponent topicXMLComponent,
            final TopicRenderedPresenter.Display topicRenderedDisplay,
            final TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay,
            final SearchResultsPresenter.Display searchResultsDisplay,
            final SearchResultsPresenter.LogicComponent searchResultsComponent,
            final TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay, final TopicTagsPresenter.Display topicTagsDisplay,
            final TopicTagsPresenter.LogicComponent topicTagsComponent, final TopicBugsPresenter.Display topicBugsDisplay,
            final TopicRevisionsPresenter.Display topicRevisionsDisplay,
            final TopicRevisionsPresenter.LogicComponent topicrevisionsComponent) {

        this.topicViewComponent = topicViewComponent;
        this.topicXMLDisplay = topicXMLDisplay;
        this.topicXMLComponent = topicXMLComponent;
        this.topicRenderedDisplay = topicRenderedDisplay;
        this.topicSplitPanelRenderedDisplay = topicSplitPanelRenderedDisplay;
        this.topicXMLErrorsDisplay = topicXMLErrorsDisplay;
        this.topicTagsDisplay = topicTagsDisplay;
        this.topicTagsComponent = topicTagsComponent;
        this.topicBugsDisplay = topicBugsDisplay;
        this.topicRevisionsDisplay = topicRevisionsDisplay;
        this.topicrevisionsComponent = topicrevisionsComponent;
        

        super.bind(Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, topicXMLDisplay, topicViewDisplay, searchResultsDisplay,
                searchResultsComponent, display, waitDisplay);

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
                SearchResultsAndTopicComponent.this.locales = locales;
            }
        });        

       CommonTopicComponent.addKeyboardShortcutEventHandler(this.topicXMLDisplay, this.display);
    }

    /**
     * Refresh the split panel rendered view
     */
    private void refreshRenderedView() {
        topicXMLDisplay.getDriver().flush();
        
        final boolean xmlHasChanges = lastXML == null || !lastXML.equals(getTopicOrRevisionTopic().getItem().getXml());
        
        if (xmlHasChanges)
        {
            lastXMLChange = System.currentTimeMillis();
        }
        
        final Boolean timeToDisplayImage = System.currentTimeMillis() - lastXMLChange >=  Constants.REFRESH_RATE_WTH_IMAGES;
        
        if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage))
        {
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

    @Override
    public void parseToken(final String historyToken) {

        queryString = removeHistoryToken(historyToken, SearchResultsAndTopicPresenter.HISTORY_TOKEN);

        /* Make sure that the query string has at least the prefix */
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
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

            /* set the bugs to show the loading widget */
            if (topicBugsDisplay.getProvider() != null) {
                topicBugsDisplay.getProvider().resetProvider();
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
                    }) {

            };

            /* A callback to respond to a request for a topic with the tags expanded */
            final RESTCalls.RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                    topicTagsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
                        @Override
                        public void doSuccessAction(final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {

                            /* copy the revisions into the displayed Topic */
                            filteredResultsComponent.getProviderData().getDisplayedItem().getItem().setTags(retValue.getTags());

                            /* update the view */
                            initializeViews(Arrays.asList(new TopicViewInterface[] { topicTagsDisplay }));
                        }
                    }) {

            };

            /* A callback to respond to a request for a topic with the bugzilla bugs expanded */
            final RESTCalls.RESTCallback<RESTTopicV1> topicWithBugsCallback = new BaseRestCallback<RESTTopicV1, TopicBugsPresenter.Display>(
                    topicBugsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicBugsPresenter.Display>() {
                        @Override
                        public void doSuccessAction(final RESTTopicV1 retValue, final TopicBugsPresenter.Display display) {
                            final RESTBugzillaBugCollectionV1 collection = retValue.getBugzillaBugs_OTM();
                            /* copy the revisions into the displayed Topic */
                            filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                    .setBugzillaBugs_OTM(collection);
                            /* refresh the celltable */
                            topicBugsDisplay.getProvider().displayNewFixedList(collection.getItems());
                        }
                    }) {

            };

            /* Initiate the REST calls */
            RESTCalls.getTopicWithTags(topicWithTagsCallback, filteredResultsComponent.getProviderData().getSelectedItem()
                    .getItem().getId());
            RESTCalls.getTopicWithRevisions(topicWithRevisionsCallback, filteredResultsComponent.getProviderData()
                    .getSelectedItem().getItem().getId());
            RESTCalls.getTopicWithBugs(topicWithBugsCallback, filteredResultsComponent.getProviderData().getSelectedItem()
                    .getItem().getId());

            /* fix the rendered view buttons */
            initializeSplitViewButtons();
        } finally {
            logger.log(Level.INFO, "EXIT SearchResultsAndTopicComponent.loadAdditionalDisplayedItemData()");
        }

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

            /* Update the page name */
            final StringBuilder title = new StringBuilder(displayedView.getPageName());
            if (this.filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                title.append(": " + filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getTitle());
            }
            display.getPageTitle().setText(title.toString());

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (displayedView == topicXMLDisplay && display.getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
                timer.scheduleRepeating(Constants.REFRESH_RATE);
            } else {
                timer.cancel();
            }

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
                }

                finally {
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
                                /* Update the displayed topic */
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(),
                                        true);
                                /* Update the selected topic */
                                retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);

                                initializeViews();

                                updateDisplayAfterSave(false);

                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
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
                    RESTCalls.saveTopic(callback, updateTopic, message, flag, ServiceConstants.NULL_USER_ID);
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

                initializeDisplay();
                initializeSplitViewButtons();
            }
        };

        final ClickHandler cspsHandler = new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                if (filteredResultsComponent.getProviderData().getDisplayedItem() != null && isOKToProceed()) {

                    final RESTTopicV1 topic = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                            + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + topic.getTitle() + " [" + topic.getId()
                            + "];tag" + ServiceConstants.CSP_TAG_ID + "=1;logic=AND", GWTUtilities.isEventToOpenNewWindow(event)));
                }

            }
        };

        /* Hook up the click listeners */
        for (final TopicViewInterface view : new TopicViewInterface[] { entityPropertiesView, topicXMLDisplay,
                topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay, topicRevisionsDisplay }) {
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
            for (final TopicViewInterface view : new TopicViewInterface[] { entityPropertiesView, topicXMLDisplay,
                    topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay,
                    topicSplitPanelRenderedDisplay }) {
                if (viewIsInFilter(filter, view)) {
                    view.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC, this.split, locales, false);
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
        for (final TopicViewInterface view : new TopicViewInterface[] { entityPropertiesView, topicXMLDisplay,
                topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay, topicRevisionsDisplay }) {
            view.buildSplitViewButtons(split);
        }
    }
}
