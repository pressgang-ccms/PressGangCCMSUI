package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jetbrains.annotations.NotNull;

/**
 * The base class for all components adding logic to search and edit views. This view has a split screen with a filtered results
 * list on the left, and the entity details on the right, with at least one of those views being a "properties view", which
 * shows the fields associated with an entity (especially the ID).
 *
 * @author Matthew Casperson
 */
public interface BaseSearchAndEditPresenterInterface {

    /**
     * Must call bindSearchAndEdit().
     *
     * @param queryString The query string used to load the filtered results list
     */
    void bindSearchAndEditExtended(@NotNull final String queryString);
}
