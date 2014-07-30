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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jetbrains.annotations.NotNull;

public final class TopicTagViewProjectEditor extends Grid implements Editor<SearchUIProject> {
    private final boolean readOnly;
    private final FlexTable categoriesLabelPanel = new FlexTable();
    private final Label name = new Label();
    public final ListEditor<SearchUICategory, TopicTagViewCategoryEditor> categories = ListEditor
            .of(new TopicTagViewCategoryEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class TopicTagViewCategoryEditorSource extends EditorSource<TopicTagViewCategoryEditor> {
        @NotNull
        @Override
        public TopicTagViewCategoryEditor create(final int index) {
            @NotNull final TopicTagViewCategoryEditor subEditor = new TopicTagViewCategoryEditor(readOnly);

            categoriesLabelPanel.setWidget(index, 0, subEditor);

            return subEditor;
        }

        @Override
        public void dispose(@NotNull final TopicTagViewCategoryEditor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(final TopicTagViewCategoryEditor subEditor, final int index) {
            categoriesLabelPanel.setWidget(index, 0, subEditor);
        }
    }

    /**
     * @return The name label exposed to the GWT Editor framework
     */
    @NotNull
    public Label nameEditor() {
        return name;
    }

    public TopicTagViewProjectEditor(final boolean readOnly) {
        super(1, 2);

        this.readOnly = readOnly;

        this.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_PROJECT_TABLE);

        this.setWidget(0, 0, name);
        this.setWidget(0, 1, categoriesLabelPanel);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TopicView.TOPIC_TAG_VIEW_PROJECT_ROW);
        categoriesLabelPanel.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_CATEGORIES_TABLE);
    }
}
