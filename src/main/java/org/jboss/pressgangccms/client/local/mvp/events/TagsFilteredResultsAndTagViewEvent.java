package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class TagsFilteredResultsAndTagViewEvent extends GwtEvent<TagsFilteredResultsAndTagViewEventHandler> {
    public static final Type<TagsFilteredResultsAndTagViewEventHandler> TYPE = new Type<TagsFilteredResultsAndTagViewEventHandler>();
    private final String query;

    public String getQuery() {
        return query;
    }

    public TagsFilteredResultsAndTagViewEvent(final String query) {
        this.query = query;
    }

    @Override
    public Type<TagsFilteredResultsAndTagViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final TagsFilteredResultsAndTagViewEventHandler handler) {
        handler.onTagsFilteredResultsViewAndTagOpen(this);
    }
}
