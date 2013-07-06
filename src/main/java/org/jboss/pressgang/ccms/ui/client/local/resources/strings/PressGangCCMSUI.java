package org.jboss.pressgang.ccms.ui.client.local.resources.strings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import org.jetbrains.annotations.NotNull;

/**
 * This interface contains all the strings displayed by the UI. These strings can then be localized with the functionality
 * provided by GWT.
 *
 * @author Matthew Casperson
 */
public interface PressGangCCMSUI extends Constants {

    /**
     * An instance of this interface constructed by GWT with the localized strings.
     */
    PressGangCCMSUI INSTANCE = GWT.create(PressGangCCMSUI.class);

    @NotNull
    String Done();

    @NotNull
    String Close();

    @NotNull
    String ImageTooLarge();

    @NotNull
    String NoXMLErrors();

    @NotNull
    String XMLErrors();

    @NotNull
    String LoadingText();

    @NotNull
    String Loading();

    @NotNull
    String PleaseSaveChangesBeforeUploading();

    @NotNull
    String ImportedTopic();

    @NotNull
    String OpenImportedTopics();

    @NotNull
    String BulkTopicImport();

    @NotNull
    String BulkTopicOverwrite();

    @NotNull
    String ATOMFeed();

    @NotNull
    String BulkTopicOverwriteMessage();

    @NotNull
    String HideSearchResults();

    @NotNull
    String ShowSearchResults();

    @NotNull
    String SelectFiles();

    @NotNull
    String CommonDescription();

    @NotNull
    String ImageUplodedSuccessfully();

    @NotNull
    String ImagesUplodedSuccessfully();

    @NotNull
    String ImagesNotUplodedSuccessfully();

    @NotNull
    String TopicsUplodedSuccessfully();

    @NotNull
    String TopicsNotUplodedSuccessfully();

    @NotNull
    String OverwriteFilenameErrorMessage();

    @NotNull
    String NoFilesSelected();

    @NotNull
    String And();

    @NotNull
    String Or();

    @NotNull
    String InternalLogic();

    @NotNull
    String ExternalLogic();

    @NotNull
    String ApplicationUpdated();

    @NotNull
    String New();

    @NotNull
    String Build();

    @NotNull
    String OK();

    @NotNull
    String Cancel();

    @NotNull
    String MoreInfo();

    @NotNull
    String PressGangCCMS();

    @NotNull
    String Home();

    @NotNull
    String DocBuilder();

    @NotNull
    String Welcome();

    @NotNull
    String Search();

    @NotNull
    String SearchTopics();

    @NotNull
    String SearchContentSpecs();

    @NotNull
    String DownloadZip();

    @NotNull
    String DownloadCSV();

    @NotNull
    String ApplyBulkTags();

    @NotNull
    String QuickSearch();

    @NotNull
    String Create();

    @NotNull
    String SearchFields();

    @NotNull
    String SearchLocales();

    @NotNull
    String Common();

    @NotNull
    String Troubleshooting();

    @NotNull
    String PleaseWait();

    @NotNull
    String CategoryCount();

    @NotNull
    String Included();

    @NotNull
    String Excluded();

    @NotNull
    String TagCount();

    @NotNull
    String SearchResults();

    @NotNull
    String TopicView();

    @NotNull
    String TopicID();

    @NotNull
    String TranslatedTopicID();

    @NotNull
    String TopicIDs();

    @NotNull
    String TopicCreated();

    @NotNull
    String TopicLastModified();

    @NotNull
    String TopicRevision();

    @NotNull
    String TopicTitle();

    @NotNull
    String TopicLocale();

    @NotNull
    String TopicDescription();

    @NotNull
    String TopicXML();

    @NotNull
    String TopicDetailsRESTEndpoint();

    @NotNull
    String TopicWebDAV();

    @NotNull
    String TopicXMLRESTEndpoint();

    @NotNull
    String ContentSpecDetailsRESTEndpoint();

    @NotNull
    String ContentSpecTextRESTEndpoint();

    @NotNull
    String SaveSuccess();

    @NotNull
    String TopicSaveSuccessWithID();

    @NotNull
    String ContentSpecSaveSuccessWithID();

    @NotNull
    String OverwriteSuccess();

    @NotNull
    String NoUnsavedChanges();

    @NotNull
    String TagSaveSuccess();

    @NotNull
    String ErrorGettingTopics();

    @NotNull
    String ErrorGettingTopic();

    @NotNull
    String ErrorSavingTopic();

    @NotNull
    String ErrorGettingTags();

    @NotNull
    String TopicCouldNotBeRendered();

    @NotNull
    String TagAlreadyExists();

    @NotNull
    String RemoveConflictingTags();

    @NotNull
    String BugzillaID();

    @NotNull
    String BugzillaSummary();

    @NotNull
    String IsOpen();

