package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SmallTriStatePushButton;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TranslatedTopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public final class TranslatedTopicSearchFieldUIEditor extends BaseTopicSearchFieldUIEditor<TranslatedTopicSearchUIFields> {

    private final SmallTriStatePushButton latestTranslations = new SmallTriStatePushButton();
    private final SmallTriStatePushButton latestCompletedTranslations = new SmallTriStatePushButton();

    public TranslatedTopicSearchFieldUIEditor() {
        addBasicFields();
        addAdditionalFields();
        addMatchFields();
    }

    protected void addAdditionalFields() {
        final Label latestTranslationsLabels = new Label(PressGangCCMSUI.INSTANCE.LatestTranslations());
        this.setWidget(this.getRowCount(), 0, latestTranslationsLabels);
        this.setWidget(this.getRowCount() - 1, 1, latestTranslations);

        final Label latestCompletedTranslationsLabels = new Label(PressGangCCMSUI.INSTANCE.LatestCompletedTranslations());
        this.setWidget(this.getRowCount(), 0, latestCompletedTranslationsLabels);
        this.setWidget(this.getRowCount() - 1, 1, latestCompletedTranslations);
    }

    @Override
    public void setValue(@NotNull final TranslatedTopicSearchUIFields value) {
        super.setValue(value);
        latestTranslations.setState(value.getLatestTranslations());
        latestCompletedTranslations.setState(value.getLatestCompletedTranslations());
    }

    @Override
    public TranslatedTopicSearchUIFields getValue() {
        final TranslatedTopicSearchUIFields value = super.getValue();

        value.setLatestTranslations(latestTranslations.getState());
        value.setLatestCompletedTranslations(latestCompletedTranslations.getState());

        return value;
    }
}
