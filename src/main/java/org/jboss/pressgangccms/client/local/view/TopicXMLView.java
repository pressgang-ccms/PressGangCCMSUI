package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1XMLEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;

public class TopicXMLView extends TopicViewBase implements TopicXMLPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicXMLView";
	
	/** The GWT Editor Driver */
	private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);
	
	private RESTTopicV1XMLEditor editor;
	
	public TopicXMLView()
	{
		
	}
	
	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		/* SearchUIProjectsEditor is a grid */
		editor = new RESTTopicV1XMLEditor(true);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(topic);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}
}
