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

package org.jboss.pressgang.ccms.ui.client.local.ui;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Calling updateRowData on a provider requires knowing the start row and the list of items that should be displayed. This class
 * keeps those pieces of information.
 *
 * @param <T> The type of the entity to be displayed by the provider.
 * @author Matthew Casperson
 */
public class ProviderUpdateData<T> {
    /**
     * References the providers start row.
     */
    @Nullable
    private Integer startRow;
    /**
     * References the items that the provider will display.
     */
    @Nullable
    private List<T> items;
    /**
     * References the currently selected item.
     */
    private T selectedItem;
    /**
     * References the currently displayed item.
     */
    private T displayedItem;
    /**
     * The total size of the collection - for async lists
     */
    @Nullable
    private Integer size;

    @Nullable
    public T getSelectedItem() {
        return this.selectedItem;
    }

    public void setSelectedItem(@Nullable final T selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Nullable
    public Integer getStartRow() {
        return this.startRow;
    }

    public void setStartRow(@Nullable final Integer startRow) {
        this.startRow = startRow;
    }

    @Nullable
    public List<T> getItems() {
        return this.items;
    }

    public void setItems(@Nullable final List<T> items) {
        this.items = items;
    }

    public ProviderUpdateData() {

    }

    /**
     * @param displayedItem The initially displayed item
     */
    public ProviderUpdateData(@Nullable final T displayedItem) {
        this.displayedItem = displayedItem;
    }

    public ProviderUpdateData(@Nullable final Integer startRow, @Nullable final List<T> items) {
        this.startRow = startRow;
        this.items = items;
    }

    public ProviderUpdateData(@Nullable final Integer startRow, @Nullable final List<T> items, @Nullable final Integer size) {
        this(startRow, items);
        this.size = size;
    }

    @Nullable
    public T getDisplayedItem() {
        return this.displayedItem;
    }

    public void setDisplayedItem(@Nullable final T displayedItem) {
        this.displayedItem = displayedItem;
    }

    public void reset() {
        this.items = null;
        this.startRow = null;
        this.size = null;
    }

    public void resetToEmpty() {
        this.items = new ArrayList<T>();
        this.startRow = 0;
        this.size = 0;
    }


    @Nullable
    public Integer getSize() {
        return this.size;
    }

    public void setSize(@Nullable final Integer size) {
        this.size = size;
    }

    /**
     * @return true if this object holds valid data, and false otherwise.
     */
    public boolean isValid() {
        return items != null && startRow != null && size != null;
    }
}
