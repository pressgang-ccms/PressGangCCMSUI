package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
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
		final RemoteCallback<RESTTopicV1> successCallback = new RemoteCallback<RESTTopicV1>()
		{
			@Override
			public void callback(final RESTTopicV1 retValue)
			{
				try
				{
					final String message = "Topic " + retValue.getId().toString() + " returned";
					System.out.println(message);

					display.initialize(retValue);
				}
				finally
				{
					stopProcessing();
				}
			}
		};

		final ErrorCallback errorCallback = new ErrorCallback()
		{
			@Override
			public boolean error(final Message message, final Throwable throwable)
			{
				try
				{
					final String error = "ERROR! REST call to find topic failed with a HTTP error.\nPlease check your internet connection.\nMessage: " + message + "\nException: " + throwable.toString();
					System.out.println(error);
					Window.alert(error);
					return true;
				}
				finally
				{
					stopProcessing();
				}

			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);
		/* Expand the categories and projects in the tags */
		final String expand = "";

		try
		{
			startProcessing();
			restMethod.getJSONTopic(Integer.parseInt(topicId), expand);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find topic failed with an exception.";
			System.out.println(error);
			Window.alert(error);
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
