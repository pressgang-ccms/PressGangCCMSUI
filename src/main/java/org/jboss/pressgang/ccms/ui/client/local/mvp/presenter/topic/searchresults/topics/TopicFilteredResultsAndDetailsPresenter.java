package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchResultsAndContentSpecViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.RenderedDiffEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicContentSpecsPresenter;
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
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.topic.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.FileUploadExt;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

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
    private FailOverRESTCall failOverRESTCall;

    /*
        True when the XML elements dialog is opened for the first time, and the
        elements are loaded.
     */
    private boolean xmlElementsLoadInitiated = false;
    /*
        True when the XML templates dialog is opened for the first time, and the
        elements are loaded.
     */
    private boolean xmlTemplatesLoadInitiated = false;
    /*
        True when the revisions tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean revisionsLoadInitiated = false;
    /*
        True when the property tags tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean propertyTagsLoadInitiated = false;
    /*
        True when the property tags tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean allPropertyTagsLoadInitiated = false;
    /*
        True when the tags tab or bulk import dialogis opened for the first time, and the
        elements are loaded.
     */
    private boolean allTagsLoadInitiated = false;
    /*
        True when the tags tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean tagsLoadInitiated = false;
    /*
        True when the content specs tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean contentSpecsLoadInitiated = false;

    /**
     * The presenter used to display the topic content specs.
     */
    @Inject
    private TopicContentSpecsPresenter topicContentSpecsPresenter;
    @Inject
    private TopicFilteredResultsPresenter searchResultPresenter;
    @Inject
    private TopicPresenter topicViewPresenter;
    @Inject
    private TopicRevisionsPresenter topicRevisionsPresenter;
    @Inject
    private Display display;

    /**
     * true while there is a thread checking the XML
     */
    private boolean checkingXML = false;
    /**
     * The xmllint worker
     */
    private JavaScriptObject worker = null;
    /**
     * The xml validation timeout
      */
    private JavaScriptObject timeout = null;

    /**
     * The global event bus.
     */
    @Inject
    private EventBus eventBus;


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
            if (lastDisplayedView == getTopicXMLPresenter().getDisplay()) {
                refreshSplitRenderedView(false);
            }
        }
    };

    /**
     * Setup automatic xml validation
     */

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
    private Integer renderedDiffRevision;

    private final Map<Integer, Integer> topicRevisionViewData = new HashMap<Integer, Integer>();

    @NotNull
    @Override
    protected Display getDisplay() {
        return display;
    }

    @NotNull
    @Override
    protected BaseFilteredResultsPresenter<RESTTopicCollectionItemV1> getSearchResultPresenter() {
        return searchResultPresenter;
    }

    @NotNull
    protected TopicContentSpecsPresenter getTopicContentSpecsPresenter() {
        return topicContentSpecsPresenter;
    }

    public TopicFilteredResultsAndDetailsPresenter() {
        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter()");
    }

    @Override
    protected void postBindSearchAndEditExtended(@Nullable final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTopicV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTopicV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                        @Override
                        public void success(@NotNull final RESTTopicV1 retValue) {
                            try {
                                LOGGER.log(Level.INFO,
                                        "ENTER TopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getSourceUrls_OTM() != null,
                                        "The initially retrieved entity should have an expanded source urls collection");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO,
                                        "EXIT TopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    };

                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopic(selectedEntity.getId()), callback, getDisplay());

                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        super.bindSearchAndEdit(getMainResizePreferencesKey(), getTopicXMLPresenter().getDisplay(),
                topicViewPresenter.getDisplay(), getSearchResultPresenter().getDisplay(), getSearchResultPresenter(), getDisplay(),
                getDisplay(), getNewEntityCallback);

        topicContentSpecsPresenter.bindChildrenExtended();
        topicRevisionsPresenter.bindExtended();

        bindTagButtons();

        bindContentSpecViewButtons();

        /* Bind logic to the revisions buttons */
        bindViewTopicRevisionButton();

        bindRenderedViewClicks();

        /* When the topics have been loaded, display the first one */
        getSearchResultPresenter().addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTopicCollectionV1>() {
            @Override
            public void onCollectionReceived(@NotNull final RESTTopicCollectionV1 topics) {
                try {
                    LOGGER.log(Level.INFO,
                            "ENTER TopicFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");

                    topicListLoaded = true;
                    displayInitialTopic(getNewEntityCallback);
                } finally {
                    LOGGER.log(Level.INFO,
                            "EXIT TopicFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");
                }
            }
        });

        FailOverRESTCallDatabase.populateLocales(new StringListLoaded() {
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
        }, display, failOverRESTCall);

        FailOverRESTCallDatabase.loadDefaultLocale(new StringLoaded() {
            @Override
            public void stringLoaded(@NotNull final String string) {
                defaultLocale = string;
                displayNewTopic();
            }
        }, display, failOverRESTCall);

        addKeyboardShortcutEventHandler(this.getTopicXMLPresenter().getDisplay(), this.getDisplay(), new GetCurrentTopic() {

            @Override
            public RESTTopicV1 getCurrentlyEditedTopic() {
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");

                return getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
            }
        });

        buildHelpDatabase();
    }

    /**
     * When the locales and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    protected void displayInitialTopic(@NotNull final GetNewEntityCallback<RESTTopicV1> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");

            checkState(getSearchResultPresenter().getProviderData() != null, "getSearchResultPresenter().getProviderData() should not return null");

            if (isInitialTopicReadyToBeLoaded() &&
                    getSearchResultPresenter().getProviderData().getItems() != null &&
                    getSearchResultPresenter().getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, getSearchResultPresenter().getProviderData().getItems().get(0));
                setSearchResultsVisible(false);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");
        }
    }

    @Override
    public void close() {
        timer.cancel();
        stopCheckingXMLAndCloseThread();
        GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.PressGangCCMS());

        /*
            Allow the child components to close.
         */
        getTopicRenderedPresenter().close();
        getTopicSplitPanelRenderedPresenter().close();
        getSearchResultPresenter().close();
        getTopicPropertyTagPresenter().close();
        getTopicTagsPresenter().close();
        getTopicXMLPresenter().close();
        getTopicContentSpecsPresenter().close();
    }

    private void bindRenderedViewClicks() {
        /*getTopicSplitPanelRenderedDisplay().getDiv().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindRenderedViewClicks() ClickHandler.onClick()");

                    final EventTarget eventTarget = event.getNativeEvent().getEventTarget();
                    if (Element.is(eventTarget)) {

                        final Element sender = eventTarget.cast();

                        final String lineNumber = sender.getAttribute("pressgangeditorlinenumber");
                        if (lineNumber != null) {
                            try {
                                final Integer lineNumberInt = Integer.parseInt(lineNumber);
                                getTopicXMLPresenter().getDisplay().getEditor().gotoLine(lineNumberInt);
                                LOGGER.log(Level.INFO, "Moved to line number " + lineNumberInt);
                            } catch (@NotNull final NumberFormatException ex) {
                                // do nothing
                            }
                        }
                    }

                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindRenderedViewClicks() ClickHandler.onClick()");
                }
            }
        }); */
    }

    private void bindTagButtons() {
        /* Bind the add button in the tags view */
        bindNewTagListBoxes(new AddTagClickHandler(new ReturnCurrentTopic() {
            @NotNull
            @Override
            public RESTTopicV1 getTopic() {
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getTags() != null,
                        "The displayed collection item to reference a valid entity and have a valid tags collection.");
                return getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
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
        }, getTopicTagsPresenter().getDisplay()
        ), getTopicTagsPresenter().getDisplay());

        /* The template is used to hold tags, so we need to populate the tags collection */
        bulkImportTemplate.setTags(new RESTTagCollectionV1());

        /* Bind the add button in the bulk topic import dialog */
        bindNewTagListBoxes(new AddTagClickHandler(new ReturnCurrentTopic() {
            /**
             * @return The topic used as a template when uploading newly imported topics.
             */
            @NotNull
            @Override
            public RESTTopicV1 getTopic() {
                return bulkImportTemplate;
            }
        }, new ReturnReadOnlyMode() {
            /**
             * @return false, because the bulk import dialog is never read only
             */
            @Override
            public boolean getReadOnlyMode() {
                return false;
            }
        }, new BindRemoveButtons() {
            @Override
            public void bindRemoveButtons() {
                bindBulkImportTagEditingButtons();
            }
        }, display.getBulkImport().getTagsView()
        ), display.getBulkImport().getTagsView());
    }

    private void bindContentSpecViewButtons() {
        getTopicContentSpecsPresenter().getDisplay().getPossibleChildrenButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTContentSpecCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, @NotNull final RESTContentSpecCollectionItemV1 object, final String value) {
                        if (isOKToProceed()) {
                            checkState(object != null && object.getItem() != null, "The referenced column should have a valid " +
                                    "content spec reference");

                            eventBus.fireEvent(new ContentSpecSearchResultsAndContentSpecViewEvent(
                                    Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR + "=" +
                                            object.getItem().getId(),
                                    false));
                        }
                    }
                });
    }

    /**
     * Adds event handlers to the new tag combo boxes and add button. Similar to TopicTagsPresenter.bindNewTagListBoxes(),
     * but this version takes the tags display, so we can apply it to the bulk import dialog too.
     *
     * @param clickHandler The Add button click handler
     * @param tagsDisplay  The tags view
     */
    static private void bindNewTagListBoxes(@NotNull final ClickHandler clickHandler,
            @NotNull final TopicTagsPresenter.Display tagsDisplay) {
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
    private void loadAllTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadAllTags()");

            if (!allTagsLoadInitiated) {
                allTagsLoadInitiated = true;

                /* Disable the OK button, and change the text so it is clear something is happening */
                display.getBulkImport().setLoading();

                final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTTagCollectionV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");

                                    checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                                    checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                                    getTopicTagsPresenter().getDisplay().initializeNewTags(retValue);
                                    display.getBulkImport().getTagsView().initializeNewTags(retValue);

                            display.getBulkImport().setLoaded();
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");
                        }
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTags(), callback, getTopicTagsPresenter().getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadAllTags()");
        }
    }

    @NotNull
    @Override
    protected String getMainResizePreferencesKey() {
        return Preferences.TOPIC_VIEW_MAIN_SPLIT_WIDTH;
    }

    @Override
    protected boolean isInitialTopicReadyToBeLoaded() {
        /* only proceed loading the initial topic when the locales and the topic list have been loaded */
        return localesLoaded && topicListLoaded;
    }

    @Override
    protected void preLoadAdditionalDisplayedItemData() {
        final RESTTopicCollectionItemV1 displayedItem = this.getSearchResultPresenter().getProviderData().getDisplayedItem();

        checkState(displayedItem != null, "A collection item should be selected");
        checkState(displayedItem.getItem() != null, "A collection item should be selected and have a valid item");

        if (displayedItem.getItem().getId() != null) {
            GWTUtilities.setBrowserWindowTitle(displayedItem.getItem().getId() + " - " + PressGangCCMSUI.INSTANCE.PressGangCCMS());
        } else {
            GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.New() + " - " + PressGangCCMSUI.INSTANCE.PressGangCCMS());
        }

        /* Disable the topic revision view */
        viewLatestTopicRevision();

        revisionsLoadInitiated = false;
        tagsLoadInitiated = false;
        propertyTagsLoadInitiated = false;
        contentSpecsLoadInitiated = false;
    }

    /**
     * The tags and bugs for a topic are loaded as separate operations to minimize the amount of data initially sent when a
     * topic is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    private void loadTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadTags()");

            final RESTTopicCollectionItemV1 selectedItem = this.getSearchResultPresenter().getProviderData().getSelectedItem();
            final RESTTopicV1 displayedTopic = getDisplayedTopic();

            /* If this is a new topic, the selectedItem will be null, and there will not be any tags to get */
            if (!tagsLoadInitiated && selectedItem != null) {

                tagsLoadInitiated = true;

                /* Initiate the REST calls */
                final Integer id = displayedTopic.getId();
                final Integer revision = displayedTopic.getRevision();

                /* A callback to respond to a request for a topic with the tags expanded */
                 final RESTCallBack<RESTTopicV1> topicWithTagsCallback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadTags() topicWithTagsCallback.doSuccessAction()");

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
                            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadTags() topicWithTagsCallback.doSuccessAction()");
                        }
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevisionWithTags(id, revision), topicWithTagsCallback, getTopicTagsPresenter().getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadTags()");
        }
    }

    /**
     * Called to load a specific revision if the view data included it.
     */
    private void loadTopicRevision() {
        /*
            Check to see if there is any view data associated with this topic
         */

        final RESTTopicCollectionItemV1 selectedItem = this.getSearchResultPresenter().getProviderData().getSelectedItem();

        /*
            If this is a new topic, there will be no selected item.
         */
        if (selectedItem != null) {
            final Integer topicId = selectedItem.getItem().getId();
            if (this.topicRevisionViewData.containsKey(topicId)) {
                final Integer topicRevision = topicRevisionViewData.get(topicId);

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        displayRevision(retValue);

                        /*
                            If the revision presenter has a valid provider data, then it has loaded and displayed
                            the revisions (loading this revision and the general revision list are async processes,
                             so we don't know which will finish fisrt).

                             If that is the case, we need to redisplay the list to reflect the fact that we are displaying
                             a new revision.
                         */
                        if (topicRevisionsPresenter.getProviderData().isValid()) {
                            topicRevisionsPresenter.getDisplay().getProvider().displayAsynchronousList(
                                    topicRevisionsPresenter.getProviderData().getItems(),
                                    topicRevisionsPresenter.getProviderData().getSize(),
                                    topicRevisionsPresenter.getProviderData().getStartRow());
                        }
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevision(topicId, topicRevision), callback, display);
            }
        }
    }

    /**
     * The content specs for a topic are loaded as separate operations to minimize the amount of data initially sent when a
     * topic is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    private void loadContentSpecs() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadContentSpecs()");

            final RESTTopicCollectionItemV1 selectedItem = this.getSearchResultPresenter().getProviderData().getSelectedItem();
            final RESTTopicV1 displayedTopic = getDisplayedTopic();

            /* If this is a new topic, the selectedItem will be null, and there will not be any content specs to get */
            if (!contentSpecsLoadInitiated && selectedItem != null) {
                contentSpecsLoadInitiated = true;

                /* Initiate the REST calls */
                final Integer id = displayedTopic.getId();
                final Integer revision = displayedTopic.getRevision();

                /* A callback to respond to a request for a topic with the content specs expanded */
                final RESTCallBack<RESTTopicV1> topicWithContentSpecsCallback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.loadContentSpecs() " +
                                    "topicWithContentSpecsCallback.doSuccessAction()");

                            // copy the revisions into the displayed Topic
                            getDisplayedTopic().setContentSpecs_OTM(retValue.getContentSpecs_OTM());

                            /* update the view */
                            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{getTopicContentSpecsPresenter().getDisplay()}));
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadContentSpecs() " +
                                    "topicWithContentSpecsCallback.doSuccessAction()");
                        }
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevisionWithContentSpecs(id, revision),
                        topicWithContentSpecsCallback, getTopicContentSpecsPresenter().getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.loadContentSpecs()");
        }
    }

    @Override
    protected void postLoadAdditionalDisplayedItemData() {
        loadTopicRevision();
        getTopicRenderedPresenter().getDisplay().clear();
        getTopicSplitPanelRenderedPresenter().getDisplay().clear();
    }

    /**
     * This method will load the complete list of property tags (and set allPropertyTagsLoadInitiated to true), and load
     * the property tags assigned to the latest revision of the topic (and set propertyTagsLoadInitiated to true).
     */
    private void loadPropertyTags() {
        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null, "There should be a selected collection item.");
        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        final RESTTopicCollectionItemV1 selectedItem = this.getSearchResultPresenter().getProviderData().getSelectedItem();
        final RESTTopicV1 displayedItem = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
        final RESTTopicV1 displayedTopic = getDisplayedTopic();
        // Are we displaying the latest version of the topic i.e. the one that doesn't have its tags loaded?
        final boolean displayingLatest = displayedItem == displayedTopic;

        if (!allPropertyTagsLoadInitiated) {
            allPropertyTagsLoadInitiated = true;

            // Get a new collection of property tags
            getTopicPropertyTagPresenter().refreshPossibleChildrenDataFromRESTAndRedisplayList(displayedItem);
        }

        if (!propertyTagsLoadInitiated && displayingLatest) {
            propertyTagsLoadInitiated = true;

            // if getSearchResultPresenter().getProviderData().getSelectedItem() == null, then we are displaying a new topic
            if (selectedItem != null) {

                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getId() != null,
                        "The displayed collection item to reference a valid entity with a valid ID.");
                checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision() != null,
                        "The displayed collection item to reference a valid entity with a valid revision.");

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        checkArgument(retValue.getProperties().getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getProperties().getSize() != null, "Returned collection should have a valid size.");

                        getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().setProperties(
                                retValue.getProperties());

                        Collections.sort(
                                getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getProperties()
                                        .getItems(),
                                new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());

                        // We have new data to display
                        initializeViews(
                                Arrays.asList(new BaseTemplateViewInterface[]{getTopicPropertyTagPresenter().getDisplay()}));
                    }
                };

                final Integer id = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getId();
                final Integer revision = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision();
                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevisionWithProperties(id, revision), callback, getTopicPropertyTagPresenter().getDisplay());
            }
        }
    }

    /**
     * This is called when the revisions tab is opened for the first time.
     */
    private void loadRevisions() {
        if (!revisionsLoadInitiated) {
            revisionsLoadInitiated = true;

            /* if getSearchResultPresenter().getProviderData().getSelectedItem() == null, then we are displaying a new topic */
            if (this.getSearchResultPresenter().getProviderData().getSelectedItem() != null) {

                checkState(getSearchResultPresenter().getProviderData().getSelectedItem() != null,
                        "There should be a selected collection item.");
                checkState(getSearchResultPresenter().getProviderData().getSelectedItem().getItem() != null,
                        "The selected collection item to reference a valid entity.");
                checkState(getSearchResultPresenter().getProviderData().getSelectedItem().getItem().getId() != null,
                        "The selected collection item to reference a valid entity with a valid ID.");

                this.topicRevisionsPresenter.getDisplay().setProvider(this.topicRevisionsPresenter.generateListProvider(
                        getSearchResultPresenter().getProviderData().getSelectedItem().getItem().getId(), display));
            }
        }
    }

    @Nullable
    @Override
    protected RESTTopicV1 getDisplayedTopic() {
        try {
            //LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.getDisplayedTopic()");

            RESTTopicV1 sourceTopic = null;

            if (topicRevisionsPresenter.getDisplay().getRevisionTopic() != null) {
                sourceTopic = topicRevisionsPresenter.getDisplay().getRevisionTopic();
            } else if (this.getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                sourceTopic = this.getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
            }

            return sourceTopic;
        } finally {
            //LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.getDisplayedTopic()");
        }
    }

    @Override
    protected void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postEnableAndDisableActionButtons()");

            this.getDisplay().replaceTopActionButton(this.getDisplay().getHistoryDown(), this.getDisplay().getHistory());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), this.getDisplay().getFields());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getCspsDown(), getDisplay().getCsps());

            if (displayedView == this.topicViewPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDown());
            } else if (displayedView == this.topicRevisionsPresenter.getDisplay()) {
                this.getDisplay().replaceTopActionButton(this.getDisplay().getHistory(), this.getDisplay().getHistoryDown());
            } else if (displayedView == getTopicContentSpecsPresenter().getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getCsps(), getDisplay().getCspsDown());
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


            if (displayedView == this.topicRevisionsPresenter.getDisplay()) {
                /*
                    Load the initial set of revisions.
                */
                loadRevisions();
            } else {
                /*
                    Otherwise switch back to the view of revisions.
                */
                this.topicRevisionsPresenter.getDisplay().displayRevisions();
            }

            /* Set the projects combo box as the focused element */
            if (displayedView == this.getTopicTagsPresenter().getDisplay() && getTopicTagsPresenter().getDisplay().getProjectsList()
                    .isAttached()) {

                loadTags();
                loadAllTags();
                getTopicTagsPresenter().getDisplay().getProjectsList().getElement().focus();
            }


            if (displayedView == this.getTopicContentSpecsPresenter().getDisplay()) {
                loadContentSpecs();
            }

            if (displayedView == this.getTopicPropertyTagPresenter().getDisplay()) {
                loadPropertyTags();
            }

                /* While editing the XML, we need to setup a refresh of the rendered view */
            if (displayedView == this.getTopicXMLPresenter().getDisplay()) {
                if (this.getDisplay().getSplitType() != SplitType.NONE && !isReadOnlyMode()) {
                    timer.scheduleRepeating(Constants.REFRESH_RATE);
                }

                /* This should always be false */
                if (!checkingXML) {
                    startCheckingXML();
                }
            } else {
                timer.cancel();
                refreshSplitRenderedView(true);
                stopCheckingXML();
            }

            if (displayedView == getTopicRenderedPresenter().getDisplay()) {
                refreshRenderedView();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.postAfterSwitchView()");
        }
    }

    @Override
    protected void postBindActionButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postBindActionButtons()");

            topicRevisionsPresenter.getDisplay().getDone().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getDisplayedTopic().getRevision() == getSearchResultPresenter().getProviderData().getDisplayedItem().getItem()
                            .getRevision()) {
                        checkState(topicRevisionsPresenter.getDisplay().getMergely() != null, "mergely should not be null");
                        final String lhs = topicRevisionsPresenter.getDisplay().getMergely().getLhs();
                        getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().setXml(lhs);
                        initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{getTopicXMLPresenter().getDisplay()}));
                    }
                    topicRevisionsPresenter.getDisplay().displayRevisions();
                    getDisplay().getSave().setEnabled(!isReadOnlyMode());

                }
            });

            topicRevisionsPresenter.getDisplay().getHTMLDone().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    topicRevisionsPresenter.getDisplay().displayRevisions();
                    getDisplay().getSave().setEnabled(!isReadOnlyMode());

                }
            });

            topicRevisionsPresenter.getDisplay().getHtmlOpenDiff().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    final String query = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getId() + ";" +
                        getRenderedDiffRevision() + ";" +
                        getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision();

                    eventBus.fireEvent(new RenderedDiffEvent(query,GWTUtilities.isEventToOpenNewWindow(event)));
                }
            });

            topicRevisionsPresenter.getDisplay().getCancel().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    topicRevisionsPresenter.getDisplay().displayRevisions();
                    getDisplay().getSave().setEnabled(!isReadOnlyMode());

                }
            });

            /* Build up a click handler to save the topic */
            final ClickHandler saveClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {

                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() saveClickHandler.onClick()");

                        if (hasUnsavedChanges()) {
                            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                                    "There should be a displayed collection item.");
                            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                                    "The displayed collection item to reference a valid entity.");

                            /*
                                Default to using the major change for new topics
                             */
                            if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null && getSearchResultPresenter()
                                    .getProviderData().getDisplayedItem().returnIsAddItem()) {
                                display.getMessageLogDialog().getMajorChange().setValue(true);
                                display.getMessageLogDialog().getMessage().setValue(PressGangCCMSUI.INSTANCE.InitialTopicCreation());
                            }

                            display.getMessageLogDialog().getUsername().setText(
                                    Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));

                            display.getMessageLogDialog().getDialogBox().center();
                            display.getMessageLogDialog().getDialogBox().show();
                        } else {
                            Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                        }
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() saveClickHandler.onClick()");
                    }
                }
            };

            final ClickHandler messageLogDialogOK = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    final String user = display.getMessageLogDialog().getUsername().getText().trim();

                    if (user.isEmpty()) {
                        Window.alert(PressGangCCMSUI.INSTANCE.UsernameMissing());
                        return;
                    }

                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                        if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {

                            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                                    "There should be a displayed collection item.");
                            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                                    "The displayed collection item to reference a valid entity.");



                            Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                            final StringBuilder message = new StringBuilder();
                            if (!user.isEmpty()) {
                                message.append(user).append(": ");
                            }
                            message.append(display.getMessageLogDialog().getMessage().getText());
                            final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants
                                    .MINOR_CHANGE : ServiceConstants.MAJOR_CHANGE);

                            /* Sync any changes back to the underlying object */
                            flushChanges();

                             /*
                             * Create a new instance of the topic, and copy out any updated, added or deleted fields. We don't
                             * do a clone or send the original object here because a full object will send back a whole lot of
                             * data that was never modified, wasting bandwidth, and chewing up CPU cycles as Errai serializes
                             * the data into JSON.
                             */
                            final RESTTopicV1 sourceTopic = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();

                            final RESTTopicV1 newTopic = new RESTTopicV1();

                            /*
                                Only assign those modified children to the topic that is to be added/updated
                            */
                            LOGGER.log(Level.INFO, "Copying modified collections");
                            if (sourceTopic.getProperties() != null && sourceTopic.getProperties().getItems() != null) {
                                newTopic.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                                newTopic.getProperties().setItems(
                                        sourceTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems());
                            }

                            if (sourceTopic.getSourceUrls_OTM() != null && sourceTopic.getSourceUrls_OTM().getItems() != null) {
                                newTopic.explicitSetSourceUrls_OTM(new RESTTopicSourceUrlCollectionV1());
                                newTopic.getSourceUrls_OTM().setItems(
                                        sourceTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems());
                            }

                            if (sourceTopic.getTags() != null && sourceTopic.getTags().getItems() != null) {
                                newTopic.explicitSetTags(new RESTTagCollectionV1());
                                newTopic.getTags().setItems(sourceTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems());
                            }

                            /*
                                Assume all the text fields have been updated
                            */
                            LOGGER.log(Level.INFO, "Copying modified fields");
                            newTopic.setId(sourceTopic.getId());
                            newTopic.explicitSetDescription(sourceTopic.getDescription());
                            newTopic.explicitSetLocale(sourceTopic.getLocale());
                            newTopic.explicitSetTitle(sourceTopic.getTitle());
                            newTopic.explicitSetXml(sourceTopic.getXml());

                            if (getSearchResultPresenter().getProviderData().getDisplayedItem().returnIsAddItem()) {

                                final RESTCallBack<RESTTopicV1> addCallback = new RESTCallBack<RESTTopicV1>() {
                                    @Override
                                    public void success(@NotNull final RESTTopicV1 retValue) {
                                        try {
                                            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");

                                            // Create the topic wrapper
                                            final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
                                            topicCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                            // create the topic, and add to the wrapper
                                            topicCollectionItem.setItem(retValue);

                                            /* Update the displayed topic */
                                            getSearchResultPresenter().getProviderData().setDisplayedItem(topicCollectionItem.clone(true));

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
                                            getSearchResultPresenter().setSelectedItem(topicCollectionItem);

                                            lastXML = null;

                                            if (startWithNewTopic) {
                                                LOGGER.log(Level.INFO, "Adding new topic to static list");
                                                getSearchResultPresenter().getProviderData().getItems().add(topicCollectionItem);
                                                getSearchResultPresenter().getProviderData().setSize(getSearchResultPresenter().getProviderData().getItems().size());
                                                updateDisplayWithNewEntityData(false);
                                            } else {
                                                /* Update the selected topic */
                                                LOGGER.log(Level.INFO, "Redisplaying query");
                                                updateDisplayWithNewEntityData(true);
                                            }

                                            LOGGER.log(Level.INFO, "Refreshing editor");
                                            if (getTopicXMLPresenter().getDisplay().getEditor() != null) {
                                                getTopicXMLPresenter().getDisplay().getEditor().redisplay();
                                                /*
                                                    This forces the xml validation to rehighlight the invalid rows
                                                 */
                                                getTopicXMLPresenter().getDisplay().getXmlErrors().setText("");
                                            }

                                            Window.alert(PressGangCCMSUI.INSTANCE.TopicSaveSuccessWithID() + " " + retValue.getId());
                                        } finally {
                                            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - New Topic");
                                        }
                                    }

                                    @Override
                                    public void failed() {
                                        getTopicXMLPresenter().getDisplay().getEditor().redisplay();
                                        /*
                                            This forces the xml validation to rehighlight the invalid rows
                                         */
                                        getTopicXMLPresenter().getDisplay().getXmlErrors().setText("");
                                    }
                                };

                                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createTopic(newTopic, message.toString(), flag, ServiceConstants.NULL_USER_ID.toString()), addCallback, display);
                            } else {

                                final RESTCallBack<RESTTopicV1> updateCallback = new RESTCallBack<RESTTopicV1>() {
                                    @Override
                                    public void success(@NotNull final RESTTopicV1 retValue) {
                                        try {
                                            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                            boolean overwroteChanges = false;
                                            final Integer originalRevision = getSearchResultPresenter().getProviderData().getSelectedItem().getItem().getRevision();

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
                                            retValue.cloneInto(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem(), true);
                                            /* Update the selected topic */
                                            retValue.cloneInto(getSearchResultPresenter().getProviderData().getSelectedItem().getItem(), true);

                                            lastXML = null;

                                            updateDisplayWithNewEntityData(false);

                                            if (overwroteChanges) {
                                                /* Take the user to the revisions view so they can review any overwritten changes */
                                                switchView(topicRevisionsPresenter.getDisplay());
                                                Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                            } else {
                                                Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                            }
                                        } finally {
                                            LOGGER.log(Level.INFO,
                                                    "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick() addCallback.doSuccessAction() - Existing Topic");

                                            if (getTopicXMLPresenter().getDisplay().getEditor() != null) {
                                                getTopicXMLPresenter().getDisplay().getEditor().redisplay();
                                                        /*
                                                            This forces the xml validation to rehighlight the invalid rows
                                                         */
                                                getTopicXMLPresenter().getDisplay().getXmlErrors().setText("");
                                            }
                                        }
                                    }

                                    @Override
                                    public void failed() {
                                        getTopicXMLPresenter().getDisplay().getEditor().redisplay();
                                        /*
                                            This forces the xml validation to rehighlight the invalid rows
                                         */
                                        getTopicXMLPresenter().getDisplay().getXmlErrors().setText("");
                                    }
                                };


                                failOverRESTCall.performRESTCall(
                                        FailOverRESTCallDatabase.saveTopic(
                                                newTopic,
                                                message.toString(),
                                                flag,
                                                ServiceConstants.NULL_USER_ID.toString()),
                                        updateCallback,
                                        display);
                            }
                        }
                    } finally {
                        display.getMessageLogDialog().reset();
                        display.getMessageLogDialog().getDialogBox().hide();

                        LOGGER.log(Level.INFO,
                                "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
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
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicViewPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicRevisionsClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicRevisionsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler bulkImport = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (!hasUnsavedChanges()) {
                        loadAllTags();
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
                    try {
                        display.getBulkImport().getTagsView().getDriver().flush();

                        if (display.getBulkImport().getFiles().getFiles().getLength() != 0) {
                            createNewTopic(false, 0, display.getBulkImport().getFiles().getFiles(), new ArrayList<Integer>(),
                                    new ArrayList<String>(), display.getBulkImport().getCommitMessage().getText());
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
                    try {
                        if (display.getBulkOverwrite().getFiles().getFiles().getLength() != 0) {
                            createNewTopic(true, 0, display.getBulkOverwrite().getFiles().getFiles(), new ArrayList<Integer>(),
                                    new ArrayList<String>(), display.getBulkOverwrite().getCommitMessage().getText());
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

            final ClickHandler cspsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(getTopicContentSpecsPresenter().getDisplay());
                    }
                }
            };

            getDisplay().getCsps().addClickHandler(cspsClickHandler);

            display.getMessageLogDialog().getOk().addClickHandler(messageLogDialogOK);
            display.getMessageLogDialog().getCancel().addClickHandler(messageLogDialogCancel);

            display.getBulkImport().getOK().addClickHandler(bulkImportOK);
            display.getBulkImport().getCancel().addClickHandler(bulkImportCancel);

            display.getBulkOverwrite().getOK().addClickHandler(bulkOverwriteOK);
            display.getBulkOverwrite().getCancel().addClickHandler(bulkOverwriteCancel);

            searchResultPresenter.getDisplay().getBulkImport().addClickHandler(bulkImport);
            searchResultPresenter.getDisplay().getBulkOverwrite().addClickHandler(bulkOverwrite);
            getDisplay().getSave().addClickHandler(saveClickHandler);
            getDisplay().getHistory().addClickHandler(topicRevisionsClickHandler);
            getDisplay().getFields().addClickHandler(topicViewClickHandler);

            addKeyboardShortcutEvents(getTopicXMLPresenter().getDisplay(), display);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.postBindActionButtons()");
        }
    }

    /**
     * @param overwrite   true if files named 123.xml (where 123 is the topic id) should overwrite existing topics
     * @param index       the current file being processed
     * @param files       the list of files to process
     * @param ids         the ids of the topics that have been modified
     * @param failedFiled the file names of files that were not processed
     * @param logMessage  The message to use when overwriting topics
     */
    private void createNewTopic(final boolean overwrite, final int index, @NotNull final FileList files, @NotNull final List<Integer> ids,
            @NotNull final List<String> failedFiled, @NotNull final String logMessage) {
        if (index >= files.getLength()) {

            final StringBuilder message = new StringBuilder();

            if (failedFiled.size() == 0) {
                message.append(PressGangCCMSUI.INSTANCE.TopicsUploadedSuccessfully());
            } else {
                final StringBuilder failedNames = new StringBuilder();
                for (final String name : failedFiled) {
                    if (!failedNames.toString().isEmpty()) {
                        failedNames.append(",");
                    }
                    failedNames.append(name);
                }

                message.append(PressGangCCMSUI.INSTANCE.TopicsNotUploadedSuccessfully() + ": " + failedNames.toString());

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

                eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                        Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + idsQuery.toString(),
                        false));
            } else if (startWithNewTopic) {
                /*
                    If this view was started by the Create Topic link in the menu (as opposed to a search),
                    then the new topics will just show up.
                 */
                updateDisplayWithNewEntityData(false);
            } else {
                updateDisplayWithNewEntityData(true);
            }

        } else {
            display.addWaitOperation();

            final FileReader reader = new FileReader();

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
                            newTopic.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());

                            /* save the original file name */
                            final RESTAssignedPropertyTagV1 originalFileName = new RESTAssignedPropertyTagV1();
                            originalFileName.setValue(files.getItem(index).getName());
                            originalFileName.setId(ServiceConstants.ORIGINAL_FILE_NAME_PROPERTY_TAG);

                            newTopic.getProperties().addNewItem(originalFileName);
                        }

                        final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                            @Override
                            public void success(@NotNull final RESTTopicV1 retValue) {
                                ids.add(retValue.getId());

                                /*
                                    If we are working with a collection of new topics, add anything uploaded to
                                    that list.
                                 */
                                if (startWithNewTopic) {
                                    final RESTTopicCollectionItemV1 topicCollectionItem = new RESTTopicCollectionItemV1();
                                    topicCollectionItem.setItem(retValue);
                                    topicCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                    getSearchResultPresenter().getProviderData().getItems().add(topicCollectionItem);
                                    getSearchResultPresenter().getProviderData().setSize(getSearchResultPresenter().getProviderData().getItems().size());
                                }

                                createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                            }

                            @Override
                            public void failed() {
                                failedFiled.add(files.getItem(index).getName());
                                createNewTopic(overwrite, index + 1, files, ids, failedFiled, logMessage);
                            }
                        };

                        if (overwrite) {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.saveTopic(newTopic, logMessage, (int) ServiceConstants.MAJOR_CHANGE, ServiceConstants.NULL_USER_ID.toString()), callback, display);
                        } else {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createTopic(newTopic, logMessage, (int) ServiceConstants.MAJOR_CHANGE, ServiceConstants.NULL_USER_ID.toString()), callback, display);
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

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    private void startCheckingXML() {
        checkingXML = true;
        checkXML();
    }

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    private void stopCheckingXML() {
        checkingXML = false;
    }

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    private native void stopCheckingXMLAndCloseThread() /*-{
		try {
            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::checkingXML = false;

            if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::timeout != null) {
                $wnd.clearTimeout(this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::timeout);
                this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::timeout = null;
            }

            if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker != null) {
                this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker.terminate();
                this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker = null;
            }
        } catch (ex) {
            console.log(ex);
            throw ex;
        }
	}-*/;

    private native void checkXML() /*-{
        var displayComponent = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::getTopicXMLPresenter()();
		var display = displayComponent.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter::getDisplay()();

		var entities = @org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD::getDtdDoctype()();


        if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker == null) {
			this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker = new Worker('javascript/xmllint/xmllint.js');
			this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker.addEventListener('message', function (me) {
				return function (e) {
					var editor = display.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display::getEditor()();
					var errors = display.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display::getXmlErrors()();
					var strings = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;
					var noXmlErrors = strings.@org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::NoXMLErrors()();

					var theseErrors = e.data;
					var oldErrors = errors.@com.google.gwt.user.client.ui.TextArea::getText()();

                    if (theseErrors == "" && oldErrors == "") {
						errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(noXmlErrors);
                    } else if (oldErrors != theseErrors) {
						var entitiesLines = entities.indexOf("\n") == -1 ? 0 : entities.match(/\n/g).length;

                        // "Document topic.xml does not validate against docbook45.dtd" is a standard part of the error
						// message, and is removed before being displayed.
						var errorMessage = theseErrors.replace("\nDocument topic.xml does not validate against docbook45.dtd", "");

						var errorLineRegex = /topic\.xml:(\d+):/g;
						var match = null;
						var lineNumbers = [];
						while (match = errorLineRegex.exec(theseErrors)) {
							if (match.length >= 1) {
                                var line = parseInt(match[1]) - entitiesLines - 1;
								// We need to match the line numbers in the editor with those in the topic, which
                                // means subtracting all the entities we added during validation.
								errorMessage = errorMessage.replace("topic.xml:" + match[1], "topic.xml:" + line);
								var found = false;
								for (var i = 0, lineNumbersLength = lineNumbers.length; i < lineNumbersLength; ++i) {
									if (lineNumbers[i] == line) {
										found = true;
										break;
									}
								}
								if (!found) {
									lineNumbers.push(line);
								}
							}
						}

						if (errorMessage.length == 0) {
							errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(noXmlErrors);
						} else {
							errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(errorMessage);
						}

						if (editor != null) {
							editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::clearAndAddGutterDecoration([ILjava/lang/String;)(lineNumbers, "xmlerror");
						}
					}

					me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::checkXML()();
				}
			}(this),
			false);
		}

		if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::checkingXML) {
			var editor = display.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display::getEditor()();
			if (editor != null) {
                var text = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                // Add the doctype that include the standard docbook entities
                text = entities + @org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities::removeAllPreabmle(Ljava/lang/String;)(text);
                if (text == this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker.lastXML) {
                    this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::timeout = $wnd.setTimeout(function(me) {
                        return function(){

                            me.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::checkXML()();
						};
					}(this), 250);
                } else {
				    var dtd = @org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD::getDtd()();
                    if (dtd != "") {
                        this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker.lastXML = text;
                        this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter::worker.postMessage({xml: text, schema: dtd});
                    }
                }
			}
		}
	}-*/;

    @Override
    protected void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.postInitializeViews()");

            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");

            if (viewIsInFilter(filter, topicViewPresenter.getDisplay())) {
                topicViewPresenter.getDisplay().displayTopicDetails(this.getDisplayedTopic(), isReadOnlyMode(), locales);
            }

            /*
                refresh the list of assigned property tags
             */
            if (viewIsInFilter(filter, getTopicPropertyTagPresenter().getDisplay())) {
                getTopicPropertyTagPresenter().refreshExistingChildList(getDisplayedTopic());
            }

            /*
                refresh the list of content specs
             */
            if (viewIsInFilter(filter, getTopicContentSpecsPresenter().getDisplay())) {
                getTopicContentSpecsPresenter().getDisplay().display(getDisplayedTopic(), isReadOnlyMode());
                getTopicContentSpecsPresenter().displayChildrenExtended(getDisplayedTopic(), isReadOnlyMode());
            }

            /*
                The revision display always displays details from the main topic, and not the selected revision.
            */
            if (viewIsInFilter(filter, topicRevisionsPresenter.getDisplay())) {
                LOGGER.log(Level.INFO, "\tInitializing topic revisions view");
                topicRevisionsPresenter.getDisplay().display(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem(),
                        isReadOnlyMode());
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
        return this.topicRevisionsPresenter.getDisplay().getRevisionTopic() != null;
    }

    @Override
    protected void bindFilteredResultsButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
            getSearchResultPresenter().getDisplay().getCreate().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    createNewTopic();
                }
            });

            searchResultPresenter.getDisplay().getAtomFeed().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    Window.open(ServerDetails.getSavedServer().getRestEndpoint() + "/1/topics/get/atom/" + getQueryString() + "?expand=%7B%22branches%22%3A%5B%7B%22trunk%22%3A%7B%22name%22%3A%20%22topics%22%7D%7D%5D%7D", "", "");
                }
            });

        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.parseToken()");

            String queryString = removeHistoryToken(historyToken, TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN);
            setQueryString(queryString);

            if (queryString.startsWith(Constants.CREATE_PATH_SEGMENT_PREFIX)) {
                startWithNewTopic = true;
                setQueryString(null);
            } else {
                if (queryString.startsWith(Constants.TOPIC_VIEW_DATA_PREFIX)) {

                    /*
                        Topic view data is in the form of:
                        topicViewData;1234=r:321234

                        In a the full url, this would look like:
                        http://localhost:8080/pressgang-ccms-ui/#SearchResultsAndTopicView;topicViewData;1234=r:321234;query;topicIds=1234

                        Where 1234 is the topic id, r: indicates that a particular revision should be displayed, and 321234
                         is the revision.

                         In future more view data could be added with a comma separated list like  maybe
                         topicViewData;1234=r:321234,v:Properties
                     */

                    topicRevisionViewData.clear();

                    final String topicViewDataRegex = Constants.TOPIC_VIEW_DATA_PREFIX + "(.*?)(;" + Constants.QUERY_PATH_SEGMENT_PREFIX
                            + ")";

                    final RegExp regExp = RegExp.compile(topicViewDataRegex);
                    final MatchResult matcher = regExp.exec(queryString);

                    if (matcher != null && matcher.getGroupCount() >= 2) {

                        for (int i = 0; i < matcher.getGroupCount(); ++i) {
                            LOGGER.log(Level.INFO, matcher.getGroup(i));
                        }

                        final String topicViewData = matcher.getGroup(1);

                        final String[] topicViewDataElements = topicViewData.split(";");
                        for (int i = 0; i < topicViewDataElements.length; ++i) {
                            final String[] details = topicViewDataElements[i].split("=");
                            if (details.length == 2) {
                                try {
                                    final Integer topicId = Integer.parseInt(details[0]);
                                    final String[] settings = details[1].split(",");

                                    for (@NotNull final String setting : settings) {
                                        /* A directive to display a particular revision of a topic */
                                        if (setting.startsWith(Constants.REVISION_TOPIC_VIEW_DATA_PREFIX)) {
                                            topicRevisionViewData.put(topicId,
                                                    Integer.parseInt(setting.replaceFirst(Constants.REVISION_TOPIC_VIEW_DATA_PREFIX, "")));
                                        }
                                    }
                                } catch (@NotNull final NumberFormatException ex) {
                                    // A badly formed url. Ignore this.
                                }
                            }
                        }

                        queryString = queryString.replaceFirst(Constants.TOPIC_VIEW_DATA_PREFIX + topicViewData + ";", "");
                    }
                }

                if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                    /* Make sure that the query string has at least the prefix */
                    setQueryString(Constants.QUERY_PATH_SEGMENT_PREFIX);
                }
            }

        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.parseToken()");
        }
    }

    /**
     * When the default locale and the topic list have been loaded we can display the first topic if only
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
    private void refreshSplitRenderedView(final boolean forceExternalImages) {
        try {
            //LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.refreshSplitRenderedView()");

            try {
                getTopicXMLPresenter().getDisplay().getDriver().flush();
            } catch (@NotNull final IllegalStateException ex) {
                LOGGER.log(Level.WARNING,
                        "getTopicXMLPresenter().getDisplay().getDriver().flush() threw an IllegalStateException. This probably happened "
                                + "because the rendered view was refreshed before the XML editor was bound.");
            }

            if (this.getDisplayedTopic() != null) {
                final boolean xmlHasChanges = lastXML == null || !lastXML.equals(this.getDisplayedTopic().getXml());

                if (xmlHasChanges) {
                    lastXMLChange = System.currentTimeMillis();
                }

                final Boolean timeToDisplayImage = forceExternalImages || System.currentTimeMillis() - lastXMLChange >= Constants
                        .REFRESH_RATE_WTH_IMAGES;

                if (xmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
                    isDisplayingImage = timeToDisplayImage;
                    getTopicSplitPanelRenderedPresenter().displayTopicRendered(
                            addLineNumberAttributesToXML(GWTUtilities.removeAllPreabmle(this.getDisplayedTopic().getXml())), isReadOnlyMode(), isDisplayingImage);
                }

                lastXML = this.getDisplayedTopic().getXml();
            }
        } finally {
            //LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.refreshSplitRenderedView()");
        }
    }

    private void refreshRenderedView() {
        getTopicRenderedPresenter().displayTopicRendered(this.getDisplayedTopic().getXml(), isReadOnlyMode(), true);
    }

    /**
     * Check to see if any changes were made while in the merge view. If so, prompt the user to confirm that they
     * don't want to keep the changes.
     *
     * @param displayedView The newly displayed screen.
     * @return true if the view switch should go ahead, and false otherwise
     */
    protected boolean beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        /*
            When switching from the revisions view, make sure there are no unsaved changes.
         */
        if (displayedView != topicRevisionsPresenter.getDisplay() &&
                lastDisplayedView == topicRevisionsPresenter.getDisplay() &&
                !topicRevisionsPresenter.getDisplay().isDisplayingRevisions()) {

            checkState(getDisplayedTopic() != null, "A topic or revision should be displayed.");
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null, "A topic should be displayed.");

            if (getDisplayedTopic().getRevision() == getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision()) {
                if (topicRevisionsPresenter.getDisplay().getMergely() != null &&
                        !topicRevisionsPresenter.getDisplay().getMergely().getLhs().equals(getDisplayedTopic().getXml())) {
                    return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
                }
            }

            /*
                If the user moved away from the revisions screen, return to the revision list. This is a safety net
                in case the rendered diff never rendered one or more of the revisions, as it will remove the spinner.
             */
            topicRevisionsPresenter.getDisplay().displayRevisions();
        }

        flushChanges();
        return true;
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
            if (lastDisplayedView == getTopicTagsPresenter().getDisplay()) {
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

            for (@NotNull final TopicTagViewProjectEditor topicTagViewProjectEditor : getTopicTagsPresenter().getDisplay().getEditor()
                    .projects.getEditors()) {

                checkState(topicTagViewProjectEditor.categories != null && topicTagViewProjectEditor.categories.getEditors() != null,
                        "The project's categories editor collection should be valid");

                for (@NotNull final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories
                        .getEditors()) {

                    checkState(topicTagViewCategoryEditor.myTags != null && topicTagViewCategoryEditor.myTags.getEditors() != null,
                            "The category's tag editor collection should be valid");

                    for (@NotNull final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {

                        checkState(topicTagViewTagEditor.getTag() != null, "The tag editor should point to a valid tag ui data POJO.");
                        checkState(topicTagViewTagEditor.getTag().getTag() != null,
                                "The tag editor should point to a valid tag ui data POJO, which should reference a valid tag entity.");

                        topicTagViewTagEditor.getDelete().addClickHandler(
                                new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag(), new ReturnCurrentTopic() {
                                    @NotNull
                                    @Override
                                    public RESTTopicV1 getTopic() {

                                        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                                                "There should be a displayed collection item.");
                                        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                                                "The displayed collection item to reference a valid entity.");
                                        checkState(
                                                getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getTags() !=
                                                        null,
                                                "The displayed collection item to reference a valid entity and have a valid tags " +
                                                        "collection.");

                                        return getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
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
                                }, getTopicTagsPresenter().getDisplay()
                                ));
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindTagEditingButtons()");
        }
    }

    /**
     * Add behaviour to the bulk import tag delete buttons.
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

            for (@NotNull final TopicTagViewProjectEditor topicTagViewProjectEditor : display.getBulkImport().getTagsView().getEditor()
                    .projects.getEditors()) {

                checkState(topicTagViewProjectEditor.categories != null && topicTagViewProjectEditor.categories.getEditors() != null,
                        "The project's categories editor collection should be valid");

                for (@NotNull final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories
                        .getEditors()) {

                    checkState(topicTagViewCategoryEditor.myTags != null && topicTagViewCategoryEditor.myTags.getEditors() != null,
                            "The category's tag editor collection should be valid");

                    for (@NotNull final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                        checkState(topicTagViewTagEditor.getTag() != null, "The tag editor should point to a valid tag ui data POJO.");
                        checkState(topicTagViewTagEditor.getTag().getTag() != null,
                                "The tag editor should point to a valid tag ui data POJO, which should reference a valid tag entity.");

                        topicTagViewTagEditor.getDelete().addClickHandler(
                                new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag(), new ReturnCurrentTopic() {
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
                                }, display.getBulkImport().getTagsView()
                                ));
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

            topicRevisionsPresenter.getDisplay().getDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicCollectionItemV1 revisionTopic, final String value) {
                    topicRevisionsPresenter.getDisplay().setButtonsEnabled(false);

                    final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                        @Override
                        public void success(@NotNull final RESTTopicV1 retValue) {
                            checkState(getDisplayedTopic() != null, "There should be a displayed item.");

                            /*
                                It is possible to switch away from the view while this request was loading. If we
                                have done so, don't show the merge view.
                             */
                            if (lastDisplayedView == topicRevisionsPresenter.getDisplay()) {
                                final boolean rhsReadonly = getDisplayedTopic().getRevision() != getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision();

                                topicRevisionsPresenter.getDisplay().displayDiff(retValue.getXml(), rhsReadonly, getDisplayedTopic().getXml());

                                /*
                                    We can't save while merging.
                                 */
                                getDisplay().getSave().setEnabled(false);
                            }

                            topicRevisionsPresenter.getDisplay().setButtonsEnabled(true);
                        }
                    };



                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevision(revisionTopic.getItem().getId(),
                            revisionTopic.getItem().getRevision()), callback, topicRevisionsPresenter.getDisplay());

                }
            });

            topicRevisionsPresenter.getDisplay().getViewButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");

                        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                                "There should be a displayed collection item.");
                        checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                                "The displayed collection item to reference a valid entity.");
                        checkState(getDisplayedTopic() != null, "There should be a displayed item.");

                        displayRevision(revisionTopic.getItem());

                        topicRevisionsPresenter.getDisplay().getProvider().displayAsynchronousList(
                                topicRevisionsPresenter.getProviderData().getItems(), topicRevisionsPresenter.getProviderData().getSize(),
                                topicRevisionsPresenter.getProviderData().getStartRow());
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");
                    }
                }
            });

            topicRevisionsPresenter.getDisplay().getHTMLDiffButton().setFieldUpdater(new FieldUpdater<RESTTopicCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicCollectionItemV1 revisionTopic, final String value) {

                    topicRevisionsPresenter.getDisplay().setButtonsEnabled(false);

                    final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                        @Override
                        public void success(@NotNull final RESTTopicV1 retValue) {
                            checkState(getDisplayedTopic() != null, "There should be a displayed item.");

                            final String xml1 = Constants.DOCBOOK_DIFF_XSL_REFERENCE + "\n" + retValue.getXml();

                            failOverRESTCall.performRESTCall(
                                    FailOverRESTCallDatabase.holdXML(xml1),
                                    new RESTCallBack<IntegerWrapper>() {
                                        public void success(@NotNull final IntegerWrapper value1) {
                                            final String xml2 = Constants.DOCBOOK_DIFF_XSL_REFERENCE + "\n" + getDisplayedTopic().getXml();

                                            failOverRESTCall.performRESTCall(
                                                    FailOverRESTCallDatabase.holdXML(xml2),
                                                    new RESTCallBack<IntegerWrapper>() {
                                                        public void success(@NotNull final IntegerWrapper value2) {
                                                            topicRevisionsPresenter.renderXML(value1.value, value2.value, display.getHiddenAttachmentArea());
                                                            topicRevisionsPresenter.getDisplay().setButtonsEnabled(true);
                                                        }
                                                    },
                                                    topicRevisionsPresenter.getDisplay(),
                                                    true
                                            );
                                        }
                                    },
                                    topicRevisionsPresenter.getDisplay(),
                                    true
                            );
                        }
                    };

                    /*
                        Make a note of the revision for the click handler.
                    */
                    setRenderedDiffRevision(revisionTopic.getItem().getRevision());

                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicRevision(revisionTopic.getItem().getId(),
                            revisionTopic.getItem().getRevision()), callback, topicRevisionsPresenter.getDisplay());

                }
            });


        } finally {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton()");
        }
    }

    /**
     * Displays a revision of a topic. This can be called when a revision is selected to be displayed, or if
     * a particular revision is set as the default to be displayed.
     *
     * @param revisionTopic The revision to be displayed.
     */
    private void displayRevision(@NotNull final RESTTopicV1 revisionTopic) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.displayRevision()");

            /* Reset the reference to the revision topic */
            viewLatestTopicRevision();

            if (!revisionTopic.getRevision().equals(
                    getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision())) {
                /* Reset the reference to the revision topic */
                topicRevisionsPresenter.getDisplay().setRevisionTopic(revisionTopic);
            }

            /* Initialize the views with the new topic being displayed */
            initializeViews();

            /* Load the tags and bugs */
            tagsLoadInitiated = false;
            loadTags();

            // Load the content specs
            contentSpecsLoadInitiated = false;
            loadContentSpecs();

            /* Display the revisions view (which will also update buttons like Save) */
            switchView(topicRevisionsPresenter.getDisplay());

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.displayRevision()");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");

            /* No topic selected, so no changes need to be saved */
            if (this.getSearchResultPresenter().getProviderData().getDisplayedItem() == null) {
                return false;
            }

            /* Save any pending changes */
            flushChanges();

            final RESTTopicV1 displayedTopic = this.getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();

             /*
                If there are any modified tags in newTopic, we have unsaved changes.
                If getTags() is null, the tags have not been loaded yet (and can't have been modified).
            */
            if (displayedTopic.getTags() != null && !displayedTopic.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }

            /* If there are any modified property tags in newTopic, we have unsaved changes */
            if (displayedTopic.getProperties() != null && !displayedTopic.getProperties().returnDeletedAddedAndUpdatedCollectionItems()
                    .isEmpty()) {
                return true;
            }

            /* If there are any modified source urls in newTopic, we have unsaved changes */
            if (!displayedTopic.getSourceUrls_OTM().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }


            if (this.getSearchResultPresenter().getProviderData().getSelectedItem() == null) {

                /* if there is no selected item, we are trying to save a new topic */

                if (displayedTopic.getXml() != null && !displayedTopic.getXml().trim().isEmpty()) {
                    return true;
                }

                if (displayedTopic.getTitle() != null && !displayedTopic.getTitle().trim().isEmpty()) {
                    return true;
                }

                if (displayedTopic.getLocale() != null && !displayedTopic.getLocale().equals(defaultLocale)) {
                    return true;
                }

            } else {

                /* compare the displayed topics to the selected topic */

                final RESTTopicV1 selectedTopic = this.getSearchResultPresenter().getProviderData().getSelectedItem().getItem();

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
     * topicRevisionsPresenter.getDisplay().getRevisionTopic() is used to indicate which revision
     * of a topic we are looking at. Setting it to null means that we are not looking at a revision.
     */
    private void viewLatestTopicRevision() {
        topicRevisionsPresenter.getDisplay().setRevisionTopic(null);
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

            /*
                Set the initial XML from a template defined in a string constant.
             */
             final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
                @Override
                public void success(@NotNull final RESTStringConstantV1 retValue) {
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
                    restTopic.setXml(retValue.getValue());
                    topicCollectionItem.setItem(restTopic);

                    // the topic won't show up in the list of topics until it is saved, so the
                    // selected item is null
                    getSearchResultPresenter().setSelectedItem(null);

                    // the new topic is being displayed though, so we set the displayed item
                    getSearchResultPresenter().getProviderData().setDisplayedItem(topicCollectionItem);

                    updateViewsAfterNewEntityLoaded();
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getStringConstant(ServiceConstants.BASIC_TOPIC_TEMPLATE_STRING_CONSTANT_ID), callback, display);


        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.createNewTopic()");
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

            final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
                @Override
                public void success(@NotNull final RESTStringConstantV1 retValue) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.populateXMLElements() callback.doSuccessAction()");

                        /* Get the list of locales from the StringConstant */
                        @NotNull final List<String> xmlElements = new LinkedList<String>(Arrays.asList(
                                retValue.getValue().replaceAll(Constants.CARRIAGE_RETURN_AND_LINE_BREAK, Constants.LINE_BREAK).replaceAll(
                                        " ", "").split(Constants.LINE_BREAK)));

                        /* Clean the list */
                        while (xmlElements.contains("")) {
                            xmlElements.remove("");
                        }

                        Collections.sort(xmlElements);

                        loadedCallback.stringListLoaded(xmlElements);
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT TopicFilteredResultsAndDetailsPresenter.populateXMLElements() callback.doSuccessAction()");
                    }
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getStringConstant(ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID), callback, display);
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
        final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
            @Override
            public void success(@NotNull final RESTStringConstantV1 retValue) {

                /* Get the list of template string constant ids from the StringConstant */
                final Set<String> xmlElements = new HashSet<String>(Arrays.asList(GWTUtilities.fixUpIdSearchString(
                        retValue.getValue()).split(Constants.COMMA)));
                final Map<String, String> data = new TreeMap<String, String>();

                /* work around the inability to modify an int from an anonymous class */
                final int[] counter = new int[]{0};

                for (final String id : xmlElements) {
                    try {
                        final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
                            @Override
                            public void success(@NotNull final RESTStringConstantV1 retValue) {
                                ++counter[0];

                                data.put(retValue.getName(), retValue.getValue());

                                /*
                                 * If this was the last call, return the data to the callee.
                                 */
                                if (counter[0] == xmlElements.size()) {
                                    loadedCallback.stringMapLoaded(data);
                                }
                            }

                            @Override
                            public void failed() {
                                ++counter[0];
                                if (counter[0] == xmlElements.size()) {
                                    loadedCallback.stringMapLoaded(data);
                                }
                            }
                        };

                        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getStringConstant(Integer.parseInt(id)), callback, waitDisplay);
                    } catch (@NotNull final NumberFormatException ex) {
                        // this should not happen if GWTUtilities.fixUpIdSearchString() does its job properly
                    }
                }
            }
        };

        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getStringConstant(ServiceConstants.DOCBOOK_TEMPLATES_STRING_CONSTANT_ID), callback, waitDisplay);
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

                            if (!xmlElementsLoadInitiated) {
                                xmlElementsLoadInitiated = true;

                                topicXMLDisplay.getXmlTagsDialog().setSuggestions(new ArrayList<String>() {{
                                    add(PressGangCCMSUI.INSTANCE.Loading());
                                }});
                                topicXMLDisplay.getXmlTagsDialog().getOK().setEnabled(false);

                                populateXMLElements(null, new StringListLoaded() {
                                    @Override
                                    public void stringListLoaded(final List<String> xmlElements) {
                                        topicXMLDisplay.getXmlTagsDialog().setSuggestions(xmlElements);
                                        topicXMLDisplay.getXmlTagsDialog().getOK().setEnabled(true);
                                    }
                                });
                            }

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

                            if (!xmlTemplatesLoadInitiated) {
                                xmlTemplatesLoadInitiated = true;

                                topicXMLDisplay.getXmlTemplatesDialog().setSuggestions(new HashMap<String, String>() {{
                                    put(PressGangCCMSUI.INSTANCE.Loading(), "");
                                }});
                                topicXMLDisplay.getXmlTemplatesDialog().getOK().setEnabled(false);
                                populateXMLTemplates(null, new StringMapLoaded() {

                                    @Override
                                    public void stringMapLoaded(final Map<String, String> stringMap) {
                                        topicXMLDisplay.getXmlTemplatesDialog().setSuggestions(stringMap);
                                        topicXMLDisplay.getXmlTemplatesDialog().getOK().setEnabled(true);
                                    }
                                });
                            }

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
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'T') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (display.getTopLevelPanel().isAttached() && topicXMLDisplay.isViewShown()
                                    && !isAnyDialogBoxesOpen(topicXMLDisplay)) {
                                String selection = topicXMLDisplay.getEditor().getSelection();
                                if (selection != null) {
                                    selection = selection.replaceAll("&", "&amp;");
                                    selection = selection.replaceAll("'", "&apos;");
                                    selection = selection.replaceAll("\"", "&quot;");
                                    selection = selection.replaceAll("<", "&lt;");
                                    selection = selection.replaceAll(">", "&gt;");
                                    topicXMLDisplay.getEditor().replaceSelection(selection);
                                }
                            }
                        }
                    });
                }

            }
        };

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
                    Window.open(Constants.DOCBOOK_ELEMENT_URL_PREFIX + value + Constants.DOCBOOK_ELEMENT_URL_POSTFIX, "_blank", "");
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

            final RESTCallBack<RESTTopicCollectionV1> callback = new RESTCallBack<RESTTopicCollectionV1>() {
                @Override
                public void success(@NotNull final RESTTopicCollectionV1 retValue) {
                        final StringBuilder details = new StringBuilder();
                        for (@NotNull final RESTTopicCollectionItemV1 topicCollectionItem : retValue.getItems()) {
                            final RESTTopicV1 topic = topicCollectionItem.getItem();
                            if (!details.toString().isEmpty()) {
                                details.append("\n");
                            }
                            details.append(topic.getTitle() + " [" + topic.getId() + "]");
                        }

                        topicXMLDisplay.getEditor().insertText(details.toString());

                }

                @Override
                public void failed() {
                    display.removeWaitOperation();
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopicsFromQuery(Constants.QUERY_PATH_SEGMENT_PREFIX
                    + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ids), callback, display);

            hideCspDetailsDialogBox(topicXMLDisplay);
        }

    }

    /**
     * Assign help info to the UI elements exposed by this presenter.
     */
    private void buildHelpDatabase() {
        setDataAttribute(getDisplay().getHistory(), ServiceConstants.HELP_TOPICS.TOPIC_REVISIONS.getId());
        setDataAttribute(getDisplay().getHistoryDown(), ServiceConstants.HELP_TOPICS.TOPIC_REVISIONS.getId());
        setDataAttribute(getDisplay().getSave(), ServiceConstants.HELP_TOPICS.TOPIC_SAVE.getId());
        setDataAttribute(getDisplay().getSave(), ServiceConstants.HELP_TOPICS.TOPIC_SAVE.getId());
        setDataAttribute(getDisplay().getCsps(), ServiceConstants.HELP_TOPICS.TOPIC_CONTENT_SPECS.getId());
        setDataAttribute(getDisplay().getCspsDown(), ServiceConstants.HELP_TOPICS.TOPIC_CONTENT_SPECS.getId());

        /*
            Property ui elements
        */
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().titleEditor(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_TITLE.getId());
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().getRestTopicDetails(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_REST_ENDPOINT.getId());
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().getRestTopicXML(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_REST_XML_ENDPOINT.getId());
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().getRestTopicWebDav(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_WEBDAV_URL.getId());
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().descriptionEditor(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_DESCRIPTION.getId());
        setDataAttribute(topicViewPresenter.getDisplay().getEditor().localeEditor(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTY_LOCALE.getId());

        setDataAttribute(getTopicPropertyTagPresenter().getDisplay().getPossibleChildrenResultsPanel(), ServiceConstants.HELP_TOPICS.TOPIC_AVAILABLE_EXTENDED_PROPERTIES.getId());
        setDataAttribute(getTopicPropertyTagPresenter().getDisplay().getExistingChildrenResultsPanel(), ServiceConstants.HELP_TOPICS.TOPIC_EXISTING_EXTENDED_PROPERTIES.getId());

        setDataAttribute(searchResultPresenter.getDisplay().getCreate(), ServiceConstants.HELP_TOPICS.TOPIC_CREATE_TOPIC.getId());
        setDataAttribute(searchResultPresenter.getDisplay().getBulkImport(), ServiceConstants.HELP_TOPICS.BULK_TOPIC_IMPORT.getId());
        setDataAttribute(searchResultPresenter.getDisplay().getBulkOverwrite(), ServiceConstants.HELP_TOPICS.BULK_TOPIC_OVERWRITE.getId());
        setDataAttribute(searchResultPresenter.getDisplay().getAtomFeed(), ServiceConstants.HELP_TOPICS.TOPIC_ATOM_FEED.getId());

        setDataAttribute(topicRevisionsPresenter.getDisplay().getSearchResultsPanel(), ServiceConstants.HELP_TOPICS.TOPIC_REVISION_TABLE.getId());
        setDataAttribute(topicRevisionsPresenter.getDisplay().getDone(), ServiceConstants.HELP_TOPICS.DIFF_DONE.getId());
        setDataAttribute(topicRevisionsPresenter.getDisplay().getCancel(), ServiceConstants.HELP_TOPICS.DIFF_CANCEL.getId());
        setDataAttribute(topicRevisionsPresenter.getDisplay().getHTMLDone(), ServiceConstants.HELP_TOPICS.RENDERED_DIFF_DONE.getId());
        setDataAttribute(topicRevisionsPresenter.getDisplay().getHtmlOpenDiff(), ServiceConstants.HELP_TOPICS.RENDERED_DIFF_NEW_WINDOW.getId());
        setDataAttribute(topicRevisionsPresenter.getDisplay().getDiffParent(), ServiceConstants.HELP_TOPICS.TOPIC_DIFF_PANE.getId());
    }

    /**
     * The revision that was used to generate the rendered diff
      */
    private Integer getRenderedDiffRevision() {
        return renderedDiffRevision;
    }

    private void setRenderedDiffRevision(@Nullable final Integer renderedDiffRevision) {
        this.renderedDiffRevision = renderedDiffRevision;
    }

    private interface ReturnCurrentTopic {
        @NotNull
        RESTTopicV1 getTopic();
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
         * @param tagDisplay         The display that the callback is modifying
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
            final Collection<RESTCategoryInTagCollectionItemV1> mutuallyExclusiveCategories = Collections2.filter(
                    selectedTag.getCategories().getItems(), new Predicate<RESTCategoryInTagCollectionItemV1>() {

                @Override
                public boolean apply(final @Nullable RESTCategoryInTagCollectionItemV1 arg0) {
                    if (arg0 == null || arg0.getItem() == null) {
                        return false;
                    }
                    return arg0.getItem().getMutuallyExclusive();
                }
            });

            /* Find existing tags that belong to any of the mutually exclusive categories */
            final Collection<RESTTagCollectionItemV1> conflictingTags = Collections2.filter(
                    returnCurrentTopic.getTopic().getTags().getItems(), new Predicate<RESTTagCollectionItemV1>() {

                @Override
                public boolean apply(final @Nullable RESTTagCollectionItemV1 existingTag) {

                            /* there is no match if the tag has already been removed */
                    if (existingTag == null || existingTag.getItem() == null || RESTBaseCollectionItemV1.REMOVE_STATE.equals(
                            existingTag.getState())) {
                        return false;
                    }

                            /* loop over the categories that the tag belongs to */
                    return Iterables.any(existingTag.getItem().getCategories().getItems(),
                            new Predicate<RESTCategoryInTagCollectionItemV1>() {

                                @Override
                                public boolean apply(final @Nullable RESTCategoryInTagCollectionItemV1 existingTagCategory) {
                                    if (existingTagCategory == null || existingTagCategory.getItem() == null) {
                                        return false;
                                    }

                                            /*
                                             * match any categories that the tag belongs to with any of the mutually exclusive
                                             * categories
                                             */
                                    return Iterables.any(mutuallyExclusiveCategories, new Predicate<RESTCategoryInTagCollectionItemV1>() {

                                        @Override
                                        public boolean apply(final @Nullable RESTCategoryInTagCollectionItemV1 mutuallyExclusiveCategory) {
                                            return mutuallyExclusiveCategory.getItem().getId().equals(
                                                    existingTagCategory.getItem().getId());
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
         * @param tagDisplay         The display that the callback is modifying
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
        @NotNull
        PushButton getSave();

        @NotNull
        PushButton getHistory();

        @NotNull
        Label getHistoryDown();

        @NotNull
        BulkImport getBulkImport();

        @NotNull
        BulkOverwrite getBulkOverwrite();

        /**
         * @return The button this is used show csps
         */
        PushButton getCsps();

        /**
         * @return The label this is used to show csps
         */
        Label getCspsDown();
    }

    /**
     * Defines the dialog box used to bulk overwrite topics.
     */
    public interface BulkOverwrite {
        @NotNull
        DialogBox getDialog();

        @NotNull
        FileUploadExt getFiles();

        @NotNull
        PushButton getOK();

        @NotNull
        PushButton getCancel();

        @NotNull
        TextArea getCommitMessage();
    }

    /**
     * Defines the dialog box used to bulk upload topics.
     */
    public interface BulkImport extends BulkOverwrite {
        @NotNull
        TopicTagsPresenter.Display getTagsView();

        void setLoading();

        void setLoaded();

    }
}
