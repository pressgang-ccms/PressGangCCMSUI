package org.jboss.pressgang.ccms.ui.client.local.preferences;

import com.google.gwt.storage.client.Storage;
import org.jetbrains.annotations.NotNull;

/**
 * This class provides access to the local preferences saved by the browser.
 *
 * @author Matthew Casperson
 */
final public class Preferences {
    /**
     * Saves the server that the UI should connect to.
     */
    public static final String SERVER = "Server";
    /**
     * Saves the server that the UI should connect to.
     */
    public static final String FAILED_SERVER = "FailedServer";
    /**
     * Saves the last entered username in the topic save log message dialog box.
     */
    public static final String LOG_MESSAGE_USERNAME = "LogMessageUsername";
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
     * The setting that saves the width of the split screen on the topic search screen
     */
    public static final String TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH = "TopicViewXMLErrorsSplitWidth";
    /**
     * The setting that saves the width of the split screen on the topic search screen
     */
    public static final String TRANSLATED_TOPIC_VIEW_MAIN_SPLIT_WIDTH = "TranslatedTopicViewMainSplitWidth";
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
     * The setting that saves the width of the split screen on the property tags search screen
     */
    public static final String PROPERTY_TAG_VIEW_MAIN_SPLIT_WIDTH = "PropertyTagViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the property category search screen
     */
    public static final String PROPERTY_CATEGORY_VIEW_MAIN_SPLIT_WIDTH = "PropertyCategoryViewMainSplitWidth";
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
     * The setting that saves the width of the split screen on the category search screen
     */
    public static final String FILE_VIEW_MAIN_SPLIT_WIDTH = "FileViewMainSplitWidth";
    /**
     * The setting that saves the width of the split screen on the content spec search screen
     */
    public static final String CONTENT_SPEC_VIEW_MAIN_SPLIT_WIDTH = "ContentSpecViewMainSplitWidth";

    public static final String SHORTCUT_SEARCH_MENU_OPEN = "ShortcutSearchMenuOpen";
    public static final String SHORTCUT_ENTITIES_MENU_OPEN = "ShortcutEntitiesMenuOpen";
    public static final String SHORTCUT_ADVANCED_MENU_OPEN = "ShortcutAdvancedMenuOpen";
    public static final String EDITOR_THEME = "EditorTheme";
    public static final String EDITOR_FONT_SIZE = "EditorFontSize";
    public static final String EDITOR_FONT = "EditorFont";

    /**
     * A singelton instance of this class.
     */
    public static final Preferences INSTANCE = new Preferences();
    /**
     * A reference to the GWT storage object.
     */
    private final Storage storage;


    private Preferences() {
        storage = Storage.getLocalStorageIfSupported();
    }

    public void saveSetting(final String key, final String data) {
        if (storage != null) {
            storage.setItem(key, data);
        }
    }

    public void saveSetting(final String key, final Boolean data) {
        if (storage != null && data != null) {
            storage.setItem(key, data.toString());
        }
    }

    public void saveSetting(final String key, final Integer data) {
        if (storage != null && data != null) {
            storage.setItem(key, data.toString());
        }
    }

    public void deleteSetting(final String key) {
        if (storage != null) {
            storage.removeItem(key);
        }
    }

    public String getString(final String key, final String defaultValue) {
        if (storage != null) {
            final String data = storage.getItem(key);
            if (data != null) {
                return data;
            }
        }

        return defaultValue;
    }

    public Integer getInt(final String key, final Integer defaultValue) {
        try {
            if (storage != null) {
                final String data = storage.getItem(key);
                if (data != null) {
                    return Integer.parseInt(data);
                }
            }

        } catch (@NotNull final Exception ex) {

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

        } catch (@NotNull final Exception ex) {

        }

        return defaultValue;
    }

    public Boolean getBoolean(final String key, final Boolean defaultValue) {
        if (storage != null) {
            final String data = storage.getItem(key);
            if (data != null) {
                return Boolean.parseBoolean(data);
            }
        }

        return defaultValue;
    }
}
