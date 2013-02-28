package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines the base for all views displaying a filtered results set
 *
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
public interface BaseFilteredResultsViewInterface<V extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseTemplateViewInterface {
    /**
     * @return The button that initiates a new search
     */
    PushButton getCreate();

    /**
     * @return The button that creates a new entity
     */
    PushButton getEntitySearch();

    /**
     * @return The celltable that displays the results
     */
    CellTable<V> getResults();

    /**
     * @return The pager used to move over the results
     */
    SimplePager getPager();

    /**
     * @return The provider used to populate the celltable
     */
    EnhancedAsyncDataProvider<V> getProvider();
    /**
     * @return The panel that holds the buttons used as tabs.
     */
    @NotNull
    FlexTable getTabPanel();
    /**
     * @param provider The provider used to populate the celltable
     */
    void setProvider(final EnhancedAsyncDataProvider<V> provider);
}
