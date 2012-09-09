package org.jboss.pressgangccms.client.local.preferences;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

/**
 * This class provides access to the local preferences saved by the browser.
 * 
 * @author Matthew Casperson
 */
final public class Preferences {
    public static final Preferences INSTANCE = new Preferences();
    private static final String BUTTONVIEWSETTING = "ButtonView";
    private final Storage storage;

    public enum ButtonView {
        TEXT, IMAGE, TEXTANDIMAGE
    }

    private Preferences() {
        storage = Storage.getLocalStorageIfSupported();
    }

    public ButtonView getButtonView() {
        try {
            if (storage != null) {
                final StorageMap stockMap = new StorageMap(storage);
                if (stockMap.containsValue(BUTTONVIEWSETTING)) {
                    return ButtonView.valueOf(stockMap.get(BUTTONVIEWSETTING));
                }
            }
        } catch (final Exception ex) {
            // invalid data in local store. Just fail silently
        }

        return ButtonView.TEXT;
    }

    public void setButtonView(final ButtonView value) {
        if (storage != null) {
            storage.setItem(BUTTONVIEWSETTING, value.toString());
        }
    }
}
