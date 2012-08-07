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
import org.jboss.pressgangccms.client.local.Presenter;
import org.jboss.pressgangccms.client.local.Presenter.TemplateDisplay;
import org.jboss.pressgangccms.client.local.Presenter.TemplateInterface;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display;
import org.jboss.pressgangccms.client.local.view.WelcomeView;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any javax_enterprise_inject_Any_24361474 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_24280348 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_26837744 = new Annotation[] { javax_enterprise_inject_Any_24361474, javax_enterprise_inject_Default_24280348 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<CallerProvider> inj1887_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final CallerProvider inj1868_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1868_CallerProvider);
      return inj1868_CallerProvider;
    }
  };
  private final CallerProvider inj1868_CallerProvider = inj1887_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj1888_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final RequestDispatcherProvider inj1872_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1872_RequestDispatcherProvider);
      return inj1872_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1872_RequestDispatcherProvider = inj1888_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj1889_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final SenderProvider inj1884_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1884_SenderProvider);
      return inj1884_SenderProvider;
    }
  };
  private final SenderProvider inj1884_SenderProvider = inj1889_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1890_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final RootPanelProvider inj1874_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1874_RootPanelProvider);
      return inj1874_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1874_RootPanelProvider = inj1890_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1891_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final InstanceProvider inj1886_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1886_InstanceProvider);
      return inj1886_InstanceProvider;
    }
  };
  private final InstanceProvider inj1886_InstanceProvider = inj1891_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj1892_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final IOCBeanManagerProvider inj1880_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1880_IOCBeanManagerProvider);
      return inj1880_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1880_IOCBeanManagerProvider = inj1892_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1893_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final App inj608_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj608_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1894_proxy inj1894_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1894_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1894_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1894_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_26837744);
      org_jboss_pressgangccms_client_local_App_appController(inj608_App, inj1894_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj608_App.startApp();
            }
          });
        }
      });
      return inj608_App;
    }
  };
  private final App inj608_App = inj1893_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1866_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj608_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_26837744), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1896_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final AppController inj1895_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1895_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1895_AppController, inj1880_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1895_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj608_App));
      return inj1895_AppController;
    }
  };
  private final AppController inj1895_AppController = inj1896_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1897_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final EventProvider inj1882_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1882_EventProvider);
      return inj1882_EventProvider;
    }
  };
  private final EventProvider inj1882_EventProvider = inj1897_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1898_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final MessageBusProvider inj1878_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1878_MessageBusProvider);
      return inj1878_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1878_MessageBusProvider = inj1898_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1899_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final InitBallotProvider inj1876_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1876_InitBallotProvider);
      return inj1876_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1876_InitBallotProvider = inj1899_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj1900_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final DisposerProvider inj1870_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1870_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1870_DisposerProvider, inj1880_IOCBeanManagerProvider.get());
      return inj1870_DisposerProvider;
    }
  };
  private final DisposerProvider inj1870_DisposerProvider = inj1900_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj1903_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final WelcomeView inj1544_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1544_WelcomeView);
      return inj1544_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1902_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26837744;
      final WelcomePresenter inj1901_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1901_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1901_WelcomePresenter, inj1903_WelcomeView_creationalCallback.getInstance(context));
      return inj1901_WelcomePresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1894_proxy extends AppController {
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
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1887_CallerProvider_creationalCallback, inj1868_CallerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1887_CallerProvider_creationalCallback, inj1868_CallerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1888_RequestDispatcherProvider_creationalCallback, inj1872_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1888_RequestDispatcherProvider_creationalCallback, inj1872_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1889_SenderProvider_creationalCallback, inj1884_SenderProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1889_SenderProvider_creationalCallback, inj1884_SenderProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1890_RootPanelProvider_creationalCallback, inj1874_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1890_RootPanelProvider_creationalCallback, inj1874_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1891_InstanceProvider_creationalCallback, inj1886_InstanceProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1891_InstanceProvider_creationalCallback, inj1886_InstanceProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1892_IOCBeanManagerProvider_creationalCallback, inj1880_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1892_IOCBeanManagerProvider_creationalCallback, inj1880_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(App.class, App.class, inj1893_App_creationalCallback, inj608_App, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1866_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1866_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1896_AppController_creationalCallback, inj1895_AppController, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1896_AppController_creationalCallback, inj1895_AppController, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1896_AppController_creationalCallback, inj1895_AppController, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1896_AppController_creationalCallback, inj1895_AppController, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1897_EventProvider_creationalCallback, inj1882_EventProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1897_EventProvider_creationalCallback, inj1882_EventProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1898_MessageBusProvider_creationalCallback, inj1878_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1898_MessageBusProvider_creationalCallback, inj1878_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1899_InitBallotProvider_creationalCallback, inj1876_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1899_InitBallotProvider_creationalCallback, inj1876_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1900_DisposerProvider_creationalCallback, inj1870_DisposerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1900_DisposerProvider_creationalCallback, inj1870_DisposerProvider, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1903_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj1903_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj1903_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj1903_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1902_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1902_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26837744, null, false);
  }

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
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