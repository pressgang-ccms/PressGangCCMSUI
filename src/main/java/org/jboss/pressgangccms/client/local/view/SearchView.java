package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.ui.editor.SearchUIProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class SearchView extends BaseTemplateView implements SearchPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchView";
	
	private final PushButton search = new PushButton(new Image(Constants.resources.search48()), new Image(Constants.resources.searchDown48()));

	// Empty interface declaration, similar to UiBinder
	interface Driver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor>
	{
	}

	// Create the Driver
	final Driver driver = GWT.create(Driver.class);
	
	public PushButton getSearch()
	{
		return search;
	}

	public SearchView()
	{
		super(Constants.pressGangCCMSUI.PressGangCCMS(), Constants.pressGangCCMSUI.Search());
	}
	
	@Override
	public void initialise(final RESTTagCollectionV1 tags)
	{
	    /* Build the action bar icons */
		this.getTopActionPanel().add(this.search);
		
		/* Construct a hierarchy of tags from the tag collection */
		final SearchUIProjects searchUIProjects = new SearchUIProjects(tags);
		
		/* SearchUIProjectsEditor is a grid */
		final SearchUIProjectsEditor editor = new SearchUIProjectsEditor();
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(searchUIProjects);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}
}
