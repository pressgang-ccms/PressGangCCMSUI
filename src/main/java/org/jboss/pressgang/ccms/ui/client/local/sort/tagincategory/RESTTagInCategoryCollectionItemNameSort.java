package org.jboss.pressgang.ccms.ui.client.local.sort.tagincategory;

import java.util.Comparator;

import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jetbrains.annotations.Nullable;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their name.
 */
public class RESTTagInCategoryCollectionItemNameSort implements Comparator<RESTTagInCategoryCollectionItemV1> {
    final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTTagInCategoryCollectionItemNameSort(final boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(@Nullable final RESTTagInCategoryCollectionItemV1 arg0, @Nullable final RESTTagInCategoryCollectionItemV1 arg1) {
        final int ascendingMultiplier = ascending ? 1 : -1;

        /*
            First deal with null objects
        */
        if (arg0 == null && arg1 == null) {
            return 0;
        }

        if (arg0 == arg1) {
            return 0;
        }

        if (arg0 == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1 == null) {
            return 1 * ascendingMultiplier;
        }

        /*
            Fall back to comparing by name
         */
        if (arg0.getItem().getName() == null && arg1.getItem().getName() == null) {
            return 0;
        }

        if (arg0.getItem().getName() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1.getItem().getName() == null) {
            return 1 * ascendingMultiplier;
        }

        return arg0.getItem().getName().compareTo(arg1.getItem().getName()) * ascendingMultiplier;
    }
}
