package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.components.ComponentTagV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

public class TagCategoriesView extends TagViewBase implements TagCategoriesPresenter.Display
{
	public static final String HISTORY_TOKEN = "TagCategoriesView";

	/** A reference to the tag that this view will be modifying */
	private RESTTagV1 tag;

	private SplitLayoutPanel split = new SplitLayoutPanel();

	private final VerticalPanel searchResultsPanel = new VerticalPanel();
	private final SimplePager pager = new SimplePager();
	private final CellTable<RESTCategoryV1> results = new CellTable<RESTCategoryV1>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
	private AsyncDataProvider<RESTCategoryV1> provider;

	private final VerticalPanel tagsResultsPanel = new VerticalPanel();
	private final SimplePager tagsPager = new SimplePager();
	private final CellTable<RESTTagV1> tagsResults = new CellTable<RESTTagV1>(Constants.MAX_SEARCH_RESULTS, (Resources) GWT.create(TableResources.class));
	private AsyncDataProvider<RESTTagV1> tagsProvider;

	private final TextColumn<RESTCategoryV1> idColumn = new TextColumn<RESTCategoryV1>()
	{
		@Override
		public String getValue(final RESTCategoryV1 object)
		{
			return object.getId().toString();

		}
	};

	private final TextColumn<RESTCategoryV1> nameColumn = new TextColumn<RESTCategoryV1>()
	{
		@Override
		public String getValue(final RESTCategoryV1 object)
		{
			return object.getName();
		}
	};

	private final TextColumn<RESTCategoryV1> descriptionColumn = new TextColumn<RESTCategoryV1>()
	{
		@Override
		public String getValue(final RESTCategoryV1 object)
		{
			return object.getDescription();
		}
	};

	private final Column<RESTCategoryV1, String> buttonColumn = new Column<RESTCategoryV1, String>(new ButtonCell())
	{
		@Override
		public String getValue(final RESTCategoryV1 object)
		{
			if (tag != null)
			{
				if (ComponentTagV1.containedInCategory(tag, object.getId()))
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

	private final TextColumn<RESTTagV1> tagIdColumn = new TextColumn<RESTTagV1>()
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return object.getId().toString();

		}
	};

	private final TextColumn<RESTTagV1> tagNameColumn = new TextColumn<RESTTagV1>()
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return object.getName();
		}
	};

	private final TextColumn<RESTTagV1> tagDescriptionColumn = new TextColumn<RESTTagV1>()
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return object.getDescription();
		}
	};

	private final Column<RESTTagV1, String> tagUpButtonColumn = new Column<RESTTagV1, String>(new ButtonCell())
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return PressGangCCMSUI.INSTANCE.Up();
		}
	};
	
	private final Column<RESTTagV1, String> tagDownButtonColumn = new Column<RESTTagV1, String>(new ButtonCell())
	{
		@Override
		public String getValue(final RESTTagV1 object)
		{
			return PressGangCCMSUI.INSTANCE.Down();
		}
	};

	public Column<RESTTagV1, String> getTagDownButtonColumn()
	{
		return tagDownButtonColumn;
	}

	public Column<RESTTagV1, String> getTagUpButtonColumn()
	{
		return tagUpButtonColumn;
	}

	public AsyncDataProvider<RESTTagV1> getTagsProvider()
	{
		return tagsProvider;
	}

	public void setTagsProvider(AsyncDataProvider<RESTTagV1> tagsProvider)
	{
		this.tagsProvider = tagsProvider;
	}

	public Column<RESTCategoryV1, String> getButtonColumn()
	{
		return buttonColumn;
	}

	public AsyncDataProvider<RESTCategoryV1> getProvider()
	{
		return provider;
	}

	public void setProvider(final AsyncDataProvider<RESTCategoryV1> provider)
	{
		this.provider = provider;
		provider.addDataDisplay(results);
	}

	public CellTable<RESTCategoryV1> getResults()
	{
		return results;
	}

	public SimplePager getPager()
	{
		return pager;
	}

	public TagCategoriesView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());

		results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.CategoryID());
		results.addColumn(nameColumn, PressGangCCMSUI.INSTANCE.CategoryName());
		results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.CategoryDescription());
		results.addColumn(buttonColumn, PressGangCCMSUI.INSTANCE.AddRemove());

		searchResultsPanel.add(results);
		searchResultsPanel.add(pager);
		
		pager.setDisplay(results);

		split.addWest(searchResultsPanel, Constants.SPLIT_PANEL_SIZE);
		
		tagsResults.addColumn(tagIdColumn, PressGangCCMSUI.INSTANCE.TagID());
		tagsResults.addColumn(tagNameColumn, PressGangCCMSUI.INSTANCE.TagName());
		tagsResults.addColumn(tagDescriptionColumn, PressGangCCMSUI.INSTANCE.TagDescription());
		tagsResults.addColumn(tagUpButtonColumn, PressGangCCMSUI.INSTANCE.Up());
		tagsResults.addColumn(tagDownButtonColumn, PressGangCCMSUI.INSTANCE.Down());
		
		tagsResultsPanel.add(tagsResults);
		tagsResultsPanel.add(tagsPager);
		
		tagsPager.setDisplay(tagsResults);
		
		split.add(tagsResultsPanel);

		this.getPanel().setWidget(split);
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
