package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicSourceURLsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
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
        extends BaseSearchAndEditPresenter<T, U, V, Y>
        implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTopicFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * The global event bus.
     */
    @Inject
    private HandlerManager eventBus;

    /**
     * The query string, which is the full history token minus the HISTORY_TOKEN. Or it is null
     * if we are creating only new topics.
     */
    @Nullable
    private String queryString;

    @Inject
    private TopicXMLPresenter topicXMLComponent;
    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;

    /**
     * The presenter used to display the topic tags.
     */
    @Inject
    private TopicTagsPresenter topicTagsPresenter;
    /**
     * The presenter used to display the topic bugs.
     */
    @Inject
    private TopicBIRTBugsPresenter topicBugsPresenter;
    @Inject
    private TopicRenderedPresenter topicRenderedPresenter;
    /**
     * The presenter used to display the topic property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter topicPropertyTagPresenter;
    /**
     * The presenter used to display the topic's source urls.
     */
    @Inject
    private TopicSourceURLsPresenter topicSourceURLsPresenter;
    /**
     * How the rendering panel is displayed
     */
    @NotNull
    private SplitType split = SplitType.NONE;

    private boolean displayingSearchResults = true;

    public boolean isDisplayingSearchResults() {
        return displayingSearchResults;
    }

    public void setDisplayingSearchResults(final boolean displayingSearchResults) {
        this.displayingSearchResults = displayingSearchResults;
        if (displayingSearchResults) {
            getDisplay().getShowHideSearchResults().getUpFace().setText(PressGangCCMSUI.INSTANCE.HideSearchResults());
        } else {
            getDisplay().getShowHideSearchResults().getUpFace().setText(PressGangCCMSUI.INSTANCE.ShowSearchResults());

        }
    }

    /**
     *
     * @return The view that corresponds to this parent presenter.
     */
    protected abstract Display getDisplay();

    @NotNull
    protected final TopicXMLPresenter getTopicXMLComponent() {
        return topicXMLComponent;
    }

    @NotNull
    protected abstract BaseFilteredResultsPresenter<V> getSearchResultsComponent();

    @NotNull
    protected final TopicTagsPresenter getTopicTagsPresenter() {
        return topicTagsPresenter;
    }

    @NotNull
    protected final CommonExtendedPropertiesPresenter getTopicPropertyTagPresenter() {
        return topicPropertyTagPresenter;
    }


    @NotNull
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
    public void close() {

    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @Nullable final String queryString) {
        /* Initialize the other presenters we have pulled in */
        getSearchResultsComponent().bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);
        topicTagsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicPropertyTagPresenter.bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicSourceURLsPresenter.bindChildrenExtended(ServiceConstants.TOPIC_SOURCE_URLS_HELP_TOPIC, pageId);
        topicXMLComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        /*topicBugsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicRenderedPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicXMLErrorsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId); */

        bindSplitPanelResize();

        postBindSearchAndEditExtended(topicId, pageId, queryString);

        /*
            Display the split panes. This is done after the call to postBindSearchAndEditExtended to ensure that the
            super.bindSearchAndEdit() method has been called. This means that the loadMainSplitResize() method
            can be called.
        */
        initializeDisplay();
    }

    /**
     * Called by bindSearchAndEditExtended(). Overriding classes should perform any additional initialization in this
     * method.
     * @param topicId The help topic for this view
     * @param pageId The id for this view
     * @param queryString The query for this view
     */
    abstract protected void postBindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @Nullable final String queryString);

    /**
     * @return true if all the data that is required to be loaded before the first
     *         topic is displayed has been loaded, and false otherwise.
     */
    abstract protected boolean isInitialTopicReadyToBeLoaded();

    /**
     * When the locales and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    protected void displayInitialTopic(@NotNull final GetNewEntityCallback<T> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");

            if (isInitialTopicReadyToBeLoaded() &&
                    getSearchResultsComponent().getProviderData().getItems() != null &&
                    getSearchResultsComponent().getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, getSearchResultsComponent().getProviderData().getItems().get(0));
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");
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

            int renderedPanelSize = Constants.SPLIT_PANEL_SIZE;
            if (this.split == SplitType.HORIZONTAL) {
                renderedPanelSize = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE);
            } else if (this.split == SplitType.VERTICAL) {
                renderedPanelSize = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE);
            }

            final int searchResultsWidth = Preferences.INSTANCE.getInt(getMainResizePreferencesKey(), Constants.SPLIT_PANEL_SIZE);

            /* Have to do this after the parseToken method has been called */
            getDisplay().initialize(false, split, isDisplayingSearchResults(), topicSplitPanelRenderedDisplay.getPanel(), searchResultsWidth, renderedPanelSize);
            enableAndDisableActionButtons(lastDisplayedView);
            loadMainSplitResize(getMainResizePreferencesKey());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeDisplay()");
        }
    }

    /**
     *
     * @return The key that saves the width of the split between the search results and the topic details
     */
    @NotNull
    protected abstract String getMainResizePreferencesKey();

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
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");

            getDisplay().getSplitPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(@NotNull final ResizeEvent event) {
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
            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");


            /* Display the list of property tags */
            topicSourceURLsPresenter.redisplayPossibleChildList(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem());

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
            this.getDisplay().replaceTopActionButton(this.getDisplay().getUrlsDown(), getDisplay().getUrls());

            if (displayedView == this.topicXMLComponent.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getXml(), getDisplay().getXmlDown());
            } else if (displayedView == this.topicBugsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getBugs(), getDisplay().getBugsDown());
            } else if (displayedView == this.topicPropertyTagPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getExtendedProperties(), getDisplay().getExtendedPropertiesDown());
            } else if (displayedView == this.topicRenderedPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getRendered(), getDisplay().getRenderedDown());
            } else if (displayedView == this.topicTagsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getTopicTags(), getDisplay().getTopicTagsDown());
            } else if (displayedView == this.topicSourceURLsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getUrls(), getDisplay().getUrlsDown());
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

    /**
     *  Update the page name.
     *  @param displayedView the currently displayed view.
     */
    private void updatePageTitle(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.updatePageTitle()");

            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

            final StringBuilder title = new StringBuilder(displayedView.getPageName());
            final StringBuilder id = new StringBuilder(getDisplayedTopic().getId() == null ? PressGangCCMSUI.INSTANCE.New() : getDisplayedTopic().getId().toString());

            /*
                Test to see if we are looking at a specific revision. If so, add the revision to the page title.
             */
            if (getDisplayedTopic().getRevision() != null &&
                    !getDisplayedTopic().getRevision().equals(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem().getRevision())) {
               id.append("-" + getDisplayedTopic().getRevision());
            }

            String displayTitle = getDisplayedTopic().getTitle() == null ? "" : getDisplayedTopic().getTitle();
            if (displayTitle.length() > Constants.MAX_PAGE_TITLE_LENGTH) {
                displayTitle = displayTitle.substring(0, Constants.MAX_PAGE_TITLE_LENGTH - 3) + "...";
            }

            if (this.getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                title.append(": " + displayTitle + " [" + id.toString() + "]");
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
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicPropertyTagPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicSourceUrlsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicSourceURLsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicXMLClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicXMLComponent.getDisplay());

                    }
                }
            };

            final ClickHandler topicRenderedClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicRenderedPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicTagsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicTagsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicBugsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null) {
                        switchView(topicBugsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler splitMenuHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    showRenderedSplitPanelMenu();
                }
            };

            final ClickHandler splitMenuCloseHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    showRegularMenu();
                }
            };

            final ClickHandler splitMenuNoSplitHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE);

                    initializeDisplay();
                    initializeSplitViewButtons();
                }
            };

            final ClickHandler splitMenuVSplitHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
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
                public void onClick(@NotNull final ClickEvent event) {
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

            final ClickHandler showHideSearchResults = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    setDisplayingSearchResults(!isDisplayingSearchResults());
                    initializeDisplay();

                    /*
                        Elements like the ace editor and mergely diff viewer need to get
                        an onResize event so they can be sized appropriately. For reasons that
                        I have not yet worked out, this can only be done after control has been
                        handed back to the browser loop (which is when scheduleDeferred runs).
                     */
                    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                        @Override
                        public void execute() {
                            getDisplay().getSplitPanel().onResize();
                        }
                    });
                }
            };

            final ClickHandler cspsHandler = new ClickHandler() {

                @Override
                public void onClick(@NotNull final ClickEvent event) {

                    if (getSearchResultsComponent().getProviderData().getDisplayedItem() != null && isOKToProceed()) {

                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
                        checkState(getSearchResultsComponent().getProviderData().getDisplayedItem().getItem() != null, "The displayed item need to reference a valid entity");

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
            getDisplay().getTopicTags().addClickHandler(topicTagsClickHandler);
            getDisplay().getBugs().addClickHandler(topicBugsClickHandler);
            getDisplay().getCsps().addClickHandler(cspsHandler);
            getDisplay().getUrls().addClickHandler(topicSourceUrlsClickHandler);

            getDisplay().getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            getDisplay().getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            getDisplay().getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);

            getDisplay().getShowHideSearchResults().addClickHandler(showHideSearchResults);

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
             * values depend on the parent entity set when initializing the views. This is
             * common for "Add" and "Remove" column buttons that need to know if the
             * entity in the row is in the parent in order to choose between the add and
             * remove labels.
             */

            LOGGER.log(Level.INFO, "\tInitializing topic views");

            /*
                Loop over all the standard view i.e. those that will display details from the selected topic
                or topic revision
            */
            final List<BaseCustomViewInterface> displayableViews = new ArrayList<BaseCustomViewInterface>();
            displayableViews.add(topicXMLComponent.getDisplay());
            displayableViews.add(topicTagsPresenter.getDisplay());
            displayableViews.add(topicBugsPresenter.getDisplay());
            displayableViews.add(topicPropertyTagPresenter.getDisplay());
            displayableViews.add(topicSourceURLsPresenter.getDisplay());

            final RESTBaseTopicV1<?, ?, ?> topicToDisplay = getDisplayedTopic();
            for (@NotNull final BaseCustomViewInterface view : displayableViews) {
                if (viewIsInFilter(filter, view)) {
                    view.display(topicToDisplay, isReadOnlyMode());
                }
            }

            /* We display the rendered view with images */
            if (viewIsInFilter(filter, topicRenderedPresenter.getDisplay())) {
                topicRenderedPresenter.getDisplay().displayEditorRendered(getTopicXMLComponent().getDisplay().getEditor());
                topicRenderedPresenter.getDisplay().stop();
            }

            /* We initially display the split pane rendered view without images */
            if (viewIsInFilter(filter, topicSplitPanelRenderedDisplay)) {
                topicSplitPanelRenderedDisplay.displayEditorRendered(getTopicXMLComponent().getDisplay().getEditor());
                topicSplitPanelRenderedDisplay.stop();
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

    /**
     * In order to link the rendered view to the line in the editor where the rendered text is coming from,
     * each element has an attribute pressgangeditorlinenumber added to it to reflect the original line number.
     * This can then be read when an element in the rendered view is clicked.
     * @param topicXML The source XML
     * @return The XML with all the elements having pressgangeditorlinenumber attributes
     */
    @NotNull
    protected String addLineNumberAttributesToXML(@Nullable final String topicXML) {

        final StringBuilder retValue = new StringBuilder();

        if (topicXML != null) {
            /* true if the last line had a hanging cdata start */
            boolean inCData = false;
            int i = 0;
            for (final String line : topicXML.split("\n")) {
                ++i;

                /* true if the last line had a hanging cdata start and we did not find an end in this line */
                boolean lineIsOnlyCData = inCData;

                String fixedLine = line;

                final Map<String, String> endHangingCData = new HashMap<String, String>();
                if (inCData) {
                    final RegExp cdataEndHangingRe = RegExp.compile("^.*?\\]\\]>", "g");
                    final MatchResult matcher = cdataEndHangingRe.exec(fixedLine);
                    if (matcher != null) {
                        int marker = endHangingCData.size();
                        while (fixedLine.indexOf("#CDATAENDPLACEHOLDER" + marker + "#") != -1) {
                            ++marker;
                        }

                        endHangingCData.put("#CDATAENDPLACEHOLDER" + marker + "#", matcher.getGroup(0));

                        inCData = false;

                        /*
                         * We found an end to the hanging cdata start. this lets us know further down that
                         * we do need to process some elements in this line.
                         */
                        lineIsOnlyCData = false;
                    }

                    for (final String marker : endHangingCData.keySet()) {
                        fixedLine = fixedLine.replace(endHangingCData.get(marker), marker);
                    }
                }

                final RegExp cdataRe = RegExp.compile("<!\\[CDATA\\[.*?\\]\\]>", "g");
                final Map<String, String> singleLineCData = new HashMap<String, String>();
                for (MatchResult matcher = cdataRe.exec(line); matcher != null; matcher = cdataRe.exec(line)) {
                    int marker = singleLineCData.size();
                    while (line.indexOf("#CDATAPLACEHOLDER" + marker + "#") != -1) {
                        ++marker;
                    }

                    singleLineCData.put("#CDATAPLACEHOLDER" + marker + "#", matcher.getGroup(0));
                }


                for (final String marker : singleLineCData.keySet()) {
                    fixedLine = fixedLine.replace(singleLineCData.get(marker), marker);
                }

                final Map<String, String> startHangingCData = new HashMap<String, String>();
                if (!inCData) {
                    final RegExp cdataStartHangingRe = RegExp.compile("<!\\[CDATA\\[.*?$", "g");
                    final MatchResult matcher = cdataStartHangingRe.exec(fixedLine);
                    if (matcher != null) {
                        int marker = startHangingCData.size();
                        while (fixedLine.indexOf("#CDATASTARTPLACEHOLDER" + marker + "#") != -1) {
                            ++marker;
                        }

                        startHangingCData.put("#CDATASTARTPLACEHOLDER" + marker + "#", matcher.getGroup(0));

                        inCData = true;
                    }
                }

                for (final String marker : startHangingCData.keySet()) {
                    fixedLine = fixedLine.replace(startHangingCData.get(marker), marker);
                }

                if (!lineIsOnlyCData) {
                    final RegExp elementRe = RegExp.compile("(<[^/!].*?)(/?)(>)", "g");
                    fixedLine = elementRe.replace(fixedLine, "$1 pressgangeditorlinenumber='" + i + "'$2$3");
                }

                for (final String marker : endHangingCData.keySet()) {
                    fixedLine = fixedLine.replace(marker, endHangingCData.get(marker));
                }

                for (final String marker : singleLineCData.keySet()) {
                    fixedLine = fixedLine.replace(marker, singleLineCData.get(marker));
                }

                for (final String marker : startHangingCData.keySet()) {
                    fixedLine = fixedLine.replace(marker, startHangingCData.get(marker));
                }

                retValue.append(fixedLine);
                retValue.append("\n");
            }
        }

        return retValue.toString();
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
        } else if (view instanceof TopicSourceURLsView) {
            component.setHelpTopicId(ServiceConstants.TOPIC_SOURCE_URLS_HELP_TOPIC);
        } else if (view instanceof CommonExtendedPropertiesPresenter) {
            component.setHelpTopicId(ServiceConstants.TOPIC_EXTENDED_PROPERTIES_HELP_TOPIC);
        } else {
            component.setHelpTopicId(ServiceConstants.DEFAULT_HELP_TOPIC);
        }
    }

    /**
     * The presenter used to display the topic's rendered view..
     */
    public TopicRenderedPresenter getTopicRenderedPresenter() {
        return topicRenderedPresenter;
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

        void initialize(final boolean readOnly, final SplitType splitType, final boolean dislaySearchResults, final Panel panel, final int searchResultsWidth, final int renderedPanelSize);

        /**
         * @return The button used to show or hide the search results panel
         */
        @NotNull PushButton getShowHideSearchResults();
    }
}
