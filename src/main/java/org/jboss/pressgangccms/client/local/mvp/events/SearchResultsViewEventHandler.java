package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface SearchResultsViewEventHandler extends EventHandler
{
	void onSearchResultsViewOpen(final SearchResultsViewEvent event);
}
