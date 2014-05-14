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
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTLogDetailsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

public class FreezePresenter extends BaseActionPresenter<RESTTextContentSpecV1> {
    @Inject
    private Display display;
    @Inject
    private PreviewDialogBox previewDialogBox;
    private RESTTextContentSpecV1 contentSpec;
    private final List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
    private BaseTemplateViewInterface parentDisplay;

    private final ClickHandler okClickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            // Check a username was entered
            final String username = display.getUsername().getText().trim();
            final boolean createNewSpec = display.getCreateNewSpec().getValue();
            final boolean useLatestRevisions = display.getUseLatestRevisions().getValue();

            if (username.isEmpty()) {
                display.getDialogBox().hide();
                AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.UsernameMissing(), new CloseHandler() {
                    @Override
                    public void onClose(CloseEvent event) {
                        display.getDialogBox().center();
                    }
                });
                return;
            }

            final StringBuilder message = new StringBuilder();
            if (!username.isEmpty()) {
                message.append(username).append(": ");
            }
            message.append(display.getMessage().getText());
            final Integer flag = (int) (display.getMinorChange().getValue() ? RESTLogDetailsV1.MINOR_CHANGE_FLAG_BIT : RESTLogDetailsV1
                    .MAJOR_CHANGE_FLAG_BIT);

            // Create the callback
            final RESTCallBack<RESTTextContentSpecV1> callback = new RESTCallBack<RESTTextContentSpecV1>() {
                @Override
                public void success(@NotNull final RESTTextContentSpecV1 retValue) {
                    final String message;
                    if (createNewSpec) {
                        message = PressGangCCMSUI.INSTANCE.ContentSpecFreezeSuccessWithID() + " " + retValue.getId();
                    } else {
                        message = PressGangCCMSUI.INSTANCE.ContentSpecFreezeSuccess();
                    }

                    display.getDialogBox().hide();
                    AlertBox.setMessageAndDisplay(message);

                    // Call the success method for the completed handlers
                    for (final ActionCompletedCallback<RESTTextContentSpecV1> callback : getCallbacks()) {
                        callback.success(retValue);
                    }

                    close();
                }
            };

            getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                    // Create the snapshot
                    getFailOverRESTCall().performRESTCall(
                            FailOverRESTCallDatabase.freezeContentSpec(contentSpec.getId(), message.toString(), useLatestRevisions,
                                    createNewSpec, flag, serverSettings.getEntities().getUnknownUserId().toString()), callback,
                            parentDisplay);
                }
            });
        }
    };

    private final ClickHandler previewClickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            final boolean createNewSpec = display.getCreateNewSpec().getValue();
            final boolean useLatestRevisions = display.getUseLatestRevisions().getValue();

            // Create the callback
            final RESTCallBack<String> callback = new RESTCallBack<String>() {
                @Override
                public void success(@NotNull final String retValue) {
                    display.getDialogBox().hide();
                    previewDialogBox.setText(retValue);
                    previewDialogBox.getDialogBox().addCloseHandler(new CloseHandler<PopupPanel>() {
                        @Override
                        public void onClose(CloseEvent<PopupPanel> event) {
                            display.getDialogBox().center();
                        }
                    });
                    previewDialogBox.getDialogBox().center();
                }
            };

            getFailOverRESTCall().performRESTCall(
                    FailOverRESTCallDatabase.previewContentSpecSnapshot(contentSpec.getId(), useLatestRevisions, createNewSpec), callback,
                    parentDisplay);
        }
    };

    public void display(final RESTTextContentSpecV1 contentSpec, final BaseTemplateViewInterface parentDisplay) {
        this.contentSpec = contentSpec;
        this.parentDisplay = parentDisplay;
        bindButtons();
        display.getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));
        display.getDialogBox().center();
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
        handlers.add(display.getOk().addClickHandler(okClickHandler));
        handlers.add(display.getPreview().addClickHandler(previewClickHandler));
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

    public interface Display extends LogMessageInterface {
        CheckBox getUseLatestRevisions();

        CheckBox getCreateNewSpec();

        PushButton getPreview();
    }

    public interface PreviewDialogBox {
        PushButton getClose();

        String getText();

        void setText(String text);

        ClosablePopup getDialogBox();
    }
}
