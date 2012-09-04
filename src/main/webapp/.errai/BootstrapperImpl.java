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
import org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter.Display;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.Presenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgangccms.client.local.mvp.view.ImageFilteredResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.ImageView;
import org.jboss.pressgangccms.client.local.mvp.view.ImagesFilteredResultsAndImageView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsAndTopicView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchResultsView;
import org.jboss.pressgangccms.client.local.mvp.view.SearchView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicBugsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicRenderedView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicRevisionsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicTagsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicXMLErrorsView;
import org.jboss.pressgangccms.client.local.mvp.view.TopicXMLView;
import org.jboss.pressgangccms.client.local.mvp.view.WelcomeView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.base.TopicViewBase;
import org.jboss.pressgangccms.client.local.mvp.view.base.TopicViewInterface;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new JaxrsModuleBootstrapper().run();
  }
  private final Any javax_enterprise_inject_Any_9917231 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default javax_enterprise_inject_Default_25341603 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_java_lang_annotation_Annotation_30006858 = new Annotation[] { javax_enterprise_inject_Any_9917231, javax_enterprise_inject_Default_25341603 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<IOCBeanManagerProvider> inj6170_IOCBeanManagerProvider_creationalCallback = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      Class beanType = IOCBeanManagerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final IOCBeanManagerProvider inj6163_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6163_IOCBeanManagerProvider);
      return inj6163_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj6163_IOCBeanManagerProvider = inj6170_IOCBeanManagerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<App> inj6171_App_creationalCallback = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      Class beanType = App.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final App inj4793_App = new App();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj4793_App);
      final org_jboss_pressgangccms_client_local_AppController_inj6172_proxy inj6172_proxy = new org_jboss_pressgangccms_client_local_AppController_inj6172_proxy();
      context.addUnresolvedProxy(new ProxyResolver<AppController>() {
        public void resolve(AppController obj) {
          inj6172_proxy.__$setProxiedInstance$(obj);
          context.addProxyReference(inj6172_proxy, obj);
        }
      }, AppController.class, arrayOf_java_lang_annotation_Annotation_30006858);
      org_jboss_pressgangccms_client_local_App_appController(inj4793_App, inj6172_proxy);
      InitVotes.registerOneTimeInitCallback(new Runnable() {
        public void run() {
          GWT.runAsync(new RunAsyncCallback() {
            public void onFailure(Throwable throwable) {
              throw new RuntimeException("failed to run asynchronously", throwable);
            }
            public void onSuccess() {
              inj4793_App.startApp();
            }
          });
        }
      });
      return inj4793_App;
    }
  };
  private final App inj4793_App = inj6171_App_creationalCallback.getInstance(context);
  private final CreationalCallback<HandlerManager> inj6149_HandlerManager_creationalCallback = new CreationalCallback<HandlerManager>() {
    public HandlerManager getInstance(CreationalContext pContext) {
      HandlerManager var3 = org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App);
      context.addBean(context.getBeanReference(HandlerManager.class, arrayOf_java_lang_annotation_Annotation_30006858), var3);
      return var3;
    }
  };
  private final CreationalCallback<AppController> inj6174_AppController_creationalCallback = new CreationalCallback<AppController>() {
    public AppController getInstance(final CreationalContext context) {
      Class beanType = AppController.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final AppController inj6173_AppController = new AppController();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6173_AppController);
      org_jboss_pressgangccms_client_local_AppController_manager(inj6173_AppController, inj6163_IOCBeanManagerProvider.get());
      org_jboss_pressgangccms_client_local_AppController_eventBus(inj6173_AppController, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6173_AppController;
    }
  };
  private final AppController inj6173_AppController = inj6174_AppController_creationalCallback.getInstance(context);
  private final CreationalCallback<SearchView> inj6177_SearchView_creationalCallback = new CreationalCallback<SearchView>() {
    public SearchView getInstance(final CreationalContext context) {
      Class beanType = SearchView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchView inj6048_SearchView = new SearchView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6048_SearchView);
      return inj6048_SearchView;
    }
  };
  private final CreationalCallback<SearchPresenter> inj6176_SearchPresenter_creationalCallback = new CreationalCallback<SearchPresenter>() {
    public SearchPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchPresenter inj6175_SearchPresenter = new SearchPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6175_SearchPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_eventBus(inj6175_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_display(inj6175_SearchPresenter, inj6177_SearchView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6175_SearchPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6175_SearchPresenter;
    }
  };
  private final CreationalCallback<SearchResultsView> inj6180_SearchResultsView_creationalCallback = new CreationalCallback<SearchResultsView>() {
    public SearchResultsView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchResultsView inj6047_SearchResultsView = new SearchResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6047_SearchResultsView);
      return inj6047_SearchResultsView;
    }
  };
  private final CreationalCallback<TopicView> inj6181_TopicView_creationalCallback = new CreationalCallback<TopicView>() {
    public TopicView getInstance(final CreationalContext context) {
      Class beanType = TopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicView inj6044_TopicView = new TopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6044_TopicView);
      return inj6044_TopicView;
    }
  };
  private final CreationalCallback<SearchResultsPresenter> inj6179_SearchResultsPresenter_creationalCallback = new CreationalCallback<SearchResultsPresenter>() {
    public SearchResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchResultsPresenter inj6178_SearchResultsPresenter = new SearchResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6178_SearchResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_display(inj6178_SearchResultsPresenter, inj6180_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_topicViewDisplay(inj6178_SearchResultsPresenter, inj6181_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6178_SearchResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6178_SearchResultsPresenter;
    }
  };
  private final CreationalCallback<RequestDispatcherProvider> inj6182_RequestDispatcherProvider_creationalCallback = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      Class beanType = RequestDispatcherProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final RequestDispatcherProvider inj6155_RequestDispatcherProvider = new RequestDispatcherProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6155_RequestDispatcherProvider);
      return inj6155_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj6155_RequestDispatcherProvider = inj6182_RequestDispatcherProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicTagsView> inj6185_TopicTagsView_creationalCallback = new CreationalCallback<TopicTagsView>() {
    public TopicTagsView getInstance(final CreationalContext context) {
      Class beanType = TopicTagsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicTagsView inj6042_TopicTagsView = new TopicTagsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6042_TopicTagsView);
      return inj6042_TopicTagsView;
    }
  };
  private final CreationalCallback<TopicTagsPresenter> inj6184_TopicTagsPresenter_creationalCallback = new CreationalCallback<TopicTagsPresenter>() {
    public TopicTagsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicTagsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicTagsPresenter inj6183_TopicTagsPresenter = new TopicTagsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6183_TopicTagsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicTagsPresenter_display(inj6183_TopicTagsPresenter, inj6185_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6183_TopicTagsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6183_TopicTagsPresenter;
    }
  };
  private final CreationalCallback<InstanceProvider> inj6186_InstanceProvider_creationalCallback = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      Class beanType = InstanceProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final InstanceProvider inj6169_InstanceProvider = new InstanceProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6169_InstanceProvider);
      return inj6169_InstanceProvider;
    }
  };
  private final InstanceProvider inj6169_InstanceProvider = inj6186_InstanceProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicRenderedPresenter> inj6188_TopicRenderedPresenter_creationalCallback = new CreationalCallback<TopicRenderedPresenter>() {
    public TopicRenderedPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicRenderedPresenter inj6187_TopicRenderedPresenter = new TopicRenderedPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6187_TopicRenderedPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6187_TopicRenderedPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6187_TopicRenderedPresenter;
    }
  };
  private final CreationalCallback<EventProvider> inj6189_EventProvider_creationalCallback = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      Class beanType = EventProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final EventProvider inj6165_EventProvider = new EventProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6165_EventProvider);
      return inj6165_EventProvider;
    }
  };
  private final EventProvider inj6165_EventProvider = inj6189_EventProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj6190_MessageBusProvider_creationalCallback = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      Class beanType = MessageBusProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final MessageBusProvider inj6161_MessageBusProvider = new MessageBusProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6161_MessageBusProvider);
      return inj6161_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj6161_MessageBusProvider = inj6190_MessageBusProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicXMLErrorsView> inj6193_TopicXMLErrorsView_creationalCallback = new CreationalCallback<TopicXMLErrorsView>() {
    public TopicXMLErrorsView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicXMLErrorsView inj6040_TopicXMLErrorsView = new TopicXMLErrorsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6040_TopicXMLErrorsView);
      return inj6040_TopicXMLErrorsView;
    }
  };
  private final CreationalCallback<TopicXMLErrorsPresenter> inj6192_TopicXMLErrorsPresenter_creationalCallback = new CreationalCallback<TopicXMLErrorsPresenter>() {
    public TopicXMLErrorsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLErrorsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicXMLErrorsPresenter inj6191_TopicXMLErrorsPresenter = new TopicXMLErrorsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6191_TopicXMLErrorsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicXMLErrorsPresenter_display(inj6191_TopicXMLErrorsPresenter, inj6193_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6191_TopicXMLErrorsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6191_TopicXMLErrorsPresenter;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImageView> inj6196_ImagesFilteredResultsAndImageView_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImageView>() {
    public ImagesFilteredResultsAndImageView getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImagesFilteredResultsAndImageView inj6053_ImagesFilteredResultsAndImageView = new ImagesFilteredResultsAndImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6053_ImagesFilteredResultsAndImageView);
      return inj6053_ImagesFilteredResultsAndImageView;
    }
  };
  private final CreationalCallback<ImageFilteredResultsView> inj6197_ImageFilteredResultsView_creationalCallback = new CreationalCallback<ImageFilteredResultsView>() {
    public ImageFilteredResultsView getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImageFilteredResultsView inj6046_ImageFilteredResultsView = new ImageFilteredResultsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6046_ImageFilteredResultsView);
      return inj6046_ImageFilteredResultsView;
    }
  };
  private final CreationalCallback<ImageView> inj6198_ImageView_creationalCallback = new CreationalCallback<ImageView>() {
    public ImageView getInstance(final CreationalContext context) {
      Class beanType = ImageView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImageView inj6041_ImageView = new ImageView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6041_ImageView);
      return inj6041_ImageView;
    }
  };
  private final CreationalCallback<ImagesFilteredResultsAndImagePresenter> inj6195_ImagesFilteredResultsAndImagePresenter_creationalCallback = new CreationalCallback<ImagesFilteredResultsAndImagePresenter>() {
    public ImagesFilteredResultsAndImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagesFilteredResultsAndImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImagesFilteredResultsAndImagePresenter inj6194_ImagesFilteredResultsAndImagePresenter = new ImagesFilteredResultsAndImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6194_ImagesFilteredResultsAndImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_display(inj6194_ImagesFilteredResultsAndImagePresenter, inj6196_ImagesFilteredResultsAndImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(inj6194_ImagesFilteredResultsAndImagePresenter, inj6197_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageDisplay(inj6194_ImagesFilteredResultsAndImagePresenter, inj6198_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6194_ImagesFilteredResultsAndImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6194_ImagesFilteredResultsAndImagePresenter;
    }
  };
  private final CreationalCallback<TopicBugsPresenter> inj6200_TopicBugsPresenter_creationalCallback = new CreationalCallback<TopicBugsPresenter>() {
    public TopicBugsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicBugsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicBugsPresenter inj6199_TopicBugsPresenter = new TopicBugsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6199_TopicBugsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6199_TopicBugsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6199_TopicBugsPresenter;
    }
  };
  private final CreationalCallback<SenderProvider> inj6201_SenderProvider_creationalCallback = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      Class beanType = SenderProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SenderProvider inj6167_SenderProvider = new SenderProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6167_SenderProvider);
      return inj6167_SenderProvider;
    }
  };
  private final SenderProvider inj6167_SenderProvider = inj6201_SenderProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj6202_InitBallotProvider_creationalCallback = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      Class beanType = InitBallotProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final InitBallotProvider inj6159_InitBallotProvider = new InitBallotProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6159_InitBallotProvider);
      return inj6159_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj6159_InitBallotProvider = inj6202_InitBallotProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicRevisionsPresenter> inj6204_TopicRevisionsPresenter_creationalCallback = new CreationalCallback<TopicRevisionsPresenter>() {
    public TopicRevisionsPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicRevisionsPresenter inj6203_TopicRevisionsPresenter = new TopicRevisionsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6203_TopicRevisionsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6203_TopicRevisionsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6203_TopicRevisionsPresenter;
    }
  };
  private final CreationalCallback<TopicXMLPresenter> inj6206_TopicXMLPresenter_creationalCallback = new CreationalCallback<TopicXMLPresenter>() {
    public TopicXMLPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicXMLPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicXMLPresenter inj6205_TopicXMLPresenter = new TopicXMLPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6205_TopicXMLPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6205_TopicXMLPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6205_TopicXMLPresenter;
    }
  };
  private final CreationalCallback<ImageFilteredResultsPresenter> inj6208_ImageFilteredResultsPresenter_creationalCallback = new CreationalCallback<ImageFilteredResultsPresenter>() {
    public ImageFilteredResultsPresenter getInstance(final CreationalContext context) {
      Class beanType = ImageFilteredResultsPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImageFilteredResultsPresenter inj6207_ImageFilteredResultsPresenter = new ImageFilteredResultsPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6207_ImageFilteredResultsPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImageFilteredResultsPresenter_display(inj6207_ImageFilteredResultsPresenter, inj6197_ImageFilteredResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6207_ImageFilteredResultsPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6207_ImageFilteredResultsPresenter;
    }
  };
  private final CreationalCallback<CallerProvider> inj6209_CallerProvider_creationalCallback = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      Class beanType = CallerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final CallerProvider inj6151_CallerProvider = new CallerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6151_CallerProvider);
      return inj6151_CallerProvider;
    }
  };
  private final CallerProvider inj6151_CallerProvider = inj6209_CallerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj6210_RootPanelProvider_creationalCallback = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      Class beanType = RootPanelProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final RootPanelProvider inj6157_RootPanelProvider = new RootPanelProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6157_RootPanelProvider);
      return inj6157_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj6157_RootPanelProvider = inj6210_RootPanelProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<WelcomeView> inj6213_WelcomeView_creationalCallback = new CreationalCallback<WelcomeView>() {
    public WelcomeView getInstance(final CreationalContext context) {
      Class beanType = WelcomeView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final WelcomeView inj6045_WelcomeView = new WelcomeView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6045_WelcomeView);
      return inj6045_WelcomeView;
    }
  };
  private final CreationalCallback<WelcomePresenter> inj6212_WelcomePresenter_creationalCallback = new CreationalCallback<WelcomePresenter>() {
    public WelcomePresenter getInstance(final CreationalContext context) {
      Class beanType = WelcomePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final WelcomePresenter inj6211_WelcomePresenter = new WelcomePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6211_WelcomePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(inj6211_WelcomePresenter, inj6213_WelcomeView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6211_WelcomePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6211_WelcomePresenter;
    }
  };
  private final CreationalCallback<ImagePresenter> inj6215_ImagePresenter_creationalCallback = new CreationalCallback<ImagePresenter>() {
    public ImagePresenter getInstance(final CreationalContext context) {
      Class beanType = ImagePresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final ImagePresenter inj6214_ImagePresenter = new ImagePresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6214_ImagePresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_ImagePresenter_display(inj6214_ImagePresenter, inj6198_ImageView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6214_ImagePresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6214_ImagePresenter;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicView> inj6218_SearchResultsAndTopicView_creationalCallback = new CreationalCallback<SearchResultsAndTopicView>() {
    public SearchResultsAndTopicView getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchResultsAndTopicView inj6049_SearchResultsAndTopicView = new SearchResultsAndTopicView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6049_SearchResultsAndTopicView);
      return inj6049_SearchResultsAndTopicView;
    }
  };
  private final CreationalCallback<TopicXMLView> inj6219_TopicXMLView_creationalCallback = new CreationalCallback<TopicXMLView>() {
    public TopicXMLView getInstance(final CreationalContext context) {
      Class beanType = TopicXMLView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicXMLView inj6043_TopicXMLView = new TopicXMLView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6043_TopicXMLView);
      return inj6043_TopicXMLView;
    }
  };
  private final CreationalCallback<TopicRenderedView> inj6220_TopicRenderedView_creationalCallback = new CreationalCallback<TopicRenderedView>() {
    public TopicRenderedView getInstance(final CreationalContext context) {
      Class beanType = TopicRenderedView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicRenderedView inj6051_TopicRenderedView = new TopicRenderedView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6051_TopicRenderedView);
      return inj6051_TopicRenderedView;
    }
  };
  private final CreationalCallback<TopicBugsView> inj6221_TopicBugsView_creationalCallback = new CreationalCallback<TopicBugsView>() {
    public TopicBugsView getInstance(final CreationalContext context) {
      Class beanType = TopicBugsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicBugsView inj6052_TopicBugsView = new TopicBugsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6052_TopicBugsView);
      return inj6052_TopicBugsView;
    }
  };
  private final CreationalCallback<TopicRevisionsView> inj6222_TopicRevisionsView_creationalCallback = new CreationalCallback<TopicRevisionsView>() {
    public TopicRevisionsView getInstance(final CreationalContext context) {
      Class beanType = TopicRevisionsView.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicRevisionsView inj6050_TopicRevisionsView = new TopicRevisionsView();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6050_TopicRevisionsView);
      return inj6050_TopicRevisionsView;
    }
  };
  private final CreationalCallback<SearchResultsAndTopicPresenter> inj6217_SearchResultsAndTopicPresenter_creationalCallback = new CreationalCallback<SearchResultsAndTopicPresenter>() {
    public SearchResultsAndTopicPresenter getInstance(final CreationalContext context) {
      Class beanType = SearchResultsAndTopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final SearchResultsAndTopicPresenter inj6216_SearchResultsAndTopicPresenter = new SearchResultsAndTopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6216_SearchResultsAndTopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_display(inj6216_SearchResultsAndTopicPresenter, inj6218_SearchResultsAndTopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(inj6216_SearchResultsAndTopicPresenter, inj6181_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(inj6216_SearchResultsAndTopicPresenter, inj6219_TopicXMLView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(inj6216_SearchResultsAndTopicPresenter, inj6220_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(inj6216_SearchResultsAndTopicPresenter, inj6220_TopicRenderedView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(inj6216_SearchResultsAndTopicPresenter, inj6180_SearchResultsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(inj6216_SearchResultsAndTopicPresenter, inj6193_TopicXMLErrorsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(inj6216_SearchResultsAndTopicPresenter, inj6185_TopicTagsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(inj6216_SearchResultsAndTopicPresenter, inj6221_TopicBugsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(inj6216_SearchResultsAndTopicPresenter, inj6222_TopicRevisionsView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6216_SearchResultsAndTopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6216_SearchResultsAndTopicPresenter;
    }
  };
  private final CreationalCallback<DisposerProvider> inj6223_DisposerProvider_creationalCallback = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      Class beanType = DisposerProvider.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final DisposerProvider inj6153_DisposerProvider = new DisposerProvider();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6153_DisposerProvider);
      org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(inj6153_DisposerProvider, inj6163_IOCBeanManagerProvider.get());
      return inj6153_DisposerProvider;
    }
  };
  private final DisposerProvider inj6153_DisposerProvider = inj6223_DisposerProvider_creationalCallback.getInstance(context);
  private final CreationalCallback<TopicPresenter> inj6225_TopicPresenter_creationalCallback = new CreationalCallback<TopicPresenter>() {
    public TopicPresenter getInstance(final CreationalContext context) {
      Class beanType = TopicPresenter.class;
      Annotation[] qualifiers = arrayOf_java_lang_annotation_Annotation_30006858;
      final TopicPresenter inj6224_TopicPresenter = new TopicPresenter();
      BeanRef beanRef = context.getBeanReference(beanType, qualifiers);
      context.addBean(beanRef, inj6224_TopicPresenter);
      org_jboss_pressgangccms_client_local_mvp_presenter_TopicPresenter_display(inj6224_TopicPresenter, inj6181_TopicView_creationalCallback.getInstance(context));
      org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(inj6224_TopicPresenter, org_jboss_pressgangccms_client_local_App_produceEventBus(inj4793_App));
      return inj6224_TopicPresenter;
    }
  };
  static class org_jboss_pressgangccms_client_local_AppController_inj6172_proxy extends AppController {
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
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj6170_IOCBeanManagerProvider_creationalCallback, inj6163_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj6170_IOCBeanManagerProvider_creationalCallback, inj6163_IOCBeanManagerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(App.class, App.class, inj6171_App_creationalCallback, inj4793_App, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(HandlerManager.class, HandlerManager.class, inj6149_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(HasHandlers.class, HandlerManager.class, inj6149_HandlerManager_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(AppController.class, AppController.class, inj6174_AppController_creationalCallback, inj6173_AppController, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Presenter.class, AppController.class, inj6174_AppController_creationalCallback, inj6173_AppController, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ValueChangeHandler.class, AppController.class, inj6174_AppController_creationalCallback, inj6173_AppController, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(EventHandler.class, AppController.class, inj6174_AppController_creationalCallback, inj6173_AppController, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchView.class, SearchView.class, inj6177_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Display.class, SearchView.class, inj6177_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchView.class, inj6177_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, SearchView.class, inj6177_SearchView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchPresenter.class, SearchPresenter.class, inj6176_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, SearchPresenter.class, inj6176_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, SearchPresenter.class, inj6176_SearchPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchResultsView.class, SearchResultsView.class, inj6180_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter.Display.class, SearchResultsView.class, inj6180_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsView.class, inj6180_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsView.class, inj6180_SearchResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicView.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicView.class, inj6181_TopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchResultsPresenter.class, SearchResultsPresenter.class, inj6179_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsPresenter.class, inj6179_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, SearchResultsPresenter.class, inj6179_SearchResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj6182_RequestDispatcherProvider_creationalCallback, inj6155_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj6182_RequestDispatcherProvider_creationalCallback, inj6155_RequestDispatcherProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicTagsView.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicTagsView.class, inj6185_TopicTagsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicTagsPresenter.class, TopicTagsPresenter.class, inj6184_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicTagsPresenter.class, inj6184_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicTagsPresenter.class, inj6184_TopicTagsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj6186_InstanceProvider_creationalCallback, inj6169_InstanceProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj6186_InstanceProvider_creationalCallback, inj6169_InstanceProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicRenderedPresenter.class, TopicRenderedPresenter.class, inj6188_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRenderedPresenter.class, inj6188_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicRenderedPresenter.class, inj6188_TopicRenderedPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj6189_EventProvider_creationalCallback, inj6165_EventProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj6189_EventProvider_creationalCallback, inj6165_EventProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj6190_MessageBusProvider_creationalCallback, inj6161_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj6190_MessageBusProvider_creationalCallback, inj6161_MessageBusProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicXMLErrorsView.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLErrorsView.class, inj6193_TopicXMLErrorsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicXMLErrorsPresenter.class, TopicXMLErrorsPresenter.class, inj6192_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLErrorsPresenter.class, inj6192_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicXMLErrorsPresenter.class, inj6192_TopicXMLErrorsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImagesFilteredResultsAndImageView.class, ImagesFilteredResultsAndImageView.class, inj6196_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter.Display.class, ImagesFilteredResultsAndImageView.class, inj6196_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImagesFilteredResultsAndImageView.class, inj6196_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, ImagesFilteredResultsAndImageView.class, inj6196_ImagesFilteredResultsAndImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImageFilteredResultsView.class, ImageFilteredResultsView.class, inj6197_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display.class, ImageFilteredResultsView.class, inj6197_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageFilteredResultsView.class, inj6197_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, ImageFilteredResultsView.class, inj6197_ImageFilteredResultsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImageView.class, ImageView.class, inj6198_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter.Display.class, ImageView.class, inj6198_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, ImageView.class, inj6198_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, ImageView.class, inj6198_ImageView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImagesFilteredResultsAndImagePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj6195_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, ImagesFilteredResultsAndImagePresenter.class, inj6195_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, ImagesFilteredResultsAndImagePresenter.class, inj6195_ImagesFilteredResultsAndImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicBugsPresenter.class, TopicBugsPresenter.class, inj6200_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicBugsPresenter.class, inj6200_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicBugsPresenter.class, inj6200_TopicBugsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj6201_SenderProvider_creationalCallback, inj6167_SenderProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj6201_SenderProvider_creationalCallback, inj6167_SenderProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj6202_InitBallotProvider_creationalCallback, inj6159_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj6202_InitBallotProvider_creationalCallback, inj6159_InitBallotProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicRevisionsPresenter.class, TopicRevisionsPresenter.class, inj6204_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicRevisionsPresenter.class, inj6204_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicRevisionsPresenter.class, inj6204_TopicRevisionsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicXMLPresenter.class, TopicXMLPresenter.class, inj6206_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicXMLPresenter.class, inj6206_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicXMLPresenter.class, inj6206_TopicXMLPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImageFilteredResultsPresenter.class, ImageFilteredResultsPresenter.class, inj6208_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, ImageFilteredResultsPresenter.class, inj6208_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, ImageFilteredResultsPresenter.class, inj6208_ImageFilteredResultsPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj6209_CallerProvider_creationalCallback, inj6151_CallerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj6209_CallerProvider_creationalCallback, inj6151_CallerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj6210_RootPanelProvider_creationalCallback, inj6157_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj6210_RootPanelProvider_creationalCallback, inj6157_RootPanelProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(WelcomeView.class, WelcomeView.class, inj6213_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display.class, WelcomeView.class, inj6213_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, WelcomeView.class, inj6213_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, WelcomeView.class, inj6213_WelcomeView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(WelcomePresenter.class, WelcomePresenter.class, inj6212_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, WelcomePresenter.class, inj6212_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, WelcomePresenter.class, inj6212_WelcomePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(ImagePresenter.class, ImagePresenter.class, inj6215_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, ImagePresenter.class, inj6215_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, ImagePresenter.class, inj6215_ImagePresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchResultsAndTopicView.class, SearchResultsAndTopicView.class, inj6218_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter.Display.class, SearchResultsAndTopicView.class, inj6218_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, SearchResultsAndTopicView.class, inj6218_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, SearchResultsAndTopicView.class, inj6218_SearchResultsAndTopicView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicXMLView.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter.Display.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicXMLView.class, inj6219_TopicXMLView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicRenderedView.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRenderedView.class, inj6220_TopicRenderedView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicBugsView.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter.Display.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicBugsView.class, inj6221_TopicBugsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicRevisionsView.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter.Display.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewInterface.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateViewInterface.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicViewBase.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(BaseTemplateView.class, TopicRevisionsView.class, inj6222_TopicRevisionsView_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(SearchResultsAndTopicPresenter.class, SearchResultsAndTopicPresenter.class, inj6217_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, SearchResultsAndTopicPresenter.class, inj6217_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, SearchResultsAndTopicPresenter.class, inj6217_SearchResultsAndTopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj6223_DisposerProvider_creationalCallback, inj6153_DisposerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj6223_DisposerProvider_creationalCallback, inj6153_DisposerProvider, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(TopicPresenter.class, TopicPresenter.class, inj6225_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, true);
    injContext.addBean(TemplatePresenter.class, TopicPresenter.class, inj6225_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
    injContext.addBean(Presenter.class, TopicPresenter.class, inj6225_TopicPresenter_creationalCallback, null, arrayOf_java_lang_annotation_Annotation_30006858, null, false);
  }

  private native static void org_jboss_errai_ioc_client_api_builtin_DisposerProvider_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicTagsPresenter_display(TopicTagsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_eventBus(AppController instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicXMLDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicSplitPanelRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicSplitPanelRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicViewDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_topicViewDisplay(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter::topicViewDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageFilteredResultsDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter::imageFilteredResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicPresenter_display(TopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsPresenter_display(SearchResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_base_TemplatePresenter_eventBus(TemplatePresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.base.TemplatePresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagePresenter_display(ImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_display(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRevisionsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRevisionsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicRevisionsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicRenderedDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicRenderedPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicRenderedDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImageFilteredResultsPresenter_display(ImageFilteredResultsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImageFilteredResultsPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_display(SearchPresenter instance, Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicBugsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicBugsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicBugsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_imageDisplay(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter::imageDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_searchResultsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::searchResultsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_WelcomePresenter_display(WelcomePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.WelcomePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchPresenter_eventBus(SearchPresenter instance, HandlerManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter::eventBus = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_App_appController(App instance, AppController value) /*-{
    instance.@org.jboss.pressgangccms.client.local.App::appController = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicTagsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicTagsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicTagsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_SearchResultsAndTopicPresenter_topicXMLErrorsDisplay(SearchResultsAndTopicPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter::topicXMLErrorsDisplay = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_AppController_manager(AppController instance, IOCBeanManager value) /*-{
    instance.@org.jboss.pressgangccms.client.local.AppController::manager = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_ImagesFilteredResultsAndImagePresenter_display(ImagesFilteredResultsAndImagePresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter::display = value;
  }-*/;

  private native static void org_jboss_pressgangccms_client_local_mvp_presenter_TopicXMLErrorsPresenter_display(TopicXMLErrorsPresenter instance, org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter.Display value) /*-{
    instance.@org.jboss.pressgangccms.client.local.mvp.presenter.TopicXMLErrorsPresenter::display = value;
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