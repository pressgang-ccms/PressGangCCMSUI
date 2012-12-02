package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

/**
 * This interface defines the methods that need top be found on views that use editors to display an entities details.
 * 
 * @author Matthew Casperson
 */
public interface BaseEditorViewInterface <W, X extends Editor<W>> extends BaseTemplateViewInterface {
    SimpleBeanEditorDriver<W, X> getDriver();
}
