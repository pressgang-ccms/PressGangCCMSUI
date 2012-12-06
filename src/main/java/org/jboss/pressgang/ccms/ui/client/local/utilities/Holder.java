package org.jboss.pressgang.ccms.ui.client.local.utilities;

/**
 * Used to work around those situations where you need to set same value that was declared outside an anonymous class
 * @author Matthew Casperson
 *
 * @param <T>
 */
public class Holder<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public Holder() {
    }

    public Holder(final T value) {
        this.value = value;
    }

}
