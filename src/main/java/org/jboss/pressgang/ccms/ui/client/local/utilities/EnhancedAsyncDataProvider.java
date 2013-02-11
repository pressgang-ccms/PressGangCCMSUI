package org.jboss.pressgang.ccms.ui.client.local.utilities;

import com.google.gwt.view.client.AsyncDataProvider;

import java.util.List;

/**
 * An extension of the AsyncDataProvider class that provides methods for common procedures such as resetting the list,
 * displaying newly loaded data.
 * 
 * The AsyncDataProvider exposes two methods, updateRowData() and updateRowCount(), that are best called together. If you
 * call only updateRowData(), the CellTable will show the loading bar when there are zero rows, and will not remove rows when
 * you delete them in the provider. This class provides methods that group calls to these two methods for convenience.
 * 
 * This class is also used to fill the middle ground between the AsyncDataProvider and ListDataProvider classes. There are times
 * when you can use a memory backed list of all the items available, but that list may not be immediately available.
 * Unfortunately, to display the loading widget in the UI element while the list is being retrieved, you need to get access to
 * the updateRowCount() method, which is not exposed by the ListDataProvider class. So in these cases you can use the
 * resetProvider() and displayNewFixedList() methods to indicate that a complete list is in the process of being retrieved.
 * 
 * @author Matthew Casperson
 * @param <T> The type of item to be displayed
 */
abstract public class EnhancedAsyncDataProvider<T> extends AsyncDataProvider<T> {

    /**
     * Reset the provider so no items are shown and the loading widget is displayed.
     */
    public final void resetProvider() {
        if (this.getDataDisplays() != null) {
            this.updateRowCount(0, false);
        }
    }

    /**
     * Display a new collection of items, where the items in the collection are part of a bigger collection read asynchronously
     * 
     * @param items The items to be displayed
     * @param listSize The fixed number of items
     * @param startRow The row that this async load represents
     */
    public final void displayAsynchronousList(final List<T> items, final int listSize, final int startRow) {
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        this.updateRowData(startRow, items);
        this.updateRowCount(listSize, true);
        
    }

    /**
     * Display a new collection of items, where the items in the collection are part of a bigger collection read asynchronously
     * 
     * @param items The items to be displayed
     * @param listSize The fixed number of items
     * @param startRow The row that this async load represents
     */
    public final void displayFuzzyAsynchronousList(final List<T> items, final int listSize, final int startRow) {
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        this.updateRowData(startRow, items);
        this.updateRowCount(listSize, false);
    }

    /**
     * Display a new collection of items, where the items in the collection are loaded in bulk once, but still loaded
     * asynchronously
     * 
     * @param items The items to be displayed
     */
    public final void displayNewFixedList(final List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        this.updateRowData(0, items);
        this.updateRowCount(items.size(), true);
    }

}
