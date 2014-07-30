/*
  Copyright 2011-2014 Red Hat, Inc

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

package org.jboss.pressgang.ccms.ui.client.local.server;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.AllServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * Contains the details for the various servers that the UI can connect to.
 *
 * These details can be loaded from a JSON file on the server at /pressgang-ccms-config/servers.json. The following is
 * an example of the format of this file.
 *
 * [
 *  {
 *     "serverId": 1,
 *     "serverName": "My Server",
 *     "serverGroup": "Default Servers",
 *     "restUrl": "http://myserver.com/pressgang-ccms",
 *     "reportUrl": "http://mybirtserver.com",
 *     "monitoringUrl": "http://myserver.com/pressgang-ccms/monitoring",
 *     "readOnly": false
 *  }
 * ]
 */
public class ServerDetails {

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ServerDetails.class.getName());

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

    public static void getCurrentServers(@NotNull final AllServerDetailsCallback allServerDetailsCallback) {
        getSavedServer(new ServerDetailsCallback() {
            @Override
            public void serverDetailsFound(@NotNull ServerDetails serverDetails) {
                allServerDetailsCallback.serverDetailsFound(currentServers);
            }
        });
    }

    /**
     * This looks like an async call, but in reality once it has been called once it is synchronous. This
     * method is called by App.startApp() when the application is first loaded, so all subsequent calls
     * will be synchronous.
     * @param serverDetailsCallback The callback to be called with the server details
     */
    public static void getSavedServer(@NotNull final ServerDetailsCallback serverDetailsCallback) {

        if (currentServer == null) {
            // first attempt to load the settings from the server
            loadFromServer(serverDetailsCallback);
        } else {
            final Integer selectedServerId = Preferences.INSTANCE.getInt(Preferences.SERVER, null);
            if (selectedServerId != null && currentServers.containsKey(selectedServerId)) {
                currentServer = currentServers.get(selectedServerId);
            } else if (currentServers.size() != 0) {
                currentServer = currentServers.values().iterator().next();
            }

            serverDetailsCallback.serverDetailsFound(currentServer);
        }
    }

    /**
     * If loading the server details from a JSON file hosted on the server didn't work, the
     * we fall back to whatever is in local storage, or the default settings.
     */
    private static void loadFallbackServerDetails(@NotNull final ServerDetailsCallback serverDetailsCallback) {
        // then attempt to load the settings from the local storage
        if (!loadFromLocalStorage(serverDetailsCallback)) {
            serverGroups.clear();
            currentServers.clear();

            // as a last resort, assume some defaults and use them
            final ServerGroup serverGroup = new ServerGroup("Default");
            serverGroups.put("Default", serverGroup);
            final String hostUrl = GWTUtilities.getLocalUrl();
            currentServer = new ServerDetails(1, "Default", hostUrl + "/pressgang-ccms", hostUrl + "/birt",
                    hostUrl + "/pressgang-ccms/monitoring", serverGroup, false);
            currentServers.put(currentServer.getId(), currentServer);

            serverDetailsCallback.serverDetailsFound(currentServer);
        }
    }

    private static boolean loadFromLocalStorage(@NotNull final ServerDetailsCallback serverDetailsCallback) {
        final String json = Preferences.INSTANCE.getString(Preferences.SERVER_DETAILS, null);
        if (json != null) {
            return parseJSONFile(json, serverDetailsCallback);
        }

        return false;
    }

    private static void loadFromServer(@NotNull final ServerDetailsCallback serverDetailsCallback) {
        // First attempt to read the config file from the server
        final String url = "/pressgang-ccms-config/servers.json";
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        try {
            builder.sendRequest(null, new RequestCallback() {

                @Override
                public void onResponseReceived(@NotNull final Request req, @NotNull final Response resp) {
                    final String text = resp.getText();

                    if (parseJSONFile(text, serverDetailsCallback)) {
                        // save these servers for future reference
                        Preferences.INSTANCE.saveSetting(Preferences.SERVER_DETAILS, text);
                    } else {
                        loadFallbackServerDetails(serverDetailsCallback);
                    }
                }

                @Override
                public void onError(@NotNull final Request res, @NotNull final Throwable throwable) {
                    loadFallbackServerDetails(serverDetailsCallback);
                }
            });
        } catch (@NotNull final RequestException e) {

        }
    }

    /**
     * Load the server details from the supplied JSON file.
     * @param json The server json file to parse
     * @return true if the json string defined at least one server, and that every server defined had all tye required fields.
     */
    private static boolean parseJSONFile(@NotNull final String json, @NotNull final ServerDetailsCallback serverDetailsCallback) {
        try {
            final JSONArray serverDetails = JSONParser.parseStrict(json).isArray();

            if (serverDetails != null && serverDetails.size() != 0) {

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
                            serverDetail.containsKey(READONLY) && serverDetail.get(READONLY).isBoolean() != null) {

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
                    } else {
                        LOGGER.log(Level.INFO, "The server defined in the JSON file did not have the required fields");
                        return false;
                    }
                }

                final Integer selectedServerId = Preferences.INSTANCE.getInt(Preferences.SERVER, null);
                if (selectedServerId != null && currentServers.containsKey(selectedServerId)) {
                    currentServer = currentServers.get(selectedServerId);
                } else if (currentServers.size() != 0) {
                    currentServer = currentServers.values().iterator().next();
                }

                serverDetailsCallback.serverDetailsFound(currentServer);

                return true;
            } else {
                return false;
            }
        } catch (@NotNull final Exception ex) {
            LOGGER.log(Level.INFO, "Could not parse:\n" +  json);
            return false;
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
