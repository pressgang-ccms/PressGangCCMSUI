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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslationServerV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

public class TranslationPushPresenter extends BaseActionPresenter<RESTProcessInformationV1> {
    @Inject
    private Display display;
    @Inject
    private ProcessWaitingPresenter processWaitingPresenter;
    private RESTTextContentSpecV1 contentSpec;
    private final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    private BaseTemplateViewInterface parentDisplay;

    private final ClickHandler startClickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            // Check a username was entered
            final String username = display.getUsername().getText().trim();
            final String apikey = display.getApiKey().getText().trim();
            final String processName = display.getProcessName().getText().trim();

            if (username.isEmpty() || apikey.isEmpty()) {
                display.getDialogBox().hide();

                // Build the error message
                final StringBuilder message = new StringBuilder();
                if (username.isEmpty()) {
                    message.append(PressGangCCMSUI.INSTANCE.TranslationUsernameMissing());
                }
                if (apikey.isEmpty()) {
                    if (message.length() > 0) {
                        message.append("\n");
                    }
                    message.append(PressGangCCMSUI.INSTANCE.TranslationApiKeyMissing());
                }

                AlertBox.setMessageAndDisplay(message.toString(), new CloseHandler() {
                    @Override
                    public void onClose(CloseEvent event) {
                        display.getDialogBox().center();
                    }
                });

                return;
            }

            // Save the username
            Preferences.INSTANCE.saveSetting(Preferences.TRANSLATION_USERNAME, username);

            // Create the callback
            final RESTCallBack<RESTProcessInformationV1> callback = new RESTCallBack<RESTProcessInformationV1>() {
                @Override
                public void success(@NotNull final RESTProcessInformationV1 retValue) {
                    // Move the completed handlers over to the waiting presenter
                    for (final ActionCompletedCallback<RESTProcessInformationV1> callback : getCallbacks()) {
                        processWaitingPresenter.addActionCompletedHandler(callback);
                    }

                    close();
                    processWaitingPresenter.display(retValue);
                }

                @Override
                public void failed() {
                    // Call the failed method for  the completed handlers
                    for (final ActionCompletedCallback<RESTProcessInformationV1> callback : getCallbacks()) {
                        callback.failure();
                    }

                    close();
                }
            };

            // Start the push
            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.startTranslationPush(contentSpec.getId(), processName,
                    display.getContentSpecOnly().getValue(), display.getDisableCopyTrans().getValue(), username, apikey), callback,
                    parentDisplay);

        }
    };

    public void display(final RESTTextContentSpecV1 contentSpec, final BaseTemplateViewInterface parentDisplay) {
        this.contentSpec = contentSpec;
        this.parentDisplay = parentDisplay;
        bindButtons();
        display.getUsername().setText(Preferences.INSTANCE.getString(Preferences.TRANSLATION_USERNAME, ""));

        // Set the server label
        initServer();
        display.getDialogBox().center();
    }

    protected void initServer() {
        if (contentSpec.getTranslationDetails() != null && contentSpec.getTranslationDetails().getTranslationServer() != null) {
            final RESTTranslationServerV1 translationServer = contentSpec.getTranslationDetails().getTranslationServer();
            display.getServer().setText(translationServer.getName());
        } else {
            display.getServer().setText("");
        }
    }

    @Override
    public void close() {
        display.reset();
        display.getDialogBox().hide();
        for (final HandlerRegistration handlerRegistration : handlers) {
            handlerRegistration.removeHandler();
        }
        contentSpec = null;
        parentDisplay = null;
        super.close();
    }

    protected void bindButtons() {
        handlers.add(display.getStart().addClickHandler(startClickHandler));

        handlers.add(display.getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                close();
            }
        }));
    }

    public Display getDisplay() {
        return display;
    }

    public interface Display {
        TextBox getProcessName();

        TextBox getUsername();

        TextBox getApiKey();

        CheckBox getContentSpecOnly();

        CheckBox getDisableCopyTrans();

        Label getServer();

        PushButton getStart();

        PushButton getCancel();

        DialogBox getDialogBox();

        void reset();
    }
}
