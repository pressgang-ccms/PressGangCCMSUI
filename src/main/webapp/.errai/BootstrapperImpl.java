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
  private final Any javax_enterprise_inject_Any_28922510 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_29283737 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_10122122 = new Annotation[] { javax_enterprise_inject_Any_28922510, javax_enterprise_inject_Default_29283737 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj1974_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final IOCBeanManagerProvider inj1967_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1967_IOCBeanManagerProvider);
      return inj1967_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1967_IOCBeanManagerProvider = inj1974_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1975_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final App inj625_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj625_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1976_proxy inj1976_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1976_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1976_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1976_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_10122122);
      org_jboss_pressgangccms_client_local_App_appController(inj625_App, inj1976_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj625_App.startApp();
            }
          });
        }
      });
      return inj625_App;
    }
  };
  private final App inj625_App = inj1975_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1953_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_10122122), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1978_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final AppController inj1977_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1977_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1977_AppController, inj1967_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1977_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1977_AppController;
    }
  };
  private final AppController inj1977_AppController = inj1978_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<ImageView> inj1981_ImageView_creationalCallback = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      Class beanType = ImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final ImageView inj1583_ImageView = new ImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1583_ImageView);
      return inj1583_ImageView;
    }
  };
  private final CreationalCallback<ImagePresenter> inj1980_ImagePresenter_creationalCallback = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final ImagePresenter inj1979_ImagePresenter = new ImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1979_ImagePresenter);
      org_jboss_pressgangccms_client_local_presenter_ImagePresenter_display(inj1979_ImagePresenter, inj1981_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1979_ImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1979_ImagePresenter;
    }
  };
  private final CreationalCallback<SearchResultsView> inj1984_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchResultsView inj1588_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1588_SearchResultsView);
      return inj1588_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj1985_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicView inj1586_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1586_TopicView);
      return inj1586_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj1983_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchResultsPresenter inj1982_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1982_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj1982_SearchResultsPresenter, inj1984_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj1982_SearchResultsPresenter, inj1985_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1982_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1982_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<TopicRevisionsPresenter> inj1987_TopicRevisionsPresenter_creationalCallback = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicRevisionsPresenter inj1986_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1986_TopicRevisionsPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1986_TopicRevisionsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1986_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj1988_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final RequestDispatcherProvider inj1959_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1959_RequestDispatcherProvider);
      return inj1959_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1959_RequestDispatcherProvider = inj1988_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1989_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final InstanceProvider inj1973_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1973_InstanceProvider);
      return inj1973_InstanceProvider;
    }
  };
  private final InstanceProvider inj1973_InstanceProvider = inj1989_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1990_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final EventProvider inj1969_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1969_EventProvider);
      return inj1969_EventProvider;
    }
  };
  private final EventProvider inj1969_EventProvider = inj1990_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1991_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final MessageBusProvider inj1965_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1965_MessageBusProvider);
      return inj1965_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1965_MessageBusProvider = inj1991_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicBugsPresenter> inj1993_TopicBugsPresenter_creationalCallback = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicBugsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicBugsPresenter inj1992_TopicBugsPresenter = new TopicBugsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1992_TopicBugsPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1992_TopicBugsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1992_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<TopicXMLErrorsView> inj1996_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicXMLErrorsView inj1594_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1594_TopicXMLErrorsView);
      return inj1594_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj1995_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicXMLErrorsPresenter inj1994_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1994_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(inj1994_TopicXMLErrorsPresenter, inj1996_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1994_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1994_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj1999_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final WelcomeView inj1587_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1587_WelcomeView);
      return inj1587_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1998_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final WelcomePresenter inj1997_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1997_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1997_WelcomePresenter, inj1999_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1997_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj1997_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj2000_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SenderProvider inj1971_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1971_SenderProvider);
      return inj1971_SenderProvider;
    }
  };
  private final SenderProvider inj1971_SenderProvider = inj2000_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj2001_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final InitBallotProvider inj1963_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1963_InitBallotProvider);
      return inj1963_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1963_InitBallotProvider = inj2001_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj2003_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicPresenter inj2002_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2002_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj2002_TopicPresenter, inj1985_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2002_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2002_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj2004_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final CallerProvider inj1955_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1955_CallerProvider);
      return inj1955_CallerProvider;
    }
  };
  private final CallerProvider inj1955_CallerProvider = inj2004_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj2007_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchView inj1589_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1589_SearchView);
      return inj1589_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj2006_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchPresenter inj2005_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2005_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj2005_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj2005_SearchPresenter, inj2007_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2005_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2005_SearchPresenter;
    }
  };
  private final CreationalCallback<RootPanelProvider> inj2008_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final RootPanelProvider inj1961_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1961_RootPanelProvider);
      return inj1961_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1961_RootPanelProvider = inj2008_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsView> inj2011_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicTagsView inj1584_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1584_TopicTagsView);
      return inj1584_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj2010_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicTagsPresenter inj2009_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2009_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(inj2009_TopicTagsPresenter, inj2011_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2009_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2009_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj2013_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicRenderedPresenter inj2012_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2012_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2012_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2012_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj2015_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicXMLPresenter inj2014_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2014_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2014_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2014_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj2016_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final DisposerProvider inj1957_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1957_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1957_DisposerProvider, inj1967_IOCBeanManagerProvider.get());
      return inj1957_DisposerProvider;
    }
  };
  private final DisposerProvider inj1957_DisposerProvider = inj2016_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj2019_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchResultsAndTopicView inj1590_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1590_SearchResultsAndTopicView);
      return inj1590_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj2020_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicXMLView inj1585_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1585_TopicXMLView);
      return inj1585_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj2021_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicRenderedView inj1592_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1592_TopicRenderedView);
      return inj1592_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj2022_TopicBugsView_creationalCallback = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      Class beanType = TopicBugsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicBugsView inj1593_TopicBugsView = new TopicBugsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1593_TopicBugsView);
      return inj1593_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj2023_TopicRevisionsView_creationalCallback = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final TopicRevisionsView inj1591_TopicRevisionsView = new TopicRevisionsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1591_TopicRevisionsView);
      return inj1591_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj2018_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_10122122;
      final SearchResultsAndTopicPresenter inj2017_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2017_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj2017_SearchResultsAndTopicPresenter, inj2019_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj2017_SearchResultsAndTopicPresenter, inj1985_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj2017_SearchResultsAndTopicPresenter, inj2020_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj2017_SearchResultsAndTopicPresenter, inj2021_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(inj2017_SearchResultsAndTopicPresenter, inj2021_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj2017_SearchResultsAndTopicPresenter, inj1984_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj2017_SearchResultsAndTopicPresenter, inj1996_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj2017_SearchResultsAndTopicPresenter, inj2011_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(inj2017_SearchResultsAndTopicPresenter, inj2022_TopicBugsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(inj2017_SearchResultsAndTopicPresenter, inj2023_TopicRevisionsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj2017_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj625_App));
      return inj2017_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1976_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1974_IOCBeanManagerProvider_creationalCallback, inj1967_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1974_IOCBeanManagerProvider_creationalCallback, inj1967_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(App.class, App.class, inj1975_App_creationalCallback, inj625_App, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1953_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1953_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1978_AppController_creationalCallback, inj1977_AppController, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1978_AppController_creationalCallback, inj1977_AppController, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1978_AppController_creationalCallback, inj1977_AppController, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1978_AppController_creationalCallback, inj1977_AppController, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj1981_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Display.class, ImageView.class, inj1981_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj1981_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj1981_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj1980_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj1980_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj1980_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj1984_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display.class, SearchResultsView.class, inj1984_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj1984_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj1984_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj1985_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj1983_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj1983_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj1983_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj1987_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj1987_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj1987_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1988_RequestDispatcherProvider_creationalCallback, inj1959_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1988_RequestDispatcherProvider_creationalCallback, inj1959_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1989_InstanceProvider_creationalCallback, inj1973_InstanceProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1989_InstanceProvider_creationalCallback, inj1973_InstanceProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1990_EventProvider_creationalCallback, inj1969_EventProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1990_EventProvider_creationalCallback, inj1969_EventProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1991_MessageBusProvider_creationalCallback, inj1965_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1991_MessageBusProvider_creationalCallback, inj1965_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj1993_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj1993_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj1993_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj1996_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj1995_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj1995_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj1995_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1999_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj1999_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1999_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1999_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1998_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1998_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1998_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj2000_SenderProvider_creationalCallback, inj1971_SenderProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj2000_SenderProvider_creationalCallback, inj1971_SenderProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj2001_InitBallotProvider_creationalCallback, inj1963_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj2001_InitBallotProvider_creationalCallback, inj1963_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj2003_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj2003_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj2003_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj2004_CallerProvider_creationalCallback, inj1955_CallerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj2004_CallerProvider_creationalCallback, inj1955_CallerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj2007_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj2007_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj2007_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj2007_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj2006_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj2006_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj2006_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj2008_RootPanelProvider_creationalCallback, inj1961_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj2008_RootPanelProvider_creationalCallback, inj1961_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj2011_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj2010_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj2010_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj2010_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj2013_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj2013_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj2013_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj2015_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj2015_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj2015_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj2016_DisposerProvider_creationalCallback, inj1957_DisposerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj2016_DisposerProvider_creationalCallback, inj1957_DisposerProvider, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj2019_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj2019_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj2019_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj2019_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj2020_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj2021_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicBugsPresenter.Display.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj2022_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj2023_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj2018_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj2018_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj2018_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_10122122, null, false);
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