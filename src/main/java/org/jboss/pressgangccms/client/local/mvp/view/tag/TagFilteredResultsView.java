package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class TagFilteredResultsView extends BaseTemplateView implements TagFilteredResultsPresenter.Display
{
	public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

	private final VerticalPanel searchResultsPanel = new VerticalPanel();
	private final FlexTable filterTable = new FlexTable();
	private final Label idFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagID());
	private final TextBox idFilter = new TextBox();
	private final Label nameFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagName());
	private final TextBox nameFilter = new TextBox();
	private final Label descriptionFilterLabel = new Label(PressGangCCMSUI.INSTANCE.TagDescription());
	private final TextBox descriptionFilter = new TextBox();
	private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
	
	private final SimplePager pager = new SimplePager();
	private final CellTable<RESTTagV1> results = new CellTable<RESTTagV1>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
	private AsyncDataProvider<RESTTagV1> provider;

	private final TextColumn<RESTTagV1> idColumn = new TextColumn<RESTTagV1>()
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return object.getId().toString();

		}
	};

	private final TextColumn<RESTTagV1> descriptionColumn = new TextColumn<RESTTagV1>()
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return object.getDescription();
		}
	};

	public TextBox getNameFilter()
	{
		return nameFilter;
	}

	public TextBox getIdFilter()
	{
		return idFilter;
	}

	public TextBox getDescriptionFilter()
	{
		return descriptionFilter;
	}

	public SimplePager getPager()
	{
		return pager;
	}

	public CellTable<RESTTagV1> getResults()
	{
		return results;
	}

	public AsyncDataProvider<RESTTagV1> getProvider()
	{
		return provider;		
	}

	public void setProvider(AsyncDataProvider<RESTTagV1> provider)
	{
		this.provider = provider;
		provider.addDataDisplay(results);
	}

	public TagFilteredResultsView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());
		
		this.addActionButton(search);
		this.addRightAlignedActionButtonPaddingPanel();
		
		results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
		results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());
		
		searchResultsPanel.addStyleName(CSSConstants.TagFilteredResultsView.TAGFILTEREDRESULTSPANEL);
		filterTable.addStyleName(CSSConstants.TagFilteredResultsView.TAGFILTEREDOPTIONSPANEL);
		
		filterTable.setWidget(0, 0, idFilterLabel);
		filterTable.setWidget(0, 1, idFilter);
		filterTable.setWidget(1, 0, nameFilterLabel);
		filterTable.setWidget(1, 1, nameFilter);
		filterTable.setWidget(2, 0, descriptionFilterLabel);
		filterTable.setWidget(2, 1, descriptionFilter);
		
		searchResultsPanel.add(filterTable);
		searchResultsPanel.add(results);
		searchResultsPanel.add(pager);
		
		pager.setDisplay(results);
		
		this.getPanel().add(searchResultsPanel);
	}

	public PushButton getSearch()
	{
		return search;
	}
}
