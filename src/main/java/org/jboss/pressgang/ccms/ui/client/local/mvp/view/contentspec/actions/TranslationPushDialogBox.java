package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.TranslationPushPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

public class TranslationPushDialogBox extends DialogBox implements TranslationPushPresenter.Display {
    private final FlexTable layout = new FlexTable();
    private final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.ProcessPushName());
    private final Label usernameLabel = new Label(PressGangCCMSUI.INSTANCE.TranslationUsername());
    private final Label apiKeyLabel = new Label(PressGangCCMSUI.INSTANCE.TranslationApiKey());
    private final Label serversLabel = new Label(PressGangCCMSUI.INSTANCE.Server());

    private final TextBox name = new TextBox();
    private final TextBox username = new TextBox();
    private final PasswordTextBox apiKey = new PasswordTextBox();
    private final CheckBox contentSpecOnly = new CheckBox(PressGangCCMSUI.INSTANCE.TranslationContentSpecOnly());
    private final ListBox servers = new ListBox();

    private final PushButton start = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Start());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

    public TranslationPushDialogBox() {
        setText(PressGangCCMSUI.INSTANCE.TranslationPush());
        setGlassEnabled(true);

        @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(start);
        buttonPanel.add(cancel);

        layout.setWidget(0, 0, nameLabel);
        layout.setWidget(0, 1, name);
        layout.setWidget(1, 0, serversLabel);
        layout.setWidget(1, 1, servers);
        layout.setWidget(2, 0, usernameLabel);
        layout.setWidget(2, 1, username);
        layout.setWidget(3, 0, apiKeyLabel);
        layout.setWidget(3, 1, apiKey);
        layout.setWidget(4, 0, contentSpecOnly);
        layout.setWidget(5, 0, buttonPanel);

        layout.getFlexCellFormatter().setColSpan(4, 0, 2);
        layout.getFlexCellFormatter().setColSpan(5, 0, 2);
        servers.setWidth("100%");

        name.getElement().setPropertyString("placeholder", PressGangCCMSUI.INSTANCE.EnterOptionalName());

        add(layout);
    }

    @Override
    public TextBox getProcessName() {
        return name;
    }

    @Override
    public TextBox getUsername() {
        return username;
    }

    @Override
    public TextBox getApiKey() {
        return apiKey;
    }

    @Override
    public CheckBox getContentSpecOnly() {
        return contentSpecOnly;
    }

    @Override
    public ListBox getServers() {
        return servers;
    }

    @Override
    public PushButton getStart() {
        return start;
    }

    @Override
    public PushButton getCancel() {
        return cancel;
    }

    @Override
    public DialogBox getDialogBox() {
        return this;
    }

    @Override
    public void reset() {
        name.setText("");
        username.setText("");
        apiKey.setText("");
        servers.clear();
        contentSpecOnly.setEnabled(true);
    }
}
