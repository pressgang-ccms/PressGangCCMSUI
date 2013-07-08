package com.google.gwt.user.client.ui;

import com.google.gwt.event.logical.shared.HasResizeHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jetbrains.annotations.NotNull;

/**
 * A SplitLayoutPanel that manages resize event listeners.
 *
 * @author Matthew Casperson
 */
public final class HandlerSplitLayoutPanel extends SplitLayoutPanel implements HasResizeHandlers {
    private static final int ANIMATION_TIME_MILLISECONDS = 500;

    public HandlerSplitLayoutPanel(final int size) {
        super(size);
    }

    @Override
    public void onResize() {
        super.onResize();
        ResizeEvent.fire(this, this.getOffsetWidth(), this.getOffsetHeight());
    }

    @Override
    public HandlerRegistration addResizeHandler(@NotNull final ResizeHandler handler) {
        return ensureHandlers().addHandler(ResizeEvent.getType(), handler);
    }

    public void setSplitPosition(@NotNull final Widget widgetBeforeTheSplitter, final double size, final boolean animate) {
        if (this.getChildren().contains(widgetBeforeTheSplitter)) {
            final LayoutData layout = (LayoutData) widgetBeforeTheSplitter.getLayoutData();
            layout.oldSize = layout.size;
            layout.size = size;
            if (animate) {
                animate(ANIMATION_TIME_MILLISECONDS);
            } else {
                forceLayout();
            }
        }
    }

    public double getSplitPosition(@NotNull final Widget widget) {
        return ((LayoutData) widget.getLayoutData()).size;
    }

}
