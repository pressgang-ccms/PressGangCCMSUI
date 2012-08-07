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
  private final Default javax_enterprise_inject_Default_19647613 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_8269158 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_2764779 = new Annotation[] { javax_enterprise_inject_Default_19647613, javax_enterprise_inject_Any_8269158 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<RequestDispatcherProvider> inj9531_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final RequestDispatcherProvider inj9516_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9516_RequestDispatcherProvider);
      return inj9516_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj9516_RequestDispatcherProvider = inj9531_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj9532_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final InstanceProvider inj9530_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9530_InstanceProvider);
      return inj9530_InstanceProvider;
    }
  };
  private final InstanceProvider inj9530_InstanceProvider = inj9532_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj9533_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final IOCBeanManagerProvider inj9524_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9524_IOCBeanManagerProvider);
      return inj9524_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj9524_IOCBeanManagerProvider = inj9533_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj9534_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final App inj8249_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj8249_App);
      final org_jboss_pressgangccms_client_local_AppController_inj9535_proxy inj9535_proxy = new org_jboss_pressgangccms_client_local_AppController_inj9535_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj9535_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj9535_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_2764779);
      org_jboss_pressgangccms_client_local_App_appController(inj8249_App, inj9535_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj8249_App.startApp();
            }
          });
        }
      });
      return inj8249_App;
    }
  };
  private final App inj8249_App = inj9534_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj9510_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var5 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj8249_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_2764779), var5);
      return var5;
    }
  };
  private final CreationalCallback<AppController> inj9537_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final AppController inj9536_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9536_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj9536_AppController, inj9524_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj9536_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj8249_App));
      return inj9536_AppController;
    }
  };
  private final AppController inj9536_AppController = inj9537_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj9538_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final EventProvider inj9526_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9526_EventProvider);
      return inj9526_EventProvider;
    }
  };
  private final EventProvider inj9526_EventProvider = inj9538_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj9539_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final MessageBusProvider inj9522_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9522_MessageBusProvider);
      return inj9522_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj9522_MessageBusProvider = inj9539_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj9542_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final WelcomeView inj9186_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9186_WelcomeView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj9186_WelcomeView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj8249_App));
      return inj9186_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj9541_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final WelcomePresenter inj9540_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9540_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj9540_WelcomePresenter, inj9542_WelcomeView_creationalCallback.getInstance(context));
      return inj9540_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj9543_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final SenderProvider inj9528_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9528_SenderProvider);
      return inj9528_SenderProvider;
    }
  };
  private final SenderProvider inj9528_SenderProvider = inj9543_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj9544_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final InitBallotProvider inj9520_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9520_InitBallotProvider);
      return inj9520_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj9520_InitBallotProvider = inj9544_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<CallerProvider> inj9545_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final CallerProvider inj9512_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9512_CallerProvider);
      return inj9512_CallerProvider;
    }
  };
  private final CallerProvider inj9512_CallerProvider = inj9545_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj9548_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final SearchView inj9185_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9185_SearchView);
      org_jboss_pressgangccms_client_local_presenter_base_Presenter$TemplateDisplay_eventBus(inj9185_SearchView, org_jboss_pressgangccms_client_local_App_produceEventBus(inj8249_App));
      return inj9185_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj9547_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final SearchPresenter inj9546_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9546_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj9546_SearchPresenter, inj9548_SearchView_creationalCallback.getInstance(context));
      return inj9546_SearchPresenter;
    }
  };
  private final CreationalCallback<RootPanelProvider> inj9549_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final RootPanelProvider inj9518_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9518_RootPanelProvider);
      return inj9518_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj9518_RootPanelProvider = inj9549_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj9550_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_2764779;
      final DisposerProvider inj9514_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj9514_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj9514_DisposerProvider, inj9524_IOCBeanManagerProvider.get());
      return inj9514_DisposerProvider;
    }
  };
  private final DisposerProvider inj9514_DisposerProvider = inj9550_DisposerProvider_creationalCallback.getInstance(context);
  static class org_jboss_pressgangccms_client_local_AppController_inj9535_proxy extends AppController {
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
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj9531_RequestDispatcherProvider_creationalCallback, inj9516_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj9531_RequestDispatcherProvider_creationalCallback, inj9516_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj9532_InstanceProvider_creationalCallback, inj9530_InstanceProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj9532_InstanceProvider_creationalCallback, inj9530_InstanceProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj9533_IOCBeanManagerProvider_creationalCallback, inj9524_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj9533_IOCBeanManagerProvider_creationalCallback, inj9524_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(App.class, App.class, inj9534_App_creationalCallback, inj8249_App, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj9510_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj9510_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(AppController.class, AppController.class, inj9537_AppController_creationalCallback, inj9536_AppController, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj9537_AppController_creationalCallback, inj9536_AppController, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj9537_AppController_creationalCallback, inj9536_AppController, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj9537_AppController_creationalCallback, inj9536_AppController, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj9538_EventProvider_creationalCallback, inj9526_EventProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj9538_EventProvider_creationalCallback, inj9526_EventProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj9539_MessageBusProvider_creationalCallback, inj9522_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj9539_MessageBusProvider_creationalCallback, inj9522_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj9542_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Display.class, WelcomeView.class, inj9542_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(TemplateInterface.class, WelcomeView.class, inj9542_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(TemplateDisplay.class, WelcomeView.class, inj9542_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj9541_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj9541_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj9543_SenderProvider_creationalCallback, inj9528_SenderProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj9543_SenderProvider_creationalCallback, inj9528_SenderProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj9544_InitBallotProvider_creationalCallback, inj9520_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj9544_InitBallotProvider_creationalCallback, inj9520_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj9545_CallerProvider_creationalCallback, inj9512_CallerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj9545_CallerProvider_creationalCallback, inj9512_CallerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj9548_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj9548_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(TemplateInterface.class, SearchView.class, inj9548_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(TemplateDisplay.class, SearchView.class, inj9548_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj9547_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj9547_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj9549_RootPanelProvider_creationalCallback, inj9518_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj9549_RootPanelProvider_creationalCallback, inj9518_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj9550_DisposerProvider_creationalCallback, inj9514_DisposerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj9550_DisposerProvider_creationalCallback, inj9514_DisposerProvider, arrayOf_java_lang_annotation_Annotation_2764779, null, false);
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