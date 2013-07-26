package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.config;

import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
    This presenter is a debug page used to override the configuration of the app
    via the token. End users won't have a need to use this page.
 */
@Dependent
public class ConfigPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "ConfigView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ConfigPresenter.class.getName());

    @Inject
    private Display display;

    private String jsonConfig;

    @Override
    public void parseToken(@NotNull final String historyToken) {
        jsonConfig = URL.decodeQueryString(removeHistoryToken(historyToken, HISTORY_TOKEN));
        LOGGER.info(jsonConfig);
    }

    @Override
    public void bindExtended() {
        super.bind(display);

        try {
            final JSONObject config = JSONParser.parseStrict(jsonConfig).isObject();

            if (config != null) {
                if (config.containsKey("servers")) {
                    final JSONArray servers = config.get("servers").isArray();
                    if (servers != null) {

                        final List<ServerDetails> serverDetailsList = new ArrayList<ServerDetails>();

                        for (int serverIndex = 0; serverIndex < servers.size(); ++serverIndex) {
                            final JSONObject serverDetails = servers.get(serverIndex).isObject();
                            if (serverDetails != null) {
                                JSONNumber id = null;
                                JSONString name = null;
                                JSONString restUrl = null;
                                JSONString reportUrl = null;
                                JSONString monitoringUrl = null;
                                JSONString serverType = null;
                                JSONBoolean defaultServerFlag = null;

                                if (serverDetails.containsKey("id")) {
                                    id = serverDetails.get("id").isNumber();
                                }

                                if (serverDetails.containsKey("name")) {
                                    name = serverDetails.get("name").isString();
                                }

                                if (serverDetails.containsKey("restUrl")) {
                                    restUrl = serverDetails.get("restUrl").isString();
                                }

                                if (serverDetails.containsKey("reportUrl")) {
                                    reportUrl = serverDetails.get("reportUrl").isString();
                                }

                                if (serverDetails.containsKey("monitoringUrl")) {
                                    monitoringUrl = serverDetails.get("monitoringUrl").isString();
                                }

                                if (serverDetails.containsKey("serverType")) {
                                    serverType = serverDetails.get("serverType").isString();
                                }

                                if (serverDetails.containsKey("defaultServerFlag")) {
                                    defaultServerFlag = serverDetails.get("defaultServerFlag").isBoolean();
                                }


                                if (id != null && name != null && restUrl != null && monitoringUrl != null && serverType != null) {
                                    LOGGER.info("Found server config:\n" +
                                        "\tID:" + id.doubleValue() + "\n" +
                                        "\tName:" + name.stringValue() + "\n" +
                                        "\tREST URL:" + restUrl.stringValue() + "\n" +
                                        "\tReport URL:" + (reportUrl == null ? null : reportUrl.stringValue()) + "\n" +
                                        "\tMonitoring URL:" + monitoringUrl.stringValue() + "\n" +
                                        "\tServer Type:" + serverType.stringValue() + "\n" +
                                        "\tDefault:" + serverType.stringValue());

                                    final ServerDetails newServerDetails = new ServerDetails(
                                            (int)id.doubleValue(),
                                            name.stringValue(),
                                            restUrl.stringValue(),
                                            reportUrl == null ? null : reportUrl.stringValue(),
                                            monitoringUrl.stringValue(),
                                            serverType.stringValue());
                                    serverDetailsList.add(newServerDetails);

                                    if (defaultServerFlag != null) {
                                        LOGGER.info("\tDefault:" + defaultServerFlag.booleanValue());
                                        if (defaultServerFlag.booleanValue()) {
                                            Preferences.INSTANCE.saveSetting(Preferences.SERVER, newServerDetails.getId());
                                        }
                                    }
                                }
                            }
                        }

                        if (serverDetailsList.size() != 0) {
                            ServerDetails.SERVERS = serverDetailsList.toArray(new ServerDetails[]{});
                            buildServersList();
                            saveServer(display.getServers().getValue(display.getServers().getSelectedIndex()));
                        }
                    }
                }
            }

        } catch (@NotNull final JSONException ex) {
            // do nothing if the supplied config is not valid
            LOGGER.info("Supplied config JSON object was not valid.");
        }

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public interface Display extends BaseTemplateViewInterface {

    }
}
