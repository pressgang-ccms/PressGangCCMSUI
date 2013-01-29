package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a single project, with child categories.
 *
 * @author Matthew Casperson
 */
public final class SearchUIProject extends SearchUIBase {

    /**
     * The categories held by this project *
     */
    private final List<SearchUICategory> categories = new ArrayList<SearchUICategory>();

    /**
     * @param project The project that this object represents
     */
    public SearchUIProject(final RESTProjectCollectionItemV1 project) {
        super(project.getItem().getName(), project.getItem().getId().toString());
    }

    /**
     * @param name The name of the project that this object represents
     */
    public SearchUIProject(final String name) {
        super(name, "-1");
    }

    /**
     * @return An object that contains the summary information to be displayed on the tile
     */
    public CategorySummary getSummary() {
        int includedTags = 0;
        int excludedTags = 0;
        for (final SearchUICategory category : this.categories) {
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

    /**
     * @return The categories held by this project
     */
    public List<SearchUICategory> getCategories() {
        return this.categories;
    }

    /**
     * @return The number of tags held by all the categories in this project
     */
    public int getChildCount() {
        int retValue = 0;
        for (final SearchUICategory category : this.categories) {
            retValue += category.getChildCount();
        }
        return retValue;
    }

    /**
     * Populate the tags into categories under this project.
     *
     * @param project The project that this object represents
     * @param tags    The tags that will be populated under this project
     */
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

                for (final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories()
                        .returnExistingAndAddedCollectionItems()) {
                    final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                    if (!this.categories.contains(searchUICategory)) {
                        searchUICategory.populateCategories(project, category, tags);
                        this.categories.add(searchUICategory);
                    }
                }

            }
        }

        Collections.sort(this.categories, new SearchUINameSort());
    }

    /**
     * Populate the tags into categories under the "Common" project.
     *
     * @param tags The tags that will be populated under this project
     */
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

                for (final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories()
                        .returnExistingAndAddedCollectionItems()) {
                    final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                    if (!this.categories.contains(searchUICategory)) {
                        searchUICategory.populateCategoriesWithoutProject(category, tags);
                        this.categories.add(searchUICategory);
                    }
                }
            }

        }

        Collections.sort(this.categories, new SearchUINameSort());
    }

    @Override
    public boolean equals(final Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * This class holds the summary information that is displayed on the category tile.
     *
     * @author Matthew Casperson
     */
    public static final class CategorySummary {
        private final String name;
        private final int categoryCount;
        private final int includedTags;
        private final int excludedTags;

        /**
         * @param name          The name of the project
         * @param categoryCount The number of categories held by the project
         * @param includedTags  The number of tags that are to be included in the search
         * @param excludedTags  The number of tags that are to be excluded from the search
         */
        public CategorySummary(final String name, final int categoryCount, final int includedTags, final int excludedTags) {
            this.name = name;
            this.categoryCount = categoryCount;
            this.includedTags = includedTags;
            this.excludedTags = excludedTags;
        }

        /**
         * @return The name of the project
         */
        public String getName() {
            return this.name;
        }

        /**
         * @return The number of categories held by the project
         */
        public int getCategoryCount() {
            return this.categoryCount;
        }

        /**
         * @return The number of tags that are to be included in the search
         */
        public int getIncludedTags() {
            return this.includedTags;
        }

        /**
         * @return The number of tags that are to be excluded from the search
         */
        public int getExcludedTags() {
            return this.excludedTags;
        }
    }
}