    @NotNull
    String Link();

    @NotNull
    String RevisionNumber();

    @NotNull
    String RevisionDate();

    @NotNull
    String RevisionMessage();

    @NotNull
    String Username();

    @NotNull
    String View();

    @NotNull
    String Diff();

    @NotNull
    String HTMLDiff();

    @NotNull
    String NoXML();

    @NotNull
    String SameXML();

    @NotNull
    String CurrentlyViewing();

    @NotNull
    String Edit();

    @NotNull
    String CurrentlyEditing();

    @NotNull
    String RenderedView();

    @NotNull
    String XMLEditing();

    @NotNull
    String XMLValidationErrors();

    @NotNull
    String SpecValidationErrors();

    @NotNull
    String Properties();

    @NotNull
    String Filters();

    @NotNull
    String Bugs();

    @NotNull
    String Revisions();

    @NotNull
    String ContentSpecifications();

    @NotNull
    String TopicTags();

    @NotNull
    String SearchTranslations();

    @NotNull
    String Reports();

    @NotNull
    String CreateBug();

    @NotNull
    String RenderedPane();

    @NotNull
    String HorizontalSplit();

    @NotNull
    String VerticalSplit();

    @NotNull
    String NoSplit();

    @NotNull
    String CloseSubMenu();

    @NotNull
    String Save();

    @NotNull
    String ImageDescription();

    @NotNull
    String ImageLocale();

    @NotNull
    String ImageFilename();

    @NotNull
    String ImageDimensions();

    @NotNull
    String ImageSample();

    @NotNull
    String Images();

    @NotNull
    String ImagePlaceholder();

    @NotNull
    String LineWrap();

    @NotNull
    String ShowHiddenCharacters();

    @NotNull
    String Add();

    @NotNull
    String Remove();

    @NotNull
    String AddLocale();

    @NotNull
    String RemoveLocale();

    @NotNull
    String ViewImage();

    @NotNull
    String DownloadImage();

    @NotNull
    String FindTopics();

    @NotNull
    String Locales();

    @NotNull
    String NotSpecified();

    @NotNull
    String UploadFile();

    @NotNull
    String Upload();

    @NotNull
    String PleaseUseFirefox();

    @NotNull
    String ConnectionError();

    @NotNull
    String InvalidInput();

    @NotNull
    String ConfirmDelete();

    @NotNull
    String DocbookFilename();

    @NotNull
    String ImageID();

    @NotNull
    String BulkTopicUpload();

    @NotNull
    String BulkImageUpload();

    @NotNull
    String DocbookImageTemplate();

    @NotNull
    String DocbookInlineImageTemplate();

    @NotNull
    String DocbookBareImageTemplate();

    @NotNull
    String Projects();

    @NotNull
    String StringConstants();

    @NotNull
    String BulkTagging();

    @NotNull
    String BlobConstants();

    @NotNull
    String IntegerConstants();

    @NotNull
    String Users();

    @NotNull
    String Roles();

    @NotNull
    String PropertyTags();

    @NotNull
    String PropertyTagName();

    @NotNull
    String PropertyTagDescription();

    @NotNull
    String PropertyTagValue();

    @NotNull
    String PropertyTagCategories();

    @NotNull
    String Monitoring();

    @NotNull
    String CreateTopic();

    @NotNull
    String CreateContentSpec();

    @NotNull
    String Tags();

    @NotNull
    String Categories();

    @NotNull
    String Advanced();

    @NotNull
    String TagID();

    @NotNull
    String TagIDs();

    @NotNull
    String TagName();

    @NotNull
    String TagSearchState();

    @NotNull
    String TagBulkUpdateState();

    @NotNull
    String ChildTagID();

    @NotNull
    String ChildTagName();

    @NotNull
    String TagDescription();

    @NotNull
    String ImageOriginalFileName();

    @NotNull
    String ProjectName();

    @NotNull
    String ProjectID();

    @NotNull
    String ProjectIDs();

    @NotNull
    String ProjectDescription();

    @NotNull
    String ProjectDetails();

    @NotNull
    String ProjectTags();

    @NotNull
    String NoAction();

    @NotNull
    String AddRemove();

    @NotNull
    String TagDetails();

    @NotNull
    String TagProjects();

    @NotNull
    String TagCategories();

    @NotNull
    String CategoryID();

    @NotNull
    String CategoryIDs();

    @NotNull
    String CategoryName();

    @NotNull
    String CategoryDescription();

    @NotNull
    String CategoryDetails();

    @NotNull
    String CategoryTags();

    @NotNull
    String Up();

    @NotNull
    String Down();

    @NotNull
    String Top();

    @NotNull
    String Bottom();

    @NotNull
    String UnsavedChangesPrompt();

    @NotNull
    String Feedback();

    @NotNull
    String Help();

    @NotNull
    String TopicsEditedInLastXDays();

