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
 * Sorts RESTContentSpecCollectionItemV1 objects based on their title.
 */
public class RESTContentSpecCollectionItemTitleSort implements Comparator<RESTContentSpecCollectionItemV1> {
    final boolean ascending;

    /**
     * @param ascending true if the items should be sorted in ascending order, false otherwise
     */
    public RESTContentSpecCollectionItemTitleSort(final boolean ascending) {
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
        final RESTCSNodeV1 arg0Title = ComponentContentSpecV1.returnMetaData(arg0.getItem(), "Title");
        final RESTCSNodeV1 arg1Title = ComponentContentSpecV1.returnMetaData(arg1.getItem(), "Title");

        if (arg0Title == arg1Title) {
            return 0;
        }

        if (arg0Title == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Title == null) {
            return 1 * ascendingMultiplier;
        }

        if (arg0Title.getAdditionalText() == null && arg1Title.getAdditionalText() == null) {
            return 0;
        }

        if (arg0Title.getAdditionalText() == null) {
            return -1 * ascendingMultiplier;
        }

        if (arg1Title.getAdditionalText() == null) {
            return 1 * ascendingMultiplier;
        }

        final int nameSort = arg0Title.getAdditionalText().compareTo(arg1Title.getAdditionalText()) * ascendingMultiplier;

        if (nameSort == 0) {
            return new RESTContentSpecCollectionItemIDSort(ascending).compare(arg0, arg1);
        }

        return nameSort;
    }
}
