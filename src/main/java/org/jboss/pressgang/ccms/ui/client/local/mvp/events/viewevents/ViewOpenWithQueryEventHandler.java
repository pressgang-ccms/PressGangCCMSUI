package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import org.jetbrains.annotations.NotNull;

/**
 * Event handler for events that open a new view and run a query.
 *
 * @author kamiller@redhat.com (Katie Miller)
 */
public class ViewOpenWithQueryEventHandler extends ViewOpenEventHandler {

    /**
     * Initialize the event handler.
     *
     * @param historyToken The history token to be appended to the URL.
     */
    public ViewOpenWithQueryEventHandler(@NotNull final String historyToken) {
        super(historyToken);
    }

    /**
     * Set the url, which in turn will trigger a presenter to be loaded and the view to be displayed.
     *
     * @param event The event that this handler is responding to
     */
    protected final void onViewOpen(@NotNull final ViewOpenWithQueryEvent<?> event) {
        if (event.isNewWindow()) {
            final String newWindowURL = Window.Location.getProtocol() + "//" + Window.Location.getHost() + "/"
                    + Window.Location.getPath() + "#" + historyToken + ";"
                    + ((event.getQuery() != null) ? event.getQuery() : "");

            Window.open(newWindowURL, "_blank", "");
        } else {
            final String token = historyToken + ";" + ((event.getQuery() != null) ? event.getQuery() : "");

            if (History.getToken().equals(token)) {
                History.fireCurrentHistoryState();
            } else {
                History.newItem(token);
            }
        }
    }
}
