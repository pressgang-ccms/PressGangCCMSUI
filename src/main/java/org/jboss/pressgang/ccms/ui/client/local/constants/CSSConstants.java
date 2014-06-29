package org.jboss.pressgang.ccms.ui.client.local.constants;

/**
 * This interface lists all of the CSS class names used by the application. The nested interfaces are just used to group the
 * styles assigned to particular views.
 *
 * @author Matthew Casperson
 */
public interface CSSConstants {

    interface SysInfo {
        String SYSINFO_LABEL = "SysInfoLabel";
        String SYS_INFO_PANEL = "SysInfoPanel";
    }

    interface Legend {
        String LEGEND_PANEL = "LegendPanel";
        String LEGEND_PARENT_PANEL = "LegendParentPanel";
        String TAG_MATCH_LEGEND = "TagMatchLegend";
        String BAD_PHRASE_LEGEND = "BadPhraseLegend";
        String MISSPELLED_LEGEND = "MisspelledLegend";
        String BAD_WORD_LEGEND = "BadWordLegend";
        String LEGEND = "Legend";
        String EDITED_ONE_DAY_LEGEND = "EditedOneDayLegend";
        String EDITED_ONE_WEEK_LEGEND = "EditedOneWeekLegend";
        String EDITED_ONE_MONTH_LEGEND = "EditedOneMonthLegend";
        String EDITED_ONE_YEAR_LEGEND = "EditedOneYearLegend";
        String EDITED_OLDER_LEGEND = "EditedOlderLegend";
        String XML_ERROR_LEGEND = "XMLErrorLegend";
        String RENDERED_DIFF_ADDED_TEXT_LEGEND = "RenderedDiffAddedText";
        String RENDERED_DIFF_REMOVED_TEXT_LEGEND = "RenderedDiffRemovedText";
    }

    interface AlertBox {
        String ALERT_BOX_MESSAGE = "AlertBoxMessage";
        String ALERT_BOX_OK = "AlertBoxOK";
        String ALERT_BOX_PANEL = "AlertBoxPanel";
    }

    interface BaseChildrenView {
        String POSSIBLE_CHILDREN_RESULTS_PANEL = "PossibleChildrenResultsPanel";
    }

    interface TopicReviewView {
        String TOPIC_REVIEW_LAYOUT_PANEL = "TopicReviewLayoutPanel";
        String TOPIC_REVIEW_RENDERED_DIFF = "TopicReviewRenderedDiff";
        String TOPIC_REVIEW_VIEW_SPINNER = "TopicReviewSpinner";
        String TOPIC_REVIEW_HELP_IFRAME = "TopicReviewHelpIframe";
        String TOPIC_REVIEW_HELP_IFRAME_PARENT = "TopicReviewHelpIframeParent";
        String TOPIC_REVIEW_BUTTON_LAYOUT_PANEL = "TopicReviewButtonLayoutPanel";
    }

    interface HelpOverlay {
        /**
         * Style applied to the dimmer panel as the base of the help overlay.
         */
        String HELP_OVERLAY_DIMMER_PANEL = "HelpOverlayDimmerPanel";
        /**
         * Style applied to the panel that covers the screen to disable mouse events
         */
        String HELP_OVERLAY_MOUSE_LOCK_PANEL = "HelpOverlayMouseLockPanel";

        String TOP_ARROW = "TopArrow";
        String BOTTOM_ARROW = "BottomArrow";
        String LEFT_ARROW = "LeftArrow";
        String RIGHT_ARROW = "RightArrow";
        String HELP_CALLOUT_IFRAME = "HelpCalloutIFrame";
        String HELP_CALLOUT_CONTENT_PARENT = "HelpCalloutContentParent";
        String HELP_CALLOUT_SPINNER = "HelpCalloutSpinner";
        String HELP_CALLOUT = "HelpCallout";
        String ARROW_CELL = "ArrowCell";
        String CONTENT_CELL = "ContentCell";
        String CLOSE_BUTTON = "CloseButton";
        String CLOSE_AND_EDIT_BUTTONS_PARENT = "CloseAndEditButtonsParent";
        String CLOSE_AND_EDIT_BUTTONS_PARENT_CELL = "CloseAndEditButtonsParentCell";
    }

