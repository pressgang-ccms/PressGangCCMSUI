package org.jboss.pressgangccms.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicRevisionsPresenter extends TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicHistoryView";
    
    private String topicId;

    public interface Display extends TopicViewInterface {
        EnhancedAsyncDataProvider<RESTTopicV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicV1> provider);

        CellTable<RESTTopicV1> getResults();

        SimplePager getPager();

        Column<RESTTopicV1, String> getViewButton();

        Column<RESTTopicV1, String> getDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        RESTTopicV1 getRevisionTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setRevisionTopic(RESTTopicV1 revisionTopic);
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
