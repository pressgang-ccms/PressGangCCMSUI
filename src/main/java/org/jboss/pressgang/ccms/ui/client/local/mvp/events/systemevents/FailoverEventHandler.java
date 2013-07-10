package org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents;

import com.google.gwt.event.shared.EventHandler;

/**
 * Defines an interface for handling failover events.
 */
public interface FailoverEventHandler extends EventHandler {
    void onFailOverEvent();
}
