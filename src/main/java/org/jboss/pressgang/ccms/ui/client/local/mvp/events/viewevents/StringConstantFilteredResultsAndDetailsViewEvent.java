package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

/**
 * Event used to display the String Constants view.
 */
public class StringConstantFilteredResultsAndDetailsViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public StringConstantFilteredResultsAndDetailsViewEvent(final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
