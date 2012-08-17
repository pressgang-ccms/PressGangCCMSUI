package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.editor.SearchUIProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class SearchView extends BaseTemplateView implements SearchPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchView";
	
	private final PushButton search = new PushButton(new Image(ImageResources.INSTANCE.search48()), new Image(ImageResources.INSTANCE.searchDown48()));

	/** The GWT Editor Driver */
	private final SearchPresenterDriver driver = GWT.create(SearchPresenterDriver.class);
	/** The UI hierarchy */
	private final SearchUIProjects searchUIProjects = new SearchUIProjects();
	
	@Override
	public SearchUIProjects getSearchUIProjects()
	{
		return searchUIProjects;
	}

	@Override
	public SearchPresenterDriver getDriver()
	{
		return driver;
	}

	@Override
	public PushButton getSearch()
	{
		return search;
	}

	public SearchView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());
	}
	
	@Override
	public void initialise(final RESTTagCollectionV1 tags)
	{
	    /* Build the action bar icons */
		search.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.searchHover48()));
		addActionButton(search);
		
		addRightAlignedActionButtonPaddingPanel();
		
		/* Construct a hierarchy of tags from the tag collection */
		getSearchUIProjects().initialize(tags);
		
		/* SearchUIProjectsEditor is a grid */
		final SearchUIProjectsEditor editor = new SearchUIProjectsEditor(driver, searchUIProjects);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(searchUIProjects);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}
}
