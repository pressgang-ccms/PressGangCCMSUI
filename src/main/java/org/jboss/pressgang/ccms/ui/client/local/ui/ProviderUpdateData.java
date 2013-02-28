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
    public final T getSelectedItem() {
        return this.selectedItem;
    }

    public final void setSelectedItem(@Nullable final T selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Nullable
    public final Integer getStartRow() {
        return this.startRow;
    }

    public final void setStartRow(@Nullable final Integer startRow) {
        this.startRow = startRow;
    }

    @Nullable
    public final List<T> getItems() {
        return this.items;
    }

    public final void setItems(@Nullable final List<T> items) {
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
    public final T getDisplayedItem() {
        return this.displayedItem;
    }

    public final void setDisplayedItem(@Nullable final T displayedItem) {
        this.displayedItem = displayedItem;
    }

    public final void reset() {
        this.items = null;
        this.startRow = null;
        this.size = null;
    }

    public final void resetToEmpty() {
        this.items = new ArrayList<T>();
        this.startRow = 0;
        this.size = 0;
    }


    @Nullable
    public final Integer getSize() {
        return this.size;
    }

    public final void setSize(@Nullable final Integer size) {
        this.size = size;
    }
}
