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
