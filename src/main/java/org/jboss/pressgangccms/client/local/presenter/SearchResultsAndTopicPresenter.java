package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

@Dependent
public class SearchResultsAndTopicPresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		SimplePanel getTopicResultsPanel();

		SimplePanel getTopicViewPanel();
		
		SimplePanel getTopicViewActionButtonsPanel();
		
		SimplePanel getTopicResultsActionButtonsPanel();
	}

	@Inject
	private Display display;

	@Inject
	private TopicPresenter.Display topicViewDisplay;

	@Inject
	private SearchResultsPresenter.Display searchResultsDisplay;

	private String queryString;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getTopicResultsActionButtonsPanel().setWidget(searchResultsDisplay.getTopActionPanel());
		display.getTopicResultsPanel().setWidget(searchResultsDisplay.getPanel());
		display.getTopicViewActionButtonsPanel().setWidget(topicViewDisplay.getTopActionPanel());
		display.getTopicViewPanel().setWidget(topicViewDisplay.getPanel());

		bind();
	}

	private void bind()
	{
		super.bind(display);

		final AsyncDataProvider<RESTTopicV1> provider = new AsyncDataProvider<RESTTopicV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTTopicV1> display)
			{
				final int start = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = start + length;

				final RESTCalls.RESTCallback<RESTTopicCollectionV1> callback = new RESTCalls.RESTCallback<RESTTopicCollectionV1>()
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
					}

					@Override
					public void success(final RESTTopicCollectionV1 retValue)
					{
						try
						{
							updateRowData(start, retValue.getItems());
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
					}
				};

				RESTCalls.getTopicsFromQuery(callback, queryString, start, end);
			}
		};

		/* Respone to row clicks */

		searchResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTopicV1>()
		{
			@Override
			public void onCellPreview(final CellPreviewEvent<RESTTopicV1> event)
			{
				/* Check to see if this was a click event */
				final boolean isClick = "click".equals(event.getNativeEvent().getType());

				if (isClick)
				{
					final Integer id = event.getValue().getId();

					final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>()
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
						}

						@Override
						public void success(final RESTTopicV1 retValue)
						{
							try
							{
								topicViewDisplay.initialize(retValue);
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
						}
					};

					RESTCalls.getTopic(callback, id);
				}
			}
		});

		searchResultsDisplay.setProvider(provider);
	}

	@Override
	public void parseToken(final String historyToken)
	{
		queryString = historyToken.replace(SearchResultsAndTopicView.HISTORY_TOKEN + ";", "");
	}

	private void stopProcessing()
	{
		display.setSpinnerVisible(false);
	}

	private void startProcessing()
	{
		display.setSpinnerVisible(true);
	}
}
