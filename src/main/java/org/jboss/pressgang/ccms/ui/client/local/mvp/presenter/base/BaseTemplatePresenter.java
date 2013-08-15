package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.URL;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
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
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyCategoryFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.PropertyTagFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.StringConstantFilteredResultsAndDetailsViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TranslatedSearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.WelcomeViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * Provides the basic functionality added to a template view defined in BaseTemplateViewInterface.
 *
 * @author Matthew Casperson
 */
abstract public class BaseTemplatePresenter implements BaseTemplatePresenterInterface, EditableView {

    private static final RegExp ID_SEARCH = RegExp.compile(",*(\\s*\\d+\\s*,+)*\\s*\\d+\\s*,*");
    private static final RegExp CS_ID_SEARCH = RegExp.compile(",*(\\s*CS\\s*\\d+\\s*,+)*\\s*CS\\s*\\d+\\s*,*");

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTemplatePresenter.class.getName());


    /**
     * The GWT event bus.
     */
    @Inject
    private EventBus eventBus;

    /**
     * The display that holds the UI elements the user interacts with.
     */
    private BaseTemplateViewInterface display;

    @Override
    public boolean isOKToProceed() {
        return !hasUnsavedChanges() || Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    /**
     * Populate the list of servers.
     */
    protected void buildServersList() {
        /* Add the REST server */
        display.getServers().clear();
        for (final ServerDetails serverDetails : ServerDetails.SERVERS) {
            display.getServers().addItem(serverDetails.getName(), serverDetails.getId() + "");
            if (serverDetails.getId() == ServerDetails.getSavedServer().getId()) {
                display.getServers().setSelectedIndex(display.getServers().getItemCount() - 1);
            }
        }
    }

    private void bindServerSelector() {
        display.getServers().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                final ServerDetails currentServerSettings = ServerDetails.getSavedServer();

                final ServerDetails newServerSettings = saveServer(display.getServers().getValue(display.getServers().getSelectedIndex()));

                if (!newServerSettings.getServerType().equals(currentServerSettings.getServerType())) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ChangedServers().replace("$1", currentServerSettings.getServerType().replaceAll("_", " ")).replace("$2", newServerSettings.getServerType().replaceAll("_", " ")));
                    Window.Location.reload();
                }
            }
        });
    }

    /**
     * Saves the server id as the default, and changes the REST endpoint
     * @param id The new server id
     * @return The ServerDetails instance that matches the ID
     */
    protected ServerDetails saveServer(@NotNull final String id) {
        Preferences.INSTANCE.saveSetting(Preferences.SERVER, id);
        final ServerDetails newServerSettings = ServerDetails.getSavedServer();
        RestClient.setApplicationRoot(newServerSettings.getRestEndpoint());
        return newServerSettings;
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
        // Shortcut button menus
        display.getTopShortcutView().getHome().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new WelcomeViewEvent());
                }
            }
        });

        display.getTopShortcutView().getDocbuilder().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isOKToProceed()) {

                    eventBus.fireEvent(new DocBuilderViewEvent());

                }
            }
        });

        display.getTopShortcutView().getSearchTopics().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TopicSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getTopShortcutView().getSearchContentSpec().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ContentSpecSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getTopShortcutView().getSearchTranslations().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TranslatedSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getTopShortcutView().getCreateTopic().addClickHandler(new ClickHandler() {
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

        display.getTopShortcutView().getCreateContentSpec().addClickHandler(new ClickHandler() {
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

        display.getTopShortcutView().getBug().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Window.open(Constants.BUGZILLA_URL, "_blank", "");
            }
        });

        display.getTopShortcutView().getReports().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Window.open(ServerDetails.getSavedServer().getReportUrl(), "_blank", "");
            }
        });

        display.getTopShortcutView().getImages().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getFiles().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new FilesFilteredResultsAndFileViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getTags().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getCategories().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getProjects().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });
    }

    private void bindAdvancedShortcutButtons() {
        display.getTopShortcutView().getMonitoring().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                Window.open(ServerDetails.getSavedServer().getMonitoringUrl(), "_blank", "");
            }
        });

        display.getTopShortcutView().getStringConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new StringConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getIntegerConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new IntegerConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getBlobConstants().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new BlobConstantFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getPropertyTags().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyTagFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getPropertyTagCategories().setScheduledCommand(new Command() {
            @Override
            public void execute() {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new PropertyCategoryFilteredResultsAndDetailsViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getTopShortcutView().getBulkTagging().setScheduledCommand(new Command() {
            @Override
            public void execute() {
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
                if (CS_ID_SEARCH.test(query)) {
                    /* If the search query was numbers and integers, assume that we are searching for topics ids */
                    @NotNull final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                    eventBus.fireEvent(new ContentSpecSearchResultsAndContentSpecViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR + "=" + fixedQuery, newWindow));
                } else if (ID_SEARCH.test(query)) {
                    /* If the search query was numbers and integers, assume that we are searching for topics ids */
                    @NotNull final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                            Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + fixedQuery, newWindow));
                } else {
                    /* Otherwise do a search against the title, description and content of the topics */
                    eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                            + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(query) : query) + ";"
                            + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(query) : query) + ";"
                            + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + (Constants.ENCODE_QUERY_OPTIONS ? URL.encodePathSegment(query) : query) + ";"
                            + CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.OR_LOGIC, newWindow));
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
     * @param display The view that this presenter is associated with
     */
    protected void bind(@NotNull final BaseTemplateViewInterface display) {
        this.display = display;

        bindStandardButtons();
        bindServerSelector();
        buildHelpDatabase();
        buildServersList();

        /* Watch for page closes */
        Window.addWindowClosingHandler(new ClosingHandler() {

            @Override
            public void onWindowClosing(@NotNull final ClosingEvent event) {
                if (display.getTopLevelPanel().isAttached() && hasUnsavedChanges()) {
                    event.setMessage(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
                }
            }
        });

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

        display.getHelpMode().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                enableHelpOverlay();
            };
        });
    }

    private native void enableHelpOverlay() /*-{
		if ($wnd.pressgang_website_enable) {
			$wnd.pressgang_website_enable();
		} else {
			$wnd.alert("Help overlay is not available.")
		}
    }-*/;

    private void buildHelpDatabase() {
        setDataAttribute(display.getTopShortcutView().getDocbuilder(), ServiceConstants.HELP_TOPICS.DOCBUILDER_VIEW_TOPIC.getId());
        setDataAttribute(display.getTopShortcutView().getHome(), ServiceConstants.HELP_TOPICS.HOME_VIEW_TOPIC.getId());
        setDataAttribute(display.getTopShortcutView().getCreateTopic(), ServiceConstants.HELP_TOPICS.CREATE_TOPIC_VIEW_TOPIC.getId());

        setDataAttribute(display.getTopShortcutView().getSearchTopics().getElement(), ServiceConstants.HELP_TOPICS.SEARCH_TOPICS_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getSearchTranslations().getElement(), ServiceConstants.HELP_TOPICS.SEARCH_TRANSLATIONS_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getImages().getElement(), ServiceConstants.HELP_TOPICS.IMAGES_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getTags().getElement(), ServiceConstants.HELP_TOPICS.TAGS_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getCategories().getElement(), ServiceConstants.HELP_TOPICS.CATEGORIES_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getProjects().getElement(), ServiceConstants.HELP_TOPICS.PROJECTS_VIEW.getId());
        setDataAttribute(display.getTopShortcutView().getReports().getElement(), ServiceConstants.HELP_TOPICS.REPORTS.getId());
        setDataAttribute(display.getTopShortcutView().getBug().getElement(), ServiceConstants.HELP_TOPICS.CREATE_BUG.getId());
        setDataAttribute(display.getTopShortcutView().getFiles().getElement(), ServiceConstants.HELP_TOPICS.FILES.getId());

        setDataAttribute(display.getTopShortcutView().getEntitiesSubMenu().getElement(), ServiceConstants.HELP_TOPICS.ENTITIES.getId());
        setDataAttribute(display.getTopShortcutView().getAdvancedSubMenu().getElement(), ServiceConstants.HELP_TOPICS.ADVANCED.getId());
        setDataAttribute(display.getTopShortcutView().getSearchSubMenu().getElement(), ServiceConstants.HELP_TOPICS.SEARCH.getId());

        setDataAttribute(display.getTopShortcutView().getSearchContentSpec().getElement(), ServiceConstants.HELP_TOPICS.SEARCH_CONTENT_SPECS.getId());
        setDataAttribute(display.getTopShortcutView().getCreateContentSpec().getElement(), ServiceConstants.HELP_TOPICS.CREATE_CONTENT_SPEC.getId());
        setDataAttribute(display.getTopShortcutView().getBulkTagging().getElement(), ServiceConstants.HELP_TOPICS.BULK_TAGGING.getId());
        setDataAttribute(display.getTopShortcutView().getStringConstants().getElement(), ServiceConstants.HELP_TOPICS.STRING_CONSTANTS.getId());
        setDataAttribute(display.getTopShortcutView().getBlobConstants().getElement(), ServiceConstants.HELP_TOPICS.BLOB_CONSTANTS.getId());
        setDataAttribute(display.getTopShortcutView().getIntegerConstants().getElement(), ServiceConstants.HELP_TOPICS.INTEGER_CONSTANTS.getId());
        setDataAttribute(display.getTopShortcutView().getPropertyTags().getElement(), ServiceConstants.HELP_TOPICS.EXTENDED_PROPERTIES.getId());
        setDataAttribute(display.getTopShortcutView().getPropertyTagCategories().getElement(), ServiceConstants.HELP_TOPICS.EXTENDED_PROPERTY_CATEGORIES.getId());
        setDataAttribute(display.getTopShortcutView().getMonitoring().getElement(), ServiceConstants.HELP_TOPICS.MONITORING.getId());

        setDataAttribute(display.getQuickSearchPanel(), ServiceConstants.HELP_TOPICS.SIMPLE_SEARCH.getId());
        setDataAttribute(display.getHelpMode(), ServiceConstants.HELP_TOPICS.HELP_MODE.getId());
        setDataAttribute(display.getServers(), ServiceConstants.HELP_TOPICS.SERVER_SELECTION.getId());
        setDataAttribute(display.getVersion(), ServiceConstants.HELP_TOPICS.BUILD_LABEL.getId());
    }

    /**
     * Adds the data attribute that identifies the widget to the help overlay system
     * @param widget The widget to be modified
     * @param id The help topic id
     */
    protected void setDataAttribute(@NotNull final Widget widget, @NotNull final Integer id) {
        setDataAttribute(widget.getElement(), id);
    }

    /**
     * Adds the data attribute that identifies the widget to the help overlay system
     * @param element The widget to be modified
     * @param id The help topic id
     */
    protected void setDataAttribute(@NotNull final Element element, @NotNull final Integer id) {
        element.setAttribute(Constants.PRESSGANG_WEBSITES_HELP_OVERLAY_DATA_ATTR, id.toString());
    }
}
