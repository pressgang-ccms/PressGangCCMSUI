package org.jboss.pressgang.ccms.ui.client.local;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Optional;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.DocBuilderPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.SysInfoPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FileFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.file.FilesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic.TranslatedTopicSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.translatedtopic.TranslatedTopicSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics.TranslatedTopicFilteredResultsAndDetailsPresenter;
import org.jetbrains.annotations.NotNull;

/**
 * This class watches the event bus for page change requests, and instructs the appropriate presenters
 * to display when a page change event is received.
 */
@ApplicationScoped
public class AppController implements PresenterInterface, ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;

    @Inject
    private EventBus eventBus;

    private HasWidgets container;

    private BaseTemplatePresenterInterface lastPresenter;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AppController.class.getName());

    /**
     * Default constructor. Does nothing.
     */
    public AppController() {

    }

    /**
     * Adds event handlers to the event bus.
     */
    public void bind() {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.bind()");

            History.addValueChangeHandler(this);

            this.eventBus.addHandler(SysInfoViewEvent.TYPE, new ViewOpenEventHandler(SysInfoPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(DocBuilderViewEvent.TYPE, new ViewOpenEventHandler(DocBuilderPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(WelcomeViewEvent.TYPE, new ViewOpenEventHandler(WelcomePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchViewEvent.TYPE, new ViewOpenEventHandler(SearchTagPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchResultsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TopicFilteredResultsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TopicSearchResultsAndTopicViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesViewEvent.TYPE, new ViewOpenEventHandler(ImagePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesFilteredResultsAndImageViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(ImagesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TagsFilteredResultsAndTagViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TagsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(CategoriesFilteredResultsAndCategoryViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(CategoriesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TopicSearchTagsFieldsAndFiltersViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TopicSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TranslatedSearchTagsFieldsAndFiltersViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TranslatedTopicSearchTagsFieldsAndFiltersPresenter.TRANSLATED_HISTORY_TOKEN));
            this.eventBus.addHandler(ProjectsFilteredResultsAndProjectViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(ProjectsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TranslatedSearchResultsAndTopicViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TranslatedTopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(StringConstantFilteredResultsAndDetailsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(StringConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(IntegerConstantFilteredResultsAndDetailsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(IntegerConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(BlobConstantFilteredResultsAndDetailsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(BlobConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(PropertyTagFilteredResultsAndDetailsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(PropertyTagFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(PropertyCategoryFilteredResultsAndDetailsViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(PropertyCategoryFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(BulkTagSearchTagsFieldsAndFiltersViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TopicSearchTagsFieldsAndFiltersPresenter.BULK_TAG_HISTORY_TOKEN));
            this.eventBus.addHandler(ContentSpecSearchTagsFieldsAndFiltersViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(ContentSpecSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ContentSpecSearchResultsAndContentSpecViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(FilesViewEvent.TYPE, new ViewOpenEventHandler(FilePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(FilesFilteredResultsAndFileViewEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(FilesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(RenderedDiffEvent.TYPE,
                    new ViewOpenWithQueryEventHandler(TopicRevisionsPresenter.HISTORY_TOKEN));

        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.bind()");
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.go()");

            this.container = container;
            bind();

            if ("".equals(History.getToken())) {
                LOGGER.log(Level.INFO, "Setting default history token");
                History.newItem(WelcomePresenter.HISTORY_TOKEN);
            } else {
                LOGGER.log(Level.INFO, "Firing current history token");
                History.fireCurrentHistoryState();
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.go()");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void onValueChange(@NotNull final ValueChangeEvent<String> event) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.onValueChange()");

            final String token = event.getValue();

            if (token != null) {
                Optional<? extends BaseTemplatePresenterInterface> presenter = Optional.absent();

                if (token.startsWith(SysInfoPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(SysInfoPresenter.class);
                } else if (token.startsWith(DocBuilderPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(DocBuilderPresenter.class);
                } else if (token.startsWith(WelcomePresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(WelcomePresenter.class);
                } else if (token.startsWith(SearchTagPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(SearchTagPresenter.class);
                } else if (token.startsWith(TopicFilteredResultsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicFilteredResultsPresenter.class);
                } else if (token.startsWith(TopicPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicPresenter.class);
                } else if (token.startsWith(TopicXMLPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicXMLPresenter.class);
                } else if (token.startsWith(TagPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TagPresenter.class);
                } else if (token.startsWith(TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(ImagePresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ImagePresenter.class);
                } else if (token.startsWith(ImagesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ImagesFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(TagsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TagsFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(ImageFilteredResultsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ImageFilteredResultsPresenter.class);
                } else if (token.startsWith(CategoryFilteredResultsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(CategoryFilteredResultsPresenter.class);
                } else if (token.startsWith(CategoryPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(CategoryPresenter.class);
                } else if (token.startsWith(CategoriesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(CategoriesFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(TagFilteredResultsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TagFilteredResultsPresenter.class);
                } else if (token.startsWith(TopicSearchFieldPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicSearchFieldPresenter.class);
                } else if (token.startsWith(TranslatedTopicSearchFieldPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TranslatedTopicSearchFieldPresenter.class);
                } else if (token.startsWith(TopicSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN) ||
                        token.startsWith(TopicSearchTagsFieldsAndFiltersPresenter.BULK_TAG_HISTORY_TOKEN)) {
                    /*
                        The TopicSearchTagsFieldsAndFiltersView and BulkTagSearchTagsFieldsAndFiltersViewEvent history tokens will launch
                         the search view.
                     */
                    presenter = getBeanInstance(TopicSearchTagsFieldsAndFiltersPresenter.class);
                } else if (token.startsWith(TranslatedTopicSearchTagsFieldsAndFiltersPresenter.TRANSLATED_HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TranslatedTopicSearchTagsFieldsAndFiltersPresenter.class);
                } else if (token.startsWith(ProjectsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ProjectsFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(TopicSearchFilterResultsAndFilterPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicSearchFilterResultsAndFilterPresenter.class);
                } else if (token.startsWith(ContentSpecSearchFilterResultsAndFilterPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ContentSpecSearchFilterResultsAndFilterPresenter.class);
                } else if (token.startsWith(TranslatedTopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TranslatedTopicFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(StringConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(StringConstantFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(IntegerConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(IntegerConstantFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(BlobConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(BlobConstantFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(PropertyTagFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(PropertyTagFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(PropertyCategoryFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(PropertyCategoryFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(TopicRenderedPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicRenderedPresenter.class);
                } else if (token.startsWith(ContentSpecSearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ContentSpecSearchTagsFieldsAndFiltersPresenter.class);
                } else if (token.startsWith(ContentSpecSearchFieldPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ContentSpecSearchFieldPresenter.class);
                } else if (token.startsWith(ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ContentSpecFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(FilePresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(FilePresenter.class);
                } else if (token.startsWith(FilesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(FilesFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(FileFilteredResultsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(FileFilteredResultsPresenter.class);
                } else if (token.startsWith(TopicRevisionsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicRevisionsPresenter.class);
                }

                if (presenter.isPresent()) {
                    if (lastPresenter != null) {
                        lastPresenter.close();
                    }

                    LOGGER.log(Level.INFO, "Displaying Presenter");
                    presenter.get().parseToken(token);
                    presenter.get().go(this.container);

                    lastPresenter = presenter.get();
                }
            }
        } catch(@NotNull final RuntimeException ex) {
            LOGGER.log(Level.INFO, ex.toString());
            throw ex;
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.onValueChange()");
        }
    }

    private <T> Optional<T> getBeanInstance(
            final Class<T> presenterType) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.getBeanInstance()");

            final IOCBeanDef<T> bean = this.manager.lookupBean(presenterType);
            if (bean != null) {
                LOGGER.log(Level.INFO, "Found bean.");
                final T presenter = bean.getInstance();
                return Optional.of(presenter);
            }
            return Optional.absent();
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.getBeanInstance()");
        }
    }
}
