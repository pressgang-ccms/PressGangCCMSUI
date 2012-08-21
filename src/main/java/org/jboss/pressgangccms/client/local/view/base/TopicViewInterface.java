package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.PushButton;

public interface TopicViewInterface extends BaseTemplateViewInterface
{
	PushButton getRendered();
	PushButton getXml();
	PushButton getFields();
	PushButton getSave();
	PushButton getXmlErrors();
	PushButton getTags();
	
	SimpleBeanEditorDriver getDriver();
	
	void initialize(final RESTTopicV1 topic);
}
