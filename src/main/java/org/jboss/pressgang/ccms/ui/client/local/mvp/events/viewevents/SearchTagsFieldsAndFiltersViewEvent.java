package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

public final class SearchTagsFieldsAndFiltersViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @Override
    public Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
