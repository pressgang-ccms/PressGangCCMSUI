package org.jboss.pressgang.ccms.ui.client.local.mvp.view.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * A dialog box to replace the browser alert.
 */
public class AlertBox extends DialogBox {
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final Label message = new Label();
    private final VerticalPanel verticalPanel = new VerticalPanel();

    public AlertBox() {
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
            }
        });
    }

    public void setMessageAndDisplay(@NotNull final String message) {
        setMessage(message);
        this.center();
    }

    public void setMessage(@NotNull final String message) {
        this.message.setText(message);
    }
}
