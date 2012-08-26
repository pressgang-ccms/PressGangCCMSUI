package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;
import org.jboss.pressgangccms.client.local.view.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;

@Dependent
public class TopicTagsPresenter extends TemplatePresenter
{
	private String topicId;

	@Inject
	private Display display;
	
	public interface Display extends TopicViewInterface
	{
		@Override
		void initialize(final RESTTopicV1 topic);
		void initializeNewTags(final SearchUIProjects tags);
		void updateNewTagCategoriesDisplay();
		void updateNewTagTagDisplay();
		ValueListBox<SearchUITag> getMyTags();
		ValueListBox<SearchUICategory> getCategories();
		ValueListBox<SearchUIProject> getProjects();
		TopicTagViewProjectsEditor getEditor();
		PushButton getAdd();
	}

	@Override
	public void parseToken(final String searchToken)
	{
		topicId = searchToken.replace(TopicXMLErrorsView.HISTORY_TOKEN + ";", "");
	}
	
	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		getTopic();

		bind();
	}
	
	private void getTopic()
	{
		final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>()
		{
			@Override
			public void begin()
			{
				startProcessing();
			}

			@Override
			public void generalException(final Exception ex)
			{
				stopProcessing();
			}

			@Override
			public void success(final RESTTopicV1 retValue)
			{
				try
				{
					display.initialize(retValue);
				}
				finally
				{
					stopProcessing();
				}
			}

			@Override
			public void failed()
			{
				stopProcessing();
			}
		};
		
		try
		{
			RESTCalls.getTopic(callback, Integer.parseInt(topicId));
		}
		catch (final NumberFormatException ex)
		{
			stopProcessing();
		}
	}
	
	private void bind()
	{

	}

	private void stopProcessing()
	{
		display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		display.setSpinnerVisible(true);
	}
}
