package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create;

import java.util.List;

import com.google.gwt.user.client.ui.*;
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
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicCombinedViewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.GetCurrentTopic;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.*;
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

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * A component used to provide the logic for the create topicViewComponent.getDisplay() view
 *
 * @author Matthew Casperson
 */
@Dependent
public class CreateTopicPresenter extends ComponentBase implements
        TemplatePresenter {

    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getTopicViewActionButtonsPanel();

        DockLayoutPanel getTopicViewLayoutPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        SimpleLayoutPanel getTopicViewPanel();

        void initialize(final SplitType splitType, final Panel panel);

        LogMessageInterface getMessageLogDialog();
    }

    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "CreateTopicView";



    @Inject
    private Display display;

    /** The component that provides logic for the topic view */
    @Inject
    private TopicPresenter topicComponent;

    /** The component that provides logic for the tags view */
    @Inject
    private TopicTagsPresenter topicTagsComponent;

    /** The component that provides logic for the topic xml view */
    @Inject
    private TopicXMLPresenter topicXMLComponent;

    /** The component that provides logic for the topic xml errors view */
    @Inject
    private TopicXMLErrorsPresenter topicXMLErrorsComponent;

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

    /** The new topicViewComponent.getDisplay() being created */
    private final RESTTopicV1 newTopic = new RESTTopicV1();

    /** The state of the topic when it was last saved */
    private RESTTopicV1 lastSavedTopic;

    private List<String> locales;

    private boolean templateLoaded = false;
    private boolean localesLoaded = false;

    private String lastXML = null;
    private long lastXMLChange;
    private boolean isDisplayingImage = false;

    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastView == topicXMLComponent.getDisplay()) {

                refreshRenderedView(false);

            }
        }
    };

    /**
     * A click handler to add a tag to a topicViewComponent.getDisplay()
     *
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = topicTagsComponent.getDisplay().getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            for (final RESTTagCollectionItemV1 tag : newTopic.getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (RESTBaseCollectionItemV1.REMOVE_STATE.equals(tag.getState())) {
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
            /* Add the tag to the topicViewComponent.getDisplay() */
            newTopic.getTags().addNewItem(selectedTagClone);
            /* Redisplay the view */
            updateDisplayedTopicView(lastView);
        }
    }

    /**
     * A click handler to remove a tag from a topicViewComponent.getDisplay()
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
            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
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
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        topicComponent.process(null, ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        topicTagsComponent.process(null, ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        topicXMLComponent.process(null, ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        topicXMLErrorsComponent.process(null, ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);

        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

    public void process(final int helpTopicId, final String pageId, final BaseTemplateViewInterface waitDisplay) {

        super.bind(helpTopicId, pageId, display);

        updatableTopicViews = new TopicViewInterface[] { topicComponent.getDisplay(), topicTagsComponent.getDisplay(), topicXMLComponent.getDisplay(),
                topicXMLErrorsComponent.getDisplay(), topicRenderedDisplay };
        editableTopicViews = new TopicViewInterface[] { topicComponent.getDisplay(), topicXMLComponent.getDisplay() };

        /* Create an initial collection to hold any new tags, urls and property tags */
        newTopic.setLocale("");
        newTopic.setTags(new RESTTagCollectionV1());
        newTopic.setSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
        newTopic.setProperties(new RESTAssignedPropertyTagCollectionV1());

        /* initialise the GWT editors */
        bindTagEditingButtons();

        initializeDisplay();

        topicTagsComponent.bindNewTagListBoxes(new AddTagClickhandler());

        loadSplitPanelSize();
        bindSplitPanelResize();
        bindTopActionButtons();

        getTopicTemplate();

        BaseTopicCombinedViewPresenter.populateLocales(waitDisplay, new StringListLoaded() {

            @Override
            public void stringListLoaded(final List<String> stringList) {

                CreateTopicPresenter.this.locales = stringList;

                final RESTCallback<RESTStringConstantV1> defaultLocaleCallback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                        waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue,
                                                final BaseTemplateViewInterface display) {

                        newTopic.setLocale(retValue.getValue());

                        localesLoaded = true;
                        finishLoading();
                    }
                }) {
                };

                RESTCalls.getStringConstant(defaultLocaleCallback, ServiceConstants.DEFAULT_LOCALE_ID);

            }
        });

        BaseTopicCombinedViewPresenter.populateXMLElements(waitDisplay, new StringListLoaded() {

            @Override
            public void stringListLoaded(final List<String> xmlElements) {
                topicXMLComponent.getDisplay().getXmlTagsDialog().setSuggestions(xmlElements);
            }
        });

        BaseTopicCombinedViewPresenter.addKeyboardShortcutEventHandler(this.topicXMLComponent.getDisplay(), this.display, new GetCurrentTopic() {

            @Override
            public RESTTopicV1 getCurrentlyEditedTopic() {
                return newTopic;
            }
        });

    }

    /**
     * Reflect the state of the editor with the XML editor toggle buttons
     */
    private void setXMLEditorButtonsToEditorState() {

        topicXMLComponent.getDisplay().getLineWrap().setDown(topicXMLComponent.getDisplay().getEditor().getUserWrapMode());
        topicXMLComponent.getDisplay().getShowInvisibles().setDown(topicXMLComponent.getDisplay().getEditor().getShowInvisibles());

    }

    /**
     * Called after the template and locales are loaded, and once the last load operation is complete the view is updated
     */
    private void finishLoading() {
        if (templateLoaded && localesLoaded) {
            initializeViews();
            updateDisplayedTopicView(topicXMLComponent.getDisplay());
            initializeSplitViewButtons();
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

    }

    private void getTopicTemplate() {
        RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, CreateTopicPresenter.Display>(
                display, new BaseRestCallback.SuccessAction<RESTStringConstantV1, CreateTopicPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTStringConstantV1 retValue, final CreateTopicPresenter.Display display) {
                newTopic.setXml(retValue.getValue());

                templateLoaded = true;
                finishLoading();
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
    }

    private void bindTopActionButtons() {
        final ClickHandler displayTopicClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicComponent.getDisplay());

            }
        };

        final ClickHandler displayTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicTagsComponent.getDisplay());
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXMLComponent.getDisplay());

            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXMLErrorsComponent.getDisplay());

            }
        };

        final ClickHandler saveClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                if (hasUnsavedChanges()) {
                    /* set to major revision and add default message when saving a topic for the first time */
                    if (newTopic.getId() == null) {
                        display.getMessageLogDialog().getMajorChange().setValue(true);
                        display.getMessageLogDialog().getMessage().setValue(PressGangCCMSUI.INSTANCE.InitialTopicCreation());
                    }

                    display.getMessageLogDialog().getDialogBox().center();
                    display.getMessageLogDialog().getDialogBox().show();
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            }
        };

        display.getMessageLogDialog().getOk().addClickHandler(new ClickHandler() {
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
                        lastXML = null;
                                /* The save process will modify the title, so do a refresh of the rendered view */
                        topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split, locales, false);
                        Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                }) {
                };

                final String message = display.getMessageLogDialog().getMessage().getText();
                final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE
                        : ServiceConstants.MAJOR_CHANGE);

                /* Create or update the topic */
                if (saveRestTopic.getId() != null) {
                    RESTCalls.saveTopic(callback, saveRestTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
                } else {
                    RESTCalls.createTopic(callback, saveRestTopic, message, flag, ServiceConstants.NULL_USER_ID.toString());
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

                timer.cancel();

                initializeSplitViewButtons();
                initializeDisplay();
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

                timer.scheduleRepeating(Constants.REFRESH_RATE);

                initializeSplitViewButtons();
                initializeDisplay();
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split, locales, false);
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

                timer.scheduleRepeating(Constants.REFRESH_RATE);

                initializeSplitViewButtons();
                initializeDisplay();
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split, locales, false);
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

        BaseTopicCombinedViewPresenter.addKeyboardShortcutEvents(topicXMLComponent.getDisplay(), display);
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
                topicTagsComponent.getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
            @Override
            public void doSuccessAction(final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {
                        /* copy the revisions into the displayed Topic */
                newTopic.setTags(retValue.getTags());
                        /* If we are looking at the rendered view, update it */
                if (lastView == topicTagsComponent.getDisplay()) {
                    updateDisplayedTopicView(topicTagsComponent.getDisplay());
                }
            }
        }) {

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
        this.topicComponent.getDisplay().setViewShown(shownDisplay == topicComponent.getDisplay());
        this.topicTagsComponent.getDisplay().setViewShown(shownDisplay == topicTagsComponent.getDisplay());
        this.topicXMLComponent.getDisplay().setViewShown(shownDisplay == topicXMLComponent.getDisplay());
        this.topicXMLErrorsComponent.getDisplay().setViewShown(shownDisplay == topicXMLErrorsComponent.getDisplay());
    }

    /**
     * Updates the current topicViewComponent.getDisplay() view
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

        initializeViews();

        if (selectedView == this.topicXMLComponent.getDisplay()) {
            /* Force a redisplay of the ACE editor */
            topicXMLComponent.getDisplay().getEditor().redisplay();

            /* While editing the XML, we need to setup a refresh of the rendered view */
            if (split != SplitType.NONE) {
                /* setup automatic updating */
                timer.scheduleRepeating(Constants.REFRESH_RATE);
                /* Do an initial update */
                topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split, locales, false);
            }

            setXMLEditorButtonsToEditorState();

        } else {

            refreshRenderedView(true);

            if (selectedView == this.topicTagsComponent.getDisplay()) {
                bindTagEditingButtons();
            }
        }

        BaseTopicCombinedViewPresenter.setHelpTopicForView(this, selectedView);

        lastView = selectedView;
    }

    private void initializeViews() {
        /* refresh the data being displayed */
        for (final TopicViewInterface view : this.updatableTopicViews) {
            view.initialize(newTopic, false, true, split, locales, false);
        }
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        /* This will be null if the tags have not been downloaded */
        if (topicTagsComponent.getDisplay().getEditor() != null) {
            for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTagsComponent.getDisplay().getEditor().projects.getEditors()) {
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
    public boolean hasUnsavedChanges() {

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
        if (!newTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
            unsavedChanges = true;
        }

        /* If there are any modified property tags in newTopic, we have unsaved changes */
        if (!newTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
            unsavedChanges = true;
        }

        /* If there are any modified source urls in newTopic, we have unsaved changes */
        if (!newTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
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

        return unsavedChanges;
    }

    private void initializeSplitViewButtons() {
        /* fix the rendered view button */
        for (final TopicViewInterface view : new TopicViewInterface[] { topicComponent.getDisplay(), topicXMLComponent.getDisplay(),
                topicRenderedDisplay, topicXMLErrorsComponent.getDisplay(), topicTagsComponent.getDisplay() }) {
            view.buildSplitViewButtons(split);
        }
    }

    /**
     * Refresh the split panel rendered view
     */
    private void refreshRenderedView(final boolean forceExternalImages) {
        topicXMLComponent.getDisplay().getDriver().flush();

        final boolean xmlHasChanges = lastXML == null || !lastXML.equals(newTopic.getXml());

        if (xmlHasChanges) {
            lastXMLChange = System.currentTimeMillis();
        }

        final Boolean timeToDisplayImage = forceExternalImages
                || System.currentTimeMillis() - lastXMLChange >= Constants.REFRESH_RATE_WTH_IMAGES;

        if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
            isDisplayingImage = timeToDisplayImage;
            topicSplitPanelRenderedDisplay.initialize(newTopic, false, true, split, locales, isDisplayingImage);
        }

        lastXML = newTopic.getXml();
    }
}
