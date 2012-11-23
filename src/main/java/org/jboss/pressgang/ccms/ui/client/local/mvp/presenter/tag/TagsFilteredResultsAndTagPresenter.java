package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * This presenter takes the TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and
 * TagCategoriesView to provide a way to edit the properties and relationships of a tag.
 * 
 * @author Matthew Casperson
 */
@Dependent
public class TagsFilteredResultsAndTagPresenter implements TemplatePresenter {

    /** The history token used to identify this view */
    public static final String HISTORY_TOKEN = "TagsFilteredResultsAndTagView";

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the four views
     * TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and TagCategoriesView.
     * 
     * @author Matthew Casperson
     * 
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
        
    }

    public interface LogicComponent extends Component<Display> {
        void bind(final int topicId, final String pageId, final TagsFilteredResultsAndTagPresenter.Display display, BaseTemplateViewInterface waitDisplay,
                final TagFilteredResultsPresenter.Display filteredResultsDisplay,
                final TagFilteredResultsPresenter.LogicComponent filteredResultsComponent,
                final TagPresenter.Display resultDisplay, final TagPresenter.LogicComponent resultComponent,
                final TagProjectsPresenter.Display projectsDisplay, final TagProjectsPresenter.LogicComponent projectsComponent,
                final TagCategoriesPresenter.Display categoriesDisplay,
                final TagCategoriesPresenter.LogicComponent categoriesComponent);
    }

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    /**
     * An Errai injected instance of a class that implements TagFilteredResultsPresenter.Display. This is the view that displays
     * the list of tags.
     */
    @Inject
    private TagFilteredResultsPresenter.Display filteredResultsDisplay;

    @Inject
    private TagFilteredResultsPresenter.LogicComponent filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements TagPresenter.Display. This is the view that displays the fields of
     * the tag (name, description etc)
     */
    @Inject
    private TagPresenter.Display resultDisplay;

    @Inject
    private TagPresenter.LogicComponent resultComponent;

    /**
     * An Errai injected instance of a class that implements TagProjectsPresenter.Display. This is the view that lists all the
     * projects that the tag can be added to or removed from.
     */
    @Inject
    private TagProjectsPresenter.Display projectsDisplay;

    @Inject
    private TagProjectsPresenter.LogicComponent projectsComponent;

    /**
     * An Errai injected instance of a class that implements TagCategoriesPresenter.Display. This is the view that lists all the
     * categories that the tag can be added to or removed from.
     */
    @Inject
    private TagCategoriesPresenter.Display categoriesDisplay;

    @Inject
    private TagCategoriesPresenter.LogicComponent categoriesComponent;

    /** The tag query string extracted from the history token */
    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.displaySearchResultsView(filteredResultsDisplay);

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        categoriesDisplay.getSplit().setSplitPosition(categoriesDisplay.getPossibleChildrenResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        
        filteredResultsComponent.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString, filteredResultsDisplay, display);
        projectsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, projectsDisplay, display);
        categoriesComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, categoriesDisplay, display);
        resultComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, resultDisplay, display);
        
        component.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display, display, filteredResultsDisplay, filteredResultsComponent, resultDisplay, resultComponent,
                projectsDisplay, projectsComponent, categoriesDisplay, categoriesComponent);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }       
    }

}
