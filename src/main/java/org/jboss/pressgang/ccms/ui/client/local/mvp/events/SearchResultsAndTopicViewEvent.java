package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class SearchResultsAndTopicViewEvent extends ViewOpenWithQueryEvent {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public SearchResultsAndTopicViewEvent(String query) {
        super(query);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
