package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

/**
 * This presenter takes the CategoryFilteredResults view to provide a list of categories, and the CategoryView to provide a way
 * to edit the properties of a category.
 * 
 * @author Matthew Casperson
 */
@Dependent
public class CategoriesFilteredResultsAndCategoryPresenter implements TemplatePresenter {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "CategoriesFilteredResultsAndCategoryView";

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     * 
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface {

    }

    public interface LogicComponent extends Component<Display> {
        void bind(final CategoryFilteredResultsPresenter.Display filteredResultsDisplay,
                final CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
                final CategoryPresenter.Display resultDisplay,
                final CategoriesFilteredResultsAndCategoryPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
    }

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /** An Errai injected instance of a class that implements LogicCompnent */
    @Inject
    private LogicComponent component;

    /**
     * An Errai injected instance of a class that implements CategoryFilteredResultsPresenter.Display. This is the view that
     * displays the list of categories.
     */
    @Inject
    private CategoryFilteredResultsPresenter.Display filteredResultsDisplay;

    /** An Errai injected instance of a class that implements CategoryFilteredResultsPresenter.LogicCompnent */
    @Inject
    private CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements CategoryPresenter.Display. This is the view that displays the
     * fields of the category (name, description etc)
     */
    @Inject
    private CategoryPresenter.Display resultDisplay;

    /** An Errai injected instance of a class that implements CategoryPresenter.LogicComponent */
    @Inject
    private CategoryPresenter.LogicComponent resultComponent;

    @Inject
    private CategoryTagPresenter.Display tagDisplay;

    /**
     * The category query string extracted from the history token
     */
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
                Preferences.INSTANCE.getInt(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);

        filteredResultsComponent.bind(queryString, filteredResultsDisplay, display);
        resultComponent.bind(resultDisplay, display);
        component.bind(filteredResultsDisplay, filteredResultsComponent, resultDisplay, display, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("categoryIds")) {
                    this.filteredResultsDisplay.getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("categoryName")) {
                    this.filteredResultsDisplay.getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("categoryDesc")) {
                    this.filteredResultsDisplay.getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

}
