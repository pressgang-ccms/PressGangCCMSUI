package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.FreezePresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.WholeNumbersValidator;
import org.jetbrains.annotations.NotNull;

public class FreezeDialogBox extends DialogBox implements FreezePresenter.Display {
    /**
     * Used to group the radio buttons
     */
    private static final String CHANGE_TYPE_GROUP = "ChangeType";

    private final FlexTable layout = new FlexTable();
    private final RadioButton minorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
    private final RadioButton majorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
    private final TextArea message = new TextArea();
    private final TextBox username = new TextBox();
    private final IntegerBox maxTopicRevision = new IntegerBox();

    private final CheckBox latestRevisions = new CheckBox(PressGangCCMSUI.INSTANCE.UseLatestRevisions());
    private final CheckBox newContentSpec = new CheckBox(PressGangCCMSUI.INSTANCE.FreezeNewContentSpec());

    private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
    private final PushButton preview = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Preview());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

    public FreezeDialogBox() {
        setText(PressGangCCMSUI.INSTANCE.FreezeContentSpec());
        setGlassEnabled(true);
        message.getElement().setId(Constants.ElementIDs.MESSAGE_SAVE_DIALOG.getId());
        username.getElement().setId(Constants.ElementIDs.USERNAME_SAVE_DIALOG.getId());
        maxTopicRevision.getElement().setId(Constants.ElementIDs.MAX_TOPIC_REV_DIALOG.getId());
        minorChange.getElement().setId(Constants.ElementIDs.MINOR_CHANGE_SAVE_DIALOG.getId());
        majorChange.getElement().setId(Constants.ElementIDs.MAJOR_CHANGE_SAVE_DIALOG.getId());
        cancel.getElement().setId(Constants.ElementIDs.CANCEL_SAVE_DIALOG.getId());
        ok.getElement().setId(Constants.ElementIDs.OK_SAVE_DIALOG.getId());

        new WholeNumbersValidator(maxTopicRevision);

        int row = 0;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Message()));
        layout.setWidget(row, 1, message);

        ++row;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.Username()));
        layout.setWidget(row, 1, username);

        ++row;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MaxTopicRevision()));
        layout.setWidget(row, 1, maxTopicRevision);

        ++row;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MinorChange()));
        layout.setWidget(row, 1, minorChange);

        ++row;
        layout.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.MajorChange()));
        layout.setWidget(row, 1, majorChange);

        ++row;
        layout.setWidget(row, 0, newContentSpec);
        layout.getFlexCellFormatter().setColSpan(row, 0, 2);

        ++row;
        layout.setWidget(row, 0, latestRevisions);
        layout.getFlexCellFormatter().setColSpan(row, 0, 2);

        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(preview);
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        ++row;
        layout.setWidget(row, 0, buttonPanel);
        layout.getFlexCellFormatter().setColSpan(row, 0, 2);

        add(layout);

        reset();
    }

    @Override
    public CheckBox getUseLatestRevisions() {
        return latestRevisions;
    }

    @Override
    public CheckBox getCreateNewSpec() {
        return newContentSpec;
    }

    @Override
    public PushButton getPreview() {
        return preview;
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

    @NotNull
    @Override
    public TextArea getMessage() {
        return message;
    }

    @NotNull
    @Override
    public IntegerBox getMaxTopicRevision() {
        return maxTopicRevision;
    }

    @NotNull
    @Override
    public RadioButton getMajorChange() {
        return majorChange;
    }

    @NotNull
    @Override
    public RadioButton getMinorChange() {
        return minorChange;
    }

    @NotNull
    @Override
    public TextBox getUsername() {
        return username;
    }

    @Override
    public void reset() {
        message.setText("");
        minorChange.setValue(true);
        latestRevisions.setValue(false);
        newContentSpec.setValue(false);
        maxTopicRevision.setValue(null);
    }

    @Override
    public void show() {
        super.show();
        message.setFocus(true);
    }
}
