package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.SmallTriStatePushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseTopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public abstract class BaseTopicSearchFieldUIEditor<T extends BaseTopicSearchUIFields> extends BaseSearchFieldUIEditor<T> {

    /**
     * The default format for the DateBoxes
     */
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_LONG);
    /**
     * The name of the group that the matching option checkboxes belong to
     */
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
    private final ListBox topicFormat = new ListBox();
    private final SmallTriStatePushButton hasXMLErrors = new SmallTriStatePushButton();
    private final RadioButton matchAll = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAll());
    private final RadioButton matchAny = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAny());

    private T value;

    protected BaseTopicSearchFieldUIEditor() {
        new NumbersAndCommaValidator(ids);
        new NumbersAndCommaValidator(notIds);
    }

    protected void addBasicFields() {
        final Label topicCreatedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedAfter());
        createdAfter.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        createdAfter.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, topicCreatedAfterLabel);
        setWidget(getRowCount() - 1, 1, createdAfter);

        final Label topicCreatedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicCreatedBefore());
        createdBefore.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        createdBefore.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, topicCreatedBeforeLabel);
        setWidget(getRowCount() - 1, 1, createdBefore);

        final Label topicEditedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedAfter());
        editedAfter.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedAfter.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, topicEditedAfterLabel);
        setWidget(getRowCount() - 1, 1, editedAfter);

        final Label topicEditedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.TopicEditedBefore());
        editedBefore.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedBefore.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, topicEditedBeforeLabel);
        setWidget(getRowCount() - 1, 1, editedBefore);

        final Label topicEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsEditedInLastXDays());
        setWidget(getRowCount(), 0, topicEditedInLastXDaysLabels);
        setWidget(getRowCount() - 1, 1, editedInLastXDays);

        final Label topicNotEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.TopicsNotEditedInLastXDays());
        setWidget(getRowCount(), 0, topicNotEditedInLastXDaysLabels);
        setWidget(getRowCount() - 1, 1, notEditedInLastXDays);

        final Label topicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.TopicIds());
        setWidget(getRowCount(), 0, topicsIDLabel);
        setWidget(getRowCount() - 1, 1, ids);

        final Label notTopicsIDLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicIds());
        setWidget(getRowCount(), 0, notTopicsIDLabel);
        setWidget(getRowCount() - 1, 1, notIds);

        final Label topicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.TopicTitle());
        setWidget(getRowCount(), 0, topicTitleLabel);
        setWidget(getRowCount() - 1, 1, title);

        final Label notTopicTitleLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicTitle());
        setWidget(getRowCount(), 0, notTopicTitleLabel);
        setWidget(getRowCount() - 1, 1, notTitle);

        final Label topicFormatLabel = new Label(PressGangCCMSUI.INSTANCE.TopicFormat());
        setWidget(getRowCount(), 0, topicFormatLabel);
        setWidget(getRowCount() - 1, 1, topicFormat);

        final Label topicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.TopicContents());
        setWidget(getRowCount(), 0, topicContentsLabel);
        setWidget(getRowCount() - 1, 1, contents);

        final Label notTopicContentsLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicContents());
        setWidget(getRowCount(), 0, notTopicContentsLabel);
        setWidget(getRowCount() - 1, 1, notContents);

        final Label topicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.TopicDescription());
        setWidget(getRowCount(), 0, topicDescriptionLabel);
        setWidget(getRowCount() - 1, 1, description);

        final Label notTopicDescriptionLabel = new Label(PressGangCCMSUI.INSTANCE.NotTopicDescription());
        setWidget(getRowCount(), 0, notTopicDescriptionLabel);
        setWidget(getRowCount() - 1, 1, notDescription);

        final Label includedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.IncludedInContentSpec());
        setWidget(getRowCount(), 0, includedInContentSpecLabel);
        setWidget(getRowCount() - 1, 1, includedInContentSpecs);

        final Label notIncludedInContentSpecLabel = new Label(PressGangCCMSUI.INSTANCE.NotIncludedInContentSpec());
        setWidget(getRowCount(), 0, notIncludedInContentSpecLabel);
        setWidget(getRowCount() - 1, 1, notIncludedInContentSpecs);

        final Label hasXMLErrorsLabels = new Label(PressGangCCMSUI.INSTANCE.HasXMLErrors());
        setWidget(getRowCount(), 0, hasXMLErrorsLabels);
        setWidget(getRowCount() - 1, 1, hasXMLErrors);

        for (int i = 0; i < getRowCount(); ++i) {
            getCellFormatter().addStyleName(i, 0, CSSConstants.FieldEditor.FIELD_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < getRowCount() - 1; ++i) {
            getCellFormatter().addStyleName(i, 1, CSSConstants.FieldEditor.FIELD_VIEW_VALUE_CELL);
        }

        topicFormat.clear();
        topicFormat.addItem("", Integer.toString(-1));
        for (final RESTXMLFormat docType : RESTXMLFormat.values()) {
            topicFormat.addItem(docType.getCommonName(), RESTXMLFormat.getXMLFormatId(docType).toString());
        }
    }

    protected void addMatchFields() {
        setWidget(getRowCount(), 0, matchAll);
        setWidget(getRowCount() - 1, 1, matchAny);
    }

    @Override
    public void setValue(@NotNull final T value) {
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
        hasXMLErrors.setState(value.getHasXMLErrors());
        matchAll.setValue(value.isMatchAll());
        matchAny.setValue(!value.isMatchAll());

        if (value.getFormat() == null) {
            topicFormat.setSelectedIndex(0);
        } else {
            final RESTXMLFormat format = RESTXMLFormat.getXMLFormat(value.getFormat());
            topicFormat.setSelectedIndex(format.ordinal() + 1);
        }
    }

    @Override
    public T getValue() {

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
        value.setHasXMLErrors(hasXMLErrors.getState());
        value.setMatchAll(matchAll.getValue());
        value.setMatchAll(!matchAny.getValue());
        final Integer formatValue = Integer.valueOf(topicFormat.getValue(topicFormat.getSelectedIndex()));
        value.setFormat(formatValue == -1 ? null : formatValue);

        return value;
    }
}
