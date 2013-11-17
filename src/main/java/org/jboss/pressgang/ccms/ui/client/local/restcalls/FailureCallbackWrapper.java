package org.jboss.pressgang.ccms.ui.client.local.restcalls;


import org.jboss.errai.bus.client.api.ErrorCallback;

/**
 * Used to return a callback for a failed rest call.
 */
public abstract class FailureCallbackWrapper extends CallbackWrapper {
    public abstract ErrorCallback getErrorCallback();
}
