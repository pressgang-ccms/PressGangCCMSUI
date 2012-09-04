package org.jboss.pressgangccms.client.local.mvp.view;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class ImageFilteredResultsView extends BaseTemplateView implements ImageFilteredResultsPresenter.Display
{
	public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

	private final VerticalPanel searchResultsPanel = new VerticalPanel();

	private final SimplePager pager = new SimplePager();
	private final CellTable<RESTImageV1> results = new CellTable<RESTImageV1>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
	private AsyncDataProvider<RESTImageV1> provider;

	private final TextColumn<RESTImageV1> idColumn = new TextColumn<RESTImageV1>()
	{
		@Override
		public String getValue(final RESTImageV1 object)
		{
			return object.getId().toString();

		}
	};

	private final TextColumn<RESTImageV1> descriptionColumn = new TextColumn<RESTImageV1>()
	{
		@Override
		public String getValue(final RESTImageV1 object)
		{
			return object.getDescription();
		}
	};

	public SimplePager getPager()
	{
		return pager;
	}

	public CellTable<RESTImageV1> getResults()
	{
		return results;
	}

	public AsyncDataProvider<RESTImageV1> getProvider()
	{
		return provider;		
	}

	public void setProvider(AsyncDataProvider<RESTImageV1> provider)
	{
		this.provider = provider;
		provider.addDataDisplay(results);
	}

	public ImageFilteredResultsView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());
		
		results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
		results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());
		
		searchResultsPanel.addStyleName(CSSConstants.IMAGEFILTEREDRESULTSPANEL);
		
		searchResultsPanel.add(results);
		searchResultsPanel.add(pager);
		
		pager.setDisplay(results);
		
		this.getPanel().add(searchResultsPanel);
	}
}
