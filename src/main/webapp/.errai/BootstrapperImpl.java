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
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.view.SearchView;
import org.jboss.pressgangccms.client.local.view.TopicRenderedView;
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
  private final Any javax_enterprise_inject_Any_23583128 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_28029645 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_22973272 = new Annotation[] { javax_enterprise_inject_Any_23583128, javax_enterprise_inject_Default_28029645 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj1926_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final IOCBeanManagerProvider inj1919_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1919_IOCBeanManagerProvider);
      return inj1919_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1919_IOCBeanManagerProvider = inj1926_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1927_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final App inj618_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj618_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1928_proxy inj1928_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1928_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1928_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1928_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_22973272);
      org_jboss_pressgangccms_client_local_App_appController(inj618_App, inj1928_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj618_App.startApp();
            }
          });
        }
      });
      return inj618_App;
    }
  };
  private final App inj618_App = inj1927_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1905_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_22973272), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1930_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final AppController inj1929_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1929_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1929_AppController, inj1919_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1929_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1929_AppController;
    }
  };
  private final AppController inj1929_AppController = inj1930_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj1933_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchResultsView inj1556_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1556_SearchResultsView);
      return inj1556_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj1934_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicView inj1561_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1561_TopicView);
      return inj1561_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj1932_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchResultsPresenter inj1931_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1931_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj1931_SearchResultsPresenter, inj1933_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj1931_SearchResultsPresenter, inj1934_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1931_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1931_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj1935_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final RequestDispatcherProvider inj1911_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1911_RequestDispatcherProvider);
      return inj1911_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1911_RequestDispatcherProvider = inj1935_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1936_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final InstanceProvider inj1925_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1925_InstanceProvider);
      return inj1925_InstanceProvider;
    }
  };
  private final InstanceProvider inj1925_InstanceProvider = inj1936_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1937_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final EventProvider inj1921_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1921_EventProvider);
      return inj1921_EventProvider;
    }
  };
  private final EventProvider inj1921_EventProvider = inj1937_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1938_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final MessageBusProvider inj1917_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1917_MessageBusProvider);
      return inj1917_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1917_MessageBusProvider = inj1938_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SenderProvider> inj1939_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SenderProvider inj1923_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1923_SenderProvider);
      return inj1923_SenderProvider;
    }
  };
  private final SenderProvider inj1923_SenderProvider = inj1939_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj1942_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final WelcomeView inj1563_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1563_WelcomeView);
      return inj1563_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1941_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final WelcomePresenter inj1940_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1940_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1940_WelcomePresenter, inj1942_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1940_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1940_WelcomePresenter;
    }
  };
  private final CreationalCallback<TopicXMLErrorsView> inj1945_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicXMLErrorsView inj1564_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1564_TopicXMLErrorsView);
      return inj1564_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj1944_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicXMLErrorsPresenter inj1943_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1943_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(inj1943_TopicXMLErrorsPresenter, inj1945_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1943_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1943_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<InitBallotProvider> inj1946_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final InitBallotProvider inj1915_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1915_InitBallotProvider);
      return inj1915_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1915_InitBallotProvider = inj1946_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj1948_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicPresenter inj1947_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1947_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj1947_TopicPresenter, inj1934_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1947_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1947_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj1949_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final CallerProvider inj1907_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1907_CallerProvider);
      return inj1907_CallerProvider;
    }
  };
  private final CallerProvider inj1907_CallerProvider = inj1949_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj1950_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final RootPanelProvider inj1913_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1913_RootPanelProvider);
      return inj1913_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1913_RootPanelProvider = inj1950_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj1953_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchView inj1557_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1557_SearchView);
      return inj1557_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj1952_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchPresenter inj1951_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1951_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj1951_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj1951_SearchPresenter, inj1953_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1951_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1951_SearchPresenter;
    }
  };
  private final CreationalCallback<TopicTagsView> inj1956_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicTagsView inj1559_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1559_TopicTagsView);
      return inj1559_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj1955_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicTagsPresenter inj1954_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1954_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(inj1954_TopicTagsPresenter, inj1956_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1954_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1954_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj1958_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicRenderedPresenter inj1957_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1957_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1957_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1957_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj1960_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicXMLPresenter inj1959_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1959_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1959_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1959_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj1961_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final DisposerProvider inj1909_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1909_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1909_DisposerProvider, inj1919_IOCBeanManagerProvider.get());
      return inj1909_DisposerProvider;
    }
  };
  private final DisposerProvider inj1909_DisposerProvider = inj1961_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsAndTopicView> inj1964_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchResultsAndTopicView inj1558_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1558_SearchResultsAndTopicView);
      return inj1558_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj1965_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicXMLView inj1560_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1560_TopicXMLView);
      return inj1560_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj1966_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final TopicRenderedView inj1562_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1562_TopicRenderedView);
      return inj1562_TopicRenderedView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj1963_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_22973272;
      final SearchResultsAndTopicPresenter inj1962_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1962_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj1962_SearchResultsAndTopicPresenter, inj1964_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj1962_SearchResultsAndTopicPresenter, inj1934_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj1962_SearchResultsAndTopicPresenter, inj1965_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj1962_SearchResultsAndTopicPresenter, inj1966_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj1962_SearchResultsAndTopicPresenter, inj1933_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj1962_SearchResultsAndTopicPresenter, inj1945_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj1962_SearchResultsAndTopicPresenter, inj1956_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1962_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1962_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1928_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1926_IOCBeanManagerProvider_creationalCallback, inj1919_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1926_IOCBeanManagerProvider_creationalCallback, inj1919_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(App.class, App.class, inj1927_App_creationalCallback, inj618_App, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1905_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1905_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1930_AppController_creationalCallback, inj1929_AppController, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1930_AppController_creationalCallback, inj1929_AppController, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1930_AppController_creationalCallback, inj1929_AppController, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1930_AppController_creationalCallback, inj1929_AppController, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj1933_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Display.class, SearchResultsView.class, inj1933_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj1933_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj1933_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj1934_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj1932_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj1932_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj1932_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1935_RequestDispatcherProvider_creationalCallback, inj1911_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1935_RequestDispatcherProvider_creationalCallback, inj1911_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1936_InstanceProvider_creationalCallback, inj1925_InstanceProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1936_InstanceProvider_creationalCallback, inj1925_InstanceProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1937_EventProvider_creationalCallback, inj1921_EventProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1937_EventProvider_creationalCallback, inj1921_EventProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1938_MessageBusProvider_creationalCallback, inj1917_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1938_MessageBusProvider_creationalCallback, inj1917_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1939_SenderProvider_creationalCallback, inj1923_SenderProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1939_SenderProvider_creationalCallback, inj1923_SenderProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1942_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj1942_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1942_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1942_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1941_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1941_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1941_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj1945_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj1944_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj1944_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj1944_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1946_InitBallotProvider_creationalCallback, inj1915_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1946_InitBallotProvider_creationalCallback, inj1915_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj1948_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj1948_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj1948_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1949_CallerProvider_creationalCallback, inj1907_CallerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1949_CallerProvider_creationalCallback, inj1907_CallerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1950_RootPanelProvider_creationalCallback, inj1913_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1950_RootPanelProvider_creationalCallback, inj1913_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj1956_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj1955_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj1955_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj1955_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj1958_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj1958_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj1958_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj1960_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj1960_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj1960_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1961_DisposerProvider_creationalCallback, inj1909_DisposerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1961_DisposerProvider_creationalCallback, inj1909_DisposerProvider, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj1964_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj1964_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj1964_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj1964_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj1965_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj1966_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj1963_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj1963_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj1963_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_22973272, null, false);
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

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(TopicTagsPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter::display = value;
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
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