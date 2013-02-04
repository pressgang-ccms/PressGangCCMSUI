package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

public final class CreateTopicViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @Override
    public final Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
