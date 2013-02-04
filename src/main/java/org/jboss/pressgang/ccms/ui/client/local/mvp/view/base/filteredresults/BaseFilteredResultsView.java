package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This class serves as the base for all views displaying a filtered results set.
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
abstract public class BaseFilteredResultsView<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends BaseTemplateView implements BaseFilteredResultsViewInterface<T, U, V> {

    /**
     * The button that initiates a new search
     */
    private final PushButton entitySearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    /**
     * The button that creates a new entity
     */
    private final PushButton create = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Create());
    /**
     * The pager used to move over the results
     */
    private final SimplePager pager = UIUtilities.createSimplePager();
    /**
     * The celltable that displays the results
     */
    private final CellTable<V> results = UIUtilities.<V>createCellTable();
    /**
     * The panel that holds the filter fields and the search results
     */
    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    /**
     * The table that holds the filter fields
     */
    private final FlexTable filterTable = new FlexTable();
    /**
     * The provider used to populate the celltable
     */
    private EnhancedAsyncDataProvider<V> provider;

    public BaseFilteredResultsView(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        searchResultsPanel.addStyleName(CSSConstants.FilteredResultsView.FILTERED_RESULTS_PANEL);
        filterTable.addStyleName(CSSConstants.FilteredResultsView.FILTERED_OPTIONS_PANEL);

        this.addActionButton(entitySearch);
        this.addActionButton(create);

        searchResultsPanel.add(filterTable);
        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    /**
     * @return The provider used to populate the celltable
     */
    @Override
    public final EnhancedAsyncDataProvider<V> getProvider() {
        return provider;
    }

    /**
     * @param provider The provider used to populate the celltable
     */
    @Override
    public final void setProvider(final EnhancedAsyncDataProvider<V> provider) {
        if (this.provider != null) {
            this.provider.removeDataDisplay(getResults());
        }

        this.provider = provider;
        provider.addDataDisplay(getResults());
    }

    /**
     * @return The panel that holds the filter fields and the search results
     */
    public final FlexTable getFilterTable() {
        return filterTable;
    }

    /**
     * @return The table that holds the filter fields
     */
    public final VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    /**
     * @return The celltable that displays the results
     */
    @Override
    public final CellTable<V> getResults() {
        return results;
    }

    /**
     * @return The pager used to move over the results
     */
    @Override
    public final SimplePager getPager() {
        return pager;
    }

    /**
     * @return The button that initiates a new search
     */
    @Override
    public final PushButton getCreate() {
        return create;
    }

    /**
     * @return The button that creates a new entity
     */
    @Override
    public final PushButton getEntitySearch() {
        return entitySearch;
    }

    /**
     * Adds a filter field above the search results
     *
     * @param label The lable to apply to the field
     * @param field The field itself
     */
    protected final void addFilterField(final String label, final Widget field) {
        final int rowCount = filterTable.getRowCount();
        filterTable.setWidget(rowCount, 0, new Label(label));
        filterTable.setWidget(rowCount, 1, field);
    }
}
