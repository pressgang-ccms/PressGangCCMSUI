package org.jboss.pressgang.ccms.ui.client.local.utilities;

import com.google.gwt.core.client.JavaScriptObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Some utility methods for working with XML
 */
public class XMLUtilities {
    /**
     * Parse the supplied text as XML, and return any errors.
     * @param xml  The XML to be parsed
     * @return Any errors, or null if none were found
     */
    @Nullable
    public static native String getXMLErrors(@NotNull final String xml) /*-{
        var parserError = "parsererror";

        // code for IE
        if ($wnd.ActiveXObject)
        {
            var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async="false";
            xmlDoc.loadXML($doc.all(xml).value);

            if (xmlDoc.parseError.errorCode != 0) {
                var message = "Error Code: " + xmlDoc.parseError.errorCode + "\n";
                message = message + "Error Reason: " + xmlDoc.parseError.reason + "\n";
                message = message + "Error Line: " + xmlDoc.parseError.line;
                return message;
            }
        }
        // code for Mozilla, Firefox, Opera, etc.
        else if ($doc.implementation.createDocument)
        {
            var parser = new DOMParser();
            var xmlDoc = parser.parseFromString(xml, "text/xml");

            if (xmlDoc.getElementsByTagName(parserError).length > 0)
            {
                var message = null;
                var foundH3 = false;
                checkXML = function(node) {
                    var nodeName = node.nodeName;
                    if (nodeName == "h3")
                    {
                        if (foundH3)
                        {
                            return;
                        }
                        foundH3 = true;
                    }
                    if (nodeName == "#text")
                    {
                        if (message == null) {
                            message = node.nodeValue + "\n";
                        } else {
                            message = message + node.nodeValue + "\n";
                        }
                    }

                    var nodeLength = node.childNodes.length;
                    for (var i = 0; i < nodeLength; i++)
                    {
                        checkXML(node.childNodes[i], message, foundH3);
                    }
                }

                checkXML(xmlDoc.getElementsByTagName(parserError)[0]);
                return message;
            }
        }

        return null;
    }-*/;
}
