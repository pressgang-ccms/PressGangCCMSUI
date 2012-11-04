package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base;

/**
 * This interface is used as a callback to check to see if there are any unsaved changes pending. If so, the user will usually
 * be notified before moving between screens.
 * 
 * @author Matthew Casperson
 */
public interface EditableView {

    /**
     * @return true if there are no unsaved changes, or if the user is happy to lose any unsaved changes, and false otherwise.
     */
    boolean isOKToProceed();
}
