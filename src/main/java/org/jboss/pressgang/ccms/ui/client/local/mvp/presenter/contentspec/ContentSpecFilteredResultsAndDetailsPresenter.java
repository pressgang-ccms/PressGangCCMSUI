/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AnchorButton;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTTextContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTTextContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerExtendedV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSTranslationDetailV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceivedHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.FreezePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.TranslationPushPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.TranslationSyncPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.ReadOnlyPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTLocaleV1Sort;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTTextContentSpecCollectionItemV1RevisionSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTTextContentSpecV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter that combines all the content spec presenters.
 */
@Dependent
public class ContentSpecFilteredResultsAndDetailsPresenter extends BaseSearchAndEditPresenter<RESTTextContentSpecV1,
        RESTTextContentSpecCollectionV1, RESTTextContentSpecCollectionItemV1, RESTTextContentSpecV1BasicDetailsEditor> implements ReadOnlyPresenter {

    public final static String HISTORY_TOKEN = "ContentSpecFilteredResultsAndContentSpecView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecFilteredResultsAndDetailsPresenter.class.getName());

    private HandlerRegistration keyboardEventHandler = null;

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
        True when the processes tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean processesLoadInitiated = false;
    /*
        True when the revisions tab is opened for the first time, and the
        elements are loaded.
     */
    private boolean revisionsLoadInitiated = false;

    /**
     * true if this presenter should be opened with a fresh topic, and false otherwise
     */
    private boolean startWithNewSpec = false;

    /**
     * A list of locales retrieved from the server
     */
    private List<RESTLocaleV1> locales;
    private List<RESTTranslationServerV1> translationServers;

    /**
     * true after the default locale has been loaded
     */
    @Nullable
    private RESTLocaleV1 defaultLocale = null;

    /**
     * true after the topics have been loaded
     */
    private boolean contentSpecListLoaded = false;

    /**
     * When a new topic is created, it is populated with a default template. The
     * default template is saved in this property, which is then used to
     * determine if any changes were made.
     */
    private String lastNewContentSpecTemplate;

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
    private ContentSpecTranslationDetailsPresenter contentSpecTranslationDetailsPresenter;
    @Inject
    private ContentSpecFilteredResultsPresenter filteredResultsPresenter;
    /**
     * The presenter used to display the property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter commonExtendedPropertiesPresenter;
    @Inject
    private ContentSpecRevisionsPresenter contentSpecRevisionsPresenter;
    @Inject
    private ContentSpecTagsPresenter contentSpecTagsPresenter;
    @Inject
    private ContentSpecErrorPresenter contentSpecErrorsPresenter;
    @Inject
    private ContentSpecProcessPresenter contentSpecProcessPresenter;

    @Inject
    private TranslationPushPresenter translationPushPresenter;
    @Inject
    private TranslationSyncPresenter translationSyncPresenter;
    @Inject
    private FreezePresenter freezePresenter;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    private String action;

    private boolean displayingSearchResults = true;

    private Integer lastVisibleRow = null;

    /**
     * A web worker that is used to check the age of each topic and highlight
     * them in the gutter.
     */
    private JavaScriptObject lastEditWorker;
    /**
     * A reference to any pending timeout call to update the text in the workers.
     */
    private JavaScriptObject textUpdaterTimeout;

    private final ActionCompletedCallback<RESTProcessInformationV1> completedProcessCallback = new
            ActionCompletedCallback<RESTProcessInformationV1>() {
        @Override
        public void success(final RESTProcessInformationV1 restProcessInformationV1) {
            // Update the process list if it has been initialised
            if (processesLoadInitiated) {
                processesLoadInitiated = false;
                loadProcesses();
            }
        }

        @Override
        public void failure() {
            // Do nothing
        }
    };

    private final ActionCompletedCallback<RESTTextContentSpecV1> completedFreezeCallback = new
            ActionCompletedCallback<RESTTextContentSpecV1>() {
        @Override
        public void success(final RESTTextContentSpecV1 retValue) {
            final boolean newSpec = !retValue.getId().equals(getDisplayedContentSpec().getId());

            // Create the contentspec wrapper
            final RESTTextContentSpecCollectionItemV1 contentSpecCollectionItem = new
                    RESTTextContentSpecCollectionItemV1();
            contentSpecCollectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);

            // create the content spec, and add to the wrapper
            contentSpecCollectionItem.setItem(retValue);

            // Update the displayed content spec
            filteredResultsPresenter.getProviderData().setDisplayedItem(contentSpecCollectionItem.clone(true));
            filteredResultsPresenter.setSelectedItem(contentSpecCollectionItem);

            if (newSpec) {
                // We need to swap the text with the invalid text
                ComponentContentSpecV1.fixDisplayedText(filteredResultsPresenter.getProviderData().getSelectedItem().getItem());

                filteredResultsPresenter.getProviderData().getItems().add(contentSpecCollectionItem);
                filteredResultsPresenter.getProviderData().setSize(filteredResultsPresenter.getProviderData().getItems().size());

                setSearchResultsVisible(true);
            }

            initializeViews(new ArrayList<BaseTemplateViewInterface>() {{
                add(contentSpecPresenter.getDisplay());
            }});
            updateDisplayWithNewEntityData(false);
        }

        @Override
        public void failure() {
            // Do nothing
        }
    };

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

    @NotNull
    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");

            final RESTTextContentSpecCollectionItemV1 displayedItem = filteredResultsPresenter.getProviderData().getDisplayedItem();
            checkState(displayedItem != null, "There should be a displayed collection item.");
            checkState(displayedItem.getItem() != null, "The displayed collection item to reference a valid entity.");
            checkState(displayedItem.returnIsAddItem() || displayedItem.getItem().getId() != null,
                    "The displayed collection item to reference a valid entity with a valid ID.");

            // Make the window title display the id of the content spec
            if (displayedItem.getItem().getId() != null && displayedItem.getItem().getTitle() != null) {
                GWTUtilities.setBrowserWindowTitle(
                        "CS " + displayedItem.getItem().getId() + " - " + displayedItem.getItem().getTitle() + " - " + PressGangCCMSUI
                                .INSTANCE.PressGangCCMS());
            } else if (displayedItem.getItem().getId() != null) {
                GWTUtilities.setBrowserWindowTitle(
                        "CS " + displayedItem.getItem().getId() + " - " + PressGangCCMSUI.INSTANCE.PressGangCCMS());
            } else {
                GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.New() + " - " + PressGangCCMSUI.INSTANCE.PressGangCCMS());
            }

            if (displayedItem.getItem().getId() != null) {
                // Set the href for the docbuilder url
                getServerSettings(new ServerSettingsCallback() {
                    @Override
                    public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 serverSettings) {
                        String docBuilderUrl = serverSettings.getDocBuilderUrl();
                        docBuilderUrl = docBuilderUrl == null ? Constants.DOCBUILDER_SERVER : docBuilderUrl;
                        if (!docBuilderUrl.endsWith("/")) {
                            docBuilderUrl += "/";
                        }
                        display.getViewInDocBuilder().setHref(docBuilderUrl + displayedItem.getItem().getId());
                        display.getViewInDocBuilder().setTarget("_blank");
                    }
                });
            }

            /* Disable the topic revision view */
            viewLatestSpecRevision();

            contentSpecRevisionsPresenter.reset();
            contentSpecProcessPresenter.reset();
            processesLoadInitiated = false;
            revisionsLoadInitiated = false;
            tagsLoadInitiated = false;
            propertyTagsLoadInitiated = false;
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

            isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(final boolean readOnly) {
                    if (viewIsInFilter(filter, contentSpecDetailsPresenter.getDisplay())) {
                        contentSpecDetailsPresenter.getDisplay().displayContentSpecDetails(displayedItem, readOnly, locales);
                    }

                    if (viewIsInFilter(filter, contentSpecTranslationDetailsPresenter.getDisplay())) {
                        contentSpecTranslationDetailsPresenter.getDisplay().displayContentSpecTranslationDetails(displayedItem, readOnly,
                                locales, translationServers);
                    }

                    if (viewIsInFilter(filter, contentSpecPresenter.getDisplay())) {
                        contentSpecPresenter.getDisplay().display(displayedItem, readOnly);
                    }

                    if (viewIsInFilter(filter, contentSpecErrorsPresenter.getDisplay())) {
                        contentSpecErrorsPresenter.getDisplay().display(displayedItem);
                    }

                    if (viewIsInFilter(filter, contentSpecTagsPresenter.getDisplay())) {
                        contentSpecTagsPresenter.getDisplay().display(displayedItem, readOnly);
                        bindTagEditingButtons();
                    }

                    if (viewIsInFilter(filter, commonExtendedPropertiesPresenter.getDisplay())) {
                        commonExtendedPropertiesPresenter.getDisplay().display(displayedItem, readOnly);
                        commonExtendedPropertiesPresenter.displayDetailedChildrenExtended(displayedItem, readOnly);
                        commonExtendedPropertiesPresenter.refreshExistingChildList(displayedItem);
                    }

                    /*
                        The revision display always displays details from the main topic, and not the selected revision.
                    */
                    if (viewIsInFilter(filter, contentSpecRevisionsPresenter.getDisplay())) {
                        LOGGER.log(Level.INFO, "\tInitializing content spec revisions view");
                        contentSpecRevisionsPresenter.getDisplay().display(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),readOnly);
                        contentSpecRevisionsPresenter.refreshList();
                        // make sure the revisions list is displayed and not the diff view if it ws previously open
                        if (!contentSpecRevisionsPresenter.getDisplay().isDisplayingRevisions()) {
                            contentSpecRevisionsPresenter.getDisplay().displayRevisions();
                        }
                    }

                    if (viewIsInFilter(filter, contentSpecProcessPresenter.getDisplay())) {
                        contentSpecProcessPresenter.refreshList();
                    }

                    /* Redisplay the editor. contentSpecPresenter.getDisplay().getEditor() will be not null after the display method was called
                    above */
                    if (viewIsInFilter(filter, contentSpecPresenter.getDisplay())) {
                        LOGGER.log(Level.INFO, "\tSetting topic XML edit button state and redisplaying ACE editor");
                        contentSpecPresenter.loadEditorSettings();
                        contentSpecPresenter.getDisplay().getEditor().redisplay();
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.initializeViews()");
        }
    }

    protected boolean beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.beforeSwitchView()");
        flushChanges();
        if (lastDisplayedView == contentSpecPresenter.getDisplay()) {
            lastVisibleRow = contentSpecPresenter.getDisplay().getEditor().getFirstVisibleRow();
        }
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

        display.getTranslationDetails().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(contentSpecTranslationDetailsPresenter.getDisplay());
            }
        });

        final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                saveContentSpec();
            }
        };

        final ClickHandler revisionsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecRevisionsPresenter.getDisplay());
                }
            }
        };

        final ClickHandler revisionDoneClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (getDisplayedContentSpec().getRevision() == filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                        .getRevision()) {
                    checkState(contentSpecRevisionsPresenter.getDisplay().getMergely() != null, "mergely should not be null");
                    final String rhs = contentSpecRevisionsPresenter.getDisplay().getMergely().getRhs();
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setText(rhs);
                    initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{contentSpecPresenter.getDisplay(),
                            contentSpecTranslationDetailsPresenter.getDisplay(),
                            contentSpecRevisionsPresenter.getDisplay()}));
                }
                contentSpecRevisionsPresenter.getDisplay().displayRevisions();
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        getDisplay().getSave().setEnabled(!readOnly);
                        getDisplay().getActionsMenu().setEnabled(!readOnly);
                    }
                });
            }
        };

        final ClickHandler revisionCancelClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                contentSpecRevisionsPresenter.getDisplay().displayRevisions();

                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(boolean readOnly) {
                        getDisplay().getSave().setEnabled(!readOnly);
                        getDisplay().getActionsMenu().setEnabled(!readOnly);
                    }
                });

            }
        };

        final ClickHandler tagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecTagsPresenter.getDisplay());
                }
            }
        };

        final ClickHandler processesClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                    switchView(contentSpecProcessPresenter.getDisplay());
                }
            }
        };

        final ClickHandler logMessageOkClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                final String user = display.getMessageLogDialog().getUsername().getText().trim();

                if (user.isEmpty()) {
                    display.getMessageLogDialog().getDialogBox().hide();
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.UsernameMissing(), new CloseHandler() {
                        @Override
                        public void onClose(CloseEvent event) {
                            display.getMessageLogDialog().getDialogBox().center();
                        }
                    });
                    return;
                }

                try {
                    LOGGER.log(Level.INFO,
                            "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");

                    Preferences.INSTANCE.saveSetting(Preferences.LOG_MESSAGE_USERNAME, user);

                    final StringBuilder message = new StringBuilder();
                    if (!user.isEmpty()) {
                        message.append(user).append(": ");
                    }
                    message.append(display.getMessageLogDialog().getMessage().getText());
                    final Integer flag = (int) (display.getMessageLogDialog().getMinorChange().getValue() ? RESTLogDetailsV1.MINOR_CHANGE_FLAG_BIT : RESTLogDetailsV1.MAJOR_CHANGE_FLAG_BIT);

                    final RESTTextContentSpecV1 displayedEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
                    final RESTTextContentSpecV1 selectedEntity;
                    if (filteredResultsPresenter.getProviderData().getSelectedItem() != null) {
                        selectedEntity = filteredResultsPresenter.getProviderData().getSelectedItem().getItem();
                    } else {
                        selectedEntity = null;
                    }
                    final Integer id = displayedEntity.getId();

                    // Copy out the text
                    flushChanges();

                    // create the object to be saved
                    final RESTTextContentSpecV1 updatedSpec = new RESTTextContentSpecV1();
                    if (selectedEntity == null || selectedEntity.getText() == null || !selectedEntity.getText().equals(displayedEntity.getText())) {
                        updatedSpec.explicitSetText(displayedEntity.getText());
                    }
                    updatedSpec.setProcessingOptions(displayedEntity.getProcessingOptions());
                    updatedSpec.explicitSetLocale(displayedEntity.getLocale());

                    if (displayedEntity.getProperties() != null) {
                        updatedSpec.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());
                        updatedSpec.getProperties().setItems(displayedEntity.getProperties().getItems());
                    }

                    if (displayedEntity.getTags() != null) {
                        updatedSpec.explicitSetTags(new RESTTagCollectionV1());
                        updatedSpec.getTags().setItems(displayedEntity.getTags().getItems());
                    }

                    if (displayedEntity.getTranslationDetails() != null) {
                        final RESTCSTranslationDetailV1 displayedDetails = displayedEntity.getTranslationDetails();
                        final RESTCSTranslationDetailV1 currentDetails = selectedEntity.getTranslationDetails();

                        final RESTCSTranslationDetailV1 translationDetail = new RESTCSTranslationDetailV1();
                        translationDetail.setId(displayedDetails.getId());

                        if (currentDetails != null) {
                            // Updated translation details
                            if (!GWTUtilities.booleanEquals(displayedDetails.isEnabled(), currentDetails.isEnabled())) {
                                translationDetail.explicitSetEnabled(displayedDetails.isEnabled());
                            }

                            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(displayedDetails.getProject(),
                                    currentDetails.getProject())) {
                                translationDetail.explicitSetProject(displayedDetails.getProject());
                            }

                            if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(displayedDetails.getProjectVersion(),
                                    currentDetails.getProjectVersion())) {
                                translationDetail.explicitSetProjectVersion(displayedDetails.getProjectVersion());
                            }

                            if (!GWTUtilities.objectEquals(displayedDetails.getTranslationServer(),
                                    currentDetails.getTranslationServer())) {
                                translationDetail.explictSetTranslationServer(displayedDetails.getTranslationServer());
                            }

                            if (displayedDetails.getLocales() != null && displayedDetails.getLocales()
                                    .returnDeletedAddedAndUpdatedCollectionItems().size() > 0) {
                                translationDetail.explicitSetLocales(displayedDetails.getLocales());
                            }
                        } else {
                            // New translation details
                            if (displayedDetails.isEnabled()) {
                                translationDetail.explicitSetEnabled(displayedDetails.isEnabled());
                            }
                            if (displayedDetails.getTranslationServer() != null) {
                                translationDetail.explictSetTranslationServer(displayedDetails.getTranslationServer());
                            }
                            if (displayedDetails.getLocales() != null && displayedDetails.getLocales().getItems().size() > 0) {
                                translationDetail.explicitSetLocales(displayedDetails.getLocales());
                            }

                            if (translationDetail.getConfiguredParameters() != null
                                    && !translationDetail.getConfiguredParameters().isEmpty()) {
                                translationDetail.explicitSetProject(displayedDetails.getProject());
                                translationDetail.explicitSetProjectVersion(displayedDetails.getProjectVersion());
                            }
                        }

                        if (translationDetail.getConfiguredParameters() != null
                                && !translationDetail.getConfiguredParameters().isEmpty()) {
                            updatedSpec.explicitSetTranslationDetails(translationDetail);
                        }
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
                                    contentSpecCollectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);

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
                                    ComponentContentSpecV1.fixDisplayedText(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());


                                    if (startWithNewSpec) {
                                        LOGGER.log(Level.INFO, "Adding new content spec to static list");

                                        // We need to swap the text with the invalid text
                                        ComponentContentSpecV1.fixDisplayedText(filteredResultsPresenter.getProviderData().getSelectedItem().getItem());

                                        filteredResultsPresenter.getProviderData().getItems().add(contentSpecCollectionItem);
                                        filteredResultsPresenter.getProviderData().setSize(filteredResultsPresenter.getProviderData().getItems().size());
                                        updateDisplayWithNewEntityData(false);
                                    } else {
                                        // Update the selected topic
                                        LOGGER.log(Level.INFO, "Redisplaying query");

                                        // When the list is repopulated, the text will be swapped with the invalid text
                                        updateDisplayWithNewEntityData(true);
                                    }

                                    initializeViews(Arrays.<BaseTemplateViewInterface>asList(contentSpecPresenter.getDisplay(),
                                            contentSpecTranslationDetailsPresenter.getDisplay()));

                                    if (!isStringNullOrEmpty(retValue.getFailedContentSpec())) {
                                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ContentSpecSaveSuccessWithID() + " " + retValue.getId() + "" +
                                                ".\n\n" + PressGangCCMSUI.INSTANCE.ContentSpecSaveSuccessWithErrorsPostFix(), new CloseHandler() {
                                            @Override
                                            public void onClose(CloseEvent event) {
                                                // Take the user to the errors view so they can review any error messages
                                                switchView(contentSpecErrorsPresenter.getDisplay());
                                            }
                                        });
                                    } else {
                                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.ContentSpecSaveSuccessWithID() + " " + retValue.getId());
                                    }
                                } finally {
                                    LOGGER.log(Level.INFO,
                                            "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK" +
                                                    ".onClick() addCallback.doSuccessAction() - New Topic");
                                }
                            }
                        };

                        getServerSettings(new ServerSettingsCallback() {
                            @Override
                            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                                getFailOverRESTCall().performRESTCall(
                                        FailOverRESTCallDatabase.createTextContentSpec(updatedSpec, message.toString(), flag,
                                                serverSettings.getEntities().getUnknownUserId().toString()), addCallback, display);
                            }
                        });
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

                                initializeViews(Arrays.<BaseTemplateViewInterface>asList(contentSpecPresenter.getDisplay(),
                                        contentSpecTranslationDetailsPresenter.getDisplay()));
                                updateDisplayWithNewEntityData(false);

                                if (overwroteChanges) {
                                    // Take the user to the revisions view so they can review any overwritten changes
                                    switchView(contentSpecRevisionsPresenter.getDisplay());
                                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.OverwriteSuccess());
                                } else if (!isStringNullOrEmpty(retValue.getFailedContentSpec())) {
                                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess() + "\n\n" + PressGangCCMSUI.INSTANCE.ContentSpecSaveSuccessWithErrorsPostFix(), new CloseHandler() {
                                        @Override
                                        public void onClose(CloseEvent event) {
                                            switchView(contentSpecErrorsPresenter.getDisplay());
                                        }
                                    });
                                } else {
                                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                }
                            }
                        };

                        getServerSettings(new ServerSettingsCallback() {
                            @Override
                            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                                getFailOverRESTCall().performRESTCall(
                                        FailOverRESTCallDatabase.updateTextContentSpec(updatedSpec, message.toString(), flag,
                                                serverSettings.getEntities().getUnknownUserId().toString()), updateCallback, display);
                            }
                        });
                    }
                } finally {
                    display.getMessageLogDialog().reset();
                    display.getMessageLogDialog().getDialogBox().hide();
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setProcessingOptions(null);

                    LOGGER.log(Level.INFO,
                            "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() messageLogDialogOK.onClick()");
                }
            }
        };

        final ClickHandler showHideSearchResults = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                setSearchResultsVisible(!isDisplayingSearchResults());
            }
        };

        final ClickHandler logMessageCancelClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().hide();
            }
        };

        // Hook up the xml editor settings button
        contentSpecPresenter.getDisplay().getEditorSettings().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (getDisplay().getTopLevelPanel().isAttached() && getDisplay().isViewShown() && !isAnyDialogBoxesOpen(
                        contentSpecPresenter.getDisplay())) {
                    contentSpecPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().center();
                    contentSpecPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().show();
                }
            }
        });

        final Command pushTranslationCommand = new Command() {
            @Override
            public void execute() {
                if (hasUnsavedChanges()) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotProceedWithUnsavedChanges());
                } else if (getDisplayedContentSpec().getTranslationDetails() == null
                        || getDisplayedContentSpec().getTranslationDetails().getTranslationServer() == null) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotProceedWithoutTranslationDetails());
                } else {
                    getServerSettings(new ServerSettingsCallback() {
                        @Override
                        public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                            final ActionCompletedCallback<RESTTextContentSpecV1> callback = new
                                    ActionCompletedCallback<RESTTextContentSpecV1>() {
                                @Override
                                public void success(RESTTextContentSpecV1 restTextContentSpecV1) {
                                    if (ComponentContentSpecV1.hasTag(getDisplayedContentSpec(), serverSettings.getEntities().getFrozenTagId())) {
                                        translationPushPresenter.display(getDisplayedContentSpec(), display);
                                        translationPushPresenter.addActionCompletedHandler(completedProcessCallback);
                                    } else {
                                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotPushUnfrozenContentSpec());
                                    }
                                }

                                @Override
                                public void failure() {
                                    // Not used so do nothing
                                }
                            };

                            if (!tagsLoadInitiated) {
                                loadTags(callback, getDisplay());
                            } else {
                                callback.success(getDisplayedContentSpec());
                            }
                        }
                    });
                }
            }
        };

        final Command syncTranslationCommand = new Command() {
            @Override
            public void execute() {
                if (hasUnsavedChanges()) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotProceedWithUnsavedChanges());
                } else if (getDisplayedContentSpec().getTranslationDetails() == null
                        || getDisplayedContentSpec().getTranslationDetails().getTranslationServer() == null) {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotProceedWithoutTranslationDetails());
                } else {
                    translationSyncPresenter.display(getDisplayedContentSpec(), display);
                    translationSyncPresenter.addActionCompletedHandler(completedProcessCallback);
                }
            }
        };

        final Command freezeCommand = new Command() {
            @Override
            public void execute() {
                if (!hasUnsavedChanges()) {
                    freezePresenter.display(getDisplayedContentSpec(), display);
                    freezePresenter.addActionCompletedHandler(completedFreezeCallback);
                } else {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.CanNotProceedWithUnsavedChanges());
                }
            }
        };

        display.getSave().addClickHandler(saveClickHandler);
        display.getHistory().addClickHandler(revisionsClickHandler);
        display.getContentSpecTags().addClickHandler(tagsClickHandler);
        display.getProcesses().addClickHandler(processesClickHandler);
        display.getMessageLogDialog().getOk().addClickHandler(logMessageOkClickHandler);
        display.getPushTranslation().setScheduledCommand(pushTranslationCommand);
        display.getSyncTranslation().setScheduledCommand(syncTranslationCommand);
        display.getFreeze().setScheduledCommand(freezeCommand);
        contentSpecRevisionsPresenter.getDisplay().getDone().addClickHandler(revisionDoneClickHandler);
        contentSpecRevisionsPresenter.getDisplay().getCancel().addClickHandler(revisionCancelClickHandler);
        display.getMessageLogDialog().getCancel().addClickHandler(logMessageCancelClickHandler);
        getDisplay().getShowHideSearchResults().addClickHandler(showHideSearchResults);
    }

    private void saveContentSpec() {
        if (!AlertBox.isDisplayed() && !display.getMessageLogDialog().getDialogBox().isShowing()) {
            if (hasUnsavedChanges()) {
                display.getMessageLogDialog().getUsername().setText(
                        Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));
                display.getMessageLogDialog().reset();
                display.getMessageLogDialog().getDialogBox().center();
            } else {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
            }
        }
    }

    private void setSearchResultsVisible(final boolean visible) {
        if (visible != isDisplayingSearchResults()) {
            setDisplayingSearchResults(visible);
            if (visible) {
                getDisplay().showSearchResults();
            } else {
                getDisplay().hideSearchResults();
            }

            /*
                Elements like the ace editor and mergely diff viewer need to get
                an onResize event so they can be sized appropriately. For reasons that
                I have not yet worked out, this can only be done after control has been
                handed back to the browser loop (which is when scheduleDeferred runs).
             */
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() ScheduledCommand" + ".execute()");
                        getDisplay().getSplitPanel().onResize();
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindActionButtons() ScheduledCommand" + ".execute()");
                    }
                }
            });
        }
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

            // Set the initial text from a template defined in a string constant.
            final RESTCallBack<RESTStringConstantV1> callback = new RESTCallBack<RESTStringConstantV1>() {
                @Override
                public void success(@NotNull final RESTStringConstantV1 retValue) {
                    // Create the content spec wrapper
                    final RESTTextContentSpecCollectionItemV1 contentSpecCollectionItem = new RESTTextContentSpecCollectionItemV1();
                    contentSpecCollectionItem.setState(RESTBaseEntityCollectionItemV1.ADD_STATE);

                    // create the content spec, and add to the wrapper
                    final RESTTextContentSpecV1 newEntity = new RESTTextContentSpecV1();
                    newEntity.setTags(new RESTTagCollectionV1());
                    newEntity.setProperties(new RESTAssignedPropertyTagCollectionV1());
                    newEntity.setText(retValue.getValue().trim());
                    newEntity.setLocale(defaultLocale);
                    contentSpecCollectionItem.setItem(newEntity);

                    // make a note of the default template. this is used to ensure that if
                    // no changes are made to the topic beyond the default template, the unsaved
                    // changes warning does not appear.
                    lastNewContentSpecTemplate = retValue.getValue().trim();

                    // the content spec won't show up in the list of content specs until it is saved, so the
                    // selected item is null
                    filteredResultsPresenter.setSelectedItem(null);

                    // the new content spec is being displayed though, so we set the displayed item
                    filteredResultsPresenter.getProviderData().setDisplayedItem(contentSpecCollectionItem);

                    updateViewsAfterNewEntityLoaded();
                }
            };

            getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                    getFailOverRESTCall().performRESTCall(
                            FailOverRESTCallDatabase.getStringConstant(serverSettings.getEntities().getContentSpecTemplateId()),
                            callback, display);
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.createNewTopic()");
        }
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTTextContentSpecV1> getNewEntityCallback = new GetNewEntityCallback<RESTTextContentSpecV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTextContentSpecV1 selectedEntity,
                    @NotNull final DisplayNewEntityCallback<RESTTextContentSpecV1> displayCallback) {

                final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                        checkState(retValue.getText() != null, "The returned entity needs to have a valid text field");

                        ComponentContentSpecV1.fixDisplayedText(retValue);
                        displayCallback.displayNewEntity(retValue);

                        if ("Freeze".equalsIgnoreCase(action)) {
                            freezePresenter.display(retValue, getDisplay());
                            freezePresenter.addActionCompletedHandler(completedFreezeCallback);
                        } else if ("TranslationPush".equalsIgnoreCase(action)) {
                            translationPushPresenter.display(retValue, getDisplay());
                            translationPushPresenter.addActionCompletedHandler(completedProcessCallback);
                        } else if ("TranslationSync".equalsIgnoreCase(action)) {
                            translationSyncPresenter.display(retValue, getDisplay());
                            translationSyncPresenter.addActionCompletedHandler(completedProcessCallback);
                        }
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getContentSpec(selectedEntity.getId()), callback, display);
            }
        };

        contentSpecDetailsPresenter.bindExtended();
        contentSpecTranslationDetailsPresenter.bindExtended();
        contentSpecPresenter.bindExtended();
        contentSpecErrorsPresenter.bindExtended();
        filteredResultsPresenter.bindExtendedFilteredResults(queryString);
        commonExtendedPropertiesPresenter.bindDetailedChildrenExtended();
        contentSpecTagsPresenter.bindExtended();
        contentSpecProcessPresenter.bindExtended();

        super.bindSearchAndEdit(Preferences.CONTENT_SPEC_VIEW_MAIN_SPLIT_WIDTH, contentSpecPresenter.getDisplay(),
                contentSpecDetailsPresenter.getDisplay(), filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display,
                getNewEntityCallback);

        bindTagButtons();

        bindViewContentSpecRevisionButton();

        addKeyboardShortcutEventHandler();

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

        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                locales = serverSettings.getLocales().returnItems();
                Collections.sort(locales, new RESTLocaleV1Sort());
                defaultLocale = serverSettings.getDefaultLocale();

                // Copy the translation servers
                translationServers = new ArrayList<RESTTranslationServerV1>();
                for (final RESTTranslationServerExtendedV1 item : serverSettings.getTranslationServers().returnItems()) {
                    final RESTTranslationServerV1 translationServer = new RESTTranslationServerV1();
                    item.cloneInto(translationServer, true);
                    translationServers.add(translationServer);
                }

                displayNewContentSpec();
                displayInitialContentSpec(getNewEntityCallback);
            }
        });

        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull ServerDetails serverDetails) {
                createWorkers(serverDetails.getRestEndpoint());
            }
        });

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                display.getSave().setEnabled(!readOnly);
                display.getActionsMenu().setEnabled(!readOnly);
            }
        });

        buildLegend();
    }

    private void disableButtonsInReadonlyMode() {
        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                display.getSave().setEnabled(!readOnly);
                display.getActionsMenu().setEnabled(!readOnly);
            }
        });

        super.isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                filteredResultsPresenter.getDisplay().getCreate().setEnabled(!readOnly);
            }
        });
    }

    @Override
    protected void disableTopShortcutButtonsInReadOnlyMode() {
        super.isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                getDisplay().getTopShortcutView().getCreateContentSpec().setEnabled(!readOnly);
                getDisplay().getTopShortcutView().getCreateTopic().setEnabled(!readOnly);
            }
        });
    }

    /**
     * Builds the legend at the bottom of the screen
     */
    private void buildLegend() {
        /*
            The horizontal panel is used to vertically align the legend items
         */
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel.addStyleName(CSSConstants.Legend.LEGEND_PARENT_PANEL);
        getDisplay().getFooterPanelCustomContent().setWidget(horizontalPanel);

        final PushButton showLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ShowLegend());
        showLegend.addStyleName(CSSConstants.Legend.LEGEND);

        final PushButton hideLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HideLegend());
        hideLegend.addStyleName(CSSConstants.Legend.LEGEND);

        final FlowPanel legendPanel = new FlowPanel();
        legendPanel.addStyleName(CSSConstants.Legend.LEGEND_PANEL);

        final Label styleGuide = new Label(PressGangCCMSUI.INSTANCE.StyleGuideMatch());
        styleGuide.addStyleName(CSSConstants.Legend.TAG_MATCH_LEGEND);
        legendPanel.add(styleGuide);

        final Label oneDay = new Label(PressGangCCMSUI.INSTANCE.EditedOneDay());
        oneDay.addStyleName(CSSConstants.Legend.EDITED_ONE_DAY_LEGEND);
        legendPanel.add(oneDay);

        final Label oneWeek = new Label(PressGangCCMSUI.INSTANCE.EditedOneWeek());
        oneWeek.addStyleName(CSSConstants.Legend.EDITED_ONE_WEEK_LEGEND);
        legendPanel.add(oneWeek);

        final Label oneMonth = new Label(PressGangCCMSUI.INSTANCE.EditedOneMonth());
        oneMonth.addStyleName(CSSConstants.Legend.EDITED_ONE_MONTH_LEGEND);
        legendPanel.add(oneMonth);

        final Label oneYear = new Label(PressGangCCMSUI.INSTANCE.EditedOneYear());
        oneYear.addStyleName(CSSConstants.Legend.EDITED_ONE_YEAR_LEGEND);
        legendPanel.add(oneYear);

        final Label older = new Label(PressGangCCMSUI.INSTANCE.EditedOlder());
        older.addStyleName(CSSConstants.Legend.EDITED_OLDER_LEGEND);
        legendPanel.add(older);

        if (Preferences.INSTANCE.getBoolean(Preferences.SHOW_LEGEND, true)) {
            horizontalPanel.add(legendPanel);
            horizontalPanel.add(hideLegend);
        } else {
            horizontalPanel.add(showLegend);
        }

        hideLegend.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_LEGEND, false);
                horizontalPanel.clear();
                horizontalPanel.add(showLegend);
            }
        });

        showLegend.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_LEGEND, true);
                horizontalPanel.clear();
                horizontalPanel.add(legendPanel);
                horizontalPanel.add(hideLegend);
            }
        });
    }

    /**
     * When the locales and the topic list have been loaded we can display the first topic if only
     * one was returned.
     */
    protected void displayInitialContentSpec(@NotNull final GetNewEntityCallback<RESTTextContentSpecV1> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");

            if (isInitialContentSpecReadyToBeLoaded() &&
                    filteredResultsPresenter.getProviderData().getItems() != null &&
                    filteredResultsPresenter.getProviderData().getItems().size() == 1) {
                loadNewEntity(getNewEntityCallback, filteredResultsPresenter.getProviderData().getItems().get(0));
                setSearchResultsVisible(false);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayInitialContentSpec()");
        }
    }

    private boolean isInitialContentSpecReadyToBeLoaded() {
        /* only proceed loading the initial topic when the locales and the topic list have been loaded */
        return contentSpecListLoaded;
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

            contentSpecRevisionsPresenter.getDisplay().getDiffButton().setFieldUpdater(
                    new FieldUpdater<RESTTextContentSpecCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTextContentSpecCollectionItemV1 revisionContentSpec,
                                final String value) {
                            checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");
                            final RESTTextContentSpecV1 contentSpec = revisionContentSpec.getItem();

                            /*
                                It is possible to switch away from the view while this request was loading. If we
                                have done so, don't show the merge view.
                             */
                            if (lastDisplayedView == contentSpecRevisionsPresenter.getDisplay()) {
                                final boolean rhsReadonly = getDisplayedContentSpec().getRevision() != filteredResultsPresenter
                                        .getProviderData().getDisplayedItem().getItem().getRevision();

                                // Fix the displayed text up
                                ComponentContentSpecV1.fixDisplayedText(contentSpec);

                                // Display the diffs
                                contentSpecRevisionsPresenter.getDisplay().displayDiff(contentSpec.getText(), rhsReadonly,
                                        getDisplayedContentSpec().getText());

                                // We can't save while merging.
                                getDisplay().getSave().setEnabled(false);
                                getDisplay().getActionsMenu().setEnabled(false);
                            }

                            contentSpecRevisionsPresenter.getDisplay().setButtonsEnabled(true);
                        }
                    });

            contentSpecRevisionsPresenter.getDisplay().getViewButton().setFieldUpdater(
                    new FieldUpdater<RESTTextContentSpecCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTextContentSpecCollectionItemV1 revisionTopic,
                                final String value) {

                            try {
                                LOGGER.log(Level.INFO,
                                        "ENTER ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton() " +
                                                "FieldUpdater.update()");

                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null,
                                        "There should be a displayed collection item.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                                        "The displayed collection item to reference a valid entity.");
                                checkState(getDisplayedContentSpec() != null, "There should be a displayed item.");

                                displayRevision(revisionTopic.getItem());

                                if (contentSpecRevisionsPresenter.getProviderData().isValid()) {
                                    contentSpecRevisionsPresenter.getDisplay().getProvider().displayAsynchronousList(
                                            contentSpecRevisionsPresenter.getProviderData().getItems(),
                                            contentSpecRevisionsPresenter.getProviderData().getSize(),
                                            contentSpecRevisionsPresenter.getProviderData().getStartRow());
                                }
                            } finally {
                                LOGGER.log(Level.INFO,
                                        "EXIT ContentSpecFilteredResultsAndDetailsPresenter.bindViewContentSpecRevisionButton() " +
                                                "FieldUpdater.update()");
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
                contentSpecRevisionsPresenter.getDisplay().setRevisionContentSpec(revisionSpec);
            }

            /* Initialize the views with the new topic being displayed */
            initializeViews();

            // Load the tags
            tagsLoadInitiated = false;
            loadTags();

            /* Display the revisions view (which will also update buttons like Save) */
            switchView(contentSpecRevisionsPresenter.getDisplay());

        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.displayRevision()");
        }
    }

    /**
     * contentSpecRevisionsPresenter.getDisplay().getRevisionContentSpec() is used to indicate which revision
     * of a content spec we are looking at. Setting it to null means that we are not looking at a revision.
     */
    private void viewLatestSpecRevision() {
        contentSpecRevisionsPresenter.getDisplay().setRevisionContentSpec(null);
    }

    /**
     * Gets the tags, so they can be displayed and added to topics. Unlike the ContentSpecTagsPresenter.getTags() method,
     * here we populate both the tags view, and the tags view in the bulk topic import dialog.
     */
    private void loadAllTags() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadAllTags()");

            if (!allTagsLoadInitiated) {
                allTagsLoadInitiated = true;


                final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTTagCollectionV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO,
                                    "ENTER ContentSpecFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");

                            checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                            contentSpecTagsPresenter.getDisplay().initializeNewTags(retValue);
                        } finally {
                            LOGGER.log(Level.INFO,
                                    "EXIT ContentSpecFilteredResultsAndDetailsPresenter.getTags() callback.doSuccessAction()");
                        }
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTags(), callback, contentSpecTagsPresenter.getDisplay());
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadAllTags()");
        }
    }

    /**
     * The tags for a content spec are loaded as separate operations to minimize the amount of data initially sent when a
     * content spec is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    private void loadTags() {
        loadTags(new ActionCompletedCallback<RESTTextContentSpecV1>() {
            @Override
            public void success(RESTTextContentSpecV1 restTextContentSpecV1) {
                /* update the view */
                initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{contentSpecTagsPresenter.getDisplay()}));
            }

            @Override
            public void failure() {
                // Not used, so do nothing
            }
        }, contentSpecTagsPresenter.getDisplay());
    }

    /**
     * The tags for a content spec are loaded as separate operations to minimize the amount of data initially sent when a
     * content spec is displayed.
     * <p/>
     * We pull down the extended collections from a revision, just to make sure that the collections we are getting are for
     * the entity we are viewing, since there is a slight chance that a new revision could be saved in between us loading
     * the empty entity and then loading the collections.
     */
    private void loadTags(final ActionCompletedCallback<RESTTextContentSpecV1> callback, final BaseTemplateViewInterface display) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadTags()");

            final RESTTextContentSpecCollectionItemV1 selectedItem = filteredResultsPresenter.getProviderData().getSelectedItem();
            final RESTTextContentSpecV1 displayedTopic = getDisplayedContentSpec();

            /* If this is a new topic, the selectedItem will be null, and there will not be any tags to get */
            if (!tagsLoadInitiated && selectedItem != null) {

                tagsLoadInitiated = true;

                /* Initiate the REST calls */
                final Integer id = displayedTopic.getId();
                final Integer revision = displayedTopic.getRevision();

                /* A callback to respond to a request for a topic with the tags expanded */
                final RESTCallBack<RESTTextContentSpecV1> contentSpecWithTagsCallback = new RESTCallBack<RESTTextContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.loadTags() " +
                                    "contentSpecWithTagsCallback.doSuccessAction" + "()");

                            /*
                                There is a small chance that in between loading the content spec's details and
                                loading its tags, a new revision was created.

                                So, what do we do? If changes are made to the content spec, then
                                the user will be warned that they have overwritten a revision created
                                in the mean time. In fact seeing the latest tag relationships could
                                mean that the user doesn't try to add conflicting tags (like adding
                                a tag from a mutually exclusive category when one already exists).

                                This check is left in comments just to show that a conflict is possible.
                            */
                            /*if (!retValue.getRevision().equals(revision)) {
                                AlertBox.setMessageAndDisplay("The content spec details and tags are not in sync.");
                            }*/

                            /* copy the revisions into the displayed Topic */
                            getDisplayedContentSpec().setTags(retValue.getTags());

                            callback.success(retValue);
                        } finally {
                            LOGGER.log(Level.INFO,
                                    "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadTags() contentSpecWithTagsCallback" +
                                            ".doSuccessAction()");
                        }
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getContentSpecRevisionWithTags(id, revision),
                        contentSpecWithTagsCallback, display);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.loadTags()");
        }
    }

    /**
     * This method will load the complete list of property tags (and set allPropertyTagsLoadInitiated to true), and load
     * the property tags assigned to the latest revision of the topic (and set propertyTagsLoadInitiated to true).
     */
    private void loadPropertyTags() {
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a selected collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                "The displayed collection item to reference a valid entity.");

        final RESTTextContentSpecCollectionItemV1 selectedItem = filteredResultsPresenter.getProviderData().getSelectedItem();
        final RESTTextContentSpecV1 displayedItem = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
        final RESTTextContentSpecV1 displayedContentSpec = getDisplayedContentSpec();
        // Are we displaying the latest version of the content spec i.e. the one that doesn't have its tags loaded? */
        final boolean displayingLatest = displayedItem == displayedContentSpec;

        if (!allPropertyTagsLoadInitiated) {
            allPropertyTagsLoadInitiated = true;

            // Get a new collection of property tags
            commonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(displayedItem);
        }

        if (!propertyTagsLoadInitiated && displayingLatest) {
            propertyTagsLoadInitiated = true;

            // if getSearchResultPresenter().getProviderData().getSelectedItem() == null, then we are displaying a new content spec
            if (selectedItem != null) {

                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId() != null,
                        "The displayed collection item to reference a valid entity with a valid ID.");
                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getRevision() != null,
                        "The displayed collection item to reference a valid entity with a valid revision.");

                final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                        checkArgument(retValue.getProperties().getItems() != null,
                                "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getProperties().getSize() != null, "Returned collection should have a valid size.");

                        filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().setProperties(retValue.getProperties());

                        Collections.sort(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                                new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());

                        // We have new data to display
                        initializeViews(Arrays.asList(new BaseTemplateViewInterface[]{commonExtendedPropertiesPresenter.getDisplay()}));
                    }
                };

                final Integer id = displayedItem.getId();
                final Integer revision = displayedItem.getRevision();
                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getContentSpecRevisionWithProperties(id, revision), callback,
                        commonExtendedPropertiesPresenter.getDisplay());
            }
        }
    }

    /**
     * This is called when the revisions tab is opened for the first time.
     */
    private void loadRevisions() {
        if (!revisionsLoadInitiated) {
            revisionsLoadInitiated = true;

            contentSpecRevisionsPresenter.getDisplay().setProvider(contentSpecRevisionsPresenter.generateListProvider(getDisplayedContentSpec()));
        }
    }

    /**
     * This is called when the revisions tab is opened for the first time.
     */
    private void loadProcesses() {
        final RESTTextContentSpecCollectionItemV1 selectedItem = filteredResultsPresenter.getProviderData().getSelectedItem();
        if (!processesLoadInitiated && selectedItem != null) {
            processesLoadInitiated = true;

            contentSpecProcessPresenter.getDisplay().setProvider(contentSpecProcessPresenter.generateListProvider(
                    getDisplayedContentSpec(), null));
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        this.queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        action = null;

        final Map<String, String> tokens = parseTokenFragment(queryString);

        if (tokens.containsKey(Constants.CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON)) {
            startWithNewSpec = true;
            queryString = null;
        } else if (tokens.containsKey(Constants.ENTITY_ID_PREFIX_WO_SEMICOLON)) {
            final String id = tokens.get(Constants.ENTITY_ID_PREFIX_WO_SEMICOLON);
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR + "=" + id;

            if (tokens.containsKey(Constants.ACTION_PREFIX_WO_SEMICOLON)) {
                action = tokens.get(Constants.ACTION_PREFIX_WO_SEMICOLON);
            }
        } else if (!tokens.containsKey(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON)) {
            /* Make sure that the query string has at least the prefix */
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    @Override
    protected void go() {
        bindSearchAndEditExtended(queryString);
    }

    /**
     * Create the web works required to highlight the topics.
     */
    private native void createWorkers(final String restUrl) /*-{

        this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::lastEditWorker = new Worker("javascript/highlighters/age.js");
        var worker = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::lastEditWorker;
        var textEditorPresenter = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::contentSpecPresenter;
        var textEditorDisplay = textEditorPresenter.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter::getDisplay()();
        var lastText = null;
        var lastEditor = null;

        worker.addEventListener('message', function (e) {
            try {
                var message = JSON.parse(e.data);

                if (message.event == 'topicDetails') {

                    console.log("Received topicDetails message");

                    var cache = message.data;

                    var aceEditor = textEditorDisplay.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter.Display::getEditor()();

                    // The keys in this dictionary represent the css styles that are applied to the gutter
                    var highlights = {dayOld: [], weekOld: [], monthOld: [], yearOld: [], older: []};

                    // clear the gutter
                    for (var key in highlights) {
                        if (highlights.hasOwnProperty(key)) {
                            aceEditor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::clearGutterDecoration(Ljava/lang/String;)(key);
                        }
                    }

                    for (var key in cache) {
                        if (cache.hasOwnProperty(key)) {
                            if (cache[key].date) {
                                var date = $wnd.moment(cache[key].date);

                                if ($wnd.moment().subtract('day', 1).isBefore(date)) {
                                    for (var lineIndex = 0, lineCount = cache[key].lines.length; lineIndex < lineCount; ++lineIndex) {
                                        var line = cache[key].lines[lineIndex];
                                        highlights.dayOld.push(line);
                                    }
                                } else if ($wnd.moment().subtract('week', 1).isBefore(date)) {
                                    for (var lineIndex = 0, lineCount = cache[key].lines.length; lineIndex < lineCount; ++lineIndex) {
                                        var line = cache[key].lines[lineIndex];
                                        highlights.weekOld.push(line);
                                    }
                                } else if ($wnd.moment().subtract('month', 1).isBefore(date)) {
                                    for (var lineIndex = 0, lineCount = cache[key].lines.length; lineIndex < lineCount; ++lineIndex) {
                                        var line = cache[key].lines[lineIndex];
                                        highlights.monthOld.push(line);
                                    }
                                } else if ($wnd.moment().subtract('year', 1).isBefore(date)) {
                                    for (var lineIndex = 0, lineCount = cache[key].lines.length; lineIndex < lineCount; ++lineIndex) {
                                        var line = cache[key].lines[lineIndex];
                                        highlights.yearOld.push(line);
                                    }
                                } else {
                                    for (var lineIndex = 0, lineCount = cache[key].lines.length; lineIndex < lineCount; ++lineIndex) {
                                        var line = cache[key].lines[lineIndex];
                                        highlights.older.push(line);
                                    }
                                }
                            }
                        }
                    }

                    for (var key in highlights) {
                        if (highlights.hasOwnProperty(key)) {
                            if (highlights[key].length != 0) {
                                aceEditor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::addGutterDecoration([ILjava/lang/String;)
                                    (highlights[key], key);
                            }
                        }
                    }

                    // recursively call the function until some text has changed
                    var checkText = function () {
                        // the editor may have changed if we saved the content spec
                        var aceEditor = textEditorDisplay.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter.Display::getEditor()();
                        var text = aceEditor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                        if (!lastText || lastText != text || !lastEditor || lastEditor !== aceEditor) {
                            // some text has changed, so post it to the worker
                            lastText = text;
                            lastEditor = aceEditor;
                            var json = JSON.stringify({event: "text", data: text});
                            worker.postMessage(json);
                            return;
                        } else {
                            // otherwise call this function again in a second
                            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout =
                                $wnd.setTimeout(checkText, 1000);
                        }
                    }

                    // in 1 second, run checktext to see if there were any changes to the text
                    this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout =
                        $wnd.setTimeout(checkText, 1000);
                }
            } catch (ex) {

            }
        });

        var sendText = function () {
            var aceEditor = textEditorDisplay.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter.Display::getEditor()();
            if (aceEditor) {
                var text = aceEditor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                var json = JSON.stringify({event: "text", data: text});
                worker.postMessage(json);
                return;
            }

            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout =
                $wnd.setTimeout(sendText, 1000);
        }

        // Send the REST server to load the age information from
        var json = JSON.stringify({event: "init", restUrl: restUrl});
        worker.postMessage(json);

        sendText();
    }-*/;

    /**
     * Terminate any web workers before moving off the page
     */
    private native void cleanUpWorkers() /*-{
        if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout) {
            $wnd.clearTimeout(this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout);
            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::textUpdaterTimeout = null;
        }

        if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::lastEditWorker != null) {
            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::lastEditWorker.terminate();
            this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter::lastEditWorker = null;
        }
    }-*/;

    @Override
    public void close() {
        GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.PressGangCCMS());

        cleanUpWorkers();

        /*
            Allow the child components to close.
         */
        contentSpecRevisionsPresenter.close();
        filteredResultsPresenter.close();
        commonExtendedPropertiesPresenter.close();
        contentSpecTagsPresenter.close();
        contentSpecDetailsPresenter.close();
        contentSpecTranslationDetailsPresenter.close();
        contentSpecProcessPresenter.close();
        translationSyncPresenter.close();
        translationPushPresenter.close();
        freezePresenter.close();

        keyboardEventHandler.removeHandler();
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.afterSwitchView()");

            enableAndDisableActionButtons(displayedView);

            updatePageTitle(displayedView);

            /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
            if (displayedView != null) {
                displayedView.setViewShown(true);
            }

            // Load the tags as needed
            if (displayedView == contentSpecTagsPresenter.getDisplay() && contentSpecTagsPresenter.getDisplay().getProjectsList()
                    .isAttached()) {

                loadTags();
                loadAllTags();
                // Set the projects combo box as the focused element
                contentSpecTagsPresenter.getDisplay().getProjectsList().getElement().focus();
            }

            if (displayedView == commonExtendedPropertiesPresenter.getDisplay()) {
                loadPropertyTags();
            }

            if (displayedView == contentSpecProcessPresenter.getDisplay()) {
                loadProcesses();
            }

            if (displayedView == contentSpecPresenter.getDisplay()) {
                if (lastVisibleRow != null) {
                    contentSpecPresenter.getDisplay().getEditor().focus();
                    contentSpecPresenter.getDisplay().getEditor().scrollToRow(lastVisibleRow);
                }
            }

            if (displayedView == contentSpecRevisionsPresenter.getDisplay()) {
                /*
                    Load the initial set of revisions.
                */
                loadRevisions();
            } else {
                /*
                    Otherwise switch back to the view of revisions.
                */
                this.contentSpecRevisionsPresenter.getDisplay().displayRevisions();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.afterSwitchView()");
        }
    }

    /**
     * Update the page name.
     *
     * @param displayedView the currently displayed view.
     */
    private void updatePageTitle(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.updatePageTitle()");

            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed item need to reference a valid entity");

            final StringBuilder title = new StringBuilder(
                    filteredResultsPresenter.getDisplay().getPageName() + " - " + displayedView.getPageName());
            final StringBuilder id = new StringBuilder(
                    getDisplayedContentSpec().getId() == null ? PressGangCCMSUI.INSTANCE.New() : getDisplayedContentSpec().getId()
                            .toString());

            /*
                Test to see if we are looking at a specific revision. If so, add the revision to the page title.
             */
            if (getDisplayedContentSpec().getRevision() != null && !getDisplayedContentSpec().getRevision().equals(
                    filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getRevision())) {
                id.append("-" + getDisplayedContentSpec().getRevision());
            }

            String displayTitle = getDisplayedContentSpec().getTitle() == null ? "" : getDisplayedContentSpec().getTitle();
            if (displayTitle.length() > Constants.MAX_PAGE_TITLE_LENGTH) {
                displayTitle = displayTitle.substring(0, Constants.MAX_PAGE_TITLE_LENGTH - 3) + "...";
            }

            if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                title.append(": " + displayTitle + " [" + id.toString() + "]");
            }
            getDisplay().getPageTitle().setText(title.toString());
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.updatePageTitle()");
        }
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        display.replaceTopActionButton(this.display.getTextDown(), this.display.getText());
        display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());
        display.replaceTopActionButton(this.display.getTranslationDetailsDown(), this.display.getTranslationDetails());
        display.replaceTopActionButton(this.display.getExtendedPropertiesDown(), this.display.getExtendedProperties());
        display.replaceTopActionButton(this.display.getHistoryDown(), this.display.getHistory());
        display.replaceTopActionButton(this.display.getContentSpecTagsDown(), this.display.getContentSpecTags());
        display.replaceTopActionButton(this.display.getErrorsDown(), this.display.getErrors());
        display.replaceTopActionButton(this.display.getProcessesDown(), this.display.getProcesses());

        if (displayedView == this.contentSpecDetailsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.contentSpecTranslationDetailsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTranslationDetails(), this.display.getTranslationDetailsDown());
        } else if (displayedView == this.contentSpecPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getText(), this.display.getTextDown());
        } else if (displayedView == this.commonExtendedPropertiesPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getExtendedProperties(), this.display.getExtendedPropertiesDown());
        } else if (displayedView == this.contentSpecRevisionsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getHistory(), this.display.getHistoryDown());
        } else if (displayedView == this.contentSpecTagsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getContentSpecTags(), this.display.getContentSpecTagsDown());
        } else if (displayedView == this.contentSpecErrorsPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getErrors(), this.display.getErrorsDown());
        } else if (displayedView == this.contentSpecProcessPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getProcesses(), this.display.getProcessesDown());
        }

        final RESTTextContentSpecV1 displayedContentSpec = getDisplayedContentSpec();
        if (displayedContentSpec != null && displayedContentSpec.getErrors() != null && !displayedContentSpec.getErrors().isEmpty() &&
                (displayedContentSpec.getErrors().contains("ERROR") || displayedContentSpec.getErrors().contains("WARN"))) {
            if (displayedContentSpec.getErrors().contains("ERROR")) {
                getDisplay().getErrors().removeStyleName(CSSConstants.Common.WARNING);
                getDisplay().getErrorsDown().removeStyleName(CSSConstants.Common.WARNING);
                getDisplay().getErrors().addStyleName(CSSConstants.Common.ERROR);
                getDisplay().getErrorsDown().addStyleName(CSSConstants.Common.ERROR);
            } else if (displayedContentSpec.getErrors().contains("WARN")) {
                getDisplay().getErrors().removeStyleName(CSSConstants.Common.ERROR);
                getDisplay().getErrorsDown().removeStyleName(CSSConstants.Common.ERROR);
                getDisplay().getErrors().addStyleName(CSSConstants.Common.WARNING);
                getDisplay().getErrorsDown().addStyleName(CSSConstants.Common.WARNING);
            }
        } else {
            getDisplay().getErrors().removeStyleName(CSSConstants.Common.ERROR);
            getDisplay().getErrors().removeStyleName(CSSConstants.Common.WARNING);
            getDisplay().getErrorsDown().removeStyleName(CSSConstants.Common.ERROR);
            getDisplay().getErrorsDown().removeStyleName(CSSConstants.Common.WARNING);
        }

        if (displayedContentSpec.getId() != null) {
            getDisplay().getViewInDocBuilder().setVisible(true);
        } else {
            getDisplay().getViewInDocBuilder().setVisible(false);
        }

        disableButtonsInReadonlyMode();
    }

    @Nullable
    private RESTTextContentSpecV1 getDisplayedContentSpec() {
        try {
            LOGGER.log(Level.INFO, "ENTER ContentSpecFilteredResultsAndDetailsPresenter.getDisplayedContentSpec()");

            RESTTextContentSpecV1 source = null;

            if (contentSpecRevisionsPresenter.getDisplay().getRevisionContentSpec() != null) {
                source = contentSpecRevisionsPresenter.getDisplay().getRevisionContentSpec();
            } else if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
                source = filteredResultsPresenter.getProviderData().getDisplayedItem().getItem();
            }

            return source;
        } finally {
            LOGGER.log(Level.INFO, "EXIT ContentSpecFilteredResultsAndDetailsPresenter.getDisplayedContentSpec()");
        }
    }

    @Override
    public void isReadOnlyMode(@NotNull final ReadOnlyCallback readOnlyCallback) {
        super.isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                final boolean isDisplayingRevisions = contentSpecRevisionsPresenter.getDisplay().getRevisionContentSpec() != null;
                readOnlyCallback.readonlyCallback(readOnly || isDisplayingRevisions);
            }
        });
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

            /* If there are any modified property tags in the new ContentSpec, we have unsaved changes */
            if (displayedEntity.getProperties() != null && !displayedEntity.getProperties().returnDeletedAddedAndUpdatedCollectionItems()
                    .isEmpty()) {
                return true;
            }

            if (displayedEntity.getTranslationDetails() != null) {
                final RESTCSTranslationDetailV1 displayedDetails = displayedEntity.getTranslationDetails();
                final RESTCSTranslationDetailV1 currentDetails = selectedEntityCollectionItem.getItem().getTranslationDetails() == null ?
                        new RESTCSTranslationDetailV1() : selectedEntityCollectionItem.getItem().getTranslationDetails();

                // Check for changed boolean values
                if (!GWTUtilities.booleanEquals(displayedDetails.isEnabled(), currentDetails.isEnabled())) {
                    return true;
                }

                // Check for changed server
                if (!GWTUtilities.objectEquals(displayedDetails.getTranslationServer(), currentDetails.getTranslationServer())) {
                    return true;
                }

                // Check for changed locales
                if (displayedDetails.getLocales() != null && !displayedDetails.getLocales().returnDeletedAddedAndUpdatedCollectionItems()
                        .isEmpty()) {
                    return true;
                }

                // TODO Enable this when we can set the project/project version
//                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(displayedDetails.getProject(),
//                        displayedDetails.getProject())) {
//                    return true;
//                }
//                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(displayedDetails.getProjectVersion(),
//                        displayedDetails.getProjectVersion())) {
//                    return true;
//                }
            }

            if (!displayedEntityCollectionItem.returnIsAddItem()) {
                /* See if the text or locale has changed */
                if (!GWTUtilities.stringEqualsEquatingNullWithEmptyString(selectedEntityCollectionItem.getItem().getText(), displayedEntity.getText()) ||
                        !selectedEntityCollectionItem.getItem().getLocale().equals(displayedEntity.getLocale())) {
                    LOGGER.log(Level.INFO,
                            "Text is different between selected and displayed. \n" + selectedEntityCollectionItem.getItem().getText() +
                                    "\n" + displayedEntity.getText());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getId().toString());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getErrors());
                    LOGGER.log(Level.INFO, selectedEntityCollectionItem.getItem().getFailedContentSpec());
                    return true;
                }
            } else {
                // If there has been any text added, we have unsaved changes.
                return displayedEntity.getText() != null && !displayedEntity.getText().trim().equals(lastNewContentSpecTemplate);
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
        }, ContentSpecFilteredResultsAndDetailsPresenter.this, new BindRemoveButtons() {
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
                                },
                                ContentSpecFilteredResultsAndDetailsPresenter.this,
                                new BindRemoveButtons() {
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
        private final ReadOnlyPresenter returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;

        /**
         * @param returnCurrentContentSpec A callback used to get the topic that the click handler is modifying
         * @param tagDisplay               The display that the callback is modifying
         */
        public AddTagClickHandler(@NotNull final ReturnCurrentContentSpec returnCurrentContentSpec,
                @NotNull final ReadOnlyPresenter returnReadOnlyMode, @NotNull final BindRemoveButtons bindRemoveButtons,
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
                    if (RESTBaseEntityCollectionItemV1.REMOVE_STATE.equals(tag.getState())) {
                        deletedTag = tag;
                        break;
                    } else {
                        /* Don't add tags twice */
                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.TagAlreadyExists());
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
                    if (existingTag == null || existingTag.getItem() == null || RESTBaseEntityCollectionItemV1.REMOVE_STATE.equals(
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
                    tag.setState(RESTBaseEntityCollectionItemV1.REMOVE_STATE);
                }
            }

            if (deletedTag == null) {
                /* Get the selected tag, and clone it */
                final RESTTagV1 selectedTagClone = selectedTag.clone(true);
                /* Add the tag to the topic */
                returnCurrentContentSpec.getContentSpec().getTags().addNewItem(selectedTagClone);
            } else {
                deletedTag.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
            }

            /* Redisplay the view */
            returnReadOnlyMode.isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(final boolean readOnly) {
                    tagDisplay.display(returnCurrentContentSpec.getContentSpec(), readOnly);
                }
            });

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
        private final ReadOnlyPresenter returnReadOnlyMode;
        private final BindRemoveButtons bindRemoveButtons;


        /**
         * @param returnCurrentContentSpec A callback used to get the topic that the click handler is modifying
         * @param tagDisplay               The display that the callback is modifying
         */
        public DeleteTagClickHandler(@NotNull final RESTTagCollectionItemV1 tag,
                @NotNull final ReturnCurrentContentSpec returnCurrentContentSpec, @NotNull final ReadOnlyPresenter returnReadOnlyMode,
                @NotNull final BindRemoveButtons bindRemoveButtons, @NotNull final ContentSpecTagsPresenter.Display tagDisplay) {
            this.returnCurrentContentSpec = returnCurrentContentSpec;
            this.tagDisplay = tagDisplay;
            this.tag = tag;
            this.returnReadOnlyMode = returnReadOnlyMode;
            this.bindRemoveButtons = bindRemoveButtons;
        }

        @Override
        public void onClick(@NotNull final ClickEvent event) {

            if (RESTBaseEntityCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                /* Tag was added and then removed, so we just delete the tag */
                returnCurrentContentSpec.getContentSpec().getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseEntityCollectionItemV1.REMOVE_STATE);
            }

            returnReadOnlyMode.isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(final boolean readOnly) {
                    tagDisplay.display(returnCurrentContentSpec.getContentSpec(), readOnly);
                }
            });
            bindRemoveButtons.bindRemoveButtons();
        }
    }

    /**
     * Add listeners for keyboard events
     */
    private void addKeyboardShortcutEventHandler() {
        final Event.NativePreviewHandler keyboardShortcutPreviewHandler = new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(@NotNull final Event.NativePreviewEvent event) {
                final NativeEvent ne = event.getNativeEvent();

                if (ne.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (getDisplay().getTopLevelPanel().isAttached() && contentSpecPresenter.getDisplay().isViewShown()) {
                                if (contentSpecPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().isShowing()) {
                                    contentSpecPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().hide();
                                }
                            }
                        }
                    });
                } else if (ne.getCtrlKey() && ne.getAltKey() && ne.getKeyCode() == 'S') {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {

                                saveContentSpec();

                        }
                    });
                }
            }
        };

        keyboardEventHandler = Event.addNativePreviewHandler(keyboardShortcutPreviewHandler);
    }

    protected boolean isAnyDialogBoxesOpen(@NotNull final ContentSpecPresenter.Display contentSpecTextDisplay) {
        if (contentSpecTextDisplay.getEditorSettingsDialog().getDialogBox().isShowing()) {
            return true;
        }

        return false;
    }

    /**
     * The view that holds the other views
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTTextContentSpecV1, RESTTextContentSpecCollectionV1,
            RESTTextContentSpecCollectionItemV1> {
        PushButton getText();

        PushButton getErrors();

        PushButton getExtendedProperties();

        PushButton getDetails();

        PushButton getTranslationDetails();

        PushButton getSave();

        PushButton getHistory();

        PushButton getContentSpecTags();

        AnchorButton getViewInDocBuilder();

        PushButton getProcesses();

        MenuItem getPushTranslation();

        MenuItem getSyncTranslation();

        MenuItem getFreeze();

        MenuItem getActionsMenu();

        Label getTextDown();

        Label getErrorsDown();

        Label getDetailsDown();

        Label getTranslationDetailsDown();

        Label getExtendedPropertiesDown();

        Label getHistoryDown();

        Label getContentSpecTagsDown();

        Label getProcessesDown();

        LogMessageInterface getMessageLogDialog();

        void initialize(final boolean displaySearchResults, final double searchResultsWidth);

        /**
         * @return The button used to show or hide the search results panel
         */
        @NotNull
        PushButton getShowHideSearchResults();
    }
}
