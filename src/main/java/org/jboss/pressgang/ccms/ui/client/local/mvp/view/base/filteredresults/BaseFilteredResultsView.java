package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class serves as the base for all views displaying a filtered results set
 * 
 * @author Matthew Casperson
 * 
 */
abstract public class BaseFilteredResultsView<T extends RESTBaseCollectionItemV1> extends BaseTemplateView implements
        BaseFilteredResultsViewInterface<T> {

    /** The button that initiates a new search */
    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    /** The button that creates a new entity */
    private final PushButton create = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Create());
    /** The pager used to move over the results */
    private final SimplePager pager = UIUtilities.createSimplePager();
    /** The celltable that displays the results */
    private final CellTable<T> results = UIUtilities.<T>createCellTable();
    /** The panel that holds the filter fields and the search results */
    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    /** The table that holds the filter fields */
    private final FlexTable filterTable = new FlexTable();
    /** The provider used to populate the celltable */
    private EnhancedAsyncDataProvider<T> provider;

    /** @return The provider used to populate the celltable */
    public EnhancedAsyncDataProvider<T> getProvider() {
        return provider;
    }

    /**
     * @param provider The provider used to populate the celltable
     */
    public void setProvider(final EnhancedAsyncDataProvider<T> provider) {
        this.provider = provider;
        provider.addDataDisplay(getResults());
    }

    /** @return The panel that holds the filter fields and the search results */
    public FlexTable getFilterTable() {
        return filterTable;
    }

    /** @return The table that holds the filter fields */
    public VerticalPanel getSearchResultsPanel() {
        return searchResultsPanel;
    }

    /** @return The celltable that displays the results */
    public CellTable<T> getResults() {
        return results;
    }

    /** @return The pager used to move over the results */
    public SimplePager getPager() {
        return pager;
    }

    /**
     * @return The button that initiates a new search
     */
    public PushButton getCreate() {
        return create;
    }

    /**
     * @return The button that creates a new entity
     */
    public PushButton getSearch() {
        return search;
    }

    public BaseFilteredResultsView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
        
        searchResultsPanel.addStyleName(CSSConstants.FilteredResultsView.FILTERED_RESULTS_PANEL);
        filterTable.addStyleName(CSSConstants.FilteredResultsView.FILTERED_OPTIONS_PANEL);
        
        this.addActionButton(search);
        this.addActionButton(create);
        this.addRightAlignedActionButtonPaddingPanel();
        
        searchResultsPanel.add(filterTable);
        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);
        
        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }
    
    /**
     * Adds a filter field above the search results
     * @param label The lable to apply to the field
     * @param field The field itself
     */
    protected void addFilterField(final String label, final Widget field)
    {
        final int rowCount = filterTable.getRowCount();
        filterTable.setWidget(rowCount, 0, new Label(label));
        filterTable.setWidget(rowCount, 1, field);
    }
}