    /**
     * Styles applied to the base template.
     */
    interface Template {
        /**
         * The style applied to the header image
         */
        String PRESSGANG_HEADER_IMAGE = "PressGangHeaderImage";
        /**
         * Assigned to the doc layout panel below the page heading.
         */
        String APPLICATION_HEADING_PANEL = "ApplicationHeadingPanel";
        /**
         * The style added to the panel that holds the top shortcut buttons and menus
         */
        String TOP_SHORTCUT_PANEL = "TopShortcutPanel";
        /**
         * The style added to the cell that holds the shortcut panel
         */
        String TOP_SHORTCUT_PANEL_CELL = "TopShortcutPanelCell";
        /**
         * The style applied to the top level menu
         */
        String TOP_SHORTCUT_MENU = "TopShortcutMenu";
        /**
         * The style applied to the top level menu items
         */
        String TOP_SHORTCUT_MENU_ITEM = "TopShortcutMenuItem";
        /**
         * The style applied to the top level sub menu items
         */
        String TOP_SHORTCUT_SUB_MENU = "TopShortcutSubMenu";
        /**
         * Style applied to the label that identifies the ui as working with a production server.
         */
        String SERVER_TYPE_PRODUCTION = "ServerTypeProduction";
        /**
         * Style applied to the label that identifies the ui as working with a development server.
         */
        String SERVER_TYPE_DEVELOPMENT = "ServerTypeDevelopment";
        /**
         * Assigned to the panel down the bottom of the screen that serves as the footer.
         */
        String FOOTER_PANEL = "FooterPanel";
        /**
         * The class assigned to the top level DockLayoutPanel.
         */
        String TOP_LEVEL_LAYOUT_PANEL = "TopLevelLayoutPanel";
        /**
         * Assigned to the doc layout panel below the page heading.
         */
        String SECOND_LEVEL_LAYOUT_PANEL = "SecondLevelLayoutPanel";
        /**
         * The class assigned to the application title label.
         */
        String APPLICATION_TITLE = "ApplicationTitle";
        /**
         * The class assigned to the page title label.
         */
        String PAGE_TITLE = "PageTitle";
        /**
         * Assigned to the panel that holds the quick search controls.
         */
        String QUICK_SEARCH_PARENT_PANEL = "QuickSearchParentPanel";
        /**
         * Assigned to the textbox that is used for the quick search query.
         */
        String QUICK_SEARCH_TEXT_BOX = "QuickSearchTextBox";
        /**
         * Assigned to the panel that holds the page title and quick search buttons
         */
        String PAGE_TITLE_PARENT_LAYOUT_PANEL = "PageTitleParentLayoutPanel";
        /**
         * Assigned to the panel that holds the top action buttons and page content.
         */
        String THIRD_LEVEL_LAYOUT_PANEL = "ThirdLevelLayoutPanel";
        /**
         * Assigned to the panel that holds the parent of the  action buttons panel.
         */
        String TOP_ACTION_GRANDPARENT_PANEL = "TopActionGrandParentPanel";
        /**
         * Assigned to the panel that holds the action buttons panel.
         */
        String TOP_ACTION_PARENT_PANEL = "TopActionParentPanel";
        /**
         * Assigned to the panel that holds the action buttons.
         */
        String TOP_ACTION_PANEL = "TopActionPanel";
        /**
         * Assigned to the panel that holds the page content.
         */
        String CONTENT_LAYOUT_PANEL = "ContentLayoutPanel";
        /**
         * Assigned to a padding element that pushing the remaining buttons to the right.
         */
        String RIGHT_ALIGNED_ACTION_BUTTONS = "RightAlignedActionButtons";
        /**
         * The style applied to the panel that holds custom footer items
          */
        String CUSTOM_FOOTER_PANEL = "CustomFooterPanel";
        /**
         * The style applied to the version label.
          */
        String VERSION_LABEL = "VersionLabel";


        /**
         * Assigned to the table that holds the tabs above the filtered results table.
         */
        String FILTERED_RESULTS_TAB_MENU_TABLE = "FilteredResultsTabMenu";

        String START_TAB_BUTTONS = "StartTabButtons";
        String END_TAB_BUTTONS = "EndTabButtons";
    }


    interface BulkImageUploadDialog {
        String DESCRIPTION_FIELD = "BulkImageUploadDescriptionField";
    }

    interface BulkFileUploadDialog {
        String DESCRIPTION_FIELD = "BulkFileUploadDescriptionField";
        String FILE_PATH_FIELD = "BulkFileUploadFilePathField";
    }

    interface HelpDialog {
        String HELP_CONTENTS = "HelpContents";
    }

    interface ImportDialogs {
        String IMPORT_DIALOG_MESSAGE = "ImportDialogMessage";
        String IMPORT_DIALOG_MESSAGE_PANEL = "ImportDialogMessagePanel";
    }

    interface WelcomeView {
        String WELCOME_VIEW_IFRAME = "WelcomeViewIFrame";
        String WELCOME_VIEW_PANEL = "WelcomeViewPanel";
        String WELCOME_VIEW_PANEL_PARENT = "WelcomeViewPanelParent";
    }

    /**
     * These styles are applied to the topic and translated topic search views.
     */
    interface SearchView {
        /**
         * The style applied to the panel that holds the locales
         */
        String LOCALE_PANEL = "LocalePanel";
        /**
         * The style applied to the cell that appears above the
         * category logic options.
         */
        String LOGIC_HEADER_CELL = "LogicHeaderCell";
        /**
         * The style applied to the cell that holds the table holding the logic details radio buttons.
         */
        String LOGIC_DETAILS_CELL = "LogicDetailsCell";
        /**
         * The style applied to the table holding the logic details radio buttons.
         */
        String LOGIC_DETAILS_TABLE = "LogicDetailsTable";
        /**
         * The style applied to the cell that holds the tag's name
         */
        String TAG_NAME = "TagName";
        /**
         * The style applied to the cell that holds the tag's search state
         */
        String SEARCH_STATE = "SearchState";
        /**
         * The style applied to the cell that holds the tag's bulk update state
         */
        String BULK_TAG_STATE = "BulkTagState";
    }

    /**
     * The CSS style names applied to the search filter editor
     */
    interface FilterEditor {
        /**
         * The style name of the filter's parent panel.
         */
        String FILTER_VIEW_PANEL = "FilterViewPanel";
        /**
         * The style name of the filter's name textbox.
         */
        String FILTER_VIEW_NAME_FIELD = "FilterViewNameField";
        /**
         * The style name of the filter's description textarea.
         */
        String FILTER_VIEW_DESCRIPTION_FIELD = "FilterViewDescriptionField";
        /**
         * The style name of the cell that holds the label next to the filter's details.
         */
        String FILTER_VIEW_LABEL_CELL = "FilterViewLabelCell";
        /**
         * The style name of the cell that holds the filter's details.
         */
        String FILTER_VIEW_DETAIL_CELL = "FilterViewDetailCell";
        /**
         * The style name of the cell that holds the filter's description textarea.
         */
        String FILTER_VIEW_DESCRIPTION_CELL = "FilterViewDescriptionCell";
    }

    /**
     * The CSS style names applied to the search field editor
     */
    interface FieldEditor {
        /**
         * The style name of the field's parent panel.
         */
        String FIELD_VIEW_PANEL = "FieldViewPanel";
        /**
         * The style name of the cell that holds the label next to the field's value.
         */
        String FIELD_VIEW_LABEL_CELL = "FieldViewLabelCell";
        /**
         * The style name of the cell that holds the fields value.
         */
        String FIELD_VIEW_VALUE_CELL = "FieldViewValueCell";
    }

    interface PlainTextXMLDialog {
        String PLAIN_TEXT_XML_DIALOG_TEXTAREA = "PlainTextXMLDialogTextArea";
    }

