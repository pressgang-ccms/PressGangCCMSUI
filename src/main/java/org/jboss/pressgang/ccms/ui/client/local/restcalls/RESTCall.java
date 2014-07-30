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
