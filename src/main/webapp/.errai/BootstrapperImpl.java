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
import org.jboss.pressgangccms.client.local.presenter.base.Presenter.TemplateDisplay;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter.TemplateInterface;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.WelcomeView;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any javax_enterprise_inject_Any_26854810 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_11307526 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_3108198 = new Annotation[] { javax_enterprise_inject_Any_26854810, javax_enterprise_inject_Default_11307526 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj3803_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final RequestDispatcherProvider inj3788_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3788_RequestDispatcherProvider);
      return inj3788_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj3788_RequestDispatcherProvider = inj3803_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj3804_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final InstanceProvider inj3802_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3802_InstanceProvider);
      return inj3802_InstanceProvider;
    }
  };
  private final InstanceProvider inj3802_InstanceProvider = inj3804_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj3805_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final IOCBeanManagerProvider inj3796_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3796_IOCBeanManagerProvider);
      return inj3796_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj3796_IOCBeanManagerProvider = inj3805_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj3806_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final App inj2520_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj2520_App);
      final org_jboss_pressgangccms_client_local_AppController_inj3807_proxy inj3807_proxy = new org_jboss_pressgangccms_client_local_AppController_inj3807_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj3807_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj3807_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_3108198);
      org_jboss_pressgangccms_client_local_App_appController(inj2520_App, inj3807_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj2520_App.startApp();
            }
          });
        }
      });
      return inj2520_App;
    }
  };
  private final App inj2520_App = inj3806_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj3782_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var2 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj2520_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_3108198), var2);
      return var2;
    }
  };
  private final CreationalCallback<AppController> inj3809_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final AppController inj3808_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3808_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj3808_AppController, inj3796_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj3808_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2520_App));
      return inj3808_AppController;
    }
  };
  private final AppController inj3808_AppController = inj3809_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj3810_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final EventProvider inj3798_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3798_EventProvider);
      return inj3798_EventProvider;
    }
  };
  private final EventProvider inj3798_EventProvider = inj3810_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj3811_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final MessageBusProvider inj3794_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3794_MessageBusProvider);
      return inj3794_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj3794_MessageBusProvider = inj3811_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj3814_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final WelcomeView inj3458_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3458_WelcomeView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj3458_WelcomeView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2520_App));
      return inj3458_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj3813_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final WelcomePresenter inj3812_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3812_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj3812_WelcomePresenter, inj3814_WelcomeView_creationalCallback.getInstance(context));
      return inj3812_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj3815_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final SenderProvider inj3800_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3800_SenderProvider);
      return inj3800_SenderProvider;
    }
  };
  private final SenderProvider inj3800_SenderProvider = inj3815_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj3816_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final InitBallotProvider inj3792_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3792_InitBallotProvider);
      return inj3792_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj3792_InitBallotProvider = inj3816_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<CallerProvider> inj3817_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final CallerProvider inj3784_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3784_CallerProvider);
      return inj3784_CallerProvider;
    }
  };
  private final CallerProvider inj3784_CallerProvider = inj3817_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj3820_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final SearchView inj3457_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3457_SearchView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj3457_SearchView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj2520_App));
      return inj3457_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj3819_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final SearchPresenter inj3818_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3818_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj3818_SearchPresenter, inj3820_SearchView_creationalCallback.getInstance(context));
      return inj3818_SearchPresenter;
    }
  };
  private final CreationalCallback<RootPanelProvider> inj3821_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final RootPanelProvider inj3790_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3790_RootPanelProvider);
      return inj3790_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj3790_RootPanelProvider = inj3821_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj3822_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_3108198;
      final DisposerProvider inj3786_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj3786_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj3786_DisposerProvider, inj3796_IOCBeanManagerProvider.get());
      return inj3786_DisposerProvider;
    }
  };
  private final DisposerProvider inj3786_DisposerProvider = inj3822_DisposerProvider_creationalCallback.getInstance(context);
  static class org_jboss_pressgangccms_client_local_AppController_inj3807_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj3803_RequestDispatcherProvider_creationalCallback, inj3788_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj3803_RequestDispatcherProvider_creationalCallback, inj3788_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj3804_InstanceProvider_creationalCallback, inj3802_InstanceProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj3804_InstanceProvider_creationalCallback, inj3802_InstanceProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj3805_IOCBeanManagerProvider_creationalCallback, inj3796_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj3805_IOCBeanManagerProvider_creationalCallback, inj3796_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(App.class, App.class, inj3806_App_creationalCallback, inj2520_App, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj3782_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj3782_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(AppController.class, AppController.class, inj3809_AppController_creationalCallback, inj3808_AppController, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj3809_AppController_creationalCallback, inj3808_AppController, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj3809_AppController_creationalCallback, inj3808_AppController, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj3809_AppController_creationalCallback, inj3808_AppController, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj3810_EventProvider_creationalCallback, inj3798_EventProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj3810_EventProvider_creationalCallback, inj3798_EventProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj3811_MessageBusProvider_creationalCallback, inj3794_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj3811_MessageBusProvider_creationalCallback, inj3794_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj3814_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj3814_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj3814_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj3814_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj3813_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj3813_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj3815_SenderProvider_creationalCallback, inj3800_SenderProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj3815_SenderProvider_creationalCallback, inj3800_SenderProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj3816_InitBallotProvider_creationalCallback, inj3792_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj3816_InitBallotProvider_creationalCallback, inj3792_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj3817_CallerProvider_creationalCallback, inj3784_CallerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj3817_CallerProvider_creationalCallback, inj3784_CallerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj3820_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj3820_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(TemplateInterface.class, SearchView.class, inj3820_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(TemplateDisplay.class, SearchView.class, inj3820_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj3819_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj3819_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj3821_RootPanelProvider_creationalCallback, inj3790_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj3821_RootPanelProvider_creationalCallback, inj3790_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj3822_DisposerProvider_creationalCallback, inj3786_DisposerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj3822_DisposerProvider_creationalCallback, inj3786_DisposerProvider, arrayOf_java_lang_annotation_Annotation_3108198, null, false);
  }

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(SearchPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(TemplateDisplay instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.base.Presenter.TemplateDisplay::eventBus = value;
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