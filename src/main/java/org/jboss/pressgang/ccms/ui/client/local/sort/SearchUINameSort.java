package org.jboss.pressgang.ccms.ui.client.local.sort;

import java.util.Comparator;

import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIBase;

public class SearchUINameSort implements Comparator<SearchUIBase> {

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
