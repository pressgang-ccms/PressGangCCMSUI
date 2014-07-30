/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec.actions;

import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
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
    private final ListBox servers = new ListBox();

    private final PushButton start = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Start());
    private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

    public TranslationSyncDialogBox() {
        setText(PressGangCCMSUI.INSTANCE.TranslationSync());
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
        layout.setWidget(4, 0, localesLabel);
        layout.setWidget(4, 1, locales);
        layout.setWidget(5, 0, buttonPanel);

        layout.getFlexCellFormatter().setColSpan(5, 0, 2);
        locales.addStyleName(CSSConstants.ContentSpecActionsView.SYNC_LOCALE_LIST_BOX);
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
    public CheckBoxList getLocales() {
        return locales;
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
        locales.clear();
        servers.clear();
        username.setText("");
        apiKey.setText("");
    }
}
