package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This class holds constants that relate to the underlying REST service.
 *
 * @author Matthew Casperson
 */
public final class ServiceConstants {
    /**
     * The StringConstant that holds the locales.
     */
    public static final int LOCALE_STRING_CONSTANT = 38;
    /**
     * The StringConstant that holds the concept XML template.
     */
    public static final int CONCEPT_TOPIC_TEMPLATE = 14;
    /**
     * The StringConstant the holds the default topic template.
     */
    public static final int TOPIC_TEMPLATE = 39;
    /**
     * The StringConstant default locale id.
     */
    public static final int DEFAULT_LOCALE_ID = 40;
    /**
     * Used to indicate a minor change to an entity.
     */
    public static final byte MINOR_CHANGE = 0x01;
    /**
     * Used to indicate a major change to an entity.
     */
    public static final byte MAJOR_CHANGE = 0x02;
    /**
     * The id of the tagincategory applied to all content specs.
     */
    public static final Integer CSP_TAG_ID = 268;
    /**
     * The null user, used when no login information is available. Null identifies an unknown user, but this has issues in errai
     * (https://issues.jboss.org/browse/ERRAI-433).
     */
    public static final Integer NULL_USER_ID = 89;
    //public static final Integer NULL_USER_ID = null;
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
     * The help topic for the search screen.
     */
    public static final int SEARCH_VIEW_HELP_TOPIC = 12505;
    /**
     * The topic that is displayed in the welcome view.
     */
    public static final int WELCOME_VIEW_CONTENT_TOPIC = 12556;
    /**
     * The topic that is displayed in the welcome view.
     */
    public static final int TOPIC_EDIT_VIEW_CONTENT_TOPIC = 12510;
    /**
     * The help topic for the xml edit view.
     */
    public static final int TOPIC_XML_EDIT_TOPIC = 12683;
    /**
     * The help topic for the validation errors view.
     */
    public static final int TOPIC_VALIDATION_ERRORS_TOPIC = 12559;
    /**
     * The help topic for the properties view.
     */
    public static final int TOPIC_PROPERTIES_TOPIC = 12560;
    /**
     * The help topic for the tags view.
     */
    public static final int TOPIC_TAGS_TOPIC = 12587;
    /**
     * The help topic for the revisions view.
     */
    public static final int TOPIC_REVISIONS_TOPIC = 12588;
    /**
     * The help topic for the revisions view.
     */
    public static final int TOPIC_BUGS_TOPIC = 12589;
    /**
     * The help topic for the images view.
     */
    public static final int IMAGES_TOPIC = 12592;
    /**
     * The default "No help is available" topic.
     */
    public static final int DEFAULT_HELP_TOPIC = 12558;
    /**
     * The help topic for the search filters screen.
     */
    public static final int FILTERS_HELP_TOPIC = 13434;

    /**
     * The help topic for the project details view.
     */
    public static final int PROJECT_DETAILS_HELP_TOPIC = 13581;

    /**
     * The help topic for the project tags view.
     */
    public static final int PROJECT_TAGS_HELP_TOPIC = 13582;
    /**
     * The help topic for the project view.
     */
    public static final int PROJECT_HELP_TOPIC = 13583;
    /**
     * The help topic for the property tagincategory details view.
     */
    public static final int PROPERTY_TAG_DETAILS_HELP_TOPIC = 13663;

    /**
     * The help topic for the property tagincategory tags view.
     */
    public static final int PROPERTY_TAG_CATEGORIES_HELP_TOPIC = 13664;
    /**
     * The help topic for the property tagincategory view.
     */
    public static final int PROPERTY_TAG_HELP_TOPIC = 13665;

    /**
     * The help topic for the property category view.
     */
    public static final int PROPERTY_CATEGORY_HELP_TOPIC =  13742;

    /**
     * The help topic for the property category details view.
     */
    public static final int PROPERTY_CATEGORY_DETAILS_HELP_TOPIC = 13743;

    /**
     * The help topic for the property tagincategory tags view.
     */
    public static final int PROPERTY_CATEGORY_TAGS_HELP_TOPIC = 13744;

    /**
     * The help topic for the string constants view.
     */
    public static final int STRING_CONSTANT_HELP_TOPIC = 13598;

    /**
     * The help topic for the string constants details view.
     */
    public static final int STRING_CONSTANT_DETAILS_HELP_TOPIC = 13600;

    /**
     * The help topic for the category view.
     */
    public static final int CATEGORY_HELP_TOPIC = 13584;

    /**
     * The help topic for the category detail view.
     */
    public static final int CATEGORY_DETAIL_HELP_TOPIC = 13585;

    /**
     * The help topic for the category tagincategory view.
     */
    public static final int CATEGORY_TAG_HELP_TOPIC = 13586;

    /**
     * The help topic for the image view.
     */
    public static final int IMAGE_HELP_TOPIC = 13587;

    /**
     * The help topic for the tagincategory view.
     */
    public static final int TAG_HELP_TOPIC = 13588;

    /**
     * The help topic for the tagincategory projects view.
     */
    public static final int TAG_PROJECTS_HELP_TOPIC = 13589;

    /**
     * The help topic for the tagincategory categories view.
     */
    public static final int TAG_CATEGORIES_HELP_TOPIC = 13590;

    /**
     * The help topic for the tagincategory details view.
     */
    public static final int TAG_DETAIL_HELP_TOPIC = 13591;

    /**
     * The help topic for the search fields view.
     */
    public static final int SEARCH_FIELDS_HELP_TOPIC = 13592;

    /**
     * The help topic for the search locales view.
     */
    public static final int SEARCH_LOCALES_HELP_TOPIC = 13593;

    /**
     * The help topic for the topics source url view.
     */
    public static final int TOPIC_SOURCE_URLS_HELP_TOPIC = 13594;

    /**
     * The help topic for the topics extended properties view.
     */
    public static final int TOPIC_EXTENDED_PROPERTIES_HELP_TOPIC = 13595;

    /**
     * The help topic for the home view.
     */
    public static final int HOME_HELP_TOPIC = 13596;

    /**
     * The help topic for the integer constants view.
     */
    public static final int INTEGER_CONSTANT_HELP_TOPIC = 13653;

    /**
     * The help topic for the blob constants view.
     */
    public static final int BLOB_CONSTANT_HELP_TOPIC = 13654;

    /**
     * A private constructor to prevent instantiation.
     */
    private ServiceConstants() {

    }
}
