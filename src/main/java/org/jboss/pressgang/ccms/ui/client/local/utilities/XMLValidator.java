package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class XMLValidator {
    private final AceEditor editor;
    private final TextArea errors;
    /**
     * true while there is a thread checking the XML
     */
    private boolean checkingXML = false;
    /**
     * The xmllint worker
     */
    private JavaScriptObject worker = null;
    /**
     * The xml validation timeout
     */
    private JavaScriptObject timeout = null;

    public XMLValidator(final AceEditor editor, final TextArea errors) {
        this.editor = editor;
        this.errors = errors;
    }

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    public native void stopCheckingXMLAndCloseThread() /*-{
        try {
            this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkingXML = false;

            if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout != null) {
                $wnd.clearTimeout(this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout);
                this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout = null;
            }

            if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker != null) {
                this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.terminate();
                this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker = null;
            }
        } catch (ex) {
            console.log(ex);
            throw ex;
        }
    }-*/;

    private native void checkXML() /*-{
        var entities = @org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD::getDtdDoctype()();
        var dummyEntities = @org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD::getDummyDtdDoctype()();

        if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker == null) {
            this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker = new Worker('javascript/xmllint/xmllint.js');
            this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.addEventListener('message', function (me) {
                return function (e) {
                    var editor = me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::editor;
                    var errors = me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::errors;
                    var strings = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;
                    var noXmlErrors = strings.@org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::NoXMLErrors()();

                    var theseErrors = e.data;
                    var oldErrors = errors.@com.google.gwt.user.client.ui.TextArea::getText()();

                    // Do additional DocBook validation
                    if (theseErrors == "") {
                        var text = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                        theseErrors = me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::doAdditionalDocBookValidation(Ljava/lang/String;Ljava/lang/String;)(text, entities)
                    }

                    if (theseErrors == "" && oldErrors == "") {
                        errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(noXmlErrors);
                    } else if (oldErrors != theseErrors) {
                        var entitiesLines = entities.indexOf("\n") == -1 ? 0 : entities.match(/\n/g).length;

                        // "Document topic.xml does not validate against docbook45.dtd" is a standard part of the error
                        // message, and is removed before being displayed.
                        var errorMessage = theseErrors.replace("\nDocument topic.xml does not validate against docbook45.dtd", "");

                        var errorLineRegex = /^topic\.xml:(\d+):.*$/gm;
                        var errorLineNumRegex = / line (\d+)/;
                        var match = null;
                        var lineNumbers = [];
                        while (match = errorLineRegex.exec(theseErrors)) {
                            if (match.length >= 1) {
                                var line = parseInt(match[1]) - entitiesLines;
                                // We need to match the line numbers in the editor with those in the topic, which
                                // means subtracting all the entities we added during validation.
                                errorMessage = errorMessage.replace("topic.xml:" + match[1], "topic.xml:" + line);
                                var found = false;
                                for (var i = 0, lineNumbersLength = lineNumbers.length; i < lineNumbersLength; ++i) {
                                    if (lineNumbers[i] == line) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    lineNumbers.push(line - 1);
                                }

                                // Check if there as a line number in the error message
                                match = errorLineNumRegex.exec(match[0])
                                if (match != null && match.length >= 1) {
                                    line = parseInt(match[1]) - entitiesLines;
                                    errorMessage = errorMessage.replace(" line " + match[1], " line " + line);
                                }
                            }
                        }

                        if (errorMessage.length == 0) {
                            errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(noXmlErrors);
                        } else {
                            errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(errorMessage);
                        }

                        if (editor != null) {
                            editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::clearAndAddGutterDecoration([ILjava/lang/String;)(lineNumbers, "xmlerror");
                        }
                    }

                    me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkXML()();
                }
            }(this),
                false);
        }

        if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkingXML) {
            var editor = this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::editor;
            if (editor != null) {
                var text = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                if (text == null) {
                    text = "";
                }
                // Add the doctype that include the standard docbook entities
                text = @org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities::removeAllPreamble(Ljava/lang/String;)(text);
                text = entities + @org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities::resolveInjections(Ljava/lang/String;Ljava/lang/String;)(text, dummyEntities);
                if (text == this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastXML) {
                    this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout = $wnd.setTimeout(function(me) {
                        return function(){

                            me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkXML()();
                        };
                    }(this), 250);
                } else {
                    var dtd = @org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD::getDtd()();
                    if (dtd != "") {
                        this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastXML = text;
                        this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.postMessage({xml: text, schema: dtd});
                    }
                }
            }
        }
    }-*/;

    public String doAdditionalDocBookValidation(final String xml, final String entities) {
        final String xmlWithLineNumbers = XMLUtilities.addLineNumberAttributesToXML(XMLUtilities.removeAllPreamble(xml));
        final Document doc = XMLUtilities.convertStringToDocument(entities + xmlWithLineNumbers);
        final int entitiesLines = entities.indexOf("\n") == -1 ? 0 : entities.split("\n").length;

        final StringBuilder retValue = new StringBuilder();
        if (doc != null) {
            // Validate that the table cols declaration matches the number of entries for each table row
            final List<Node> tables = XMLUtilities.getChildNodes(doc.getDocumentElement(), "table", "informaltable");
            for (final Node table : tables) {
                if (!DocBookUtilities.validateTableRows((Element) table)) {
                    final int line = entitiesLines + Integer.parseInt(((Element) table).getAttribute("pressgangeditorlinenumber")) - 1;
                    retValue.append("topic.xml:" + line + ": element table: validity error : cols declaration doesn't match the " +
                            "number of entry elements\n");
                }
            }

            // Validate the injections are valid
            final Map<Comment, List<String>> injectionErrors = InjectionValidator.checkForInvalidInjections(doc);
            if (!injectionErrors.isEmpty()) {
                for (final Map.Entry<Comment, List<String>> injectionError : injectionErrors.entrySet()) {
                    final String commentText = injectionError.getKey().getNodeValue();
                    retValue.append("\"").append(commentText.trim()).append("\" is possibly an invalid custom Injection Point. Errors:\n");
                    for (final String msg : injectionError.getValue()) {
                        retValue.append("\t- ").append(msg).append("\n");
                    }
                }
            }
        }

        return retValue.toString();
    };

    public boolean isCheckingXML() {
        return checkingXML;
    }

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    public void startCheckingXML() {
        checkingXML = true;
        checkXML();
    }

    /**
     * The worker that continuously checks the XML will stop when checkingXML is set to false.
     */
    public void stopCheckingXML() {
        checkingXML = false;
    }
}
