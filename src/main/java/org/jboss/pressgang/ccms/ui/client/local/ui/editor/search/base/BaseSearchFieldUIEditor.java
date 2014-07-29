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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.FlexTable;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseSearchUIFields;

public abstract class BaseSearchFieldUIEditor<T extends BaseSearchUIFields> extends FlexTable implements LeafValueEditor<T> {
    protected BaseSearchFieldUIEditor() {
        setStyleName(CSSConstants.FieldEditor.FIELD_VIEW_PANEL);
    }
}
