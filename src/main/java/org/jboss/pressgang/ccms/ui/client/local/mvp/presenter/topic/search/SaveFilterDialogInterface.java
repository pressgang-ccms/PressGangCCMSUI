package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;

/**
 * The interface that defines the filter create/overwrite dialog
 */
public interface SaveFilterDialogInterface {

    TextArea getName();

    TextArea getDescription();

    PushButton getOk();

    PushButton getCancel() ;

    DialogBox getDialogBox();

    void reset();
}
