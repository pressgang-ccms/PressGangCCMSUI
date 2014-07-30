/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

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
