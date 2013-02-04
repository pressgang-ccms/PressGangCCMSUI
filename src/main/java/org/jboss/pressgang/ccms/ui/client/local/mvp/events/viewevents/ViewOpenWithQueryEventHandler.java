package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

/**
 * Event handler for events that open a new view and run a query.
 * 
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenWithQueryEventHandler extends ViewOpenEventHandler {

    public ViewOpenWithQueryEventHandler(final String historyToken) {
        super(historyToken);
    }

    protected final void onViewOpen(final ViewOpenWithQueryEvent<?> event) {
        if (event.isNewWindow()) {
            final String newWindowURL = Window.Location.getProtocol() + "//" + Window.Location.getHost() + "/"
                    + Window.Location.getPath() + "#" + historyToken + ";"
                    + ((event.getQuery() != null) ? event.getQuery() : "");
            
            Window.open(newWindowURL, "_blank", "");
        } else {
            History.newItem(historyToken + ";" + ((event.getQuery() != null) ? event.getQuery() : ""));
        }
    }
}
