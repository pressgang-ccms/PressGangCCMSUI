package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;

public class TopicView extends TopicViewBase implements TopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicView";

	/** The GWT Editor Driver */
	private final TopicPresenterDriver driver = GWT.create(TopicPresenterDriver.class);
	
	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}

	public TopicView()
	{
		addActionButton(this.getRendered());		
		addActionButton(this.getXml());
		addActionButton(this.getXmlErrors());
		addActionButton(new Image(ImageResources.INSTANCE.fieldsDown48()));
		addActionButton(this.getSave());
		
		addRightAlignedActionButtonPaddingPanel();
	}
	
	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		/* SearchUIProjectsEditor is a grid */
		final RESTTopicV1BasicDetailsEditor editor = new RESTTopicV1BasicDetailsEditor(false);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(topic);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}


}
