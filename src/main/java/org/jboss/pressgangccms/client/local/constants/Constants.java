package org.jboss.pressgangccms.client.local.constants;

/**
 * This class contains a number of constants used throughout the application
 * 
 * @author Matthew Casperson
 * 
 */
public final class Constants
{
	/**
	 * The maximum number of results to return in a search result
	 */
	public static final int MAX_SEARCH_RESULTS = 15;
	/**
	 * The REST server
	 */
	public static final String BASE_URL = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/";
	/**
	 * The REST URL
	 */
	public static final String REST_SERVER = BASE_URL + "seam/resource/rest";

	/**
	 * The base URL to Bugzilla
	 */
	public static final String BUGZILLA_BASE_URL = "https://bugzilla.redhat.com";

	/**
	 * The Bugzilla link for this product
	 */
	public static final String BUGZILLA_URL = BUGZILLA_BASE_URL + "/enter_bug.cgi?product=PressGang CCMS&component=Web-UI";

	/**
	 * View Bugzilla bug URL
	 */
	public static final String BUGZILLA_VIEW_BUG_URL = BUGZILLA_BASE_URL + "/show_bug.cgi?id=";
}
