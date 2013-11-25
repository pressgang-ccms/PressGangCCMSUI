package org.jboss.pressgang.ccms.ui.client.local.callbacks;

/**
 * Quite often the ui needs to know if it is in readonly mode. This can be dependent
 * on whether or not the server is readonly, and that can only be found out with
 * an async call.
 */
public interface ReadOnlyCallback {
    void readonlyCallback(final boolean readOnly);
}
