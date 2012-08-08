package org.jboss.pressgangccms.client.local.presenter.base;

import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.events.SearchViewEvent;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * The base class for all presenters that display the standard template
 * @author Matthew Casperson
 *
 */
abstract public class TemplatePresenter implements Presenter
{
	@Inject	private HandlerManager eventBus;

	/**
	 * Called to bind the UI elements to event handlers
	 */
	protected void bind(final BaseTemplateViewInterface display)
	{
		display.getSearch().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				eventBus.fireEvent(new SearchViewEvent());
			}
		});
		
		display.getBug().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				Window.open(Constants.BUGZILLA_URL, "_blank", "");
			}
		});
	}

	
}
