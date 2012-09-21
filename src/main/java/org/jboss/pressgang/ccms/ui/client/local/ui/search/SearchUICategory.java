package org.jboss.pressgang.ccms.ui.client.local.ui.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryTagV1;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single category assigned to a project, with child tags.
 * 
 * @author Matthew Casperson
 */
public class SearchUICategory extends SearchUIBase {
    private final List<SearchUITag> myTags = new ArrayList<SearchUITag>();

    public class TagSummary {
        private final String name;
        private final int tagCount;
        private final int includedTags;
        private final int excludedTags;

        public String getName() {
            return name;
        }

        public int getTagCount() {
            return tagCount;
        }

        public int getIncludedTags() {
            return includedTags;
        }

        public int getExcludedTags() {
            return excludedTags;
        }

        public TagSummary(final String name, final int tagCount, final int includedTags, final int excludedTags) {
            this.name = name;
            this.tagCount = tagCount;
            this.includedTags = includedTags;
            this.excludedTags = excludedTags;
        }
    }

    public TagSummary getSummary() {
        int includedTags = 0;
        int excludedTags = 0;
        for (final SearchUITag tag : myTags) {
            if (tag.getState() == TriStateSelectionState.SELECTED) {
                ++includedTags;
            } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                ++excludedTags;
            }
        }

        return new TagSummary(this.getName(), this.myTags.size(), includedTags, excludedTags);
    }

    public List<SearchUITag> getMyTags() {
        return myTags;
    }

    public int getChildCount() {
        return myTags.size();
    }

    public SearchUICategory(final SearchUIProject project, final RESTCategoryTagCollectionItemV1 category) {
        super(category.getItem().getName(), project.getId() + "-" + category.getItem().getId());
    }

    public void populateCategories(final RESTProjectCollectionItemV1 project, final RESTCategoryTagCollectionItemV1 category,
            final RESTTagCollectionV1 tags) {
        if (tags == null) {
            throw new IllegalArgumentException("tags parameter cannot be null");
        }
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
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
            
            final Optional<RESTCategoryTagCollectionItemV1> matchingCategory = Iterables.tryFind( tag.getItem().getCategories().getItems(), new Predicate<RESTCategoryTagCollectionItemV1>() {
                public boolean apply(final RESTCategoryTagCollectionItemV1 arg) { return arg.getItem().getId().equals(category.getItem().getId()); }
            });
            
            final Optional<RESTProjectCollectionItemV1> matchingProject = Iterables.tryFind( tag.getItem().getProjects().getItems(), new Predicate<RESTProjectCollectionItemV1>() {
                public boolean apply(final RESTProjectCollectionItemV1 arg) { return arg.getItem().getId().equals(project.getItem().getId()); }
            });
                            
            if (matchingCategory.isPresent() && matchingProject.isPresent()) {
                final SearchUITag searchUITag = new SearchUITag(this, tag);
                if (!myTags.contains(searchUITag)) {
                    myTags.add(searchUITag);
                }
            }
        }

        Collections.sort(myTags, new SearchUINameSort());
    }

    public void populateCategoriesWithoutProject(final RESTCategoryTagCollectionItemV1 category, final RESTTagCollectionV1 tags) {
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

                final Optional<RESTCategoryTagCollectionItemV1> matchingCategory = Iterables.tryFind( tag.getItem().getCategories().getItems(), new Predicate<RESTCategoryTagCollectionItemV1>() {
                    public boolean apply(final RESTCategoryTagCollectionItemV1 arg) { return arg.getItem().getId().equals(category.getItem().getId()); }
                });
                                
                if (matchingCategory.isPresent()) {
                    final SearchUITag searchUITag = new SearchUITag(this, tag);
                    if (!myTags.contains(searchUITag)) {
                        myTags.add(searchUITag);
                    }
                }
                            
            }
        }

        Collections.sort(myTags, new SearchUINameSort());
    }
}
