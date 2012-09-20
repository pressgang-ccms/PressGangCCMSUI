package org.jboss.errai.ioc.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasWidgets;
import java.lang.annotation.Annotation;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Provider;
import org.jboss.errai.common.client.api.extension.InitVotes;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.enterprise.client.jaxrs.JaxrsModuleBootstrapper;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.ProxyResolver;
import org.jboss.pressgang.ccms.ui.client.local.App;
import org.jboss.pressgang.ccms.ui.client.local.AppController;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenterBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenterBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.WelcomeView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.image.ImageFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.image.ImageView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.image.ImagesFilteredResultsAndImageView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagCategoriesView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagProjectsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagsFilteredResultsAndTagView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicBugsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderedView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRevisionsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicTagsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLErrorsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topicsearch.SearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topicsearch.SearchResultsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topicsearch.SearchView;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any _1998831462Any_4818359 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default _1998831462Default_30655864 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_19635043Annotation_15046212 = new Annotation[] { _1998831462Any_4818359, _1998831462Default_30655864 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj2060_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj2047_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, arrayOf_19635043Annotation_15046212), inj2047_IOCBeanManagerProvider);
      return inj2047_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2047_IOCBeanManagerProvider = inj2060_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<App> inj2062_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj2061_App = new App();
      context.addBean(context.getBeanReference(App.class, arrayOf_19635043Annotation_15046212), inj2061_App);
      final AppController_inj2063_proxy inj2063_proxy = new AppController_inj2063_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj2063_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj2063_proxy, obj);
        }
      }, AppController.class, arrayOf_19635043Annotation_15046212);
      _$477517530_appController(inj2061_App, inj2063_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          inj2061_App.startApp();
        }
      });
      return inj2061_App;
    }
  };
  private final App inj2061_App = inj2062_App_creational.getInstance(context);
  private final CreationalCallback<HandlerManager> inj2039_HandlerManager_creational = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = _$477517530_produceEventBus(inj2061_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_19635043Annotation_15046212), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj2065_AppController_creational = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      final AppController inj2064_AppController = new AppController();
      context.addBean(context.getBeanReference(AppController.class, arrayOf_19635043Annotation_15046212), inj2064_AppController);
      _2098016610_manager(inj2064_AppController, inj2047_IOCBeanManagerProvider.get());
      _2098016610_eventBus(inj2064_AppController, _$477517530_produceEventBus(inj2061_App));
      return inj2064_AppController;
    }
  };
  private final AppController inj2064_AppController = inj2065_AppController_creational.getInstance(context);
  private final CreationalCallback<TopicView> inj2068_TopicView_creational = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      final TopicView inj493_TopicView = new TopicView();
      context.addBean(context.getBeanReference(TopicView.class, arrayOf_19635043Annotation_15046212), inj493_TopicView);
      return inj493_TopicView;
    }
  };
  private final CreationalCallback<TopicPresenter> inj2067_TopicPresenter_creational = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      final TopicPresenter inj2066_TopicPresenter = new TopicPresenter();
      context.addBean(context.getBeanReference(TopicPresenter.class, arrayOf_19635043Annotation_15046212), inj2066_TopicPresenter);
      _392006560_display(inj2066_TopicPresenter, inj2068_TopicView_creational.getInstance(context));
      _1500219897_eventBus(inj2066_TopicPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2066_TopicPresenter;
    }
  };
  private final CreationalCallback<SearchResultsView> inj2071_SearchResultsView_creational = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      final SearchResultsView inj501_SearchResultsView = new SearchResultsView();
      context.addBean(context.getBeanReference(SearchResultsView.class, arrayOf_19635043Annotation_15046212), inj501_SearchResultsView);
      return inj501_SearchResultsView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj2070_SearchResultsPresenter_creational = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      final SearchResultsPresenter inj2069_SearchResultsPresenter = new SearchResultsPresenter();
      context.addBean(context.getBeanReference(SearchResultsPresenter.class, arrayOf_19635043Annotation_15046212), inj2069_SearchResultsPresenter);
      _954332697_display(inj2069_SearchResultsPresenter, inj2071_SearchResultsView_creational.getInstance(context));
      _954332697_topicViewDisplay(inj2069_SearchResultsPresenter, inj2068_TopicView_creational.getInstance(context));
      _1500219897_eventBus(inj2069_SearchResultsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2069_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<TagsFilteredResultsAndTagView> inj2074_TagsFilteredResultsAndTagView_creational = new CreationalCallback<TagsFilteredResultsAndTagView>() {
    public TagsFilteredResultsAndTagView getInstance(final CreationalContext context) {
      final TagsFilteredResultsAndTagView inj1876_TagsFilteredResultsAndTagView = new TagsFilteredResultsAndTagView();
      context.addBean(context.getBeanReference(TagsFilteredResultsAndTagView.class, arrayOf_19635043Annotation_15046212), inj1876_TagsFilteredResultsAndTagView);
      return inj1876_TagsFilteredResultsAndTagView;
    }
  };
  private final CreationalCallback<TagFilteredResultsView> inj2075_TagFilteredResultsView_creational = new CreationalCallback<TagFilteredResultsView>() {
    public TagFilteredResultsView getInstance(final CreationalContext context) {
      final TagFilteredResultsView inj1875_TagFilteredResultsView = new TagFilteredResultsView();
      context.addBean(context.getBeanReference(TagFilteredResultsView.class, arrayOf_19635043Annotation_15046212), inj1875_TagFilteredResultsView);
      return inj1875_TagFilteredResultsView;
    }
  };
  private final CreationalCallback<TagView> inj2076_TagView_creational = new CreationalCallback<TagView>() {
    public TagView getInstance(final CreationalContext context) {
      final TagView inj1878_TagView = new TagView();
      context.addBean(context.getBeanReference(TagView.class, arrayOf_19635043Annotation_15046212), inj1878_TagView);
      return inj1878_TagView;
    }
  };
  private final CreationalCallback<TagProjectsView> inj2077_TagProjectsView_creational = new CreationalCallback<TagProjectsView>() {
    public TagProjectsView getInstance(final CreationalContext context) {
      final TagProjectsView inj1877_TagProjectsView = new TagProjectsView();
      context.addBean(context.getBeanReference(TagProjectsView.class, arrayOf_19635043Annotation_15046212), inj1877_TagProjectsView);
      return inj1877_TagProjectsView;
    }
  };
  private final CreationalCallback<TagCategoriesView> inj2078_TagCategoriesView_creational = new CreationalCallback<TagCategoriesView>() {
    public TagCategoriesView getInstance(final CreationalContext context) {
      final TagCategoriesView inj1879_TagCategoriesView = new TagCategoriesView();
      context.addBean(context.getBeanReference(TagCategoriesView.class, arrayOf_19635043Annotation_15046212), inj1879_TagCategoriesView);
      return inj1879_TagCategoriesView;
    }
  };
  private final CreationalCallback<TagsFilteredResultsAndTagPresenter> inj2073_TagsFilteredResultsAndTagPresenter_creational = new CreationalCallback<TagsFilteredResultsAndTagPresenter>() {
    public TagsFilteredResultsAndTagPresenter getInstance(final CreationalContext context) {
      final TagsFilteredResultsAndTagPresenter inj2072_TagsFilteredResultsAndTagPresenter = new TagsFilteredResultsAndTagPresenter();
      context.addBean(context.getBeanReference(TagsFilteredResultsAndTagPresenter.class, arrayOf_19635043Annotation_15046212), inj2072_TagsFilteredResultsAndTagPresenter);
      _$525173285_display(inj2072_TagsFilteredResultsAndTagPresenter, inj2074_TagsFilteredResultsAndTagView_creational.getInstance(context));
      _$525173285_filteredResultsDisplay(inj2072_TagsFilteredResultsAndTagPresenter, inj2075_TagFilteredResultsView_creational.getInstance(context));
      _$525173285_resultDisplay(inj2072_TagsFilteredResultsAndTagPresenter, inj2076_TagView_creational.getInstance(context));
      _$525173285_projectsDisplay(inj2072_TagsFilteredResultsAndTagPresenter, inj2077_TagProjectsView_creational.getInstance(context));
      _$525173285_categoriesDisplay(inj2072_TagsFilteredResultsAndTagPresenter, inj2078_TagCategoriesView_creational.getInstance(context));
      _1500219897_eventBus(inj2072_TagsFilteredResultsAndTagPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2072_TagsFilteredResultsAndTagPresenter;
    }
  };
  private final CreationalCallback<TagFilteredResultsPresenter> inj2080_TagFilteredResultsPresenter_creational = new CreationalCallback<TagFilteredResultsPresenter>() {
    public TagFilteredResultsPresenter getInstance(final CreationalContext context) {
      final TagFilteredResultsPresenter inj2079_TagFilteredResultsPresenter = new TagFilteredResultsPresenter();
      context.addBean(context.getBeanReference(TagFilteredResultsPresenter.class, arrayOf_19635043Annotation_15046212), inj2079_TagFilteredResultsPresenter);
      _1748359463_display(inj2079_TagFilteredResultsPresenter, inj2075_TagFilteredResultsView_creational.getInstance(context));
      _1500219897_eventBus(inj2079_TagFilteredResultsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2079_TagFilteredResultsPresenter;
    }
  };
  private final CreationalCallback<TopicTagsView> inj2083_TopicTagsView_creational = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      final TopicTagsView inj492_TopicTagsView = new TopicTagsView();
      context.addBean(context.getBeanReference(TopicTagsView.class, arrayOf_19635043Annotation_15046212), inj492_TopicTagsView);
      return inj492_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj2082_TopicTagsPresenter_creational = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      final TopicTagsPresenter inj2081_TopicTagsPresenter = new TopicTagsPresenter();
      context.addBean(context.getBeanReference(TopicTagsPresenter.class, arrayOf_19635043Annotation_15046212), inj2081_TopicTagsPresenter);
      _$1522350201_display(inj2081_TopicTagsPresenter, inj2083_TopicTagsView_creational.getInstance(context));
      _1500219897_eventBus(inj2081_TopicTagsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2081_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj2086_WelcomeView_creational = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      final WelcomeView inj705_WelcomeView = new WelcomeView();
      context.addBean(context.getBeanReference(WelcomeView.class, arrayOf_19635043Annotation_15046212), inj705_WelcomeView);
      return inj705_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj2085_WelcomePresenter_creational = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      final WelcomePresenter inj2084_WelcomePresenter = new WelcomePresenter();
      context.addBean(context.getBeanReference(WelcomePresenter.class, arrayOf_19635043Annotation_15046212), inj2084_WelcomePresenter);
      _1478143022_display(inj2084_WelcomePresenter, inj2086_WelcomeView_creational.getInstance(context));
      _1500219897_eventBus(inj2084_WelcomePresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2084_WelcomePresenter;
    }
  };
  private final CreationalCallback<TagPresenter> inj2088_TagPresenter_creational = new CreationalCallback<TagPresenter>() {
    public TagPresenter getInstance(final CreationalContext context) {
      final TagPresenter inj2087_TagPresenter = new TagPresenter();
      context.addBean(context.getBeanReference(TagPresenter.class, arrayOf_19635043Annotation_15046212), inj2087_TagPresenter);
      _$774116150_display(inj2087_TagPresenter, inj2076_TagView_creational.getInstance(context));
      _1500219897_eventBus(inj2087_TagPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2087_TagPresenter;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicView> inj2091_SearchResultsAndTopicView_creational = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      final SearchResultsAndTopicView inj500_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      context.addBean(context.getBeanReference(SearchResultsAndTopicView.class, arrayOf_19635043Annotation_15046212), inj500_SearchResultsAndTopicView);
      return inj500_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj2092_TopicXMLView_creational = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      final TopicXMLView inj496_TopicXMLView = new TopicXMLView();
      context.addBean(context.getBeanReference(TopicXMLView.class, arrayOf_19635043Annotation_15046212), inj496_TopicXMLView);
      return inj496_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj2093_TopicRenderedView_creational = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      final TopicRenderedView inj494_TopicRenderedView = new TopicRenderedView();
      context.addBean(context.getBeanReference(TopicRenderedView.class, arrayOf_19635043Annotation_15046212), inj494_TopicRenderedView);
      return inj494_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsView> inj2094_TopicXMLErrorsView_creational = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      final TopicXMLErrorsView inj497_TopicXMLErrorsView = new TopicXMLErrorsView();
      context.addBean(context.getBeanReference(TopicXMLErrorsView.class, arrayOf_19635043Annotation_15046212), inj497_TopicXMLErrorsView);
      return inj497_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj2095_TopicBugsView_creational = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      final TopicBugsView inj495_TopicBugsView = new TopicBugsView();
      context.addBean(context.getBeanReference(TopicBugsView.class, arrayOf_19635043Annotation_15046212), inj495_TopicBugsView);
      return inj495_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj2096_TopicRevisionsView_creational = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      final TopicRevisionsView inj491_TopicRevisionsView = new TopicRevisionsView();
      context.addBean(context.getBeanReference(TopicRevisionsView.class, arrayOf_19635043Annotation_15046212), inj491_TopicRevisionsView);
      return inj491_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj2090_SearchResultsAndTopicPresenter_creational = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      final SearchResultsAndTopicPresenter inj2089_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      context.addBean(context.getBeanReference(SearchResultsAndTopicPresenter.class, arrayOf_19635043Annotation_15046212), inj2089_SearchResultsAndTopicPresenter);
      _1567125089_display(inj2089_SearchResultsAndTopicPresenter, inj2091_SearchResultsAndTopicView_creational.getInstance(context));
      _1567125089_topicViewDisplay(inj2089_SearchResultsAndTopicPresenter, inj2068_TopicView_creational.getInstance(context));
      _1567125089_topicXMLDisplay(inj2089_SearchResultsAndTopicPresenter, inj2092_TopicXMLView_creational.getInstance(context));
      _1567125089_topicRenderedDisplay(inj2089_SearchResultsAndTopicPresenter, inj2093_TopicRenderedView_creational.getInstance(context));
      _1567125089_topicSplitPanelRenderedDisplay(inj2089_SearchResultsAndTopicPresenter, inj2093_TopicRenderedView_creational.getInstance(context));
      _1567125089_searchResultsDisplay(inj2089_SearchResultsAndTopicPresenter, inj2071_SearchResultsView_creational.getInstance(context));
      _1567125089_topicXMLErrorsDisplay(inj2089_SearchResultsAndTopicPresenter, inj2094_TopicXMLErrorsView_creational.getInstance(context));
      _1567125089_topicTagsDisplay(inj2089_SearchResultsAndTopicPresenter, inj2083_TopicTagsView_creational.getInstance(context));
      _1567125089_topicBugsDisplay(inj2089_SearchResultsAndTopicPresenter, inj2095_TopicBugsView_creational.getInstance(context));
      _1567125089_topicRevisionsDisplay(inj2089_SearchResultsAndTopicPresenter, inj2096_TopicRevisionsView_creational.getInstance(context));
      _1500219897_eventBus(inj2089_SearchResultsAndTopicPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2089_SearchResultsAndTopicPresenter;
    }
  };
  private final CreationalCallback<SearchView> inj2099_SearchView_creational = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      final SearchView inj502_SearchView = new SearchView();
      context.addBean(context.getBeanReference(SearchView.class, arrayOf_19635043Annotation_15046212), inj502_SearchView);
      return inj502_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj2098_SearchPresenter_creational = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      final SearchPresenter inj2097_SearchPresenter = new SearchPresenter();
      context.addBean(context.getBeanReference(SearchPresenter.class, arrayOf_19635043Annotation_15046212), inj2097_SearchPresenter);
      _$192804415_eventBus(inj2097_SearchPresenter, _$477517530_produceEventBus(inj2061_App));
      _$192804415_display(inj2097_SearchPresenter, inj2099_SearchView_creational.getInstance(context));
      _1500219897_eventBus(inj2097_SearchPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2097_SearchPresenter;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImageView> inj2102_ImagesFilteredResultsAndImageView_creational = new CreationalCallback<ImagesFilteredResultsAndImageView>() {
    public ImagesFilteredResultsAndImageView getInstance(final CreationalContext context) {
      final ImagesFilteredResultsAndImageView inj248_ImagesFilteredResultsAndImageView = new ImagesFilteredResultsAndImageView();
      context.addBean(context.getBeanReference(ImagesFilteredResultsAndImageView.class, arrayOf_19635043Annotation_15046212), inj248_ImagesFilteredResultsAndImageView);
      return inj248_ImagesFilteredResultsAndImageView;
    }
  };
  private final CreationalCallback<ImageFilteredResultsView> inj2103_ImageFilteredResultsView_creational = new CreationalCallback<ImageFilteredResultsView>() {
    public ImageFilteredResultsView getInstance(final CreationalContext context) {
      final ImageFilteredResultsView inj250_ImageFilteredResultsView = new ImageFilteredResultsView();
      context.addBean(context.getBeanReference(ImageFilteredResultsView.class, arrayOf_19635043Annotation_15046212), inj250_ImageFilteredResultsView);
      return inj250_ImageFilteredResultsView;
    }
  };
  private final CreationalCallback<ImageView> inj2104_ImageView_creational = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      final ImageView inj249_ImageView = new ImageView();
      context.addBean(context.getBeanReference(ImageView.class, arrayOf_19635043Annotation_15046212), inj249_ImageView);
      return inj249_ImageView;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImagePresenter> inj2101_ImagesFilteredResultsAndImagePresenter_creational = new CreationalCallback<ImagesFilteredResultsAndImagePresenter>() {
    public ImagesFilteredResultsAndImagePresenter getInstance(final CreationalContext context) {
      final ImagesFilteredResultsAndImagePresenter inj2100_ImagesFilteredResultsAndImagePresenter = new ImagesFilteredResultsAndImagePresenter();
      context.addBean(context.getBeanReference(ImagesFilteredResultsAndImagePresenter.class, arrayOf_19635043Annotation_15046212), inj2100_ImagesFilteredResultsAndImagePresenter);
      _491824760_display(inj2100_ImagesFilteredResultsAndImagePresenter, inj2102_ImagesFilteredResultsAndImageView_creational.getInstance(context));
      _491824760_imageFilteredResultsDisplay(inj2100_ImagesFilteredResultsAndImagePresenter, inj2103_ImageFilteredResultsView_creational.getInstance(context));
      _491824760_imageDisplay(inj2100_ImagesFilteredResultsAndImagePresenter, inj2104_ImageView_creational.getInstance(context));
      _1500219897_eventBus(inj2100_ImagesFilteredResultsAndImagePresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2100_ImagesFilteredResultsAndImagePresenter;
    }
  };
  private final CreationalCallback<TagCategoriesPresenter> inj2106_TagCategoriesPresenter_creational = new CreationalCallback<TagCategoriesPresenter>() {
    public TagCategoriesPresenter getInstance(final CreationalContext context) {
      final TagCategoriesPresenter inj2105_TagCategoriesPresenter = new TagCategoriesPresenter();
      context.addBean(context.getBeanReference(TagCategoriesPresenter.class, arrayOf_19635043Annotation_15046212), inj2105_TagCategoriesPresenter);
      _$147774578_display(inj2105_TagCategoriesPresenter, inj2078_TagCategoriesView_creational.getInstance(context));
      _1500219897_eventBus(inj2105_TagCategoriesPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2105_TagCategoriesPresenter;
    }
  };
  private final CreationalCallback<TopicBugsPresenter> inj2108_TopicBugsPresenter_creational = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      final TopicBugsPresenter inj2107_TopicBugsPresenter = new TopicBugsPresenter();
      context.addBean(context.getBeanReference(TopicBugsPresenter.class, arrayOf_19635043Annotation_15046212), inj2107_TopicBugsPresenter);
      _1500219897_eventBus(inj2107_TopicBugsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2107_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<TagProjectsPresenter> inj2110_TagProjectsPresenter_creational = new CreationalCallback<TagProjectsPresenter>() {
    public TagProjectsPresenter getInstance(final CreationalContext context) {
      final TagProjectsPresenter inj2109_TagProjectsPresenter = new TagProjectsPresenter();
      context.addBean(context.getBeanReference(TagProjectsPresenter.class, arrayOf_19635043Annotation_15046212), inj2109_TagProjectsPresenter);
      _$1369662608_display(inj2109_TagProjectsPresenter, inj2077_TagProjectsView_creational.getInstance(context));
      _1500219897_eventBus(inj2109_TagProjectsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2109_TagProjectsPresenter;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj2112_TopicXMLErrorsPresenter_creational = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      final TopicXMLErrorsPresenter inj2111_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      context.addBean(context.getBeanReference(TopicXMLErrorsPresenter.class, arrayOf_19635043Annotation_15046212), inj2111_TopicXMLErrorsPresenter);
      _$1893219218_display(inj2111_TopicXMLErrorsPresenter, inj2094_TopicXMLErrorsView_creational.getInstance(context));
      _1500219897_eventBus(inj2111_TopicXMLErrorsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2111_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<TopicRevisionsPresenter> inj2114_TopicRevisionsPresenter_creational = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      final TopicRevisionsPresenter inj2113_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      context.addBean(context.getBeanReference(TopicRevisionsPresenter.class, arrayOf_19635043Annotation_15046212), inj2113_TopicRevisionsPresenter);
      _1500219897_eventBus(inj2113_TopicRevisionsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2113_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<ImageFilteredResultsPresenter> inj2116_ImageFilteredResultsPresenter_creational = new CreationalCallback<ImageFilteredResultsPresenter>() {
    public ImageFilteredResultsPresenter getInstance(final CreationalContext context) {
      final ImageFilteredResultsPresenter inj2115_ImageFilteredResultsPresenter = new ImageFilteredResultsPresenter();
      context.addBean(context.getBeanReference(ImageFilteredResultsPresenter.class, arrayOf_19635043Annotation_15046212), inj2115_ImageFilteredResultsPresenter);
      _$1131523351_display(inj2115_ImageFilteredResultsPresenter, inj2103_ImageFilteredResultsView_creational.getInstance(context));
      _1500219897_eventBus(inj2115_ImageFilteredResultsPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2115_ImageFilteredResultsPresenter;
    }
  };
  private final CreationalCallback<ImagePresenter> inj2118_ImagePresenter_creational = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      final ImagePresenter inj2117_ImagePresenter = new ImagePresenter();
      context.addBean(context.getBeanReference(ImagePresenter.class, arrayOf_19635043Annotation_15046212), inj2117_ImagePresenter);
      _$550803640_display(inj2117_ImagePresenter, inj2104_ImageView_creational.getInstance(context));
      _1500219897_eventBus(inj2117_ImagePresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2117_ImagePresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj2120_TopicRenderedPresenter_creational = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      final TopicRenderedPresenter inj2119_TopicRenderedPresenter = new TopicRenderedPresenter();
      context.addBean(context.getBeanReference(TopicRenderedPresenter.class, arrayOf_19635043Annotation_15046212), inj2119_TopicRenderedPresenter);
      _1500219897_eventBus(inj2119_TopicRenderedPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2119_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj2122_TopicXMLPresenter_creational = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      final TopicXMLPresenter inj2121_TopicXMLPresenter = new TopicXMLPresenter();
      context.addBean(context.getBeanReference(TopicXMLPresenter.class, arrayOf_19635043Annotation_15046212), inj2121_TopicXMLPresenter);
      _1500219897_eventBus(inj2121_TopicXMLPresenter, _$477517530_produceEventBus(inj2061_App));
      return inj2121_TopicXMLPresenter;
    }
  };
  static class AppController_inj2063_proxy extends AppController {
    private AppController $$_proxy_$$;
    public void bind() {
      $$_proxy_$$.bind();
    }

    public void go(HasWidgets a0) {
      $$_proxy_$$.go(a0);
    }

    public void onValueChange(ValueChangeEvent a0) {
      $$_proxy_$$.onValueChange(a0);
    }

    public int hashCode() {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to hashCode() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.hashCode();
      }
    }

    public boolean equals(Object o) {
      if ($$_proxy_$$ == null) {
        throw new IllegalStateException("call to equal() on an unclosed proxy.");
      } else {
        return $$_proxy_$$.equals(o);
      }
    }

    public void __$setProxiedInstance$(AppController proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  private void declareBeans_0() {
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2060_IOCBeanManagerProvider_creational, inj2047_IOCBeanManagerProvider, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2060_IOCBeanManagerProvider_creational, inj2047_IOCBeanManagerProvider, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(App.class, App.class, inj2062_App_creational, inj2061_App, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj2039_HandlerManager_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj2039_HandlerManager_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(AppController.class, AppController.class, inj2065_AppController_creational, inj2064_AppController, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj2065_AppController_creational, inj2064_AppController, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj2065_AppController_creational, inj2064_AppController, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj2065_AppController_creational, inj2064_AppController, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(Display.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj2068_TopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj2067_TopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj2067_TopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj2067_TopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj2071_SearchResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display.class, SearchResultsView.class, inj2071_SearchResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj2071_SearchResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj2071_SearchResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj2070_SearchResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, SearchResultsPresenter.class, inj2070_SearchResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj2070_SearchResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj2070_SearchResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagsFilteredResultsAndTagView.class, TagsFilteredResultsAndTagView.class, inj2074_TagsFilteredResultsAndTagView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter.Display.class, TagsFilteredResultsAndTagView.class, inj2074_TagsFilteredResultsAndTagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagsFilteredResultsAndTagView.class, inj2074_TagsFilteredResultsAndTagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TagsFilteredResultsAndTagView.class, inj2074_TagsFilteredResultsAndTagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagFilteredResultsView.class, TagFilteredResultsView.class, inj2075_TagFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display.class, TagFilteredResultsView.class, inj2075_TagFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagFilteredResultsView.class, inj2075_TagFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TagFilteredResultsView.class, inj2075_TagFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagView.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.Display.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewInterface.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewBase.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TagView.class, inj2076_TagView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagProjectsView.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter.Display.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewInterface.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewBase.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TagProjectsView.class, inj2077_TagProjectsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagCategoriesView.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter.Display.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewInterface.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagViewBase.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TagCategoriesView.class, inj2078_TagCategoriesView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagsFilteredResultsAndTagPresenter.class, TagsFilteredResultsAndTagPresenter.class, inj2073_TagsFilteredResultsAndTagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TagPresenterBase.class, TagsFilteredResultsAndTagPresenter.class, inj2073_TagsFilteredResultsAndTagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(EditableView.class, TagsFilteredResultsAndTagPresenter.class, inj2073_TagsFilteredResultsAndTagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, TagsFilteredResultsAndTagPresenter.class, inj2073_TagsFilteredResultsAndTagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TagsFilteredResultsAndTagPresenter.class, inj2073_TagsFilteredResultsAndTagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagFilteredResultsPresenter.class, TagFilteredResultsPresenter.class, inj2080_TagFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, TagFilteredResultsPresenter.class, inj2080_TagFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, TagFilteredResultsPresenter.class, inj2080_TagFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TagFilteredResultsPresenter.class, inj2080_TagFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter.Display.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj2083_TopicTagsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj2082_TopicTagsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj2082_TopicTagsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj2082_TopicTagsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj2086_WelcomeView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj2086_WelcomeView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj2086_WelcomeView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj2086_WelcomeView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj2085_WelcomePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, WelcomePresenter.class, inj2085_WelcomePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj2085_WelcomePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj2085_WelcomePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagPresenter.class, TagPresenter.class, inj2088_TagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, TagPresenter.class, inj2088_TagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, TagPresenter.class, inj2088_TagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TagPresenter.class, inj2088_TagPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj2091_SearchResultsAndTopicView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj2091_SearchResultsAndTopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj2091_SearchResultsAndTopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj2091_SearchResultsAndTopicView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj2092_TopicXMLView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj2093_TopicRenderedView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj2094_TopicXMLErrorsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter.Display.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj2095_TopicBugsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj2096_TopicRevisionsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj2090_SearchResultsAndTopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, SearchResultsAndTopicPresenter.class, inj2090_SearchResultsAndTopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj2090_SearchResultsAndTopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj2090_SearchResultsAndTopicPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj2099_SearchView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchPresenter.Display.class, SearchView.class, inj2099_SearchView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj2099_SearchView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj2099_SearchView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj2098_SearchPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, SearchPresenter.class, inj2098_SearchPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj2098_SearchPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj2098_SearchPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImagesFilteredResultsAndImageView.class, ImagesFilteredResultsAndImageView.class, inj2102_ImagesFilteredResultsAndImageView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter.Display.class, ImagesFilteredResultsAndImageView.class, inj2102_ImagesFilteredResultsAndImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImagesFilteredResultsAndImageView.class, inj2102_ImagesFilteredResultsAndImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, ImagesFilteredResultsAndImageView.class, inj2102_ImagesFilteredResultsAndImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImageFilteredResultsView.class, ImageFilteredResultsView.class, inj2103_ImageFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display.class, ImageFilteredResultsView.class, inj2103_ImageFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageFilteredResultsView.class, inj2103_ImageFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, ImageFilteredResultsView.class, inj2103_ImageFilteredResultsView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj2104_ImageView_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display.class, ImageView.class, inj2104_ImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj2104_ImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj2104_ImageView_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImagesFilteredResultsAndImagePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2101_ImagesFilteredResultsAndImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, ImagesFilteredResultsAndImagePresenter.class, inj2101_ImagesFilteredResultsAndImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImagePresenterBase.class, ImagesFilteredResultsAndImagePresenter.class, inj2101_ImagesFilteredResultsAndImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2101_ImagesFilteredResultsAndImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2101_ImagesFilteredResultsAndImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagCategoriesPresenter.class, TagCategoriesPresenter.class, inj2106_TagCategoriesPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, TagCategoriesPresenter.class, inj2106_TagCategoriesPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, TagCategoriesPresenter.class, inj2106_TagCategoriesPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TagCategoriesPresenter.class, inj2106_TagCategoriesPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj2108_TopicBugsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj2108_TopicBugsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj2108_TopicBugsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TagProjectsPresenter.class, TagProjectsPresenter.class, inj2110_TagProjectsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, TagProjectsPresenter.class, inj2110_TagProjectsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, TagProjectsPresenter.class, inj2110_TagProjectsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TagProjectsPresenter.class, inj2110_TagProjectsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj2112_TopicXMLErrorsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj2112_TopicXMLErrorsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj2112_TopicXMLErrorsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj2114_TopicRevisionsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj2114_TopicRevisionsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj2114_TopicRevisionsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImageFilteredResultsPresenter.class, ImageFilteredResultsPresenter.class, inj2116_ImageFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(EditableView.class, ImageFilteredResultsPresenter.class, inj2116_ImageFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, ImageFilteredResultsPresenter.class, inj2116_ImageFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, ImageFilteredResultsPresenter.class, inj2116_ImageFilteredResultsPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj2118_ImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(ImagePresenterBase.class, ImagePresenter.class, inj2118_ImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj2118_ImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj2118_ImagePresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj2120_TopicRenderedPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj2120_TopicRenderedPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj2120_TopicRenderedPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj2122_TopicXMLPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj2122_TopicXMLPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj2122_TopicXMLPresenter_creational, null, arrayOf_19635043Annotation_15046212, null, false);
  }

  private native static void _$550803640_display(ImagePresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter::display = value;
  }-*/;

  private native static void _1567125089_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void _1478143022_display(WelcomePresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void _$147774578_display(TagCategoriesPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter::display = value;
  }-*/;

  private native static void _1567125089_topicXMLDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicXMLDisplay = value;
  }-*/;

  private native static void _$525173285_filteredResultsDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::filteredResultsDisplay = value;
  }-*/;

  private native static void _392006560_display(TopicPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter::display = value;
  }-*/;

  private native static void _$1131523351_display(ImageFilteredResultsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter::display = value;
  }-*/;

  private native static void _$525173285_projectsDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::projectsDisplay = value;
  }-*/;

  private native static void _1567125089_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void _$192804415_display(SearchPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchPresenter::display = value;
  }-*/;

  private native static void _954332697_display(SearchResultsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter::display = value;
  }-*/;

  private native static void _491824760_imageDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::imageDisplay = value;
  }-*/;

  private native static void _1500219897_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void _$525173285_resultDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::resultDisplay = value;
  }-*/;

  private native static void _$192804415_eventBus(SearchPresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchPresenter::eventBus = value;
  }-*/;

  private native static void _1567125089_topicRevisionsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicRevisionsDisplay = value;
  }-*/;

  private native static void _$774116150_display(TagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagPresenter::display = value;
  }-*/;

  private native static void _2098016610_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.AppController::manager = value;
  }-*/;

  private native static void _$1369662608_display(TagProjectsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagProjectsPresenter::display = value;
  }-*/;

  private native static void _1748359463_display(TagFilteredResultsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter::display = value;
  }-*/;

  private native static void _$525173285_display(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::display = value;
  }-*/;

  private native static void _491824760_imageFilteredResultsDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::imageFilteredResultsDisplay = value;
  }-*/;

  private native static void _$477517530_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.App::appController = value;
  }-*/;

  private native static void _1567125089_topicSplitPanelRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicSplitPanelRenderedDisplay = value;
  }-*/;

  private native static void _954332697_topicViewDisplay(SearchResultsPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter::topicViewDisplay = value;
  }-*/;

  private native static void _1567125089_topicViewDisplay(SearchResultsAndTopicPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void _1567125089_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void _2098016610_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.AppController::eventBus = value;
  }-*/;

  private native static void _1567125089_topicBugsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicBugsDisplay = value;
  }-*/;

  private native static void _$1893219218_display(TopicXMLErrorsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter::display = value;
  }-*/;

  private native static void _$525173285_categoriesDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagCategoriesPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::categoriesDisplay = value;
  }-*/;

  private native static void _$1522350201_display(TopicTagsPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter::display = value;
  }-*/;

  private native static void _491824760_display(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::display = value;
  }-*/;

  private native static void _1567125089_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void _1567125089_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
  }-*/;

  public native static HandlerManager _$477517530_produceEventBus(App instance) /*-{
    return instance.@org.jboss.pressgang.ccms.ui.client.local.App::produceEventBus()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}