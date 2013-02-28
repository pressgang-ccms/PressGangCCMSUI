package org.jboss.pressgang.ccms.ui.client.local.sort;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two RESTTopicCollectionItemV1's based on their revision numbers.
 */
public final class RESTTopicCollectionItemV1RevisionSort implements Comparator<RESTTopicCollectionItemV1>, Serializable {

    @Override
    public int compare(@Nullable final RESTTopicCollectionItemV1 arg0, @Nullable final RESTTopicCollectionItemV1 arg1) {
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

        if (arg1.getItem().getRevision() == null) {
            return 1;
        }

        return arg0.getItem().getRevision().compareTo(arg1.getItem().getRevision());
    }
}
