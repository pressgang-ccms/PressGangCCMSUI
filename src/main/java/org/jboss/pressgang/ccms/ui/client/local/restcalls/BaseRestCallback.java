package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Most REST calls have the same responses to starting the call, exceptions during the call, and failure of the call. This class
 * wraps up these generic responses.
 *
 * @param <C> The type of the returned entity
 * @param <D> The type of the wait view
 * @author kamiller@redhat.com (Katie Miller)
 */
public final class BaseRestCallback<C, D extends BaseTemplateViewInterface> implements RESTCalls.RESTCallback<C> {

    private final D display;
    private final SuccessAction<C, D> successAction;
    @Nullable
    private final FailureAction<D> failureAction;

    private static final Logger LOGGER = Logger.getLogger(BaseRestCallback.class.getName());

    public BaseRestCallback(final D display, final SuccessAction<C, D> successAction) {
        this.display = display;
        this.successAction = successAction;
        this.failureAction = null;
    }

    public BaseRestCallback(final D display, final SuccessAction<C, D> successAction, final FailureAction<D> failureAction) {
        this.display = display;
        this.successAction = successAction;
        this.failureAction = failureAction;
    }

    @Override
    public void begin() {
        display.addWaitOperation();
    }

    @Override
    public void generalException(@NotNull final Exception e) {
        LOGGER.log(Level.SEVERE, "BaseRestCallback.generalException(): A general exception was thrown by the REST operation: " + e.toString());
        LOGGER.log(Level.SEVERE, GWTUtilities.convertExceptionStackToString(e));
        display.removeWaitOperation();

        try {
            if (failureAction != null) {
                failureAction.doFailureAction(display);
            }

        } catch (@NotNull final Exception ex) {
            LOGGER.log(Level.WARNING, "ENTER BaseRestCallback.generalException() threw an exception");
        }
    }

    @Override
    public void success(final C retValue) {
        try {
            successAction.doSuccessAction(retValue, display);
        } finally {
            display.removeWaitOperation();
        }
    }

    @Override
    public void failed(@Nullable final Message message, @Nullable final Throwable throwable) {

        try {
            if (failureAction != null) {
                failureAction.doFailureAction(display);
            }
        } catch (@NotNull final Exception ex) {
            LOGGER.log(Level.WARNING, "ENTER BaseRestCallback.failed() threw an exception");
        }

        try {

            if (message != null) {
                LOGGER.log(Level.SEVERE, message.toString());
            }
            if (throwable != null) {
                LOGGER.log(Level.SEVERE, "BaseRestCallback.generalException(): A general exception was thrown by the REST operation: " + throwable.toString());
                LOGGER.log(Level.SEVERE, GWTUtilities.convertExceptionStackToString(throwable));
            }

            if (throwable instanceof ResponseException) {
                @NotNull final ResponseException ex = (ResponseException) throwable;
                /* A bad request means invalid input, like a duplicated name */
                if (ex.getResponse().getStatusCode() == Response.SC_BAD_REQUEST) {
                    Window.alert(PressGangCCMSUI.INSTANCE.InvalidInput());
                }
            } else {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError() + "\n" + (message != null ? message.toString() : "")
                        + "\n" + (throwable != null ? throwable.toString() : ""));
            }

        } finally {
            display.removeWaitOperation();
        }
    }

    public interface SuccessAction<C, D extends BaseTemplateViewInterface> {
        void doSuccessAction(C retValue, D display);
    }

    public interface FailureAction<D extends BaseTemplateViewInterface> {
        void doFailureAction(D display);
    }
}
