package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The help data associated with a widget.
 */
public class HelpData {
    private final Widget widget;
    private final Integer topicID;
    private final Integer topicRevision;
    private final Integer newSinceBuild;
    private final Integer zIndex;
    private final Integer opacity;
    private HandlerRegistration mouseOverHandler;
    private HandlerRegistration mouseOutHandler;
    private final boolean staticCss;
    private final int direction;

    /**
     *  The direction is a number from 0 to 7, and signifies a direction using the diagram below.
     *  0   1   2
     *  7       3
     *  6   5   4
     * @param widget The widget this help data relates to
     * @param topicID The topic that will be displayed for this widget
     * @param direction The direction that the help topic callout should appear
     */
    public HelpData(@NotNull final Widget widget,
                    @NotNull final Integer topicID,
                    final int direction)
    {
        this(widget, topicID, direction, null, null);
    }

    /**
     *  The direction is a number from 0 to 7, and signifies a direction using the diagram below.
     *  0   1   2
     *  7       3
     *  6   5   4
     * @param widget The widget this help data relates to
     * @param topicID The topic that will be displayed for this widget
     * @param direction The direction that the help topic callout should appear
     * @param topicRevision The topic revision
     * @param newSinceBuild When this feature was introduced
     */
    public HelpData(@NotNull final Widget widget,
                    @NotNull final Integer topicID,
                    final int direction,
                    @Nullable final Integer topicRevision,
                    @Nullable final Integer newSinceBuild) {

        checkArgument(direction >= 0 && direction <= 7, "direction should be greater than or equal to 0, and less than or equal to 7.");

        this.widget = widget;
        this.topicID = topicID;
        this.topicRevision = topicRevision;
        this.newSinceBuild = newSinceBuild;
        this.direction = direction;

        Integer zIndexParsed;
        try {
            zIndexParsed = Integer.parseInt(widget.getElement().getStyle().getZIndex());
        } catch (@NotNull final NumberFormatException ex) {
            zIndexParsed = 0;
        }

        zIndex = zIndexParsed;

        Integer opacityParsed;
        try {
            opacityParsed = Integer.parseInt(widget.getElement().getStyle().getOpacity());
        } catch (@NotNull final NumberFormatException ex) {
            opacityParsed = 0;
        }

        opacity = zIndexParsed;

        staticCss = widget.getElement().getStyle().getPosition() == Style.Position.STATIC.getCssName() ||
                widget.getElement().getStyle().getPosition().isEmpty();
    }



    /**
     *
     * @return The widget that this help data is associated with
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     *
     * @return The topic id that provides the help
     */
    public Integer getTopicID() {
        return topicID;
    }

    /**
     *
     * @return The topic revision that provides the help, or null if using the latest topic.
     */
    public Integer getTopicRevision() {
        return topicRevision;
    }

    /**
     *
     * @return A build ID that a new feature appeared in
     */
    public Integer getNewSinceBuild() {
        return newSinceBuild;
    }

    /**
     *
     * @return The original zIndex of the widget that is promoted in the help overlay
     */
    public Integer getzIndex() {
        return zIndex;
    }

    public HandlerRegistration getMouseOverHandler() {
        return mouseOverHandler;
    }

    public void setMouseOverHandler(@NotNull final HandlerRegistration mouseOverHandler) {
        this.mouseOverHandler = mouseOverHandler;
    }

    public HandlerRegistration getMouseOutHandler() {
        return mouseOutHandler;
    }

    public void setMouseOutHandler(@NotNull final HandlerRegistration mouseOutHandler) {
        this.mouseOutHandler = mouseOutHandler;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public boolean isStatic() {
        return staticCss;
    }

    public int getDirection() {
        return direction;
    }
}
