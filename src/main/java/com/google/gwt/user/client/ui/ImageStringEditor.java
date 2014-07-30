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

package com.google.gwt.user.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import org.jetbrains.annotations.NotNull;

/**
 * An Image that can be bound to a Base64 representation of an image.
 *
 * @author Matthew Casperson
 */
public final class ImageStringEditor extends Image implements IsEditor<LeafValueEditor<String>> {
    @NotNull
    @Override
    public LeafValueEditor<String> asEditor() {
        return new LeafValueEditor<String>() {

            @Override
            public void setValue(final String value) {
                ImageStringEditor.this.setUrl(value);

            }

            @Override
            public String getValue() {
                return ImageStringEditor.this.getUrl();
            }

        };
    }

}
