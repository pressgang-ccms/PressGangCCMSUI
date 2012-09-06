package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.PushButton;

abstract public class TagViewBase extends BaseTemplateView implements TagViewInterface
{
	private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
	private final PushButton tagDetails = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagDetails());
	private final PushButton tagProjects = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagProjects());
	private final PushButton tagCategories = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TagCategories());
	
	public PushButton getTagCategories()
	{
		return tagCategories;
	}

	public PushButton getSave()
	{
		return save;
	}

	public PushButton getTagProjects()
	{
		return tagProjects;
	}

	public PushButton getTagDetails()
	{
		return tagDetails;
	}

	public TagViewBase(final String applicationName, final String pageName)
	{
		super(applicationName, pageName);
		
		populateTopActionBar();
	}
	
	/**
	 * This method is called to initialize the buttons that should appear in the
	 * top action bar
	 */
	abstract protected void populateTopActionBar();
}
