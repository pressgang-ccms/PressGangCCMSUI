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

package org.jboss.pressgang.ccms.ui.client.local.sort.topic;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two RESTTopicCollectionItemV1's based on their revision numbers.
 */
public final class RESTTopicCollectionItemV1RevisionSort implements Comparator<RESTTopicCollectionItemV1>, Serializable {

    @Override
    public int compare(@Nullable final RESTTopicCollectionItemV1 arg0, @Nullable final RESTTopicCollectionItemV1 arg1) {
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

        if (arg0.getItem() == null && arg1.getItem() == null) {
            return 0;
        }

        if (arg0.getItem() == arg1.getItem()) {
            return 0;
        }

        if (arg0.getItem() == null) {
            return -1;
        }

        if (arg1.getItem() == null) {
            return 1;
        }

        if (arg0.getItem().getRevision() == null && arg1.getItem().getRevision() == null) {
            return 0;
        }

        if (arg0.getItem().getRevision() == null) {
            return -1;
        }

        if (arg1.getItem().getRevision() == null) {
            return 1;
        }

        return arg0.getItem().getRevision().compareTo(arg1.getItem().getRevision());
    }
}
