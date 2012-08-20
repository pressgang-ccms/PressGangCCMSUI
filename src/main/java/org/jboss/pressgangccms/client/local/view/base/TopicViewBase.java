package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.client.ui.PushButton;

abstract public class TopicViewBase extends BaseTemplateView implements TopicViewInterface
{
	private final PushButton fields;
	private final PushButton xml;
	private final PushButton xmlErrors;
	private final PushButton rendered;
	private final PushButton save;

	@Override
	public PushButton getXmlErrors()
	{
		return xmlErrors;
	}
	
	@Override
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
		rendered = createPushButton(ImageResources.INSTANCE.rendered48(), ImageResources.INSTANCE.renderedDown48(), ImageResources.INSTANCE.renderedHover48(), "SpacedButton");
		xml = createPushButton(ImageResources.INSTANCE.xml48(), ImageResources.INSTANCE.xmlDown48(), ImageResources.INSTANCE.xmlHover48(), "SpacedButton");
		xmlErrors = createPushButton(ImageResources.INSTANCE.attention48(), ImageResources.INSTANCE.attentionDown48(), ImageResources.INSTANCE.attentionHover48(), "SpacedButton");
		fields = createPushButton(ImageResources.INSTANCE.fields48(), ImageResources.INSTANCE.fieldsDown48(), ImageResources.INSTANCE.fieldsHover48(), "SpacedButton");
		save = createPushButton(ImageResources.INSTANCE.save48(), ImageResources.INSTANCE.saveDown48(), ImageResources.INSTANCE.saveHover48(), ImageResources.INSTANCE.saveDisabled48(), "SpacedButton");		
	}
}
