package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface SearchResultsAndTopicViewEventHandler extends EventHandler {
    void onSearchResultsViewOpen(final SearchResultsAndTopicViewEvent event);
}
