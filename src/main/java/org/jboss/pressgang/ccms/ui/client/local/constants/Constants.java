package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This class contains a number of constants used throughout the application.
 *
 * @author Matthew Casperson
 */
public final class Constants {
    /**
     * The UI Version - yyyymmddhhmm.
     */
    public static final String VERSION = "201303011532";

    /**
     * The sort order to apply to a newly added child.
     */
    public static final int NEW_CHILD_SORT_ORDER = 0;
    /**
     * The first sort order used when setting the sort order to an existing list.
     */
    public static final int CHILDREN_SORT_ORDER_START = 1;
    /**
     * The number of rows to "fast forward" by with the SimplePager.
     */
    public static final int FAST_FORWARD_ROWS = 100;
    /**
     * An ID that can not appear in the database.
     */
    public static final int NULL_ID = -1;
    /**
     * How long to wait before refreshing the rendered view (in milliseconds).
     */
    public static final int REFRESH_RATE = 1000;
    /**
     * How long to wait before refreshing the rendered view (in milliseconds).
     */
    public static final int REFRESH_RATE_WTH_IMAGES = REFRESH_RATE * 5;
    /**
     * The name of a javascript click event.
     */
    public static final String JAVASCRIPT_CLICK_EVENT = "click";
    /**
     * All path segments that define a query must start with this string.
     */
    public static final String QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON = "query";
    /**
     * All path segments that define a query must start with this string.
     */
    public static final String QUERY_PATH_SEGMENT_PREFIX = QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + ";";
    /**
     * All path segments that define a query must start with this string.
     */
    public static final String CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON = "create";
    /**
     * All path segments that define a query must start with this string.
     */
    public static final String CREATE_PATH_SEGMENT_PREFIX = CREATE_PATH_SEGMENT_PREFIX_WO_SEMICOLON + ";";
    /**
     * The size of the split panels.
     */
    public static final int SPLIT_PANEL_SIZE = 300;
    /**
     * The height of the header banner in the template.
     */
    public static final int HEADING_BANNER_HEIGHT = 55;
    /**
     * The size of the split panel dividers.
     */
    public static final int SPLIT_PANEL_DIVIDER_SIZE = 5;
    /**
     * The size of the page title bar, in EM.
     */
    public static final int PAGE_TITLE_BAR_HEIGHT = 4;
    /**
     * The height of the action bars.
     */
    public static final int ACTION_BAR_HEIGHT = 80;
    /**
     * The height of the spacer to add to the shortcut bar to make up for the loss of the action bar.
     * This value is ACTION_BAR_HEIGHT - (border spacing * 2)
     */
    public static final int SHORTCUT_BAR_SPACER_HEIGHT = 76;
    /**
     * The width of the shortcut bar.
     */
    public static final int SHORTCUT_BAR_WIDTH = 120;
    /**
     * The height of the footer.
     */
    public static final int FOOTER_HEIGHT = 16;
    /**
     * The maximum number of results to return in a search result.
     */
    public static final int MAX_SEARCH_RESULTS = 15;
    /**
     * The BIRT URL.
     */
    public static final String BIRT_URL = "http://skynet.usersys.redhat.com:8080/birt/";
    //public static final String BIRT_URL =  "http://skynet-dev.usersys.redhat.com:8080/birt/";
    /**
     * The report that run a report with no other options.
     */
    public static final String BIRT_RUN_REPORT = "run?__report=";
    /**
     * The report that displays the topics bugzilla bugs.
     */
    public static final String BIRT_TOPIC_BUGZILLA_REPORT = "General/Bugs_Per_Topic.rptdesign&ShowOnlyData=True&AlternateTableHeaderBackgroundColour=True&TopicIDParameter=";
    /**
     * The JavaMelody URL
     */
    public static final String MONITORING_URL = "https://skynet.usersys.redhat.com:8443/TopicIndex/monitoring";
    /**
     * A link to a locally hosted REST server.
     */
    public static final String LOCAL_REST_SERVER = "http://localhost:8080/TopicIndex/";
    /**
     * The dev server.
     */
    public static final String DEV_REST_SERVER = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/";
    /**
     * The production server.
     */
    public static final String PROD_REST_SERVER = "http://skynet.usersys.redhat.com:8080/TopicIndex/";
    /**
     * The REST server.
     */
    //public static final String BASE_URL = LOCAL_REST_SERVER;
    public static final String BASE_URL = DEV_REST_SERVER;
    //public static final String BASE_URL = PROD_REST_SERVER;
    /**
     * The newer versions of the REST interface encode the query PathParam elements (so as to allow for searches to
     * include characters like the semicolon). If this constant is true, the query elements will be encoded.
     */
    //public static final boolean ENCODE_QUERY_OPTIONS = false;
    public static final boolean ENCODE_QUERY_OPTIONS = true;
    /**
     * The REST URL.
     */
    public static final String REST_SERVER = BASE_URL + "seam/resource/rest";
    /**
     * The base URL to Bugzilla.
     */
    public static final String BUGZILLA_BASE_URL = "https://bugzilla.redhat.com";
    /**
     * The Bugzilla link for this product.
     */
    public static final String BUGZILLA_URL = BUGZILLA_BASE_URL + "/enter_bug.cgi?product=PressGang CCMS&component=Web-UI&version=1.1&cf_build_id=UI%20Build%20" + VERSION;
    /**
     * A link to the survey.
     */
    public static final String KEY_SURVEY_LINK = "http://www.keysurvey.com/f/457744/149e/?LQID=1&source=";
    /**
     * The start of the url for the documentation of Docbook XML elements.
     */
    public static final String DOCBOOK_ELEMENT_URL_PREFIX = "http://www.docbook.org/tdg5/en/html/";
    /**
     * The end of the url for the documentation of Docbook XML elements.
     */
    public static final String DOCBOOK_ELEMENT_URL_POSTFIX = ".html";
    /**
     * The width of the help dialog box.
     */
    public static final String HELP_DIALOG_WIDTH = "640px";
    /**
     * The height of the help dialog box.
     */
    public static final String HELP_DIALOG_HEIGHT = "480px";
    /**
     * A line break escaped.
     */
    public static final String LINE_BREAK_ESCAPED = "\\n";
    /**
     * A carriage return and line break escaped.
     */
    public static final String CARRIAGE_RETURN_AND_LINE_BREAK_ESCAPED = "\\r\\n";
    /**
     * A line break.
     */
    public static final String LINE_BREAK = "\n";
    /**
     * A carriage return and line break.
     */
    public static final String CARRIAGE_RETURN_AND_LINE_BREAK = "\r\n";
    /**
     * A comma.
     */
    public static final String COMMA = ",";
    /**
     * The category internal "and" logic to apply when searching. The "or" logic is !DEFAULT_INTERNAL_AND_LOGIC
     */
    public static final boolean DEFAULT_INTERNAL_AND_LOGIC = false;
    /**
     * The category external "and" category logic to apply when searching. The "or" logic is !DEFAULT_EXTERNAL_AND_LOGIC
     */
    public static final boolean DEFAULT_EXTERNAL_AND_LOGIC = true;
    /**
     * This is the value for the category logic option "and", as passed by the query strings.
     */
    public static final String AND_LOGIC_QUERY_STRING_VALUE = "And";
    /**
     * This is the value for the category logic option "or", as passed by the query strings.
     */
    public static final String OR_LOGIC_QUERY_STRING_VALUE = "Or";

    /**
     * A private constructor to prevent instantiation.
     */
    private Constants() {

    }
}

