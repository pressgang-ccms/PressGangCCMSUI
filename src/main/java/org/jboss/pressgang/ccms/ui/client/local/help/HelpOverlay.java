package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
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
    @Inject
    private HandlerManager eventBus;
    private static final float UNFOCUSED_HELP_WIDGET_OPACITY = 0.7f;
    private static final float FOCUSED_HELP_WIDGET_OPACITY = 1f;
    private boolean helpOverlayEnabled = false;
    private final Widget dimmerPanel = new HTML();
    private final Widget mouseLockPanel = new HTML();
    private Map<Widget, HelpData> helpDatabase;
    private HelpCallout helpCallout;
    private HelpData lastWidget;
    private final PushButton close = new PushButton(PressGangCCMSUI.INSTANCE.Close());

    @Inject
    private FailOverRESTCall failOverRESTCall;

    private ClickHandler editClickHandler = new ClickHandler() {
        @Override
        public void onClick(@NotNull final ClickEvent event) {
            if (lastWidget != null) {
                eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(
                    Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + lastWidget.getTopicID(), true));
            }
        }
    };

    private ClickHandler closeClickHandler = new ClickHandler() {
        @Override
        public void onClick(@NotNull final ClickEvent event) {
            if (helpCallout != null) {
                helpCallout.removeFromParent();
                helpCallout = null;
                lastWidget = null;
            }
        }
    };

    public boolean isHelpOverlayEnabled() {
        return helpOverlayEnabled;
    }

    public HelpOverlay() {
        dimmerPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_DIMMER_PANEL);
        mouseLockPanel.addStyleName(CSSConstants.HelpOverlay.HELP_OVERLAY_MOUSE_LOCK_PANEL);
        close.addStyleName(CSSConstants.HelpOverlay.CLOSE_BUTTON);

        close.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                if (isHelpOverlayEnabled()) {
                    hideOverlay();
                }
            }
        });

        final HandlerRegistration handler = Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            public void onPreviewNativeEvent(final Event.NativePreviewEvent event) {
                final int eventType = event.getTypeInt();
                switch (eventType) {
                    case Event.ONMOUSEMOVE:
                        if (isHelpOverlayEnabled()) {

                            checkState(helpDatabase != null, "If the overlay is enabled, the help database should not be null");

                            for (final Widget widget : helpDatabase.keySet()) {
                                /*
                                    Collision detection
                                */
                                if (widget.getElement().getAbsoluteLeft() <= event.getNativeEvent().getClientX() &&
                                        widget.getElement().getAbsoluteRight() >= event.getNativeEvent().getClientX() &&
                                        widget.getElement().getAbsoluteTop() <= event.getNativeEvent().getClientY() &&
                                        widget.getElement().getAbsoluteBottom() >= event.getNativeEvent().getClientY()) {

                                    if (helpDatabase.get(widget) != lastWidget) {
                                        if (!(helpCallout != null &&
                                            helpCallout.getElement().getAbsoluteLeft() <= event.getNativeEvent().getClientX() &&
                                            helpCallout.getElement().getAbsoluteRight()  >= event.getNativeEvent().getClientX() &&
                                            helpCallout.getElement().getAbsoluteTop() <= event.getNativeEvent().getClientY() &&
                                            helpCallout.getElement().getAbsoluteBottom() >= event.getNativeEvent().getClientY())) {

                                            showCallout(widget);
                                            break;
                                        }
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

    private void showCallout(@NotNull final Widget widget) {
        lastWidget = helpDatabase.get(widget);

        if (helpCallout != null) {
            helpCallout.removeFromParent();
            helpCallout = null;
        }

        helpCallout = new HelpCallout(helpDatabase.get(widget));
        helpCallout.getEdit().addClickHandler(editClickHandler);
        helpCallout.getClose().addClickHandler(closeClickHandler);
        RootLayoutPanel.get().add(helpCallout);
        positionCallout();

        final HelpCallout currentHelpCallout = helpCallout;
        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopic(helpDatabase.get(widget).getTopicID()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 value) {
                        final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + DocbookDTD.getDtdDoctype() + "\n" + GWTUtilities.removeAllPreabmle(value.getXml());
                        failOverRESTCall.performRESTCall(
                                FailOverRESTCallDatabase.holdXML(xml),
                                new RESTCallBack<IntegerWrapper>() {
                                    public void success(@NotNull final IntegerWrapper value) {
                                        /*
                                            It is possible that the callout was changed or closed by the time we get
                                            to this call.
                                         */
                                        if (helpCallout == currentHelpCallout) {
                                            helpCallout.getiFrame().setUrl(
                                                    ServerDetails.getSavedServer().getRestEndpoint() +
                                                            Constants.ECHO_ENDPOINT + "?id=" + value.value + "&" +
                                                            Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded());
                                        }
                                    }
                                },
                                true
                        );
                    }
                }
        );
    }

    private void positionCloseButton() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                positionCloseButtonDirect();
            }
        });
    }

    private void positionCloseButtonDirect() {
        close.getElement().getStyle().setRight(8, Style.Unit.PX);
        close.getElement().getStyle().setTop(8, Style.Unit.PX);
        close.getElement().getStyle().setProperty("left", "");
        close.getElement().getStyle().setProperty("bottom", "");
    }

    private void positionCallout() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {

                helpCallout.getElement().getStyle().clearRight();
                helpCallout.getElement().getStyle().clearLeft();
                helpCallout.getElement().getStyle().clearTop();
                helpCallout.getElement().getStyle().clearBottom();

                final double widgetLeft = lastWidget.getWidget().getElement().getAbsoluteLeft();
                final double widgetRight = lastWidget.getWidget().getElement().getAbsoluteRight();
                final double widgetBottom = lastWidget.getWidget().getElement().getAbsoluteBottom();
                final double widgetTop = lastWidget.getWidget().getElement().getAbsoluteTop();
                final double widgetHeight = lastWidget.getWidget().getElement().getClientHeight();
                final double widgetWidth = lastWidget.getWidget().getElement().getClientWidth();
                final double calloutWidth = helpCallout.getElement().getClientWidth();
                final double calloutHeight = helpCallout.getElement().getClientHeight();

                if (lastWidget.getDirection() == 0) {
                    helpCallout.getElement().getStyle().setLeft(widgetRight - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetBottom - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                } else if (lastWidget.getDirection() == 1) {
                    helpCallout.getElement().getStyle().setLeft(widgetLeft + (widgetWidth / 2) - (calloutWidth / 2), Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetBottom - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                } else if (lastWidget.getDirection() == 2) {
                    helpCallout.getElement().getStyle().setLeft(widgetRight - widgetWidth - calloutWidth + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop + widgetHeight - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                } else if (lastWidget.getDirection() == 3) {
                    helpCallout.getElement().getStyle().setLeft(widgetLeft - calloutWidth + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop + (widgetHeight / 2) - (calloutHeight / 2), Style.Unit.PX);
                } else if (lastWidget.getDirection() == 4) {
                    helpCallout.getElement().getStyle().setLeft(widgetRight - widgetWidth - calloutWidth + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop - calloutHeight + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                } else if (lastWidget.getDirection() == 5) {
                    helpCallout.getElement().getStyle().setLeft(widgetLeft + (widgetWidth / 2) - (calloutWidth / 2), Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop - calloutHeight + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                } else if (lastWidget.getDirection() == 6) {
                    helpCallout.getElement().getStyle().setLeft(widgetRight - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop - calloutHeight + Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                }  else if (lastWidget.getDirection() == 7) {
                    helpCallout.getElement().getStyle().setLeft(widgetRight - Constants.CALLOUT_ARROW_SIZE, Style.Unit.PX);
                    helpCallout.getElement().getStyle().setTop(widgetTop + (widgetHeight / 2) - (calloutHeight / 2), Style.Unit.PX);
                }

                /*
                    Readjust so the callout is on the page
                 */
                if (helpCallout.getElement().getAbsoluteTop() < 0) {
                    helpCallout.getElement().getStyle().setTop(0, Style.Unit.PX);
                } else if (helpCallout.getElement().getAbsoluteBottom() > Window.getClientHeight()) {
                    helpCallout.getElement().getStyle().setTop(Window.getClientHeight() - calloutHeight, Style.Unit.PX);
                }

                if (helpCallout.getElement().getAbsoluteLeft() < 0) {
                    helpCallout.getElement().getStyle().setLeft(0, Style.Unit.PX);
                } else if (helpCallout.getElement().getAbsoluteRight() > Window.getClientWidth()) {
                    helpCallout.getElement().getStyle().setLeft(Window.getClientWidth() - calloutWidth, Style.Unit.PX);
                }



                positionCloseButtonDirect();
            }
        });

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
        showHelpCallout();
    }

    /**
     * When entering the help overlay mode, we initially display the help for the help button.
     */
    private void showHelpCallout() {
        for (final HelpData helpData : helpDatabase.values()) {
            if (helpData.getTopicID() == ServiceConstants.HELP_TOPICS.HELP_MODE.getId()) {
                showCallout(helpData.getWidget());
                return;
            }
        }
    }

    public void hideOverlay() {
        checkState(helpOverlayEnabled, "Overlay is not shown");
        checkState(helpDatabase != null, "If the overlay is being hidden, the help database should not null");

        unstyleHelpWidgets();
        demoteHelpWidgets();
        removeDimmerPanel();
        removeCallout();

        lastWidget = null;

        helpDatabase = null;
        helpOverlayEnabled = false;
    }

    private void removeCallout() {
        if (helpCallout != null) {
            helpCallout.removeFromParent();
            helpCallout = null;
        }
    }

    private void addDimmerPanel() {
        final RootLayoutPanel root = RootLayoutPanel.get();
        root.add(dimmerPanel);
        root.add(mouseLockPanel);
        root.add(close);
        positionCloseButton();
    }

    private void removeDimmerPanel() {
        dimmerPanel.removeFromParent();
        mouseLockPanel.removeFromParent();
        close.removeFromParent();
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

    public Widget getMouseLockPanel() {
        return mouseLockPanel;
    }
}
