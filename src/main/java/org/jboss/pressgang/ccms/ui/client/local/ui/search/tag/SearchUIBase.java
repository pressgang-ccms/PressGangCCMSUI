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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import org.jetbrains.annotations.NotNull;

/**
 * The base class for all the entities that make up the tag search screen.
 *
 * @author matthew
 */
public class SearchUIBase {
    /**
     * Each entity has a name
     */
    private final String name;
    /**
     * Each entity has an ID
     */
    private final Integer id;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SearchUIBase(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SearchUIBase)) {
            return false;
        }

        @NotNull final SearchUIBase otherCasted = (SearchUIBase) other;

        if (name == null && otherCasted.name == null) {
            return true;
        }

        if (name == null || otherCasted.name == null) {
            return false;
        }

        return (name.equals(otherCasted.name));
    }

    @Override
    public int hashCode() {
        if (name == null) {
            return 0;
        }
        return name.hashCode();
    }
}
