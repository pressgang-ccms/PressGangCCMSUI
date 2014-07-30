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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.image;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLanguageImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTLanguageImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
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

    private boolean readOnly;

    @Ignore
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor.
     *
     * @author Matthew Casperson
     */
    private class LanguageImageEditorSource extends EditorSource<RESTLanguageImageV1Editor> {
        @NotNull
        @Override
        public RESTLanguageImageV1Editor create(final int index) {
            final RESTLanguageImageV1Editor editor = new RESTLanguageImageV1Editor(RESTLanguageImageCollectionV1Editor.this, index);
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
        super(Constants.TAB_PANEL_HEIGHT, Constants.TAB_PANEL_HEIGHT_FORMAT);
        this.addStyleName(CSSConstants.ImageView.IMAGE_VIEW_LANGUAGE_IMAGE_TAB_PANEL);
    }
}
