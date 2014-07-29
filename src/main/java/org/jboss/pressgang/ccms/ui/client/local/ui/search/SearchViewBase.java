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

package org.jboss.pressgang.ccms.ui.client.local.ui.search;

/**
 * Defines the interface implemented by all backing objects that populate the editors
 * that are displayed by the topics search views.
 *
 * @author Matthew Casperson
 */
public interface SearchViewBase {
    /**
     * @param includeQueryPrefix true if the query string should be prefixed with "query;"
     * @return the query string that is used to search for the topics
     */
    String getSearchQuery(final boolean includeQueryPrefix);
}
