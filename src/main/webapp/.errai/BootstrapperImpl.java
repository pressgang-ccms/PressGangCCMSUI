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
import org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.ImageFilteredResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.ImageView;
import org.jboss.pressgangccms.client.local.mvp.view.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicBugsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicRenderedView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicRevisionsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicTagsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicXMLView;
import org.jboss.pressgangccms.client.local.mvp.view.WelcomeView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.base.TopicViewBase;
import org.jboss.pressgangccms.client.local.mvp.view.base.TopicViewInterface;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Default javax_enterprise_inject_Default_20559803 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_5072385 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_7108486 = new Annotation[] { javax_enterprise_inject_Default_20559803, javax_enterprise_inject_Any_5072385 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj4095_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final IOCBeanManagerProvider inj4088_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4088_IOCBeanManagerProvider);
      return inj4088_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj4088_IOCBeanManagerProvider = inj4095_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj4096_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final App inj2718_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2718_App);
      final org_jboss_pressgangccms_client_local_AppController_inj4097_proxy inj4097_proxy = new org_jboss_pressgangccms_client_local_AppController_inj4097_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj4097_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj4097_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_7108486);
      org_jboss_pressgangccms_client_local_App_appController(inj2718_App, inj4097_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj2718_App.startApp();
            }
          });
        }
      });
      return inj2718_App;
    }
  };
  private final App inj2718_App = inj4096_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj4074_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var2 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_7108486), var2);
      return var2;
    }
  };
  private final CreationalCallback<AppController> inj4099_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final AppController inj4098_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4098_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj4098_AppController, inj4088_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj4098_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4098_AppController;
    }
  };
  private final AppController inj4098_AppController = inj4099_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj4102_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchResultsView inj3972_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3972_SearchResultsView);
      return inj3972_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj4103_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicView inj3969_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3969_TopicView);
      return inj3969_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj4101_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchResultsPresenter inj4100_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4100_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_display(inj4100_SearchResultsPresenter, inj4102_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_topicViewDisplay(inj4100_SearchResultsPresenter, inj4103_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4100_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4100_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<SearchView> inj4106_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchView inj3973_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3973_SearchView);
      return inj3973_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj4105_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchPresenter inj4104_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4104_SearchPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_eventBus(inj4104_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_display(inj4104_SearchPresenter, inj4106_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4104_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4104_SearchPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj4107_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final RequestDispatcherProvider inj4080_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4080_RequestDispatcherProvider);
      return inj4080_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj4080_RequestDispatcherProvider = inj4107_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj4108_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final InstanceProvider inj4094_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4094_InstanceProvider);
      return inj4094_InstanceProvider;
    }
  };
  private final InstanceProvider inj4094_InstanceProvider = inj4108_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsView> inj4111_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicTagsView inj3967_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3967_TopicTagsView);
      return inj3967_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj4110_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicTagsPresenter inj4109_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4109_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicTagsPresenter_display(inj4109_TopicTagsPresenter, inj4111_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4109_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4109_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj4113_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicRenderedPresenter inj4112_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4112_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4112_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4112_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<EventProvider> inj4114_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final EventProvider inj4090_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4090_EventProvider);
      return inj4090_EventProvider;
    }
  };
  private final EventProvider inj4090_EventProvider = inj4114_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj4115_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final MessageBusProvider inj4086_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4086_MessageBusProvider);
      return inj4086_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj4086_MessageBusProvider = inj4115_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLErrorsView> inj4118_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicXMLErrorsView inj3965_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3965_TopicXMLErrorsView);
      return inj3965_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj4117_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicXMLErrorsPresenter inj4116_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4116_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicXMLErrorsPresenter_display(inj4116_TopicXMLErrorsPresenter, inj4118_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4116_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4116_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImageView> inj4121_ImagesFilteredResultsAndImageView_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImageView>() {
    public ImagesFilteredResultsAndImageView getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImagesFilteredResultsAndImageView inj3978_ImagesFilteredResultsAndImageView = new ImagesFilteredResultsAndImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3978_ImagesFilteredResultsAndImageView);
      return inj3978_ImagesFilteredResultsAndImageView;
    }
  };
  private final CreationalCallback<ImageFilteredResultsView> inj4122_ImageFilteredResultsView_creationalCallback = new CreationalCallback<ImageFilteredResultsView>() {
    public ImageFilteredResultsView getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImageFilteredResultsView inj3971_ImageFilteredResultsView = new ImageFilteredResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3971_ImageFilteredResultsView);
      return inj3971_ImageFilteredResultsView;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImagePresenter> inj4120_ImagesFilteredResultsAndImagePresenter_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImagePresenter>() {
    public ImagesFilteredResultsAndImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImagesFilteredResultsAndImagePresenter inj4119_ImagesFilteredResultsAndImagePresenter = new ImagesFilteredResultsAndImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4119_ImagesFilteredResultsAndImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_display(inj4119_ImagesFilteredResultsAndImagePresenter, inj4121_ImagesFilteredResultsAndImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(inj4119_ImagesFilteredResultsAndImagePresenter, inj4122_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4119_ImagesFilteredResultsAndImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4119_ImagesFilteredResultsAndImagePresenter;
    }
  };
  private final CreationalCallback<TopicBugsPresenter> inj4124_TopicBugsPresenter_creationalCallback = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicBugsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicBugsPresenter inj4123_TopicBugsPresenter = new TopicBugsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4123_TopicBugsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4123_TopicBugsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4123_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj4125_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SenderProvider inj4092_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4092_SenderProvider);
      return inj4092_SenderProvider;
    }
  };
  private final SenderProvider inj4092_SenderProvider = inj4125_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj4126_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final InitBallotProvider inj4084_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4084_InitBallotProvider);
      return inj4084_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj4084_InitBallotProvider = inj4126_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicRevisionsPresenter> inj4128_TopicRevisionsPresenter_creationalCallback = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicRevisionsPresenter inj4127_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4127_TopicRevisionsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4127_TopicRevisionsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4127_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj4130_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicXMLPresenter inj4129_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4129_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4129_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4129_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<ImageFilteredResultsPresenter> inj4132_ImageFilteredResultsPresenter_creationalCallback = new CreationalCallback<ImageFilteredResultsPresenter>() {
    public ImageFilteredResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImageFilteredResultsPresenter inj4131_ImageFilteredResultsPresenter = new ImageFilteredResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4131_ImageFilteredResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImageFilteredResultsPresenter_display(inj4131_ImageFilteredResultsPresenter, inj4122_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4131_ImageFilteredResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4131_ImageFilteredResultsPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj4133_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final CallerProvider inj4076_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4076_CallerProvider);
      return inj4076_CallerProvider;
    }
  };
  private final CallerProvider inj4076_CallerProvider = inj4133_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj4134_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final RootPanelProvider inj4082_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4082_RootPanelProvider);
      return inj4082_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj4082_RootPanelProvider = inj4134_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj4137_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final WelcomeView inj3970_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3970_WelcomeView);
      return inj3970_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj4136_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final WelcomePresenter inj4135_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4135_WelcomePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(inj4135_WelcomePresenter, inj4137_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4135_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4135_WelcomePresenter;
    }
  };
  private final CreationalCallback<ImageView> inj4140_ImageView_creationalCallback = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      Class beanType = ImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImageView inj3966_ImageView = new ImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3966_ImageView);
      return inj3966_ImageView;
    }
  };
  private final CreationalCallback<ImagePresenter> inj4139_ImagePresenter_creationalCallback = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final ImagePresenter inj4138_ImagePresenter = new ImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4138_ImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagePresenter_display(inj4138_ImagePresenter, inj4140_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4138_ImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4138_ImagePresenter;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicView> inj4143_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchResultsAndTopicView inj3974_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3974_SearchResultsAndTopicView);
      return inj3974_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj4144_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicXMLView inj3968_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3968_TopicXMLView);
      return inj3968_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj4145_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicRenderedView inj3976_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3976_TopicRenderedView);
      return inj3976_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj4146_TopicBugsView_creationalCallback = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      Class beanType = TopicBugsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicBugsView inj3977_TopicBugsView = new TopicBugsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3977_TopicBugsView);
      return inj3977_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj4147_TopicRevisionsView_creationalCallback = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicRevisionsView inj3975_TopicRevisionsView = new TopicRevisionsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3975_TopicRevisionsView);
      return inj3975_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj4142_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final SearchResultsAndTopicPresenter inj4141_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4141_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_display(inj4141_SearchResultsAndTopicPresenter, inj4143_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj4141_SearchResultsAndTopicPresenter, inj4103_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj4141_SearchResultsAndTopicPresenter, inj4144_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj4141_SearchResultsAndTopicPresenter, inj4145_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(inj4141_SearchResultsAndTopicPresenter, inj4145_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj4141_SearchResultsAndTopicPresenter, inj4102_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj4141_SearchResultsAndTopicPresenter, inj4118_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj4141_SearchResultsAndTopicPresenter, inj4111_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(inj4141_SearchResultsAndTopicPresenter, inj4146_TopicBugsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(inj4141_SearchResultsAndTopicPresenter, inj4147_TopicRevisionsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4141_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4141_SearchResultsAndTopicPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj4148_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final DisposerProvider inj4078_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4078_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj4078_DisposerProvider, inj4088_IOCBeanManagerProvider.get());
      return inj4078_DisposerProvider;
    }
  };
  private final DisposerProvider inj4078_DisposerProvider = inj4148_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj4150_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_7108486;
      final TopicPresenter inj4149_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4149_TopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicPresenter_display(inj4149_TopicPresenter, inj4103_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj4149_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2718_App));
      return inj4149_TopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj4097_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj4095_IOCBeanManagerProvider_creationalCallback, inj4088_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj4095_IOCBeanManagerProvider_creationalCallback, inj4088_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(App.class, App.class, inj4096_App_creationalCallback, inj2718_App, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj4074_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj4074_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(AppController.class, AppController.class, inj4099_AppController_creationalCallback, inj4098_AppController, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj4099_AppController_creationalCallback, inj4098_AppController, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj4099_AppController_creationalCallback, inj4098_AppController, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj4099_AppController_creationalCallback, inj4098_AppController, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj4102_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Display.class, SearchResultsView.class, inj4102_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj4102_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj4102_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj4103_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj4101_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj4101_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj4101_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj4106_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter.Display.class, SearchView.class, inj4106_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj4106_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj4106_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj4105_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj4105_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj4105_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj4107_RequestDispatcherProvider_creationalCallback, inj4080_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj4107_RequestDispatcherProvider_creationalCallback, inj4080_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj4108_InstanceProvider_creationalCallback, inj4094_InstanceProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj4108_InstanceProvider_creationalCallback, inj4094_InstanceProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj4111_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj4110_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj4110_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj4110_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj4113_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj4113_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj4113_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj4114_EventProvider_creationalCallback, inj4090_EventProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj4114_EventProvider_creationalCallback, inj4090_EventProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj4115_MessageBusProvider_creationalCallback, inj4086_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj4115_MessageBusProvider_creationalCallback, inj4086_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj4118_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj4117_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj4117_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj4117_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImagesFilteredResultsAndImageView.class, ImagesFilteredResultsAndImageView.class, inj4121_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter.Display.class, ImagesFilteredResultsAndImageView.class, inj4121_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImagesFilteredResultsAndImageView.class, inj4121_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, ImagesFilteredResultsAndImageView.class, inj4121_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImageFilteredResultsView.class, ImageFilteredResultsView.class, inj4122_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display.class, ImageFilteredResultsView.class, inj4122_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageFilteredResultsView.class, inj4122_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, ImageFilteredResultsView.class, inj4122_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImagesFilteredResultsAndImagePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj4120_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj4120_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, ImagesFilteredResultsAndImagePresenter.class, inj4120_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj4124_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj4124_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj4124_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj4125_SenderProvider_creationalCallback, inj4092_SenderProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj4125_SenderProvider_creationalCallback, inj4092_SenderProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj4126_InitBallotProvider_creationalCallback, inj4084_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj4126_InitBallotProvider_creationalCallback, inj4084_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj4128_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj4128_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj4128_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj4130_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj4130_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj4130_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImageFilteredResultsPresenter.class, ImageFilteredResultsPresenter.class, inj4132_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, ImageFilteredResultsPresenter.class, inj4132_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, ImageFilteredResultsPresenter.class, inj4132_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj4133_CallerProvider_creationalCallback, inj4076_CallerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj4133_CallerProvider_creationalCallback, inj4076_CallerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj4134_RootPanelProvider_creationalCallback, inj4082_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj4134_RootPanelProvider_creationalCallback, inj4082_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj4137_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj4137_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj4137_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj4137_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj4136_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj4136_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj4136_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj4140_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter.Display.class, ImageView.class, inj4140_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj4140_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj4140_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj4139_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj4139_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj4139_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj4143_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj4143_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj4143_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj4143_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj4144_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj4145_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter.Display.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj4146_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj4147_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj4142_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj4142_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj4142_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj4148_DisposerProvider_creationalCallback, inj4078_DisposerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj4148_DisposerProvider_creationalCallback, inj4078_DisposerProvider, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj4150_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj4150_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj4150_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_7108486, null, false);
  }

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicTagsPresenter_display(TopicTagsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicXMLDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicSplitPanelRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_topicViewDisplay(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter::imageFilteredResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_display(SearchResultsPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagePresenter_display(ImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicRevisionsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImageFilteredResultsPresenter_display(ImageFilteredResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicBugsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(WelcomePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_eventBus(SearchPresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_display(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicXMLErrorsPresenter_display(TopicXMLErrorsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter::display = value;
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