package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicBugsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicBugsView";
    
    private String topicId;

    public interface Display extends TopicViewInterface {

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
        // TODO Auto-generated method stub

    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
    }
}
