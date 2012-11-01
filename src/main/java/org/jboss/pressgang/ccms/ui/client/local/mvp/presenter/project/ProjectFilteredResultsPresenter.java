package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults.BaseFilteredResultsComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;

@Dependent
public class ProjectFilteredResultsPresenter implements TemplatePresenter {
    
    public static final String HISTORY_TOKEN = "ProjectFilteredResultsView";

    public interface Display extends
            BaseFilteredResultsViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();
    }

    public interface LogicCompnent
            extends
            BaseFilteredResultsComponentInterface<Display, RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {
        @Override
        
        void bind(final String queryString, final Display display,
                final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;

    @Inject
    private LogicCompnent component;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(queryString, display, display);
    }
}
