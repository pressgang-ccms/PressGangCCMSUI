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
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.WelcomeView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.view.base.BaseTemplateViewInterface;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Default javax_enterprise_inject_Default_19044129 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_32515653 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_9545222 = new Annotation[] { javax_enterprise_inject_Default_19044129, javax_enterprise_inject_Any_32515653 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj1897_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final RequestDispatcherProvider inj1882_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1882_RequestDispatcherProvider);
      return inj1882_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1882_RequestDispatcherProvider = inj1897_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1898_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final InstanceProvider inj1896_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1896_InstanceProvider);
      return inj1896_InstanceProvider;
    }
  };
  private final InstanceProvider inj1896_InstanceProvider = inj1898_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj1899_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final IOCBeanManagerProvider inj1890_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1890_IOCBeanManagerProvider);
      return inj1890_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1890_IOCBeanManagerProvider = inj1899_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1900_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final App inj609_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj609_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1901_proxy inj1901_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1901_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1901_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1901_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_9545222);
      org_jboss_pressgangccms_client_local_App_appController(inj609_App, inj1901_proxy);
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
  private final App inj609_App = inj1900_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1876_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_9545222), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1903_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final AppController inj1902_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1902_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1902_AppController, inj1890_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1902_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1902_AppController;
    }
  };
  private final AppController inj1902_AppController = inj1903_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1904_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final EventProvider inj1892_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1892_EventProvider);
      return inj1892_EventProvider;
    }
  };
  private final EventProvider inj1892_EventProvider = inj1904_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1905_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final MessageBusProvider inj1888_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1888_MessageBusProvider);
      return inj1888_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1888_MessageBusProvider = inj1905_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj1906_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final SenderProvider inj1894_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1894_SenderProvider);
      return inj1894_SenderProvider;
    }
  };
  private final SenderProvider inj1894_SenderProvider = inj1906_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj1909_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final WelcomeView inj1550_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1550_WelcomeView);
      return inj1550_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1908_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final WelcomePresenter inj1907_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1907_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1907_WelcomePresenter, inj1909_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1907_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1907_WelcomePresenter;
    }
  };
  private final CreationalCallback<InitBallotProvider> inj1910_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final InitBallotProvider inj1886_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1886_InitBallotProvider);
      return inj1886_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1886_InitBallotProvider = inj1910_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<CallerProvider> inj1911_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final CallerProvider inj1878_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1878_CallerProvider);
      return inj1878_CallerProvider;
    }
  };
  private final CallerProvider inj1878_CallerProvider = inj1911_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1912_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final RootPanelProvider inj1884_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1884_RootPanelProvider);
      return inj1884_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1884_RootPanelProvider = inj1912_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj1915_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final SearchView inj1549_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1549_SearchView);
      return inj1549_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj1914_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final SearchPresenter inj1913_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1913_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj1913_SearchPresenter, inj1915_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1913_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj609_App));
      return inj1913_SearchPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj1916_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_9545222;
      final DisposerProvider inj1880_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1880_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1880_DisposerProvider, inj1890_IOCBeanManagerProvider.get());
      return inj1880_DisposerProvider;
    }
  };
  private final DisposerProvider inj1880_DisposerProvider = inj1916_DisposerProvider_creationalCallback.getInstance(context);
  static class org_jboss_pressgangccms_client_local_AppController_inj1901_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1897_RequestDispatcherProvider_creationalCallback, inj1882_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1897_RequestDispatcherProvider_creationalCallback, inj1882_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1898_InstanceProvider_creationalCallback, inj1896_InstanceProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1898_InstanceProvider_creationalCallback, inj1896_InstanceProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1899_IOCBeanManagerProvider_creationalCallback, inj1890_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1899_IOCBeanManagerProvider_creationalCallback, inj1890_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(App.class, App.class, inj1900_App_creationalCallback, inj609_App, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1876_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1876_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1903_AppController_creationalCallback, inj1902_AppController, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1903_AppController_creationalCallback, inj1902_AppController, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1903_AppController_creationalCallback, inj1902_AppController, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1903_AppController_creationalCallback, inj1902_AppController, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1904_EventProvider_creationalCallback, inj1892_EventProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1904_EventProvider_creationalCallback, inj1892_EventProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1905_MessageBusProvider_creationalCallback, inj1888_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1905_MessageBusProvider_creationalCallback, inj1888_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1906_SenderProvider_creationalCallback, inj1894_SenderProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1906_SenderProvider_creationalCallback, inj1894_SenderProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1909_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj1909_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1909_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1909_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1908_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1908_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1908_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1910_InitBallotProvider_creationalCallback, inj1886_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1910_InitBallotProvider_creationalCallback, inj1886_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1911_CallerProvider_creationalCallback, inj1878_CallerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1911_CallerProvider_creationalCallback, inj1878_CallerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1912_RootPanelProvider_creationalCallback, inj1884_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1912_RootPanelProvider_creationalCallback, inj1884_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj1915_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj1915_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj1915_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj1915_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj1914_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj1914_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj1914_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1916_DisposerProvider_creationalCallback, inj1880_DisposerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1916_DisposerProvider_creationalCallback, inj1880_DisposerProvider, arrayOf_java_lang_annotation_Annotation_9545222, null, false);
  }

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(WelcomePresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.WelcomePresenter::display = value;
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