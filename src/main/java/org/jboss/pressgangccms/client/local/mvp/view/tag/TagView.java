package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter.TagPresenterDriver;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;

public class TagView extends TagViewBase implements TagPresenter.Display
{
	public static final String HISTORY_TOKEN = "TagView";
	
	/** The GWT Editor Driver */
	private final TagPresenterDriver driver = GWT.create(TagPresenterDriver.class);
	
	private boolean readOnly = false;

	@SuppressWarnings("rawtypes")
	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}
	
	public TagView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());
	}

	protected void populateTopActionBar()
	{
		this.addActionButton(this.getTagDetails());
		this.addActionButton(this.getTagProjects());
		this.addActionButton(this.getSave());
		addRightAlignedActionButtonPaddingPanel();
	}

	public void initialize(final RESTTagV1 tag, final boolean readOnly)
	{
		this.readOnly = readOnly;
		
		/* SearchUIProjectsEditor is a grid */
		final RESTTagV1BasicDetailsEditor editor = new RESTTagV1BasicDetailsEditor(this.readOnly);
	    /* Initialize the driver with the top-level editor */
	    driver.initialize(editor);
	    /* Copy the data in the object into the UI */
	    driver.edit(tag);
	    /* Add the projects */
	    this.getPanel().setWidget(editor);
	} 
}
