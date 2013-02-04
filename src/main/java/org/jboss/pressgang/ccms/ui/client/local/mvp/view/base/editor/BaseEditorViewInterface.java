package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

/**
 * This interface defines the methods that need top be found on views that use editors to display an entities details.
 * 
 * @author Matthew Casperson
 * @param <W> The type of the object being bound to by the editor
 * @param <X> The type of the editor that is bound to W
 */
public interface BaseEditorViewInterface <W, X extends Editor<W>> extends BaseTemplateViewInterface {
    /**
     * @return The GWT editor that binds a POJO to a collection of UI elements.
     */
    SimpleBeanEditorDriver<W, X> getDriver();
}
