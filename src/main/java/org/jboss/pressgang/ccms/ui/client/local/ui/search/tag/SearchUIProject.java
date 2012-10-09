package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;

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

    public SearchUIProject(final RESTProjectCollectionItemV1 project) {
        super(project.getItem().getName(), project.getItem().getId().toString());
    }

    public SearchUIProject(final String name) {
        super(name, "-1");
    }

    public void populateCategories(final RESTProjectCollectionItemV1 project, final RESTTagCollectionV1 tags) {
        if (tags == null) {
            throw new IllegalArgumentException("tags parameter cannot be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
        }

        for (final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
            if (tag.getItem().getProjects() == null) {
                throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
            }


                if (tag.getItem().getProjects().getItems().contains(project)) {
                    if (tag.getItem().getCategories().getItems() == null) {
                        throw new IllegalArgumentException("tag.getItem().getCategories().getItems() cannot be null");
                    }

                    for (final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories().returnExistingAndAddedCollectionItems()) {
                        final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                        if (!categories.contains(searchUICategory)) {
                            searchUICategory.populateCategories(project, category, tags);
                            categories.add(searchUICategory);
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

        for (final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
            if (tag.getItem().getProjects() == null) {
                throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
            }


                if (tag.getItem().getProjects().getItems().isEmpty()) {
                    if (tag.getItem().getCategories().getItems() == null) {
                        throw new IllegalArgumentException("tag.getItem().getCategories().getItems() cannot be null");
                    }

                    for (final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories().returnExistingAndAddedCollectionItems()) {
                        final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                        if (!categories.contains(searchUICategory)) {
                            searchUICategory.populateCategoriesWithoutProject(category, tags);
                            categories.add(searchUICategory);
                        }
                    }
                }
            
        }

        Collections.sort(categories, new SearchUINameSort());
    }
}
