package org.jboss.pressgangccms.client.local;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgangccms.client.local.events.ImagesViewEvent;
import org.jboss.pressgangccms.client.local.events.ImagesViewEventHandler;
import org.jboss.pressgangccms.client.local.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgangccms.client.local.events.SearchResultsAndTopicViewEventHandler;
import org.jboss.pressgangccms.client.local.events.SearchResultsViewEvent;
import org.jboss.pressgangccms.client.local.events.SearchResultsViewEventHandler;
import org.jboss.pressgangccms.client.local.events.SearchViewEvent;
import org.jboss.pressgangccms.client.local.events.SearchViewEventHandler;
import org.jboss.pressgangccms.client.local.presenter.ImagePresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.ImageView;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicView;
import org.jboss.pressgangccms.client.local.view.WelcomeView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

@ApplicationScoped
public class AppController implements Presenter, ValueChangeHandler<String>
{
	@Inject
	private IOCBeanManager manager;

	@Inject
	private HandlerManager eventBus;

	private HasWidgets container;

	public void bind()
	{
		History.addValueChangeHandler(this);

		eventBus.addHandler(SearchViewEvent.TYPE, new SearchViewEventHandler()
		{
			@Override
			public void onSearchViewOpen(final SearchViewEvent event)
			{
				History.newItem(SearchView.HISTORY_TOKEN);
			}
		});
		
		eventBus.addHandler(SearchResultsViewEvent.TYPE, new SearchResultsViewEventHandler()
		{
			@Override
			public void onSearchResultsViewOpen(final SearchResultsViewEvent event)
			{
				History.newItem(SearchResultsView.HISTORY_TOKEN);
			}
		});
		
		eventBus.addHandler(SearchResultsAndTopicViewEvent.TYPE, new SearchResultsAndTopicViewEventHandler()
		{
			@Override
			public void onSearchResultsViewOpen(final SearchResultsAndTopicViewEvent event)
			{
				History.newItem(SearchResultsAndTopicView.HISTORY_TOKEN + ";" + event.getQuery());
			}
		});
		
		eventBus.addHandler(ImagesViewEvent.TYPE, new ImagesViewEventHandler()
		{
			@Override
			public void onImagesViewOpen(final ImagesViewEvent event)
			{
				History.newItem(ImageView.HISTORY_TOKEN);
			}
		});
	}

	@Override
	public void go(final HasWidgets container)
	{
		this.container = container;
		bind();

		if ("".equals(History.getToken()))
		{
			History.newItem(WelcomeView.HISTORY_TOKEN);
		}
		else
		{
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(final ValueChangeEvent<String> event)
	{
		final String token = event.getValue();
		if (token != null)
		{
			TemplatePresenter presenter = null;

			if (token.startsWith(WelcomeView.HISTORY_TOKEN))
			{
				final IOCBeanDef<WelcomePresenter> bean = manager.lookupBean(WelcomePresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.startsWith(SearchView.HISTORY_TOKEN))
			{
				final IOCBeanDef<SearchPresenter> bean = manager.lookupBean(SearchPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.startsWith(SearchResultsView.HISTORY_TOKEN))
			{
				final IOCBeanDef<SearchResultsPresenter> bean = manager.lookupBean(SearchResultsPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.startsWith(TopicView.HISTORY_TOKEN))
			{
				final IOCBeanDef<TopicPresenter> bean = manager.lookupBean(TopicPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.startsWith(SearchResultsAndTopicView.HISTORY_TOKEN))
			{
				final IOCBeanDef<SearchResultsAndTopicPresenter> bean = manager.lookupBean(SearchResultsAndTopicPresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}
			else if (token.startsWith(ImageView.HISTORY_TOKEN))
			{
				final IOCBeanDef<ImagePresenter> bean = manager.lookupBean(ImagePresenter.class);
				if (bean != null)
				{
					presenter = bean.getInstance();
				}
			}

			if (presenter != null)
			{
				presenter.parseToken(token);
				presenter.go(container);
			}
		}
	}
}
