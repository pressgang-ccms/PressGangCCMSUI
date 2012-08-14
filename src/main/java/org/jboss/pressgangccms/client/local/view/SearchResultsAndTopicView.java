package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

public class SearchResultsAndTopicView extends BaseTemplateView implements SearchResultsAndTopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
	private final Grid resultsViewPanel = new Grid(1, 2);
	private final SimplePanel topicResultsPanel = new SimplePanel();
	private final SimplePanel topicViewPanel = new SimplePanel();
	
	public SearchResultsAndTopicView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
		
		resultsViewPanel.addStyleName("TopicSearchResultsAndViewPanel");
		
		resultsViewPanel.setWidget(0, 0, topicResultsPanel);
		resultsViewPanel.setWidget(0, 1, topicViewPanel);
		
		this.getPanel().add(resultsViewPanel);
	}

	@Override
	public SimplePanel getTopicResultsPanel()
	{
		return topicResultsPanel;
	}

	@Override
	public SimplePanel getTopicViewPanel()
	{
		return topicViewPanel;
	}

}
