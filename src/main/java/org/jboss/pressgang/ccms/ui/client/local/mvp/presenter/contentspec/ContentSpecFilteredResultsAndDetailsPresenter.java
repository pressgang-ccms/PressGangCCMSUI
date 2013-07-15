package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTTextContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTTextContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextCSProcessingOptionsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTextContentSpecCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTTextContentSpecV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter that combines all the content spec presenters.
 */
@Dependent
public class ContentSpecFilteredResultsAndDetailsPresenter extends BaseSearchAndEditPresenter<RESTTextContentSpecV1,
        RESTTextContentSpecCollectionV1, RESTTextContentSpecCollectionItemV1, RESTTextContentSpecV1BasicDetailsEditor> {

    public final static String HISTORY_TOKEN = "ContentSpecFilteredResultsAndContentSpecView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecFilteredResultsAndDetailsPresenter.class.getName());

    @Inject private FailOverRESTCall failOverRESTCall;

    /**
     * true if this presenter should be opened with a fresh topic, and false otherwise
     */
    private boolean startWithNewSpec = false;

    /**
     * A list of locales retrieved from the server
     */
    private List<String> locales;

    /**
     * true after the locales have been loaded
     */
    private boolean localesLoaded = false;

    /**
     * true after the default locale has been loaded
     */
    @Nullable
    private String defaultLocale = null;

    /**
     * true after the topics have been loaded
     */
    private boolean contentSpecListLoaded = false;

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject
    private ContentSpecPresenter contentSpecPresenter;
    @Inject
    private ContentSpecDetailsPresenter contentSpecDetailsPresenter;
    @Inject
    private ContentSpecFilteredResultsPresenter filteredResultsPresenter;
    /**
     * The presenter used to display the property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter commonExtendedPropertiesPresenter;
    @Inject
    private ContentSpecRevisionsPresenter contentSpecRevisionsComponent;
    @Inject
    private ContentSpecTagsPresenter contentSpecTagsPresenter;
    @Inject
    private ContentSpecErrorPresenter contentSpecErrorsPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @NotNull
    protected Display getDisplay() {
        return display;
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");

            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");
            checkState(
                    filteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem() || filteredResultsPresenter
                            .getProviderData().getDisplayedItem().getItem().getId() != null,
                    "The displayed collection item to reference a valid entity with a valid ID.");

            /* Disable the topic revision view */
            viewLatestSpecRevision();

            final RESTTextContentSpecV1 displayedItem = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
            final RESTTextContentSpecCollectionItemV1 selectedCollectionItem = filteredResultsPresenter.getProviderData().getSelectedItem();


            /*
                Display the list of assigned property tags. This should not be null, but bugs in the REST api can
                lead to the properties collection being null.
            */
            if (displayedItem.getProperties() != null) {
                Collections.sort(displayedItem.getProperties().getItems(),
                        new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                commonExtendedPropertiesPresenter.refreshExistingChildList(displayedItem);
            }

            /* Get a new collection of property tags. */
            commonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(displayedItem);

            displayPropertyTags();

            /* set the revisions to show the loading widget */
            if (contentSpecRevisionsComponent.getDisplay().getProvider() != null) {
                contentSpecRevisionsComponent.getDisplay().getProvider().resetProvider();
            }

            /*
                If this is not a new item (i.e. the selected item is not null), load some revisions.
             */
            if (selectedCollectionItem != null) {
                this.contentSpecRevisionsComponent.getDisplay().setProvider(
                        this.contentSpecRevisionsComponent.generateListProvider(selectedCollectionItem.getItem().getId(), display));
            }

            loadTags();
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");
        }
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.initializeViews()");

            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");

            final RESTTextContentSpecV1 displayedItem = getDisplayedContentSpec();

            if (viewIsInFilter(filter, contentSpecDetailsPresenter.getDisplay())) {
                contentSpecDetailsPresenter.getDisplay().displayContentSpecDetails(displayedItem, isReadOnlyMode(),
                        locales);
            }

            if (viewIsInFilter(filter, contentSpecPresenter.getDisplay())) {
                contentSpecPresenter.getDisplay().display(displayedItem, isReadOnlyMode());
            }

            if (viewIsInFilter(filter, contentSpecErrorsPresenter.getDisplay())) {
                contentSpecErrorsPresenter.getDisplay().display(displayedItem);
            }

            if (viewIsInFilter(filter, contentSpecTagsPresenter.getDisplay())) {
                contentSpecTagsPresenter.getDisplay().display(displayedItem, isReadOnlyMode());
            }

            if (viewIsInFilter(filter, commonExtendedPropertiesPresenter.getDisplay())) {
                commonExtendedPropertiesPresenter.getDisplay().display(displayedItem, isReadOnlyMode());
                commonExtendedPropertiesPresenter.displayDetailedChildrenExtended(displayedItem, isReadOnlyMode());
            }

            /*
                The revision display always displays details from the main topic, and not the selected revision.
            */
            if (viewIsInFilter(filter, contentSpecRevisionsComponent.getDisplay())) {
                LOGGER.log(Level.INFO, "\tInitializing topic revisions view");
                contentSpecRevisionsComponent.getDisplay().display(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                        isReadOnlyMode());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.initializeViews()");
        }
    }

    protected boolean beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.beforeSwitchView()");
        flushChanges();
        LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.beforeSwitchView()");
        return true;
    }

    @Override
    protected void bindActionButtons() {
        display.getText().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecPresenter.getDisplay());
            }
        });

        display.getErrors().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecErrorsPresenter.getDisplay());
            }
        });

        display.getExtendedProperties().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(commonExtendedPropertiesPresenter.getDisplay());
            }
        });

        display.getDetails().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecDetailsPresenter.getDisplay());
            }
        });

        display.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hasUnsavedChanges()) {
                    display.getMessageLogDialog().getUsername().setText(
                            Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().center();
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            }
        });

        display.getPermissiveSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hasUnsavedChanges()) {
                    display.getMessageLogDialog().getUsername().setText(
                            Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().center();

                    final RESTTextCSProcessingOptionsV1 processingOptions = new RESTTextCSProcessingOptionsV1();
                    processingOptions.setPermissive(true);
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setProcessingOptions(processingOptions);
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            }
        });

        display.getHistory().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecRevisionsComponent.getDisplay());
                }
            }
        });

        contentSpecRevisionsComponent.getDisplay().getDone().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (getDisplayedContentSpec().getRevision() == filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getRevision
                        ()) {
                    checkState(contentSpecRevisionsComponent.getDisplay().getMergely() != null, "mergely should not be null");
                    final String lhs = contentSpecRevisionsComponent.getDisplay().getMergely().getLhs();
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setText(lhs);
                    initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{contentSpecPresenter.getDisplay()}));
                }
                contentSpecRevisionsComponent.getDisplay().displayRevisions();
                getDisplay().getSave().setEnabled(!isReadOnlyMode());
                getDisplay().getPermissiveSave().setEnabled(!isReadOnlyMode());
            }
        });

        contentSpecRevisionsComponent.getDisplay().getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                contentSpecRevisionsComponent.getDisplay().displayRevisions();
                getDisplay().getSave().setEnabled(!isReadOnlyMode());
                getDisplay().getPermissiveSave().setEnabled(!isReadOnlyMode());
            }
        });

        display.getContentSpecTags().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecTagsPresenter.getDisplay());
                }
            }
        });

        display.getMessageLogDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO,
                            "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    final String user = display.getMessageLogDialog().getUsername().getText().trim();
                    Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                    final StringBuilder message = new StringBuilder();
                    if (!user.isEmpty()) {
                        message.append(user).append(": ");
                    }
                    message.append(display.getMessageLogDialog().getMessage().getText());
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE
                            : ServiceConstants.MAJOR_CHANGE);

                    final RESTTextContentSpecV1 displayedEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
                    final Integer id = displayedEntity.getId();

                    // Copy out the text
                    flushChanges();

                    // create the object to be saved
                    final RESTTextContentSpecV1 updatedSpec = new RESTTextContentSpecV1();
                    updatedSpec.explicitSetText(displayedEntity.getText());
                    updatedSpec.setProcessingOptions(displayedEntity.getProcessingOptions());

                    if (displayedEntity.getProperties() != null) {
                        updatedSpec.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                        updatedSpec.getProperties().setItems(displayedEntity.getProperties().getItems());
                    }


                    if (displayedEntity.getTags() != null) {
                        updatedSpec.explicitSetTags(new RESTTagCollectionV1());
                        updatedSpec.getTags().setItems(displayedEntity.getTags().getItems());
                    }

                    if (filteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem()) {

                        final RESTCallBack<RESTTextContentSpecV1> addCallback = new RESTCallBack<RESTTextContentSpecV1>() {
                            @Override
                            public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                                try {
                                    LOGGER.log(Level.INFO,
                                            "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK"
                                                    + ".onClick() addCallback.doSuccessAction() - New Content Spec");

                                    // Create the contentspec wrapper
                                    final RESTTextContentSpecCollectionItemV1 contentSpecCollectionItem = new
                                            RESTTextContentSpecCollectionItemV1();
                                    contentSpecCollectionItem.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                    // create the content spec, and add to the wrapper
                                    contentSpecCollectionItem.setItem(retValue);

                                    // Update the displayed content spec
                                    filteredResultsPresenter.getProviderData().setDisplayedItem(contentSpecCollectionItem.clone(true));

                                    /*
                                        Two things can happen to the selected item at this point. Either we are in the
                                        "create content spec" mode, in which we simply add the new topics to the data provider,
                                        and never refresh from the database. In this case, the selected item and the item
                                        in the data provider are the same, and always linked.

                                        The second mode is where we have created a topic when already displaying a query.
                                        In this case the selected item will be relinked in the relinkSelectedItem() method,
                                        or it will remain referencing the returned value here if the query doesn't actually
                                        return the topic that was saved.
                                     */
                                    filteredResultsPresenter.setSelectedItem(contentSpecCollectionItem);

                                    /*
                                        Show the invalid text if required. Also fix up the selected item, because this is what
                                        we will be comparing to when checking for changes.
                                    */
                                    ComponentContentSpecV1.fixDisplayedText(
                                            filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());


                                    if (startWithNewSpec) {
                                        LOGGER.log(Level.INFO, "Adding new topic to static list");

                                        // We need to swap the text with the invalid text
                                        ComponentContentSpecV1.fixDisplayedText(
                                                filteredResultsPresenter.getProviderData().getSelectedItem().getItem());

                                        filteredResultsPresenter.getProviderData().getItems().add(contentSpecCollectionItem);
                                        filteredResultsPresenter.getProviderData().setSize(
                                                filteredResultsPresenter.getProviderData().getItems().size());
                                        updateDisplayWithNewEntityData(false);
                                    } else {
                                        // Update the selected topic
                                        LOGGER.log(Level.INFO, "Redisplaying query");

                                        // When the list is repopulated, the text will be swapped with the invalid text
                                        updateDisplayWithNewEntityData(true);
                                    }

                                    initializeViews(new ArrayList<BaseTemplateViewInterface>() {{
                                        add(contentSpecPresenter.getDisplay());
                                    }});

                                    Window.alert(PressGangCCMSUI.INSTANCE.ContentSpecSaveSuccessWithID() + " " + retValue.getId());
                                } finally {
                                    LOGGER.log(Level.INFO,
                                            "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK" +
                                                    ".onClick() addCallback.doSuccessAction() - New Topic");
                                }
                            }
                        };

                        failOverRESTCall.performRESTCall(
                                FailOverRESTCallDatabase.createContentSpec(
                                updatedSpec,
                                message.toString(),
                                flag,
                                ServiceConstants.NULL_USER_ID.toString())
                        ,addCallback, display);
                    } else {
                        /* We are updating, so we need the id */
                        updatedSpec.setId(id);

                        /*
                            Save the spec
                         */
                        final RESTCallBack<RESTTextContentSpecV1> updateCallback = new RESTCallBack<RESTTextContentSpecV1>() {
                            @Override
                            public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                                boolean overwroteChanges = false;
                                final Integer originalRevision = displayedEntity.getRevision();

                                if (retValue.getRevisions() != null && retValue.getRevisions().getItems() != null) {
                                    Collections.sort(retValue.getRevisions().getItems(),
                                            new RESTTextContentSpecCollectionItemV1RevisionSort());

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

                                        LOGGER.log(Level.INFO,
                                                "originalRevision: " + originalRevision + " new revision: " + overwriteRevision);

                                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                                    }

                                    /*
                                        Otherwise we need to make sure that the second last revision matches the revision of the
                                        topic we were editing.
                                     */
                                    if (overwroteChanges && retValue.getRevisions().getItems().size() >= 2) {
                                                /* Get the second last revision (the last one is the current one) */
                                        final Integer overwriteRevision = retValue.getRevisions().getItems().get(
                                                retValue.getRevisions().getItems().size() - 2).getItem().getRevision();

                                        LOGGER.log(Level.INFO,
                                                "originalRevision: " + originalRevision + " last revision: " + overwriteRevision);

                                        /*
                                         * if the second last revision doesn't match the revision of the topic when editing was
                                         * started, then we have overwritten someone elses changes
                                         */
                                        overwroteChanges = !originalRevision.equals(overwriteRevision);
                                    }
                                }

                                // Update the displayed content spec
                                retValue.cloneInto(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);
                                // Update the selected content spec
                                retValue.cloneInto(filteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);

                                // Show the invalid text if required.
                                ComponentContentSpecV1.fixDisplayedText(
                                        filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
                                ComponentContentSpecV1.fixDisplayedText(
                                        filteredResultsPresenter.getProviderData().getSelectedItem().getItem());

                                initializeViews(new ArrayList<BaseTemplateViewInterface>() {{
                                    add(contentSpecPresenter.getDisplay());
                                }});
                                updateDisplayWithNewEntityData(false);

                                if (overwroteChanges) {
                                    // Take the user to the revisions view so they can review any overwritten changes
                                    switchView(contentSpecRevisionsComponent.getDisplay());
                                    Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                } else {
                                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                }
                            }
                        };

                        failOverRESTCall.performRESTCall(
                                FailOverRESTCallDatabase.updateContentSpec(
                                        updatedSpec,
                                        message.toString(),
                                        flag,
                                        ServiceConstants.NULL_USER_ID.toString()),
                                updateCallback, display);
                    }
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setProcessingOptions(null);

                    LOGGER.log(Level.INFO,
                            "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
                }
            }
        });

        display.getMessageLogDialog().getCancel().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        });
    }

    @Override
    protected void bindFilteredResultsButtons() {

        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
            filteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {

                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    createNewContentSpec();
                }
            });

        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
        }
    }

    /**
     * Called to create a new content spec
     */
    private void createNewContentSpec() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.createNewTopic()");

            /* make sure there are no unsaved changes, or that the user is happy to continue without saving */
            if (!isOKToProceed()) {
                return;
            }

            // Create the topic wrapper
            final RESTTextContentSpecCollectionItemV1 contentSpecCollectionItem = new RESTTextContentSpecCollectionItemV1();
            contentSpecCollectionItem.setState(RESTBaseCollectionItemV1.ADD_STATE);

            // create the topic, and add to the wrapper
            final RESTTextContentSpecV1 newEntity = new RESTTextContentSpecV1();
            newEntity.setProperties(new RESTAssignedPropertyTagCollectionV1());
            newEntity.setText("");
            newEntity.setLocale(defaultLocale);
            contentSpecCollectionItem.setItem(newEntity);

            // the topic won't show up in the list of topics until it is saved, so the
            // selected item is null
            filteredResultsPresenter.setSelectedItem(null);

            // the new topic is being displayed though, so we set the displayed item
            filteredResultsPresenter.getProviderData().setDisplayedItem(contentSpecCollectionItem);

            updateViewsAfterNewEntityLoaded();
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.createNewTopic()");
        }
    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTTextContentSpecV1> getNewEntityCallback = new GetNewEntityCallback<RESTTextContentSpecV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTextContentSpecV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTTextContentSpecV1> displayCallback) {

                final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                        checkState(retValue.getProperties() != null, "The returned entity needs to have a valid properties collection");
                        checkState(retValue.getText() != null, "The returned entity needs to have a valid text field");

                        ComponentContentSpecV1.fixDisplayedText(retValue);
                        displayCallback.displayNewEntity(retValue);
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getContentSpec(selectedEntity.getId()), callback, display);
            }
        };

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);

        contentSpecDetailsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        contentSpecPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);
        contentSpecErrorsPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);
        filteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);
        commonExtendedPropertiesPresenter.bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        contentSpecTagsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);

        contentSpecTagsPresenter.getTags();

        super.bindSearchAndEdit(topicId, pageId, Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, contentSpecPresenter.getDisplay(),
                contentSpecDetailsPresenter.getDisplay(), filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display,
                getNewEntityCallback);

        bindTagButtons();

        bindViewContentSpecRevisionButton();

        /* When the topics have been loaded, display the first one */
        filteredResultsPresenter.addTopicListReceivedHandler(new EntityListReceivedHandler<RESTTextContentSpecCollectionV1>() {
            @Override
            public void onCollectionReceived(@NotNull final RESTTextContentSpecCollectionV1 topics) {
                try {
                    LOGGER.log(Level.INFO,
                            "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");

                    contentSpecListLoaded = true;
                    displayInitialContentSpec(getNewEntityCallback);
                } finally {
                    LOGGER.log(Level.INFO,
                            "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bind() EntityListReceivedHandler.onCollectionReceived()");
                }
            }
        });

        FailOverRESTCallDatabase.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> locales) {
                try {
                    LOGGER.log(Level.INFO,
                            "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bind() StringListLoaded.stringListLoaded()");

                    ContentSpecFilteredResultsAndDetailsPresenter.this.locales = locales;
                    localesLoaded = true;
                    displayNewContentSpec();
                    displayInitialContentSpec(getNewEntityCallback);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bind() StringListLoaded.stringListLoaded()");
                }

            }
        }, display, failOverRESTCall);

        FailOverRESTCallDatabase.loadDefaultLocale(new StringLoaded() {
            @Override
            public void stringLoaded(@NotNull final String string) {
                defaultLocale = string;
                displayNewContentSpec();
            }
        }, display, failOverRESTCall);
    }

    /**
     * When the locales and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    protected void displayInitialContentSpec(@NotNull final GetNewEntityCallback<RESTTextContentSpecV1> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");

            if (isInitialTopicReadyToBeLoaded() &&
                    filteredResultsPresenter.getProviderData().getItems() != null &&
                    filteredResultsPresenter.getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, filteredResultsPresenter.getProviderData().getItems().get(0));
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");
        }
    }

    private boolean isInitialTopicReadyToBeLoaded() {
        /* only proceed loading the initial topic when the locales and the topic list have been loaded */
        return localesLoaded && contentSpecListLoaded;
    }

    /**
     * When the default locale and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    private void displayNewContentSpec() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.displayNewContentSpec()");

            if (defaultLocale != null && locales != null && startWithNewSpec) {
                createNewContentSpec();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayNewContentSpec()");
        }
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewContentSpecRevisionButton() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton()");

            contentSpecRevisionsComponent.getDisplay().getDiffButton().setFieldUpdater(
                    new FieldUpdater<RESTTextContentSpecCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTextContentSpecCollectionItemV1 revisionContentSpec,
                                final String value) {

                            final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                                @Override
                                public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                                    checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");

                                    /*
                                        It is possible to switch away from the view while this request was loading. If we
                                        have done so, don't show the merge view.
                                     */
                                    if (lastDisplayedView == contentSpecRevisionsComponent.getDisplay()) {
                                        final boolean lhsReadonly = getDisplayedContentSpec().getRevision() !=
                                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                                                        .getRevision();

                                        // Fix the displayed text up
                                        ComponentContentSpecV1.fixDisplayedText(retValue);

                                        // Display the diffs
                                        contentSpecRevisionsComponent.getDisplay().displayDiff(getDisplayedContentSpec().getText(),
                                                lhsReadonly, retValue.getText());

                                        // We can't save while merging.
                                        getDisplay().getSave().setEnabled(false);
                                        getDisplay().getPermissiveSave().setEnabled(false);
                                    }

                                    contentSpecRevisionsComponent.getDisplay().setButtonsEnabled(true);
                                }
                            };

                            failOverRESTCall.performRESTCall(
                                    FailOverRESTCallDatabase.getContentSpecRevision(
                                            revisionContentSpec.getItem().getId(),
                                            revisionContentSpec.getItem().getRevision()),
                                    callback, display);
                        }
                    });

            contentSpecRevisionsComponent.getDisplay().getViewButton().setFieldUpdater(
                    new FieldUpdater<RESTTextContentSpecCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTextContentSpecCollectionItemV1 revisionTopic,
                                final String value) {

                            try {
                                LOGGER.log(Level.INFO,
                                        "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton() FieldUpdater"
                                                + ".update()");

                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                                        "There should be a displayed collection item.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                                        "The displayed collection item to reference a valid entity.");
                                checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");

                                displayRevision(revisionTopic.getItem());

                                contentSpecRevisionsComponent.getDisplay().getProvider().displayAsynchronousList(
                                        contentSpecRevisionsComponent.getProviderData().getItems(),
                                        contentSpecRevisionsComponent.getProviderData().getSize(),
                                        contentSpecRevisionsComponent.getProviderData().getStartRow());
                            } finally {
                                LOGGER.log(Level.INFO,
                                        "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton() FieldUpdater" +
                                                ".update()");
                            }
                        }
                    });
        } finally {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton()");
        }
    }

    /**
     * Displays a revision of a topic. This can be called when a revision is selected to be displayed, or if
     * a particular revision is set as the default to be displayed.
     *
     * @param revisionSpec The revision to be displayed.
     */
    private void displayRevision(@NotNull final RESTTextContentSpecV1 revisionSpec) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.displayRevision()");

            /* Reset the reference to the revision topic */
            viewLatestSpecRevision();

            /*
                The latest revision is the same as the revision that is pulled down when the content spec is first viewed.
                 If the selected revision is the latest one, just display the latest one. Otherwise, display the revision.
             */
            if (!revisionSpec.getRevision().equals(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getRevision())) {
                /* Reset the reference to the revision topic */
                contentSpecRevisionsComponent.getDisplay().setRevisionContentSpec(revisionSpec);
            }

            /* Initialize the views with the new topic being displayed */
            initializeViews();

            /* Load the tags and bugs */
            loadTags();

            /* Redisplay the list of property tags */
            displayPropertyTags();

            /* Display the revisions view (which will also update buttons like Save) */
            switchView(contentSpecRevisionsComponent.getDisplay());

        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayRevision()");
        }
    }

    /**
     * topicRevisionsComponent.getDisplay().getRevisionTopic() is used to indicate which revision
     * of a topic we are looking at. Setting it to null means that we are not looking at a revision.
     */
    private void viewLatestSpecRevision() {
        contentSpecRevisionsComponent.getDisplay().setRevisionContentSpec(null);
    }

    private void displayPropertyTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.displayPropertyTags()");

            final RESTTextContentSpecV1 displayedItem = getDisplayedContentSpec();

            checkState(displayedItem.getProperties() != null,
                    "The displayed entity or revision needs to have a valid properties collection");

            /*
                Display the list of assigned property tags. This should not be null, but bugs in the REST api can
                lead to the properties collection being null.
            */

            Collections.sort(displayedItem.getProperties().getItems(),
                    new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
            commonExtendedPropertiesPresenter.refreshExistingChildList(displayedItem);

        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayPropertyTags()");
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
    private void loadTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadTags()");

            // Initiate the REST calls
            final Integer id = getDisplayedContentSpec().getId();
            final Integer revision = getDisplayedContentSpec().getRevision();

            // If this is a new topic, the id will be null, and there will not be any tags to get
            if (id != null) {

                // A callback to respond to a request for a topic with the tags expanded
                final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO,
                                    "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback" +
                                            ".doSuccessAction()");

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

                            // copy the revisions into the displayed Topic */
                            getDisplayedContentSpec().setTags(retValue.getTags());

                            // update the view
                            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{contentSpecTagsPresenter.getDisplay()}));
                        } finally {
                            LOGGER.log(Level.INFO,
                                    "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadTags() topicWithTagsCallback" + "" +
                                            ".doSuccessAction()");
                        }
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getContentSpecWithTags(id), callback, contentSpecTagsPresenter.getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTags()");
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

        this.queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);

        if (queryString.startsWith(Constants.CREATE_PATH_SEGMENT_PREFIX)) {
            startWithNewSpec = true;
            queryString = null;
        } else {
            if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
                /* Make sure that the query string has at least the prefix */
                queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
            }
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public void close() {
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.afterSwitchView()");

            enableAndDisableActionButtons(displayedView);
            setHelpTopicForView(displayedView);

            /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
            if (displayedView != null) {
                displayedView.setViewShown(true);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.afterSwitchView()");
        }
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getTextDown(), this.display.getText());
        this.display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());
        this.display.replaceTopActionButton(this.display.getExtendedPropertiesDown(), this.display.getExtendedProperties());
        this.display.replaceTopActionButton(this.display.getHistoryDown(), this.display.getHistory());
        this.display.replaceTopActionButton(this.display.getContentSpecTagsDown(), this.display.getContentSpecTags());
        this.display.replaceTopActionButton(this.display.getErrorsDown(), this.display.getErrors());

        if (displayedView == this.contentSpecDetailsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.contentSpecPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getText(), this.display.getTextDown());
        } else if (displayedView == this.commonExtendedPropertiesPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getExtendedProperties(), this.display.getExtendedPropertiesDown());
        } else if (displayedView == this.contentSpecRevisionsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getHistory(), this.display.getHistoryDown());
        } else if (displayedView == this.contentSpecTagsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getContentSpecTags(), this.display.getContentSpecTagsDown());
        } else if (displayedView == this.contentSpecErrorsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getErrors(), this.display.getErrorsDown());
        }
    }

    private void setHelpTopicForView(@NotNull final BaseTemplateViewInterface view) {
        if (view == contentSpecDetailsPresenter.getDisplay()) {
            setHelpTopicId(contentSpecDetailsPresenter.getHelpTopicId());
        } else if (view == contentSpecPresenter.getDisplay()) {
            setHelpTopicId(contentSpecPresenter.getHelpTopicId());
        }
    }

    @Nullable
    private RESTTextContentSpecV1 getDisplayedContentSpec() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.getDisplayedContentSpec()");

            RESTTextContentSpecV1 source = null;

            if (contentSpecRevisionsComponent.getDisplay().getRevisionContentSpec() != null) {
                source = contentSpecRevisionsComponent.getDisplay().getRevisionContentSpec();
            } else if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                source = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
            }

            return source;
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.getDisplayedContentSpec()");
        }
    }

    private boolean isReadOnlyMode() {
        return this.contentSpecRevisionsComponent.getDisplay().getRevisionContentSpec() != null;
    }

    /**
     * Sync any changes back to the underlying object
     */
    private void flushChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.flushChanges()");

            if (lastDisplayedView == null || !(lastDisplayedView instanceof BasePopulatedEditorViewInterface)) {
                return;
            }

            ((BasePopulatedEditorViewInterface) lastDisplayedView).getDriver().flush();
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.flushChanges()");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");

            /* No topic selected, so no changes need to be saved */
            if (filteredResultsPresenter.getProviderData().getDisplayedItem() == null) {
                return false;
            }

            flushChanges();

            final RESTTextContentSpecCollectionItemV1 displayedEntityCollectionItem = filteredResultsPresenter.getProviderData()
                    .getDisplayedItem();
            final RESTTextContentSpecV1 displayedEntity = displayedEntityCollectionItem.getItem();
            final RESTTextContentSpecCollectionItemV1 selectedEntityCollectionItem = filteredResultsPresenter.getProviderData()
                    .getSelectedItem();


             /*
                If there are any modified tags in newContentSpec, we have unsaved changes.
                If getTags() is null, the tags have not been loaded yet (and can't have been modified).
            */
            if (displayedEntity.getTags() != null && !displayedEntity.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }

            /* If there are any modified property tags in newTopic, we have unsaved changes */
            if (!displayedEntity.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                LOGGER.log(Level.INFO, "Unsaved properties");
                return true;
            }

            if (!displayedEntityCollectionItem.returnIsAddItem()) {
                /* See if the text has changed */
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedEntityCollectionItem.getItem().getText(),
                        displayedEntity.getText())) {
                    LOGGER.log(Level.INFO,
                            "Text is different between selected and displayed. \n" + selectedEntityCollectionItem.getItem().getText() +
                                    "\n" + displayedEntity.getText());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getId().toString());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getErrors());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getFailedContentSpec());
                    return true;
                }
            } else {
                /*
                    If there has been any text added, we have unsaved changes.
                 */
                LOGGER.log(Level.INFO, "Text is not empty");
                return !displayedEntity.getText().trim().isEmpty();
            }

            return false;
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");
        }
    }

    private void bindTagButtons() {
        /* Bind the add button in the tags view */
        bindNewTagListBoxes(new AddTagClickHandler(new ReturnCurrentContentSpec() {
            @NotNull
            @Override
            public RESTTextContentSpecV1 getContentSpec() {
                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                        "There should be a displayed collection item.");
                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                        "The displayed collection item to reference a valid entity.");
                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags() != null,
                        "The displayed collection item to reference a valid entity and have a valid tags collection.");
                return filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
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
        }, contentSpecTagsPresenter.getDisplay()
        ), contentSpecTagsPresenter.getDisplay());
    }

    /**
     * Add behaviour to the tag delete buttons
     */
    private void bindTagEditingButtons() {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindTagEditingButtons()");

            /* This will be null if the tags have not been downloaded */
            if (contentSpecTagsPresenter.getDisplay().getEditor() == null) {
                return;
            }

            if (contentSpecTagsPresenter.getDisplay().getEditor().projects == null) {
                return;
            }

            for (@NotNull final TopicTagViewProjectEditor topicTagViewProjectEditor : contentSpecTagsPresenter.getDisplay().getEditor()
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
                                new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag(), new ReturnCurrentContentSpec() {
                                    @NotNull
                                    @Override
                                    public RESTTextContentSpecV1 getContentSpec() {

                                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                                                "There should be a displayed collection item.");
                                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                                                "The displayed collection item to reference a valid entity.");
                                        checkState(
                                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags() != null,
                                                "The displayed collection item to reference a valid entity and have a valid tags " +
                                                        "collection.");

                                        return filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
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
                                }, contentSpecTagsPresenter.getDisplay()
                                ));
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindTagEditingButtons()");
        }
    }

    /**
     * Adds event handlers to the new tag combo boxes and add button. Similar to TopicTagsPresenter.bindNewTagListBoxes(),
     * but this version takes the tags display, so we can apply it to the bulk import dialog too.
     *
     * @param clickHandler The Add button click handler
     * @param tagsDisplay  The tags view
     */
    static private void bindNewTagListBoxes(@NotNull final ClickHandler clickHandler,
            @NotNull final ContentSpecTagsPresenter.Display tagsDisplay) {
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

    private interface ReturnCurrentContentSpec {
        @NotNull
        RESTTextContentSpecV1 getContentSpec();
    }

    private interface ReturnReadOnlyMode {
        boolean getReadOnlyMode();
    }

    private interface BindRemoveButtons {
        void bindRemoveButtons();
    }

    /**
     * A click handler to add a tag to a content spec
     *
     * @author Matthew Casperson
     */
    private static class AddTagClickHandler implements ClickHandler {

        private final ReturnCurrentContentSpec returnCurrentContentSpec;
        private final ContentSpecTagsPresenter.Display tagDisplay;
        private final ReturnReadOnlyMode returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;

        /**
         * @param returnCurrentContentSpec A callback used to get the topic that the click handler is modifying
         * @param tagDisplay               The display that the callback is modifying
         */
        public AddTagClickHandler(@NotNull final ReturnCurrentContentSpec returnCurrentContentSpec,
                @NotNull final ReturnReadOnlyMode returnReadOnlyMode, @NotNull final BindRemoveButtons bindRemoveButtons,
                @NotNull final ContentSpecTagsPresenter.Display tagDisplay) {
            this.returnCurrentContentSpec = returnCurrentContentSpec;
            this.tagDisplay = tagDisplay;
            this.returnReadOnlyMode = returnReadOnlyMode;
            this.bindRemoveButtons = bindRemoveButtons;
        }

        @Override
        public void onClick(@NotNull final ClickEvent event) {

            final RESTTagV1 selectedTag = tagDisplay.getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            @Nullable RESTTagCollectionItemV1 deletedTag = null;
            for (@NotNull final RESTTagCollectionItemV1 tag : returnCurrentContentSpec.getContentSpec().getTags().getItems()) {
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
                    returnCurrentContentSpec.getContentSpec().getTags().getItems(), new Predicate<RESTTagCollectionItemV1>() {

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
                returnCurrentContentSpec.getContentSpec().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            tagDisplay.display(returnCurrentContentSpec.getContentSpec(), returnReadOnlyMode.getReadOnlyMode());
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
        private final ReturnCurrentContentSpec returnCurrentContentSpec;
        private final ContentSpecTagsPresenter.Display tagDisplay;
        private final ReturnReadOnlyMode returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;


        /**
         * @param returnCurrentContentSpec A callback used to get the topic that the click handler is modifying
         * @param tagDisplay               The display that the callback is modifying
         */
        public DeleteTagClickHandler(@NotNull final RESTTagCollectionItemV1 tag,
                @NotNull final ReturnCurrentContentSpec returnCurrentContentSpec, @NotNull final ReturnReadOnlyMode returnReadOnlyMode,
                @NotNull final BindRemoveButtons bindRemoveButtons, @NotNull final ContentSpecTagsPresenter.Display tagDisplay) {
            this.returnCurrentContentSpec = returnCurrentContentSpec;
            this.tagDisplay = tagDisplay;
            this.tag = tag;
            this.returnReadOnlyMode = returnReadOnlyMode;
            this.bindRemoveButtons = bindRemoveButtons;
        }

        @Override
        public void onClick(@NotNull final ClickEvent event) {

            if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                returnCurrentContentSpec.getContentSpec().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            tagDisplay.display(returnCurrentContentSpec.getContentSpec(), returnReadOnlyMode.getReadOnlyMode());
            bindRemoveButtons.bindRemoveButtons();
        }
    }

    /**
     * The view that holds the other views
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTTextContentSpecV1, RESTTextContentSpecCollectionV1, RESTTextContentSpecCollectionItemV1> {
        PushButton getText();

        PushButton getErrors();

        PushButton getExtendedProperties();

        PushButton getDetails();

        PushButton getSave();

        PushButton getPermissiveSave();

        PushButton getHistory();

        PushButton getContentSpecTags();

        Label getTextDown();

        Label getErrorsDown();

        Label getDetailsDown();

        Label getExtendedPropertiesDown();

        Label getHistoryDown();

        Label getContentSpecTagsDown();

        LogMessageInterface getMessageLogDialog();


    }
}
