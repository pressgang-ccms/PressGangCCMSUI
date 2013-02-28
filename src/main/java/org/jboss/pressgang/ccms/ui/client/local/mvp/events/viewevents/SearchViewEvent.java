package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import org.jetbrains.annotations.NotNull;

/**
 * Event used to display the topic search view.
 */
public final class SearchViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @NotNull
    @Override
    public Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
