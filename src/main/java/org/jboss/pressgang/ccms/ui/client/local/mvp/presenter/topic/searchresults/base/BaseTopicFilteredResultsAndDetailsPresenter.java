package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * This class provides the common functionality shared between the topic and
 * translated topic screen.
 * <p/>
 * This class has a number of abstract protected methods that can be used to
 * provide additional functionality specific to the needs of either the topic
 * or translated topic screens.
 */
public abstract class BaseTopicFilteredResultsAndDetailsPresenter<
        T extends RESTBaseTopicV1<T, U, V>,
        U extends RESTBaseCollectionV1<T, U, V>,
        V extends RESTBaseCollectionItemV1<T, U, V>,
        Y extends Editor<T>>
        extends BaseSearchAndEditComponent<T, U, V, Y>
        implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTopicFilteredResultsAndDetailsPresenter.class.getName());

    @Inject
    private HandlerManager eventBus;

    private String queryString;

    @Inject
    private TopicXMLPresenter topicXMLComponent;
    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;

    @Inject
    private TopicXMLErrorsPresenter topicXMLErrorsPresenter;
    @Inject
    private TopicTagsPresenter topicTagsComponent;
    @Inject
    private TopicBIRTBugsPresenter topicBugsPresenter;
    @Inject
    private TopicRenderedPresenter topicRenderedPresenter;
    @Inject
    private TopicPropertyTagsPresenter topicPropertyTagPresenter;
    @Inject
    private TopicSourceURLsPresenter topicSourceURLsPresenter;
    /**
     * How the rendering panel is displayed
     */
    private SplitType split = SplitType.NONE;

    protected abstract Display getDisplay();

    protected final TopicXMLPresenter getTopicXMLComponent() {
        return topicXMLComponent;
    }

    protected abstract BaseFilteredResultsComponent<V> getSearchResultsComponent();

    protected final TopicTagsPresenter getTopicTagsComponent() {
        return topicTagsComponent;
    }

    protected final TopicPropertyTagsPresenter getTopicPropertyTagPresenter() {
        return topicPropertyTagPresenter;
    }

    protected final TopicXMLErrorsPresenter getTopicXMLErrorsPresenter() {
        return topicXMLErrorsPresenter;
    }

    protected final TopicRenderedPresenter.Display getTopicSplitPanelRenderedDisplay() {
        return topicSplitPanelRenderedDisplay;
    }

    /**
     * @return The parsed query string
     */
    @Nullable
    protected final String getQueryString() {
        return queryString;
    }

    /**
     * To be set in the overriding classes parseToken() method.
     *
     * @param queryString The parsed query string
     */
    protected final void setQueryString(@Nullable final String queryString) {
        this.queryString = queryString;
    }


    @Override
    public void go(@NotNull final HasWidgets container) {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, getDisplay());
            bindSearchAndEditExtended(ServiceConstants.TOPIC_EDIT_VIEW_CONTENT_TOPIC, HISTORY_TOKEN, queryString);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.go()");
        }
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, final String pageId, @Nullable final String queryString) {
        /* Initialize the other presenters we have pulled in */
        getSearchResultsComponent().bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);
        topicTagsComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicPropertyTagPresenter.bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicSourceURLsPresenter.bindChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);

        /* Display the split panes */
        initializeDisplay();

        bindSplitPanelResize();
        loadSplitPanelSize();

        postBindSearchAndEditExtended(topicId, pageId, queryString);
    }

    abstract protected void postBindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @Nullable final String queryString);

    /**
     * @return true if all the data that is required to be loaded before the first
     *         topic is displayed has been loaded, and false otherwise.
     */
    abstract protected boolean isInitialTopicReadyToBeLoaded();

    /**
     * When the locales and the topic list have been loaded we can display the fisrt topic if only
     * one was returned.
     */
    protected void displayInitialTopic(@NotNull final GetNewEntityCallback<T> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.displayInitialTopic()");

            if (isInitialTopicReadyToBeLoaded() &&
                    getSearchResultsComponent().getProviderData().getItems() != null &&
                    getSearchResultsComponent().getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, getSearchResultsComponent().getProviderData().getItems().get(0));
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.displayInitialTopic()");
        }
    }

    /**
     * Reflect the state of the editor with the XML editor toggle buttons
     */
    private void setXMLEditorButtonsToEditorState() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.setXMLEditorButtonsToEditorState()");

            topicXMLComponent.getDisplay().getLineWrap().setDown(topicXMLComponent.getDisplay().getEditor().getUserWrapMode());
            topicXMLComponent.getDisplay().getShowInvisibles().setDown(topicXMLComponent.getDisplay().getEditor().getShowInvisibles());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.setXMLEditorButtonsToEditorState()");
        }
    }

    /**
     * (Re)Initialize the main display with the rendered view split pane (if selected).
     */
    private void initializeDisplay() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeDisplay()");

            final String savedSplit = Preferences.INSTANCE.getString(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE, "");
            if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE.equals(savedSplit)) {
                split = SplitType.NONE;
            } else if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL.equals(savedSplit)) {
                split = SplitType.VERTICAL;
            } else {
                split = SplitType.HORIZONTAL;
            }

            /* Have to do this after the parseToken method has been called */
            getDisplay().initialize(false, split, topicSplitPanelRenderedDisplay.getPanel());
            enableAndDisableActionButtons(lastDisplayedView);

            loadSplitPanelSize();
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeDisplay()");
        }
    }

    /**
     * Display the usual menus. This is called after the split rendering pane menu has been closed.
     */
    private void showRegularMenu() {
        this.getDisplay().displayChildView(this.lastDisplayedView);
    }

    /**
     * Display the split panel menu, which will remove all common and local action buttons
     */
    private void showRenderedSplitPanelMenu() {
        this.getDisplay().getViewActionButtonsParentPanel().clear();
        this.getDisplay().getViewActionButtonsParentPanel().setWidget(0, 0, this.getDisplay().getRenderedSplitViewMenu());
    }

    /**
     * Load the split panel sizes
     */
    private void loadSplitPanelSize() {
        try {
            this.LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadSplitPanelSize()");

            if (this.split == SplitType.HORIZONTAL) {
                this.getDisplay().getSplitPanel().setSplitPosition(
                        this.topicSplitPanelRenderedDisplay.getPanel().getParent(),
                        Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH,
                                Constants.SPLIT_PANEL_SIZE), false);
            } else if (this.split == SplitType.VERTICAL) {
                this.getDisplay().getSplitPanel().setSplitPosition(
                        this.topicSplitPanelRenderedDisplay.getPanel().getParent(),
                        Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH,
                                Constants.SPLIT_PANEL_SIZE), false);
            }
        } finally {
            this.LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadSplitPanelSize()");
        }
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");

            getDisplay().getSplitPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(final ResizeEvent event) {
                    if (topicXMLComponent.getDisplay().getEditor() != null) {
                        topicXMLComponent.getDisplay().getEditor().redisplay();
                    }

                    /*
                     * Saves the width of the split screen
                     */
                    if (split == SplitType.HORIZONTAL) {
                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH, getDisplay()
                                .getSplitPanel().getSplitPosition(topicSplitPanelRenderedDisplay.getPanel().getParent()) + "");
                    } else if (split == SplitType.VERTICAL) {
                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH, getDisplay()
                                .getSplitPanel().getSplitPosition(topicSplitPanelRenderedDisplay.getPanel().getParent()) + "");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");
        }
    }

    @Override
    protected final void loadAdditionalDisplayedItemData() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");

            preLoadAdditionalDisplayedItemData();

            enableAndDisableActionButtons(lastDisplayedView);

            /* fix the rendered view buttons */
            initializeSplitViewButtons();

            /* Display the property tags that are added to the category */
            Collections.sort(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                    new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
            topicPropertyTagPresenter.refreshExistingChildList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of property tags */
            topicPropertyTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

            /* Display the list of property tags */
            topicSourceURLsPresenter.redisplayPossibleChildList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

            loadTagsAndBugs();

            postLoadAdditionalDisplayedItemData();

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");
        }

    }

    /**
     * Called before loadAdditionalDisplayedItemData() does any processing.
     */
    protected abstract void preLoadAdditionalDisplayedItemData();

    /**
     * Called after loadAdditionalDisplayedItemData() has finished.
     */
    protected abstract void postLoadAdditionalDisplayedItemData();

    /**
     * @return The topic, or topic revision, that is being displayed.
     */
    protected abstract RESTBaseTopicV1<?, ?, ?> getDisplayedTopic();

    /**
     * The tags and bugs for a topic are loaded as separate operations to minimize the amount of data initially sent when a
     * topic is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    protected final void loadTagsAndBugs() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs()");

            /* Initiate the REST calls */
            final Integer id = getDisplayedTopic().getId();
            final Integer revision = getDisplayedTopic().getRevision();

            /* If this is a new topic, the id will be null, and there will not be any tags to get */
            if (id != null) {

                /* A callback to respond to a request for a topic with the tags expanded */
                final RESTCallback<RESTTopicV1> topicWithTagsCallback = new BaseRestCallback<RESTTopicV1, TopicTagsPresenter.Display>(
                        topicTagsComponent.getDisplay(), new BaseRestCallback.SuccessAction<RESTTopicV1, TopicTagsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTTopicV1 retValue, final TopicTagsPresenter.Display display) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");

                            /* copy the revisions into the displayed Topic */
                            getDisplayedTopic().setTags(retValue.getTags());

                            /* update the view */
                            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{topicTagsComponent.getDisplay()}));
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");
                        }
                    }
                });

                RESTCalls.getTopicRevisionWithTags(topicWithTagsCallback, id, revision);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs()");
        }
    }

    /**
     * This method will replace the top action buttons with their disabled labels based on the
     * currently displayed view.
     *
     * @param displayedView the view to be displayed
     */
    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.enableAndDisableActionButtons()");

            this.getDisplay().replaceTopActionButton(this.getDisplay().getXmlDown(), getDisplay().getXml());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getBugsDown(), getDisplay().getBugs());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getExtendedPropertiesDown(), getDisplay().getExtendedProperties());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), getDisplay().getFields());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getRenderedDown(), getDisplay().getRendered());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getTopicTagsDown(), getDisplay().getTopicTags());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getXmlErrorsDown(), getDisplay().getXmlErrors());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getUrlsDown(), getDisplay().getUrls());

            if (displayedView == this.topicXMLComponent.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getXml(), getDisplay().getXmlDown());
            } else if (displayedView == this.topicBugsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getBugs(), getDisplay().getBugsDown());
            } else if (displayedView == this.topicPropertyTagPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getExtendedProperties(), getDisplay().getExtendedPropertiesDown());
            } else if (displayedView == this.topicRenderedPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getRendered(), getDisplay().getRenderedDown());
            } else if (displayedView == this.topicTagsComponent.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getTopicTags(), getDisplay().getTopicTagsDown());
            } else if (displayedView == this.topicXMLErrorsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getXmlErrors(), getDisplay().getXmlErrorsDown());
            } else if (displayedView == this.topicSourceURLsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getUrls(), getDisplay().getUrlsDown());
            }

            if (getDisplayedTopic() != null && getDisplayedTopic().getXmlErrors() != null && !getDisplayedTopic().getXmlErrors().isEmpty()) {
                getDisplay().getXmlErrors().addStyleName(CSSConstants.ERROR);
                getDisplay().getXmlErrorsDown().addStyleName(CSSConstants.ERROR);
            } else {
                getDisplay().getXmlErrors().removeStyleName(CSSConstants.ERROR);
                getDisplay().getXmlErrorsDown().removeStyleName(CSSConstants.ERROR);
            }

            postEnableAndDisableActionButtons(displayedView);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.enableAndDisableActionButtons()");
        }
    }

    /**
     * Enable or disable any additional buttons that were added by the extending class.
     */
    protected abstract void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView);

    /* Update the page name */
    private void updatePageTitle(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.updatePageTitle()");

            final StringBuilder title = new StringBuilder(displayedView.getPageName());
            final String id = getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getId() == null ?
                    PressGangCCMSUI.INSTANCE.New() : getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getId().toString();
            final String displayTitle = getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTitle() == null ?
                    "" : getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getTitle();
            if (this.getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                title.append(": [" + id + "] " + displayTitle);
            }
            getDisplay().getPageTitle().setText(title.toString());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.updatePageTitle()");
        }
    }

    @Override
    protected final void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.afterSwitchView()");

            enableAndDisableActionButtons(displayedView);

            updatePageTitle(displayedView);

            /* Save any changes to the xml editor */
            if (lastDisplayedView == this.topicXMLComponent.getDisplay()) {
                this.topicXMLComponent.getDisplay().getDriver().flush();
            }

            setHelpTopicForView(this, displayedView);

            postAfterSwitchView(displayedView);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.afterSwitchView()");
        }
    }

    protected abstract void postAfterSwitchView(@NotNull final BaseTemplateViewInterface displayedView);

    @Override
    protected void bindActionButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons()");

            final ClickHandler topicPropertyTagsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicPropertyTagPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicSourceUrlsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicSourceURLsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicXMLClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicXMLComponent.getDisplay());

                    }
                }
            };

            final ClickHandler topicRenderedClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicRenderedPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicXMLErrorsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicTagsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicTagsComponent.getDisplay());
                    }
                }
            };

            final ClickHandler topicBugsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicBugsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler splitMenuHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    showRenderedSplitPanelMenu();
                }
            };

            final ClickHandler splitMenuCloseHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    showRegularMenu();
                }
            };

            final ClickHandler splitMenuNoSplitHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE);

                    initializeDisplay();
                    initializeSplitViewButtons();
                }
            };

            final ClickHandler splitMenuVSplitHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL);

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
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL);

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

                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null && isOKToProceed()) {

                        final RESTBaseTopicV1<?, ?, ?> topic = getSearchResultsComponent().getProviderData().getDisplayedItem().getItem();

                        eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                                + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_FILTER_VAR + "="
                                + topic.getTitle() + " [" + topic.getId() + "];tag" + ServiceConstants.CSP_TAG_ID + "=1;logic=AND",
                                GWTUtilities.isEventToOpenNewWindow(event)));
                    }

                }
            };

            /* Hook up the click listeners */
            getDisplay().getRenderedSplit().addClickHandler(splitMenuHandler);
            getDisplay().getExtendedProperties().addClickHandler(topicPropertyTagsClickHandler);
            getDisplay().getXml().addClickHandler(topicXMLClickHandler);
            getDisplay().getRendered().addClickHandler(topicRenderedClickHandler);
            getDisplay().getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
            getDisplay().getTopicTags().addClickHandler(topicTagsClickHandler);
            getDisplay().getBugs().addClickHandler(topicBugsClickHandler);
            getDisplay().getCsps().addClickHandler(cspsHandler);
            getDisplay().getUrls().addClickHandler(topicSourceUrlsClickHandler);

            getDisplay().getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            getDisplay().getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            getDisplay().getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);

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

            postBindActionButtons();
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons()");
        }
    }

    protected abstract void postBindActionButtons();

    @Override
    protected final void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeViews()");

            /**
             * Initialize the views first, as quite often the tables have columns whose
             * values depend on the parent entity set when initialzing the views. This is
             * common for "Add" and "Remove" column buttons that need to know if the
             * entity in the row is in the parent in order to choose between the add and
             * remove labels.
             */

            LOGGER.log(Level.INFO, "\tInitializing topic views");

            /*
                Loop over all the standard view i.e. those that will display details from the selected topic
                or topic revision
            */
            final List<BaseCustomViewInterface<RESTBaseTopicV1<?, ?, ?>>> displayableViews = new ArrayList<BaseCustomViewInterface<RESTBaseTopicV1<?, ?, ?>>>();
            displayableViews.add(topicXMLComponent.getDisplay());
            displayableViews.add(topicXMLErrorsPresenter.getDisplay());
            displayableViews.add(topicTagsComponent.getDisplay());
            displayableViews.add(topicBugsPresenter.getDisplay());
            displayableViews.add(topicPropertyTagPresenter.getDisplay());
            displayableViews.add(topicSourceURLsPresenter.getDisplay());

            final RESTBaseTopicV1<?, ?, ?> topicToDisplay = getDisplayedTopic();
            for (final BaseCustomViewInterface<RESTBaseTopicV1<?, ?, ?>> view : displayableViews) {
                if (viewIsInFilter(filter, view)) {
                    view.display(topicToDisplay, isReadOnlyMode());
                }
            }

            /* We display the rendered view with images */
            if (viewIsInFilter(filter, topicRenderedPresenter.getDisplay())) {
                topicRenderedPresenter.getDisplay().displayTopicRendered(topicToDisplay, isReadOnlyMode(), true);
            }

            /* We initially display the split pane rendered view without images */
            if (viewIsInFilter(filter, topicSplitPanelRenderedDisplay)) {
                topicSplitPanelRenderedDisplay.displayTopicRendered(topicToDisplay, isReadOnlyMode(), true);
            }

            /* Redisplay the editor. topicXMLComponent.getDisplay().getEditor() will be not null after the initialize method was called above */
            if (viewIsInFilter(filter, topicXMLComponent.getDisplay())) {
                LOGGER.log(Level.INFO, "\tSetting topic XML edit button state and redisplaying ACE editor");
                setXMLEditorButtonsToEditorState();
                topicXMLComponent.getDisplay().getEditor().redisplay();
            }

            LOGGER.log(Level.INFO, "\tInitializing topic presenters");

            if (viewIsInFilter(filter, topicPropertyTagPresenter.getDisplay())) {
                topicPropertyTagPresenter.displayDetailedChildrenExtended(topicToDisplay, isReadOnlyMode());
            }

            if (viewIsInFilter(filter, topicSourceURLsPresenter.getDisplay())) {
                topicSourceURLsPresenter.displayChildrenExtended(topicToDisplay, isReadOnlyMode());
            }

            postInitializeViews(filter);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeViews()");
        }

    }

    protected abstract void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter);

    protected abstract boolean isReadOnlyMode();

    private void initializeSplitViewButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeSplitViewButtons()");

            /* fix the rendered view button */
            getDisplay().buildSplitViewButtons(split);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeSplitViewButtons()");
        }
    }

    /**
     * Set the help link topic ids.
     */
    private void setHelpTopicForView(@NotNull final BaseTemplatePresenterInterface component, @NotNull final BaseTemplateViewInterface view) {

        if (view instanceof TopicXMLErrorsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_VALIDATION_ERRORS_TOPIC);
        } else if (view instanceof TopicPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_PROPERTIES_TOPIC);
        } else if (view instanceof TopicTagsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_TAGS_TOPIC);
        } else if (view instanceof TopicRevisionsPresenter.Display) {
            component.setHelpTopicId(ServiceConstants.TOPIC_REVISIONS_TOPIC);
        } else if (view instanceof TopicBIRTBugsPresenter.Display) {
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
    public interface Display<
            T extends RESTBaseEntityV1<T, U, V>,
            U extends RESTBaseCollectionV1<T, U, V>,
            V extends RESTBaseCollectionItemV1<T, U, V>> extends BaseSearchAndEditViewInterface<T, U, V> {

        FlexTable getRenderedSplitViewMenu();

        PushButton getRenderedSplitOpen();

        PushButton getRenderedHorizontalSplit();

        PushButton getRenderedSplitClose();

        PushButton getRenderedVerticalSplit();

        PushButton getRenderedNoSplit();

        PushButton getRenderedSplit();

        PushButton getUrls();

        Label getUrlsDown();

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
}