    interface FilteredResultsView {
        /**
         * Assigned to the panel that holds the list of images.
         */
        String FILTERED_RESULTS_PANEL = "FilteredResultsPanel";
        /**
         * Assigned to the panel that holds the filter options for images.
         */
        String FILTERED_OPTIONS_PANEL = "FilteredOptionsPanel";
        /**
         * Assigned to the table that holds the list of results.
         */
        String FILTERED_RESULTS_TABLE = "FilteredResultsTable";
        /**
         * Assigned to the selected row of the table that holds the list of results.
         */
        String FILTERED_RESULTS_TABLE_SELECTED_ROW = "FilteredResultsTableSelectedRow";
    }

    interface OrderedChildrenResultsView {
        String ORDERED_CHILDREN_LIST_PANEL = "OrderedChildrenListPanel";
        String ORDERED_CHILDREN_EXISTING_LIST_PANEL = "OrderedChildrenExistingListPanel";
        String ORDERED_CHILDREN_SPLIT_PANEL = "OrderedChildrenSplitPanel";
    }

    interface BaseSearchAndEditView {
        String RESULTS_VIEW_LAYOUT_PANEL = "ResultsViewLayoutPanel";
        String ENTITY_VIEW_LAYOUT_PANEL = "EntityViewLayoutPanel";
        /**
         * Assigned to the panel that holds the action buttons above the entity search results lists
         */
        String ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL = "EntitySearchTagsResultButtonsPanel";
        String ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PARENT_PANEL = "EntitySearchTagsResultButtonsParentPanel";
        /**
         * Assigned to the panel that holds the action buttons above the entity search result view
         */
        String ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL = "EntitySearchTagViewButtonsPanel";
        String ENTITY_SEARCH_TAG_VIEW_BUTTONS_PARENT_PANEL = "EntitySearchTagViewButtonsParentPanel";
        String ENTITY_SEARCH_TOPIC_VIEW_DETAILS_PANEL = "EntitySearchTopicViewDetailsPanel";
        String ENTITY_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL = "EntitySearchResultsAndViewParentPanel";
        /**
         * The style applied to the scroll panel that holds the views action buttons
         */
        String VIEW_ACTION_BUTTONS_PARENT_PANEL_SCROLL = "ViewActionButtonsParentPanelScroll";
        /**
         * The style applied to the scroll panel that holds the results action buttons
         */
        String RESULTS_ACTION_BUTTONS_PARENT_PANEL_SCROLL = "ResultsActionButtonsParentPanelScroll";
    }

    /**
     * Holds the styles that are applied to the string constant's views.
     */
    interface StringConstantView {
        String STRING_CONSTANT_VIEW_PANEL = "StringConstantViewPanel";
        String STRING_CONSTANT_VIEW_ID_FIELD = "StringConstantViewIdField";
        String STRING_CONSTANT_VIEW_NAME_FIELD = "StringConstantViewNameField";
        String STRING_CONSTANT_VIEW_VALUE_FIELD = "StringConstantViewValueField";

        String STRING_CONSTANT_VIEW_LABEL_CELL = "StringConstantViewLabelCell";
        String STRING_CONSTANT_VIEW_DETAIL_CELL = "StringConstantViewDetailCell";
        String STRING_CONSTANT_VIEW_DESCRIPTION_CELL = "StringConstantViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the property tag's views.
     */
    interface PropertyTagView {
        String PROPERTY_TAG_VIEW_PANEL = "PropertyTagViewPanel";
        String PROPERTY_TAG_VIEW_ID_FIELD = "PropertyTagViewIdField";
        String PROPERTY_TAG_VIEW_NAME_FIELD = "PropertyTagViewNameField";
        String PROPERTY_TAG_VIEW_REGEX_FIELD = "PropertyTagViewRegexField";
        String PROPERTY_TAG_VIEW_DESCRIPTION_FIELD = "PropertyTagViewDescriptionField";
        String PROPERTY_TAG_VIEW_UNIQUE_FIELD = "PropertyTagViewUniqueField";
        String PROPERTY_TAG_VIEW_CANBENULL_FIELD = "PropertyTagViewCanBeNullField";

        String PROPERTY_TAG_VIEW_LABEL_CELL = "PropertyTagViewLabelCell";
        String PROPERTY_TAG_VIEW_DETAIL_CELL = "PropertyTagViewDetailCell";
        String PROPERTY_TAG_VIEW_DESCRIPTION_CELL = "PropertyTagViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the property category's views.
     */
    interface PropertyCategoryView {
        String PROPERTY_CATEGORY_VIEW_PANEL = "PropertyCategoryViewPanel";
        String PROPERTY_CATEGORY_VIEW_ID_FIELD = "PropertyCategoryViewIdField";
        String PROPERTY_CATEGORY_VIEW_NAME_FIELD = "PropertyCategoryViewNameField";
        String PROPERTY_CATEGORY_VIEW_DESCRIPTION_FIELD = "PropertyCategoryViewDescriptionField";

        String PROPERTY_CATEGORY_VIEW_LABEL_CELL = "PropertyCategoryViewLabelCell";
        String PROPERTY_CATEGORY_VIEW_DETAIL_CELL = "PropertyCategoryViewDetailCell";
        String PROPERTY_CATEGORY_VIEW_DESCRIPTION_CELL = "PropertyCategoryViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the integer constant's views.
     */
    interface IntegerConstantView {
        String INTEGER_CONSTANT_VIEW_PANEL = "IntegerConstantViewPanel";
        String INTEGER_CONSTANT_VIEW_ID_FIELD = "IntegerConstantViewIdField";
        String INTEGER_CONSTANT_VIEW_NAME_FIELD = "IntegerConstantViewNameField";
        String INTEGER_CONSTANT_VIEW_VALUE_FIELD = "IntegerConstantViewValueField";

        String INTEGER_CONSTANT_VIEW_LABEL_CELL = "IntegerConstantViewLabelCell";
        String INTEGER_CONSTANT_VIEW_DETAIL_CELL = "IntegerConstantViewDetailCell";
        String INTEGER_CONSTANT_VIEW_DESCRIPTION_CELL = "IntegerConstantViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the blob constant's views.
     */
    interface BlobConstantView {
        String BLOB_CONSTANT_VIEW_PANEL = "BlobConstantViewPanel";
        String BLOB_CONSTANT_VIEW_ID_FIELD = "BlobConstantViewIdField";
        String BLOB_CONSTANT_VIEW_NAME_FIELD = "BlobConstantViewNameField";

