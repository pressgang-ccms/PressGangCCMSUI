package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SaveFilterDialogInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The dialog box shown when a new filter is created, or an existing one is
 * overwritten.
 */
public class SaveFilterDialog extends DialogBox implements SaveFilterDialogInterface {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SaveFilterDialog.class.getName());

    /**
     * The table that holds the various UI elements.
     */
    private final FlexTable layout = new FlexTable();

    /**
     * The textbox representing the name of the filter.
     */
    private final TextBox name = new TextBox();
    /**
     * The textarea representing the description of the filter.
     */
    private final TextArea description = new TextArea();
    /**
     * The OK button.
     */
    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    /**
     * The cancel button.
     */
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());


    @Override
    public TextBox getName() {
        return name;
    }

    @Override
    public TextArea getDescription() {
        return description;
    }

    @Override
    public PushButton getOk() {
        return ok;
    }

    @Override
    public PushButton getCancel() {
        return cancel;
    }

    @Override
    public DialogBox getDialogBox() {
        return this;
    }

    public SaveFilterDialog() {
        this.setGlassEnabled(true);
        this.setText(PressGangCCMSUI.INSTANCE.SaveLog());

        int row = 0;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.FilterName()));
        layout.setWidget(row, 1, name);

        ++row;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.FilterDescription()));
        layout.setWidget(row, 1, description);

        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(cancel);
        buttonPanel.add(ok);

        ++row;
        layout.setWidget(row, 0, buttonPanel);
        layout.getFlexCellFormatter().setColSpan(row, 0, 2);

        this.add(layout);
    }

    @Override
    public void show()
    {
        super.show();

        try {
            LOGGER.log(Level.INFO, "ENTER SaveFilterDialog.show()");

            reset();
            super.center();

            name.setFocus(true);
        } finally {
            LOGGER.log(Level.INFO, "EXIT SaveFilterDialog.show()");
        }
    }

    @Override
    public void reset()
    {
        this.name.setValue("");
        this.description.setValue("");
    }

}
