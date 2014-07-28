/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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
