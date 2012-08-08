package org.jboss.pressgangccms.client.local.ui.search;

import java.util.ArrayList;
import java.util.List;

import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

/**
 * This class represents a single project, with child categories 
 * @author Matthew Casperson
 */
public class SearchUIProject extends SearchUIBase
{
	private final List<SearchUICategory> categories = new ArrayList<SearchUICategory>();
	
	public List<SearchUICategory> getCategories()
	{
		return categories;
	}

	public SearchUIProject(final String name)
	{
		super(name);
	}
	
	public void populateCategories(final RESTProjectV1 project, final RESTTagCollectionV1 tags)
	{
		if (tags == null) throw new NullPointerException("tags parameter cannot be null");
		if (tags.getItems() == null) throw new IllegalArgumentException("tags.getItems() cannot be null");
		if (project == null) throw new IllegalArgumentException("project cannot be null");
		
		for (final RESTTagV1 tag : tags.getItems())
		{
			if (tag.getProjects().getItems() == null) throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
			
			if (tag.getProjects().getItems().contains(project))
			{
				if (tag.getCategories().getItems() == null) throw new IllegalArgumentException("tag.getCategories().getItems() cannot be null");
				
				for (final RESTCategoryV1 category : tag.getCategories().getItems())
				{
					final SearchUICategory searchUICategory = new SearchUICategory(category.getName());
					if (!categories.contains(searchUICategory))
					{
						searchUICategory.populateCategories(project, category, tags);
						categories.add(searchUICategory);
					}
				}
			}
		}
	}
}
