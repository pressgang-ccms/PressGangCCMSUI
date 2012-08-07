package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgangccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class SearchPresenter implements Presenter
{
	public interface Display extends TemplateInterface
	{

	}

	@Inject
	private Display display;

	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		getProjects();
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
		final RemoteCallback<RESTProjectCollectionV1> successCallback = new RemoteCallback<RESTProjectCollectionV1>()
		{
			@Override
			public void callback(final RESTProjectCollectionV1 retValue)
			{
				try
				{
					final String message = retValue.getItems().size() + " projects returned.";
					System.out.println(message);
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
					final String error = "ERROR! REST call to find projects failed with a HTTP error.\nMessage: " + message + "\nException: " + throwable.toString();
					System.out.println(error);
					return true;
				}
				finally
				{
					stopProcessing();
				}

			}
		};

		final RESTInterfaceV1 restMethod = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);
		final String expand = "{\"branches\":[{\"trunk\":{\"showSize\":true,\"name\":\"projects\"}}]}";

		try
		{
			startProcessing();
			restMethod.getJSONProjects(URL.encode(expand));
		}
		catch (final Exception ex)
		{
			final String error = "ERROR! REST call to find projects failed with an exception.";
			System.out.println(error);
			stopProcessing();
		}

	}
}
