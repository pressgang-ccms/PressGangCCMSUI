package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * An event that is triggered to indicate that the Search Results view should be displayed.
 * 
 * @author Matthew Casperson
 */
public class SearchResultsViewEvent extends GwtEvent<SearchResultsViewEventHandler> {
    public static final Type<SearchResultsViewEventHandler> TYPE = new Type<SearchResultsViewEventHandler>();

    @Override
    public Type<SearchResultsViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SearchResultsViewEventHandler handler) {
        handler.onSearchResultsViewOpen(this);
    }
}