        String BLOB_CONSTANT_VIEW_LABEL_CELL = "BlobConstantViewLabelCell";
        String BLOB_CONSTANT_VIEW_DETAIL_CELL = "BlobConstantViewDetailCell";
        String BLOB_CONSTANT_VIEW_DESCRIPTION_CELL = "BlobConstantViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the tag's views.
     */
    interface TagView {
        String TAG_VIEW_PANEL = "TagViewPanel";
        String TAG_VIEW_ID_LABEL = "TagViewIDLabel";
        String TAG_VIEW_ID_TEXT = "TagViewIDText";
        String TAG_VIEW_NAME_LABEL = "TagViewNameLabel";
        String TAG_VIEW_NAME_TEXT = "TagViewNameText";
        String TAG_VIEW_DESCRIPTION_LABEL = "TagViewDescriptionLabel";
        String TAG_VIEW_DESCRIPTION_TEXT = "TagViewDescriptionText";

        String TAG_VIEW_LABEL_CELL = "TagViewLabelCell";
        String TAG_VIEW_DETAIL_CELL = "TagViewDetailCell";
        String TAG_VIEW_DESCRIPTION_CELL = "TagViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the category's views.
     */
    interface CategoryView {
        String CATEGORY_VIEW_PANEL = "CategoryViewPanel";
        String CATEGORY_VIEW_ID_LABEL = "CategoryViewIDLabel";
        String CATEGORY_VIEW_ID_TEXT = "CategoryViewIDText";
        String CATEGORY_VIEW_NAME_LABEL = "CategoryViewNameLabel";
        String CATEGORY_VIEW_NAME_TEXT = "CategoryViewNameText";
        String CATEGORY_VIEW_DESCRIPTION_LABEL = "CategoryViewDescriptionLabel";
        String CATEGORY_VIEW_DESCRIPTION_TEXT = "CategoryViewDescriptionText";

        String CATEGORY_VIEW_LABEL_CELL = "CategoryViewLabelCell";
        String CATEGORY_VIEW_DETAIL_CELL = "CategoryViewDetailCell";
        String CATEGORY_VIEW_DESCRIPTION_CELL = "CategoryViewDescriptionCell";
    }

    /**
     * Holds the styles that are applied to the project's views.
     */
    interface ProjectView {
        String PROJECT_VIEW_PANEL = "ProjectViewPanel";
        String PROJECT_VIEW_ID_LABEL = "ProjectViewIDLabel";
        String PROJECT_VIEW_ID_TEXT = "ProjectViewIDText";
        String PROJECT_VIEW_NAME_LABEL = "ProjectViewNameLabel";
        String PROJECT_VIEW_NAME_TEXT = "ProjectViewNameText";
        String PROJECT_VIEW_DESCRIPTION_LABEL = "ProjectViewDescriptionLabel";
        String PROJECT_VIEW_DESCRIPTION_TEXT = "ProjectViewDescriptionText";

        String PROJECT_VIEW_LABEL_CELL = "ProjectViewLabelCell";
        String PROJECT_VIEW_DETAIL_CELL = "ProjectViewDetailCell";
        String PROJECT_VIEW_DESCRIPTION_CELL = "ProjectViewDescriptionCell";
    }

    interface TranslatedTopicView {
        String TRANSLATED_TOPIC_VIEW_PANEL = "TranslatedTopicViewPanel";

    }

    interface ContentSpecView {
        String CONTENT_SPEC_ERRORS = "ContentSpecErrors";
        String CONTENT_SPEC_VIEW_PANEL = "ContentSpecViewPanel";
        String CONTENT_SPEC_VIEW_ID_FIELD = "ContentSpecViewIDField";
        String CONTENT_SPEC_VIEW_REVISION_NUMBER_FIELD = "ContentSpecViewRevisionNumberField";
        String CONTENT_SPEC_VIEW_LOCALE_FIELD = "ContentSpecViewLocaleField";
        /**
         * Assigned to the field that holds the content spec's detail endpoint url
         */
        String CONTENT_SPEC_VIEW_DETAILS_ENDPOINT_FIELD = "ContentSpecViewDetailsEndpointField";
        /**
         * Assigned to the field that holds the content spec's Text endpoint url
         */
        String CONTENT_SPEC_VIEW_TEXT_ENDPOINT_FIELD = "ContentSpecViewTextEndpointField";
        String CONTENT_SPEC_TEXT_VIEW_ACE_PANEL = "ContentSpecTextViewACEPanel";
        String CONTENT_SPEC_TEXT_VIEW_TEXT_FIELD = "ContentSpecTextViewTextField";

        String CONTENT_SPEC_VIEW_LABEL_CELL = "ContentSpecViewLabelCell";
        String CONTENT_SPEC_VIEW_DETAIL_CELL = "ContentSpecViewDetailCell";
        String ACTIONS_MENU = "ContentSpecActionsMenu";
        String ACTIONS_MENU_ITEM = "ContentSpecActionsMenuItem";
        String ACTIONS_SUB_MENU = "ContentSpecActionsSubMenu";
    }

