package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

public interface BaseSearchAndEditViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends BaseTemplateViewInterface {
    /**
     * @return The panel used to hold the list of tags
     */
    SimpleLayoutPanel getResultsPanel();

    /**
     * @return The panel used to hold the views that display the tagincategory details
     */
    SimpleLayoutPanel getViewPanel();

    /**
     * @return The panel that holds the action buttons for the tagincategory detail views
     */
    FlexTable getViewActionButtonsParentPanel();

    /**
     * @return The panel that holds the action buttons for the list of tags
     */
    FlexTable getResultsActionButtonsParentPanel();

    /**
     * @return The split panel that separates the tagincategory list from the tagincategory details views
     */
    HandlerSplitLayoutPanel getSplitPanel();

    DockLayoutPanel getResultsViewLayoutPanel();

    /**
     * Displays the contents of a child view
     *
     * @param displayedView The view to be displayed
     */
    void displayChildView(final BaseTemplateViewInterface displayedView);

    void displaySearchResultsView(final BaseFilteredResultsViewInterface<V> filteredResultsView);
}
