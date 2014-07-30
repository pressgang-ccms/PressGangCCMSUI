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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorData;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;
import edu.ycp.cs.dh.acegwt.client.tagdb.XMLElementDB;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.XMLElementDBLoader;

public final class RESTTextContentSpecV1TextEditor extends SimplePanel implements Editor<RESTTextContentSpecV1> {
    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public final AceEditor text;

    public RESTTextContentSpecV1TextEditor(final boolean readOnly, final XMLElementDBLoader XMLElementDBLoader) {

        final XMLElementDB xmlElementDB = XMLElementDBLoader.getXMLElementDB();
        final AceEditorData data = new AceEditorData();
        data.setXMLElementDB(xmlElementDB);
        data.setRestUrl(xmlElementDB.getRestEndpoint());
        text = new AceEditor(false, data, false, true);

        this.getElement().setAttribute(Constants.PRESSGANG_WEBSITES_HELP_OVERLAY_DATA_ATTR, ServiceConstants.HELP_TOPICS.CONTENT_SPEC_TEXT_EDITOR.getId() + "");

        this.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_TEXT_VIEW_ACE_PANEL);
        text.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_TEXT_VIEW_TEXT_FIELD);

        text.setReadOnly(readOnly);
        text.setMode(AceEditorMode.CSP);
        text.setTheme(AceEditorTheme.ECLIPSE);
        text.setUseSoftTabs(true);
        text.setTabSize(2);
        text.enableAutoCodeFolding("Fixed\\s+URL\\s*=\\s*.*?(?=(\\]|,|$))", "");

        this.setWidget(text);
    }
}
