package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
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
public interface BaseFilteredResultsViewInterface<T extends RESTBaseCollectionItemV1> extends BaseTemplateViewInterface {
    /**
     * @return The button that initiates a new search
     */
    PushButton getCreate();

    /**
     * @return The button that creates a new entity
     */
    PushButton getSearch();
    
    /** @return The celltable that displays the results */
    CellTable<T> getResults();

    /** @return The pager used to move over the results */
    SimplePager getPager();
    
    /** @return The provider used to populate the celltable */
    EnhancedAsyncDataProvider<T> getProvider();

    /**
     * @param provider The provider used to populate the celltable
     */
    void setProvider(final EnhancedAsyncDataProvider<T> provider);
}
