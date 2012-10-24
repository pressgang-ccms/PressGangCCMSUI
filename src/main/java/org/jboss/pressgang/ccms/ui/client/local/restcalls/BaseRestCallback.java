package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.client.Window;

/**
 * @author kamiller@redhat.com (Katie Miller)
 */
public abstract class BaseRestCallback<T, D extends BaseTemplateViewInterface> implements RESTCalls.RESTCallback<T> {

    D display;
    SuccessAction<T, D> successAction;

    public BaseRestCallback(D display, SuccessAction<T, D> successAction) {
        this.display = display;
        this.successAction = successAction;
    }

    @Override
    public void begin() {
        display.addWaitOperation();
    }

    @Override
    public void generalException(Exception e) {
        display.removeWaitOperation();
    }

    @Override
    public void success(T retValue) {
        try {
            successAction.doSuccessAction(retValue, display);
        } finally {
            display.removeWaitOperation();
        }
    }

    @Override
    public void failed(final Message message, final Throwable throwable) {
        display.removeWaitOperation();
        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
    }

    public interface SuccessAction<T, D extends BaseTemplateViewInterface> {
        void doSuccessAction(T retValue, D display);
    }
}
