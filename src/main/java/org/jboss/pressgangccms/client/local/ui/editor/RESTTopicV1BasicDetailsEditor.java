package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class RESTTopicV1BasicDetailsEditor extends Grid implements Editor<RESTTopicV1>
{
	final IntegerBox id = new IntegerBox();
	final TextBox locale = new TextBox();
	final TextBox title = new TextBox();
	final TextArea description = new TextArea();
	
	public RESTTopicV1BasicDetailsEditor(final boolean readOnly)
	{
		super(4, 2);
		
		this.addStyleName("TopicViewPanel");
		
		for (int i = 0; i < 4; ++i)
			this.getCellFormatter().addStyleName(i, 0, "TopicViewLabel");
		
		for (int i = 0; i < 4; ++i)
			this.getCellFormatter().addStyleName(i, 1, "TopicViewDetail");
		
		id.setReadOnly(readOnly);
		title.setReadOnly(readOnly);
		locale.setReadOnly(readOnly);
		description.setReadOnly(readOnly);
		
		id.addStyleName("TopicViewIDField");
		title.addStyleName("TopicViewTitleField");
		locale.addStyleName("TopicViewLocaleField");
		description.addStyleName("TopicViewDescriptionField");
		
		this.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
		this.setWidget(0, 1, id);
		
		this.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
		this.setWidget(1, 1, locale);
		
		this.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.TopicTitle()));
		this.setWidget(2, 1, title);
		
		this.setWidget(3, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
		this.setWidget(3, 1, description);
	}
}