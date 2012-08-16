package org.jboss.pressgangccms.client.local.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	private TopicXMLPresenter.Display topicXMLDisplay;

	@Inject
	private TopicRenderedPresenter.Display topicRenderedDisplay;

	@Inject
	private SearchResultsPresenter.Display searchResultsDisplay;

	/**
	 * This will reference the selected view, so as to maintain the view between
	 * clicks
	 */
	private TopicViewInterface selectedView;

	private String queryString;

	private RESTTopicV1 selectedTopic;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getTopicResultsActionButtonsPanel().setWidget(searchResultsDisplay.getTopActionPanel());
		display.getTopicResultsPanel().setWidget(searchResultsDisplay.getPanel());

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

		/* Respond to row clicks */

		searchResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTopicV1>()
		{
			@Override
			public void onCellPreview(final CellPreviewEvent<RESTTopicV1> event)
			{
				/* Check to see if this was a click event */
				final boolean isClick = "click".equals(event.getNativeEvent().getType());

				if (isClick)
				{
					selectedTopic = event.getValue();

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
								/* default to the topic details view */
								if (selectedView == null)
								{
									selectedView = topicViewDisplay;
									updateTopicDisplay();
								}

								selectedView.initialize(retValue);
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

					RESTCalls.getTopic(callback, selectedTopic.getId());
				}
			}
		});

		final ClickHandler topicViewClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				if (selectedTopic != null)
				{
					selectedView = topicViewDisplay;
					selectedView.initialize(selectedTopic);
					updateTopicDisplay();
				}
			}
		};

		final ClickHandler topicXMLClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				if (selectedTopic != null)
				{
					selectedView = topicXMLDisplay;
					selectedView.initialize(selectedTopic);
					updateTopicDisplay();
				}
			}
		};

		final ClickHandler topicRenderedClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				if (selectedTopic != null)
				{
					selectedView = topicRenderedDisplay;
					topicRenderedDisplay.initialize(selectedTopic);
					updateTopicDisplay();
				}
			}
		};

		topicViewDisplay.getFields().addClickHandler(topicViewClickHandler);
		topicViewDisplay.getXml().addClickHandler(topicXMLClickHandler);
		topicViewDisplay.getRendered().addClickHandler(topicRenderedClickHandler);

		topicXMLDisplay.getFields().addClickHandler(topicViewClickHandler);
		topicXMLDisplay.getXml().addClickHandler(topicXMLClickHandler);
		topicXMLDisplay.getRendered().addClickHandler(topicRenderedClickHandler);

		topicRenderedDisplay.getFields().addClickHandler(topicViewClickHandler);
		topicRenderedDisplay.getXml().addClickHandler(topicXMLClickHandler);
		topicRenderedDisplay.getRendered().addClickHandler(topicRenderedClickHandler);

		topicXMLDisplay.getLineWrap().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				topicXMLDisplay.getEditor().setUseWrapMode(!topicXMLDisplay.getEditor().getUserWrapMode());
			}
		});

		searchResultsDisplay.setProvider(provider);
	}

	/**
	 * Sets the topic display view
	 */
	private void updateTopicDisplay()
	{
		display.getTopicViewActionButtonsPanel().clear();
		display.getTopicViewPanel().clear();

		display.getTopicViewActionButtonsPanel().setWidget(selectedView.getTopActionPanel());
		display.getTopicViewPanel().setWidget(selectedView.getPanel());
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
