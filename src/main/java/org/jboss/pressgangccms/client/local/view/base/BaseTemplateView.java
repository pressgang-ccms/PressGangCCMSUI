package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.resources.css.CSSResources;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is used to build the standard page template
 * 
 * @author Matthew Casperson
 */
public abstract class BaseTemplateView implements BaseTemplateViewInterface
{
	private final FlexTable topLevelPanel = new FlexTable();
	private final SimplePanel headingBanner = new SimplePanel();
	private final Label pageTitle = new Label();
	private final Grid shortcutAndContentPanel = new Grid(1, 2);
	private final VerticalPanel shortcutPanel = new VerticalPanel();
	private final FlexTable contentParentPanel = new FlexTable();
	private final FlexTable topActionPanel = new FlexTable();
	private final HorizontalPanel footerPanel = new HorizontalPanel();
	private final Image spinner = new Image(ImageResources.INSTANCE.spinner());
	private final SimplePanel contentPanel = new SimplePanel();
	private final DialogBox waiting = new DialogBox();

	private final PushButton home;
	private final PushButton search;
	private final PushButton searchTranslations;
	private final PushButton bug;
	private final PushButton reports;

	public PushButton getReports()
	{
		return reports;
	}

	public PushButton getSearchTranslations()
	{
		return searchTranslations;
	}

	@Override
	public FlexTable getTopActionPanel()
	{
		return topActionPanel;
	}

	@Override
	public SimplePanel getPanel()
	{
		return contentPanel;
	}

	@Override
	public PushButton getBug()
	{
		return bug;
	}

	@Override
	public PushButton getSearch()
	{
		return search;
	}

	@Override
	public Panel getTopLevelPanel()
	{
		return this.topLevelPanel;
	}

	@Override
	public void setSpinnerVisible(final boolean enabled)
	{
		if (enabled)
		{
			waiting.center();
			waiting.show();
		}
		else
		{
			waiting.hide();
		}
	}

