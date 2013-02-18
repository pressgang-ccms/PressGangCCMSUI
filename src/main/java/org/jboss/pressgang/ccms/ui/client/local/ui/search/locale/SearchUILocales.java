package org.jboss.pressgang.ccms.ui.client.local.ui.search.locale;

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
        for (final String locale : localeNames) {
            locales.add(new SearchUILocale(locale));
        }
    }

    /**
     * Default constructor. Does nothing.
     */
    public SearchUILocales() {

    }

    /**
     * @return The individual locales that can be searched.
     */
    public List<SearchUILocale> getLocales() {
        return locales;
    }
}
