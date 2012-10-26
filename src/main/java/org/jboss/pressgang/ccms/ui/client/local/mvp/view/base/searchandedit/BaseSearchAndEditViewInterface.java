package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public interface BaseSearchAndEditViewInterface extends BaseTemplateViewInterface {
    /**
     * @return The panel used to hold the list of tags
     */
    SimpleLayoutPanel getResultsPanel();

    /**
     * @return The panel used to hold the views that display the tag details
     */
    SimpleLayoutPanel getViewPanel();

    /**
     * @return The panel that holds the action buttons for the tag detail views
     */
    SimpleLayoutPanel getViewActionButtonsPanel();

    /**
     * @return The panel that holds the action buttons for the list of tags
     */
    SimpleLayoutPanel getResultsActionButtonsPanel();

    /**
     * @return The split panel that separates the tag list from the tag details views
     */
    HandlerSplitLayoutPanel getSplitPanel();
    
    DockLayoutPanel getResultsViewLayoutPanel();
}
