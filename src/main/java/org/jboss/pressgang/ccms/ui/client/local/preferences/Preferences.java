package org.jboss.pressgang.ccms.ui.client.local.preferences;

import com.google.gwt.storage.client.Storage;

/**
 * This class provides access to the local preferences saved by the browser.
 * 
 * @author Matthew Casperson
 */
final public class Preferences {
    /** The setting that saves the width of the split screen on the tag search screen */
    public static final String TAG_VIEW_MAIN_SPLIT_WIDTH = "TagViewMainSplitWidth";
    /** The setting that saves the width of the split screen on the topic search screen */
    public static final String TOPIC_VIEW_MAIN_SPLIT_WIDTH = "TopicViewMainSplitWidth";
    /** The setting that saves the width of the horizontally split screen on the rendered topic search screen */
    public static final String TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH = "TopicViewRenderedHorizontalSplitWidth";
    /** The setting that saves the width of the vertically split screen on the rendered topic search screen */
    public static final String TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH = "TopicViewRenderedVerticalSplitWidth";
    /** The setting that saves the width of the split screen on the tag search screen */
    public static final String TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH = "TagCategoryViewMainSplitWidth";
    /** The setting that saves the width of the split screen on the tag search screen */
    public static final String  CATEGORY_VIEW_MAIN_SPLIT_WIDTH = "CategoryViewMainSplitWidth";


    
    /** A singelton instance of this class. */
    public static final Preferences INSTANCE = new Preferences();
    /** A reference to the GWT storage object. */
    private final Storage storage;


    private Preferences() {
        storage = Storage.getLocalStorageIfSupported();
    }

    public void saveSetting(final String key, final String data) {
        if (storage != null) {
            storage.setItem(key, data);
        }
    }

    public Integer getInt(final String key, final Integer defaultValue) {
        try {
            if (storage != null) {
                final String data = storage.getItem(key);
                if (data != null) {
                    return Integer.parseInt(data);
                }
            }

        } catch (final Exception NumberFormatException) {

        }

        return defaultValue;
    }
    
    public Double getDouble(final String key, final Double defaultValue) {
        try {
            if (storage != null) {
                final String data = storage.getItem(key);
                if (data != null) {
                    return Double.parseDouble(data);
                }
            }

        } catch (final Exception NumberFormatException) {

        }

        return defaultValue;
    }
}
