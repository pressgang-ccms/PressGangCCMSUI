package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.ui.editor.SearchUIProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class SearchPresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		// Empty interface declaration, similar to UiBinder
		public interface Driver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor>
		{
		}

		SearchUIProjects getSearchUIProjects();

		PushButton getSearch();

		Driver getDriver();

		void initialise(final RESTTagCollectionV1 tags);
	}

	@Inject
	private Display display;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		getProjects();

		bind();
	}

	protected void bind()
	{
		super.bind(display);

		display.getSearch().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				
			}
		});
	}

	private void stopProcessing()
	{
		display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		display.setSpinnerVisible(true);
	}

	private void getProjects()
	{
		final RemoteCallback<RESTTagCollectionV1> successCallback = new RemoteCallback<RESTTagCollectionV1>()
		{
			@Override
			public void callback(final RESTTagCollectionV1 retValue)
			{
				try
				{
					final String message = retValue.getItems().size() + " tags returned.";
					System.out.println(message);

					display.initialise(retValue);
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
					final String error = "ERROR! REST call to find tags failed with a HTTP error.\nPlease check your internet connection.\nMessage: " + message + "\nException: " + throwable.toString();
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
		final String expand = "{\"branches\":[{\"branches\":[{\"trunk\":{\"showSize\":true,\"name\":\"categories\"}},{\"trunk\":{\"showSize\":true,\"name\":\"projects\"}}],\"trunk\":{\"showSize\":true,\"name\":\"tags\"}}]}";

		try
		{
			startProcessing();
			restMethod.getJSONTags(expand);
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find tags failed with an exception.";
			System.out.println(error);
			Window.alert(error);
			stopProcessing();
		}

	}

	@Override
	public void parseToken(String historyToken)
	{
		// TODO Auto-generated method stub
		
	}
}
