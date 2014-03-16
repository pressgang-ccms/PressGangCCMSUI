package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.errai.enterprise.client.jaxrs.api.RestErrorCallback;

/**
 * Used to return a callback for a failed rest call.
 */
public abstract class FailureCallbackWrapper extends CallbackWrapper {
    public abstract RestErrorCallback getErrorCallback();
}
