package org.jboss.pressgangccms.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTBugzillaBugV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;

@Dependent
public class TopicRevisionsPresenter extends TemplatePresenter
{
	private String topicId;

	public interface Display extends TopicViewInterface
	{
		AsyncDataProvider<RESTTopicV1> getProvider();

		void setProvider(final AsyncDataProvider<RESTTopicV1> provider);

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
	public void go(final HasWidgets container)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseToken(final String historyToken)
	{
		// TODO Auto-generated method stub		
	}
}
