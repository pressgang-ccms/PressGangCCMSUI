/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The REST interface does not define a hierarchy or projects->categories->tags. Instead, tags belong to both categories and
 * projects, but the projects and categories don't have any direct relationship.
 * <p/>
 * When being viewed however tags are displayed in the projects->categories->tags hierarchy. This class defines the top level
 * collection of projects.
 *
 * @author Matthew Casperson
 */
public class SearchUIProjects implements SearchViewBase {
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchUIProjects.class.getName());


    private final LinkedList<SearchUIProject> projects = new LinkedList<SearchUIProject>();


    /**
     * Default constructor. Does nothing.
     */
    public SearchUIProjects() {

    }

    /**
     * @param tags The collection of tags that is used to build the hierarchy of projects, categories and tags
     */
    public SearchUIProjects(@NotNull final RESTTagCollectionV1 tags) {
        initializeFromTags(tags, null);
    }

    /**
     * @param tags   The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter The filter that defines the state of the tags
     */
    public SearchUIProjects(@NotNull final RESTTagCollectionV1 tags, final RESTFilterV1 filter) {
        initializeFromTags(tags, filter);
    }

    /**
     * @param tags The collection of tags that is used to build the hierarchy of projects, categories and tags
     */
    public SearchUIProjects(@NotNull final RESTProjectCollectionV1 projects) {
        initializeFromProjects(projects, null);
    }

    /**
     * @param tags   The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter The filter that defines the state of the tags
     */
    public SearchUIProjects(@NotNull final RESTProjectCollectionV1 projects, final RESTFilterV1 filter) {
        initializeFromProjects(projects, filter);
    }


    /**
     * @return The list of projects
     */
    @NotNull
    public List<SearchUIProject> getProjects() {
        return projects;
    }

    /**
     * @param tags   The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter The filter that defines the state of the tags
     */
    public void initializeFromTags(@NotNull final RESTTagCollectionV1 tags, @Nullable final RESTFilterV1 filter) {
        projects.clear();

        for (@NotNull final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
            checkState(tag.getItem().getProjects() != null, "tag.getItem().getProjects() cannot be null");

            /* Tags to be removed should not show up */
            for (@NotNull final RESTProjectCollectionItemV1 project : tag.getItem().getProjects().returnExistingCollectionItems()) {
                @NotNull final SearchUIProject searchUIProject = new SearchUIProject(project);
                if (!projects.contains(searchUIProject)) {
                    searchUIProject.setFilter(filter);
                    searchUIProject.populateCategories(project.getItem(), tags, filter);
                    projects.add(searchUIProject);
                }
            }
        }

        initialiseInternal(tags, filter);
    }

    /**
     * @param projects The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter   The filter that defines the state of the tags
     */
    public void initializeFromProjects(@NotNull final RESTProjectCollectionV1 projects, @Nullable final RESTFilterV1 filter) {
        this.projects.clear();

        /* Tags to be removed should not show up */
        for (@NotNull final RESTProjectCollectionItemV1 project : projects.returnExistingCollectionItems()) {
            @NotNull final SearchUIProject searchUIProject = new SearchUIProject(project);
            if (!this.projects.contains(searchUIProject)) {
                searchUIProject.setFilter(filter);
                this.projects.add(searchUIProject);
            }
        }

        initialiseInternal(null, filter);
    }

    protected void initialiseInternal(@NotNull final RESTTagCollectionV1 tags, @Nullable final RESTFilterV1 filter) {
        Collections.sort(projects, new SearchUINameSort());

        /*
         * Add the common project to the start of the list. Do this after all the projects have been added, so it won't get
         * confused with a project that might be called common.
         */
        @NotNull final SearchUIProject common = new SearchUIProject(PressGangCCMSUI.INSTANCE.Common());
        if (tags == null) {
            projects.addFirst(common);
        } else if (tags.getItems() != null) {
            common.setFilter(filter);
            common.populateCategoriesWithoutProject(tags, filter);
            projects.addFirst(common);
        }
    }

    @NotNull
    private RESTFilterCategoryV1 generateRESTFilterCategory(@NotNull final SearchUIProject project, @NotNull final SearchUICategory category, @NotNull final RESTFilterV1 filter) {
        @NotNull final RESTCategoryV1 restCategory = new RESTCategoryV1();
        restCategory.setId(category.getId());

        @NotNull final RESTFilterCategoryV1 restFilterCategory = new RESTFilterCategoryV1();
        restFilterCategory.explicitSetCategory(restCategory);

        if (project.getId() != -1) {
            @NotNull final RESTProjectV1 restProject = new RESTProjectV1();
            restProject.setId(project.getId());
            restFilterCategory.explicitSetProject(restProject);
        }

        filter.getFilterCategories_OTM().addNewItem(restFilterCategory);

        return restFilterCategory;
    }

    /**
     * Add the tag and category states to the supplied filter. This is usually so a filter object can be
     * saved.
     *
     * @param filter The filter to be synced with the tag and category states.
     */
    public void populateFilter(@NotNull final RESTFilterV1 filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchUIProjects.populateFilter()");

            checkArgument(filter.getFilterTags_OTM() != null, "The filter must have a collection of tags");
            checkArgument(filter.getFilterFields_OTM() != null, "The filter must have a collection of fields");
            checkArgument(filter.getFilterCategories_OTM() != null, "The filter must have a collection of categories");

            // because a tag can be listed under multiple categories with different values,
            // we keep a track of the tags we have processed here
            @NotNull final List<Integer> processedIds = new ArrayList<Integer>();

            for (@NotNull final SearchUIProject project : projects) {
                for (@NotNull final SearchUICategory category : project.getCategories()) {

                    /*
                        Add the parameters for the category logic.
                     */
                    if (category.isInternalLogicAnd() && category.isInternalLogicAnd() != Constants.DEFAULT_INTERNAL_AND_LOGIC) {
                        /*
                            If the internal "and" logic is specified, and the internal "and" logic is not the default value (i.e. Constants.DEFAULT_INTERNAL_AND_LOGIC is false),
                            then add a query parameter.
                         */
                        @NotNull final RESTFilterCategoryV1 restFilterCategory = generateRESTFilterCategory(project, category, filter);
                        restFilterCategory.explicitSetState(CommonFilterConstants.CATEGORY_INTERNAL_AND_STATE);

                    } else if (category.isInternalLogicOr() && category.isInternalLogicOr() == Constants.DEFAULT_INTERNAL_AND_LOGIC) {
                        /*
                            If the internal "or" logic is specified, and the internal "or" logic is not the default value (i.e. Constants.DEFAULT_INTERNAL_AND_LOGIC is true),
                            then add a query parameter.
                         */
                        @NotNull final RESTFilterCategoryV1 restFilterCategory = generateRESTFilterCategory(project, category, filter);
                        restFilterCategory.explicitSetState(CommonFilterConstants.CATEGORY_INTERNAL_OR_STATE);
                    }

                    if (category.isExternalLogicAnd() && category.isExternalLogicAnd() != Constants.DEFAULT_EXTERNAL_AND_LOGIC) {
                        @NotNull final RESTFilterCategoryV1 restFilterCategory = generateRESTFilterCategory(project, category, filter);
                        restFilterCategory.explicitSetState(CommonFilterConstants.CATEGORY_EXTERNAL_AND_STATE);
                    } else if (category.isExternalLogicOr() && category.isExternalLogicOr() == Constants.DEFAULT_EXTERNAL_AND_LOGIC) {
                        @NotNull final RESTFilterCategoryV1 restFilterCategory = generateRESTFilterCategory(project, category, filter);
                        restFilterCategory.explicitSetState(CommonFilterConstants.CATEGORY_EXTERNAL_OR_STATE);
                    }

                    for (@NotNull final SearchUITag tag : category.getMyTags()) {
                        if (!processedIds.contains(tag.getTag().getItem().getId())) {
                            if (tag.getState() != TriStateSelectionState.NONE) {
                                @NotNull final RESTTagV1 filterTagReference = new RESTTagV1();
                                filterTagReference.setId(tag.getTag().getItem().getId());

                                @NotNull final RESTFilterTagV1 filterTag = new RESTFilterTagV1();
                                filterTag.explicitSetTag(filterTagReference);
                                filterTag.explicitSetState(tag.getState() == TriStateSelectionState.SELECTED ? CommonFilterConstants.MATCH_TAG_STATE : CommonFilterConstants.NOT_MATCH_TAG_STATE);
                                filter.getFilterTags_OTM().addNewItem(filterTag);
                                processedIds.add(tag.getTag().getItem().getId());
                            }
                        }
                    }
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchUIProjects.populateFilter()");
        }
    }

    @NotNull
    @Override
    public String getSearchQuery(final boolean includeQueryPrefix) {

        final StringBuilder builder = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        for (@NotNull final SearchUIProject project : projects) {
            for (@NotNull final SearchUICategory category : project.getCategories()) {

                /*
                    Add the parameters for the category logic.
                 */
                if (category.isInternalLogicAnd() && category.isInternalLogicAnd() != Constants.DEFAULT_INTERNAL_AND_LOGIC) {
                    /*
                        If the internal "and" logic is specified, and the internal "and" logic is not the default value (i.e. Constants.DEFAULT_INTERNAL_AND_LOGIC is false),
                        then add a query parameter.
                     */
                    builder.append(";").append(CommonFilterConstants.CATEORY_INTERNAL_LOGIC).append(category.getId());
                    // If the project isn't the common project then add the project id.
                    if (project.getId() > 0) {
                        builder.append("-").append(project.getId());
                    }
                    builder.append("=").append(Constants.AND_LOGIC_QUERY_STRING_VALUE);
                } else if (category.isInternalLogicOr() && category.isInternalLogicOr() == Constants.DEFAULT_INTERNAL_AND_LOGIC) {
                    /*
                        If the internal "or" logic is specified, and the internal "or" logic is not the default value (i.e. Constants.DEFAULT_INTERNAL_AND_LOGIC is true),
                        then add a query parameter.
                     */
                    builder.append(";").append(CommonFilterConstants.CATEORY_INTERNAL_LOGIC).append(category.getId());
                    // If the project isn't the common project then add the project id.
                    if (project.getId() > 0) {
                        builder.append("-").append(project.getId());
                    }
                    builder.append("=").append(Constants.OR_LOGIC_QUERY_STRING_VALUE);
                }

                if (category.isExternalLogicAnd() && category.isExternalLogicAnd() != Constants.DEFAULT_EXTERNAL_AND_LOGIC) {
                    builder.append(";").append(CommonFilterConstants.CATEORY_EXTERNAL_LOGIC).append(category.getId()).append("=").append(Constants.AND_LOGIC_QUERY_STRING_VALUE);
                } else if (category.isExternalLogicOr() && category.isExternalLogicOr() == Constants.DEFAULT_EXTERNAL_AND_LOGIC) {
                    builder.append(";").append(CommonFilterConstants.CATEORY_EXTERNAL_LOGIC).append(category.getId()).append("=").append(Constants.OR_LOGIC_QUERY_STRING_VALUE);
                }

                for (@NotNull final SearchUITag tag : category.getMyTags()) {
                    if (tag.getState() != TriStateSelectionState.NONE) {
                        builder.append(";");

                        if (tag.getState() == TriStateSelectionState.SELECTED) {
                            builder.append(CommonFilterConstants.MATCH_TAG).append(tag.getTag().getItem().getId()).append("=").append(CommonFilterConstants.MATCH_TAG_STATE);
                        } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                            builder.append(CommonFilterConstants.MATCH_TAG).append(tag.getTag().getItem().getId()).append("=").append(CommonFilterConstants.NOT_MATCH_TAG_STATE);
                        }
                    }
                }
            }
        }

        return builder.toString();
    }
}
