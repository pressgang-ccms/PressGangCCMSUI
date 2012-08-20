package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1XMLEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class TopicXMLView extends TopicViewBase implements TopicXMLPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicXMLView";

	/** The GWT Editor Driver */
	private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

	private RESTTopicV1XMLEditor editor;

	private final ToggleButton lineWrap = createToggleButton(ImageResources.INSTANCE.lineWrap48(), ImageResources.INSTANCE.lineWrapDown48(), ImageResources.INSTANCE.lineWrapHover48(), "SpacedButton");
	private final ToggleButton showInvisibles = createToggleButton(ImageResources.INSTANCE.hiddenCharacters48(), ImageResources.INSTANCE.hiddenCharactersDown48(), ImageResources.INSTANCE.hiddenCharactersHover48(), "SpacedButton");

	@Override
	public ToggleButton getShowInvisibles()
	{
		return showInvisibles;
	}

	@Override
	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}

	@Override
	public ToggleButton getLineWrap()
	{
		return lineWrap;
	}

	@Override
	public AceEditor getEditor()
	{
		return editor.xml;
	}

	public TopicXMLView()
	{
		addActionButton(this.getRendered());
		final Image downImage = new Image(ImageResources.INSTANCE.xmlDown48());
		downImage.addStyleName("SpacedButton");
		addActionButton(downImage);
		addActionButton(this.getXmlErrors());
		addActionButton(this.getFields());
		addActionButton(this.getSave());

		addRightAlignedActionButtonPaddingPanel();
		addActionButton(lineWrap);
		addActionButton(showInvisibles);
	}

	@Override
	public void initialize(final RESTTopicV1 topic)
	{
		this.getPanel().addStyleName("TopicXMLViewPanel");
		
		/* SearchUIProjectsEditor is a grid */
		editor = new RESTTopicV1XMLEditor(false);
		/* Initialize the driver with the top-level editor */
		driver.initialize(editor);
		/* Copy the data in the object into the UI */
		driver.edit(topic);
		/* Add the projects */
		this.getPanel().setWidget(editor);
	}
}
