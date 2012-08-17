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
	private final PushButton xmlErrors;
	private final PushButton rendered;
	private final PushButton save;

	public PushButton getXmlErrors()
	{
		return xmlErrors;
	}
	
	public PushButton getSave()
	{
		return save;
	}

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
		rendered = new PushButton(new Image(ImageResources.INSTANCE.rendered48()), new Image(ImageResources.INSTANCE.renderedDown48()));
		rendered.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.renderedHover48()));
		addActionButton(this.rendered);
		
		xml = new PushButton(new Image(ImageResources.INSTANCE.xml48()), new Image(ImageResources.INSTANCE.xmlDown48()));
		xml.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.xmlHover48()));
		addActionButton(this.xml);
		
		xmlErrors = createPushButton(ImageResources.INSTANCE.attention48(), ImageResources.INSTANCE.attentionDown48(), ImageResources.INSTANCE.attentionHover48());
		addActionButton(this.xmlErrors);
		
		fields = new PushButton(new Image(ImageResources.INSTANCE.fields48()), new Image(ImageResources.INSTANCE.fieldsDown48()));
		fields.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.fieldsHover48()));
		addActionButton(this.fields);
		
		save = new PushButton(new Image(ImageResources.INSTANCE.save48()), new Image(ImageResources.INSTANCE.saveDown48()));
		getSave().getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.saveHover48()));
		getSave().getUpDisabledFace().setImage(new Image(ImageResources.INSTANCE.saveDisabled48()));
		addActionButton(this.getSave());
	}
}
