package org.jboss.pressgangccms.client.local.events;

import com.google.gwt.event.shared.GwtEvent;

public class SearchResultsViewEvent extends GwtEvent<SearchResultsViewEventHandler>
{
	public static Type<SearchResultsViewEventHandler> TYPE = new Type<SearchResultsViewEventHandler>();

	@Override
	public Type<SearchResultsViewEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(final SearchResultsViewEventHandler handler)
	{
		handler.onSearchResultsViewOpen(this);
	}
}
