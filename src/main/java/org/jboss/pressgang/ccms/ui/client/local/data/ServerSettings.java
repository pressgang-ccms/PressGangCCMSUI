package org.jboss.pressgang.ccms.ui.client.local.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTServerEntitiesV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.ServerSettingsReceived;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

@ApplicationScoped
public class ServerSettings {
    private RESTServerSettingsV1 serverSettings;
    private boolean initialised = false;

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ServerSettings.class.getName());

    @Inject
    private FailOverRESTCall failOverRESTCall;
    @Inject
    private EventBus eventBus;

    public ServerSettings() {
    }

    public void loadSettings() {
        if (initialised == true) {
            return;
        }
        try {
            LOGGER.log(Level.INFO, "ENTER ServerSettings.loadSettings()");

            failOverRESTCall.performRESTCall(
                    FailOverRESTCallDatabase.getServerSettings(),
                    new RESTCallBack<RESTServerSettingsV1>() {
                        public void success(@NotNull final RESTServerSettingsV1 value) {
                            serverSettings = value;
                            initialised = true;

                            eventBus.fireEvent(new ServerSettingsReceived(value));
                        }

                        public void failed() {
                            eventBus.fireEvent(new ServerSettingsReceived(null));
                        }
                    },
                    null,
                    true
            );
        } finally {
            LOGGER.log(Level.INFO, "EXIT ServerSettings.loadSettings()");
        }
    }

    public RESTServerSettingsV1 getSettings() {
        return serverSettings;
    }

    public RESTServerEntitiesV1 getEntities() {
        return serverSettings == null ? null : serverSettings.getEntities();
    }
}
