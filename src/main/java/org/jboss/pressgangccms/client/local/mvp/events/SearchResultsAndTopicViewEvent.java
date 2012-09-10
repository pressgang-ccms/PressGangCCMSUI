package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class SearchResultsAndTopicViewEvent extends GwtEvent<SearchResultsAndTopicViewEventHandler> {
    public static final Type<SearchResultsAndTopicViewEventHandler> TYPE = new Type<SearchResultsAndTopicViewEventHandler>();
    private final String query;

    public String getQuery() {
        return query;
    }

    public SearchResultsAndTopicViewEvent(final String query) {
        this.query = query;
    }

    @Override
    public Type<SearchResultsAndTopicViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SearchResultsAndTopicViewEventHandler handler) {
        handler.onSearchResultsViewOpen(this);
    }
}
