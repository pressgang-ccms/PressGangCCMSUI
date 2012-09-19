package org.jboss.pressgang.ccms.ui.client.local.preferences;

import com.google.gwt.storage.client.Storage;

/**
 * This class provides access to the local preferences saved by the browser.
 * 
 * @author Matthew Casperson
 */
final public class Preferences {
    /** A singelton instance of this class. */
    public static final Preferences INSTANCE = new Preferences();
    /** A reference to the GWT storage object. */
    private final Storage storage;

    public enum ButtonView {
        TEXT, IMAGE, TEXTANDIMAGE
    }

    private Preferences() {
        storage = Storage.getLocalStorageIfSupported();
    }
}
