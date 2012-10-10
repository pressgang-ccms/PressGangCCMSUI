package org.jboss.pressgang.ccms.ui.client.local;

import com.google.common.base.Optional;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create.CreateTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppController implements Presenter, ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;

    @Inject
    private HandlerManager eventBus;

    private HasWidgets container;

    public void bind() {
        History.addValueChangeHandler(this);
        eventBus.addHandler(SearchViewEvent.TYPE, new ViewOpenEventHandler(SearchPresenter.HISTORY_TOKEN));
        eventBus.addHandler(SearchResultsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(SearchResultsPresenter.HISTORY_TOKEN));
        eventBus.addHandler(SearchResultsAndTopicViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                SearchResultsAndTopicPresenter.HISTORY_TOKEN));
        eventBus.addHandler(ImagesViewEvent.TYPE, new ViewOpenEventHandler(ImagePresenter.HISTORY_TOKEN));
        eventBus.addHandler(ImagesFilteredResultsAndImageViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN));
        eventBus.addHandler(TagsFilteredResultsAndTagViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN));
        eventBus.addHandler(CategoriesFilteredResultsAndCategoryViewEvent.TYPE, new ViewOpenWithQueryEventHandler(
                CategoriesFilteredResultsAndCategoryPresenter.HISTORY_TOKEN));
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
            Optional<TemplatePresenter> presenter = Optional.absent();

            if (token.startsWith(WelcomePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(WelcomePresenter.class);
            } else if (token.startsWith(SearchPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchPresenter.class);
            } else if (token.startsWith(SearchResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchResultsPresenter.class);
            } else if (token.startsWith(TopicPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TopicPresenter.class);
            } else if (token.startsWith(TagPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagPresenter.class);
            } else if (token.startsWith(SearchResultsAndTopicPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchResultsAndTopicPresenter.class);
            } else if (token.startsWith(ImagePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImagePresenter.class);
            } else if (token.startsWith(ImagesFilteredResultsAndImagePresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImagesFilteredResultsAndImagePresenter.class);
            } else if (token.startsWith(TagsFilteredResultsAndTagPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagsFilteredResultsAndTagPresenter.class);
            } else if (token.startsWith(ImageFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(ImageFilteredResultsPresenter.class);
            } else if (token.startsWith(CategoryFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoryFilteredResultsPresenter.class);
            } else if (token.startsWith(CategoryPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoryPresenter.class);
            } else if (token.startsWith(CategoriesFilteredResultsAndCategoryPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CategoriesFilteredResultsAndCategoryPresenter.class);
            } else if (token.startsWith(TagFilteredResultsPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(TagFilteredResultsPresenter.class);
            } else if (token.startsWith(CreateTopicPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(CreateTopicPresenter.class);
            } else if (token.startsWith(SearchFieldPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchFieldPresenter.class);
            }else if (token.startsWith(SearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN)) {
                presenter = getBeanInstance(SearchTagsFieldsAndFiltersPresenter.class);
            }

            if (presenter.isPresent()) {
                presenter.get().parseToken(token);
                presenter.get().go(container);
            }
        }
    }

    private <T extends TemplatePresenter> Optional<TemplatePresenter> getBeanInstance(Class<T> presenterType) {
        final IOCBeanDef<T> bean = manager.lookupBean(presenterType);
        if (bean != null) {
            final TemplatePresenter presenter = bean.getInstance();
            return Optional.of(presenter);
        }
        return Optional.absent();
    }
}
