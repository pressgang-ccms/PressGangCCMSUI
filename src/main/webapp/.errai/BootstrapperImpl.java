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
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_903644 = new Annotation[] { javax_enterprise_inject_Any_3852854, javax_enterprise_inject_Default_27042222 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj20965_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final RequestDispatcherProvider inj20950_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20950_RequestDispatcherProvider);
      return inj20950_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj20950_RequestDispatcherProvider = inj20965_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj20966_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final InstanceProvider inj20964_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20964_InstanceProvider);
      return inj20964_InstanceProvider;
    }
  };
  private final InstanceProvider inj20964_InstanceProvider = inj20966_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj20967_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final IOCBeanManagerProvider inj20958_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20958_IOCBeanManagerProvider);
      return inj20958_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj20958_IOCBeanManagerProvider = inj20967_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj20968_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final App inj19684_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj19684_App);
      final org_jboss_pressgangccms_client_local_AppController_inj20969_proxy inj20969_proxy = new org_jboss_pressgangccms_client_local_AppController_inj20969_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj20969_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj20969_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_903644);
      org_jboss_pressgangccms_client_local_App_appController(inj19684_App, inj20969_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj19684_App.startApp();
            }
          });
        }
      });
      return inj19684_App;
    }
  };
  private final App inj19684_App = inj20968_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj20944_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var11 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj19684_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_903644), var11);
      return var11;
    }
  };
  private final CreationalCallback<AppController> inj20971_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final AppController inj20970_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20970_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj20970_AppController, inj20958_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj20970_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj19684_App));
      return inj20970_AppController;
    }
  };
  private final AppController inj20970_AppController = inj20971_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj20972_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final EventProvider inj20960_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20960_EventProvider);
      return inj20960_EventProvider;
    }
  };
  private final EventProvider inj20960_EventProvider = inj20972_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj20973_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final MessageBusProvider inj20956_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20956_MessageBusProvider);
      return inj20956_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj20956_MessageBusProvider = inj20973_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj20974_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final SenderProvider inj20962_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20962_SenderProvider);
      return inj20962_SenderProvider;
    }
  };
  private final SenderProvider inj20962_SenderProvider = inj20974_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj20977_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final WelcomeView inj20621_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20621_WelcomeView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj20621_WelcomeView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj19684_App));
      return inj20621_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj20976_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final WelcomePresenter inj20975_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20975_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj20975_WelcomePresenter, inj20977_WelcomeView_creationalCallback.getInstance(context));
      return inj20975_WelcomePresenter;
    }
  };
  private final CreationalCallback<InitBallotProvider> inj20978_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final InitBallotProvider inj20954_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20954_InitBallotProvider);
      return inj20954_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj20954_InitBallotProvider = inj20978_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<CallerProvider> inj20979_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final CallerProvider inj20946_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20946_CallerProvider);
      return inj20946_CallerProvider;
    }
  };
  private final CallerProvider inj20946_CallerProvider = inj20979_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj20980_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final RootPanelProvider inj20952_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20952_RootPanelProvider);
      return inj20952_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj20952_RootPanelProvider = inj20980_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj20983_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final SearchView inj20620_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20620_SearchView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj20620_SearchView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj19684_App));
      return inj20620_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj20982_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final SearchPresenter inj20981_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20981_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj20981_SearchPresenter, inj20983_SearchView_creationalCallback.getInstance(context));
      return inj20981_SearchPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj20984_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_903644;
      final DisposerProvider inj20948_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj20948_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj20948_DisposerProvider, inj20958_IOCBeanManagerProvider.get());
      return inj20948_DisposerProvider;
    }
  };
  private final DisposerProvider inj20948_DisposerProvider = inj20984_DisposerProvider_creationalCallback.getInstance(context);
  static class org_jboss_pressgangccms_client_local_AppController_inj20969_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj20965_RequestDispatcherProvider_creationalCallback, inj20950_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj20965_RequestDispatcherProvider_creationalCallback, inj20950_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj20966_InstanceProvider_creationalCallback, inj20964_InstanceProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj20966_InstanceProvider_creationalCallback, inj20964_InstanceProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj20967_IOCBeanManagerProvider_creationalCallback, inj20958_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj20967_IOCBeanManagerProvider_creationalCallback, inj20958_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(App.class, App.class, inj20968_App_creationalCallback, inj19684_App, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj20944_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj20944_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(AppController.class, AppController.class, inj20971_AppController_creationalCallback, inj20970_AppController, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj20971_AppController_creationalCallback, inj20970_AppController, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj20971_AppController_creationalCallback, inj20970_AppController, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj20971_AppController_creationalCallback, inj20970_AppController, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj20972_EventProvider_creationalCallback, inj20960_EventProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj20972_EventProvider_creationalCallback, inj20960_EventProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj20973_MessageBusProvider_creationalCallback, inj20956_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj20973_MessageBusProvider_creationalCallback, inj20956_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj20974_SenderProvider_creationalCallback, inj20962_SenderProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj20974_SenderProvider_creationalCallback, inj20962_SenderProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj20977_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj20977_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj20977_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj20977_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj20976_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj20976_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj20978_InitBallotProvider_creationalCallback, inj20954_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj20978_InitBallotProvider_creationalCallback, inj20954_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj20979_CallerProvider_creationalCallback, inj20946_CallerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj20979_CallerProvider_creationalCallback, inj20946_CallerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj20980_RootPanelProvider_creationalCallback, inj20952_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj20980_RootPanelProvider_creationalCallback, inj20952_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj20983_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj20983_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(TemplateInterface.class, SearchView.class, inj20983_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(TemplateDisplay.class, SearchView.class, inj20983_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj20982_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj20982_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_903644, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj20984_DisposerProvider_creationalCallback, inj20948_DisposerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj20984_DisposerProvider_creationalCallback, inj20948_DisposerProvider, arrayOf_java_lang_annotation_Annotation_903644, null, false);
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