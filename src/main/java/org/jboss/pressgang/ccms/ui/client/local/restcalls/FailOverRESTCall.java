package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.pressgang.ccms.rest.v1.constants.RESTv1Constants;
import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.systemevents.FailoverEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    private EventBus eventBus;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FailOverRESTCall.class.getName());

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

    public <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, final boolean disableDefaultFailureAction) {
        performRESTCall(restCall, callback, null, disableDefaultFailureAction, new ArrayList<Integer>());
    }

    private <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {

        /*
            The server that we failed to call.
        */
        final ServerDetails serverDetails = ServerDetails.getSavedServer();

        final SuccessCallbackWrapper<T> successCallbackWrapper = new SuccessCallbackWrapper<T>() {
            @Override
            public RemoteCallback<T> getSuccessCallback() {
                return new RemoteCallback<T>() {
                    @Override
                    public void callback(final T retValue) {
                        if (!isTimedout() && !isReturned()) {
                            if (display != null) {
                                display.removeWaitOperation();
                            }

                            callback.success(retValue);
                        }

                        setReturned(true);
                    }
                };
            }
        };

        final FailureCallbackWrapper failureCallbackWrapper = new FailureCallbackWrapper() {
            @Override
            public ErrorCallback getErrorCallback() {
                return new ErrorCallback() {
                    @Override
                    public boolean error(final Message message, final Throwable throwable) {
                        if (!isTimedout() && !isReturned()) {
                            if (throwable instanceof ResponseException) {
                                final ResponseException ex = (ResponseException) throwable;
                                final String responseText = ex.getResponse().getText();
                                final String pressgangHeader = ex.getResponse().getHeader(RESTv1Constants.X_PRESSGANG_VERSION_HEADER);

                                if (pressgangHeader == null) {
                                    /*
                                        The response did not contain the header that should be found in all responses from the
                                        pressgang sever. This means the server is down.
                                     */
                                    LOGGER.info("Failing over due to incorrect headers");
                                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                } else if (ex.getResponse().getStatusCode() != Response.SC_NOT_FOUND) {
                                    /*
                                        A 404 is not necessarily an error, as long as the PressGang header is present.
                                     */
                                    LOGGER.info("Failing over due unrecognised HTTP response");
                                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                } else if (ex.getResponse().getStatusCode() == Response.SC_INTERNAL_SERVER_ERROR || ex.getResponse()
                                        .getStatusCode() == Response.SC_BAD_REQUEST) {
                                    if (!disableDefaultFailureAction) {
                                        final String prefix;
                                        if (ex.getResponse().getStatusCode() == Response.SC_BAD_REQUEST) {
                                            /*
                                                A bad request means invalid input, like a duplicated name. This does not indicate a
                                                failure of the REST server.
                                            */
                                            prefix = PressGangCCMSUI.INSTANCE.InvalidInput();
                                        } else {
                                            /*
                                                An Internal Server Error likely means it is a bug in the server and therefore will have
                                                to have a bug logged, so no point failing over.
                                             */
                                            prefix = PressGangCCMSUI.INSTANCE.InternalServerError();
                                        }
                                        Window.alert(prefix + responseText == null ? "" : ("\n\n" + responseText));
                                    }

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
                                    callback.failed();
                                } else {
                                    /*
                                        Any other possible responses that could happen should fail over is possible otherwise display an
                                        unlnown error message.
                                     */
                                    if (restCall.isRepeatable()) {
                                        LOGGER.info("Failing over due to error");
                                        failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                    } else {
                                        if (!disableDefaultFailureAction) {
                                            Window.alert(PressGangCCMSUI.INSTANCE.UnknownError() + responseText == null ? "" : ("\n\n" + responseText));
                                        }

                                        if (display != null) {
                                            display.removeWaitOperation();
                                        }
                                        callback.failed();
                                    }
                                }
                            } else {
                                if (restCall.isRepeatable()) {
                                    LOGGER.info("Failing over due to error");
                                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                } else {
                                    if (!disableDefaultFailureAction) {
                                        Window.alert(PressGangCCMSUI.INSTANCE.UnknownError());
                                    }

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
                                    callback.failed();
                                }
                            }
                        }

                        setReturned(true);

                        return true;
                    }
                };
            }
        };

        final Timer timeoutMonitor = new Timer() {
            @Override
            public void run() {
                if (!(successCallbackWrapper.isReturned() || failureCallbackWrapper.isReturned())) {
                    /*
                        If this particular rest call can be repeated, mark the old callbacks as timed out
                        and fail over.
                     */
                    if (restCall.isRepeatable()) {
                        successCallbackWrapper.setTimedout(true);
                        failureCallbackWrapper.setTimedout(true);
                        LOGGER.info("Failing over due to timeout");
                        failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                    }
                }
            }
        };

        final RESTInterfaceV1 restInterface = RestClient.create(RESTInterfaceV1.class, successCallbackWrapper.getSuccessCallback(), failureCallbackWrapper.getErrorCallback());

        try {
            /*
                Only add a wait operation if this is the first time we are calling this REST endpoint (i.e.
                failedRESTServers.size() == 0).
             */
            if (display != null && failedRESTServers.size() == 0) {
                display.addWaitOperation();
            }

            restCall.call(restInterface);
            timeoutMonitor.schedule(Constants.REST_CALL_TIMEOUT);
        } catch (@NotNull final Exception ex) {
            if (display != null) {
                display.removeWaitOperation();
            }

            LOGGER.info("Failing over due to exception");
            failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
        }
    }

    /**
     * Switch to another server in the same group if available and try the rest call again.
     * @param failedRESTServers The list of servers that have already failed
     */
    private <T> void failOver(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback,
                              @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction,
                              @NotNull final List<Integer> failedRESTServers, @NotNull final ServerDetails serverDetails) {
        /*
            We know this server has failed.
         */
        failedRESTServers.add(serverDetails.getId());
        /*
            Which group did this server belong to.
        */
        final String serverType = serverDetails.getServerType();

        /*
            Often there are concurrent requests. To save time we need to make a note of any failed servers so the next
            concurrent REST request to fail on the dead node will prioritise nodes that are not known to be down.
         */
        JSONArray recentlyFailedServers = JSONParser.parseStrict(Preferences.INSTANCE.getString(Preferences.FAILED_SERVER, new JSONArray().toString())).isArray();
        if (recentlyFailedServers == null) {
            recentlyFailedServers = new JSONArray();
        }

        /*
            Remember only the current entries
         */
        final JSONArray newRecentlyFailedServers = new JSONArray();
        for (int i = 0, length = recentlyFailedServers.size(); i < length; ++i) {
            final JSONObject failedServer = recentlyFailedServers.get(i).isObject();
            if (failedServer != null) {
                final JSONNumber date = failedServer.get(Constants.FAILED_SERVER_TIME).isNumber();
                final JSONNumber id = failedServer.get(Constants.FAILED_SERVER_ID).isNumber();
                if (date != null &&
                        id != null &&
                        id.doubleValue() != serverDetails.getId() &&
                        new Date().getTime() < date.doubleValue() + Constants.REMEMBER_RECENTLY_FAILED_SERVERS) {
                    newRecentlyFailedServers.set(newRecentlyFailedServers.size(), failedServer);
                }
            }
        }

        /*
            Note the current failure
         */
        final JSONObject newlyFailedServer = new JSONObject();
        newlyFailedServer.put(Constants.FAILED_SERVER_ID, new JSONNumber(serverDetails.getId()));
        newlyFailedServer.put(Constants.FAILED_SERVER_TIME, new JSONNumber(new Date().getTime()));
        newRecentlyFailedServers.set(newRecentlyFailedServers.size(), newlyFailedServer);

        /*
            Save the failures
         */
        Preferences.INSTANCE.saveSetting(Preferences.FAILED_SERVER, newRecentlyFailedServers.toString());

        /*
            Do an initial loop over the available servers, skipping any recently failed servers
         */
        outerloop:
        for (final ServerDetails nextServer : ServerDetails.SERVERS) {
            if (!failedRESTServers.contains(nextServer.getId()) && nextServer.getServerType().equals(serverType)) {
                /*
                    Make sure this server did not recently fail
                 */
                innerloop:
                for (int i = 0, length = newRecentlyFailedServers.size(); i < length; ++i) {
                    final JSONObject failedServer = newRecentlyFailedServers.get(i).isObject();
                    if (failedServer != null) {
                        final JSONNumber date = failedServer.get(Constants.FAILED_SERVER_TIME).isNumber();
                        final JSONNumber id = failedServer.get(Constants.FAILED_SERVER_ID).isNumber();
                        if (date != null && id != null) {
                            if (id.doubleValue() == nextServer.getId()) {
                                continue outerloop;
                            }
                        }
                    }
                }

                Preferences.INSTANCE.saveSetting(Preferences.SERVER, nextServer.getId() + "");
                RestClient.setApplicationRoot(nextServer.getRestEndpoint());

                eventBus.fireEvent(new FailoverEvent(nextServer.getId()));

                performRESTCall(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                return;
            }
        }

        /*
            If we get to here, all servers have recently failed. We now try them again.
         */
        for (final ServerDetails nextServer : ServerDetails.SERVERS) {
            if (!failedRESTServers.contains(nextServer.getId()) && nextServer.getServerType().equals(serverType)) {
                Preferences.INSTANCE.saveSetting(Preferences.SERVER, nextServer.getId() + "");
                RestClient.setApplicationRoot(nextServer.getRestEndpoint());

                eventBus.fireEvent(new FailoverEvent(nextServer.getId()));

                performRESTCall(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                return;
            }
        }

        /*
            There are no more servers left to call, so remove the wait operation and call the failed callback.
         */
        if (!disableDefaultFailureAction) {
            Window.alert(PressGangCCMSUI.INSTANCE.NoServersError());
        }

        if (display != null) {
            display.removeWaitOperation();
        }
        callback.failed();
    }
}
