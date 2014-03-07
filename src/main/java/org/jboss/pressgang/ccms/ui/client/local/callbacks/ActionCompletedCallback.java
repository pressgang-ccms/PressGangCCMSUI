package org.jboss.pressgang.ccms.ui.client.local.callbacks;

public interface ActionCompletedCallback<T> {
    /**
     * The action completed successfully.
     *
     * @param t The result of the successfully completed action
     */
    void success(T t);

    /**
     * The action failed.
     */
    void failure();
}
