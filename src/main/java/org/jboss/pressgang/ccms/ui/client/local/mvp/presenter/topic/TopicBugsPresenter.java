package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicViewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TopicBugsPresenter extends BaseTopicViewPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicBugsView";
    
    private String topicId;

    public interface Display extends BaseTopicViewInterface {

        EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTBugzillaBugCollectionItemV1> provider);

        CellTable<RESTBugzillaBugCollectionItemV1> getResults();

        SimplePager getPager();
    }

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void process(final int topicId, final String pageId)
    {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
    }
}
