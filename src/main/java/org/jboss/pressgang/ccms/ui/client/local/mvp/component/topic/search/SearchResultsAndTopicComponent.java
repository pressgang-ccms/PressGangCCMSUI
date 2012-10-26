package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class SearchResultsAndTopicComponent extends ComponentBase<SearchResultsAndTopicPresenter.Display> implements
        SearchResultsAndTopicPresenter.LogicComponent {

    /**
     * false to indicate that the topic views should display action buttons applicabale to established topics (as opposed to new
     * topics)
     */
    private static final boolean NEW_TOPIC = false;

    @Inject
    private HandlerManager eventBus;

    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (selectedView == topicXMLDisplay) {
                topicXMLDisplay.getDriver().flush();
                topicSplitPanelRenderedDisplay.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC,
                        display.getSplitType());
            }
        }
    };

    private String queryString;

    private TopicPresenter.Display topicViewDisplay;
    private TopicPresenter.LogicComponent topicViewComponent;
    private TopicXMLPresenter.Display topicXMLDisplay;
    private TopicXMLPresenter.LogicComponent topicXMLComponent;
    private TopicRenderedPresenter.Display topicRenderedDisplay;
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;
    private SearchResultsPresenter.Display searchResultsDisplay;
    private SearchResultsPresenter.LogicComponent searchResultsComponent;
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;
    private TopicTagsPresenter.Display topicTagsDisplay;
    private TopicTagsPresenter.LogicComponent topicTagsComponent;
    private TopicBugsPresenter.Display topicBugsDisplay;
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;
    private TopicRevisionsPresenter.LogicComponent topicrevisionsComponent;

    private TopicViewInterface[] topicViews;

    /**
     * This will reference the selected view, so as to maintain the view between clicks
     */
    private TopicViewInterface selectedView;

    /**
     * This will reference the previously selected view,
     */
    private TopicViewInterface previousView;

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
            for (final RESTTagCollectionItemV1 tag : searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
                    .getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (tag.getState() == RESTBaseCollectionItemV1.REMOVE_STATE) {
                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        /* Redisplay the view */
                        updateDisplayedTopicView();

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
            searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getTags().addNewItem(selectedTagClone);
            /* Redisplay the view */
            updateDisplayedTopicView();
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
            if (searchResultsComponent.getTopicProviderData().getDisplayedItem() == null) {
                throw new IllegalStateException(
                        "searchResultsComponent.getTopicProviderData().getDisplayedItem() cannot be null");
            }

            if (tag.getState() == RESTBaseCollectionItemV1.ADD_STATE) {
                /* Tag was added and then removed, so we just delete the tag */
                searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            updateDisplayedTopicView();
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

        super.bind(display, waitDisplay);

        this.topicViewDisplay = topicViewDisplay;
        this.topicViewComponent = topicViewComponent;
        this.topicXMLDisplay = topicXMLDisplay;
        this.topicXMLComponent = topicXMLComponent;
        this.topicRenderedDisplay = topicRenderedDisplay;
        this.topicSplitPanelRenderedDisplay = topicSplitPanelRenderedDisplay;
        this.searchResultsDisplay = searchResultsDisplay;
        this.searchResultsComponent = searchResultsComponent;
        this.topicXMLErrorsDisplay = topicXMLErrorsDisplay;
        this.topicTagsDisplay = topicTagsDisplay;
        this.topicTagsComponent = topicTagsComponent;
        this.topicBugsDisplay = topicBugsDisplay;
        this.topicRevisionsDisplay = topicRevisionsDisplay;
        this.topicrevisionsComponent = topicrevisionsComponent;

        this.topicViews = new TopicViewInterface[] { topicViewDisplay, topicXMLDisplay, topicRenderedDisplay,
                topicXMLErrorsDisplay, topicTagsDisplay, topicBugsDisplay, topicRevisionsDisplay };

        initializeDisplay();

        this.topicBugsDisplay.setProvider(generateTopicBugListProvider());
        this.topicRevisionsDisplay.setProvider(generateTopicRevisionsListProvider());

        bindTopicEditButtons(this.searchResultsComponent.getProvider());
        bindViewTopicRevisionButton();
        bindSplitPanelResize();
        bindTagEditingButtons();
        loadSplitPanelSize();
        this.topicTagsComponent.bindNewTagListBoxes(new AddTagClickhandler());
        bindTopicListRowClicks();
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

        for (final TopicViewInterface view : topicViews) {
            view.buildSplitViewButtons(split);
        }
    }

    /**
     * Updates the current topic view
     */
    private void updateDisplayedTopicView() {
        /* Update the page name */
        final StringBuilder title = new StringBuilder(selectedView.getPageName());
        if (this.searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
            title.append(": " + searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getTitle());
        }
        display.getPageTitle().setText(title.toString());

        /*
         * Here we use the initialize function to copy the topic data into the GWT Editors. To save some data being retreived
         * and sent by the server, the revisions view always uses the revisions from the current topic. All other views will
         * display the revision topic if it has been selected.
         */

        /*
         * Need to do an initial call to initialize for the rendered view in the split pane
         */
        topicSplitPanelRenderedDisplay.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC,
                display.getSplitType());
        /* By default, stop the automatic updating of the rendered view panel */
        timer.cancel();

        if (selectedView == this.topicRevisionsDisplay) {
            /*
             * The revisions always come from the parent topic (this saves us expanding the revisions when loading a revision
             */
            selectedView.initialize(searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem(),
                    isReadOnlyMode(), NEW_TOPIC, display.getSplitType());
        } else {
            /* All other details come from the revision topic */
            selectedView.initialize(getTopicOrRevisionTopic().getItem(), isReadOnlyMode(), NEW_TOPIC, display.getSplitType());
        }

        /* Need to redisplay to work around a bug in the ACE editor */
        if (selectedView == this.topicXMLDisplay) {
            topicXMLDisplay.getLineWrap().setDown(topicXMLDisplay.getEditor().getUserWrapMode());
            topicXMLDisplay.getShowInvisibles().setDown(topicXMLDisplay.getEditor().getShowInvisibles());
            topicXMLDisplay.getEditor().redisplay();

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (display.getSplitType() != SplitType.NONE) {
                if (!isReadOnlyMode()) {
                    timer.scheduleRepeating(Constants.REFRESH_RATE);
                }
            }
        }

        /*
         * Here we add behaviours to additional buttons or views that don't use the Editor framework (like those that use
         * CellTables)
         */

        /*
         * if we just displayed a new selection of tags, link up all the tag delete buttons
         */
        else if (selectedView == this.topicTagsDisplay) {
            bindTagEditingButtons();
        }
    }

    /**
     * Displays a new topic view
     */
    private void changeDisplayedTopicView() {
        display.displayChildView(selectedView);

        if (previousView != null) {
            previousView.setViewShown(false);
        }
        selectedView.setViewShown(true);

        previousView = selectedView;

        updateDisplayedTopicView();
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        if (selectedView == null || selectedView.getDriver() == null) {
            return;
        }

        /* These are read only views */
        if (selectedView == topicXMLErrorsDisplay || selectedView == topicTagsDisplay) {
            return;
        }

        selectedView.getDriver().flush();
    }

    /**
     * The currently displayed topic is topicRevisionsDisplay.getRevisionTopic() if it is not null, or
     * searchResultsComponent.getTopicProviderData().getDisplayedItem() otherwise.
     * 
     * @return The currently displayed topic
     */
    private RESTTopicCollectionItemV1 getTopicOrRevisionTopic() {
        final RESTTopicCollectionItemV1 sourceTopic = topicRevisionsDisplay.getRevisionTopic() == null ? searchResultsComponent
                .getTopicProviderData().getDisplayedItem() : topicRevisionsDisplay.getRevisionTopic();
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
        display.getViewActionButtonsPanel().add(selectedView.getTopActionPanel());
    }

    private void showRenderedSplitPanelMenu() {
        display.getViewActionButtonsPanel().clear();
        display.getViewActionButtonsPanel().add(selectedView.getRenderedSplitViewMenu());
    }

    /**
     * Load the split panel sizes
     */
    private void loadSplitPanelSize() {

        display.getSplitPanel().setSplitPosition(display.getResultsViewLayoutPanel(),
                Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);

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
                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null
                        && searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getRevisions() != null
                        && searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getRevisions().getItems() != null) {
                    displayNewFixedList(searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
                            .getRevisions().getItems());
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
                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null
                        && searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getBugzillaBugs_OTM() != null) {
                    displayNewFixedList(searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
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

                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH, display.getSplitPanel()
                        .getSplitPosition(display.getResultsViewLayoutPanel()) + "");

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
     * Bind the button click events for the topic editor screens
     */
    private void bindTopicListRowClicks() {
        searchResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTopicCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTTopicCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    searchResultsComponent.getTopicProviderData().setSelectedItem(event.getValue());
                    searchResultsComponent.getTopicProviderData().setDisplayedItem(event.getValue().clone(true));
                    loadNewTopicDetails();
                }
            }
        });
    }

    private void loadNewTopicDetails() {
        topicRevisionsDisplay.setRevisionTopic(null);

        /* Update the current view, or display the default */
        if (selectedView == null) {
            /* Switch to the default view if none was selected */
            selectedView = topicXMLDisplay;
            changeDisplayedTopicView();
        } else {
            /* Otherwise update the display with the new topic */
            updateDisplayedTopicView();
        }

        /* set the revisions to show the loading widget */
        if (topicRevisionsDisplay.getProvider() != null)
            topicRevisionsDisplay.getProvider().resetProvider();

        /* set the bugs to show the loading widget */
        if (topicBugsDisplay.getProvider() != null)
            topicBugsDisplay.getProvider().resetProvider();

        /* A callback to respond to a request for a topic with the revisions expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithRevisionsCallback = new BaseRestCallback<RESTTopicV1, TopicRevisionsPresenter.Display>(
                topicRevisionsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicRevisionsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, TopicRevisionsPresenter.Display display) {
                        searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
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
                    public void doSuccessAction(RESTTopicV1 retValue, TopicTagsPresenter.Display display) {
                        /* copy the revisions into the displayed Topic */
                        searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().setTags(retValue.getTags());
                        /* If we are looking at the rendered view, update it */
                        if (selectedView == topicTagsDisplay) {
                            updateDisplayedTopicView();
                        }
                    }
                }) {

        };

        /* A callback to respond to a request for a topic with the bugzilla bugs expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithBugsCallback = new BaseRestCallback<RESTTopicV1, TopicBugsPresenter.Display>(
                topicBugsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicBugsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, TopicBugsPresenter.Display display) {
                        final RESTBugzillaBugCollectionV1 collection = retValue.getBugzillaBugs_OTM();
                        /* copy the revisions into the displayed Topic */
                        searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
                                .setBugzillaBugs_OTM(collection);
                        /* refresh the celltable */
                        topicBugsDisplay.getProvider().displayNewFixedList(collection.getItems());
                    }
                }) {

        };

        /* Initiate the REST calls */
        RESTCalls.getTopicWithTags(topicWithTagsCallback, searchResultsComponent.getTopicProviderData().getSelectedItem()
                .getItem().getId());
        RESTCalls.getTopicWithRevisions(topicWithRevisionsCallback, searchResultsComponent.getTopicProviderData()
                .getSelectedItem().getItem().getId());
        RESTCalls.getTopicWithBugs(topicWithBugsCallback, searchResultsComponent.getTopicProviderData().getSelectedItem()
                .getItem().getId());
    }

    /**
     * Bind the button click events for the various topic views.
     * 
     * @param provider The provider created by the generateTopicListProvider() method
     */
    private void bindTopicEditButtons(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider) {
        /* Build up a click handler to save the topic */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>() {
                        @Override
                        public void begin() {
                            display.addWaitOperation();
                        }

                        @Override
                        public void generalException(final Exception e) {
                            Window.alert(PressGangCCMSUI.INSTANCE.ErrorSavingTopic());
                            display.removeWaitOperation();
                            topicXMLDisplay.getEditor().redisplay();
                        }

                        @Override
                        public void success(final RESTTopicV1 retValue) {
                            try {
                                /* Update the displayed topic */
                                retValue.cloneInto(searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem(),
                                        true);
                                /* Update the selected topic */
                                retValue.cloneInto(searchResultsComponent.getTopicProviderData().getSelectedItem().getItem(),
                                        true);
                                /* Update the topic list */
                                provider.updateRowData(searchResultsComponent.getTopicProviderData().getStartRow(),
                                        searchResultsComponent.getTopicProviderData().getItems());

                                loadNewTopicDetails();

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
                    final RESTTopicV1 updateTopic = searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem()
                            .clone(true);
                    updateTopic.explicitSetBugzillaBugs_OTM(updateTopic.getBugzillaBugs_OTM());
                    updateTopic.explicitSetProperties(updateTopic.getProperties());
                    updateTopic.explicitSetSourceUrls_OTM(updateTopic.getSourceUrls_OTM());
                    updateTopic.explicitSetTags(updateTopic.getTags());
                    updateTopic.explicitSetDescription(updateTopic.getDescription());
                    updateTopic.explicitSetLocale(updateTopic.getLocale());
                    updateTopic.explicitSetTitle(updateTopic.getTitle());
                    updateTopic.explicitSetXml(updateTopic.getXml());

                    RESTCalls.saveTopic(callback, updateTopic);
                }
            }
        };

        final ClickHandler topicViewClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicViewDisplay;
                    changeDisplayedTopicView();
                }
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicXMLDisplay;
                    changeDisplayedTopicView();

                }
            }
        };

        final ClickHandler topicRenderedClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicRenderedDisplay;
                    changeDisplayedTopicView();
                }
            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicXMLErrorsDisplay;
                    changeDisplayedTopicView();
                }
            }
        };

        final ClickHandler topicTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicTagsDisplay;
                    changeDisplayedTopicView();
                }
            }
        };

        final ClickHandler topicBugsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicBugsDisplay;
                    changeDisplayedTopicView();
                }
            }
        };

        final ClickHandler topicRevisionsClickHanlder = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                if (searchResultsComponent.getTopicProviderData().getDisplayedItem() != null) {
                    selectedView = topicRevisionsDisplay;
                    changeDisplayedTopicView();
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
            }
        };

        /* Hook up the click listeners */
        for (final TopicViewInterface view : topicViews) {
            view.getRenderedSplit().addClickHandler(splitMenuHandler);
            view.getFields().addClickHandler(topicViewClickHandler);
            view.getXml().addClickHandler(topicXMLClickHandler);
            view.getRendered().addClickHandler(topicRenderedClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
            view.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
            view.getTopicTags().addClickHandler(topicTagsClickHandler);
            view.getBugs().addClickHandler(topicBugsClickHandler);
            view.getHistory().addClickHandler(topicRevisionsClickHanlder);

            view.getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            view.getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            view.getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            view.getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            view.getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);
        }
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        /* This will be null if the tags have not been downloaded */
        if (topicTagsDisplay.getEditor() != null) {
            for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTagsDisplay.getEditor().projects.getEditors()) {
                for (final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories
                        .getEditors()) {
                    for (final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                        topicTagViewTagEditor.getDelete().addClickHandler(
                                new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag()));
                    }
                }
            }
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
                            public void doSuccessAction(RESTTopicV1 retValue, TopicRevisionsPresenter.Display display) {
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
                                topicrevisionsComponent.displayDiff(retValue.getXml(), retValueLabel, sourceTopic.getItem()
                                        .getXml(), sourceTopicLabel);
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

                if (revisionTopic.getItem().getRevision()
                        .equals(searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem().getRevision())) {
                    /*
                     * The latest revision is actually the same as the main topic, so if that is clicked, we want to edit the
                     * main topic
                     */
                    updateDisplayedTopicView();
                } else {
                    /* Reset the reference to the revision topic */
                    topicRevisionsDisplay.setRevisionTopic(revisionTopic);
                }

                /* default to the rendered view */
                if (selectedView == null) {
                    selectedView = topicRevisionsDisplay;
                    changeDisplayedTopicView();
                } else {
                    updateDisplayedTopicView();
                }
            }
        });
    }

    @Override
    public boolean checkForUnsavedChanges() {

        /* Save any pending changes */
        flushChanges();

        final RESTTopicV1 displayedTopic = this.searchResultsComponent.getTopicProviderData().getDisplayedItem().getItem();
        final RESTTopicV1 selectedTopic = this.searchResultsComponent.getTopicProviderData().getSelectedItem().getItem();

        boolean unsavedChanges = false;

        /* If there are any modified tags in newTopic, we have unsaved changes */
        if (!displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
            unsavedChanges = true;
        }

        /*
         * If any values in selectedTopic don't match displayedTopic, we have unsaved changes
         */
        if (!GWTUtilities.compareStrings(selectedTopic.getTitle(), displayedTopic.getTitle()))
            unsavedChanges = true;
        if (!GWTUtilities.compareStrings(selectedTopic.getLocale(), displayedTopic.getLocale()))
            unsavedChanges = true;
        if (!GWTUtilities.compareStrings(selectedTopic.getDescription(), displayedTopic.getDescription()))
            unsavedChanges = true;
        if (!GWTUtilities.compareStrings(selectedTopic.getXml(), displayedTopic.getXml()))
            unsavedChanges = true;

        /* Prompt the user if they are happy to lose their changes */
        if (unsavedChanges) {
            return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
        }

        return true;
    }

}
