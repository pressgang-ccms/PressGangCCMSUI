package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

import org.jetbrains.annotations.NotNull;

public class ContentSpecSearchTagsFieldsAndFiltersViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @NotNull
    @Override
    public Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
