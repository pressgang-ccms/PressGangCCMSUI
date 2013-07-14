package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;
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
import java.util.logging.Logger;

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

    private <T> void performRESTCall(@NotNull final RESTCall restCall, @NotNull final RESTCallBack<T> callback, @Nullable final BaseTemplateViewInterface display, final boolean disableDefaultFailureAction, @NotNull final List<Integer> failedRESTServers) {

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
                                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                                } else if (ex.getResponse().getStatusCode() == Response.SC_BAD_REQUEST) {
                                    /*
                                        A bad request means invalid input, like a duplicated name. This does not indicate a
                                        failure of the REST server.
                                    */

                                    if (!disableDefaultFailureAction) {
                                        Window.alert(PressGangCCMSUI.INSTANCE.InvalidInput() + "\n\n" + responseText);
                                    }

                                    if (display != null) {
                                        display.removeWaitOperation();
                                    }
                                    callback.failed();
                                } else if (ex.getResponse().getStatusCode() != Response.SC_NOT_FOUND) {
                                    /*
                                        A 404 is not necessarily an error, as long as the PressGang header is present.
                                     */
                                    LOGGER.info("Failing over due unrecognised HTTP response");
                                    failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
                                }
                            } else {
                                LOGGER.info("Failing over due to error");
                                failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
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
                        failOver(restCall, callback, display, disableDefaultFailureAction, failedRESTServers);
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

        /*
            There are no more servers left to call, so remove the wait operation and call the failed callback.
         */
        if (display != null) {
            display.removeWaitOperation();
        }
        callback.failed();
    }
}
