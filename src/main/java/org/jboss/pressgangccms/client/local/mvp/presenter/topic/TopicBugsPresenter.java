package org.jboss.pressgangccms.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgangccms.rest.v1.entities.RESTBugzillaBugV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicBugsPresenter extends TemplatePresenter {
    private String topicId;

    public interface Display extends TopicViewInterface {
        @Override
        void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType);

        EnhancedAsyncDataProvider<RESTBugzillaBugV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTBugzillaBugV1> provider);

        CellTable<RESTBugzillaBugV1> getResults();

        SimplePager getPager();
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
