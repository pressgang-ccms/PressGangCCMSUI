package org.jboss.pressgangccms.client.local.ui.search;

import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.client.ui.TriStateSelectionState;

/**
 * This class represents a single tag under a parent category.
 * 
 * @author Matthew Casperson
 */
public class SearchUITag extends SearchUIBase {
    private TriStateSelectionState state = TriStateSelectionState.NONE;
    private RESTTagV1 tag;

    public RESTTagV1 getTag() {
        return tag;
    }

    public void setTag(RESTTagV1 tag) {
        this.tag = tag;
    }

    public TriStateSelectionState getState() {
        return state;
    }

    public void setState(TriStateSelectionState state) {
        this.state = state;
    }

    public SearchUITag(final SearchUICategory parent, final RESTTagV1 tag) {
        super(tag.getName(), parent.getId() + "-" + tag.getId());
        this.tag = tag;
    }
}
