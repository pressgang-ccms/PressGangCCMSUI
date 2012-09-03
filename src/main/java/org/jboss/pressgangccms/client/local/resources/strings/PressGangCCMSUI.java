package org.jboss.pressgangccms.client.local.resources.strings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface PressGangCCMSUI extends Constants
{
	public static final PressGangCCMSUI INSTANCE = GWT.create(PressGangCCMSUI.class);

	String PressGangCCMS();
	
	String Home();

	String Welcome();

	String Search();

	String Common();

	String Troubleshooting();

	String PleaseWait();

	String Categories();

	String Included();

	String Excluded();

	String Tags();

	String SearchResults();

	String TopicView();

	String TopicID();
	
	String TopicRevision();

	String TopicTitle();

	String TopicLocale();

	String TopicDescription();

	String TopicXML();
	
	String SaveSuccess();
	
	String ErrorGettingTopics();
	
	String ErrorGettingTopic();
	
	String ErrorSavingTopic();
	
	String ErrorGettingTags();
	
	String TopicCouldNotBeRendered();
	
	String TagAlreadyExists();
	
	String BugzillaID();
	
	String BugzillaSummary();
	
	String IsOpen();
	
	String Link();
	
	String RevisionNumber();
	
	String RevisionDate();
	
	String View();
	
	String Diff();
	
	String CurrentlyViewing();
	
	String Edit();
	
	String CurrentlyEditing();
	
	String RenderedView();
	
	String XMLEditing();
	
	String XMLValidationErrors();
	
	String Properties();
	
	String Bugs();
	
	String Revisions();
	
	String TopicTags();
	
	String SearchTranslations();
	
	String Reports();
	
	String CreateBug();
	
	String RenderedPane();
	
	String HorizontalSplit();
	
	String VerticalSplit();
	
	String NoSplit();
	
	String Close();
	
	String Save();
	
	String ImageDescription();
	
	String ImageLocale();
	
	String ImageFilename();
	
	String ImageSample();
	
	String Images();
	
	String ImagePlaceholder();
	
	String LineWrap();
	
	String ShowHiddenCharacters();
	
	String Add();
	
	String Remove();
}
