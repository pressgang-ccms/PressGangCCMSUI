/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The entry point to the GWT application.
 *
 * @author Matthew Casperson
 */
@EntryPoint
public class App {
    /**
     * The Errai event bus.
     */
    @NotNull
    private static final EventBus eventBus = new SimpleEventBus();

    /**
     * The controller that handles the transitions between views.
     */
    @Inject
    private AppController appController;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static final GWT.UncaughtExceptionHandler uncaughtExceptionHandler = new GWT.UncaughtExceptionHandler() {
        @Override
        public void onUncaughtException(@NotNull final Throwable ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            LOGGER.log(Level.SEVERE, GWTUtilities.convertExceptionStackToString(ex));
            Window.alert("Uncaught exception was detected. Redirecting you to the home page.\nException: " + ex.getMessage());
            History.newItem(WelcomePresenter.HISTORY_TOKEN);
        }
    };

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
        try {
            LOGGER.log(Level.INFO, "ENTER App.startApp()");

            GWT.setUncaughtExceptionHandler(uncaughtExceptionHandler);

            /* Load the DTD file used for validation and rendering */
            DocBookDTD.loadDtd();

            /* Setup the REST client */
            ServerDetails.getSavedServer(new ServerDetailsCallback() {
                @Override
                public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                    RestClient.setApplicationRoot(serverDetails.getRestEndpoint());

                    RestClient.setJacksonMarshallingActive(true);

                    final RootLayoutPanel root = RootLayoutPanel.get();

                    appController.initAndGo(root);
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT App.startApp()");
        }
    }

    @NotNull
    @Produces
    private EventBus produceEventBus() {
        return this.eventBus;
    }
}
