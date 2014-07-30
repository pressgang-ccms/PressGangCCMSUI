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

import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Sorts RESTContentSpecCollectionItemV1 objects based on their version.
 */
public class RESTContentSpecCollectionItemVersionSort implements Comparator<RESTContentSpecCollectionItemV1> {
    final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTContentSpecCollectionItemVersionSort(final boolean ascending) {
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
            Fall back to comparing by version
         */
        final RESTCSNodeV1 arg0Version = ComponentContentSpecV1.returnMetaData(arg0.getItem(), "Version");
        final RESTCSNodeV1 arg1Version = ComponentContentSpecV1.returnMetaData(arg1.getItem(), "Version");

        if (arg0Version == arg1Version) {
            return 0;
        }

        if (arg0Version == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Version == null) {
            return 1 * ascendingMultiplier;
        }

        if (arg0Version.getAdditionalText() == null && arg1Version.getAdditionalText() == null) {
            return 0;
        }

        if (arg0Version.getAdditionalText() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Version.getAdditionalText() == null) {
            return 1 * ascendingMultiplier;
        }

        final int nameSort = arg0Version.getAdditionalText().compareTo(arg1Version.getAdditionalText()) * ascendingMultiplier;

        if (nameSort == 0) {
            return new RESTContentSpecCollectionItemIDSort(ascending).compare(arg0, arg1);
        }

        return nameSort;
    }
}
