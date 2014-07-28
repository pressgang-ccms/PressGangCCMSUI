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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorData;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.tagdb.XMLElementDB;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.XMLElementDBLoader;

public final class RESTTranslatedTopicV1AdditionalXMLEditor extends SimplePanel implements Editor<RESTTranslatedTopicV1> {
    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public final AceEditor translatedAdditionalXML;


    /**
     * @param readOnly      true if the UI created by this editor should be readonly, and false otherwise
     * @param baseTypoJsUrl a reference to the dictionary used by the ACE editor spell checking
     */
    public RESTTranslatedTopicV1AdditionalXMLEditor(final boolean readOnly, final String baseTypoJsUrl,
            final XMLElementDBLoader XMLElementDBLoader) {
        final XMLElementDB xmlElementDB = XMLElementDBLoader.getXMLElementDB();
        final AceEditorData data = new AceEditorData();
        data.setXMLElementDB(xmlElementDB);
        data.setTypoJsBaseUrl(baseTypoJsUrl);
        data.setTypeJsLang("en_US");
        data.setRestUrl(xmlElementDB.getRestEndpoint());
        this.translatedAdditionalXML = new AceEditor(false, data, true, false);

        this.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_ACE_PANEL);
        translatedAdditionalXML.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_XML_FIELD);

        translatedAdditionalXML.setReadOnly(readOnly);
        translatedAdditionalXML.setMode(AceEditorMode.DOCBOOK_45);
        translatedAdditionalXML.setThemeByName(Constants.DEFAULT_THEME);

        this.setWidget(translatedAdditionalXML);
    }
}
