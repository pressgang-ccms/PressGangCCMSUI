package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

/**
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
public interface BaseFilteredResultsComponentInterface<V extends RESTBaseCollectionItemV1<?, ?, ?>> extends PresenterInterface {

    /**
     * @return The query string that represents the current state of the filters
     */
    String getQuery();

    /**
     * @return The provider data used to populate the celltable
     */
    ProviderUpdateData<V> getProviderData();

    /**
     * @return The display that is associated with the presenter.
     */
    BaseFilteredResultsViewInterface<V> getDisplay();

    /**
     * Perform any initialisation required by filtered results presenters.
     *
     * @param topicId     The help topic that is associated with the view
     * @param pageId      The string that identifies the page
     * @param queryString The query string that is passed to the REST interface
     */
    void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString);
}