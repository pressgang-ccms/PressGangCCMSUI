package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.PathSegmentImpl;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgangccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

@Dependent
public class SearchResultsPresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		AsyncDataProvider<RESTTopicV1> getProvider();

		void setProvider(final AsyncDataProvider<RESTTopicV1> provider);

		CellTable<RESTTopicV1> getResults();

		SimplePager getPager();
	}

	@Inject
	private Display display;

	private String queryString;
	
	public void parseToken(final String searchToken)
	{
		queryString = searchToken.replace(SearchResultsView.HISTORY_TOKEN + ";", "");
	}
	
	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		bind();
	}

	private void bind()
	{
		super.bind(display);

		final AsyncDataProvider<RESTTopicV1> provider = new AsyncDataProvider<RESTTopicV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTTopicV1> display)
			{
				final int start = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = start + length;

				final RemoteCallback<RESTTopicCollectionV1> successCallback = new RemoteCallback<RESTTopicCollectionV1>()
				{
					@Override
					public void callback(final RESTTopicCollectionV1 retValue)
					{
						try
						{
							final String message = retValue.getSize() + " topics returned.";
							System.out.println(message);
							updateRowData(start, retValue.getItems());
							updateRowCount(retValue.getSize(), true);
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
							final String error = "ERROR! REST call to find topics failed with a HTTP error.\nPlease check your internet connection.\nMessage: " + message + "\nException: " + throwable.toString();
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
				final String expand = "{\"branches\":[{\"trunk\":{\"start\": " + start + ", \"end\": " + end + ", \"showSize\":true,\"name\": \"topics\"}}]}";

				try
				{
					startProcessing();
					restMethod.getJSONTopicsWithQuery(new PathSegmentImpl(queryString), expand);
				}
				catch (final Exception ex)
				{
					final String error = "ERROR! REST call to find topics failed with an exception.";
					System.out.println(error);
					Window.alert(error);
					stopProcessing();
				}
			}
		};
		
		display.setProvider(provider);		
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
