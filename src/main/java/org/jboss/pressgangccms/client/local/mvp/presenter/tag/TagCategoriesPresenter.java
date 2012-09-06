package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagProjectsView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

/**
 * The presenter that provides the logic for the tag category relationships.
 * @author matthew
 *
 */
@Dependent
public class TagCategoriesPresenter extends TemplatePresenter
{
	public interface Display extends TagViewInterface
	{
		AsyncDataProvider<RESTCategoryV1> getProvider();

		void setProvider(final AsyncDataProvider<RESTCategoryV1> provider);

		CellTable<RESTCategoryV1> getResults();

		SimplePager getPager();
		
		Column<RESTCategoryV1, String> getButtonColumn();
		
		Column<RESTTagV1, String> getTagDownButtonColumn();

		Column<RESTTagV1, String> getTagUpButtonColumn();

		AsyncDataProvider<RESTTagV1> getTagsProvider();

		void setTagsProvider(AsyncDataProvider<RESTTagV1> tagsProvider);
		
		VerticalPanel getTagsResultsPanel();
		
		SplitLayoutPanel getSplit();
	}

	@Inject
	private Display display;
	
	private String queryString;

	@Override
	public void parseToken(final String searchToken)
	{
		queryString = searchToken.replace(TagProjectsView.HISTORY_TOKEN + ";", "");
	}

	@Override
	public void go(final HasWidgets container)
	{
		container.clear();
		container.add(display.getTopLevelPanel());

		bind();
	}

	private void bind()
	{
		super.bind(display);

		final AsyncDataProvider<RESTCategoryV1> provider = new AsyncDataProvider<RESTCategoryV1>()
		{
			@Override
			protected void onRangeChanged(final HasData<RESTCategoryV1> display)
			{
				final int start = display.getVisibleRange().getStart();
				final int length = display.getVisibleRange().getLength();
				final int end = start + length;

				final RESTCalls.RESTCallback<RESTCategoryCollectionV1> callback = new RESTCalls.RESTCallback<RESTCategoryCollectionV1>()
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
					public void success(final RESTCategoryCollectionV1 retValue)
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
				
				RESTCalls.getCategoriesFromQuery(callback, queryString, start, end);
			}
		};

		display.setProvider(provider);
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
