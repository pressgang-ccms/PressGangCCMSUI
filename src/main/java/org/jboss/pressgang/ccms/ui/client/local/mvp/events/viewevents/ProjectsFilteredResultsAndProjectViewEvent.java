package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import org.jetbrains.annotations.NotNull;

public class ProjectsFilteredResultsAndProjectViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public ProjectsFilteredResultsAndProjectViewEvent(@NotNull final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @NotNull
    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
