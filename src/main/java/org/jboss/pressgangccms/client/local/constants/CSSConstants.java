package org.jboss.pressgangccms.client.local.constants;

/**
 * This class lists all of the CSS class names used by the application. The
 * nested classes are just used to group the styles assigned to particular views.
 * 
 * @author Matthew Casperson
 * 
 */
public interface CSSConstants
{
	public interface TagView
	{
		String TAGVIEWPANEL = "TagViewPanel";
		String TAGVIEWIDLABEL = "TagViewIDLabel";
		String TAGVIEWIDTEXT = "TagViewIDText";
		String TAGVIEWNAMELABEL = "TagViewNameLabel";
		String TAGVIEWNAMETEXT = "TagViewNameText";
		String TAGVIEWDESCRIPTIONLABEL = "TagViewDescriptionLabel";
		String TAGVIEWDESCRIPTIONTEXT = "TagViewDescriptionText";
		
		String TAGVIEWIDLABELCELL = "TagViewIDLabelCell";
		String TAGVIEWIDTEXTCELL = "TagViewIDTextCell";
		String TAGVIEWNAMELABELCELL = "TagViewNameLabelCell";
		String TAGVIEWNAMETEXTCELL = "TagViewNameTextCell";
		String TAGVIEWDESCRIPTIONLABELCELL = "TagViewDescriptionLabelCell";
		String TAGVIEWDESCRIPTIONTEXTCELL = "TagViewDescriptionTextCell";
	}
	
	public interface TagFilteredResultsView
	{
		/** Assigned to the panel that holds the list of images */
		String TAGFILTEREDRESULTSPANEL = "TagFilteredResultsPanel";
		/** Assigned to the panel that holds the filter options for images */
		String TAGFILTEREDOPTIONSPANEL = "TagFilteredOptionsPanel";
	}
	
	public interface TagCategoryView
	{
		String TAGCATEGORIESLISTPANEL = "TagCategoriesListPanel";
		String TAGCATEGORYTAGSLISTPANEL = "TagCategoryTagsListPanel";
		String TAGCATEGORIESSPLITPANEL = "TagCategoriesSplitPanel";
	}

