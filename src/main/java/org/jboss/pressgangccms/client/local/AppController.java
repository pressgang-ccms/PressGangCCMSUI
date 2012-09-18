package org.jboss.pressgangccms.client.local;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgangccms.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.ImagesFilteredResultsViewAndImageEventHandler;
import org.jboss.pressgangccms.client.local.mvp.events.ImagesViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.ImagesViewEventHandler;
import org.jboss.pressgangccms.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.SearchResultsAndTopicViewEventHandler;
import org.jboss.pressgangccms.client.local.mvp.events.SearchResultsViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.SearchResultsViewEventHandler;
import org.jboss.pressgangccms.client.local.mvp.events.SearchViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.SearchViewEventHandler;
import org.jboss.pressgangccms.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgangccms.client.local.mvp.events.TagsFilteredResultsAndTagViewEventHandler;
import org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.WelcomeView;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImageView;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagsFilteredResultsAndTagView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicView;
import org.jboss.pressgangccms.client.local.mvp.view.topicsearch.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.mvp.view.topicsearch.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.topicsearch.SearchView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

@ApplicationScoped
public class AppController implements Presenter, ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;

    @Inject
    private HandlerManager eventBus;

    private HasWidgets container;

    public void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(SearchViewEvent.TYPE, new SearchViewEventHandler() {
            @Override
            public void onSearchViewOpen(final SearchViewEvent event) {
                History.newItem(SearchPresenter.HISTORY_TOKEN);
            }
        });

        eventBus.addHandler(SearchResultsViewEvent.TYPE, new SearchResultsViewEventHandler() {
            @Override
            public void onSearchResultsViewOpen(final SearchResultsViewEvent event) {
                History.newItem(SearchResultsPresenter.HISTORY_TOKEN);
            }
        });

        eventBus.addHandler(SearchResultsAndTopicViewEvent.TYPE, new SearchResultsAndTopicViewEventHandler() {
            @Override
            public void onSearchResultsViewOpen(final SearchResultsAndTopicViewEvent event) {
                History.newItem(SearchResultsAndTopicPresenter.HISTORY_TOKEN + ";" + event.getQuery());
            }
        });

        eventBus.addHandler(ImagesViewEvent.TYPE, new ImagesViewEventHandler() {
            @Override
            public void onImagesViewOpen(final ImagesViewEvent event) {
                History.newItem(ImagePresenter.HISTORY_TOKEN);
            }
        });

        eventBus.addHandler(ImagesFilteredResultsAndImageViewEvent.TYPE, new ImagesFilteredResultsViewAndImageEventHandler() {
            @Override
            public void onImagesFilteredResultsAndImageViewOpen(final ImagesFilteredResultsAndImageViewEvent event) {
                History.newItem(ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN + ";" + event.getQuery());
            }
        });

        eventBus.addHandler(TagsFilteredResultsAndTagViewEvent.TYPE, new TagsFilteredResultsAndTagViewEventHandler() {
            @Override
            public void onTagsFilteredResultsViewAndTagOpen(final TagsFilteredResultsAndTagViewEvent event) {
                History.newItem(TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN + ";" + event.getQuery());
            }
        });

    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;
        bind();

        if ("".equals(History.getToken())) {
            History.newItem(WelcomePresenter.HISTORY_TOKEN);
        } else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        final String token = event.getValue();
        if (token != null) {
            TemplatePresenter presenter = null;

            if (token.startsWith(WelcomePresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<WelcomePresenter> bean = manager.lookupBean(WelcomePresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(SearchPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<SearchPresenter> bean = manager.lookupBean(SearchPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(SearchResultsPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<SearchResultsPresenter> bean = manager.lookupBean(SearchResultsPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(TopicPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<TopicPresenter> bean = manager.lookupBean(TopicPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(TagPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<TagPresenter> bean = manager.lookupBean(TagPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(SearchResultsAndTopicPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<SearchResultsAndTopicPresenter> bean = manager
                        .lookupBean(SearchResultsAndTopicPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(ImagePresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<ImagePresenter> bean = manager.lookupBean(ImagePresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<ImagesFilteredResultsAndImagePresenter> bean = manager
                        .lookupBean(ImagesFilteredResultsAndImagePresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            } else if (token.startsWith(TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN)) {
                final IOCBeanDef<TagsFilteredResultsAndTagPresenter> bean = manager
                        .lookupBean(TagsFilteredResultsAndTagPresenter.class);
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            }

            if (presenter != null) {
                presenter.parseToken(token);
                presenter.go(container);
            }
        }
    }
}
