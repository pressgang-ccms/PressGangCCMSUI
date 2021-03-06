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

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;

/**
 * Instances of this interface are used to pass information back and forth between the class doing
 * the xml validation, and the presenter displaying the topic.
 */
public interface XMLValidationHelper {
    /**
     * @return The ace editor that the xml validator will get the XML from
     */
    AceEditor getEditor();

    /**
     * @return The current error string
     */
    String getError();

    /**
     * @param errorMsg The new error string
     * @param isError whether the message indicates an actual error, or a a "all ok" message.
     */
    void setError(final String errorMsg, final boolean isError);

    /**
     * @return The format of the topic
     */
    RESTXMLFormat getFormat();
}
