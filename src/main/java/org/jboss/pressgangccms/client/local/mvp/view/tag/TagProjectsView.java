package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.components.ComponentTagV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class TagProjectsView extends TagViewBase implements TagProjectsPresenter.Display
{
	public static final String HISTORY_TOKEN = "TagProjectsView";
	
	/** A reference to the tag that this view will be modifying */
	private RESTTagV1 tag;

	private final VerticalPanel searchResultsPanel = new VerticalPanel();

	private final SimplePager pager = new SimplePager();
	private final CellTable<RESTProjectV1> results = new CellTable<RESTProjectV1>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
	private AsyncDataProvider<RESTProjectV1> provider;

	private final TextColumn<RESTProjectV1> idColumn = new TextColumn<RESTProjectV1>()
	{
		@Override
		public String getValue(final RESTProjectV1 object)
		{
			return object.getId().toString();

		}
	};

	private final TextColumn<RESTProjectV1> nameColumn = new TextColumn<RESTProjectV1>()
	{
		@Override
		public String getValue(final RESTProjectV1 object)
		{
			return object.getName();
		}
	};

	private final TextColumn<RESTProjectV1> descriptionColumn = new TextColumn<RESTProjectV1>()
	{
		@Override
		public String getValue(final RESTProjectV1 object)
		{
			return object.getDescription();
		}
	};

	private final Column<RESTProjectV1, String> buttonColumn = new Column<RESTProjectV1, String>(new ButtonCell())
	{
		@Override
		public String getValue(final RESTProjectV1 object)
		{
			if (tag != null)
			{
				if (ComponentTagV1.containedInProject(tag, object.getId()))
				{
					return PressGangCCMSUI.INSTANCE.Remove();
				}
				else
				{
					return PressGangCCMSUI.INSTANCE.Add();
				}
			}
			
			return PressGangCCMSUI.INSTANCE.NoAction();
		}
	};

	public Column<RESTProjectV1, String> getButtonColumn()
	{
		return buttonColumn;
	}

	public AsyncDataProvider<RESTProjectV1> getProvider()
	{
		return provider;
	}

	public void setProvider(final AsyncDataProvider<RESTProjectV1> provider)
	{
		this.provider = provider;
		provider.addDataDisplay(results);
	}

	public CellTable<RESTProjectV1> getResults()
	{
		return results;
	}

	public SimplePager getPager()
	{
		return pager;
	}

	public TagProjectsView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());

		results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ProjectID());
		results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ProjectName());
		results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ProjectName());
		results.addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());
		
		pager.setDisplay(results);

		searchResultsPanel.add(results);
		searchResultsPanel.add(pager);
		
		this.getPanel().setWidget(searchResultsPanel);
	}
	
	public void initialize(final RESTTagV1 tag, final boolean readOnly)
	{
		this.tag = tag;
	}
	
	protected void populateTopActionBar()
	{
		this.addActionButton(this.getTagDetails());
		this.addActionButton(this.getTagProjects());
		this.addActionButton(this.getTagCategories());
		this.addActionButton(this.getSave());
		addRightAlignedActionButtonPaddingPanel();
	}
}
