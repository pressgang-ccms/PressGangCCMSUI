package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class SearchResultsAndTopicView extends BaseTemplateView implements SearchResultsAndTopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
	private final HanldedSplitLayoutPanel splitPanel = new HanldedSplitLayoutPanel(5);
	private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
	private final DockLayoutPanel topicViewLayoutPanel = new DockLayoutPanel(Unit.PX);
	private final SimpleLayoutPanel topicResultsPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel topicViewPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel topicResultsActionButtonsPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel topicViewActionButtonsPanel = new SimpleLayoutPanel();

	@Override
	public HanldedSplitLayoutPanel getSplitPanel()
	{
		return splitPanel;
	}

	@Override
	public SimpleLayoutPanel getTopicResultsActionButtonsPanel()
	{
		return topicResultsActionButtonsPanel;
	}

	@Override
	public SimpleLayoutPanel getTopicResultsPanel()
	{
		return topicResultsPanel;
	}

	@Override
	public SimpleLayoutPanel getTopicViewPanel()
	{
		return topicViewPanel;
	}

	@Override
	public SimpleLayoutPanel getTopicViewActionButtonsPanel()
	{
		return topicViewActionButtonsPanel;
	}

	public SearchResultsAndTopicView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

		/* We have own own top action panels */
		this.getTopActionPanel().removeFromParent();

		/* Add a spacer */
		final Image spacer = new Image(ImageResources.INSTANCE.transparent48());
		spacer.addStyleName(CSSConstants.SPACEDBUTTON);
		this.getShortcutPanel().insert(spacer, 0);

		resultsViewLayoutPanel.addStyleName(CSSConstants.RESULTSVIEWLAYOUTPANEL);
		topicViewLayoutPanel.addStyleName(CSSConstants.TOPICVIEWLAYOUTPANEL);

		resultsViewLayoutPanel.addNorth(topicResultsActionButtonsPanel, 80);
		topicViewLayoutPanel.addNorth(topicViewActionButtonsPanel, 80);

		resultsViewLayoutPanel.add(topicResultsPanel);
		topicViewLayoutPanel.add(topicViewPanel);

		splitPanel.addWest(resultsViewLayoutPanel, 300);
		splitPanel.add(topicViewLayoutPanel);

		topicViewActionButtonsPanel.addStyleName(CSSConstants.TOPICSEARCHTOPICVIEWBUTTONSPANEL);
		topicViewPanel.addStyleName(CSSConstants.TOPICSEARCHTOPICVIEWDETAILSPANEL);

		splitPanel.addStyleName(CSSConstants.TOPICSEARCHRESULTSANDVIEWPARENTPANEL);

		this.getPanel().add(splitPanel);
	}
}
