package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import org.jetbrains.annotations.NotNull;

/**
 * Event used to display the translated topic search view.
 */

public final class TranslatedSearchResultsAndTopicViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    /**
     * Initialize the event data.
     * @param query The query to be passed in the URL history token, and then ultimately onto the REST service
     * @param newWindow true if the view should be opened in a new window
     */
    public TranslatedSearchResultsAndTopicViewEvent(@NotNull final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @NotNull
    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
