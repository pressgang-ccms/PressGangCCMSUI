package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This class serves as the base for all views displaying a filtered results set.
 *
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
abstract public class BaseFilteredResultsView<V extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseTemplateView implements BaseFilteredResultsViewInterface<V> {

    /**
     * The button that initiates a new search
     */
    private final PushButton entitySearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    /**
     * The button that creates a new entity
     */
    private final PushButton create;
    /**
     * The pager used to move over the results
     */
    private final SimplePager pager = UIUtilities.createSimplePager();
    /**
     * The cell table that displays the results
     */
    private final CellTable<V> results = UIUtilities.<V>createCellTable();
    /**
     * The panel that holds the filter fields and the search results
     */
    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final FlexTable tabPanel = new FlexTable();
    /**
     * The table that holds the filter fields
     */
    private final FlexTable filterTable = new FlexTable();
    /**
     * The provider used to populate the cell table
     */
    private EnhancedAsyncDataProvider<V> provider;

    public BaseFilteredResultsView(@NotNull final String applicationName, @NotNull final String pageName, final String createLabel) {
        super(applicationName, pageName);

        create = UIUtilities.createPushButton(createLabel);

        // Configure the selector to display the selected result
        final SingleSelectionModel<V> selectionModel = new SingleSelectionModel<V>();
        results.setSelectionModel(selectionModel);
        results.setRowStyles(new RowStyles<V>() {
            @Override
            public String getStyleNames(V row, int rowIndex) {
                if (selectionModel.isSelected(row)) {
                    return CSSConstants.FilteredResultsView.FILTERED_RESULTS_TABLE_SELECTED_ROW;
                } else {
                    return null;
                }
            }
        });

        searchResultsPanel.addStyleName(CSSConstants.FilteredResultsView.FILTERED_RESULTS_PANEL);
        filterTable.addStyleName(CSSConstants.FilteredResultsView.FILTERED_OPTIONS_PANEL);
        tabPanel.addStyleName(CSSConstants.Template.FILTERED_RESULTS_TAB_MENU_TABLE);
        results.addStyleName(CSSConstants.FilteredResultsView.FILTERED_RESULTS_TABLE);

        this.addActionButton(entitySearch);
        this.addActionButton(create);

        searchResultsPanel.add(filterTable);
        searchResultsPanel.add(tabPanel);
        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    /**
     * @return The provider used to populate the cell table
     */
    @Override
    public EnhancedAsyncDataProvider<V> getProvider() {
        return provider;
    }

    /**
     * @param provider The provider used to populate the cell table
     */
    @Override
    public void setProvider(@NotNull final EnhancedAsyncDataProvider<V> provider) {
        if (this.provider != null) {
            this.provider.removeDataDisplay(getResults());
        }

        this.provider = provider;
        provider.addDataDisplay(getResults());
    }

    /**
     * @return The panel that holds the filter fields and the search results
     */
    @NotNull
    public FlexTable getFilterTable() {
        return filterTable;
    }

    /**
     * @return The table that holds the filter fields
     */
    @NotNull
    public VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    /**
     * @return The cell table that displays the results
     */
    @Override
    public CellTable<V> getResults() {
        return results;
    }

    /**
     * @return The pager used to move over the results
     */
    @Override
    public SimplePager getPager() {
        return pager;
    }

    /**
     * @return The button that initiates a new search
     */
    @Override
    public PushButton getCreate() {
        return create;
    }

    /**
     * @return The button that creates a new entity
     */
    @Override
    public PushButton getEntitySearch() {
        return entitySearch;
    }

    /**
     * Adds a filter field above the search results
     *
     * @param label The label to apply to the field
     * @param field The field itself
     */
    protected void addFilterField(final String label, final Widget field) {
        final int rowCount = filterTable.getRowCount();
        filterTable.setWidget(rowCount, 0, new Label(label));
        filterTable.setWidget(rowCount, 1, field);
    }

    /**
     * @return The panel that holds the buttons used as tabs.
     */
    @NotNull
    @Override
    public FlexTable getTabPanel() {
        return tabPanel;
    }
}
