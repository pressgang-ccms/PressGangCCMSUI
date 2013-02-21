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
     *
     * @param name The locale name.
     */
    public SearchUILocale(@NotNull final String name) {
        this.name = name;
    }

    /**
     *
     * @return Whether the locale is include, excluded or ignored.
     */
    public TriStateSelectionState getLocale() {
        return locale;
    }

    /**
     *
     * @param locale Whether the locale is include, excluded or ignored.
     */
    public void setLocale(final TriStateSelectionState locale) {
        this.locale = locale;
    }

    /**
     *
     * @return The locale name.
     */
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }
}
