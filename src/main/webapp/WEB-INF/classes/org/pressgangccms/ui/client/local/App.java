package org.pressgangccms.ui.client.local;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

@EntryPoint
public class App
{
	private HandlerManager eventBus = new HandlerManager(null);

	@Inject
	private AppController appController;
	
	public App()
	{
		System.out.println("Constructed");
	}

	@AfterInitialization
	public void startApp()
	{
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler()
		{
			public void onUncaughtException(final Throwable ex)
			{
				ex.printStackTrace();
				Window.alert("Uncaught exception event");
			}
		});
		
		appController.go(RootPanel.get());

	}

	@Produces
	private HandlerManager produceEventBus()
	{
		return eventBus;
	}	
}