package org.jboss.pressgang.ccms.ui.client.local.ui.search;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single tag under a parent category.
 * 
 * @author Matthew Casperson
 */
public class SearchUITag extends SearchUIBase {
    private TriStateSelectionState state = TriStateSelectionState.NONE;
    private RESTTagCollectionItemV1 tag;

    public RESTTagCollectionItemV1 getTag() {
        return tag;
    }

    public void setTag(final RESTTagCollectionItemV1 tag) {
        this.tag = tag;
    }

    public TriStateSelectionState getState() {
        return state;
    }

    public void setState(final TriStateSelectionState state) {
        this.state = state;
    }

    public SearchUITag(final SearchUICategory parent, final RESTTagCollectionItemV1 tag) {
        super(tag.getItem().getName(), parent.getId() + "-" + tag.getItem().getId());
        this.tag = tag;
    }
}
