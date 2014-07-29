/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;

public final class TopicTagViewCategoryEditor extends Grid implements Editor<SearchUICategory> {
    private final boolean readOnly;
    private final FlexTable tagsTable = new FlexTable();
    final Label name = new Label();
    public final ListEditor<SearchUITag, TopicTagViewTagEditor> myTags = ListEditor.of(new TopicTagViewTagEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class TopicTagViewTagEditorSource extends EditorSource<TopicTagViewTagEditor> {
        @NotNull
        @Override
        public TopicTagViewTagEditor create(final int index) {
            @NotNull final TopicTagViewTagEditor subEditor = new TopicTagViewTagEditor(readOnly);
            tagsTable.setWidget(index, 0, subEditor.name);

            /* do not show the delete button in readOnly mode */
            if (!readOnly) {
                tagsTable.setWidget(index, 1, subEditor.getDelete());
            }

            tagsTable.getCellFormatter().addStyleName(index, 0, CSSConstants.TopicView.TOPIC_TAG_VIEW_TAG_ROW);
            return subEditor;
        }

        @Override
        public void dispose(@NotNull final TopicTagViewTagEditor subEditor) {
            subEditor.name.removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final TopicTagViewTagEditor subEditor, final int index) {
            tagsTable.setWidget(index, 0, subEditor.name);
        }
    }

    public TopicTagViewCategoryEditor(final boolean readOnly) {
        super(1, 2);

        this.readOnly = readOnly;

        this.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_CATEGORY_TABLE);

        this.setWidget(0, 0, name);
        this.setWidget(0, 1, tagsTable);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TopicView.TOPIC_TAG_VIEW_CATEGORY_ROW);
        tagsTable.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_TAG_STABLE);
    }
}
