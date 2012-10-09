package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public interface Component<T extends BaseTemplateViewInterface> {
    
    /**
     * Bind behaviour to the UI elements in the display
     * @param display The display to bind behaviour to
     */
    void bind(final T display, final BaseTemplateViewInterface waitDisplay);
    
    /**
     * Sets the feedback survey link
     * @param pageId A token that identifies the view
     */
    void setFeedbackLink(final String pageId);
}
