package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Provides the basic functionality added to a template view.
 *
 * @author Matthew Casperson
 */
abstract public class BaseTemplatePresenter implements BaseTemplatePresenterInterface, EditableView {

    private static final RegExp ID_SEARCH = RegExp.compile(",*(\\s*\\d+\\s*,+)*\\s*\\d+\\s*,*");

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
    public final int getHelpTopicId() {
        return helpTopicId;
    }

    /**
     * @param helpTopicId The topic of the ID to be used for the help dialog
     */
    @Override
    public final void setHelpTopicId(final int helpTopicId) {
        this.helpTopicId = helpTopicId;
    }

    @Override
    public final boolean isOKToProceed() {
        return !hasUnsavedChanges() || Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    /**
     * Called to bind the UI elements to event handlers.
     */
    private void bindStandardButtons() {

        display.getHome().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new WelcomeViewEvent());
                }
            }
        });

        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new SearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });

        display.getCreateTopic().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                /* don't try and launch the page again */
                if (History.getToken().startsWith(SearchResultsAndTopicPresenter.HISTORY_TOKEN + ";" + Constants.CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON)) {
                    return;
                }

                if (isOKToProceed()) {
                    eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.CREATE_PATH_SEGMENT_PREFIX, false));
                }
            }
        });

        display.getBug().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Window.open(Constants.BUGZILLA_URL, "_blank", "");
            }
        });

        display.getReports().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Window.open(Constants.BIRT_URL, "_blank", "");
            }
        });

        display.getImages().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getTags().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getCategories().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, GWTUtilities.isEventToOpenNewWindow(event)));
                }
            }
        });

        display.getProjects().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        });

        display.getAdvanced().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getShortCutPanelParent().setWidget(display.getAdvancedShortcutPanel());
            }
        });

        final ClickHandler closeAdvancedMenu = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getShortCutPanelParent().setWidget(display.getShortcutPanel());
            }
        };

        display.getAdvancedOpen().addClickHandler(closeAdvancedMenu);
        display.getClose().addClickHandler(closeAdvancedMenu);

        display.getQuickSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doQuickSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        display.getQuickSearchQuery().addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(final KeyPressEvent event) {
                final int charCode = event.getUnicodeCharCode();
                if (charCode == 0) {
                    // it's probably Firefox
                    final int keyCode = event.getNativeEvent().getKeyCode();
                    // beware! keyCode=40 means "down arrow", while charCode=40 means '('
                    // always check the keyCode against a list of "known to be buggy" codes!
                    if (keyCode == KeyCodes.KEY_ENTER) {
                        doQuickSearch(event.isControlKeyDown());
                    }
                } else if (charCode == KeyCodes.KEY_ENTER) {
                    doQuickSearch(event.isControlKeyDown());
                }
            }
        });

        display.getSearchTranslations().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TranslatedSearchTagsFieldsAndFiltersViewEvent());
                }
            }
        });
    }

    private void doQuickSearch(final boolean newWindow) {
        final String query = display.getQuickSearchQuery().getValue().trim();

        if (query.isEmpty()) {
            Window.alert(PressGangCCMSUI.INSTANCE.PleaseEnterValue());
        } else {
            if (ID_SEARCH.test(query)) {
                /* If the search query was numbers and integers, assume that we are searching for topics ids */
                final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                        + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + fixedQuery, newWindow));
            } else {
                /* Otherwise do a search against the title, description and content of the topics */
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                        + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + query + ";"
                        + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + query + ";"
                        + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + query + ";"
                        + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.LOGIC_FILTER_VAR + "=" + org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants.OR_LOGIC, newWindow));
            }
        }
    }

    protected final void bind(final int topicId, final String pageId, @NotNull final BaseTemplateViewInterface display) {
        this.display = display;
        this.helpTopicId = topicId;

        this.setFeedbackLink(pageId);
        this.bindStandardButtons();

        /* Watch for page closes */
        Window.addWindowClosingHandler(new ClosingHandler() {

            @Override
            public void onWindowClosing(final ClosingEvent event) {
                if (display.getTopLevelPanel().isAttached() && hasUnsavedChanges()) {
                    event.setMessage(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
                }
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

        display.getHelp().addClickHandler(openHelpClickHandler);
        display.getHelpDialog().getOK().addClickHandler(okClickHandler);
    }

    /**
     * Set the feedback URL for the page.
     *
     * @param pageId The id of the page
     */
    protected final void setFeedbackLink(final String pageId) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);
    }
}
