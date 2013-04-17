package org.jboss.pressgang.ccms.ui.client.local;

import com.google.common.base.Optional;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.*;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.PresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytagcategory.PropertyCategoryFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics.TranslatedTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class watches the event bus for page change requests, and instructs the appropriate presenters
 * to display when a page change event is received.
 */
@ApplicationScoped
public class AppController implements PresenterInterface, ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;

    @Inject
    private HandlerManager eventBus;

    private HasWidgets container;

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

            this.eventBus.addHandler(WelcomeViewEvent.TYPE, new ViewOpenEventHandler(WelcomePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchViewEvent.TYPE, new ViewOpenEventHandler(SearchPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchResultsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(TopicFilteredResultsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchResultsAndTopicViewEvent.TYPE, new ViewOpenWithQueryEventHandler(TopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesViewEvent.TYPE, new ViewOpenEventHandler(ImagePresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(ImagesFilteredResultsAndImageViewEvent.TYPE, new ViewOpenWithQueryEventHandler(ImagesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TagsFilteredResultsAndTagViewEvent.TYPE, new ViewOpenWithQueryEventHandler(TagsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(CategoriesFilteredResultsAndCategoryViewEvent.TYPE, new ViewOpenWithQueryEventHandler(CategoriesFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(SearchTagsFieldsAndFiltersViewEvent.TYPE, new ViewOpenWithQueryEventHandler(SearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TranslatedSearchTagsFieldsAndFiltersViewEvent.TYPE, new ViewOpenWithQueryEventHandler(SearchTagsFieldsAndFiltersPresenter.TRANSLATED_HISTORY_TOKEN));
            this.eventBus.addHandler(ProjectsFilteredResultsAndProjectViewEvent.TYPE, new ViewOpenWithQueryEventHandler(ProjectsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(TranslatedSearchResultsAndTopicViewEvent.TYPE, new ViewOpenWithQueryEventHandler(TranslatedTopicFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(StringConstantFilteredResultsAndDetailsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(StringConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(IntegerConstantFilteredResultsAndDetailsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(IntegerConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(BlobConstantFilteredResultsAndDetailsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(BlobConstantFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(PropertyTagFilteredResultsAndDetailsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(PropertyTagFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(PropertyCategoryFilteredResultsAndDetailsViewEvent.TYPE, new ViewOpenWithQueryEventHandler(PropertyCategoryFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));
            this.eventBus.addHandler(BulkTagSearchTagsFieldsAndFiltersViewEvent.TYPE, new ViewOpenWithQueryEventHandler(SearchTagsFieldsAndFiltersPresenter.BULK_TAG_HISTORY_TOKEN));
            this.eventBus.addHandler(ContentSpecSearchResultsAndTopicViewEvent.TYPE, new ViewOpenWithQueryEventHandler(ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN));

        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.bind()");
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.go()");

            displayWelcomeMessage();

            this.container = container;
            this.bind();

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

    /**
     * Display a message if we are using an updated version
     */
    private void displayWelcomeMessage() {
        final String lastBuild = Preferences.INSTANCE.getString(Preferences.LAST_BUILD, null);
        if (lastBuild == null || !lastBuild.equals(Constants.VERSION)) {
            Window.alert(PressGangCCMSUI.INSTANCE.ApplicationUpdated());
        }
        Preferences.INSTANCE.saveSetting(Preferences.LAST_BUILD, Constants.VERSION);
    }

    @Override
    public void onValueChange(@NotNull final ValueChangeEvent<String> event) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.onValueChange()");

            final String token = event.getValue();

            if (token != null) {
                Optional<BaseTemplatePresenterInterface> presenter = Optional.absent();

                if (token.startsWith(WelcomePresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(WelcomePresenter.class);
                } else if (token.startsWith(SearchPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(SearchPresenter.class);
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
                } else if (token.startsWith(SearchFieldPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(SearchFieldPresenter.class);
                } else if (token.startsWith(SearchTagsFieldsAndFiltersPresenter.HISTORY_TOKEN) ||
                        token.startsWith(SearchTagsFieldsAndFiltersPresenter.TRANSLATED_HISTORY_TOKEN) ||
                        token.startsWith(SearchTagsFieldsAndFiltersPresenter.BULK_TAG_HISTORY_TOKEN)) {
                    /*
                        The SearchTagsFieldsAndFiltersView, TranslatedSearchTagsFieldsAndFiltersView
                        and BulkTagSearchTagsFieldsAndFiltersViewEvent history tokens will launch the search view.
                     */
                    presenter = getBeanInstance(SearchTagsFieldsAndFiltersPresenter.class);
                } else if (token.startsWith(ProjectsFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ProjectsFilteredResultsAndDetailsPresenter.class);
                } else if (token.startsWith(SearchFilterResultsAndFilterPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(SearchFilterResultsAndFilterPresenter.class);
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
                }  else if (token.startsWith(TopicRenderedPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(TopicRenderedPresenter.class);
                }  else if (token.startsWith(ContentSpecFilteredResultsAndDetailsPresenter.HISTORY_TOKEN)) {
                    presenter = getBeanInstance(ContentSpecFilteredResultsAndDetailsPresenter.class);
                }


                if (presenter.isPresent()) {
                    LOGGER.log(Level.INFO, "Displaying Presenter");
                    presenter.get().parseToken(token);
                    presenter.get().go(this.container);
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.onValueChange()");
        }
    }

    private <T extends BaseTemplatePresenterInterface> Optional<BaseTemplatePresenterInterface> getBeanInstance(final Class<T> presenterType) {
        try {
            LOGGER.log(Level.INFO, "ENTER AppController.getBeanInstance()");

            final IOCBeanDef<T> bean = this.manager.lookupBean(presenterType);
            if (bean != null) {
                LOGGER.log(Level.INFO, "Found bean.");
                final BaseTemplatePresenterInterface presenter = bean.getInstance();
                return Optional.of(presenter);
            }
            return Optional.absent();
        } finally {
            LOGGER.log(Level.INFO, "EXIT AppController.getBeanInstance()");
        }
    }
}
