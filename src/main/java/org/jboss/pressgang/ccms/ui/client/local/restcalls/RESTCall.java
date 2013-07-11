package org.jboss.pressgang.ccms.ui.client.local.restcalls;

import org.jboss.pressgang.ccms.rest.v1.jaxrsinterfaces.RESTInterfaceV1;

/**
 * Used to wrap up a REST call.
 */
public interface RESTCall {
    /**
     * @param restService The service to make a call against.
     */
    void call(final RESTInterfaceV1 restService);

    /**
     * @return true for REST calls that can be repeated (i.e calls that are idempotent), and false otherwise
     */
    boolean isRepeatable();
}
