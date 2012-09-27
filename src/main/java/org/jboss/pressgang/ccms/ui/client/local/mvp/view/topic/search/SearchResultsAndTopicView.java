package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;

public class SearchResultsAndTopicView extends BaseTemplateView implements SearchResultsAndTopicPresenter.Display {

    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel topicViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel topicResultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel topicViewPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel topicResultsActionButtonsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel topicViewActionButtonsPanel = new SimpleLayoutPanel();
    
    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

    private SplitType splitType = SplitType.NONE;

    public DockLayoutPanel getResultsViewLayoutPanel() {
        return resultsViewLayoutPanel;
    }

    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    @Override
    public DockLayoutPanel getTopicViewLayoutPanel() {
        return topicViewLayoutPanel;
    }

    @Override
    public HandlerSplitLayoutPanel getSplitPanel() {
        return splitPanel;
    }

    @Override
    public SimpleLayoutPanel getTopicResultsActionButtonsPanel() {
        return topicResultsActionButtonsPanel;
    }

    @Override
    public SimpleLayoutPanel getTopicResultsPanel() {
        return topicResultsPanel;
    }

    @Override
    public SimpleLayoutPanel getTopicViewPanel() {
        return topicViewPanel;
    }

    @Override
    public SimpleLayoutPanel getTopicViewActionButtonsPanel() {
        return topicViewActionButtonsPanel;
    }

    public SearchResultsAndTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
        
        /* We have own own top action panels */
        this.getTopActionParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        resultsViewLayoutPanel.addStyleName(CSSConstants.RESULTS_VIEW_LAYOUT_PANEL);
        topicViewLayoutPanel.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);

        resultsViewLayoutPanel.addNorth(topicResultsActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);
        topicViewLayoutPanel.addNorth(topicViewActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);

        resultsViewLayoutPanel.add(topicResultsPanel);
        topicViewLayoutPanel.add(topicViewPanel);

        splitPanel.addWest(resultsViewLayoutPanel, Constants.SPLIT_PANEL_SIZE);

        topicViewActionButtonsPanel.addStyleName(CSSConstants.TOPIC_SEARCH_TOPIC_VIEW_BUTTONS_PANEL);
        topicViewPanel.addStyleName(CSSConstants.TOPIC_SEARCH_TOPIC_VIEW_DETAILS_PANEL);

        splitPanel.addStyleName(CSSConstants.TOPIC_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL);

        this.getPanel().add(splitPanel);
    }

    /**
     * The split panel needs to have the center widget added last, which we need to do after optionally added a east or south
     * widget for the rendered view.
     * 
     * @param splitType How the parent panel should be split
     * @param panel The rendered view panel itself
     */
    @Override
    public void initialize(final SplitType splitType, final Panel panel) {
        this.splitType = splitType;

        final SimplePanel renderedPanelParent = new SimplePanel();
        renderedPanelParent.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);
        renderedPanelParent.add(panel);

        if (splitType == SplitType.HORIZONTAL) {
            splitPanel.addSouth(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        } else if (splitType == SplitType.VERTICAL) {
            splitPanel.addEast(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        }

        splitPanel.add(topicViewLayoutPanel);
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
}
