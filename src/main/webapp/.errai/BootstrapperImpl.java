package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
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
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicViewPresenter;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicViewPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicView;
import org.jboss.pressgangccms.client.local.view.WelcomeView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Default javax_enterprise_inject_Default_12303923 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_29632376 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_25339545 = new Annotation[] { javax_enterprise_inject_Default_12303923, javax_enterprise_inject_Any_29632376 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj1907_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final IOCBeanManagerProvider inj1900_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1900_IOCBeanManagerProvider);
      return inj1900_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1900_IOCBeanManagerProvider = inj1907_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1908_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final App inj609_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj609_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1909_proxy inj1909_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1909_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1909_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1909_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_25339545);
      org_jboss_pressgangccms_client_local_App_appController(inj609_App, inj1909_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj609_App.startApp();
            }
          });
        }
      });
      return inj609_App;
    }
  };
  private final App inj609_App = inj1908_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1886_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_25339545), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1911_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final AppController inj1910_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1910_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1910_AppController, inj1900_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1910_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1910_AppController;
    }
  };
  private final AppController inj1910_AppController = inj1911_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj1914_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchResultsView inj1546_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1546_SearchResultsView);
      return inj1546_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj1915_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final TopicView inj1548_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1548_TopicView);
      return inj1548_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj1913_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchResultsPresenter inj1912_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1912_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj1912_SearchResultsPresenter, inj1914_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj1912_SearchResultsPresenter, inj1915_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1912_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1912_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj1916_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final RequestDispatcherProvider inj1892_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1892_RequestDispatcherProvider);
      return inj1892_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1892_RequestDispatcherProvider = inj1916_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1917_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final InstanceProvider inj1906_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1906_InstanceProvider);
      return inj1906_InstanceProvider;
    }
  };
  private final InstanceProvider inj1906_InstanceProvider = inj1917_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1918_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final EventProvider inj1902_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1902_EventProvider);
      return inj1902_EventProvider;
    }
  };
  private final EventProvider inj1902_EventProvider = inj1918_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1919_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final MessageBusProvider inj1898_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1898_MessageBusProvider);
      return inj1898_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1898_MessageBusProvider = inj1919_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj1922_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final WelcomeView inj1550_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1550_WelcomeView);
      return inj1550_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1921_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final WelcomePresenter inj1920_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1920_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1920_WelcomePresenter, inj1922_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1920_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1920_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj1923_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SenderProvider inj1904_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1904_SenderProvider);
      return inj1904_SenderProvider;
    }
  };
  private final SenderProvider inj1904_SenderProvider = inj1923_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1924_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final InitBallotProvider inj1896_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1896_InitBallotProvider);
      return inj1896_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1896_InitBallotProvider = inj1924_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj1926_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final TopicPresenter inj1925_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1925_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj1925_TopicPresenter, inj1915_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1925_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1925_TopicPresenter;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicViewPresenter> inj1928_SearchResultsAndTopicViewPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicViewPresenter>() {
    public SearchResultsAndTopicViewPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicViewPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchResultsAndTopicViewPresenter inj1927_SearchResultsAndTopicViewPresenter = new SearchResultsAndTopicViewPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1927_SearchResultsAndTopicViewPresenter);
      final org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter$Display_inj1929_proxy inj1929_proxy = new org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter$Display_inj1929_proxy();
      context.addUnresolvedProxy(new ProxyResolver<Display>() {
        public void resolve(Display obj) {
          inj1929_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1929_proxy, obj);
        }
      }, Display.class, arrayOf_java_lang_annotation_Annotation_25339545);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_display(inj1927_SearchResultsAndTopicViewPresenter, inj1929_proxy);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_topicViewDisplay(inj1927_SearchResultsAndTopicViewPresenter, inj1915_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_searchResultsDisplay(inj1927_SearchResultsAndTopicViewPresenter, inj1914_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1927_SearchResultsAndTopicViewPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1927_SearchResultsAndTopicViewPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj1930_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final CallerProvider inj1888_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1888_CallerProvider);
      return inj1888_CallerProvider;
    }
  };
  private final CallerProvider inj1888_CallerProvider = inj1930_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1931_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final RootPanelProvider inj1894_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1894_RootPanelProvider);
      return inj1894_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1894_RootPanelProvider = inj1931_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj1934_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchView inj1547_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1547_SearchView);
      return inj1547_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj1933_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchPresenter inj1932_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1932_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj1932_SearchPresenter, inj1934_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1932_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1932_SearchPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj1935_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final DisposerProvider inj1890_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1890_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1890_DisposerProvider, inj1900_IOCBeanManagerProvider.get());
      return inj1890_DisposerProvider;
    }
  };
  private final DisposerProvider inj1890_DisposerProvider = inj1935_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj1938_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchResultsAndTopicView inj1549_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1549_SearchResultsAndTopicView);
      return inj1549_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj1937_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_25339545;
      final SearchResultsAndTopicPresenter inj1936_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1936_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj1936_SearchResultsAndTopicPresenter, inj1938_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj1936_SearchResultsAndTopicPresenter, inj1915_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj1936_SearchResultsAndTopicPresenter, inj1914_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1936_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1936_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1909_proxy extends AppController {
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
  static class org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter$Display_inj1929_proxy implements Display {
    private Display $$_proxy_$$;
    public SimplePanel getTopicResultsPanel() {
      return $$_proxy_$$.getTopicResultsPanel();
    }

    public SimplePanel getTopicViewPanel() {
      return $$_proxy_$$.getTopicViewPanel();
    }

    public PushButton getBug() {
      return $$_proxy_$$.getBug();
    }

    public PushButton getSearch() {
      return $$_proxy_$$.getSearch();
    }

    public Panel getTopLevelPanel() {
      return $$_proxy_$$.getTopLevelPanel();
    }

    public HorizontalPanel getTopActionPanel() {
      return $$_proxy_$$.getTopActionPanel();
    }

    public void setSpinnerVisible(boolean a0) {
      $$_proxy_$$.setSpinnerVisible(a0);
    }

    public SimplePanel getPanel() {
      return $$_proxy_$$.getPanel();
    }

    public void __$setProxiedInstance$(Display proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  private void declareBeans_0() {
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1907_IOCBeanManagerProvider_creationalCallback, inj1900_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1907_IOCBeanManagerProvider_creationalCallback, inj1900_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(App.class, App.class, inj1908_App_creationalCallback, inj609_App, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1886_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1886_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1911_AppController_creationalCallback, inj1910_AppController, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1911_AppController_creationalCallback, inj1910_AppController, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1911_AppController_creationalCallback, inj1910_AppController, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1911_AppController_creationalCallback, inj1910_AppController, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj1914_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display.class, SearchResultsView.class, inj1914_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj1914_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj1914_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj1915_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj1915_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj1915_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj1915_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj1913_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj1913_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj1913_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1916_RequestDispatcherProvider_creationalCallback, inj1892_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1916_RequestDispatcherProvider_creationalCallback, inj1892_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1917_InstanceProvider_creationalCallback, inj1906_InstanceProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1917_InstanceProvider_creationalCallback, inj1906_InstanceProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1918_EventProvider_creationalCallback, inj1902_EventProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1918_EventProvider_creationalCallback, inj1902_EventProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1919_MessageBusProvider_creationalCallback, inj1898_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1919_MessageBusProvider_creationalCallback, inj1898_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1922_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj1922_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1922_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1922_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1921_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1921_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1921_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1923_SenderProvider_creationalCallback, inj1904_SenderProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1923_SenderProvider_creationalCallback, inj1904_SenderProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1924_InitBallotProvider_creationalCallback, inj1896_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1924_InitBallotProvider_creationalCallback, inj1896_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj1926_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj1926_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj1926_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchResultsAndTopicViewPresenter.class, SearchResultsAndTopicViewPresenter.class, inj1928_SearchResultsAndTopicViewPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicViewPresenter.class, inj1928_SearchResultsAndTopicViewPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicViewPresenter.class, inj1928_SearchResultsAndTopicViewPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1930_CallerProvider_creationalCallback, inj1888_CallerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1930_CallerProvider_creationalCallback, inj1888_CallerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1931_RootPanelProvider_creationalCallback, inj1894_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1931_RootPanelProvider_creationalCallback, inj1894_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj1934_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj1934_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj1934_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj1934_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj1933_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj1933_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj1933_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1935_DisposerProvider_creationalCallback, inj1890_DisposerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1935_DisposerProvider_creationalCallback, inj1890_DisposerProvider, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj1938_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj1938_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj1938_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj1938_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj1937_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj1937_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj1937_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_25339545, null, false);
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_topicViewDisplay(SearchResultsAndTopicViewPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicViewPresenter::topicViewDisplay = value;
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_display(SearchResultsAndTopicViewPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicViewPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicViewPresenter_searchResultsDisplay(SearchResultsAndTopicViewPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicViewPresenter::searchResultsDisplay = value;
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

  public native static HandlerManager org_jboss_pressgangccms_client_local_App_produceEventBus(App instance) /*-{
    return instance.@org.jboss.pressgangccms.client.local.App::produceEventBus()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}