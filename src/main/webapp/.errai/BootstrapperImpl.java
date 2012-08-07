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
  private final Any javax_enterprise_inject_Any_3852854 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_27042222 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_30608847 = new Annotation[] { javax_enterprise_inject_Any_3852854, javax_enterprise_inject_Default_27042222 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj22874_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final RequestDispatcherProvider inj22859_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22859_RequestDispatcherProvider);
      return inj22859_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj22859_RequestDispatcherProvider = inj22874_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj22875_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final InstanceProvider inj22873_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22873_InstanceProvider);
      return inj22873_InstanceProvider;
    }
  };
  private final InstanceProvider inj22873_InstanceProvider = inj22875_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj22876_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final IOCBeanManagerProvider inj22867_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22867_IOCBeanManagerProvider);
      return inj22867_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj22867_IOCBeanManagerProvider = inj22876_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj22877_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final App inj21593_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj21593_App);
      final org_jboss_pressgangccms_client_local_AppController_inj22878_proxy inj22878_proxy = new org_jboss_pressgangccms_client_local_AppController_inj22878_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj22878_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj22878_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_30608847);
      org_jboss_pressgangccms_client_local_App_appController(inj21593_App, inj22878_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj21593_App.startApp();
            }
          });
        }
      });
      return inj21593_App;
    }
  };
  private final App inj21593_App = inj22877_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj22853_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var12 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj21593_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_30608847), var12);
      return var12;
    }
  };
  private final CreationalCallback<AppController> inj22880_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final AppController inj22879_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22879_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj22879_AppController, inj22867_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj22879_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj21593_App));
      return inj22879_AppController;
    }
  };
  private final AppController inj22879_AppController = inj22880_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj22881_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final EventProvider inj22869_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22869_EventProvider);
      return inj22869_EventProvider;
    }
  };
  private final EventProvider inj22869_EventProvider = inj22881_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj22882_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final MessageBusProvider inj22865_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22865_MessageBusProvider);
      return inj22865_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj22865_MessageBusProvider = inj22882_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj22883_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final SenderProvider inj22871_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22871_SenderProvider);
      return inj22871_SenderProvider;
    }
  };
  private final SenderProvider inj22871_SenderProvider = inj22883_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj22886_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final WelcomeView inj22530_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22530_WelcomeView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj22530_WelcomeView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj21593_App));
      return inj22530_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj22885_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final WelcomePresenter inj22884_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22884_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj22884_WelcomePresenter, inj22886_WelcomeView_creationalCallback.getInstance(context));
      return inj22884_WelcomePresenter;
    }
  };
  private final CreationalCallback<InitBallotProvider> inj22887_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final InitBallotProvider inj22863_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22863_InitBallotProvider);
      return inj22863_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj22863_InitBallotProvider = inj22887_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<CallerProvider> inj22888_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final CallerProvider inj22855_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22855_CallerProvider);
      return inj22855_CallerProvider;
    }
  };
  private final CallerProvider inj22855_CallerProvider = inj22888_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj22889_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final RootPanelProvider inj22861_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22861_RootPanelProvider);
      return inj22861_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj22861_RootPanelProvider = inj22889_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj22892_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final SearchView inj22529_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22529_SearchView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj22529_SearchView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj21593_App));
      return inj22529_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj22891_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final SearchPresenter inj22890_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22890_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj22890_SearchPresenter, inj22892_SearchView_creationalCallback.getInstance(context));
      return inj22890_SearchPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj22893_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30608847;
      final DisposerProvider inj22857_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj22857_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj22857_DisposerProvider, inj22867_IOCBeanManagerProvider.get());
      return inj22857_DisposerProvider;
    }
  };
  private final DisposerProvider inj22857_DisposerProvider = inj22893_DisposerProvider_creationalCallback.getInstance(context);
  static class org_jboss_pressgangccms_client_local_AppController_inj22878_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj22874_RequestDispatcherProvider_creationalCallback, inj22859_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj22874_RequestDispatcherProvider_creationalCallback, inj22859_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj22875_InstanceProvider_creationalCallback, inj22873_InstanceProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj22875_InstanceProvider_creationalCallback, inj22873_InstanceProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj22876_IOCBeanManagerProvider_creationalCallback, inj22867_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj22876_IOCBeanManagerProvider_creationalCallback, inj22867_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(App.class, App.class, inj22877_App_creationalCallback, inj21593_App, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj22853_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj22853_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(AppController.class, AppController.class, inj22880_AppController_creationalCallback, inj22879_AppController, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj22880_AppController_creationalCallback, inj22879_AppController, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj22880_AppController_creationalCallback, inj22879_AppController, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj22880_AppController_creationalCallback, inj22879_AppController, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj22881_EventProvider_creationalCallback, inj22869_EventProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj22881_EventProvider_creationalCallback, inj22869_EventProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj22882_MessageBusProvider_creationalCallback, inj22865_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj22882_MessageBusProvider_creationalCallback, inj22865_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj22883_SenderProvider_creationalCallback, inj22871_SenderProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj22883_SenderProvider_creationalCallback, inj22871_SenderProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj22886_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj22886_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj22886_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj22886_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj22885_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj22885_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj22887_InitBallotProvider_creationalCallback, inj22863_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj22887_InitBallotProvider_creationalCallback, inj22863_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj22888_CallerProvider_creationalCallback, inj22855_CallerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj22888_CallerProvider_creationalCallback, inj22855_CallerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj22889_RootPanelProvider_creationalCallback, inj22861_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj22889_RootPanelProvider_creationalCallback, inj22861_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj22892_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj22892_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(TemplateInterface.class, SearchView.class, inj22892_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(TemplateDisplay.class, SearchView.class, inj22892_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj22891_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj22891_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj22893_DisposerProvider_creationalCallback, inj22857_DisposerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj22893_DisposerProvider_creationalCallback, inj22857_DisposerProvider, arrayOf_java_lang_annotation_Annotation_30608847, null, false);
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