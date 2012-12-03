package org.jboss.pressgang.ccms.ui.client.local.sort;

import java.util.Comparator;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;

public class RESTTopicCollectionItemV1RevisionSort implements Comparator<RESTTopicCollectionItemV1> {

    @Override
    public int compare(final RESTTopicCollectionItemV1 arg0, final RESTTopicCollectionItemV1 arg1) {
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
        
        if (arg0.getItem() == null && arg1.getItem() == null) {
            return 0;
        }

        if (arg0.getItem() == arg1.getItem()) {
            return 0;
        }

        if (arg0.getItem() == null) {
            return -1;
        }

        if (arg1.getItem() == null) {
            return 1;
        }

        if (arg0.getItem().getRevision() == null && arg1.getItem().getRevision() == null) {
            return 0;
        }

        if (arg0.getItem().getRevision() == null) {
            return -1;
        }

        if (arg1.getItem().getRevision() == null)
        {
            return 1;
        }

        return arg0.getItem().getRevision().compareTo(arg1.getItem().getRevision());
    }
}
