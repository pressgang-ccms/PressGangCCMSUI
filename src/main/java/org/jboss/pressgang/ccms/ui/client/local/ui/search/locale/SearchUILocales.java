package org.jboss.pressgang.ccms.ui.client.local.ui.search.locale;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of locales that can be searched for,
 */
public class SearchUILocales {
    private final List<SearchUILocale> locales = new ArrayList<SearchUILocale>();

    /**
     * @param localeNames The list of locale names to be used for searching.
     */
    public SearchUILocales(@NotNull final List<String> localeNames) {
        initialize(localeNames);
    }

    /**
     * Default constructor. Does nothing.
     */
    public SearchUILocales() {

    }

    public void initialize(@NotNull final List<String> localeNames) {
        locales.clear();
        for (final String locale : localeNames) {
            locales.add(new SearchUILocale(locale));
        }
    }

    /**
     * @return The individual locales that can be searched.
     */
    public List<SearchUILocale> getLocales() {
        return locales;
    }

    /**
     * Build the query string that is used to search topics based on locale. Locales to be found or excluded are
     * defined in "locale#" query parameters, where the # is just a unique number.
     *
     * @param includeQueryPrefix  true if the "query;" prefix is to be included, and false otherwise
     * @return the query string
     */
    public String buidlQueryString(final boolean includeQueryPrefix) {
        final StringBuilder retValue = new StringBuilder(includeQueryPrefix ? Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON : "");

        int index = 0;
        for (final SearchUILocale locale : locales) {
            if (locale.getLocale() != TriStateSelectionState.NONE) {
                ++index;

                retValue.append(";");
                if (locale.getLocale() == TriStateSelectionState.SELECTED) {
                    retValue.append(CommonFilterConstants.MATCH_LOCALE + index + "=" + locale.getName());
                } else {
                    retValue.append(CommonFilterConstants.NOT_MATCH_LOCALE_STATE + index + "=" + locale.getName());
                }
            }
        }

        return  retValue.toString();
    }
}
