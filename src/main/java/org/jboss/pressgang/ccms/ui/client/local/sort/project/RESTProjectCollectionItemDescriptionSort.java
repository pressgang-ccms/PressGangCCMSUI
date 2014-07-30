/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.sort.project;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTTagCollectionItemV1 objects based on their name.
 */
public final class RESTProjectCollectionItemDescriptionSort implements Comparator<RESTProjectCollectionItemV1> {
    private final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTProjectCollectionItemDescriptionSort(final boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(@Nullable final RESTProjectCollectionItemV1 arg0, @Nullable final RESTProjectCollectionItemV1 arg1) {
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
        if (arg0.getItem().getDescription() == null && arg1.getItem().getDescription() == null) {
            return 0;
        }

        if (arg0.getItem().getDescription() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1.getItem().getDescription() == null) {
            return 1 * ascendingMultiplier;
        }

        final int nameSort = arg0.getItem().getDescription().compareTo(arg1.getItem().getDescription()) * ascendingMultiplier;

        if (nameSort == 0) {
            return new RESTProjectCollectionItemIDSort(ascending).compare(arg0, arg1);
        }

        return nameSort;
    }
}
