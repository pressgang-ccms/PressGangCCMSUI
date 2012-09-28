package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class TagsFilteredResultsAndTagViewEvent extends ViewOpenWithQueryEvent {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public TagsFilteredResultsAndTagViewEvent(String query) {
        super(query);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
