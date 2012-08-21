package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.TopicTagsPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.tags.TopicTagViewProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.tags.TopicTagViewTagEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class TopicTagsView extends TopicViewBase implements TopicTagsPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicTagsView";
	
	/** The GWT Editor Driver */
	private final TopicTagsPresenterDriver driver = GWT.create(TopicTagsPresenterDriver.class);
	
	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}
	
	public TopicTagsView()
	{
		addActionButton(this.getRendered());		
		addActionButton(this.getXml());
		addActionButton(this.getXmlErrors());
		addActionButton(this.getFields());
		final Image downImage = new Image(ImageResources.INSTANCE.tagDown48());
		downImage.addStyleName(CSSConstants.SPACEDBUTTON);
		addActionButton(downImage);
		addActionButton(this.getSave());
		
		addRightAlignedActionButtonPaddingPanel();
	}

	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		/* Build up a hierarchy of tags */
		final SearchUIProjects projects = new SearchUIProjects(topic.getTags());
		/* SearchUIProjectsEditor is a simple panel */
		final TopicTagViewProjectsEditor editor = new TopicTagViewProjectsEditor(driver, projects);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(projects);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);		
	}
}
