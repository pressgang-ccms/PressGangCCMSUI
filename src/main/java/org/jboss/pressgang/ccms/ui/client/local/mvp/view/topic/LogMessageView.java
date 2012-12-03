package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;

 /**
     * The dialog box that presents the list of locales for the user to select from.
     * 
     * @author Matthew Casperson
     */
    public class LogMessageView extends DialogBox implements LogMessageInterface {
        
        /** Used to group the radio buttons */
        private static final String CHANGE_TYPE_GROUP = "ChangeType";
        
        private final FlexTable layout = new FlexTable();
        private final RadioButton minorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
        private final RadioButton majorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
        private final TextArea message = new TextArea(); 
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

        @Override
        public TextArea getMessage() {
            return message;
        }

        @Override
        public RadioButton getMajorChange() {
            return majorChange;
        }

        @Override
        public RadioButton getMinorChange() {
            return minorChange;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public PushButton getOk() {
            return ok;
        }

        public LogMessageView() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.SaveLog());

            layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.Message()));
            layout.setWidget(0, 1, message);
            layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.MinorChange()));
            layout.setWidget(1, 1, minorChange);
            layout.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.MajorChange()));
            layout.setWidget(2, 1, majorChange);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(3, 0, buttonPanel);

            layout.getFlexCellFormatter().setColSpan(3, 0, 2);

            this.add(layout);
            
            reset();
        }

        @Override
        public void reset() {
            this.message.setText("");
            this.minorChange.setValue(true);            
        }
        
        @Override
        public void show()
        {            
            super.show();
            message.setFocus(true);
        }

    }