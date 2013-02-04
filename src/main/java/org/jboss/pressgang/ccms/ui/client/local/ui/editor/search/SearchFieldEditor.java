package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;

public final class SearchFieldEditor extends FlexTable implements LeafValueEditor<SearchUIFields> {

    /** The default format for the DateBoxes */
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getLongDateFormat();
    /** The name of the group that the matching option checkboxes belong to */
    private static final String MATCH_GROUP = "match";

    private final DateBox createdAfter = new DateBox();
    private final DateBox createdBefore = new DateBox();
    private final DateBox editedAfter = new DateBox();
    private final DateBox editedBefore = new DateBox();
    private final SimpleIntegerBox editedInLastXDays = new SimpleIntegerBox();
    private final SimpleIntegerBox notEditedInLastXDays = new SimpleIntegerBox();
    private final TextBox ids = new TextBox();
    private final TextBox notIds = new TextBox();
    private final TextBox title = new TextBox();
    private final TextBox contents = new TextBox();
    private final TextBox notContents = new TextBox();
    private final TextBox notTitle = new TextBox();
    private final TextBox description = new TextBox();
    private final TextBox notDescription = new TextBox();
    private final TextBox includedInContentSpecs = new TextBox();
    private final TextBox notIncludedInContentSpecs = new TextBox();
    private final TextBox freeTextSearch = new TextBox();
    //private final TriStatePushButton hasBugzillaBugs = new TriStatePushButton();
    //private final TriStatePushButton hasOpenBugzillaBugs = new TriStatePushButton();
    private final RadioButton matchAll = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAll());
    private final RadioButton matchAny = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAny());
    
    private SearchUIFields value;

    public SearchFieldEditor() {
        final Label topicCreatedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedAfter());
        createdAfter.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        createdAfter.getTextBox().setReadOnly(true);
        this.setWidget(this.getRowCount(), 0, topicCreatedAfterLabel);
        this.setWidget(this.getRowCount() - 1, 1, createdAfter);

        final Label topicCreatedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedBefore());
        createdBefore.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        createdBefore.getTextBox().setReadOnly(true);
        this.setWidget(this.getRowCount(), 0, topicCreatedBeforeLabel);
        this.setWidget(this.getRowCount() - 1, 1, createdBefore);

        final Label topicEditedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedAfter());
        editedAfter.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedAfter.getTextBox().setReadOnly(true);
        this.setWidget(this.getRowCount(), 0, topicEditedAfterLabel);
        this.setWidget(this.getRowCount() - 1, 1, editedAfter);

        final Label topicEditedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedBefore());
        editedBefore.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedBefore.getTextBox().setReadOnly(true);
        this.setWidget(this.getRowCount(), 0, topicEditedBeforeLabel);
        this.setWidget(this.getRowCount() - 1, 1, editedBefore);

        final Label topicEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsEditedInLastXDays());
        this.setWidget(this.getRowCount(), 0, topicEditedInLastXDaysLabels);
        this.setWidget(this.getRowCount() - 1, 1, editedInLastXDays);

        final Label topicNotEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsNotEditedInLastXDays());
        this.setWidget(this.getRowCount(), 0, topicNotEditedInLastXDaysLabels);
        this.setWidget(this.getRowCount() - 1, 1, notEditedInLastXDays);

        final Label topicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.TopicIds());
        this.setWidget(this.getRowCount(), 0, topicsIDLabel);
        this.setWidget(this.getRowCount() - 1, 1, ids);

        final Label notTopicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicIds());
        this.setWidget(this.getRowCount(), 0, notTopicsIDLabel);
        this.setWidget(this.getRowCount() - 1, 1, notIds);

        final Label topicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.TopicTitle());
        this.setWidget(this.getRowCount(), 0, topicTitleLabel);
        this.setWidget(this.getRowCount() - 1, 1, title);

        final Label notTopicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicTitle());
        this.setWidget(this.getRowCount(), 0, notTopicTitleLabel);
        this.setWidget(this.getRowCount() - 1, 1, notTitle);

        final Label topicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.TopicContents());
        this.setWidget(this.getRowCount(), 0, topicContentsLabel);
        this.setWidget(this.getRowCount() - 1, 1, contents);

        final Label notTopicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicContents());
        this.setWidget(this.getRowCount(), 0, notTopicContentsLabel);
        this.setWidget(this.getRowCount() - 1, 1, notContents);

        final Label topicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.TopicDescription());
        this.setWidget(this.getRowCount(), 0, topicDescriptionLabel);
        this.setWidget(this.getRowCount() - 1, 1, description);

        final Label notTopicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicDescription());
        this.setWidget(this.getRowCount(), 0, notTopicDescriptionLabel);
        this.setWidget(this.getRowCount() - 1, 1, notDescription);

        final Label includedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.IncludedInContentSpec());
        this.setWidget(this.getRowCount(), 0, includedInContentSpecLabel);
        this.setWidget(this.getRowCount() - 1, 1, includedInContentSpecs);

        final Label notIncludedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.NotIncludedInContentSpec());
        this.setWidget(this.getRowCount(), 0, notIncludedInContentSpecLabel);
        this.setWidget(this.getRowCount() - 1, 1, notIncludedInContentSpecs);

        final Label freeTextSearchLabel = new Label(PressGangCCMSUI.INSTANCE.FreeTextSearch());
        this.setWidget(this.getRowCount(), 0, freeTextSearchLabel);
        this.setWidget(this.getRowCount() - 1, 1, freeTextSearch);

        /*final Label hasBugzillaBugsLabels = new Label(PressGangCCMSUI.INSTANCE.HasBugzillaBugs());
        this.setWidget(this.getRowCount(), 0, hasBugzillaBugsLabels);
        this.setWidget(this.getRowCount() - 1, 1, hasBugzillaBugs);

        final Label hasOpenBugzillaBugsLabels = new Label(PressGangCCMSUI.INSTANCE.HasOpenBugzillaBugs());
        this.setWidget(this.getRowCount(), 0, hasOpenBugzillaBugsLabels);
        this.setWidget(this.getRowCount() - 1, 1, hasOpenBugzillaBugs);*/

        this.setWidget(this.getRowCount(), 0, matchAll);
        this.setWidget(this.getRowCount() - 1, 1, matchAny);
        
        new NumbersAndCommaValidator(ids);
        new NumbersAndCommaValidator(notIds);
    }

    @Override
    public void setValue(final SearchUIFields value) {
        this.value = value;
        
        createdAfter.setValue(value.getCreatedAfter());
        createdBefore.setValue(value.getCreatedBefore());
        editedAfter.setValue(value.getEditedAfter());
        editedBefore.setValue(value.getEditedBefore());
        editedInLastXDays.setValue(value.getEditedInLastXDays());
        notEditedInLastXDays.setValue(value.getNotEditedInLastXDays());
        ids.setValue(value.getIds());
        notIds.setValue(value.getNotIds());
        title.setValue(value.getTitle());
        notTitle.setValue(value.getNotTitle());
        contents.setValue(value.getContents());
        notContents.setValue(value.getNotContents());        
        description.setValue(value.getDescription());
        notDescription.setValue(value.getNotDescription());
        includedInContentSpecs.setValue(value.getIncludedInContentSpecs());
        notIncludedInContentSpecs.setValue(value.getNotIncludedInContentSpecs());
        freeTextSearch.setValue(value.getFreeTextSearch());
        /*hasBugzillaBugs.setState(value.getHasBugzillaBugs());
        hasOpenBugzillaBugs.setState(value.getHasOpenBugzillaBugs());*/
        matchAll.setValue(value.isMatchAll());
        matchAny.setValue(!value.isMatchAll());
    }

    @Override
    public SearchUIFields getValue() {
                
        value.setCreatedAfter(createdAfter.getValue());
        value.setCreatedBefore(createdBefore.getValue());
        value.setEditedAfter(editedAfter.getValue());
        value.setEditedBefore(editedBefore.getValue());
        value.setEditedInLastXDays(editedInLastXDays.getValue());
        value.setNotEditedInLastXDays(notEditedInLastXDays.getValue());
        value.setIds(ids.getValue());
        value.setNotIds(notIds.getValue());
        value.setTitle(title.getValue());
        value.setNotTitle(notTitle.getValue());
        value.setContents(contents.getValue());
        value.setNotContents(notContents.getValue());
        value.setDescription(description.getValue());
        value.setNotDescription(notDescription.getValue());
        value.setIncludedInContentSpecs(includedInContentSpecs.getValue());
        value.setNotIncludedInContentSpecs(notIncludedInContentSpecs.getValue());
        value.setFreeTextSearch(freeTextSearch.getValue());
        /*value.setHasBugzillaBugs(hasBugzillaBugs.getState());
        value.setHasOpenBugzillaBugs(hasOpenBugzillaBugs.getState());*/
        value.setMatchAll(matchAll.getValue());
        value.setMatchAll(!matchAny.getValue());
        
        return value;
    }
}
