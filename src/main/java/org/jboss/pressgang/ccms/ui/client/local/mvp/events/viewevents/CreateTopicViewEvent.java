package org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents;

/**
 * Event used to display the topic creation view.
 */
public final class CreateTopicViewEvent extends ViewOpenEvent<ViewOpenEventHandler> {
    public static final Type<ViewOpenEventHandler> TYPE = new Type<ViewOpenEventHandler>();

    @Override
    public Type<ViewOpenEventHandler> getAssociatedType() {
        return TYPE;
    }
}
