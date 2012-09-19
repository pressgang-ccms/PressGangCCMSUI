package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class ImagesViewEvent extends GwtEvent<ImagesViewEventHandler> {
    public static final Type<ImagesViewEventHandler> TYPE = new Type<ImagesViewEventHandler>();

    @Override
    public Type<ImagesViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final ImagesViewEventHandler handler) {
        handler.onImagesViewOpen(this);
    }
}
