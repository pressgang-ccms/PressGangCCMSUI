package org.jboss.pressgang.ccms.ui.client.local.preferences;

import com.google.gwt.storage.client.Storage;

/**
 * This class provides access to the local preferences saved by the browser.
 *
 * @author Matthew Casperson
 */
final public class Preferences {
    /**
     * The setting that saves the build that was used last. This allows us to display a message when the UI has been updated
     */
    public static final String LAST_BUILD = "LastBuild";
    /**
     * The setting that saves the width of the split screen on the tag search screen
     */
    public static final String TAG_VIEW_MAIN_SPLIT_WIDTH = "TagViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the topic search screen
     */
    public static final String TOPIC_VIEW_MAIN_SPLIT_WIDTH = "TopicViewMainSplitWidth";
    /**
     * The setting that saves the width of the horizontally split screen on the rendered topic search screen
     */
    public static final String TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH = "TopicViewRenderedHorizontalSplitWidth";
    /**
     * The setting that saves the width of the vertically split screen on the rendered topic search screen
     */
    public static final String TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH = "TopicViewRenderedVerticalSplitWidth";
    /**
     * The setting that saves the width of the split screen on the tag search screen
     */
    public static final String TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH = "TagCategoryViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category search screen
     */
    public static final String IMAGE_VIEW_MAIN_SPLIT_WIDTH = "ImageViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category search screen
     */
    public static final String CATEGORY_VIEW_MAIN_SPLIT_WIDTH = "CategoryViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category's tag editing screen
     */
    public static final String CATEGORY_TAG_VIEW_MAIN_SPLIT_WIDTH = "CategoryTagViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category's tag editing screen
     */
    public static final String TOPIC_PROPERTYTAG_VIEW_MAIN_SPLIT_WIDTH = "TopicPropertyTagViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category search screen
     */
    public static final String PROJECT_VIEW_MAIN_SPLIT_WIDTH = "ProjectViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the category's tag editing screen
     */
    public static final String PROJECT_TAG_VIEW_MAIN_SPLIT_WIDTH = "ProjectTagViewMainSplitWidth";
    /**
     * The setting that saves the type of split on the topic edit screen
     */
    public static final String TOPIC_RENDERED_VIEW_SPLIT_TYPE = "TopicRenderedViewSplitType";
    /**
     * The setting that saves defines a horizontal split
     */
    public static final String TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL = "HorizontalSplit";
    /**
     * The setting that saves defines a vertical split
     */
    public static final String TOPIC_RENDERED_VIEW_SPLIT_VERTICAL = "VerticalSplit";
    /**
     * The setting that saves defines no split
     */
    public static final String TOPIC_RENDERED_VIEW_SPLIT_NONE = "NoSplit";
    /**
     * The setting that saves the width of the split screen on the filter list screen
     */
    public static final String FILTER_VIEW_MAIN_SPLIT_WIDTH = "FilterViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the string constants list screen
     */
    public static final String STRING_CONSTANTS_VIEW_MAIN_SPLIT_WIDTH = "StringConstantsViewMainSplitWidth";


    /**
     * A singelton instance of this class.
     */
    public static final Preferences INSTANCE = new Preferences();
    /**
     * A reference to the GWT storage object.
     */
    private final Storage storage;

    /**
     * private constructor to force usage of singleton.
     */
    private Preferences() {
        storage = Storage.getLocalStorageIfSupported();
    }

    /**
     * Save a setting.
     *
     * @param key  the setting name
     * @param data the setting value
     */
    public void saveSetting(final String key, final String data) {
        if (storage != null) {
            storage.setItem(key, data);
        }
    }

    /**
     * Get a setting, or return the defaultValue if the setting does not exist.
     *
     * @param key          the setting name
     * @param defaultValue the value to return if the setting does not exist
     * @return the saved setting if it exists, or defaultValue if it does not
     */
    public String getString(final String key, final String defaultValue) {
        if (storage != null) {
            final String data = storage.getItem(key);
            if (data != null) {
                return data;
            }
        }

        return defaultValue;
    }

    /**
     * Get a setting as an integer, or return the defaultValue if the setting does not exist or is not a valid integer
     *
     * @param key          the setting name
     * @param defaultValue the value to return if the setting does not exist
     * @return the saved setting if it exists and is a valid integer, or defaultValue if it does not
     */
    public Integer getInt(final String key, final Integer defaultValue) {
        try {
            if (storage != null) {
                final String data = storage.getItem(key);
                if (data != null) {
                    return Integer.parseInt(data);
                }
            }

        } catch (final Exception ex) {
            /*
                The saved setting was not a valid integer, so we
                continue on to return the default value.
             */
        }

        return defaultValue;
    }

    /**
     * Get a setting as an double, or return the defaultValue if the setting does not exist or is not a valid double
     *
     * @param key          the setting name
     * @param defaultValue the value to return if the setting does not exist
     * @return the saved setting if it exists and is a valid double, or defaultValue if it does not
     */
    public Double getDouble(final String key, final Double defaultValue) {
        try {
            if (storage != null) {
                final String data = storage.getItem(key);
                if (data != null) {
                    return Double.parseDouble(data);
                }
            }

        } catch (final Exception ex) {
            /*
                The saved setting was not a valid double, so we
                continue on to return the default value.
             */
        }

        return defaultValue;
    }
}
