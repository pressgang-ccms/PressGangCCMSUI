package org.jboss.pressgang.ccms.ui.client.local.restcalls;

/**
 * Defines the callbacks that are available when making a REST call.
 */
public interface RESTCallBack<T> {
    /**
     * Called if the REST call was a success.
     * @param value The value returned by the REST service.
     */
    void success(final T value);

    /**
     * Called if the REST call did not succeed.
     */
    void failed();
}
