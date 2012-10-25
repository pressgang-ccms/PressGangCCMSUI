package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class TagsFilteredResultsAndTagView extends BaseTemplateView implements TagsFilteredResultsAndTagPresenter.Display {
    public TagsFilteredResultsAndTagView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
    }
    
    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel resultsActionButtonsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewActionButtonsPanel = new SimpleLayoutPanel();
    
    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

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

    public TagsFilteredResultsAndTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());
        
        /* We have own own top action panels */
        this.getTopActionParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        resultsViewLayoutPanel.addStyleName(CSSConstants.RESULTS_VIEW_LAYOUT_PANEL);
        viewLayoutPanel.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);

        resultsViewLayoutPanel.addNorth(resultsActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);
        viewLayoutPanel.addNorth(viewActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);

        resultsViewLayoutPanel.add(resultsPanel);
        viewLayoutPanel.add(viewPanel);

        splitPanel.addWest(resultsViewLayoutPanel, Constants.SPLIT_PANEL_SIZE);

        viewActionButtonsPanel.addStyleName(CSSConstants.TagsFilteredResultsAndTagView.TAG_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        resultsActionButtonsPanel.addStyleName(CSSConstants.TagsFilteredResultsAndTagView.TAG_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
        viewPanel.addStyleName(CSSConstants.TOPIC_SEARCH_TOPIC_VIEW_DETAILS_PANEL);

        splitPanel.addStyleName(CSSConstants.TOPIC_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL);

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
}
