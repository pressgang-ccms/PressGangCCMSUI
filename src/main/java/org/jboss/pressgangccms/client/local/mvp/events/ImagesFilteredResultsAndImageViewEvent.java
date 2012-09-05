package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ImagesFilteredResultsAndImageViewEvent extends GwtEvent<ImagesFilteredResultsViewAndImageEventHandler>
{
	public static Type<ImagesFilteredResultsViewAndImageEventHandler> TYPE = new Type<ImagesFilteredResultsViewAndImageEventHandler>();
	private final String query;
	
	public String getQuery()
	{
		return query;
	}
	
	public ImagesFilteredResultsAndImageViewEvent(final String query)
	{
		this.query = query;
	}
	
	@Override
	public Type<ImagesFilteredResultsViewAndImageEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(final ImagesFilteredResultsViewAndImageEventHandler handler)
	{
		handler.onImagesFilteredResultsAndImageViewOpen(this);
	}
}
