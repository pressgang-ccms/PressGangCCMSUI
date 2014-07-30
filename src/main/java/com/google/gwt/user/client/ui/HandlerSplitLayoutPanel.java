/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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
