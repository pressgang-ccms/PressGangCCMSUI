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

/**
 * The base interface for all presenters
 */
public abstract interface Presenter
{
	public abstract void go(final HasWidgets container);

	/**
	 * This interface defines the base methods that the presenters expect to
	 * see.
	 * 
	 * @author Matthew Casperson
	 * 
	 */
	public interface TemplateInterface
	{
		/**
		 * @return The panel that should be added as a child to the root panel
		 */
		Panel getTopLevelPanel();
	}

	/**
	 * This class provides a way to build pages from a standard template. The
	 * views are expected to extend this class, as well as a Display interface
	 * defined in the Presenter classes.
	 * 
	 * In this way Errai can know which class to instantiate (because the View
	 * class extends the Presenters own Display interface), the View can build a
	 * standard template (because it extends this class), and the Presenter can
	 * work with the view through the TemplateInterface.
	 * 
	 * @author Matthew Casperson
	 */
	public abstract class TemplateDisplay implements TemplateInterface
	{
		private static final ImageResources resources = GWT.create(ImageResources.class);
		private final VerticalPanel topLevelPanel = new VerticalPanel();
		private final Label applicationTitle = new Label();
		private final Label pageTitle = new Label();
		private final HorizontalPanel shortcutPanel = new HorizontalPanel();
		private final HorizontalPanel topActionPanel = new HorizontalPanel();
		private final HorizontalPanel footerPanel = new HorizontalPanel();

		public Panel getTopLevelPanel()
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
