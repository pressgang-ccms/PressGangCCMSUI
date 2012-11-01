package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class ProjectsFilteredResultsAndProjectViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public ProjectsFilteredResultsAndProjectViewEvent(String query) {
        super(query);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
