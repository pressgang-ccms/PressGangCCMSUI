package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.config;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerGroup;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerType;
import org.jetbrains.annotations.NotNull;

/**
    This presenter is a debug page used to override the configuration of the app
    via the token. End users won't have a need to use this page.

    When testing in cargo, you'll visit this page first to set the REST url to the
    local test server. e.g.

    #ConfigView;%7B%22servers%22%3A%5B%7B%22id%22%3A100%2C%22name%22%3A%22Cargo%20Server%22%2C%22restUrl%22%3A%22http%3A%2F%2Flocalhost%3A9898%2Fpressgang-ccms%22%2C%22reportUrl%22%3Anull%2C%22monitoringUrl%22%3A%22http%3A%2F%2Flocalhost%3A9898%2Fpressgang-ccms%2Fmonitoring%22%2C%22serverType%22%3A%22test%22%2C%22defaultServerFlag%22%3Atrue%7D%5D%7D
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
                                JSONString serverTypeName = null;
                                JSONBoolean defaultServerFlag = null;
                                JSONBoolean readOnly = null;

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
                                    serverTypeName = serverDetails.get("serverType").isString();
                                }

                                if (serverDetails.containsKey("defaultServerFlag")) {
                                    defaultServerFlag = serverDetails.get("defaultServerFlag").isBoolean();
                                }

                                if (serverDetails.containsKey("readOnly")) {
                                    readOnly = serverDetails.get("readOnly").isBoolean();
                                }

                                // Get the server type
                                final ServerType serverType = serverTypeName == null ? null : ServerType.valueOf(serverTypeName.stringValue());

                                if (id != null && name != null && restUrl != null && monitoringUrl != null && serverType != null) {
                                    LOGGER.info("Found server config:\n" +
                                        "\tID:" + id.doubleValue() + "\n" +
                                        "\tName:" + name.stringValue() + "\n" +
                                        "\tREST URL:" + restUrl.stringValue() + "\n" +
                                        "\tReport URL:" + (reportUrl == null ? null : reportUrl.stringValue()) + "\n" +
                                        "\tMonitoring URL:" + monitoringUrl.stringValue() + "\n" +
                                        "\tServer Type:" + serverTypeName.stringValue() + "\n" +
                                        "\tDefault:" + serverTypeName.stringValue());

                                    // Find the server group to add to, or create a new one.
                                    ServerGroup matchedServerGroup = null;
                                    for (final ServerGroup group : ServerDetails.GROUPS) {
                                        if (group.getType().equals(serverType)) {
                                            matchedServerGroup = group;
                                            break;
                                        }
                                    }
                                    if (matchedServerGroup == null) {
                                        matchedServerGroup = new ServerGroup(serverType);
                                    }

                                    final ServerDetails newServerDetails = new ServerDetails(
                                            (int)id.doubleValue(),
                                            name.stringValue(),
                                            restUrl.stringValue(),
                                            reportUrl == null ? null : reportUrl.stringValue(),
                                            monitoringUrl.stringValue(),
                                            matchedServerGroup,
                                            readOnly == null ? false : readOnly.booleanValue());
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
