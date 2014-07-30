/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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
        setWidget(getRowCount(), 0, latestTranslationsLabels);
        setWidget(getRowCount() - 1, 1, latestTranslations);

        final Label latestCompletedTranslationsLabels = new Label(PressGangCCMSUI.INSTANCE.LatestCompletedTranslations());
        setWidget(getRowCount(), 0, latestCompletedTranslationsLabels);
        setWidget(getRowCount() - 1, 1, latestCompletedTranslations);
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
