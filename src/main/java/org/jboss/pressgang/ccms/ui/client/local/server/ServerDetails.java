package org.jboss.pressgang.ccms.ui.client.local.server;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jetbrains.annotations.NotNull;

/**
 * Contains the details for the various servers that the UI can connect to.
 */
public class ServerDetails {
    /**
     * The production server in Brisbane.
     */
    private static final  ServerDetails BNE_PRODUCTION = new  ServerDetails(1, "Brisbane Production", "http://topika.ecs.eng.bne.redhat.com:8080/pressgang-ccms", "http://skynet.usersys.redhat.com:8080/birt/", "http://skynet.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.Production.name());
    /**
     * The development server in Brisbane.
     */
    private static final  ServerDetails BNE_DEVELOPMENT = new  ServerDetails(2, "Brisbane Development", "http://topicindex-dev.ecs.eng.bne.redhat.com:8080/pressgang-ccms", "http://skynet-dev.usersys.redhat.com:8080/birt/", "http://skynet-dev.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.Development.name());
    /**
     * A local server.
     */
    private static final  ServerDetails LOCAL = new  ServerDetails(3, "Local", "http://localhost:8080/pressgang-ccms", "http://localhost:8080/birt/", "http://localhost:8080/pressgang-ccms/monitoring", ServerTypes.Local.name());

    /**
     * A HA test server.
     */
    private static final  ServerDetails HATEST1 = new  ServerDetails(4, "HA Test Server 1", "http://ecs-cloud-pg-test-1.usersys.redhat.com:8080/pressgang-ccms", null, "http://ecs-cloud-pg-test-1.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.High_Availability_Test.name());

    /**
     * A HA test server.
     */
    private static final  ServerDetails HATEST2 = new  ServerDetails(5, "HA Test Server 2", "http://ecs-cloud-pg-test-2.usersys.redhat.com:8080/pressgang-ccms", null, "http://ecs-cloud-pg-test-2.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.High_Availability_Test.name());

    /**
     * A HA test server.
     */
    private static final  ServerDetails HATEST3 = new  ServerDetails(6, "HA Test Server 3", "http://ecs-cloud-pg-test-3.usersys.redhat.com:8080/pressgang-ccms", null, "http://ecs-cloud-pg-test-3.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.High_Availability_Test.name());

    /**
     * A HA test server.
     */
    private static final  ServerDetails HATEST4 = new  ServerDetails(7, "HA Test Server 4", "http://local-pg-test-1.usersys.redhat.com:8080/pressgang-ccms", null, "http://local-pg-test-1.usersys.redhat.com:8080/pressgang-ccms/monitoring", ServerTypes.High_Availability_Test.name());

    //private static final  ServerDetails DEFAULT_OVERRIDE =  BNE_DEVELOPMENT;
    private static final  ServerDetails DEFAULT_OVERRIDE =  BNE_PRODUCTION;

    /**
     * A collection of all the available servers.
     */
    public static final ServerDetails[] DEFAULT_SERVERS = new ServerDetails[] {BNE_PRODUCTION/*, BNE_DEVELOPMENT, LOCAL, HATEST1, HATEST2, HATEST3, HATEST4*/};

    /**
     * This is the list we work from, which may be modified through the config page.
     */
    public static ServerDetails[] SERVERS = DEFAULT_SERVERS;

    /**
     *
     * @param id The ID of the server to return
     * @return The server details if the ID was found, and the default server otherwise.
     */
    @NotNull
    public static ServerDetails getServer(final int id) {
        for (final ServerDetails serverDetails : SERVERS) {
            if (serverDetails.id == id) {
                return serverDetails;
            }
        }

        return DEFAULT_OVERRIDE;
    }

    /**
     *
     * @return The saved server details.
     */
    public static ServerDetails getSavedServer() {
        return ServerDetails.getServer(Preferences.INSTANCE.getInt(Preferences.SERVER, Constants.DEFAULT_SERVER));
    }

    private final int id;
    private final String name;
    private final String restUrl;
    private final String reportUrl;
    private final String monitoringUrl;
    private final String serverType;

    /**
     * Define the server urls.
     * @param restUrl The REST base URL.
     * @param reportUrl The reporting base URL.
     * @param monitoringUrl The monitoring URL.
     * @para serverType The type of the server.
     */
    public ServerDetails(final int id, @NotNull final String name, @NotNull final String restUrl,
                         @NotNull final String reportUrl, @NotNull final String monitoringUrl,
                         @NotNull final String serverType) {
        this.id = id;
        this.name = name;
        this.restUrl = restUrl;
        this.reportUrl = reportUrl;
        this.monitoringUrl = monitoringUrl;
        this.serverType = serverType;
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
    public String getServerType() {
        return serverType;
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
}
