package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import com.google.gwt.user.client.ui.TriStateSelectionState;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;

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
     */
    public SearchUITag(final SearchUICategory parent, final RESTTagCollectionItemV1 tag) {
        super(tag.getItem().getName(), parent.getId() + "-" + tag.getItem().getId());
        this.tag = tag;
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
