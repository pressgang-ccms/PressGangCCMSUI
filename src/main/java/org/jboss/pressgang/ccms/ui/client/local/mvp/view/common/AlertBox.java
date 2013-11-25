package org.jboss.pressgang.ccms.ui.client.local.mvp.view.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

/**
 * A dialog box to replace the browser alert.
 */
public class AlertBox extends DialogBox {
    private static final AlertBox INSTANCE = new AlertBox();
    private static final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final Label message = new Label();
    private final VerticalPanel verticalPanel = new VerticalPanel();

    private AlertBox() {
        this.setModal(true);
        this.setGlassEnabled(true);
        this.setText(PressGangCCMSUI.INSTANCE.Alert());
        this.add(verticalPanel);
        this.setWidth("300px");

        verticalPanel.add(this.message);
        verticalPanel.add(ok);

        this.ok.addStyleName(CSSConstants.AlertBox.ALERT_BOX_OK);
        this.message.addStyleName(CSSConstants.AlertBox.ALERT_BOX_MESSAGE);
        verticalPanel.addStyleName(CSSConstants.AlertBox.ALERT_BOX_PANEL);

        // this logic should not really be in a view, but as we are replacing
        // the alert box, this functionality is not going to change
        ok.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent clickEvent) {
                AlertBox.this.hide();
                for (final HandlerRegistration handlerRegistration : handlers) {
                    handlerRegistration.removeHandler();
                }
            }
        });
    }

    /**
     * Display a message in a dialog box with an OK button.
     * @param message The message to display
     * @param clickHandler A handler that is called when the ok button is clicked. Once clicked, the handler is removed.
     */
    static public void setMessageAndDisplay(@NotNull final String message, @NotNull final ClickHandler clickHandler) {
        checkState(!INSTANCE.isShowing(), "setMessageAndDisplay() should not be called when the alert is already displayed.");
        handlers.add(INSTANCE.ok.addClickHandler(clickHandler));
        INSTANCE.setMessage(message);
        INSTANCE.center();
    }

    /**
     * Display a message in a dialog box with an OK button.
     * @param message The message to display
     */
    static public void setMessageAndDisplay(@NotNull final String message) {
        INSTANCE.setMessage(message);
        INSTANCE.center();
    }

    static public void setMessage(@NotNull final String message) {
        INSTANCE.message.setText(message);
    }
}
