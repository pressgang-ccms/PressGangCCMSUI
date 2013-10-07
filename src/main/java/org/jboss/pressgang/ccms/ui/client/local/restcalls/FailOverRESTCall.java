package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
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
     * true if the application has been closed
     */
    private boolean closed = false;

    /**
     * The last time the failed messages was displayed.
     */
    private static Date lastMessage = null;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FailOverRESTCall.class.getName());

    public FailOverRESTCall () {

    }

    @PostConstruct
    private void postCreate() {
        /*
            Watch for page close events, because these will instantly cancel any pending requests.
         */
        Window.addCloseHandler(new CloseHandler<Window>() {
            @Override
            public void onClose(@NotNull final CloseEvent<Window> event) {
                closed = true;
            }
        });
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

    /**
     * Performs a REST call, which will fail over to a new server if it fails.
     * @param restCall The wrapper around the actual REST call
     * @param callback The callback to call when when the REST call succeeds or fails
     * @param display The display used to show the waiting action, or null if no display should be used
     * @param disableDefaultFailureAction true if no messages should be displayed. This is useful if it is expected that a call might fail.
     * @param failedRESTServers
     * @param <T>
     */
    private <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {

        /*
            The server that we failed to call.
        */
        final ServerDetails serverDetails = ServerDetails.getSavedServer();

        /*
            Fail over after a timeout
         */
        final SuccessCallbackWrapper<T> successCallbackWrapper = new SuccessCallbackWrapper<T>() {
            @Override
            public RemoteCallback<T> getSuccessCallback() {
                return new RemoteCallback<T>() {
                    @Override
                    public void callback(final T retValue) {
                        if (!isTimedout() && !isReturned()) {

                            try {
                                callback.success(retValue);
                            } catch (@NotNull final RuntimeException ex) {
                                LOGGER.info("Success method threw RuntimeException. Rethrowing");
                                throw ex;
                            }

                            if (display != null) {
                                display.removeWaitOperation();
                            }
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
                        /*
                            Make sure the response it not from a request we just didn't wait long enough for.
                         */
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
                                    failOverAndTryAgain(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                } else if (ex.getResponse().getStatusCode() == Response.SC_NOT_FOUND) {
                                    /*
                                        The entity was not found. This is expected if an invalid ID was supplied.
                                     */
                                    if (!disableDefaultFailureAction) {
                                        Window.alert(PressGangCCMSUI.INSTANCE.NotFound());
                                    }

                                    callback.failed();

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
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
                                        Window.alert(prefix + (responseText == null ? "" : ("\n\n" + responseText)));
                                    }

                                    callback.failed();

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
                                } else {
                                    /*
                                        Any other possible responses that could happen should fail over if possible otherwise display an
                                        unknown error message. These events include situations like the database is down but the REST server
                                        is up.
                                     */
                                    if (restCall.isRepeatable()) {
                                        LOGGER.info("Failing over due to error");
                                        failOverAndTryAgain(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                    } else {
                                        if (!disableDefaultFailureAction) {
                                            Window.alert(PressGangCCMSUI.INSTANCE.UnknownError() + (responseText == null ? "" : ("\n\n" +
                                                    responseText)));
                                        }

                                        callback.failed();

                                        if (display != null) {
                                            display.removeWaitOperation();
                                        }
                                    }
                                }
                            } else {
                                if (restCall.isRepeatable()) {
                                    LOGGER.info("Failing over due to error");
                                    failOverAndTryAgain(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
                                } else {
                                    /*
                                        So, what do we do when a non repeatable call failed? In reality most calls
                                        that modify the database are probably repeatable - it's just like hitting
                                        the save icon twice. But that logic is to be determined for each call. If
                                        we have got to this point we just failover the server so the next attempt
                                        by the user won't hit the same failed server again.
                                     */
                                    failOver(failedRESTServers, serverDetails);

                                    if (!disableDefaultFailureAction) {
                                        Window.alert(PressGangCCMSUI.INSTANCE.UnknownError());
                                    }

                                    callback.failed();

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
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
                        failOverAndTryAgain(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);
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
            LOGGER.info("Failing over due to exception");
            failOverAndTryAgain(restCall, callback, display, disableDefaultFailureAction, failedRESTServers, serverDetails);

            if (display != null) {
                display.removeWaitOperation();
            }
        }
    }

    /**
     * Switch to another server in the same group if available and try the rest call again.
     * @param failedRESTServers The list of servers that have already failed
     */
    private <T> void failOverAndTryAgain(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback,
                              @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction,
                              @NotNull final List<Integer> failedRESTServers, @NotNull final ServerDetails serverDetails) {
        /*
            If the application has been closed, just fail any calls.
         */
        if (closed) {
            callback.failed();
        }

        if (failOver(failedRESTServers, serverDetails)) {
             performRESTCall(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
        } else {
            /*
                There are no more servers left to call, so remove the wait operation and call the failed callback.
            */
            if (!disableDefaultFailureAction && (lastMessage == null || new Date().getTime() - lastMessage.getTime() > Constants.REST_SERVER_ERROR_MESSAGE_DELAY)) {
                lastMessage = new Date();
                Window.alert(PressGangCCMSUI.INSTANCE.NoServersError());
            }

            callback.failed();

            if (display != null) {
                display.removeWaitOperation();
            }
        }
    }

    /**
     * Switch to another server in the same group if available.
     * @param failedRESTServers The list of servers that have already failed
     */
    private <T> boolean failOver(@NotNull final List<Integer> failedRESTServers, @NotNull final ServerDetails serverDetails) {



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


                return true;
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


                return true;
            }
        }

        return false;
    }
}
