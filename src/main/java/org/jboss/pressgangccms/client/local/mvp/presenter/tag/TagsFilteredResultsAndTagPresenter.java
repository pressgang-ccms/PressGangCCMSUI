package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.ImagePresenterBase;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TagPresenterBase;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

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

	@Inject
	private Display display;

	@Inject
	private TagFilteredResultsPresenter.Display filteredResultsDisplay;

	@Inject
	private TagPresenter.Display resultDisplay;

	private String queryString;

	/** Keeps a reference to the start row */
	private Integer tableStartRow;

	/** Keeps a reference to the list of topics being displayed */
	private List<RESTTagV1> currentList;

	/** The currently selected item in the search results */
	private RESTTagV1 selectedSearchItem;
	
	/** The currently selected item in the search results */
	private RESTTagV1 displayedItem;

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

		final AsyncDataProvider<RESTTagV1> provider = generateListProvider();
		filteredResultsDisplay.setProvider(provider);

		bindListRowClicks();

		bindViewButtons(resultDisplay);

		bindSearchButtons();
	}

	protected void bindSearchButtons()
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
	 * @return A provider to be used for the topic display list
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
								displayedItem = retValue;
								reInitialiseView();

								/*
								 * If this is the first image selected, display
								 * the image view
								 */
								if (needToAddImageView)
								{
									display.getViewPanel().setWidget(resultDisplay.getPanel());
									display.getViewActionButtonsPanel().setWidget(resultDisplay.getTopActionPanel());
								}
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
		queryString = historyToken.replace(ImagesFilteredResultsAndImageView.HISTORY_TOKEN + ";", "");
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
		resultDisplay.initialize(displayedItem, false);
	}
}
