package org.jboss.pressgang.ccms.ui.client.local.sort.contentspec;

import java.util.Comparator;

import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jetbrains.annotations.Nullable;

/**
 * Sorts RESTContentSpecV1 objects based on their id.
 */
public final class RESTContentSpecIDSort implements Comparator<RESTContentSpecV1> {
    private final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTContentSpecIDSort(final boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(@Nullable final RESTContentSpecV1 arg0, @Nullable final RESTContentSpecV1 arg1) {
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
            Fall back to comparing by id
         */
        if (arg0.getId() == null && arg1.getId() == null) {
            return 0;
        }

        if (arg0.getId() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1.getId() == null) {
            return 1 * ascendingMultiplier;
        }

        return arg0.getId().compareTo(arg1.getId()) * ascendingMultiplier;
    }
}
