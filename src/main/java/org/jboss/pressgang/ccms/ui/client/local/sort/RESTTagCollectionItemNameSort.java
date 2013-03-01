package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their name.
 */
public class RESTTagCollectionItemNameSort implements Comparator<RESTTagCollectionItemV1> {
    @Override
    public int compare(@Nullable final RESTTagCollectionItemV1 arg0, @Nullable final RESTTagCollectionItemV1 arg1) {
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
            return -1;
        }

        if (arg1 == null) {
            return 1 ;
        }

        /*
            Fall back to comparing by name
         */
        if (arg0.getItem().getName() == null && arg1.getItem().getName() == null) {
            return 0;
        }

        if (arg0.getItem().getName() == null) {
            return -1;
        }

        if (arg1.getItem().getName() == null) {
            return 1;
        }

        final int nameSort = arg0.getItem().getName().compareTo(arg1.getItem().getName());

        if (nameSort == 0) {
            return new RESTTagCollectionItemIDSort().compare(arg0, arg1);
        }

        return nameSort;
    }
}
