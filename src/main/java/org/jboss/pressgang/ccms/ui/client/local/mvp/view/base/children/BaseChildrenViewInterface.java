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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for the potential children
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseChildrenViewInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseCustomViewInterface<T> {

    /**
     * @return The entity that will have the possible children added to it.
     */
    @Nullable
    T getOriginalEntity();

    /**
     * @param originalEntity The entity that will have the possible children added to it.
     */
    void setOriginalEntity(final T originalEntity);

    /**
     * @return The panel used to hold the table and pager
     */
    @NotNull
    VerticalPanel getPossibleChildrenResultsPanel();

    /**
     * @return The table pager.
     */
    @NotNull
    SimplePager getPossibleChildrenPager();

    /**
     * @return The table used to display the possible children
     */
    @NotNull
    CellTable<C> getPossibleChildrenResults();

    /**
     * @return The provider used as the source for the possible children.
     */
    @Nullable
    EnhancedAsyncDataProvider<C> getPossibleChildrenProvider();

    /**
     * @param possibleChildrenProvider The provider used as the source for the possible children.
     */
    void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<C> possibleChildrenProvider);

    /**
     * @return The column that holds the buttons used to add or remove the possible children.
     */
    @NotNull
    Column<C, String> getPossibleChildrenButtonColumn();
}
