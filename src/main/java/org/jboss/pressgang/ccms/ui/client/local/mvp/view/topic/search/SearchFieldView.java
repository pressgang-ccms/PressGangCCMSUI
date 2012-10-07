package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TriStatePushButton;
import com.google.gwt.user.datepicker.client.DateBox;

public class SearchFieldView extends BaseTemplateView implements SearchFieldPresenter.Display {
    
    public SearchFieldView()
    {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());
        
        /* The date format used by the date UI controls */ 
        final DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
        
        final FlexTable fieldTable = new FlexTable();
        
        final Label topicCreatedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedAfter());        
        final DateBox topicCreatedAfter = new DateBox();
        topicCreatedAfter.setFormat(new DateBox.DefaultFormat(dateFormat));
        topicCreatedAfter.getTextBox().setReadOnly(true);
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicCreatedAfterLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicCreatedAfter);
        
        final Label topicCreatedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedBefore());        
        final DateBox topicCreatedBefore = new DateBox();
        topicCreatedBefore.setFormat(new DateBox.DefaultFormat(dateFormat));
        topicCreatedBefore.getTextBox().setReadOnly(true);
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicCreatedBeforeLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicCreatedBefore);
        
        final Label topicEditedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedAfter());        
        final DateBox topicEditedAfter = new DateBox();
        topicEditedAfter.setFormat(new DateBox.DefaultFormat(dateFormat));
        topicEditedAfter.getTextBox().setReadOnly(true);
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicEditedAfterLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicEditedAfter);
        
        final Label topicEditedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedBefore());        
        final DateBox topicEditedBefore = new DateBox();
        topicEditedBefore.setFormat(new DateBox.DefaultFormat(dateFormat));
        topicEditedBefore.getTextBox().setReadOnly(true);
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicEditedBeforeLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicEditedBefore);
        
        final Label topicEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsEditedInLastXDays());
        final IntegerBox topicEditedInLastXDays = new IntegerBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicEditedInLastXDaysLabels);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicEditedInLastXDays);
        
        final Label topicNotEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsNotEditedInLastXDays());
        final IntegerBox topicNotEditedInLastXDays = new IntegerBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicNotEditedInLastXDaysLabels);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicNotEditedInLastXDays);
        
        final Label topicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.TopicIds());
        final TextBox topicIds = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicsIDLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicIds);
        
        final Label notTopicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicIds());
        final TextBox notTopicIds = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, notTopicsIDLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, notTopicIds);
        
        final Label topicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.TopicTitle());
        final TextBox topicTitle = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicTitleLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicTitle);
        
        final Label notTopicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicTitle());
        final TextBox notTopicTitle = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, notTopicTitleLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, notTopicTitle);
        
        final Label topicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.TopicContents());
        final TextBox topicContents = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicContentsLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicContents);
        
        final Label notTopicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicContents());
        final TextBox notTopicContents = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, notTopicContentsLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, notTopicContents);
        
        final Label topicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.TopicDescription());
        final TextBox topicDescription = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, topicDescriptionLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, topicDescription);
        
        final Label notTopicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicDescription());
        final TextBox notTopicDescription = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, notTopicDescriptionLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, notTopicDescription);
        
        final Label includedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.IncludedInContentSpec());
        final TextBox includedInContentSpec = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, includedInContentSpecLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, includedInContentSpec);
        
        final Label notIncludedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.NotIncludedInContentSpec());
        final TextBox notIncludedInContentSpec = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, notIncludedInContentSpecLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, notIncludedInContentSpec);
        
        final Label freeTextSearchLabel = new Label(PressGangCCMSUI.INSTANCE.FreeTextSearch());
        final TextBox freeTextSearch = new TextBox();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, freeTextSearchLabel);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, freeTextSearch);
        
        final Label hasBugzillaBugsLabels = new Label(PressGangCCMSUI.INSTANCE.HasBugzillaBugs());
        final TriStatePushButton hasBugzillaBugs = new TriStatePushButton();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, hasBugzillaBugsLabels);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, hasBugzillaBugs);
        
        final Label hasOpenBugzillaBugsLabels = new Label(PressGangCCMSUI.INSTANCE.HasOpenBugzillaBugs());
        final TriStatePushButton hasOpenBugzillaBugs = new TriStatePushButton();
        fieldTable.setWidget(fieldTable.getRowCount(), 0, hasOpenBugzillaBugsLabels);
        fieldTable.setWidget(fieldTable.getRowCount()-1, 1, hasOpenBugzillaBugs);
        
        this.getPanel().setWidget(fieldTable);
    }
    

}
