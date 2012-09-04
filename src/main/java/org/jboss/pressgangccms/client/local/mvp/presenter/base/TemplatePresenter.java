package org.jboss.pressgangccms.client.local.mvp.presenter.base;

import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.events.ImagesViewEvent;
import org.jboss.pressgangccms.client.local.events.SearchViewEvent;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;

/**
 * The base class for all presenters that display the standard template
 * 
 * @author Matthew Casperson
 * 
 */
abstract public class TemplatePresenter implements Presenter
{
	@Inject
	protected HandlerManager eventBus;

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

		display.getImages().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				eventBus.fireEvent(new ImagesViewEvent());
			}
		});
	}

	/**
	 * Parse the history token to extract some state from it
	 * 
	 * @param historyToken
	 *            The history token in the URL
	 */
	abstract public void parseToken(final String historyToken);
}
