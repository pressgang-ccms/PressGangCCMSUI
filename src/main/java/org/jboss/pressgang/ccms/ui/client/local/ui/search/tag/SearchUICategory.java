package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;
import org.jboss.pressgang.ccms.utils.constants.CommonFilterConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a single category assigned to a project, with child tags.
 *
 * @author Matthew Casperson
 */
public final class SearchUICategory extends SearchUIBase {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchUICategory.class.getName());

    /**
     * The tags held by this category
     */
    private final List<SearchUITag> myTags = new ArrayList<SearchUITag>();

    private boolean internalLogicAnd = Constants.DEFAULT_INTERNAL_AND_LOGIC;
    private boolean internalLogicOr = !Constants.DEFAULT_INTERNAL_AND_LOGIC;
    private boolean externalLogicAnd = Constants.DEFAULT_EXTERNAL_AND_LOGIC;
    private boolean externalLogicOr = !Constants.DEFAULT_EXTERNAL_AND_LOGIC;

    /**
     * @param project  The project that this object represents
     * @param category The category that this object represents
     */
    public SearchUICategory(final SearchUIProject project, final RESTCategoryInTagCollectionItemV1 category) {
        super(category.getItem().getName(), category.getItem().getId());
    }

    /**
     * @return An object that contains the summary information displayed on the Category tile in the search screen.
     */
    public TagSummary getSummary() {
        int includedTags = 0;
        int excludedTags = 0;
        for (final SearchUITag tag : this.myTags) {
            if (tag.getState() == TriStateSelectionState.SELECTED) {
                ++includedTags;
            } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                ++excludedTags;
            }
        }

        return new TagSummary(this.getName(), this.myTags.size(), includedTags, excludedTags);
    }

    /**
     * @return The tags held by this category
     */
    public List<SearchUITag> getMyTags() {
        return this.myTags;
    }

    public boolean isInternalLogicAnd() {
        return internalLogicAnd;
    }

    public void setInternalLogicAnd(final boolean internalLogicAnd) {
        this.internalLogicAnd = internalLogicAnd;
    }

    public boolean isInternalLogicOr() {
        return internalLogicOr;
    }

    public void setInternalLogicOr(final boolean internalLogicOr) {
        this.internalLogicOr = internalLogicOr;
    }

    public boolean isExternalLogicAnd() {
        return externalLogicAnd;
    }

    public void setExternalLogicAnd(final boolean externalLogicAnd) {
        this.externalLogicAnd = externalLogicAnd;
    }

    public boolean isExternalLogicOr() {
        return externalLogicOr;
    }

    public void setExternalLogicOr(final boolean externalLogicOr) {
        this.externalLogicOr = externalLogicOr;
    }

    /**
     * @return The number of tags held by this category
     */
    public int getChildCount() {
        return this.myTags.size();
    }

    /**
     * Populate the tags under this category.
     *
     * @param project  The project this category belongs to
     * @param category The category that this object represents
     * @param tags     The tags collection from which tags will be selected for this category
     * @param filter   The filter that defines the state of the tags
     */
    public void populateCategories(@NotNull final RESTProjectCollectionItemV1 project, @NotNull final RESTCategoryInTagCollectionItemV1 category,
                                   @NotNull final RESTTagCollectionV1 tags, @Nullable final RESTFilterV1 filter) {
        try {
            //LOGGER.log(Level.INFO, "ENTER SearchUICategory.populateCategories()");

            if (filter != null) {
                for (final RESTFilterCategoryCollectionItemV1 filterCategory : filter.getFilterCategories_OTM().getItems()) {
                    if (filterCategory.getItem().getCategory().getId().equals(category.getItem().getId())) {
                        if (filterCategory.getItem().getState().equals(CommonFilterConstants.CATEGORY_INTERNAL_AND_STATE)) {
                            internalLogicAnd = true;
                            internalLogicOr = !internalLogicAnd;
                        } else if (filterCategory.getItem().getState().equals(CommonFilterConstants.CATEGORY_INTERNAL_OR_STATE)) {
                            internalLogicAnd = false;
                            internalLogicOr = !internalLogicAnd;
                        } else if (filterCategory.getItem().getState().equals(CommonFilterConstants.CATEGORY_EXTERNAL_AND_STATE)) {
                            externalLogicAnd = true;
                            externalLogicOr = !externalLogicAnd;
                        } else if (filterCategory.getItem().getState().equals(CommonFilterConstants.CATEGORY_EXTERNAL_OR_STATE)) {
                            externalLogicAnd = false;
                            externalLogicOr = !externalLogicAnd;
                        }
                    }
                }
            }

            for (final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
                if (tag.getItem().getProjects() == null) {
                    throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
                }
                if (tag.getItem().getCategories() == null) {
                    throw new IllegalArgumentException("tag.getItem().getCategories() cannot be null");
                }

                final Optional<RESTCategoryInTagCollectionItemV1> matchingCategory = Iterables.tryFind(tag.getItem()
                        .getCategories().getItems(), new Predicate<RESTCategoryInTagCollectionItemV1>() {
                    @Override
                    public boolean apply(final RESTCategoryInTagCollectionItemV1 arg) {
                        return arg.getItem().getId().equals(category.getItem().getId());
                    }
                });

                final Optional<RESTProjectCollectionItemV1> matchingProject = Iterables.tryFind(tag.getItem().getProjects()
                        .getItems(), new Predicate<RESTProjectCollectionItemV1>() {
                    @Override
                    public boolean apply(final RESTProjectCollectionItemV1 arg) {
                        return arg.getItem().getId().equals(project.getItem().getId());
                    }
                });

                if (matchingCategory.isPresent() && matchingProject.isPresent()) {
                    final SearchUITag searchUITag = new SearchUITag(this, tag, filter);
                    if (!this.myTags.contains(searchUITag)) {
                        this.myTags.add(searchUITag);
                    }
                }
            }

            Collections.sort(this.myTags, new SearchUINameSort());
        } finally {
            //LOGGER.log(Level.INFO, "EXIT SearchUICategory.populateCategories()");
        }
    }

    /**
     * Populate the tags under this category under the "Common" project.
     *
     * @param category The category that this object represents
     * @param tags     The tags collection from which tags will be selected for this category
     * @param filter   The filter that defines the state of the tags
     */
    public void populateCategoriesWithoutProject(final RESTCategoryInTagCollectionItemV1 category,
                                                 final RESTTagCollectionV1 tags, final RESTFilterV1 filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchUICategory.populateCategoriesWithoutProject()");


            if (tags == null) {
                throw new IllegalArgumentException("tags parameter cannot be null");
            }
            if (category == null) {
                throw new IllegalArgumentException("category cannot be null");
            }

            for (final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
                if (tag.getItem().getProjects() == null) {
                    throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
                }
                if (tag.getItem().getCategories() == null) {
                    throw new IllegalArgumentException("tag.getItem().getCategories() cannot be null");
                }

                if (tag.getItem().getProjects().getItems().isEmpty()) {

                    final Optional<RESTCategoryInTagCollectionItemV1> matchingCategory = Iterables.tryFind(tag.getItem()
                            .getCategories().getItems(), new Predicate<RESTCategoryInTagCollectionItemV1>() {
                        @Override
                        public boolean apply(final RESTCategoryInTagCollectionItemV1 arg) {
                            return arg.getItem().getId().equals(category.getItem().getId());
                        }
                    });

                    if (matchingCategory.isPresent()) {
                        final SearchUITag searchUITag = new SearchUITag(this, tag, filter);
                        if (!this.myTags.contains(searchUITag)) {
                            this.myTags.add(searchUITag);
                        }
                    }

                }
            }

            Collections.sort(this.myTags, new SearchUINameSort());
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchUICategory.populateCategoriesWithoutProject()");
        }
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
     * This class contains the summary information to be displayed on a Category tile in the search screen.
     *
     * @author Matthew Casperson
     */
    public static final class TagSummary {
        private final String name;
        private final int tagCount;
        private final int includedTags;
        private final int excludedTags;

        /**
         * @param name         The name of the Category
         * @param tagCount     The number of Tags held by the Category
         * @param includedTags The number of tags that are to be included in the search
         * @param excludedTags The number of tags that are to be excluded from the search
         */
        public TagSummary(final String name, final int tagCount, final int includedTags, final int excludedTags) {
            this.name = name;
            this.tagCount = tagCount;
            this.includedTags = includedTags;
            this.excludedTags = excludedTags;
        }

        /**
         * @return The name of the Category
         */
        public String getName() {
            return this.name;
        }

        /**
         * @return The number of tiles under this category
         */
        public int getTagCount() {
            return this.tagCount;
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
