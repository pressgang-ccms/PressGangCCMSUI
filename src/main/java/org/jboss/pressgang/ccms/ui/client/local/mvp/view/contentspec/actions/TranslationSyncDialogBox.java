/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions.TranslationSyncPresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

public class TranslationSyncDialogBox extends DialogBox implements TranslationSyncPresenter.Display {
    private final FlexTable layout = new FlexTable();
    private final Label nameLabel = new Label(PressGangCCMSUI.INSTANCE.ProcessSyncName());
    private final Label usernameLabel = new Label(PressGangCCMSUI.INSTANCE.TranslationUsername());
    private final Label apiKeyLabel = new Label(PressGangCCMSUI.INSTANCE.TranslationApiKey());
    private final Label localesLabel = new Label(PressGangCCMSUI.INSTANCE.Locales());
    private final Label serversLabel = new Label(PressGangCCMSUI.INSTANCE.Server());

    private final TextBox name = new TextBox();
    private final TextBox username = new TextBox();
    private final PasswordTextBox apiKey = new PasswordTextBox();
    private final CheckBoxList locales = new CheckBoxList();
    private final Label serversValueLabel = new Label();

    private final PushButton start = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Start());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

    public TranslationSyncDialogBox() {
        setText(PressGangCCMSUI.INSTANCE.TranslationSync());
        setGlassEnabled(true);

        @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
        buttonPanel.add(start);
        buttonPanel.add(cancel);

        int row = 0;
        layout.setWidget(row, 0, nameLabel);
        layout.setWidget(row, 1, name);

        row++;
        layout.setWidget(row, 0, serversLabel);
        layout.setWidget(row, 1, serversValueLabel);

        row++;
        layout.setWidget(row, 0, usernameLabel);
        layout.setWidget(row, 1, username);

        row++;
        layout.setWidget(row, 0, apiKeyLabel);
        layout.setWidget(row, 1, apiKey);

        row++;
        layout.setWidget(row, 0, localesLabel);
        layout.setWidget(row, 1, locales);

        row++;
        layout.setWidget(row, 0, buttonPanel);

        layout.getFlexCellFormatter().setColSpan(row, 0, 2);
        locales.addStyleName(CSSConstants.ContentSpecActionsView.SYNC_LOCALE_LIST_BOX);

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
    public CheckBoxList getLocales() {
        return locales;
    }

    @Override
    public Label getServer() {
        return serversValueLabel;
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
        locales.clear();
        serversValueLabel.setText("");
        username.setText("");
        apiKey.setText("");
    }
}
