package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 */
public interface BaseFilteredResultsComponentInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends PresenterInterface {

    /**
     * @return The query string that represents the current state of the filters
     */
    String getQuery();

    /**
     * @return The provider data used to populate the celltable
     */
    ProviderUpdateData<V> getProviderData();

    /**
     * @param providerData The provider data used to populate the celltable
     */
    void setTagProviderData(final ProviderUpdateData<V> providerData);

    /**
     * Perform any initialisation required by filtered results presenters.
     *
     * @param topicId The help topic that is associated with the view
     * @param pageId  The string that identifies the page
     * @param queryString The query string that is passed to the REST interface
     */
    void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString);
}