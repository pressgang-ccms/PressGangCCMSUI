package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SaveFilterDialogInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The dialog box shown when a new filter is created, or an existing one is
 * overwritten.
 */
public class SaveFilterDialog extends DialogBox implements SaveFilterDialogInterface {

    private final FlexTable layout = new FlexTable();

    private final TextArea name = new TextArea();
    private final TextArea description = new TextArea();

    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

    @Override
    public TextArea getName() {
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
        getName().setFocus(true);
    }

}
