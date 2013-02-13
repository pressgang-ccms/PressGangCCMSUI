package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
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

    /**
     * @param parent The parent category that this tag belongs to
     * @param tag    The tag referenced by this object
     * @param filter The filter that defines the state of the tags
     */
    public SearchUITag(@NotNull final SearchUICategory parent, @NotNull final RESTTagCollectionItemV1 tag, @Nullable final RESTFilterV1 filter) {
        super(tag.getItem().getName(), parent.getId() + "-" + tag.getItem().getId());
        this.tag = tag;

        if (filter != null) {
            for (final RESTFilterTagCollectionItemV1 filterTag : filter.getFilterTags_OTM().getItems())  {
                if (filterTag.getItem().getId() == tag.getItem().getId()) {
                    if (filterTag.getItem().getState() ==  Constants.TAG_INCLUDED) {
                        state = TriStateSelectionState.SELECTED;
                    } else if (filterTag.getItem().getState() ==  Constants.TAG_INCLUDED) {
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
    public RESTTagCollectionItemV1 getTag() {
        return this.tag;
    }

    /**
     * @param tag The tag referenced by this object
     */
    public void setTag(final RESTTagCollectionItemV1 tag) {
        this.tag = tag;
    }

    /**
     * @return The selection state of the tag
     */
    public TriStateSelectionState getState() {
        return this.state;
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

}
