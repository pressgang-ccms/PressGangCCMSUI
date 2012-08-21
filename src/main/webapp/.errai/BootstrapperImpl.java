package org.jboss.errai.ioc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
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
import org.jboss.pressgangccms.client.local.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.Display;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectsEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.newtags.TopicTagViewNewCategoryEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.newtags.TopicTagViewNewProjectEditor;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.newtags.TopicTagViewNewProjectsEditor;
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
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Default javax_enterprise_inject_Default_19408894 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any javax_enterprise_inject_Any_22661530 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_26914062 = new Annotation[] { javax_enterprise_inject_Default_19408894, javax_enterprise_inject_Any_22661530 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj1927_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final IOCBeanManagerProvider inj1920_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1920_IOCBeanManagerProvider);
      return inj1920_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj1920_IOCBeanManagerProvider = inj1927_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj1928_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final App inj618_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj618_App);
      final org_jboss_pressgangccms_client_local_AppController_inj1929_proxy inj1929_proxy = new org_jboss_pressgangccms_client_local_AppController_inj1929_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj1929_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1929_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_26914062);
      org_jboss_pressgangccms_client_local_App_appController(inj618_App, inj1929_proxy);
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
  private final App inj618_App = inj1928_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj1906_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var1 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_26914062), var1);
      return var1;
    }
  };
  private final CreationalCallback<AppController> inj1931_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final AppController inj1930_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1930_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj1930_AppController, inj1920_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj1930_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1930_AppController;
    }
  };
  private final AppController inj1930_AppController = inj1931_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchResultsView> inj1934_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SearchResultsView inj1560_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1560_SearchResultsView);
      return inj1560_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj1935_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicView inj1563_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1563_TopicView);
      return inj1563_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj1933_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SearchResultsPresenter inj1932_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1932_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_display(inj1932_SearchResultsPresenter, inj1934_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsPresenter_topicViewDisplay(inj1932_SearchResultsPresenter, inj1935_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1932_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1932_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj1936_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final RequestDispatcherProvider inj1912_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1912_RequestDispatcherProvider);
      return inj1912_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj1912_RequestDispatcherProvider = inj1936_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj1937_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final InstanceProvider inj1926_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1926_InstanceProvider);
      return inj1926_InstanceProvider;
    }
  };
  private final InstanceProvider inj1926_InstanceProvider = inj1937_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<EventProvider> inj1938_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final EventProvider inj1922_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1922_EventProvider);
      return inj1922_EventProvider;
    }
  };
  private final EventProvider inj1922_EventProvider = inj1938_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj1939_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final MessageBusProvider inj1918_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1918_MessageBusProvider);
      return inj1918_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj1918_MessageBusProvider = inj1939_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLErrorsView> inj1942_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicXMLErrorsView inj1566_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1566_TopicXMLErrorsView);
      return inj1566_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj1941_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicXMLErrorsPresenter inj1940_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1940_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicXMLErrorsPresenter_display(inj1940_TopicXMLErrorsPresenter, inj1942_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1940_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1940_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<WelcomeView> inj1945_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final WelcomeView inj1565_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1565_WelcomeView);
      return inj1565_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj1944_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final WelcomePresenter inj1943_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1943_WelcomePresenter);
      org_jboss_pressgangccms_client_local_presenter_WelcomePresenter_display(inj1943_WelcomePresenter, inj1945_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1943_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1943_WelcomePresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj1946_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SenderProvider inj1924_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1924_SenderProvider);
      return inj1924_SenderProvider;
    }
  };
  private final SenderProvider inj1924_SenderProvider = inj1946_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj1947_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final InitBallotProvider inj1916_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1916_InitBallotProvider);
      return inj1916_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj1916_InitBallotProvider = inj1947_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj1949_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicPresenter inj1948_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1948_TopicPresenter);
      org_jboss_pressgangccms_client_local_presenter_TopicPresenter_display(inj1948_TopicPresenter, inj1935_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1948_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1948_TopicPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj1950_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final CallerProvider inj1908_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1908_CallerProvider);
      return inj1908_CallerProvider;
    }
  };
  private final CallerProvider inj1908_CallerProvider = inj1950_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj1953_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SearchView inj1561_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1561_SearchView);
      return inj1561_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj1952_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SearchPresenter inj1951_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1951_SearchPresenter);
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_eventBus(inj1951_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      org_jboss_pressgangccms_client_local_presenter_SearchPresenter_display(inj1951_SearchPresenter, inj1953_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1951_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1951_SearchPresenter;
    }
  };
  private final CreationalCallback<RootPanelProvider> inj1954_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final RootPanelProvider inj1914_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1914_RootPanelProvider);
      return inj1914_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj1914_RootPanelProvider = inj1954_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsPresenter> inj1956_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicTagsPresenter inj1955_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1955_TopicTagsPresenter);
      final org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter$Display_inj1957_proxy inj1957_proxy = new org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter$Display_inj1957_proxy();
      context.addUnresolvedProxy(new ProxyResolver<Display>() {
        public void resolve(Display obj) {
          inj1957_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1957_proxy, obj);
        }
      }, Display.class, arrayOf_java_lang_annotation_Annotation_26914062);
      org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(inj1955_TopicTagsPresenter, inj1957_proxy);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1955_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1955_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<TopicRenderedPresenter> inj1959_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicRenderedPresenter inj1958_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1958_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1958_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1958_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj1961_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicXMLPresenter inj1960_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1960_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1960_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1960_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj1962_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final DisposerProvider inj1910_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1910_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj1910_DisposerProvider, inj1920_IOCBeanManagerProvider.get());
      return inj1910_DisposerProvider;
    }
  };
  private final DisposerProvider inj1910_DisposerProvider = inj1962_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLView> inj1966_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicXMLView inj1562_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1562_TopicXMLView);
      return inj1562_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj1967_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final TopicRenderedView inj1564_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1564_TopicRenderedView);
      return inj1564_TopicRenderedView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj1964_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_26914062;
      final SearchResultsAndTopicPresenter inj1963_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj1963_SearchResultsAndTopicPresenter);
      final org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter$Display_inj1965_proxy inj1965_proxy = new org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter$Display_inj1965_proxy();
      context.addUnresolvedProxy(new ProxyResolver<org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display>() {
        public void resolve(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display obj) {
          inj1965_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1965_proxy, obj);
        }
      }, org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display.class, arrayOf_java_lang_annotation_Annotation_26914062);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_display(inj1963_SearchResultsAndTopicPresenter, inj1965_proxy);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj1963_SearchResultsAndTopicPresenter, inj1935_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj1963_SearchResultsAndTopicPresenter, inj1966_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj1963_SearchResultsAndTopicPresenter, inj1967_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj1963_SearchResultsAndTopicPresenter, inj1934_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj1963_SearchResultsAndTopicPresenter, inj1942_TopicXMLErrorsView_creationalCallback.getInstance(context));
      final org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter$Display_inj1957_proxy inj1957_proxy = new org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter$Display_inj1957_proxy();
      context.addUnresolvedProxy(new ProxyResolver<Display>() {
        public void resolve(Display obj) {
          inj1957_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj1957_proxy, obj);
        }
      }, Display.class, arrayOf_java_lang_annotation_Annotation_26914062);
      org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj1963_SearchResultsAndTopicPresenter, inj1957_proxy);
      org_jboss_pressgangccms_client_local_presenter_base_TemplatePresenter_eventBus(inj1963_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj618_App));
      return inj1963_SearchResultsAndTopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj1929_proxy extends AppController {
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
  static class org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter$Display_inj1957_proxy implements Display {
    private Display $$_proxy_$$;
    public void initialize(RESTTopicV1 a0) {
      $$_proxy_$$.initialize(a0);
    }

    public TopicTagViewProjectsEditor getEditor() {
      return $$_proxy_$$.getEditor();
    }

    public SimpleBeanEditorDriver getNewCategoryDriver() {
      return $$_proxy_$$.getNewCategoryDriver();
    }

    public SimpleBeanEditorDriver getNewProjectDriver() {
      return $$_proxy_$$.getNewProjectDriver();
    }

    public SimpleBeanEditorDriver getNewProjectsDriver() {
      return $$_proxy_$$.getNewProjectsDriver();
    }

    public TopicTagViewNewCategoryEditor getNewTagCategory() {
      return $$_proxy_$$.getNewTagCategory();
    }

    public TopicTagViewNewProjectEditor getNewTagProject() {
      return $$_proxy_$$.getNewTagProject();
    }

    public TopicTagViewNewProjectsEditor getNewTagProjects() {
      return $$_proxy_$$.getNewTagProjects();
    }

    public void updateNewTagDisplay() {
      $$_proxy_$$.updateNewTagDisplay();
    }

    public PushButton getFields() {
      return $$_proxy_$$.getFields();
    }

    public PushButton getRendered() {
      return $$_proxy_$$.getRendered();
    }

    public PushButton getXml() {
      return $$_proxy_$$.getXml();
    }

    public PushButton getSave() {
      return $$_proxy_$$.getSave();
    }

    public PushButton getXmlErrors() {
      return $$_proxy_$$.getXmlErrors();
    }

    public PushButton getTags() {
      return $$_proxy_$$.getTags();
    }

    public SimpleBeanEditorDriver getDriver() {
      return $$_proxy_$$.getDriver();
    }

    public DockLayoutPanel getTopLevelPanel() {
      return $$_proxy_$$.getTopLevelPanel();
    }

    public void setSpinnerVisible(boolean a0) {
      $$_proxy_$$.setSpinnerVisible(a0);
    }

    public PushButton getSearch() {
      return $$_proxy_$$.getSearch();
    }

    public PushButton getBug() {
      return $$_proxy_$$.getBug();
    }

    public FlexTable getTopActionPanel() {
      return $$_proxy_$$.getTopActionPanel();
    }

    public SimpleLayoutPanel getPanel() {
      return $$_proxy_$$.getPanel();
    }

    public void __$setProxiedInstance$(Display proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  static class org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter$Display_inj1965_proxy implements org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display {
    private org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display $$_proxy_$$;
    public SimpleLayoutPanel getTopicResultsActionButtonsPanel() {
      return $$_proxy_$$.getTopicResultsActionButtonsPanel();
    }

    public SimpleLayoutPanel getTopicResultsPanel() {
      return $$_proxy_$$.getTopicResultsPanel();
    }

    public HanldedSplitLayoutPanel getSplitPanel() {
      return $$_proxy_$$.getSplitPanel();
    }

    public SimpleLayoutPanel getTopicViewActionButtonsPanel() {
      return $$_proxy_$$.getTopicViewActionButtonsPanel();
    }

    public SimpleLayoutPanel getTopicViewPanel() {
      return $$_proxy_$$.getTopicViewPanel();
    }

    public DockLayoutPanel getTopLevelPanel() {
      return $$_proxy_$$.getTopLevelPanel();
    }

    public void setSpinnerVisible(boolean a0) {
      $$_proxy_$$.setSpinnerVisible(a0);
    }

    public PushButton getSearch() {
      return $$_proxy_$$.getSearch();
    }

    public PushButton getBug() {
      return $$_proxy_$$.getBug();
    }

    public FlexTable getTopActionPanel() {
      return $$_proxy_$$.getTopActionPanel();
    }

    public SimpleLayoutPanel getPanel() {
      return $$_proxy_$$.getPanel();
    }

    public void __$setProxiedInstance$(org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter.Display proxy) {
      $$_proxy_$$ = proxy;
    }
  }
  private void declareBeans_0() {
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj1927_IOCBeanManagerProvider_creationalCallback, inj1920_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj1927_IOCBeanManagerProvider_creationalCallback, inj1920_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(App.class, App.class, inj1928_App_creationalCallback, inj618_App, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj1906_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj1906_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(AppController.class, AppController.class, inj1931_AppController_creationalCallback, inj1930_AppController, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj1931_AppController_creationalCallback, inj1930_AppController, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj1931_AppController_creationalCallback, inj1930_AppController, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj1931_AppController_creationalCallback, inj1930_AppController, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj1934_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display.class, SearchResultsView.class, inj1934_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj1934_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj1934_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicPresenter.Display.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj1935_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj1933_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj1933_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj1933_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj1936_RequestDispatcherProvider_creationalCallback, inj1912_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj1936_RequestDispatcherProvider_creationalCallback, inj1912_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj1937_InstanceProvider_creationalCallback, inj1926_InstanceProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj1937_InstanceProvider_creationalCallback, inj1926_InstanceProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj1938_EventProvider_creationalCallback, inj1922_EventProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj1938_EventProvider_creationalCallback, inj1922_EventProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj1939_MessageBusProvider_creationalCallback, inj1918_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj1939_MessageBusProvider_creationalCallback, inj1918_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj1942_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj1941_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj1941_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj1941_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj1945_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj1945_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj1945_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj1945_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj1944_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj1944_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj1944_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj1946_SenderProvider_creationalCallback, inj1924_SenderProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj1946_SenderProvider_creationalCallback, inj1924_SenderProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj1947_InitBallotProvider_creationalCallback, inj1916_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj1947_InitBallotProvider_creationalCallback, inj1916_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj1949_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj1949_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj1949_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj1950_CallerProvider_creationalCallback, inj1908_CallerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj1950_CallerProvider_creationalCallback, inj1908_CallerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj1953_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj1952_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj1954_RootPanelProvider_creationalCallback, inj1914_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj1954_RootPanelProvider_creationalCallback, inj1914_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj1956_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj1956_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj1956_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj1959_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj1959_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj1959_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj1961_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj1961_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj1961_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj1962_DisposerProvider_creationalCallback, inj1910_DisposerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj1962_DisposerProvider_creationalCallback, inj1910_DisposerProvider, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj1966_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj1967_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj1964_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj1964_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj1964_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_26914062, null, false);
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

  private native static void org_jboss_pressgangccms_client_local_presenter_TopicTagsPresenter_display(TopicTagsPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.SearchResultsPresenter.Display value) /*-{
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

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.presenter.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
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