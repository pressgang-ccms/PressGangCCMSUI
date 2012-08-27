package org.jboss.pressgangccms.client.local.ui.editor.topicview;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
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
	final IntegerBox revision = new IntegerBox(); 
	final TextBox locale = new TextBox();
	final TextBox title = new TextBox();
	final TextArea description = new TextArea();
	
	public RESTTopicV1BasicDetailsEditor(final boolean readOnly)
	{
		super(5, 2);
		
		this.addStyleName(CSSConstants.TOPICVIEWPANEL);
		
		for (int i = 0; i < 5; ++i)
			this.getCellFormatter().addStyleName(i, 0, CSSConstants.TOPICVIEWLABEL);
		
		for (int i = 0; i < 4; ++i)
			this.getCellFormatter().addStyleName(i, 1, CSSConstants.TOPICVIEWDETAIL);
				
		title.setReadOnly(readOnly);
		locale.setReadOnly(readOnly);
		description.setReadOnly(readOnly);
		
		id.setReadOnly(true);
		revision.setReadOnly(true);
		
		id.addStyleName(CSSConstants.TOPICVIEWIDFIELD);
		revision.addStyleName(CSSConstants.TOPICVIEWREVISIONNUMBERFIELD);
		title.addStyleName(CSSConstants.TOPICVIEWTITLEFIELD);
		locale.addStyleName(CSSConstants.TOPICVIEWLOCALEFIELD);
		description.addStyleName(CSSConstants.TOPICVIEWDESCRIPTIONFIELD);
		
		this.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
		this.setWidget(0, 1, id);
		
		this.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
		this.setWidget(1, 1, revision);
		
		this.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
		this.setWidget(2, 1, locale);
		
		this.setWidget(3, 0, new Label(PressGangCCMSUI.INSTANCE.TopicTitle()));
		this.setWidget(3, 1, title);
				
		this.setWidget(4, 0, new Label(PressGangCCMSUI.INSTANCE.TopicDescription()));
		this.getCellFormatter().addStyleName(4, 1, CSSConstants.TOPICVIEWDESCRIPTIONDETAIL);
		this.setWidget(4, 1, description);
	}
}
