package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1XMLEditor;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class TopicXMLView extends TopicViewBase implements TopicXMLPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicXMLView";

	/** The GWT Editor Driver */
	private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

	private RESTTopicV1XMLEditor editor;

	private final ToggleButton lineWrap = new ToggleButton(new Image(ImageResources.INSTANCE.lineWrap48()), new Image(ImageResources.INSTANCE.lineWrapDown48()));

	public SimpleBeanEditorDriver getDriver()
	{
		return driver;
	}

	public ToggleButton getLineWrap()
	{
		return lineWrap;
	}

	public AceEditor getEditor()
	{
		return editor.xml;
	}

	public TopicXMLView()
	{
		addActionButton(this.getRendered());
		addActionButton(new Image(ImageResources.INSTANCE.xmlDown48()));
		addActionButton(this.getXmlErrors());
		addActionButton(this.getFields());
		addActionButton(this.getSave());

		lineWrap.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.lineWrapHover48()));
		lineWrap.addStyleName("SpacedElement");

		addRightAlignedActionButtonPaddingPanel();
		addActionButton(lineWrap);
	}

	@Override
	public void initialize(final RESTTopicV1 topic)
	{
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
