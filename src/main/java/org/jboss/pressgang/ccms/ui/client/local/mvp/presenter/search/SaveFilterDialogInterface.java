package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * The interface that defines the filter create/overwrite dialog
 */
public interface SaveFilterDialogInterface {

    /**
     * @return The text box representing the name of the filter.
     */
    TextBox getName();

    /**
     * @return The textarea representing the description of the filter.
     */
    TextArea getDescription();

    /**
     * @return The OK button.
     */
    PushButton getOk();

    /**
     * @return The cancel button.
     */
    PushButton getCancel();

    /**
     * @return The dialog box itself.
     */
    DialogBox getDialogBox();

    /**
     * Clear the dialog box fields.
     */
    void reset();
}
