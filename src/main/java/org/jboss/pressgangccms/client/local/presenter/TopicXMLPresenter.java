package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

@Dependent
public class TopicXMLPresenter extends TemplatePresenter
{
	private String topicId;
	
	// Empty interface declaration, similar to UiBinder
	public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLEditor>
	{
	}

	public interface Display extends TopicViewInterface
	{
		@Override
		void initialize(final RESTTopicV1 topic);
		ToggleButton getLineWrap();
		ToggleButton getShowInvisibles();
		AceEditor getEditor();
	}

	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void parseToken(String historyToken)
	{
		// TODO Auto-generated method stub		
	}
}
