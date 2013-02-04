package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

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

            int row = 0;            
            layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Message()));
            layout.setWidget(row, 1, message);
            
            ++row;
            layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MinorChange()));
            layout.setWidget(row, 1, minorChange);
            
            ++row;
            layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MajorChange()));
            layout.setWidget(row, 1, majorChange);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            ++row;
            layout.setWidget(row, 0, buttonPanel);
            layout.getFlexCellFormatter().setColSpan(row, 0, 2);

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