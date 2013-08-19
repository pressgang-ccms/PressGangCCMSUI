package org.jboss.pressgang.ccms.ui.client.local.utilities;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Holds utility methods for entities
 */
public final class EntityUtilities {
    public static boolean topicHasTag(@NotNull final RESTTopicV1 value, final int id) {
        checkArgument(value.getTags() != null && value.getTags().getItems() != null, "Topic has to have a valid tags collection");

        for (final RESTTagCollectionItemV1 tag : value.getTags().getItems()) {
            if (tag.getItem().getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
