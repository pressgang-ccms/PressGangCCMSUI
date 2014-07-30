/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.utilities;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityWithPropertiesV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jetbrains.annotations.NotNull;

/**
 * Holds utility methods for entities. These are usually copied and pasted from the Component classes in the
 * REST library, which exist to separate out code from the regular REST entities that is not GWT compatible.
 */
public final class EntityUtilities {
    public static boolean topicHasTag(@NotNull final RESTBaseTopicV1<?, ?, ?> value, final int id) {
        checkArgument(value.getTags() != null && value.getTags().getItems() != null, "Topic has to have a valid tags collection");

        for (final RESTTagCollectionItemV1 tag : value.getTags().getItems()) {
            if (tag.getItem().getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static RESTAssignedPropertyTagV1 returnProperty(final RESTBaseEntityWithPropertiesV1<?, ?, ?> source, final Integer propertyTagId) {
        checkArgument(source != null, "The source parameter can not be null");

        if (source.getProperties() != null && source.getProperties().getItems() != null) {
            final List<RESTAssignedPropertyTagV1> properties = source.getProperties().returnItems();
            for (final RESTAssignedPropertyTagV1 property : properties) {
                if (property.getId().equals(propertyTagId)) return property;
            }
        }

        return null;
    }

    public static List<RESTAssignedPropertyTagV1> returnProperties(final RESTBaseEntityWithPropertiesV1<?, ?, ?> source, final Integer propertyTagId) {
        checkArgument(source != null, "The source parameter can not be null");

        if (source.getProperties() != null && source.getProperties().getItems() != null) {
            final List<RESTAssignedPropertyTagV1> retValue = new ArrayList<RESTAssignedPropertyTagV1>();
            final List<RESTAssignedPropertyTagV1> properties = source.getProperties().returnItems();
            for (final RESTAssignedPropertyTagV1 property : properties) {
                if (property.getId().equals(propertyTagId)) {
                    retValue.add(property);
                }
            }
            return retValue;
        }

        return null;
    }
}
