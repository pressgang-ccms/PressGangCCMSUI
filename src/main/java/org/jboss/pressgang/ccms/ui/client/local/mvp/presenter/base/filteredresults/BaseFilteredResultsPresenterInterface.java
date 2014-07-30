/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This is the base class that is used for components adding logic to views that list the results of a query.
 *
 * @param <V> The collection item type for entity T
 * @author Matthew Casperson
 */
public interface BaseFilteredResultsPresenterInterface<V extends RESTBaseEntityCollectionItemV1<?, ?, ?>> extends PresenterInterface {

    /**
     * @return The query string that represents the current state of the filters
     */
    @NotNull
    String getQuery();

    /**
     * @return The provider data used to populate the cell table
     */
    @NotNull
    ProviderUpdateData<V> getProviderData();

    /**
     * @return The display that is associated with the presenter.
     */
    @NotNull
    BaseFilteredResultsViewInterface<V> getDisplay();

    /**
     * Perform any initialisation required by filtered results presenters.
     *
     * @param topicId     The help topic that is associated with the view
     * @param pageId      The string that identifies the page
     * @param queryString The query string that is passed to the REST interface
     */
    void bindExtendedFilteredResults(@Nullable final String queryString);

    void setSelectedItem(@Nullable final V selectedItem);
}