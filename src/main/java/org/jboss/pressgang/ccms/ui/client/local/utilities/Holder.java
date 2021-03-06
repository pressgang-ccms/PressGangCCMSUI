/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.utilities;

/**
 * Used to work around those situations where you need to set work with a final value that was declared outside an anonymous
 * class.
 *
 * @param <T> The type held by the wrapper.
 * @author Matthew Casperson
 */
public final class Holder<T> {
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
    public T getValue() {
        return this.value;
    }

    /**
     * Set the value being held by the wrapper
     *
     * @param newValue the value being held by the wrapper
     */
    public void setValue(final T newValue) {
        this.value = newValue;
    }
}
