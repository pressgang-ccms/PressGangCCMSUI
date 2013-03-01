package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their id.
 */
public class RESTTagCollectionItemIDSort implements Comparator<RESTTagCollectionItemV1> {
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
            Fall back to comparing by id
         */
        if (arg0.getItem().getId() == null && arg1.getItem().getId() == null) {
            return 0;
        }

        if (arg0.getItem().getId() == null) {
            return -1;
        }

        if (arg1.getItem().getId() == null) {
            return 1;
        }

        return arg0.getItem().getId().compareTo(arg1.getItem().getId());
    }
}
