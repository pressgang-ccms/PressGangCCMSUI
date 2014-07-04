package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flipthebird.gwthashcodeequals.EqualsBuilder;
import com.flipthebird.gwthashcodeequals.HashCodeBuilder;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a single project, with child categories.
 *
 * @author Matthew Casperson
 */
public final class SearchUIProject extends SearchUIBase {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchUIProject.class.getName());

    /**
     * The categories held by this project *
     */
    private final List<SearchUICategory> categories = new ArrayList<SearchUICategory>();
    private RESTFilterV1 filter = null;
    private boolean isInitialised = false;

    /**
     * @param project The project that this object represents
     */
    public SearchUIProject(@NotNull final RESTProjectCollectionItemV1 project) {
        super(project.getItem().getName(), project.getItem().getId());
    }

    /**
     * @param name The name of the project that this object represents
     */
    public SearchUIProject(final String name) {
        super(name, -1);
    }

    /**
     * @return An object that contains the summary information to be displayed on the tile
     */
    @NotNull
    public CategorySummary getSummary() {
        int includedTags = 0;
        int excludedTags = 0;
        for (@NotNull final SearchUICategory category : categories) {
            for (@NotNull final SearchUITag tag : category.getMyTags()) {
                if (tag.getState() == TriStateSelectionState.SELECTED) {
                    ++includedTags;
                } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                    ++excludedTags;
                }
            }
        }

        return new CategorySummary(getName(), categories.size(), includedTags, excludedTags);
    }

    /**
     * @return The categories held by this project
     */
    @NotNull
    public List<SearchUICategory> getCategories() {
        return categories;
    }

    /**
     * @return The number of tags held by all the categories in this project
     */
    public int getChildCount() {
        int retValue = 0;
        for (@NotNull final SearchUICategory category : categories) {
            retValue += category.getChildCount();
        }
        return retValue;
    }

    /**
     * Populate the tags into categories under this project.
     *
     * @param project The project that this object represents
     * @param tags    The tags that will be populated under this project
     * @param filter  The filter that defines the state of the tags
     */
    public void populateCategories(@NotNull final RESTProjectV1 project, @NotNull final RESTTagCollectionV1 tags,
            @Nullable final RESTFilterV1 filter) {
        for (@NotNull final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {

            checkState(tag.getItem().getProjects() != null, "tag.getItem().getProjects() cannot be null");

            final Optional<RESTProjectCollectionItemV1> matchingProject = Iterables.tryFind(tag.getItem().getProjects().getItems(),
                    new Predicate<RESTProjectCollectionItemV1>() {
                        @Override
                        public boolean apply(@Nullable final RESTProjectCollectionItemV1 arg) {
                            return arg != null && arg.getItem().getId().equals(project.getId());
                        }
                    });

            if (matchingProject.isPresent()) {

                checkState(tag.getItem().getCategories().getItems() != null, "tag.getItem().getCategories().getItems() cannot be null");

                for (@NotNull final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories()
                        .returnExistingAndAddedCollectionItems()) {
                    @NotNull final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                    if (!categories.contains(searchUICategory)) {
                        searchUICategory.populateCategories(project, category.getItem(), tags, filter);
                        categories.add(searchUICategory);
                    }
                }

            }
        }

        Collections.sort(categories, new SearchUINameSort());
        isInitialised = true;
    }

    /**
     * Populate the tags into categories under the "Common" project.
     *
     * @param tags   The tags that will be populated under this project
     * @param filter The filter that defines the state of the tags
     */
    public void populateCategoriesWithoutProject(@Nullable final RESTTagCollectionV1 tags, final RESTFilterV1 filter) {
        LOGGER.log(Level.INFO, "ENTER SearchUIProject.populateCategoriesWithoutProject()");

        if (tags == null) {
            throw new IllegalArgumentException("tags parameter cannot be null");
        }
        if (tags.getItems() == null) {
            throw new IllegalArgumentException("tags.getItems() cannot be null");
        }

        for (@NotNull final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
            if (tag.getItem().getProjects() == null) {
                throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
            }

            if (tag.getItem().getProjects().getItems().isEmpty()) {
                if (tag.getItem().getCategories().getItems() == null) {
                    throw new IllegalArgumentException("tag.getItem().getCategories().getItems() cannot be null");
                }

                for (@NotNull final RESTCategoryInTagCollectionItemV1 category : tag.getItem().getCategories()
                        .returnExistingAndAddedCollectionItems()) {
                    @NotNull final SearchUICategory searchUICategory = new SearchUICategory(this, category);
                    if (!categories.contains(searchUICategory)) {
                        searchUICategory.populateCategoriesWithoutProject(category, tags, filter);
                        categories.add(searchUICategory);
                    }
                }
            }

        }

        Collections.sort(categories, new SearchUINameSort());
        isInitialised = true;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SearchUIProject)) {
            return false;
        } else if (categories == null || categories.isEmpty()) {
            return super.equals(other);
        } else {
            return new EqualsBuilder().append(getName(), ((SearchUIProject) other).getName()).append(categories,
                    ((SearchUIProject) other).getCategories()).build();
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getName()).append(categories).hashCode();
    }

    public boolean isInitialised() {
        return isInitialised;
    }

    public RESTFilterV1 getFilter() {
        return filter;
    }

    public void setFilter(RESTFilterV1 filter) {
        this.filter = filter;
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
            return name;
        }

        /**
         * @return The number of categories held by the project
         */
        public int getCategoryCount() {
            return categoryCount;
        }

        /**
         * @return The number of tags that are to be included in the search
         */
        public int getIncludedTags() {
            return includedTags;
        }

        /**
         * @return The number of tags that are to be excluded from the search
         */
        public int getExcludedTags() {
            return excludedTags;
        }
    }
}
