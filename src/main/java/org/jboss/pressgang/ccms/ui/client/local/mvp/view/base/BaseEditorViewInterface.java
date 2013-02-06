package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

/**
 * The interface for views that display ui elements defined in an editor.
 */
public interface BaseEditorViewInterface<W, X extends Editor<W>> {
    /**
     * @return The GWT editor that binds a POJO to a collection of UI elements.
     */
    SimpleBeanEditorDriver<W, X> getDriver();
}
