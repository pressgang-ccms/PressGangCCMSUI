/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a single tag under a parent category.
 *
 * @author Matthew Casperson
 */
public final class SearchUITag extends SearchUIBase {
    private TriStateSelectionState state = TriStateSelectionState.NONE;
    private RESTTagCollectionItemV1 tag;
    private TriStateSelectionState bulkTagState = TriStateSelectionState.NONE;
    private final boolean inMutuallyExclusiveCategory;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchUITag.class.getName());

    /**
     * @param parent The parent category that this tag belongs to
     * @param tag    The tag referenced by this object
     * @param filter The filter that defines the state of the tags
     */
    public SearchUITag(@NotNull final SearchUICategory parent, @NotNull final RESTTagCollectionItemV1 tag, final boolean inMutuallyExclusiveCategory, @Nullable final RESTFilterV1 filter) {
        super(tag.getItem().getName(), tag.getItem().getId());

        checkArgument(filter == null || filter.getFilterTags_OTM() != null, "Filter must be null or have a populated collection of tags.");

        this.inMutuallyExclusiveCategory = inMutuallyExclusiveCategory;

        this.tag = tag;

        if (filter != null) {
            for (@NotNull final RESTFilterTagCollectionItemV1 filterTag : filter.getFilterTags_OTM().getItems()) {
                if (filterTag.getItem().getTag().getId().equals(tag.getItem().getId())) {
                    if (filterTag.getItem().getState().equals(CommonFilterConstants.MATCH_TAG_STATE)) {
                        state = TriStateSelectionState.SELECTED;
                    } else if (filterTag.getItem().getState().equals(CommonFilterConstants.NOT_MATCH_TAG_STATE)) {
                        state = TriStateSelectionState.UNSELECTED;
                    }
                    break;
                }
            }
        }
    }

    /**
     * @return The tag referenced by this object
     */
    @NotNull
    public RESTTagCollectionItemV1 getTag() {
        return tag;
    }

    /**
     * @param tag The tag referenced by this object
     */
    public void setTag(@NotNull final RESTTagCollectionItemV1 tag) {
        this.tag = tag;
    }

    /**
     * @return The selection state of the tag
     */
    public TriStateSelectionState getState() {
        return state;
    }

    /**
     * @param state The selection state of the tag
     */
    public void setState(final TriStateSelectionState state) {
        this.state = state;
    }

    @Override
    public boolean equals(final Object other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public TriStateSelectionState getBulkTagState() {
        return bulkTagState;
    }

    public void setBulkTagState(final TriStateSelectionState bulkTagState) {
        this.bulkTagState = bulkTagState;
    }

    public boolean isInMutuallyExclusiveCategory() {
        return inMutuallyExclusiveCategory;
    }
}