	public BaseTemplateView(final String applicationName, final String pageName)
	{
		topLevelPanel.addStyleName("TopLevelPanel");

		/* Iinitialize the loading spinner */
		waiting.setGlassEnabled(true);
		waiting.setWidget(spinner);

		/* Add the header */
		headingBanner.addStyleName(CSSResources.INSTANCE.App().ApplicationHeadingPanel());
		headingBanner.add(new Image(ImageResources.INSTANCE.headingBanner()));
		topLevelPanel.getCellFormatter().addStyleName(0, 0, "TopLevelPanelHeading");
		topLevelPanel.setWidget(0, 0, headingBanner);

		/* Add the page title */
		pageTitle.setText(" " + pageName);
		pageTitle.addStyleName("PageTitle");
		topLevelPanel.getCellFormatter().addStyleName(1, 0, "TopLevelPanelPageTitle");
		topLevelPanel.setWidget(1, 0, pageTitle);

		/* Add the vertical shortcut panel and the content panel */
		shortcutAndContentPanel.addStyleName("ShortcutAndContentPanel");
		topLevelPanel.getCellFormatter().addStyleName(2, 0, "TopLevelPanelShortcutAndContentPanel");
		topLevelPanel.setWidget(2, 0, shortcutAndContentPanel);

		/*->Add the shortcut panel */
		/**/shortcutPanel.addStyleName("ShortcutPanel");
		/**/shortcutAndContentPanel.getCellFormatter().setStyleName(0, 0, "ShortcutAndContentPanelTopActionPanel");
		/**/shortcutAndContentPanel.setWidget(0, 0, shortcutPanel);

		/*->Add the panel that will hold the action buttons and content */
		/**/contentParentPanel.addStyleName("ContentParentPanel");
		/**/shortcutAndContentPanel.getCellFormatter().setStyleName(0, 1, "ShortcutAndContentPanelContentParentPanel");
		/**/shortcutAndContentPanel.setWidget(0, 1, contentParentPanel);

		/*----> Add the action buttons */
		/*--->*/contentParentPanel.setWidget(0, 0, topActionPanel);
		/*--->*/contentParentPanel.getCellFormatter().addStyleName(0, 0, "ContentParentTopActionPanel");
		/*--->*/topActionPanel.addStyleName("TopActionPanel");

		/*----> Add the content */
		/*----> Each view can style the content panel with the name that suits them */
		/*--->*/contentParentPanel.getCellFormatter().addStyleName(1, 0, "ContentParentContentPanel");
		/*--->*/contentParentPanel.setWidget(1, 0, contentPanel);

		/* Add the footer */
		footerPanel.addStyleName("FooterPanel");
		topLevelPanel.getCellFormatter().addStyleName(3, 0, "TopLevelPanelFooterPanel");
		topLevelPanel.setWidget(3, 0, footerPanel);

		/* Build the shortcut panel */

		/* Add a spacer */
		shortcutPanel.add(new Image(ImageResources.INSTANCE.transparent48()));

		home = new PushButton(new Image(ImageResources.INSTANCE.home48()), new Image(ImageResources.INSTANCE.homeDown48()));
		home.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.homeHover48()));
		home.addStyleName("SpacedButton");
		shortcutPanel.add(home);

		search = new PushButton(new Image(ImageResources.INSTANCE.search48()), new Image(ImageResources.INSTANCE.searchDown48()));
		search.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.searchHover48()));
		search.addStyleName("SpacedButton");
		shortcutPanel.add(search);

		searchTranslations = new PushButton(new Image(ImageResources.INSTANCE.searchTranslations48()), new Image(ImageResources.INSTANCE.searchTranslationsDown48()));
		searchTranslations.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.searchTranslationsHover48()));
		searchTranslations.getUpDisabledFace().setImage(new Image(ImageResources.INSTANCE.searchTranslationsDisabled48()));
		searchTranslations.addStyleName("SpacedButton");
		searchTranslations.setEnabled(false);
		shortcutPanel.add(searchTranslations);

		reports = new PushButton(new Image(ImageResources.INSTANCE.reports48()), new Image(ImageResources.INSTANCE.reportsDown48()));
		reports.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.reportsHover48()));
		reports.getUpDisabledFace().setImage(new Image(ImageResources.INSTANCE.reportsDisabled48()));
		reports.setEnabled(false);
		reports.addStyleName("SpacedButton");
		shortcutPanel.add(reports);

		bug = new PushButton(new Image(ImageResources.INSTANCE.bug48()), new Image(ImageResources.INSTANCE.bugDown48()));
		bug.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.bugHover48()));
		bug.addStyleName("SpacedButton");
		shortcutPanel.add(bug);
	}

	protected void addRightAlignedActionButtonPaddingPanel()
	{
		final int rows = this.getTopActionPanel().getRowCount();
		int columns = 0;
		if (rows != 0)
		{
			columns = this.getTopActionPanel().getCellCount(0);
		}

		this.getTopActionPanel().setWidget(0, columns, new SimplePanel());
		this.getTopActionPanel().getCellFormatter().addStyleName(0, columns, "RightAlignedActionButtons");
	}

	protected void addActionButton(final Widget widget)
	{
		final int rows = this.getTopActionPanel().getRowCount();
		int columns = 0;
		if (rows != 0)
		{
			columns = this.getTopActionPanel().getCellCount(0);
		}

		this.getTopActionPanel().setWidget(0, columns, widget);
	}

	protected PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		return retvalue;
	}

	protected PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String className)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.addStyleName(className);
		return retvalue;
	}

	protected PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.getUpDisabledFace().setImage(new Image(disabled));
		return retvalue;
	}

	protected PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled, final String className)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.getUpDisabledFace().setImage(new Image(disabled));
		retvalue.addStyleName(className);
		return retvalue;
	}

	protected ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover)
	{
		final ToggleButton retvalue = new ToggleButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		return retvalue;
	}

	protected ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String className)
	{
		final ToggleButton retvalue = new ToggleButton(new Image(up), new Image(down));
		retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.addStyleName(className);
		return retvalue;
	}
}