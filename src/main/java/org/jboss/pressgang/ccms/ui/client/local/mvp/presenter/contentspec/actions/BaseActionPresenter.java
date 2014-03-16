package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.actions;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTProcessInformationV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jetbrains.annotations.NotNull;

public abstract class BaseActionPresenter {
    /**
     * The GWT event bus.
     */
    @Inject
    private EventBus eventBus;
    @Inject
    private FailOverRESTCall failOverRESTCall;

    private RESTServerSettingsV1 serverSettings;

    private List<ActionCompletedCallback<RESTProcessInformationV1>> callbacks = new ArrayList<ActionCompletedCallback<RESTProcessInformationV1>>();

    protected void getServerSettings(@NotNull final ServerSettingsCallback settingsCallback) {
        if (serverSettings == null) {
            FailOverRESTCallDatabase.getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 value) {
                    serverSettings = value;
                    settingsCallback.serverSettingsLoaded(serverSettings);
                }
            }, null, failOverRESTCall);
        } else {
            settingsCallback.serverSettingsLoaded(serverSettings);
        }
    }

    @NotNull
    protected EventBus getEventBus() {
        return eventBus;
    }

    @NotNull
    protected FailOverRESTCall getFailOverRESTCall() {
        return failOverRESTCall;
    }

    protected List<ActionCompletedCallback<RESTProcessInformationV1>> getCallbacks() {
        return callbacks;
    }

    public void addActionCompletedHandler(final ActionCompletedCallback<RESTProcessInformationV1> callback) {
        callbacks.add(callback);
    }

    public void removeActionCompletedHandler(final ActionCompletedCallback<RESTProcessInformationV1> callback) {
        callbacks.remove(callback);
    }

    public void close() {
        getCallbacks().clear();
    }
}
