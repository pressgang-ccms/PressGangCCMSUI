package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class ImagesFilteredResultsAndImageViewEvent extends ViewOpenWithQueryEvent {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public ImagesFilteredResultsAndImageViewEvent(String query) {
        super(query);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
