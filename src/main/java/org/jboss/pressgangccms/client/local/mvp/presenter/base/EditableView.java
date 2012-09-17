package org.jboss.pressgangccms.client.local.mvp.presenter.base;

/**
 * This interface is used as a callback to check to see if there are any unsaved changes
 * pending. If so, the user will usually be notified before moving between screens.
 * 
 * @author Matthew Casperson
 */
public interface EditableView {
    boolean checkForUnsavedChanges();
}
