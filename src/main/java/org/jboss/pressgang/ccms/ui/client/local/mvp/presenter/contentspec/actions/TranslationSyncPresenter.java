package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions;

import static com.google.common.base.Preconditions.checkState;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBoxList;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTZanataServerSettingsCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTZanataServerSettingsCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTZanataServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

public class TranslationSyncPresenter extends BaseActionPresenter {
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
            final List<String> locales = display.getLocales().getSelectedItemValues();
            final String serverId = display.getServers().getValue(display.getServers().getSelectedIndex());

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
            Preferences.INSTANCE.saveSetting(Preferences.TRANSLATION_SERVER, serverId);

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

            // Start the sync
            getFailOverRESTCall().performRESTCall(
                    FailOverRESTCallDatabase.startTranslationSync(contentSpec.getId(), serverId, processName, locales, username, apikey),
                    callback, parentDisplay);
        }
    };

    public void display(final RESTTextContentSpecV1 contentSpec, final BaseTemplateViewInterface parentDisplay) {
        this.parentDisplay = parentDisplay;
        this.contentSpec = contentSpec;
        bindButtons();
        display.getUsername().setText(Preferences.INSTANCE.getString(Preferences.TRANSLATION_USERNAME, ""));
        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                // Build up the locale list
                initLocales(new ArrayList(serverSettings.getLocales()));
                initServers(serverSettings.getZanataSettings());

                display.getDialogBox().center();
            }
        });
    }

    protected void initLocales(final List<String> locales) {
        locales.remove(contentSpec.getLocale());
        Collections.sort(locales);

        display.getLocales().clear();
        for (final String locale : locales) {
            display.getLocales().addItem(locale, locale, true);
        }
    }

    protected void initServers(final RESTZanataServerSettingsCollectionV1 zanataSettings) {
        display.getServers().clear();
        final String savedServer = Preferences.INSTANCE.getString(Preferences.TRANSLATION_SERVER, null);
        for (final RESTZanataServerSettingsCollectionItemV1 zanataServerItem : zanataSettings.getItems()) {
            checkState(zanataServerItem.getItem() != null, "The zanata settings collection item should reference a valid entity.");

            final RESTZanataServerSettingsV1 zanataServer = zanataServerItem.getItem();
            display.getServers().addItem("Zanata - " + zanataServer.getName(), zanataServer.getId());

            if (zanataServer.getId().equals(savedServer)) {
                display.getServers().setSelectedIndex(display.getServers().getItemCount() - 1);
            }
        }
    }

    @Override
    public void close() {
        contentSpec = null;
        parentDisplay = null;
        display.reset();
        display.getDialogBox().hide();
        for (final HandlerRegistration handlerRegistration : handlers) {
            handlerRegistration.removeHandler();
        }
        super.close();
    }

    protected void bindButtons() {
        handlers.add(display.getStart().addClickHandler(startClickHandler));

        handlers.add(display.getCancel().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                contentSpec = null;
                display.getDialogBox().hide();
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

        CheckBoxList getLocales();

        ListBox getServers();

        PushButton getStart();

        PushButton getCancel();

        DialogBox getDialogBox();

        void reset();
    }
}
