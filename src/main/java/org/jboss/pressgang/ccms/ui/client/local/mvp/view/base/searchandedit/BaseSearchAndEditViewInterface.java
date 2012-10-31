package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public interface BaseSearchAndEditViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends BaseTemplateViewInterface {
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

    /**
     * Displays the contents of a child view
     * 
     * @param displayedView The view to be displayed
     */
    void displayChildView(final BaseTemplateViewInterface displayedView);

    void displaySearchResultsView(final BaseFilteredResultsViewInterface<T, U, V> filteredResultsView);
}
