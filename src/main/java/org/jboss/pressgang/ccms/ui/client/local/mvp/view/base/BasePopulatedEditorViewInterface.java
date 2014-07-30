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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.editor.client.Editor;

/**
 * The interface for views that display ui elements defined in an editor that
 * is populated from an external object.
 * <p/>
 * This interface just combines the BaseCustomViewInterface and BaseEditorViewInterface interfaces.
 * The BaseCustomViewInterface interface provides a method for supplying the object that will be used as
 * the data source for the editor. This object is either used by the editor directly, in which case T and W
 * will be the same type, or indirectly, in which case W will usually be constructed with a reference to T.
 *
 * @param <T> The entity that this view displays
 * @param <W> The source of the data used to populate the editor
 * @param <X> The editor that links ui elements to an instance of W
 */
public interface BasePopulatedEditorViewInterface<T, W, X extends Editor<W>> extends BaseCustomViewInterface<T>, BaseEditorViewInterface<W, X>, BaseTemplateViewInterface {

}
