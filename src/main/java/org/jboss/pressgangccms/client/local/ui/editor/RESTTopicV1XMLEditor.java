package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class RESTTopicV1XMLEditor extends SimplePanel implements Editor<RESTTopicV1>
{
	public final AceEditor xml = new AceEditor(false);

	public RESTTopicV1XMLEditor(final boolean readOnly)
	{
		this.addStyleName(CSSConstants.TOPICXMLVIEWACEPANEL);
		xml.addStyleName(CSSConstants.TOPICXMLVIEWXMLFIELD);
		
		xml.setReadOnly(readOnly);
		xml.setMode(AceEditorMode.XML);
		xml.setTheme(AceEditorTheme.ECLIPSE);
		
		this.setWidget(xml);	
	}
}
