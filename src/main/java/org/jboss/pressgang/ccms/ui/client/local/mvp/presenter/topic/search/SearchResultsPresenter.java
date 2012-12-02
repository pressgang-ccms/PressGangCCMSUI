package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults.BaseFilteredResultsComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class SearchResultsPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchResultsView";

    public interface Display extends
            BaseFilteredResultsViewInterface<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {

    }

    public interface LogicComponent extends BaseFilteredResultsComponentInterface<Display, RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> {
        @Override
        void bind(final int topicId, final String pageId, final String queryString,
                final SearchResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    private String queryString;

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString, display, display);
    }
}
