package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Some utility methods for working with XML
 */
public class XMLUtilities {
    /**
     * Parse the supplied text as XML, and return any errors.
     *
     * @param xml The XML to be parsed
     * @return Any errors, or null if none were found
     */
    @Nullable
    public static native String getXMLErrors(@NotNull final String xml) /*-{
		var parserError = "parsererror";

		// code for IE
		if ($wnd.ActiveXObject) {
			var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async = "false";
			xmlDoc.loadXML($doc.all(xml).value);

			if (xmlDoc.parseError.errorCode != 0) {
				var message = "Error Code: " + xmlDoc.parseError.errorCode + "\n";
				message = message + "Error Reason: " + xmlDoc.parseError.reason + "\n";
				message = message + "Error Line: " + xmlDoc.parseError.line;
				return message;
			}
		}
		// code for Mozilla, Firefox, Opera, etc.
		else if ($doc.implementation.createDocument) {
			var parser = new DOMParser();
			var xmlDoc = parser.parseFromString(xml, "text/xml");

			if (xmlDoc.getElementsByTagName(parserError).length > 0) {
				var message = null;
				var foundH3 = false;
				checkXML = function (node) {
					var nodeName = node.nodeName;
					if (nodeName == "h3") {
						if (foundH3) {
							return;
						}
						foundH3 = true;
					}
					if (nodeName == "#text") {
						if (message == null) {
							message = node.nodeValue + "\n";
						} else {
							message = message + node.nodeValue + "\n";
						}
					}

					var nodeLength = node.childNodes.length;
					for (var i = 0; i < nodeLength; i++) {
						checkXML(node.childNodes[i], message, foundH3);
					}
				}

				checkXML(xmlDoc.getElementsByTagName(parserError)[0]);
				return message;
			}
		}

		return null;
	}-*/;

    /**
     * Strips out the xml preamble. This is usually done before the XML
     * is rendered in the UI
     * @param xml The source xml
     * @return the xml without the preamble
     */
    public static String removeXmlPreamble(@NotNull final String xml) {
        final RegExp regExp = RegExp.compile("^\\s*<\\?[\\s\\S]*?\\?>");
        return regExp.replace(xml, "");
    }

    /**
     * Strips out the doctype preamble in XML. This is usually done before the XML
     * is rendered in the UI
     * @param xml The source xml
     * @return the xml without the doctype preamble
     */
    public static String removeDoctypePreamble(@NotNull final String xml) {
        final RegExp regExp = RegExp.compile("^\\s*<\\s*!DOCTYPE[\\s\\S]*?>");
        return regExp.replace(xml, "");
    }

    public static String removeAllPreamble(@NotNull final String xml) {
        return removeDoctypePreamble(removeXmlPreamble(xml));
    }

    public static Document convertStringToDocument(final String xml) {
        try {
            return XMLParser.parse(xml);
        } catch (Exception e) {
            return null;
        }
    }

    public static String resolveInjections(@NotNull final String xml) {
        final Document doc = convertStringToDocument(xml);
        if (doc == null) {
            return xml;
        } else {
            InjectionResolver.resolveInjections(doc);
            return doc.toString();
        }
    }

    public static List<Comment> getComments(final Document doc) {
        final List<Comment> retValue = new ArrayList<Comment>();
        getComments(doc.getDocumentElement(), retValue);
        return retValue;
    }

    private static void getComments(final Node node, final List<Comment> comments) {
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            final Node childNode = children.item(i);
            if (childNode.getNodeName().equals("#comment")) {
                comments.add((Comment) childNode);
            }
            getComments(childNode, comments);
        }
    }
}
