package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.URL;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEventHandler;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides the basic functionality added to a template view defined in BaseTemplateViewInterface.
 *
 * @author Matthew Casperson
 */
abstract public class BaseTemplatePresenter implements BaseTemplatePresenterInterface, EditableView {

    private static final RegExp ID_SEARCH = RegExp.compile(",*(\\s*\\d+\\s*,+)*\\s*\\d+\\s*,*");

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTemplatePresenter.class.getName());

    /**
     * The GWT event bus.
     */
    @Inject
    private HandlerManager eventBus;

    /**
     * The display that holds the UI elements the user interacts with.
     */
    private BaseTemplateViewInterface display;

    /**
     * The help topic that will be displayed when the help link is clicked.
     */
    private int helpTopicId = ServiceConstants.DEFAULT_HELP_TOPIC;

    /**
     * @return The topic of the ID to be used for the help dialog
     */
    @Override
    public int getHelpTopicId() {
        return helpTopicId;
    }

    /**
     * @param helpTopicId The topic of the ID to be used for the help dialog
     */
    @Override
    public void setHelpTopicId(final int helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    @Override
    public boolean isOKToProceed() {
        return !hasUnsavedChanges() || Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    private void bindServerSelector() {
        display.getServers().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                final ServerDetails currentServerSettings = ServerDetails.getSavedServer();

                final String serverIdString = display.getServers().getValue(display.getServers().getSelectedIndex());
                Preferences.INSTANCE.saveSetting(Preferences.SERVER, serverIdString);

                final ServerDetails newServerSettings = ServerDetails.getSavedServer();

                RestClient.setApplicationRoot(newServerSettings.getRestEndpoint());

                if (!newServerSettings.getServerType().equals(currentServerSettings.getServerType())) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ChangedServers().replace("$1", currentServerSettings.getServerType().name().replaceAll("_", " ")).replace("$2", newServerSettings.getServerType().name().replaceAll("_", " ")));
                    Window.Location.reload();
                }
            }
        });
    }


    /**
     * Called to bind the UI elements to event handlers.
     */
    private void bindStandardButtons() {
        bindDefaultShortcutButtons();
        bindAdvancedShortcutButtons();

        display.getQuickSearchQuery().addKeyPressHandler(new KeyPressHandler() {

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

        display.getQuickSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                doQuickSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });
    }

    private void bindDefaultShortcutButtons() {
        display.getShortcuts().getHomeButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new WelcomeViewEvent());
                }
            }
        });

        display.getShortcuts().getDocBuilderButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {

                    eventBus.fireEvent(new DocBuilderViewEvent());

                }
            }
        });

        display.getShortcuts().getSearchSubMenu().getSearchTopicsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TopicSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getShortcuts().getSearchSubMenu().getSearchContentSpecsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ContentSpecSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getShortcuts().getSearchSubMenu().getSearchTranslationsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TranslatedSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getShortcuts().getCreateTopicButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
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

        display.getShortcuts().getCreateContentSpecButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
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

        display.getShortcuts().getBugButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Window.open(Constants.BUGZILLA_URL, "_blank", "");
            }
        });

        display.getShortcuts().getReportsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Window.open(ServerDetails.getSavedServer().getReportUrl(), "_blank", "");
            }
        });

        display.getShortcuts().getEntitiesSubMenu().getImagesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getEntitiesSubMenu().getFilesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new FilesFilteredResultsAndFileViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getEntitiesSubMenu().getTagsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getEntitiesSubMenu().getCategoriesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getEntitiesSubMenu().getProjectsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                        GWTUtilities.isEventToOpenNewWindow(event)));
            }
        });
    }

    private void bindAdvancedShortcutButtons() {
        display.getShortcuts().getAdvancedSubMenu().getMonitoringButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Window.open(ServerDetails.getSavedServer().getMonitoringUrl(), "_blank", "");
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getStringConstantsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new StringConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getIntegerConstantsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new IntegerConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getBlobConstantsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BlobConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getPropertyTagsButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyTagFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getPropertyTagCategoriesButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyCategoryFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX,
                            GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getShortcuts().getAdvancedSubMenu().getBulkTaggingButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BulkTagSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });
    }

    private void doQuickSearch(final boolean newWindow) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTemplatePresenter.doQuickSearch()");

            final String query = display.getQuickSearchQuery().getValue().trim();

            if (query.isEmpty()) {
                Window.alert(PressGangCCMSUI.INSTANCE.PleaseEnterValue());
            } else {
                if (ID_SEARCH.test(query)) {
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
     * The display methods are used to display the actual data.
     *
     * @param topicId The help topic associated with the view
     * @param pageId  The id of the view
     * @param display The view that this presenter is associated with
     */
    protected void bind(final int topicId, @NotNull final String pageId, @NotNull final BaseTemplateViewInterface display) {
        this.display = display;
        this.helpTopicId = topicId;

        setFeedbackLink(pageId);
        bindStandardButtons();
        bindServerSelector();

        /* Watch for page closes */
        Window.addWindowClosingHandler(new ClosingHandler() {

            @Override
            public void onWindowClosing(@NotNull final ClosingEvent event) {
                if (display.getTopLevelPanel().isAttached() && hasUnsavedChanges()) {
                    event.setMessage(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
                }

                // Save the open shortcut menus
                Preferences.INSTANCE.saveSetting(Preferences.SHORTCUT_SEARCH_MENU_OPEN, display.getShortcuts().getSearchSubMenu()
                        .isOpen());
                Preferences.INSTANCE.saveSetting(Preferences.SHORTCUT_ENTITIES_MENU_OPEN, display.getShortcuts().getEntitiesSubMenu()
                        .isOpen());
            }
        });

        /* Add handlers for the help link */
        final ClickHandler openHelpClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getHelpDialog().show(helpTopicId, display);
            }
        };

        final ClickHandler okClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getHelpDialog().getDialogBox().hide();
            }
        };

        final ClickHandler editClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants
                                    .TOPIC_IDS_FILTER_VAR + "=" + helpTopicId,
                            false));
                    display.getHelpDialog().getDialogBox().hide();
                }
            }
        };

        display.getHelp().addClickHandler(openHelpClickHandler);
        display.getHelpDialog().getOK().addClickHandler(okClickHandler);
        display.getHelpDialog().getEdit().addClickHandler(editClickHandler);

        this.eventBus.addHandler(FailoverEvent.getType(), new FailoverEventHandler() {
            @Override
            public void onFailOverEvent() {
                final ServerDetails currentServerSettings = ServerDetails.getSavedServer();

                for (int i = 0; i < display.getServers().getItemCount(); ++i) {
                    if (display.getServers().getValue(i).equals(currentServerSettings.getId() + "")) {
                        display.getServers().setSelectedIndex(i);
                        break;
                    }
                }
            }
        });
    }

    /**
     * Set the feedback URL for the page.
     *
     * @param pageId The id of the page
     */
    protected void setFeedbackLink(@NotNull final String pageId) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);
    }
}
