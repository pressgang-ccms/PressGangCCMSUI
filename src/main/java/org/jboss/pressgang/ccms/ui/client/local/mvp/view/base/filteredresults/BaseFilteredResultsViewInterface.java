package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.PushButton;

/**
 * This interface defines the base for all views displaying a filtered results set
 * 
 * @author Matthew Casperson
 * 
 */
public interface BaseFilteredResultsViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends BaseTemplateViewInterface {
    /**
     * @return The button that initiates a new search
     */
    PushButton getCreate();

    /**
     * @return The button that creates a new entity
     */
    @Override
    PushButton getSearch();

    /** @return The celltable that displays the results */
    CellTable<V> getResults();

    /** @return The pager used to move over the results */
    SimplePager getPager();

    /** @return The provider used to populate the celltable */
    EnhancedAsyncDataProvider<V> getProvider();

    /**
     * @param provider The provider used to populate the celltable
     */
    void setProvider(final EnhancedAsyncDataProvider<V> provider);
}