    interface TopicRevisionView {
        String TOPIC_REVISION_BASE_PANEL = "TopicRevisionBasePanel";
        String TOPIC_REVISION_NUMBER_COLUMN = "TopicRevisionNumberColumn";
        String TOPIC_REVISION_DATE_COLUMN = "TopicRevisionDateColumn";
        String TOPIC_REVISION_MINOR_RELEASE_COLUMN = "TopicRevisionMinorReleaseColumn";
        String TOPIC_REVISION_MAJOR_RELEASE_COLUMN = "TopicRevisionMajorReleaseColumn";
        String TOPIC_REVISION_USER_COLUMN = "TopicRevisionUserColumn";
        String TOPIC_REVISION_MESSAGE_COLUMN = "TopicRevisionMessageColumn";
        String TOPIC_REVISION_VIEW_COLUMN = "TopicRevisionViewColumn";
        String TOPIC_REVISION_DIFF_COLUMN = "TopicRevisionDiffColumn";
        String TOPIC_REVISION_DIFF_PANEL = "TopicRevisionDiffPanel";
        String TOPIC_REVISION_DIFF_BUTTON_PANEL = "TopicRevisionDiffButtonPanel";
        String TOPIC_REVISION_HTML_DIFF_FRAME = "TopicRevisionHTMLDiffFrame";
        /**
         * Assigned to the spinner used for the loading widget
         */
        String TOPIC_REVISION_VIEW_SPINNER = "TopicRevisionViewSpinner";
        String TOPIC_REVISION_DIFF_PARENT_PANEL = "TopicRevisionDiffParentPanel";
        String TOPIC_REVISION_DIFF = "TopicRevisionDiff";
    }

    interface TopicContentSpecView {
        String TOPIC_CONTENT_SPEC_VIEW_PANEL = "TopicContentSpecViewPanel";
        String TOPIC_CONTENT_SPEC_VIEW_RESULTS = "TopicContentSpecViewResults";
        String TOPIC_CONTENT_SPEC_VIEW_ID_COLUMN = "TopicContentSpecViewIDColumn";
        String TOPIC_CONTENT_SPEC_VIEW_TITLE_COLUMN = "TopicContentSpecViewTitleColumn";
        String TOPIC_CONTENT_SPEC_VIEW_PRODUCT_COLUMN = "TopicContentSpecViewProductColumn";
        String TOPIC_CONTENT_SPEC_VIEW_VERSION_COLUMN = "TopicContentSpecViewVersionColumn";
        String TOPIC_CONTENT_SPEC_VIEW_BUTTON_COLUMN = "TopicContentSpecViewButtonColumn";
    }

    /**
     * Holds the styles that are applied to the topic's views.
     */
    interface TopicView {
        String XML_TAGS_LIST = "XmlTagsList";

        String TOPIC_VIEW_PANEL = "TopicViewPanel";
        String TOPIC_VIEW_ID_FIELD = "TopicViewIDField";
        String TOPIC_VIEW_REVISION_NUMBER_FIELD = "TopicViewRevisionNumberField";
        String TOPIC_VIEW_TITLE_FIELD = "TopicViewTitleField";
        String TOPIC_VIEW_XMLDOCTYPE_FIELD = "TopicViewXMLDocTypeField";
        /**
         * Assigned to the field that holds the topic's detail endpoint url
         */
        String TOPIC_VIEW_DETAILS_ENDPOINT_FIELD = "TopicViewDetailsEndpointField";
        /**
         * Assigned to the field that holds the topic's XML endpoint url
         */
        String TOPIC_VIEW_XML_ENDPOINT_FIELD = "TopicViewXMLEndpointField";
        String TOPIC_VIEW_WEBDAV_ENDPOINT_FIELD = "TopicViewWebDAVEndpointField";
        String TOPIC_VIEW_LOCALE_FIELD = "TopicViewLocaleField";
        String TOPIC_VIEW_DESCRIPTION_FIELD = "TopicViewDescriptionField";
        String TOPIC_VIEW_LABEL_CELL = "TopicViewLabelCell";
        String TOPIC_VIEW_DETAIL_CELL = "TopicViewDetailCell";
        String TOPIC_VIEW_DESCRIPTION_CELL = "TopicViewDescriptionCell";
        String TOPIC_VIEW_LAYOUT_PANEL = "TopicViewLayoutPanel";
        String TOPIC_RENDERED_VIEW_IFRAME = "TopicRenderedViewIFrame";
        String TOPIC_RENDERED_VIEW_IFRAME_TABLE = "TopicRenderedViewIFrameTable";
        String TOPIC_RENDERED_VIEW_ERROR_CELL = "TopicRenderedViewErrorCell";
        String TOPIC_RENDERED_VIEW_ERROR_LABEL = "TopicRenderedViewErrorLabel";
        String TOPIC_RENDERED_VIEW_IFRAME_TABLE_LOADING_CELL = "TopicRenderedViewIFrameTableLoadingCell";
        String TOPIC_RENDERED_VIEW_IFRAME_TABLE_DISPLAYING_CELL = "TopicRenderedViewIFrameTableDisplayingCell";
        String TOPIC_RENDERING_INFO_ENTITIES_CELL = "TopicRenderingInfoEntitiesCell";

        String TOPIC_RENDERING_OPTIONS_PARENT = "TopicRenderingOptionsParent";

        String TOPIC_RENDERING_OPTIONS_SPECS = "TopicRenderingOptionsSpecs";

