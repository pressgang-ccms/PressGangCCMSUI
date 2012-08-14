package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.ui.editor.SearchUIProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.TopicView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgangccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicPresenter extends TemplatePresenter
{
	// Empty interface declaration, similar to UiBinder
	public interface TopicPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1BasicDetailsEditor>
	{
	}

	public interface Display extends BaseTemplateViewInterface
	{
		void initialize(final RESTTopicV1 topic);
	}

	private String topicId;

	@Inject
	private Display display;

	public void parseToken(final String searchToken)
	{
		topicId = searchToken.replace(TopicView.HISTORY_TOKEN + ";", "");
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
