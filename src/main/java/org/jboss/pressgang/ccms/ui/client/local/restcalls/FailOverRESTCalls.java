package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds a number of preconfigured REST calls that can fail over to another server
 * if the call failed.
 */
public final class FailOverRESTCalls {

    public static <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback) {
        performRESTCall(restCall, callback, false, new ArrayList<Integer>());
    }

    public static <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, final boolean disableDefaultFailureAction) {
        performRESTCall(restCall, callback, disableDefaultFailureAction, new ArrayList<Integer>());
    }

    private static <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {
        final RemoteCallback<T> successCallback = new RemoteCallback<T>() {
            @Override
            public void callback(final T retValue) {
                callback.success(retValue);
            }
        };

        final ErrorCallback errorCallback = new ErrorCallback() {
            @Override
            public boolean error(final Message message, final Throwable throwable) {

                if (throwable instanceof ResponseException) {
                    final ResponseException ex = (ResponseException) throwable;


                    if (ex.getResponse().getHeader(Constants.REST_SERVER_HEADER) == null) {
                        /*
                            If we didn't receive the header, then the response was not from the PressGang REST
                            server.
                        */
                        failOver(restCall, callback, disableDefaultFailureAction, failedRESTServers);
                    } else if (ex.getResponse().getStatusCode() == Response.SC_BAD_REQUEST) {
                        /*
                            A bad request means invalid input, like a duplicated name. This does not indicate a
                            failure of the REST server.
                        */
                        if (!disableDefaultFailureAction) {
                            Window.alert(PressGangCCMSUI.INSTANCE.InvalidInput() + "\n\n" + ex.getResponse().getText());
                        }
                    } else if (ex.getResponse().getStatusCode() != Response.SC_NOT_FOUND) {
                        /*
                            Not found is also not an indication of a failure of the rest server (as long as it also
                            includes the REST_SERVER_HEADER).
                         */
                        failOver(restCall, callback, disableDefaultFailureAction, failedRESTServers);
                    }
                } else {
                    failOver(restCall, callback, disableDefaultFailureAction, failedRESTServers);
                }


               return true;
            }
        };

        final RESTInterfaceV1 restInterface = RestClient.create(RESTInterfaceV1.class, successCallback, errorCallback);

        try {
            restCall.call(restInterface);
        } catch (@NotNull final Exception ex) {
            failOver(restCall, callback, disableDefaultFailureAction, failedRESTServers);
        }
    }

    /**
     * Switch to another server in the same group if available and try the rest call again.
     * @param failedRESTServers The list of servers that have already failed
     */
    private static <T> void failOver(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {
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
                RestClient.setApplicationRoot(nextServer.getRestEndpoint());
                performRESTCall(restCall, callback, disableDefaultFailureAction, failedRESTServers);
                return;
            }
        }

        callback.failed();
    }
}
