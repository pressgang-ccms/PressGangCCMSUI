package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public final class TopicSearchFieldUIEditor extends BaseTopicSearchFieldUIEditor<TopicSearchUIFields> {

    private final TextBox createdBy = new TextBox();
    private final TextBox editedBy = new TextBox();

    public TopicSearchFieldUIEditor() {
        @NotNull final Label topicCreatedByLabel = new Label(PressGangCCMSUI.INSTANCE.CreatedBy());
        setWidget(getRowCount(), 0, topicCreatedByLabel);
        setWidget(getRowCount() - 1, 1, createdBy);

        @NotNull final Label topicEditedByLabel = new Label(PressGangCCMSUI.INSTANCE.EditedBy());
        setWidget(getRowCount(), 0, topicEditedByLabel);
        setWidget(getRowCount() - 1, 1, editedBy);

        addBasicFields();
        addMatchFields();
    }

    @Override
    public void setValue(@NotNull final TopicSearchUIFields value) {
        super.setValue(value);

        createdBy.setValue(value.getCreatedBy());
        editedBy.setValue(value.getEditedBy());
    }

    @Override
    public TopicSearchUIFields getValue() {
        final TopicSearchUIFields value = super.getValue();

        value.setCreatedBy(createdBy.getValue());
        value.setEditedBy(editedBy.getValue());

        return value;
    }
}
