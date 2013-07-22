package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;

/**
 * A help overlay system for displaying in place help contents for any view.
 */
public class HelpOverlay {
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(HelpOverlay.class.getName());
    private static final float UNFOCUSED_HELP_WIDGET_OPACITY = 0.7f;
    private static final float FOCUSED_HELP_WIDGET_OPACITY = 1f;
    private boolean helpOverlayEnabled = false;
    private final Widget dimmerPanel = new HTML();
    private final Widget mouseLockPanel = new HTML();
    private Map<Widget, HelpData> helpDatabase;

    public boolean isHelpOverlayEnabled() {
        return helpOverlayEnabled;
    }

    public HelpOverlay() {
        dimmerPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_DIMMER_PANEL);
        mouseLockPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_MOUSE_LOCK_PANEL);

        final HandlerRegistration handler = mouseLockPanel.addHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(@NotNull final MouseMoveEvent event) {
                if (helpOverlayEnabled) {
                    checkState(helpDatabase != null, "If the overlay is enabled, the help database should not be null");

                    for (final Widget widget : helpDatabase.keySet()) {
                        /*
                            Collision detection
                         */
                        if (widget.getElement().getAbsoluteLeft() <= event.getClientX() &&
                                widget.getElement().getAbsoluteRight() >= event.getClientX() &&
                                widget.getElement().getAbsoluteTop() <= event.getClientY() &&
                                widget.getElement().getAbsoluteBottom() >= event.getClientY()) {

                            LOGGER.info("Mouse is over a help widget");

                            break;
                        }
                    }
                }
            }
        }, new GwtEvent.Type<MouseMoveHandler>());
    }

    /**
     * Register a widget and its help data with the help overlay.
     * @param helpData The help data for a widget.
     */
    public void registerWidget(@NotNull final HelpData helpData) {

    }

    public void showOver(@NotNull final Map<Widget, HelpData> helpDatabase) {
        checkState(!helpOverlayEnabled, "Overlay is already shown");
        checkState(helpDatabase != null, "If the overlay is enabled, the help database should not be null");

        this.helpDatabase = helpDatabase;
        helpOverlayEnabled = true;
        addDimmerPanel();
        promoteHelpWidgets();
        styleHelpWidgets();
        addEventListenersOnHelpWidgets();
    }

    public void hideOverlay() {
        checkState(helpOverlayEnabled, "Overlay is not shown");
        checkState(helpDatabase == null, "If the overlay is not enabled, the help database should not null");

        helpDatabase = null;
        helpOverlayEnabled = false;
        removeEventListenersOnHelpWidgets();
        unstyleHelpWidgets();
        demoteHelpWidgets();
        removeDimmerPanel();
    }

    private void addDimmerPanel() {
        final RootLayoutPanel root = RootLayoutPanel.get();
        root.add(dimmerPanel);
        root.add(mouseLockPanel);
    }

    private void removeDimmerPanel() {
        dimmerPanel.removeFromParent();
        mouseLockPanel.removeFromParent();
    }

    private void promoteHelpWidgets() {
        for (final Widget widget : helpDatabase.keySet()) {
            widget.getElement().getStyle().setZIndex(Constants.HELP_OVERLAY_ITEM_ZINDEX);
        }
    }

    private void demoteHelpWidgets() {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getWidget().getElement().getStyle().setZIndex(helpData.getzIndex());
        }
    }

    private void styleHelpWidgets() {
        for (final HelpData helpData : helpDatabase.values()) {
            if (helpData.isStatic()) {
                helpData.getWidget().getElement().getStyle().setPosition(Style.Position.RELATIVE);
            }
        }
    }

    private void unstyleHelpWidgets() {
        for (final HelpData helpData : helpDatabase.values()) {
            if (helpData.isStatic()) {
                helpData.getWidget().getElement().getStyle().setPosition(Style.Position.STATIC);
            }
        }
    }

    private void addEventListenersOnHelpWidgets() {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.setMouseOverHandler(
                helpData.getWidget().addMouseOverHandler(new MouseOverHandler() {
                    @Override
                    public void onMouseOver(@NotNull final MouseOverEvent event) {
                        helpData.getWidget().getElement().getStyle().setOpacity(FOCUSED_HELP_WIDGET_OPACITY);
                    }
                })
            );

            helpData.setMouseOutHandler(
                helpData.getWidget().addMouseOutHandler(new MouseOutHandler() {
                    @Override
                    public void onMouseOut(@NotNull final MouseOutEvent event) {
                        helpData.getWidget().getElement().getStyle().setOpacity(UNFOCUSED_HELP_WIDGET_OPACITY);
                    }
                })
            );
        }
    }

    private void removeEventListenersOnHelpWidgets() {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getMouseOutHandler().removeHandler();
            helpData.getMouseOverHandler().removeHandler();
            helpData.setMouseOutHandler(null);
            helpData.setMouseOverHandler(null);
        }
    }

    public Widget getMouseLockPanel() {
        return mouseLockPanel;
    }
}
