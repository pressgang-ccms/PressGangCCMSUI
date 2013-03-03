package org.jboss.pressgang.ccms.ui.client.local.sort.propertycategory;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyCategoryCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their id.
 */
public final class RESTPropertyCategoryCollectionItemIDSort implements Comparator<RESTPropertyCategoryCollectionItemV1> {
    private final boolean ascending;

    /**
     *
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTPropertyCategoryCollectionItemIDSort(final boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(@Nullable final RESTPropertyCategoryCollectionItemV1 arg0, @Nullable final RESTPropertyCategoryCollectionItemV1 arg1) {
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
            return 1  * ascendingMultiplier;
        }

        /*
            Fall back to comparing by id
         */
        if (arg0.getItem().getId() == null && arg1.getItem().getId() == null) {
            return 0;
        }

        if (arg0.getItem().getId() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1.getItem().getId() == null) {
            return 1 * ascendingMultiplier;
        }

        return arg0.getItem().getId().compareTo(arg1.getItem().getId()) * ascendingMultiplier;
    }
}
