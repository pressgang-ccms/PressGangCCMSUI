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

import com.google.gwt.event.shared.EventBus;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ActionCompletedCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jetbrains.annotations.NotNull;

public abstract class BaseActionPresenter<T> {
    /**
     * The GWT event bus.
     */
    @Inject
    private EventBus eventBus;
    @Inject
    private FailOverRESTCall failOverRESTCall;

    private RESTServerSettingsV1 serverSettings;
    private RESTLocaleCollectionV1 locales;

    private List<ActionCompletedCallback<T>> callbacks = new ArrayList<ActionCompletedCallback<T>>();

    protected void getServerSettings(@NotNull final ServerSettingsCallback settingsCallback) {
        if (serverSettings == null || locales == null) {
            FailOverRESTCallDatabase.getServerSettings(new ServerSettingsCallback() {
                @Override
                public void serverSettingsLoaded(@NotNull RESTServerSettingsV1 value, @NotNull RESTLocaleCollectionV1 localesValue) {
                    serverSettings = value;
                    locales = localesValue;
                    settingsCallback.serverSettingsLoaded(serverSettings, localesValue);
                }
            }, null, failOverRESTCall);
        } else {
            settingsCallback.serverSettingsLoaded(serverSettings, locales);
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

    protected List<ActionCompletedCallback<T>> getCallbacks() {
        return callbacks;
    }

    public void addActionCompletedHandler(final ActionCompletedCallback<T> callback) {
        callbacks.add(callback);
    }

    public void removeActionCompletedHandler(final ActionCompletedCallback<T> callback) {
        callbacks.remove(callback);
    }

    public void close() {
        getCallbacks().clear();
    }
}
