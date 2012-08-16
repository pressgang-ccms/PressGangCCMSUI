package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

abstract public class TopicViewBase extends BaseTemplateView implements TopicViewInterface
{
	private final PushButton fields;
	private final PushButton xml;
	private final PushButton rendered;

	@Override
	public PushButton getRendered()
	{
		return rendered;
	}

	@Override
	public PushButton getXml()
	{
		return xml;
	}

	@Override
	public PushButton getFields()
	{
		return fields;
	}
	
	public TopicViewBase()
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
}
