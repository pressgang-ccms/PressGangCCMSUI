package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

/**
 * The interface that defines the search and edit presenter functionality.
 */
public interface BaseSearchAndEditPresenterInterface {

    /**
     * Must call bindSearchAndEdit().
     *
     * @param topicId     The help topic id
     * @param pageId      The page identifier
     * @param queryString The query string used to load the filtered results list
     */
    void bindSearchAndEditExtended(int topicId, String pageId, final String queryString);
}
