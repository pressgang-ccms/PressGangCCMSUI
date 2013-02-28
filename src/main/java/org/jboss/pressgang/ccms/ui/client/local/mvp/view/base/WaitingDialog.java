package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

/**
 * A dialog box to display when the screen is locked.
 *
 * @author Matthew Casperson
 */
public class WaitingDialog extends DialogBox {

    /**
     * The image to display in the waiting dialog.
     */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());

    /**
     * Configures the dialog.
     */
    public WaitingDialog() {
        this.setGlassEnabled(true);
        this.add(spinner);
        this.setText(PressGangCCMSUI.INSTANCE.PleaseWait());
    }
}
