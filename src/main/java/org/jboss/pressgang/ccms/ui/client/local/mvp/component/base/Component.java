package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

/**
 * 
 * @author matthew
 *
 * @param <S> The type of the view
 */
public interface Component<S extends BaseTemplateViewInterface> {

    /**
     * Bind behaviour to the UI elements in the display
     * 
     * @param display The display to bind behaviour to
     */
    void bind(final S display, final BaseTemplateViewInterface waitDisplay);

    /**
     * Sets the feedback survey link
     * 
     * @param pageId A token that identifies the view
     */
    void setFeedbackLink(final String pageId);

    /**
     * @return false if the view has unsaved changes that the user wishes to save (i.e. don't continue with a navigation event),
     *         true otherwise
     */
    boolean isOKToProceed();
    
    /**
     * Is called by isOKToProceed to determine if it is ok to move from the page. Can also be called by other methods to see if there are 
     * pending changes.
     * @return true if there are unsaved changes, and false otherwise
     */
    boolean hasUnsavedChanges();
}
