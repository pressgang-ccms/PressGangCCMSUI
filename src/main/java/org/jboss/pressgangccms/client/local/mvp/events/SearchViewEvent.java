package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class SearchViewEvent extends GwtEvent<SearchViewEventHandler> {
    public static final Type<SearchViewEventHandler> TYPE = new Type<SearchViewEventHandler>();

    @Override
    public Type<SearchViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SearchViewEventHandler handler) {
        handler.onSearchViewOpen(this);
    }
}
