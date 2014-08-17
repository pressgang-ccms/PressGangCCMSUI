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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.URL;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.HyperlinkImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.AllServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEventHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.BlobConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.BulkTagSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchResultsAndContentSpecViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.DocBuilderViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.FilesFilteredResultsAndFileViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.IntegerConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ProcessViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyCategoryFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyTagFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ServerSettingsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.StringConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SysInfoViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TranslatedSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.WelcomeViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.DocBuilderPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.SysInfoPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process.ProcessPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.settings.ServerSettingsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.ReadOnlyPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * Provides the basic functionality added to a template view defined in BaseTemplateViewInterface.
 *
 * @author Matthew Casperson
 */
abstract public class BaseTemplatePresenter implements BaseTemplatePresenterInterface, EditableView, ReadOnlyPresenter {

    private static final RegExp ID_SEARCH = RegExp.compile(",*(\\s*\\d+\\s*,+)*\\s*\\d+\\s*,*");
    private static final RegExp CS_ID_SEARCH = RegExp.compile(",*(\\s*CS\\s*\\d+\\s*,+)*\\s*CS\\s*\\d+\\s*,*", "i");

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTemplatePresenter.class.getName());


    /**
     * The GWT event bus.
     */
    @Inject
    private EventBus eventBus;
    @Inject
    private FailOverRESTCall failOverRESTCall;

    private RESTServerSettingsV1 serverSettings;

    private final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    @Override
    public boolean isOKToProceed() {
        return !hasUnsavedChanges() || Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    @NotNull
    protected EventBus getEventBus() {
        return eventBus;
    }

    @NotNull
    protected FailOverRESTCall getFailOverRESTCall() {
        return failOverRESTCall;
    }

    @Override
    public final void initAndGo(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, getDisplay());

        bind(getDisplay());

        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 serverSettings) {
                disableTopShortcutButtonsInReadOnlyMode();
                go();
            }
        });
    }

    /**
     * Display any additional data specific to the presenter
     */
    protected abstract void go();

    protected void getServerSettings(@NotNull final ServerSettingsCallback settingsCallback) {
        if (serverSettings == null) {
            FailOverRESTCallDatabase.getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 value) {
                    serverSettings = value;
                    settingsCallback.serverSettingsLoaded(serverSettings);
                }
            }, getDisplay(), failOverRESTCall);
        } else {
            settingsCallback.serverSettingsLoaded(serverSettings);
        }
    }

    protected void setServerSettings(@NotNull final RESTServerSettingsV1 serverSettings) {
        this.serverSettings = serverSettings;
    }

    public void isReadOnlyMode(@NotNull final ReadOnlyCallback callback) {
        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                getServerSettings(new ServerSettingsCallback() {
                    @Override
                    public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                        callback.readonlyCallback(serverSettings.isReadOnly() || serverDetails.isReadOnly());
                    }
                });
            }
        });
    }

    /**
     * Populate the list of servers.
     */
    protected void buildServersList() {
        /* Add the REST server */
        getDisplay().getServers().clear();

        ServerDetails.getCurrentServers(new AllServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final Map<Integer, ServerDetails> allServerDetails) {
                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails currentServerDetails) {
                        for (final ServerDetails serverDetail : allServerDetails.values()) {
                            getDisplay().getServers().addItem(serverDetail.getName(), serverDetail.getId() + "");
                            if (serverDetail.getId() == currentServerDetails.getId()) {
                                getDisplay().getServers().setSelectedIndex(getDisplay().getServers().getItemCount() - 1);
                            }
                        }

                        // Disable the menu if we on;y have one available server
                        if (allServerDetails.values().size() <= 1) {
                            getDisplay().getServers().setEnabled(false);
                        }
                    }
                });
            }
        });


    }

    private void bindServerSelector() {
        getDisplay().getServers().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails currentServerSettings) {
                        saveServer(getDisplay().getServers().getValue(getDisplay().getServers().getSelectedIndex()));
                    }
                });
            }
        });
    }

    /**
     * Saves the server id as the default, and changes the REST endpoint
     *
     * @param id The new server id
     * @return The ServerDetails instance that matches the ID
     */
    protected void saveServer(@NotNull final String id) {
        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails currentServerSettings) {
                Preferences.INSTANCE.saveSetting(Preferences.SERVER, id);
                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails newServerSettings) {
                        RestClient.setApplicationRoot(newServerSettings.getRestEndpoint());
                        if (!newServerSettings.getGroup().equals(currentServerSettings.getGroup())) {
                            AlertBox.setMessageAndDisplay(
                                PressGangCCMSUI.INSTANCE.ChangedServers()
                                    .replace("$1",currentServerSettings.getGroup().getType())
                                    .replace("$2",newServerSettings.getGroup().getType()),
                                new CloseHandler() {
                                    @Override
                                    public void onClose(CloseEvent event) {
                                        Window.Location.reload();
                                    }
                                }
                            );
                        }
                    }
                });
            }
        });

    }

    /**
     * Called to bind the UI elements to event handlers.
     */
    private void bindStandardButtons() {
        final HyperlinkImpl hyperlinkImpl = GWT.create(HyperlinkImpl.class);

        bindShortcutLinks();
        bindDefaultShortcutButtons(hyperlinkImpl);
        bindAdvancedShortcutButtons();

        getDisplay().getQuickSearchQuery().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {
                try {
                    LOGGER.log(Level.INFO, "ENTER BaseTemplatePresenter.bindStandardButtons() KeyPressHandler.onKeyPress()");

                    final int charCode = event.getUnicodeCharCode();
                    if (charCode == 0) {
                        // it's probably Firefox
                        final int keyCode = event.getNativeEvent().getKeyCode();
                        // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                        // always check the keyCode against a list of "known to be buggy" codes!
                        if (keyCode == KeyCodes.KEY_ENTER) {
                            if (isOKToProceed()) {
                                doQuickSearch(event.isControlKeyDown());
                            }
                        }
                    } else if (charCode == KeyCodes.KEY_ENTER) {
                        if (isOKToProceed()) {
                            doQuickSearch(event.isControlKeyDown());
                        }
                    }
                } finally {
                    LOGGER.log(Level.INFO, "EXIT BaseTemplatePresenter.bindStandardButtons() KeyPressHandler.onKeyPress()");
                }
            }
        });

        getDisplay().getQuickSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                doQuickSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        // Setup the report a bug button
        getDisplay().getReportBugButton().setHref(Constants.BUGZILLA_URL);
        getDisplay().getReportBugButton().setTarget("_blank");

        getDisplay().getReportBugButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hyperlinkImpl.handleAsClick((Event) event.getNativeEvent())) {
                    eatEvent((Event) event.getNativeEvent());
                    Window.open(Constants.BUGZILLA_URL, "_blank", "");
                }
            }
        });
    }

    private void bindShortcutLinks() {
        // Base
        getDisplay().getHome().setHref("#" + WelcomePresenter.HISTORY_TOKEN);
        getDisplay().getTopShortcutView().getDocbuilder().setHref("#" + DocBuilderPresenter.HISTORY_TOKEN);
        getDisplay().getTopShortcutView().getCreateTopic().setHref("#" + TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.CREATE_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getCreateContentSpec().setHref("#" + ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";"
                + Constants.CREATE_PATH_SEGMENT_PREFIX);

        // Search SubMenu
        getDisplay().getTopShortcutView().getSearchTopics().setHref("#" + TopicSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN);
        getDisplay().getTopShortcutView().getSearchTranslations().setHref("#" + TopicSearchTagsFieldsAndFiltersPresenter.TRANSLATED_HISTORY_TOKEN);
        getDisplay().getTopShortcutView().getSearchContentSpec().setHref("#" + ContentSpecSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN);

        // Entities SubMenu
        getDisplay().getTopShortcutView().getImages().setHref("#" + ImagesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getFiles().setHref("#" + FilesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getTags().setHref("#" + TagsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getCategories().setHref("#" + CategoriesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getProjects().setHref("#" + ProjectsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);

        // Administration SubMenu
        getDisplay().getTopShortcutView().getBulkTagging().setHref("#" + TopicSearchTagsFieldsAndFiltersPresenter.BULK_TAG_HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getBlobConstants().setHref("#" + BlobConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getStringConstants().setHref("#" + StringConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getIntegerConstants().setHref("#" + IntegerConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getPropertyTags().setHref("#" + PropertyTagFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getPropertyTagCategories().setHref("#" + PropertyCategoryFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.QUERY_PATH_SEGMENT_PREFIX);
        getDisplay().getTopShortcutView().getProcesses().setHref("#" + ProcessPresenter.HISTORY_TOKEN);
        ServerDetails.getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                getDisplay().getTopShortcutView().getMonitoring().setHref(serverDetails.getMonitoringUrl());
                getDisplay().getTopShortcutView().getMonitoring().setTarget("_blank");
            }
        });
        getDisplay().getTopShortcutView().getSysinfo().setHref("#" + SysInfoPresenter.HISTORY_TOKEN);
        getDisplay().getTopShortcutView().getServerSettings().setHref("#" + ServerSettingsPresenter.HISTORY_TOKEN);
    }

    private void bindDefaultShortcutButtons(final HyperlinkImpl hyperlinkImpl) {
        // Shortcut button menus
        getDisplay().getHome().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hyperlinkImpl.handleAsClick((Event) event.getNativeEvent())) {
                    eatEvent((Event) event.getNativeEvent());
                    if (isOKToProceed()) {
                        eventBus.fireEvent(new WelcomeViewEvent());
                    }
                }
            }
        });

        getDisplay().getTopShortcutView().getDocbuilder().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (hyperlinkImpl.handleAsClick((Event) event.getNativeEvent())) {
                    eatEvent((Event) event.getNativeEvent());
                    if (isOKToProceed()) {
                        eventBus.fireEvent(new DocBuilderViewEvent());
                    }
                }
            }
        });

        getDisplay().getTopShortcutView().getCreateTopic().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                /* don't try and launch the page again */
                if (History.getToken().startsWith(
                        TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants.CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON)) {
                    return;
                }

                if (isOKToProceed()) {
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(Constants.CREATE_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getCreateContentSpec().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                /* don't try and launch the page again */
                if (History.getToken().startsWith(
                        ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN + ";" + Constants
                                .CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON)) {
                    return;
                }

                if (isOKToProceed()) {
                    eventBus.fireEvent(new ContentSpecSearchResultsAndContentSpecViewEvent(Constants.CREATE_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getSearchTopics().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TopicSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getSearchContentSpec().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ContentSpecSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getSearchTranslations().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TranslatedSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getImages().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getFiles().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new FilesFilteredResultsAndFileViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getTags().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getCategories().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getProjects().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });
    }

    private void eatEvent(@NotNull final Event event) {
        DOM.eventCancelBubble(event, true);
        DOM.eventPreventDefault(event);
    }

    private void bindAdvancedShortcutButtons() {
        getDisplay().getTopShortcutView().getSysinfo().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new SysInfoViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getMonitoring().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                        Window.open(serverDetails.getMonitoringUrl(), "_blank", "");
                    }
                });
            }
        });

        getDisplay().getTopShortcutView().getStringConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new StringConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getIntegerConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new IntegerConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getBlobConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BlobConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getPropertyTags().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyTagFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getPropertyTagCategories().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyCategoryFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        getDisplay().getTopShortcutView().getBulkTagging().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BulkTagSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getProcesses().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ProcessViewEvent());
                }
            }
        });

        getDisplay().getTopShortcutView().getServerSettings().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ServerSettingsViewEvent());
                }
            }
        });
    }

    private void doQuickSearch(final boolean newWindow) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTemplatePresenter.doQuickSearch()");

            final String query = getDisplay().getQuickSearchQuery().getValue().trim();

            if (query.isEmpty()) {
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.PleaseEnterValue());
            } else {
                if (CS_ID_SEARCH.test(query)) {
                    /* If the search query was numbers and integers, assume that we are searching for topics ids */
                    @NotNull final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                    eventBus.fireEvent(new ContentSpecSearchResultsAndContentSpecViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR + "=" + fixedQuery,
                            newWindow));
                } else if (ID_SEARCH.test(query)) {
                    /* If the search query was numbers and integers, assume that we are searching for topics ids */
                    @NotNull final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + fixedQuery,
                            newWindow));
                } else {
                    /* Otherwise do a search against the title, description and content of the topics */
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + (Constants
                                    .ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(
                                    query) : query) + ";" + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + (Constants
                                    .ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(
                                    query) : query) + ";" + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + (Constants
                                    .ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(
                                    query) : query) + ";" + CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.OR_LOGIC,
                            newWindow));
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTemplatePresenter.doQuickSearch()");
        }
    }

    /**
     * Binding is done when the view is loaded. The code here doesn't reference the data in a selected entity (it is
     * quite possible that an entity has not been selected yet).
     * <p/>
     * Binding is done once.
     * <p/>
     * The display methods (see the interfaces like BaseChildrenPresenterInterface) are used to display the actual
     * data once an entity has been selected.
     *
     * @param display The view that this presenter is associated with
     */
    private void bind(@NotNull final BaseTemplateViewInterface display) {
        bindStandardButtons();
        bindServerSelector();
        buildHelpDatabase();
        buildServersList();

        /* Watch for page closes */
        handlers.add(Window.addWindowClosingHandler(new ClosingHandler() {

            @Override
            public void onWindowClosing(@NotNull final ClosingEvent event) {
                if (display.getTopLevelPanel().isAttached() && hasUnsavedChanges()) {
                    event.setMessage(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
                }
            }
        }));

        handlers.add(eventBus.addHandler(FailoverEvent.getType(), new FailoverEventHandler() {
            @Override
            public void onFailOverEvent() {
                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                        for (int i = 0; i < display.getServers().getItemCount(); ++i) {
                            if (display.getServers().getValue(i).equals(serverDetails.getId() + "")) {
                                display.getServers().setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                });

            }
        }));

        display.getHelpMode().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                enableHelpOverlay();
            }
        });
    }

    protected void disableTopShortcutButtonsInReadOnlyMode() {
        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                getDisplay().getTopShortcutView().getCreateContentSpec().setEnabled(!readOnly);
                getDisplay().getTopShortcutView().getCreateTopic().setEnabled(!readOnly);
            }
        });
    }

    @Override
    public void destroy() {
        for (final HandlerRegistration handlerRegistration : handlers) {
            handlerRegistration.removeHandler();
        }
        handlers.clear();
    }

    private native void enableHelpOverlay() /*-{
        if ($wnd.pressgang_website_enable) {
            $wnd.pressgang_website_enable();
        } else {
            $wnd.alert("Help overlay is not available.")
        }
    }-*/;

    private void buildHelpDatabase() {
        setDataAttribute(getDisplay().getTopShortcutView().getDocbuilder(), ServiceConstants.HELP_TOPICS.DOCBUILDER_VIEW_TOPIC.getId());
        setDataAttribute(getDisplay().getHome(), ServiceConstants.HELP_TOPICS.HOME_VIEW_TOPIC.getId());

        setDataAttribute(getDisplay().getTopShortcutView().getSearchTopics().getElement(),
                ServiceConstants.HELP_TOPICS.SEARCH_TOPICS_VIEW.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getSearchTranslations().getElement(),
                ServiceConstants.HELP_TOPICS.SEARCH_TRANSLATIONS_VIEW.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getImages().getElement(), ServiceConstants.HELP_TOPICS.IMAGES_VIEW.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getTags().getElement(), ServiceConstants.HELP_TOPICS.TAGS_VIEW.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getCategories().getElement(), ServiceConstants.HELP_TOPICS.CATEGORIES_VIEW.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getProjects().getElement(), ServiceConstants.HELP_TOPICS.PROJECTS_VIEW.getId());
        setDataAttribute(getDisplay().getReportBugButton().getElement(), ServiceConstants.HELP_TOPICS.CREATE_BUG.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getFiles().getElement(), ServiceConstants.HELP_TOPICS.FILES.getId());

        setDataAttribute(getDisplay().getTopShortcutView().getCreateSubMenu().getElement(), ServiceConstants.HELP_TOPICS.CREATE.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getEntitiesSubMenu().getElement(), ServiceConstants.HELP_TOPICS.ENTITIES.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getAdminSubMenu().getElement(), ServiceConstants.HELP_TOPICS.ADVANCED.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getSearchSubMenu().getElement(), ServiceConstants.HELP_TOPICS.SEARCH.getId());

        setDataAttribute(getDisplay().getTopShortcutView().getSearchContentSpec().getElement(),
                ServiceConstants.HELP_TOPICS.SEARCH_CONTENT_SPECS.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getCreateContentSpec().getElement(),
                ServiceConstants.HELP_TOPICS.CREATE_CONTENT_SPEC.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getBulkTagging().getElement(), ServiceConstants.HELP_TOPICS.BULK_TAGGING.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getStringConstants().getElement(),
                ServiceConstants.HELP_TOPICS.STRING_CONSTANTS.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getBlobConstants().getElement(), ServiceConstants.HELP_TOPICS.BLOB_CONSTANTS.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getIntegerConstants().getElement(),
                ServiceConstants.HELP_TOPICS.INTEGER_CONSTANTS.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getPropertyTags().getElement(),
                ServiceConstants.HELP_TOPICS.EXTENDED_PROPERTIES.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getPropertyTagCategories().getElement(),
                ServiceConstants.HELP_TOPICS.EXTENDED_PROPERTY_CATEGORIES.getId());
        setDataAttribute(getDisplay().getTopShortcutView().getMonitoring().getElement(), ServiceConstants.HELP_TOPICS.MONITORING.getId());

        setDataAttribute(getDisplay().getQuickSearchPanel(), ServiceConstants.HELP_TOPICS.SIMPLE_SEARCH.getId());
        setDataAttribute(getDisplay().getHelpMode(), ServiceConstants.HELP_TOPICS.HELP_MODE.getId());
        setDataAttribute(getDisplay().getServers(), ServiceConstants.HELP_TOPICS.SERVER_SELECTION.getId());
        setDataAttribute(getDisplay().getVersion(), ServiceConstants.HELP_TOPICS.BUILD_LABEL.getId());
    }

    /**
     * Adds the data attribute that identifies the widget to the help overlay system
     *
     * @param widget The widget to be modified
     * @param id     The help topic id
     */
    protected void setDataAttribute(@NotNull final Widget widget, @NotNull final Integer id) {
        setDataAttribute(widget.getElement(), id);
    }

    /**
     * Adds the data attribute that identifies the widget to the help overlay system
     *
     * @param element The widget to be modified
     * @param id      The help topic id
     */
    protected void setDataAttribute(@NotNull final Element element, @NotNull final Integer id) {
        element.setAttribute(Constants.PRESSGANG_WEBSITES_HELP_OVERLAY_DATA_ATTR, id.toString());
    }

    protected Map<String, String> parseTokenFragment(final String fragment) {
        final Map<String, String> retValue = new HashMap<String, String>();

        final String[] split = fragment.split(";");
        for (int i = 0; i < split.length; i++) {
            final String s = split[i];
            if (Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON.equals(s)) {
                final StringBuilder builder = new StringBuilder();
                for (int j = i + 1; j < split.length; j++) {
                    builder.append(split[j]).append(";");
                }
                retValue.put(s, builder.toString());
            } else {
                final String[] keyValue = s.split("=", 2);
                final String key = keyValue[0];
                final String value;
                if (keyValue.length > 1) {
                    value = keyValue[1];
                } else {
                    value = null;
                }

                retValue.put(key, value);
            }
        }

        return retValue;
    }
}