        String TOPIC_XML_VIEW_PANEL = "TopicXMLViewPanel";
        String TOPIC_XML_ERRORS = "TopicXMLErrors";
        String TOPIC_XML_VIEW_ACE_PANEL = "TopicXMLViewACEPanel";
        String TOPIC_XML_VIEW_XML_FIELD = "TopicXMLViewXMLField";
        String TOPIC_XML_ERRORS_VIEW_PANEL = "TopicXMLErrorsViewPanel";
        String TOPIC_XML_ERRORS_VIEW_FIELD = "TopicXMLErrorsViewField";
        String TOPIC_VIEW_TAG_LABEL = "TopicViewTagLabel";
        String ACE_EDITOR_PARENT = "AceEditorParent";
        /**
         * Assigned to the FlexTable rows that list a category in the topic tags view.
         */
        String TOPIC_TAG_VIEW_CATEGORY_ROW = "TopicTagViewCategoryRow";
        /**
         * Assigned to the FlexTable rows that list a project in the topic tags view.
         */
        String TOPIC_TAG_VIEW_PROJECT_ROW = "TopicTagViewProjectRow";
        /**
         * Assigned to the FlexTable rows that list a tag in the topic tags view.
         */
        String TOPIC_TAG_VIEW_TAG_ROW = "TopicTagViewTagRow";
        /**
         * Assigned to the panel that holds all the TOPIC_TAG_VIEW_PROJECT_STABLE element in the topic tags view.
         */
        String TOPIC_TAG_VIEW_PARENT_PROJECTS_TABLE = "TopicTagViewParentProjectsTable";
        /**
         * Assigned to the table that holds all the projects, categories and tags in the topic tags view.
         */
        String TOPIC_TAG_VIEW_PROJECT_STABLE = "TopicTagViewProjectsTable";
        /**
         * Assigned to the table that holds a project in the topic tags view.
         */
        String TOPIC_TAG_VIEW_PROJECT_TABLE = "TopicTagViewProjectTable";
        /**
         * Assigned to the table that holds multiple categories in the topic tags view.
         */
        String TOPIC_TAG_VIEW_CATEGORIES_TABLE = "TopicTagViewCategoriesTable";
        /**
         * Assigned to the table that holds a category in the topic tags view.
         */
        String TOPIC_TAG_VIEW_CATEGORY_TABLE = "TopicTagViewCategoryTable";
        /**
         * Assigned to the table that holds multiple tags in the topic tags view.
         */
        String TOPIC_TAG_VIEW_TAG_STABLE = "TopicTagViewTagsTable";
        /**
         * Assigned to the table that holds the new tag project, category and tag lists.
         */
        String TOPIC_TAG_VIEW_NEW_TAG_TABLE = "TopicTagViewNewTagTable";
        /**
         * Assigned to the panel that holds the topic tag view.
         */
        String TOPIC_TAG_VIEW_CONTENT_PANEL = "TopicTagViewContentPanel";
        /**
         * Assigned to the list that holds the available projects in the topic tags view.
         */
        String TOPIC_TAG_VIEW_NEW_TAG_PROJECTS_LIST = "TopicTagViewNewTagProjectsList";
        /**
         * Assigned to the list that holds the available categories in the topic tags view.
         */
        String TOPIC_TAG_VIEW_NEW_TAG_CATEGORIES_LIST = "TopicTagViewNewTagCategoriesList";
        /**
         * Assigned to the list that holds the available tags in the topic tags view.
         */
        String TOPIC_TAG_VIEW_NEW_TAG_TAGS_LIST = "TopicTagViewNewTagTagsList";
        /**
         * Assigned to the panel that holds the new tag ui elements.
         */
        String TOPIC_TAG_VIEW_NEW_TAG_PARENT_PANEL = "TopicTagViewNewTagParentPanel";
        /**
         * Assigned to the panel that holds the revisions table and pager.
         */
        String SEARCH_RESULTS_PANEL = "SearchResultsPanel";

        /**
         * Assigned to the table that holds the split view menu.
         */
        String RENDERED_SPLIT_VIEW_MENU_TABLE = "RenderedSplitViewMenuTable";
        /**
         * Assigned to the spinner used for the loading widget
         */
        String TOPIC_TAG_VIEW_SPINNER = "TopicTagViewSpinner";
        /**
         * Assigned to the spinner used for the loading widget
         */
        String TOPIC_CONTENT_SPEC_VIEW_SPINNER = "TopicContentSpecViewSpinner";
    }

    /**
     * Holds the styles that are applied to the tag list category list view.
     */
    interface TagListCategoryView {
        String CATEGORIES_BUTTONS_LAYOUT = "CategoriesButtonsLayout";
        String CATEGORIES_LAYOUT = "CategoriesLayout";
        String CATEGORIES_SCROLL_PANEL = "CategoriesScrollPanel";
        String CATEGORY_TAG_LAYOUT = "CategoryTagLayout";
        String CATEGORY_TAG_SCROLL = "CategoryTagScroll";
    }

    /**
     * Holds the styles that are applied to the tag list projects list view.
     */
    interface TagListProjectsView {
        String PROJECTS_BUTTONS_LAYOUT = "ProjectsButtonsLayout";
        String PROJECTS_SCROLL_PANEL = "ProjectsScrollPanel";
        String PROJECTS_LAYOUT = "ProjectsLayout";
    }

    /**
     * Holds the styles that are applied to the tag list tags view.
     */
    interface TagListTagView {
        /**
         * Assigned to the tag labels.
         */
        String TAG_LABEL = "TagLabel";
    }

    /**
     * Holds the styles that are applied to the locales view.
     */
    interface LocaleList {
        /**
         * Assigned to the locale labels.
         */
        String LOCALE_LABEL = "LocaleLabel";
    }

    /**
     * Holds the styles that are applied to the images view.
     */
    interface ImageView {
        /**
         * Assigned to the dock panel that hosts the image description and the tab view of specific language images.
         */
        String IMAGE_VIEW_PARENT_DOCK_PANEL = "ImageViewParentDockPanel";
        /**
         * Assigned to the tab panel of specific language images.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_TAB_PANEL = "ImageViewLanguageImageTabPanel";
        /**
         * Assigned to the tab that shows a specific language image.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_TAB = "ImageViewLanguageImageTab";
        /**
         * Assigned to the cell that holds the language file name label.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_LABEL = "ImageViewLanguageImageFilenameLabel";
        /**
         * Assigned to the cell that holds the language file name text.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_FILENAME_TEXT = "ImageViewLanguageImageFilenameText";
        /**
         * Assigned to the cell that holds the language display label.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL = "ImageViewLanguageImageDisplayLabel";
        /**
         * Assigned to the cell that holds the language display image.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE = "ImageViewLanguageImageDisplayImage";
        /**
         * Assigned to the cells that hold the label for a set of properties
         */
        String IMAGE_VIEW_LABEL_CELL = "ImageViewLabelCell";
        /**
         * Assigned to the cells that holds the details for a set of properties.
         */
        String IMAGE_VIEW_DETAIL_CELL = "ImageViewDetailCell";
        /**
         * Assigned to the cell that holds the language display label cell.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_LABEL_CELL = "ImageViewLanguageImageDisplayLabelCell";
        /**
         * Assigned to the cell that holds the language display image cell.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_DISPLAY_IMAGE_CELL = "ImageViewLanguageImageDisplayImageCell";
        /**
         * Assigned to the image description label.
         */
        String IMAGE_VIEW_DESCRIPTION_LABEL = "ImageViewDescriptionLabel";
        /**
         * Assigned to the image description text.
         */
        String IMAGE_VIEW_DESCRIPTION_TEXT = "ImageViewDescriptionText";
        /**
         * Assigned to the image id text.
         */
        String IMAGE_VIEW_ID_TEXT = "ImageViewIDText";
        /**
         * Assigned to the image template text.
         */
        String IMAGE_VIEW_TEMPLATE_TEXT = "ImageViewTemplateText";
        /**
         * Assigned to the image description text cell.
         */
        String IMAGE_VIEW_DESCRIPTION_CELL = "ImageViewDescriptionCell";
        /**
         * Assigned to the cell that holds the image template text cells.
         */
        String IMAGE_VIEW_TEMPLATE_CELL = "ImageViewTemplateCell";
        /**
         * Assigned to the table that holds the image details.
         */
        String IMAGE_VIEW_DETAILS_TABLE = "ImageViewDetailsTable";
        /**
         * Assigned to the cell that holds the image upload label.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_LABEL_CELL = "ImageViewLanguageImageUploadLabelCell";
        /**
         * Assigned to the cell that holds the image upload buttons.
         */
        String IMAGE_VIEW_LANGUAGE_IMAGE_UPLOAD_BUTTONS_CELL = "ImageViewLanguageImageUploadButtonsCell";

