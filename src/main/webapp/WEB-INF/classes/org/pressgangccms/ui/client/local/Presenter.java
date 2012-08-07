package org.pressgangccms.ui.client.local;

import org.pressgangccms.ui.resources.ImageResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The base interface for all presenters
 */
public abstract interface Presenter 
{
	public abstract void go(final HasWidgets container);
	
	public abstract class TemplateDisplay
	{
		private static final ImageResources resources = GWT.create(ImageResources.class);
		private final VerticalPanel topLevelPanel = new VerticalPanel();
		private final Label applicationTitle = new Label();
		private final Label pageTitle = new Label();
		private final HorizontalPanel shortcutPanel = new HorizontalPanel();
		private final HorizontalPanel topActionPanel = new HorizontalPanel();
		private final HorizontalPanel footerPanel = new HorizontalPanel();
		
		public Widget getTopLevelPanel()
		{
			return this.topLevelPanel;
		}
		
		public TemplateDisplay(final String applicationName, final String pageName)
		{
			applicationTitle.setText(applicationName);
			topLevelPanel.add(applicationTitle);
			
			pageTitle.setText(pageName);
			topLevelPanel.add(pageTitle);
			
			topLevelPanel.add(shortcutPanel);
			topLevelPanel.add(topActionPanel);
			
			topLevelPanel.add(getContentPanel());
			
			topLevelPanel.add(footerPanel);
			
			buildShortcutPanel();
		}
		
		private void buildShortcutPanel()
		{
			final PushButton search = new PushButton(new Image(resources.search()));
			
			shortcutPanel.add(search);
		}
		
		abstract protected Panel getContentPanel();
	}
}
