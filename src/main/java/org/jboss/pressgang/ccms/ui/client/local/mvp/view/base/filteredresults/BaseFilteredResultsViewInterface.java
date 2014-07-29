/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines the base for all views displaying a filtered results set
 *
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
public interface BaseFilteredResultsViewInterface<V extends RESTBaseEntityCollectionItemV1<?, ?, ?>>
        extends BaseTemplateViewInterface {
    /**
     * @return The button that creates a new entity
     */
    PushButton getCreate();

    /**
     * @return The button that initiates a new search
     */
    PushButton getEntitySearch();

    /**
     * @return The cell table that displays the results
     */
    CellTable<V> getResults();

    /**
     * @return The pager used to move over the results
     */
    SimplePager getPager();

    /**
     * @return The provider used to populate the cell table
     */
    EnhancedAsyncDataProvider<V> getProvider();

    /**
     * @return The panel that holds the buttons used as tabs.
     */
    @NotNull
    FlexTable getTabPanel();

    @NotNull
    VerticalPanel getSearchResultsPanel();

    /**
     * @param provider The provider used to populate the cell table
     */
    void setProvider(final EnhancedAsyncDataProvider<V> provider);
}
