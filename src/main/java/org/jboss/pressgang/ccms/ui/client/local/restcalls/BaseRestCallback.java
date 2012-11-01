package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

/**
 * Most REST calls have the same responses to starting the call, exceptions during the call, and failure of the call. This class
 * wraps up these generic responses.
 * 
 * @param <C> The type of the returned entity
 * @Param <D> The type of the wait view
 * @author kamiller@redhat.com (Katie Miller)
 * 
 */
public abstract class BaseRestCallback<C, D extends BaseTemplateViewInterface> implements RESTCalls.RESTCallback<C> {

    D display;
    SuccessAction<C, D> successAction;

    public BaseRestCallback(D display, SuccessAction<C, D> successAction) {
        this.display = display;
        this.successAction = successAction;
    }

    @Override
    public void begin() {
        display.addWaitOperation();
    }

    @Override
    public void generalException(final Exception e) {
        display.removeWaitOperation();
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
    public void failed(final Message message, final Throwable throwable) {
        try {
            if (throwable instanceof ResponseException) {
                final ResponseException ex = (ResponseException) throwable;
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

    public interface SuccessAction<T, D extends BaseTemplateViewInterface> {
        void doSuccessAction(T retValue, D display);
    }
}
