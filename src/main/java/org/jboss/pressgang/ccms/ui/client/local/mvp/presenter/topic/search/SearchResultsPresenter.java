package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class SearchResultsPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        CellTable<RESTTopicCollectionItemV1> getResults();

        SimplePager getPager();
    }

    public interface LogicComponent extends Component<Display> {
        void bind(final String queryString, final SearchResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
        ProviderUpdateData<RESTTopicCollectionItemV1> getTopicProviderData();
        void setTopicProviderData(ProviderUpdateData<RESTTopicCollectionItemV1> topicProviderData);
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
        component.bind(queryString, display,  display);
    }
}
