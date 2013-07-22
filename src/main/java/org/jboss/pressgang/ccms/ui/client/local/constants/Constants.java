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
    public static final String VERSION = "201307221657";

    /**
     * The size of the arrows in the help overlay callout
     */
    public static final int CALLOUT_ARROW_SIZE = 20;

    /**
     * The zindex of the widgets promoted above the help overlay dimmer
     */
    public static final Integer HELP_OVERLAY_ITEM_ZINDEX = 10001;

    /**
     * The JSON Key for a recently failed failed server id
     */
    public static final String FAILED_SERVER_ID = "FailedServerId";

    /**
     * The JSON Key for a recently failed failed server time
     */
    public static final String FAILED_SERVER_TIME = "FailedServerTime";

    /**
     * How long to remember recently failed servers in milliseconds
     */
    public static final int REMEMBER_RECENTLY_FAILED_SERVERS = 30000;

    /**
     * The time in milliseconds to wait for a rest call to complete.
     */
    public static final int REST_CALL_TIMEOUT = 30000;

    /**
     * This header is expected from all responses of the REST server
     */
    public static final String REST_SERVER_HEADER = "X-PressGang-Version";
    /**
     * This is some common text prefixed to any error response generated by the server. This
     * allows clients to distinguish between a generic error and something generated by Pressgang.
     */
    public static final String ERROR_TEXT_PREFIX = "PressGang Exception.";

    /**
     * The minimum size of the split size panels, to prevent them from being
     * resized to 0.
     */
    public static final int MINIMUM_SPLIT_SIZE = 100;

    /**
     * The query parameter to append to the echo xml endpoint, which will allow the returned XML
     * to communicate with the parent server across domains once it is parsed to HTML.
     */
    public static final String ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM = "parentDomain";

    /**
     * XML Mime type
     */
    public static final String XML_MIME_TYPE = "application/xml";

    /**
     * Plain Text Mime type
     */
    public static final String PLAIN_TEXT_MIME_TYPE = "text/plain";

    /**
     * The maximum length of the title before it is truncated
     */
    public static final int MAX_PAGE_TITLE_LENGTH = 70;

    /**
     * The prefix used to identify a topic's initial revision
     */
    public static final String REVISION_TOPIC_VIEW_DATA_PREFIX = "r:";

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
    public static final int REFRESH_RATE = 5000;
    /**
     * How long to wait before refreshing the rendered view with images (in milliseconds).
     */
    public static final int REFRESH_RATE_WTH_IMAGES = 10000;
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
     * This path segment defines the initial topic view state.
     */
    public static final String TOPIC_VIEW_DATA_PREFIX_WO_SEMICOLON = "topicViewData";
    /**
     * This path segment defines the initial topic view state.
     */
    public static final String TOPIC_VIEW_DATA_PREFIX = TOPIC_VIEW_DATA_PREFIX_WO_SEMICOLON + ";";
    /**
     * The deafult size of the xml error split panels.
     */
    public static final int XML_ERRORS_SPLIT_PANEL_SIZE = 64;
    /**
     * The size of the split panels.
     */
    public static final double SPLIT_PANEL_SIZE = 300;
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
    public static final int ACTION_BAR_HEIGHT = 92;
    /**
     * The height of the spacer to add to the shortcut bar to make up for the loss of the action bar.
     * This value is ACTION_BAR_HEIGHT - (border spacing * 2)
     */
    public static final int SHORTCUT_BAR_SPACER_HEIGHT = ACTION_BAR_HEIGHT - (2 * 2);
    /**
     * The width of the shortcut bar.
     */
    public static final int SHORTCUT_BAR_WIDTH = 120;
    /**
     * The height of the footer.
     */
    public static final int FOOTER_HEIGHT = 40;
    /**
     * The maximum number of results to return in a search result.
     */
    public static final int MAX_SEARCH_RESULTS = 15;
    /**
     * The ID of the default server, as defined in the ServerDetails.SERVERS collection.
     */
    public static final int DEFAULT_SERVER = 1;
    /**
     * The report that run a report with no other options.
     */
    public static final String BIRT_RUN_REPORT = "run?__report=";
    /**
     * The report that displays the topics bugzilla bugs.
     */
    public static final String BIRT_TOPIC_BUGZILLA_REPORT = "General/Bugs_Per_Topic.rptdesign&ShowOnlyData=True&AlternateTableHeaderBackgroundColour=True&TopicIDParameter=";
    /**
     * The DocBuilder server
     */
    public static final String DOCBUILDER_SERVER = "http://docbuilder.usersys.redhat.com";

    /**
     * Old versions of the rest interface did not accept encode text for queries. New versions do. Set to
     * true for the new REST API, and false for older versions.
     */
    public static final boolean ENCODE_QUERY_OPTIONS = true;

    /**
     * The reference to the XSL file, to be added to any XML being rendered by the browser
     */
    public static final String DOCBOOK_XSL_REFERENCE = "<?xml-stylesheet type='text/xsl' href='/pressgang-ccms-static/publican-docbook/html-single.xsl'?>";

    /**
     * The reference to the XSL file that will post back the rendered HTML, to be added to any XML being rendered by the browser
     */
    public static final String DOCBOOK_DIFF_XSL_REFERENCE = "<?xml-stylesheet type='text/xsl' href='/pressgang-ccms-static/publican-docbook/html-single-diff.xsl'?>";

    /**
     * The reference to the XSL file that will post back the rendered HTML, to be added to any XML being rendered by the browser
     */
    public static final String DOCBOOK_RENDER_ONLY_XSL_REFERENCE = "<?xml-stylesheet type='text/xsl' href='/pressgang-ccms-static/publican-docbook/html-single-renderonly.xsl'?>";

    /**
     * The reference to the XSL file with placeholder images, to be added to any XML being rendered by the browser
     */
    public static final String DOCBOOK_PLACEHOLDER_XSL_REFERENCE = "<?xml-stylesheet type='text/xsl' href='/pressgang-ccms-static/publican-docbook/html-single-placeholder.xsl'?>";
    /**
     * The REST endpoint that echos previously submitted xml
     */
    public static final String ECHO_ENDPOINT = "/1/echoxml";

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
     * The width of the bulk topic dialog box.
     */
    public static final String BULK_IMPORT_DIALOG_WIDTH = "800px";
    /**
     * The height of the bulk topic dialog box.
     */
    public static final String BULK_IMPORT_DIALOG_HEIGHT = "480px";
    /**
     * The width of the bulk topic dialog box.
     */
    public static final String BULK_OVERWRITE_DIALOG_WIDTH = "400px";
    /**
     * The height of the bulk topic dialog box.
     */
    public static final String BULK_OVERWRITE_DIALOG_HEIGHT = "120px";
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

