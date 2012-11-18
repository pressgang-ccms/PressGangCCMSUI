package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This class holds constants that relate to the underlying REST service
 * 
 * @author Matthew Casperson
 */
final public class ServiceConstants {
    /** The StringConstant that holds the locales */
    static public final int LOCALE_STRING_CONSTANT = 38;
    /** The StringConstant that holds the concept XML template */
    static public final int CONCEPT_TOPIC_TEMPLATE = 14;
    /** The StringConstant the holds the default topic template */
    static public final int TOPIC_TEMPLATE = 39;
    /** The StringConstant default locale id */
    static public final int DEFAULT_LOCALE_ID = 40;

    /** Used to indicate a minor change to an entity */
    static public final byte MINOR_CHANGE = 0x01;
    /** Used to indicate a major change to an entity */
    static public final byte MAJOR_CHANGE = 0x02;
    
    /** The id of the tag applied tgo all content specs */
    static public final Integer CSP_TAG_ID = 268;

    /**
     * The null user, used when no login information is available. Null identifies an unknown user, but this has issues in errai
     * (https://issues.jboss.org/browse/ERRAI-433)
     */
    static public final Integer NULL_USER_ID = 89;
    
    /**
     * The string constant that contains all the docbook elements
     */
    static public final Integer DOCBOOK_ELEMENTS_STRING_CONSTANT_ID = 41; 
    
    /**
     * The string constant ID that contains a comma separated list of 
     * other strnig constant IDs that contain docbook templates
     */
    static public final Integer DOCBOOK_TEMPLATES_STRING_CONSTANT_ID = 43;

    private ServiceConstants() {

    }
}
