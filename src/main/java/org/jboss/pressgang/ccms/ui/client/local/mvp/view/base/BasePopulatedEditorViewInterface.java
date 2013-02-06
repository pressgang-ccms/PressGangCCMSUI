package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * The interface for views that display ui elements defined in an editor that
 * is populated from an external object.
 *
 * This interface just combines the BaseCustomViewInterface and BaseEditorViewInterface interfaces.
 * The BaseCustomViewInterface interface provides a method for supplying the object that will be used as
 * the data source for the editor. This object is either used by the editor directly, in which case T and W
 * will be the same type, or indirectly, in which case W will usually be constructed with a reference to T.
 */
public interface BasePopulatedEditorViewInterface<T, W, X extends Editor<W>> extends BaseCustomViewInterface<T>, BaseEditorViewInterface<W, X> {

}
