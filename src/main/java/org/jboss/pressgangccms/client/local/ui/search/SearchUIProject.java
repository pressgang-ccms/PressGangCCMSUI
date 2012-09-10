package org.jboss.pressgangccms.client.local.ui.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.pressgangccms.client.local.sort.SearchUINameSort;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single project, with child categories
 * 
 * @author Matthew Casperson
 */
public class SearchUIProject extends SearchUIBase {
    private final List<SearchUICategory> categories = new ArrayList<SearchUICategory>();

    public class CategorySummary {
        private final String name;
        private final int categoryCount;
        private final int includedTags;
        private final int excludedTags;

        public String getName() {
            return name;
        }

        public int getCategoryCount() {
            return categoryCount;
        }

        public int getIncludedTags() {
            return includedTags;
        }

        public int getExcludedTags() {
            return excludedTags;
        }

        public CategorySummary(final String name, final int categoryCount, final int includedTags, final int excludedTags) {
            this.name = name;
            this.categoryCount = categoryCount;
            this.includedTags = includedTags;
            this.excludedTags = excludedTags;
        }
    }

    public CategorySummary getSummary() {
        int includedTags = 0;
        int excludedTags = 0;
        for (final SearchUICategory category : categories) {
            for (final SearchUITag tag : category.getMyTags()) {
                if (tag.getState() == TriStateSelectionState.SELECTED) {
                    ++includedTags;
                } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                    ++excludedTags;
                }
            }
        }

        return new CategorySummary(this.getName(), this.categories.size(), includedTags, excludedTags);
    }

    public List<SearchUICategory> getCategories() {
        return categories;
    }

    public int getChildCount() {
        int retValue = 0;
        for (final SearchUICategory category : categories) {
            retValue += category.getChildCount();
        }
        return retValue;
    }

    public SearchUIProject(final RESTProjectV1 project) {
        super(project.getName(), project.getId().toString());
    }

    public SearchUIProject(final String name) {
        super(name, "-1");
    }

    public void populateCategories(final RESTProjectV1 project, final RESTTagCollectionV1 tags) {
        if (tags == null) {
            throw new IllegalArgumentException("tags parameter cannot be null");
        }
        if (tags.getItems() == null) {
            throw new IllegalArgumentException("tags.getItems() cannot be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
        }

        for (final RESTTagV1 tag : tags.getItems()) {
            if (tag.getProjects().getItems() == null) {
                throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
            }

            if (!tag.getRemoveItem()) {
                if (tag.getProjects().getItems().contains(project)) {
                    if (tag.getCategories().getItems() == null) {
                        throw new IllegalArgumentException("tag.getCategories().getItems() cannot be null");
                    }

                    for (final RESTCategoryV1 category : tag.getCategories().getItems()) {
                        final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                        if (!categories.contains(searchUICategory)) {
                            searchUICategory.populateCategories(project, category, tags);
                            categories.add(searchUICategory);
                        }
                    }
                }
            }
        }

        Collections.sort(categories, new SearchUINameSort());
    }

    public void populateCategoriesWithoutProject(final RESTTagCollectionV1 tags) {
        if (tags == null) {
            throw new IllegalArgumentException("tags parameter cannot be null");
        }
        if (tags.getItems() == null) {
            throw new IllegalArgumentException("tags.getItems() cannot be null");
        }

        for (final RESTTagV1 tag : tags.getItems()) {
            if (tag.getProjects().getItems() == null) {
                throw new IllegalArgumentException("tag.getProjects().getItems() cannot be null");
            }

            if (!tag.getRemoveItem()) {
                if (tag.getProjects().getItems().isEmpty()) {
                    if (tag.getCategories().getItems() == null) {
                        throw new IllegalArgumentException("tag.getCategories().getItems() cannot be null");
                    }

                    for (final RESTCategoryV1 category : tag.getCategories().getItems()) {
                        final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                        if (!categories.contains(searchUICategory)) {
                            searchUICategory.populateCategoriesWithoutProject(category, tags);
                            categories.add(searchUICategory);
                        }
                    }
                }
            }
        }

        Collections.sort(categories, new SearchUINameSort());
    }
}
