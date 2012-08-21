package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.PushButton;

abstract public class TopicViewBase extends BaseTemplateView implements TopicViewInterface
{
	private final PushButton fields;
	private final PushButton xml;
	private final PushButton xmlErrors;
	private final PushButton rendered;
	private final PushButton tags;
	private final PushButton save;

	@Override
	public PushButton getTags()
	{
		return tags;
	}

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
		rendered = UIUtilities.createPushButton(ImageResources.INSTANCE.rendered48(), ImageResources.INSTANCE.renderedDown48(), ImageResources.INSTANCE.renderedHover48(), CSSConstants.SPACEDBUTTON);
		xml = UIUtilities.createPushButton(ImageResources.INSTANCE.xml48(), ImageResources.INSTANCE.xmlDown48(), ImageResources.INSTANCE.xmlHover48(), CSSConstants.SPACEDBUTTON);
		xmlErrors = UIUtilities.createPushButton(ImageResources.INSTANCE.attention48(), ImageResources.INSTANCE.attentionDown48(), ImageResources.INSTANCE.attentionHover48(), CSSConstants.SPACEDBUTTON);
		fields = UIUtilities.createPushButton(ImageResources.INSTANCE.fields48(), ImageResources.INSTANCE.fieldsDown48(), ImageResources.INSTANCE.fieldsHover48(), CSSConstants.SPACEDBUTTON);
		save = UIUtilities.createPushButton(ImageResources.INSTANCE.save48(), ImageResources.INSTANCE.saveDown48(), ImageResources.INSTANCE.saveHover48(), ImageResources.INSTANCE.saveDisabled48(), CSSConstants.SPACEDBUTTON);
		tags = UIUtilities.createPushButton(ImageResources.INSTANCE.tag48(), ImageResources.INSTANCE.tagDown48(), ImageResources.INSTANCE.tagHover48(), ImageResources.INSTANCE.tagDisabled48(), CSSConstants.SPACEDBUTTON);
	}
}
