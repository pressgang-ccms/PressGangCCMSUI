package org.jboss.pressgangccms.client.local.mvp.presenter;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
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
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

@Dependent
public class ImagesFilteredResultsAndImagePresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		SimpleLayoutPanel getResultsPanel();

		SimpleLayoutPanel getViewPanel();

		SimpleLayoutPanel getViewActionButtonsPanel();

		SimpleLayoutPanel getResultsActionButtonsPanel();

		HanldedSplitLayoutPanel getSplitPanel();

		DockLayoutPanel getViewLayoutPanel();

		void initialize(final Panel panel);
	}

	@Inject
	private Display display;

	@Inject
	private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;

	private String queryString;

	/** Keeps a reference to the start row */
	private Integer tableStartRow;

	/** Keeps a reference to the list of topics being displayed */
	private List<RESTImageV1> currentList;
	
	/** used to keep a track of how many rest calls are active */
	int count = 0;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getResultsActionButtonsPanel().setWidget(imageFilteredResultsDisplay.getTopActionPanel());
		display.getResultsPanel().setWidget(imageFilteredResultsDisplay.getPanel());
		
		bind();
	}

	/**
	 * Add behaviour to the UI elements exposed by the views
	 */
	private void bind()
	{
		final AsyncDataProvider<RESTImageV1> provider = generateListProvider();
		imageFilteredResultsDisplay.setProvider(provider);
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

	@Override
	public void parseToken(final String historyToken)
	{
		queryString = historyToken.replace(ImagesFilteredResultsAndImageView.HISTORY_TOKEN + ";", "");
		if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX))
			queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
	}
	
	private void stopProcessing()
	{
		--count;
		if (count == 0)
			display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		++count;
		display.setSpinnerVisible(true);
	}
}
