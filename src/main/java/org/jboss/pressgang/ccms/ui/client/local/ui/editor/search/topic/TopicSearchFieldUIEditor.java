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

import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public final class TopicSearchFieldUIEditor extends BaseTopicSearchFieldUIEditor<TopicSearchUIFields> {

    private final TextBox createdBy = new TextBox();
    private final TextBox notCreatedBy = new TextBox();
    private final TextBox editedBy = new TextBox();
    private final TextBox notEditedBy = new TextBox();

    public TopicSearchFieldUIEditor() {
//        @NotNull final Label topicCreatedByLabel = new Label(PressGangCCMSUI.INSTANCE.CreatedBy());
//        setWidget(getRowCount(), 0, topicCreatedByLabel);
//        setWidget(getRowCount() - 1, 1, createdBy);
//
//        @NotNull final Label topicNotCreatedByLabel = new Label(PressGangCCMSUI.INSTANCE.NotCreatedBy());
//        setWidget(getRowCount(), 0, topicNotCreatedByLabel);
//        setWidget(getRowCount() - 1, 1, notCreatedBy);
//
//        @NotNull final Label topicEditedByLabel = new Label(PressGangCCMSUI.INSTANCE.EditedBy());
//        setWidget(getRowCount(), 0, topicEditedByLabel);
//        setWidget(getRowCount() - 1, 1, editedBy);
//
//        @NotNull final Label topicNotEditedByLabel = new Label(PressGangCCMSUI.INSTANCE.NotEditedBy());
//        setWidget(getRowCount(), 0, topicNotEditedByLabel);
//        setWidget(getRowCount() - 1, 1, notEditedBy);

        addBasicFields();
        addMatchFields();
    }

    @Override
    public void setValue(@NotNull final TopicSearchUIFields value) {
        super.setValue(value);

//        createdBy.setValue(value.getCreatedBy());
//        notCreatedBy.setValue(value.getNotCreatedBy());
//        editedBy.setValue(value.getEditedBy());
//        notEditedBy.setValue(value.getNotEditedBy());
    }

    @Override
    public TopicSearchUIFields getValue() {
        final TopicSearchUIFields value = super.getValue();

//        value.setCreatedBy(createdBy.getValue());
//        value.setNotCreatedBy(notCreatedBy.getValue());
//        value.setEditedBy(editedBy.getValue());
//        value.setNotEditedBy(notEditedBy.getValue());

        return value;
    }
}
