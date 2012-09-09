package org.jboss.pressgangccms.client.local.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface SearchViewEventHandler extends EventHandler {
    void onSearchViewOpen(final SearchViewEvent event);
}
