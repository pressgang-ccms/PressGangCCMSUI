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
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicRenderedView;
import org.jboss.pressgangccms.client.local.view.TopicView;
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
  private final Default javax_enterprise_inject_Default_28261704 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_31488832 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_15643482 = new Annotation[] { javax_enterprise_inject_Default_28261704, javax_enterprise_inject_Any_31488832 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj13637_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final IOCBeanManagerProvider inj13630_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13630_IOCBeanManagerProvider);
      return inj13630_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj13630_IOCBeanManagerProvider = inj13637_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj13638_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final App inj12334_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj12334_App);
      final org_jboss_pressgangccms_client_local_AppController_inj13639_proxy inj13639_proxy = new org_jboss_pressgangccms_client_local_AppController_inj13639_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj13639_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj13639_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_15643482);
      org_jboss_pressgangccms_client_local_App_appController(inj12334_App, inj13639_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj12334_App.startApp();
            }
          });
        }
      });
      return inj12334_App;
    }
  };
  private final App inj12334_App = inj13638_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj13616_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var7 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_15643482), var7);
      return var7;
    }
  };
  private final CreationalCallback<AppController> inj13641_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final AppController inj13640_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13640_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj13640_AppController, inj13630_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj13640_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13640_AppController;
    }
  };
  private final AppController inj13640_AppController = inj13641_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj13644_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchResultsView inj13271_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13271_SearchResultsView);
      return inj13271_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj13645_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicView inj13275_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13275_TopicView);
      return inj13275_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj13643_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchResultsPresenter inj13642_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13642_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj13642_SearchResultsPresenter, inj13644_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj13642_SearchResultsPresenter, inj13645_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13642_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13642_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj13646_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final RequestDispatcherProvider inj13622_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13622_RequestDispatcherProvider);
      return inj13622_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj13622_RequestDispatcherProvider = inj13646_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj13647_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final InstanceProvider inj13636_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13636_InstanceProvider);
      return inj13636_InstanceProvider;
    }
  };
  private final InstanceProvider inj13636_InstanceProvider = inj13647_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj13648_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final EventProvider inj13632_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13632_EventProvider);
      return inj13632_EventProvider;
    }
  };
  private final EventProvider inj13632_EventProvider = inj13648_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj13649_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final MessageBusProvider inj13628_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13628_MessageBusProvider);
      return inj13628_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj13628_MessageBusProvider = inj13649_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj13652_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final WelcomeView inj13277_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13277_WelcomeView);
      return inj13277_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj13651_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final WelcomePresenter inj13650_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13650_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj13650_WelcomePresenter, inj13652_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13650_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13650_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj13653_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SenderProvider inj13634_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13634_SenderProvider);
      return inj13634_SenderProvider;
    }
  };
  private final SenderProvider inj13634_SenderProvider = inj13653_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj13654_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final InitBallotProvider inj13626_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13626_InitBallotProvider);
      return inj13626_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj13626_InitBallotProvider = inj13654_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj13656_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicPresenter inj13655_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13655_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj13655_TopicPresenter, inj13645_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13655_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13655_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj13657_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final CallerProvider inj13618_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13618_CallerProvider);
      return inj13618_CallerProvider;
    }
  };
  private final CallerProvider inj13618_CallerProvider = inj13657_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj13658_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final RootPanelProvider inj13624_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13624_RootPanelProvider);
      return inj13624_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj13624_RootPanelProvider = inj13658_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj13661_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchView inj13272_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13272_SearchView);
      return inj13272_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj13660_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchPresenter inj13659_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13659_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj13659_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj13659_SearchPresenter, inj13661_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13659_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13659_SearchPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj13663_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicRenderedPresenter inj13662_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13662_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13662_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13662_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj13665_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicXMLPresenter inj13664_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13664_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13664_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13664_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj13666_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final DisposerProvider inj13620_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13620_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj13620_DisposerProvider, inj13630_IOCBeanManagerProvider.get());
      return inj13620_DisposerProvider;
    }
  };
  private final DisposerProvider inj13620_DisposerProvider = inj13666_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj13669_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchResultsAndTopicView inj13273_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13273_SearchResultsAndTopicView);
      return inj13273_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj13670_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicXMLView inj13274_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13274_TopicXMLView);
      return inj13274_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj13671_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final TopicRenderedView inj13276_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13276_TopicRenderedView);
      return inj13276_TopicRenderedView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj13668_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_15643482;
      final SearchResultsAndTopicPresenter inj13667_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj13667_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj13667_SearchResultsAndTopicPresenter, inj13669_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj13667_SearchResultsAndTopicPresenter, inj13645_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj13667_SearchResultsAndTopicPresenter, inj13670_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj13667_SearchResultsAndTopicPresenter, inj13671_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj13667_SearchResultsAndTopicPresenter, inj13644_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj13667_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj12334_App));
      return inj13667_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj13639_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj13637_IOCBeanManagerProvider_creationalCallback, inj13630_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj13637_IOCBeanManagerProvider_creationalCallback, inj13630_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(App.class, App.class, inj13638_App_creationalCallback, inj12334_App, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj13616_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj13616_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(AppController.class, AppController.class, inj13641_AppController_creationalCallback, inj13640_AppController, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj13641_AppController_creationalCallback, inj13640_AppController, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj13641_AppController_creationalCallback, inj13640_AppController, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj13641_AppController_creationalCallback, inj13640_AppController, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj13644_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Display.class, SearchResultsView.class, inj13644_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj13644_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj13644_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj13645_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj13643_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj13643_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj13643_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj13646_RequestDispatcherProvider_creationalCallback, inj13622_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj13646_RequestDispatcherProvider_creationalCallback, inj13622_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj13647_InstanceProvider_creationalCallback, inj13636_InstanceProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj13647_InstanceProvider_creationalCallback, inj13636_InstanceProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj13648_EventProvider_creationalCallback, inj13632_EventProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj13648_EventProvider_creationalCallback, inj13632_EventProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj13649_MessageBusProvider_creationalCallback, inj13628_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj13649_MessageBusProvider_creationalCallback, inj13628_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj13652_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj13652_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj13652_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj13652_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj13651_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj13651_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj13651_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj13653_SenderProvider_creationalCallback, inj13634_SenderProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj13653_SenderProvider_creationalCallback, inj13634_SenderProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj13654_InitBallotProvider_creationalCallback, inj13626_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj13654_InitBallotProvider_creationalCallback, inj13626_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj13656_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj13656_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj13656_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj13657_CallerProvider_creationalCallback, inj13618_CallerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj13657_CallerProvider_creationalCallback, inj13618_CallerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj13658_RootPanelProvider_creationalCallback, inj13624_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj13658_RootPanelProvider_creationalCallback, inj13624_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj13661_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj13661_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj13661_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj13661_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj13660_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj13660_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj13660_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj13663_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj13663_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj13663_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj13665_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj13665_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj13665_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj13666_DisposerProvider_creationalCallback, inj13620_DisposerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj13666_DisposerProvider_creationalCallback, inj13620_DisposerProvider, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj13669_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj13669_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj13669_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj13669_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj13670_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj13671_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj13668_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj13668_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj13668_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_15643482, null, false);
  }

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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(SearchResultsPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(WelcomePresenter instance, org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::display = value;
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