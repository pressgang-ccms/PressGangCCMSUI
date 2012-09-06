package org.jboss.pressgangccms.client.local.mvp.view.topic;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.TopicXMLErrorsPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;

public class TopicXMLErrorsView extends TopicViewBase implements TopicXMLErrorsPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicXMLErrorsView";

	/** The GWT Editor Driver */
	private final TopicXMLErrorsPresenterDriver driver = GWT.create(TopicXMLErrorsPresenterDriver.class);

	
	@SuppressWarnings("rawtypes")
	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}

	public TopicXMLErrorsView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - " + PressGangCCMSUI.INSTANCE.XMLValidationErrors());
	}
	
	@Override
	protected void populateTopActionBar()
	{
		addActionButton(this.getRenderedSplit());
		addActionButton(this.getRendered());		
		addActionButton(this.getXml());
		addActionButton(this.getXmlErrorsDown());
		addActionButton(this.getFields());
		addActionButton(this.getTags());
		addActionButton(this.getBugs());
		addActionButton(this.getHistory());
		addActionButton(this.getSave());
		
		fixReadOnlyButtons();
		
		addRightAlignedActionButtonPaddingPanel();
	}
	
	@Override
	public void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType)
	{
		this.readOnly = readOnly;
		fixReadOnlyButtons();
		buildSplitViewButtons(splitType);
		
		/* SearchUIProjectsEditor is a grid */
		final RESTTopicV1XMLErrorsEditor editor = new RESTTopicV1XMLErrorsEditor();
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(topic);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	}
	
}
