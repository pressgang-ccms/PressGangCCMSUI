package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.resources.css.CSSResources;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
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
	 * @author Matthew Casperson
	 */
	public abstract class BaseTemplateView implements BaseTemplateViewInterface
	{
		private final VerticalPanel topLevelPanel = new VerticalPanel();
		private final SimplePanel headingBanner = new SimplePanel();
		private final Label pageTitle = new Label();
		private final Grid shortcutAndContentPanel = new Grid(1, 2);
		private final VerticalPanel shortcutPanel = new VerticalPanel();
		private final VerticalPanel contentPanel = new VerticalPanel();
		private final FlexTable topActionPanel = new FlexTable();
		private final HorizontalPanel footerPanel = new HorizontalPanel();
		private final Image spinner = new Image(ImageResources.INSTANCE.spinner());
		private final SimplePanel panel = new SimplePanel();
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
			return panel;
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
			
			waiting.setGlassEnabled(true);
			waiting.setWidget(spinner);
			
			headingBanner.addStyleName(CSSResources.INSTANCE.App().ApplicationHeadingPanel());
			headingBanner.add(new Image(ImageResources.INSTANCE.headingBanner()));
			topLevelPanel.add(headingBanner);
			
			pageTitle.setText(" " + pageName);
			pageTitle.addStyleName("PageTitle");
			topLevelPanel.add(pageTitle);

			shortcutAndContentPanel.addStyleName("ShortcutAndContentPanel");
			shortcutAndContentPanel.getCellFormatter().setStyleName(0, 0, "ShortcutParentPanel");
			shortcutAndContentPanel.getCellFormatter().setStyleName(0, 1, "ContentParentPanel");
			
			topLevelPanel.add(shortcutAndContentPanel);
			shortcutAndContentPanel.setWidget(0, 0, shortcutPanel);
			
			contentPanel.addStyleName("ContentPanel");
			shortcutAndContentPanel.setWidget(0, 1, contentPanel);
			
			contentPanel.add(topActionPanel);
			topActionPanel.addStyleName("TopActionPanel");

			contentPanel.add(panel);

			topLevelPanel.add(footerPanel);

			/* Build the shortcut panel */
			
			/* Add a spacer */
			shortcutPanel.add(new Image(ImageResources.INSTANCE.transparent48()));
			
			home = new PushButton(new Image(ImageResources.INSTANCE.home48()), new Image(ImageResources.INSTANCE.homeDown48()));
			home.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.homeHover48()));
			home.addStyleName("SpacedElement");
			shortcutPanel.add(home);
			
			search = new PushButton(new Image(ImageResources.INSTANCE.search48()), new Image(ImageResources.INSTANCE.searchDown48()));
			search.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.searchHover48()));
			search.addStyleName("SpacedElement");
			shortcutPanel.add(search);
			
			searchTranslations = new PushButton(new Image(ImageResources.INSTANCE.searchTranslations48()), new Image(ImageResources.INSTANCE.searchTranslationsDown48()));
			searchTranslations.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.searchTranslationsHover48()));
			searchTranslations.getUpDisabledFace().setImage(new Image(ImageResources.INSTANCE.searchTranslationsDisabled48()));
			searchTranslations.addStyleName("SpacedElement");
			searchTranslations.setEnabled(false);
			shortcutPanel.add(searchTranslations);
			
			reports = new PushButton(new Image(ImageResources.INSTANCE.reports48()), new Image(ImageResources.INSTANCE.reportsDown48()));
			reports.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.reportsHover48()));
			reports.getUpDisabledFace().setImage(new Image(ImageResources.INSTANCE.reportsDisabled48()));
			reports.setEnabled(false);
			reports.addStyleName("SpacedElement");
			shortcutPanel.add(reports);
			
			bug = new PushButton(new Image(ImageResources.INSTANCE.bug48()), new Image(ImageResources.INSTANCE.bugDown48()));
			bug.getUpHoveringFace().setImage(new Image(ImageResources.INSTANCE.bugHover48()));
			bug.addStyleName("SpacedElement");
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
		
		protected PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled)
		{
			final PushButton retvalue = new PushButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));
			retvalue.getUpDisabledFace().setImage(new Image(disabled));
			return retvalue;
		}
		
		protected ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover)
		{
			final ToggleButton retvalue = new ToggleButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));	
			return retvalue;
		}
	}