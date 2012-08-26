package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.PushButton;

/**
 * The base class for all views that display some details of a topic. This class
 * creates and exposes all the common UI elements.
 * 
 * @author Matthew Casperson
 */
abstract public class TopicViewBase extends BaseTemplateView implements TopicViewInterface
{
	private final PushButton fields;
	private final PushButton xml;
	private final PushButton xmlErrors;
	private final PushButton rendered;
	private final PushButton tags;
	private final PushButton save;
	private final PushButton bugs;
	private final PushButton history;

	@Override
	public PushButton getHistory()
	{
		return history;
	}

	@Override
	public PushButton getBugs()
	{
		return bugs;
	}

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
		bugs = UIUtilities.createPushButton(ImageResources.INSTANCE.bugs48(), ImageResources.INSTANCE.bugsDown48(), ImageResources.INSTANCE.bugsHover48(), ImageResources.INSTANCE.bugsDisabled48(), CSSConstants.SPACEDBUTTON);
		history= UIUtilities.createPushButton(ImageResources.INSTANCE.history48(), ImageResources.INSTANCE.historyDown48(), ImageResources.INSTANCE.historyHover48(), ImageResources.INSTANCE.historyDisabled48(), CSSConstants.SPACEDBUTTON);
		
		populateTopActionBar();
	}
	
	/**
	 * This method is called to initialize the buttons that should appear in the top action bar
	 */
	abstract protected void populateTopActionBar();
}
