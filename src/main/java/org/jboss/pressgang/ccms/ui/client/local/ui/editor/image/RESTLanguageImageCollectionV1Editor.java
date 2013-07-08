package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * This class provides a UI object to represent the collection of language images in an image.
 *
 * @author Matthew Casperson
 */
public final class RESTLanguageImageCollectionV1Editor extends TabLayoutPanel implements Editor<RESTLanguageImageCollectionV1> {
    /**
     * The collection of language image editors
     */
    private final ListEditor<RESTLanguageImageCollectionItemV1, RESTLanguageImageV1Editor> items = ListEditor
            .of(new LanguageImageEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor.
     *
     * @author Matthew Casperson
     */
    private class LanguageImageEditorSource extends EditorSource<RESTLanguageImageV1Editor> {
        @NotNull
        @Override
        public RESTLanguageImageV1Editor create(final int index) {
            @NotNull final RESTLanguageImageV1Editor editor = new RESTLanguageImageV1Editor(RESTLanguageImageCollectionV1Editor.this,
                    index);
            add(editor, PressGangCCMSUI.INSTANCE.ImagePlaceholder());
            return editor;
        }

        @Override
        public void dispose(@NotNull final RESTLanguageImageV1Editor subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final RESTLanguageImageV1Editor subEditor, final int index) {
            subEditor.removeFromParent();
            insert(subEditor, index);
        }
    }

    public ListEditor<RESTLanguageImageCollectionItemV1, RESTLanguageImageV1Editor> itemsEditor() {
        return items;
    }

    public RESTLanguageImageCollectionV1Editor() {
        super(2, Unit.EM);
        this.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_TAB_PANEL);
    }
}
