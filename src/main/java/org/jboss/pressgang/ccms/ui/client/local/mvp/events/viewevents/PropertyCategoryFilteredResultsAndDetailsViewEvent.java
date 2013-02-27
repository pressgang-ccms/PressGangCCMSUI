package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import org.jetbrains.annotations.NotNull;

/**
 * The event used to display the property category view.
 */
public class PropertyCategoryFilteredResultsAndDetailsViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public PropertyCategoryFilteredResultsAndDetailsViewEvent(@NotNull final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
