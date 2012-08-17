package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

public class RESTTopicV1XMLErrorsEditor extends SimplePanel implements Editor<RESTTopicV1>
{
	public final TextArea xmlErrors = new TextArea();

	public RESTTopicV1XMLErrorsEditor()
	{
		this.addStyleName("TopicXMLErrorsViewPanel");
		xmlErrors.addStyleName("TopicXMLErrorsViewField");
		xmlErrors.setReadOnly(true);
		
		this.setWidget(xmlErrors);	
	}
}
