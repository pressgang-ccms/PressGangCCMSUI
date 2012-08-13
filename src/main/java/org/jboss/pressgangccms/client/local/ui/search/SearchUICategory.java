package org.jboss.pressgangccms.client.local.ui.search;

import java.util.ArrayList;
import java.util.List;

import org.jboss.pressgangccms.client.local.ui.TriStateSelectionState;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

/**
 * This class represents a single category assigned to a project, with child tags.
 * @author Matthew Casperson
 */
public class SearchUICategory extends SearchUIBase
{
	private final List<SearchUITag> myTags = new ArrayList<SearchUITag>();
	
	public class TagSummary
	{
		private final String name;
		private final int tagCount;
		private final int includedTags;
		private final int excludedTags;

		public String getName()
		{
			return name;
		}

		public int getTagCount()
		{
			return tagCount;
		}

		public int getIncludedTags()
		{
			return includedTags;
		}

		public int getExcludedTags()
		{
			return excludedTags;
		}

		public TagSummary(final String name, final int tagCount, final int includedTags, final int excludedTags)
		{
			this.name = name;
			this.tagCount = tagCount;
			this.includedTags = includedTags;
			this.excludedTags = excludedTags;
		}
	}
	
	public TagSummary getSummary()
	{
		int includedTags = 0;
		int excludedTags = 0;
		for (final SearchUITag tag : myTags)
		{
			if (tag.getState() == TriStateSelectionState.SELECTED)
				++includedTags;
			else if (tag.getState() == TriStateSelectionState.UNSELECTED)
				++excludedTags;
		}
		
		return new TagSummary(this.getName(), this.myTags.size(), includedTags, excludedTags);
	}

	public List<SearchUITag> getMyTags()
	{
		return myTags;
	}

	public SearchUICategory(final String name)
	{
		super(name);
	}

	public void populateCategories(final RESTProjectV1 project, final RESTCategoryV1 category, final RESTTagCollectionV1 tags)
	{
		if (tags == null)
			throw new NullPointerException("tags parameter cannot be null");
		if (tags.getItems() == null)
			throw new IllegalArgumentException("tags.getItems() cannot be null");
		if (project == null)
			throw new IllegalArgumentException("project cannot be null");
		if (category == null)
			throw new IllegalArgumentException("category cannot be null");

		for (final RESTTagV1 tag : tags.getItems())
		{
			if (tag.getProjects().getItems() == null)
				throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
			if (tag.getCategories().getItems() == null)
				throw new IllegalArgumentException("tag.getCategories().getItems() cannot be null");

			if (tag.getProjects().getItems().contains(project) && tag.getCategories().getItems().contains(category))
			{
				final SearchUITag searchUITag = new SearchUITag(tag.getName(), tag.getId());
				if (!myTags.contains(searchUITag))
				{
					myTags.add(searchUITag);
				}
			}
		}
	}
	
	public void populateCategoriesWithoutProject(final RESTCategoryV1 category, final RESTTagCollectionV1 tags)
	{
		if (tags == null)
			throw new NullPointerException("tags parameter cannot be null");
		if (tags.getItems() == null)
			throw new IllegalArgumentException("tags.getItems() cannot be null");
		if (category == null)
			throw new IllegalArgumentException("category cannot be null");

		for (final RESTTagV1 tag : tags.getItems())
		{
			if (tag.getProjects().getItems() == null)
				throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
			if (tag.getCategories().getItems() == null)
				throw new IllegalArgumentException("tag.getCategories().getItems() cannot be null");

			if (tag.getProjects().getItems().isEmpty() && tag.getCategories().getItems().contains(category))
			{
				final SearchUITag searchUITag = new SearchUITag(tag.getName(), tag.getId());
				if (!myTags.contains(searchUITag))
				{
					myTags.add(searchUITag);
				}
			}
		}
	}
}
