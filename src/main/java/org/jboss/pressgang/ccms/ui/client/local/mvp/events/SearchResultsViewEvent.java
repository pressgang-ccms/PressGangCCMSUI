package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

/**
 * An event that is triggered to indicate that the Search Results view should be displayed.
 */
public class SearchResultsViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @Override
    public Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
