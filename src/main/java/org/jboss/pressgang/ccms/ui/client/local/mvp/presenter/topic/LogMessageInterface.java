package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;

/**
 * The interface that defines the save log message dialog box
 *
 * @author Matthew Casperson
 */
public interface LogMessageInterface {
    PushButton getCancel();

    PushButton getOk();

    TextArea getMessage();

    RadioButton getMinorChange();

    RadioButton getMajorChange();

    DialogBox getDialogBox();

    /**
     * Reset all the ui elements to default states
     */
    void reset();
}
