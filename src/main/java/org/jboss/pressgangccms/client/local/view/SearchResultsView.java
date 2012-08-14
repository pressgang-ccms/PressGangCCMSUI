package org.jboss.pressgangccms.client.local.view;

import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class SearchResultsView extends BaseTemplateView implements SearchResultsPresenter.Display
{
	public static final String HISTORY_TOKEN = "SearchResultsView";
	
	private final VerticalPanel searchResultsPanel = new VerticalPanel();
	
	private final SimplePager pager = new SimplePager();
	private final CellTable<RESTTopicV1> results = new CellTable<RESTTopicV1>();
	private AsyncDataProvider<RESTTopicV1> provider;
	private final TextColumn<RESTTopicV1> idColumn = new TextColumn<RESTTopicV1>()
	{
		@Override
		public String getValue(final RESTTopicV1 object)
		{
			return object.getId().toString();
		}
	};

	private final TextColumn<RESTTopicV1> titleColumn = new TextColumn<RESTTopicV1>()
	{
		@Override
		public String getValue(RESTTopicV1 object)
		{
			return object.getTitle();
		}
	};

	public AsyncDataProvider<RESTTopicV1> getProvider()
	{
		return provider;
	}

	public void setProvider(AsyncDataProvider<RESTTopicV1> provider)
	{
		this.provider = provider;
		provider.addDataDisplay(results);
	}

	public CellTable<RESTTopicV1> getResults()
	{
		return results;
	}
	
	public SimplePager getPager()
	{
		return pager;
	}

	public SearchResultsView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
		
		results.addColumn(idColumn);
		results.addColumn(titleColumn);
		
		searchResultsPanel.add(results);
		searchResultsPanel.add(pager);
		
		pager.setDisplay(results);
		
		this.getPanel().add(searchResultsPanel);
	}

}
