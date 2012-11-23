package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

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
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> {

    }

    public interface LogicComponent extends Component<Display> {
        void bind(final int topicId, final String pageId, final CategoryFilteredResultsPresenter.Display filteredResultsDisplay,
                final CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
                final CategoryPresenter.Display entityPropertiesView, final CategoryTagPresenter.Display tagDisplay,
                final CategoryTagPresenter.LogicComponent tagComponent,
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

    @Inject
    private CategoryTagPresenter.LogicComponent tagComponent;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
  
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        resultComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, resultDisplay, display);
        tagComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, tagDisplay, display);
        component.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, filteredResultsDisplay, filteredResultsComponent, resultDisplay, tagDisplay, tagComponent, display,
                display);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

}
