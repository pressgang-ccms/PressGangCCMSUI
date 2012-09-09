package org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public class TopicTagViewProjectEditor extends Grid implements Editor<SearchUIProject> {
    private final boolean readOnly;
    private final FlexTable categoriesLabelPanel = new FlexTable();
    final Label name = new Label();
    public final ListEditor<SearchUICategory, TopicTagViewCategoryEditor> categories = ListEditor
            .of(new TopicTagViewCategoryEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     * 
     * @author Matthew Casperson
     */
    private class TopicTagViewCategoryEditorSource extends EditorSource<TopicTagViewCategoryEditor> {
        @Override
        public TopicTagViewCategoryEditor create(final int index) {
            final TopicTagViewCategoryEditor subEditor = new TopicTagViewCategoryEditor(readOnly);

            categoriesLabelPanel.setWidget(index, 0, subEditor);

            return subEditor;
        }

        @Override
        public void dispose(final TopicTagViewCategoryEditor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(final TopicTagViewCategoryEditor subEditor, final int index) {
            categoriesLabelPanel.setWidget(index, 0, subEditor);
        }
    }

    public TopicTagViewProjectEditor(final boolean readOnly) {
        super(1, 2);

        this.readOnly = readOnly;

        this.addStyleName(CSSConstants.TOPICTAGVIEWPROJECTTABLE);

        this.setWidget(0, 0, name);
        this.setWidget(0, 1, categoriesLabelPanel);

        this.getCellFormatter().addStyleName(0, 0, CSSConstants.TOPICTAGVIEWPROJECTROW);
        categoriesLabelPanel.addStyleName(CSSConstants.TOPICTAGVIEWCATEGORIESTABLE);
    }
}
