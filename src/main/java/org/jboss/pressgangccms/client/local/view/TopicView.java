package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;

public class TopicView extends TopicViewBase implements TopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicView";

	
	/** The GWT Editor Driver */
	private final TopicPresenterDriver driver = GWT.create(TopicPresenterDriver.class);


	public TopicView()
	{
		
	}
	
	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		/* SearchUIProjectsEditor is a grid */
		final RESTTopicV1BasicDetailsEditor editor = new RESTTopicV1BasicDetailsEditor(true);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(topic);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}
}