        String IMAGE_TEMPLATES_TAB_PANEL = "ImageTemplatesTabPanel";
    }

    /**
     * Holds the styles that are applied to the files view.
     */
    interface FileView {
        /**
         * Assigned to the dock panel that hosts the file description and the tab view of specific language files.
         */
        String FILE_VIEW_PARENT_DOCK_PANEL = "FileViewParentDockPanel";
        /**
         * Assigned to the tab panel of specific language files.
         */
        String FILE_VIEW_LANGUAGE_FILE_TAB_PANEL = "FileViewLanguageFileTabPanel";
        /**
         * Assigned to the tab that shows a specific language file.
         */
        String FILE_VIEW_LANGUAGE_FILE_TAB = "FileViewLanguageFileTab";
        /**
         * Assigned to the cell that holds the file name label.
         */
        String FILE_VIEW_FILENAME_LABEL = "FileViewFilenameLabel";
        /**
         * Assigned to the cell that holds the file name text.
         */
        String FILE_VIEW_FILENAME_TEXT = "FileViewFilenameText";
        /**
         * Assigned to the cells that holds the labels.
         */
        String FILE_VIEW_LABEL_CELL = "FileViewLabelCell";
        /**
         * Assigned to the cells that holds the details.
         */
        String FILE_VIEW_DETAIL_CELL = "FileViewDetailCell";
        /**
         * Assigned to the cell that holds the file path label.
         */
        String FILE_VIEW_FILE_PATH_LABEL = "FileViewFilePathLabel";
        /**
         * Assigned to the cell that holds the file path text.
         */
        String FILE_VIEW_FILE_PATH_TEXT = "FileViewFilePathText";
        /**
         * Assigned to the cell that holds the full file path label.
         */
        String FILE_VIEW_FULL_FILE_PATH_LABEL = "FileViewFullFilePathLabel";
        /**
         * Assigned to the cell that holds the full file path text.
         */
        String FILE_VIEW_FULL_FILE_PATH_TEXT = "FileViewFullFilePathText";
        /**
         * Assigned to the file description label.
         */
        String FILE_VIEW_DESCRIPTION_LABEL = "FileViewDescriptionLabel";
        /**
         * Assigned to the file description text.
         */
        String FILE_VIEW_DESCRIPTION_TEXT = "FileViewDescriptionText";
        /**
         * Assigned to the file description text cell.
         */
        String FILE_VIEW_DESCRIPTION_CELL = "FileViewDescriptionCell";
        /**
         * Assigned to the table that holds the file details.
         */
        String FILE_VIEW_DETAILS_TABLE = "FileViewDetailsTable";
        /**
         * Assigned to the cell that holds the file upload label.
         */
        String FILE_VIEW_LANGUAGE_FILE_UPLOAD_LABEL_CELL = "FileViewLanguageFileUploadLabelCell";
        /**
         * Assigned to the cell that holds the file upload buttons.
         */
        String FILE_VIEW_LANGUAGE_FILE_UPLOAD_BUTTONS_CELL = "FileViewLanguageFileUploadButtonsCell";
        /**
         * Assigned to the file explode archive label.
         */
        String FILE_VIEW_EXPLODE_ARCHIVE_LABEL = "FileViewExplodeArchiveLabel";
        /**
         * Assigned to the cell that holds the language file file name label.
         */
        String FILE_VIEW_LANGUAGE_FILE_FILENAME_LABEL = "FileViewLanguageFileFilenameLabel";
        /**
         * Assigned to the cell that holds the language file file name text.
         */
        String FILE_VIEW_LANGUAGE_FILE_FILENAME_TEXT = "FileViewLanguageFileFilenameText";
    }

    /**
     * Holds the styles that are applied to UI elements used throughout the app.
     */
    interface Common {
        String CUSTOM_BUTTON_TEXT = "CustomButtonText";
        String CUSTOM_BUTTON_TAGS_INCLUDED = "CustomButtonTagsInclued";
        String CUSTOM_BUTTON_TAGS_EXCLUDED = "CustomButtonTagsExcluded";
        String CUSTOM_BUTTON_TEXT_BOLD = "CustomButtonTextBold";

        /**
         * Assigned to image buttons.
         */
        String TEXT_BUTTON = "TextButton";
        /**
         * Assigned to left.
         */
        String LEFT_TAB_LABEL = "LeftTabLabel";
        /**
         * Assigned to action buttons that are top tabs
         */
        String TOP_TAB_BUTTON = "TopTab";
        /**
         * Assigned to action button
         */
        String TOP_BUTTON = "TopButton";
        /**
         * Assigned to image buttons that are top tabs
         */
        String LEFT_TAB_BUTTON = "LeftTab";
        /**
         * Assigned to image buttons that open external links
         */
        String EXTERNAL_BUTTON = "ExternalLink";

