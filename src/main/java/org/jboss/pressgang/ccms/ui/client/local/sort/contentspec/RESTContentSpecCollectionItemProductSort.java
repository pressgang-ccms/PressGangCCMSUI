package org.jboss.pressgang.ccms.ui.client.local.sort.contentspec;

import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTContentSpecCollectionItemV1 objects based on their product.
 */
public class RESTContentSpecCollectionItemProductSort implements Comparator<RESTContentSpecCollectionItemV1> {
    final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTContentSpecCollectionItemProductSort(final boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(@Nullable final RESTContentSpecCollectionItemV1 arg0, @Nullable final RESTContentSpecCollectionItemV1 arg1) {
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
            Fall back to comparing by title
         */
        final RESTCSNodeV1 arg0Product = ComponentContentSpecV1.returnMetaData(arg0.getItem(), "Product");
        final RESTCSNodeV1 arg1Product = ComponentContentSpecV1.returnMetaData(arg1.getItem(), "Product");

        if (arg0Product == arg1Product) {
            return 0;
        }

        if (arg0Product == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Product == null) {
            return 1 * ascendingMultiplier;
        }

        if (arg0Product.getAdditionalText() == null && arg1Product.getAdditionalText() == null) {
            return 0;
        }

        if (arg0Product.getAdditionalText() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Product.getAdditionalText() == null) {
            return 1 * ascendingMultiplier;
        }

        final int nameSort = arg0Product.getAdditionalText().compareTo(arg1Product.getAdditionalText()) * ascendingMultiplier;

        if (nameSort == 0) {
            return new RESTContentSpecCollectionItemIDSort(ascending).compare(arg0, arg1);
        }

        return nameSort;
    }
}
