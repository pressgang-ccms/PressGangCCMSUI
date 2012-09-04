package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ImagesFilteredResultsAndImageViewEvent extends GwtEvent<ImagesFilteredResultsViewAndImageEventHandler>
{
	public static Type<ImagesFilteredResultsViewAndImageEventHandler> TYPE = new Type<ImagesFilteredResultsViewAndImageEventHandler>();

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
