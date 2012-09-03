package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.preferences.Preferences;
import org.jboss.pressgangccms.client.local.preferences.Preferences.ButtonView;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
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
	
	private final Label fieldsDown;
	private final Label xmlDown;
	private final Label xmlErrorsDown;
	private final Label renderedDown;
	private final Label tagsDown;
	private final Label bugsDown;
	private final Label historyDown;

	private final FlexTable renderedSplitViewMenu = new FlexTable();
	private final PushButton renderedSplit;
	private final PushButton renderedNoSplit;
	private final PushButton renderedVerticalSplit;
	private final PushButton renderedHorizontalSplit;
	private final Label renderedNoSplitDown;
	private final Label renderedVerticalSplitDown;
	private final Label renderedHorizontalSplitDown;
	private final PushButton renderedSplitClose;
	private final PushButton renderedSplitOpen;

	protected boolean readOnly = false;
	
	public Label getFieldsDown()
	{
		return fieldsDown;
	}

	public Label getXmlDown()
	{
		return xmlDown;
	}

	public Label getXmlErrorsDown()
	{
		return xmlErrorsDown;
	}

	public Label getRenderedDown()
	{
		return renderedDown;
	}

	public Label getTagsDown()
	{
		return tagsDown;
	}

	public Label getBugsDown()
	{
		return bugsDown;
	}

	public Label getHistoryDown()
	{
		return historyDown;
	}
	
	public PushButton getRenderedSplitOpen()
	{
		return renderedSplitOpen;
	}

	public PushButton getRenderedHorizontalSplit()
	{
		return renderedHorizontalSplit;
	}

	public FlexTable getRenderedSplitViewMenu()
	{
		return renderedSplitViewMenu;
	}

	public PushButton getRenderedSplitClose()
	{
		return renderedSplitClose;
	}

	public PushButton getRenderedVerticalSplit()
	{
		return renderedVerticalSplit;
	}

	public PushButton getRenderedNoSplit()
	{
		return renderedNoSplit;
	}

	public PushButton getRenderedSplit()
	{
		return renderedSplit;
	}

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

	public TopicViewBase(final String applicationName, final String pageName)
	{
		super(applicationName, pageName);
		
		renderedSplitViewMenu.addStyleName(CSSConstants.RENDEREDSPLITVIEWMENUTABLE);

		/* Build the action bar icons */
		renderedSplit = UIUtilities.createPushButton(ImageResources.INSTANCE.renderedSplit48(), ImageResources.INSTANCE.renderedSplit48(), ImageResources.INSTANCE.renderedSplit48(), PressGangCCMSUI.INSTANCE.RenderedPane());
		rendered = UIUtilities.createPushButton(ImageResources.INSTANCE.rendered48(), ImageResources.INSTANCE.renderedDown48(), ImageResources.INSTANCE.renderedHover48(), PressGangCCMSUI.INSTANCE.RenderedView());
		xml = UIUtilities.createPushButton(ImageResources.INSTANCE.xml48(), ImageResources.INSTANCE.xmlDown48(), ImageResources.INSTANCE.xmlHover48(), PressGangCCMSUI.INSTANCE.XMLEditing());
		xmlErrors = UIUtilities.createPushButton(ImageResources.INSTANCE.attention48(), ImageResources.INSTANCE.attentionDown48(), ImageResources.INSTANCE.attentionHover48(), PressGangCCMSUI.INSTANCE.XMLValidationErrors());
		fields = UIUtilities.createPushButton(ImageResources.INSTANCE.fields48(), ImageResources.INSTANCE.fieldsDown48(), ImageResources.INSTANCE.fieldsHover48(), PressGangCCMSUI.INSTANCE.Properties());
		save = UIUtilities.createPushButton(ImageResources.INSTANCE.save48(), ImageResources.INSTANCE.saveDown48(), ImageResources.INSTANCE.saveHover48(), ImageResources.INSTANCE.saveDisabled48(), PressGangCCMSUI.INSTANCE.Save());
		tags = UIUtilities.createPushButton(ImageResources.INSTANCE.tag48(), ImageResources.INSTANCE.tagDown48(), ImageResources.INSTANCE.tagHover48(), ImageResources.INSTANCE.tagDisabled48(), PressGangCCMSUI.INSTANCE.TopicTags());
		bugs = UIUtilities.createPushButton(ImageResources.INSTANCE.bugs48(), ImageResources.INSTANCE.bugsDown48(), ImageResources.INSTANCE.bugsHover48(), ImageResources.INSTANCE.bugsDisabled48(), PressGangCCMSUI.INSTANCE.Bugs());
		history = UIUtilities.createPushButton(ImageResources.INSTANCE.history48(), ImageResources.INSTANCE.historyDown48(), ImageResources.INSTANCE.historyHover48(), ImageResources.INSTANCE.historyDisabled48(), PressGangCCMSUI.INSTANCE.Revisions());
		
		fieldsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Properties());
		xmlDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicXML());
		xmlErrorsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.XMLValidationErrors());
		renderedDown= UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.RenderedView());
		tagsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicTags());
		bugsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Bugs());
		historyDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicRevision());

		renderedSplitOpen = UIUtilities.createPushButton(ImageResources.INSTANCE.renderedSplit48(), ImageResources.INSTANCE.renderedSplit48(), ImageResources.INSTANCE.renderedSplit48(), PressGangCCMSUI.INSTANCE.RenderedPane());
		renderedNoSplit = UIUtilities.createPushButton(ImageResources.INSTANCE.renderedNoSplit48(), ImageResources.INSTANCE.renderedNoSplitDown48(), ImageResources.INSTANCE.renderedNoSplitHover48(), ImageResources.INSTANCE.renderedNoSplitDisabled48(), PressGangCCMSUI.INSTANCE.NoSplit());
		renderedNoSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.NoSplit());
		renderedVerticalSplit = UIUtilities.createPushButton(ImageResources.INSTANCE.renderedVerticalSplit48(), ImageResources.INSTANCE.renderedVerticalSplitDown48(), ImageResources.INSTANCE.renderedVerticalSplitHover48(), ImageResources.INSTANCE.renderedVerticalSplitDisabled48(), PressGangCCMSUI.INSTANCE.VerticalSplit());
		renderedVerticalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.VerticalSplit());
		renderedHorizontalSplit = UIUtilities.createPushButton(ImageResources.INSTANCE.renderedHorizontalSplit48(), ImageResources.INSTANCE.renderedHorizontalSplitDown48(), ImageResources.INSTANCE.renderedHorizontalSplitHover48(), ImageResources.INSTANCE.renderedHorizontalSplitDisabled48(), PressGangCCMSUI.INSTANCE.HorizontalSplit());
		renderedHorizontalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.HorizontalSplit());
		renderedSplitClose = UIUtilities.createPushButton(ImageResources.INSTANCE.cross48(), ImageResources.INSTANCE.crossDown48(), ImageResources.INSTANCE.crossHover48(), ImageResources.INSTANCE.crossDisabled48(), PressGangCCMSUI.INSTANCE.Close());
		
		

		populateTopActionBar();		
	}

	/**
	 * This method enables or disables the save button based on the read only
	 * state, and also highlights the history button if needed.
	 */
	protected void fixReadOnlyButtons()
	{
		this.getSave().setEnabled(!readOnly);

		if (readOnly)
		{
			if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
				this.getHistory().getUpFace().setImage(new Image(ImageResources.INSTANCE.historyAlert48()));
		}
		else
		{
			if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
				this.getHistory().getUpFace().setImage(new Image(ImageResources.INSTANCE.history48()));
		}
	}

	protected void buildSplitViewButtons(final SplitType splitType)
	{
		renderedSplitViewMenu.clear();

		if (splitType != SplitType.DISABLED)
		{
			renderedSplitViewMenu.setWidget(0, 0, renderedSplitOpen);
			
			if (splitType == SplitType.NONE)
				renderedSplitViewMenu.setWidget(0, 1, renderedNoSplitDown);
			else
				renderedSplitViewMenu.setWidget(0, 1, renderedNoSplit);

			if (splitType == SplitType.VERTICAL)
				renderedSplitViewMenu.setWidget(0, 2, renderedVerticalSplitDown);
			else
				renderedSplitViewMenu.setWidget(0, 2, renderedVerticalSplit);

			if (splitType == SplitType.HORIZONTAL)
				renderedSplitViewMenu.setWidget(0, 3, renderedHorizontalSplitDown);
			else
				renderedSplitViewMenu.setWidget(0, 3, renderedHorizontalSplit);

			renderedSplitViewMenu.setWidget(0, 4, renderedSplitClose);
		}
		
		addRightAlignedActionButtonPaddingPanel(this.renderedSplitViewMenu);
	}
	
	/** Show the rendered split view menu */
	public void showSplitViewButtons()
	{
		getTopActionParentPanel().clear();
		getTopActionParentPanel().add(renderedSplitViewMenu);
	}

	/**
	 * This method is called to initialize the buttons that should appear in the
	 * top action bar
	 */
	abstract protected void populateTopActionBar();
}
