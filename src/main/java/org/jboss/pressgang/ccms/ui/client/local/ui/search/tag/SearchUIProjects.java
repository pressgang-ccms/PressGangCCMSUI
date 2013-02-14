package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.sort.SearchUINameSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The REST interface does not define a hierarchy or projects->categories->tags. Instead, tags belong to both categories and
 * projects, but the projects and categories don't have any direct relationship.
 * 
 * When being viewed however tags are displayed in the projects->categories->tags hierarchy. This class defines the top level
 * collection of projects.
 * 
 * @author Matthew Casperson
 */
public class SearchUIProjects implements SearchViewBase {
    /** The string that appears in the query to indicate the presence or absence of a tag */
    private static final String TAG_PREFIX = "tag";

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
     * 
     * @param tags The collection of tags that is used to build the hierarchy of projects, categories and tags
     */
    public SearchUIProjects(final RESTTagCollectionV1 tags) {
        initialize(tags, null);
    }

    /**
     *
     * @param tags The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter The filter that defines the state of the tags
     */
    public SearchUIProjects(final RESTTagCollectionV1 tags, final RESTFilterV1 filter) {
        initialize(tags, filter);
    }


    
    /**
     * 
     * @return The list of projects
     */
    public final List<SearchUIProject> getProjects() {
        return this.projects;
    }

    /**
     * 
     * @param tags The collection of tags that is used to build the hierarchy of projects, categories and tags
     * @param filter The filter that defines the state of the tags
     */
    public final void initialize(@NotNull final RESTTagCollectionV1 tags, @Nullable final RESTFilterV1 filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER SearchUIProjects.initialize()");

            if (tags == null) {
                throw new IllegalArgumentException("tags parameter cannot be null");
            }

            this.projects.clear();

            for (final RESTTagCollectionItemV1 tag : tags.returnExistingAndAddedCollectionItems()) {
                if (tag.getItem().getProjects() == null) {
                    throw new IllegalArgumentException("tag.getItem().getProjects() cannot be null");
                }

                /* Tags to be removed should not show up */
                for (final RESTProjectCollectionItemV1 project : tag.getItem().getProjects().returnExistingCollectionItems()) {
                    final SearchUIProject searchUIProject = new SearchUIProject(project);
                    if (!this.projects.contains(searchUIProject)) {
                        searchUIProject.populateCategories(project, tags, filter);
                        this.projects.add(searchUIProject);
                    }
                }
            }

            Collections.sort(this.projects, new SearchUINameSort());

            /*
             * Add the common project to the start of the list. Do this after all the projects have been added, so it won't get
             * confused with a project that might be called common.
             */
            final SearchUIProject common = new SearchUIProject(PressGangCCMSUI.INSTANCE.Common());
            common.populateCategoriesWithoutProject(tags, filter);
            if (common.getChildCount() != 0) {
                this.projects.addFirst(common);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT SearchUIProjects.initialize()");
        }
    }

    @Override
    public final String getSearchQuery(final boolean includeQueryPrefix) {

        final StringBuilder builder = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON
                : "");
        
        for (final SearchUIProject project : this.projects) {
            for (final SearchUICategory category : project.getCategories()) {
                for (final SearchUITag tag : category.getMyTags()) {
                    if (tag.getState() != TriStateSelectionState.NONE) {
                        builder.append(";");

                        if (tag.getState() == TriStateSelectionState.SELECTED) {
                            builder.append(TAG_PREFIX + tag.getTag().getItem().getId() + "=" + Constants.TAG_INCLUDED);
                        } else if (tag.getState() == TriStateSelectionState.UNSELECTED) {
                            builder.append(TAG_PREFIX + tag.getTag().getItem().getId() + "=" + Constants.TAG_EXCLUDED);
                        }
                    }
                }
            }
        }

        return builder.toString();
    }
}
