package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorData;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.tagdb.XMLElementDB;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.XMLElementDBLoader;

public final class RESTTopicV1XMLEditor extends SimplePanel implements Editor<RESTBaseTopicV1<?, ?, ?>> {
    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public final AceEditor xml;


    /**
     * @param readOnly      true if the UI created by this editor should be readonly, and false otherwise
     * @param typoJsBaseUrl a reference to the dictionary used by the ACE editor spell checking
     * @param xmlFormat
     */
    public RESTTopicV1XMLEditor(final boolean readOnly, final String typoJsBaseUrl, final XMLElementDBLoader XMLElementDBLoader,
            final RESTXMLFormat xmlFormat) {

        final XMLElementDB xmlElementDB = XMLElementDBLoader.getXMLElementDB();
        final AceEditorData data = new AceEditorData();
        data.setXMLElementDB(xmlElementDB);
        data.setTypoJsBaseUrl(typoJsBaseUrl);
        data.setTypeJsLang("en_US");
        data.setRestUrl(xmlElementDB.getRestEndpoint());
        xml = new AceEditor(false, data, true, false);

        this.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_ACE_PANEL);
        xml.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_XML_FIELD);

        xml.setReadOnly(readOnly);
        if (xmlFormat == RESTXMLFormat.DOCBOOK_50) {
            xml.setMode(AceEditorMode.DOCBOOK_50);
        } else {
            xml.setMode(AceEditorMode.DOCBOOK_45);
        }
        xml.setThemeByName(Constants.DEFAULT_THEME);

        this.setWidget(xml);
    }
}
