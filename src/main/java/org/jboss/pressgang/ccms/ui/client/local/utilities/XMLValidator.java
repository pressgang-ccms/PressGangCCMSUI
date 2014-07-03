package org.jboss.pressgang.ccms.ui.client.local.utilities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;

public class XMLValidator {
    private static final String[] DATE_FORMATS = new String[]{"MM-dd-yyyy", "MM/dd/yyyy", "yyyy-MM-dd", "yyyy/MM/dd", "EEE MMM dd yyyy",
            "EEE, MMM dd yyyy", "EEE MMM dd yyyy Z", "EEE dd MMM yyyy", "EEE, dd MMM yyyy", "EEE dd MMM yyyy Z", "yyyyMMdd",
            "yyyyMMdd'T'HHmmss.SSSZ"};

    private final XMLValidationHelper validationHelper;
    private String customEntities = "";
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

    public XMLValidator(final XMLValidationHelper validationHelper) {
        this.validationHelper = validationHelper;
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

    private String getEntities() {
        return DocBookDTD.getDtdDoctype(customEntities);
    }

    private native void checkXML() /*-{
        var entities = this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::getEntities()();
        var strings = @org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::INSTANCE;

        if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker == null) {
            this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker = new Worker('javascript/xmllint/xmllint.js');
            this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.addEventListener('message', function (me) {
                return function (e) {
                    var helper = me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::validationHelper;
                    var editor = helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::getEditor()();
                    var noXmlErrors = strings.@org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI::NoXMLErrors()();

                    var theseErrors = e.data;
                    var oldErrors = helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::getError()();

                    // Do additional DocBook validation
                    if (theseErrors == "") {
                        var text = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                        var format = helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::getFormat()();
                        theseErrors = me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::doAdditionalDocBookValidation(Ljava/lang/String;Ljava/lang/String;Lorg/jboss/pressgang/ccms/rest/v1/entities/enums/RESTXMLFormat;)(text, entities, format);
                    }

                    if (theseErrors == "" && oldErrors == "") {
                        helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::setError(Ljava/lang/String;Z)(noXmlErrors, false);
                    } else if (oldErrors != theseErrors) {
                        var entitiesLines = entities.indexOf("\n") == -1 ? 0 : entities.match(/\n/g).length;

                        // "Document topic.xml does not validate against docbook??.dtd" is a standard part of the error
                        // message, and is removed before being displayed.
                        var errorMessage = theseErrors.replace("\nDocument topic.xml does not validate against schemas/docbook45.dtd", "");
                        errorMessage = errorMessage.replace("\nDocument topic.xml does not validate against schemas/docbook50.dtd", "");

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

                                // Check if there is a line number in the error message
                                match = errorLineNumRegex.exec(match[0])
                                if (match != null && match.length >= 1) {
                                    line = parseInt(match[1]) - entitiesLines;
                                    errorMessage = errorMessage.replace(" line " + match[1], " line " + line);
                                }
                            }
                        }

                        if (errorMessage.length == 0) {
                            helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::setError(Ljava/lang/String;Z)(noXmlErrors, false);
                        } else {
                            helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::setError(Ljava/lang/String;Z)(errorMessage, true);
                        }

                        if (editor != null) {
                            editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::clearAndAddGutterDecoration([ILjava/lang/String;)(lineNumbers, "xmlerror");
                        }
                    }

                    // Add a slight pause to allow the GC time to cleanup since the validator uses a lot of non-reusable memory
                    me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout = $wnd.setTimeout(function(me) {
                        return function(){
                            me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkXML()();
                        };
                    }(me), 250)
                }
            }(this),
                false);
        }

        if (this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkingXML) {
            var helper = this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::validationHelper;
            var editor = helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::getEditor()();
            if (editor != null) {
                var text = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
                if (text == null) {
                    text = "";
                }

                // get the format of the topic
                var format = helper.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidationHelper::getFormat()();
                var formatName = format.@org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat::name()();

                // if there has been no change to the xml and the format, don't revalidate
                if (text == this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastXML &&
                    formatName == this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastFormat) {
                    this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::timeout = $wnd.setTimeout(function(me) {
                        return function(){
                            me.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::checkXML()();
                        };
                    }(this), 250);
                } else {
                    this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastXML = text;
                    this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.lastFormat = formatName;

                    // Add the doctype that include the standard docbook entities
                    text = @org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities::removeAllPreamble(Ljava/lang/String;)(text);
                    // resolve injections and append the xml entities, which are common to docbook 4 and 5
                    // see (http://www.sagehill.net/docbookxsl/Db5Entities.html)
                    text = entities + @org.jboss.pressgang.ccms.ui.client.local.utilities.InjectionResolver::resolveInjections(Lorg/jboss/pressgang/ccms/rest/v1/entities/enums/RESTXMLFormat;Ljava/lang/String;)(format, text);

                    if (format == @org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat::DOCBOOK_45) {
                        this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.postMessage({xml: text, docbook4: true});
                    } else if (format == @org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat::DOCBOOK_50) {
                        text = @org.jboss.pressgang.ccms.ui.client.local.utilities.DocBookUtilities::addDocBook50Namespaces(Ljava/lang/String;)(text);
                        this.@org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator::worker.postMessage({xml: text, docbook5: true});
                    }
                }
            }
        }
    }-*/;

    public String doAdditionalDocBookValidation(final String xml, final String entities, final RESTXMLFormat format) {
        final String fixedXML;
        if (format == RESTXMLFormat.DOCBOOK_50) {
            fixedXML = DocBookUtilities.addDocBook50Namespaces(XMLUtilities.removeAllPreamble(xml));
        } else {
            fixedXML = XMLUtilities.removeAllPreamble(xml);
        }
        final String xmlWithLineNumbers = XMLUtilities.addLineNumberAttributesToXML(fixedXML);
        final int numEntityLines = entities.indexOf("\n") == -1 ? 0 : entities.split("\n").length;
        Document doc = null;
        try {
            doc = XMLUtilities.convertStringToDocument(entities + xmlWithLineNumbers);
        } catch (DOMParseException e) {

        }

        final StringBuilder retValue = new StringBuilder();
        if (doc != null) {
            // Make sure nested sections aren't used
            if (doc.getDocumentElement().getNodeName().equalsIgnoreCase("section")) {
                final NodeList subSections = doc.getDocumentElement().getElementsByTagName("section");
                if (subSections.getLength() > 0) {
                    for (int i = 0; i < subSections.getLength(); i++) {
                        final int line = numEntityLines + getLineNumberFromElement((Element) subSections.item(i));
                        retValue.append("topic.xml:" + line + ": element section: validity error : Nested sections cannot be " +
                                "used in topics. Please consider breaking the content into multiple topics\n");
                    }
                }
            }

            // Validate that the table cols declaration matches the number of entries for each table row
            final List<Node> tables = XMLUtilities.getChildNodes(doc.getDocumentElement(), "table", "informaltable");
            for (final Node table : tables) {
                if (!DocBookUtilities.validateTableRows((Element) table)) {
                    final int line = numEntityLines + getLineNumberFromElement((Element) table);
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

            // If the root node is an info node, then make sure <title>, <subtitle> or <titleabbrev> cannot be used.
            if (doc.getDocumentElement().getNodeName().equalsIgnoreCase("info")
                    || doc.getDocumentElement().getNodeName() .equalsIgnoreCase("sectioninfo")) {
                final List<Node> invalidElements = XMLUtilities.getDirectChildNodes(doc.getDocumentElement(), "title", "subtitle",
                        "titleabbrev");
                for (final Node invalidElement : invalidElements) {
                    final int line = numEntityLines + getLineNumberFromElement((Element) invalidElement);
                    retValue.append("topic.xml:" + line + ": element " + invalidElement.getNodeName() + " cannot be used in an info " +
                            "topic\n");
                }
            }

            // Check that the revision history has valid numbers and dates
            final NodeList revhistories = doc.getElementsByTagName("revhistory");
            if (revhistories.getLength() > 0) {
                validateRevisionHistory(retValue, doc, numEntityLines);
            }
        }

        return retValue.toString();
    }

    /**
     * Validates a Revision History XML DOM Document to ensure that the content is valid for use with Publican.
     *
     * @param doc         The DOM Document that represents the XML that is to be validated.
     * @return Null if there weren't any errors otherwise an error message that states what is wrong.
     */
    public static void validateRevisionHistory(final StringBuilder messages, final Document doc, final int entitiesLines) {

        // Find each <revnumber> element and make sure it matches the publican regex
        final NodeList revisions = doc.getElementsByTagName("revision");
        Date previousDate = null;
        for (int i = 0; i < revisions.getLength(); i++) {
            final Element revision = (Element) revisions.item(i);
            final NodeList revnumbers = revision.getElementsByTagName("revnumber");
            final Element revnumber = revnumbers.getLength() == 1 ? (Element) revnumbers.item(0) : null;
            final NodeList dates = revision.getElementsByTagName("date");
            final Element date = dates.getLength() == 1 ? (Element) dates.item(0) : null;

            // Make sure the rev number is valid and the order is correct
            if (revnumber != null && !XMLUtilities.getNodeText(revnumber).matches("^([0-9.]*)-([0-9.]*)$")) {
                final int line = entitiesLines + getLineNumberFromElement(revnumber);
                messages.append(
                        "topic.xml:" + line + ": element " + revnumber.getNodeName() + ": validity error : revnumber must match \"^([0-9" +
                                ".]*)-([0-9.]*)$\"\n");
            } else if (revnumber == null) {
                final int line = entitiesLines + getLineNumberFromElement(revision);
                messages.append("topic.xml:" + line + ": element " + revision.getNodeName() + ": validity error : missing <revnumber> " +
                        "element\n");
            }

            // Check the dates are in chronological order
            if (date != null) {
                try {
                    final Date revisionDate = DateUtilities.parseDateStrictly(DateUtilities.cleanDate(XMLUtilities.getNodeText(date)),
                            DATE_FORMATS);
                    if (previousDate != null && revisionDate.after(previousDate)) {
                        final int line = entitiesLines + getLineNumberFromElement(date);
                        messages.append("topic.xml:" + line + ": element " + date.getNodeName() + ": validity error : the date is not" +
                                " in descending chronological order\n");
                        return;
                    }

                    previousDate = revisionDate;
                } catch (Exception e) {
                    final int line = entitiesLines + getLineNumberFromElement(date);
                    messages.append("topic.xml:" + line + ": element " + date.getNodeName() + ": validity error : either the date is invalid, or it is incorrectly formatted\n");
                    return;
                }
            } else {
                final int line = entitiesLines + getLineNumberFromElement(revision);
                messages.append("topic.xml:" + line + ": element " + revision.getNodeName() + ": validity error : missing <date> " +
                        "element\n");
            }
        }
    }

    protected static Integer getLineNumberFromElement(final Element element) {
        return Integer.parseInt(((Element) element).getAttribute("pressgangeditorlinenumber")) - 1;
    }

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

    public String getCustomEntities() {
        return customEntities;
    }

    public void setCustomEntities(String customEntities) {
        this.customEntities = customEntities;
    }
}
