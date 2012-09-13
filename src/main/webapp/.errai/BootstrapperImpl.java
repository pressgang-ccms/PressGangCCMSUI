package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
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
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.enterprise.client.jaxrs.JaxrsModuleBootstrapper;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.container.BeanRef;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.ProxyResolver;
import org.jboss.pressgangccms.client.local.App;
import org.jboss.pressgangccms.client.local.AppController;
import org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenterBase;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagCategoriesPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagCategoriesPresenter.Display;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenterBase;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchView;
import org.jboss.pressgangccms.client.local.mvp.view.WelcomeView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImageFilteredResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImageView;
import org.jboss.pressgangccms.client.local.mvp.view.image.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagCategoriesView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagFilteredResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagProjectsView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagView;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagViewBase;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagsFilteredResultsAndTagView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicBugsView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicRenderedView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicRevisionsView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicTagsView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicViewBase;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.mvp.view.topic.TopicXMLView;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any javax_enterprise_inject_Any_14377257 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_16190115 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_4464356 = new Annotation[] { javax_enterprise_inject_Any_14377257, javax_enterprise_inject_Default_16190115 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj2027_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final RequestDispatcherProvider inj2012_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2012_RequestDispatcherProvider);
      return inj2012_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj2012_RequestDispatcherProvider = inj2027_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj2028_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final InstanceProvider inj2026_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2026_InstanceProvider);
      return inj2026_InstanceProvider;
    }
  };
  private final InstanceProvider inj2026_InstanceProvider = inj2028_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj2029_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final IOCBeanManagerProvider inj2020_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2020_IOCBeanManagerProvider);
      return inj2020_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2020_IOCBeanManagerProvider = inj2029_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj2030_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final App inj659_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj659_App);
      final org_jboss_pressgangccms_client_local_AppController_inj2031_proxy inj2031_proxy = new org_jboss_pressgangccms_client_local_AppController_inj2031_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj2031_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj2031_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_4464356);
      org_jboss_pressgangccms_client_local_App_appController(inj659_App, inj2031_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj659_App.startApp();
            }
          });
        }
      });
      return inj659_App;
    }
  };
  private final App inj659_App = inj2030_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj2006_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_4464356), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj2033_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final AppController inj2032_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2032_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj2032_AppController, inj2020_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj2032_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2032_AppController;
    }
  };
  private final AppController inj2032_AppController = inj2033_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj2034_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final EventProvider inj2022_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2022_EventProvider);
      return inj2022_EventProvider;
    }
  };
  private final EventProvider inj2022_EventProvider = inj2034_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TagCategoriesView> inj2037_TagCategoriesView_creationalCallback = new CreationalCallback<TagCategoriesView>() {
    public TagCategoriesView getInstance(final CreationalContext context) {
      Class beanType = TagCategoriesView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagCategoriesView inj512_TagCategoriesView = new TagCategoriesView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj512_TagCategoriesView);
      return inj512_TagCategoriesView;
    }
  };
  private final CreationalCallback<TagCategoriesPresenter> inj2036_TagCategoriesPresenter_creationalCallback = new CreationalCallback<TagCategoriesPresenter>() {
    public TagCategoriesPresenter getInstance(final CreationalContext context) {
      Class beanType = TagCategoriesPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagCategoriesPresenter inj2035_TagCategoriesPresenter = new TagCategoriesPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2035_TagCategoriesPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagCategoriesPresenter_display(inj2035_TagCategoriesPresenter, inj2037_TagCategoriesView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2035_TagCategoriesPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2035_TagCategoriesPresenter;
    }
  };
  private final CreationalCallback<MessageBusProvider> inj2038_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final MessageBusProvider inj2018_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2018_MessageBusProvider);
      return inj2018_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj2018_MessageBusProvider = inj2038_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLErrorsView> inj2041_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicXMLErrorsView inj328_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj328_TopicXMLErrorsView);
      return inj328_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj2040_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicXMLErrorsPresenter inj2039_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2039_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicXMLErrorsPresenter_display(inj2039_TopicXMLErrorsPresenter, inj2041_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2039_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2039_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<TagView> inj2044_TagView_creationalCallback = new CreationalCallback<TagView>() {
    public TagView getInstance(final CreationalContext context) {
      Class beanType = TagView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagView inj511_TagView = new TagView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj511_TagView);
      return inj511_TagView;
    }
  };
  private final CreationalCallback<TagPresenter> inj2043_TagPresenter_creationalCallback = new CreationalCallback<TagPresenter>() {
    public TagPresenter getInstance(final CreationalContext context) {
      Class beanType = TagPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagPresenter inj2042_TagPresenter = new TagPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2042_TagPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagPresenter_display(inj2042_TagPresenter, inj2044_TagView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2042_TagPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2042_TagPresenter;
    }
  };
  private final CreationalCallback<ImageView> inj2047_ImageView_creationalCallback = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      Class beanType = ImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImageView inj306_ImageView = new ImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj306_ImageView);
      return inj306_ImageView;
    }
  };
  private final CreationalCallback<ImagePresenter> inj2046_ImagePresenter_creationalCallback = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImagePresenter inj2045_ImagePresenter = new ImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2045_ImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagePresenter_display(inj2045_ImagePresenter, inj2047_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2045_ImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2045_ImagePresenter;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImageView> inj2050_ImagesFilteredResultsAndImageView_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImageView>() {
    public ImagesFilteredResultsAndImageView getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImagesFilteredResultsAndImageView inj305_ImagesFilteredResultsAndImageView = new ImagesFilteredResultsAndImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj305_ImagesFilteredResultsAndImageView);
      return inj305_ImagesFilteredResultsAndImageView;
    }
  };
  private final CreationalCallback<ImageFilteredResultsView> inj2051_ImageFilteredResultsView_creationalCallback = new CreationalCallback<ImageFilteredResultsView>() {
    public ImageFilteredResultsView getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImageFilteredResultsView inj307_ImageFilteredResultsView = new ImageFilteredResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj307_ImageFilteredResultsView);
      return inj307_ImageFilteredResultsView;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImagePresenter> inj2049_ImagesFilteredResultsAndImagePresenter_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImagePresenter>() {
    public ImagesFilteredResultsAndImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImagesFilteredResultsAndImagePresenter inj2048_ImagesFilteredResultsAndImagePresenter = new ImagesFilteredResultsAndImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2048_ImagesFilteredResultsAndImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_display(inj2048_ImagesFilteredResultsAndImagePresenter, inj2050_ImagesFilteredResultsAndImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(inj2048_ImagesFilteredResultsAndImagePresenter, inj2051_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_imageDisplay(inj2048_ImagesFilteredResultsAndImagePresenter, inj2047_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2048_ImagesFilteredResultsAndImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2048_ImagesFilteredResultsAndImagePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj2052_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SenderProvider inj2024_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2024_SenderProvider);
      return inj2024_SenderProvider;
    }
  };
  private final SenderProvider inj2024_SenderProvider = inj2052_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj2053_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final InitBallotProvider inj2016_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2016_InitBallotProvider);
      return inj2016_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj2016_InitBallotProvider = inj2053_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj2056_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchView inj1907_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1907_SearchView);
      return inj1907_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj2055_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchPresenter inj2054_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2054_SearchPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchPresenter_eventBus(inj2054_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchPresenter_display(inj2054_SearchPresenter, inj2056_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2054_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2054_SearchPresenter;
    }
  };
  private final CreationalCallback<TagProjectsView> inj2059_TagProjectsView_creationalCallback = new CreationalCallback<TagProjectsView>() {
    public TagProjectsView getInstance(final CreationalContext context) {
      Class beanType = TagProjectsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagProjectsView inj510_TagProjectsView = new TagProjectsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj510_TagProjectsView);
      return inj510_TagProjectsView;
    }
  };
  private final CreationalCallback<TagProjectsPresenter> inj2058_TagProjectsPresenter_creationalCallback = new CreationalCallback<TagProjectsPresenter>() {
    public TagProjectsPresenter getInstance(final CreationalContext context) {
      Class beanType = TagProjectsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagProjectsPresenter inj2057_TagProjectsPresenter = new TagProjectsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2057_TagProjectsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagProjectsPresenter_display(inj2057_TagProjectsPresenter, inj2059_TagProjectsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2057_TagProjectsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2057_TagProjectsPresenter;
    }
  };
  private final CreationalCallback<TopicBugsPresenter> inj2061_TopicBugsPresenter_creationalCallback = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicBugsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicBugsPresenter inj2060_TopicBugsPresenter = new TopicBugsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2060_TopicBugsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2060_TopicBugsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2060_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj2062_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final CallerProvider inj2008_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2008_CallerProvider);
      return inj2008_CallerProvider;
    }
  };
  private final CallerProvider inj2008_CallerProvider = inj2062_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj2063_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final RootPanelProvider inj2014_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2014_RootPanelProvider);
      return inj2014_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj2014_RootPanelProvider = inj2063_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsView> inj2066_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicTagsView inj323_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj323_TopicTagsView);
      return inj323_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj2065_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicTagsPresenter inj2064_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2064_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicTagsPresenter_display(inj2064_TopicTagsPresenter, inj2066_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2064_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2064_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj2069_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final WelcomeView inj1908_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1908_WelcomeView);
      return inj1908_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj2068_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final WelcomePresenter inj2067_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2067_WelcomePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(inj2067_WelcomePresenter, inj2069_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2067_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2067_WelcomePresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj2071_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicRenderedPresenter inj2070_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2070_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2070_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2070_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicRevisionsPresenter> inj2073_TopicRevisionsPresenter_creationalCallback = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicRevisionsPresenter inj2072_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2072_TopicRevisionsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2072_TopicRevisionsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2072_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<TagsFilteredResultsAndTagView> inj2076_TagsFilteredResultsAndTagView_creationalCallback = new CreationalCallback<TagsFilteredResultsAndTagView>() {
    public TagsFilteredResultsAndTagView getInstance(final CreationalContext context) {
      Class beanType = TagsFilteredResultsAndTagView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagsFilteredResultsAndTagView inj509_TagsFilteredResultsAndTagView = new TagsFilteredResultsAndTagView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj509_TagsFilteredResultsAndTagView);
      return inj509_TagsFilteredResultsAndTagView;
    }
  };
  private final CreationalCallback<TagFilteredResultsView> inj2077_TagFilteredResultsView_creationalCallback = new CreationalCallback<TagFilteredResultsView>() {
    public TagFilteredResultsView getInstance(final CreationalContext context) {
      Class beanType = TagFilteredResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagFilteredResultsView inj508_TagFilteredResultsView = new TagFilteredResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj508_TagFilteredResultsView);
      return inj508_TagFilteredResultsView;
    }
  };
  private final CreationalCallback<TagsFilteredResultsAndTagPresenter> inj2075_TagsFilteredResultsAndTagPresenter_creationalCallback = new CreationalCallback<TagsFilteredResultsAndTagPresenter>() {
    public TagsFilteredResultsAndTagPresenter getInstance(final CreationalContext context) {
      Class beanType = TagsFilteredResultsAndTagPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagsFilteredResultsAndTagPresenter inj2074_TagsFilteredResultsAndTagPresenter = new TagsFilteredResultsAndTagPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2074_TagsFilteredResultsAndTagPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_display(inj2074_TagsFilteredResultsAndTagPresenter, inj2076_TagsFilteredResultsAndTagView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_filteredResultsDisplay(inj2074_TagsFilteredResultsAndTagPresenter, inj2077_TagFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_resultDisplay(inj2074_TagsFilteredResultsAndTagPresenter, inj2044_TagView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_projectsDisplay(inj2074_TagsFilteredResultsAndTagPresenter, inj2059_TagProjectsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_categoriesDisplay(inj2074_TagsFilteredResultsAndTagPresenter, inj2037_TagCategoriesView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2074_TagsFilteredResultsAndTagPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2074_TagsFilteredResultsAndTagPresenter;
    }
  };
  private final CreationalCallback<SearchResultsView> inj2080_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchResultsView inj1906_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1906_SearchResultsView);
      return inj1906_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj2081_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicView inj324_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj324_TopicView);
      return inj324_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj2079_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchResultsPresenter inj2078_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2078_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsPresenter_display(inj2078_SearchResultsPresenter, inj2080_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsPresenter_topicViewDisplay(inj2078_SearchResultsPresenter, inj2081_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2078_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2078_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj2082_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final DisposerProvider inj2010_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2010_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj2010_DisposerProvider, inj2020_IOCBeanManagerProvider.get());
      return inj2010_DisposerProvider;
    }
  };
  private final DisposerProvider inj2010_DisposerProvider = inj2082_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLPresenter> inj2084_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicXMLPresenter inj2083_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2083_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2083_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2083_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<ImageFilteredResultsPresenter> inj2086_ImageFilteredResultsPresenter_creationalCallback = new CreationalCallback<ImageFilteredResultsPresenter>() {
    public ImageFilteredResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final ImageFilteredResultsPresenter inj2085_ImageFilteredResultsPresenter = new ImageFilteredResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2085_ImageFilteredResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_image_ImageFilteredResultsPresenter_display(inj2085_ImageFilteredResultsPresenter, inj2051_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2085_ImageFilteredResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2085_ImageFilteredResultsPresenter;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicView> inj2089_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchResultsAndTopicView inj1909_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1909_SearchResultsAndTopicView);
      return inj1909_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj2090_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicXMLView inj327_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj327_TopicXMLView);
      return inj327_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj2091_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicRenderedView inj325_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj325_TopicRenderedView);
      return inj325_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj2092_TopicBugsView_creationalCallback = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      Class beanType = TopicBugsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicBugsView inj326_TopicBugsView = new TopicBugsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj326_TopicBugsView);
      return inj326_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj2093_TopicRevisionsView_creationalCallback = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicRevisionsView inj322_TopicRevisionsView = new TopicRevisionsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj322_TopicRevisionsView);
      return inj322_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj2088_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final SearchResultsAndTopicPresenter inj2087_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2087_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_display(inj2087_SearchResultsAndTopicPresenter, inj2089_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicViewDisplay(inj2087_SearchResultsAndTopicPresenter, inj2081_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicXMLDisplay(inj2087_SearchResultsAndTopicPresenter, inj2090_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj2087_SearchResultsAndTopicPresenter, inj2091_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(inj2087_SearchResultsAndTopicPresenter, inj2091_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_searchResultsDisplay(inj2087_SearchResultsAndTopicPresenter, inj2080_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj2087_SearchResultsAndTopicPresenter, inj2041_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicTagsDisplay(inj2087_SearchResultsAndTopicPresenter, inj2066_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicBugsDisplay(inj2087_SearchResultsAndTopicPresenter, inj2092_TopicBugsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicRevisionsDisplay(inj2087_SearchResultsAndTopicPresenter, inj2093_TopicRevisionsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2087_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2087_SearchResultsAndTopicPresenter;
    }
  };
  private final CreationalCallback<TopicPresenter> inj2095_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TopicPresenter inj2094_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2094_TopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicPresenter_display(inj2094_TopicPresenter, inj2081_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2094_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2094_TopicPresenter;
    }
  };
  private final CreationalCallback<TagFilteredResultsPresenter> inj2097_TagFilteredResultsPresenter_creationalCallback = new CreationalCallback<TagFilteredResultsPresenter>() {
    public TagFilteredResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = TagFilteredResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_4464356;
      final TagFilteredResultsPresenter inj2096_TagFilteredResultsPresenter = new TagFilteredResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2096_TagFilteredResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagFilteredResultsPresenter_display(inj2096_TagFilteredResultsPresenter, inj2077_TagFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj2096_TagFilteredResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj659_App));
      return inj2096_TagFilteredResultsPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj2031_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj2027_RequestDispatcherProvider_creationalCallback, inj2012_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj2027_RequestDispatcherProvider_creationalCallback, inj2012_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj2028_InstanceProvider_creationalCallback, inj2026_InstanceProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj2028_InstanceProvider_creationalCallback, inj2026_InstanceProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2029_IOCBeanManagerProvider_creationalCallback, inj2020_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2029_IOCBeanManagerProvider_creationalCallback, inj2020_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(App.class, App.class, inj2030_App_creationalCallback, inj659_App, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj2006_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj2006_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(AppController.class, AppController.class, inj2033_AppController_creationalCallback, inj2032_AppController, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj2033_AppController_creationalCallback, inj2032_AppController, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj2033_AppController_creationalCallback, inj2032_AppController, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj2033_AppController_creationalCallback, inj2032_AppController, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj2034_EventProvider_creationalCallback, inj2022_EventProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj2034_EventProvider_creationalCallback, inj2022_EventProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagCategoriesView.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Display.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewInterface.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewBase.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TagCategoriesView.class, inj2037_TagCategoriesView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagCategoriesPresenter.class, TagCategoriesPresenter.class, inj2036_TagCategoriesPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TagCategoriesPresenter.class, inj2036_TagCategoriesPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TagCategoriesPresenter.class, inj2036_TagCategoriesPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj2038_MessageBusProvider_creationalCallback, inj2018_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj2038_MessageBusProvider_creationalCallback, inj2018_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj2041_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj2040_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj2040_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj2040_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagView.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter.Display.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewInterface.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewBase.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TagView.class, inj2044_TagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagPresenter.class, TagPresenter.class, inj2043_TagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TagPresenter.class, inj2043_TagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TagPresenter.class, inj2043_TagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj2047_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter.Display.class, ImageView.class, inj2047_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj2047_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj2047_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj2046_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ImagePresenterBase.class, ImagePresenter.class, inj2046_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj2046_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj2046_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImagesFilteredResultsAndImageView.class, ImagesFilteredResultsAndImageView.class, inj2050_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter.Display.class, ImagesFilteredResultsAndImageView.class, inj2050_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImagesFilteredResultsAndImageView.class, inj2050_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, ImagesFilteredResultsAndImageView.class, inj2050_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImageFilteredResultsView.class, ImageFilteredResultsView.class, inj2051_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display.class, ImageFilteredResultsView.class, inj2051_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageFilteredResultsView.class, inj2051_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, ImageFilteredResultsView.class, inj2051_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImagesFilteredResultsAndImagePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2049_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ImagePresenterBase.class, ImagesFilteredResultsAndImagePresenter.class, inj2049_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TemplatePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2049_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, ImagesFilteredResultsAndImagePresenter.class, inj2049_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj2052_SenderProvider_creationalCallback, inj2024_SenderProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj2052_SenderProvider_creationalCallback, inj2024_SenderProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj2053_InitBallotProvider_creationalCallback, inj2016_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj2053_InitBallotProvider_creationalCallback, inj2016_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj2056_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter.Display.class, SearchView.class, inj2056_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj2056_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj2056_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj2055_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj2055_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj2055_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagProjectsView.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter.Display.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewInterface.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagViewBase.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TagProjectsView.class, inj2059_TagProjectsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagProjectsPresenter.class, TagProjectsPresenter.class, inj2058_TagProjectsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TagProjectsPresenter.class, inj2058_TagProjectsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TagProjectsPresenter.class, inj2058_TagProjectsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj2061_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj2061_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj2061_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj2062_CallerProvider_creationalCallback, inj2008_CallerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj2062_CallerProvider_creationalCallback, inj2008_CallerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj2063_RootPanelProvider_creationalCallback, inj2014_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj2063_RootPanelProvider_creationalCallback, inj2014_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicTagsPresenter.Display.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj2066_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj2065_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj2065_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj2065_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj2069_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj2069_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj2069_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj2069_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj2068_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj2068_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj2068_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj2071_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj2071_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj2071_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj2073_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj2073_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj2073_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagsFilteredResultsAndTagView.class, TagsFilteredResultsAndTagView.class, inj2076_TagsFilteredResultsAndTagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter.Display.class, TagsFilteredResultsAndTagView.class, inj2076_TagsFilteredResultsAndTagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagsFilteredResultsAndTagView.class, inj2076_TagsFilteredResultsAndTagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TagsFilteredResultsAndTagView.class, inj2076_TagsFilteredResultsAndTagView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagFilteredResultsView.class, TagFilteredResultsView.class, inj2077_TagFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display.class, TagFilteredResultsView.class, inj2077_TagFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TagFilteredResultsView.class, inj2077_TagFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TagFilteredResultsView.class, inj2077_TagFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagsFilteredResultsAndTagPresenter.class, TagsFilteredResultsAndTagPresenter.class, inj2075_TagsFilteredResultsAndTagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TagPresenterBase.class, TagsFilteredResultsAndTagPresenter.class, inj2075_TagsFilteredResultsAndTagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TemplatePresenter.class, TagsFilteredResultsAndTagPresenter.class, inj2075_TagsFilteredResultsAndTagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TagsFilteredResultsAndTagPresenter.class, inj2075_TagsFilteredResultsAndTagPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj2080_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display.class, SearchResultsView.class, inj2080_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj2080_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj2080_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.Display.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj2081_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj2079_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj2079_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj2079_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj2082_DisposerProvider_creationalCallback, inj2010_DisposerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj2082_DisposerProvider_creationalCallback, inj2010_DisposerProvider, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj2084_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj2084_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj2084_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(ImageFilteredResultsPresenter.class, ImageFilteredResultsPresenter.class, inj2086_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, ImageFilteredResultsPresenter.class, inj2086_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, ImageFilteredResultsPresenter.class, inj2086_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj2089_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj2089_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj2089_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj2089_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLPresenter.Display.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj2090_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj2091_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicBugsPresenter.Display.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj2092_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj2093_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj2088_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj2088_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj2088_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj2095_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj2095_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj2095_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(TagFilteredResultsPresenter.class, TagFilteredResultsPresenter.class, inj2097_TagFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, true);
    injContext.addBean(TemplatePresenter.class, TagFilteredResultsPresenter.class, inj2097_TagFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
    injContext.addBean(Presenter.class, TagFilteredResultsPresenter.class, inj2097_TagFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_4464356, null, false);
  }

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagePresenter_display(ImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_display(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_categoriesDisplay(TagsFilteredResultsAndTagPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::categoriesDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicXMLErrorsPresenter_display(TopicXMLErrorsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter::display = value;
  }-*/;

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_image_ImageFilteredResultsPresenter_display(ImageFilteredResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicXMLDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicXMLDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicViewDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::imageFilteredResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsPresenter_display(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsPresenter_topicViewDisplay(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagPresenter_display(TagPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagFilteredResultsPresenter_display(TagFilteredResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchPresenter_eventBus(SearchPresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagProjectsPresenter_display(TagProjectsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_display(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_filteredResultsDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::filteredResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_resultDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::resultDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicSplitPanelRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagCategoriesPresenter_display(TagCategoriesPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagCategoriesPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topic_TopicTagsPresenter_display(TopicTagsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicTagsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(WelcomePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicBugsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicBugsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicBugsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_tag_TagsFilteredResultsAndTagPresenter_projectsDisplay(TagsFilteredResultsAndTagPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagProjectsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter::projectsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_topicsearch_SearchResultsAndTopicPresenter_topicRevisionsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicRevisionsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.topicsearch.SearchResultsAndTopicPresenter::topicRevisionsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_image_ImagesFilteredResultsAndImagePresenter_imageDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter::imageDisplay = value;
  }-*/;

  public native static HandlerManager org_jboss_pressgangccms_client_local_App_produceEventBus(App instance) /*-{
    return instance.@org.jboss.pressgangccms.client.local.App::produceEventBus()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}