package org.jboss.pressgang.ccms.ui.client.local.utilities;

/**
 * Used to work around those situations where you need to set work with a final value that was declared outside an anonymous
 * class.
 *
 * @param <T> The type held by the wrapper.
 * @author Matthew Casperson
 */
public class Holder<T> {
    private T value;

    /**
     * Set the value being held by the wrapper
     *
     * @param newValue the value to be held by the wrapper
     */
    public Holder(final T newValue) {
        this.value = newValue;
    }

    /**
     * Default constructor. Does nothing.
     */
    public Holder() {
    }

    /**
     * Get the value being held by the wrapper
     *
     * @return the value being held by the wrapper
     */
    public final T getValue() {
        return this.value;
    }

    /**
     * Set the value being held by the wrapper
     *
     * @param newValue the value being held by the wrapper
     */
    public final void setValue(final T newValue) {
        this.value = newValue;
    }
}
