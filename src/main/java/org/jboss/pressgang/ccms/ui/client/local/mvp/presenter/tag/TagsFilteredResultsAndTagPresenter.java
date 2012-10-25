package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

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
    public interface Display extends BaseTemplateViewInterface {
        /**
         * @return The panel used to hold the list of tags
         */
        SimpleLayoutPanel getResultsPanel();

        /**
         * @return The panel used to hold the views that display the tag details
         */
        SimpleLayoutPanel getViewPanel();

        /**
         * @return The panel that holds the action buttons for the tag detail views
         */
        SimpleLayoutPanel getViewActionButtonsPanel();

        /**
         * @return The panel that holds the action buttons for the list of tags
         */
        SimpleLayoutPanel getResultsActionButtonsPanel();

        /**
         * @return The split panel that separates the tag list from the tag details views
         */
        HandlerSplitLayoutPanel getSplitPanel();
    }

    public interface LogicComponent extends Component<Display> {
        void bind(final TagsFilteredResultsAndTagPresenter.Display display, BaseTemplateViewInterface waitDisplay,
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

        display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        categoriesDisplay.getSplit().setSplitPosition(categoriesDisplay.getPossibleChildrenResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        
        filteredResultsComponent.bind(queryString, filteredResultsDisplay, display);
        projectsComponent.bind(projectsDisplay, display);
        categoriesComponent.bind(categoriesDisplay, display);
        resultComponent.bind(resultDisplay, display);
        
        component.bind(display, display, filteredResultsDisplay, filteredResultsComponent, resultDisplay, resultComponent,
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
