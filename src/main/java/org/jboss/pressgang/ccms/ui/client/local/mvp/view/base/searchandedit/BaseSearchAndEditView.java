package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

import com.google.gwt.dom.client.Style.Unit;

abstract public class BaseSearchAndEditView<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
        extends BaseTemplateView implements BaseSearchAndEditViewInterface<T, U, V> {

    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
    /**
     * The panel that will hold the command and local action buttons from the main view and the filtered results view it is displaying
     */
    private final FlexTable resultsActionButtonsParentPanel = new FlexTable();
    /**
     * The panel that will hold the common action buttons from the main view and the filtered results view
     */
    private final HorizontalPanel resultsActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the local action buttons from the main view and the filtered results view
     */
    private final HorizontalPanel resultsViewSpecificActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the action buttons from the main view and the child view it is displaying
     */
    private final FlexTable viewActionButtonsParentPanel = new FlexTable();
    /**
     * The panel that will hold the action buttons
     */
    private final HorizontalPanel viewActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the view specific action buttons
     */
    private final HorizontalPanel viewViewSpecificActionButtonsPanel = new HorizontalPanel();

    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

    @Override
    public DockLayoutPanel getResultsViewLayoutPanel() {
        return resultsViewLayoutPanel;
    }

    public DockLayoutPanel getViewLayoutPanel() {
        return viewLayoutPanel;
    }

    @Override
    public HandlerSplitLayoutPanel getSplitPanel() {
        return splitPanel;
    }

    @Override
    public FlexTable getResultsActionButtonsParentPanel() {
        return resultsActionButtonsParentPanel;
    }

    @Override
    public SimpleLayoutPanel getResultsPanel() {
        return resultsPanel;
    }

    @Override
    public SimpleLayoutPanel getViewPanel() {
        return viewPanel;
    }

    @Override
    public FlexTable getViewActionButtonsParentPanel() {
        return viewActionButtonsParentPanel;
    }

    public BaseSearchAndEditView(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        /* We have own own top action panels */
        this.getTopActionGrandParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        resultsViewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.RESULTS_VIEW_LAYOUT_PANEL);
        viewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_VIEW_LAYOUT_PANEL);

        resultsViewLayoutPanel.addNorth(resultsActionButtonsParentPanel, Constants.ACTION_BAR_HEIGHT);
        viewLayoutPanel.addNorth(viewActionButtonsParentPanel, Constants.ACTION_BAR_HEIGHT);

        resultsViewLayoutPanel.add(resultsPanel);
        viewLayoutPanel.add(viewPanel);

        splitPanel.addWest(resultsViewLayoutPanel, Constants.SPLIT_PANEL_SIZE);

        viewActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        viewViewSpecificActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        viewActionButtonsParentPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PARENT_PANEL);

        resultsActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
        resultsViewSpecificActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
        resultsActionButtonsParentPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PARENT_PANEL);

        viewPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TOPIC_VIEW_DETAILS_PANEL);

        splitPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL);

        splitPanel.add(viewLayoutPanel);

        this.getPanel().add(splitPanel);
    }

    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

    /**
     * Displays the contents of a child view. This method will also merge the action buttons
     * defined in the top level view and the individual view that it is displaying as a child.
     * 
     * @param displayedView The view to be displayed, or null if no view is to be displayed
     */
    @Override
    public void displayChildView(final BaseTemplateViewInterface displayedView) {
        this.getViewPanel().clear();
        this.viewActionButtonsParentPanel.clear();
        this.viewActionButtonsPanel.clear();
        this.viewViewSpecificActionButtonsPanel.clear();

        viewActionButtonsParentPanel.setWidget(0, 0, viewActionButtonsPanel);
        viewActionButtonsParentPanel.setWidget(0, 1, new SimplePanel());
        viewActionButtonsParentPanel.setWidget(0, 2, viewViewSpecificActionButtonsPanel);
        viewActionButtonsParentPanel.getFlexCellFormatter().setWidth(0, 1, "100%");

        this.viewActionButtonsPanel.add(this.getTopActionPanel());
        this.viewViewSpecificActionButtonsPanel.add(this.getTopViewSpecificActionPanel());

        if (displayedView != null) {
            this.getViewPanel().setWidget(displayedView.getPanel());
            this.viewActionButtonsPanel.add(displayedView.getTopActionParentPanel());

            this.viewViewSpecificActionButtonsPanel.add(displayedView.getTopViewSpecificActionPanel());
        }
    }

    /**
     * Displays the contents of a child filtered results view. This method will also merge the action buttons
     * defined in the top level view and the individual view that it is displaying as a child.
     *
     * @param filteredResultsView The filtered view to be displayed, or null if no view is to be displayed
     */
    @Override
    public void displaySearchResultsView(final BaseFilteredResultsViewInterface<T, U, V> filteredResultsView) {
        this.getResultsPanel().clear();
        this.resultsActionButtonsParentPanel.clear();
        this.resultsActionButtonsPanel.clear();
        this.resultsViewSpecificActionButtonsPanel.clear();

        resultsActionButtonsParentPanel.setWidget(0, 0, resultsActionButtonsPanel);
        resultsActionButtonsParentPanel.setWidget(0, 1, new SimplePanel());
        resultsActionButtonsParentPanel.setWidget(0, 2, resultsViewSpecificActionButtonsPanel);
        resultsActionButtonsParentPanel.getFlexCellFormatter().setWidth(0, 1, "100%");

        //this.resultsActionButtonsPanel.add(this.getTopActionPanel());
        //this.resultsViewSpecificActionButtonsPanel.add(this.getTopViewSpecificActionPanel());

        if (filteredResultsView != null) {
            this.getResultsPanel().setWidget(filteredResultsView.getPanel());
            this.resultsActionButtonsPanel.add(filteredResultsView.getTopActionPanel());
            this.resultsViewSpecificActionButtonsPanel.add(filteredResultsView.getTopViewSpecificActionPanel());
        }
    }
}
