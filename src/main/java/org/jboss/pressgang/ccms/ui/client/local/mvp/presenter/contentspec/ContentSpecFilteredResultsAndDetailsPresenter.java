package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTContentSpecCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTopicCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTContentSpecV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter that combines all the content spec presenters.
 */
@Dependent
public class ContentSpecFilteredResultsAndDetailsPresenter
        extends BaseSearchAndEditPresenter<
            RESTContentSpecV1,
            RESTContentSpecCollectionV1,
            RESTContentSpecCollectionItemV1,
            RESTContentSpecV1BasicDetailsEditor> {

    public final static String HISTORY_TOKEN = "ContentSpecFilteredResultsAndContentSpecView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject private Display display;

    @Inject private ContentSpecPresenter contentSpecPresenter;
    @Inject private ContentSpecDetailsPresenter contentSpecDetailsPresenter;
    @Inject private ContentSpecFilteredResultsPresenter filteredResultsPresenter;
    /**
     * The presenter used to display the property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter commonExtendedPropertiesPresenter;
    @Inject
    private ContentSpecRevisionsPresenter contentSpecRevisionsComponent;
    @Inject private ContentSpecTagsPresenter contentSpecTagsPresenter;
    @Inject private ContentSpecErrorPresenter contentSpecErrorsPresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    protected void loadAdditionalDisplayedItemData() {
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId() != null, "The displayed collection item to reference a valid entity with a valid ID.");

        final RESTContentSpecV1 displayedItem =  filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
        final RESTContentSpecV1 selectedItem =  filteredResultsPresenter.getProviderData().getSelectedItem().getItem();

        /*
            Display the list of assigned property tags. This should not be null, but bugs in the REST api can
            lead to the properties collection being null.
        */
        if (displayedItem.getProperties() != null) {
            Collections.sort(displayedItem.getProperties().getItems(), new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
            commonExtendedPropertiesPresenter.refreshExistingChildList(displayedItem);
        }

        /* Get a new collection of property tags. */
        commonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(displayedItem);

        displayPropertyTags();

        this.contentSpecRevisionsComponent.getDisplay().setProvider(this.contentSpecRevisionsComponent.generateListProvider(selectedItem.getId(), display));

        loadTags();
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        final RESTContentSpecV1 displayedItem = getDisplayedContentSpec();

        if (viewIsInFilter(filter, contentSpecDetailsPresenter.getDisplay())) {
            contentSpecDetailsPresenter.getDisplay().displayContentSpecDetails(displayedItem, isReadOnlyMode(), new ArrayList<String>());
        }

        if (viewIsInFilter(filter, contentSpecPresenter.getDisplay())) {
            contentSpecPresenter.getDisplay().display(displayedItem, isReadOnlyMode());
        }

        if (viewIsInFilter(filter, contentSpecErrorsPresenter.getDisplay())) {
            contentSpecErrorsPresenter.getDisplay().display(displayedItem);
        }

        if (viewIsInFilter(filter, contentSpecTagsPresenter.getDisplay())) {
            /*
                Tags are always readonly. Tags associated with a csontent spec
                appear in the spec itself. This is just a secondary way to view them.
             */
            contentSpecTagsPresenter.getDisplay().display(displayedItem, true);
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
            contentSpecRevisionsComponent.getDisplay().display(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), isReadOnlyMode());
        }
    }

    protected void beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        flushChanges();
    }

    @Override
    protected void bindActionButtons() {
        display.getText().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecPresenter.getDisplay());
            }
        }) ;

        display.getErrors().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecErrorsPresenter.getDisplay());
            }
        }) ;

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
        }) ;

        display.getSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hasUnsavedChanges()) {
                    display.getMessageLogDialog().getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().center();
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

        display.getContentSpecTags().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecTagsPresenter.getDisplay());
                }
            }
        });

        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getMessageLogDialog().getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));

                display.getMessageLogDialog().getDialogBox().center();
                display.getMessageLogDialog().getDialogBox().show();
            }
        };

        display.getMessageLogDialog().getOk().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    final String user = display.getMessageLogDialog().getUsername().getText().trim();
                    Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                    final StringBuilder message = new StringBuilder();
                    if (!user.isEmpty()) {
                        message.append(user).append(": ");
                    }
                    message.append(display.getMessageLogDialog().getMessage().getText());
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? ServiceConstants.MINOR_CHANGE : ServiceConstants.MAJOR_CHANGE);

                    final RESTContentSpecV1 displayedEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
                    final Integer id = displayedEntity.getId();

                    /*
                        Save the spec
                     */
                    final BaseRestCallback<RESTContentSpecV1, Display> callback2 =  new BaseRestCallback<RESTContentSpecV1, Display>(
                            display,
                            new BaseRestCallback.SuccessAction<RESTContentSpecV1, Display>() {
                                @Override
                                public void doSuccessAction(@NotNull final RESTContentSpecV1 retValue, @NotNull final Display display) {

                                    boolean overwroteChanges = false;
                                    final Integer originalRevision = displayedEntity.getRevision();

                                    if (retValue.getRevisions() != null && retValue.getRevisions().getItems() != null) {
                                        Collections.sort(retValue.getRevisions().getItems(), new RESTContentSpecCollectionItemV1RevisionSort());

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
                                    retValue.cloneInto(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);
                                    /* Update the selected topic */
                                    retValue.cloneInto(filteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);

                                    initializeViews(new ArrayList<BaseTemplateViewInterface>() {{add(contentSpecPresenter.getDisplay());}});
                                    updateDisplayAfterSave(false);

                                    if (overwroteChanges) {
                                        /* Take the user to the revisions view so they can review any overwritten changes */
                                        switchView(contentSpecRevisionsComponent.getDisplay());
                                        Window.alert(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                    } else {
                                        Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                    }
                                }
                            }
                    );

                    /*
                        Copy out the text
                     */
                    flushChanges();

                    final RESTContentSpecV1 updatedSpec = new RESTContentSpecV1();
                    updatedSpec.setId(id);
                    updatedSpec.explicitSetText(displayedEntity.getText());

                    if (displayedEntity.getProperties() != null) {
                        updatedSpec.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                        updatedSpec.getProperties().setItems(displayedEntity.getProperties().getItems());
                    }

                    /*if (displayedEntity.getTags() != null) {
                        updatedSpec.explicitSetTags(new RESTTagCollectionV1());
                        updatedSpec.getTags().setItems(displayedEntity.getTags().getItems());
                    }*/

                    RESTCalls.updateContentSpec(callback2, updatedSpec, message.toString(), flag, ServiceConstants.NULL_USER_ID.toString());
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();

                    LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
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


    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTContentSpecV1> getNewEntityCallback = new GetNewEntityCallback<RESTContentSpecV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTContentSpecV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTContentSpecV1> displayCallback) {
                @NotNull final RESTCalls.RESTCallback<RESTContentSpecV1> callback = new BaseRestCallback<RESTContentSpecV1, BaseTemplateViewInterface>(
                        display, new BaseRestCallback.SuccessAction<RESTContentSpecV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTContentSpecV1 retValue, @NotNull final BaseTemplateViewInterface display) {
                        checkState(retValue.getProperties() != null, "The returned entity needs to have a valid properties collection");
                        checkState(retValue.getText() != null, "The returned entity needs to have a valid text field");

                        /*
                            If the last save of the content spec was not valid, the text field will display the
                            last valid state, the errors field will be populated, and the failedContentSpec will
                            have the invalid spec text.

                            In this situation, the UI will display the invalid spec text. So we copy the invalid text
                            into the text field, and edit as usual.
                         */

                        if (retValue.getErrors() != null) {
                            retValue.setText(retValue.getFailedContentSpec());
                        }

                        displayCallback.displayNewEntity(retValue);
                    }
                });
                RESTCalls.getContentSpec(callback, selectedEntity.getId());
            }
        };

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);

        contentSpecDetailsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        contentSpecPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);
        contentSpecErrorsPresenter.bindExtended(ServiceConstants.CONTENT_SPEC_TEXT_EDIT_HELP_TOPIC, pageId);
        filteredResultsPresenter.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);
        commonExtendedPropertiesPresenter.bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        contentSpecTagsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);

        /**
         * For now we defer to the tags presenter to get the tags. When bulk importing is implemented, this collection of tags
         * will be loaded in this class and then supplied to the display. This is because both the bul import and the
         * tags views need the same collection of tags, so it makes sense to load the collection once and share it with
         * both presenters.
         */
        contentSpecTagsPresenter.getTags();

        super.bindSearchAndEdit(topicId, pageId, Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, contentSpecPresenter.getDisplay(), contentSpecDetailsPresenter.getDisplay(),
                filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display, getNewEntityCallback);

        bindViewTopicRevisionButton();
    }

    /**
     * Bind behaviour to the view buttons in the topic revisions cell table
     */
    private void bindViewTopicRevisionButton() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton()");

            contentSpecRevisionsComponent.getDisplay().getDiffButton().setFieldUpdater(new FieldUpdater<RESTContentSpecCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTContentSpecCollectionItemV1 revisionContentSpec, final String value) {
                    final RESTCalls.RESTCallback<String> callback = new BaseRestCallback<String, ContentSpecRevisionsPresenter.Display>(
                            contentSpecRevisionsComponent.getDisplay(),
                            new BaseRestCallback.SuccessAction<String, ContentSpecRevisionsPresenter.Display>() {
                                @Override
                                public void doSuccessAction(@NotNull final String retValue, final ContentSpecRevisionsPresenter.Display display) {
                                    checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");

                                    if (getDisplayedContentSpec() != null) {
                                        final String retValueLabel = PressGangCCMSUI.INSTANCE.TopicID()
                                                + ": "
                                                + revisionContentSpec.getItem().getId()
                                                + " "
                                                + PressGangCCMSUI.INSTANCE.ContentSpecRevision()
                                                + ": "
                                                + revisionContentSpec.getItem().getRevision().toString()
                                                + " "
                                                + PressGangCCMSUI.INSTANCE.RevisionDate()
                                                + ": "
                                                + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_FULL).format(revisionContentSpec.getItem().getLastModified());

                                        final String sourceTopicLabel = PressGangCCMSUI.INSTANCE.TopicID()
                                                + ": "
                                                + getDisplayedContentSpec().getId()
                                                + " "
                                                + PressGangCCMSUI.INSTANCE.ContentSpecRevision()
                                                + ": "
                                                + getDisplayedContentSpec().getRevision().toString()
                                                + " "
                                                + PressGangCCMSUI.INSTANCE.RevisionDate()
                                                + ": "
                                                + DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_FULL).format(getDisplayedContentSpec().getLastModified());

                                        GWTUtilities.displayDiff(retValue, retValueLabel, filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getText(), sourceTopicLabel, false);
                                    }
                                }
                            });
                    RESTCalls.getContentSpecTextRevision(callback, revisionContentSpec.getItem().getId(), revisionContentSpec.getItem().getRevision());
                }
            });

            contentSpecRevisionsComponent.getDisplay().getViewButton().setFieldUpdater(new FieldUpdater<RESTContentSpecCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTContentSpecCollectionItemV1 revisionTopic, final String value) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");

                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                        checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");

                        displayRevision(revisionTopic.getItem());

                        contentSpecRevisionsComponent.getDisplay().getProvider().displayAsynchronousList(contentSpecRevisionsComponent.getProviderData().getItems(), contentSpecRevisionsComponent.getProviderData().getSize(), contentSpecRevisionsComponent.getProviderData().getStartRow());
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton() FieldUpdater.update()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewTopicRevisionButton()");
        }
    }

    /**
     * Displays a revision of a topic. This can be called when a revision is selected to be displayed, or if
     * a particular revision is set as the default to be displayed.
     * @param revisionSpec The revision to be displayed.
     */
    private void displayRevision(@NotNull final RESTContentSpecV1 revisionSpec) {
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

            final RESTContentSpecV1 displayedItem = getDisplayedContentSpec();

            checkState(displayedItem.getProperties() != null, "The displayed entity or revision needs to have a valid properties collection");

            /*
                Display the list of assigned property tags. This should not be null, but bugs in the REST api can
                lead to the properties collection being null.
            */

            Collections.sort(displayedItem.getProperties().getItems(), new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
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

            /* Initiate the REST calls */
            final Integer id = getDisplayedContentSpec().getId();
            final Integer revision = getDisplayedContentSpec().getRevision();

            /* If this is a new topic, the id will be null, and there will not be any tags to get */
            if (id != null) {

                /* A callback to respond to a request for a topic with the tags expanded */
                @NotNull final RESTCalls.RESTCallback<RESTContentSpecV1> contentSpecWithTagsCallback = new BaseRestCallback<RESTContentSpecV1, ContentSpecTagsPresenter.Display>(
                        contentSpecTagsPresenter.getDisplay(), new BaseRestCallback.SuccessAction<RESTContentSpecV1, ContentSpecTagsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(@NotNull final RESTContentSpecV1 retValue, final ContentSpecTagsPresenter.Display display) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadTagsAndBugs() topicWithTagsCallback.doSuccessAction()");

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
                            getDisplayedContentSpec().setTags(retValue.getTags());

                            /* update the view */
                            initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{contentSpecTagsPresenter.getDisplay()}));
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadTags() topicWithTagsCallback.doSuccessAction()");
                        }
                    }
                });

                RESTCalls.getContentSpecWithTags(contentSpecWithTagsCallback, id);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadTags()");
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

        this.queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);

        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            /* Make sure that the query string has at least the prefix */
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindSearchAndEditExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        enableAndDisableActionButtons(displayedView);
        setHelpTopicForView(displayedView);

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
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
        }  else if (displayedView == this.commonExtendedPropertiesPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getExtendedProperties(), this.display.getExtendedPropertiesDown());
        }  else if (displayedView == this.contentSpecRevisionsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getHistory(), this.display.getHistoryDown());
        }  else if (displayedView == this.contentSpecTagsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getContentSpecTags(), this.display.getContentSpecTagsDown());
        }  else if (displayedView == this.contentSpecErrorsPresenter.getDisplay()) {
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
    private RESTContentSpecV1 getDisplayedContentSpec() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.getDisplayedContentSpec()");

            RESTContentSpecV1 source = null;

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

            /* These are read only views */
            if (lastDisplayedView == contentSpecTagsPresenter.getDisplay()) {
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

            final RESTContentSpecV1 displayedEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
            final RESTContentSpecV1 selectedEntity = filteredResultsPresenter.getProviderData().getSelectedItem().getItem();

             /*
                If there are any modified tags in newTopic, we have unsaved changes.
                If getTags() is null, the tags have not been loaded yet (and can't have been modified).
            */
            /*if (displayedEntity.getTags() != null &&
                    !displayedEntity.getTags().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }*/

            /* If there are any modified property tags in newTopic, we have unsaved changes */
            if (!displayedEntity.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty()) {
                return true;
            }

            /* See if the text has changed */
            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedEntity.getText(), displayedEntity.getText())) {
                return true;
            }

            return false;
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.hasUnsavedChanges()");
        }
    }

    /**
     * The view that holds the other views
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTContentSpecV1, RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> {
        PushButton getText();

        PushButton getErrors();

        PushButton getExtendedProperties();

        PushButton getDetails();

        PushButton getSave();

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
