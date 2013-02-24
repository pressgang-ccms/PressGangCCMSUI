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
        @Override
        public TopicTagViewTagEditor create(final int index) {
            final TopicTagViewTagEditor subEditor = new TopicTagViewTagEditor(readOnly);
            tagsTable.setWidget(index, 0, subEditor.name);

            /* do not show the delete button in readOnly mode */
            if (!readOnly) {
                tagsTable.setWidget(index, 1, subEditor.getDelete());
            }

            tagsTable.getCellFormatter().addStyleName(index, 0, CSSConstants.TOPIC_TAG_VIEW_TAG_ROW);
            return subEditor;
        }

        @Override
        public void dispose(final TopicTagViewTagEditor subEditor) {
            subEditor.name.removeFromParent();
        }

        @Override
        public void setIndex(final TopicTagViewTagEditor subEditor, final int index) {
            tagsTable.setWidget(index, 0, subEditor.name);
        }
    }

    public TopicTagViewCategoryEditor(final boolean readOnly) {
        super(1, 2);

        this.readOnly = readOnly;

        this.addStyleName(CSSConstants.TOPIC_TAG_VIEW_CATEGORY_TABLE);

        this.setWidget(0, 0, name);
        this.setWidget(0, 1, tagsTable);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TOPIC_TAG_VIEW_CATEGORY_ROW);
        tagsTable.addStyleName(CSSConstants.TOPIC_TAG_VIEW_TAG_STABLE);
    }
}
