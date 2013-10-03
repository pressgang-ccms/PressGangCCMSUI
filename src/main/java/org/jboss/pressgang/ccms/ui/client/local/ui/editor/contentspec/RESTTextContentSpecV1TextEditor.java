package org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;

public final class RESTTextContentSpecV1TextEditor extends SimplePanel implements Editor<RESTTextContentSpecV1> {
    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public final AceEditor text = new AceEditor(false);

    public RESTTextContentSpecV1TextEditor(final boolean readOnly) {
        this.getElement().setAttribute(Constants.PRESSGANG_WEBSITES_HELP_OVERLAY_DATA_ATTR, ServiceConstants.HELP_TOPICS.CONTENT_SPEC_TEXT_EDITOR.getId() + "");

        this.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_TEXT_VIEW_ACE_PANEL);
        text.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_TEXT_VIEW_TEXT_FIELD);

        text.setReadOnly(readOnly);
        text.setMode(AceEditorMode.CSP);
        text.setTheme(AceEditorTheme.ECLIPSE);
        text.setUseSoftTabs(true);
        text.setTabSize(2);

        this.setWidget(text);
    }
}
