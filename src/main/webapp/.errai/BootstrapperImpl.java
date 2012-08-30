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
import org.jboss.pressgangccms.client.local.presenter.ImagePresenter;
import org.jboss.pressgangccms.client.local.presenter.ImagePresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicBugsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicRevisionsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.ImageView;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicBugsView;
import org.jboss.pressgangccms.client.local.view.TopicRenderedView;
import org.jboss.pressgangccms.client.local.view.TopicRevisionsView;
import org.jboss.pressgangccms.client.local.view.TopicTagsView;
import org.jboss.pressgangccms.client.local.view.TopicView;
import org.jboss.pressgangccms.client.local.view.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.view.TopicXMLView;
import org.jboss.pressgangccms.client.local.view.WelcomeView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.view.base.TopicViewBase;
import org.jboss.pressgangccms.client.local.view.base.TopicViewInterface;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any javax_enterprise_inject_Any_30893458 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_29848851 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_193139 = new Annotation[] { javax_enterprise_inject_Any_30893458, javax_enterprise_inject_Default_29848851 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj3995_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final IOCBeanManagerProvider inj3988_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3988_IOCBeanManagerProvider);
      return inj3988_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj3988_IOCBeanManagerProvider = inj3995_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj3996_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final App inj2646_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2646_App);
      final org_jboss_pressgangccms_client_local_AppController_inj3997_proxy inj3997_proxy = new org_jboss_pressgangccms_client_local_AppController_inj3997_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj3997_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj3997_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_193139);
      org_jboss_pressgangccms_client_local_App_appController(inj2646_App, inj3997_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj2646_App.startApp();
            }
          });
        }
      });
      return inj2646_App;
    }
  };
  private final App inj2646_App = inj3996_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj3974_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var2 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_193139), var2);
      return var2;
    }
  };
  private final CreationalCallback<AppController> inj3999_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final AppController inj3998_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3998_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj3998_AppController, inj3988_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj3998_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj3998_AppController;
    }
  };
  private final AppController inj3998_AppController = inj3999_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<ImageView> inj4002_ImageView_creationalCallback = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      Class beanType = ImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final ImageView inj3604_ImageView = new ImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3604_ImageView);
      return inj3604_ImageView;
    }
  };
  private final CreationalCallback<ImagePresenter> inj4001_ImagePresenter_creationalCallback = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final ImagePresenter inj4000_ImagePresenter = new ImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4000_ImagePresenter);
      org_jboss_pressgangccms_client_local_presenter_ImagePresenter_display(inj4000_ImagePresenter, inj4002_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4000_ImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4000_ImagePresenter;
    }
  };
  private final CreationalCallback<SearchResultsView> inj4005_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchResultsView inj3609_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3609_SearchResultsView);
      return inj3609_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj4006_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicView inj3607_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3607_TopicView);
      return inj3607_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj4004_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchResultsPresenter inj4003_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4003_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj4003_SearchResultsPresenter, inj4005_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj4003_SearchResultsPresenter, inj4006_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4003_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4003_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<TopicRevisionsPresenter> inj4008_TopicRevisionsPresenter_creationalCallback = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicRevisionsPresenter inj4007_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4007_TopicRevisionsPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4007_TopicRevisionsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4007_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj4009_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final RequestDispatcherProvider inj3980_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3980_RequestDispatcherProvider);
      return inj3980_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj3980_RequestDispatcherProvider = inj4009_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj4010_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final InstanceProvider inj3994_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3994_InstanceProvider);
      return inj3994_InstanceProvider;
    }
  };
  private final InstanceProvider inj3994_InstanceProvider = inj4010_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj4011_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final EventProvider inj3990_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3990_EventProvider);
      return inj3990_EventProvider;
    }
  };
  private final EventProvider inj3990_EventProvider = inj4011_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj4012_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final MessageBusProvider inj3986_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3986_MessageBusProvider);
      return inj3986_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj3986_MessageBusProvider = inj4012_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicBugsPresenter> inj4014_TopicBugsPresenter_creationalCallback = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicBugsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicBugsPresenter inj4013_TopicBugsPresenter = new TopicBugsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4013_TopicBugsPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4013_TopicBugsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4013_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<TopicXMLErrorsView> inj4017_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicXMLErrorsView inj3615_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3615_TopicXMLErrorsView);
      return inj3615_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj4016_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicXMLErrorsPresenter inj4015_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4015_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(inj4015_TopicXMLErrorsPresenter, inj4017_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4015_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4015_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj4020_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final WelcomeView inj3608_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3608_WelcomeView);
      return inj3608_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj4019_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final WelcomePresenter inj4018_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4018_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj4018_WelcomePresenter, inj4020_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4018_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4018_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj4021_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SenderProvider inj3992_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3992_SenderProvider);
      return inj3992_SenderProvider;
    }
  };
  private final SenderProvider inj3992_SenderProvider = inj4021_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj4022_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final InitBallotProvider inj3984_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3984_InitBallotProvider);
      return inj3984_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj3984_InitBallotProvider = inj4022_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj4024_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicPresenter inj4023_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4023_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj4023_TopicPresenter, inj4006_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4023_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4023_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj4025_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final CallerProvider inj3976_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3976_CallerProvider);
      return inj3976_CallerProvider;
    }
  };
  private final CallerProvider inj3976_CallerProvider = inj4025_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj4028_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchView inj3610_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3610_SearchView);
      return inj3610_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj4027_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchPresenter inj4026_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4026_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj4026_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj4026_SearchPresenter, inj4028_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4026_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4026_SearchPresenter;
    }
  };
  private final CreationalCallback<RootPanelProvider> inj4029_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final RootPanelProvider inj3982_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3982_RootPanelProvider);
      return inj3982_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj3982_RootPanelProvider = inj4029_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsView> inj4032_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicTagsView inj3605_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3605_TopicTagsView);
      return inj3605_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj4031_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicTagsPresenter inj4030_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4030_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(inj4030_TopicTagsPresenter, inj4032_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4030_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4030_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj4034_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicRenderedPresenter inj4033_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4033_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4033_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4033_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj4036_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicXMLPresenter inj4035_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4035_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4035_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4035_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj4037_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final DisposerProvider inj3978_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3978_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj3978_DisposerProvider, inj3988_IOCBeanManagerProvider.get());
      return inj3978_DisposerProvider;
    }
  };
  private final DisposerProvider inj3978_DisposerProvider = inj4037_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj4040_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchResultsAndTopicView inj3611_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3611_SearchResultsAndTopicView);
      return inj3611_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj4041_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicXMLView inj3606_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3606_TopicXMLView);
      return inj3606_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj4042_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicRenderedView inj3613_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3613_TopicRenderedView);
      return inj3613_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj4043_TopicBugsView_creationalCallback = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      Class beanType = TopicBugsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicBugsView inj3614_TopicBugsView = new TopicBugsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3614_TopicBugsView);
      return inj3614_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj4044_TopicRevisionsView_creationalCallback = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final TopicRevisionsView inj3612_TopicRevisionsView = new TopicRevisionsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3612_TopicRevisionsView);
      return inj3612_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj4039_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_193139;
      final SearchResultsAndTopicPresenter inj4038_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4038_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj4038_SearchResultsAndTopicPresenter, inj4040_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj4038_SearchResultsAndTopicPresenter, inj4006_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj4038_SearchResultsAndTopicPresenter, inj4041_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj4038_SearchResultsAndTopicPresenter, inj4042_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(inj4038_SearchResultsAndTopicPresenter, inj4042_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj4038_SearchResultsAndTopicPresenter, inj4005_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj4038_SearchResultsAndTopicPresenter, inj4017_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj4038_SearchResultsAndTopicPresenter, inj4032_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(inj4038_SearchResultsAndTopicPresenter, inj4043_TopicBugsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(inj4038_SearchResultsAndTopicPresenter, inj4044_TopicRevisionsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj4038_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2646_App));
      return inj4038_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj3997_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj3995_IOCBeanManagerProvider_creationalCallback, inj3988_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj3995_IOCBeanManagerProvider_creationalCallback, inj3988_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(App.class, App.class, inj3996_App_creationalCallback, inj2646_App, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj3974_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj3974_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(AppController.class, AppController.class, inj3999_AppController_creationalCallback, inj3998_AppController, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj3999_AppController_creationalCallback, inj3998_AppController, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj3999_AppController_creationalCallback, inj3998_AppController, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj3999_AppController_creationalCallback, inj3998_AppController, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj4002_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Display.class, ImageView.class, inj4002_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj4002_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj4002_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj4001_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj4001_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj4001_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj4005_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display.class, SearchResultsView.class, inj4005_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj4005_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj4005_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj4006_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj4004_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj4004_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj4004_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj4008_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj4008_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj4008_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj4009_RequestDispatcherProvider_creationalCallback, inj3980_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj4009_RequestDispatcherProvider_creationalCallback, inj3980_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj4010_InstanceProvider_creationalCallback, inj3994_InstanceProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj4010_InstanceProvider_creationalCallback, inj3994_InstanceProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj4011_EventProvider_creationalCallback, inj3990_EventProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj4011_EventProvider_creationalCallback, inj3990_EventProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj4012_MessageBusProvider_creationalCallback, inj3986_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj4012_MessageBusProvider_creationalCallback, inj3986_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj4014_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj4014_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj4014_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj4017_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj4016_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj4016_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj4016_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj4020_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj4020_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj4020_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj4020_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj4019_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj4019_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj4019_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj4021_SenderProvider_creationalCallback, inj3992_SenderProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj4021_SenderProvider_creationalCallback, inj3992_SenderProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj4022_InitBallotProvider_creationalCallback, inj3984_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj4022_InitBallotProvider_creationalCallback, inj3984_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj4024_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj4024_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj4024_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj4025_CallerProvider_creationalCallback, inj3976_CallerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj4025_CallerProvider_creationalCallback, inj3976_CallerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj4028_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj4028_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj4028_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj4028_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj4027_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj4027_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj4027_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj4029_RootPanelProvider_creationalCallback, inj3982_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj4029_RootPanelProvider_creationalCallback, inj3982_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj4032_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj4031_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj4031_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj4031_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj4034_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj4034_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj4034_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj4036_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj4036_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj4036_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj4037_DisposerProvider_creationalCallback, inj3978_DisposerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj4037_DisposerProvider_creationalCallback, inj3978_DisposerProvider, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj4040_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj4040_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj4040_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj4040_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj4041_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj4042_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicBugsPresenter.Display.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj4043_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj4044_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj4039_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj4039_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj4039_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_193139, null, false);
  }

  private native static void org_jboss_pressgangccms_client_local_presenter_ImagePresenter_display(ImagePresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.ImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(TopicTagsPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(TopicXMLErrorsPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicSplitPanelRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(WelcomePresenter instance, org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicBugsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicBugsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicRevisionsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicRevisionsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(SearchPresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchPresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicXMLDisplay = value;
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