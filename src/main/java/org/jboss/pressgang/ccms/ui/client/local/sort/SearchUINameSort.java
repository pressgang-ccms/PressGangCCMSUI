package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIBase;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares SearchUIBase's objects based on their name.
 */
public class SearchUINameSort implements Comparator<SearchUIBase>, Serializable {

    @Override
    public int compare(final SearchUIBase arg0, final SearchUIBase arg1) {
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

        if (arg0.getName() == null && arg1.getName() == null) {
            return 0;
        }

        if (arg0.getName() == null) {
            return -1;
        }

        if (arg1.getName() == null)
        {
            return 1;
        }

        return arg0.getName().compareTo(arg1.getName());
    }

}
