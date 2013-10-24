package org.jboss.pressgang.ccms.ui.client.local.ui.editor.file;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageFileCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageFileCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * This class provides a UI object to represent the collection of language files in a file.
 *
 * @author Matthew Casperson
 */
public final class RESTLanguageFileCollectionV1Editor extends TabLayoutPanel implements Editor<RESTLanguageFileCollectionV1> {
    /**
     * The collection of language file editors
     */
    private final ListEditor<RESTLanguageFileCollectionItemV1, RESTLanguageFileV1Editor> items = ListEditor
            .of(new LanguageFileEditorSource());

    /**
     * The EditorSource is used to create and organise the Editors that go into a ListEditor.
     *
     * @author Matthew Casperson
     */
    private class LanguageFileEditorSource extends EditorSource<RESTLanguageFileV1Editor> {
        @NotNull
        @Override
        public RESTLanguageFileV1Editor create(final int index) {
            final RESTLanguageFileV1Editor editor = new RESTLanguageFileV1Editor(RESTLanguageFileCollectionV1Editor.this, index);
            add(editor, PressGangCCMSUI.INSTANCE.FilePlaceholder());
            return editor;
        }

        @Override
        public void dispose(@NotNull final RESTLanguageFileV1Editor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final RESTLanguageFileV1Editor subEditor, final int index) {
            subEditor.removeFromParent();
            insert(subEditor, index);
        }
    }

    public ListEditor<RESTLanguageFileCollectionItemV1, RESTLanguageFileV1Editor> itemsEditor() {
        return items;
    }

    public RESTLanguageFileCollectionV1Editor() {
        super(2, Unit.EM);
        addStyleName(CSSConstants.FileView.FILE_VIEW_LANGUAGE_FILE_TAB_PANEL);
    }
}
