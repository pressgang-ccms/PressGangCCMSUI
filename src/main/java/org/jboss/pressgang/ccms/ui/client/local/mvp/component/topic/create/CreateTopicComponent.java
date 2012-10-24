package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.create;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create.CreateTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

/**
 * A component used to provide the logic for the create topicViewDisplay view
 * 
 * @author Matthew Casperson
 */
public class CreateTopicComponent extends ComponentBase<CreateTopicPresenter.Display> implements
        CreateTopicPresenter.LogicComponent {

    private TopicPresenter.Display topicViewDisplay;
    private TopicPresenter.LogicComponent topicViewComponent;
    private TopicXMLPresenter.Display topicXMLDisplay;
    private TopicXMLPresenter.LogicComponent topicXMLComponent;
    private TopicRenderedPresenter.Display topicRenderedDisplay;
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;

    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;
    private TopicTagsPresenter.Display topicTagsDisplay;
    private TopicTagsPresenter.LogicComponent topicTagsComponent;
    /** A collection of views that can be edited and flushed back to their underlying objects */
    private TopicViewInterface[] editableTopicViews;
    /** A collecion view vies that can be updated */
    private TopicViewInterface[] updatableTopicViews;

    /**
     * How the rendering panel is displayed
     */
    private SplitType split = SplitType.NONE;

    /** The last displayed view */
    private TopicViewInterface lastView;

    /** The new topicViewDisplay being created */
    private final RESTTopicV1 newTopic = new RESTTopicV1();

    /** The state of the topic when it was last saved */
    private RESTTopicV1 lastSavedTopic;

    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastView == topicXMLDisplay) {
                topicXMLDisplay.getDriver().flush();
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split);
            }
        }
    };

    /**
     * A click handler to add a tag to a topicViewDisplay
     * 
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = topicTagsDisplay.getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            for (final RESTTagCollectionItemV1 tag : newTopic.getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (tag.getState() == RESTBaseCollectionItemV1.REMOVE_STATE) {
                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        /* Redisplay the view */
                        updateDisplayedTopicView(lastView);

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
            /* Add the tag to the topicViewDisplay */
            newTopic.getTags().addNewItem(selectedTagClone);
            /* Redisplay the view */
            updateDisplayedTopicView(lastView);
        }
    }

    /**
     * A click handler to remove a tag from a topicViewDisplay
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
            if (tag.getState() == RESTBaseCollectionItemV1.ADD_STATE) {
                /* Tag was added and then removed, so we just delete the tag */
                newTopic.getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            updateDisplayedTopicView(lastView);
        }
    }

    @Override
    public void bind(final TopicPresenter.Display topicViewDisplay, final TopicPresenter.LogicComponent topicViewComponent,
            final TopicXMLPresenter.Display topicXMLDisplay, final TopicXMLPresenter.LogicComponent topicXMLComponent,
            final TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay,
            final TopicXMLErrorsPresenter.LogicComponent topicXMLErrorsComponent,
            final TopicTagsPresenter.Display topicTagsDisplay, final TopicTagsPresenter.LogicComponent topicTagsComponent, final TopicRenderedPresenter.Display topicRenderedDisplay,
            final TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay, final CreateTopicPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);

        this.topicViewDisplay = topicViewDisplay;
        this.topicViewComponent = topicViewComponent;
        this.topicXMLDisplay = topicXMLDisplay;
        this.topicXMLComponent = topicXMLComponent;
        this.topicXMLErrorsDisplay = topicXMLErrorsDisplay;
        this.topicTagsDisplay = topicTagsDisplay;
        this.topicTagsComponent = topicTagsComponent;
        this.topicSplitPanelRenderedDisplay = topicSplitPanelRenderedDisplay;
        this.topicRenderedDisplay = topicRenderedDisplay;
        updatableTopicViews = new TopicViewInterface[] { topicViewDisplay, topicTagsDisplay, topicXMLDisplay, topicXMLErrorsDisplay, topicRenderedDisplay };
        editableTopicViews = new TopicViewInterface[] { topicViewDisplay, topicXMLDisplay };

        /* Create an initial collection to hold any new tags, urls and property tags */
        newTopic.setTags(new RESTTagCollectionV1());
        newTopic.setSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
        newTopic.setProperties(new RESTAssignedPropertyTagCollectionV1());

        /* initialise the GWT editors */
        updateViews();
        bindTagEditingButtons();
        
        initializeDisplay();
               
        topicTagsComponent.bindNewTagListBoxes(new AddTagClickhandler());
        updateDisplayedTopicView(topicViewDisplay);

        loadSplitPanelSize();
        bindSplitPanelResize();
        bindTopActionButtons();

        getTopicTemplate();
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

        for (final TopicViewInterface view : updatableTopicViews) {
            view.buildSplitViewButtons(split);
        }
    }

    private void getTopicTemplate() {
        RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, CreateTopicPresenter.Display>(
                display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, CreateTopicPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final CreateTopicPresenter.Display display) {
                        newTopic.setXml(retValue.getValue());

                        /* Refresh the views */
                        topicXMLDisplay.initialize(newTopic, false, true, SplitType.NONE);
                        topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split);
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.TOPIC_TEMPLATE);
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

    private void bindTopActionButtons() {
        final ClickHandler displayTopicClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicViewDisplay);

            }
        };

        final ClickHandler displayTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicTagsDisplay);
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXMLDisplay);

            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXMLErrorsDisplay);

            }
        };

        final ClickHandler saveClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                flushChanges();

                /* Create a topic that has the new details */
                final RESTTopicV1 saveRestTopic = new RESTTopicV1();
                saveRestTopic.setId(newTopic.getId());
                saveRestTopic.explicitSetXml(newTopic.getXml());
                saveRestTopic.explicitSetDescription(newTopic.getDescription());
                saveRestTopic.explicitSetLocale(newTopic.getLocale());
                saveRestTopic.explicitSetTags(newTopic.getTags());
                saveRestTopic.explicitSetSourceUrls_OTM(newTopic.getSourceUrls_OTM());
                saveRestTopic.explicitSetTitle(newTopic.getTitle());
                saveRestTopic.explicitSetProperties(newTopic.getProperties());

                final RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, CreateTopicPresenter.Display>(
                        display, new BaseRestCallback.SuccessAction<RESTTopicV1, CreateTopicPresenter.Display>() {
                            @Override
                            public void doSuccessAction(final RESTTopicV1 retValue, final CreateTopicPresenter.Display display) {
                                retValue.cloneInto(newTopic, true);
                                lastSavedTopic = retValue;
                                updateDisplayedTopicView(lastView);
                                loadTags();
                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                            }
                        }) {
                };

                /* Create or update the topic */
                if (saveRestTopic.getId() != null) {
                    RESTCalls.saveTopic(callback, saveRestTopic);
                } else {
                    RESTCalls.createTopic(callback, saveRestTopic);
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

                split = SplitType.NONE;
                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE);

                initializeDisplay();
                updateDisplayedTopicView(lastView);
            }
        };

        final ClickHandler splitMenuVSplitHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                split = SplitType.VERTICAL;
                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL);

                initializeDisplay();
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split);
                updateDisplayedTopicView(lastView);
            }
        };

        final ClickHandler splitMenuHSplitHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();

                split = SplitType.HORIZONTAL;
                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                        Preferences.TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL);

                initializeDisplay();
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split);
                updateDisplayedTopicView(lastView);
            }
        };
        
        final ClickHandler topicRenderedClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* Sync any changes back to the underlying object */
                flushChanges();
                updateDisplayedTopicView(topicRenderedDisplay);                
            }
        };

        for (final TopicViewInterface view : updatableTopicViews) {
            view.getTopicTags().addClickHandler(displayTagsClickHandler);
            view.getFields().addClickHandler(displayTopicClickHandler);
            view.getXml().addClickHandler(topicXMLClickHandler);
            view.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
            view.getRendered().addClickHandler(topicRenderedClickHandler);

            view.getRenderedSplit().addClickHandler(splitMenuHandler);
            view.getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            view.getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            view.getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            view.getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            view.getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);
        }
    }

    /**
     * TODO: copied from SearchResultsAndTopicComponent - abstract this
     */
    private void showRegularMenu() {
        display.getTopicViewActionButtonsPanel().clear();
        display.getTopicViewActionButtonsPanel().add(lastView.getTopActionPanel());
    }

    /**
     * TODO: copied from SearchResultsAndTopicComponent - abstract this
     */
    private void showRenderedSplitPanelMenu() {
        display.getTopicViewActionButtonsPanel().clear();
        display.getTopicViewActionButtonsPanel().add(lastView.getRenderedSplitViewMenu());
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        /* Update the data object with the GUI changes */
        for (final TopicViewInterface view : editableTopicViews) {
            /* Make sure we have a driver, and it is not a readonly view */
            if (view.getDriver() != null) {
                view.getDriver().flush();
            }
        }
    }

    private void loadTags() {
        /* A callback to respond to a request for a topic with the tags expanded */
        final RESTCalls.RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                topicTagsDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(RESTTopicV1 retValue, TopicTagsPresenter.Display display) {
                        /* copy the revisions into the displayed Topic */
                        newTopic.setTags(retValue.getTags());
                        /* If we are looking at the rendered view, update it */
                        if (lastView == topicTagsDisplay) {
                            updateDisplayedTopicView(topicTagsDisplay);
                        }
                    }
                }) {
            @Override
            public void failed() {
                super.failed();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        RESTCalls.getTopicWithTags(topicWithTagsCallback, newTopic.getId());
    }

    /**
     * Sets the viewShown property on the views to reflect the currently displayed view
     * 
     * @param shownDisplay The currently displayed view
     */
    private void setShownDisplay(final TopicViewInterface shownDisplay) {
        /* The main display is always visible */
        this.display.setViewShown(true);
        this.topicViewDisplay.setViewShown(shownDisplay == topicViewDisplay);
        this.topicTagsDisplay.setViewShown(shownDisplay == topicTagsDisplay);
        this.topicXMLDisplay.setViewShown(shownDisplay == topicXMLDisplay);
        this.topicXMLErrorsDisplay.setViewShown(shownDisplay == topicXMLErrorsDisplay);
    }

    /**
     * Updates the current topicViewDisplay view
     */
    private void updateDisplayedTopicView(final TopicViewInterface selectedView) {
        if (lastView != selectedView) {

            flushChanges();

            setShownDisplay(selectedView);

            display.getTopicViewActionButtonsPanel().clear();
            display.getTopicViewActionButtonsPanel().setWidget(selectedView.getTopActionPanel());

            display.getTopicViewPanel().clear();
            display.getTopicViewPanel().setWidget(selectedView.getPanel());
        }

        /* By default, stop the automatic updating of the rendered view panel */
        timer.cancel();

        updateViews();

        if (selectedView == this.topicXMLDisplay) {
            /* Force a redisplay of the ACE editor */
            topicXMLDisplay.getEditor().redisplay();

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (split != SplitType.NONE) {
                /* setup automatic updating */
                timer.scheduleRepeating(Constants.REFRESH_RATE);
                /* Do an initial update */
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split);
            }
        }
        else if (selectedView == this.topicTagsDisplay)
        {
            bindTagEditingButtons();
        }

        lastView = selectedView;
    }
    
    private void updateViews()
    {
        /* refresh the data being displayed */
        for (final TopicViewInterface view : this.updatableTopicViews) {
            view.initialize(newTopic, false, true, split);
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
    public boolean checkForUnsavedChanges() {
        
        /* Save any pending changes */
        flushChanges();
        
        final boolean lastSavedTopicIsNull = lastSavedTopic == null;
        boolean unsavedChanges = false;

        /* See if any values have been set */
        boolean newTopicIsEmpty = true;
        if (newTopic.getLocale() != null && !newTopic.getLocale().trim().isEmpty())
            newTopicIsEmpty = false;
        if (newTopic.getDescription() != null && !newTopic.getDescription().trim().isEmpty())
            newTopicIsEmpty = false;
        if (newTopic.getTitle() != null && !newTopic.getTitle().trim().isEmpty())
            newTopicIsEmpty = false;
        if (newTopic.getXml() != null && !newTopic.getXml().trim().isEmpty())
            newTopicIsEmpty = false;
        
        /* If there are any modified tags in newTopic, we have unsaved changes */
        if (!newTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty())
        {
            unsavedChanges = true;
        }

        /*
         * The newTopic can be in two states. The first is where lastSavedTopic == null, which implies that the topics has not
         * been saved.
         */
        if (lastSavedTopicIsNull) {

            /*
             * If the newTopic is empty, it means nothing has been set, and we have no changes to save.
             */
            if (!newTopicIsEmpty) {
                unsavedChanges = true;
            }
        }
        /* The second state is where newTopics has been saved */
        else {
            
            /*
             * If any values in newTopic don't match lastSavedTopic, wehave unsaved changes
             */
            if (!GWTUtilities.compareStrings(newTopic.getTitle(), lastSavedTopic.getTitle()))
                unsavedChanges = true;
            if (!GWTUtilities.compareStrings(newTopic.getLocale(), lastSavedTopic.getLocale()))
                unsavedChanges = true;
            if (!GWTUtilities.compareStrings(newTopic.getDescription(), lastSavedTopic.getDescription()))
                unsavedChanges = true;
            if (!GWTUtilities.compareStrings(newTopic.getXml(), lastSavedTopic.getXml()))
                unsavedChanges = true;
        }

        /* Prompt the user if they are happy to lose thheir changes */
        if (unsavedChanges) {
            return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
        }

        return true;
    }
}
