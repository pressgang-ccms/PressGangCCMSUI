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
import com.google.gwt.user.client.ui.SimplePanel;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;

public final class TopicTagViewProjectsEditor extends SimplePanel implements Editor<SearchUIProjects> {
    private boolean readOnly;
    private final FlexTable projectLabelPanel = new FlexTable();
    public final ListEditor<SearchUIProject, TopicTagViewProjectEditor> projects = ListEditor
            .of(new TopicTagViewProjectEditorSource());

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class TopicTagViewProjectEditorSource extends EditorSource<TopicTagViewProjectEditor> {
        @NotNull
        @Override
        public TopicTagViewProjectEditor create(final int index) {
            final TopicTagViewProjectEditor subEditor = new TopicTagViewProjectEditor(readOnly);
            projectLabelPanel.setWidget(index, 0, subEditor);

            return subEditor;
        }

        @Override
        public void dispose(@NotNull final TopicTagViewProjectEditor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(final TopicTagViewProjectEditor subEditor, final int index) {
            projectLabelPanel.setWidget(index, 0, subEditor);
        }
    }

    public TopicTagViewProjectsEditor() {
        projectLabelPanel.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_PROJECT_STABLE);
        this.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_PARENT_PROJECTS_TABLE);
        this.setWidget(projectLabelPanel);
    }
}
