package org.jboss.pressgangccms.client.local.ui.search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.sort.SearchUIProjectNameSort;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

/**
 * The REST interface does not define a hierarchy or projects->categories->tags.
 * Instead, tags belong to both categories and projects, but the projects and
 * categories don't have any direct relationship.
 * 
 * When being viewed however tags are displayed in the
 * projects->categories->tags hierarchy. This class defines the top level
 * collection of projects.
 * 
 * @author Matthew Casperson
 */
public class SearchUIProjects
{
	private final LinkedList<SearchUIProject> projects = new LinkedList<SearchUIProject>();

	public List<SearchUIProject> getProjects()
	{
		return projects;
	}

	public SearchUIProjects(final RESTTagCollectionV1 tags)
	{
		if (tags == null)
			throw new NullPointerException("tags parameter cannot be null");
		if (tags.getItems() == null)
			throw new IllegalArgumentException("tags.getItems() cannot be null");

		for (final RESTTagV1 tag : tags.getItems())
		{
			if (tag.getProjects() == null)
				throw new IllegalArgumentException("tag.getProjects() cannot be null");
			if (tag.getProjects().getItems() == null)
				throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");

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
		
		Collections.sort(projects, new SearchUIProjectNameSort());

		/*
		 * Add the common project to the start of the list. Do this after all
		 * the projects have been added, so it won't get confused with a project
		 * that might be called common.
		 */
		final SearchUIProject common = new SearchUIProject(PressGangCCMSUI.INSTANCE.Common());
		common.populateCategoriesWithoutProject(tags);
		projects.addFirst(common);
	}
}
