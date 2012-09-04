package org.jboss.pressgangccms.client.local.mvp.presenter;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.ImagePresenterBase;
import org.jboss.pressgangccms.client.local.mvp.view.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

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
public class ImagesFilteredResultsAndImagePresenter extends ImagePresenterBase
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
	private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;
	
	@Inject
	private ImagePresenter.Display imageDisplay;

	private String queryString;

	/** Keeps a reference to the start row */
	private Integer tableStartRow;

	/** Keeps a reference to the list of topics being displayed */
	private List<RESTImageV1> currentList;
	
	/** The currently selected image in the search results */
	private RESTImageV1 selectedSearchImage;
	
	/** used to keep a track of how many rest calls are active */
	int count = 0;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getResultsActionButtonsPanel().setWidget(imageFilteredResultsDisplay.getTopActionPanel());
		display.getResultsPanel().setWidget(imageFilteredResultsDisplay.getPanel());
		
		getLocales();
		
		bind();
	}

	/**
	 * Add behaviour to the UI elements exposed by the views
	 */
	private void bind()
	{
		final AsyncDataProvider<RESTImageV1> provider = generateListProvider();
		imageFilteredResultsDisplay.setProvider(provider);
		
		bindListRowClicks();
		
		bindImageViewButtons(imageDisplay);
	}

	/**
	 * @return A provider to be used for the topic display list
	 */
	private AsyncDataProvider<RESTImageV1> generateListProvider()
	{
		final AsyncDataProvider<RESTImageV1> provider = new AsyncDataProvider<RESTImageV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTImageV1> display)
			{
				tableStartRow = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = tableStartRow + length;

				final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>()
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
					public void success(final RESTImageCollectionV1 retValue)
					{
						try
						{
							currentList = retValue.getItems();
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

				RESTCalls.getImagesFromQuery(callback, queryString, tableStartRow, end);
			}
		};
		return provider;
	}
	
	/**
	 * Bind the button click events for the topic editor screens
	 */
	private void bindListRowClicks()
	{
		imageFilteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTImageV1>()
		{
			@Override
			public void onCellPreview(final CellPreviewEvent<RESTImageV1> event)
			{
				/* Check to see if this was a click event */
				final boolean isClick = "click".equals(event.getNativeEvent().getType());

				if (isClick)
				{
					/* selectedSearchImage will be null until an image is selected for the first time */
					final boolean needToAddImageView = selectedSearchImage == null;
					
					selectedSearchImage = event.getValue();
					displayedImage = null;

					final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>()
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
						public void success(final RESTImageV1 retValue)
						{
							try
							{
								displayedImage = retValue;
								reInitialiseImageView();
								
								/* If this is the first image selected, display the image view */
								if (needToAddImageView)
								{
									display.getViewPanel().setWidget(imageDisplay.getPanel());
									display.getViewActionButtonsPanel().setWidget(imageDisplay.getTopActionPanel());
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

					RESTCalls.getImage(callback, selectedSearchImage.getId());
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
	protected void reInitialiseImageView()
	{
		imageDisplay.initialize(displayedImage, getUnassignedLocales().toArray(new String[0]));

		bindImageUploadButtons(imageDisplay);
	}
}
