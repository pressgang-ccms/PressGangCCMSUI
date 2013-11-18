package org.jboss.pressgang.ccms.ui.client.local.server;

import java.util.*;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jetbrains.annotations.NotNull;

/**
 * Contains the details for the various servers that the UI can connect to.
 */
public class ServerDetails {

    private static final String SERVER_ID = "serverId";
    private static final String SERVER_NAME = "serverName";
    private static final String SERVER_GROUP = "serverGroup";
    private static final String REST_URL = "restUrl";
    private static final String REPORT_URL = "reportUrl";
    private static final String MONITORING_URL = "monitoringUrl";
    private static final String READONLY = "readOnly";

    private static final Map<String, ServerGroup> serverGroups = new HashMap<String, ServerGroup>();
    private static final Map<Integer, ServerDetails> currentServers = new HashMap<Integer, ServerDetails>();
    private static ServerDetails currentServer = null;

    public static Map<Integer, ServerDetails> getCurrentServers() {
        return currentServers;
    }

    /**
     *
     * @return The saved server details.
     */
    public static ServerDetails getSavedServer() {

        if (currentServer == null) {
            // first attempt to load the settings from the server
            loadFromServer();
            if (currentServer == null) {
                // then attempt to load the settings from the local storage
                loadFromLocalStorage();
                if (currentServer == null) {
                    // as a last resort, assume some defaults and use them
                    currentServer = new ServerDetails(1, "Default", "/pressgang-ccms/rest", "/birt", "/pressgang-ccms/monitoring", new ServerGroup("Default"), false);;
                }
            }
        }


        return currentServer;
    }

    private static void loadFromLocalStorage() {
        final String json = Preferences.INSTANCE.getString(Preferences.SERVER_DETAILS, null);
        if (json != null) {
            parseJSONFile(json);
        }
    }

    private static void loadFromServer() {
        // First attempt to read the config file from the server
        final String url = "/pressgang-ccms-config/servers.json";
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        try {
            builder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(@NotNull final Request req, @NotNull final Response resp) {
                    final String text = resp.getText();

                    // save these servers for future reference
                    Preferences.INSTANCE.saveSetting(Preferences.SERVER_DETAILS, text);

                    parseJSONFile(text);
                }

                @Override
                public void onError(@NotNull final Request res, @NotNull final Throwable throwable) {

                }
            });
        } catch (@NotNull final RequestException e) {

        }
    }

    private static void parseJSONFile(@NotNull final String json) {
        final JSONArray serverDetails = JSONParser.parseStrict(json).isArray();

        if (serverDetails != null) {

            serverGroups.clear();
            currentServers.clear();

            for (int serverDetailsIndex = 0, serverDetailsSize = serverDetails.size(); serverDetailsIndex < serverDetailsSize; ++serverDetailsIndex) {
                final JSONObject serverDetail = serverDetails.get(serverDetailsIndex).isObject();
                if (serverDetail != null &&
                        serverDetail.containsKey(SERVER_ID) && serverDetail.get(SERVER_ID).isNumber() != null &&
                        serverDetail.containsKey(SERVER_GROUP) && serverDetail.get(SERVER_GROUP).isString() != null &&
                        serverDetail.containsKey(SERVER_NAME) && serverDetail.get(SERVER_NAME).isString() != null &&
                        serverDetail.containsKey(REST_URL) && serverDetail.get(REST_URL).isString() != null &&
                        serverDetail.containsKey(REPORT_URL) && serverDetail.get(REPORT_URL).isString() != null&&
                        serverDetail.containsKey(MONITORING_URL) && serverDetail.get(MONITORING_URL).isString() != null &&
                        serverDetail.containsKey(READONLY) && serverDetail.get(MONITORING_URL).isBoolean() != null) {

                    final int serverId = (int)serverDetail.get(SERVER_ID).isNumber().doubleValue();
                    final String serverGroup = serverDetail.get(SERVER_GROUP).isString().stringValue();
                    final String serverName = serverDetail.get(SERVER_NAME).isString().stringValue();
                    final String restUrl = serverDetail.get(REST_URL).isString().stringValue();
                    final String reportUrl = serverDetail.get(REPORT_URL).isString().stringValue();
                    final String monitoringUrl = serverDetail.get(MONITORING_URL).isString().stringValue();
                    final boolean readOnly = serverDetail.get(READONLY).isBoolean().booleanValue();

                    if (!serverGroups.containsKey(serverGroup)) {
                        serverGroups.put(serverGroup, new ServerGroup(serverGroup));
                    }

                    final ServerDetails newServerDetails = new ServerDetails(serverId, serverName, restUrl, reportUrl, monitoringUrl, serverGroups.get(serverGroup), readOnly);
                    currentServers.put(serverId, newServerDetails);
                }
            }

            final Integer selectedServerId = Preferences.INSTANCE.getInt(Preferences.SERVER, null);
            if (selectedServerId != null && currentServers.containsKey(selectedServerId)) {
                currentServer = currentServers.get(selectedServerId);
            } else {
                currentServer = currentServers.values().iterator().next();
            }
        }
    }

    private final int id;
    private final String name;
    private final String restUrl;
    private final String reportUrl;
    private final String monitoringUrl;
    private final ServerGroup group;
    private final boolean readOnly;

    /**
     * Define the server urls.
     * @param restUrl The REST base URL.
     * @param reportUrl The reporting base URL.
     * @param monitoringUrl The monitoring URL.
     * @para serverType The type of the server.
     */
    public ServerDetails(final int id, @NotNull final String name, @NotNull final String restUrl,
                         @NotNull final String reportUrl, @NotNull final String monitoringUrl,
                         @NotNull final ServerGroup group, final boolean readOnly) {
        this.id = id;
        this.name = name;
        this.restUrl = restUrl;
        this.reportUrl = reportUrl;
        this.monitoringUrl = monitoringUrl;
        this.group = group;
        group.addServer(this);
        this.readOnly = readOnly;
    }


    /**
     *
     * @return The REST base URL.
     */
    @NotNull
    public String getRestUrl() {
        return restUrl;
    }

    /**
     *
     * @return The reporting base URL.
     */
    @NotNull
    public String getReportUrl() {
        return reportUrl;
    }

    /**
     *
     * @return The monitoring URL.
     */
    @NotNull
    public String getMonitoringUrl() {
        return monitoringUrl;
    }

    @NotNull
    public ServerGroup getGroup() {
        return group;
    }

    /**
     *
     * @return The rest endpoint url.
     */
    @NotNull
    public String getRestEndpoint() {
        return restUrl + "/rest";
    }

    /**
     *
     * @return The ID that identifies this server.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return The name of the server.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     *
     * @return Whether the server is readonly or not.
     */
    public boolean isReadOnly() {
        return readOnly;
    }
}
