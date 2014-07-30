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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchViewBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseSearchUIFields implements SearchViewBase {

    protected BaseSearchUIFields() {

    }

    /**
     * @param filter The filter that defines the state of the tags
     */
    protected BaseSearchUIFields(@Nullable final RESTFilterV1 filter) {
        initialize(filter);
    }

    public abstract void initialize(@Nullable final RESTFilterV1 filter);

    public abstract void populateFilter(@NotNull final RESTFilterV1 filter);

    protected RESTFilterFieldV1 createFilterField(@NotNull final String name, @NotNull final String value) {
        @NotNull final RESTFilterFieldV1 field = new RESTFilterFieldV1();
        field.explicitSetName(name);
        field.explicitSetValue(value);
        return field;
    }
}
