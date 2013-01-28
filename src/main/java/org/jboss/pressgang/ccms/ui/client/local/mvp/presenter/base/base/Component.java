package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.base;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

/**
 * Components are used to add logic to the veiws held by a presenter. Quite often multiple components are used by a single presenter.
 * 
 * @author Matthew Casperson
 */
public interface Component {

    /**
     * Bind behaviour to the UI elements in the display
     * 
     * @param topicId the help topic for the page
     * @param pageId The history token of the page
     * @param display The display that holds the UI elements the user interacts with
     */
    void bind(final int topicId, final String pageId, final BaseTemplateViewInterface display);

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
    
    /**
     * 
     * @return The topic of the ID to be used for the help dialog
     */
    int getHelpTopicId();

    /**
     * 
     * @param helpTopicId The topic of the ID to be used for the help dialog
     */
    void setHelpTopicId(int helpTopicId);
}
