package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Defines the callbacks that are available when making a REST call.
 */
public class RESTCallBack<T> {
    private static final Logger LOGGER = Logger.getLogger(RESTCallBack.class.getName());

    /**
     * Called if the REST call was a success.
     * @param value The value returned by the REST service.
     */
    public void success(@NotNull final T value) {
        LOGGER.log(Level.INFO, "REST call succeeded.");
    }

    /**
     * Called if the REST call did not succeed.
     */
    public void failed() {
        LOGGER.log(Level.INFO, "REST call failed.");
    }
}