    @NotNull
    String TopicsNotEditedInLastXDays();

    @NotNull
    String TopicCreatedAfter();

    @NotNull
    String TopicCreatedBefore();

    @NotNull
    String TopicEditedAfter();

    @NotNull
    String TopicEditedBefore();

    @NotNull
    String TopicIds();

    @NotNull
    String NotTopicIds();

    @NotNull
    String NotTopicTitle();

    @NotNull
    String TopicContents();

    @NotNull
    String NotTopicContents();

    @NotNull
    String NotTopicDescription();

    @NotNull
    String IncludedInContentSpec();

    @NotNull
    String NotIncludedInContentSpec();

    @NotNull
    String FreeTextSearch();

    @NotNull
    String HasBugzillaBugs();

    @NotNull
    String HasOpenBugzillaBugs();

    @NotNull
    String MatchAll();

    @NotNull
    String MatchAny();

    @NotNull
    String Fields();

    @NotNull
    String NoTitle();

    @NotNull
    String MinorChange();

    @NotNull
    String MajorChange();

    @NotNull
    String SaveLog();

    @NotNull
    String Message();

    @NotNull
    String PleaseEnterValue();

    @NotNull
    String InsertXMLElement();

    @NotNull
    String InsertCSPTopicDetails();

    @NotNull
    String InitialTopicCreation();

    @NotNull
    String SpellChecking();

    @NotNull
    String ImageUploadFailure();

    @NotNull
    String TopicSourceUrls();

    @NotNull
    String URLTitle();

    @NotNull
    String URL();

    @NotNull
    String ProductionServer();

    @NotNull
    String LocalServer();

    @NotNull
    String DevelopmentServer();

    @NotNull
    String OtherServer();

    @NotNull
    String OpenURL();

    @NotNull
    String NewURLTitle();

    @NotNull
    String NewURLLink();

    @NotNull
    String FilterName();

    @NotNull
    String FilterId();

    @NotNull
    String FilterDescription();

    @NotNull
    String Overwrite();

    @NotNull
    String Load();

    @NotNull
    String LoadAndSearch();

    @NotNull
    String CreateFilter();

    @NotNull
    String CreateCategory();

    @NotNull
    String CreateProject();

    @NotNull
    String CreateImage();

    @NotNull
    String CreateTag();

    @NotNull
    String StringConstantId();

    @NotNull
    String StringConstantName();

    @NotNull
    String StringConstantValue();

    @NotNull
    String StringConstantDetails();

    @NotNull
    String CreateStringConstant();

    @NotNull
    String IntegerConstantId();

    @NotNull
    String IntegerConstantName();

    @NotNull
    String IntegerConstantValue();

    @NotNull
    String IntegerConstantDetails();

    @NotNull
    String CreateIntegerConstant();

    @NotNull
    String BlobConstantId();

    @NotNull
    String BlobConstantName();

    @NotNull
    String BlobConstantValue();

    @NotNull
    String BlobConstantDetails();

    @NotNull
    String CreateBlobConstant();

    @NotNull
    String ExtendedPropertyID();

    @NotNull
    String ExtendedPropertyName();

    @NotNull
    String ExtendedPropertyDescription();

    @NotNull
    String ExtendedPropertyRegex();

    @NotNull
    String ExtendedPropertyCanBeNull();

    @NotNull
    String ExtendedPropertyUnique();

    @NotNull
    String ExtendedPropertyCategoryID();

    @NotNull
    String ExtendedPropertyCategoryName();

    @NotNull
    String ExtendedPropertyCategoryDescription();

    @NotNull
    String CreateExtendedPropertyCategory();

    @NotNull
    String ExtendedPropertyCategoryDetails();

    @NotNull
    String ExtendedPropertyCategoryExtendedProperties();

    @NotNull
    String ExtendedPropertyCategories();

    @NotNull
    String ExtendedProperties();

    @NotNull
    String CreateExtendedProperty();

    @NotNull
    String ThisOperationWillModify();

    @NotNull
    String AreYouSureYouWishToContinue();

    @NotNull
    String Topics();

    @NotNull
    String NoTopicsFound();

    @NotNull
    String BulkTagUpdateLogMessage();

    @NotNull
    String AllTopicsUpdatedSuccessfully();

    @NotNull
    String TopicsWereNotUpdatedCorrectly();

    @NotNull
    String NoBulkTagsSelected();

    @NotNull
    String ContentSpecTextEdit();

    @NotNull
    String ContentSpecID();

    @NotNull
    String ContentSpecTitle();

    @NotNull
    String ContentSpecRevision();

    @NotNull
    String ContentSpecLastModified();

    @NotNull
    String ContentSpecLocale();

    @NotNull
    String ContentSpecDetails();

    @NotNull
    String ContentSpecText();

    @NotNull
    String ContentSpecTags();
}
