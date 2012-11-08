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
    /** The default locale */
    static public final int DEFAULT_LOCALE_ID = 40;
    
    /** Used to indicate a minor change to an entity */
    static public final byte MINOR_CHANGE = 0x01;
    /** Used to indicate a major change to an entity */
    static public final byte MAJOR_CHANGE = 0x01;
    
    /** The null user, used when no login information is available */
    static public final Integer NULL_USER_ID = null;

    private ServiceConstants() {

    }
}
