package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class ProjectsFilteredResultsAndProjectPresenter implements TemplatePresenter {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "ProjectsFilteredResultsAndProjectView";

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     * 
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

    }

    public interface LogicComponent extends Component<Display> {
        void bind(final ProjectFilteredResultsPresenter.Display filteredResultsDisplay,
                final ProjectFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
                final ProjectPresenter.Display resultDisplay, final ProjectTagPresenter.Display tagDisplay,
                final ProjectTagPresenter.LogicComponent tagComponent,
                final ProjectsFilteredResultsAndProjectPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
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
     * An Errai injected instance of a class that implements ProjectFilteredResultsPresenter.Display. This is the view that
     * displays the list of categories.
     */
    @Inject
    private ProjectFilteredResultsPresenter.Display filteredResultsDisplay;

    /** An Errai injected instance of a class that implements ProjectFilteredResultsPresenter.LogicCompnent */
    @Inject
    private ProjectFilteredResultsPresenter.LogicCompnent filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements ProjectPresenter.Display. This is the view that displays the
     * fields of the category (name, description etc)
     */
    @Inject
    private ProjectPresenter.Display resultDisplay;

    /** An Errai injected instance of a class that implements ProjectPresenter.LogicComponent */
    @Inject
    private ProjectPresenter.LogicComponent resultComponent;

    @Inject
    private ProjectTagPresenter.Display tagDisplay;

    @Inject
    private ProjectTagPresenter.LogicComponent tagComponent;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        filteredResultsComponent.bind(queryString, filteredResultsDisplay, display);
        resultComponent.bind(resultDisplay, display);
        tagComponent.bind(tagDisplay, display);
        component.bind(filteredResultsDisplay, filteredResultsComponent, resultDisplay, tagDisplay, tagComponent, display,
                display);
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
                if (queryElements[0].equals("entityIds")) {
                    this.filteredResultsDisplay.getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityName")) {
                    this.filteredResultsDisplay.getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("entityDesc")) {
                    this.filteredResultsDisplay.getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }
}
