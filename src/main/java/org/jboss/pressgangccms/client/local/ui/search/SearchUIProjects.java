package org.jboss.pressgangccms.client.local.ui.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.ui.TextAndImageButton;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;

/**
 * The REST interface does not define a hierarchy or projects->categories->tags. Instead, tags belong to both categories and projects,
 * but the projects and categories don't have any direct relationship.
 * 
 * When being viewed however tags are displayed in the projects->categories->tags hierarchy. This class defines the top level collection
 * of projects.
 * 
 * @author Matthew Casperson
 */
public class SearchUIProjects
{
	private final List<SearchUIProject> projects = new ArrayList<SearchUIProject>();
	
	public List<SearchUIProject> getProjects()
	{
		return projects;
	}

	public SearchUIProjects(final RESTTagCollectionV1 tags)
	{
		if (tags == null) throw new NullPointerException("tags parameter cannot be null");
		if (tags.getItems() == null) throw new IllegalArgumentException("tags.getItems() cannot be null");
		
		for (final RESTTagV1 tag : tags.getItems())
		{
			if (tag.getProjects() == null) throw new IllegalArgumentException("tag.getProjects() cannot be null");
			if (tag.getProjects().getItems() == null) throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
			
			for (final RESTProjectV1 project : tag.getProjects().getItems())
			{
				final SearchUIProject searchUIProject = new SearchUIProject(project.getName());
				if (!projects.contains(searchUIProject))
				{
					searchUIProject.populateCategories(project, tags);
					projects.add(searchUIProject);
					
				}
			}
		}
		
		/* Add the common project */
		projects.add(new SearchUIProject(Constants.pressGangCCMSUI.Common()));
	}
}
