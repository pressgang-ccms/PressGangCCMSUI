package org.jboss.pressgang.ccms.ui.client.local;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.CSSResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * The entry point to the GWT application.
 * @author Matthew Casperson
 */
@EntryPoint
public final class App {
    /** The Errai event bus. */
    private HandlerManager eventBus = new HandlerManager(null);

    /** The controller that handles the transitions between views. */
    @Inject
    private AppController appController;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    /**
     * Default constructor. Does nothing.
     */
    public App() {

    }


    /**
     * Called once GWT has initialized.
     */
    @AfterInitialization
    public void startApp() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(final Throwable ex) {
                ex.printStackTrace();
                LOGGER.log(Level.SEVERE, ex.getMessage());
                Window.alert("Uncaught exception was detected. Redirecting you to the home page.\nException: "
                        + ex.getMessage());
                History.newItem(WelcomePresenter.HISTORY_TOKEN);
            }
        });

        /* Setup the REST client */
        RestClient.setApplicationRoot(Constants.REST_SERVER);
        RestClient.setJacksonMarshallingActive(true);

        final RootLayoutPanel root = RootLayoutPanel.get();

        /* Inject the CSS file */
        CSSResources.INSTANCE.appCss().ensureInjected();

        this.appController.go(root);
    }

    @Produces
    private HandlerManager produceEventBus() {
        return this.eventBus;
    }
}
