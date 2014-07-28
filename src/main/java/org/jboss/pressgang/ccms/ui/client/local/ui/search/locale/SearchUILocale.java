/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.locale;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an individual locale that can be searched for.
 */
public class SearchUILocale {
    /**
     * Whether the locale is include, excluded or ignored.
     */
    private TriStateSelectionState locale = TriStateSelectionState.NONE;
    /**
     * The locale name.
     */
    private String name;

    /**
     * @param name The locale name.
     */
    public SearchUILocale(@NotNull final String name) {
        this.name = name;
    }

    /**
     * @return Whether the locale is include, excluded or ignored.
     */
    public TriStateSelectionState getLocale() {
        return locale;
    }

    /**
     * @param locale Whether the locale is include, excluded or ignored.
     */
    public void setLocale(final TriStateSelectionState locale) {
        this.locale = locale;
    }

    /**
     * @return The locale name.
     */
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }
}
