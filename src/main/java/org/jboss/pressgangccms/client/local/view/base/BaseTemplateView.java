package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.constants.Constants;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

	/**
	 * This class is used to build the standard page template
	 * @author Matthew Casperson
	 */
	public abstract class BaseTemplateView implements BaseTemplateViewInterface
	{
		private final VerticalPanel topLevelPanel = new VerticalPanel();
		private final HorizontalPanel appTitleAndSpinnerPanel = new HorizontalPanel();
		private final Label applicationTitle = new Label();
		private final Label pageTitle = new Label();
		private final HorizontalPanel shortcutAndContentPanel = new HorizontalPanel();
		private final VerticalPanel shortcutPanel = new VerticalPanel();
		private final VerticalPanel contentPanel = new VerticalPanel();
		private final HorizontalPanel topActionPanel = new HorizontalPanel();
		private final HorizontalPanel footerPanel = new HorizontalPanel();
		private final Image spinner = new Image(Constants.resources.spinner());
		private final SimplePanel panel = new SimplePanel();

		private final PushButton search;
		private final PushButton bug;

		protected SimplePanel getPanel()
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
			spinner.setVisible(enabled);
		}

		public BaseTemplateView(final String applicationName, final String pageName)
		{
			topLevelPanel.add(appTitleAndSpinnerPanel);
			
			applicationTitle.setText(applicationName);
			applicationTitle.addStyleName("ApplicationTitle");
			applicationTitle.addStyleName("SpacedElement");
			appTitleAndSpinnerPanel.add(applicationTitle);
			
			appTitleAndSpinnerPanel.add(spinner);
			setSpinnerVisible(false);

			pageTitle.setText(pageName);
			pageTitle.addStyleName("PageTitle");
			pageTitle.addStyleName("SpacedElement");
			topLevelPanel.add(pageTitle);

			topLevelPanel.add(shortcutAndContentPanel);
			shortcutAndContentPanel.add(shortcutPanel);
			shortcutAndContentPanel.add(contentPanel);
			
			contentPanel.add(topActionPanel);

			contentPanel.add(panel);

			topLevelPanel.add(footerPanel);

			/* Build the shortcut panel */
			search = new PushButton(new Image(Constants.resources.search48()), new Image(Constants.resources.searchDown48()));
			search.getUpHoveringFace().setImage(new Image(Constants.resources.searchHover48()));
			search.addStyleName("SpacedElement");
			shortcutPanel.add(search);
			
			bug = new PushButton(new Image(Constants.resources.bug48()), new Image(Constants.resources.bugDown48()));
			bug.getUpHoveringFace().setImage(new Image(Constants.resources.bugHover48()));
			bug.addStyleName("SpacedElement");
			shortcutPanel.add(bug);
		}
	}