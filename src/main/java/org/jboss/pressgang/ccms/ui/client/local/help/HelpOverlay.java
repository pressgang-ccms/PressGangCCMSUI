package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
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
    private HelpCallout helpCallout;
    private HelpData lastWidget;

    @Inject
    private FailOverRESTCall failOverRESTCall;

    public boolean isHelpOverlayEnabled() {
        return helpOverlayEnabled;
    }

    public HelpOverlay() {
        dimmerPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_DIMMER_PANEL);
        mouseLockPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_MOUSE_LOCK_PANEL);

        final HandlerRegistration handler = Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            public void onPreviewNativeEvent(final Event.NativePreviewEvent event) {
                final int eventType = event.getTypeInt();
                switch (eventType) {
                    case Event.ONMOUSEMOVE:
                        if (isHelpOverlayEnabled()) {

                            checkState(helpDatabase != null, "If the overlay is enabled, the help database should not be null");

                            boolean found = false;
                            for (final Widget widget : helpDatabase.keySet()) {
                                /*
                                    Collision detection
                                */
                                if (widget.getElement().getAbsoluteLeft() <= event.getNativeEvent().getClientX() &&
                                        widget.getElement().getAbsoluteRight() >= event.getNativeEvent().getClientX() &&
                                        widget.getElement().getAbsoluteTop() <= event.getNativeEvent().getClientY() &&
                                        widget.getElement().getAbsoluteBottom() >= event.getNativeEvent().getClientY()) {

                                    found = true;

                                    if (helpDatabase.get(widget) != lastWidget) {

                                        lastWidget = helpDatabase.get(widget);

                                        if (helpCallout != null) {
                                            helpCallout.removeFromParent();
                                            helpCallout = null;
                                        }

                                        helpCallout = new HelpCallout(helpDatabase.get(widget));
                                        positionCallout();
                                        RootLayoutPanel.get().add(helpCallout);

                                        failOverRESTCall.performRESTCall(
                                                FailOverRESTCallDatabase.getTopic(helpDatabase.get(widget).getTopicID()),
                                                new RESTCallBack<RESTTopicV1>() {
                                                    public void success(@NotNull final RESTTopicV1 value) {
                                                        final String xml = Constants.DOCBOOK_RENDER_ONLY_XSL_REFERENCE + "\n" + DocbookDTD.getDtdDoctype() + "\n" + GWTUtilities.removeAllPreabmle(value.getXml());
                                                        failOverRESTCall.performRESTCall(
                                                                FailOverRESTCallDatabase.holdXML(xml),
                                                                new RESTCallBack<IntegerWrapper>() {
                                                                    public void success(@NotNull final IntegerWrapper value) {
                                                                        helpCallout.getiFrame().setUrl(ServerDetails.getSavedServer().getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + value.value);
                                                                    }
                                                                },
                                                                true
                                                        );
                                                    }
                                                }
                                        );

                                        break;
                                   }
                                }

                                if (!found) {
                                    lastWidget = null;

                                    if (helpCallout != null) {
                                        helpCallout.removeFromParent();
                                        helpCallout = null;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        );
    }

    private void positionCallout() {
        if (lastWidget.getDirection() == 7) {

            final double widgetRight = lastWidget.getWidget().getElement().getAbsoluteRight();
            final double widgetTop = lastWidget.getWidget().getElement().getAbsoluteTop();
            final double widgetHeight = lastWidget.getWidget().getElement().getClientHeight();
            final double calloutHeight = helpCallout.getElement().getClientHeight();

            helpCallout.getElement().getStyle().setLeft(widgetRight, Style.Unit.PX);
            helpCallout.getElement().getStyle().setTop(widgetTop + (widgetHeight / 2) - (calloutHeight / 2), Style.Unit.PX);
        }
    }

    /**
     * Register a widget and its help data with the help overlay.
     * @param helpData The help data for a widget.
     */
    public void registerWidget(@NotNull final HelpData helpData) {

    }

    public void showOver(@NotNull final Map<Widget, HelpData> helpDatabase) {
        checkState(!helpOverlayEnabled, "Overlay is already shown");
        checkState(this.helpDatabase == null, "If the overlay is being enabled, the help database should be null");

        this.helpDatabase = helpDatabase;
        helpOverlayEnabled = true;
        addDimmerPanel();
        promoteHelpWidgets();
        styleHelpWidgets();
        addEventListenersOnHelpWidgets();
    }

    public void hideOverlay() {
        checkState(helpOverlayEnabled, "Overlay is not shown");
        checkState(helpDatabase != null, "If the overlay is being hidden, the help database should not null");

        removeEventListenersOnHelpWidgets();
        unstyleHelpWidgets();
        demoteHelpWidgets();
        removeDimmerPanel();

        helpDatabase = null;
        helpOverlayEnabled = false;
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
