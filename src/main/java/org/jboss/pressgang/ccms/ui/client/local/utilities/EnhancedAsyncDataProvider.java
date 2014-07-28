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

package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.Collections;
import java.util.List;

import com.google.gwt.view.client.AsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * An extension of the AsyncDataProvider class that provides methods for common procedures such as resetting the list,
 * displaying newly loaded data.
 * <p/>
 * The AsyncDataProvider exposes two methods, updateRowData() and updateRowCount(), that are best called together. If you
 * call only updateRowData(), the CellTable will show the loading bar when there are zero rows, and will not remove rows when
 * you delete them in the provider. This class provides methods that group calls to these two methods for convenience.
 * <p/>
 * This class is also used to fill the middle ground between the AsyncDataProvider and ListDataProvider classes. There are times
 * when you can use a memory backed list of all the items available, but that list may not be immediately available.
 * Unfortunately, to display the loading widget in the UI element while the list is being retrieved, you need to get access to
 * the updateRowCount() method, which is not exposed by the ListDataProvider class. So in these cases you can use the
 * resetProvider() and displayNewFixedList() methods to indicate that a complete list is in the process of being retrieved.
 *
 * @param <T> The type of item to be displayed
 * @author Matthew Casperson
 */
abstract public class EnhancedAsyncDataProvider<T> extends AsyncDataProvider<T> {

    /**
     * Reset the provider so no items are shown and the loading widget is displayed.
     */
    public void resetProvider(boolean resetRowCount) {
        updateRowData(0, Collections.EMPTY_LIST);
        if (resetRowCount) {
            updateRowCount(0, true);
        }
    }

    /**
     * Display a new collection of items, where the items in the collection are part of a bigger collection read asynchronously
     *
     * @param items    The items to be displayed
     * @param listSize The fixed number of items
     * @param startRow The row that this async load represents
     */
    public void displayAsynchronousList(@NotNull final List<T> items, final int listSize, final int startRow) {
        updateRowData(startRow, items);
        updateRowCount(listSize, true);

    }

    /**
     * Display a new collection of items, where the items in the collection are part of a bigger collection read asynchronously
     *
     * @param items    The items to be displayed
     * @param listSize The fixed number of items
     * @param startRow The row that this async load represents
     */
    public void displayFuzzyAsynchronousList(@NotNull final List<T> items, final int listSize, final int startRow) {
        updateRowData(startRow, items);
        updateRowCount(listSize, false);
    }

    /**
     * Display a new collection of items, where the items in the collection are loaded in bulk once, but still loaded
     * asynchronously
     *
     * @param items The items to be displayed
     */
    public void displayNewFixedList(@NotNull final List<T> items) {
        updateRowData(0, items);
        updateRowCount(items.size(), true);
    }

}
