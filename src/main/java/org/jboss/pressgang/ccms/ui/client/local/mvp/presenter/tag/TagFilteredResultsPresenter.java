package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults.BaseFilteredResultsComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;

@Dependent
public class TagFilteredResultsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagFilteredResultsView";

    public interface Display extends BaseFilteredResultsViewInterface<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();
    }

    public interface LogicComponent
            extends
            BaseFilteredResultsComponentInterface<TagFilteredResultsPresenter.Display, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
        @Override
        void bind(final String queryString, final TagFilteredResultsPresenter.Display display,
                final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(queryString, display, display);
    }

}
