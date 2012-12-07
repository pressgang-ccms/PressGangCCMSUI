package org.jboss.pressgang.ccms.ui.client.local.ui.search.tag;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single tag under a parent category.
 * 
 * @author Matthew Casperson
 */
public class SearchUITag extends SearchUIBase {
    private TriStateSelectionState state = TriStateSelectionState.NONE;
    private RESTTagCollectionItemV1 tag;
    
    /**
     * 
     * @param parent The parent category that this tag belongs to
     * @param tag The tag referenced by this object
     */
    public SearchUITag(final SearchUICategory parent, final RESTTagCollectionItemV1 tag) {
        super(tag.getItem().getName(), parent.getId() + "-" + tag.getItem().getId());
        this.tag = tag;
    }

    /**
     * 
     * @return The tag referenced by this object
     */
    public final RESTTagCollectionItemV1 getTag() {
        return this.tag;
    }

    /**
     * 
     * @param tag The tag referenced by this object
     */
    public final void setTag(final RESTTagCollectionItemV1 tag) {
        this.tag = tag;
    }

    /**
     * 
     * @return The selection state of the tag
     */
    public final TriStateSelectionState getState() {
        return this.state;
    }

    /**
     * 
     * @param state The selection state of the tag
     */
    public final void setState(final TriStateSelectionState state) {
        this.state = state;
    }

 
}
