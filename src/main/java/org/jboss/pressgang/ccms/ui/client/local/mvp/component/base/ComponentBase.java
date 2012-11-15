package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base;

import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.CreateTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ProjectsFilteredResultsAndProjectViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchTagsFieldsAndFiltersViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;

/**
 * Views can either be stand alone, displayed in isolation. Views can also be combined into a presenter to provide a more
 * complex UI.
 * 
 * Because of the One-To_many relationship between presenters and views, the logic that is attached to a view needs to be shared
 * across multiple presenters. Controllers are used to provide the logic that should be applied to a view. This allows
 * presenters to mix and match controllers in much the same way they mix and match views.
 * 
 * @author Matthew Casperson
 * 
 * @param <S> The type of the view
 */
abstract public class ComponentBase<S extends BaseTemplateViewInterface> implements Component<S>, EditableView {

    private static final RegExp ID_SEARCH = RegExp.compile(",*(\\s*\\d+\\s*,+)*\\s*\\d+\\s*,*");

    @Inject
    private HandlerManager eventBus;

    protected BaseTemplateViewInterface waitDisplay;
    protected S display;

    @Override
    public boolean isOKToProceed() {
        if (hasUnsavedChanges())
            return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
        return true;
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    /**
     * Called to bind the UI elements to event handlers.
     * 
     * @param display The main template display
     */
    protected void bindStandardButtons() {
        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new SearchTagsFieldsAndFiltersViewEvent());
            }
        });

        display.getCreateTopic().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new CreateTopicViewEvent());
            }
        });

        display.getBug().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                Window.open(Constants.BUGZILLA_URL, "_blank", "");
            }
        });

        display.getImages().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getTags().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getCategories().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed())
                    eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
            }
        });

        display.getProjects().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ProjectsFilteredResultsAndProjectViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX));
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
                doQuickSearch();
            }
        });
        
        display.getQuickSearchQuery().addKeyPressHandler(new KeyPressHandler() {
            
            @Override
            public void onKeyPress(final KeyPressEvent event) {
                if (event.getCharCode() == KeyCodes.KEY_ENTER)
                {
                    doQuickSearch();
                }                
            }
        });
    }

    private void doQuickSearch() {
        final String query = display.getQuickSearchQuery().getValue().trim();

        if (query.isEmpty()) {
            Window.alert(PressGangCCMSUI.INSTANCE.PleaseEnterValue());
        } else {
            if (ID_SEARCH.test(query)) {
                /* If the search query was numbers and integers, assume that we are searching for topics ids */
                final String fixedQuery = GWTUtilities.fixUpIdSearchString(query);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                        + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + fixedQuery));
            } else {
                /* Otherwise do a search against the title, description and content of the topics */
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX
                        + CommonFilterConstants.TOPIC_XML_FILTER_VAR + "=" + query + ";"
                        + CommonFilterConstants.TOPIC_TITLE_FILTER_VAR + "=" + query + ";"
                        + CommonFilterConstants.TOPIC_DESCRIPTION_FILTER_VAR + "=" + query + ";"
                        + CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.OR_LOGIC));
            }
        }
    }

    @Override
    public void bind(final S display, final BaseTemplateViewInterface waitDisplay) {
        this.display = display;
        this.waitDisplay = waitDisplay;
        bindStandardButtons();

    }

    @Override
    public void setFeedbackLink(final String pageId) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + pageId);
    }
}
