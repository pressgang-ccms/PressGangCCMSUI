package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This class holds constants that relate to the underlying REST service.
 *
 * @author Matthew Casperson
 */
public final class ServiceConstants {

    /**
     * Original file name property tag
     */
    public static final int ORIGINAL_FILE_NAME_PROPERTY_TAG = 28;

    /**
     * Tag style property tag id
     */
    public static final int TAG_STYLE_PROPERTY_TAG = 36;

    /**
     * Used to indicate a minor change to an entity.
     */
    public static final byte MINOR_CHANGE = 0x01;
    /**
     * Used to indicate a major change to an entity.
     */
    public static final byte MAJOR_CHANGE = 0x02;
    /**
     * The id of the tag applied to all content specs.
     */
    public static final Integer CSP_TAG_ID = 268;
    /**
     * The null user, used when no login information is available. Null identifies an unknown user, but this has issues in errai
     * (https://issues.jboss.org/browse/ERRAI-433).
     */
    public static final Integer NULL_USER_ID = 89;
    //public static final Integer NULL_USER_ID = null;

    /*
        *************** STRING CONSTANTS ***************
     */

    /**
     * The StringConstant that holds the locales.
     */
    public static final int LOCALE_STRING_CONSTANT = 38;
    /**
     * The StringConstant default locale id.
     */
    public static final int DEFAULT_LOCALE_ID = 40;
    /**
     * The string constant that contains all the docbook elements.
     */
    public static final Integer DOCBOOK_ELEMENTS_STRING_CONSTANT_ID = 41;
    /**
     * The string constant ID that contains a comma separated list of
     * other string constant IDs that contain docbook templates.
     */
    public static final Integer DOCBOOK_TEMPLATES_STRING_CONSTANT_ID = 43;
    /**
     * The String Constant ID of the basic section template.
      */
    public static final Integer BASIC_TOPIC_TEMPLATE_STRING_CONSTANT_ID = 59;

    /*
        *************** TOPIC CONSTANTS ***************
     */
    public static enum HELP_TOPICS {
        WELCOME_VIEW_CONTENT_TOPIC(12556),
        SHOW_HIDE_SEARCH_RESULTS_TOPIC(21174),
        HOME_VIEW_TOPIC(21175),
        DOCBUILDER_VIEW_TOPIC(21176),
        CREATE_TOPIC_VIEW_TOPIC(21177),
        SEARCH_TOPICS_VIEW(21178),
        SEARCH_TRANSLATIONS_VIEW(21179),
        IMAGES_VIEW(21180),
        TAGS_VIEW(21181),
        CATEGORIES_VIEW(21182),
        PROJECTS_VIEW(21183),
        REPORTS(21184),
        CREATE_BUG(21185),
        FILES(21200),
        ENTITIES(21201),
        ADVANCED(21186),
        SEARCH(21202),
        SEARCH_CONTENT_SPECS(21203),
        CREATE_CONTENT_SPEC(21204),
        BULK_TAGGING(21206),
        STRING_CONSTANTS(21208),
        BLOB_CONSTANTS(21209),
        INTEGER_CONSTANTS(21210),
        EXTENDED_PROPERTIES(21211),
        EXTENDED_PROPERTY_CATEGORIES(21212),
        MONITORING(21213),
        SIMPLE_SEARCH(21214),
        HELP_MODE(21226),
        SERVER_SELECTION(21227),
        BUILD_LABEL(21228),
        TOPIC_SEARCH_RESULTS(21229),
        XML_EDITOR(21230),
        RENDERED_PREVIEW(21231),
        TOPIC_XML_EDITOR(21233),
        TOPIC_PROPERTIES(21234),
        TOPIC_EXTENDED_PROPERTIES(21235),
        TOPIC_SOURCE_URLS(21236),
        TOPIC_TAGS(21237),
        TOPIC_BUGS(21238),
        TOPIC_REVISIONS(21239),
        TOPIC_SAVE(21240),
        TOPIC_LINE_WRAP(21241),
        TOPIC_HIDDEN_CHARACTERS(21242),
        TOPIC_RENDERED_PANE(21232),
        TOPIC_CONTENT_SPECS(21335),
        TOPIC_XML_VALIDATION(21336),
        TOPIC_PROPERT_TITLE(21351),
        TOPIC_PROPERTY_REST_ENDPOINT(21352),
        TOPIC_PROPERTY_REST_XML_ENDPOINT(21353),
        TOPIC_PROPERTY_WEBDAV_URL(21354),
        TOPIC_PROPERTY_DESCRIPTION(21355),
        TOPIC_PROPERTY_LOCALE(21356),
        TOPIC_AVAILABLE_EXTENDED_PROPERTIES(21357),
        TOPIC_EXISTING_EXTENDED_PROPERTIES(21358),
        TOPIC_SOURCE_URLS_LIST(21359),
        TOPIC_ADD_SOURCE_URL(21360),
        TOPIC_TAG_PROJECTS_LIST(21364),
        TOPIC_TAG_CATEGORIES_LIST(21365),
        TOPIC_TAG_TAGS_LIST(21366),
        TOPIC_TAG_EXISTING(21368),
        TOPIC_TAG_ADD(21369);

        private final int id;
        public int getId() {
            return id;
        }
        HELP_TOPICS(final int id) {
            this.id = id;
        }
    }


    /**
     * A private constructor to prevent instantiation.
     */
    private ServiceConstants() {

    }
}
