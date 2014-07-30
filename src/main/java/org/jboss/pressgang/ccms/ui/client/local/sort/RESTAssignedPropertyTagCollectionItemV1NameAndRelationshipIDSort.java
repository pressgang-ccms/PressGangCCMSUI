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

package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two RESTAssignedPropertyTagCollectionItemV1s based on their names and relationship ids.
 */
public final class RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort implements Comparator<RESTAssignedPropertyTagCollectionItemV1>, Serializable {

    @Override
    public int compare(@Nullable final RESTAssignedPropertyTagCollectionItemV1 arg0, @Nullable final RESTAssignedPropertyTagCollectionItemV1 arg1) {
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

        /*
            Second order sort when names are equal or null
         */
        if ((arg0.getItem().getName() == null && arg1.getItem().getName() == null) ||
                (arg0.getItem().getName() != null && arg1.getItem().getName() != null && arg0.getItem().getName().equals(arg1.getItem().getName()))) {
            if (arg0.getItem().getRelationshipId() == null && arg1.getItem().getRelationshipId() == null) {
                return 0;
            }

            if (arg0.getItem().getRelationshipId() == null) {
                return -1;
            }

            if (arg1.getItem().getRelationshipId() == null) {
                return 1;
            }

            return arg0.getItem().getRelationshipId().compareTo(arg1.getItem().getRelationshipId());
        }

        if (arg0.getItem().getName() == null) {
            return -1;
        }

        if (arg1.getItem().getName() == null) {
            return 1;
        }

        return arg0.getItem().getName().compareTo(arg1.getItem().getName());
    }
}
