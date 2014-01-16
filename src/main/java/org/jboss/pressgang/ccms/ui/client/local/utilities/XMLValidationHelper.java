package org.jboss.pressgang.ccms.ui.client.local.utilities;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLDoctype;

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
    RESTXMLDoctype getFormat();
}
