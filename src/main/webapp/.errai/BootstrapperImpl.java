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
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter.TemplateDisplay;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter.TemplateInterface;
import org.jboss.pressgangccms.client.local.view.WelcomeView;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Default javax_enterprise_inject_Default_28660627 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_25292630 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_8307912 = new Annotation[] { javax_enterprise_inject_Default_28660627, javax_enterprise_inject_Any_25292630 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<CallerProvider> inj24736_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final CallerProvider inj24717_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24717_CallerProvider);
      return inj24717_CallerProvider;
    }
  };
  private final CallerProvider inj24717_CallerProvider = inj24736_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj24737_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final RequestDispatcherProvider inj24721_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24721_RequestDispatcherProvider);
      return inj24721_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj24721_RequestDispatcherProvider = inj24737_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj24738_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final SenderProvider inj24733_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24733_SenderProvider);
      return inj24733_SenderProvider;
    }
  };
  private final SenderProvider inj24733_SenderProvider = inj24738_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj24739_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final RootPanelProvider inj24723_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24723_RootPanelProvider);
      return inj24723_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj24723_RootPanelProvider = inj24739_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj24740_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final InstanceProvider inj24735_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24735_InstanceProvider);
      return inj24735_InstanceProvider;
    }
  };
  private final InstanceProvider inj24735_InstanceProvider = inj24740_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj24741_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final IOCBeanManagerProvider inj24729_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24729_IOCBeanManagerProvider);
      return inj24729_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj24729_IOCBeanManagerProvider = inj24741_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj24742_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final App inj23457_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj23457_App);
      final org_jboss_pressgangccms_client_local_AppController_inj24743_proxy inj24743_proxy = new org_jboss_pressgangccms_client_local_AppController_inj24743_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj24743_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj24743_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_8307912);
      org_jboss_pressgangccms_client_local_App_appController(inj23457_App, inj24743_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj23457_App.startApp();
            }
          });
        }
      });
      return inj23457_App;
    }
  };
  private final App inj23457_App = inj24742_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj24715_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var13 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj23457_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_8307912), var13);
      return var13;
    }
  };
  private final CreationalCallback<AppController> inj24745_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final AppController inj24744_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24744_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj24744_AppController, inj24729_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj24744_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj23457_App));
      return inj24744_AppController;
    }
  };
  private final AppController inj24744_AppController = inj24745_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj24746_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final EventProvider inj24731_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24731_EventProvider);
      return inj24731_EventProvider;
    }
  };
  private final EventProvider inj24731_EventProvider = inj24746_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj24747_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final MessageBusProvider inj24727_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24727_MessageBusProvider);
      return inj24727_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj24727_MessageBusProvider = inj24747_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj24748_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final InitBallotProvider inj24725_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24725_InitBallotProvider);
      return inj24725_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj24725_InitBallotProvider = inj24748_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj24749_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final DisposerProvider inj24719_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24719_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj24719_DisposerProvider, inj24729_IOCBeanManagerProvider.get());
      return inj24719_DisposerProvider;
    }
  };
  private final DisposerProvider inj24719_DisposerProvider = inj24749_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj24752_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final WelcomeView inj24393_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24393_WelcomeView);
      return inj24393_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj24751_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_8307912;
      final WelcomePresenter inj24750_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj24750_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj24750_WelcomePresenter, inj24752_WelcomeView_creationalCallback.getInstance(context));
      return inj24750_WelcomePresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj24743_proxy extends AppController {
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
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj24736_CallerProvider_creationalCallback, inj24717_CallerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj24736_CallerProvider_creationalCallback, inj24717_CallerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj24737_RequestDispatcherProvider_creationalCallback, inj24721_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj24737_RequestDispatcherProvider_creationalCallback, inj24721_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj24738_SenderProvider_creationalCallback, inj24733_SenderProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj24738_SenderProvider_creationalCallback, inj24733_SenderProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj24739_RootPanelProvider_creationalCallback, inj24723_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj24739_RootPanelProvider_creationalCallback, inj24723_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj24740_InstanceProvider_creationalCallback, inj24735_InstanceProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj24740_InstanceProvider_creationalCallback, inj24735_InstanceProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj24741_IOCBeanManagerProvider_creationalCallback, inj24729_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj24741_IOCBeanManagerProvider_creationalCallback, inj24729_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(App.class, App.class, inj24742_App_creationalCallback, inj23457_App, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj24715_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj24715_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(AppController.class, AppController.class, inj24745_AppController_creationalCallback, inj24744_AppController, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj24745_AppController_creationalCallback, inj24744_AppController, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj24745_AppController_creationalCallback, inj24744_AppController, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj24745_AppController_creationalCallback, inj24744_AppController, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj24746_EventProvider_creationalCallback, inj24731_EventProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj24746_EventProvider_creationalCallback, inj24731_EventProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj24747_MessageBusProvider_creationalCallback, inj24727_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj24747_MessageBusProvider_creationalCallback, inj24727_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj24748_InitBallotProvider_creationalCallback, inj24725_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj24748_InitBallotProvider_creationalCallback, inj24725_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj24749_DisposerProvider_creationalCallback, inj24719_DisposerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj24749_DisposerProvider_creationalCallback, inj24719_DisposerProvider, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj24752_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj24752_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj24752_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj24752_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj24751_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj24751_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_8307912, null, false);
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