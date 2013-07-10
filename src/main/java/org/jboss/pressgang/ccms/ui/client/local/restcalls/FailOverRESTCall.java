package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.constants.RESTv1Constants;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds a number of preconfigured REST calls that can fail over to another server
 * if the call failed.
 */
@Dependent
public final class FailOverRESTCall {

    /**
     * The GWT event bus.
     */
    @Inject
    private HandlerManager eventBus;

    public FailOverRESTCall () {

    }

    public <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback) {
        performRESTCall(restCall, callback, null, false, new ArrayList<Integer>());
    }

    public <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display) {
        performRESTCall(restCall, callback, display, false, new ArrayList<Integer>());
    }

    public <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction) {
        performRESTCall(restCall, callback, display, disableDefaultFailureAction, new ArrayList<Integer>());
    }

    private <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {
        final RemoteCallback<T> successCallback = new RemoteCallback<T>() {
            @Override
            public void callback(final T retValue) {
                if (display != null) {
                    display.removeWaitOperation();
                }

                callback.success(retValue);
            }
        };

        final ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public boolean error(final Message message, final Throwable throwable) {

                if (throwable instanceof ResponseException) {
                    final ResponseException ex = (ResponseException) throwable;
                    final String responseText = ex.getResponse().getText();

                    if (!responseText.startsWith(RESTv1Constants.ERROR_TEXT_PREFIX)) {
                       // The response text did not include the expected prefix,
                       // which means it was not from the PressGang REST server.
                       failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                    } else if (ex.getResponse().getStatusCode() == Response.SC_BAD_REQUEST) {
                        /*
                            A bad request means invalid input, like a duplicated name. This does not indicate a
                            failure of the REST server.
                        */

                        if (!disableDefaultFailureAction) {
                            Window.alert(PressGangCCMSUI.INSTANCE.InvalidInput() + "\n\n" + responseText.replaceFirst(RESTv1Constants.ERROR_TEXT_PREFIX + " ", ""));
                        }
                    } else {
                        failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                    }
                } else {
                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                }

                if (display != null) {
                    display.removeWaitOperation();
                }

               return true;
            }
        };

        final RESTInterfaceV1 restInterface = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

        try {
            if (display != null) {
                display.addWaitOperation();
            }

            restCall.call(restInterface);
        } catch (@NotNull final Exception ex) {
            if (display != null) {
                display.removeWaitOperation();
            }

            failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
        }
    }

    /**
     * Switch to another server in the same group if available and try the rest call again.
     * @param failedRESTServers The list of servers that have already failed
     */
    private <T> void failOver(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {
        /*
            The server that we failed to call.
         */
        final ServerDetails serverDetails = ServerDetails.getSavedServer();
        /*
            We know this server has failed.
         */
        failedRESTServers.add(serverDetails.getId());
        /*
            Which group did this server belong to.
        */
        final ServerTypes serverType = serverDetails.getServerType();

        for (final ServerDetails nextServer : ServerDetails.SERVERS) {
            if (!failedRESTServers.contains(nextServer.getId()) && nextServer.getServerType().equals(serverType)) {
                Preferences.INSTANCE.saveSetting(Preferences.SERVER, nextServer.getId() + "");
                RestClient.setApplicationRoot(nextServer.getRestEndpoint());

                eventBus.fireEvent(new FailoverEvent(nextServer.getId()));

                performRESTCall(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                return;
            }
        }

        callback.failed();
    }
}
