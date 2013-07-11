package org.jboss.pressgang.ccms.ui.client.local.restcalls;

/**
 * A wrapper class around a timeout method. Used to implement request timeouts on the client side.
 */
public class CallbackWrapper {
    /**
     * true if the callback returned, either as a failure or as a success
     */
    private boolean returned = false;
    /**
     * true if the call that this callback is responding to has been marked as having timed out.
     */
    private boolean timedout = false;

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean isTimedout() {
        return timedout;
    }

    public void setTimedout(boolean timedout) {
        this.timedout = timedout;
    }
}
