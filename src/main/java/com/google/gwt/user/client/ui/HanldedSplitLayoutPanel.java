package com.google.gwt.user.client.ui;

import com.google.gwt.event.logical.shared.HasResizeHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * A SplitLayoutPanel that manages resize event listeners.
 * 
 * @author Matthew Casperson
 */
public class HanldedSplitLayoutPanel extends SplitLayoutPanel implements HasResizeHandlers {
    public HanldedSplitLayoutPanel(final int size) {
        super(size);
    }

    @Override
    public void onResize() {
        super.onResize();
        ResizeEvent.fire(this, this.getOffsetWidth(), this.getOffsetHeight());
    }

    @Override
    public HandlerRegistration addResizeHandler(final ResizeHandler handler) {
        return ensureHandlers().addHandler(ResizeEvent.getType(), handler);
    }
}