	/**
	 * The class assigned to the page title label.
	 */
	String PAGETITLE = "PageTitle";
	/**
	 * The class assigned to the top level DockLayoutPanel.
	 */
	String TOPLEVELLAYOUTPANEL = "TopLevelLayoutPanel";
	String SECONDLEVELLAYOUTPANEL = "SecondLevelLayoutPanel";
	String THIRDLEVELLAYOUTPANEL = "ThirdLevelLayoutPanel";
	String CONTENTLAYOUTPANEL = "ContentLayoutPanel";
	String TOPACTIONPANEL = "TopActionPanel";
	String SHORTCUTPANEL = "ShortcutPanel";
	/**
	 * Assigned to the panel that holds the shortcut panels
	 */
	String SHORTCUTPANELPARENT = "ShortcutPanelParent";
	String FOOTERPANEL = "FooterPanel";
	String RIGHTALIGNEDACTIONBUTTONS = "RightAlignedActionButtons";
	String PAGETITLEPARENTLAYOUTPANEL = "PageTitleParentLayoutPanel";
	String RESULTSVIEWLAYOUTPANEL = "ResultsViewLayoutPanel";
	String TOPICVIEWLAYOUTPANEL = "TopicViewLayoutPanel";
	String TOPICSEARCHTOPICVIEWBUTTONSPANEL = "TopicSearchTopicViewButtonsPanel";
	String TOPICSEARCHTOPICVIEWDETAILSPANEL = "TopicSearchTopicViewDetailsPanel";
	String TOPICSEARCHRESULTSANDVIEWPARENTPANEL = "TopicSearchResultsAndViewParentPanel";
	String TOPICRENDEREDVIEWDIV = "TopicRenderedViewDiv";
	String SEARCHRESULTSPANEL = "SearchResultsPanel";
	String TOPICVIEWPANEL = "TopicViewPanel";
	String TOPICVIEWIDFIELD = "TopicViewIDField";
	String TOPICVIEWREVISIONNUMBERFIELD = "TopicViewRevisionNumberField";
	String TOPICVIEWTITLEFIELD = "TopicViewTitleField";
	String TOPICVIEWLOCALEFIELD = "TopicViewLocaleField";
	String TOPICVIEWDESCRIPTIONFIELD = "TopicViewDescriptionField";
	String TOPICVIEWLABEL = "TopicViewLabel";
	String TOPICVIEWDETAIL = "TopicViewDetail";
	String TOPICVIEWDESCRIPTIONDETAIL = "TopicViewDescriptionDetail";
	String SPACEDBUTTON = "SpacedButton";
	String PROJECTSLAYOUT = "ProjectsLayout";
	String PROJECTLAYOUT = "ProjectLayout";
	String PROJECTSBUTTONSLAYOUT = "ProjectsButtonsLayout";
	String PROJECTSSCROLLPANEL = "ProjectsScrollPanel";
	String CUSTOMBUTTON = "CustomButton";
	String CUSTOMBUTTONDOWN = "CustomButtonDown";
	String CATEGORIESBUTTONSLAYOUT = "CategoriesButtonsLayout";
	String CATEGORIESLAYOUT = "CategoriesLayout";
	String CATEGORIESSCROLLPANEL = "CategoriesScrollPanel";
	String CATEGORYTAGLAYOUT = "CategoryTagLayout";
	String CATEGORYTAGSCROLL = "CategoryTagScroll";
	String TAGLABEL = "TagLabel";
	String CUSTOMBUTTONTEXT = "CustomButtonText";
	String CUSTOMBUTTONTAGSINCLUDED = "CustomButtonTagsInclued";
	String CUSTOMBUTTONTAGSEXCLUDED = "CustomButtonTagsExcluded";
	String CUSTOMBUTTONTEXTBOLD = "CustomButtonTextBold";
	String TOPICXMLVIEWPANEL = "TopicXMLViewPanel";
	String TOPICXMLVIEWACEPANEL = "TopicXMLViewACEPanel";
	String TOPICXMLVIEWXMLFIELD = "TopicXMLViewXMLField";
	String TOPICXMLERRORSVIEWPANEL = "TopicXMLErrorsViewPanel";
	String TOPICXMLERRORSVIEWFIELD = "TopicXMLErrorsViewField";
	String TOPICVIEWTAGLABEL = "TopicViewTagLabel";
	/**
	 * Assigned to the FlexTable rows that list a category in the topic tags
	 * view.
	 */
	String TOPICTAGVIEWCATEGORYROW = "TopicTagViewCategoryRow";
	/**
	 * Assigned to the FlexTable rows that list a project in the topic tags view.
	 */
	String TOPICTAGVIEWPROJECTROW = "TopicTagViewProjectRow";
	/** Assigned to the FlexTable rows that list a tag in the topic tags view. */
	String TOPICTAGVIEWTAGROW = "TopicTagViewTagRow";
	/**
	 * Assigned to the panel that holds all the TOPICTAGVIEWPROJECTSTABLE
	 * element in the topic tags view
	 */
	String TOPICTAGVIEWPARENTPROJECTSTABLE = "TopicTagViewParentProjectsTable";
	/**
	 * Assigned to the table that holds all the projects, categories and tags in
	 * the topic tags view.
	 */
	String TOPICTAGVIEWPROJECTSTABLE = "TopicTagViewProjectsTable";
	/** Assigned to the table that holds a project in the topic tags view. */
	String TOPICTAGVIEWPROJECTTABLE = "TopicTagViewProjectTable";
	/**
	 * Assigned to the table that holds multiple categories in the topic tags
	 * view.
	 */
	String TOPICTAGVIEWCATEGORIESTABLE = "TopicTagViewCategoriesTable";
	/** Assigned to the table that holds a category in the topic tags view. */
	String TOPICTAGVIEWCATEGORYTABLE = "TopicTagViewCategoryTable";
	/** Assigned to the table that holds multiple tags in the topic tags view. */
	String TOPICTAGVIEWTAGSTABLE = "TopicTagViewTagsTable";
	/**
	 * Assigned to the table that holds the new tag project, category and tag
	 * lists.
	 */
	String TOPICTAGVIEWNEWTAGTABLE = "TopicTagViewNewTagTable";
	/** Assigned to the panel that holds the topic tag view. */
	String TOPICTAGVIEWCONTENTPANEL = "TopicTagViewContentPanel";
	/**
	 * Assigned to the list that holds the available projects in the topic tags
	 * view.
	 */
	String TOPICTAGVIEWNEWTAGPROJECTSLIST = "TopicTagViewNewTagProjectsList";
	/**
	 * Assigned to the list that holds the available categories in the topic
	 * tags view.
	 */
	String TOPICTAGVIEWNEWTAGCATEGORIESLIST = "TopicTagViewNewTagCategoriesList";
	/**
	 * Assigned to the list that holds the available tags in the topic tags view.
	 */
	String TOPICTAGVIEWNEWTAGTAGSLIST = "TopicTagViewNewTagTagsList";
	/** Assigned to the panel that holds the new tag ui elements. */
	String TOPICTAGVIEWNEWNEWTAGPARENTPANEL = "TopicTagViewNewTagParentPanel";
	/**
	 * Assigned to the dock panel that hosts the image description and the tab
	 * view of specific language images.
	 */
	String IMAGEVIEWPARENTDOCKPANEL = "ImageViewParentDockPanel";
	/** Assigned to the tab panel of specific language images. */
	String IMAGEVIEWLANGUAGEIMAGETABPANEL = "ImageViewLanguageImageTabPanel";
	/** Assigned to the tab that shows a specific language image. */
	String IMAGEVIEWLANGUAGEIMAGETAB = "ImageViewLanguageImageTab";
	/** Assigned to the cell that holds the language file name label. */
	String IMAGEVIEWLANGUAGEIMAGEFILENAMELABEL = "ImageViewLanguageImageFilenameLabel";
	/** Assigned to the cell that holds the language file name text. */
	String IMAGEVIEWLANGUAGEIMAGEFILENAMETEXT = "ImageViewLanguageImageFilenameText";
	/** Assigned to the cell that holds the language display label. */
	String IMAGEVIEWLANGUAGEIMAGEDISPLAYLABEL = "ImageViewLanguageImageDisplayLabel";
	/** Assigned to the cell that holds the language display image. */
	String IMAGEVIEWLANGUAGEIMAGEDISPLAYIMAGE = "ImageViewLanguageImageDisplayImage";
	/** Assigned to the cell that holds the language file name label cell. */
	String IMAGEVIEWLANGUAGEIMAGEFILENAMELABELCELL = "ImageViewLanguageImageFilenameLabelCell";
	/** Assigned to the cell that holds the language file name text cell. */
	String IMAGEVIEWLANGUAGEIMAGEFILENAMETEXTCELL = "ImageViewLanguageImageFilenameTextCell";
	/** Assigned to the cell that holds the language display label cell. */
	String IMAGEVIEWLANGUAGEIMAGEDISPLAYLABELCELL = "ImageViewLanguageImageDisplayLabelCell";
	/** Assigned to the cell that holds the language display image cell. */
	String IMAGEVIEWLANGUAGEIMAGEDISPLAYIMAGECELL = "ImageViewLanguageImageDisplayImageCell";
	/** Assigned to the image description label. */
	String IMAGEVIEWDESCRIPTIONLABEL = "ImageViewDescriptionLabel";
	/** Assigned to the image description text. */
	String IMAGEVIEWDESCRIPTIONTEXT = "ImageViewDescriptionText";
	/** Assigned to the image template text. */
	String IMAGEVIEWTEMPLATETEXT = "ImageViewTemplateText";
	/** Assigned to the image description label cell. */
	String IMAGEVIEWDESCRIPTIONLABELCELL = "ImageViewDescriptionLabelCell";
	/** Assigned to the image description text cell. */
	String IMAGEVIEWDESCRIPTIONTEXTCELL = "ImageViewDescriptionTextCell";
	/** Assigned to the cell that holds the image template label. */
	String IMAGEVIEWTEMPLATELABELCELL = "ImageViewTemplateLabelCell";
	/** Assigned to the cell that holds the image template text. */
	String IMAGEVIEWTEMPLATETEXTCELL = "ImageViewTemplateTextCell";
	/** Assigned to the image docbook file name label cell. */
	String IMAGEVIEWDOCBOOKFILENAMELABELCELL = "ImageViewDocbookFileNameLabelCell";
	/** Assigned to the image docbook file name text cell. */
	String IMAGEVIEWDOCBOOKFILENAMETEXTCELL = "ImageViewDocbookFileNameTextCell";
	/** Assigned to the table that holds the image details. */
	String IMAGEVIEWDETAILSTABLE = "ImageViewDetailsTable";
	/** Assigned to image buttons. */
	String TEXTBUTTON = "TextButton";
	/** Assigned to text buttons. */
	String IMAGEBUTTON = "ImageButton";
	/** Assigned to the table that holds the split view menu. */
	String RENDEREDSPLITVIEWMENUTABLE = "RenderedSplitViewMenuTable";
	/** Assigned to labels that represent down pushbuttons. */
	String DOWNLABEL = "DownLabel";
	/** Assigned to push buttons that are sub menus. */
	String SUBMENU = "SubMenu";
	/** Assigned to buttons that need to show some kind of alert status. */
	String ALERTBUTTON = "AlertButton";
	/**
	 * Assigned to the panel that holds the OK and Cancel buttons in a popup
	 * dialog box.
	 */
	String DIALOGBOXOKCANCELPANEL = "DialogBoxOKCancelPanel";
	/** Assigned to the cell that holds the image upload label. */
	String IMAGEVIEWLANGUAGEIMAGEUPLOADLABELCELL = "ImageViewLanguageImageUploadLabelCell";
	/** Assigned to the cell that holds the image upload buttons. */
	String IMAGEVIEWLANGUAGEIMAGEUPLOADBUTTONSCELL = "ImageViewLanguageImageUploadButtonsCell";
	/** Assigned to the panel that holds the list of images. */
	String IMAGEFILTEREDRESULTSPANEL = "ImageFilteredResultsPanel";
	/** Assigned to the panel that holds the filter options for images. */
	String IMAGEFILTEREDOPTIONSPANEL = "ImageFilteredOptionsPanel";
}
