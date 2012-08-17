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
import org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicRenderedView;
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
  private final Any javax_enterprise_inject_Any_25536377 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_6497986 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_13097358 = new Annotation[] { javax_enterprise_inject_Any_25536377, javax_enterprise_inject_Default_6497986 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj1921_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final IOCBeanManagerProvider inj1914_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1914_IOCBeanManagerProvider);
      return inj1914_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1914_IOCBeanManagerProvider = inj1921_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1922_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final App inj616_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj616_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1923_proxy inj1923_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1923_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1923_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1923_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_13097358);
      org_jboss_pressgangccms_client_local_App_appController(inj616_App, inj1923_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj616_App.startApp();
            }
          });
        }
      });
      return inj616_App;
    }
  };
  private final App inj616_App = inj1922_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1900_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_13097358), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1925_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final AppController inj1924_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1924_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1924_AppController, inj1914_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1924_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1924_AppController;
    }
  };
  private final AppController inj1924_AppController = inj1925_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj1928_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchResultsView inj1554_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1554_SearchResultsView);
      return inj1554_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj1929_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicView inj1558_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1558_TopicView);
      return inj1558_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj1927_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchResultsPresenter inj1926_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1926_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj1926_SearchResultsPresenter, inj1928_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj1926_SearchResultsPresenter, inj1929_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1926_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1926_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj1930_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final RequestDispatcherProvider inj1906_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1906_RequestDispatcherProvider);
      return inj1906_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1906_RequestDispatcherProvider = inj1930_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1931_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final InstanceProvider inj1920_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1920_InstanceProvider);
      return inj1920_InstanceProvider;
    }
  };
  private final InstanceProvider inj1920_InstanceProvider = inj1931_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1932_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final EventProvider inj1916_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1916_EventProvider);
      return inj1916_EventProvider;
    }
  };
  private final EventProvider inj1916_EventProvider = inj1932_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1933_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final MessageBusProvider inj1912_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1912_MessageBusProvider);
      return inj1912_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1912_MessageBusProvider = inj1933_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLErrorsView> inj1936_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicXMLErrorsView inj1561_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1561_TopicXMLErrorsView);
      return inj1561_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj1935_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicXMLErrorsPresenter inj1934_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1934_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(inj1934_TopicXMLErrorsPresenter, inj1936_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1934_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1934_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj1939_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final WelcomeView inj1560_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1560_WelcomeView);
      return inj1560_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1938_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final WelcomePresenter inj1937_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1937_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1937_WelcomePresenter, inj1939_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1937_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1937_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj1940_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SenderProvider inj1918_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1918_SenderProvider);
      return inj1918_SenderProvider;
    }
  };
  private final SenderProvider inj1918_SenderProvider = inj1940_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1941_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final InitBallotProvider inj1910_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1910_InitBallotProvider);
      return inj1910_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1910_InitBallotProvider = inj1941_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj1943_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicPresenter inj1942_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1942_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj1942_TopicPresenter, inj1929_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1942_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1942_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj1944_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final CallerProvider inj1902_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1902_CallerProvider);
      return inj1902_CallerProvider;
    }
  };
  private final CallerProvider inj1902_CallerProvider = inj1944_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1945_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final RootPanelProvider inj1908_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1908_RootPanelProvider);
      return inj1908_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1908_RootPanelProvider = inj1945_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj1948_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchView inj1555_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1555_SearchView);
      return inj1555_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj1947_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchPresenter inj1946_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1946_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj1946_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj1946_SearchPresenter, inj1948_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1946_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1946_SearchPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj1950_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicRenderedPresenter inj1949_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1949_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1949_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1949_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj1952_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicXMLPresenter inj1951_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1951_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1951_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1951_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj1953_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final DisposerProvider inj1904_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1904_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1904_DisposerProvider, inj1914_IOCBeanManagerProvider.get());
      return inj1904_DisposerProvider;
    }
  };
  private final DisposerProvider inj1904_DisposerProvider = inj1953_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj1956_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchResultsAndTopicView inj1556_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1556_SearchResultsAndTopicView);
      return inj1556_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj1957_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicXMLView inj1557_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1557_TopicXMLView);
      return inj1557_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj1958_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final TopicRenderedView inj1559_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1559_TopicRenderedView);
      return inj1559_TopicRenderedView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj1955_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_13097358;
      final SearchResultsAndTopicPresenter inj1954_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1954_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj1954_SearchResultsAndTopicPresenter, inj1956_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj1954_SearchResultsAndTopicPresenter, inj1929_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj1954_SearchResultsAndTopicPresenter, inj1957_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj1954_SearchResultsAndTopicPresenter, inj1958_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj1954_SearchResultsAndTopicPresenter, inj1928_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj1954_SearchResultsAndTopicPresenter, inj1936_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1954_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj616_App));
      return inj1954_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1923_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1921_IOCBeanManagerProvider_creationalCallback, inj1914_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1921_IOCBeanManagerProvider_creationalCallback, inj1914_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(App.class, App.class, inj1922_App_creationalCallback, inj616_App, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1900_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1900_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1925_AppController_creationalCallback, inj1924_AppController, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1925_AppController_creationalCallback, inj1924_AppController, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1925_AppController_creationalCallback, inj1924_AppController, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1925_AppController_creationalCallback, inj1924_AppController, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj1928_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Display.class, SearchResultsView.class, inj1928_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj1928_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj1928_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj1929_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj1927_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj1927_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj1927_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1930_RequestDispatcherProvider_creationalCallback, inj1906_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1930_RequestDispatcherProvider_creationalCallback, inj1906_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1931_InstanceProvider_creationalCallback, inj1920_InstanceProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1931_InstanceProvider_creationalCallback, inj1920_InstanceProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1932_EventProvider_creationalCallback, inj1916_EventProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1932_EventProvider_creationalCallback, inj1916_EventProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1933_MessageBusProvider_creationalCallback, inj1912_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1933_MessageBusProvider_creationalCallback, inj1912_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj1936_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj1935_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj1935_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj1935_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1939_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj1939_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1939_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1939_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1938_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1938_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1938_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1940_SenderProvider_creationalCallback, inj1918_SenderProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1940_SenderProvider_creationalCallback, inj1918_SenderProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1941_InitBallotProvider_creationalCallback, inj1910_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1941_InitBallotProvider_creationalCallback, inj1910_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj1943_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj1943_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj1943_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1944_CallerProvider_creationalCallback, inj1902_CallerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1944_CallerProvider_creationalCallback, inj1902_CallerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1945_RootPanelProvider_creationalCallback, inj1908_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1945_RootPanelProvider_creationalCallback, inj1908_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj1948_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj1948_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj1948_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj1948_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj1947_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj1947_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj1947_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj1950_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj1950_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj1950_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj1952_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj1952_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj1952_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1953_DisposerProvider_creationalCallback, inj1904_DisposerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1953_DisposerProvider_creationalCallback, inj1904_DisposerProvider, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj1956_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj1956_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj1956_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj1956_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj1957_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj1958_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj1955_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj1955_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj1955_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_13097358, null, false);
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
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