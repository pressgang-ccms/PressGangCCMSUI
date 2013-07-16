package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;
import edu.ycp.cs.dh.acegwt.client.typo.TypoJS;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.TagDBLoader;

public final class RESTTopicV1XMLEditor extends SimplePanel implements Editor<RESTBaseTopicV1<?, ?, ?>> {
    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public final AceEditor xml;

    /**
     * @param readOnly           true if the UI created by this editor should be readonly, and false otherwise
     * @param positiveDictionary a reference to the dictionary used by the ACE editor spell checking
     */
    public RESTTopicV1XMLEditor(
            final boolean readOnly,
            final TypoJS positiveDictionary,
            final TypoJS negativeDictionary,
            final TypoJS negativePhraseDictionary,
            final TagDBLoader tagDBLoader) {
        this.xml = new AceEditor(false, positiveDictionary, negativeDictionary, negativePhraseDictionary, tagDBLoader.getTagDB());

        this.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_ACE_PANEL);
        xml.addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_XML_FIELD);

        xml.setReadOnly(readOnly);
        xml.setMode(AceEditorMode.XML);
        xml.setTheme(AceEditorTheme.ECLIPSE);

        this.setWidget(xml);
    }
}
