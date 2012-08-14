package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.TriStatePushButton;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

/**
 * A GWT Editor to provide a visual representation of SearchUITag
 * 
 * @author Matthew Casperson
 */
public class SearchUITagEditor implements Editor<SearchUITag>
{
	private final SearchUIProjects searchUIProjects;
	
	final Label name = new Label();
	final TriStatePushButton state = new TriStatePushButton();

	public SearchUITagEditor(final SearchPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		this.searchUIProjects = searchUIProjects;
		
		name.addStyleName("TagLabel");

		state.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				driver.flush();
			}
		});
	}
}
