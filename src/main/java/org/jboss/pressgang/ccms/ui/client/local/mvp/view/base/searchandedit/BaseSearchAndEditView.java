package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class BaseSearchAndEditView extends BaseTemplateView implements BaseSearchAndEditViewInterface {

    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel resultsActionButtonsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewActionButtonsPanel = new SimpleLayoutPanel();

    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

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
    public SimpleLayoutPanel getResultsActionButtonsPanel() {
        return resultsActionButtonsPanel;
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
    public SimpleLayoutPanel getViewActionButtonsPanel() {
        return viewActionButtonsPanel;
    }

    public BaseSearchAndEditView(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        /* We have own own top action panels */
        this.getTopActionParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        resultsViewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.RESULTS_VIEW_LAYOUT_PANEL);
        viewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_VIEW_LAYOUT_PANEL);

        resultsViewLayoutPanel.addNorth(resultsActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);
        viewLayoutPanel.addNorth(viewActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);

        resultsViewLayoutPanel.add(resultsPanel);
        viewLayoutPanel.add(viewPanel);

        splitPanel.addWest(resultsViewLayoutPanel, Constants.SPLIT_PANEL_SIZE);

        viewActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        resultsActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
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
     * Displays the contents of a child view
     * 
     * @param displayedView The view to be displayed, or null if no view is to be displayed
     */
    public void displayChildView(final BaseTemplateViewInterface displayedView) {
        this.getViewActionButtonsPanel().clear();
        this.getViewPanel().clear();
        if (displayedView != null) {
            this.getViewActionButtonsPanel().setWidget(displayedView.getTopActionPanel());
            this.getViewPanel().setWidget(displayedView.getPanel());
        }
    }
    
    public void displaySearchResultsView(final BaseFilteredResultsViewInterface filteredResultsView)
    {
        this.getResultsActionButtonsPanel().clear();
        this.getResultsPanel().clear();
        if (filteredResultsView != null) {
            this.getResultsActionButtonsPanel().setWidget(filteredResultsView.getTopActionPanel());
            this.getResultsPanel().setWidget(filteredResultsView.getPanel());
        }
    }
}
