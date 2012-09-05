package org.jboss.pressgangccms.client.local.mvp.view.image;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

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

public class ImageFilteredResultsView extends BaseTemplateView implements ImageFilteredResultsPresenter.Display
{
	public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

	private final VerticalPanel searchResultsPanel = new VerticalPanel();
	private final FlexTable filterTable = new FlexTable();
	private final Label imageIdFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageID());
	private final TextBox imageIdFilter = new TextBox();
	private final Label imageDescriptionFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());
	private final TextBox imageDescriptionFilter = new TextBox();
	private final Label imageOriginalFileNameFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageOriginalFileName());
	private final TextBox imageOriginalFileNameFilter = new TextBox();
	private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());

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

	public TextBox getImageOriginalFileNameFilter()
	{
		return imageOriginalFileNameFilter;
	}

	public TextBox getImageIdFilter()
	{
		return imageIdFilter;
	}

	public TextBox getImageDescriptionFilter()
	{
		return imageDescriptionFilter;
	}

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
		
		this.addActionButton(search);
		this.addRightAlignedActionButtonPaddingPanel();
		
		results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
		results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());
		
		searchResultsPanel.addStyleName(CSSConstants.IMAGEFILTEREDRESULTSPANEL);
		filterTable.addStyleName(CSSConstants.IMAGEFILTEREDOPTIONSPANEL);
		
		filterTable.setWidget(0, 0, imageIdFilterLabel);
		filterTable.setWidget(0, 1, imageIdFilter);
		filterTable.setWidget(1, 0, imageDescriptionFilterLabel);
		filterTable.setWidget(1, 1, imageDescriptionFilter);
		filterTable.setWidget(2, 0, imageOriginalFileNameFilterLabel);
		filterTable.setWidget(2, 1, imageOriginalFileNameFilter);
		
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
