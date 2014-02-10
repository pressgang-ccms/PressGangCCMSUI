package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.inject.Inject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.RevisionNodeSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EntityUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;

public class TranslatedTopicRenderedPresenter extends BaseTopicRenderedPresenter<RESTTranslatedTopicV1> {
    public static final String HISTORY_TOKEN = "TranslatedTopicRenderedView";

    public interface Display extends BaseTopicRenderedPresenter.Display {
        CheckBox getMergeAdditionalXML();
    }

    /**
     * The rendered topic view display
     */
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);

        /*
            The history token is expected to be the topic id, optionally with a semicolon
            and then an encoded condition.
         */

        try {
            final String[] tokens = fixedToken.split(";");

            if (tokens.length > 0) {

                /*
                    Set the condition override if it is present.
                 */
                if (tokens.length > 1) {
                    conditionOverride = URL.decode(tokens[1]);
                }

                final Integer translatedTopicId = Integer.parseInt(tokens[0]);

                final RESTCallBack<RESTTranslatedTopicV1> callback = new RESTCallBack<RESTTranslatedTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTranslatedTopicV1 retValue) {
                        displayTopicRendered(retValue, true, true);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTranslatedTopic(translatedTopicId), callback, display);
            }

        } catch (@NotNull final NumberFormatException ex) {

        }
    }

    public void displayTopicRendered(final RESTTranslatedTopicV1 translatedTopic, final boolean readOnly, final boolean showImages) {
        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                try {
                    String xml = cleanXMLAndAddAdditionalContent(translatedTopic.getXml(), showImages);

                    // If we are displaying a revision history topic then merge the revisions together
                    final Boolean merge = getDisplay().getMergeAdditionalXML().getValue();
                    if (merge && EntityUtilities.topicHasTag(translatedTopic, serverSettings.getEntities().getRevisionHistoryTagId())) {
                        xml = processRevisionHistoryXML(translatedTopic.getXmlFormat(), xml, translatedTopic.getTranslatedAdditionalXML());
                    } else if (merge && EntityUtilities.topicHasTag(translatedTopic, serverSettings.getEntities().getAuthorGroupTagId())) {
                        xml = processAuthorGroupXML(translatedTopic.getXmlFormat(), xml, translatedTopic.getTranslatedAdditionalXML());
                    } else {
                        xml = processXML(translatedTopic.getXmlFormat(), xml);
                    }

                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.holdXML(xml), new RESTCallBack<IntegerWrapper>() {
                        @Override
                        public void success(@NotNull final IntegerWrapper value) {
                            getDisplay().displayTopicRendered(value.value, readOnly, showImages);
                        }
                    }, getDisplay(), true);
                } catch (DOMParseException e) {
                    handleXMLError(e);
                }
            }
        });
    }

    protected String processRevisionHistoryXML(final RESTXMLFormat xmlFormat, final String xml, final String additionalXml) {
        // Check that we have some additional xml to process first
        if (isStringNullOrEmpty(additionalXml)) {
            return xml;
        }

        try {
            final Document doc = XMLUtilities.convertStringToDocument(xml);
            final Document additionalDoc = XMLUtilities.convertStringToDocument(additionalXml);

            processXML(xmlFormat, doc);
            processXML(xmlFormat, additionalDoc);

            final NodeList revhistories = doc.getElementsByTagName("revhistory");
            if (revhistories.getLength() > 0) {
                final Element revhistory = (Element) revhistories.item(0);

                // Get the revision nodes
                final NodeList docRevisions = doc.getElementsByTagName("revision");
                final NodeList additionalDocRevisions = additionalDoc.getElementsByTagName("revision");
                final List<Element> revisionNodes = new LinkedList<Element>();
                for (int i = 0; i < docRevisions.getLength(); i++) {
                    revisionNodes.add((Element) docRevisions.item(i));
                }
                for (int i = 0; i < additionalDocRevisions.getLength(); i++) {
                    revisionNodes.add((Element) additionalDocRevisions.item(i));
                }

                // Sort the revisions
                Collections.sort(revisionNodes, new RevisionNodeSort());

                // Insert the additional revisions
                final ListIterator<Element> listIterator = revisionNodes.listIterator(revisionNodes.size());

                Node prevNode = null;
                while (listIterator.hasPrevious()) {
                    final Element revisionNode = listIterator.previous();

                    // The node is from the additional doc
                    if (!revisionNode.getOwnerDocument().equals(doc)) {
                        final Node importedNode = doc.importNode(revisionNode, true);

                        if (prevNode == null) {
                            revhistory.appendChild(importedNode);
                        } else {
                            prevNode.getParentNode().insertBefore(importedNode, prevNode);
                        }
                        prevNode = importedNode;
                    } else {
                        prevNode = revisionNode;
                    }
                }

                return doc.toString();
            }
        } catch (DOMParseException e) {

        }

        return xml;
    }

    protected String processAuthorGroupXML(final RESTXMLFormat xmlFormat, final String xml, final String additionalXml) {
        // Check that we have some additional xml to process first
        if (isStringNullOrEmpty(additionalXml)) {
            return xml;
        }

        try {
            final Document doc = XMLUtilities.convertStringToDocument(xml);
            final Document additionalDoc = XMLUtilities.convertStringToDocument(additionalXml);

            processXML(xmlFormat, doc);
            processXML(xmlFormat, additionalDoc);

            final NodeList authorGroups = doc.getElementsByTagName("authorgroup");
            final NodeList mergeAuthorGroups = additionalDoc.getElementsByTagName("authorgroup");
            if (authorGroups.getLength() > 0 && mergeAuthorGroups.getLength() > 0) {
                final Element authorGroup = (Element) authorGroups.item(0);
                final Node mergeAuthorGroup = doc.importNode(mergeAuthorGroups.item(0), true);

                // Move all the authors/editors/othercredits to the main doc
                final NodeList mergeAuthorGroupChildren = mergeAuthorGroup.getChildNodes();
                while (mergeAuthorGroupChildren.getLength() > 0) {
                    authorGroup.appendChild(mergeAuthorGroupChildren.item(0));
                }

                return doc.toString();
            }
        } catch (DOMParseException e) {

        }

        return xml;
    }
}
