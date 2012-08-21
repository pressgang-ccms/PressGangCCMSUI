package org.jboss.pressgangccms.client.local.presenter;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class SearchResultsAndTopicPresenter extends TemplatePresenter
{
	public interface Display extends BaseTemplateViewInterface
	{
		SimpleLayoutPanel getTopicResultsPanel();

		SimpleLayoutPanel getTopicViewPanel();

		SimpleLayoutPanel getTopicViewActionButtonsPanel();

		SimpleLayoutPanel getTopicResultsActionButtonsPanel();

		HanldedSplitLayoutPanel getSplitPanel();
	}

	/**
	 * A click handler to remove a tag from a topic
	 * 
	 * @author Matthew Casperson
	 * 
	 */
	private class DeleteTagClickHandler implements ClickHandler
	{
		private final RESTTagV1 tag;

		public DeleteTagClickHandler(final RESTTagV1 tag)
		{
			if (tag == null)
				throw new IllegalArgumentException("tag cannot be null");

			this.tag = tag;
		}

		@Override
		public void onClick(final ClickEvent event)
		{
			if (selectedTopic == null)
				throw new IllegalStateException("selectedTopic cannot be null");

			tag.setRemoveItem(true);
			updateTopicTagViewDisplay();
		}
	};

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

	@Inject
	private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;

	@Inject
	private TopicTagsPresenter.Display topicTagsDisplay;
	
	/** used to keep a track of how many rest calls are active */
	int count = 0;

	/**
	 * This will reference the selected view, so as to maintain the view between
	 * clicks
	 */
	private TopicViewInterface selectedView;

	private String queryString;

	private RESTTopicV1 selectedTopic;

	/** Keeps a reference to the list of topics being displayed */
	private List<RESTTopicV1> currentList;

	private final SearchUIProjects searchUIProjects = new SearchUIProjects();

	/** Keeps a reference to the start row */
	private Integer tableStartRow;

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		display.getTopicResultsActionButtonsPanel().setWidget(searchResultsDisplay.getTopActionPanel());
		display.getTopicResultsPanel().setWidget(searchResultsDisplay.getPanel());

		bind();

		
	}

	/**
	 * Gets the tags, so they can be displayed and added to topics
	 */
	private void getTags()
	{
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
				stopProcessing();
			}

			@Override
			public void success(final RESTTagCollectionV1 retValue)
			{
				try
				{
					searchUIProjects.initialize(retValue);
					topicTagsDisplay.initializeNewTags(searchUIProjects);
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

		RESTCalls.getTags(callback);
	}

	private void bind()
	{
		super.bind(display);

		bindSplitPanelResize();

		final AsyncDataProvider<RESTTopicV1> provider = generateTopicListProvider();

		bindTopicListRowClicks();

		bindTopicEditButtons(provider);

		bindAceEditorButtons();

		bindNewTagListBoxes();

		searchResultsDisplay.setProvider(provider);
		
		getTags();
	}

	private void bindNewTagListBoxes()
	{
		topicTagsDisplay.getProjects().addValueChangeHandler(new ValueChangeHandler<SearchUIProject>()
		{
			@Override
			public void onValueChange(final ValueChangeEvent<SearchUIProject> event)
			{
				topicTagsDisplay.updateNewTagCategoriesDisplay();
			}
		});

		topicTagsDisplay.getCategories().addValueChangeHandler(new ValueChangeHandler<SearchUICategory>()
		{
			@Override
			public void onValueChange(final ValueChangeEvent<SearchUICategory> event)
			{
				topicTagsDisplay.updateNewTagTagDisplay();
			}
		});
	}

	private void bindTagEditingButtons()
	{

		for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTagsDisplay.getEditor().projects.getEditors())
		{
			for (final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories.getEditors())
			{
				for (final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors())
				{
					topicTagViewTagEditor.getDelete().addClickHandler(new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag()));
				}
			}
		}
	}

	/**
	 * Respond to the split panel resizing by redisplaying the ACE editor
	 * component
	 */
	private void bindSplitPanelResize()
	{
		display.getSplitPanel().addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(final ResizeEvent event)
			{
				if (topicXMLDisplay.getEditor() != null)
					topicXMLDisplay.getEditor().redisplay();
			}
		});
	}

	/**
	 * @return A provider to be used for the topic display list
	 */
	private AsyncDataProvider<RESTTopicV1> generateTopicListProvider()
	{
		final AsyncDataProvider<RESTTopicV1> provider = new AsyncDataProvider<RESTTopicV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTTopicV1> display)
			{
				tableStartRow = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = tableStartRow + length;

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
						Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
						stopProcessing();
					}

					@Override
					public void success(final RESTTopicCollectionV1 retValue)
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
						Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
						stopProcessing();
					}
				};

				RESTCalls.getTopicsFromQuery(callback, queryString, tableStartRow, end);
			}
		};
		return provider;
	}

	/**
	 * Bind the button click events for the topic editor screens
	 */
	private void bindTopicListRowClicks()
	{
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

					/* default to the rendered view */
					if (selectedView == null)
						selectedView = topicRenderedDisplay;

					updateTopicDisplay();
				}
			}
		});
	}

	/**
	 * Bind the button clicks for the ACE editor buttons
	 */
	private void bindAceEditorButtons()
	{
		topicXMLDisplay.getLineWrap().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				topicXMLDisplay.getEditor().setUseWrapMode(!topicXMLDisplay.getEditor().getUserWrapMode());
				topicXMLDisplay.getLineWrap().setDown(topicXMLDisplay.getEditor().getUserWrapMode());
			}
		});

		topicXMLDisplay.getShowInvisibles().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				topicXMLDisplay.getEditor().setShowInvisibles(!topicXMLDisplay.getEditor().getShowInvisibles());
				topicXMLDisplay.getShowInvisibles().setDown(topicXMLDisplay.getEditor().getShowInvisibles());
			}
		});
	}

	/**
	 * Bind the button click events for the various topic views
	 * 
	 * @param provider
	 *            The provider created by the generateTopicListProvider() method
	 */
	private void bindTopicEditButtons(final AsyncDataProvider<RESTTopicV1> provider)
	{
		/* Build up a click handler to save the topic */
		final ClickHandler saveClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				if (selectedTopic != null)
				{
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
							Window.alert(PressGangCCMSUI.INSTANCE.ErrorSavingTopic());
							stopProcessing();
							topicXMLDisplay.getEditor().redisplay();
						}

						@Override
						public void success(final RESTTopicV1 retValue)
						{
							try
							{
								/* Update the local collection of topics */
								retValue.cloneInto(selectedTopic, true);
								/* Update the list of topics */
								provider.updateRowData(tableStartRow, currentList);
								/* Update the edit window */
								selectedView.initialize(selectedTopic);

								Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
								retValue.getTags().getItems();
							}
							finally
							{
								stopProcessing();
								if (topicXMLDisplay.getEditor() != null)
									topicXMLDisplay.getEditor().redisplay();
							}
						}

						@Override
						public void failed()
						{
							Window.alert(PressGangCCMSUI.INSTANCE.ErrorSavingTopic());
							stopProcessing();
							topicXMLDisplay.getEditor().redisplay();
						}
					};

					/* Sync any changes back to the underlying object */
					flushChanges();

					/*
					 * Create a new instance of the topic, with all the
					 * properties set to explicitly update
					 */
					final RESTTopicV1 updateTopic = selectedTopic.clone(true);
					updateTopic.explicitSetBugzillaBugs_OTM(updateTopic.getBugzillaBugs_OTM());
					updateTopic.explicitSetProperties(updateTopic.getProperties());
					updateTopic.explicitSetSourceUrls_OTM(updateTopic.getSourceUrls_OTM());
					updateTopic.explicitSetTags(updateTopic.getTags());
					updateTopic.explicitSetDescription(updateTopic.getDescription());
					updateTopic.explicitSetLocale(updateTopic.getLocale());
					updateTopic.explicitSetTitle(updateTopic.getTitle());
					updateTopic.explicitSetXml(updateTopic.getXml());

					RESTCalls.saveTopic(callback, updateTopic);
				}
			}
		};

		final ClickHandler topicViewClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Sync any changes back to the underlying object */
				flushChanges();

				if (selectedTopic != null)
				{
					selectedView = topicViewDisplay;
					updateTopicDisplay();
				}
			}
		};

		final ClickHandler topicXMLClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Sync any changes back to the underlying object */
				flushChanges();

				if (selectedTopic != null)
				{
					selectedView = topicXMLDisplay;
					updateTopicDisplay();

				}
			}
		};

		final ClickHandler topicRenderedClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Sync any changes back to the underlying object */
				flushChanges();

				if (selectedTopic != null)
				{
					selectedView = topicRenderedDisplay;
					updateTopicDisplay();
				}
			}
		};

		final ClickHandler topicXMLErrorsClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Sync any changes back to the underlying object */
				flushChanges();

				if (selectedTopic != null)
				{
					selectedView = topicXMLErrorsDisplay;
					updateTopicDisplay();
				}
			}
		};

		final ClickHandler topicTagsClickHandler = new ClickHandler()
		{
			@Override
			public void onClick(final ClickEvent event)
			{
				/* Sync any changes back to the underlying object */
				flushChanges();

				if (selectedTopic != null)
				{
					selectedView = topicTagsDisplay;
					updateTopicTagViewDisplay();
				}
			}
		};

		/* Hook up the click listeners */
		for (final TopicViewInterface view : new TopicViewInterface[]
		{ topicViewDisplay, topicXMLDisplay, topicRenderedDisplay, topicXMLErrorsDisplay, topicTagsDisplay })
		{
			view.getFields().addClickHandler(topicViewClickHandler);
			view.getXml().addClickHandler(topicXMLClickHandler);
			view.getRendered().addClickHandler(topicRenderedClickHandler);
			view.getSave().addClickHandler(saveClickHandler);
			view.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
			view.getTags().addClickHandler(topicTagsClickHandler);
		}
	}

	/**
	 * The topic tag view requires each remove button to be re-bound once the
	 * view is initialized
	 */
	private void updateTopicTagViewDisplay()
	{
		updateTopicDisplay();
		bindTagEditingButtons();
	}

	/**
	 * Sets the topic display view
	 */
	private void updateTopicDisplay()
	{
		selectedView.initialize(selectedTopic);

		display.getTopicViewActionButtonsPanel().clear();
		display.getTopicViewPanel().clear();

		display.getTopicViewActionButtonsPanel().setWidget(selectedView.getTopActionPanel());
		display.getTopicViewPanel().setWidget(selectedView.getPanel());

		/* Need to redisplay to work around a bug in the ACE editor */
		if (selectedView == this.topicXMLDisplay)
		{
			topicXMLDisplay.getLineWrap().setDown(topicXMLDisplay.getEditor().getUserWrapMode());
			topicXMLDisplay.getShowInvisibles().setDown(topicXMLDisplay.getEditor().getShowInvisibles());
			topicXMLDisplay.getEditor().redisplay();
		}
	}

	/**
	 * Sync any changes back to the underlying object
	 */
	private void flushChanges()
	{
		if (selectedView == null || selectedView.getDriver() == null)
			return;

		/* These are read only views */
		if (selectedView == topicXMLErrorsDisplay || selectedView == topicTagsDisplay)
			return;

		selectedView.getDriver().flush();
	}

	@Override
	public void parseToken(final String historyToken)
	{
		queryString = historyToken.replace(SearchResultsAndTopicView.HISTORY_TOKEN + ";", "");
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
