package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

public class SearchResultsAndTopicView extends BaseTemplateView implements SearchResultsAndTopicPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";
	private final Grid resultsViewPanel = new Grid(2, 2);
	private final SimplePanel topicResultsPanel = new SimplePanel();
	private final SimplePanel topicViewPanel = new SimplePanel();
	private final SimplePanel topicResultsActionButtonsPanel = new SimplePanel();
	private final SimplePanel topicViewActionButtonsPanel = new SimplePanel();
	
	@Override
	public SimplePanel getTopicResultsActionButtonsPanel()
	{
		return topicResultsActionButtonsPanel;
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

	@Override
	public SimplePanel getTopicViewActionButtonsPanel()
	{
		return topicViewActionButtonsPanel;
	}

	public SearchResultsAndTopicView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
				
		resultsViewPanel.addStyleName("TopicSearchResultsAndViewPanel");
		
		resultsViewPanel.getCellFormatter().addStyleName(0, 1, "TopicSearchResultsPanel");
		resultsViewPanel.getCellFormatter().addStyleName(1, 1, "TopicSearchResultsPanel");
		resultsViewPanel.getCellFormatter().addStyleName(0, 1, "TopicSearchTopicViewPanel");
		resultsViewPanel.getCellFormatter().addStyleName(1, 1, "TopicSearchTopicViewPanel");
		
		resultsViewPanel.setWidget(0, 0, topicResultsActionButtonsPanel);
		resultsViewPanel.setWidget(0, 1, topicViewActionButtonsPanel);
		resultsViewPanel.setWidget(1, 0, topicResultsPanel);
		resultsViewPanel.setWidget(1, 1, topicViewPanel);
		
		this.getPanel().add(resultsViewPanel);
	}



}
