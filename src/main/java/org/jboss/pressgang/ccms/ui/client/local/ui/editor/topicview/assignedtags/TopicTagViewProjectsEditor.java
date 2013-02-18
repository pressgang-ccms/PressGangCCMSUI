package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;

public class TopicTagViewProjectsEditor extends SimplePanel implements Editor<SearchUIProjects> {
    private final boolean readOnly;
    private final FlexTable projectLabelPanel = new FlexTable();
    public final ListEditor<SearchUIProject, TopicTagViewProjectEditor> projects = ListEditor
            .of(new TopicTagViewProjectEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class TopicTagViewProjectEditorSource extends EditorSource<TopicTagViewProjectEditor> {
        @Override
        public TopicTagViewProjectEditor create(final int index) {
            final TopicTagViewProjectEditor subEditor = new TopicTagViewProjectEditor(readOnly);
            projectLabelPanel.setWidget(index, 0, subEditor);

            return subEditor;
        }

        @Override
        public void dispose(final TopicTagViewProjectEditor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(final TopicTagViewProjectEditor subEditor, final int index) {
            projectLabelPanel.setWidget(index, 0, subEditor);
        }
    }

    public TopicTagViewProjectsEditor(final boolean readOnly) {
        this.readOnly = readOnly;
        projectLabelPanel.addStyleName(CSSConstants.TOPIC_TAG_VIEW_PROJECT_STABLE);
        this.addStyleName(CSSConstants.TOPIC_TAG_VIEW_PARENT_PROJECT_STABLE);
        this.setWidget(projectLabelPanel);
    }
}
