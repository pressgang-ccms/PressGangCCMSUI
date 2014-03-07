package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.common.client.api.RemoteCallback;

/**
 * Used to return a callback for a successful rest call.
 */
public abstract class SuccessCallbackWrapper<T> extends CallbackWrapper {
    public abstract RemoteCallback<T> getSuccessCallback();
}
