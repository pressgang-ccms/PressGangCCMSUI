package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * The interface for views that display ui elements defined in an editor
 */
public interface BaseEditorViewInterface<T, W, X extends Editor<W>> extends BaseCustomViewInterface<T> {
    /**
     * @return The GWT editor that binds a POJO to a collection of UI elements.
     */
    SimpleBeanEditorDriver<W, X> getDriver();
}