        /**
         * Assigned to labels that represent down pushbuttons.
         */
        String DOWN_LABEL = "DownLabel";
        /**
         * Assigned to push buttons that are sub menus.
         */
        String SUB_MENU = "SubMenu";
        /**
         * Assigned to push buttons that have an error state.
         */
        String ERROR = "Error";
        /**
         * Assigned to push buttons that have an warning state.
         */
        String WARNING = "Warning";
        /**
         * Assigned to buttons that need to show some kind of alert status.
         */
        String ALERT_BUTTON = "AlertButton";
        /**
         * Assigned to the panel that holds the OK and Cancel buttons in a popup dialog box.
         */
        String DIALOG_BOX_OK_CANCEL_PANEL = "DialogBoxOKCancelPanel";

        String CUSTOM_BUTTON = "CustomButton";
        String CUSTOM_BUTTON_DOWN = "CustomButtonDown";
    }

    public interface Shortcut {
        /**
         * Assigned to the panel that holds the shortcut panels.
         */
        String SHORTCUT_PANEL_PARENT = "ShortcutPanelParent";
        /**
         * Assigned to the vertical panel holding the shortcut buttons on the left of the screen.
         */
        String SHORTCUT_PANEL = "ShortcutPanel";
        /**
         * Assigned to shortcut menus that can be collapsed.
         */
        String SHORTCUT_COLLAPSE_MENU = "ShortcutCollapseMenu";
        /**
         * Assigned to the shortcut menus header, for collapsible menus.
         */
        String SHORTCUT_COLLAPSE_MENU_HEADER = "ShortcutCollapseMenuHeader";
        /**
         * Assigned to the shortcut menus content, for collapsible menus.
         */
        String SHORTCUT_COLLAPSE_MENU_CONTENT = "ShortcutCollapseMenuContent";
    }

    interface ProcessLogsDialog {
        String LOGS_DIALOG_BOX = "ProcessLogsDialog";
        String LOGS_FIELD = "ProcessLogsText";
    }

    interface ProcessView {
        String PROCESS_PANEL = "ProcessPanel";
        String TAB_PANEL = "ProcessTabPanel";
        String RESULTS_PANEL = "ProcessResultsPanel";
        String RESULTS_TABLE = "ProcessResultsTable";
        String RESULTS_TABLE_ID_COLUMN = "ProcessResultsTableIdColumn";
        String RESULTS_TABLE_LOGS_COLUMN = "ProcessResultsTableLogsColumn";
        String RESULTS_TABLE_STATUS_COLUMN = "ProcessResultsTableStatusColumn";
        String RESULTS_TABLE_STARTED_BY_COLUMN = "ProcessResultsTableStartedByColumn";
        String RESULTS_TABLE_DATE_COLUMN = "ProcessResultsTableDateColumn";
        String RESULTS_TABLE_CANCEL_COLUMN = "ProcessResultsTableCancelColumn";
        String RESULTS_TABLE_SELECTED_ROW = "ProcessResultsTableSelectedRow";
        String SUCCESSFUL_STATUS = "SuccessfulStatus";
        String FAILED_STATUS = "FailedStatus";
        String ACTION_IMAGE = "ProcessActionImage";
    }

    interface ContentSpecActionsView {
        String WAITING_TABLE = "ProcessWaitingTable";
        String WAITING_MESSAGE = "ProcessWaitingMessage";
        String WAITING_LABEL = "ProcessWaitingLabel";
        String WAITING_BUTTON_PANEL = "ProcessWaitingButtonPanel";
        String SYNC_LOCALE_LIST_BOX = "ProcessSyncLocaleListBox";
    }

    interface FreezePreviewDialog {
        String PREVIEW_DIALOG_BOX = "FreezePreviewDialog";
        String PREVIEW_FIELD = "FreezePreviewText";
    }

    interface SettingsView {
        String LOCALES_TABLE = "SettingsLocaleTable";
        String LOCALES_CELL = "SettingsLocaleCell";
        String LOCALES_CELL_LIST = "SettingsLocaleCellList";
        String LOCALES_CELL_SCROLL = "SettingsLocaleCellScroll";
        String DISCLOSURE_PANEL = "SettingsDisclosurePanel";
        String ENTITIES_LABEL = "SettingsEntitiesLabel";
        String ENTITIES_FIELD = "SettingsEntitiesField";
        String ENTITIES_TABLE = "SettingsEntitiesTable";
        String SETTINGS_PANEL = "SettingsPanel";
        String SETTINGS_ACTION_PANEL = "SettingsActionPanel";
        String SETTINGS_TABLE = "SettingsTable";
        String SETTINGS_LABEL = "SettingsLabel";
        String SETTINGS_FIELD = "SettingsField";
        String ZANATA_TABLE = "SettingsZanataTable";
        String ZANATA_ID_FOOTER = "SettingsZanataIDFooter";
        String ZANATA_NAME_FOOTER = "SettingsZanataNameFooter";
        String ZANATA_URL_FOOTER = "SettingsZanataURLFooter";
        String ZANATA_PROJECT_FOOTER = "SettingsZanataProjectFooter";
        String ZANATA_PROJECT_VERSION_FOOTER = "SettingsZanataProjectVersionFooter";
        String UNDEFINED_TABLE = "SettingsUndefinedTable";
        String KEY_FOOTER = "SettingsKeyFooter";
        String VALUE_FOOTER = "SettingsValueFooter";
        String OTHER_CELL_LIST = "SettingsOtherCellList";
        String OTHER_CELL_SCROLL = "SettingsOtherCellScroll";
        String OTHER_CELL = "SettingsOtherCell";
    }

    public interface TopicSourceURLView {
        String TOPIC_SOURCE_URL_RESULTS = "TopicSourceURLResults";
        String TOPIC_SOURCE_URL_RESULTS_URL_COLUMN = "TopicSourceURLResultsURLField";
        String TOPIC_SOURCE_URL_RESULTS_TITLE_COLUMN = "TopicSourceURLResultsTitleField";
    }
}
