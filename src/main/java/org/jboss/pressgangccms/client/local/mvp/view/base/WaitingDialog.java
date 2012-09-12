package org.jboss.pressgangccms.client.local.mvp.view.base;

import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;

/**
 * This class holds a dialog box that locks the screen when some process is active (usually loading or saving data).
 * 
 * @author Matthew Casperson
 */
public class WaitingDialog {
    /** The image to display in the waiting dialog */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());
    /** The dialog that is presented when the view is unavailable. */
    private final DialogBox waiting = new DialogBox();
    /** true when the waiting dialog box should be displayed, false otherwise */
    private boolean isWaiting = false;
    /** true when the view is visible, false otherwise */
    private boolean isViewShown = false;
    /** Maintains a count of how many waiting operations are in progress */
    private int waitingCount = 0;

    /**
     * @return true when the view is visible, false otherwise
     */
    public boolean isViewShown() {
        return isViewShown;
    }

    /**
     * @param isViewShown true if the view is visible, false otherwise
     */
    public void setViewShown(boolean isViewShown) {
        this.isViewShown = isViewShown;
        updateDisplay();
    }

    public WaitingDialog() {
        /* Initialize the loading spinner */
        waiting.setGlassEnabled(true);
        waiting.setText(PressGangCCMSUI.INSTANCE.PleaseWait());
        waiting.setWidget(spinner);
    }
    
    public void addWaitOperation()
    {
        ++waitingCount;
    }
    
    public void removeWaitOperation()
    {
        if (waitingCount < 1) throw new IllegalStateException("waitingCount should never be less than one when removeWaitOperation() is called.");
        
        --waitingCount;
    }

    private void updateDisplay() {
        if (!isViewShown || waitingCount == 0) {
            waiting.hide();
        } else if (isViewShown && waitingCount != 0) {
            waiting.center();
            waiting.show();
        }
    }
}
