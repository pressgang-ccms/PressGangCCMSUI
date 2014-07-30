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

package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIBase;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares SearchUIBase's objects based on their name.
 */
public final class SearchUINameSort implements Comparator<SearchUIBase>, Serializable {

    @Override
    public int compare(@Nullable final SearchUIBase arg0, @Nullable final SearchUIBase arg1) {
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
            return 1;
        }

        if (arg0.getName() == null && arg1.getName() == null) {
            return 0;
        }

        if (arg0.getName() == null) {
            return -1;
        }

        if (arg1.getName() == null) {
            return 1;
        }

        return arg0.getName().compareTo(arg1.getName());
    }

}
