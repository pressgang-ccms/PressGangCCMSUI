/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jetbrains.annotations.NotNull;

public interface BaseSearchAndEditViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseEntityCollectionV1<T, U, V>, V extends RESTBaseEntityCollectionItemV1<T, U, V>>
        extends BaseTemplateViewInterface {
    /**
     * @return The panel used to hold the list of tags
     */
    @NotNull
    SimpleLayoutPanel getResultsPanel();

    /**
     * @return The panel used to hold the views that display the tag details
     */
    @NotNull
    SimpleLayoutPanel getViewPanel();

    /**
     * @return The panel that holds the action buttons for the tag detail views
     */
    @NotNull
    FlexTable getViewActionButtonsParentPanel();

    /**
     * @return The panel that holds the action buttons for the list of tags
     */
    @NotNull
    FlexTable getResultsActionButtonsParentPanel();

    /**
     * @return The split panel that separates the tag list from the tag details views
     */
    @NotNull
    HandlerSplitLayoutPanel getSplitPanel();

    @NotNull
    DockLayoutPanel getResultsViewLayoutPanel();


    /**
     * Displays the contents of a child view
     *
     * @param displayedView The view to be displayed
     */
    void displayChildView(@NotNull final BaseTemplateViewInterface displayedView);

    void displaySearchResultsView(@NotNull final BaseFilteredResultsViewInterface<V> filteredResultsView);

    void hideSearchResults();

    void showSearchResults();
}
