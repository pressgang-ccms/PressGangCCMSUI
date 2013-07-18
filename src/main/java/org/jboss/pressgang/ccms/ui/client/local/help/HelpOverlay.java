package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A help overlay system for displaying in place help contents for any view.
 */
public class HelpOverlay {
    private static final float UNFOCUSED_HELP_WIDGET_OPACITY = 0.7f;
    private static final float FOCUSED_HELP_WIDGET_OPACITY = 1f;
    private boolean helpOverlayEnabled = false;
    private final Widget dimmerPanel = new HTML("<div>");

    public boolean isHelpOverlayEnabled() {
        return helpOverlayEnabled;
    }

    public HelpOverlay() {
        dimmerPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_DIMMER_PANEL);
    }

    /**
     * Register a widget and its help data with the help overlay.
     * @param helpData The help data for a widget.
     */
    public void registerWidget(@NotNull final HelpData helpData) {

    }

    public void toggleOverlay(@NotNull final Map<Widget, HelpData> helpDatabase) {
        if (helpOverlayEnabled) {
            removeEventListenersOnHelpWidgets(helpDatabase);
            unstyleHelpWidgets(helpDatabase);
            demoteHelpWidgets(helpDatabase);
            removeDimmerPanel();

        } else {
            addDimmerPanel();
            promoteHelpWidgets(helpDatabase);
            styleHelpWidgets(helpDatabase);
            addEventListenersOnHelpWidgets(helpDatabase);
        }
    }

    private void addDimmerPanel() {
        final RootLayoutPanel root = RootLayoutPanel.get();
        root.add(dimmerPanel);
    }

    private void removeDimmerPanel() {
        dimmerPanel.removeFromParent();
    }

    private void promoteHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
        for (final Widget widget : helpDatabase.keySet()) {
            widget.getElement().getStyle().setZIndex(Constants.HELP_OVERLAY_ITEM_ZINDEX);
        }
    }

    private void demoteHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getWidget().getElement().getStyle().setZIndex(helpData.getzIndex());
        }
    }

    private void styleHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getWidget().getElement().getStyle().setOpacity(UNFOCUSED_HELP_WIDGET_OPACITY);
        }
    }

    private void unstyleHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getWidget().getElement().getStyle().setOpacity(helpData.getOpacity());
        }
    }

    private void addEventListenersOnHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
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

    private void removeEventListenersOnHelpWidgets(@NotNull final Map<Widget, HelpData> helpDatabase) {
        for (final HelpData helpData : helpDatabase.values()) {
            helpData.getMouseOutHandler().removeHandler();
            helpData.getMouseOverHandler().removeHandler();
            helpData.setMouseOutHandler(null);
            helpData.setMouseOverHandler(null);
        }
    }
}
