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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.locale;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a collection of locales that can be searched for,
 */
public class SearchUILocales {
    private final List<SearchUILocale> locales = new ArrayList<SearchUILocale>();

    /**
     * @param localeNames The list of locale names to be used for searching.
     */
    public SearchUILocales(@NotNull final List<RESTLocaleV1> localeNames) {
        initialize(localeNames);
    }

    /**
     * Default constructor. Does nothing.
     */
    public SearchUILocales() {

    }

    /**
     * Initialize the list of locales that can be searched on.
     *
     * @param localeNames The list of locales to include in the UI
     */
    public void initialize(@NotNull final List<RESTLocaleV1> localeNames) {
        locales.clear();
        for (@NotNull final RESTLocaleV1 locale : localeNames) {
            locales.add(new SearchUILocale(locale.getValue()));
        }
    }

    /**
     * @return The individual locales that can be searched.
     */
    @NotNull
    public List<SearchUILocale> getLocales() {
        return locales;
    }

    /**
     * Build the query string that is used to search topics based on locale. Locales to be found or excluded are
     * defined in "locale#=ja[0 or 1]" query parameters, where the "#" is just a unique number, and [0 or 1] is either
     * "0" or "1" depending on whether the locale is to be specifically excluded or included.
     * <p/>
     * For example:
     * <p/>
     * locale1=ja1;locale2=de0
     * <p/>
     * means include topics that have the Japanese locale, and exclude those with the German locale.
     *
     * @param includeQueryPrefix true if the "query;" prefix is to be included, and false otherwise
     * @return the query string
     */
    @NotNull
    public String buildQueryString(final boolean includeQueryPrefix) {
        @NotNull final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        int index = 0;
        for (@NotNull final SearchUILocale locale : locales) {
            if (locale.getLocale() != TriStateSelectionState.NONE) {
                ++index;

                retValue.append(";");
                if (locale.getLocale() == TriStateSelectionState.SELECTED) {
                    retValue.append(CommonFilterConstants.MATCH_LOCALE + index + "=" + locale.getName() + CommonFilterConstants.MATCH_LOCALE_STATE);
                } else {
                    retValue.append(CommonFilterConstants.MATCH_LOCALE + index + "=" + locale.getName() + CommonFilterConstants.NOT_MATCH_LOCALE_STATE);
                }
            }
        }

        return retValue.toString();
    }
}
