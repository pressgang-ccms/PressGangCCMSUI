package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.ServerSettings;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicAdditionalXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.topic.RESTTranslatedTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTranslatedTopicV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EntityUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Extends the BaseTopicFilteredResultsAndDetailsPresenter class to provide the functionality required to
 * display translated topics.
 */
@Dependent
public class TranslatedTopicFilteredResultsAndDetailsPresenter extends BaseTopicFilteredResultsAndDetailsPresenter<RESTTranslatedTopicV1,
        RESTTranslatedTopicCollectionV1, RESTTranslatedTopicCollectionItemV1, RESTTranslatedTopicV1BasicDetailsEditor> {
    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "TranslatedTopicResultsAndTranslatedTopicView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TranslatedTopicFilteredResultsAndDetailsPresenter.class.getName());

    @Inject
    private FailOverRESTCall failOverRESTCall;
    @Inject
    private ServerSettings serverSettings;

    /**
     * The main view.
     */
    @Inject
    private Display display;

    @Inject
    private TranslatedTopicPresenter translatedTopicPresenter;
    @Inject
    private TranslatedTopicsFilteredResultsPresenter translatedTopicsFilteredResultsPresenter;
    @Inject
    private TranslatedTopicAdditionalXMLPresenter translatedTopicAdditionalXMLPresenter;
    @Inject
    private TranslatedTopicRenderedPresenter translatedTopicRenderedPresenter;
    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TranslatedTopicRenderedPresenter translatedTopicSplitPanelRenderedPresenter;

    /**
     * A list of locales retrieved from the server
     */
    private List<String> locales;

    private XMLValidator xmlValidator;

    /**
     * The last xml that was rendered
     */
    @Nullable
    private String lastXML;
    /**
     * The last xml that was rendered
     */
    @Nullable
    private String lastAdditionalXML;
    /**
     * How long it has been since the xml changes
     */
    private long lastXMLChange;
    /**
     * False if we are not displaying external images in the current rendered view, and true otherwise
     */
    private boolean isDisplayingImage;

    /**
     * The REST callback called when a topic is updated
     */
    private final RESTCallBack<RESTTranslatedTopicV1> updateCallback = new RESTCallBack<RESTTranslatedTopicV1>() {
        @Override
        public void success(@NotNull final RESTTranslatedTopicV1 retValue) {
            try {
                LOGGER.log(Level.INFO, "ENTER RESTCallBack.success()");

                final RESTTranslatedTopicV1 displayedTopic = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();
                final RESTTranslatedTopicV1 selectedTopic = getSearchResultPresenter().getProviderData().getSelectedItem().getItem();

                boolean overwroteChanges = false;
                final Integer originalRevision = getSearchResultPresenter().getProviderData().getSelectedItem().getItem().getRevision();

                if (retValue.getRevisions() != null && retValue.getRevisions().getItems() != null) {
                    Collections.sort(retValue.getRevisions().getItems(), new RESTTranslatedTopicCollectionItemV1RevisionSort());

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
                        final Integer overwriteRevision = retValue.getRevisions().getItems().get(
                                retValue.getRevisions().getItems().size() - 1).getItem().getRevision();

                        LOGGER.log(Level.INFO, "originalRevision: " + originalRevision + " new revision: " + overwriteRevision);

                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                    }

                    /*
                        Otherwise we need to make sure that the second last revision matches the revision of the topic we were editing.
                     */
                    if (overwroteChanges && retValue.getRevisions().getItems().size() >= 2) {
                                                            /* Get the second last revision (the last one is the current one) */
                        final Integer overwriteRevision = retValue.getRevisions().getItems().get(
                                retValue.getRevisions().getItems().size() - 2).getItem().getRevision();

                        LOGGER.log(Level.INFO, "originalRevision: " + originalRevision + " last revision: " + overwriteRevision);

                        /*
                         * if the second last revision doesn't match the revision of the topic when editing was
                         * started, then we have overwritten someone elses changes
                         */
                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                    }
                }

                /* Update the displayed topic */
                retValue.cloneInto(displayedTopic, true);
                /* Update the selected topic */
                retValue.cloneInto(selectedTopic, true);

                /*
                    The XML will have been reformatted, so we need to reset this variable to
                    trigger a fresh validation.
                 */
                lastXML = null;
                lastAdditionalXML = null;

                updateDisplayWithNewEntityData(false);

                if (overwroteChanges) {
                    Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                }
            } finally {
                LOGGER.log(Level.INFO, "EXIT RESTCallBack.success()");

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

    /**
     * The click handler that saves changes to the topic.
     */
    private final ClickHandler messageLogDialogOK = new ClickHandler() {
        @Override
        public void onClick(@NotNull final ClickEvent event) {
            final String user = display.getMessageLogDialog().getUsername().getText().trim();

            if (user.isEmpty()) {
                Window.alert(PressGangCCMSUI.INSTANCE.UsernameMissing());
                return;
            }

            try {
                LOGGER.log(Level.INFO, "ENTER messageLogDialogOK.onClick()");

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
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? RESTLogDetailsV1
                            .MINOR_CHANGE_FLAG_BIT : RESTLogDetailsV1.MAJOR_CHANGE_FLAG_BIT);

                    /* Sync any changes back to the underlying object */
                    flushChanges();

                     /*
                     * Create a new instance of the topic, and copy out any updated, added or deleted fields. We don't
                     * do a clone or send the original object here because a full object will send back a whole lot of
                     * data that was never modified, wasting bandwidth, and chewing up CPU cycles as Errai serializes
                     * the data into JSON.
                     */
                    final RESTTranslatedTopicV1 sourceTopic = getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();

                    final RESTTranslatedTopicV1 newTopic = new RESTTranslatedTopicV1();

                    /*
                        Assume all the text fields have been updated
                    */
                    LOGGER.log(Level.INFO, "Copying modified fields");
                    newTopic.setId(sourceTopic.getId());
                    newTopic.setTopicId(sourceTopic.getTopicId());
                    newTopic.setTopicRevision(sourceTopic.getTopicRevision());
                    newTopic.explicitSetTranslatedAdditionalXML(sourceTopic.getTranslatedAdditionalXML());

                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.saveTranslatedTopic(newTopic, message.toString(), flag,
                            serverSettings.getEntities().getUnknownUserId().toString()), updateCallback, display);
                }
            } finally {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();

                LOGGER.log(Level.INFO, "EXIT messageLogDialogOK.onClick()");
            }
        }
    };

    /**
     * Setup automatic flushing and rendering.
     */
    final Timer timer = new Timer() {
        @Override
        public void run() {
            if (lastDisplayedView == translatedTopicAdditionalXMLPresenter.getDisplay()) {
                refreshSplitRenderedView(false);
            }
        }
    };

    /**
     * @return The display.
     */
    @Override
    protected Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        super.go(container);

        getTopicXMLPresenter().getDisplay().getXmlErrors().removeFromParent();
    }

    @Override
    public void close() {
        super.close();
        timer.cancel();
        getXmlValidator().stopCheckingXMLAndCloseThread();

        translatedTopicAdditionalXMLPresenter.close();
    }

    @NotNull
    @Override
    protected BaseFilteredResultsPresenter<RESTTranslatedTopicCollectionItemV1> getSearchResultPresenter() {
        return translatedTopicsFilteredResultsPresenter;
    }

    @Override
    @NotNull
    protected TranslatedTopicRenderedPresenter getTopicRenderedPresenter() {
        return translatedTopicRenderedPresenter;
    }

    @Override
    @NotNull
    protected TranslatedTopicRenderedPresenter getTopicSplitPanelRenderedPresenter() {
        return translatedTopicSplitPanelRenderedPresenter;
    }

    @Override
    protected void postBindSearchAndEditExtended(@Nullable final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTTranslatedTopicV1> getNewEntityCallback = new GetNewEntityCallback<RESTTranslatedTopicV1>
                () {

            @Override
            public void getNewEntity(@NotNull final RESTTranslatedTopicV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTTranslatedTopicV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO,
                            "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");

                    final RESTCallBack<RESTTranslatedTopicV1> callback = new RESTCallBack<RESTTranslatedTopicV1>() {
                        @Override
                        public void success(@NotNull final RESTTranslatedTopicV1 retValue) {
                            try {
                                LOGGER.log(Level.INFO,
                                        "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getSourceUrls_OTM() != null,
                                        "The initially retrieved entity should have an expanded source urls collection");
                                checkArgument(retValue.getProperties() != null,
                                        "The initially retrieved entity should have an expanded properties collection");
                                checkArgument(retValue.getRevisions() != null,
                                        "The initially retrieved entity should have an expanded revisions collection");
                                checkArgument(retValue.getTags() != null,
                                        "The initially retrieved entity should have an expanded tags collection");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO,
                                        "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() RESTCallback.doSuccessAction()");
                            }
                        }
                    };

                    failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTranslatedTopic(selectedEntity.getId()), callback,
                            getDisplay());
                } finally {
                    LOGGER.log(Level.INFO,
                            "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bind() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        bindSearchAndEdit(getMainResizePreferencesKey(), getTopicXMLPresenter().getDisplay(), translatedTopicPresenter.getDisplay(),
                getSearchResultPresenter().getDisplay(), getSearchResultPresenter(), getDisplay(), getDisplay(), getNewEntityCallback);

        translatedTopicAdditionalXMLPresenter.bindExtended();

        /* When the topics have been loaded, display the first one */
        getSearchResultPresenter().addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTranslatedTopicCollectionV1>() {
            @Override
            public void onCollectionReceived(@NotNull final RESTTranslatedTopicCollectionV1 topics) {
                displayInitialTopic(getNewEntityCallback);
            }
        });

        bindSplitPanelResize();
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");

            getDisplay().getSplitPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(@NotNull final ResizeEvent event) {
                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize() ResizeHandler.onResize()");

                        if (translatedTopicAdditionalXMLPresenter.getDisplay().getEditor() != null) {
                            translatedTopicAdditionalXMLPresenter.getDisplay().getEditor().redisplay();
                        }
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize() ResizeHandler.onResize()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");
        }
    }

    /**
     * When the locales and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    protected void displayInitialTopic(@NotNull final GetNewEntityCallback<RESTTranslatedTopicV1> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");

            checkState(getSearchResultPresenter().getProviderData() != null,
                    "getSearchResultPresenter().getProviderData() should not return null");

            if (isInitialTopicReadyToBeLoaded() &&
                    getSearchResultPresenter().getProviderData().getItems() != null &&
                    getSearchResultPresenter().getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, getSearchResultPresenter().getProviderData().getItems().get(0));
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");
        }
    }

    @NotNull
    @Override
    protected String getMainResizePreferencesKey() {
        return Preferences.TRANSLATED_TOPIC_VIEW_MAIN_SPLIT_WIDTH;
    }

    @Override
    protected boolean isInitialTopicReadyToBeLoaded() {
        /* displayInitialContentSpec() is only called when all the data is available, so just return true */
        return true;
    }

    @Override
    protected void preLoadAdditionalDisplayedItemData() {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    protected void postLoadAdditionalDisplayedItemData() {

        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.postLoadAdditionalDisplayedItemData()");

            this.getTopicPropertyTagPresenter().refreshExistingChildList(
                    getSearchResultPresenter().getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of property tags */
            this.getTopicPropertyTagPresenter().refreshPossibleChildrenDataFromRESTAndRedisplayList(
                    getSearchResultPresenter().getProviderData().getDisplayedItem().getItem());

            Collections.sort(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                    new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.postLoadAdditionalDisplayedItemData()");
        }
    }

    @Nullable
    @Override
    protected RESTTranslatedTopicV1 getDisplayedTopic() {
        final RESTTranslatedTopicCollectionItemV1 displayedItem = this.getSearchResultPresenter().getProviderData().getDisplayedItem();
        return displayedItem == null ? null : displayedItem.getItem();
    }

    @Override
    protected void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        getDisplay().removeTopActionButton(getDisplay().getAdditionalXML());
        getDisplay().removeTopActionButton(getDisplay().getAdditionalXMLDown());
        getDisplay().replaceTopActionButton(getDisplay().getFieldsDown(), getDisplay().getFields());
        getDisplay().replaceTopActionButton(getDisplay().getAdditionalXMLDown(), getDisplay().getAdditionalXML());

        if (isExtendedTopic()) {
            if (displayedView == getTopicXMLPresenter().getDisplay()) {
                getDisplay().insertActionButton(getDisplay().getAdditionalXML(), getDisplay().getXmlDown());
            } else {
                getDisplay().insertActionButton(getDisplay().getAdditionalXML(), getDisplay().getXml());
            }
        }

        if (displayedView == translatedTopicPresenter.getDisplay()) {
            getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDown());
        } else if (displayedView == translatedTopicAdditionalXMLPresenter.getDisplay()) {
            getDisplay().replaceTopActionButton(getDisplay().getAdditionalXML(), getDisplay().getAdditionalXMLDown());
        }
    }

    @Override
    protected void postAfterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        // Save any changes to the xml editor
        if (lastDisplayedView == translatedTopicAdditionalXMLPresenter.getDisplay()) {
            translatedTopicAdditionalXMLPresenter.getDisplay().getDriver().flush();
        }

        // While editing the XML, we need to setup a refresh of the rendered view
        if (displayedView == translatedTopicAdditionalXMLPresenter.getDisplay()) {
            final SplitType splitType = getDisplay().getSplitType();
            if (splitType != SplitType.NONE) {
                timer.scheduleRepeating(Constants.REFRESH_RATE);
            }

            // This should always be false
            if (!getXmlValidator().isCheckingXML()) {
                getXmlValidator().startCheckingXML();
            }
        } else {
            timer.cancel();
            refreshSplitRenderedView(true);
            getXmlValidator().stopCheckingXML();
        }
    }

    private XMLValidator getXmlValidator() {
        if (xmlValidator == null) {
            xmlValidator = new XMLValidator(translatedTopicAdditionalXMLPresenter.getDisplay().getEditor(),
                    translatedTopicAdditionalXMLPresenter.getDisplay().getXmlErrors());
        }

        return xmlValidator;
    }

    @Override
    protected void postBindActionButtons() {
        @NotNull final ClickHandler topicViewClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                    switchView(translatedTopicPresenter.getDisplay());
                }
            }
        };

        final ClickHandler translatedTopicAdditionalXMLClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                    switchView(translatedTopicAdditionalXMLPresenter.getDisplay());

                }
            }
        };

        final ValueChangeHandler<Boolean> mergeAdditionalXMLChangeHandler = new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                refreshSplitRenderedView(false, true);
            }
        };

        /* Hook up the xml editor buttons */
        translatedTopicAdditionalXMLPresenter.getDisplay().getEditorSettings().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getDisplay().getTopLevelPanel().isAttached() && getDisplay().isViewShown() && !isAnyDialogBoxesOpen(
                        translatedTopicAdditionalXMLPresenter.getDisplay())) {
                    translatedTopicAdditionalXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().center();
                    translatedTopicAdditionalXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().show();
                }
            }
        });

        /* Build up a click handler to save the topic */
        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                saveTopic(messageLogDialogOK);
            }
        };

        final ClickHandler messageLogDialogCancel = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        };

        getDisplay().getFields().addClickHandler(topicViewClickHandler);
        getDisplay().getSave().addClickHandler(saveClickHandler);
        getDisplay().getMessageLogDialog().getCancel().addClickHandler(messageLogDialogCancel);
        getDisplay().getAdditionalXML().addClickHandler(translatedTopicAdditionalXMLClickHandler);
        getTopicRenderedPresenter().getDisplay().getMergeAdditionalXML().addValueChangeHandler(mergeAdditionalXMLChangeHandler);
        getTopicSplitPanelRenderedPresenter().getDisplay().getMergeAdditionalXML().addValueChangeHandler(mergeAdditionalXMLChangeHandler);
    }

    @Override
    public boolean hasUnsavedChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");

            /* No topic selected, so no changes need to be saved */
            if (getSearchResultPresenter().getProviderData().getDisplayedItem() == null) {
                return false;
            }

            /* Save any pending changes */
            flushChanges();

            final RESTTranslatedTopicV1 displayedTopic = this.getSearchResultPresenter().getProviderData().getDisplayedItem().getItem();

            /* compare the displayed topics to the selected topic */
            final RESTTranslatedTopicV1 selectedTopic = this.getSearchResultPresenter().getProviderData().getSelectedItem().getItem();

            /*
             * If any values in selectedTopic don't match displayedTopic, we have unsaved changes
             */
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedTopic.getTranslatedAdditionalXML(),
                    displayedTopic.getTranslatedAdditionalXML())) {
                return true;
            }

            return false;
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");
        }
    }

    protected boolean isAnyDialogBoxesOpen(@NotNull final TranslatedTopicAdditionalXMLPresenter.Display topicXMLDisplay) {
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

        if (topicXMLDisplay.getEditorSettingsDialog().getDialogBox().isShowing()) {
            return true;
        }

        return false;
    }

    @Override
    protected void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        if (viewIsInFilter(filter, translatedTopicPresenter.getDisplay())) {
            translatedTopicPresenter.getDisplay().display(this.getDisplayedTopic(), isReadOnlyMode());
        }
        if (viewIsInFilter(filter, translatedTopicAdditionalXMLPresenter.getDisplay())) {
            LOGGER.log(Level.INFO, "\tSetting translated topic additional XML edit button state and redisplaying ACE editor");
            translatedTopicAdditionalXMLPresenter.getDisplay().display(this.getDisplayedTopic(), ServerDetails.getSavedServer().isReadOnly());
            translatedTopicAdditionalXMLPresenter.loadEditorSettings();
            translatedTopicAdditionalXMLPresenter.getDisplay().getEditor().redisplay();
        }
    }

    @Override
    protected boolean isReadOnlyMode() {
        /* translated topics are always readonly */
        return true;
    }

    @Override
    protected void bindFilteredResultsButtons() {
        /*
            Nothing needs to be done here
        */
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.parseToken()");

            setQueryString(removeHistoryToken(historyToken, HISTORY_TOKEN));

            if (!getQueryString().startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                /* Make sure that the query string has at least the prefix */
                setQueryString(Constants.QUERY_PATH_SEGMENT_PREFIX);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.parseToken()");
        }
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.flushChanges()");

            if (lastDisplayedView == null || !(lastDisplayedView instanceof BasePopulatedEditorViewInterface)) {
                return;
            }

            /* These are read only views */
            if (lastDisplayedView == getTopicTagsPresenter().getDisplay()) {
                return;
            }

            ((BasePopulatedEditorViewInterface) lastDisplayedView).getDriver().flush();
        } finally {
            LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.flushChanges()");
        }
    }

    /**
     * Refresh the split panel rendered view
     *
     * @param forceExternalImages true if external images should be displayed, false if they should only be displayed
     *                            after the topics has not been edited after a period of time
     */
    private void refreshSplitRenderedView(final boolean forceExternalImages) {
        refreshSplitRenderedView(forceExternalImages, false);
    }

    /**
     * Refresh the split panel rendered view
     *
     * @param forceExternalImages true if external images should be displayed, false if they should only be displayed
     *                            after the topics has not been edited after a period of time
     */
    private void refreshSplitRenderedView(final boolean forceExternalImages, final boolean forceUpdate) {
        try {
            //LOGGER.log(Level.INFO, "ENTER TranslatedTopicFilteredResultsAndDetailsPresenter.refreshSplitRenderedView()");

            try {
                translatedTopicAdditionalXMLPresenter.getDisplay().getDriver().flush();
            } catch (@NotNull final IllegalStateException ex) {
                LOGGER.log(Level.WARNING,
                        "translatedTopicAdditionalXMLPresenter.getDisplay().getDriver().flush() threw an IllegalStateException. This " +
                                "probably " + "happened because the rendered view was refreshed before the XML editor was bound.");
            }

            if (getDisplayedTopic() != null) {
                final boolean xmlHasChanges = lastXML == null || !lastXML.equals(getDisplayedTopic().getXml());
                final boolean mergingChanges = translatedTopicRenderedPresenter.getDisplay().getMergeAdditionalXML().getValue() ||
                        translatedTopicSplitPanelRenderedPresenter.getDisplay().getMergeAdditionalXML().getValue();
                final boolean additionalXmlHasChanges = mergingChanges && lastAdditionalXML != null && !lastAdditionalXML.equals(
                        getDisplayedTopic().getTranslatedAdditionalXML());

                if (xmlHasChanges || additionalXmlHasChanges) {
                    lastXMLChange = System.currentTimeMillis();
                }

                final Boolean timeToDisplayImage = forceExternalImages || System.currentTimeMillis() - lastXMLChange >= Constants
                        .REFRESH_RATE_WTH_IMAGES;

                if (forceUpdate || xmlHasChanges || additionalXmlHasChanges || (!isDisplayingImage && timeToDisplayImage)) {
                    isDisplayingImage = timeToDisplayImage;
                    getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), isReadOnlyMode(), isDisplayingImage);
                }

                lastXML = getDisplayedTopic().getXml();
                if (mergingChanges) {
                    lastAdditionalXML = getDisplayedTopic().getTranslatedAdditionalXML();
                }
            }
        } finally {
            //LOGGER.log(Level.INFO, "EXIT TranslatedTopicFilteredResultsAndDetailsPresenter.refreshSplitRenderedView()");
        }
    }

    protected boolean isExtendedTopic() {
        final RESTTranslatedTopicV1 topic = getDisplayedTopic();
        if (getDisplayedTopic() != null) {
            if (EntityUtilities.topicHasTag(topic, serverSettings.getEntities().getRevisionHistoryTagId())) {
                return true;
            } else if (EntityUtilities.topicHasTag(topic, serverSettings.getEntities().getAuthorGroupTagId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This interface defines nothing over BaseTopicFilteredResultsAndDetailsPresenter.Display,
     * but exists for the benefit of the injection.
     */
    public interface Display extends BaseTopicFilteredResultsAndDetailsPresenter.Display<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {
        @NotNull
        PushButton getAdditionalXML();

        @NotNull
        Label getAdditionalXMLDown();

        @NotNull
        PushButton getSave();
    }
}
