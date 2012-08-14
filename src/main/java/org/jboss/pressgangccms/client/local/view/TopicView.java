package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.editor.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class TopicView extends BaseTemplateView implements TopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "TopicView";
	
	private final PushButton fields;
	private final PushButton xml;
	private final PushButton rendered;
	
	/** The GWT Editor Driver */
	private final TopicPresenterDriver driver = GWT.create(TopicPresenterDriver.class);

	public PushButton getRendered()
	{
		return rendered;
	}

	public PushButton getXml()
	{
		return xml;
	}

	public PushButton getFields()
	{
		return fields;
	}

	public TopicView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TopicView());
		
	    /* Build the action bar icons */
		fields = new PushButton(new Image(ImageResources.INSTANCE.fields48()), new Image(ImageResources.INSTANCE.fieldsDown48()));
		fields.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.fieldsHover48()));
		this.getTopActionPanel().add(this.fields);
		
		xml = new PushButton(new Image(ImageResources.INSTANCE.xml48()), new Image(ImageResources.INSTANCE.xmlDown48()));
		xml.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.xmlHover48()));
		this.getTopActionPanel().add(this.xml);
		
		rendered = new PushButton(new Image(ImageResources.INSTANCE.rendered48()), new Image(ImageResources.INSTANCE.renderedDown48()));
		rendered.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.renderedHover48()));
		this.getTopActionPanel().add(this.rendered);
	}
	
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
	
	public void initializeXML(final RESTTopicV1 topic)
	{
		
	}
}
