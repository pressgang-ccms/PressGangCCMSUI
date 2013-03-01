package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their id.
 */
public class RESTTagCollectionItemParentSort implements Comparator<RESTTagCollectionItemV1> {
    @NotNull final RESTCategoryV1 parent;

    public RESTTagCollectionItemParentSort(@NotNull final RESTCategoryV1 parent) {
        this.parent = parent;

    }

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
        final boolean arg0IsChild = ComponentCategoryV1.containsTag(parent, arg0.getItem().getId());
        final boolean arg1IsChild = ComponentCategoryV1.containsTag(parent, arg1.getItem().getId());

        if (arg0IsChild && arg1IsChild) {
            final int id = new RESTTagCollectionItemIDSort().compare(arg0, arg1);
            if (id == 0) {
                return new RESTTagCollectionItemNameSort().compare(arg0, arg1);
            }

            return id;
        }

        if (arg0IsChild) {
            return -1;
        }

        return -1;
    }
}
