package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagsFilteredResultsAndTagView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class TagsFilteredResultsAndTagPresenter extends TagPresenterBase
{
	public interface Display extends BaseTemplateViewInterface
	{
		SimpleLayoutPanel getResultsPanel();

		SimpleLayoutPanel getViewPanel();

		SimpleLayoutPanel getViewActionButtonsPanel();

		SimpleLayoutPanel getResultsActionButtonsPanel();

		HanldedSplitLayoutPanel getSplitPanel();

		DockLayoutPanel getViewLayoutPanel();
	}

	private final ClickHandler tagDetailsClickHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			displayedView = resultDisplay;
			reInitialiseView();
		}
	};

	private final ClickHandler tagProjectsClickHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			displayedView = projectsDisplay;
			reInitialiseView();
		}
	};

	private final ClickHandler saveClickHandler = new ClickHandler()
	{
		@Override
		public void onClick(final ClickEvent event)
		{
			final RESTCalls.RESTCallback<RESTTagV1> callback = new RESTCalls.RESTCallback<RESTTagV1>()
			{
				@Override
				public void begin()
				{
					startProcessing();
				}

				@Override
				public void generalException(final Exception ex)
				{
					Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
					stopProcessing();
				}

				@Override
				public void success(final RESTTagV1 retValue)
				{
					try
					{
						displayedItem = retValue;
						reInitialiseView();
					}
					finally
					{
						stopProcessing();
					}

				}

				@Override
				public void failed()
				{
					Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
					stopProcessing();
				}
			};
			
			final RESTTagV1 updateTag = new RESTTagV1();
			updateTag.setId(displayedItem.getId());
			updateTag.explicitSetDescription(displayedItem.getDescription());
			updateTag.explicitSetName(displayedItem.getName());
			updateTag.explicitSetProjects(displayedItem.getProjects());
			
			RESTCalls.saveTag(callback, updateTag);
		}
	};

	@Inject
	private Display display;

	@Inject
	private TagFilteredResultsPresenter.Display filteredResultsDisplay;

	@Inject
	private TagPresenter.Display resultDisplay;

	@Inject
	private TagProjectsPresenter.Display projectsDisplay;

	private String queryString;

	/** Keeps a reference to the start row */
	private Integer tableStartRow;
	
	/** Keeps a reference to the start row of the projects list*/
	private Integer projectTableStartRow;

	/** Keeps a reference to the list of topics being displayed */
	private List<RESTTagV1> currentList;
	
	/** Keeps a reference to the list of projects being displayed */
	private List<RESTProjectV1> currentProjectsList;

	/** The currently selected item in the search results */
	private RESTTagV1 selectedSearchItem;

	/** The currently selected item in the search results */
	private RESTTagV1 displayedItem;

	/** The currently displayed view */
	private TagViewInterface displayedView;

	/** The last displayed view */
	private TagViewInterface lastDisplayedView;

	/** used to keep a track of how many rest calls are active */
	int count = 0;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
		display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());

		bind();
	}

	/**
	 * Add behaviour to the UI elements exposed by the views
	 */
	private void bind()
	{
		super.bind(display);

		filteredResultsDisplay.setProvider(generateListProvider());
		projectsDisplay.setProvider(generateProjectListProvider());

		bindListRowClicks();

		bindSearchButtons();

		bindTagViewButtons();

		bindProjectColumnButtons();
	}

	private void bindProjectColumnButtons()
	{
		projectsDisplay.getButtonColumn().setFieldUpdater(new FieldUpdater<RESTProjectV1, String>()
		{
			public void update(final int index, final RESTProjectV1 object, final String value)
			{
				boolean found = false;
				for (final RESTProjectV1 project : displayedItem.getProjects().getItems())
				{
					if (project.getId().equals(object.getId()))
					{
						/* Project was added and then removed */
						if (project.getAddItem())
						{
							displayedItem.getProjects().getItems().remove(project);
						}
						
						/* Project existed, was removed and then was added again */
						if (project.getRemoveItem())
						{
							project.setRemoveItem(false);							
						}
						/* Project existed and was removed */
						else
						{
							project.setRemoveItem(true);
						}
						
						found = true;
						break;
					}
				}

				if (!found)
				{
					final RESTProjectV1 newProject = new RESTProjectV1();
					newProject.setId(object.getId());
					newProject.setAddItem(true);
					displayedItem.getProjects().addItem(newProject);
				}
				
				/* refresh the project list */
				projectsDisplay.getProvider().updateRowData(projectTableStartRow, currentProjectsList);
			}
		});
	}

	/**
	 * Bind behaviour to the buttons found in the tag views
	 */
	private void bindTagViewButtons()
	{
		for (final TagViewInterface tagDisplay : new TagViewInterface[]
		{ resultDisplay, projectsDisplay })
		{
			tagDisplay.getTagDetails().addClickHandler(tagDetailsClickHandler);
			tagDisplay.getTagProjects().addClickHandler(tagProjectsClickHandler);
			tagDisplay.getSave().addClickHandler(saveClickHandler);
		}
	}

	private void bindSearchButtons()
	{
		filteredResultsDisplay.getSearch().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(getQuery(filteredResultsDisplay)));
			}
		});
	}

	/**
	 * @return A provider to be used for the tag display list
	 */
	private AsyncDataProvider<RESTProjectV1> generateProjectListProvider()
	{
		final AsyncDataProvider<RESTProjectV1> provider = new AsyncDataProvider<RESTProjectV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTProjectV1> display)
			{
				projectTableStartRow = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = projectTableStartRow + length;

				final RESTCalls.RESTCallback<RESTProjectCollectionV1> callback = new RESTCalls.RESTCallback<RESTProjectCollectionV1>()
				{
					@Override
					public void begin()
					{
						startProcessing();
					}

					@Override
					public void generalException(final Exception ex)
					{
						Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
						stopProcessing();
					}

					@Override
					public void success(final RESTProjectCollectionV1 retValue)
					{
						try
						{
							/* Zero results can be a null list */
							currentProjectsList = retValue.getItems() == null ? new ArrayList<RESTProjectV1>() : retValue.getItems();
							updateRowData(projectTableStartRow, currentProjectsList);
							updateRowCount(retValue.getSize(), true);
						}
						finally
						{
							stopProcessing();
						}
					}

					@Override
					public void failed()
					{
						stopProcessing();
						Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
					}
				};

				RESTCalls.getProjectsFromQuery(callback, queryString, tableStartRow, end);
			}
		};
		return provider;
	}

	/**
	 * @return A provider to be used for the tag display list
	 */
	private AsyncDataProvider<RESTTagV1> generateListProvider()
	{
		final AsyncDataProvider<RESTTagV1> provider = new AsyncDataProvider<RESTTagV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTTagV1> display)
			{
				tableStartRow = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = tableStartRow + length;

				final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>()
				{
					@Override
					public void begin()
					{
						startProcessing();
					}

					@Override
					public void generalException(final Exception ex)
					{
						Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
						stopProcessing();
					}

					@Override
					public void success(final RESTTagCollectionV1 retValue)
					{
						try
						{
							/* Zero results can be a null list */
							currentList = retValue.getItems() == null ? new ArrayList<RESTTagV1>() : retValue.getItems();
							updateRowData(tableStartRow, currentList);
							updateRowCount(retValue.getSize(), true);
						}
						finally
						{
							stopProcessing();
						}
					}

					@Override
					public void failed()
					{
						stopProcessing();
						Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
					}
				};

				RESTCalls.getTagsFromQuery(callback, queryString, tableStartRow, end);
			}
		};
		return provider;
	}

	/**
	 * Bind the button click events for the topic editor screens
	 */
	private void bindListRowClicks()
	{
		filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTagV1>()
		{
			@Override
			public void onCellPreview(final CellPreviewEvent<RESTTagV1> event)
			{
				/* Check to see if this was a click event */
				final boolean isClick = "click".equals(event.getNativeEvent().getType());

				if (isClick)
				{
					/*
					 * selectedSearchImage will be null until an image is
					 * selected for the first time
					 */
					final boolean needToAddImageView = selectedSearchItem == null;

					selectedSearchItem = event.getValue();
					displayedItem = null;

					final RESTCalls.RESTCallback<RESTTagV1> callback = new RESTCalls.RESTCallback<RESTTagV1>()
					{
						@Override
						public void begin()
						{
							startProcessing();
						}

						@Override
						public void generalException(final Exception ex)
						{
							stopProcessing();
							Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
						}

						@Override
						public void success(final RESTTagV1 retValue)
						{
							try
							{
								/*
								 * If this is the first image selected, display
								 * the image view
								 */
								if (needToAddImageView)
								{
									displayedView = resultDisplay;
								}

								displayedItem = retValue;
								reInitialiseView();
							}
							finally
							{
								stopProcessing();
							}
						}

						@Override
						public void failed()
						{
							stopProcessing();
							Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
						}

					};

					RESTCalls.getTag(callback, selectedSearchItem.getId());
				}
			}
		});
	}

	@Override
	public void parseToken(final String historyToken)
	{
		queryString = historyToken.replace(TagsFilteredResultsAndTagView.HISTORY_TOKEN + ";", "");
		if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX))
			queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;

		final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
		for (final String queryStringElement : queryStringElements)
		{
			final String[] queryElements = queryStringElement.split("=");

			if (queryElements.length == 2)
			{
				if (queryElements[0].equals("tagIds"))
				{
					this.filteredResultsDisplay.getIdFilter().setText(queryElements[1]);
				}
				else if (queryElements[0].equals("tagName"))
				{
					this.filteredResultsDisplay.getNameFilter().setText(queryElements[1]);
				}
				else if (queryElements[0].equals("tagDesc"))
				{
					this.filteredResultsDisplay.getDescriptionFilter().setText(queryElements[1]);
				}
			}
		}
	}

	protected void stopProcessing()
	{
		--count;
		if (count == 0)
			display.setSpinnerVisible(false);
	}

	protected void startProcessing()
	{
		++count;
		display.setSpinnerVisible(true);
	}

	@Override
	protected void reInitialiseView()
	{
		/* save any changes as we move between views */
		if (lastDisplayedView == resultDisplay)
			resultDisplay.getDriver().flush();

		/* update the new view */
		if (displayedView != null)
		{
			displayedView.initialize(displayedItem, false);
		}
		
		/* Update the projects list to show the buttons as they apply to a new tag */
		if (displayedView == projectsDisplay)
			projectsDisplay.getProvider().updateRowData(projectTableStartRow, currentProjectsList);

		/* update the display widgets if we have changed displays */
		if (lastDisplayedView != displayedView)
		{
			display.getViewPanel().setWidget(displayedView.getPanel());
			display.getViewActionButtonsPanel().setWidget(displayedView.getTopActionPanel());
		}

		lastDisplayedView = displayedView;
	}
}
